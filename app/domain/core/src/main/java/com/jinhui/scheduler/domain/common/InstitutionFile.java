package com.jinhui.scheduler.domain.common;

import org.springframework.util.StringUtils;

import javax.annotation.Nullable;
import java.util.Date;

/**
 * Created by jinhui on 2017/6/8.
 */
public class InstitutionFile {
    //机构
    private InstitutionType institution;
    //
    private String fileType;
    private String fileName;
    private String isDownloadFile;
    @Nullable
    private String filePattern;
    @Nullable
    private String fileUploadLocation;
    @Nullable
    private String fileDownloadLocation;
    @Nullable
    private String fileWorkspaceLocation;
    @Nullable
    private String fileBackupLocation;
    @Nullable
    private String fileFtpLocation;

    /**
     *
     * @param institution
     * @param fileType
     * @param fileName
     * @param isDownloadFile 是否是他方机构传给我方文件
     */
    public InstitutionFile(InstitutionType institution, String fileType,
                           String fileName, String isDownloadFile) {
        this.institution = institution;
        this.fileType = fileType;
        this.fileName = fileName;
        this.isDownloadFile = isDownloadFile;
    }

    public String getFileName(){
        if(StringUtils.isEmpty(fileName))
            throw new RuntimeException("文件名未知");
        return fileName;
    }

    public void setFileName(Date batchDate){
        if(StringUtils.isEmpty(filePattern))
            throw new IllegalArgumentException("文件模式为空, 无法生成文件名");
        fileName = institution.fileNameWithoutSeq(filePattern, batchDate);
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public InstitutionType getInstitution() {
        return institution;
    }

    public void setInstitution(InstitutionType institution) {
        this.institution = institution;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getIsDownloadFile() {
        return isDownloadFile;
    }

    public void setIsDownloadFile(String isDownloadFile) {
        this.isDownloadFile = isDownloadFile;
    }

    @Nullable
    public String getFilePattern() {
        return filePattern;
    }

    public void setFilePattern(@Nullable String filePattern) {
        this.filePattern = filePattern;
    }

    @Nullable
    public String getFileUploadLocation() {
        return fileUploadLocation;
    }

    public void setFileUploadLocation(@Nullable String fileUploadLocation) {
        this.fileUploadLocation = fileUploadLocation;
    }

    @Nullable
    public String getFileDownloadLocation() {
        return fileDownloadLocation;
    }

    public void setFileDownloadLocation(@Nullable String fileDownloadLocation) {
        this.fileDownloadLocation = fileDownloadLocation;
    }

    @Nullable
    public String getFileWorkspaceLocation() {
        return fileWorkspaceLocation;
    }

    public void setFileWorkspaceLocation(@Nullable String fileWorkspaceLocation) {
        this.fileWorkspaceLocation = fileWorkspaceLocation;
    }

    @Nullable
    public String getFileBackupLocation() {
        return fileBackupLocation;
    }

    public void setFileBackupLocation(@Nullable String fileBackupLocation) {
        this.fileBackupLocation = fileBackupLocation;
    }

    public String getFileLocation(){
        if(!StringUtils.isEmpty(fileUploadLocation))
            return fileUploadLocation;
        if(!StringUtils.isEmpty(fileDownloadLocation))
            return fileDownloadLocation;
        if(!StringUtils.isEmpty(fileWorkspaceLocation))
            return fileWorkspaceLocation;
        if(!StringUtils.isEmpty(fileBackupLocation))
            return fileBackupLocation;
        return null;
    }

    @Nullable
    public String getFileFtpLocation() {
        return fileFtpLocation;
    }

    public void setFileFtpLocation(@Nullable String fileFtpLocation) {
        this.fileFtpLocation = fileFtpLocation;
    }

    InstitutionFile() {
        //for ORM
    }

    public static Criteria criteria(){
        return new Criteria();
    }

    public static class Criteria{
        private InstitutionType institution;
        private String isDownloadFile;
        public Criteria institution(InstitutionType institution){
            this.institution = institution;
            return this;
        }
        public Criteria isDownloadFile(String isDownloadFile){
            this.isDownloadFile = isDownloadFile;
            return this;
        }
        public InstitutionType institution(){
            return this.institution;
        }
    }
}
