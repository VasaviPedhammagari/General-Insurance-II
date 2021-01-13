package com.lti.dto;

public class RegisterStatus extends Status {

	private int registeredUserId;
	private String registeredUserName;

	public int getRegisteredUserId() {
		return registeredUserId;
	}

	public void setRegisteredUserId(int registeredUserId) {
		this.registeredUserId = registeredUserId;
	}

	public String getRegisteredUserName() {
		return registeredUserName;
	}

	public void setRegisteredUserName(String registeredUserName) {
		this.registeredUserName = registeredUserName;
	}

}
