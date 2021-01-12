package com.lti.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.dao.VehicleDao;
import com.lti.entity.Vehicle;
import com.lti.exception.UserServiceException;

@Service
public class VehicleServiceImpl implements VehicleService{

	@Autowired
	private VehicleDao vehicleDao;
	
	@Transactional
	public String register(Vehicle vehicle) {
		if (vehicleDao.isVehiclePresent(vehicle.getRegNo()))
			throw new UserServiceException("Vehicle already registerd");
		Vehicle newVehicle = (Vehicle) vehicleDao.store(vehicle);
		return newVehicle.getRegNo();
	}

}
