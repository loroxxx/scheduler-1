package com.jinhui.scheduler.domain.core;

import java.util.Date;

/**
 * <p>Title:Holiday</p>
 * <p>Description:VO类</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 金汇金融有限公司</p>
 * @author luoyuanq
 * @version v1.0 2017-06-05
 */
public class Holiday {
		/** 日期 */	private Date date;
		/** 是否工作日:N-非工作日,Y-工作日 */	private String flag;
		/** 描述 */	private String despict;
	
	

		public Holiday() {
		super();
	}




	public Holiday(Date date, String flag, String despict) {
		this.date = date;
		this.flag = flag;
		this.despict = despict;
	}

	
	
	
	public Date getDate() {
		return date;
	}




	public void setDate(Date date) {
		this.date = date;
	}




	public String getFlag() {
		return flag;
	}




	public void setFlag(String flag) {
		this.flag = flag;
	}




	public String getDespict() {
		return despict;
	}




	public void setDespict(String despict) {
		this.despict = despict;
	}




	@Override
	public String toString() {
		return "Holiday [date=" + date + ", flag=" + flag + ", despict=" + despict + "]";
	}
}