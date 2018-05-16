package com.jinhui.scheduler.domain.divided;

import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jinhui on 2017/5/27.
 */
public class ProductDivided {

    /**
     * 每只产品购买人数上限
     */
    private final static int limitSize = 200;

    private String productNo;
    private String productName;
    private String childProductNo;
    private Integer count;

    public DividedCriteria dividedCriteria(){
        return new DividedCriteria(this);
    }

    /**
     * 在购买该产品的人数中加1
     * @return 当前购买该子产品人数序数
     */
    public Integer countAddOne(){
        if(count<limitSize) {
            count++;
            return count;
        }
        throw new RuntimeException("该子产品的购买人数已达上限");
    }

    /**
     * 在购买该子产品的人数中加n人
     * @param n 加入人数
     * @return 大小为n, 依次购买该子产品的序数
     */
    public List<Integer> countAddN(int n){
        if((count+n) > limitSize)
            throw new RuntimeException("超过该子产品的购买人数上限");
        List<Integer> array = new ArrayList<>(n);
        for(int i=0;i<n;i++){
            count++;
            array.add(count);
        }
        return array;
    }

    /**
     * 用户子产品购买序号检查
     * @param index
     */
    public static void indexCheck(int index){
        if(index<1 || index>limitSize)
            throw new IllegalArgumentException("子产品购买序号必须是1~200");
    }

    public String productNo(){
        return this.productNo;
    }
    public void productNo(String productNo){
        this.productNo = productNo;
    }

    public String productName(){
        return this.productName;
    }
    public void productName(String productName){
        this.productName = productName;
    }

    public void childProductNo(String childProductNo){
        this.childProductNo = childProductNo;
    }
    public String childProductNo(){
        return this.childProductNo;
    }

    public void count(Integer count){
        this.count = count;
    }
    public Integer count(){
        return this.count;
    }

    public void nullCheck(){
        if(StringUtils.isEmpty(productNo))
            throw new IllegalArgumentException("产品ID不能为空");
        if(StringUtils.isEmpty(productName))
            throw new IllegalArgumentException("产品名不能为空");
        if(StringUtils.isEmpty(childProductNo))
            throw new IllegalArgumentException("子产品ID不能为空");
    }

    public ProductDivided(String productNo, String productName, String childProductNo) {
        this.productNo = productNo;
        this.productName = productName;
        this.childProductNo = childProductNo;
        this.count = 0;
    }

    public ProductDivided(String productNo) {
        this.productNo = productNo;
    }

    @Deprecated
    public ProductDivided() {
        //for ORM
        this.count = 0;
    }

    public class DividedCriteria{

        private String productNo;
        //拆分人数上线
        private Integer limitSize;
        //拆分组数
        private Integer dividedNum;

        public DividedCriteria(ProductDivided divided) {
            this.productNo = divided.productNo;
            this.limitSize = divided.limitSize;
            this.dividedNum = 1;
        }

    }
}
