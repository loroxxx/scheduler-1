package com.jinhui.scheduler.biz.core.batch;

import com.jinhui.scheduler.domain.cmbfae.model.BatchState;

import java.util.Date;
import java.util.List;

public interface BatchStateService {

	/**
	 * 获取最新的批次日期和状态
	 * 
	 * @return
	 */
	BatchState getBatchState();


	
	List<BatchState> queryBatchStates(List date);

	
	/**
	 * 获取要生成文件的批次列表 正常交易日每天生成一个批次的文件，遇到节假日，要生成多个批次的文件
	 */
	List<BatchState> getWorkBatchStates(String date);
	
	
	/**
	 * 获取当天的批次日期和状态
	 * 
	 * @return
	 */
	BatchState getBatchStateCurrent();


	/**
	 * 获取下个工作日的前一天，举个栗子，batchDate是周五，返回的是周日
	 * @return
	 */
	BatchState getNextBatchStateBefore(Date batchDate);

}
