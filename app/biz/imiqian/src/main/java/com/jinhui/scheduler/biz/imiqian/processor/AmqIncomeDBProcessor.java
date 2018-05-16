package com.jinhui.scheduler.biz.imiqian.processor;

import com.jinhui.scheduler.domain.imiqian.pojo.InvestorIncomeInfo;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.BeanUtils;

/**
 * Created by wsc on 2017/5/21.
 */
public class AmqIncomeDBProcessor implements ItemProcessor<InvestorIncomeInfo,InvestorIncomeInfo> {

    @Override
    public InvestorIncomeInfo process(InvestorIncomeInfo investorIncomeInfoIn) throws Exception {
        InvestorIncomeInfo investorIncomeInfoOut = new InvestorIncomeInfo();
        BeanUtils.copyProperties(investorIncomeInfoIn,investorIncomeInfoOut);

        return investorIncomeInfoOut;
    }
}
