package com.artek.validation;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.artek.model.Project;
import com.artek.repository.ProjectRepository;

@Component
public class ProjectValidator implements Validator {
	
	@Autowired
	private ProjectRepository projectRepo;

	public boolean supports(Class<?> arg0) {
		return Project.class.isAssignableFrom(arg0);
	}

	public void validate(Object arg0, Errors errors) {
		
		Project project = (Project)arg0;
		
		System.out.println("inside validator() method of ProjectValidator");
		
		ValidationUtils.rejectIfEmpty(errors, "projectName", "errors.projectName.empty");
		ValidationUtils.rejectIfEmpty(errors, "clientName", "errors.clientName.empty");
		
		ValidationUtils.rejectIfEmpty(errors, "startDate", "errors.startDate.empty_or_notinformat");
		ValidationUtils.rejectIfEmpty(errors, "endDate", "errors.endDate.empty_or_notinformat");
		
		//Date startDate = project.getStartDate();
		//Date endDate = project.getEndDate();
		
		//System.out.println(startDate);
		//System.out.println(endDate);
		
		/* Database Validation 
		------------------------------------------------------------------------------------ */
		if(!errors.hasFieldErrors("emailId")){
			if( projectRepo.checkProjectName(project.getProjectName()) > 0 ){
				
				// Error Messages WITHOUT properties file (rejectIfEmpty with 4 parameters)
				errors.rejectValue("projectName", "errors.projectName.exist");
			}
		}
		
	}

}
