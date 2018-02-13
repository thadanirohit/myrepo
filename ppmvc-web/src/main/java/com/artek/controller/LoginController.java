package com.artek.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.artek.model.User;
import com.artek.service.LoginService;
import com.artek.validation.LoginValidator;
import com.artek.validation.RegistrationValidator;

@Controller
public class LoginController {
	
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private LoginValidator loginValidation;
	
	@RequestMapping("/login")
	public String showLogin(Map<String,Object> map){
		map.put("user", new User());
		return "login";
	}
	
	@RequestMapping(value="/checkLogin",method = RequestMethod.POST)
	public String checkLogin(@ModelAttribute("user") User user, BindingResult result, ModelMap model, Map map){
		
		System.err.println("Inside checkLogin of LoginController class");
		System.out.println(user.getEmailId());
		System.out.println(user.getPassword());			
		
		loginValidation.validate(user, result);
		
		if( !result.hasErrors() ){
			if(loginService.loginServ(user)){
				//model.addAttribute("loginStatus", "valid");
				return "redirect:/projects.do";
			}else{
				model.addAttribute("loginStatus", "invalid");
				//return "redirect:/login.do";
				return "login";
			}			
		}
		return "login";
	}
}