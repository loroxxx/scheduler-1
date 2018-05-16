package com.jinhui.scheduler.biz.core.common;

import com.jinhui.scheduler.domain.common.ChannelCode;
import com.jinhui.scheduler.domain.common.CustomerType;

/**
 * 提供ID生成服务
 */
public interface IIdService {
    /**
     * 获取20位交易流水号
     * @param code 渠道代码
     * @return
     */
    String generateSerialNumber(ChannelCode code);

    /**
     * 生成12位投资者ID
     * @param type
     * @return
     */
    String generateInvestorID(CustomerType type);

}
