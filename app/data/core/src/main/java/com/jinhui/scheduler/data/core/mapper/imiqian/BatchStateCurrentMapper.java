package com.jinhui.scheduler.data.core.mapper.imiqian;


import com.jinhui.scheduler.domain.imiqian.domain.BatchStateCurrent;

public interface BatchStateCurrentMapper {

    int delete();

    int save(BatchStateCurrent batchStateCurrent);

    BatchStateCurrent findCurrentBatch();

}