package com.jinhui.scheduler.biz.core.product;

import com.jinhui.scheduler.biz.core.product.support.InterestType;
import com.jinhui.scheduler.biz.core.product.support.in.InterestInYearFactory;
import com.jinhui.scheduler.biz.core.product.support.over.InterestOverYearFactory;
import org.joda.time.DateTime;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**一次性还本付息定期产品，测试
 * Created by Administrator on 2017/12/18 0018.
 */
public class OnceInterestTest {


    //不超过一年
    @Test
    public void onceInterestInYearTest() {
        int term = 180; //期限
        String startDate = "2016-09-29"; //起息日
        String endDate = "2017-03-27"; //
        BigDecimal rate = new BigDecimal("0.064");
        BigDecimal perAmount = new BigDecimal("100000");
        BigDecimal totalAmount = new BigDecimal("7900000");

        InterestInYearFactory factory = new InterestInYearFactory(term, startDate, endDate);
        InterestType interest = factory.createOnceInterest();

        show(interest,rate,perAmount,totalAmount);

    }


    //不超过一年
    @Test
    public void onceInterestInYearTest2() {
        int term = 50; //期限
        String startDate = "2017-11-01"; //起息日
        String endDate = "2017-12-20"; //
        BigDecimal rate = new BigDecimal("0.067");
        BigDecimal perAmount = new BigDecimal("1000000");
        BigDecimal totalAmount = new BigDecimal("31000000");

        InterestInYearFactory factory = new InterestInYearFactory(term, startDate, endDate);
        InterestType interest = factory.createOnceInterest();

        show(interest,rate,perAmount,totalAmount);

    }


    //超过一年
    @Test
    public void onceInterestOverYearTest() {
        int term = 730; //期限
        String startDate = "2016-09-29"; //起息日
        String endDate = "2017-03-27"; //
        BigDecimal rate = new BigDecimal("0.064");
        BigDecimal perAmount = new BigDecimal("100000");
        BigDecimal totalAmount = new BigDecimal("7900000");

        InterestOverYearFactory factory = new InterestOverYearFactory(term, startDate, endDate);
        InterestType interest = factory.createOnceInterest();

        show(interest,rate,perAmount,totalAmount);


    }



    private static void show( InterestType interest, BigDecimal rate,BigDecimal perAmount,BigDecimal totalAmount){

        System.out.println("一次性：");
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
