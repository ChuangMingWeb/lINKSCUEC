package com.model;

public class Status {
	private int id;
	private String name;
	private boolean hireStatus;
	private boolean zuchuStatus;
	private boolean chuzuStatus;
	
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
	
	public boolean isHireStatus() {
		return hireStatus;
	}
	public void setHireStatus(boolean hireStatus) {
		this.hireStatus = hireStatus;
	}
	public boolean isZuchuStatus() {
		return zuchuStatus;
	}
	public void setZuchuStatus(boolean zuchuStatus) {
		this.zuchuStatus = zuchuStatus;
	}
	public boolean isChuzuStatus() {
		return chuzuStatus;
	}
	public void setChuzuStatus(boolean chuzuStatus) {
		this.chuzuStatus = chuzuStatus;
	}
}
