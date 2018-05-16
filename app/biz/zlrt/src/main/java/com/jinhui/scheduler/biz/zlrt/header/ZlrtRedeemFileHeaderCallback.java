package com.jinhui.scheduler.biz.zlrt.header;


import com.jinhui.scheduler.biz.zlrt.common.ZlrtFileConstants;
import com.jinhui.scheduler.biz.zlrt.utils.DateUtils;
import com.jinhui.scheduler.data.core.mapper.imiqian.InvestorPositionMapper;
import com.jinhui.scheduler.data.core.mapper.imiqian.PositionHistoryMapper;
import com.jinhui.scheduler.data.core.mapper.zlrt.InvestorTransMapperZlrt;
import com.jinhui.scheduler.domain.common.ChannelCode;
import com.jinhui.scheduler.domain.imiqian.domain.InvestorPosition;
import com.jinhui.scheduler.domain.zlrt.InvestorTrans;
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
import java.util.List;

/**
 * Created by wsc on 2017/05/21
 */
public class ZlrtRedeemFileHeaderCallback implements FlatFileHeaderCallback {
    private final static Logger logger = LoggerFactory.getLogger(ZlrtRedeemFileHeaderCallback.class);
    @Autowired
    JobExplorerFactoryBean jobExplorer;
    @Autowired
    InvestorTransMapperZlrt investorTransMapperZlrt;
    @Override
    public void writeHeader(Writer writer) throws IOException {
        String applyDate = "";
        String zlInstuId = "";
        String batchCode = "";
        try {
            List<JobInstance> list = jobExplorer.getObject().findJobInstancesByJobName("zlrtRedeemJob",0,1);
            if(list!= null && !list.isEmpty()) {
                List<JobExecution> jobExecutions = jobExplorer.getObject().getJobExecutions(list.get(0));
                if(jobExecutions != null && !jobExecutions.isEmpty()) {
                    JobParameters jobParameters = jobExecutions.get(0).getJobParameters();
                    applyDate = jobParameters.getString("applyDate");
                    zlInstuId = jobParameters.getString("zlInstuId");
                    batchCode = jobParameters.getString("batchCode");
                }
            }
        } catch (Exception e) {
            logger.info("获取确认日期失败",e);
        }
        File file = ResourceUtils.getFile(ZlrtFileConstants.HEADERFILEPATH_Redeem);//收益文件头信息
        StringBuilder result= new StringBuilder();
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));
            InvestorTrans investorTrans = investorTransMapperZlrt.findRedeemCountSum(batchCode,zlInstuId);
            String s = "";
            while((s = br.readLine())!=null){
                if("0#{applyDate}".equals(s)) {
                    s = "0" + applyDate + DateUtils.getCurrentTime();
                    result.append(s + System.lineSeparator());
                }else if("0#{zlInstuId}".equals(s)){
                    s = "0" + zlInstuId;
                    result.append(s + System.lineSeparator());
                }else if("0#{count}".equals(s)){
                    s = "0" + String.valueOf(investorTrans.getRedeemCount());
                    result.append(s);
                }else{
                    result.append(s + System.lineSeparator());
                }

            }
            br.close();
        }catch(Exception e){
            logger.info("文件生成出错了",e);
        }
        writer.write(result.toString());
    }
}
