package com.jinhui.scheduler.biz.core.common;

import com.jinhui.scheduler.domain.common.InstitutionType;

import java.util.Date;

/**
 * Created by jinhui on 2017/5/23.
 */
public interface IFileDirectoryService {

    /**
     * 返回对应机构提供的ftp目录
     * @param institution
     * @param batchDate
     * @return
     */
    String getSftpFileDir(InstitutionType institution, Date batchDate);

    /**
     * 获取对应机构的上传目录
     * @param institution
     * @param batchDate 跑批日期, 由于跑批时间不确定一定发生在当天或可能回滚,所以每次都需要确定跑批日期
     * @return 对应机构指定跑批日的上次目录
     */
    String getUploadFileDir(InstitutionType institution, Date batchDate);

    String getWorkspaceUploadFileDir(InstitutionType institution);

    /**
     * 获取对应机构的下载目录
     * @param institution
     * @param batchDate
     * @return
     */
    String getDownloadFileDir(InstitutionType institution, Date batchDate);

    /**
     * 获取对应机构的下载文件的工作目录
     * @param institution
     * @return
     */
    String getWorkspaceDownloadFileDir(InstitutionType institution);

    /**
     * 获取对应机构的文件备份目录
     * @param institution
     * @param batchDate 跑批日期, 由于跑批时间不确定一定发生在当天或可能回滚,所以每次都需要确定跑批日期
     * @return
     */
    String getBackupFileDir(InstitutionType institution, Date batchDate);

    /**
     * 为机构提前一天生成文件上传目录
     * @param institution
     */
    @Deprecated
    void genDownloadFileDir(InstitutionType institution);

}
