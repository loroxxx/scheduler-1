package com.jinhui.scheduler.web.api.vo.file;

import com.jinhui.scheduler.web.api.vo.base.WebResult;

import java.util.List;

/**
 * Created by jinhui on 2017/6/22.
 */
public class FileStateResult extends WebResult {

    private List<FileStateVo> actualFiles;

    public List<FileStateVo> getActualFiles() {
        return actualFiles;
    }

    public void setActualFiles(List<FileStateVo> actualFiles) {
        this.actualFiles = actualFiles;
    }

    @Override
    public String toString() {
        return "FileStateResult{" +
                "actualFiles=" + actualFiles +
                '}';
    }
}
