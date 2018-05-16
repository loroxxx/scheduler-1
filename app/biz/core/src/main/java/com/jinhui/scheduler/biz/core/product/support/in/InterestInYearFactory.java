package com.jinhui.scheduler.biz.core.product.support.in;

import com.jinhui.scheduler.biz.core.product.support.InterestType;
import com.jinhui.scheduler.biz.core.product.support.InterestTypeFactory;
import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.List;

/**一年以内的计息方式工厂类
 * Created by Administrator on 2017/12/15 0015.
 */
public class InterestInYearFactory implements InterestTypeFactory {

    private final List quarterlyRule = Arrays.asList(90, 90, 90, 90);
    private final List yearlyRule = Arrays.asList(360);
    private final List halfYearlyRule = Arrays.asList(180, 180);

    private int term; //期限
    private String startDate; //起息日 yyyy-MM-dd
    private String endDate; //到期日 yyyy-MM-dd


    public InterestInYearFactory(int term, String startDate, String endDate) {
        this.term = term;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public InterestType createQuarterlyInterest() {
        return new QuarterlyInterestInYear(quarterlyRule,term,startDate,endDate);
    }


    @Override
    public InterestType createYearlyInterest() {
        if (true){
            throw new RuntimeException("一年期以内的定期产品不支持按年为周期的计息方式");
        }
        return null;
    }

    @Override
    public InterestType createHalfYearlyInterest() {
        return new HalfYearlyInterestInYear(halfYearlyRule,term,startDate,endDate);
    }

    @Override
    public InterestType createOnceInterest() {
        return new OnceInterestInYear(yearlyRule,term,startDate,endDate);
    }

}
