package com.jinhui.scheduler.biz.core;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

public class LongRunningTasklet implements Tasklet {
    @Override
    public RepeatStatus execute(StepContribution contribution,
                                ChunkContext chunkContext) throws Exception {
        for (int i = 0; i < 10; i++) {
            Thread.sleep(5 * 1000);
            System.out.println("*");
        }

        return RepeatStatus.FINISHED;
    }
}
