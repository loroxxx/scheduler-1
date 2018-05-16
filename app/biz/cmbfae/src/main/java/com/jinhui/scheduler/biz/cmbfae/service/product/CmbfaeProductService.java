package com.jinhui.scheduler.biz.cmbfae.service.product;


import com.jinhui.scheduler.domain.cmbfae.model.Product;

import java.util.List;

public interface CmbfaeProductService {


	/**
	 * 导入产品信息
	 */

	public List<Product> importProductInfo() throws Exception;
	

}
