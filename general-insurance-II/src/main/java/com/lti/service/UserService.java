package com.lti.service;

import java.util.List;

import com.lti.dto.RenewDetails;
import com.lti.dto.ResetPassword;
import com.lti.dto.UserInsuranceStatus;
import com.lti.dto.ValidateClaim;
import com.lti.entity.InsuranceClaim;
import com.lti.entity.MotorInsurance;
import com.lti.entity.Payment;
import com.lti.entity.User;
import com.lti.entity.Vehicle;
import com.lti.entity.VehicleModels;

public interface UserService {

	public User register(User user);

	public User login(String email, String password);
	
	public InsuranceClaim claim(int policyNumber, String email, //String password, 
			String claimReason, double claimAmount);
	
    public List<VehicleModels> fetchVehicles();
	
    public MotorInsurance getRenewDetails(RenewDetails renewDetails);

    public Payment storeInsuranceDetails(MotorInsurance motorInsurance);
    
    public int savePaymentdetails(Payment payment);

	public int resetPassword(User user);
    
    public List<MotorInsurance> getUserInsuranceDetails(int userId);
    
    public List<InsuranceClaim> getPolicyClaimDetails(int policyNumber);

    public List<InsuranceClaim> getAllClaims();
    
    public void validateClaimUpdate(ValidateClaim validateClaim);
    
    public void denyClaimUpdate(ValidateClaim validateClaim);
    
    public void updateUserDetails(User user);
    
    public UserInsuranceStatus getVehiclesByUserId(int userId);

    public User getUserDetails(int userId);

}
