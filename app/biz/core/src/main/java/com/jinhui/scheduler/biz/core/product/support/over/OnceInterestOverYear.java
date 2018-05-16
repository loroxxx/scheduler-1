package com.jinhui.scheduler.biz.core.product.support.over;

import com.jinhui.scheduler.biz.core.product.support.OnceInterest;

import java.util.List;

/**
 * Created by Administrator on 2017/12/18 0018.
 */
public class OnceInterestOverYear extends OnceInterest {

    public OnceInterestOverYear(int term, String startDate, String endDate) {
        super(null, term, startDate, endDate);
    }

    @Override
    public int getDayOfYear() {
        return 365;
    }
}
