package com.jinhui.scheduler.biz.imiqian.processor;

import com.jinhui.scheduler.domain.imiqian.pojo.InvestorTransInfo;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.BeanUtils;

/**
 * Created by wsc on 2017/5/21.
 */
public class AmqTradeConfirmDBProcessor implements ItemProcessor<InvestorTransInfo,InvestorTransInfo> {

    @Override
    public InvestorTransInfo process(InvestorTransInfo investorTransIn) throws Exception {
        InvestorTransInfo investorTransOut = new InvestorTransInfo();
        BeanUtils.copyProperties(investorTransIn,investorTransOut);

        return investorTransOut;
    }
}
