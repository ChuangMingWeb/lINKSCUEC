package com.model;

public class RentClient {
	private int id;
	private String name;
	private String nameRent;
	private String passwordRent;
	private String startTime;
	private String overTime;
	private float price;
	private boolean hireStatus;
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
	public String getNameRent() {
		return nameRent;
	}
	public void setNameRent(String nameRent) {
		this.nameRent = nameRent;
	}
	public String getPasswordRent() {
		return passwordRent;
	}
	public void setPasswordRent(String passwordRent) {
		this.passwordRent = passwordRent;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getOverTime() {
		return overTime;
	}
	public void setOverTime(String overTime) {
		this.overTime = overTime;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public boolean isHireStatus() {
		return hireStatus;
	}
	public void setHireStatus(boolean hireStatus) {
		this.hireStatus = hireStatus;
	}
	
}