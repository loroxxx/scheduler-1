package com.jinhui.scheduler.biz.imiqian.service.impl;

import com.jinhui.scheduler.biz.imiqian.service.ClearErrorLogService;
import com.jinhui.scheduler.data.core.mapper.imiqian.ClearErrorLogMapper;
import com.jinhui.scheduler.domain.imiqian.domain.ClearErrorLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 错误日志实现
 *
 * created by wsc 2017-06-15 11:53
 **/
@Service("clearErrorLogService")
public class ClearErrorLogServiceImpl implements ClearErrorLogService{

    @Autowired
    ClearErrorLogMapper clearErrorLogMapper;

    @Override
    public int saveClearErrorLog(ClearErrorLog clearErrorLog) {
        return clearErrorLogMapper.save(clearErrorLog);
    }
}
