package com.lti.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lti.entity.Estimate;
import com.lti.entity.Vehicle;
import com.lti.service.VehicleService;

@RestController
@CrossOrigin
public class PremiumEstimationController {

	@Autowired
	private VehicleService vehicleService;
	
	@PostMapping("/get-estimates")
	public List<Estimate> calculateEstimates(@RequestBody Vehicle vehicle){
		List<Estimate> list = vehicleService.getPremiumPlans(vehicle);
		return list;
	}
	
}
