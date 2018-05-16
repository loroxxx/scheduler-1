package com.jinhui.scheduler.data.core.mapper.core;

import com.jinhui.scheduler.domain.core.ExchangeInfo;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

public interface ExchangeInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ExchangeInfo record);

    int insertSelective(ExchangeInfo record);

    ExchangeInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ExchangeInfo record);

    int updateByPrimaryKey(ExchangeInfo record);

    ExchangeInfo queryExchangeInfo(@Param("exchangeCode") String exchangeCode);

    int updateExchangeLimit(@Param("exchangeCode") String exchangeCode, @Param("limit") BigDecimal limit);

    List<ExchangeInfo> queryExchanges();
}