package com.lti.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="otp")
public class Otp {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "otp_sequence")
	@SequenceGenerator(name = "otp_sequence", sequenceName = "otp_sequence", allocationSize = 10)
	@Column(name = "id")
	private int id;
	
	@Column(name="email_id")
	private String emailId;
	
	@Column(name="otp_number")
	private int otpNumber;
	
	@Column(name="generated_time")
	private LocalDateTime generatedTime;

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
	
	
	
	
}
