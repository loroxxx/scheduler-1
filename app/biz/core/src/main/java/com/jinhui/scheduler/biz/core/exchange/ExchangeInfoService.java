package com.jinhui.scheduler.biz.core.exchange;


public interface ExchangeInfoService {


    /**
     * 交易所开户
     * 1.按当天批次检查交易的用户id，
     * 如果这个用户没有在该产品所属的交易所开过户，
     * 就保存该用户在该交易所的开户记录
     *
     * 如果需要账户
     */
    void openAccount();

}
