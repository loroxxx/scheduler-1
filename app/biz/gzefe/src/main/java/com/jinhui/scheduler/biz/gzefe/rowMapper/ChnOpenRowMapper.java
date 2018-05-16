package com.jinhui.scheduler.biz.gzefe.rowMapper;

import com.jinhui.scheduler.biz.gzefe.constant.GzefeConst;
import com.jinhui.scheduler.biz.gzefe.pojo.AccountApplyInfo;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;



public class ChnOpenRowMapper implements RowMapper<AccountApplyInfo> {


    @Override
    public AccountApplyInfo mapRow(ResultSet resultSet, int rowNum) throws SQLException {


        AccountApplyInfo accountApplyInfo = new AccountApplyInfo();


        accountApplyInfo.setAddress(resultSet.getString("address"));//通讯地址
        accountApplyInfo.setInstReprIDCode(resultSet.getString("inst_repr_id_no"));//法人代表身份证件代码
        accountApplyInfo.setInstReprIDType(resultSet.getString("inst_repr_id_type")); //法人代表证件类型
        accountApplyInfo.setInstReprName(resultSet.getString("inst_repr_id_name")); //法人代表姓名

        accountApplyInfo.setAppSheetSerialNo(resultSet.getString("app_sheet_serial_no"));//申请单编号
        accountApplyInfo.setCertificateType(resultSet.getString("id_type")); //个人证件类型及机构证件型
        accountApplyInfo.setCertificateNo(resultSet.getString("id_no"));// 投资人证件号码
        accountApplyInfo.setInvestorName(resultSet.getString("name")); // 投资人户名
        accountApplyInfo.setTransactionDate(resultSet.getString("apply_date"));// 交易发生日期
        accountApplyInfo.setTransactionTime(resultSet.getString("apply_time")); // 交易发生时间(时分秒)
        accountApplyInfo.setIndividualOrInstitution(resultSet.getString("user_flag"));// 个人/机构标志

        accountApplyInfo.setPostCode(resultSet.getString("zipcode"));// 投资人邮政编码
        accountApplyInfo.setTransactionAccountID(resultSet.getString("chn_id"));// 投资人基金交易帐号
        accountApplyInfo.setDistributorCode(resultSet.getString("chn_code")); //销售人代码
        accountApplyInfo.setBusinessCode(GzefeConst.OPEN_FILE_BUSINESS_CODE); //业务代码(数据库中没有)
        accountApplyInfo.setDepositAcct(""); //投资人在销售人处用于交易的资金帐号(数据库中没有)
        accountApplyInfo.setFaxNo(resultSet.getString("fax"));//投资人传真号码
        accountApplyInfo.setMobileTelNo(resultSet.getString("phone"));//投资人手机号码

        accountApplyInfo.setAcctNameOfInvestorInClearingAgency("");// 投资人收款银行账户户名(数据库中没有)
        accountApplyInfo.setAcctNoOfInvestorInClearingAgency(resultSet.getString("bank_account"));//投资人收款银行账户账号
        accountApplyInfo.setClearingAgency(resultSet.getString("open_bank"));//投资人收款银行账户开户行
        accountApplyInfo.setClientRiskRate(resultSet.getString("risk_level"));// 客户风险等级

        System.out.println(accountApplyInfo);
        return accountApplyInfo;
    }


}

