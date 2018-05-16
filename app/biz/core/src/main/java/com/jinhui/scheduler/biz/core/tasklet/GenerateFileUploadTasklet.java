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

public class GenerateFileUploadTasklet implements Tasklet {

    private final static Logger LOGGER = LoggerFactory.getLogger(GenerateFileUploadTasklet.class);

    private String uploadFile;

    @Autowired
    private IFileService fileService;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        if(LOGGER.isInfoEnabled()){
            LOGGER.info("上传文件={}",uploadFile);
        }
        File file = new File(uploadFile);
        fileService.uploadFile(file);
        return RepeatStatus.FINISHED;
    }

    public void setUploadFile(String uploadFile) {
        this.uploadFile = uploadFile;
    }
}
