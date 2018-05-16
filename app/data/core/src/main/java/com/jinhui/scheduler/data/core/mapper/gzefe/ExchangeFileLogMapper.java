package com.jinhui.scheduler.data.core.mapper.gzefe;

import com.jinhui.scheduler.domain.gzefe.ExchangeFileLog;
import org.apache.ibatis.annotations.Param;

public interface ExchangeFileLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ExchangeFileLog record);

    int insertSelective(ExchangeFileLog record);

    ExchangeFileLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ExchangeFileLog record);

    int updateByPrimaryKey(ExchangeFileLog record);

    int deleteByBatchDate(@Param("batchDate") String batchDate);


    /**
     *根据批次日期查询导出的贵股交的文件数量
     */
    int selectGzefeFileCount(@Param("batchDate") String batchDate);


}