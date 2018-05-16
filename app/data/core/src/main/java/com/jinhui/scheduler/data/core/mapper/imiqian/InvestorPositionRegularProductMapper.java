package com.jinhui.scheduler.data.core.mapper.imiqian;


import com.jinhui.scheduler.domain.imiqian.domain.InvestorPositionRegularProduct;
import com.jinhui.scheduler.domain.imiqian.domain.InvestorTrans;
import com.jinhui.scheduler.domain.imiqian.domain.TermPaymentDetail;

public interface InvestorPositionRegularProductMapper {
    /**
     * 根据渠道代码，用户ID，产品代码查询是否已存在持仓记录
     * @param investorPosition
     * @return
     */
    InvestorPositionRegularProduct selectIsExistPosition(InvestorPositionRegularProduct investorPosition);

    /**
     * 定期产品成立时，添加定期产品的持仓记录
     * @param investorPosition
     * @return
     */
    int addRegularProductPosition(InvestorPositionRegularProduct investorPosition);


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