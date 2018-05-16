package com.jinhui.scheduler.data.core.mapper.cmbfae;

import java.util.List;
import com.jinhui.scheduler.domain.cmbfae.template.PositionSuContent;
import org.apache.ibatis.annotations.Param;
import com.jinhui.scheduler.domain.cmbfae.template.PositionDetailContent;


/**
 * 投资人持仓表
 *
 */
public interface InvestorPositionDao  {
	

	/**
	 * 按交易所的产品，分页查询最新的持仓记录
	 */
	List<PositionDetailContent> queryPositionDetailInfo(@Param("exchangeNo") String exchangeNo);


	/**
	 * 按交易所的产品，统计最新的持仓记录的条数
	 */
	List<PositionSuContent> queryPositionCount(@Param("exchangeNo") String exchangeNo);


	/**
	 *统计最新的持仓记录的总条数
	 */
	int queryTotalCount(@Param("exchangeNo") String exchangeNo);

}
