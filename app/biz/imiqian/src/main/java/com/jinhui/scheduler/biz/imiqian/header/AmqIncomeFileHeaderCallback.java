package com.jinhui.scheduler.biz.imiqian.header;


import com.jinhui.scheduler.biz.imiqian.common.AmqAccountFileConstants;
import com.jinhui.scheduler.data.core.mapper.imiqian.InvestorIncomeMapper;
import com.jinhui.scheduler.domain.common.ChannelCode;
import com.jinhui.scheduler.domain.imiqian.domain.InvestorIncome;
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
public class AmqIncomeFileHeaderCallback implements FlatFileHeaderCallback {
    private final static Logger logger = LoggerFactory.getLogger(AmqIncomeFileHeaderCallback.class);
    @Autowired
    JobExplorerFactoryBean jobExplorer;
    @Autowired
    private InvestorIncomeMapper investorIncomeMapper;
    @Override
    public void writeHeader(Writer writer) throws IOException {
        String applyDate = "";
        String beginDate = "";
        String endDate = "";
        String chnCode = "";
        try {
            List<JobInstance> list = jobExplorer.getObject().findJobInstancesByJobName("amqConfirmJob",0,1);
            if(list!= null && !list.isEmpty()) {
                List<JobExecution> jobExecutions = jobExplorer.getObject().getJobExecutions(list.get(0));
                if(jobExecutions != null && !jobExecutions.isEmpty()) {
                    JobParameters jobParameters = jobExecutions.get(0).getJobParameters();
                    beginDate = jobParameters.getString("beginDate");
                    endDate = jobParameters.getString("endDate");
                    applyDate = jobParameters.getString("applyDate");
                    chnCode = jobParameters.getString("chnCode");

                }
            }
        } catch (Exception e) {
            logger.info("获取确认日期失败",e);
        }
        File file = ResourceUtils.getFile(AmqAccountFileConstants.HEADERFILEPATH_06);//收益文件头信息
        StringBuilder result= new StringBuilder();
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));
            String s = "";
            while((s = br.readLine())!=null){
                if("#{applyDate}".equals(s)) {
                    s = applyDate;
                }
                if("#{chnCode}".equals(s)) {
                    s = chnCode;
                }
                result.append(s + System.lineSeparator());
            }
            br.close();
            InvestorIncome investorIncome = new InvestorIncome();
            investorIncome.setChnCode(chnCode);
            investorIncome.setBeginDate(beginDate);
            investorIncome.setEndDate(endDate);
            int count = investorIncomeMapper.findListCount(investorIncome);
            result.append(String.format("%08d",count));//记录数
        }catch(Exception e){
            logger.info("分红数据文件生成出错了",e);
        }

        writer.write(result.toString());
    }
}
