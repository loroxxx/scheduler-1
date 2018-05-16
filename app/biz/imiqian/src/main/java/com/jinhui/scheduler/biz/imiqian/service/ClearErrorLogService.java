package com.jinhui.scheduler.biz.imiqian.service;

import com.jinhui.scheduler.domain.imiqian.domain.ClearErrorLog;

/**
 * 错误日志
 *
 * created by wsc 2017-06-15 11:52
 **/
public interface ClearErrorLogService {

    int saveClearErrorLog(ClearErrorLog clearErrorLog);
}
