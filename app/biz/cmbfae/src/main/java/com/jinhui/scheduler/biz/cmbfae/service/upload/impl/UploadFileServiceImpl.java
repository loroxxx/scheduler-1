package com.jinhui.scheduler.biz.cmbfae.service.upload.impl;

import com.jinhui.scheduler.biz.cmbfae.constans.CmbfaeExportSteps;
import com.jinhui.scheduler.biz.cmbfae.service.log.LogService;
import com.jinhui.scheduler.biz.cmbfae.service.upload.UploadFileService;
import com.jinhui.scheduler.biz.cmbfae.util.CommonUtil;
import com.jinhui.scheduler.biz.core.batch.BatchStateService;
import com.jinhui.scheduler.data.core.mapper.cmbfae.ExchangeFileLogDao;
import com.jinhui.scheduler.domain.cmbfae.ExchangeFileLog;
import com.jinhui.scheduler.domain.cmbfae.model.BatchState;
import com.jinhui.scheduler.biz.core.common.IFileService;
import com.jinhui.scheduler.domain.common.ExchangeType;
import com.jinhui.scheduler.domain.common.InstitutionFile;
import com.jinhui.scheduler.domain.common.InstitutionType;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

/**
 * Created by jinhui on 2017/6/26.
 */
@Service("UploadFileService")
public class UploadFileServiceImpl implements UploadFileService {

    private static Logger log = LoggerFactory.getLogger(UploadFileServiceImpl.class);

    @Autowired
    private LogService logService;

    @Autowired
    private IFileService IFileService;

    @Autowired
    private BatchStateService batchStateService;

    @Autowired
    private ExchangeFileLogDao exchangeFileLogDao;

    @Override
    public void uploadFile() {


        BatchState current = batchStateService.getBatchStateCurrent();

        String exchangeNo = ExchangeType.Cmbfae.getExchangeNo();


        // 避免出现有生成失败的情况，要先检查Step1，Step2,Step3都已经完成
        int count = exchangeFileLogDao.queryStepCount(current.getDate(), exchangeNo);
        if (count != 3) {
            return;
        }

        CmbfaeExportSteps step = logService.queryStep(current.getDate(), exchangeNo);

        if (step.equals(CmbfaeExportSteps.STEP_3)) {//文件已经全部生成，开始上传交易文件

            //找到交易文件路径
            ExchangeFileLog fileLog = logService.queryStepByCode(CmbfaeExportSteps.STEP_1, current.getDate(), exchangeNo);
            String transFilePath = fileLog.getFilePath();

            if (StringUtils.isBlank(transFilePath)) {//没有交易文件上传
                logService.writeLog(CmbfaeExportSteps.STEP_4, "");
            } else {
                String fileName = new File(transFilePath).getName();
                uploadFile(transFilePath);
                //保存文件名
                logService.writeLog(CmbfaeExportSteps.STEP_4, fileName);
            }
            return;

        }

        if (step.equals(CmbfaeExportSteps.STEP_4)) {//交易文件上传成功，开始确认交易文件是否已经返回

            //查找交易确认文件

            ExchangeFileLog fileLog = logService.queryStepByCode(CmbfaeExportSteps.STEP_4, current.getDate(), exchangeNo);
            String transFileName = fileLog.getFilePath();

            if (StringUtils.isBlank(transFileName)) {//没有交易文件上传
                logService.writeLog(CmbfaeExportSteps.STEP_5);
            } else {
                List<InstitutionFile> files = downFiles(transFileName, current);

                if (files.size() != 0) {
                    //检查确认文件是否正确,
                    if (!CommonUtil.checkFiles(files)) {
                        //正确则写入日志
                        //TODO 日记也记录文件的名称和文件内的数据条数
                        logService.writeLog(CmbfaeExportSteps.STEP_5);
                        //备份文件
                        backupFiles(files);
                    }
                }
            }


            return;
        }


        if (step.equals(CmbfaeExportSteps.STEP_5)) { //交易文件确认成功，上传持仓文件

            //找到持仓文件路径
            ExchangeFileLog fileLog = logService.queryStepByCode(CmbfaeExportSteps.STEP_2, current.getDate(), exchangeNo);
            String positionFilePath = fileLog.getFilePath();
            String fileName = new File(positionFilePath).getName();
            uploadFile(positionFilePath);
            logService.writeLog(CmbfaeExportSteps.STEP_6, fileName);
            return;
        }


        if (step.equals(CmbfaeExportSteps.STEP_6)) {//持仓文件上传成功，开始确认持仓文件是否已经返回

            //下载持仓确认文件
            ExchangeFileLog fileLog = logService.queryStepByCode(CmbfaeExportSteps.STEP_6, current.getDate(), exchangeNo);
            String positionFileName = fileLog.getFilePath();
            List<InstitutionFile> files = downFiles(positionFileName, current);

            //检查确认文件是否正确,
            if (files.size() != 0) {
                if (!CommonUtil.checkFiles(files)) {
                    //正确则写入日志
                    logService.writeLog(CmbfaeExportSteps.STEP_7);
                    //备份文件
                    backupFiles(files);
                }
            }

            return;

        }


        if (step.equals(CmbfaeExportSteps.STEP_7)) {//持仓文件确认成功，开始时候上传收益文件

            //找到收益文件路径
            ExchangeFileLog fileLog = logService.queryStepByCode(CmbfaeExportSteps.STEP_3, current.getDate(), exchangeNo);
            String profitFilePath = fileLog.getFilePath();
            String fileName = new File(profitFilePath).getName();
            uploadFile(profitFilePath);
            logService.writeLog(CmbfaeExportSteps.STEP_8, fileName);
            return;
        }

        if (step.equals(CmbfaeExportSteps.STEP_8)) {//确认收益文件

            //下载收益确认文件
            ExchangeFileLog fileLog = logService.queryStepByCode(CmbfaeExportSteps.STEP_8, current.getDate(), exchangeNo);
            String profitFileName = fileLog.getFilePath();
            List<InstitutionFile> files = downFiles(profitFileName, current);

            //检查确认文件是否正确,

            if (files.size() != 0) {
                if (!CommonUtil.checkFiles(files)) {
                    //正确则写入日志
                    logService.writeLog(CmbfaeExportSteps.STEP_9);
                    //备份文件
                    backupFiles(files);
                }
            }

            return;

        }


    }


    public void uploadFile(final String filePath) {

        File file = new File(filePath);
        if (file.isFile() && file.exists()) {
            IFileService.uploadFile(file);
        }
    }


    private List<InstitutionFile> downFiles(String fileName, BatchState current) {

        //上传的文件名是xxx.req.txt,返回的是xxx.resp.txt

        String[] split = fileName.split("\\.");
        String name = split[0];

        List<InstitutionFile> files = IFileService.lookupDownloadFile(InstitutionType.Cmbfae, current.getDate(), filename -> {
            if (filename.contains(name) && filename.contains("resp")) {
                return true;
            }
            return false;
        });
        return files;
    }


    private void backupFiles(List<InstitutionFile> files) {

        for (InstitutionFile file : files) {
            String filePath = file.getFileWorkspaceLocation() + File.separator + file.getFileName();
            IFileService.backupFile(new File(filePath));
        }

    }


}