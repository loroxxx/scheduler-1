package com.jinhui.scheduler.data.core.mapper.imiqian;


import com.jinhui.scheduler.domain.imiqian.domain.PositionHistory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PositionHistoryMapper {

    int delete(@Param("batchCode") int batchCode);

    int save(PositionHistory positionHistory);

    /**
     * //批量备份T-1日的历史持仓数据
     * @return
     */
    int bakcupToPosHis(@Param("posHisList") List<PositionHistory> posHisList);

    /**
     * 查询历史持仓
     * @param positionHistory
     * @return
     */
    PositionHistory findPosHistory(PositionHistory positionHistory);

    int findHisCount(@Param("batchCode") int batchCode);

}