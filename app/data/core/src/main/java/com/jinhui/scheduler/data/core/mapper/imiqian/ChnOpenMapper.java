package com.jinhui.scheduler.data.core.mapper.imiqian;


import com.jinhui.scheduler.domain.imiqian.domain.ChnOpen;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 渠道开户mapper
 *
 *  wsc
 *  2017-05-19 14:33
 **/
public interface ChnOpenMapper {

    int delete(@Param("batchCode") int batchCode);

    int save(ChnOpen chnOpen);

    ChnOpen findChnOpen(@Param("investorId") String investorId);

    /**
     * 查询金汇系统用户
     * @return
     */
    ChnOpen findSysChnOpen();

    int updateByPrimaryKeySelective(ChnOpen record);

    int findListCount(ChnOpen chnOpen);

    List<ChnOpen> findList(ChnOpen chnOpen);

}