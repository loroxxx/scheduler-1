package com.jinhui.scheduler.biz.imiqian.processor;

import com.jinhui.scheduler.domain.imiqian.pojo.BusinessGatherInfo;
import com.jinhui.scheduler.domain.imiqian.pojo.InvestorPositionInfo;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.BeanUtils;

/**
 * Created by wsc on 2017/5/21.
 */
public class AmqGatherDataDBProcessor implements ItemProcessor<BusinessGatherInfo,BusinessGatherInfo> {

    @Override
    public BusinessGatherInfo process(BusinessGatherInfo businessGatherInfoIn) throws Exception {
        BusinessGatherInfo businessGatherInfoOut = new BusinessGatherInfo();
        BeanUtils.copyProperties(businessGatherInfoIn,businessGatherInfoOut);

        return businessGatherInfoOut;
    }
}
