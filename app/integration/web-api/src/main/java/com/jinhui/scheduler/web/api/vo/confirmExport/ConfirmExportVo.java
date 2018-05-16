package com.jinhui.scheduler.web.api.vo.confirmExport;

import com.jinhui.scheduler.web.api.vo.file.InstitutionFileVo;

import java.util.List;

/**
 * vo
 *
 * created by wsc 2017-06-13 19:17
 **/

public class ConfirmExportVo {

    private List<InstitutionFileVo> uploadFiles;

    private String errorMsg;

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public List<InstitutionFileVo> getUploadFiles() {
        return uploadFiles;
    }

    public void setUploadFiles(List<InstitutionFileVo> uploadFiles) {
        this.uploadFiles = uploadFiles;
    }
}
