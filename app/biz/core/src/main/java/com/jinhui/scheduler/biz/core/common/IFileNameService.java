package com.jinhui.scheduler.biz.core.common;

import com.jinhui.scheduler.domain.common.InstitutionType;

import java.util.Date;

/**
 * Created by jinhui on 2017/5/23.
 */
public interface IFileNameService {
    /**
     *
     * @param institution
     * @param fileNamePattern 文件名模式,
     *         eg: 招银产品发布文件{@fileNamePattern = product_1000_current_publish_(\\d{8})_(\\d{3}).req.txt}
     * @param batchDate
     * @return
     */
    String getFileName(InstitutionType institution, String fileNamePattern, Date batchDate);
}
