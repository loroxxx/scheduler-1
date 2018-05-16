package com.jinhui.scheduler.biz.core.product.service;

import com.jinhui.scheduler.domain.core.Product;
import com.jinhui.scheduler.domain.core.TermPaymentSchedule;

import java.util.List;


/**
 * Created by Administrator on 2017/12/14 0014.
 */
public interface TermPaymentService {

    /**
     *根据定期产品信息来构造定期回款计划
     */
    List<TermPaymentSchedule>  createSchedule(Product product);


    void saveSchedule(List<TermPaymentSchedule> list);

}
