package com.artek.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.artek.model.User;
import com.artek.repository.RegistrationRepository;

@Service
public class RegistrationService {
	
	@Autowired
	private RegistrationRepository registerationRepository;
	
	public int registerService(User user){
		
		System.err.println("Inside registerService of RegistrationService class");
		/*System.out.println(user.getEmailId());
		System.out.println(user.getPassword());*/
		
		return registerationRepository.registerRepo(user);				
	}
}
