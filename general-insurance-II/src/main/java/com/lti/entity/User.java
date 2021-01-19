package com.lti.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "user_details")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "my_user_seq")
	@SequenceGenerator(sequenceName = "user_seq", allocationSize = 1, name = "my_user_seq")
	@Column(name = "user_id")
	private int userId;

	@Column(name = "user_name")
	private String userName;

	@Column(name = "user_email")
	private String email;

	@Column(name = "date_of_birth")
	private LocalDate dateOfBirth;

	@Column(name = "user_phone")
	private long phoneNo;

	private String password;

	@JsonIgnoreProperties(value = {"address"},allowSetters = true)
	@OneToOne(cascade = { CascadeType.PERSIST,CascadeType.MERGE, CascadeType.REMOVE })
	@JoinColumn(name = "address_id", referencedColumnName = "addressId")
	private Address address;
	
//	@OneToOne(mappedBy = "user", cascade = { CascadeType.PERSIST,CascadeType.MERGE, CascadeType.REMOVE })
//	private Address address;
    @JsonIgnoreProperties(value = {"user"},allowSetters = true)
	@OneToMany(mappedBy = "user",cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REMOVE})
	private List<MotorInsurance> insurances;
    
    @OneToMany(mappedBy = "user",cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REMOVE})
    @JsonIgnoreProperties(value = {"user","insurances"},allowSetters = true)
	private List<Vehicle> vehicles;
    
	public List<Vehicle> getVehicles() {
		return vehicles;
	}

	public void setVehicles(List<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public long getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(long phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<MotorInsurance> getInsurances() {
		return insurances;
	}

	public void setInsurances(List<MotorInsurance> insurances) {
		this.insurances = insurances;
	}

}
