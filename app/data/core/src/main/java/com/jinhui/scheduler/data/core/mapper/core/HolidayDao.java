package com.jinhui.scheduler.data.core.mapper.core;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jinhui.scheduler.domain.core.Holiday;

/**
 * <p>Title:HolidayDao</p>
 * <p>Description:DAO接口类</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 金汇金融有限公司</p>
 * @author luoyuanq
 * @version v1.0 2017-06-05
 */
public interface HolidayDao  {
	
	int save(Holiday holiday);
	
	int addList(List list);
	
	Holiday queryHoliday(@Param("date") String date);
	
	int updateHoliday(Holiday holiday);
	
}