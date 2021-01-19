package com.lti.dao;

import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;

import com.lti.dto.RenewDetails;
import com.lti.entity.InsuranceClaim;
import com.lti.entity.MotorInsurance;
import com.lti.entity.Payment;
import com.lti.entity.Vehicle;
import com.lti.exception.UserServiceException;

@Repository
public class UserDao extends GenericDao {

	public boolean isUserPresent(String email) {

		return (Long) entityManager.createQuery("select count(u) from User u where u.email = :email")
				.setParameter("email", email).getSingleResult() == 1 ? true : false;
	}

	public int findByEmailAndPassword(String email, String password) {
		try {
			return (Integer) entityManager
					.createQuery("select u.userId from User u where u.email = :email and u.password = :password")
					.setParameter("email", email).setParameter("password", password).getSingleResult();
		}catch(NoResultException e) {
			throw new UserServiceException("incorrect password");
		} 
		
	}

	public MotorInsurance getInsuranceDetails(RenewDetails renewDetails) {
		return (MotorInsurance) entityManager
				.createQuery("select m from MotorInsurance m join m.user u where m.policyNumber = :policyNumber "
						+ "and u.email = :email and u.phoneNo = :phoneNumber")
				.setParameter("policyNumber", renewDetails.getPolicyNumber())
				.setParameter("email", renewDetails.getEmail()).setParameter("phoneNumber", renewDetails.getPhoneNo())
				.getSingleResult();

	}

	public boolean isPolicyPresent(int policyNumber, String email) {
		try {
			return (Long) entityManager.createQuery(
					"select count(m.policyNumber) from MotorInsurance m join m.user u where u.email = :email and m.policyNumber =:policyNumber")
					.setParameter("email", email).setParameter("policyNumber", policyNumber).getSingleResult() == 1
							? true
							: false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	public double findBalanceClaimAmount(int policyNumber) {
		return (Double) entityManager
				.createQuery("select m.balanceClaimAmount from MotorInsurance m where m.policyNumber = :policyNumber")
				.setParameter("policyNumber", policyNumber).getSingleResult();
	}

	public void updateClaimAmount(int claimNumber, int claimAmount) {
		entityManager.createQuery("update InsuranceClaim c set c.claimAmount = :amount where c.claimNumber = :number")
				.setParameter("amount", claimAmount).setParameter("number", claimNumber).executeUpdate();

		entityManager.createQuery("update InsuranceClaim c set c.claimStatus = :status where c.claimNumber = :number")
				.setParameter("status", "Approved").setParameter("number", claimNumber).executeUpdate();

		entityManager.createQuery(
				"update MotorInsurance m set m.totalClaimAmount = m.totalClaimAmount + :amount "
				+ "where m.policyNumber = (select m.policyNumber from InsuranceClaim c inner join c.motorInsurance m where c.claimNumber = :number)")
				.setParameter("amount", claimAmount).setParameter("number", claimNumber).executeUpdate();
		
		entityManager.createQuery(
				"update MotorInsurance m set m.balanceClaimAmount = m.balanceClaimAmount - :amount "
				+ "where m.policyNumber = (select m.policyNumber from InsuranceClaim c inner join c.motorInsurance m where c.claimNumber = :number)")
				.setParameter("amount", claimAmount).setParameter("number", claimNumber).executeUpdate();

	}

	public void denyClaim(int claimNumber) {
		entityManager.createQuery("update InsuranceClaim c set c.claimStatus = :status where c.claimNumber = :number")
				.setParameter("status", "Denied").setParameter("number", claimNumber).executeUpdate();
	}
	
	public int getPolicyNumber(int claimNumber) {
		return (Integer) entityManager.createQuery("select m.policyNumber from InsuranceClaim c inner join c.motorInsurance m where c.claimNumber = :number")
				.setParameter("number", claimNumber)
				.getSingleResult();
	}
	
	public List<MotorInsurance> fetchInsuranceDetailsByUserId(int userId){
		return entityManager
			   .createQuery("select u.insurances from User u where u.userId = :userId")
			   .setParameter("userId", userId)
			   .getResultList();
	}
	
	public List<InsuranceClaim> fetchClaimDetailsByPolicyNumber(int policyNumber){
		return entityManager
			   .createQuery("select i from InsuranceClaim i join i.motorInsurance m where m.policyNumber = :policyNumber")
			   .setParameter("policyNumber", policyNumber)
			   .getResultList();
	}
	
	public Object findByEmail(String email) {
		return entityManager.createQuery("select u from User u where u.email = :email").setParameter("email", email).getSingleResult();
	}
	
	public List<Vehicle> fetchVehiclesByUserId(int userId){
		return entityManager
				.createQuery("select v from Vehicle v join v.user u where u.userId = :userId")
				.setParameter("userId", userId)
				.getResultList();
	}

	public Payment fetchPaymentDetailsByPolicyNumber(int policyNumber) {
		return (Payment) entityManager
				.createQuery("select p from Payment p join p.motorInsurance m where m.policyNumber = :policyNumber order by m.planExpiryDate desc")
				.setParameter("policyNumber", policyNumber)
				.setMaxResults(1)
				.getSingleResult();
	}

}
