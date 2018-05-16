package com.jinhui.scheduler.biz.core.product.support.over;

import com.jinhui.scheduler.biz.core.product.support.InterestType;
import com.jinhui.scheduler.biz.core.product.support.InterestTypeFactory;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2017/12/15 0015.
 */
public class InterestOverYearFactory implements InterestTypeFactory {


    private final List quarterlyRule = Arrays.asList(91, 91, 91, 92);
    private final List yearlyRule = Arrays.asList(365);
    private final List halfYearlyRule = Arrays.asList(182, 183);

    private int term; //期限
    private String startDate; //起息日
    private String endDate; //


    public InterestOverYearFactory(int term, String startDate, String endDate) {
        this.term = term;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public InterestType createQuarterlyInterest() {
        return new QuarterlyInterestOverYear(quarterlyRule, term, startDate, endDate);
    }

    @Override
    public InterestType createYearlyInterest() {
        return new YearlyInterestOverYear(yearlyRule, term, startDate, endDate);
    }

    @Override
    public InterestType createHalfYearlyInterest() {
        return new HalfYearlyInterestOverYear(halfYearlyRule, term, startDate, endDate);
    }

    @Override
    public InterestType createOnceInterest() {
        return new OnceInterestOverYear(term, startDate, endDate);
    }

}
