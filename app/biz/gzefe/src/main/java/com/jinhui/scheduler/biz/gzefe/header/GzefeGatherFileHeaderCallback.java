package com.jinhui.scheduler.biz.gzefe.header;

import com.jinhui.scheduler.biz.core.batch.BatchStateService;
import com.jinhui.scheduler.biz.gzefe.constant.GzefeFileConstants;
import com.jinhui.scheduler.data.core.mapper.gzefe.GzefeBusinessGatherMapper;
import com.jinhui.scheduler.data.core.mapper.imiqian.InvestorIncomeMapper;
import com.jinhui.scheduler.domain.cmbfae.model.BatchState;
import com.jinhui.scheduler.domain.common.ChannelCode;
import com.jinhui.scheduler.domain.gzefe.GzefeBusinessGather;
import com.jinhui.scheduler.domain.imiqian.domain.InvestorIncome;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobInstance;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.explore.support.JobExplorerFactoryBean;
import org.springframework.batch.item.file.FlatFileHeaderCallback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/11/19 0019.
 */
public class GzefeGatherFileHeaderCallback implements FlatFileHeaderCallback {

    private final static Logger logger = LoggerFactory.getLogger(GzefeTradeApplyFileHeaderCallback.class);

    @Autowired
    JobExplorerFactoryBean jobExplorer;

    @Autowired
    private GzefeBusinessGatherMapper gzefeBusinessGatherMapper;


    @Autowired
    private BatchStateService batchStateService;


    @Override
    public void writeHeader(Writer writer) throws IOException {


        BatchState batchStateCurrent = batchStateService.getBatchStateCurrent();
        Date batchDate = batchStateCurrent.getDate();

        String applyDate = new DateTime(batchDate).toString("yyyyMMdd");

        File file = ResourceUtils.getFile(GzefeFileConstants.HEADERFILEPATH_12);//汇总文件头信息
        StringBuilder result= new StringBuilder();
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));
            String s = "";
            while((s = br.readLine())!=null){
                if("#{applyDate}".equals(s)) {
                    s = applyDate;
                }
                result.append(s + System.lineSeparator());
            }
            br.close();

            GzefeBusinessGather gzefeBusinessGather = new GzefeBusinessGather();
            gzefeBusinessGather.setGatherDate(batchDate);
            gzefeBusinessGather.setChnCode(ChannelCode.Imiqian.getCode());
            int count = gzefeBusinessGatherMapper.findListCount(gzefeBusinessGather);
            result.append(String.format("%08d",count));//记录数
        }catch(Exception e){
            logger.info("汇总数据文件生成出错了",e);
        }

        writer.write(result.toString());
    }

}
