package com.jinhui.scheduler.biz.imiqian.rowMapper;

import com.jinhui.scheduler.biz.imiqian.common.AmqConst;
import com.jinhui.scheduler.domain.imiqian.pojo.ChnOpenInfo;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * @autor wsc
 * @create 2017-05-22 18:39
 **/
public class ChnOpenRowMapper implements RowMapper<ChnOpenInfo> {


    @Override
    public ChnOpenInfo mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        ChnOpenInfo chnOpenInfo = new ChnOpenInfo();

        //申请单编号
        chnOpenInfo.setAppSheetSerialNo(resultSet.getString("app_sheet_serial_no"));
        //交易确认日期
        chnOpenInfo.setTransactionCfmDate(resultSet.getString("confirm_date"));
        //交易处理返回代码
        chnOpenInfo.setReturnCode(AmqConst.RETURN_SUCCESS);
        //投资人基金交易帐号
        chnOpenInfo.setTransactionAccountID(resultSet.getString("chn_id"));
        //销售人代码
        chnOpenInfo.setDistributorCode(resultSet.getString("chn_code"));
        //业务代码
        chnOpenInfo.setBusinessCode(AmqConst.BUSINESS_CODE_ACCOUNT_CONFIRM);
        //投资人基金帐号
        chnOpenInfo.setTAAccountID(resultSet.getString("investor_id"));
        //TA确认交易流水号
        chnOpenInfo.setTASerialNO(resultSet.getString("serial_number"));
        //交易发生日期
        chnOpenInfo.setTransactionDate(resultSet.getString("apply_date").substring(0,8));
        //交易发生时间
        chnOpenInfo.setTransactionTime(resultSet.getString("apply_date").substring(8,14));
        //网点号码
        chnOpenInfo.setBranchCode(resultSet.getString("chn_code"));
        //个人证件类型及机构证件型
        chnOpenInfo.setCertificateType(resultSet.getString("id_type"));
        //投资人证件号码
        chnOpenInfo.setCertificateNo(resultSet.getString("id_no"));
        //投资人户名
        chnOpenInfo.setInvestorName(resultSet.getString("name"));
        //个人/机构标志
        chnOpenInfo.setIndividualOrInstitution(resultSet.getString("user_flag"));


        return chnOpenInfo;
    }


}

