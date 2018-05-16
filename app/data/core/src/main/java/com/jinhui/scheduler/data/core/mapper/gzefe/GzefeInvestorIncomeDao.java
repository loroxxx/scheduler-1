package com.jinhui.scheduler.data.core.mapper.gzefe;

import com.jinhui.scheduler.domain.gzefe.InvestorIncome;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

public interface GzefeInvestorIncomeDao {
    int deleteByPrimaryKey(Integer id);

    int insert(InvestorIncome record);

    int insertSelective(InvestorIncome record);

    InvestorIncome selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(InvestorIncome record);

    int updateByPrimaryKey(InvestorIncome record);


    int findListCount(@Param("applyDate") Date applyDate, @Param("exchangeNo") String exchangeNo);
}