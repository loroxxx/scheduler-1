package com.jinhui.scheduler.biz.imiqian.writer;

import com.jinhui.scheduler.data.core.mapper.imiqian.InvestorTransMapper;
import com.jinhui.scheduler.domain.imiqian.domain.InvestorTrans;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * created by wsc
 *  2017-05-19 19:16
 **/
public class AmqTradeApplyDBWriter implements ItemWriter<InvestorTrans> {

    @Autowired
    InvestorTransMapper ivestornTransMapper;

    @Override
    public void write(List<? extends InvestorTrans> items) throws Exception {
        for(InvestorTrans investorTrans : items){
            ivestornTransMapper.save(investorTrans);
        }
    }
}
