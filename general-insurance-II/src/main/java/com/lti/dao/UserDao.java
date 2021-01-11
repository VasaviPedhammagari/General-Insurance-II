package com.lti.dao;

import org.springframework.stereotype.Repository;

@Repository
public class UserDao extends GenericDao{
	
	public boolean isUserPresent(String email) {
		return (Long) entityManager
				.createQuery("select count(u.userId) from User as u where e.email = :em")
				.setParameter("em", email)
				.getSingleResult() == 1 ? true : false;
	}
	
}
