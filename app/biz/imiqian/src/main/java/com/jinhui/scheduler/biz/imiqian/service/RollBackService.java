package com.jinhui.scheduler.biz.imiqian.service;

import com.jinhui.scheduler.biz.imiqian.common.Result;

import java.text.ParseException;

/**
 * 回滚服务层
 *
 * create by wsc 2017-06-01 11:32
 **/
public interface RollBackService {

    /**
     * 回滚
     */
   public Result rollBack() throws ParseException;
}
