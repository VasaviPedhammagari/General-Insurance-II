package com.lti.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.lti.dao.UserDao;
import com.lti.dto.RenewDetails;
import com.lti.dto.ValidateClaim;
import com.lti.entity.Estimate;
import com.lti.entity.InsuranceClaim;
import com.lti.entity.MotorInsurance;
import com.lti.entity.Payment;
import com.lti.entity.User;
import com.lti.entity.Vehicle;
import com.lti.entity.VehicleModels;
import com.lti.exception.UserServiceException;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private VehicleService vehicleService;

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
	/*
	 * @Override
	 * 
	 * @Transactional public InsuranceClaim claim(int policyNumber, String email,
	 * String password, String claimReason, double claimAmount) { try { if
	 * (!userDao.isUserPresent(email)) throw new
	 * UserServiceException("User not registered"); else {
	 * System.out.println("Email found"); if (userDao.findByEmailAndPassword(email,
	 * password)== 0) throw new UserServiceException("Incorrect password"); else {
	 * System.out.println("Password found"); int userId =
	 * userDao.findByEmailAndPassword(email, password); System.out.println("hello");
	 * System.out.println(userId); System.out.println(policyNumber);
	 * System.out.println(userDao.isPolicyPresent(policyNumber, userId)); if
	 * (!userDao.isPolicyPresent(policyNumber, userId)) throw new
	 * UserServiceException("Incorrect policy number");
	 * 
	 * else { System.out.println("Policy found");
	 * 
	 * if (userDao.findBalanceClaimAmount(policyNumber)>claimAmount) {
	 * 
	 * System.out.println(userDao.findBalanceClaimAmount(policyNumber));
	 * 
	 * InsuranceClaim insuranceClaim = new InsuranceClaim();
	 * insuranceClaim.setClaimReason(claimReason);
	 * insuranceClaim.setClaimDate(LocalDate.now());
	 * insuranceClaim.setClaimStatus("APPROVED");
	 * insuranceClaim.setClaimAmount(claimAmount);
	 * 
	 * MotorInsurance motorInsurance = userDao.Fetch(MotorInsurance.class,
	 * policyNumber);
	 * motorInsurance.setTotalClaimAmount(motorInsurance.getTotalClaimAmount() +
	 * claimAmount);
	 * motorInsurance.setBalanceClaimAmount(motorInsurance.getBalanceClaimAmount() -
	 * claimAmount);
	 * 
	 * insuranceClaim.setMotorInsurance(motorInsurance);
	 * 
	 * MotorInsurance updatedmotorInsurance = (MotorInsurance)
	 * userDao.store(motorInsurance); InsuranceClaim updatedInsuranceClaim =
	 * (InsuranceClaim) userDao.store(insuranceClaim); return updatedInsuranceClaim;
	 * } else throw new
	 * UserServiceException("Enough money not available for claim"); } } } }
	 * catch(NoResultException e) { throw new
	 * UserServiceException("Enough money not available for claim"); } //return
	 * null; }
	 */

	@Override
	@Transactional
	public InsuranceClaim claim(int policyNumber, String email, String password, String claimReason,
			double claimAmount) {
		MotorInsurance motorInsurance1 = userDao.Fetch(MotorInsurance.class, policyNumber);
		try {
			if (!userDao.isUserPresent(email))
				throw new UserServiceException("User not registered");
			else {
				System.out.println("Email found");
				if (userDao.findByEmailAndPassword(email, password) > 0) {
					System.out.println("Password found");
					int userId = userDao.findByEmailAndPassword(email, password);
					if (!userDao.isPolicyPresent(policyNumber, userId))
						throw new UserServiceException("Incorrect policy number");
					else {
						System.out.println("Policy found");
						if (motorInsurance1.getPlanType().equals("Third Party Liability") || userDao.findBalanceClaimAmount(policyNumber) > claimAmount) {

							System.out.println(userDao.findBalanceClaimAmount(policyNumber));

							InsuranceClaim insuranceClaim = new InsuranceClaim();
							insuranceClaim.setClaimReason(claimReason);
							insuranceClaim.setClaimDate(LocalDate.now());
							insuranceClaim.setClaimStatus("PENDING");
							insuranceClaim.setClaimAmount(claimAmount);

							MotorInsurance motorInsurance = userDao.Fetch(MotorInsurance.class, policyNumber);
							// motorInsurance.setTotalClaimAmount(motorInsurance.getTotalClaimAmount() +
							// claimAmount);
							// motorInsurance.setBalanceClaimAmount(motorInsurance.getBalanceClaimAmount() -
							// claimAmount);

							insuranceClaim.setMotorInsurance(motorInsurance);

							MotorInsurance updatedmotorInsurance = (MotorInsurance) userDao.store(motorInsurance);
							InsuranceClaim updatedInsuranceClaim = (InsuranceClaim) userDao.store(insuranceClaim);
							return updatedInsuranceClaim;
						} else
							throw new UserServiceException("Enough money not available for claim");
					}
				} else
					throw new UserServiceException("Incorrect password");
			}
		} catch (EmptyResultDataAccessException e) {
			throw new UserServiceException("Incorrect password");
		}
	}

	@Override
	public List<VehicleModels> fetchVehicles() {
		List<VehicleModels> models = userDao.fetchAll(VehicleModels.class);
		return models;
	}

	@Override
	public MotorInsurance getRenewDetails(RenewDetails renewDetails) {
		try {
			if (!userDao.isUserPresent(renewDetails.getEmail()))
				throw new UserServiceException("User not registered");
			MotorInsurance motorInsurance = userDao.getInsuranceDetails(renewDetails);
			return motorInsurance;
		} catch (NoResultException e) {
			throw new UserServiceException("incorrect mail id or policy number or phone number");
		}
	}

	@Override
	@Transactional
	public MotorInsurance storeInsuranceDetails(MotorInsurance motorInsurance) {
		try {
			motorInsurance.setPlanStartDate(LocalDate.now());
			// motorInsurance.setPlanStartDate(LocalDate.of(2019,01,01));
			motorInsurance.setPlanExpiryDate(LocalDate.now().plusYears(motorInsurance.getNoOfYrs()));
			motorInsurance.setTotalClaimAmount(0);
            System.out.println("Before premium plans");

			List<Estimate> list =vehicleService.getPremiumPlans(motorInsurance.getVehicle());
			System.out.println(motorInsurance.getPlanType()+" "+ motorInsurance.getNoOfYrs());
			System.out.println("After premium plans");

			for(Estimate estimate : list) {
				System.out.println(estimate.getType()+" "+estimate.getNoOfYears());
				if(estimate.getType().equals(motorInsurance.getPlanType())) {
					//System.out.println(estimate.getType());
					if(estimate.getType().equals("Third Party Liability")) {
						System.out.println("inside third party if");
						motorInsurance.setInsurancePremium(estimate.getPrice()*motorInsurance.getNoOfYrs());
					}	
					if(estimate.getType().equals("Comprehensive") && estimate.getNoOfYears() == motorInsurance.getNoOfYrs()){
						System.out.println("Comprehensive if");
						motorInsurance.setBalanceClaimAmount((double)estimate.getCoverage());
						motorInsurance.setInsurancePremium(estimate.getPrice());
					}
				}
			}
			System.out.println(motorInsurance.getUser().getUserId());
			motorInsurance = (MotorInsurance) userDao.store(motorInsurance);
			System.out.println(motorInsurance.getInsurancePremium());
			return motorInsurance;
		} catch (Exception e) {
			e.printStackTrace();
			throw new UserServiceException(e.getMessage());
		}
	}

	@Override
	@Transactional
	public int savePaymentdetails(Payment payment) {
		payment.setInsuranceStatus("Active");
		payment.setPaymentStatus("Paid");
		payment.setPaymentDate(LocalDate.now());

		payment = (Payment) userDao.store(payment);
		return payment.getPaymentId();
	}

	@Override
	public List<MotorInsurance> getUserInsuranceDetails(int userId) {
		try {
			List<MotorInsurance> list = userDao.fetchInsuranceDetailsByUserId(userId);
			for(MotorInsurance insurance : list) {
				insurance.getUser().getAddress().setUser(null);
			}
			return list;
		}catch(NoResultException e) {
			throw new UserServiceException("No User with such User Id");
		}
	}	
	public List<InsuranceClaim> getAllClaims() {
		return userDao.fetchAll(InsuranceClaim.class);
	}

	@Override
	@Transactional
	public void validateClaimUpdate(ValidateClaim validateClaim) {
		// userDao.updateClaimAmount(validateClaim.getClaimNumber(),
		// validateClaim.getClaimAmount());
		InsuranceClaim insuranceClaim = userDao.Fetch(InsuranceClaim.class, validateClaim.getClaimNumber());
		insuranceClaim.setClaimStatus("APPROVED");
		insuranceClaim.setClaimAmount(validateClaim.getClaimAmount());

		int policyNumber = insuranceClaim.getMotorInsurance().getPolicyNumber();

		MotorInsurance motorInsurance = userDao.Fetch(MotorInsurance.class, policyNumber);
		motorInsurance.setTotalClaimAmount(motorInsurance.getTotalClaimAmount() + validateClaim.getClaimAmount());
		motorInsurance.setBalanceClaimAmount(motorInsurance.getBalanceClaimAmount() - validateClaim.getClaimAmount());

		insuranceClaim.setMotorInsurance(motorInsurance);

		MotorInsurance updatedmotorInsurance = (MotorInsurance) userDao.store(motorInsurance);
		InsuranceClaim updatedInsuranceClaim = (InsuranceClaim) userDao.store(insuranceClaim);
	}

	@Override
	@Transactional
	public void denyClaimUpdate(ValidateClaim validateClaim) {
		InsuranceClaim insuranceClaim = userDao.Fetch(InsuranceClaim.class, validateClaim.getClaimNumber());
		insuranceClaim.setClaimStatus("DENIED");

		InsuranceClaim updatedInsuranceClaim = (InsuranceClaim) userDao.store(insuranceClaim);

	}
}
