package com.artek.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


import com.artek.model.User;
import com.artek.repository.LoginRepository;

@Component
public class LoginValidator implements Validator {
	
	@Autowired
	private LoginRepository loginRepo;

	public boolean supports(Class<?> arg0) {
		// TODO Auto-generated method stub
		return User.class.isAssignableFrom(arg0);
	}

	public void validate(Object arg0, Errors errors) {
		
		User user = (User)arg0;
		
		ValidationUtils.rejectIfEmpty(errors, "emailId", "errors.emailId.empty");
		ValidationUtils.rejectIfEmpty(errors, "password", "errors.password.empty");
	}

}
