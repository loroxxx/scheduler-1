package com.jinhui.scheduler.biz.core.product.support.in;

import com.jinhui.scheduler.biz.core.product.support.QuarterlyInterest;

import java.util.List;

/**
 * 不超过一年，按季付息方式
 * Created by Administrator on 2017/12/15 0015.
 */
public class QuarterlyInterestInYear extends QuarterlyInterest {


    public QuarterlyInterestInYear(List dateRule, int term, String startDate, String endDate) {
        super(dateRule, term, startDate, endDate);
    }

    @Override
    public int getDayOfYear() {
        return 360;
    }
}
