package com.jinhui.scheduler.domain.common;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by jinhui on 2017/6/8.
 */
public interface IFile {

    /**
     * 根据批次日期和文件模式获取文件名
     * @param filePattern
     * @param batchDate
     * @return
     */
    String fileNameWithoutSeq(String filePattern, Date batchDate);

    /**
     * 根据文件名，获取文件模式
     * @param fileName
     * @return
     */
    String filePattern(String fileName);

    /**
     * 文件名中日期格式化
     * @return
     */
    DateFormat fileNameDateFormat();

    /**
     * 是否支持一天多批次文件
     * @return
     */
    boolean multiBatch();

    /**
     * 在文件中加入批次序号
     * @param fileNamePatternWithDate
     * @param seq
     * @return
     */
    String fileNameWithSeq(String fileNamePatternWithDate, int seq);

    /**
     * 获取当日批次的下载文件路径
     * @param basePath
     * @param batchDate
     * @return
     */
    String downloadFilePath(String basePath, Date batchDate);

    /**
     * 获取当日批次的上传文件路径
     * @param basePath
     * @param batchDate
     * @return
     */
    String uploadFilePath(String basePath, Date batchDate);

    /**
     * 个别机构会以文件名对应的不同类型存放文件
     * @param name
     * @return
     */
    String childDirByName(String name);

    /**
     * 下载文件子目录
     * @return
     */
    String downloadChildDir();
}
