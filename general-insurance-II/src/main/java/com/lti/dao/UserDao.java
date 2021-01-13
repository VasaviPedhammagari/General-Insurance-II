package com.lti.dao;

import org.springframework.stereotype.Repository;

import com.lti.dto.RenewDetails;
import com.lti.entity.MotorInsurance;
import com.lti.entity.User;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;


@Repository
public class UserDao extends GenericDao{

	public boolean isUserPresent(String email) {

				return (Long) entityManager
						.createQuery("select count(u) from User u where u.email = :email")
				        .setParameter("email", email).getSingleResult() == 1 ? true : false;
	}
	
	public int findByEmailAndPassword(String email, String password) {
		return (Integer) entityManager
				.createQuery("select u.userId from User u where u.email = :email and u.password = :password")
				.setParameter("email", email)
				.setParameter("password", password)
				.getSingleResult();
	}
	
	public MotorInsurance getInsuranceDetails(RenewDetails renewDetails) {
		return (MotorInsurance) entityManager
				.createQuery("select m from MotorInsurance m join m.user u where m.policyNumber = :policyNumber "
						+ "and u.email = :email and u.phoneNo = :phoneNumber")
		        .setParameter("policyNumber", renewDetails.getPolicyNumber())
		        .setParameter("email", renewDetails.getEmail())
		        .setParameter("phoneNumber", renewDetails.getPhoneNo())
		        .getSingleResult();
		
	}

}
