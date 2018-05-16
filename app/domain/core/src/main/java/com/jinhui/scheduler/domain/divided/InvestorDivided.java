package com.jinhui.scheduler.domain.divided;

import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

/**
 * Created by jinhui on 2017/5/27.
 */
public class InvestorDivided {

    private String investorId;
    private String investorName;
    private String productNo;
    private String childProductNo;
    private Integer index;

    public String investorId(){
        return this.investorId;
    }
    public void investorId(String investorId){
        this.investorId = investorId;
    }

    public String investorName(){
        return this.investorName;
    }
    public void investorName(String investorName){
        this.investorName = investorName;
    }

    public String productNo(){
        return this.productNo;
    }
    public void productNo(String productNo){
        this.productNo = productNo;
    }

    public void childProductNo(String childProductNo){
        this.childProductNo = childProductNo;
    }
    public String childProductNo(){
        return this.childProductNo;
    }

    public void index(Integer index){
        ProductDivided.indexCheck(index);
        this.index = index;
    }
    public Integer index(){
        return this.index;
    }

    public InvestorDivided(String investorId, String investorName, String productNo) {
        if(StringUtils.isEmpty(investorName))
            throw new IllegalArgumentException("投资人姓名不能为空");
        if(StringUtils.isEmpty(investorId))
            throw new IllegalArgumentException("投资人ID不能为空");
        if(StringUtils.isEmpty(productNo))
            throw new IllegalArgumentException("产品ID不能为空");
        this.investorId = investorId;
        this.investorName = investorName;
        this.productNo = productNo;
    }

    /*public InvestorDivided(String investorId, String investorName, String productNo,
                           String childProductNo, Integer index) {
        if(StringUtils.isEmpty(investorName))
            throw new IllegalArgumentException("投资人姓名不能为空");
        if(StringUtils.isEmpty(investorId))
            throw new IllegalArgumentException("投资人ID不能为空");
        if(StringUtils.isEmpty(productNo))
            throw new IllegalArgumentException("产品ID不能为空");
        if(StringUtils.isEmpty(childProductNo))
            throw new IllegalArgumentException("子产品ID不能为空");
        Assert.notNull(index, "index不能为空");
        ProductDivided.indexCheck(index);
        this.investorId = investorId;
        this.investorName = investorName;
        this.productNo = productNo;
        this.childProductNo = childProductNo;
        this.index = index;
    }*/

    InvestorDivided() {
        //for ORM
    }

    @Override
    public String toString() {
        return "InvestorDivided{" +
                "investorId='" + investorId + '\'' +
                ", investorName='" + investorName + '\'' +
                ", productNo='" + productNo + '\'' +
                ", childProductNo='" + childProductNo + '\'' +
                ", index=" + index +
                '}';
    }
}
