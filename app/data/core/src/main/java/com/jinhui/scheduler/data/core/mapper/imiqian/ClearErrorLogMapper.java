package com.jinhui.scheduler.data.core.mapper.imiqian;


import com.jinhui.scheduler.domain.imiqian.domain.ClearErrorLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ClearErrorLogMapper {

    int save(ClearErrorLog clearErrorLog);

    List<ClearErrorLog> findErrorLogList(@Param("batchCode") String batchCode);

    int deleteErrorLog(@Param("batchCode") int batchCode);
}