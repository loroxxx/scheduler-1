package com.jinhui.scheduler.data.core.mapper.core;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jinhui.scheduler.domain.cmbfae.model.ChnProduct;
import com.jinhui.scheduler.domain.cmbfae.template.ProfitContent;


/**
 * 渠道产品关系表
 *
 */
public interface ChnProductDao  {

	
	ProfitContent queryProfitContont(@Param("childProductNo") String childProductNo);

	List<ChnProduct> queryChnProduct(@Param("chnCode") String chnCode);
	
	int updateChnProduct(ChnProduct cp);

	int saveChnProduct(ChnProduct cp);


	int deleteByProductNo(@Param("productNo") String productNo);


	List<ChnProduct> queryAllChnProduct();


	ChnProduct selectByProductNo(@Param("productNo") String productNo);
}
