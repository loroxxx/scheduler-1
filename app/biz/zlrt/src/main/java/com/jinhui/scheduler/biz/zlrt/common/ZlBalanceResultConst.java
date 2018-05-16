package com.jinhui.scheduler.biz.zlrt.common;

/**
 * 对账结果常量
 *
 * create by wsc 2017-05-22 20:02
 **/
public class ZlBalanceResultConst {

    public static final String CODE = "ZLRT";

    //已报送
    public static final String SUMMITTED = "1";

    //未报送
    public static final String NOT_SUMMITTED = "0";

    //不一致标识
    public static enum NotEqualFlag {
        NOT_EQUAL_CHANNEL("1", "渠道端单边"),     //渠道端单边
        NOT_EQUAL_WEALTH("2", "证联端单边"),     //证联端单边
        NOT_EQUAL_DOUBLE("3", "双边");   //双边

        private String code;
        private String desc;

        NotEqualFlag(String code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public static String returnDesc(String code) {
            for (NotEqualFlag busCode : NotEqualFlag.values()) {
                if (busCode.getCode().equals(code)) {
                    return busCode.getDesc();
                }
            }
            return null;
        }

        public String getCode() {
            return code;
        }

        public String getDesc() {
            return desc;
        }
    }

    //一致
    public static final String EQUAL = "0";

    //不一致
    public static final String NOT_EQUAL = "1";

    //对比结果
    public static enum BalanceResult {
        EQUAL("0","一致"),  //一致
        NOT_EXIST_FUND_SEQID("1", "商户系统流水号不存在"),     //商户系统流水号不存在
        NOT_EQUAL_FUND_CODE("2", "产品代码不一致"),     //产品代码不一致
        NOT_EQUAL_TRANS_AMT("3", "交易金额不一致"),     //交易金额不一致
        NOT_EQUAL_TRANS_DATE("4", "交易日期不一致"),     //交易日期不一致
        NOT_EQUAL_USERNAME("5", "客户的姓名不一致"),     //客户的姓名不一致
        NOT_EQUAL_CERT_NO("6", "客户的证件号码不一致");   //客户的证件号码不一致

        private String code;
        private String desc;

        BalanceResult(String code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public static String returnDesc(String code) {
            for (BalanceResult busCode : BalanceResult.values()) {
                if (busCode.getCode().equals(code)) {
                    return busCode.getDesc();
                }
            }
            return null;
        }

        public String getCode() {
            return code;
        }

        public String getDesc() {
            return desc;
        }
    }

}
