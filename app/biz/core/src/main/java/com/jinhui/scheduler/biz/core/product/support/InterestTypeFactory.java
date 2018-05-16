package com.jinhui.scheduler.biz.core.product.support;


/** 计息方式抽象工厂
 * Created by Administrator on 2017/12/15 0015.
 */
public interface InterestTypeFactory {

    /**
     * 按季付息
     * @return
     */
    InterestType createQuarterlyInterest();

    /**
     * 按年付息
     * @return
     */
    InterestType createYearlyInterest();

    /**
     * 按半年付息
     * @return
     */
    InterestType createHalfYearlyInterest();

    /**
     * 一次性还本付息（仅计算利息）
     */
    InterestType createOnceInterest();
}
