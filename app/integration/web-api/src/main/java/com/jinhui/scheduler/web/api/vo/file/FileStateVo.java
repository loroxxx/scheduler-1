package com.jinhui.scheduler.web.api.vo.file;

/**
 * Created by jinhui on 2017/6/22.
 */
public class FileStateVo {

    private String fileName;

    private String fileType;

    private String fileState;


    public FileStateVo() {
    }

    public FileStateVo(String fileName, String fileType, String fileState) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.fileState = fileState;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        switch (fileType) {
            case "trans":
                this.fileType="客户申赎产品文件";
                break;
            case "profit":
                this.fileType="产品收益率文件";
                break;
            case "position":
                this.fileType="客户持仓文件";
                break;
            default: this.fileType=fileType;
        }
    }

    public String getFileState() {
        return fileState;
    }



    /**
     * 文件状态：
     * 0："文件上传中"
     * 1：文件生成失败
     * 2：resp文件处理成功
     * 4：文件上传成功，等待resp文件返回
     * 5：resp文件处理失败
     * */
    public void setFileState(String fileState) {
        this.fileState=fileState;

    }

    @Override
    public String toString() {
        return "FileStateVo{" +
                "fileName='" + fileName + '\'' +
                ", fileType='" + fileType + '\'' +
                ", fileState='" + fileState + '\'' +
                '}';
    }
}
