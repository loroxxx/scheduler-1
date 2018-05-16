package com.jinhui.scheduler.biz.core.product.service;

import com.jinhui.scheduler.domain.core.Product;
import com.jinhui.scheduler.domain.core.ProductVo;
import com.jinhui.scheduler.domain.cmbfae.model.ChnProduct;

import java.util.Date;
import java.util.List;


/**
 * 渠道产品
 */
public interface ChnProductService {

		
	/**
	 * 初始化渠道产品信息
	 */
	public void createChnProductInfo(Product product, String chnCode);
	

	
	/**
	 * 查询渠道产品信息
	 */
	public List<ChnProduct> queryChnProductInfo(String chnCode);
	
	
	
	/**
	 * 更新渠道产品信息
	 */
	public int updateChnProduct(ChnProduct cp, Date perferDate);
	
	
	/**
	 *把当前的渠道产品备份到渠道产品历史表
	 */
	public void backupChnProductHistory(String chnCode);
	
	
}
