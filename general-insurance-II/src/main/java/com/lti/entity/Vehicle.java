package com.lti.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "vehicle")
public class Vehicle {

	@Id
	@Column(name = "reg_no")
	private String regNo;
	
	private String manufacturer;
	private String model;
	
	@Column(name="driving_license")
	private String drivingLicense;
	
	@Column(name = "purchase_date")
	private LocalDate purchaseDate;
	
	@Column(name = "engine_number")
	private String engineNumber;
	
	@Column(name = "chassis_number")
	private String chassisNumber;
	
	@Column(name = "vehicle_type")
	private String vehicleType;
	
	@JsonIgnoreProperties(value = {"vehicle"},allowSetters = true)
    @OneToMany(mappedBy = "vehicle",cascade = { CascadeType.PERSIST,CascadeType.REMOVE })
	private List<MotorInsurance> insurances;
	
	@ManyToOne(cascade = { CascadeType.PERSIST,CascadeType.MERGE, CascadeType.REMOVE })
	@JsonIgnoreProperties(value = {"vehicles","insurances"},allowSetters = true)
	@JoinColumn(name = "user_id")
	private User user;
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<MotorInsurance> getInsurances() {
		return insurances;
	}
	public void setInsurances(List<MotorInsurance> insurances) {
		this.insurances = insurances;
	}
	
	public String getRegNo() {
		return regNo;
	}
	public void setRegNo(String regNo) {
		this.regNo = regNo;
	}
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getDrivingLicense() {
		return drivingLicense;
	}
	public void setDrivingLicense(String drivingLicense) {
		this.drivingLicense = drivingLicense;
	}
	public LocalDate getPurchaseDate() {
		return purchaseDate;
	}
	public void setPurchaseDate(LocalDate purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	public String getEngineNumber() {
		return engineNumber;
	}
	public void setEngineNumber(String engineNumber) {
		this.engineNumber = engineNumber;
	}
	public String getChassisNumber() {
		return chassisNumber;
	}
	public void setChassisNumber(String chassisNumber) {
		this.chassisNumber = chassisNumber;
	}
	public String getVehicleType() {
		return vehicleType;
	}
	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}
	
}
