package com.jinhui.scheduler.biz.core.listener;

import com.jinhui.scheduler.biz.core.common.IFileDirectoryService;
import com.jinhui.scheduler.biz.core.common.IFileService;
import com.jinhui.scheduler.domain.common.InstitutionType;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.util.Date;

/**
 * Created by jinhui on 2017/6/19.
 */
public class DownloadedFileJobListener implements JobExecutionListener {

    @Autowired
    private IFileService fileService;

    @Autowired
    private IFileDirectoryService fileDirectoryService;

    private String institution;

    @Override
    public void beforeJob(JobExecution jobExecution) {
        //do nothing
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        File dir = new File(fileDirectoryService.getWorkspaceDownloadFileDir(InstitutionType.codeOf(institution)));
        File files[] = dir.listFiles();
        for(File file:files){
            fileService.backupFile(file);
        }
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }
}
