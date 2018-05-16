package com.jinhui.scheduler.biz.core.tasklet;


import com.jinhui.scheduler.biz.core.common.IFileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;

public class ReceivedFileBackupTasklet implements Tasklet {
    private final static Logger LOGGER = LoggerFactory.getLogger(ReceivedFileBackupTasklet.class);
    private String backupFile;

    @Autowired
    private IFileService fileService;

    @Override
    public RepeatStatus execute(StepContribution contribution,
                                ChunkContext chunkContext) throws Exception {
        if(LOGGER.isInfoEnabled()){
            LOGGER.info("备份文件={}",backupFile);
        }
        File file = new File(backupFile);
        fileService.backupFile(file);
        return RepeatStatus.FINISHED;
    }

    public void setBackupFile(String backupFile) {
        this.backupFile = backupFile;
    }
}
