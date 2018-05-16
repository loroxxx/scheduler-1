package com.jinhui.scheduler.biz.gzefe.tasklet;

import com.jinhui.scheduler.biz.gzefe.constant.GzefeExportSteps;
import com.jinhui.scheduler.biz.gzefe.constant.GzefeFileConstants;
import com.jinhui.scheduler.data.core.mapper.gzefe.ExchangeFileLogMapper;
import com.jinhui.scheduler.domain.gzefe.ExchangeFileLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.Date;

/**
 * Created by Administrator on 2017/11/13 0013.
 */
public class GenerateIndexFileTasklet implements Tasklet {
    private final static Logger logger = LoggerFactory.getLogger(GenerateIndexFileTasklet.class);

    private String filePath;
    private String applyDate;

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public void setApplyDate(String applyDate) {
        this.applyDate = applyDate;
    }

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {

        filePath = filePath.replace("file:", "");
        File file = ResourceUtils.getFile(GzefeFileConstants.HEADERFILEPATH_INDEX);//索引确认文件头信息
        StringBuilder result = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String s = "";
            while ((s = br.readLine()) != null) {
                if (s.contains("#{applyDate}")) {
                    s = s.replace("#{applyDate}", applyDate);
                }
                result.append(s + System.lineSeparator());
            }
            br.close();
            OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(filePath + File.separator + "OFI_JH_GJS_" + applyDate + ".TXT"), "GBK");
            writer.write(result.toString());
            writer.close();



        } catch (Exception e) {
            logger.info("索引文件生成出错了", e);
            throw new RuntimeException("贵股交索引文件生成出错");
        }

        return RepeatStatus.FINISHED;
    }

}

