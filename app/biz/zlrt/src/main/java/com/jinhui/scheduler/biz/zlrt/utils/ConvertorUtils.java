package com.jinhui.scheduler.biz.zlrt.utils;

import java.math.BigDecimal;

/**
 * 金额转换
 *
 * @autor wsc
 * @create 2017-05-24 8:55
 **/
public class ConvertorUtils {
    static final BigDecimal ONE_HUNDRED = new BigDecimal(100);

    public static BigDecimal convertToStrDivideOneHundred(String src){
        return new BigDecimal(src).divide(ONE_HUNDRED,BigDecimal.ROUND_UNNECESSARY,2);
    }

    public static BigDecimal convertToStrMulOneHundred(String src){
        return new BigDecimal(src).multiply(ONE_HUNDRED);
    }

}
