package com.jinhui.scheduler.biz.core.product.support;

import java.util.Arrays;
import java.util.List;

public abstract class OnceInterest extends AbstractInterest{

    public OnceInterest(List dateRule, int term, String startDate, String endDate) {
        super(dateRule, term, startDate, endDate);
    }


    /**一次性付息的计算方式，时间间隔等于产品期限
     */
    @Override
    public List getDays() {
        return Arrays.asList(super.getTerm());
    }
}
