package com.jinhui.scheduler.biz.cmbfae.service.log;

import java.util.Date;

import com.jinhui.scheduler.biz.cmbfae.constans.CmbfaeExportSteps;
import com.jinhui.scheduler.biz.core.batch.BatchStateService;
import com.jinhui.scheduler.biz.core.exception.BizException;
import com.jinhui.scheduler.data.core.mapper.cmbfae.ExchangeFileLogDao;
import com.jinhui.scheduler.domain.cmbfae.ExchangeFileLog;
import com.jinhui.scheduler.domain.cmbfae.model.BatchState;
import com.jinhui.scheduler.domain.common.ExchangeType;
import com.jinhui.scheduler.domain.common.InstitutionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("LogService")
public class LogServiceImpl implements LogService {

    @Autowired
    private BatchStateService batchStateService;

    @Autowired
    private ExchangeFileLogDao exchangeFileLogMapper;



    @Override
    public void writeLog(CmbfaeExportSteps step) {

        writeLog(step, "",0);

    }

    @Override
    public void writeProductLog(CmbfaeExportSteps step, String filePath) {
        ExchangeFileLog log = new ExchangeFileLog();
        BatchState current = batchStateService.getBatchStateCurrent();
        log.setStep(step.name());
        log.setBatchCode(current.getBatchCode());
        log.setBatchDate(current.getDate());
        log.setCreateTime(new Date());
        log.setStepCode(step.getCode());
        log.setStepDesc(step.getDesc());
        log.setFilePath(filePath);
        log.setStatus("0");
        log.setExchangeCode(ExchangeType.Cmbfae.getExchangeNo()+"_PRODUCT");
        exchangeFileLogMapper.insert(log);
    }

    @Override
    public void writeLog(CmbfaeExportSteps step, String filePath) {
        writeLog(step, filePath,0);
    }

    @Override
    public void writeLog(CmbfaeExportSteps step, String filePath,int count) {

        ExchangeFileLog log = new ExchangeFileLog();
        BatchState current = batchStateService.getBatchStateCurrent();
        log.setStep(step.name());
        log.setBatchCode(current.getBatchCode());
        log.setBatchDate(current.getDate());
        log.setCreateTime(new Date());
        log.setStepCode(step.getCode());
        log.setStepDesc(step.getDesc());
        log.setStatus("0");
        log.setTotalCount(count);
        log.setFilePath(filePath);
        log.setExchangeCode(ExchangeType.Cmbfae.getExchangeNo());
        exchangeFileLogMapper.insert(log);
    }



    @Override
    public ExchangeFileLog queryStepByCode(CmbfaeExportSteps step, Date date, String exchangeNo) {

        ExchangeFileLog fileLog = exchangeFileLogMapper.queryStepByCode(step.name(), date, exchangeNo);


        return fileLog;
    }


    @Override
    public CmbfaeExportSteps queryStep(Date date,String exchangeNo) {

        ExchangeFileLog exchangeFileLog = exchangeFileLogMapper.queryStep(date, exchangeNo);

        if (exchangeFileLog==null){
            return CmbfaeExportSteps.STEP_START;
        }

        CmbfaeExportSteps[] values = CmbfaeExportSteps.values();
        for (CmbfaeExportSteps value : values) {
            if(value.name().equals(exchangeFileLog.getStep())){
            return value;
            }
        }
        throw new BizException("不支持的步骤："+exchangeFileLog.getStep());
    }
}
