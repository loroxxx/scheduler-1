package com.jinhui.scheduler.data.core.mapper.cmbfae;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ScheduleProductDividedDao {

    int insertList(List list);


    /**
     * 更新子产品的状态为已确认
     */
    int updateState(@Param("childProductNo") String childProductNo);

}
