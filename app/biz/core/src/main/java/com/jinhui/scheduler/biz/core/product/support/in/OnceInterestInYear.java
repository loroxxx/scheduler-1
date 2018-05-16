package com.jinhui.scheduler.biz.core.product.support.in;

import com.jinhui.scheduler.biz.core.product.support.OnceInterest;

import java.util.List;

/**
 * Created by Administrator on 2017/12/18 0018.
 */
public class OnceInterestInYear extends OnceInterest {

    public OnceInterestInYear(List dateRule, int term, String startDate, String endDate) {
        super(dateRule, term, startDate, endDate);
    }

    @Override
    public int getDayOfYear() {
        return 360;
    }
}
