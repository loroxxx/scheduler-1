package com.jinhui.scheduler.domain.cmbfae.model;

import java.util.Date;



/**
 * 批处理时间表
 * 
 * @author luoyuanq
 * @email 184673258@qq.com
 * @date 2017-05-23 19:52:47
 */
public class BatchState  {

	//批次号
	private int batchCode;
	
	//批处理日期
	private Date date;
	

	//批处理状态
	private String status;
	

	public int getBatchCode() {
		return batchCode;
	}

	public void setBatchCode(int batchCode) {
		this.batchCode = batchCode;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getDate() {
		return date;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	@Override
	public String toString() {
		return "BatchState [batchCode=" + batchCode + ", date=" + date
				+ ", status=" + status + "]";
	}
	
	
	
}
