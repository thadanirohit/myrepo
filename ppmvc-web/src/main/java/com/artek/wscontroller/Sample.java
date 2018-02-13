package com.artek.wscontroller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.artek.model.UserWS;

@Controller
public class Sample {
	
	@RequestMapping("/testinggg")
	public String getTesting(){
		return "jsontest";
	}
	
	//@RequestMapping(value="/user/{id}", method=RequestMethod.GET, produces="application/json", headers="Accept=application/json" )
	@RequestMapping(value="/user/{id}", method=RequestMethod.GET, headers="Accept=application/json" )
	public @ResponseBody UserWS getUser( @PathVariable String id ){
		
		System.out.println("inside sample webservices controller");
		
		UserWS userws = new UserWS();
		userws.setId( Integer.valueOf(id));
		userws.setName("Rohit Thadani");
		userws.setAddress("Indore");
		
		return userws;
	}
	
	@RequestMapping(value="/user", method=RequestMethod.POST)
	public @ResponseBody void saveUser(@RequestBody UserWS user){
		System.out.println(user.getId());
		System.out.println(user.getName());
		System.out.println(user.getAddress());
	}
	
}