package com.jinhui.scheduler.data.core.mapper.core;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jinhui.scheduler.domain.cmbfae.model.BatchState;



public interface BatchStateDao  {
	
	BatchState queryNewState();

	BatchState queryBatchState(@Param("batchDate") Date batchDate);
	
	int insertBatchState(BatchState state);
	
	List<BatchState> queryBatchStateList(List dates);
	
	
	BatchState queryCurrentState();
}
