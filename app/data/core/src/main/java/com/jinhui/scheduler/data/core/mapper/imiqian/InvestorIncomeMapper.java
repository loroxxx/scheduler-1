package com.jinhui.scheduler.data.core.mapper.imiqian;


import com.jinhui.scheduler.domain.imiqian.domain.InvestorIncome;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface InvestorIncomeMapper {

    int delete(@Param("batchCode") int batchCode);

    int save(InvestorIncome investorIncome);

    InvestorIncome selectByPrimaryKey(Integer id);

    int findListCount(InvestorIncome investorIncome);

    int updateByPrimaryKeySelective(InvestorIncome investorIncome);

    /**
     * 批量生成T日客户当前持有金额的收益
     * @return
     */
    int batchGenerateIncome(@Param("incomeList") List<InvestorIncome> incomeList);

}