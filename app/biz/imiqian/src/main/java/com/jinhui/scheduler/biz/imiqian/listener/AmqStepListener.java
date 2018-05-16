package com.jinhui.scheduler.biz.imiqian.listener;

import com.jinhui.scheduler.biz.core.common.HolidayService;
import com.jinhui.scheduler.biz.imiqian.common.AmqClearStepConst;
import com.jinhui.scheduler.biz.imiqian.utils.DateUtils;
import com.jinhui.scheduler.data.core.mapper.imiqian.BatchStateCurrentMapper;
import com.jinhui.scheduler.data.core.mapper.imiqian.BatchStateMapper;
import com.jinhui.scheduler.data.core.mapper.imiqian.ClearResultMapper;
import com.jinhui.scheduler.domain.imiqian.domain.BatchState;
import com.jinhui.scheduler.domain.imiqian.domain.BatchStateCurrent;
import com.jinhui.scheduler.domain.imiqian.domain.ClearResult;
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
public class AmqStepListener implements StepExecutionListener {
    @Autowired
    ClearResultMapper clearResultMapper;
    @Autowired
    BatchStateMapper batchStateMapper;
    @Autowired
    HolidayService holidayService;
    @Autowired
    BatchStateCurrentMapper batchStateCurrentMapper;

    //批次号
    private int batchCode;
    //汇总日期
    private String batchDate = "";
    //渠道代码
    String chnCode;
    Map<String,ClearResult> clearResultMap = new HashMap<>();

    public AmqStepListener(String chnCode){
        this.chnCode = chnCode;
    }

    @Override
    public void beforeStep(StepExecution stepExecution) {
        //初始化汇总日期和批次号
        BatchState batchState = batchStateMapper.findNewest();
        if(stepExecution.getStepName().equals(AmqClearStepConst.ClearSteps.STEP_1.getClearCode()) || stepExecution.getStepName().equals(AmqClearStepConst.ClearSteps.STEP_2.getClearCode())){
            //如果是账户和交易文件入库，直接取最新批次，此时尚未清算，批次尚未更新
            batchCode = batchState.getBatchCode();
            batchDate = String.valueOf(batchState.getDate());
        }else{
            //当前批次
            BatchStateCurrent currBatchState = batchStateCurrentMapper.findCurrentBatch();
            batchCode = currBatchState.getBatchCode();
            batchDate = String.valueOf(currBatchState.getDate());
        }
        //初始化已清算的步骤
        ClearResult clearResult = new ClearResult();
        clearResult.setBatchCode(batchCode);
        clearResult.setChnCode(chnCode);
        List<ClearResult> clearResultList = clearResultMapper.findList(clearResult);
        clearResultMap.clear();
        for(ClearResult clearResultIn:clearResultList){
            clearResultMap.put(clearResultIn.getStepCode()+clearResultIn.getChnCode(),clearResultIn);
        }
        if(clearResultMap.containsKey(stepExecution.getStepName()+chnCode)){
            throw new IllegalArgumentException("[chnCode = "+chnCode+"][batchCode = "+batchCode+"]["+stepExecution.getStepName()+"]已执行过");
        }
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        //记录完成日志
        if(!clearResultMap.containsKey(stepExecution.getStepName()+chnCode) && "COMPLETED".equals(stepExecution.getStatus().toString())){
            if(stepExecution.getStepName().equals(AmqClearStepConst.ClearSteps.STEP_1.getClearCode())){
                //将01账户申请文件入库
                clearResultMapper.save(generateClearResult(AmqClearStepConst.STEPS.STEP_1.toString(),AmqClearStepConst.ClearSteps.STEP_1.getClearCode(),AmqClearStepConst.ClearSteps.STEP_1.getClearDesc(),stepExecution.getReadCount(),stepExecution.getWriteCount(),stepExecution.getFilterCount()));
            }else if(stepExecution.getStepName().equals(AmqClearStepConst.ClearSteps.STEP_2.getClearCode())){
                //将03交易申请文件入库
                clearResultMapper.save(generateClearResult(AmqClearStepConst.STEPS.STEP_2.toString(),AmqClearStepConst.ClearSteps.STEP_2.getClearCode(),AmqClearStepConst.ClearSteps.STEP_2.getClearDesc(),stepExecution.getReadCount(),stepExecution.getWriteCount(),stepExecution.getFilterCount()));
            }else if(stepExecution.getStepName().equals(AmqClearStepConst.ClearSteps.STEP_9.getClearCode())){
                //生成02账户确认文件
                clearResultMapper.save(generateClearResult(AmqClearStepConst.STEPS.STEP_9.toString(),AmqClearStepConst.ClearSteps.STEP_9.getClearCode(),AmqClearStepConst.ClearSteps.STEP_9.getClearDesc(),stepExecution.getReadCount(),stepExecution.getWriteCount(),stepExecution.getFilterCount()));
            }else if(stepExecution.getStepName().equals(AmqClearStepConst.ClearSteps.STEP_10.getClearCode())){
                //生成04交易确认文件
                clearResultMapper.save(generateClearResult(AmqClearStepConst.STEPS.STEP_10.toString(),AmqClearStepConst.ClearSteps.STEP_10.getClearCode(),AmqClearStepConst.ClearSteps.STEP_10.getClearDesc(),stepExecution.getReadCount(),stepExecution.getWriteCount(),stepExecution.getFilterCount()));
            }else if(stepExecution.getStepName().equals(AmqClearStepConst.ClearSteps.STEP_11.getClearCode())){
                //生成05对账数据文件
                clearResultMapper.save(generateClearResult(AmqClearStepConst.STEPS.STEP_11.toString(),AmqClearStepConst.ClearSteps.STEP_11.getClearCode(),AmqClearStepConst.ClearSteps.STEP_11.getClearDesc(),stepExecution.getReadCount(),stepExecution.getWriteCount(),stepExecution.getFilterCount()));
            }else if(stepExecution.getStepName().equals(AmqClearStepConst.ClearSteps.STEP_12.getClearCode())){
                //生成06基金分红文件
                clearResultMapper.save(generateClearResult(AmqClearStepConst.STEPS.STEP_12.toString(),AmqClearStepConst.ClearSteps.STEP_12.getClearCode(),AmqClearStepConst.ClearSteps.STEP_12.getClearDesc(),stepExecution.getReadCount(),stepExecution.getWriteCount(),stepExecution.getFilterCount()));
            }else if(stepExecution.getStepName().equals(AmqClearStepConst.ClearSteps.STEP_13.getClearCode())){
                //生成12业务数据汇总文件
                clearResultMapper.save(generateClearResult(AmqClearStepConst.STEPS.STEP_13.toString(),AmqClearStepConst.ClearSteps.STEP_13.getClearCode(),AmqClearStepConst.ClearSteps.STEP_13.getClearDesc(),stepExecution.getReadCount(),stepExecution.getWriteCount(),stepExecution.getFilterCount()));
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
        clearResultOut.setStepNo(stepNo);
        clearResultOut.setStepCode(stepCode);
        clearResultOut.setStepDesc(stepDesc);
        clearResultOut.setTotalCount(totalCount);
        clearResultOut.setSuccCount(succCount);
        clearResultOut.setFailCount(failCount);
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
