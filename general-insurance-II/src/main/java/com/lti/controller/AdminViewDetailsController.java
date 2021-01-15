package com.lti.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lti.dao.AdminViewDetailsDao;
import com.lti.entity.User;

@RestController
public class AdminViewDetailsController {

	@Autowired
	AdminViewDetailsDao adminViewDetailsDao;
	  
	@GetMapping(path="/admindash/userdetails")
	public List<User> getUserDetails()
	{
		return adminViewDetailsDao.findAll();
	}
}
