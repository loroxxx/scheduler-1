package com.jinhui.scheduler.biz.gzefe.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * Created by Administrator on 2017/11/16 0016.
 */
public class ConvertorUtils {

    static final BigDecimal ONE_HUNDRED = new BigDecimal(100);
    static final BigDecimal TEN_THOUSAND = new BigDecimal(10000);


    public static Double convertToDouble(String src){
        final DecimalFormat FORMAT = new DecimalFormat("#0.00");
        return new Double(FORMAT.format(new BigDecimal(src).divide(ONE_HUNDRED).doubleValue()));
    }

    public static String convertToStr(String format,String src){
        return String.format(format,Integer.parseInt(String.valueOf(new BigDecimal(src).longValue())));
    }

    public static String convertToStrMulOneHundred(String format,String src){
        return String.format(format,Integer.parseInt(String.valueOf(new BigDecimal(src).multiply(ONE_HUNDRED).longValue())));
    }

    public static String convertToStrMulTenThousand(String format,String src){
        return String.format(format,Integer.parseInt(String.valueOf(new BigDecimal(src).multiply(TEN_THOUSAND).longValue())));
    }
}
