package com.jinhui.scheduler.biz.gzefe.service.export.impl;

import com.jinhui.scheduler.biz.core.common.IFileDirectoryService;
import com.jinhui.scheduler.biz.core.common.IFileService;
import com.jinhui.scheduler.biz.gzefe.constant.GzefeExportSteps;
import com.jinhui.scheduler.biz.gzefe.service.export.ExportService;
import com.jinhui.scheduler.biz.gzefe.service.gather.BizGatherService;
import com.jinhui.scheduler.biz.shared.launch.support.SyncJobLauncher;
import com.jinhui.scheduler.data.core.mapper.gzefe.ExchangeFileLogMapper;
import com.jinhui.scheduler.data.core.mapper.gzefe.GzefeBusinessGatherMapper;
import com.jinhui.scheduler.data.core.mapper.imiqian.BatchStateCurrentMapper;
import com.jinhui.scheduler.domain.common.ChannelCode;
import com.jinhui.scheduler.domain.common.InstitutionFile;
import com.jinhui.scheduler.domain.common.InstitutionType;
import com.jinhui.scheduler.domain.gzefe.ExchangeFileLog;
import com.jinhui.scheduler.domain.imiqian.domain.BatchStateCurrent;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/12/8 0008.
 */
@Service("ExportService")
public class ExportServiceImpl implements ExportService {

    private final static Logger logger = LoggerFactory.getLogger(ExportServiceImpl.class);

    @Autowired
    private BatchStateCurrentMapper batchStateCurrentMapper;

    @Autowired
    private SyncJobLauncher syncJobLauncher;

    @Autowired
    private JobRegistry jobRegistry;

    @Autowired
    private IFileDirectoryService iFileDirectoryService;

    @Autowired
    private IFileService iFileService;

    @Autowired
    private ExchangeFileLogMapper exchangeFileLogMapper;

    @Autowired
    private GzefeBusinessGatherMapper gzefeBusinessGatherMapper;

    @Autowired
    private BizGatherService bizGatherService;


    @Override
    public boolean export(String batchDate) throws Exception {


        //统计汇总信息
        bizGatherService.statisticsChildProduct();


        BatchStateCurrent currBatchState = batchStateCurrentMapper.findCurrentBatch();

        String generatePath = iFileDirectoryService.getWorkspaceUploadFileDir(InstitutionType.Gzefe);

        Job job = jobRegistry.getJob("gzefeApplyJob");

        JobParameters parameters = new JobParametersBuilder()
                .addString("generatePath", generatePath)
                .addString("timestamp", System.currentTimeMillis() + "")
                .addString("batchCode", currBatchState.getBatchCode() + "")
                .addString("chnCode", ChannelCode.Imiqian.getCode())
                .addString("exchangeCode", InstitutionType.Gzefe.getAbbr())
                .addString("applyDate", currBatchState.getDate())
                .toJobParameters();
        JobExecution jobExecution = syncJobLauncher.run(job, parameters);


        DateTime dt = DateTime.parse(currBatchState.getDate(), DateTimeFormat.forPattern("yyyyMMdd"));

        List<InstitutionFile> files = iFileService.lookupUploadFile(InstitutionType.Gzefe, dt.toDate());
        if (jobExecution.getStatus() == BatchStatus.COMPLETED && files.size() == 5) {

            writeLog(GzefeExportSteps.STEP_5, currBatchState.getBatchCode(), currBatchState.getDate(), files.size());

            return true;
        } else {
            return false;
        }

    }

    @Override
    public List<InstitutionFile> queryFiles(String batchDate) {

        DateTime parse = DateTime.parse(batchDate, DateTimeFormat.forPattern("yyyyMMdd"));
        Date date = parse.toDate();

        return iFileService.lookupUploadFile(InstitutionType.Gzefe, date);
    }

    @Override
    public boolean isExport(String batchDate) {

        int count = exchangeFileLogMapper.selectGzefeFileCount(batchDate);

        if (count == 4) {
            return true;
        } else {
            return false;
        }

    }

    @Override
    public void rollback(int batchCode, String batchDate) {


        exchangeFileLogMapper.deleteByBatchDate(batchDate);

        gzefeBusinessGatherMapper.deleteByBatchDate(batchDate);


        writeLog(GzefeExportSteps.STEP_6, batchCode, batchDate, 0);

    }


    private void writeLog(GzefeExportSteps step, Integer batchCode, String batchDate, int size) {

        ExchangeFileLog fileLog = new ExchangeFileLog();
        fileLog.setStep(step.name());
        fileLog.setStepCode(step.getCode());
        fileLog.setStepDesc(step.getDesc());
        fileLog.setCreateTime(new Date());
        fileLog.setStatus("0");//成功
        fileLog.setBatchCode(batchCode);
        fileLog.setTotalCount(size);
        DateTime dt = DateTime.parse(batchDate, DateTimeFormat.forPattern("yyyyMMdd"));
        fileLog.setBatchDate(dt.toDate());
        fileLog.setExchangeNo(InstitutionType.Gzefe.getAbbr());
        exchangeFileLogMapper.insert(fileLog);

    }

}
