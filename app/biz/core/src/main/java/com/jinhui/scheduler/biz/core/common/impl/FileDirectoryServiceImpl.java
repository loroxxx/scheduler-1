package com.jinhui.scheduler.biz.core.common.impl;

import com.jinhui.scheduler.biz.core.common.IFileDirectoryService;
import com.jinhui.scheduler.domain.common.InstitutionType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

/**
 * Created by jinhui on 2017/5/22.
 */
@Service("fileDirectoryServiceImpl")
public class FileDirectoryServiceImpl implements IFileDirectoryService{

    @Value("${upload.workspace.basePath}")
    private String uploadWorkspaceBaseDir;

    @Value("${download.workspace.basePath}")
    private String downloadWorkspaceBaseDir;

    @Value("${backup.basePath}")
    private String backupBaseDir;

    @Resource(name = "institutionFtpBaseDir")
    private Map<String, String> sftpBaseDir;

    @Override
    public String getSftpFileDir(InstitutionType institution, Date batchDate) {
        String baseDir = sftpBaseDir.get(institution.getAbbr());
        String date = new SimpleDateFormat("yyyyMMdd").format(batchDate);
        return baseDir+File.separator+date;
    }

    /**
     * 获取文件上传目录
     * @param institution 机构类别
     * @return
     */
    @Override
    public String getUploadFileDir(InstitutionType institution, Date batchDate){
        return institution.uploadFilePath(sftpBaseDir.get(institution.getAbbr()), batchDate);
    }

    @Override
    public String getWorkspaceUploadFileDir(InstitutionType institution) {
        File dir = new File(uploadWorkspaceBaseDir+File.separator+institution.getAbbr()+File.separator);
        if(!dir.exists()) {
            mkDir(dir);
        }
        return dir.getAbsolutePath();
    }

    /**
     * 获取下载文件目录
     * @param institution 机构类别
     * @return
     */
    @Override
    public String getDownloadFileDir(InstitutionType institution, Date batchDate){
        return institution.downloadFilePath(sftpBaseDir.get(institution.getAbbr()), batchDate);
    }

    @Override
    public String getWorkspaceDownloadFileDir(InstitutionType institution) {
        File dir = new File(downloadWorkspaceBaseDir+File.separator+institution.getAbbr()+File.separator);
        if(!dir.exists()) {
            mkDir(dir);
        }
        return dir.getAbsolutePath();
    }

    @Override
    public String getBackupFileDir(InstitutionType institution, Date batchDate) {
        return getFileDateDir(backupBaseDir, institution.getAbbr(), batchDate);
    }

    @Override
    public void genDownloadFileDir(InstitutionType institution){
        //// TODO: 2017/6/19
    }

    private static String getFileDateDir(String baseDir, String institution, Date d){
        String date = new SimpleDateFormat("yyyyMMdd").format(d);
        File dir = new File(baseDir+File.separator+
                institution+File.separator+
                date+File.separator);
        if(!dir.exists()) {
            mkDir(dir);
        }
        return dir.getAbsolutePath();
    }

    private static void mkDir(File file){
        if(!file.getParentFile().exists()) {
            mkDir(file.getParentFile());
        }
        file.mkdir();
    }

}
