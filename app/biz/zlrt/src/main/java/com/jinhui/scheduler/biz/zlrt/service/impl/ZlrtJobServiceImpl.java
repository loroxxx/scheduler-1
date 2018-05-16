package com.jinhui.scheduler.biz.zlrt.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jinhui.scheduler.biz.core.common.IFileDirectoryService;
import com.jinhui.scheduler.biz.core.common.IIdService;
import com.jinhui.scheduler.biz.shared.launch.support.SyncJobLauncher;
import com.jinhui.scheduler.biz.zlrt.common.Result;
import com.jinhui.scheduler.biz.zlrt.common.ZlBalanceResultConst;
import com.jinhui.scheduler.biz.zlrt.common.ZlrtClearStepConst;
import com.jinhui.scheduler.biz.zlrt.common.ZlrtFileConstants;
import com.jinhui.scheduler.biz.zlrt.dto.SendFileResult;
import com.jinhui.scheduler.biz.zlrt.service.ZlrtJobService;
import com.jinhui.scheduler.biz.zlrt.utils.DateUtils;
import com.jinhui.scheduler.biz.zlrt.utils.zlrt.AnnounceRequest;
import com.jinhui.scheduler.biz.zlrt.utils.zlrt.AnnounceResponse;
import com.jinhui.scheduler.biz.zlrt.utils.zlrt.ZlrtHttpUtils;
import com.jinhui.scheduler.data.core.mapper.zlrt.*;
import com.jinhui.scheduler.domain.common.ChannelCode;
import com.jinhui.scheduler.domain.common.InstitutionType;
import com.jinhui.scheduler.domain.zlrt.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @autor wsc
 * @create 2017-11-24 10:07
 **/
@Service("zlrtJobService")
public class ZlrtJobServiceImpl implements ZlrtJobService{
    private final static Logger logger = LoggerFactory.getLogger(ZlrtJobServiceImpl.class);

    @Autowired
    private SyncJobLauncher syncJobLauncher;
    @Autowired
    private JobRegistry jobRegistry;
    @Autowired
    private BatchStateMapperZlrt batchStateMapper;
    @Autowired
    private BatchStateCurrentMapperZlrt batchStateCurrentMapperZlrt;
    @Autowired
    private IFileDirectoryService iFileDirectoryService;
    @Autowired
    private ClearResultMapperZlrt clearResultMapper;
    @Autowired
    private ZlBalanceAccBillMapper zlBalanceAccBillMapper;
    @Autowired
    private InvestorTransMapperZlrt investorTransMapperZlrt;
    @Autowired
    private ZlBalanceAccBillResultMapper zlBalanceAccBillResultMapper;
    @Autowired
    InvestorTransMapperZlrt investorTransMapper;
    @Autowired
    private ExchangeInfoMapperZlrt exchangeInfoMapperZlrt;
    @Autowired
    private IIdService iIdService;

    /**
     * 导入对账单
     * @return
     * @throws Exception
     */
    @Override
    public Result importBalanceAcctFile() throws Exception {
        logger.info("-----------------------   开始导入证联对账单 ------------------------");
        Job job = jobRegistry.getJob("zlrtStatementJob");
        BatchState batchState = batchStateMapper.findNewest();
        logger.info("批次： batchCode=" +batchState.getBatchCode() +"  batchDate="+batchState.getDate());
        String workspacePath = iFileDirectoryService.getWorkspaceDownloadFileDir(InstitutionType.ZLRT);
        ClearResult clearResult = new ClearResult();
        clearResult.setBatchCode(batchState.getBatchCode());
        clearResult.setChnCode(ZlBalanceResultConst.CODE);
        //查询证联对账单是否已导入
        int succCount = clearResultMapper.findBillImportCount(clearResult);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new SimpleDateFormat("yyyyMMdd").parse(batchState.getDate()));

        if(succCount == 0){
            JobParameters parameters = new JobParametersBuilder()
                    .addString("workspacePath", workspacePath)
                    .addString("applyDate", batchState.getDate())
                    .addString("chnCode", ZlBalanceResultConst.CODE)
                   // .addString("chnName", ChannelCode.codeOf(ZlBalanceResultConst.CODE).getType().getAbbr())
                    .addString("timestamp", System.currentTimeMillis()+"")
                    .toJobParameters();
            JobExecution jobExecution = syncJobLauncher.run(job, parameters);

            int count = clearResultMapper.findBillImportCount(clearResult);

            //判断证联对账单文件是否导入成功
            if("COMPLETED".equals(jobExecution.getStatus().toString()) && (count == 1)){
                  logger.info("-----------------------   证联对账单导入结束 ------------------------");
                  return Result.success("对账单导入成功!");
              }else{
                logger.info("-----------------------   证联对账单导入结束 ------------------------");
                return Result.fail("对账单导入失败!");
            }
        }else{
            logger.info("-----------------------   证联对账单导入结束 ------------------------");
            return Result.success("对账单导入成功!");
        }

    }

    /**
     * 查询导入的对账单列表
     * @return
     */
    public PageInfo<ZlBalanceAccBill> queryImportAcctList(Integer pageNum, Integer pageSize){
        BatchState batchState = batchStateMapper.findNewest();
        logger.info("批次： batchCode=" +batchState.getBatchCode() +"  batchDate="+batchState.getDate());

        int pageNumOut = pageNum == null ? 1 : pageNum;
        int pageSizeOut = pageSize == null ? 10 : pageSize;
        PageHelper.startPage(pageNumOut, pageSizeOut);

        List<ZlBalanceAccBill> list = zlBalanceAccBillMapper.queryListAfterImport(batchState.getBatchCode());
        logger.info("size: "+ list.size());
        return new PageInfo<ZlBalanceAccBill>(list);
    }

    /**
     * 对账
     * @return
     */
    @Override
    public Result balanceAcct() {
        logger.info("-----------------------   开始证联对账 ------------------------");
        //查询最新的批次信息
        BatchState batchState = batchStateMapper.findNewest();
        logger.info("批次： batchCode=" +batchState.getBatchCode() +"  batchDate="+batchState.getDate());

        ClearResult clearResult = new ClearResult();
        clearResult.setBatchCode(batchState.getBatchCode());
        clearResult.setChnCode(ZlBalanceResultConst.CODE);
        //查询证联对账是否已执行
        int succCount = clearResultMapper.findBalanceResultCount(clearResult);
        if(succCount == 0) {
            //将T日交易数据复制到临时表用于对账
            bakTransData(batchState);

            List<ZlBalanceAccBill> list = zlBalanceAccBillMapper.queryListAfterImport(batchState.getBatchCode());
            logger.info("***************  对账单记录数： *********** " + list.size());

            //将证联对账单数据和交易数据对账
            for (ZlBalanceAccBill balanceAccBill : list) {
                ZlBalanceAccBillResult zbar = new ZlBalanceAccBillResult();
                zbar.setBatchCode(batchState.getBatchCode());
                zbar.setFundDate(balanceAccBill.getFundDate());
                zbar.setFundTime(balanceAccBill.getFundTime());
                zbar.setUserName(balanceAccBill.getUserNameText());
                zbar.setUserId(balanceAccBill.getUserId());
                zbar.setFundCode(balanceAccBill.getFundCode());
                zbar.setZlBusiType(balanceAccBill.getBusiType());
                zbar.setZlTransAmt(balanceAccBill.getTransAmt());
                zbar.setZlTransState(ZlBalanceResultConst.SUMMITTED);
                zbar.setZlFundSeqId(balanceAccBill.getFundSeqId());

                //对账逻辑：根据证联对账单中的商户系统流水号去核对每笔流水信息，与系统划转表所存储的交易信息（交易日期、交易金额、产品代码）、用户信息（用户姓名、身份证号）核对
                InvestorTrans trans = new InvestorTrans();
                trans.setZlFundSeqId(balanceAccBill.getFundSeqId());
                //先查询对账单数据是否存在T日交易流水中
                List<InvestorTrans> transTempList = investorTransMapperZlrt.findTransTempList(trans);
                if (transTempList.size() == 1) {
                    justify(transTempList, zbar, balanceAccBill);
                } else {
                    //如果不存在，则交易流水表全表查询是否存在
                    List<InvestorTrans> transList = investorTransMapperZlrt.findTransList(trans);
                    if (transList.size() == 1) {
                        justify(transList, zbar, balanceAccBill);
                    } else {
                        //都不存在，则记录“证联端单边”
                        zbar.setNotEqualFlag(ZlBalanceResultConst.NotEqualFlag.NOT_EQUAL_WEALTH.getCode());
                        zbar.setResultType(ZlBalanceResultConst.NOT_EQUAL);
                        zbar.setResultDesc(ZlBalanceResultConst.NotEqualFlag.returnDesc(ZlBalanceResultConst.NotEqualFlag.NOT_EQUAL_WEALTH.getCode()));
                        zlBalanceAccBillResultMapper.insertSelective(zbar);
                    }
                }
            }

            //查询T日交易数据中不存在证联对账单中的数据, 记录对账数据为”渠道端单边“
            List<InvestorTrans> notExistBalanceDataList = investorTransMapperZlrt.findNotExistBalanceData();
            for (InvestorTrans investorTrans : notExistBalanceDataList) {
                ZlBalanceAccBillResult zbar = new ZlBalanceAccBillResult();
                zbar.setFundCode(investorTrans.getProductNo());
                zbar.setBatchCode(batchState.getBatchCode());
                zbar.setFundDate(investorTrans.getTransTime().substring(0, 8));
                zbar.setFundTime(investorTrans.getTransTime().substring(8, 14));
                zbar.setUserName(investorTrans.getName());
                zbar.setNotEqualFlag(ZlBalanceResultConst.NotEqualFlag.NOT_EQUAL_CHANNEL.getCode());
                zbar.setInvestorId(investorTrans.getInvestorId());
                zbar.setTransBankAct(investorTrans.getTransBankAct());
                zbar.setTransBankCode("000");   //待确认  交易银行代码
                zbar.setBusiType(investorTrans.getTransType());
                zbar.setTransAmt(new BigDecimal(investorTrans.getTransAmount()));
                zbar.setTransState(ZlBalanceResultConst.SUMMITTED);
                zbar.setFundSeqId(investorTrans.getSerialNumber());
                zbar.setResultType(ZlBalanceResultConst.NOT_EQUAL);
                zbar.setResultDesc(ZlBalanceResultConst.NotEqualFlag.returnDesc(ZlBalanceResultConst.NotEqualFlag.NOT_EQUAL_CHANNEL.getCode()));
                zlBalanceAccBillResultMapper.insertSelective(zbar);
            }
            //记录对账完成状态
            clearResultMapper.save(generateClearResult(ZlrtClearStepConst.STEPS.STEP_16.toString(), ZlrtClearStepConst.ClearSteps.STEP_16.getClearCode(), ZlrtClearStepConst.ClearSteps.STEP_16.getClearDesc(), batchState, ZlBalanceResultConst.CODE));
        }else{
            logger.info("[批次号="+batchState.getBatchCode()+"]的数据已执行过流程[证联对账单对账]");
        }
        logger.info("-----------------------   证联对账结束 ------------------------");
        return Result.success("对账完成!");
    }

    /**
     * 查询对账结果列表
     * @return
     */
    public PageInfo<ZlBalanceAccBillResult> queryBalanceResultList(Integer pageNum, Integer pageSize){
        BatchState batchState = batchStateMapper.findNewest();
        logger.info("批次： batchCode=" +batchState.getBatchCode() +"  batchDate="+batchState.getDate());

        int pageNumOut = pageNum == null ? 1 : pageNum;
        int pageSizeOut = pageSize == null ? 10 : pageSize;
        PageHelper.startPage(pageNumOut, pageSizeOut);

        List<ZlBalanceAccBillResult> list = zlBalanceAccBillResultMapper.queryListAfterBalance(batchState.getBatchCode());
        logger.info("size: "+ list.size());
        return new PageInfo<ZlBalanceAccBillResult>(list);
    }

    /**
     * 调账处理
     */
    public Result chargeToCorrect(){
        logger.info("-----------------------   调账处理开始 ------------------------");
        //查询T日交易数据中不存在证联对账单中的数据, 将状态改为“失败”
        /*List<InvestorTrans> notExistBalanceDataList = investorTransMapperZlrt.findNotExistBalanceData();
        for (InvestorTrans investorTrans : notExistBalanceDataList) {
            investorTransMapperZlrt.updateStateToFail(investorTrans.getId());
            logger.info("交易记录ID：" + investorTrans.getId() +"  "+investorTrans.getTransAmount());
        }*/
        logger.info("-----------------------   调账处理结束 ------------------------");
        return Result.success("调账处理完成!");
    }

    /**
     * 调账后检查
     */
    public Result checkUpAfterCharge(){
        logger.info("-----------------------   调账后检查开始 ------------------------");

        logger.info("-----------------------   调账后检查结束 ------------------------");
        return Result.success("调账后检查完成!");
    }


    /**
     * 给证联报送赎回文件
     * @return
     * @throws Exception
     */
    public Result sendRedeemFile() throws Exception {
        logger.info("-----------------------   开始给证联报送赎回文件 ------------------------");
        Job job = jobRegistry.getJob("zlrtRedeemJob");
        BatchStateCurrent batchStateCurrent = batchStateCurrentMapperZlrt.findCurrentBatch();
        logger.info("批次： batchCode=" +batchStateCurrent.getBatchCode() +"  batchDate="+batchStateCurrent.getDate());
        String generatePath = iFileDirectoryService.getWorkspaceUploadFileDir(InstitutionType.ZLRT);

        ClearResult clearResult = new ClearResult();
        clearResult.setBatchCode(batchStateCurrent.getBatchCode());

        List<ExchangeInfo> exchangeInfoList = exchangeInfoMapperZlrt.selectAllExchangeList();
        //查询清算步骤记录数，当天批次清算完了才能导出
        int clearCount = clearResultMapper.findClearCount(clearResult);
        if(clearCount == 6){
            //查询赎回记录数
            int succCount = clearResultMapper.findRedeemCount(clearResult);
            if(succCount == 0){
                for(ExchangeInfo exchangeInfo:exchangeInfoList) {

                    JobParameters parameters = new JobParametersBuilder()
                            .addString("generatePath", generatePath)
                            .addString("batchCode", batchStateCurrent.getBatchCode() + "")
                            .addString("chnCode", exchangeInfo.getExchangeCode())
                            .addString("applyDate", batchStateCurrent.getDate())
                            .addString("chnName", "zlrt")    //ftp发送文件到对方服务器使用标志
                            .addString("zlInstuId", exchangeInfo.getZlInstuId())
                            .addString("xuhao",ZlrtFileConstants.xuhao)
                            .addString("timestamp", System.currentTimeMillis() + "")
                            .toJobParameters();

                    JobExecution jobExecution = syncJobLauncher.run(job, parameters);
                }
            }else{
                logger.info("【批次日期="+batchStateCurrent.getDate()+"】赎回文件已报送！");
            }
        }else{
            logger.info("【批次日期="+batchStateCurrent.getDate()+"】尚未清算或清算失败，无法给证联报送赎回文件！");
        }


        int count = clearResultMapper.findRedeemCount(clearResult);
        if(count == 0){
            logger.info("-----------------------   给证联报送赎回文件结束 ------------------------");
            return Result.fail("赎回文件报送失败!");
        }else if(count == exchangeInfoList.size()){
            logger.info("-----------------------   给证联报送赎回文件结束 ------------------------");
            return Result.success("赎回文件报送成功!");
        }else{
            logger.info("-----------------------   给证联报送赎回文件结束 ------------------------");
            return Result.fail("赎回文件报送失败!");
        }

    }

    /**
     * 报送监管文件
     * @return
     * @throws Exception
     */
    public Result sendBankFile() throws Exception {
        logger.info("-----------------------   开始报送监管文件 ------------------------");

        BatchStateCurrent batchStateCurrent = batchStateCurrentMapperZlrt.findCurrentBatch();
        logger.info("批次： batchCode=" +batchStateCurrent.getBatchCode() +"  batchDate="+batchStateCurrent.getDate());
        String generatePath = iFileDirectoryService.getWorkspaceUploadFileDir(InstitutionType.ZLRT);

        ClearResult clearResult = new ClearResult();
        clearResult.setBatchCode(batchStateCurrent.getBatchCode());

        //查询交易所列表
        List<ExchangeInfo> exchangeInfoList = exchangeInfoMapperZlrt.selectAllExchangeList();
        //查询清算步骤记录数，当天批次清算完了才能导出
        int clearCount = clearResultMapper.findClearCount(clearResult);
        if(clearCount == 6) {
            //查询监管结算文件报送记录数
            int succCount = clearResultMapper.findBankCount(clearResult);
            if (succCount == 0) {
                for (ExchangeInfo exchangeInfo : exchangeInfoList) {

                    Job job = jobRegistry.getJob("zlrtBankJob");
                    JobParameters parameters = new JobParametersBuilder()
                            .addString("generatePath", generatePath)
                            .addString("batchCode", batchStateCurrent.getBatchCode() + "")
                            .addString("chnCode", exchangeInfo.getExchangeCode())
                            .addString("applyDate", batchStateCurrent.getDate())
                            .addString("chnName", "zlrt")    //ftp发送文件到对方服务器使用标志
                            .addString("zlInstuId", exchangeInfo.getZlInstuId())
                            .addString("xuhao",ZlrtFileConstants.xuhao)
                            .addString("timestamp", System.currentTimeMillis() + "")
                            .toJobParameters();
                    JobExecution jobExecution = syncJobLauncher.run(job, parameters);
                }
            }else{
                logger.info("【批次日期="+batchStateCurrent.getDate()+"】监管结算文件已报送！");
            }
        }else{
            logger.info("【批次日期="+batchStateCurrent.getDate()+"】尚未清算或清算失败，无法给证联报送监管结算文件！");
        }

        int count = clearResultMapper.findBankCount(clearResult);
        //判断证联对账单文件是否导入成功
        if(count == 0){
            logger.info("-----------------------   报送监管文件 结束 ------------------------");
            return Result.success("监管文件报送失败!");
        }else if(count == exchangeInfoList.size() * 2){
            logger.info("-----------------------   报送监管文件 结束 ------------------------");
            return Result.success("监管文件报送成功!");
        }else{
            logger.info("-----------------------   报送监管文件 结束 ------------------------");
            return Result.fail("监管文件报送失败!");
        }
    }


    @Override
    public Result afterSendList() {
    //查询交易所列表
        List<ExchangeInfo> exchangeInfoList = exchangeInfoMapperZlrt.selectAllExchangeList();
        List list = new ArrayList();
        for(ExchangeInfo exchangeInfo:exchangeInfoList){
            int redeemNum = 0;
            int bankRedeemNum = 0;
            int bankPurchaseNum = 0;
            Double bankRedeemAmount = new Double(0);
            Double bankPurchaseAmount = new Double(0);
            String bankSendStr = "未完成";
            String announceStr = "未完成";
            BatchStateCurrent batchState = batchStateCurrentMapperZlrt.findCurrentBatch();
            SendFileResult  sfr = new SendFileResult();
            ClearResult clearResult = new ClearResult();
            clearResult.setBatchCode(batchState.getBatchCode());
            clearResult.setChnCode(exchangeInfo.getExchangeCode());
            //查询证联赎回报送的记录
            ClearResult redeemRecord = clearResultMapper.findRedeemRecord(clearResult);
            if(redeemRecord != null){
                if("0".equals(redeemRecord.getStatus())){
                    InvestorTrans investorTrans = investorTransMapperZlrt.findRedeemCountSum(String.valueOf(batchState.getBatchCode()),exchangeInfo.getZlInstuId());
                    redeemNum = Integer.parseInt(investorTrans.getRedeemCount());  //赎回报送成功的笔数
                    sfr.setRedeemSendResult("完成");
                    sfr.setRedeemSendContent("交易 "+redeemNum+" 笔");
                }else{
                    sfr.setRedeemSendResult("未完成");
                    sfr.setRedeemSendContent("交易 "+redeemNum+" 笔");
                }
            }else{
                sfr.setRedeemSendResult("未完成");
                sfr.setRedeemSendContent("交易 "+redeemNum+" 笔");
            }


            //查询监管申购报送的记录
            ClearResult bankPurchaseRecord = clearResultMapper.findBankPurchaseRecord(clearResult);
            if(bankPurchaseRecord != null){
                if("0".equals(bankPurchaseRecord.getStatus())){
                    InvestorTrans investorTrans = investorTransMapperZlrt.findPurchaseCountSum(String.valueOf(batchState.getBatchCode()),exchangeInfo.getZlInstuId());
                    bankPurchaseNum = Integer.parseInt(investorTrans.getPurchaseCount());  //监管申购报送成功的笔数
                    bankPurchaseAmount = Double.parseDouble(investorTrans.getPurchaseSum());
                }
            }


            //查询监管赎回报送的记录
            ClearResult bankRedeemRecord = clearResultMapper.findBankRedeemRecord(clearResult);
             if(bankRedeemRecord != null){
                 if("0".equals(bankRedeemRecord.getStatus())){
                     InvestorTrans investorTrans = investorTransMapperZlrt.findRedeemCountSum(String.valueOf(batchState.getBatchCode()),exchangeInfo.getZlInstuId());
                     bankRedeemNum = Integer.parseInt(investorTrans.getRedeemCount());  //监管赎回报送成功的笔数
                     bankRedeemAmount = Double.parseDouble(investorTrans.getRedeemSum());
                     bankSendStr = "完成";
                 }
             }

            sfr.setInstuId(exchangeInfo.getZlInstuId());
            sfr.setBankSendResult(bankSendStr);
            sfr.setBankSendContent("申购交易"+bankPurchaseNum+"笔, "+"总金额：" + bankPurchaseAmount +"|" + "赎回交易"+bankRedeemNum+"笔, "+"总金额：" + bankRedeemAmount);


            clearResult.setChnCode(ZlBalanceResultConst.CODE);
            //查询是否已通知证联
            int count = clearResultMapper.findAnnounceCount(clearResult);
            if(count == 1){
              announceStr = "完成";
            }
            sfr.setAutoZlResult(announceStr);

            list.add(sfr);
        }

        return Result.successData(list);
    }

    /**
     * 报送成功之后通知证联
     * @return
     */
    public Result announce() {
        BatchStateCurrent batchStateCurrent = batchStateCurrentMapperZlrt.findCurrentBatch();
        ClearResult clearResult = new ClearResult();
        clearResult.setBatchCode(batchStateCurrent.getBatchCode());

        //查询交易所列表
        List<ExchangeInfo> exchangeInfoList = exchangeInfoMapperZlrt.selectAllExchangeList();
        List list = new ArrayList();
        //查询是否已通知证联
        int count = clearResultMapper.findAnnounceCount(clearResult);
        if(count == 0){
            for(ExchangeInfo exchangeInfo:exchangeInfoList){
                //查询当前交易所已报送证联的文件数量
                clearResult.setChnCode(exchangeInfo.getExchangeCode());
                int succCount = clearResultMapper.findSendCount(clearResult);
                if(succCount == 3){
                    for(int i=0;i< ZlrtFileConstants.sendFileArray.length;i++){
                        AnnounceRequest announceRequest = new AnnounceRequest();
                        announceRequest.setInstuId(ZlrtHttpUtils.getInstuId());
                        announceRequest.setFundDate(batchStateCurrent.getDate());
                        announceRequest.setFundTime(DateUtils.getCurrentTime());
                        announceRequest.setFundSeqId(iIdService.generateSerialNumber(ChannelCode.codeOf(ChannelCode.XWBank.getCode())));
                        announceRequest.setFileName(ZlrtFileConstants.sendFileArray[i]+"_"+ZlrtHttpUtils.getInstuId()+"_"+batchStateCurrent.getDate()+"_"+ZlrtFileConstants.xuhao+".txt");
                        announceRequest.setFilePath(ZlrtFileConstants.filePathArray[i]);
                        announceRequest.setBusiType(ZlrtFileConstants.fileTypeArray[i]);
                        try {
                            AnnounceResponse announceResponse =  ZlrtHttpUtils.announce(announceRequest);
                            list.add(announceResponse);
                        } catch (Exception e) {
                            logger.info(e.getMessage());
                            return Result.fail(e.getMessage());
                        }

                    }
                }

            }

            BatchState batchState = new BatchState();
            batchState.setBatchCode(batchStateCurrent.getBatchCode());
            batchState.setDate(batchStateCurrent.getDate());
            //记录对账完成状态
            clearResultMapper.save(generateClearResult(ZlrtClearStepConst.STEPS.STEP_20.toString(), ZlrtClearStepConst.ClearSteps.STEP_20.getClearCode(), ZlrtClearStepConst.ClearSteps.STEP_20.getClearDesc(), batchState, ZlBalanceResultConst.CODE));

        }else{
            logger.info("[批次号="+batchStateCurrent.getBatchCode()+"]的数据已执行过流程[通知证联]");
        }
            return Result.successData(list);
    }


    //将T日交易数据复制到临时表
    private int bakTransData(BatchState batchState){
        //清空交易临时表
        investorTransMapper.deleteTransTemp();

        //备份T日交易数据至临时表
        InvestorTrans investorTrans = new InvestorTrans();
        investorTrans.setBatchCode(batchState.getBatchCode());
        //investorTrans.setChnCode(ChannelCode.Imiqian.getCode());
        int count = investorTransMapper.transferDataToTemp(investorTrans);

        logger.info("将T日交易数据复制到临时表记录总数： " + count);
        return count;
    }

    private void justify(List<InvestorTrans> list,ZlBalanceAccBillResult zbar,ZlBalanceAccBill balanceAccBill) {
        InvestorTrans transPo = list.get(0);
        zbar.setNotEqualFlag(ZlBalanceResultConst.NotEqualFlag.NOT_EQUAL_DOUBLE.getCode());
        zbar.setInvestorId(transPo.getInvestorId());
        zbar.setTransBankAct(transPo.getTransBankAct());
        zbar.setTransBankCode("000");   //待确认  交易银行代码
        zbar.setBusiType(transPo.getTransType());
        zbar.setTransAmt(new BigDecimal(transPo.getTransAmount()));
        zbar.setTransState(ZlBalanceResultConst.SUMMITTED);
        zbar.setFundSeqId(transPo.getSerialNumber());
        StringBuilder errorStr = new StringBuilder();
        if (!balanceAccBill.getFundCode().equals(transPo.getProductNo())) {
            //产品代码不一致
            errorStr.append(ZlBalanceResultConst.NotEqualFlag.returnDesc(ZlBalanceResultConst.NotEqualFlag.NOT_EQUAL_DOUBLE.getCode()) + ZlBalanceResultConst.BalanceResult.returnDesc(ZlBalanceResultConst.BalanceResult.NOT_EQUAL_FUND_CODE.getCode()) + "\n");
        }if (balanceAccBill.getTransAmt().doubleValue() - transPo.getTransAmount() != 0) {
            System.out.println(balanceAccBill.getTransAmt().doubleValue() + "   ---  " + transPo.getTransAmount());
            //交易金额不一致
            errorStr.append(ZlBalanceResultConst.NotEqualFlag.returnDesc(ZlBalanceResultConst.NotEqualFlag.NOT_EQUAL_DOUBLE.getCode()) + ZlBalanceResultConst.BalanceResult.returnDesc(ZlBalanceResultConst.BalanceResult.NOT_EQUAL_TRANS_AMT.getCode()) + "\n");
        }if (!balanceAccBill.getFundDate().equals(transPo.getTransTime().substring(0, 8))) {
            //交易日期不一致
            errorStr.append(ZlBalanceResultConst.NotEqualFlag.returnDesc(ZlBalanceResultConst.NotEqualFlag.NOT_EQUAL_DOUBLE.getCode()) + ZlBalanceResultConst.BalanceResult.returnDesc(ZlBalanceResultConst.BalanceResult.NOT_EQUAL_TRANS_DATE.getCode()) + "\n");
        }if (!balanceAccBill.getUserNameText().equals(transPo.getName())) {
            //客户的姓名不一致
            errorStr.append(ZlBalanceResultConst.NotEqualFlag.returnDesc(ZlBalanceResultConst.NotEqualFlag.NOT_EQUAL_DOUBLE.getCode()) + ZlBalanceResultConst.BalanceResult.returnDesc(ZlBalanceResultConst.BalanceResult.NOT_EQUAL_USERNAME.getCode()) + "\n");
        }if (!balanceAccBill.getCertId().equals(transPo.getIdNo())) {
            //客户证件号码不一致
            errorStr.append(ZlBalanceResultConst.NotEqualFlag.returnDesc(ZlBalanceResultConst.NotEqualFlag.NOT_EQUAL_DOUBLE.getCode()) + ZlBalanceResultConst.BalanceResult.returnDesc(ZlBalanceResultConst.BalanceResult.NOT_EQUAL_CERT_NO.getCode()) + "\n");
        }
        if("".equals(errorStr.toString())){
            zbar.setResultType(ZlBalanceResultConst.EQUAL);
            zbar.setResultDesc(ZlBalanceResultConst.NotEqualFlag.returnDesc(ZlBalanceResultConst.NotEqualFlag.NOT_EQUAL_DOUBLE.getCode()) + ZlBalanceResultConst.BalanceResult.returnDesc(ZlBalanceResultConst.BalanceResult.EQUAL.getCode()));
        }else{
            zbar.setResultType(ZlBalanceResultConst.NOT_EQUAL);
            zbar.setResultDesc(errorStr.toString());
        }

        zlBalanceAccBillResultMapper.insertSelective(zbar);
    }


    /**
     * 生成清算步骤
     * @param stepNo   //步骤
     * @param stepCode //步骤代码
     * @param stepDesc //步骤描述
     * @return
     */
    private ClearResult generateClearResult(String stepNo,String stepCode,String stepDesc,BatchState batchState,String chnCode){
        ClearResult clearResultOut = new ClearResult();
        clearResultOut.setBatchCode(batchState.getBatchCode());
        clearResultOut.setChnCode(chnCode);
        clearResultOut.setBatchDate(batchState.getDate());
        clearResultOut.setStepNo(stepNo);
        clearResultOut.setStepCode(stepCode);
        clearResultOut.setStepDesc(stepDesc);
        clearResultOut.setStatus("0");  //状态： 成功
        clearResultOut.setType("2");    //类型  1-文件   2-数据
        clearResultOut.setCreateTime(DateUtils.getCurrentDatetime());
        return clearResultOut;
    }

}
