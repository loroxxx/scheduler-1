package com.jinhui.scheduler.web.api.vo.file;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by jinhui on 2017/6/8.
 */
public class InstitutionFileVo {
    //机构
    @ApiModelProperty(value="机构")
    private String institution;
    @ApiModelProperty(value="机构代码")
    private String institutionCode;
    @ApiModelProperty(value="机构名")
    private String institutionName;
    @ApiModelProperty(value="文件类型")
    private String fileType;
    @ApiModelProperty(value="文件名")
    private String fileName;
    @ApiModelProperty(value="文件位置")
    private String fileLocation;

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileLocation() {
        return fileLocation;
    }

    public void setFileLocation(String fileLocation) {
        this.fileLocation = fileLocation;
    }

    public String getInstitutionCode() {
        return institutionCode;
    }

    public void setInstitutionCode(String institutionCode) {
        this.institutionCode = institutionCode;
    }

    public String getInstitutionName() {
        return institutionName;
    }

    public void setInstitutionName(String institutionName) {
        this.institutionName = institutionName;
    }

    @Override
    public String toString() {
        return "InstitutionFileVo{" +
                "institution='" + institution + '\'' +
                ", institutionCode='" + institutionCode + '\'' +
                ", institutionName='" + institutionName + '\'' +
                ", fileType='" + fileType + '\'' +
                ", fileName='" + fileName + '\'' +
                ", fileLocation='" + fileLocation + '\'' +
                '}';
    }
}
