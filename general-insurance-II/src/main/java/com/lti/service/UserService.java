package com.lti.service;

import javax.transaction.Transactional;

import com.lti.entity.User;

public interface UserService {

	public User register(User user);

	public User login(String email, String password);

}
