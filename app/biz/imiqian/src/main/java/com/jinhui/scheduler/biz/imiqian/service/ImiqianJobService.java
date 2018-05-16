package com.jinhui.scheduler.biz.imiqian.service;

import com.jinhui.scheduler.biz.imiqian.common.Result;
import com.jinhui.scheduler.biz.imiqian.pojo.ApplyImport;
import com.jinhui.scheduler.biz.imiqian.pojo.Clear;
import com.jinhui.scheduler.biz.imiqian.pojo.ConfrimExport;

import java.text.ParseException;

/**
 *
 * 爱蜜钱服务层
 * create by wsc 2017-06-07 16:06
 *
 **/
public interface ImiqianJobService {

    /**
     * 申请文件导入
     * @return
     */
    public Result<ApplyImport> applyImport() throws Exception;

    /**
     * 清算
     * @return
     */
    public Result<Clear> clear(String batchDate);

    /**
     * 确认文件导出
     * @return
     */
    public Result<ConfrimExport> confirmExport() throws Exception;

    /**
     * 回滚
     * @return
     */
    public Result rollback() throws ParseException;

    /**
     * 查询清算任务
     * @return
     */
    public Result findClearTask(String batchDate) throws ParseException;

    /**
     * 查询清算是否成功
     * @return
     */
    public Result findClearResult(String batchDate);

    /**
     * 查询导出确认文件是否成功
     * @return
     */
    public Result findExportResult(String batchDate);


}
