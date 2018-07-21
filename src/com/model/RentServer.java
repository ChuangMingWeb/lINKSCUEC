package com.model;

public class RentServer {
	private int id;
	private String name;
	private String password;
	private String startTime;
	private String overTime;
	private String allTime;
	private float price;
	private int creditStar;
	private boolean chuzuStatus;
	private boolean zuchuStatus;
	
	
	
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
	
	public String getAllTime() {
		return allTime;
	}
	public void setAllTime(String allTime) {
		this.allTime = allTime;
	}
	
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	
	public int getCreditStar() {
		return creditStar;
	}
	public void setCreditStar(int creditStar) {
		this.creditStar = creditStar;
	}
	public boolean getChuzuStatus() {
		return chuzuStatus;
	}
	public void setChuzuStatus(boolean chuzuStatus) {
		this.chuzuStatus = chuzuStatus;
	}
	public boolean getZuchuStatus() {
		return zuchuStatus;
	}
	public void setZuchuStatus(boolean zuchuStatus) {
		this.zuchuStatus = zuchuStatus;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((allTime == null) ? 0 : allTime.hashCode());
		result = prime * result + (chuzuStatus ? 1231 : 1237);
		result = prime * result + creditStar;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((overTime == null) ? 0 : overTime.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + Float.floatToIntBits(price);
		result = prime * result + ((startTime == null) ? 0 : startTime.hashCode());
		result = prime * result + (zuchuStatus ? 1231 : 1237);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RentServer other = (RentServer) obj;
		if (allTime == null) {
			if (other.allTime != null)
				return false;
		} else if (!allTime.equals(other.allTime))
			return false;
		if (chuzuStatus != other.chuzuStatus)
			return false;
		if (creditStar != other.creditStar)
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (overTime == null) {
			if (other.overTime != null)
				return false;
		} else if (!overTime.equals(other.overTime))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (Float.floatToIntBits(price) != Float.floatToIntBits(other.price))
			return false;
		if (startTime == null) {
			if (other.startTime != null)
				return false;
		} else if (!startTime.equals(other.startTime))
			return false;
		if (zuchuStatus != other.zuchuStatus)
			return false;
		return true;
	}
}
