package com.hotel.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginAndRegisterController {
	
	@RequestMapping("/register")
	public String registerUser(){
		return "register";
	}

}
