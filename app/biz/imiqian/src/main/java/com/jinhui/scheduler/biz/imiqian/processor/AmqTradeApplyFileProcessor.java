package com.jinhui.scheduler.biz.imiqian.processor;

import com.jinhui.scheduler.biz.core.common.IIdService;
import com.jinhui.scheduler.biz.core.divided.service.IDividedService;
import com.jinhui.scheduler.biz.imiqian.common.AmqClearStepConst;
import com.jinhui.scheduler.biz.imiqian.common.AmqConst;
import com.jinhui.scheduler.biz.imiqian.dto.AmqTradeApplyLineItem;
import com.jinhui.scheduler.biz.imiqian.utils.ConvertorUtils;
import com.jinhui.scheduler.biz.imiqian.utils.DateUtils;
import com.jinhui.scheduler.biz.imiqian.utils.EmptyUtils;
import com.jinhui.scheduler.data.core.mapper.imiqian.*;
import com.jinhui.scheduler.domain.common.ChannelCode;
import com.jinhui.scheduler.domain.divided.InvestorDivided;
import com.jinhui.scheduler.domain.imiqian.domain.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 根据读取的开户申请文件数据构建对象
 *
 * @autor wsc
 * @create 2017-05-19 14:33
 **/
public class AmqTradeApplyFileProcessor implements ItemProcessor<AmqTradeApplyLineItem, InvestorTrans> {
    private final static Logger logger = LoggerFactory.getLogger(AmqTradeApplyFileProcessor.class);

    @Autowired
    private InvestorTransMapper investorTransMapper;
    @Autowired
    private ChnOpenMapper chnOpenMapper;
    @Autowired
    BatchStateMapper batchStateMapper;
    @Autowired
    ChnProductMapper chnProductMapper;
    @Autowired
    IIdService   iIdService;
    @Autowired
    IDividedService iDividedService;
    @Autowired
    ClearErrorLogMapper clearErrorLogMapper;

    //批次号
    int batchCode;
    //汇总日期
    String gatherDate = "";
    //渠道代码
    private String chnCode;

    public AmqTradeApplyFileProcessor(String chnCode){
        this.chnCode = chnCode;
    }

    @Override
    public InvestorTrans process(AmqTradeApplyLineItem amqTradeApplyLineItem) throws Exception {
        String error = "";
        //初始化汇总日期和批次号
        BatchState batchState = batchStateMapper.findNewest();
        batchCode = batchState.getBatchCode();
        gatherDate = String.valueOf(batchState.getDate());

        InvestorTrans investorTrans = new InvestorTrans();
        investorTrans.setAppSheetSerialNo(amqTradeApplyLineItem.getAppSheetSerialNo());
        List<InvestorTrans> investorTransList = investorTransMapper.findList(investorTrans);
        if(investorTransList != null && !investorTransList.isEmpty()) {
            error = "交易申请[申请单编号="+amqTradeApplyLineItem.getAppSheetSerialNo()+"]已存在,取消导入";
            saveClearErrorLog(error);
        }

        String errorMsg = checkData(amqTradeApplyLineItem);
        if(EmptyUtils.isEmpty(errorMsg)) {
            investorTrans.setReturnCode("0000");
            investorTrans.setErrorMsg(errorMsg);
        } else {
            investorTrans.setReturnCode("9999");
            investorTrans.setErrorMsg(errorMsg);


        }

        //业务流水号
        investorTrans.setSerialNumber(iIdService.generateSerialNumber(ChannelCode.codeOf(chnCode)));
        //批次号
        investorTrans.setBatchCode(batchStateMapper.findNewest().getBatchCode());

        //考虑第一次交易的用户 账户申请和交易申请一起传送，交易申请中TaAccountID为空，根据TransactionAccountID去获取
        String investorId = "";
        ChnOpen chnOpen = new ChnOpen();
        chnOpen.setChnId(amqTradeApplyLineItem.getTransactionAccountID().trim());
        List<ChnOpen> chnOpenList = chnOpenMapper.findList(chnOpen);
        if(!chnOpenList.isEmpty() && chnOpenList.size() != 0) {
            investorId = chnOpenList.get(0).getInvestorId();
        }else{
            error = "交易申请[申请单编号="+amqTradeApplyLineItem.getAppSheetSerialNo()+"]中[投资人基金交易帐号="+amqTradeApplyLineItem.getTransactionAccountID()+"]查无记录 ,取消导入";
            saveClearErrorLog(error);
        }
        //投资人基金账号
        investorTrans.setInvestorId(investorId);
        //投资人基金交易帐号
        investorTrans.setChnId(amqTradeApplyLineItem.getTransactionAccountID().trim());
        //投资人
        if(!chnOpenList.isEmpty() && chnOpenList.size() != 0) {
            investorTrans.setName(chnOpenList.get(0).getName().trim());
        }
        //交易时间
        investorTrans.setTransTime(amqTradeApplyLineItem.getTransactionDate()+amqTradeApplyLineItem.getTransactionTime());
        //基金代码
        investorTrans.setProductNo(amqTradeApplyLineItem.getFundCode().trim());

        List<ChnProduct> chnProductList = chnProductMapper.findList(amqTradeApplyLineItem.getFundCode().trim(),amqTradeApplyLineItem.getDistributorCode().trim());
        //子产品代码
        if(!chnOpenList.isEmpty() && chnOpenList.size() != 0) {
            if(!chnProductList.isEmpty() && chnProductList.size() != 0){
                InvestorDivided investorDivided = new InvestorDivided(investorId,chnOpenList.get(0).getName().trim(),amqTradeApplyLineItem.getFundCode().trim());
                investorTrans.setChildProductNo(iDividedService.getInvestorDivided(investorDivided));
            }else{
                error = "交易申请[申请单编号="+amqTradeApplyLineItem.getAppSheetSerialNo()+"][销售人代码="+amqTradeApplyLineItem.getDistributorCode()+"][基金代码="+amqTradeApplyLineItem.getFundCode()+"]查无该产品记录 ,取消导入";
                saveClearErrorLog(error);
            }
        }

        if(!chnProductList.isEmpty() && chnProductList.size() != 0) {
            //产品名称
            investorTrans.setProductName(chnProductList.get(0).getProductName());
            //产品每份份额
            if(!"".equals(chnProductList.get(0).getProductVol())){
                investorTrans.setProductVol(chnProductList.get(0).getProductVol());
            }else{
                error = "交易申请[申请单编号="+amqTradeApplyLineItem.getAppSheetSerialNo()+"][产品编号="+amqTradeApplyLineItem.getFundCode()+"]中产品每份金额[product_vol]为空 ,取消导入";
                saveClearErrorLog(error);
            }

        }

        //业务代码
        investorTrans.setTransType(amqTradeApplyLineItem.getBusinessCode().trim());
        //交易份额
        investorTrans.setTransVol(ConvertorUtils.convertToDouble(amqTradeApplyLineItem.getApplicationVol().trim()));
        //交易金额
        investorTrans.setTransAmount(ConvertorUtils.convertToDouble(amqTradeApplyLineItem.getApplicationAmount().trim()));
        //手续费
        if("".equals(amqTradeApplyLineItem.getCharge()) || amqTradeApplyLineItem.getCharge() == null){
            amqTradeApplyLineItem.setCharge("0");
        }
        investorTrans.setTransFix(ConvertorUtils.convertToDouble(amqTradeApplyLineItem.getCharge().trim()));
        //带走收益标志
        investorTrans.setTakeIncomeFlag(amqTradeApplyLineItem.getTakeIncomeFlag());
        //巨额购买处理标志
        investorTrans.setHugeSubsFlag(amqTradeApplyLineItem.getLargeBuyFlag());
        //巨额赎回处理标志
        investorTrans.setHugeRedemFlag(amqTradeApplyLineItem.getLargeRedemptionFlag());
        //销售人代码
        investorTrans.setChnCode(amqTradeApplyLineItem.getDistributorCode().trim());
        //用户是否签署风险揭示书：0-已签署，1-未签署
        /*investorTrans.setRiskDisclosure();*/
        //交易银行名称
        /*investorTrans.setTransBankName();*/
        //交易银行账号
        /*investorTrans.setTransBankAct();*/
        //收费方式
        investorTrans.setChargeType(amqTradeApplyLineItem.getChargeType());
        //收费类型
        investorTrans.setChargeWay(amqTradeApplyLineItem.getShareClass());
        //指定费率
        if("".equals(amqTradeApplyLineItem.getSpecifyRateFee()) || amqTradeApplyLineItem.getSpecifyRateFee() == null){
            amqTradeApplyLineItem.setSpecifyRateFee("0");
        }
        investorTrans.setChargeRate(ConvertorUtils.convertToDouble(amqTradeApplyLineItem.getSpecifyRateFee().trim()));
        //指定费用
        if("".equals(amqTradeApplyLineItem.getSpecifyFee()) || amqTradeApplyLineItem.getSpecifyFee() == null){
            amqTradeApplyLineItem.setSpecifyFee("0");
        }
        investorTrans.setSpecifyFee(ConvertorUtils.convertToDouble(amqTradeApplyLineItem.getSpecifyFee().trim()));
        //滚存标志
        /*investorTrans.setRollingFlag();
        //强行赎回原因
        investorTrans.setForceRedemReason();
        //强制赎回类型
        investorTrans.setForceRedemType();
        //交易处理状态
        investorTrans.setTransState();*/
        investorTrans.setCreateTime(DateUtils.getCurrentDatetime());
        investorTrans.setUpdateTime(DateUtils.getCurrentDatetime());

        investorTrans.setZlFundSeqId(amqTradeApplyLineItem.getZlFundSeqId().trim());
        investorTrans.setRedeemFlag(amqTradeApplyLineItem.getRedeemFlag().trim());
        investorTrans.setTransBankCode(amqTradeApplyLineItem.getTransBankCode().trim());
        investorTrans.setTransBankAct(amqTradeApplyLineItem.getTransBankAct().trim());

        if(!"".equals(error)){
            return  null;
        }

        return investorTrans;
    }

    private String checkData(AmqTradeApplyLineItem amqTradeApplyLineItem) {
        String errorHead = "交易申请[申请单编号="+amqTradeApplyLineItem.getAppSheetSerialNo()+"]中";
        String errorMsg= "";
        if(EmptyUtils.isEmpty(amqTradeApplyLineItem.getAppSheetSerialNo())) {
            errorMsg = errorHead +  "AppSheetSerialNo can not be null";
            saveClearErrorLog(errorMsg);
        }
        if(EmptyUtils.isEmpty(amqTradeApplyLineItem.getFundCode())) {
            errorMsg = errorHead +  "FundCode can not be null";
            saveClearErrorLog(errorMsg);
        }
        if(EmptyUtils.isEmpty(amqTradeApplyLineItem.getTransactionDate())) {
            errorMsg = errorHead +  "TransactionDate can not be null";
            saveClearErrorLog(errorMsg);
        }
        if(EmptyUtils.isEmpty(amqTradeApplyLineItem.getTransactionTime())) {
            errorMsg = errorHead +  "TransactionTime can not be null";
            saveClearErrorLog(errorMsg);
        }
        if(EmptyUtils.isEmpty(amqTradeApplyLineItem.getTransactionAccountID())) {
            errorMsg = errorHead +  "TransactionAccountID can not be null";
            saveClearErrorLog(errorMsg);
        }
        if(EmptyUtils.isEmpty(amqTradeApplyLineItem.getDistributorCode())) {
            errorMsg = errorHead +  "DistributorCode can not be null";
            saveClearErrorLog(errorMsg);
        }
        if(EmptyUtils.isEmpty(amqTradeApplyLineItem.getApplicationVol())) {
            errorMsg = errorHead +  "ApplicationVol can not be null";
            saveClearErrorLog(errorMsg);
        }
        if(EmptyUtils.isEmpty(amqTradeApplyLineItem.getApplicationAmount())) {
            errorMsg = errorHead +  "ApplicationAmount can not be null";
            saveClearErrorLog(errorMsg);
        }
        if(EmptyUtils.isEmpty(amqTradeApplyLineItem.getBusinessCode())) {
            errorMsg = errorHead +  "BusinessCode can not be null";
            saveClearErrorLog(errorMsg);
        }
        /*if(EmptyUtils.isEmpty(amqTradeApplyLineItem.getTaAccountID())) {
            errorMsg = errorHead +  "TAAccountID can not be null";
            saveClearErrorLog(errorMsg);
        }*/
        if(EmptyUtils.isEmpty(amqTradeApplyLineItem.getCurrencyType())) {
            errorMsg = errorHead +  "CurrencyType can not be null";
            saveClearErrorLog(errorMsg);
        }
        if(EmptyUtils.isEmpty(amqTradeApplyLineItem.getBranchCode())) {
            errorMsg = errorHead +  "BranchCode can not be null";
            saveClearErrorLog(errorMsg);
        }
        if(ConvertorUtils.convertToDouble(amqTradeApplyLineItem.getApplicationAmount().trim()) == 0){
            errorMsg = errorHead +  "交易金额不能为0";
            saveClearErrorLog(errorMsg);
        }
        if(ConvertorUtils.convertToDouble(amqTradeApplyLineItem.getApplicationVol().trim()) == 0){
            errorMsg = errorHead +  "交易份额不能为0";
            saveClearErrorLog(errorMsg);
        }
        if(!AmqConst.BUSINESS_CODE_PURCHASE_APPLY.equals(amqTradeApplyLineItem.getBusinessCode().trim()) && !AmqConst.BUSINESS_CODE_REDEEM_APPLY.equals(amqTradeApplyLineItem.getBusinessCode().trim())){
            errorMsg = errorHead +  "[业务代码="+amqTradeApplyLineItem.getBusinessCode()+"]业务代码错误";
            saveClearErrorLog(errorMsg);
        }
        if(!chnCode.equals(amqTradeApplyLineItem.getDistributorCode().trim())){
            errorMsg = errorHead +  "[销售人代码="+amqTradeApplyLineItem.getDistributorCode()+"]销售人代码错误";
            saveClearErrorLog(errorMsg);
        }
        return errorMsg;
    }

    private void saveClearErrorLog(String errorInfo){
        ClearErrorLog clearErrorLog = new ClearErrorLog();
        clearErrorLog.setBatchCode(batchCode);
        if(gatherDate == null || "".equals(gatherDate)){
            gatherDate = DateUtils.getCurrentDate();
        }
        clearErrorLog.setBatchDate(gatherDate);
        clearErrorLog.setChnCode(chnCode);
        clearErrorLog.setStepCode(AmqClearStepConst.ClearSteps.STEP_2.getClearCode());
        clearErrorLog.setStepDesc(AmqClearStepConst.ClearSteps.STEP_2.getClearDesc());
        clearErrorLog.setErrorInfo(errorInfo);
        clearErrorLog.setTargetCode(this.getClass().getSimpleName());
        clearErrorLog.setCreateTime(DateUtils.getCurrentDatetime());

        clearErrorLogMapper.save(clearErrorLog);
    }

}
