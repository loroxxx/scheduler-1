package com.jinhui.scheduler.biz.gzefe.rowMapper;

import com.jinhui.scheduler.biz.core.common.HolidayService;
import com.jinhui.scheduler.biz.gzefe.constant.GzefeConst;
import com.jinhui.scheduler.biz.gzefe.pojo.IncomeInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.ResultSet;
import java.sql.SQLException;
import static com.jinhui.scheduler.biz.gzefe.utils.ConvertorUtils.*;


public class InvestorIncomeRowMapper implements RowMapper<IncomeInfo> {


    @Autowired
    private HolidayService holidayService;


    @Override
    public IncomeInfo mapRow(ResultSet resultSet, int rowNum) throws SQLException {


        String nextWorkDay = holidayService.getNextWorkDay(resultSet.getString("apply_date"));

        IncomeInfo incomeInfo = new IncomeInfo();

        incomeInfo.setAppSheetSerialNo(resultSet.getString("serial_number")); //流水号
        incomeInfo.setBasisforCalculatingDividend(convertToStrMulOneHundred("%016d",divideMet(resultSet.getString("total_postion_amount"),resultSet.getString("product_vol")))); //红利/红利再投资基数
        incomeInfo.setTransactionCfmDate(nextWorkDay); //交易确认日期(下一个批次日)
        incomeInfo.setCertificateType(resultSet.getString("id_type")); //个人证件类型及机构证件型
        incomeInfo.setCertificateNo(resultSet.getString("id_no")); //投资人证件号码
        incomeInfo.setMobileTelNo(resultSet.getString("phone")); //投资人手机号码
        incomeInfo.setCurrencyType(GzefeConst.CURRENCY_TYPE); //结算币种
        incomeInfo.setVolOfDividendforReinvestment(convertToStrMulOneHundred("%016d",divideMet(resultSet.getString("income_amount"),resultSet.getString("product_vol")))) ;//基金账户红利再投资基金份数
        incomeInfo.setDividentDate(resultSet.getString("apply_date")); //分红日/发放日
        incomeInfo.setDividendAmount(convertToStrMulOneHundred("%016d",GzefeConst.FUND_DIVIDEND_AMOUNT));//基金账户红利资金
        incomeInfo.setXRDate(resultSet.getString("apply_date")); //除权日
        incomeInfo.setConfirmedAmount(convertToStrMulOneHundred("%016d",divideMet(resultSet.getString("income_amount"),resultSet.getString("product_vol")))); //每笔交易确认金额
        incomeInfo.setFundCode (resultSet.getString("product_no"));//基金代码
        incomeInfo.setCodeOfTargetFund(resultSet.getString("child_product_no"));//转换时的目标基金代码
        incomeInfo.setRegistrationDate(resultSet.getString("apply_date"));//权益登记日期
        incomeInfo.setReturnCode(GzefeConst.SHB_SUCC_CODE);//交易处理返回代码
        incomeInfo.setTransactionAccountID(resultSet.getString("chn_id"));//投资人基金交易账号
        incomeInfo.setDistributorCode(resultSet.getString("chn_code"));//销售人代码
        incomeInfo.setBusinessCode(GzefeConst.BUSINESS_CODE_FEN_HONG);//业务代码
        incomeInfo.setTAAccountID(resultSet.getString("investor_id"));//投资人基金账号
        incomeInfo.setDepositAcct(resultSet.getString("investor_id"));//投资人在销售人处用于交易的资金帐号
        incomeInfo.setDividendPerUnit(convertToStrMulOneHundred("%016d",GzefeConst.FUND_DIVIDEND_PER_UNIT));//单位基金分红金额（含税 ）
        incomeInfo.setDefDividendMethod(GzefeConst.FUND_DEF_DIVIDEND_METHOD);//默认分红方式
        incomeInfo.setDividendType(GzefeConst.FUND_DIVIDEND_TYPE);//分红类型
        incomeInfo.setOtherFee2(convertToStrMulOneHundred("%016d",GzefeConst.FUND_OTHER2));//其他费用2

        System.out.println(incomeInfo);
        return incomeInfo;
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

