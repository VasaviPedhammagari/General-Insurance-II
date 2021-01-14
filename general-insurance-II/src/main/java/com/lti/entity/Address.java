package com.lti.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "my_address_seq")
	@SequenceGenerator(sequenceName = "address_seq", allocationSize = 1, name = "my_address_seq")
	private int addressId;

	@Column(name = "address_line")
	private String addressLine;

	private String city;
	private int pin;
	private String state;
	
//	@OneToOne
//	@JoinColumn(name = "user_id")
//	private User user;
	@JsonIgnoreProperties(value = {"address"},allowSetters = true)
	@OneToOne(mappedBy = "address")
	private User user;

	public int getAddressId() {
		return addressId;
	}

	public void setAddressId(int addressId) {

		this.addressId = addressId;
	}

	public String getAddressLine() {
		return addressLine;
	}

	public void setAddressLine(String addressLine) {
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
