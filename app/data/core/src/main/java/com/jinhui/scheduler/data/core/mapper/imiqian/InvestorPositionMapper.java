package com.jinhui.scheduler.data.core.mapper.imiqian;


import com.jinhui.scheduler.domain.imiqian.domain.InvestorPosition;
import com.jinhui.scheduler.domain.imiqian.domain.InvestorTrans;
import com.jinhui.scheduler.domain.imiqian.domain.TermPaymentDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface InvestorPositionMapper {

    int delete();

    /**
     * 还原T-1日的持仓数据
     * @return
     */
    int insertRollBack(@Param("batchCode") int batchCode);

    int save(InvestorPosition investorPosition);

    InvestorPosition selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(InvestorPosition investorPosition);

    int findCount(InvestorPosition investorPosition);

    List<InvestorPosition> findList(InvestorPosition investorPosition);

    List<InvestorPosition> findNormalListByChoice(InvestorPosition investorPosition);

    /**
     * 查询客户正常持仓列表
     * @return
     */
    List<InvestorPosition> findNormalPositionList();

    /**
     * 查询差额持仓列表
     * @return
     */
    List<InvestorPosition> findDiffPositionList();

    int batchSave(@Param("positionList") List<InvestorPosition> positionList);

    int batchUpdate(@Param("positionList") List<InvestorPosition> positionList);

    //----------------------------------------  以下为定期产品方法  ----------------------------------

    /**
     * 根据渠道代码，用户ID，产品代码查询是否已存在持仓记录
     * @param investorPosition
     * @return
     */
    InvestorPosition selectIsExistPosition(InvestorPosition investorPosition);

    /**
     * 定期产品成立时，添加定期产品的持仓记录
     * @param investorPosition
     * @return
     */
    int addRegularProductPosition(InvestorPosition investorPosition);


    /**
     * 更新定期产品的持仓记录
     * @param investorTrans
     * @return
     */
    int updateRegularProductPosition(InvestorTrans investorTrans);

    /**
     * 更新定期产品的总收益
     * @param termPaymentDetail
     * @return
     */
    int upgrateTotalIncome(TermPaymentDetail termPaymentDetail);

    /**
     * 更新定期产品的总收益同时清空持仓份额和金额为0 (定期产品最后一期)
     * @param termPaymentDetail
     * @return
     */
    int upgrateTotalIncomeAndClearPosition(TermPaymentDetail termPaymentDetail);

}