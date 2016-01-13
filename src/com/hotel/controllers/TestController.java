package com.hotel.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hotel.dao.UserDAO;

@Controller
public class TestController {
	
	@Autowired
	UserDAO userDAO;
	
	@Autowired 
	PasswordEncoder encoder;

	@RequestMapping("/test")
	public String testMwc(){
		
		return "test";
	}
	
	@RequestMapping("/home")
	public String goHome(){
		return "home";
	}
	
}
