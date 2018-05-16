package com.jinhui.scheduler.biz.core.product.support;

import org.joda.time.DateTime;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 计算周期性的定期产品利息：
 * 1.单份产品预期利息=产品每份金额*产品预期收益率*每期天数/365（或=产品每份金额*产品预期收益率*每期天数/360）（四舍五入）
 * 2.产品总预期利息=产品总份数*单份产品预期利益
 * ps：定期产品利息计算与产品付息天数有关。一年期及以上的定期产品，其计息天数基数为365天；一年期以内的定期产品，其计息天数基数为360天。
 * <p>
 * <p>
 * 计算规则：
 * 1.先根据期限的天数，得到相应的付息间隔天数和日期基数（如果是一次性付息，付息间隔=期限天数）
 * 2.通过付息间隔天数计算付息日
 * 3.通过付息间隔天数计算单份产品利息
 * 4.通过单份产品利息计算总产品利息
 * Created by Administrator on 2017/12/15 0015.
 */
public abstract class AbstractInterest implements InterestType {

    private final List dateRule;//周期时间间隔规则
    private final int term; //期限
    private final String startDate; //起息日
    private final String endDate; //到期日


    public AbstractInterest(List dateRule, int term, String startDate, String endDate) {
        this.dateRule = dateRule;
        this.term = term;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * 设置计算的日期基数，产品期限等于或者超过一年的是365，不超过一年是360天
     *
     * @return
     */
    public abstract int getDayOfYear();


    @Override
    public List getDays() {
//        System.out.println("付息的时间间隔:");
        List list = ProductUtil.createRule(term, dateRule);
        return list;
    }

    @Override
    public List<Date> getInterestDate() {

        List<Integer> days = getDays();
        DateTime dt = new DateTime(startDate);
        ArrayList dateList = new ArrayList();
        //System.out.println("按季付息的付息日:");
        //1.计算方式是从T日开始计算,(T+时间间隔=付息日)
        //2.时间间隔=终止日-起始日+1，举个栗子,2017-01-01到2017-01-02算两天时间间隔
        //3.意味着第一次的时间间隔要减去一日得到第一次付息日,后续的时间间隔依次相加得到后面的付息日
        int start = -1;
        for (int i = 0; i < days.size(); i++) {
            start += days.get(i);
            DateTime dateTime = dt.plusDays(start);//日期增加start天
            dateList.add(dateTime.toDate());

        }
        return dateList;
    }

    @Override
    public List<BigDecimal> calPerInterest(BigDecimal rate, BigDecimal perAmount) {
        /**
         * 单份产品预期利息=产品每份金额*产品预期收益率*每期天数/365(结果保留小数点两位并且四舍五入)
         */
        int term = getDayOfYear();
        BigDecimal daysOverYear = new BigDecimal(term);
        List<Integer> days = getDays();
        List<BigDecimal> list = new ArrayList<>();
        for (Integer day : days) {
            BigDecimal interestAmount = perAmount.multiply(rate).multiply(new BigDecimal(day)).divide(daysOverYear, 2, RoundingMode.HALF_UP);
            list.add(interestAmount);
        }


        return list;
    }

    @Override
    public List<BigDecimal> calTotalInterest(BigDecimal rate, BigDecimal totalAmount, BigDecimal perAmount) {
        /**
         * 计算总产品收益，必须先算出单份产品利息，再乘以份数
         */
        List<BigDecimal> perInterest = calPerInterest(rate, perAmount);
        BigDecimal times = totalAmount.divide(perAmount, 0);
        List<BigDecimal> list = new ArrayList<>();
        for (BigDecimal per : perInterest) {
            list.add(per.multiply(times));
        }

        return list;
    }

    public int getTerm() {
        return term;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }
}
