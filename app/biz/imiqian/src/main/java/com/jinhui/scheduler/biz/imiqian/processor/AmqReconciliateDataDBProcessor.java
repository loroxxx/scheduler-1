package com.jinhui.scheduler.biz.imiqian.processor;

import com.jinhui.scheduler.domain.imiqian.pojo.InvestorPositionInfo;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.BeanUtils;

/**
 * Created by wsc on 2017/5/21.
 */
public class AmqReconciliateDataDBProcessor implements ItemProcessor<InvestorPositionInfo,InvestorPositionInfo> {

    @Override
    public InvestorPositionInfo process(InvestorPositionInfo investorPositionInfoIn) throws Exception {
        InvestorPositionInfo investorPositionInfoOut = new InvestorPositionInfo();
        BeanUtils.copyProperties(investorPositionInfoIn,investorPositionInfoOut);

        return investorPositionInfoOut;
    }
}
