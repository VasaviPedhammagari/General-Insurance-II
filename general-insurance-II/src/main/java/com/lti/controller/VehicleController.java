package com.lti.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lti.dto.RegisterStatus;
import com.lti.dto.Status.StatusType;
import com.lti.dto.VehicleStatus;
import com.lti.entity.Vehicle;
import com.lti.exception.VehicleServiceException;
import com.lti.service.VehicleService;

@RestController
@CrossOrigin
public class VehicleController {
	
	@Autowired
	private VehicleService vehicleService;
	
	@PostMapping("/register-vehicle")
	public @ResponseBody VehicleStatus register(@RequestBody Vehicle vehicle) {
		try {
			//System.out.println(vehicle.getRegNo()+" "+vehicle.getManufacturer()+" "+vehicle.getEngineNumber());
			vehicle = vehicleService.register(vehicle);
			//System.out.println("called!");
			VehicleStatus status = new VehicleStatus();
			status.setStatus(StatusType.SUCCESS);
			status.setMessage("Registration Successfull");
			status.setVehicle(vehicle);
			return status;
			//return "Vehicle "+vehicleId+" is registered";*/
			//return null;
		}
		catch(VehicleServiceException e) {
			System.out.println("exception");
			e.printStackTrace();
			VehicleStatus status = new VehicleStatus();
			status.setStatus(StatusType.FAILED);
			status.setMessage("Registration failed");
			return status;
			//return "Vehicle Registration Failed";
		}
	}

}
