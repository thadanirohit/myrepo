package com.artek.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.artek.model.Address;
import com.artek.model.User;
import com.artek.service.LoginService;
import com.artek.service.RegistrationService;
import com.artek.validation.RegistrationValidator;

@Controller
public class RegistrationController {
	
	@Autowired
	private RegistrationService regService;
	
	@Autowired
	RegistrationValidator validator;
	
	@RequestMapping("/register")
	public String showRegisteration(Map<String,Object> map){
		map.put("user", new User());
		return "register";
	}
	
	@RequestMapping(value="/doRegister",method = RequestMethod.POST)
	public String doRegister(@ModelAttribute("user") User user, BindingResult result, ModelMap status){
	//public String doRegister( HttpServletRequest request, HttpServletResponse response, BindingResult result, @ModelAttribute("user") User user){	
		System.err.println("Inside doRegister of RegistrationController class");
		/*System.out.println(user.getEmailId());
		System.out.println(user.getPassword());*/
		
		validator.validate(user, result);
		
		if(result.hasErrors()){
			System.out.println("errors in page");
		}else{
			System.out.println("NO errors in page");
			
			System.out.println(((Address)user.getAddresses().get(0)).getPinCode());
			
			int id = regService.registerService(user);
			if( id != 0 ){
				if( id != -1 ){
					status.addAttribute("regStatus", "yes");
				}else{
					status.addAttribute("regStatus", "error");
				}
				
			}else{
				status.addAttribute("regStatus", "no");
			}
		}				
		
		/*model.addAttribute("uname", user.getEmailId());
		model.addAttribute("pass", user.getPassword());*/
		
		return "register";
		//return "loginresult";		
	}	
}
