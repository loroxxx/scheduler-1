package com.jinhui.scheduler.biz.core.common.impl;

import com.jinhui.scheduler.biz.core.common.IFileDirectoryService;
import com.jinhui.scheduler.biz.core.common.IFileService;
import com.jinhui.scheduler.biz.core.file.support.InstitutionSftpMap;
import com.jinhui.scheduler.biz.core.file.support.Sftp;
import com.jinhui.scheduler.data.core.mapper.core.GeneratedFileMapper;
import com.jinhui.scheduler.data.core.mapper.core.InstitutionFileMapper;
import com.jinhui.scheduler.data.core.mapper.core.ReceivedFileMapper;
import com.jinhui.scheduler.domain.common.InstitutionFile;
import com.jinhui.scheduler.domain.common.InstitutionRepository;
import com.jinhui.scheduler.domain.common.InstitutionType;
import com.jinhui.scheduler.domain.core.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.*;

/**
 * Created by jinhui on 2017/6/8.
 */
@Service
public class IFileServiceImpl implements IFileService {

    private final static Logger logger = LoggerFactory.getLogger(IFileServiceImpl.class);

    @Autowired
    private IFileDirectoryService fileDirectoryService;

    @Autowired
    private ReceivedFileRepository receivedFileRepository;

    @Autowired
    private GeneratedFileRepository generatedFileRepository;

    @Autowired
    private InstitutionSftpMap sftpMap;

    @Autowired
    private InstitutionRepository institutionRepository;

    @Autowired
    private InstitutionFileMapper institutionFileMapper;

    @Autowired
    private ReceivedFileMapper receivedFileMapper;

    @Autowired
    private GeneratedFileMapper generatedFileMapper;

    @Override
    public void backupFile(File file) {
        Assert.isTrue(file.isFile(),"不允许备份非文件对象" );
        Assert.isTrue(file.exists(),"备份文件不存在");
        if(logger.isInfoEnabled()){
            logger.info("备份文件={}", file.getAbsolutePath());
        }
        String fileName = file.getName();
        String institution = AbstractBatchFile.inferInstitution(file);
        Date batchDate = AbstractBatchFile.inferBatchDate(institution, file);
        InstitutionType institutionType = InstitutionType.codeOf(institution);
        String backupLocation = fileDirectoryService.getBackupFileDir(institutionType, batchDate);

        ReceivedFile receivedFile = new ReceivedFile(fileName, institutionType, backupLocation, batchDate);

        try {
            moveFile(file, new File(backupLocation + File.separator + fileName));
            receivedFileRepository.save(receivedFile);
        } catch (DuplicateKeyException dke) {
            // ignored, allow for Idempotence
        }
    }

    @Override
    public void uploadFile(final File file) {
        uploadFile(file, new Sftp.UploadCompletedAction() {
            @Override
            public void action() {
                //do nothing
            }
        });
    }

    @Override
    public void uploadFile(final File file, final Sftp.UploadCompletedAction action) {
        Assert.isTrue(file.isFile(),"不允许上传非文件对象");
        Assert.isTrue(file.exists(),"上传文件不存在");
        if(logger.isInfoEnabled()){
            logger.info("上传文件={}", file.getAbsolutePath());
        }
        String institution = AbstractBatchFile.inferUploadInstitution(file);
        final InstitutionType institutionType = InstitutionType.codeOf(institution);
        Sftp sftp = sftpMap.getInstitution(institutionType);
        final Date batchDate = AbstractBatchFile.inferBatchDate(institution, file);
        final String backupLocation = fileDirectoryService.getBackupFileDir(institutionType, batchDate);
        /**
         * 文件上传完成后将文件备份
         */
        sftp.asycPut(file.getAbsolutePath(), fileDirectoryService.getUploadFileDir(institutionType, batchDate)
                        + institutionType.childDirByName(file.getName()),
                new Sftp.UploadCompletedAction() {
                    @Override
                    public void action() {
                        action.action();
                        GeneratedFile generatedFile = new GeneratedFile(file.getName(), institutionType, backupLocation, batchDate);
                        try {
                            generatedFileRepository.save(generatedFile);
                        } catch (DuplicateKeyException dke) {
                            // ignored
                        }
                        moveFile(file, new File(backupLocation + File.separator + file.getName()));
                    }
                });
    }

    @Override
    public List<InstitutionFile> lookupUploadFile(InstitutionType institution, Date batchDate) {
        AbstractBatchFile.Criteria criteria = AbstractBatchFile.criteria()
                .institution(institution).batchDate(batchDate);
        if(logger.isInfoEnabled()){
            logger.info("查找上传文件, {}", criteria);
        }
        List<GeneratedFile> generatedFiles = generatedFileMapper.findFile(criteria);
        List<InstitutionFile> institutionFiles = new ArrayList<>(generatedFiles.size());
        for(GeneratedFile file:generatedFiles){
            InstitutionFile institutionFile = institutionRepository.
                    findFileByIdentity(institution.filePattern(file.getFileName()), institution);
            institutionFile.setFileName(file.getFileName());
            institutionFile.setFileBackupLocation(file.getBackupLocation());
            institutionFiles.add(institutionFile);
        }
        return institutionFiles;
    }

    @Override
    public List<InstitutionFile> lookupDownloadFile(InstitutionType institution, Date batchDate) {
        return lookupDownloadFile(institution, batchDate, null);
    }

    @Override
    public List<InstitutionFile> lookupDownloadFile(InstitutionType institution, Date batchDate,
                                                    IFileService.FileFilter filter) {
        if(filter == null){
            //默认不过滤文件
            filter = new FileFilter() {
                @Override
                public boolean accept(String filename) {
                    return true;
                }
            };
        }
        if(logger.isInfoEnabled()){
            logger.info("查找下载文件, institution={}, batchDate={} ", institution, batchDate);
        }
        String workspaceDir = fileDirectoryService.getWorkspaceDownloadFileDir(institution);
        File[] workspaceFiles = new File(workspaceDir).listFiles();

        //ftp files
        Sftp sftp = sftpMap.getInstitution(institution);
        String downloadDir = fileDirectoryService.getDownloadFileDir(institution, batchDate);
        Set<String> downloadFiles = new HashSet<>();
        String batchDateString = institution.fileNameDateFormat().format(batchDate);
        Set<String> tmp = sftp.ls(downloadDir+institution.downloadChildDir());
        for(Iterator<String> iter=tmp.iterator(); iter.hasNext();) {
            String filename = iter.next();
            if(filename.contains(batchDateString)) {
                //只取{@param batchDate}批次的文件
                downloadFiles.add(filename);
            }
        }
        //去除已在workspace文件
        for(File file:workspaceFiles){
            if(downloadFiles.contains(file.getName())){
                logger.warn("文件={}，已在工作目录", file.getName());
            }
            downloadFiles.remove(file.getName());
        }
        /**
         * 装备返回结果
         */
        List<InstitutionFile> fileList = new ArrayList<>();
        //workspace
        for(File file:workspaceFiles){
            if(filter.accept(file.getName())) {
                InstitutionFile institutionFile = institutionRepository.
                        findFileByIdentity(institution.filePattern(file.getName()), institution);
                institutionFile.setFileName(file.getName());
                institutionFile.setFileWorkspaceLocation(workspaceDir);
                fileList.add(institutionFile);
            }
        }
        //download
        for(String filename:downloadFiles){
            if(filter.accept(filename)) {
                String src = downloadDir + institution.downloadChildDir() + File.separator + filename;
                sftp.get(src, workspaceDir);
                InstitutionFile institutionFile = institutionRepository.
                        findFileByIdentity(institution.filePattern(filename), institution);
                institutionFile.setFileName(filename);
                institutionFile.setFileWorkspaceLocation(workspaceDir);
                fileList.add(institutionFile);
            }
        }
        return fileList;
    }

    @Override
    public List<InstitutionFile> lookupIncompletedFile(InstitutionType institution, Date batchDate) {
        if(logger.isInfoEnabled()){
            logger.info("查找未处理完成文件, institution={}, batchDate={} ", institution, batchDate);
        }
        File workspaceFileDir = new File(fileDirectoryService.getWorkspaceDownloadFileDir(institution));
        File[] files = workspaceFileDir.listFiles();
        List<InstitutionFile> fileList = new ArrayList<>(files.length);
        for(File file:files){
            InstitutionFile institutionFile = institutionRepository.
                    findFileByIdentity(institution.filePattern(file.getName()), institution);
            institutionFile.setFileName(file.getName());
            institutionFile.setFileWorkspaceLocation(workspaceFileDir.getAbsolutePath());
            fileList.add(institutionFile);
        }
        return fileList;
    }

    @Override
    public List<InstitutionFile> lookupFinishedFile(InstitutionType institution, Date batchDate) {
        ReceivedFile.Criteria criteria = ReceivedFile.criteria().institution(institution)
                .fileName(institution.fileNameDateFormat().format(batchDate));
        if(logger.isInfoEnabled()){
            logger.info("查找处理完成文件, {}", criteria);
        }
        List<ReceivedFile> receivedFiles = receivedFileMapper.queryFile(criteria);
        String backupLocation = fileDirectoryService.getBackupFileDir(institution, batchDate);
        List<InstitutionFile> institutionFiles = new ArrayList<>(receivedFiles.size());
        for(ReceivedFile file:receivedFiles){
            InstitutionFile institutionFile = institutionRepository.
                    findFileByIdentity(institution.filePattern(file.getFileName()), institution);
            institutionFile.setFileName(file.getFileName());
            institutionFile.setFileBackupLocation(backupLocation);
            institutionFiles.add(institutionFile);
        }
        return institutionFiles;
    }

    @Override
    public List<InstitutionFile> lookupNeededFiles(InstitutionType institution, Date batchDate) {
        InstitutionFile.Criteria criteria = InstitutionFile.criteria().isDownloadFile("1").institution(institution);
        if(logger.isInfoEnabled()){
            logger.info("查找需要处理的文件, {}", criteria);
        }
        List<InstitutionFile> institutionFiles = institutionFileMapper.find(criteria);
        for(InstitutionFile file:institutionFiles){
            file.setFileName(batchDate);
        }
        return institutionFiles;
    }

    /*@Override
    public void fileCheckInWorkspace(InstitutionType institution, Date batchDate) {
        String downloadFilePath = fileDirectoryService.getDownloadFileDir(institution,batchDate);
        File downloadFileDir = new File(downloadFilePath);
        File[] files = downloadFileDir.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                //todo 正则匹配文件名格式规范
                return true;
            }
        });
        String workspacePath = fileDirectoryService.getWorkspaceDownloadFileDir(institution);
        for(File file:files) {
            copyFile(file, new File(workspacePath + File.separator + file.getName()));
        }
    }*/

    @Override
    public void fileRollback(InstitutionType institution, Date batchDate) {
        /*File backupDir = new File(fileDirectoryService.getBackupFileDir(institution, batchDate));
        File[] files = backupDir.listFiles();
        String workspacePath = fileDirectoryService.getWorkspaceDownloadFileDir(institution);
        for(File file:files) {
            moveFile(file, new File(workspacePath + File.separator + file.getName()));
        }*/
        if(logger.isInfoEnabled()){
            logger.info("回滚文件记录, institution={}, batchDate={} ", institution, batchDate);
        }
        ReceivedFile.Criteria criteria = ReceivedFile.criteria().institution(institution)
                .batchDate(batchDate);
        receivedFileMapper.rollbackFile(criteria);
    }

    private static void moveFile(File src, File dst){
        try {
            Files.move(src.toPath(), dst.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void copyFile(File src, File dst){
        try {
            Files.copy(src.toPath(), dst.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
