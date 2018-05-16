package com.jinhui.scheduler.biz.imiqian.pojo;

import com.jinhui.scheduler.domain.common.InstitutionFile;
import com.jinhui.scheduler.domain.imiqian.domain.ClearErrorLog;

import java.util.List;

/**
 *
 * 申请文件导入vo
 * create by wsc 2017-06-07 15:08
 *
 **/
public class ApplyImport {

    private List<InstitutionFile> successList;

    private List<ClearErrorLog> errorLogList;

    public List<InstitutionFile> getSuccessList() {
        return successList;
    }

    public void setSuccessList(List<InstitutionFile> successList) {
        this.successList = successList;
    }

    public List<ClearErrorLog> getErrorLogList() {
        return errorLogList;
    }

    public void setErrorLogList(List<ClearErrorLog> errorLogList) {
        this.errorLogList = errorLogList;
    }
}
