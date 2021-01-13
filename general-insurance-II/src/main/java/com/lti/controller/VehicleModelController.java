/*
package com.lti.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lti.entity.VehicleModels;
import com.lti.service.UserService;

@RestController
@CrossOrigin
public class VehicleModelController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("fetchVehicleModel")
	public List<VehicleModels> fetchModels(){
		return userService.fetchVehicles();
	}
	
}
*/