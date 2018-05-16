package com.jinhui.scheduler.biz.imiqian.rowMapper;

import com.jinhui.scheduler.biz.imiqian.common.AmqConst;
import com.jinhui.scheduler.biz.imiqian.utils.ConvertorUtils;
import com.jinhui.scheduler.domain.imiqian.pojo.InvestorTransInfo;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * @autor wsc
 * @create 2017-05-22 18:39
 **/
public class InvestorTransRowMapper implements RowMapper<InvestorTransInfo> {


    @Override
    public InvestorTransInfo mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        InvestorTransInfo investorTransInfo = new InvestorTransInfo();

        //申请单编号
        investorTransInfo.setAppSheetSerialNo(resultSet.getString("app_sheet_serial_no"));
        //交易确认日期
        investorTransInfo.setTransactionCfmDate(resultSet.getString("confirm_date"));
        //结算币种
        investorTransInfo.setCurrencyType(AmqConst.CURRENCY_TYPE);
        //基金账户交易确认份数
        investorTransInfo.setConfirmedVol(ConvertorUtils.convertToStrMulOneHundred("%016d",resultSet.getString("trans_vol")));
        //基金账户交易确认金额
        investorTransInfo.setConfirmedAmount(ConvertorUtils.convertToStrMulOneHundred("%016d",resultSet.getString("trans_amount")));
        //基金代码
        investorTransInfo.setFundCode(resultSet.getString("product_no"));
        //巨额赎回处理标志
        investorTransInfo.setLargeRedemptionFlag(resultSet.getString("huge_redem_flag"));
        //交易发生日期
        investorTransInfo.setTransactionDate(resultSet.getString("trans_time").substring(0,8));
        //交易发生时间
        investorTransInfo.setTransactionTime(resultSet.getString("trans_time").substring(8,14));
        //交易处理返回代码
        investorTransInfo.setReturnCode(resultSet.getString("is_excess"));
        //投资人基金交易帐号
        investorTransInfo.setTransactionAccountID(resultSet.getString("chn_id"));
        //销售人代码
        investorTransInfo.setDistributorCode(resultSet.getString("chn_code"));
        //申请基金份数
        investorTransInfo.setApplicationVol(ConvertorUtils.convertToStrMulOneHundred("%016d",resultSet.getString("trans_vol")));
        //申请金额
        investorTransInfo.setApplicationAmount(ConvertorUtils.convertToStrMulOneHundred("%016d",resultSet.getString("trans_amount")));
        //业务代码
        investorTransInfo.setBusinessCode(AmqConst.BusinessCode.returnConfirmCode(resultSet.getString("trans_type").trim()));
        //投资人基金帐号
        investorTransInfo.setTAAccountID(resultSet.getString("investor_id"));
        //TA确认交易流水号
        investorTransInfo.setTASerialNO(resultSet.getString("serial_number"));
        //业务过程完全结束标识
        investorTransInfo.setBusinessFinishFlag(AmqConst.BUSINESS_FINISH_FLAG);
        //交易数据下传日期
        investorTransInfo.setDownLoaddate(resultSet.getString("confirm_date"));
        //手续费
        investorTransInfo.setCharge(ConvertorUtils.convertToStrMulOneHundred("%010d",resultSet.getString("trans_fix")));
        //基金单位净值
        investorTransInfo.setNAV(ConvertorUtils.convertToStrMulTenThousand("%07d",resultSet.getString("product_vol")));
        //网点号码
        investorTransInfo.setBranchCode(resultSet.getString("chn_code"));
        //收费类别
        investorTransInfo.setShareClass(resultSet.getString("charge_type"));
        //带走收益标志
        investorTransInfo.setTakeIncomeFlag(resultSet.getString("take_income_flag"));

        return investorTransInfo;
    }


}

