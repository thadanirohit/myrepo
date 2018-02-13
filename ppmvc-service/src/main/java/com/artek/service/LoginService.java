package com.artek.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.artek.model.User;
import com.artek.repository.LoginRepository;

@Service
public class LoginService {
	
	@Autowired
	private LoginRepository loginRepository;
	
	public boolean loginServ(User user){
		
		System.err.println("Inside loginServ of LoginService class");
		System.out.println(user.getEmailId());
		System.out.println(user.getPassword());
		
		return loginRepository.checkLogin(user.getEmailId(),user.getPassword());
		
		//return user;
	}
	
}
