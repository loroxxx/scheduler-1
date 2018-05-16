package com.jinhui.scheduler.data.core.mapper.imiqian;

import com.jinhui.scheduler.domain.imiqian.domain.Holiday;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HolidayMapper {

    /**
     * 查询两个日期之间包含的日期
     * @param beginDate
     * @param endDate
     * @return
     */
    List<Holiday> findHolidayList(@Param("beginDate") String beginDate, @Param("endDate") String endDate);
}