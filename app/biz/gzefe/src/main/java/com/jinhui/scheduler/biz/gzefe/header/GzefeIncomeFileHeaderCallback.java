package com.jinhui.scheduler.biz.gzefe.header;

import com.jinhui.scheduler.biz.core.batch.BatchStateService;
import com.jinhui.scheduler.biz.gzefe.constant.GzefeFileConstants;


import com.jinhui.scheduler.data.core.mapper.gzefe.GzefeInvestorIncomeDao;
import com.jinhui.scheduler.domain.cmbfae.model.BatchState;
import com.jinhui.scheduler.domain.common.ChannelCode;
import com.jinhui.scheduler.domain.common.ExchangeType;
import com.jinhui.scheduler.domain.gzefe.InvestorIncome;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.explore.support.JobExplorerFactoryBean;
import org.springframework.batch.item.file.FlatFileHeaderCallback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.Date;

/**
 * Created by Administrator on 2017/11/17 0017.
 */
public class GzefeIncomeFileHeaderCallback implements FlatFileHeaderCallback {
    private final static Logger logger = LoggerFactory.getLogger(GzefeTradeApplyFileHeaderCallback.class);

    @Autowired
    JobExplorerFactoryBean jobExplorer;
    @Autowired
    private GzefeInvestorIncomeDao investorIncomeMapper;

    @Autowired
    private BatchStateService batchStateService;

    @Override
    public void writeHeader(Writer writer) throws IOException {



        BatchState batchStateCurrent = batchStateService.getBatchStateCurrent();
        Date batchDate = batchStateCurrent.getDate();

        String applyDate = new DateTime(batchDate).toString("yyyyMMdd");


        File file = ResourceUtils.getFile(GzefeFileConstants.HEADERFILEPATH_06);//收益文件头信息
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

            int count = investorIncomeMapper.findListCount(batchDate, ExchangeType.Gzefe.getExchangeNo());
            result.append(String.format("%08d",count));//记录数
        }catch(Exception e){
            logger.info("分红数据文件生成出错了",e);
        }

        writer.write(result.toString());
    }


}
