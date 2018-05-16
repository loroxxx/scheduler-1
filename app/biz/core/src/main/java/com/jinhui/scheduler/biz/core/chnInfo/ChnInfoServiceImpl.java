package com.jinhui.scheduler.biz.core.chnInfo;

import com.jinhui.scheduler.data.core.mapper.core.ChnInfoDao;
import com.jinhui.scheduler.domain.core.ChnInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;


@Service("ChnInfoService")
public class ChnInfoServiceImpl implements ChnInfoService{
	
	@Autowired
	private ChnInfoDao chnInfoDao;
	
	
	@Override
	public List<ChnInfo> queryList() {
		
		return chnInfoDao.queryChnInfoList();
	}


	@Override
	public int updateChnLimit(BigDecimal limit,String chnCode) {
		
		int i = chnInfoDao.updateLimit(limit, chnCode);
		
		return i;
	}



	
	
	
	
	
}
