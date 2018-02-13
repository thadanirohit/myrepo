package com.artek.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.artek.model.Test;
import com.artek.model.User;

@Component
public class GetPostValidaor implements Validator {

	public boolean supports(Class<?> arg0) {
		// TODO Auto-generated method stub
		return Test.class.isAssignableFrom(arg0);
	}

	public void validate(Object arg0, Errors errors) {
		
		Test test = (Test)arg0;
		
		ValidationUtils.rejectIfEmpty(errors, "name", "errors.emailId.empty","name can not be empty");
		ValidationUtils.rejectIfEmpty(errors, "address", "errors.password.empty", "address can not be empty");
		
	}

}
