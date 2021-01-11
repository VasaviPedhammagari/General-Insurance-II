package com.lti.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.dao.UserDao;
import com.lti.entity.User;
import com.lti.exception.UserServiceException;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDao userDao;
	
	@Transactional
	public int register(User user) {
		if(userDao.isUserPresent(user.getEmail()))
			throw new UserServiceException("User already registerd");
		User updatedUser = (User) userDao.save(user);
		return updatedUser.getUserId();
	}
	
}
