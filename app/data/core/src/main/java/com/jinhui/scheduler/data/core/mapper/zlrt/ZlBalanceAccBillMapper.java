package com.jinhui.scheduler.data.core.mapper.zlrt;


import com.jinhui.scheduler.domain.zlrt.ZlBalanceAccBill;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ZlBalanceAccBillMapper {

    int insertSelective(ZlBalanceAccBill record);

    ZlBalanceAccBill selectByPrimaryKey(Integer id);

    /**
     * 根据批次号查询对账单
     * @param batchCode
     * @return
     */
    List<ZlBalanceAccBill> queryListAfterImport(@Param("batchCode") Integer batchCode);

    /**
     * 根据批次号备份对账单
     * @param batchCode
     * @return
     */
    int bakBalanceBillHis(@Param("batchCode") Integer batchCode);


}