package com.jinhui.scheduler.biz.zlrt.rowMapper;

import com.jinhui.scheduler.biz.zlrt.dto.RedeemLineItem;
import com.jinhui.scheduler.biz.zlrt.dto.RedemptionLineItem;
import com.jinhui.scheduler.biz.zlrt.utils.ConvertorUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * @autor wsc
 * @create 2017-05-22 18:39
 **/
public class RedemptionRowMapper implements RowMapper<RedemptionLineItem> {
    private final static Logger logger = LoggerFactory.getLogger(RedemptionRowMapper.class);

    @Override
    public RedemptionLineItem mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        RedemptionLineItem redemptionLineItem = new RedemptionLineItem();

        redemptionLineItem.setFundDate(resultSet.getString("fund_date"));
        redemptionLineItem.setRedeemType(resultSet.getString("redeem_type"));
        redemptionLineItem.setFundSeqId(resultSet.getString("fund_seq_id"));
        redemptionLineItem.setBusiType(resultSet.getString("busi_type"));
        redemptionLineItem.setUserId(resultSet.getString("zl_user_id"));
        redemptionLineItem.setUserNameText(resultSet.getString("user_name_text"));
        redemptionLineItem.setCertType(resultSet.getString("cert_type"));
        redemptionLineItem.setCertId(resultSet.getString("cert_no"));
        redemptionLineItem.setBankType(resultSet.getString("bank_type"));
        redemptionLineItem.setCardNo(resultSet.getString("card_no"));
        redemptionLineItem.setTransAmt(String.valueOf(ConvertorUtils.convertToStrMulOneHundred(resultSet.getString("trans_amt")).toBigInteger()));
        redemptionLineItem.setFundCode(resultSet.getString("fund_code"));
        redemptionLineItem.setFundType(resultSet.getString("fund_type"));
        redemptionLineItem.setOrgFundDate(resultSet.getString("org_fund_date"));
        redemptionLineItem.setOrgFundSeqId(resultSet.getString("org_fund_seqId"));
        redemptionLineItem.setRefundRemark(resultSet.getString("refund_remark"));
        redemptionLineItem.setResv(resultSet.getString("resv"));


        return redemptionLineItem;
    }


}

