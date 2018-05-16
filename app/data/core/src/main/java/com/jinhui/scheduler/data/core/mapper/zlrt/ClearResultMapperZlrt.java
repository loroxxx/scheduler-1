package com.jinhui.scheduler.data.core.mapper.zlrt;


import com.jinhui.scheduler.domain.zlrt.ClearResult;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ClearResultMapperZlrt {

    int delete(@Param("chnCode") String chnCode, @Param("batchCode") int batchCode);

    int save(ClearResult clearResult);

    List<ClearResult> findList(ClearResult clearResult);

    /**
     * 查询证联对账单是否已导入
     * @param clearResult
     * @return
     */
    int findBillImportCount(ClearResult clearResult);

    /**
     * 查询证联赎回文件是否已报送
     * @param clearResult
     * @return
     */
    int findRedeemCount(ClearResult clearResult);

    /**
     * 查询清算成功的任务个数(用于清算之后判断是否清算成功)
     * @param clearResult
     * @return
     */
    int findClearCount(ClearResult clearResult);

    /**
     * 查询证联赎回文件报送记录
     * @param clearResult
     * @return
     */
    ClearResult findRedeemRecord(ClearResult clearResult);

    /**
     * 查询监管文件是否已报送
     * @param clearResult
     * @return
     */
    int findBankCount(ClearResult clearResult);

    /**
     * 查询某个交易所一起报送的文件数
     * @param clearResult
     * @return
     */
    int findSendCount(ClearResult clearResult);

    /**
     * 查询监管申购文件的报送记录
     * @param clearResult
     * @return
     */
    ClearResult findBankPurchaseRecord(ClearResult clearResult);

    /**
     * 查询监管赎回文件的报送记录
     * @param clearResult
     * @return
     */
    ClearResult findBankRedeemRecord(ClearResult clearResult);



    /**
     * 查询证联是否已对账
     * @param clearResult
     * @return
     */
    int findBalanceResultCount(ClearResult clearResult);

    /**
     * 查询是否已通知证联
     * @param clearResult
     * @return
     */
    int findAnnounceCount(ClearResult clearResult);

}