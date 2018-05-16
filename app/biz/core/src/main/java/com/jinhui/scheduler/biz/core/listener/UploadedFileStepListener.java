package com.jinhui.scheduler.biz.core.listener;

import com.jinhui.scheduler.biz.core.common.IFileDirectoryService;
import com.jinhui.scheduler.biz.core.common.IFileService;
import com.jinhui.scheduler.domain.common.InstitutionType;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.StepContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import java.io.File;
import java.util.Date;

/**
 * Created by jinhui on 2017/6/15.
 */
public class UploadedFileStepListener implements StepExecutionListener {

    @Autowired
    private IFileService fileService;

    @Autowired
    private IFileDirectoryService fileDirectoryService;

    private String institution;

    @Override
    public void beforeStep(StepExecution stepExecution) {
        //do nothing
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        File dir = new File(fileDirectoryService.getWorkspaceUploadFileDir(InstitutionType.codeOf(institution)));
        File files[] = dir.listFiles();
        for(File file:files){
            fileService.uploadFile(file);
        }
        return null;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }
}
