package com.jinhui.scheduler.biz.cmbfae.service.export.impl;

import com.jinhui.scheduler.biz.cmbfae.constans.CmbfaeExportSteps;
import com.jinhui.scheduler.biz.cmbfae.service.export.CmbfaeExportService;
import com.jinhui.scheduler.biz.cmbfae.service.log.LogService;
import com.jinhui.scheduler.biz.cmbfae.service.position.PositionService;
import com.jinhui.scheduler.biz.cmbfae.service.profit.ProfitService;
import com.jinhui.scheduler.biz.cmbfae.service.trans.TransService;
import com.jinhui.scheduler.biz.cmbfae.util.CommonUtil;
import com.jinhui.scheduler.biz.core.batch.BatchStateService;
import com.jinhui.scheduler.biz.core.common.IFileDirectoryService;
import com.jinhui.scheduler.biz.core.common.IFileNameService;
import com.jinhui.scheduler.biz.core.common.IFileService;
import com.jinhui.scheduler.biz.core.exception.BizException;
import com.jinhui.scheduler.biz.shared.launch.support.SyncJobLauncher;
import com.jinhui.scheduler.data.core.mapper.cmbfae.ExchangeFileLogDao;
import com.jinhui.scheduler.data.core.mapper.cmbfae.InvestorIncomeDao;
import com.jinhui.scheduler.data.core.mapper.cmbfae.InvestorPositionDao;
import com.jinhui.scheduler.data.core.mapper.cmbfae.TransDao;
import com.jinhui.scheduler.domain.cmbfae.ExchangeFileLog;
import com.jinhui.scheduler.domain.cmbfae.model.BatchState;
import com.jinhui.scheduler.domain.cmbfae.template.TransSuContent;
import com.jinhui.scheduler.domain.common.ExchangeType;
import com.jinhui.scheduler.domain.common.InstitutionFile;
import com.jinhui.scheduler.domain.common.InstitutionType;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2018/1/16 0016.
 */
@Service("CmbfaeExportService")
public class CmbfaeExportServiceImpl implements CmbfaeExportService {

    @Autowired
    private SyncJobLauncher syncJobLauncher;

    @Autowired
    private JobRegistry jobRegistry;

    @Autowired
    private TransService transService;

    @Autowired
    private BatchStateService batchStateService;

    @Autowired
    private IFileDirectoryService FileDirectoryService;

    @Autowired
    private IFileNameService iFileNameService;

    @Autowired
    private ProfitService profitService;

    @Autowired
    private LogService logService;

    @Autowired
    private IFileService iFileService;

    @Autowired
    private TransDao transDao;

    @Autowired
    private InvestorPositionDao investorPositionDao;

    @Autowired
    private InvestorIncomeDao investorIncomeDao;

    @Autowired
    private ExchangeFileLogDao exchangeFileLogMapper;


    @Override
    public boolean export(String batchDate) throws Exception {


        BatchState current = batchStateService.getBatchStateCurrent();

        String batchCode = String.valueOf(current.getBatchCode());

        String exchangeNo = ExchangeType.Cmbfae.getExchangeNo();

        String transPath = getFilePath("trans", current);

        //检查当前批次日有没有交易，有则生成交易文件
        if (transService.hasTrans()) {
            Job job = jobRegistry.getJob("cmbfaeTransFileJob");
            JobParameters parameters = new JobParametersBuilder()
                    .addString("generatePath", transPath)
                    .addString("timestamp", System.currentTimeMillis() + "")
                    .addString("exchangeNo", exchangeNo)
                    .addString("batchCode", batchCode)
                    .toJobParameters();

            JobExecution jobExecution = syncJobLauncher.run(job, parameters);

            if (jobExecution.getStatus() == BatchStatus.COMPLETED) {

                //交易记录的总条数
                int count = transDao.queryTransCount(current.getBatchCode(), exchangeNo);
                //记日志
                logService.writeLog(CmbfaeExportSteps.STEP_1, transPath, count);
            }
        }else {
            logService.writeLog(CmbfaeExportSteps.STEP_1, "", 0);
        }

        //生成持仓记录
        String positionPath = getFilePath("position", current);
        Job job = jobRegistry.getJob("cmbfaePositionFileJob");
        JobParameters parameters = new JobParametersBuilder()
                .addString("generatePath", positionPath)
                .addString("timestamp", System.currentTimeMillis() + "")
                .addString("exchangeNo", exchangeNo)
                .toJobParameters();

        JobExecution jobExecution = syncJobLauncher.run(job, parameters);
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            int count = investorPositionDao.queryTotalCount(exchangeNo);
            //记日志
            logService.writeLog(CmbfaeExportSteps.STEP_2, positionPath, count);
        }

        //生成收益记录
        String profitPath = getFilePath("profit", current);
        profitService.exportFile(profitPath);

        List<String> products = investorIncomeDao.queryChildProductNo(exchangeNo);

        logService.writeLog(CmbfaeExportSteps.STEP_3, profitPath, products.size());

        //上传文件通过定时任务UploadFileService，按顺序上传


        // 检查是否生成了三个文件
        int count = exchangeFileLogMapper.queryStepCount(current.getDate(),ExchangeType.Cmbfae.getExchangeNo());
        if(count!=3){
            throw new BizException("招银导出文件生成失败，请联系管理员");
        }

        return  true;

    }

    @Override
    public List<InstitutionFile> queryFiles(String batchDate) {

        DateTime parse = DateTime.parse(batchDate, DateTimeFormat.forPattern("yyyyMMdd"));
        Date date = parse.toDate();

        return iFileService.lookupUploadFile(InstitutionType.Cmbfae, date);


    }

    @Override
    public boolean isExport(String batchDate) {


        BatchState current = batchStateService.getBatchStateCurrent();

        CmbfaeExportSteps step = logService.queryStep(current.getDate(), ExchangeType.Cmbfae.getExchangeNo());

        if (step.equals(CmbfaeExportSteps.STEP_START)) {
            return false;
        }

        return true;
    }

    @Override
    public void rollback(int batchCode, String batchDate) {

    }


    /**
     * 获取文件的生成路径和文件名
     */
    private String getFilePath(String fileType, BatchState current) {

        // 获取上传目录
        String fileDir = FileDirectoryService.getWorkspaceUploadFileDir(InstitutionType.Cmbfae);

        String str = "_(\\d{8})_(\\d{3}).req.txt";
        // 生成交易文件名
        String transName = CommonUtil.getJsonFileName(fileType);
        String transFileName = iFileNameService.getFileName(InstitutionType.Cmbfae, transName + str,
                current.getDate());
        String transPath = fileDir + File.separator + transFileName;
        return transPath;
    }


}
