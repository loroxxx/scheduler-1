package com.jinhui.scheduler.biz.cmbfae.service.log;

import java.util.Date;

import com.jinhui.scheduler.biz.cmbfae.constans.CmbfaeExportSteps;
import com.jinhui.scheduler.domain.cmbfae.ExchangeFileLog;

public interface LogService {


    void writeLog(CmbfaeExportSteps step);

    void writeProductLog(CmbfaeExportSteps step ,String filePath);

    void writeLog(CmbfaeExportSteps step, String filePath);

    void writeLog(CmbfaeExportSteps step, String filePath,int count);

    ExchangeFileLog queryStepByCode(CmbfaeExportSteps step, Date date, String exchangeNo);

    /**
     * 查询日志到了第几步
     * @return
     */
    CmbfaeExportSteps queryStep(Date date, String exchangeNo);




}
