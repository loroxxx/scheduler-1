package com.jinhui.scheduler.biz.core.util;

import java.math.BigDecimal;

/**
 * Created by Administrator on 2018/2/12 0012.
 */
public class ProductUtils {


    /**
     * 计算万元收益= ((1+T日收益率）^(1/365)-1)*10000 公式
     */
    public static BigDecimal calProfit(BigDecimal profit) {
        double s = (double) 1 / 365;
        double pow = Math.pow(profit.doubleValue() + 1, s);
        BigDecimal dd = new BigDecimal((pow - 1) * 10000);
        // 截断小数点4位
        BigDecimal setScale = dd.setScale(4, BigDecimal.ROUND_DOWN);

        return setScale;

    }

    public static void main(String[] args) {
        System.out.println(calProfit(new BigDecimal("0.05")));
    }
}
