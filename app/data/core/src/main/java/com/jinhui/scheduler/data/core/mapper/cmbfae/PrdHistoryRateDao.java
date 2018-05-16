package com.jinhui.scheduler.data.core.mapper.cmbfae;

import org.apache.ibatis.annotations.Param;

import com.jinhui.scheduler.domain.cmbfae.model.PrdHistoryRate;


/**
 * 产品历史利率表
 *
 */
public interface PrdHistoryRateDao {
	
	PrdHistoryRate queryNewRate(@Param("productNo") String productNo);
}
