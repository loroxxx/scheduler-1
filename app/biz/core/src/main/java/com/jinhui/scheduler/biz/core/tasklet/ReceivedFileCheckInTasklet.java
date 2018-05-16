package com.jinhui.scheduler.biz.core.tasklet;

import com.jinhui.scheduler.biz.core.common.IFileService;
import com.jinhui.scheduler.domain.common.InstitutionType;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 *  将机构上传的文件检入工作目录
 */
public class ReceivedFileCheckInTasklet implements Tasklet {

    /**
     * {@link InstitutionType} 枚举对象的小写字母简写
     */
    private String institution;

    private Date batchDate;

    @Autowired
    private IFileService fileService;

    @Override
    public RepeatStatus execute(StepContribution contribution,
                                ChunkContext chunkContext) throws Exception {
        InstitutionType institutionType = InstitutionType.codeOf(institution);
        //fileService.fileCheckInWorkspace(institutionType, batchDate);
        return RepeatStatus.FINISHED;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public void setBatchDate(Date batchDate) {
        this.batchDate = batchDate;
    }
}
