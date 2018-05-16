package com.jinhui.scheduler.data.core.mapper.core;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jinhui.scheduler.domain.cmbfae.model.ChnProduct;

/**
 * 渠道产品历史表

 */
public interface ChnProductHistoryDao {
	
	
	public List queryChnProductHistory(@Param("productNo") String productNo,@Param("chnCode") String chnCode,@Param("preferDate") Date preferDate);
	
	public int insertChnProductHistory(ChnProduct chnProduct);
	
	public int updateChnProductHistory(ChnProduct chnProduct);
	
}
