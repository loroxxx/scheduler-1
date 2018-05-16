package com.jinhui.scheduler.biz.core.product.service.Impl;

import com.jinhui.scheduler.biz.core.product.constant.ProductConst;
import com.jinhui.scheduler.biz.core.product.service.TermPaymentService;
import com.jinhui.scheduler.biz.core.product.support.InterestType;
import com.jinhui.scheduler.biz.core.product.support.InterestTypeFactory;
import com.jinhui.scheduler.biz.core.product.support.in.InterestInYearFactory;
import com.jinhui.scheduler.biz.core.product.support.over.InterestOverYearFactory;
import com.jinhui.scheduler.data.core.mapper.core.TermPaymentScheduleMapper;
import com.jinhui.scheduler.domain.core.Product;
import com.jinhui.scheduler.domain.core.TermPaymentSchedule;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/12/14 0014.
 */

@Service
public class TermPaymentServiceImpl implements TermPaymentService {

    @Autowired
    private TermPaymentScheduleMapper termPaymentScheduleMapper;


    @Override
    public List<TermPaymentSchedule> createSchedule(Product product) {

        Integer term = product.getProductExpdate();//产品期限
        String rateDateStr = new DateTime(product.getRateDate()).toString("yyyy-MM-dd");//产品起息日
        String termDateStr = new DateTime(product.getTermDate()).toString("yyyy-MM-dd");//产品到期日
        String interestType = product.getInterestType();//计息方式
        BigDecimal incomeRate = product.getIncomeRate();//收益率
        BigDecimal productVol = product.getProductVol();//产品份额
        BigDecimal totalLimit = product.getTotalLimit();//产品总金额
        String code = product.getCalRateWay();//收益计算类型
        //根据收益计算类型,得到日期基数
        String daysOfYear = ProductConst.CalRateWay.getCalRateWayByCode(code).getDays();

        InterestType interest = null;
        if (daysOfYear.equals("365")) {
            //创建一年期及以上的计息工厂类
            InterestOverYearFactory overYearFactory = new InterestOverYearFactory(term, rateDateStr, termDateStr);
            interest = getInterest(interestType, overYearFactory);
        } else if (daysOfYear.equals("360")) {
            //创建一年期以内的计息工厂类
            InterestInYearFactory inYearFactory = new InterestInYearFactory(term, rateDateStr, termDateStr);
            interest = getInterest(interestType, inYearFactory);
        } else {
            throw new RuntimeException("不支持的日期基数:" + daysOfYear);
        }

        //计算每期时间间隔，付息日期，每期单份产品利息，每期总利息
        List<Integer> days = interest.getDays();
        List<Date> interestDate = interest.getInterestDate();
        List<BigDecimal> perInterest = interest.calPerInterest(incomeRate, productVol);
        List<BigDecimal> totalInterest = interest.calTotalInterest(incomeRate, totalLimit, productVol);


        //组装定期产品回款计划
        ArrayList list = new ArrayList();
        for (int i = 0; i < interestDate.size(); i++) {
            TermPaymentSchedule schedule = new TermPaymentSchedule();
            schedule.setInterestDays(days.get(i));
            schedule.setInterestDate(interestDate.get(i));
            schedule.setPerInterestAmount(perInterest.get(i));
            schedule.setInterestAmount(totalInterest.get(i));
            schedule.setProductTotalPeriods(days.size());
            schedule.setInterestPeriod(i + 1);//期数从1开始
            schedule.setTotalAmount(product.getTotalLimit());
            schedule.setProductNo(product.getProductNo());
            schedule.setPlatProductNo(product.getPlatProductNo());
            schedule.setProductName(product.getProductName());
            schedule.setInterestState("0");//未付息状态
            schedule.setCreateTime(new Date());
            schedule.setGmtModified(new Date());
            list.add(schedule);
        }

        return list;

    }

    @Override
    public void saveSchedule(List<TermPaymentSchedule> list) {

        for (TermPaymentSchedule schedule : list) {
            termPaymentScheduleMapper.insert(schedule);
        }

    }

    /**
     * //根据计息方式得到相应的计息类
     */
    private InterestType getInterest(String interestType, InterestTypeFactory factory) {

        String once = ProductConst.interestType.once.getCode();
        String quarter = ProductConst.interestType.quarter.getCode();
        String yearly = ProductConst.interestType.yearly.getCode();
        String halfYearly = ProductConst.interestType.halfYearly.getCode();
        InterestType interest = null;
        if (interestType.equals(once)) {
            interest = factory.createOnceInterest();
        }
        if (interestType.equals(quarter)) {
            interest = factory.createQuarterlyInterest();
        }
        if (interestType.equals(yearly)) {
            interest = factory.createYearlyInterest();
        }
        if (interestType.equals(halfYearly)) {
            interest = factory.createHalfYearlyInterest();
        }

        if (interest == null) {
            throw new RuntimeException("不支持的定期收益计算方式:" + interestType);
        }

        return interest;


    }


}
