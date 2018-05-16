package com.jinhui.scheduler.domain.cmbfae.model;

import java.util.Date;



/**
 * 
 * 
 * @author luoyuanq
 * @email 184673258@qq.com
 * @date 2017-05-27 13:43:41
 */
public class ScheduleProductDivided  {


	//金飞镖产品代码
	private String productNo;
	

	//
	private String productName;
	

	//子产品代码
	private String childProductNo;
	

	//每次插入新的用户添加进分组中，则更新该次序的统计值
	private Integer count;
	

	//
	private Date gmtCreate;
	

	//
	private Date gmtModified;
	


	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}

	public String getProductNo() {
		return productNo;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductName() {
		return productName;
	}

	public void setChildProductNo(String childProductNo) {
		this.childProductNo = childProductNo;
	}

	public String getChildProductNo() {
		return childProductNo;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getCount() {
		return count;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	public Date getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtModified(Date gmtModified) {
		this.gmtModified = gmtModified;
	}

	public Date getGmtModified() {
		return gmtModified;
	}
}
