package com.jinhui.scheduler.data.core.mapper.zlrt;


import com.jinhui.scheduler.domain.zlrt.BatchStateCurrent;

public interface BatchStateCurrentMapperZlrt {

    int delete();

    int save(BatchStateCurrent batchStateCurrent);

    BatchStateCurrent findCurrentBatch();

}