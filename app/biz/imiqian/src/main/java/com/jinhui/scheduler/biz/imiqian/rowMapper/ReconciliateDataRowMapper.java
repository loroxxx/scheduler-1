package com.jinhui.scheduler.biz.imiqian.rowMapper;

import com.jinhui.scheduler.biz.imiqian.common.AmqConst;
import com.jinhui.scheduler.biz.imiqian.utils.ConvertorUtils;
import com.jinhui.scheduler.data.core.mapper.imiqian.ChnOpenMapper;
import com.jinhui.scheduler.domain.imiqian.pojo.InvestorPositionInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * @autor wsc
 * @create 2017-05-22 18:39
 **/
public class ReconciliateDataRowMapper implements RowMapper<InvestorPositionInfo> {
    @Autowired
    ChnOpenMapper chnOpenMapper;


    @Override
    public InvestorPositionInfo mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        InvestorPositionInfo investorPositionInfo = new InvestorPositionInfo();

        //持有人可用基金份数
        investorPositionInfo.setAvailableVol(ConvertorUtils.convertToStrMulOneHundred("%016d",resultSet.getString("total_postion_vol")));
        //基金总份数（含冻结）
        investorPositionInfo.setTotalVolOfDistributorInTA(ConvertorUtils.convertToStrMulOneHundred("%016d",resultSet.getString("total_postion_vol")));
        //交易确认日期
        investorPositionInfo.setTransactionCfmDate(resultSet.getString("gather_date"));
        //基金代码
        investorPositionInfo.setFundCode(resultSet.getString("product_no"));
        //投资人基金交易账号
        investorPositionInfo.setTransactionAccountID(resultSet.getString("chn_id"));
        //销售人代码
        investorPositionInfo.setDistributorCode(resultSet.getString("chn_code"));
        //投资人基金账号
        investorPositionInfo.setTAAccountID(resultSet.getString("investor_id"));
        //网点号码
        investorPositionInfo.setBranchCode(resultSet.getString("chn_code"));
        //明细标志
        investorPositionInfo.setDetailFlag(AmqConst.FUND_DETAIL_FLAG);

        return investorPositionInfo;
    }

}

