package com.artek.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.artek.model.User;
import com.artek.repository.RegistrationRepository;

@Component
public class RegistrationValidator implements Validator {
	
	@Autowired
	RegistrationRepository regRepo;

	public boolean supports(Class<?> arg0) {
		// TODO Auto-generated method stub
		return User.class.isAssignableFrom(arg0);
	}

	public void validate(Object target, Errors errors) {
		User user = (User)target;
		
		/* Empty and null Validation 
		------------------------------------------------------------------------------------ */
		
		// Error Messages WITHOUT properties file (rejectIfEmpty with 4 parameters)
		
		/*ValidationUtils.rejectIfEmpty(errors, "firstName", "errorFN", "Please fill First Name");
		ValidationUtils.rejectIfEmpty(errors, "lastName", "errorLN", "Please fill Last Name");
		ValidationUtils.rejectIfEmpty(errors, "emailId", "errorEMAIL", "Please fill Email ID");
		ValidationUtils.rejectIfEmpty(errors, "password", "errorPASS", "Please fill Password");*/
		
		// Error Messages WITH properties file (rejectIfEmpty with 3 parameters)
		
		ValidationUtils.rejectIfEmpty(errors, "firstName", "errors.firstName.empty");
		ValidationUtils.rejectIfEmpty(errors, "lastName", "errors.lastName.empty");
		ValidationUtils.rejectIfEmpty(errors, "emailId", "errors.emailId.empty");
		ValidationUtils.rejectIfEmpty(errors, "password", "errors.password.empty");
		
		if(!errors.hasErrors()){						

			/* Business Rules Validation 
			------------------------------------------------------------------------------------ */
			
			// First Name length validation
			if(user.getFirstName().length() > 25){
				
				// Error Messages WITHOUT properties file (rejectIfEmpty with 4 parameters)
				errors.rejectValue("firstName", "errorFN.length", "First Name must not be greater than 25 characters");
			}
			
			// Last Name length validation
			if(user.getLastName().length() > 25){
				
				// Error Messages WITHOUT properties file (rejectIfEmpty with 4 parameters)
				errors.rejectValue("lastName", "errorLN.length", "Last Name must not be greater than 25 characters");
			}
			
			// Email Format validation
			boolean emailFlag = false;
			String email = user.getEmailId();
			if( email.contains(".") && email.contains("@") ){
				
				email = email.replaceAll("\\.", "@");
				
				System.out.println(email);
				
				String[] splitted = email.split("@");
				
				for(String x:splitted){
					if(x.length() == 0){
						emailFlag = true;
					}
				}
			}else{
				emailFlag = true;
			}
			if(emailFlag){
				
				// Error Messages WITHOUT properties file (rejectIfEmpty with 4 parameters)
				errors.rejectValue("emailId", "errorEMAIL.format", "Email Format is incorrect. Sample: <abc@xyz.com>");
			}
			
			// Password Policy check
			Pattern p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
			Matcher m = p.matcher(user.getPassword());
			
			boolean checkPolicy = m.find();
			
			if( !(user.getPassword().length() >= 6 && user.getPassword().length() <= 12) ){
				
				// Error Messages WITHOUT properties file (rejectIfEmpty with 4 parameters)
				errors.rejectValue("password", "errorPASS.length", "Password length be between 6 and 12");
			}
			
			if(!checkPolicy){
				
				// Error Messages WITHOUT properties file (rejectIfEmpty with 4 parameters)
				errors.rejectValue("password", "errorPASS.format", "Password must contain special characters");
			}
			
			
			/* Database Validation 
			------------------------------------------------------------------------------------ */
			if(!errors.hasFieldErrors("emailId")){
				if( regRepo.checkEmail(user.getEmailId()) > 0 ){
					
					// Error Messages WITHOUT properties file (rejectIfEmpty with 4 parameters)
					errors.rejectValue("emailId", "errorEMAIL.db", "Email already exist");
				}
			}
		}
						
	}

}
