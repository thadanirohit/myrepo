package com.artek.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.artek.model.User;

@Repository
public class LoginRepository {
	
	@Autowired
	private HibernateTemplate template;
	
	public boolean checkLogin(String email, String password){
		
		System.err.println("Inside loginRepo of LoginRepository class");
		System.out.println("##"+email+"##");
		System.out.println("##"+password+"##");
		
		List registeredUsers = null;
		
		registeredUsers = template.find("from User where emailId=? and password=?", email, password);
		
		System.err.println(registeredUsers);
		
		if( registeredUsers.size() > 0 ){
			System.out.println("Login Successfull");
			return true;
		}else{
			System.out.println("Invalid username / password");
			return false;
		}
		//return user;
	}
	
}
