package com.jinhui.scheduler.biz.core.tasklet;


import com.jinhui.scheduler.biz.core.common.IFileDirectoryService;
import com.jinhui.scheduler.biz.core.common.IFileNameService;
import com.jinhui.scheduler.biz.core.config.FileBatchJobMapper;
import com.jinhui.scheduler.domain.common.InstitutionType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.scope.context.StepContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.util.Date;

public class MakeUploadFileDirTasklet extends AbstractParameterCheckTasklet {

    private final static Logger LOGGER = LoggerFactory.getLogger(MakeUploadFileDirTasklet.class);

    @Autowired
    private IFileDirectoryService fileDirectoryService;

    private String institution;

    @Override
    public RepeatStatus doExecute(StepContribution contribution,
                                ChunkContext chunkContext) throws Exception {

        StepContext stepContext = chunkContext.getStepContext();
        JobExecution jobExecution = stepContext.getStepExecution().getJobExecution();
        ExecutionContext jobContext = jobExecution.getExecutionContext();
        String fileDir = fileDirectoryService.getUploadFileDir(InstitutionType.codeOf(institution),
                (Date) stepContext.getJobParameters().get("batchDate"));
        jobContext.put("file.dir", fileDir);

        return RepeatStatus.FINISHED;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }
}
