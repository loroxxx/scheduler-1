package com.jinhui.scheduler.data.core.mapper.core;

import com.jinhui.scheduler.domain.core.ChnInfo;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

public interface ChnInfoDao {
    int deleteByPrimaryKey(Integer id);

    int insert(ChnInfo record);

    int insertSelective(ChnInfo record);

    ChnInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ChnInfo record);

    int updateByPrimaryKey(ChnInfo record);

    List<ChnInfo> queryChnInfoList();

    int updateLimit(@Param("totalLimit") BigDecimal limit, @Param("chnCode") String chnCode);

    /**
     * 根据渠道名称查询
     */
    ChnInfo selectByName(@Param("chnName") String chnName);
}