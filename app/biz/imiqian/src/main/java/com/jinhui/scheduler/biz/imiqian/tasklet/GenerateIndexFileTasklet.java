package com.jinhui.scheduler.biz.imiqian.tasklet;


import com.jinhui.scheduler.biz.imiqian.common.AmqAccountFileConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.util.ResourceUtils;

import java.io.*;

/**
 * 生成索引文件
 * create by wsc 2017-06-01 11:32
 *
 **/

public class GenerateIndexFileTasklet implements Tasklet {
    private final static Logger logger = LoggerFactory.getLogger(GenerateIndexFileTasklet.class);

    private  String filePath;
    private  String applyDate;
    private  String chnCode;

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public void setApplyDate(String applyDate) {
        this.applyDate = applyDate;
    }

    public void setChnCode(String chnCode) {
        this.chnCode = chnCode;
    }

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        filePath = filePath.replace("file:","");
        File file = ResourceUtils.getFile(AmqAccountFileConstants.HEADERFILEPATH_INDEX);//索引确认文件头信息
        StringBuilder result= new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String s = "";
            while ((s = br.readLine()) != null) {
                if (s.contains("#{applyDate}")) {
                    s = s.replace("#{applyDate}",applyDate);
                }if (s.contains("#{chnCode}")) {
                    s = s.replace("#{chnCode}",chnCode);
                }
                result.append(s + System.lineSeparator());
            }
            br.close();
            OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(filePath + File.separator+"OFI_JFB666_"+chnCode+"_" + applyDate + ".TXT"),"GBK");
            writer.write(result.toString());
            writer.close();
        } catch (Exception e) {
            logger.info("索引文件生成出错了",e);
        }
        return RepeatStatus.FINISHED;
    }
}
