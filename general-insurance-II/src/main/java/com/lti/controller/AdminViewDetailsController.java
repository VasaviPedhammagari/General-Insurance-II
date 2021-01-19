package com.lti.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lti.dao.AdminViewDetailsDao;
import com.lti.entity.User;

@RestController
@CrossOrigin
public class AdminViewDetailsController {

	@Autowired
	AdminViewDetailsDao adminViewDetailsDao;
	  
	@GetMapping(path="/userdetails")
	public @ResponseBody List<User> getUserDetails()
	{
		List<User> list = adminViewDetailsDao.findAll();
		for(User user: list) {
			user.setAddress(null);
			user.setVehicles(null);
			user.setInsurances(null);
		}
		return list;
	}
}
