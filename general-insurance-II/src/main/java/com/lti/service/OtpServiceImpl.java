package com.lti.service;

import java.time.LocalDateTime;
import java.util.Random;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.persistence.NoResultException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.entity.Otp;
import com.lti.exception.OtpMismatchException;
import com.lti.dao.OtpDao;

@Service
public class OtpServiceImpl implements OtpService{
	
@Autowired
OtpDao otpRepo;

//@Autowired
//SmsService smsService;

@Autowired
MailService mailService;

@Override
@Transactional
public int generateOtp(String emailId) {
	
	String numbers = "0123456789"; 
	  
    
    Random rndm_method = new Random(); 

    String otpRandom = ""; 

    for (int i = 0; i < 4; i++) 
    { 
        
        otpRandom += 
         numbers.charAt(rndm_method.nextInt(numbers.length())); 
    } 
    
    if(otpRepo.isOtpPresent(emailId)) {
    	Otp otpOriginal=otpRepo.getOtpNumber(emailId);
		mailService.sendotp(otpOriginal.getOtpNumber(),emailId);
    	return otpOriginal.getOtpNumber();
    }
    
    Otp otp=new Otp();
    otp.setEmailId(emailId);
    otp.setOtpNumber(Integer.parseInt(otpRandom));
    otp.setGeneratedTime(LocalDateTime.now());
    otpRepo.store(otp);
    mailService.sendotp(otp.getOtpNumber(),emailId);
    //System.out.println(smsService.sendSms(otp.getOtpNumber()));
    return otp.getOtpNumber();
    
}

@Override
@Transactional
public boolean validateOtp(Otp otp) {
	try {
	Otp originalOtp=otpRepo.getOtpNumber(otp.getEmailId());
	
	
	int otpBackup=originalOtp.getOtpNumber();
/*	
	Duration duration = Duration.between(otp.getGeneratedTime(), originalOtp.getGeneratedTime());
	// total seconds of difference (using Math.abs to avoid negative values)
	long seconds = Math.abs(duration.getSeconds());
	if(seconds>30) {
		return false;
	}
	*/
	otpRepo.delete(originalOtp);
	if(otp.getOtpNumber()==otpBackup)
		return true;
	return false;
	}
	catch(NoResultException e) {
		throw new OtpMismatchException("Invalid orp");
		
	}
	
	
}


	
}
