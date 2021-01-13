package com.lti.entity;

public class Estimate {

	private double price;
	private long coverage;
	private String type;
	private int noOfYears;

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public long getCoverage() {
		return coverage;
	}

	public void setCoverage(long coverage) {
		this.coverage = coverage;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getNoOfYears() {
		return noOfYears;
	}

	public void setNoOfYears(int noOfYears) {
		this.noOfYears = noOfYears;
	}

}
