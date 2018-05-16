package com.jinhui.scheduler.biz.imiqian.pojo;

import com.jinhui.scheduler.domain.common.InstitutionFile;

import java.util.List;

/**
 * 确认文件导出vo
 *
 * create by wsc 2017-06-07 16:22
 **/
public class ConfrimExport {

    private List<InstitutionFile> uploadFiles;

    private String errorMsg;

    public List<InstitutionFile> getUploadFiles() {
        return uploadFiles;
    }

    public void setUploadFiles(List<InstitutionFile> uploadFiles) {
        this.uploadFiles = uploadFiles;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
