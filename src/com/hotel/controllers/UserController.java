package com.hotel.controllers;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

	/** Used to present the account page. */
	@RequestMapping("/account")
	public String showUserAcccount(Model model, Principal principal) {

		User user = userDAO.getUser(principal.getName());
		model.addAttribute("user", user);

		return "account";
	}

	/** Creates a UserRequest for roomChange. Saves it to the database and hands it over
	 * to the admin for approval. */
	@RequestMapping(value = "/requestRoomChange", method = RequestMethod.POST)
	public String requestRoomChange(HttpServletRequest servletRequest, Principal principal) {

		String username = principal.getName();
		String roomType = servletRequest.getParameter("roomType");

		// check if there is a pending user request for roomChange
		UserRequest request = requestDAO.doesRoomChangeRequestAllreadyExists(username);

		// if there isn't a pending one create a new request
		if (request == null) 
			request = new UserRequest();
		

		request.setUsername(username);
		request.setType(UserRequest.CHANGE_ROOM_TYPE_REQUEST);
		request.setValue(roomType);

		// if there was a pending request for services update it
		// if there wasn't a request, save it to the database 
		requestDAO.createOrUpdateRequest(request);

		return "redirect:/account";
	}

	/** Creates a UserRequest for servicesChange. Saves it to the database and hands it over
	 * to the admin for approval. */
	@RequestMapping(value = "/requestServiceChange", method = RequestMethod.POST)
	public String requestServiceChange(HttpServletRequest servletRequest, Principal principal) {

		String username = principal.getName();

		// check if there is a pending user request for serviceChange
		UserRequest request = requestDAO.doesServiceChangeRequestAllreadyExists(username);

		// if there isn't a pending one create a new request
		if (request == null) 
			request = new UserRequest();
		

		request.setUsername(username);
		request.setType(UserRequest.CHANGE_SERVICES_REQUEST);

		// set the requested services 
		String gym = servletRequest.getParameter("gym");
		request.setGym(gym != null ? true : false);

		String cinema = servletRequest.getParameter("cinema");
		request.setCinema(cinema != null ? true : false);

		String restaurant = servletRequest.getParameter("restaurant");
		request.setRestaurant(restaurant != null ? true : false);

		String pool = servletRequest.getParameter("pool");
		request.setPool(pool != null ? true : false);

		String sauna = servletRequest.getParameter("sauna");
		request.setSauna(sauna != null ? true : false);

		// if there was a pending request for services update it
		// if there wasn't a request, save it to the database 
		requestDAO.createOrUpdateRequest(request);

		return "redirect:/account";
	}

	/** Creates a UserRequest for logout. Saves it to the database and hands it over
	 * to the admin for approval. */
	@RequestMapping(value = "/requestLogOut", method = RequestMethod.POST)
	public String requestLogOut(Principal principal) {

		String username = principal.getName();
		
		// check if there is a pending user request for logout
		UserRequest request = requestDAO.doesLogOutRequestAllreadyExists(username);
	
		// if there isn't a pending one create a new request
		if (request == null) 
			request = new UserRequest();
		
		
		request.setUsername(username);
		request.setType(UserRequest.LOGOUT_REQUEST);
		
		// if there was a pending request for logout update it
		// if there wasn't a request, save it to the database 
		requestDAO.createOrUpdateRequest(request);

		return "redirect:/account";
	}

}
