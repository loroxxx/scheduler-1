package com.jinhui.scheduler.biz.imiqian.service;

import com.jinhui.scheduler.domain.cmbfae.model.ChnProduct;

import java.util.Date;
import java.util.List;

public interface ChnProductService {

	/**
	 *把当前的渠道产品备份到渠道产品历史表
	 */
	public void backupChnProductHistory();
	
	
}
