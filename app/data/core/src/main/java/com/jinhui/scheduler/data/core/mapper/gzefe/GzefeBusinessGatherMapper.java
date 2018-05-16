package com.jinhui.scheduler.data.core.mapper.gzefe;

import com.jinhui.scheduler.domain.gzefe.GzefeBusinessGather;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface  GzefeBusinessGatherMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GzefeBusinessGather record);

    int insertSelective(GzefeBusinessGather record);

    GzefeBusinessGather selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GzefeBusinessGather record);

    int updateByPrimaryKey(GzefeBusinessGather record);

    int findListCount(GzefeBusinessGather record);

    List<GzefeBusinessGather> selectTransGather(@Param("batchCode") Integer batchCode,@Param("exchangeNo") String exchangeNo);


    /**
     *统计批次日的收益汇总，ps：在清算日到节假日期间，applyDate不变，改变的只有gatherDate
     */
    List<GzefeBusinessGather> selectIncomingGather(@Param("applyDate") Date applyDate,@Param("exchangeNo") String exchangeNo);

    int deleteByBatchDate(@Param("batchDate") String batchDate);

}