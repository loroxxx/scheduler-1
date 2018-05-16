package com.jinhui.scheduler.web.api.vo.file;

import com.jinhui.scheduler.web.api.vo.base.WebPageResult;
import com.jinhui.scheduler.web.api.vo.base.WebResult;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by jinhui on 2017/6/1.
 */
public class InstitutionFileResult extends WebResult{

    @ApiModelProperty(value = "清算需要文件", dataType = "com.jinhui.scheduler.web.api.vo.file.InstitutionFileVo")
    private List<InstitutionFileVo> neededFiles;

    @ApiModelProperty(value = "实际收到文件", dataType = "com.jinhui.scheduler.web.api.vo.file.InstitutionFileVo")
    private List<InstitutionFileVo> actualFiles;

    public List<InstitutionFileVo> getNeededFiles() {
        return neededFiles;
    }

    public void setNeededFiles(List<InstitutionFileVo> neededFiles) {
        this.neededFiles = neededFiles;
    }

    public List<InstitutionFileVo> getActualFiles() {
        return actualFiles;
    }

    public void setActualFiles(List<InstitutionFileVo> actualFiles) {
        this.actualFiles = actualFiles;
    }

    @Override
    public String toString() {
        return "InstitutionFileResult{" +
                "neededFiles=" + neededFiles +
                ", actualFiles=" + actualFiles +
                '}'+ super.toString();
    }
}
