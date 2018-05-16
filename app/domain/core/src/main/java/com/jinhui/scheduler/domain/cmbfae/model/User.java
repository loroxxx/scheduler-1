package com.jinhui.scheduler.domain.cmbfae.model;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class User {

	@JSONField(ordinal = 2)
	private String name;
	
	@JSONField(ordinal = 3)
	private int age;
	
	@JSONField(ordinal = 4)
	private String location;
	
	@JSONField(format="yyyyMMdd" ,ordinal = 1)
	public Date date;
	
	
	public User(String name, int age, String location,Date date) {
		super();
		this.name = name;
		this.age = age;
		this.location = location;
		this.date=date;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
	
}
