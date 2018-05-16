package com.jinhui.scheduler.data.core.mapper.imiqian;


import com.jinhui.scheduler.domain.zlrt.ZlBalanceAccBill;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ZlBalanceAccBillMapperDAO {

    /**
     * 删除对账单
     * @param batchCode
     */
    void deleteBalanceAccBill(@Param("batchCode") int batchCode);

    /**
     * 删除对账单历史
     * @param batchCode
     */
    void deleteBalanceAccBillHis(@Param("batchCode") int batchCode);

    /**
     * 删除对账结果
     * @param batchCode
     */
    void deleteBalanceAccResult(@Param("batchCode") int batchCode);


}