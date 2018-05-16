package com.jinhui.scheduler.biz.core.product.support;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2017/12/15 0015.
 */
public class ProductUtil {

    /**
     * 计算产品付息间隔
     * 例如：比如产品期限为1年半
     * 1年期的时间划分rule:[91,91,91,92]
     * 最后付息间隔划分为[91,91,91,92,91,91]
     * @param days 期限
     * @param rule 时间分隔规则
     * @return
     */
    public static List<Integer> createRule(int days, List<Integer> rule) {

        ArrayList<Integer> list = new ArrayList<>();
        while (days > 0) {
            for (int i = 0; i < rule.size(); i++) {
                if (days > rule.get(i)) {
                    days -= rule.get(i);
                    list.add(rule.get(i));
                } else {
                    list.add(days);
                    return list;
                }
            }
        }
        return list;
    }














    public static void main(String[] args) {
        System.out.println(createRule(90, Arrays.asList(360)));//一年以内，三个月计息间隔
        System.out.println(createRule(30, Arrays.asList(360)));//一年以内，一个月计息间隔
        System.out.println(createRule(365, Arrays.asList(91,91,91,92)));//一年以上
        System.out.println(createRule(821, Arrays.asList(91,91,91,92)));//一年以上
        System.out.println(createRule(180, Arrays.asList(90,90,90,90)));


    }

}
