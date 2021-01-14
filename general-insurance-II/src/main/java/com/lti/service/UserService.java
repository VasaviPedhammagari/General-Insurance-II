package com.lti.service;

import java.util.List;

import com.lti.dto.RenewDetails;
import com.lti.dto.ValidateClaim;
import com.lti.entity.InsuranceClaim;
import com.lti.entity.MotorInsurance;
import com.lti.entity.Payment;
import com.lti.entity.User;
import com.lti.entity.VehicleModels;

public interface UserService {

	public User register(User user);

	public User login(String email, String password);
	
	public InsuranceClaim claim(int policyNumber, String email, String password, String claimReason, double claimAmount);
	
    public List<VehicleModels> fetchVehicles();
	
    public MotorInsurance getDetails(RenewDetails renewDetails);

    public MotorInsurance storeInsuranceDetails(MotorInsurance motorInsurance);
    
    public int savePaymentdetails(Payment payment);
    
    public List<InsuranceClaim> getAllClaims();
    
    public void validateClaimUpdate(ValidateClaim validateClaim);
    
    public void denyClaimUpdate(ValidateClaim validateClaim);
}
