package com.jinhui.scheduler.biz.core.product;

import com.jinhui.scheduler.biz.core.product.support.over.InterestOverYearFactory;
import com.jinhui.scheduler.biz.core.product.support.InterestType;
import org.joda.time.DateTime;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 超过一年以内的产品，定期测试
 * Created by Administrator on 2017/12/15 0015.
 */
public class OverYearInterestTest {



    //超过一年，按季付息
    @Test
    public void quarterlyInterestOverYearTest() {
        int term = 730; //期限
        String startDate = "2017-02-27"; //起息日
        String endDate = "2019-02-26"; //
        BigDecimal rate = new BigDecimal("0.068");
        BigDecimal perAmount = new BigDecimal("100000");
        BigDecimal totalAmount = new BigDecimal("7000000");

        InterestOverYearFactory factory = new InterestOverYearFactory(term, startDate, endDate);
        InterestType interest = factory.createQuarterlyInterest();

        show(interest,rate,perAmount,totalAmount);


    }

    //超过一年，按半年付息
    @Test
    public void halfYearlyInterestOverYearTest() {

        int term = 821; //期限
        String startDate = "2017-11-28"; //起息日
        String endDate = "2020-02-26"; //
        BigDecimal rate = new BigDecimal("0.068");
        BigDecimal perAmount = new BigDecimal("100000");
        BigDecimal totalAmount = new BigDecimal("9500000");


        InterestOverYearFactory factory = new InterestOverYearFactory(term, startDate, endDate);
        InterestType interest =factory.createHalfYearlyInterest();

        show(interest,rate,perAmount,totalAmount);


    }

    //超过一年，按年付息
    @Test
    public void yearlyInterestOverYearTest() {
        //待测试
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

        System.out.println("单份利息分配--：");
        List<BigDecimal> bigDecimals = interest.calPerInterest(rate, perAmount);
        System.out.println(bigDecimals);


        System.out.println("总利息分配--：");
        List<BigDecimal> bigDecimals1 = interest.calTotalInterest(rate, totalAmount, perAmount);
        System.out.println(bigDecimals1);
    }

}
