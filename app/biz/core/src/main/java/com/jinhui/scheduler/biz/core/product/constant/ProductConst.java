package com.jinhui.scheduler.biz.core.product.constant;

/**
 * Created by Administrator on 2017/12/14 0014.
 */
public class ProductConst {

    public static final String CURRENT_PRODUCT = "0";  //活期产品
    public static final String TERM_PRODUCT = "1";  //定期产品




    public enum interestType {// 定期计息方式
        quarter("1", "1-按季付息，到期一次还本;"),
        yearly("2", "2-按年付息,到期一次还本;"),
        once("3", "3-到期一次性还本付息;"),
        halfYearly("4", "4-按半年付息,到期一次性还本");

        private String code;
        private String remark;

        interestType(String code, String remark) {
            this.code = code;
            this.remark = remark;
        }

        public String getCode() {
            return code;
        }

        public String getRemark() {
            return remark;
        }
    }


    /**
     * 收益计算类型
     */
    public enum CalRateWay {

        zero("0", "360", "1年期以内的产品，年度天数基数360天"),
        one("1", "365", "1年期及以上的产品，年度天数基数365天");

        private String code;
        private String days;//日期基数
        private String remark;


        CalRateWay(String code, String days, String remark) {
            this.code = code;
            this.days = days;
            this.remark = remark;
        }


        public static CalRateWay getCalRateWayByDay(String day) {
            for (CalRateWay ins : CalRateWay.values()) {
                if (ins.getDays().equals(day)) {
                    return ins;
                }
            }
            throw new IllegalArgumentException("不支持的计息方式");
        }


        public static CalRateWay getCalRateWayByCode(String code) {
            for (CalRateWay ins : CalRateWay.values()) {
                if (ins.getCode().equals(code)) {
                    return ins;
                }
            }
            throw new IllegalArgumentException("不支持的计息方式");
        }

        public String getCode() {
            return code;
        }

        public String getDays() {
            return days;
        }

        public String getRemark() {
            return remark;
        }
    }

}
