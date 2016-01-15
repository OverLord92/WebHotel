package com.hotel.controllers;

import java.security.Principal;
import java.text.ParseException;
import java.util.Date;
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

import com.hotel.beans.Bill;
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
	public String showUserAcccount(Model model, Principal principal){
		
		User user = userDAO.getUser(principal.getName());
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
	public String requestRoomChange(HttpServletRequest servletRequest, Principal principal){
		
		String roomType = servletRequest.getParameter("roomType");
		
		UserRequest request = new UserRequest();
		request.setUsername(principal.getName());
		request.setType("roomChange");
		request.setValue(roomType);
		requestDAO.createRequest(request);
		
		return "account";
	}
	
	@RequestMapping(value="/requestServiceChange", method=RequestMethod.POST)
	public String requestServiceChange(HttpServletRequest servletRequest, Principal principal){
		
		UserRequest request = new UserRequest();
		
		String username = principal.getName();
		request.setUsername(username);
		request.setType("serviceChange");
		
		String gym = servletRequest.getParameter("gym");
		request.setGym(gym != null?true:false);
		
		String cinema = servletRequest.getParameter("cinema");
		request.setCinema(cinema != null?true:false);
		
		String restaurant = servletRequest.getParameter("restaurant");
		request.setRestaurant(restaurant != null?true:false);
		
		String pool = servletRequest.getParameter("pool");
		request.setPool(pool != null?true:false);
		
		String sauna = servletRequest.getParameter("sauna");
		request.setSauna(sauna != null?true:false);
		
		requestDAO.createRequest(request);
		
		return "account";
	}

}
