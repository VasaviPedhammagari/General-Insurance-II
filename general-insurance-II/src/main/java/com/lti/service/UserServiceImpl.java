package com.lti.service;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.dao.UserDao;
import com.lti.entity.User;
import com.lti.exception.UserServiceException;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Transactional
	public User register(User user) {
		if (userDao.isUserPresent(user.getEmail()))
			throw new UserServiceException("User already registerd");
		User updatedUser = (User) userDao.store(user);
		return updatedUser;
	}

	@Override
	public User login(String email, String password) {
		try {
			if (!userDao.isUserPresent(email))
				throw new UserServiceException("User not registered");
			int id = userDao.findByEmailAndPassword(email, password);
			User user = userDao.Fetch(User.class, id);
			return user;
		} catch (NoResultException e) {
			throw new UserServiceException("incorrect password");
		}
	}

}
