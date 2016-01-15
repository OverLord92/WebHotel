package com.hotel.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hotel.beans.Room;
import com.hotel.beans.Services;
import com.hotel.beans.User;
import com.hotel.beans.UserRequest;
import com.hotel.dao.RequestDAO;
import com.hotel.dao.RoomDAO;
import com.hotel.dao.UserDAO;

@Controller
public class AdminController {
	
	@Autowired
	UserDAO userDAO;
	
	@Autowired
	RoomDAO roomDAO;
	
	@Autowired
	RequestDAO requestDAO;

	@RequestMapping("/admin")
	public String showAdminMenu(Model model){
		model.addAttribute("user", new User());
		model.addAttribute("requests", requestDAO.getAllRequests());
		model.addAttribute("users", userDAO.getAllUSers());
		return "admin";
	}
	
	@RequestMapping(value="/registerUser", method=RequestMethod.POST)
	public String registerUser(User user, HttpServletRequest request) {
		
		int roomNumber = Integer.parseInt(request.getParameter("roomNumber"));   
		Room room = roomDAO.getRoom(roomNumber);
		user.setRoom(room);
		
		Services services = new Services();
		
		String gym = request.getParameter("gym");
		if(gym != null) services.setGym(true);
		
		String cinema = request.getParameter("cinema");
		if(cinema != null) services.setCinema(true);
		
		String restaurant = request.getParameter("restaurant");
		if(restaurant != null) services.setRestaurant(true);
		
		String pool = request.getParameter("pool");
		if(pool != null) services.setPool(true);
		
		String sauna = request.getParameter("sauna");
		if(sauna != null) services.setSauna(true);
		
		System.out.println(services);
		user.setServices(services);
		
		userDAO.createUser(user);
		
		return "admin";  //// kad stavim da vraca "admin" nor binding or plain target....
	}
	
	@RequestMapping("/approveRoomChange/{requestId}")
	public String changeUserRoom(@PathVariable Integer requestId){
		System.out.println(requestId);
		UserRequest request = requestDAO.getRequest(requestId);

		String username = request.getUsername();
		User user = userDAO.getUser(username);
		
		String requestedRoomType = request.getValue();
		
		Room oldRoom = user.getRoom();
		System.out.println("old room" + oldRoom);
		Room newRoom = roomDAO.getRoomOfCertainType(requestedRoomType);
		
		if(newRoom !=null) {
			oldRoom.setOccupied(false);
			roomDAO.updateRoom(oldRoom);
			oldRoom = null;
			
			newRoom.setOccupied(true);
			roomDAO.updateRoom(newRoom);
			user.setRoom(newRoom);
			
			userDAO.updateUser(user);
			requestDAO.deleteRequest(request);
		}
		
		return "home";
	}
	
	@RequestMapping("/enableUser/{username}")
	public String enableUser(@PathVariable String username){
		
		User user = userDAO.getUser(username);
		
		System.out.println(user);
	
		user.setEnabled(true);
		
		userDAO.updateUser(user);
		return "home";
	}
	
	@RequestMapping("/disableUser/{username}")
	public String disableUser(@PathVariable String username){
		
		User user = userDAO.getUser(username);
		
		System.out.println(user);
		if(user.getRoom() == null){
			user.setEnabled(false);
			userDAO.updateUser(user);
		}
		
		return "home";
	}
	
	@RequestMapping("/home")
	public String goHome(){
		return "home";
	}
}
