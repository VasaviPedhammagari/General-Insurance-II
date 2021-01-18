package com.lti.dto;

public class Claim {
	private int policyNumber;
	private String email; 
	//private String password;
	private String claimReason;
	private double claimAmount;
	
	public int getPolicyNumber() {
		return policyNumber;
	}
	
	public void setPolicyNumber(int policyNumber) {
		this.policyNumber = policyNumber;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	/*
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	*/
	public String getClaimReason() {
		return claimReason;
	}
	
	public void setClaimReason(String claimReason) {
		this.claimReason = claimReason;
	}
	
	public double getClaimAmount() {
		return claimAmount;
	}
	
	public void setClaimAmount(double claimAmount) {
		this.claimAmount = claimAmount;
	}
}
