package com.jinhui.scheduler.biz.core.common;


import com.jinhui.scheduler.biz.core.file.support.Sftp;
import com.jinhui.scheduler.domain.common.InstitutionFile;
import com.jinhui.scheduler.domain.common.InstitutionType;
import java.io.File;
import java.util.Date;
import java.util.List;

/**
 * Created by jinhui on 2017/5/23.
 */
public interface IFileService {
    /**
     * 备份文件
     * @param file 需要备份目标文件
     */
    void backupFile(File file);

    /**
     * 向机构上传文件
     * @param file
     */
    void uploadFile(File file);

    /**
     * 向机构上传文件
     * @param file
     * @param action 文件上传完成动作
     */
    void uploadFile(File file, Sftp.UploadCompletedAction action);

    /**
     * 查找发往机构的文件
     * @param institution
     * @param batchDate
     * @return
     */
    List<InstitutionFile> lookupUploadFile(InstitutionType institution, Date batchDate);

    /**
     * 查找机构给的文件：
     *  1,检查{@link IFileDirectoryService#getWorkspaceDownloadFileDir(InstitutionType)}目录是否有文件;
     *  2,检查{@link IFileDirectoryService#getSftpFileDir(InstitutionType, Date)}
     *      或{@link IFileDirectoryService#getDownloadFileDir(InstitutionType, Date)}
     *      目录下所需文件是否和workspace目录一致,否则下载到workspace目录;
     * @param institution
     * @return
     */
    List<InstitutionFile> lookupDownloadFile(InstitutionType institution, Date batchDate);

    /**
     * 支持文件过滤
     * @param institution
     * @param batchDate
     * @param filter
     * @return
     */
    List<InstitutionFile> lookupDownloadFile(InstitutionType institution, Date batchDate,
                                             IFileService.FileFilter filter);

    /**
     * {@link IFileDirectoryService#getWorkspaceDownloadFileDir(InstitutionType)} 目录下的文件.
     * @param institution
     * @return
     */
    List<InstitutionFile> lookupIncompletedFile(InstitutionType institution, Date batchDate);

    /**
     * 查找处理完成的文件
     * @param institution
     * @return
     */
    List<InstitutionFile> lookupFinishedFile(InstitutionType institution, Date batchDate);

    /**
     * 查找该批次需要处理的对应机构的文件
     * @param institution
     * @param batchDate
     * @return
     */
    List<InstitutionFile> lookupNeededFiles(InstitutionType institution, Date batchDate);

    /**
     * 将机构上传的文件检入工作目录
     * @param institution
     * @param batchDate
     */
    //void fileCheckInWorkspace(InstitutionType institution, Date batchDate);

    /**
     * 将文件恢复到工作目录
     * @param institution
     * @param batchDate
     */
    void fileRollback(InstitutionType institution, Date batchDate);

    interface FileFilter{
        /**
         * 过滤文件返回结果
         * @param filename
         * @return true:返回, false:不返回
         */
        boolean accept(String filename);
    }
}
