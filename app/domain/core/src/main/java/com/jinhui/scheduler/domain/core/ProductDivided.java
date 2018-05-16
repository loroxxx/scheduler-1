package com.jinhui.scheduler.domain.core;

import java.util.Date;

public class ProductDivided {
    /**
     * 金飞镖产品代码
     */
    private String productNo;

    private String productName;

    /**
     * 子产品代码
     */
    private String childProductNo;

    /**
     * 每次插入新的用户添加进分组中，则更新该次序的统计值
     */
    private Integer count;

    private Date gmtCreate;

    private Date gmtModified;


    private String state;

    /**
     * 获取金飞镖产品代码
     *
     * @return product_no - 金飞镖产品代码
     */
    public String getProductNo() {
        return productNo;
    }

    /**
     * 设置金飞镖产品代码
     *
     * @param productNo 金飞镖产品代码
     */
    public void setProductNo(String productNo) {
        this.productNo = productNo == null ? null : productNo.trim();
    }

    /**
     * @return product_name
     */
    public String getProductName() {
        return productName;
    }

    /**
     * @param productName
     */
    public void setProductName(String productName) {
        this.productName = productName == null ? null : productName.trim();
    }

    /**
     * 获取子产品代码
     *
     * @return child_product_no - 子产品代码
     */
    public String getChildProductNo() {
        return childProductNo;
    }

    /**
     * 设置子产品代码
     *
     * @param childProductNo 子产品代码
     */
    public void setChildProductNo(String childProductNo) {
        this.childProductNo = childProductNo == null ? null : childProductNo.trim();
    }

    /**
     * 获取每次插入新的用户添加进分组中，则更新该次序的统计值
     *
     * @return count - 每次插入新的用户添加进分组中，则更新该次序的统计值
     */
    public Integer getCount() {
        return count;
    }

    /**
     * 设置每次插入新的用户添加进分组中，则更新该次序的统计值
     *
     * @param count 每次插入新的用户添加进分组中，则更新该次序的统计值
     */
    public void setCount(Integer count) {
        this.count = count;
    }

    /**
     * @return gmt_create
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * @param gmtCreate
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * @return gmt_modified
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * @param gmtModified
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }


    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "ProductDivided{" +
                "productNo='" + productNo + '\'' +
                ", productName='" + productName + '\'' +
                ", childProductNo='" + childProductNo + '\'' +
                ", count=" + count +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                '}';
    }
}