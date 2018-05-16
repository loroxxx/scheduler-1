package com.jinhui.scheduler.biz.gzefe.rowMapper;


import com.jinhui.scheduler.biz.gzefe.constant.GzefeConst;
import com.jinhui.scheduler.biz.gzefe.pojo.TransApplyInfo;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import static com.jinhui.scheduler.biz.gzefe.utils.ConvertorUtils.*;


/**
 * Created by Administrator on 2017/11/16 0016.
 */
public class InvestorTransRowMapper implements RowMapper<TransApplyInfo> {


    @Override
    public TransApplyInfo mapRow(ResultSet rs, int rowNum) throws SQLException {

        TransApplyInfo transApplyInfo = new TransApplyInfo();

        transApplyInfo.setAppSheetSerialNo(rs.getString("app_sheet_serial_no"));//申请单编号
        transApplyInfo.setFundCode(rs.getString("product_no"));//基金代码
        transApplyInfo.setAddress(rs.getString("address")); //通讯地址
        transApplyInfo.setInvestorName(rs.getString("name")); //投资人户名;
        transApplyInfo.setCorpName("");//工作单位名称
        transApplyInfo.setFundName(rs.getString("product_name"));//基金名称
        transApplyInfo.setCertificateNo(rs.getString("id_no")); //投资人证件号码
        transApplyInfo.setCertificateType(rs.getString("id_type")); //个人证件类型及机构证件型
        transApplyInfo.setChargeType(""); //收费类型
        transApplyInfo.setMobileTelNo(rs.getString("phone"));//投资人手机号码
        transApplyInfo.setTelNo(""); //投资人电话号码
        transApplyInfo.setHomeTelNo("");//投资人住址电话
        transApplyInfo.setPostCode("");//投资人邮政编码
        transApplyInfo.setRegistrarName(rs.getString("name")); //注册登记人名称
        transApplyInfo.setInstReprName(rs.getString("artificial")); //法人代表姓名
        transApplyInfo.setDistributorName(rs.getString("chn_name")); //销售人名称
        transApplyInfo.setTransactionDate(rs.getString("trans_date")); //交易发生日期
        transApplyInfo.setTransactionTime(rs.getString("trans_time")); //交易发生时间
        transApplyInfo.setTransactionAccountID(rs.getString("chn_id")); //投资人基金交易帐号
        transApplyInfo.setDistributorCode(rs.getString("chn_code")); //销售人代码
        transApplyInfo.setApplicationVol(convertToStrMulOneHundred("%016d",rs.getString("trans_vol"))); //申请基金份数
        transApplyInfo.setApplicationAmount(convertToStrMulOneHundred("%016d",rs.getString("trans_amount"))); //申请金额
        transApplyInfo.setSpecifyFee(convertToStrMulOneHundred("%016d","0")); //指定费用
        transApplyInfo.setBusinessCode(rs.getString("trans_type")); //业务代码
        transApplyInfo.setTAAccountID(rs.getString("investor_id")); //投资人基金帐号
        transApplyInfo.setDiscountRateOfCommission(convertToStrMulTenThousand("%05d","0"));//销售佣金折扣率
        transApplyInfo.setDepositAcct(rs.getString("investor_id")); //投资人在销售人处用于交易的资金帐号
        transApplyInfo.setRegionCode(""); //交易所在地区编号
        transApplyInfo.setCurrencyType(GzefeConst.CURRENCY_TYPE); //结算币种
        transApplyInfo.setBranchCode(rs.getString("chn_code"));//网点号码
        transApplyInfo.setOriginalAppSheetNo(rs.getString("app_sheet_serial_no")); //原申请单编号
        transApplyInfo.setOriginalSubsDate("");//原申购日期
        transApplyInfo.setIndividualOrInstitution(rs.getString("user_flag")); //个人/机构标志
        transApplyInfo.setOriginalSerialNo("");//TA的原确认流水号
        transApplyInfo.setTASerialNO(rs.getString("serial_number")); //TA确认交易流水号
        transApplyInfo.setTargetBranchCode("");//对方网点号
        transApplyInfo.setTargetRegionCode(""); //对方所在地区编号
        transApplyInfo.setTargetTransactionAccountID("");//对方销售人处投资人基金交易帐号
        transApplyInfo.setSpecification("");//摘要/说明
        transApplyInfo.setCodeOfTargetFund(rs.getString("child_product_no")); //转换时的目标基金代码
        transApplyInfo.setFrozenCause("");//冻结原因
        transApplyInfo.setNetNo("");//操作（清算）网点编号
        transApplyInfo.setOtherFee2(convertToStrMulOneHundred("%016d","1"));//其他费用2

        return transApplyInfo;
    }
}
