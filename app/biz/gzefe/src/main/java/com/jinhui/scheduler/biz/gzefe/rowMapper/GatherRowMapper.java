package com.jinhui.scheduler.biz.gzefe.rowMapper;

import com.jinhui.scheduler.biz.gzefe.pojo.GatherInfo;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import static com.jinhui.scheduler.biz.gzefe.utils.ConvertorUtils.*;

/**
 * Created by Administrator on 2017/11/19 0019.
 */
public class GatherRowMapper implements RowMapper<GatherInfo> {



    @Override
    public GatherInfo mapRow(ResultSet rs, int rowNum) throws SQLException {

        GatherInfo gatherInfo=new GatherInfo();

        gatherInfo.setFundCode(rs.getString("child_product_no"));//基金代码
        gatherInfo.setDistributorCode(rs.getString("chn_code"));//销售人代码
        gatherInfo.setBusinessCode(rs.getString("trans_type"));//业务代码
        gatherInfo.setAggregationOfTransactionByBusinessType(String.format("%08d",Integer.parseInt(rs.getString("success_num"))));//每种业务笔数汇总
        gatherInfo.setAggregationDate(rs.getString("gather_date"));//汇总日期
        gatherInfo.setTotalFailingVol(convertToStrMulOneHundred("%016d",rs.getString("fail_vol")));//失败份数汇总
        gatherInfo.setTotalSuccessfulVol(convertToStrMulOneHundred("%016d",rs.getString("success_vol")));//成功份数汇总
        gatherInfo.setTotalFailingAmount(convertToStrMulOneHundred("%016d",rs.getString("fail_amount")));//失败金额汇总
        gatherInfo.setTotalSuccessfulAmount(convertToStrMulOneHundred("%016d",rs.getString("success_amount")));//成功金额汇总
        gatherInfo.setTotalFailingDealingNum(String.format("%06d",Integer.parseInt(rs.getString("fail_num"))));//失败交易笔数
        gatherInfo.setTotalSuccessfulDealingNum(String.format("%06d",Integer.parseInt(rs.getString("success_num"))));//成功交易笔数

        return gatherInfo;


    }
}
