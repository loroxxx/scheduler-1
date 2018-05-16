package com.jinhui.scheduler.biz.core.tasklet;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.scope.context.StepContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.util.Assert;

import java.util.Date;

/**
 * Created by jinhui on 2017/6/5.
 */
public abstract class AbstractParameterCheckTasklet implements Tasklet {

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        StepContext stepContext = chunkContext.getStepContext();
        Object batchDate = stepContext.getJobParameters().get("batchDate");
        Assert.isInstanceOf(Date.class, batchDate, "批次日期必须是 java.lang.util.Date");
        return doExecute(contribution,chunkContext);
    }

    public abstract RepeatStatus doExecute(StepContribution contribution, ChunkContext chunkContext)throws Exception;
}
