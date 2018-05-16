package com.jinhui.scheduler.biz.imiqian.rowMapper;

import com.jinhui.scheduler.biz.imiqian.common.AmqConst;
import com.jinhui.scheduler.biz.imiqian.utils.ConvertorUtils;
import com.jinhui.scheduler.data.core.mapper.imiqian.ChnOpenMapper;
import com.jinhui.scheduler.domain.imiqian.domain.ChnOpen;
import com.jinhui.scheduler.domain.imiqian.pojo.InvestorIncomeInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * @autor wsc
 * @create 2017-05-22 18:39
 **/
public class InvestorIncomeRowMapper implements RowMapper<InvestorIncomeInfo> {
    @Autowired
    ChnOpenMapper chnOpenMapper;


    @Override
    public InvestorIncomeInfo mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        InvestorIncomeInfo investorIncomeInfo = new InvestorIncomeInfo();

        //红利/红利再投资基数
        investorIncomeInfo.setBasisforCalculatingDividend(ConvertorUtils.convertToStrMulOneHundred("%016d",divideMet(resultSet.getString("total_postion_amount"),resultSet.getString("product_vol"))).trim());
        //交易确认日期
        investorIncomeInfo.setTransactionCfmDate(resultSet.getString("gather_date").trim());
        //结算币种
        investorIncomeInfo.setCurrencyType(AmqConst.CURRENCY_TYPE);
        //金账户红利再投资基金份数
        investorIncomeInfo.setVolOfDividendforReinvestment(ConvertorUtils.convertToStrMulOneHundred("%016d",divideMet(resultSet.getString("income_amount"),resultSet.getString("product_vol"))).trim());
        //分红日/发放日
        investorIncomeInfo.setDividentDate(resultSet.getString("gather_date").trim());
        //基金账户红利资金
        investorIncomeInfo.setDividendAmount(ConvertorUtils.convertToStrMulOneHundred("%016d",AmqConst.FUND_DIVIDEND_AMOUNT).trim());
        //每笔交易确认金额
        investorIncomeInfo.setConfirmedAmount(ConvertorUtils.convertToStrMulOneHundred("%016d",divideMet(resultSet.getString("income_amount"),resultSet.getString("product_vol"))).trim());
        //基金代码
        investorIncomeInfo.setFundCode(resultSet.getString("product_no").trim());
        //权益登记日期
        investorIncomeInfo.setRegistrationDate(resultSet.getString("apply_date").trim());
        //交易处理返回代码
        investorIncomeInfo.setReturnCode(AmqConst.SHB_SUCC_CODE);
        //投资人基金交易账号
        investorIncomeInfo.setTransactionAccountID(resultSet.getString("chn_id").trim());
        //销售人代码
        investorIncomeInfo.setDistributorCode(resultSet.getString("chn_code").trim());
        //业务代码
        investorIncomeInfo.setBusinessCode(AmqConst.BUSINESS_CODE_FEN_HONG);
        //投资人基金账号
        investorIncomeInfo.setTAAccountID(resultSet.getString("investor_id").trim());
        //单位基金分红金额（含税）
        investorIncomeInfo.setDividendPerUnit(ConvertorUtils.convertToStrMulOneHundred("%016d",AmqConst.FUND_DIVIDEND_PER_UNIT).trim());
        //默认分红方式
        investorIncomeInfo.setDefDividendMethod(ConvertorUtils.convertToStrMulOneHundred("%016d",AmqConst.FUND_DEF_DIVIDEND_METHOD).trim());
        //投资人在销售人处用于交易的资金账号
        investorIncomeInfo.setDepositAcct(ConvertorUtils.convertToStrMulOneHundred("%04d",AmqConst.FUND_DEPOSIT_ACCT).trim());
        //交易所在地区编号
        investorIncomeInfo.setRegionCode(ConvertorUtils.convertToStrMulOneHundred("%04d",AmqConst.FUND_REGION_CODE).trim());

        investorIncomeInfo.setCharge(ConvertorUtils.convertToStrMulOneHundred("%010d",AmqConst.FUND_REGION_CODE).trim());
        //交易数据下传日期
        investorIncomeInfo.setDownLoaddate(resultSet.getString("gather_date").trim());
        //个人/机构标志
        ChnOpen chnOpen = chnOpenMapper.findChnOpen(resultSet.getString("investor_id").trim());
        investorIncomeInfo.setIndividualOrInstitution(chnOpen.getUserFlag().trim());
        //TA确认交易流水号
        investorIncomeInfo.setTASerialNO(resultSet.getString("serial_number").trim());
        //分红单位
        investorIncomeInfo.setDrawBonusUnit(ConvertorUtils.convertToStr("%010d",AmqConst.FUND_DRAW_BONUS_UNIT));
        //分红类型
        investorIncomeInfo.setDividendType(AmqConst.FUND_DIVIDEND_TYPE);


        return investorIncomeInfo;
    }

    /**
     * 除法计算
     * @return
     */
    private static String divideMet(String param1,String param2){
        BigDecimal  numerator = new BigDecimal(param1.trim());
        BigDecimal denominator = new BigDecimal(param2.trim());
        return  numerator.divide(denominator,2, RoundingMode.DOWN).toString();
    }

    /**
     * 截取 将万分收益保留小数点后两位
     * @return
     */
    private static String intercept(String percentAmout){
        BigDecimal percent = new BigDecimal(percentAmout.trim()).setScale(2,RoundingMode.DOWN);
        return percent.toString();
    }


}

