package com.lti.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Address {

	@Id
	private int addressId;

	private String addressLine;
	private String city;
	private int pin;
	private String state;

	@OneToOne(mappedBy = "address", cascade = { CascadeType.MERGE, CascadeType.REMOVE })
	private User user;

	public int getId() {
		return addressId;
	}

	public void setId(int id) {
		this.addressId = id;
	}

	public String getLandmark() {
		return addressLine;
	}

	public void setLandmark(String addressLine) {
		this.addressLine = addressLine;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getPin() {
		return pin;
	}

	public void setPin(int pin) {
		this.pin = pin;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
