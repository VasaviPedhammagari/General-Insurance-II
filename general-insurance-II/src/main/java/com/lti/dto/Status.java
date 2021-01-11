package com.lti.dto;

public class Status {

	public StatusType status;
	public String message;

	public enum StatusType {
		SUCCESS, FAILED;
	}

	public StatusType getStatus() {
		return status;
	}

	public void setStatus(StatusType status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
