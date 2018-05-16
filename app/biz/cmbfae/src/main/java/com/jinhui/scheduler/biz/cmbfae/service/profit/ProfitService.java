package com.jinhui.scheduler.biz.cmbfae.service.profit;

import java.io.IOException;
import java.util.List;

import com.jinhui.scheduler.domain.cmbfae.model.BatchState;

public interface ProfitService {
	
	void exportFile(String filePath) throws IOException;
	

}
