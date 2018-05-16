package com.jinhui.scheduler.biz.core.product.support;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/12/15 0015.
 */
public interface InterestType {


    /**
     * 获取计息的日期间隔
     */
    List getDays();

    /**
     * 获取付息日期
     */
    List<Date> getInterestDate();


    /**
     * 计算单份产品预期利息
     */
    List<BigDecimal> calPerInterest(BigDecimal rate, BigDecimal perAmount);


    /**
     * 计算总产品预期利息
     */
    List<BigDecimal> calTotalInterest(BigDecimal rate, BigDecimal totalAmount, BigDecimal perAmount);


}
