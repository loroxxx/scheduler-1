package com.jinhui.scheduler.biz.core.batch.impl;

import com.jinhui.scheduler.biz.core.batch.BatchStateService;
import com.jinhui.scheduler.biz.core.common.HolidayService;
import com.jinhui.scheduler.data.core.mapper.core.BatchStateDao;
import com.jinhui.scheduler.domain.cmbfae.model.BatchState;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("BatchStateService")
public class BatchStateServiceImpl implements BatchStateService {

	@Autowired
	private BatchStateDao batchStateDao;

	@Autowired
	private HolidayService holidayService;


	public BatchState getBatchState() {
		return batchStateDao.queryNewState();
	}

	@Override
	public List<BatchState> queryBatchStates(List date) {
		List<BatchState> list = batchStateDao.queryBatchStateList(date);
		return list;
	}

	@Override
	public List<BatchState> getWorkBatchStates(String date) {

		// 根据当前的日期得到要处理的批次日期列表
		List<String> batchDays = holidayService.getNextWorkDayBetween(date);

		// 根据日期查询批次号
		List<BatchState> batchStates = queryBatchStates(batchDays);

		return batchStates;
	}

	@Override
	public BatchState getBatchStateCurrent() {

		return batchStateDao.queryCurrentState();
	}

	@Override
	public BatchState getNextBatchStateBefore(Date batchDate) {

		DateTime dt=new DateTime(batchDate);
		String nextWorkDay = holidayService.getNextWorkDay(dt.toString("yyyyMMdd"));

		DateTime dateTime = DateTime.parse(nextWorkDay, DateTimeFormat.forPattern("yyyyMMdd"));
		DateTime time = dateTime.minusDays(1);

		BatchState batchState = batchStateDao.queryBatchState(time.toDate());

		return batchState;
	}

}
