package com.lti.dao;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.lti.entity.Otp;

@Repository
public class OtpDao extends GenericDao{

	
	public Otp getOtpNumber(String emailId) throws NoResultException {
		System.out.println(emailId);
		return (Otp)entityManager.createQuery("select o from Otp o where o.emailId=:emailId")
				.setParameter("emailId", emailId).getSingleResult();
	}
	
	
	
	public boolean isOtpPresent(String emailId) {
		return (Long)entityManager.createQuery("select count(o.id) from Otp o where o.emailId=:emailId")
				.setParameter("emailId", emailId).getSingleResult()==1?true:false;
	}
	
}
