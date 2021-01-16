package com.lti.service;

import com.lti.entity.Otp;

public interface OtpService {
	public int generateOtp(String login_id);
	public boolean validateOtp(Otp otp);

}
