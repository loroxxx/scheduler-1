package com.jinhui.scheduler.data.core.mapper.cmbfae;

import java.util.List;

import org.apache.ibatis.annotations.Param;


/**
 * 收益表
 *
 */
public interface InvestorIncomeDao {

	/**
	 * 按交易所产品，查询出持仓的子产品代码
	 */
	List<String> queryChildProductNo(@Param("exchangeNo") String exchangeNo);



}
