package com.jinhui.scheduler.biz.zlrt.rowMapper;

import com.jinhui.scheduler.biz.zlrt.dto.RedeemLineItem;
import com.jinhui.scheduler.biz.zlrt.utils.ConvertorUtils;
import com.jinhui.scheduler.biz.zlrt.writer.ZlrtStatementDBWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * @autor wsc
 * @create 2017-05-22 18:39
 **/
public class RedeemRowMapper implements RowMapper<RedeemLineItem> {
    private final static Logger logger = LoggerFactory.getLogger(RedeemRowMapper.class);

    @Override
    public RedeemLineItem mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        RedeemLineItem redeemLineItem = new RedeemLineItem();

        redeemLineItem.setBusinessType(resultSet.getString("business_type"));
        redeemLineItem.setSettleDate(resultSet.getString("settle_date"));
        redeemLineItem.setSerialNumber(String.format("%-32s",resultSet.getString("serial_number")));
        redeemLineItem.setSystemDate(resultSet.getString("system_date"));

        redeemLineItem.setInstuId(resultSet.getString("instu_id"));
        redeemLineItem.setFundDate(resultSet.getString("fund_date"));
        redeemLineItem.setFundTime(resultSet.getString("fund_time"));
        redeemLineItem.setFundSeqId(resultSet.getString("fund_seq_id"));
        redeemLineItem.setBusiType(resultSet.getString("busi_type"));
        redeemLineItem.setUserId(resultSet.getString("zl_user_id"));
        redeemLineItem.setUserNameText(resultSet.getString("user_name_text"));
        redeemLineItem.setCertType(resultSet.getString("cert_type"));
        redeemLineItem.setCertId(resultSet.getString("cert_no"));
        redeemLineItem.setBankType(resultSet.getString("bank_type"));
        redeemLineItem.setCardNo(resultSet.getString("card_no"));
        redeemLineItem.setFlag(resultSet.getString("flag"));
        redeemLineItem.setTransAmt(String.valueOf(ConvertorUtils.convertToStrMulOneHundred(resultSet.getString("trans_amt")).toBigInteger()));
        redeemLineItem.setLiqDate(resultSet.getString("liq_date"));
        redeemLineItem.setFundCode(resultSet.getString("fund_code"));
        redeemLineItem.setFundType(resultSet.getString("fund_type"));
        redeemLineItem.setOrgFundDate(resultSet.getString("org_fund_date"));
        redeemLineItem.setOrgFundSeqId(resultSet.getString("org_fund_seqId"));

        return redeemLineItem;
    }


}

