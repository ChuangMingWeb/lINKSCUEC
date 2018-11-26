package com.model;

public class Person {
	private int id;
	private String name;
	private String password;
	private int creditStar;
	private String credit;
	private String info;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public int getCreditStar() {
		return creditStar;
	}
	public void setCreditStar(int creditStar) {
		this.creditStar = creditStar;
	}
	
	public String getCredit() {
		return credit;
	}
	public void setCredit(String credit) {
		this.credit = credit;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	
}
