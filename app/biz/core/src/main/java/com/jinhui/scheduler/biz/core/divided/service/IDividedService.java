package com.jinhui.scheduler.biz.core.divided.service;

import com.jinhui.scheduler.domain.divided.InvestorDivided;

import java.util.List;

/**
 * Created by jinhui on 2017/5/27.
 */
public interface IDividedService {

    /**
     * 构造投资分组基本数据的实例，获取子产品ID
     * @param investorDivided
     * @return 子产品ID
     */
    String getInvestorDivided(InvestorDivided investorDivided);

    /**
     * 构造批量投资分组基本数据的实例，获取对应的批量子产品ID
     * @param investorDivideds
     * @return 包含子产品ID的投资者分组实例
     */
    List<InvestorDivided>  getInvestorDivided(List<InvestorDivided> investorDivideds);

}
