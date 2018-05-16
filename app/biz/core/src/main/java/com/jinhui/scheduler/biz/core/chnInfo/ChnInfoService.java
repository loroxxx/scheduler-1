package com.jinhui.scheduler.biz.core.chnInfo;

import com.jinhui.scheduler.domain.core.ChnInfo;

import java.math.BigDecimal;
import java.util.List;

public interface ChnInfoService {

		
	 List<ChnInfo> queryList();

	 int updateChnLimit(BigDecimal limit, String chnCode);
	
}
