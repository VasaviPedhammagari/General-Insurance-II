package com.lti.dto;

import java.time.LocalDateTime;

import javax.persistence.Column;

public class Response extends Status {

	private int id;
	
	private String emailId;
	
	private int otpNumber;
	
	private LocalDateTime generatedTime;
	
	private Object Payload;
	
	private int StatusCode;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public int getOtpNumber() {
		return otpNumber;
	}

	public void setOtpNumber(int otpNumber) {
		this.otpNumber = otpNumber;
	}

	public LocalDateTime getGeneratedTime() {
		return generatedTime;
	}

	public void setGeneratedTime(LocalDateTime generatedTime) {
		this.generatedTime = generatedTime;
	}

	public Object getPayload() {
		return Payload;
	}

	public void setPayload(Object payload) {
		Payload = payload;
	}

	public int getStatusCode() {
		return StatusCode;
	}

	public void setStatusCode(int statusCode) {
		StatusCode = statusCode;
	}
	
	
}
