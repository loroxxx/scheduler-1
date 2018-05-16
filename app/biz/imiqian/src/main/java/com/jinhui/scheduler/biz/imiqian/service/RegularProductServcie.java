package com.jinhui.scheduler.biz.imiqian.service;

import com.jinhui.scheduler.domain.imiqian.domain.InvestorPosition;

/**
 * 定期产品服务层
 * @autor wsc
 * @create 2017-12-18 16:03
 **/
public interface RegularProductServcie {

    /**
     * 定期产品成立
     * @return
     */
    void toSetupRegularProduct();

    /**
     * 定期产品付息日，兑付日处理
     */
    void toPayInterest();

}
