package com.jinhui.scheduler.biz.imiqian.rowMapper;

import com.jinhui.scheduler.biz.imiqian.common.AmqConst;
import com.jinhui.scheduler.biz.imiqian.utils.ConvertorUtils;
import com.jinhui.scheduler.data.core.mapper.imiqian.BusinessGatherMapper;
import com.jinhui.scheduler.data.core.mapper.imiqian.ChnOpenMapper;
import com.jinhui.scheduler.domain.imiqian.pojo.BusinessGatherInfo;
import com.jinhui.scheduler.domain.imiqian.pojo.InvestorPositionInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * @autor wsc
 * @create 2017-05-22 18:39
 **/
public class GatherDataRowMapper implements RowMapper<BusinessGatherInfo> {

    @Override
    public BusinessGatherInfo mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        BusinessGatherInfo businessGatherInfo = new BusinessGatherInfo();

        //基金代码
        businessGatherInfo.setFundCode(resultSet.getString("product_no").trim());
        //销售人代码
        businessGatherInfo.setDistributorCode(resultSet.getString("chn_code").trim());
        //业务代码
        businessGatherInfo.setBusinessCode(resultSet.getString("trans_type").trim());
        //每种业务笔数汇总
        businessGatherInfo.setAggregationOfTransactionByBusinessType(String.format("%08d",Integer.parseInt(resultSet.getString("trans_num"))));
        //汇总日期
        businessGatherInfo.setAggregationDate(resultSet.getString("gather_date").trim());
        //失败份额汇总
        businessGatherInfo.setTotalFailingVol(ConvertorUtils.convertToStrMulOneHundred("%016d",resultSet.getString("fail_vol").trim()));
        //失败金额汇总
        businessGatherInfo.setTotalFailingAmount(ConvertorUtils.convertToStrMulOneHundred("%016d",resultSet.getString("fail_amount").trim()));
        //成功份额汇总
        businessGatherInfo.setTotalSuccessfulVol(ConvertorUtils.convertToStrMulOneHundred("%016d",resultSet.getString("success_vol").trim()));
        //成功金额汇总
        businessGatherInfo.setTotalSuccessfulAmount(ConvertorUtils.convertToStrMulOneHundred("%016d",resultSet.getString("success_amount").trim()));
        //失败交易笔数
        businessGatherInfo.setTotalFailingDealingNum(String.format("%06d",Integer.parseInt(resultSet.getString("fail_num").trim())));
        //成功交易笔数
        businessGatherInfo.setTotalSuccessfulDealingNum(String.format("%06d",Integer.parseInt(resultSet.getString("success_num").trim())));

        return businessGatherInfo;
    }

}

