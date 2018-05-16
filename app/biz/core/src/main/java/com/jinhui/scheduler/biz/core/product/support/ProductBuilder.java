package com.jinhui.scheduler.biz.core.product.support;

import com.jinhui.scheduler.biz.core.product.constant.ProductConst;
import com.jinhui.scheduler.domain.core.ProductVo;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * Created by Administrator on 2017/12/14 0014.
 */
public class ProductBuilder {

    private static final BigDecimal TEN_THOUSAND = new BigDecimal(10000);
    private static final BigDecimal RATE_UNIT = new BigDecimal(0.01);

    /**
     * 提取活期产品的信息,组装成产品信息
     */
    public static ProductVo buildCurrentProduct(Map<String, ProductField> map) {

        ProductVo product=new ProductVo();

        product.setProductName(map.get("产品名称").getValue());
        product.setProductNo(map.get("产品代码").getValue());
        product.setExchangeNo(map.get("发行机构编号").getValue());
        product.setSubClass(map.get("产品子类").getValue());
        product.setCalIncomeWay(map.get("产品收益类型").getValue());
        product.setSetupDate(str2Date(map.get("产品成立日").getValue()));

        String rate = map.get("当日收益率").getValue();
        product.setIncomeRate(new BigDecimal(rate).multiply(RATE_UNIT));//收益率要乘以百分比

        product.setIpoStartDate(str2Date(map.get("推介起始日").getValue()));
        product.setTotalLimit(str2DecimalMulTenThousand(map.get("计划发行规模上线").getValue()));
        product.setSubsStartAmount(new BigDecimal(map.get("投资门槛").getValue()));
        product.setSubsRange(new BigDecimal(map.get("递增份数").getValue()));
        product.setRiskLevel(map.get("风险等级").getValue());
        product.setPercentIncomeRate(new BigDecimal(map.get("每万元收益").getValue()));
        product.setProductVol(new BigDecimal("1"));//活期产品的产品份额是1元
        product.setTotalVol(str2DecimalMulTenThousand(map.get("计划发行规模上线").getValue()));
//        product.setChnCode(map.get("渠道").getValue());
        product.setPositionLimit(str2DecimalMulTenThousand(map.get("单一客户购买上线").getValue()));
        product.setProductSubToplimit(str2DecimalMulTenThousand(map.get("单日申购上线").getValue()));

        product.setProductType(ProductConst.CURRENT_PRODUCT);//活期产品
        product.setProductState("01");//首次录入是“认购期-01”
        product.setCreateTime(new Date());
        product.setGmtModified(new Date());


        return product;


    }


    /**
     * 提取定期产品的信息,组装成产品信息
     */
    public static ProductVo buildTermProduct(Map<String, ProductField> map) {
        ProductVo product = new ProductVo();

        product.setProductName(map.get("产品名称").getValue());

        if (null != map.get("金交中心产品代码")) {
            product.setPlatProductNo(map.get("金交中心产品代码").getValue());//非必需项
        }
        product.setProductNo(map.get("产品代码").getValue());
        product.setProductIssuer(map.get("发行机构").getValue());
        product.setExchangeNo(map.get("发行机构编号").getValue());
        product.setSubClass(map.get("产品子类").getValue());
        product.setProductExpdate(Integer.valueOf(map.get("期限").getValue()));
        product.setCalIncomeWay(map.get("产品收益类型").getValue());


        if (null != map.get("日期基数")) {
            product.setCalRateWay(ProductConst.CalRateWay.getCalRateWayByDay(map.get("日期基数").getValue()).getCode());//转换
        }else{
            //如果没有日期基数，那么根据期限来判断
            Integer integer = Integer.valueOf(map.get("期限").getValue());
            if (integer<365){
                product.setCalRateWay(ProductConst.CalRateWay.zero.getCode());
            }else {
                product.setCalRateWay(ProductConst.CalRateWay.one.getCode());
            }
        }


        product.setSetupDate(str2Date(map.get("计息起始日").getValue()));
        product.setRateDate(str2Date(map.get("计息起始日").getValue()));
        product.setTermDate(str2Date(map.get("计息截止日").getValue()));

        String rate = map.get("预期收益率").getValue();
        product.setIncomeRate(new BigDecimal(rate).multiply(RATE_UNIT));//收益率要乘以百分比
        product.setIpoStartDate(str2Date(map.get("推介起始日").getValue()));
        product.setIpoEndDate(str2Date(map.get("推介截止日").getValue()));
        product.setTotalLimit(str2DecimalMulTenThousand(map.get("计划发行规模上线").getValue()));
        product.setProductVol(new BigDecimal(map.get("每份价格").getValue()));
        product.setSubsStartAmount(str2DecimalMulTenThousand(map.get("投资门槛").getValue()));
        product.setSubsRange(new BigDecimal(map.get("递增份数").getValue()));
        product.setRiskLevel(map.get("风险等级").getValue());
        product.setInterestType(map.get("收益分配方式").getValue());

        BigDecimal totalVol = product.getTotalLimit().divide(product.getProductVol());

        product.setTotalVol(totalVol);//产品总份数
        product.setProductType(ProductConst.TERM_PRODUCT);//定期产品
        product.setProductState("01");//首次录入是“认购期-01”
        product.setExpdateUnit(0);//期限单位为“0-日”
        product.setCreateTime(new Date());
        product.setGmtModified(new Date());

        return product;

    }


    private static Date str2Date(String str) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = format.parse(str);
        } catch (ParseException e) {
            throw new RuntimeException("产品日期解析错误");
        }
        return date;
    }


    public static BigDecimal str2DecimalMulTenThousand(String src) {
        return new BigDecimal(src).multiply(TEN_THOUSAND);
    }


}
