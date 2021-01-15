package com.lti.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lti.dto.Status.StatusType;
import com.lti.dao.VehicleDao;
import com.lti.dto.VehicleModelStatus;
import com.lti.entity.VehicleModels;
import com.lti.exception.VehicleModelServiceException;
import com.lti.exception.VehicleServiceException;
import com.lti.service.VehicleService;

@RestController
@CrossOrigin
public class AddNewVehicleController {
	
	@Autowired
	private VehicleService vehicleService;
	@Autowired
	private VehicleDao vehicleDao;
	
	@PostMapping("/addnewvehicle")
	public @ResponseBody VehicleModelStatus add (@RequestBody VehicleModels vehicleModel) {
		VehicleModels newVehicleModel;
		try {
			VehicleModelStatus status = new VehicleModelStatus();
			if (vehicleDao.isVehicleModelPresent(vehicleModel.getManufacturer(),vehicleModel.getModel())) {
				status.setStatus(StatusType.FAILED);
				status.setMessage("Vehicle model Aready exists!");
				return status;
			}
			if(vehicleDao.isVehicleIdPresent(vehicleModel.getModelId())) {
				status.setStatus(StatusType.FAILED);
				status.setMessage("Vehicle Id already exists!");
				return status;
			}
			newVehicleModel = vehicleService.addNewVehicle(vehicleModel);
			status.setStatus(StatusType.SUCCESS);
			status.setMessage("Added new model successfully!");
			status.setVehicleModel(newVehicleModel);
			return status;
		}
		catch(VehicleModelServiceException e) {
			e.printStackTrace();
			VehicleModelStatus status = new VehicleModelStatus();
			status.setStatus(StatusType.FAILED);
			status.setMessage("Adding new model failed!");
			return status;
			
		}
	}
	
	@GetMapping("/listvehiclemodel")
	public @ResponseBody VehicleModelStatus list() {
		List<VehicleModels> vehicleList;
		try {
			vehicleList = vehicleService.listAvailableVehicle();
			VehicleModelStatus status = new VehicleModelStatus();
			status.setStatus(StatusType.SUCCESS);
			status.setMessage("Fetched Successfully!");
			status.setVehicleModels(vehicleList);
			return status;
		}
		catch(VehicleModelServiceException e) {
			e.printStackTrace();
			VehicleModelStatus status = new VehicleModelStatus();
			status.setStatus(StatusType.FAILED);
			status.setMessage("Fetch Failed!");
			return status;
		}
	}

}
