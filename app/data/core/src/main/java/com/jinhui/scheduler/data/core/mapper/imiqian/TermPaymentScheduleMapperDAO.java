package com.jinhui.scheduler.data.core.mapper.imiqian;


import com.jinhui.scheduler.domain.imiqian.domain.TermPaymentSchedule;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TermPaymentScheduleMapperDAO {

    /**
     * 根据产品代码查询定期产品回款计划
     * @param productNo
     * @return
     */
    List<TermPaymentSchedule> selectPlanByProductNo(@Param("productNo") String productNo);

    /**
     * 更新产品的回款计划状态为“已付息”
     * @param productNo
     * @param interestPeriod
     * @return
     */
    int updateToPayInterest(@Param("productNo") String productNo,@Param("interestPeriod") int interestPeriod);
}