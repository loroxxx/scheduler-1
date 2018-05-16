package com.jinhui.scheduler.web.api.vo.applyImport;

import com.jinhui.scheduler.domain.common.InstitutionFile;
import com.jinhui.scheduler.domain.imiqian.domain.ClearErrorLog;
import com.jinhui.scheduler.web.api.vo.file.InstitutionFileVo;

import java.util.List;

/**
 *
 * 申请文件导入vo
 * create by wsc 2017-06-07 15:08
 *
 **/
public class ApplyImportVo {

    private List<InstitutionFileVo> successList;

    private List<ClearErrorLog> errorLogList;

    public List<InstitutionFileVo> getSuccessList() {
        return successList;
    }

    public void setSuccessList(List<InstitutionFileVo> successList) {
        this.successList = successList;
    }

    public List<ClearErrorLog> getErrorLogList() {
        return errorLogList;
    }

    public void setErrorLogList(List<ClearErrorLog> errorLogList) {
        this.errorLogList = errorLogList;
    }
}
