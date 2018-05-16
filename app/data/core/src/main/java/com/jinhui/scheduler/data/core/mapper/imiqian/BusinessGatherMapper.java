package com.jinhui.scheduler.data.core.mapper.imiqian;


import com.jinhui.scheduler.domain.imiqian.domain.BusinessGather;
import com.jinhui.scheduler.domain.imiqian.domain.InvestorPosition;
import org.apache.ibatis.annotations.Param;

public interface BusinessGatherMapper {

    int delete(@Param("batchCode") int batchCode);

    int save(BusinessGather businessGather);

    BusinessGather selectByPrimaryKey(Integer id);

    int findCount(BusinessGather businessGather);

}