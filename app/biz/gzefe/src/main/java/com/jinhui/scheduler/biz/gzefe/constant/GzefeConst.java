package com.jinhui.scheduler.biz.gzefe.constant;

import java.math.BigDecimal;

/**
 * 常量
 *
 * create by wsc 2017-05-22 20:02
 **/
public class GzefeConst {


    public static final String CURRENCY_TYPE = "156";  //结算币种：人民币


    public static final String FUND_DIVIDEND_AMOUNT= "0";  //分红  基金账户红利资金 默认为0


    public static final String SHB_SUCC_CODE = "0000";//成功代码


    public static final String BUSINESS_CODE_FEN_HONG = "143";  //分红收益业务代码


    public static final String FUND_DEF_DIVIDEND_METHOD= "0";  //分红  默认分红方式 默认为0  0-红利转投，1-现金分红


    public static final String FUND_DIVIDEND_TYPE= "2";  //分红  分红类型 默认为2 0-普通分红，1-质押基金分红，2-货币基金收益结转，3-保本基金赔付，4-专户到期处理

    public static final String FUND_OTHER2= "1";  //分红  其他费用2


    public static final String FUND_DIVIDEND_PER_UNIT= "0";  //分红  单位基金分红金额 默认为1

    public static final String OPEN_FILE_BUSINESS_CODE="001"; //开户文件中的业务代码



/*
    //申请业务代码_确认业务代码
    public static enum BusinessCode {
        *//*ACCOUNT("001", "101"),    //开户申请*//*
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

    //是否超额 0否  1客户当日申购金额超限 2客户当日赎回金额超限 3客户当日赎回金额超过持仓金额
    public static enum IsExcess{
        ZERO,ONE,TWO,THREE
    }*/
}
