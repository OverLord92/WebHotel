package com.hotel.controllers;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hotel.beans.User;
import com.hotel.beans.UserRequest;
import com.hotel.dao.RequestDAO;
import com.hotel.dao.UserDAO;

@Controller
public class UserController {
	
	@Autowired
	UserDAO userDAO;
	
	@Autowired
	RequestDAO requestDAO;
	
	
	@RequestMapping("/account")
	public String showUserAcccount(Model model){ //Principal principal){ kad se sredi security ide ovo
		User user = userDAO.getUser("senjin");
		
		model.addAttribute("user", user);
		
		return "account";
	}
	
	@RequestMapping(value = "checkIfUserExists", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody Map<String, Object> addPostit(@RequestBody Map<String, Object> data)
			throws ParseException {

		System.out.println("pozvana kontroler metoda");
		String idNumber = (String)data.get("idNumber");
		
		User user = userDAO.getUserById(idNumber);
		
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("user", user);
		return response;
	}
	
	@RequestMapping(value="/requestRoomChange", method=RequestMethod.POST)
	public String requestRoomChange(HttpServletRequest servletRequest){  //Principal principal){ kad sredim security koristicu principal
		// cu hardkovati
		
		//String username = principal.getName();
		String roomType = servletRequest.getParameter("roomType");
		
		UserRequest request = new UserRequest();
		request.setUsername("senjin");
		request.setType("roomChange");
		request.setValue(roomType);
		requestDAO.createRequest(request);
		
		return "account";
	}

}
