package com.jinhui.scheduler.data.core.mapper.imiqian;


import com.jinhui.scheduler.domain.imiqian.domain.ClearResult;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ClearResultMapper {

    int delete(@Param("batchCode") int batchCode);

    int save(ClearResult clearResult);

    List<ClearResult> findList(ClearResult clearResult);

    /**
     * 查询申请文件导入成功的任务个数(用于导入之后判断是否导入成功)
     * @param clearResult
     * @return
     */
    int findImportCount(ClearResult clearResult);

    /**
     * 查询清算成功的任务个数(用于清算之后判断是否清算成功)
     * @param clearResult
     * @return
     */
    int findClearCount(ClearResult clearResult);

    /**
     * 查询导出确认文件成功的任务个数(用于导出之后判断是否导出成功)
     * @param clearResult
     * @return
     */
    int findExportCount(ClearResult clearResult);

    /**
     * 查询回滚的任务个数(用于回滚之后判断是否回滚成功)
     * @param clearResult
     * @return
     */
    int findRollBackCount(ClearResult clearResult);



}