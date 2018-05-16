package com.jinhui.scheduler.biz.core.listener;

import com.jinhui.scheduler.biz.core.common.IFileDirectoryService;
import com.jinhui.scheduler.biz.core.common.IFileService;
import com.jinhui.scheduler.domain.common.InstitutionType;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;

/**
 * Created by jinhui on 2017/6/15.
 */
public class DownloadFileStepListener implements StepExecutionListener {

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
        File dir = new File(fileDirectoryService.getWorkspaceDownloadFileDir(InstitutionType.codeOf(institution)));
        File files[] = dir.listFiles();
        for(File file:files){
            fileService.backupFile(file);
        }
        return null;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }
}
