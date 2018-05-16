package com.jinhui.scheduler.data.core.mapper.imiqian;


import com.jinhui.scheduler.domain.imiqian.pojo.JzTradeInfo;

import java.util.List;

/**
 * Created by Administrator on 2017/3/6.
 */
public interface JzTradeMapper {
    void save(JzTradeInfo jzTradeInfo);
    void deleteById(int id);
    int findByDate(String date);
    void updateConfirmStatus(int id);
    void updateTradeRecord(JzTradeInfo jzTradeInfo);
    List<JzTradeInfo> findList(JzTradeInfo jzTradeInfo);
    Integer findListCount(JzTradeInfo jzTradeInfo);
    int findByTradingid(String tradingid);
}
