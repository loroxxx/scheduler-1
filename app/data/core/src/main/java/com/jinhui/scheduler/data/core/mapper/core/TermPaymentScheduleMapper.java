package com.jinhui.scheduler.data.core.mapper.core;

import com.jinhui.scheduler.domain.core.TermPaymentSchedule;
import com.jinhui.scheduler.domain.core.TermPaymentScheduleVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TermPaymentScheduleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TermPaymentSchedule record);

    int insertSelective(TermPaymentSchedule record);

    TermPaymentSchedule selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TermPaymentSchedule record);

    int updateByPrimaryKey(TermPaymentSchedule record);


    int deleteByProductNo(@Param("productNo") String  productNo);

    /**
     * 根据付息日期，产品代码查询
     * @return
     */
    List<TermPaymentScheduleVo> selectByParam(@Param("startDate") String startDate, @Param("endDate") String endDate, @Param("productNo") String productNo);


}