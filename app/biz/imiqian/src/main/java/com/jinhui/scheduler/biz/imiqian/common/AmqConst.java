package com.jinhui.scheduler.biz.imiqian.common;

import java.math.BigDecimal;

/**
 * 常量
 *
 * create by wsc 2017-05-22 20:02
 **/
public class AmqConst {

    //爽活宝产品代码
    //public static final String SHB_PRODUCT_NO = "GY0012";

    //成功代码
    public static final String SHB_SUCC_CODE = "0000";

    //失败代码
    public static final String SHB_FAIL_CODE = "0010";

    public static final int ZERO_NUMBER = 0;

    public static final BigDecimal TEN_THOUSAND = new BigDecimal("10000");

    public static final String RETURN_SUCCESS = "0000";

    public static final String CURRENCY_TYPE = "156";  //人民币

    public static final String BUSINESS_FINISH_FLAG = "1";  //交易确认_业务过程完全结束标识

    public static final String BUSINESS_CODE_ACCOUNT_APPLY = "001";  //开户业务代码

    public static final String BUSINESS_CODE_PURCHASE_APPLY = "022";  //申购业务代码

    public static final String BUSINESS_CODE_REDEEM_APPLY = "024";  //赎回业务代码

    public static final String BUSINESS_CODE_REDEEM_ALL_APPLY = "088";  //全额赎回最后一天的收益业务代码

    public static final String BUSINESS_CODE_ACCOUNT_CONFIRM = "101";  //开户确认业务代码

    public static final String BUSINESS_CODE_FEN_HONG = "143";  //分红收益业务代码

    public static final String CUSTOMER_TYPE_INSTITUTION = "0";  //客户类别 机构

    public static final String CUSTOMER_TYPE_PERSONAL = "1";  //客户类别 个人

    public static final String CUSTOMER_TYPE_SELF_SUPPORT = "2";  //客户类别 自营

    public static final String FUND_DIVIDEND_AMOUNT= "0";  //分红  基金账户红利资金 默认为0

    public static final String FUND_DEF_DIVIDEND_METHOD= "0";  //分红  默认分红方式 默认为0  0-红利转投，1-现金分红

    public static final String FUND_DRAW_BONUS_UNIT= "1";  //分红  分红单位 默认为1

    public static final String FUND_DEPOSIT_ACCT= "0";  //分红  投资人在销售人处用于交易的资金账号 默认为0

    public static final String FUND_REGION_CODE= "0";  //分红  交易所在地区编号 默认为0

    public static final String FUND_DETAIL_FLAG= "0";  //基金对账数据  明细标志 默认为0

    public static final String FUND_DIVIDEND_PER_UNIT= "0";  //分红  单位基金分红金额 默认为1

    public static final String FUND_DIVIDEND_TYPE= "2";  //分红  分红类型 默认为2 0-普通分红，1-质押基金分红，2-货币基金收益结转，3-保本基金赔付，4-专户到期处理


    //申请业务代码_确认业务代码
    public static enum BusinessCode {
        /*ACCOUNT("001", "101"),    //开户申请*/
        PURCHASE("022", "122"),     //申购
        REDEEM("024", "124");   //赎回

        private String applyCode;
        private String confirmCode;

        BusinessCode(String applyCode, String confirmCode) {
            this.applyCode = applyCode;
            this.confirmCode = confirmCode;
        }

        public static String returnConfirmCode(String code) {
            for (BusinessCode busCode : BusinessCode.values()) {
                if (busCode.getApplyCode().equals(code)) {
                    return busCode.getConfirmCode();
                }
            }
            return null;
        }

        public String getApplyCode() {
            return applyCode;
        }

        public String getConfirmCode() {
            return confirmCode;
        }

    }

    public static enum IdTypeCode {
        SHENFENZHENG("0", "00"),     //身份证
        HUZHAO("1", "01"),           //护照
        JUNGUANZHENG("2", "02"),     //军官证
        GANGAOTONGXINGZHENG("3", "04"),     //港澳通行证
        ORGANIZATION("4", "10"),     //组织机构代码证
        YINGYEZHIZHAO("5", "11"),     //营业执照
        XINYONGDAIMA("6", "19"),     //统一社会信用代码
        OTHER("7", "18"),     //其他
        ;

        private String code;
        private String exchangeCode;

        IdTypeCode(String code, String exchangeCode) {
            this.code = code;
            this.exchangeCode = exchangeCode;
        }

        public String getCode() {
            return code;
        }

        public String getExchangeCode() {
            return exchangeCode;
        }

        public static String getExchCode(String code) {
            for (IdTypeCode chn : IdTypeCode.values()) {
                if (chn.getCode().equals(code)) {
                    return chn.getExchangeCode();
                }
            }
            return null;
        }
    }



    //是否超额 0否  1客户当日申购金额超限 2客户当日赎回金额超限 3客户当日赎回金额超过持仓金额
    public static enum IsExcess{
        ZERO,ONE,TWO,THREE
    }
}
