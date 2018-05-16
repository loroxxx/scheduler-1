package com.jinhui.scheduler.data.core.mapper.gzefe;

import com.jinhui.scheduler.domain.gzefe.InvestorTrans;
import org.apache.ibatis.annotations.Param;

public interface InvestorTransDao {
    int deleteByPrimaryKey(Integer id);

    int insert(InvestorTrans record);

    int insertSelective(InvestorTrans record);

    InvestorTrans selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(InvestorTrans record);

    int updateByPrimaryKey(InvestorTrans record);


    /**
     *查询这个批次下的某个交易所下的产品的交易记录数目
     */
    int queryTransCount(@Param("batchCode") int batchCode, @Param("exchangeNo") String exchangeNo);


}