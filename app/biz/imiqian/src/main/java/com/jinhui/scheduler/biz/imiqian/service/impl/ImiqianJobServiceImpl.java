package com.jinhui.scheduler.biz.imiqian.service.impl;

import com.jcraft.jsch.HASH;
import com.jinhui.scheduler.biz.core.common.HolidayService;
import com.jinhui.scheduler.biz.core.common.IFileDirectoryService;
import com.jinhui.scheduler.biz.core.common.IFileService;
import com.jinhui.scheduler.biz.imiqian.common.AmqClearStepConst;
import com.jinhui.scheduler.biz.imiqian.common.Result;
import com.jinhui.scheduler.biz.imiqian.service.ImiqianJobService;
import com.jinhui.scheduler.biz.imiqian.service.RollBackService;
import com.jinhui.scheduler.biz.imiqian.service.StatisticsPosIncomeService;
import com.jinhui.scheduler.biz.imiqian.pojo.ApplyImport;
import com.jinhui.scheduler.biz.imiqian.pojo.Clear;
import com.jinhui.scheduler.biz.imiqian.pojo.ConfrimExport;
import com.jinhui.scheduler.biz.imiqian.utils.DateUtils;
import com.jinhui.scheduler.biz.shared.launch.support.SyncJobLauncher;
import com.jinhui.scheduler.data.core.mapper.cmbfae.CmbfaeFileRuleDao;
import com.jinhui.scheduler.data.core.mapper.imiqian.*;
import com.jinhui.scheduler.domain.cmbfae.model.CmbfaeFileRule;
import com.jinhui.scheduler.domain.common.ChannelCode;
import com.jinhui.scheduler.domain.common.InstitutionType;
import com.jinhui.scheduler.domain.imiqian.domain.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.ManagedArray;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 爱蜜钱服务层实现
 *
 * create by wsc 2017-06-07 16:23
 **/
@Service("imiqianJobService")
public class ImiqianJobServiceImpl implements ImiqianJobService{
    private final static Logger logger = LoggerFactory.getLogger(ImiqianJobServiceImpl.class);

    @Autowired
    private SyncJobLauncher syncJobLauncher;
    @Autowired
    private JobRegistry jobRegistry;
    @Autowired
    private BatchStateMapper batchStateMapper;
    @Autowired
    private IFileDirectoryService iFileDirectoryService;
    @Autowired
    private ClearResultMapper clearResultMapper;
    @Autowired
    private StatisticsPosIncomeService statisticsPosIncomeService;
    @Autowired
    private BatchStateCurrentMapper batchStateCurrentMapper;
    @Autowired
    private RollBackService rollBackService;
    @Autowired
    private IFileService iFileService;
    @Autowired
    private InvestorTransMapper investorTransMapper;
    @Autowired
    private ChnProductMapper chnProductMapper;
    @Autowired
    private HolidayService holidayService;
    @Autowired
    private ClearErrorLogMapper clearErrorLogMapper;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private ChnInfoMapper chnInfoMapper;

    /**
     * 申请文件导入
     * @return
     * @throws Exception
     */
    public Result<ApplyImport> applyImport() throws Exception {
        Boolean illegal = true;
        ApplyImport applyImport = new ApplyImport();
        Job job = jobRegistry.getJob("amqApplyJob");
        BatchState batchState = batchStateMapper.findNewest();
        logger.info("批次： batchCode=" +batchState.getBatchCode() +"  batchDate="+batchState.getDate());
        List<ChnInfo> chnInfoList = chnInfoMapper.findChnInfoList();
        logger.info("渠道数量：" + chnInfoList.size() +" 个");
        for(ChnInfo channel:chnInfoList){
            logger.info("渠道： "+ channel.getChnCode() + "  "+ channel.getChnName());
            String workspacePath = iFileDirectoryService.getWorkspaceDownloadFileDir(ChannelCode.codeOf(channel.getChnCode()).getType());
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new SimpleDateFormat("yyyyMMdd").parse(batchState.getDate()));
            JobParameters parameters = new JobParametersBuilder()
                    .addString("workspacePath", workspacePath)
                    .addString("batchCode",batchState.getBatchCode()+"")
                    .addString("chnCode", channel.getChnCode())
                    .addString("chnName", ChannelCode.codeOf(channel.getChnCode()).getType().getAbbr())
                    .addString("applyDate", batchState.getDate())
                    .addString("timestamp", System.currentTimeMillis()+"")
                    .toJobParameters();
            JobExecution jobExecution = syncJobLauncher.run(job, parameters);
            illegal = checkData(illegal,batchState,channel.getChnCode());
        }
        ClearResult clearResult = new ClearResult();
        clearResult.setBatchCode(batchState.getBatchCode());
        //查询成功的任务数
        int succCount = clearResultMapper.findImportCount(clearResult);
        if(succCount == 0){
            List<ClearErrorLog> errorList = clearErrorLogMapper.findErrorLogList(String.valueOf(batchState.getBatchCode()));
            applyImport.setErrorLogList(errorList);
            return Result.failData(applyImport);
        }else if(succCount == chnInfoList.size()*2){
            //applyImport.setSuccessList(iFileService.lookupFinishedFile(InstitutionType.Imiqian,calendar.getTime()));
            return Result.successData(applyImport);
        }else{
            List<ClearErrorLog> errorList = clearErrorLogMapper.findErrorLogList(String.valueOf(batchState.getBatchCode()));
            applyImport.setErrorLogList(errorList);
            return Result.failData(applyImport);
        }
    }


    /**
     * 清算
     * @return
     */
    public Result clear(String date) {
        BatchState batchState = batchStateMapper.findBatchStateByDate(date);
        if(batchState == null){
            return Result.failData("【批次日期="+batchState.getDate()+"】的数据不存在，无法清算！");
        }
        List<ChnInfo> chnInfoList = chnInfoMapper.findChnInfoList();
        int batchCode = batchState.getBatchCode();
        String batchDate = String.valueOf(batchState.getDate());
        ClearResult clearResult = new ClearResult();
        clearResult.setBatchCode(batchState.getBatchCode());

        //查询导入成功的任务数
        int importCount = clearResultMapper.findImportCount(clearResult);
        //当天批次的申请文件导入成功才能清算
        if(importCount == chnInfoList.size()*2){
            logger.info("批次： batchCode=" +batchState.getBatchCode() +"  batchDate="+batchState.getDate());


            int succCount = clearResultMapper.findClearCount(clearResult);
            if(succCount == 0){
                statisticsPosIncomeService.statisticsPosition(batchCode,batchDate);
                int count = clearResultMapper.findClearCount(clearResult);
                //清算成功会记录6个任务
                if(count == 6){
                    return Result.successData("清算成功！");
                }else{
                    return Result.failData("清算失败！");
                }
            }else if(succCount == 6){
                return Result.successData("清算成功！");
            }else{
                return Result.failData("清算失败！");
            }
        }else{
            return Result.failData("【批次日期="+batchState.getDate()+"】的申请文件尚未导入成功，无法清算！");
        }

    }

    /**
     * 渠道确认文件导出
     * @return
     */
    public Result<ConfrimExport> confirmExport() throws Exception {
        ConfrimExport confrimExport = new ConfrimExport();
        //最新批次
        BatchState batchState = batchStateMapper.findNewest();
        logger.info("批次： batchCode=" +batchState.getBatchCode() +"  batchDate="+batchState.getDate());
        //当前批次
        BatchStateCurrent currBatchState = batchStateCurrentMapper.findCurrentBatch();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new SimpleDateFormat("yyyyMMdd").parse(currBatchState.getDate()));

        ClearResult clearResult = new ClearResult();
        clearResult.setBatchCode(currBatchState.getBatchCode());
        //当天批次清算完了才能导出
        int clearCount = clearResultMapper.findClearCount(clearResult);
        if(clearCount == 6){
            List<ChnInfo> chnInfoList = chnInfoMapper.findChnInfoList();
            logger.info("渠道数量：" + chnInfoList.size() +" 个");
            for(ChnInfo channel:chnInfoList){
                logger.info("渠道： " + channel.getChnCode() + "  " + channel.getChnName());
                String generatePath = iFileDirectoryService.getWorkspaceUploadFileDir(ChannelCode.codeOf(channel.getChnCode()).getType());
                Job job = jobRegistry.getJob("amqConfirmJob");
                JobParameters parameters = new JobParametersBuilder()
                        .addString("batchCode", currBatchState.getBatchCode() + "")
                        .addString("beginDate", currBatchState.getDate())
                        .addString("endDate", batchState.getDate())
                        .addString("chnCode", channel.getChnCode())
                        .addString("chnName", ChannelCode.codeOf(channel.getChnCode()).getType().getAbbr())
                        .addString("applyDate", currBatchState.getDate())
                        .addString("generatePath", generatePath)
                        .addString("timestamp", System.currentTimeMillis() + "")
                        .toJobParameters();
                JobExecution jobExecution = syncJobLauncher.run(job, parameters);
            }
                int count = clearResultMapper.findExportCount(clearResult);
                if(count == 0){
                    confrimExport.setErrorMsg("【批次日期=" + batchState.getDate() + "】生成渠道清算文件失败！");
                    logger.info("【批次日期=" + batchState.getDate() + "】生成渠道清算文件失败！");
                    return Result.failData(confrimExport);
                } else if (count == chnInfoList.size()*5) {
                    //confrimExport.setUploadFiles(iFileService.lookupUploadFile(InstitutionType.Imiqian, calendar.getTime()));
                    return Result.successData(confrimExport);
                } else {
                    confrimExport.setErrorMsg("【批次日期=" + batchState.getDate() + "】生成渠道清算文件失败！");
                    logger.info("【批次日期=" + batchState.getDate() + "】生成渠道清算文件失败！");
                    return Result.failData(confrimExport);
                }

            }else{
                logger.info("【批次日期="+batchState.getDate()+"】尚未清算或清算失败，无法生成渠道清算文件！");
                confrimExport.setErrorMsg("【批次日期="+batchState.getDate()+"】尚未清算或清算失败，无法生成渠道清算文件！");
                return Result.failData(confrimExport);
            }
    }

    @Override
    public Result rollback() throws ParseException {
        return rollBackService.rollBack();
    }

    @Override
    public Result findClearTask(String batchDate) throws ParseException {
        BatchState batchState = batchStateMapper.findClearTask(batchDate);
        return Result.successData(batchState);
    }

    @Override
    public Result findClearResult(String batchDate) {
        BatchState batchState = batchStateMapper.findBatchStateByDate(batchDate);
        logger.info("批次： batchCode=" +batchState.getBatchCode() +"  batchDate="+batchState.getDate());

        ClearResult clearResult = new ClearResult();
        clearResult.setBatchCode(batchState.getBatchCode());
        //查询成功的任务数
        int count = clearResultMapper.findClearCount(clearResult);
        //清算成功会记录6个任务
        if(count == 6){
            return Result.successData(combineData());
        }else{
            return Result.failData("清算失败！");
        }
    }

    @Override
    public Result findExportResult(String batchDate) {
        BatchState batchState = batchStateMapper.findBatchStateByDate(batchDate);
        ClearResult clearResult = new ClearResult();
        clearResult.setBatchCode(batchState.getBatchCode());
        logger.info("批次： batchCode=" +batchState.getBatchCode() +"  batchDate="+batchState.getDate());

        //查询成功的任务数
        int count = clearResultMapper.findExportCount(clearResult);
        //渠道确认文件导出成功会记录5个任务
        if(count == 5){
            return Result.successData("导出成功！");
        }else{
            return Result.failData("导出失败！");
        }
    }

    private List combineData(){
        List<Product> productList = productMapper.selectLiveProduct();
        List list = new ArrayList();
        for(Product product: productList){
            HashMap map1 = new HashMap();
            map1.put("exchangeNo",product.getExchangeNo());
            map1.put("productNo",product.getProductNo());
            map1.put("busName","交易处理");
            map1.put("status","已完成");

            HashMap map2 = new HashMap();
            map2.put("exchangeNo",product.getExchangeNo());
            map2.put("productNo",product.getProductNo());
            map2.put("busName","持仓处理");
            map2.put("status","已完成");

            HashMap map3 = new HashMap();
            map3.put("exchangeNo",product.getExchangeNo());
            map3.put("productNo",product.getProductNo());
            map3.put("busName","收益处理");
            map3.put("status","已完成");

            List listInner = new ArrayList();
            listInner.add(map1);
            listInner.add(map2);
            listInner.add(map3);
            HashMap map = new HashMap();
            map.put("list",listInner);
            list.add(map);
        }

        return list;
    }


    /**
     * 校验当前批次交易数据是否合法(基金代码，交易时间)
     * @param batchState
     * @param chnCode
     * @return
     */
    private Boolean checkData(Boolean illegal,BatchState batchState,String chnCode){
        investorTransMapper.deleteTransTemp();
        //备份T日交易数据至临时表
        InvestorTrans investorTrans = new InvestorTrans();
        investorTrans.setBatchCode(batchState.getBatchCode());
        investorTrans.setChnCode(chnCode);
        investorTransMapper.transferDataToTemp(investorTrans);

        //初始化产品信息
        List<ChnProduct> chnProductList = chnProductMapper.findAllProduct();
        Map<String,ChnProduct> productMaps = new HashMap<>();
        for(ChnProduct chnPro : chnProductList){
            productMaps.put(chnPro.getProductNo(),chnPro);
        }

        StringBuilder preWorkDay = new StringBuilder(holidayService.getPreWorkDay(batchState.getDate()));

        InvestorTrans transIn = new InvestorTrans();
        transIn.setChnCode(chnCode);
        transIn.setBatchCode(batchState.getBatchCode());
        List<InvestorTrans> transTempList = investorTransMapper.findTransTempList(transIn);
        String errorMsg = "";
        for(InvestorTrans trans:transTempList){
            if(!productMaps.containsKey(trans.getProductNo())){
                illegal = false;
                errorMsg = "交易申请[申请单编号="+trans.getAppSheetSerialNo()+"][基金代码="+trans.getProductNo()+"]基金代码不存在！";
                saveClearErrorLog(errorMsg,batchState,chnCode);
            }
            if(trans.getTransTime().compareTo(preWorkDay.append("150000").toString()) < 0 ||
                    trans.getTransTime().compareTo(new StringBuffer(batchState.getDate()).append("150000").toString()) > 0){
                illegal =false;
                errorMsg = "交易申请[申请单编号="+trans.getAppSheetSerialNo()+"][交易时间="+trans.getTransTime()+"]交易时间不在T-1日15:00 - T日15:00的范围内！";
                saveClearErrorLog(errorMsg,batchState,chnCode);
            }
        }
        return illegal;
    }

    private ClearErrorLog saveClearErrorLog(String errorInfo,BatchState batchState,String chnCode){
        ClearErrorLog clearErrorLog = new ClearErrorLog();
        clearErrorLog.setBatchCode(batchState.getBatchCode());
        clearErrorLog.setBatchDate(batchState.getDate());
        clearErrorLog.setChnCode(chnCode);
        clearErrorLog.setStepCode(AmqClearStepConst.ClearSteps.STEP_2.getClearCode());
        clearErrorLog.setStepDesc(AmqClearStepConst.ClearSteps.STEP_2.getClearDesc());
        clearErrorLog.setTargetCode(this.getClass().getSimpleName());
        clearErrorLog.setCreateTime(DateUtils.getCurrentDatetime());
        clearErrorLog.setErrorInfo(errorInfo);

        clearErrorLogMapper.save(clearErrorLog);
        return clearErrorLog;
    }

}
