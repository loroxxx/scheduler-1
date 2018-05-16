package com.jinhui.scheduler.biz.imiqian.tasklet;


import com.jinhui.scheduler.biz.core.common.IFileService;
import com.jinhui.scheduler.biz.imiqian.common.AmqAccountFileConstants;
import com.jinhui.scheduler.biz.imiqian.common.AmqClearStepConst;
import com.jinhui.scheduler.biz.imiqian.service.ClearErrorLogService;
import com.jinhui.scheduler.biz.imiqian.utils.DateUtils;
import com.jinhui.scheduler.domain.imiqian.domain.ClearErrorLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class CheckIndexFileTasklet implements Tasklet {
    private final static Logger LOGGER = LoggerFactory.getLogger(CheckIndexFileTasklet.class);

    @Autowired
    IFileService iFileService;
    @Autowired
    ClearErrorLogService clearErrorLogService;


//    private final Resource fileResource;
    private final String dirPath;
    private final String date;
    private final String batchCode;
    private final String chnCode;
    private Boolean illegal = true;

    @Override
    public RepeatStatus execute(StepContribution contribution,
                                ChunkContext chunkContext) throws Exception {
        File file = new File(dirPath + "///"  + "OFI_"+chnCode
                +"_JFB666_" + date + ".TXT");
        if(!file.exists()){
            saveClearErrorLog("索引文件不存在！");
            illegal = false;
        }

        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "GBK"));
            String tmp = "";
            int linenum = 1;
            int filenum = 0;            //文件个数
            while ((tmp = bufferedReader.readLine()) != null) {
                if(linenum < 6){
                    checkIndexFile(linenum, tmp, date);
                }else if(linenum == 6){
                    filenum = Integer.parseInt(tmp);
                }else if(linenum > 6){
                    if(filenum > 0){
                        File tmpfile = new File(dirPath + "///" + tmp);
                        if(!tmpfile.exists()){
                            saveClearErrorLog("索引文件中标明的文件不存在！");
                            illegal = false;
                        }else{
                            filenum--;
                        }
                    }else{
                        if(!AmqAccountFileConstants.FILE_ENDFLAG.equals(tmp)){
                            saveClearErrorLog("索引文件中的结束标识字段错误！");
                            illegal = false;
                        }
                    }
                }
                linenum++;
            }
            bufferedReader.close();
        } catch (Exception e) {
            saveClearErrorLog("索引文件解析失败！");
            illegal = false;
            return null;
        }
        if(!illegal){
            return null;
        }
        iFileService.backupFile(file);
        return RepeatStatus.FINISHED;
    }

    public CheckIndexFileTasklet(String dirPath, String date, String batchCode, String chnCode) {
        this.dirPath = dirPath;
        this.date = date;
        this.batchCode = batchCode;
        this.chnCode = chnCode;
    }

    private void checkIndexFile(int linenum, String str, String filedate){
        String error = "";
        switch (linenum){
            case 1:
                if(!AmqAccountFileConstants.INDEXFILE_STARTFLAG.equals(str)){
                    error = "索引文件中的文件开始标识字段错误！";
                    saveClearErrorLog(error);
                    illegal = false;
                }
                break;
            /*case 2:
                if(!AmqAccountFileConstants.FILE_VERSION.equals(str.trim())){
                    saveClearErrorLog("索引文件中的文件版本号字段错误！");
                    throw new IllegalArgumentException("索引文件中的文件版本号字段错误！");
                }
                break;*/
            case 3:
                if(!chnCode.equals(str.trim())){
                    error = "索引文件中的文件创建人字段错误！";
                    saveClearErrorLog(error);
                    illegal = false;
                }
                break;
            case 4:
                if(!AmqAccountFileConstants.FILE_TA.equals(str.trim())){
                    error = "索引文件中的文件接收人字段错误！";
                    saveClearErrorLog(error);
                    illegal = false;
                }
                break;
            /*case 5:
                if(!filedate.equals(str)){
                    throw new IllegalArgumentException("索引文件中的文件日期字段错误！");
                }
                break;*/
            default:
                break;
        }
    }

    private void saveClearErrorLog(String errorInfo){
        ClearErrorLog clearErrorLog = new ClearErrorLog();
        clearErrorLog.setBatchCode(Integer.parseInt(batchCode));
        clearErrorLog.setBatchDate(date);
        clearErrorLog.setChnCode(chnCode);
        clearErrorLog.setStepCode(AmqClearStepConst.ClearSteps.STEP_0.getClearCode());
        clearErrorLog.setStepDesc(AmqClearStepConst.ClearSteps.STEP_0.getClearDesc());
        clearErrorLog.setErrorInfo(errorInfo);
        clearErrorLog.setTargetCode(this.getClass().getSimpleName());
        clearErrorLog.setCreateTime(DateUtils.getCurrentDatetime());

        clearErrorLogService.saveClearErrorLog(clearErrorLog);
    }
}
