package com.jinhui.scheduler.biz.imiqian.service.impl;

import com.jinhui.scheduler.biz.core.common.IFileService;
import com.jinhui.scheduler.biz.imiqian.common.AmqClearStepConst;
import com.jinhui.scheduler.biz.imiqian.common.Result;
import com.jinhui.scheduler.biz.imiqian.service.RollBackService;
import com.jinhui.scheduler.biz.imiqian.utils.DateUtils;
import com.jinhui.scheduler.data.core.mapper.cmbfae.CmbfaeFileRuleDao;
import com.jinhui.scheduler.data.core.mapper.imiqian.*;
import com.jinhui.scheduler.domain.common.ChannelCode;
import com.jinhui.scheduler.domain.common.InstitutionType;
import com.jinhui.scheduler.domain.imiqian.domain.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * 回滚实现
 *
 * create by wsc 2017-06-05 9:07
 *
 **/
@Service("rollBackService")
public class RollBackServiceImpl implements RollBackService {
    private final static Logger logger = LoggerFactory.getLogger(RollBackServiceImpl.class);

    @Autowired
    ChnOpenMapper chnOpenMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    InvestorTransMapper investorTransMapper;
    @Autowired
    InvestorPositionMapper investorPositionMapper;
    @Autowired
    PositionHistoryMapper positionHistoryMapper;
    @Autowired
    InvestorIncomeMapper investorIncomeMapper;
    @Autowired
    BusinessGatherMapper businessGatherMapper;
    @Autowired
    ClearResultMapper clearResultMapper;
    @Autowired
    BatchStateMapper batchStateMapper;
    @Autowired
    BatchStateCurrentMapper batchStateCurrentMapper;
    @Autowired
    ClearErrorLogMapper clearErrorLogMapper;
    @Autowired
    IFileService iFileService;
    @Autowired
    private CmbfaeFileRuleDao fileRule;
    @Autowired
    private ChnInfoMapper chnInfoMapper;
    @Autowired
    private ZlBalanceAccBillMapperDAO zlBalanceAccBillMapperDAO;

    //批次号
    int batchCode;
    //汇总日期
    String gatherDate = "";
    String errorInfo = "";

    private void initData(){
        //获取需回滚到的批次
        BatchStateCurrent batchStateCurrent = batchStateCurrentMapper.findCurrentBatch();

        batchCode = batchStateCurrent.getBatchCode();
        gatherDate = String.valueOf(batchStateCurrent.getDate());

    }

    @Override
    public Result rollBack() throws ParseException {

        initData();
        logger.info("批次： " + "batchCode="+  batchCode + "batchDate="+ gatherDate);

        if("".equals(batchCode) || "".equals(gatherDate)){
            errorInfo = "当天批次号不存在，回滚失败!";
            clearErrorLogMapper.save(saveClearErrorLog(errorInfo));
            logger.info(errorInfo);
            return Result.fail("回滚失败！");
        }else{
            chnOpenMapper.delete(batchCode);

            userMapper.delete(batchCode);

            investorTransMapper.delete(batchCode);

            BatchState batchState = batchStateMapper.findFirstBatch();
            //如果是第一天就回滚，此时历史持仓是为空，需要将持仓表清空
            if(batchCode == batchState.getBatchCode()){
                investorPositionMapper.delete();
            }

            int count = positionHistoryMapper.findHisCount(batchCode-1);
            //如果有T-1日的历史持仓，说明已执行过清算，回滚操作需要把当前持仓清空
            if(count != 0){
                investorPositionMapper.delete();
            }

            investorPositionMapper.insertRollBack(batchCode-1);

            positionHistoryMapper.delete(batchCode-1);

            investorIncomeMapper.delete(batchCode);

            businessGatherMapper.delete(batchCode);

            clearResultMapper.delete(batchCode);

            clearErrorLogMapper.deleteErrorLog(batchCode);

            batchStateMapper.delete(batchCode);

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new SimpleDateFormat("yyyyMMdd").parse(gatherDate));
            List<ChnInfo> chnInfoList = chnInfoMapper.findChnInfoList();
            for(ChnInfo channel:chnInfoList){
                iFileService.fileRollback(ChannelCode.codeOf(channel.getChnCode()).getType(),calendar.getTime());
            }
           // fileRule.update("Y", gatherDate);

            //回滚对账单数据
            zlBalanceAccBillMapperDAO.deleteBalanceAccBill(batchCode);
            zlBalanceAccBillMapperDAO.deleteBalanceAccBillHis(batchCode);
            zlBalanceAccBillMapperDAO.deleteBalanceAccResult(batchCode);

            //记录完成状态
            clearResultMapper.save(generateClearResult(AmqClearStepConst.STEPS.STEP_14.toString(),AmqClearStepConst.ClearSteps.STEP_14.getClearCode(),AmqClearStepConst.ClearSteps.STEP_14.getClearDesc()));

            ClearResult clearResult = new ClearResult();
            clearResult.setBatchCode(batchCode);
            clearResult.setChnCode("0000");
            int succCount = clearResultMapper.findRollBackCount(clearResult);
            if(succCount == 1){
                return Result.success("回滚成功！");
            }else{
                return Result.fail("回滚失败！");
            }
        }
    }

    /**
     * 生成清算步骤
     * @param stepNo   //步骤
     * @param stepCode //步骤代码
     * @param stepDesc //步骤描述
     * @return
     */
    private ClearResult generateClearResult(String stepNo, String stepCode, String stepDesc){
        ClearResult clearResultOut = new ClearResult();
        clearResultOut.setBatchCode(batchCode);
        clearResultOut.setChnCode("0000");
        clearResultOut.setBatchDate(gatherDate);
        clearResultOut.setStepNo(stepNo);
        clearResultOut.setStepCode(stepCode);
        clearResultOut.setStatus("0");  //状态： 成功
        clearResultOut.setType("2");    //类型  1-文件   2-数据
        clearResultOut.setStepDesc(stepDesc);
        clearResultOut.setCreateTime(DateUtils.getCurrentDatetime());
        return clearResultOut;
    }

    private ClearErrorLog saveClearErrorLog(String errorInfo){
        ClearErrorLog clearErrorLog = new ClearErrorLog();
        clearErrorLog.setBatchCode(batchCode);
        if(gatherDate == null || "".equals(gatherDate)){
            gatherDate = DateUtils.getCurrentDatetime();
        }
        clearErrorLog.setBatchDate(gatherDate);
        clearErrorLog.setChnCode("0000");
        clearErrorLog.setStepCode(AmqClearStepConst.ClearSteps.STEP_14.getClearCode());
        clearErrorLog.setStepDesc(AmqClearStepConst.ClearSteps.STEP_14.getClearDesc());
        clearErrorLog.setErrorInfo(errorInfo);
        clearErrorLog.setTargetCode(this.getClass().getSimpleName());
        clearErrorLog.setCreateTime(DateUtils.getCurrentDatetime());

        return clearErrorLog;
    }
}
