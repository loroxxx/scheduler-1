package com.jinhui.scheduler.biz.imiqian.service.impl;

import com.jinhui.scheduler.biz.imiqian.service.ChnProductService;
import com.jinhui.scheduler.data.core.mapper.core.ChnProductDao;
import com.jinhui.scheduler.data.core.mapper.core.ChnProductHistoryDao;
import com.jinhui.scheduler.domain.cmbfae.model.ChnProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("chnProductService")
public class ChnProductServiceImpl implements ChnProductService {
	@Autowired
	private ChnProductDao chnProductDao;
	
	
	@Autowired
	private ChnProductHistoryDao chnProductHistoryDao;

	@Override
	public void backupChnProductHistory() {
		
		List<ChnProduct> list = chnProductDao.queryAllChnProduct();
		for (ChnProduct chnProduct : list) {
			//历史表中没有记录则插入，有则更新
			List historyList = chnProductHistoryDao.queryChnProductHistory(chnProduct.getProductNo(),chnProduct.getChnCode(),chnProduct.getPreferDate());
			if(historyList.size()==0){
				chnProductHistoryDao.insertChnProductHistory(chnProduct);
			}else{
				chnProductHistoryDao.updateChnProductHistory(chnProduct);
			}
		}
		
	}
	
	
	

}
