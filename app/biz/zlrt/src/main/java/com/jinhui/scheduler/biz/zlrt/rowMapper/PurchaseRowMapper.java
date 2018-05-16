package com.jinhui.scheduler.biz.zlrt.rowMapper;

import com.jinhui.scheduler.biz.zlrt.dto.PurchaseLineItem;
import com.jinhui.scheduler.biz.zlrt.dto.RedeemLineItem;
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
public class PurchaseRowMapper implements RowMapper<PurchaseLineItem> {
    private final static Logger logger = LoggerFactory.getLogger(PurchaseRowMapper.class);

    @Override
    public PurchaseLineItem mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        PurchaseLineItem purchaseLineItem = new PurchaseLineItem();

        purchaseLineItem.setFundDate(resultSet.getString("fund_date"));
        purchaseLineItem.setFundSeqId(resultSet.getString("fund_seq_id"));
        purchaseLineItem.setBusiType(resultSet.getString("busi_type"));
        purchaseLineItem.setUserId(resultSet.getString("zl_user_id"));
        purchaseLineItem.setUserNameText(resultSet.getString("user_name_text"));
        purchaseLineItem.setCertType(resultSet.getString("cert_type"));
        purchaseLineItem.setCertId(resultSet.getString("cert_no"));
        purchaseLineItem.setBankType(resultSet.getString("bank_type"));
        purchaseLineItem.setCardNo(resultSet.getString("card_no"));
        purchaseLineItem.setTransAmt(String.valueOf(ConvertorUtils.convertToStrMulOneHundred(resultSet.getString("trans_amt")).toBigInteger()));
        purchaseLineItem.setFundCode(resultSet.getString("fund_code"));
        purchaseLineItem.setFundType(resultSet.getString("fund_type"));
        purchaseLineItem.setFundUserId(resultSet.getString("fund_user_id"));
        purchaseLineItem.setResv(resultSet.getString("resv"));

        return purchaseLineItem;
    }


}

