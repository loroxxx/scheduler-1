package com.jinhui.scheduler.data.core.mapper.zlrt;



import com.jinhui.scheduler.domain.zlrt.InvestorTrans;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface InvestorTransMapperZlrt {

    int findListCount(InvestorTrans investorTrans);


    List<InvestorTrans> findTransTempList(InvestorTrans investorTrans);


    List<InvestorTrans> findTransList(InvestorTrans investorTrans);


    /**
     * 删除交易临时表数据
     */
    int deleteTransTemp();

    /**
     * 将T日交易数据移到临时表
     * @return
     */
    int transferDataToTemp(InvestorTrans investorTrans);

    /**
     * 查询申购笔数和总金额
     * @return
     */
    InvestorTrans findPurchaseCountSum(@Param("batchCode") String batchCode, @Param("zlInstuId") String zlInstuId);

    /**
     * 查询赎回笔数和总金额
     * @return
     */
    InvestorTrans findRedeemCountSum(@Param("batchCode") String batchCode, @Param("zlInstuId") String zlInstuId);

    /**
     * 查询T日交易数据中不存在证联对账单中的数据
     * @return
     */
    List<InvestorTrans> findNotExistBalanceData();

    int updateStateToFail(Integer id);

}