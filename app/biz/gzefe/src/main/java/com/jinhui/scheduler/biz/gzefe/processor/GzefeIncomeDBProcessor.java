package com.jinhui.scheduler.biz.gzefe.processor;

import com.jinhui.scheduler.biz.gzefe.pojo.IncomeInfo;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.BeanUtils;

/**
 * Created by Administrator on 2017/11/17 0017.
 */
public class GzefeIncomeDBProcessor implements ItemProcessor<IncomeInfo, IncomeInfo> {

    @Override
    public IncomeInfo process(IncomeInfo incomeInfoIn) throws Exception {

        IncomeInfo incomeInfoOut = new IncomeInfo();

        BeanUtils.copyProperties(incomeInfoIn, incomeInfoOut);

        return incomeInfoOut;
    }
}
