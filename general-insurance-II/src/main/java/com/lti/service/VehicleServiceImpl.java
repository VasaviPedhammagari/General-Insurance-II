package com.lti.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.dao.VehicleDao;
import com.lti.entity.Estimate;
import com.lti.entity.Vehicle;
import com.lti.entity.VehicleModels;
import com.lti.exception.UserServiceException;
import com.lti.exception.VehicleServiceException;

@Service
public class VehicleServiceImpl implements VehicleService {

	@Autowired
	private VehicleDao vehicleDao;

	@Transactional
	public Vehicle register(Vehicle vehicle) {
		if (vehicleDao.isVehiclePresent(vehicle.getRegNo()))
			throw new VehicleServiceException("Vehicle already registerd");
		Vehicle newVehicle = (Vehicle) vehicleDao.store(vehicle);
		return newVehicle;

	}

	@Override
	public List<Estimate> getPremiumPlans(Vehicle vehicle) {
		List<Estimate> estimates = new ArrayList<>();

		LocalDate today = LocalDate.now();

		Period age = Period.between(vehicle.getPurchaseDate(), today);

		VehicleModels vehicleModel = vehicleDao.findPriceOfVehicle(vehicle.getManufacturer(), vehicle.getModel());

		int price = vehicleModel.getPrice();
		String type = vehicleModel.getType();

		int year = age.getYears();
		int depriciationRatePercentage = 1;
		int depriciatedValue = 1;
		int insurancePrice = 1;

		if (year < 1) {
			depriciationRatePercentage = 5;
		} else if (year < 6) {
			depriciationRatePercentage = year * 10;
		} else {
			depriciationRatePercentage = 80;
		}

		depriciatedValue = price - (price * depriciationRatePercentage / 100);

		insurancePrice = (depriciatedValue * (2) / 100) - 20 * (depriciatedValue * (2) / 100) / 100;

		Estimate e1 = new Estimate();
		e1.setCoverage(depriciatedValue);
		e1.setNoOfYears(1);
		e1.setPrice(insurancePrice);
		e1.setType("Comprehensive");

		Estimate e2 = new Estimate();
		e2.setCoverage(depriciatedValue);
		e2.setNoOfYears(2);
		e2.setPrice(insurancePrice * 2 - (0.1*insurancePrice));
		e2.setType("Comprehensive");

		Estimate e3 = new Estimate();
		e3.setCoverage(depriciatedValue);
		e3.setNoOfYears(3);
		e3.setPrice(insurancePrice * 3 - (0.2*insurancePrice));
		e3.setType("Comprehensive");

		Estimate e4 = new Estimate();

		if (type.equals("4-Wheeler")) {
			if (price > 1000000) {
				e4.setNoOfYears(1);
				e4.setPrice(7790);
				e4.setType("Third Party Liability");
			} else {
				e4.setNoOfYears(1);
				e4.setPrice(3121);
				e4.setType("Third Party Liability");
			}
		} else {
			e4.setNoOfYears(1);
			e4.setPrice(1530);
			e4.setType("Third Party Liability");
		}
		
		Estimate e5 = new Estimate();
		e5.setNoOfYears(2);
		e5.setPrice(e4.getPrice()*2);
		e5.setType("Third Party Liability");
		
		Estimate e6 = new Estimate();
		e6.setNoOfYears(3);
		e6.setPrice(e4.getPrice()*3);
		e6.setType("Third Party Liability");
		estimates.add(e1);
		estimates.add(e2);
		estimates.add(e3);
		estimates.add(e4);
		estimates.add(e5);
		estimates.add(e6);
		return estimates;

	}

	@Override
	@Transactional
	public VehicleModels addNewVehicle(VehicleModels vehicleModel) {
		if (vehicleDao.isVehicleModelPresent(vehicleModel.getManufacturer(),vehicleModel.getModel()))
			throw new VehicleServiceException("Vehicle Model already exists!");		
		return (VehicleModels) vehicleDao.store(vehicleModel);
	}

	@Override
	@Transactional
	public List<VehicleModels> listAvailableVehicle() {		
		return vehicleDao.fetchAll(VehicleModels.class);
	}

}
