package com.jinhui.scheduler.biz.gzefe.listen;

import com.jinhui.scheduler.biz.gzefe.constant.GzefeExportSteps;
import com.jinhui.scheduler.data.core.mapper.gzefe.ExchangeFileLogMapper;
import com.jinhui.scheduler.domain.gzefe.ExchangeFileLog;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * 记录step执行日志
 **/
public class GzefeStepListener implements StepExecutionListener {


    @Autowired
    private ExchangeFileLogMapper exchangeFileLogMapper;


    @Override
    public void beforeStep(StepExecution stepExecution) {

    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        if ("COMPLETED".equals(stepExecution.getStatus().toString())) {

            String stepName = stepExecution.getStepName();
            JobParameters parameters = stepExecution.getJobParameters();
            String applyDate = parameters.getString("applyDate");
            DateTime dt = DateTime.parse(applyDate, DateTimeFormat.forPattern("yyyyMMdd"));
            String batchCode = parameters.getString("batchCode");
            String exchangeName = parameters.getString("exchangeCode");
            int readCount = stepExecution.getReadCount();

            GzefeExportSteps[] steps = GzefeExportSteps.values();
            for (GzefeExportSteps step : steps) {
                if (stepName.contains(step.getCode())) {
                    ExchangeFileLog fileLog = new ExchangeFileLog();
                    fileLog.setStep(step.name());
                    fileLog.setStepCode(step.getCode());
                    fileLog.setStepDesc(step.getDesc());
                    fileLog.setCreateTime(new Date());
                    fileLog.setStatus("0");//成功
                    fileLog.setBatchCode(Integer.valueOf(batchCode));
                    fileLog.setBatchDate(dt.toDate());
                    fileLog.setExchangeNo(exchangeName);
                    fileLog.setTotalCount(readCount);
                    exchangeFileLogMapper.insert(fileLog);
                }
            }

        }


        return null;
    }


}
