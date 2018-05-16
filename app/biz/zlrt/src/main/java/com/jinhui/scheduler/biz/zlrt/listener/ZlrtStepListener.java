package com.jinhui.scheduler.biz.zlrt.listener;


import com.jinhui.scheduler.biz.core.common.HolidayService;
import com.jinhui.scheduler.biz.zlrt.utils.DateUtils;
import com.jinhui.scheduler.biz.zlrt.common.ZlrtClearStepConst;
import com.jinhui.scheduler.data.core.mapper.zlrt.BatchStateCurrentMapperZlrt;
import com.jinhui.scheduler.data.core.mapper.zlrt.BatchStateMapperZlrt;
import com.jinhui.scheduler.data.core.mapper.zlrt.ClearResultMapperZlrt;
import com.jinhui.scheduler.domain.common.ChannelCode;
import com.jinhui.scheduler.domain.zlrt.BatchState;
import com.jinhui.scheduler.domain.zlrt.BatchStateCurrent;
import com.jinhui.scheduler.domain.zlrt.ClearResult;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 监听，记录step执行日志
 * create by wsc 2017-06-02 9:38
 *
 **/
public class ZlrtStepListener implements StepExecutionListener {
    @Autowired
    ClearResultMapperZlrt clearResultMapper;
    @Autowired
    BatchStateMapperZlrt batchStateMapper;
    @Autowired
    HolidayService holidayService;
    @Autowired
    BatchStateCurrentMapperZlrt batchStateCurrentMapper;

    //批次号
    int batchCode;
    //汇总日期
    String batchDate = "";
    //渠道代码
    String chnCode;
    Map<String,ClearResult> clearResultMap = new HashMap<>();

    public ZlrtStepListener(String chnCode){
        this.chnCode = chnCode;
    }

    @Override
    public void beforeStep(StepExecution stepExecution) {
        //初始化汇总日期和批次号
        BatchStateCurrent batchState = batchStateCurrentMapper.findCurrentBatch();
        batchCode = batchState.getBatchCode();
        batchDate = String.valueOf(batchState.getDate());

        //初始化已清算的步骤
        ClearResult clearResult = new ClearResult();
        clearResult.setBatchCode(batchCode);
        clearResult.setChnCode(chnCode);
        List<ClearResult> clearResultList = clearResultMapper.findList(clearResult);
        clearResultMap.clear();
        for(ClearResult clearResultIn:clearResultList){
            clearResultMap.put(clearResultIn.getStepCode()+clearResultIn.getChnCode(),clearResultIn);
        }
        if(clearResultMap.containsKey(stepExecution.getStepName() + chnCode)){
            throw new IllegalArgumentException("[chnCode = "+chnCode+"][batchCode = "+batchCode+"]["+stepExecution.getStepName()+"]已执行过");
        }
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        //记录完成日志
        if(!clearResultMap.containsKey(stepExecution.getStepName()) && "COMPLETED".equals(stepExecution.getStatus().toString())){
            if(stepExecution.getStepName().equals(ZlrtClearStepConst.ClearSteps.STEP_15.getClearCode())){
                //将证联对账单文件入库
                clearResultMapper.save(generateClearResult(ZlrtClearStepConst.STEPS.STEP_15.toString(),ZlrtClearStepConst.ClearSteps.STEP_15.getClearCode(),ZlrtClearStepConst.ClearSteps.STEP_15.getClearDesc(),stepExecution.getReadCount(),stepExecution.getWriteCount(),stepExecution.getFilterCount()));
            }else if(stepExecution.getStepName().equals(ZlrtClearStepConst.ClearSteps.STEP_17.getClearCode())){
                //赎回文件报送
                clearResultMapper.save(generateClearResult(ZlrtClearStepConst.STEPS.STEP_17.toString(),ZlrtClearStepConst.ClearSteps.STEP_17.getClearCode(),ZlrtClearStepConst.ClearSteps.STEP_17.getClearDesc(),stepExecution.getReadCount(),stepExecution.getWriteCount(),stepExecution.getFilterCount()));
            }else if(stepExecution.getStepName().equals(ZlrtClearStepConst.ClearSteps.STEP_18.getClearCode())){
                //监管申购文件报送
                clearResultMapper.save(generateClearResult(ZlrtClearStepConst.STEPS.STEP_18.toString(),ZlrtClearStepConst.ClearSteps.STEP_18.getClearCode(),ZlrtClearStepConst.ClearSteps.STEP_18.getClearDesc(),stepExecution.getReadCount(),stepExecution.getWriteCount(),stepExecution.getFilterCount()));
            }else if(stepExecution.getStepName().equals(ZlrtClearStepConst.ClearSteps.STEP_19.getClearCode())){
                //监管赎回文件报送
                clearResultMapper.save(generateClearResult(ZlrtClearStepConst.STEPS.STEP_19.toString(),ZlrtClearStepConst.ClearSteps.STEP_19.getClearCode(),ZlrtClearStepConst.ClearSteps.STEP_19.getClearDesc(),stepExecution.getReadCount(),stepExecution.getWriteCount(),stepExecution.getFilterCount()));
            }
        }

        return null;
    }

    /**
     * 生成清算步骤
     * @param stepNo   //步骤
     * @param stepCode //步骤代码
     * @param stepDesc //步骤描述
     * @return
     */
    private ClearResult generateClearResult(String stepNo, String stepCode, String stepDesc,int totalCount,int succCount,int failCount){
        ClearResult clearResultOut = new ClearResult();
        String status = "";
        clearResultOut.setBatchCode(batchCode);
        clearResultOut.setChnCode(chnCode);
        clearResultOut.setBatchDate(batchDate);
        clearResultOut.setSuccCount(succCount);
        clearResultOut.setFailCount(failCount);
        clearResultOut.setStepNo(stepNo);
        clearResultOut.setStepCode(stepCode);
        clearResultOut.setStepDesc(stepDesc);
        clearResultOut.setTotalCount(totalCount);
        if(totalCount == succCount){
            status = "0";   //成功
        }else{
            status = "1";   //失败
        }
        clearResultOut.setStatus(status);
        clearResultOut.setType("1");  //类型  1-文件   2-数据
        clearResultOut.setCreateTime(DateUtils.getCurrentDatetime());
        return clearResultOut;
    }

}
