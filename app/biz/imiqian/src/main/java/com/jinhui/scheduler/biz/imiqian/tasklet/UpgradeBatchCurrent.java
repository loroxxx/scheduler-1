package com.jinhui.scheduler.biz.imiqian.tasklet;

import com.jinhui.scheduler.biz.imiqian.utils.DateUtils;
import com.jinhui.scheduler.data.core.mapper.imiqian.BatchStateCurrentMapper;
import com.jinhui.scheduler.data.core.mapper.imiqian.BatchStateMapper;
import com.jinhui.scheduler.domain.imiqian.domain.BatchState;
import com.jinhui.scheduler.domain.imiqian.domain.BatchStateCurrent;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 更新当天的批次
 *
 * created by wsc  2017-06-13 16:05
 **/

public class UpgradeBatchCurrent implements Tasklet {
    @Autowired
    BatchStateMapper batchStateMapper;
    @Autowired
    BatchStateCurrentMapper batchStateCurrentMapper;

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        //记录当前批次(用于回滚)
        BatchState batchState = batchStateMapper.findNewest();
        batchStateCurrentMapper.delete();
        BatchStateCurrent batchStateCurrent = new BatchStateCurrent();
        batchStateCurrent.setBatchCode(batchState.getBatchCode());
        batchStateCurrent.setDate(batchState.getDate());
        batchStateCurrent.setCreateTime(DateUtils.getCurrentDatetime());
        batchStateCurrentMapper.save(batchStateCurrent);

        return RepeatStatus.FINISHED;
    }
}
