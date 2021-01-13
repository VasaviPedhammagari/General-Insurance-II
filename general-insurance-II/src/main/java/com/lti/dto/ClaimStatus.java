package com.lti.dto;

public class ClaimStatus extends Status{
	private int claimNumber;
	private double claimAmount;
	
	public int getClaimNumber() {
		return claimNumber;
	}
	
	public void setClaimNumber(int claimNumber) {
		this.claimNumber = claimNumber;
	}
	
	public double getClaimAmount() {
		return claimAmount;
	}
	
	public void setClaimAmount(double claimAmount) {
		this.claimAmount = claimAmount;
	}
}
