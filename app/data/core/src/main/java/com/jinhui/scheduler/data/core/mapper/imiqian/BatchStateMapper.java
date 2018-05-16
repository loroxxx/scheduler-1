package com.jinhui.scheduler.data.core.mapper.imiqian;


import com.jinhui.scheduler.domain.imiqian.domain.BatchState;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BatchStateMapper {

    //查询最新的一条记录
    BatchState findNewest();

    /**
     * 查询下一批次
     * @return
     */
    BatchState findNextBatch();

    /**
     * 更新批次日期
     * @return
     */
    int upgrateBatchCode(BatchState batchState);

    /**
     * 根据日期查批次
     * @param date
     * @return
     */
    BatchState findBatchStateByDate(@Param("date") String date);

    int delete(@Param("batchCode") int batchCode);

    /**
     * 查询清算任务
     * @return
     */
    BatchState findClearTask(@Param("batchDate") String batchDate);


    BatchState findFirstBatch();
}