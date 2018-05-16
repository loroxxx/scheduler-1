package com.jinhui.scheduler.biz.core.product;

import com.jinhui.scheduler.biz.core.product.support.InterestType;
import com.jinhui.scheduler.biz.core.product.support.in.InterestInYearFactory;
import com.jinhui.scheduler.biz.core.product.support.over.InterestOverYearFactory;
import org.joda.time.DateTime;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 一年以内的产品，定期测试
 * Created by Administrator on 2017/12/15 0015.
 */
public class InYearInterestTest {



    //不超过一年，按季付息
    @Test
    public void quarterlyInterestOverYearTest() {
        int term = 180; //期限
        String startDate = "2017-12-22"; //起息日
        String endDate = "2018-06-19"; //
        BigDecimal rate = new BigDecimal("0.06");
        BigDecimal perAmount = new BigDecimal("100000");
        BigDecimal totalAmount = new BigDecimal("5900000");

        InterestInYearFactory factory = new InterestInYearFactory(term, startDate, endDate);
        InterestType interest = factory.createQuarterlyInterest();

        show(interest,rate,perAmount,totalAmount);


    }

    //不超过一年，按半年付息
    @Test
    public void halfYearlyInterestOverYearTest() {



    }

    private static void show( InterestType interest, BigDecimal rate,BigDecimal perAmount,BigDecimal totalAmount){
        System.out.println("计息日期间隔：");
        List days = interest.getDays();
        System.out.println(days);

        System.out.println("计息日期：");
        List<Date> interestDate = interest.getInterestDate();

        for (Date date : interestDate) {
            DateTime dt=new DateTime(date);
            System.out.println(dt.toString("yyyy-MM-dd"));
        }

        System.out.println("单份利息分配：");
        List<BigDecimal> bigDecimals = interest.calPerInterest(rate, perAmount);
        System.out.println(bigDecimals);


        System.out.println("总利息分配：");
        List<BigDecimal> bigDecimals1 = interest.calTotalInterest(rate, totalAmount, perAmount);
        System.out.println(bigDecimals1);
    }


}