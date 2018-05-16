package com.jinhui.scheduler.biz.imiqian.processor;

import com.jinhui.scheduler.biz.core.common.IIdService;
import com.jinhui.scheduler.biz.imiqian.common.AmqClearStepConst;
import com.jinhui.scheduler.biz.imiqian.common.AmqConst;
import com.jinhui.scheduler.biz.imiqian.dto.AmqAccOpenApplyLineItem;
import com.jinhui.scheduler.biz.imiqian.utils.ConvertorUtils;
import com.jinhui.scheduler.biz.imiqian.utils.DateUtils;
import com.jinhui.scheduler.biz.imiqian.utils.EmptyUtils;
import com.jinhui.scheduler.data.core.mapper.imiqian.BatchStateMapper;
import com.jinhui.scheduler.data.core.mapper.imiqian.ChnOpenMapper;
import com.jinhui.scheduler.data.core.mapper.imiqian.ClearErrorLogMapper;
import com.jinhui.scheduler.data.core.mapper.imiqian.ClearResultMapper;
import com.jinhui.scheduler.domain.common.ChannelCode;
import com.jinhui.scheduler.domain.common.CustomerType;
import com.jinhui.scheduler.domain.imiqian.domain.BatchState;
import com.jinhui.scheduler.domain.imiqian.domain.ChnOpen;
import com.jinhui.scheduler.domain.imiqian.domain.ClearErrorLog;
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
public class AmqAccountApplyFileProcessor implements ItemProcessor<AmqAccOpenApplyLineItem, ChnOpen> {
    private final static Logger logger = LoggerFactory.getLogger(AmqAccountApplyFileProcessor.class);

    @Autowired
    private ChnOpenMapper chnOpenMapper;
    @Autowired
    BatchStateMapper batchStateMapper;
    @Autowired
    IIdService  iIdService;
    @Autowired
    ClearResultMapper clearResultMapper;
    @Autowired
    ClearErrorLogMapper clearErrorLogMapper;

    //批次号
    private int batchCode;
    //汇总日期
    private String gatherDate = "";
    private String chnCode;

    public AmqAccountApplyFileProcessor(String chnCode){
        this.chnCode = chnCode;
    }

    @Override
    public ChnOpen process(AmqAccOpenApplyLineItem amqAccOpenApplyLineItem) throws Exception {
        String error = "";
        //初始化汇总日期和批次号
        BatchState batchState = batchStateMapper.findNewest();
        batchCode = batchState.getBatchCode();
        gatherDate = String.valueOf(batchState.getDate());

        ChnOpen chnOpen = new ChnOpen();
        //申请单编号
        chnOpen.setAppSheetSerialNo(amqAccOpenApplyLineItem.getAppSheetSerialNo());
         List<ChnOpen> chnOpenList = chnOpenMapper.findList(chnOpen);
        if(chnOpenList != null && !chnOpenList.isEmpty()) {
            error = "开户申请[申请单编号="+amqAccOpenApplyLineItem.getAppSheetSerialNo()+"]已存在,取消导入";
            saveClearErrorLog(error);
        }

        String errorMsg = checkData(amqAccOpenApplyLineItem);
        if(EmptyUtils.isEmpty(errorMsg)) {
            chnOpen.setReturnCode("0000");
            chnOpen.setErrorMsg(errorMsg);
        } else {
            chnOpen.setReturnCode("9999");
            chnOpen.setErrorMsg(errorMsg);
        }

        //业务流水号
        chnOpen.setSerialNumber(iIdService.generateSerialNumber(ChannelCode.codeOf(chnCode)));

        //批次号
        chnOpen.setBatchCode(batchCode);

        //开户申请日期
        chnOpen.setApplyDate(amqAccOpenApplyLineItem.getTransactionDate()+amqAccOpenApplyLineItem.getTransactionTime());
        //开户确认日期
        /*chnOpen.setConfirmDate(DateUtils.getCurrentDate());*/
        //投资人姓名
        chnOpen.setName(amqAccOpenApplyLineItem.getInvestorName().trim());
        //渠道用户ID_对应投资人基金交易账号
        chnOpen.setChnId(amqAccOpenApplyLineItem.getTransactionAccountID().trim());   //渠道用户ID
        //客户类别
        chnOpen.setUserFlag(amqAccOpenApplyLineItem.getIndividualOrInstitution().trim());
        //证件类型
        chnOpen.setIdType(AmqConst.IdTypeCode.getExchCode(amqAccOpenApplyLineItem.getCertificateType().trim()));
        //证件号码
        chnOpen.setIdNo(amqAccOpenApplyLineItem.getCertificateNo().trim());
        //性别
        chnOpen.setSex(amqAccOpenApplyLineItem.getSex().trim());
        //投资人联系电话
        chnOpen.setPhone(amqAccOpenApplyLineItem.getMobileTelNo().trim());
        //联系地址
        chnOpen.setAddress(amqAccOpenApplyLineItem.getAddress().trim());
        //邮编
        chnOpen.setZipcode(amqAccOpenApplyLineItem.getPostCode().trim());
        //email
        chnOpen.setEmail(amqAccOpenApplyLineItem.getEmailAddress().trim());
        //传真
        chnOpen.setFax(amqAccOpenApplyLineItem.getFaxNo().trim());
        //投资人收款银行账户开户行
        chnOpen.setOpenBank(amqAccOpenApplyLineItem.getClearingAgency().trim());
        //投资人收款银行账户账号
        chnOpen.setBankAccount(amqAccOpenApplyLineItem.getAcctNoOfInvestorInClearingAgency());
        //渠道方代码 - 由我方生成
        chnOpen.setChnCode(amqAccOpenApplyLineItem.getDistributorCode().trim());
        //开户处理状态
        /*chnOpen.setOpenState("");*/
        //投资人职业
        chnOpen.setProfession(amqAccOpenApplyLineItem.getVocationCode().trim());
        //投资人学历
        chnOpen.setEducation(amqAccOpenApplyLineItem.getEducationLevel().trim());
        //投资人年收入
        if("".equals(amqAccOpenApplyLineItem.getAnnualIncome()) || amqAccOpenApplyLineItem.getAnnualIncome() == null){
            amqAccOpenApplyLineItem.setAnnualIncome("0");
        }
        chnOpen.setAnnualIncome(ConvertorUtils.convertToDouble(amqAccOpenApplyLineItem.getAnnualIncome().trim()));
        //客户风险等级
        chnOpen.setRiskLevel(amqAccOpenApplyLineItem.getClientRiskRate().trim());
        //法人代表姓名
        chnOpen.setInstReprIdName(amqAccOpenApplyLineItem.getInstReprName().trim());
        //法人代表证件类型
        chnOpen.setInstReprIdType(amqAccOpenApplyLineItem.getInstReprIDType().trim());
        //法人代表身份证件代码
        chnOpen.setInstReprIdNo(amqAccOpenApplyLineItem.getInstReprIDCode().trim());
        //法人代表证件有效期
        /*chnOpen.setInstReprExpdate("");*/
        //法人代表联系电话
        /*chnOpen.setInstReprPhone("");*/
        //机构法人经营范围
        chnOpen.setInstReprManageRange(amqAccOpenApplyLineItem.getInstReprManageRange().trim());
        //控股股东名称
        chnOpen.setControlholderName(amqAccOpenApplyLineItem.getControlHolder().trim());
        //控股股东证件类型
        /*chnOpen.setControlholderIdType("");*/
        //控股股东证件号码
        /*chnOpen.setControlholderIdNo("");*/
        //控股股东证件有效期
        /*chnOpen.setControlholderExpdate("");*/
        //行业
        chnOpen.setVocation(amqAccOpenApplyLineItem.getVocation().trim());
        //企业性质
        chnOpen.setCorpoproperty(amqAccOpenApplyLineItem.getCorpoProperty().trim());
        //员工人数
        if("".equals(amqAccOpenApplyLineItem.getStaffNum()) || amqAccOpenApplyLineItem.getStaffNum() == null){
            amqAccOpenApplyLineItem.setStaffNum("0");
        }
        chnOpen.setStaffNum(Integer.parseInt(amqAccOpenApplyLineItem.getStaffNum().trim()));
        //省/直辖市
        chnOpen.setProvince(amqAccOpenApplyLineItem.getProvince().trim());
        //市
        chnOpen.setCity(amqAccOpenApplyLineItem.getCity().trim());
        //县/区
        chnOpen.setCounty(amqAccOpenApplyLineItem.getCountry().trim());
        //注册资本
        /*chnOpen.setRegisteredCapital("");*/
        //联系人名称
        /*chnOpen.setContactName("");*/
        //联系人证件类型
        /*chnOpen.setContactIdType("");*/
        //联系人证件号码
        /*chnOpen.setContactIdNo("");*/
        //联系人证件有效期
        /*chnOpen.setContactExpdate("");*/
        //联系人电话号码
        /*chnOpen.setContactPhone("");*/
        chnOpen.setCreateTime(DateUtils.getCurrentDatetime());
        chnOpen.setUpdateTime(DateUtils.getCurrentDatetime());

        chnOpen.setZlUserId(amqAccOpenApplyLineItem.getZlUserId().trim());

        //投资人ID - 我方生成（投资人基金帐号）

        ChnOpen queryObject_ = new ChnOpen();
        queryObject_.setChnId(amqAccOpenApplyLineItem.getTransactionAccountID().trim());
        List<ChnOpen> queryObjectList = chnOpenMapper.findList(queryObject_);
        if(queryObjectList.isEmpty() || queryObjectList.size() == 0) {
            //投资人ID - 我方生成（投资人基金帐号）
            String customerType = amqAccOpenApplyLineItem.getIndividualOrInstitution().trim();
            if(AmqConst.CUSTOMER_TYPE_INSTITUTION.equals(customerType)){
                chnOpen.setInvestorId(iIdService.generateInvestorID(CustomerType.Institution));
            }else if(AmqConst.CUSTOMER_TYPE_PERSONAL.equals(customerType)){
                chnOpen.setInvestorId(iIdService.generateInvestorID(CustomerType.Personal));
            }else if(AmqConst.CUSTOMER_TYPE_SELF_SUPPORT.equals(customerType)){
                chnOpen.setInvestorId(iIdService.generateInvestorID(CustomerType.SelfSupport));
            }else{
                chnOpen.setInvestorId(iIdService.generateInvestorID(CustomerType.Personal));
            }
        } else {
            error = "开户申请[投资人基金交易帐号="+amqAccOpenApplyLineItem.getTransactionAccountID()+"]已存在，取消导入";
            saveClearErrorLog(error);
        }
        if(!"".equals(error)){
            return null;
        }
        return chnOpen;
    }

    private String checkData(AmqAccOpenApplyLineItem amqAccOpenApplyLineItem) {
        String errorHead = "开户申请[申请单编号="+amqAccOpenApplyLineItem.getAppSheetSerialNo()+"]中";
        String errorMsg = "";
        if(EmptyUtils.isEmpty(amqAccOpenApplyLineItem.getAppSheetSerialNo())) {
            errorMsg = errorHead +  "AppSheetSerialNo can not be null";
            saveClearErrorLog(errorMsg);
        }
        if(EmptyUtils.isEmpty(amqAccOpenApplyLineItem.getCertificateType())) {
            errorMsg = errorHead +  "CertificateType can not be null";
            saveClearErrorLog(errorMsg);
        }
        if(EmptyUtils.isEmpty(amqAccOpenApplyLineItem.getCertificateNo())) {
            errorMsg = errorHead +  "CertificateNo can not be null";
            saveClearErrorLog(errorMsg);
        }
        if(EmptyUtils.isEmpty(amqAccOpenApplyLineItem.getInvestorName())) {
            errorMsg = errorHead +  "InvestorName can not be null";
            saveClearErrorLog(errorMsg);
        }
        if(EmptyUtils.isEmpty(amqAccOpenApplyLineItem.getTransactionDate())) {
            errorMsg = errorHead +  "TransactionDate can not be null";
            saveClearErrorLog(errorMsg);
        }
        if(EmptyUtils.isEmpty(amqAccOpenApplyLineItem.getTransactionTime())) {
            errorMsg = errorHead +  "TransactionTime can not be null";
            saveClearErrorLog(errorMsg);
        }
        if(EmptyUtils.isEmpty(amqAccOpenApplyLineItem.getIndividualOrInstitution())) {
            errorMsg = errorHead +  "IndividualOrInstitution can not be null";
            saveClearErrorLog(errorMsg);
        }
        if(EmptyUtils.isEmpty(amqAccOpenApplyLineItem.getTransactionAccountID())) {
            errorMsg = errorHead +  "TransactionAccountID can not be null";
            saveClearErrorLog(errorMsg);
        }
        if(EmptyUtils.isEmpty(amqAccOpenApplyLineItem.getDistributorCode())) {
            errorMsg = errorHead +  "DistributorCode can not be null";
            saveClearErrorLog(errorMsg);
        }
        if(EmptyUtils.isEmpty(amqAccOpenApplyLineItem.getBusinessCode())) {
            errorMsg = errorHead +  "BusinessCode can not be null";
            saveClearErrorLog(errorMsg);
        }
        if(EmptyUtils.isEmpty(amqAccOpenApplyLineItem.getBranchCode())) {
            errorMsg = errorHead +  "BranchCode can not be null";
            saveClearErrorLog(errorMsg);
        }
        if(!AmqConst.BUSINESS_CODE_ACCOUNT_APPLY.equals(amqAccOpenApplyLineItem.getBusinessCode().trim())){
            errorMsg = errorHead +  "[业务代码="+amqAccOpenApplyLineItem.getBusinessCode()+"]业务代码错误";
            saveClearErrorLog(errorMsg);
        }
        if(!chnCode.equals(amqAccOpenApplyLineItem.getDistributorCode().trim())){
            errorMsg = errorHead +  "[销售人代码="+amqAccOpenApplyLineItem.getDistributorCode()+"]销售人代码错误";
            saveClearErrorLog(errorMsg);
        }

        /*if(EmptyUtils.isEmpty(amqAccOpenApplyLineItem.getClientRiskRate())) {
            errorMsg = "ClientRiskRate can not be null";
        }
        if(EmptyUtils.isEmpty(amqAccOpenApplyLineItem.getEmailAddress())) {
            errorMsg = "EmailAddress can not be null";
        }
        if(EmptyUtils.isEmpty(amqAccOpenApplyLineItem.getMobileTelNo())) {
            errorMsg = "MobileTelNo can not be null";
        }*/
        return errorMsg;
    }

    private ClearErrorLog saveClearErrorLog(String errorInfo){
        ClearErrorLog clearErrorLog = new ClearErrorLog();
        clearErrorLog.setBatchCode(batchCode);
        if(gatherDate == null || "".equals(gatherDate)){
            gatherDate = DateUtils.getCurrentDate();
        }
        clearErrorLog.setBatchDate(gatherDate);
        clearErrorLog.setChnCode(chnCode);
        clearErrorLog.setStepCode(AmqClearStepConst.ClearSteps.STEP_1.getClearCode());
        clearErrorLog.setStepDesc(AmqClearStepConst.ClearSteps.STEP_1.getClearDesc());
        clearErrorLog.setTargetCode(this.getClass().getSimpleName());
        clearErrorLog.setCreateTime(DateUtils.getCurrentDatetime());
        clearErrorLog.setErrorInfo(errorInfo);

        clearErrorLogMapper.save(clearErrorLog);
        return clearErrorLog;
    }

}
