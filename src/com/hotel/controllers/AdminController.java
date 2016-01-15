package com.hotel.controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hotel.beans.Bill;
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
		model.addAttribute("users", userDAO.getAllUsers());
		return "admin";
	}
	
	@RequestMapping(value="/registerUser", method=RequestMethod.POST)
	public String registerUser(User user, HttpServletRequest request) {
		
		int roomNumber = Integer.parseInt(request.getParameter("roomNumber"));   
		Room room = roomDAO.getRoom(roomNumber);
		user.setRoom(room);
		room.setOccupied(true);
		
		Services services = new Services();
		Bill bill = new Bill();
		bill.setUsername(user.getUsername());
		bill.setRoomType(room.getRoomType());
		bill.setStartDate(new Date());
		
		String gym = request.getParameter("gym");
		if(gym != null) {
			services.setGym(true);
			bill.setGym(true);
		}
		
		String cinema = request.getParameter("cinema");
		if(cinema != null) {
			services.setCinema(true);
			bill.setCinema(true);
		}
		
		String restaurant = request.getParameter("restaurant");
		if(restaurant != null) {
			services.setRestaurant(true);
			bill.setRestaurant(true);
		}
		
		String pool = request.getParameter("pool");
		if(pool != null) {
			services.setPool(true);
			bill.setPool(true);
		}
		
		String sauna = request.getParameter("sauna");
		if(sauna != null) {
			services.setSauna(true);
			bill.setSauna(true);
		}
		
		System.out.println(services);
		user.setServices(services);
		user.getBills().add(bill);
		user.setEnabled(true);
		userDAO.createUser(user);
		
		return "admin";  //// kad stavim da vraca "admin" nor binding or plain target....
	}
	
	@RequestMapping("/approveRoomChange/{requestId}")
	public String changeUserRoom(@PathVariable Integer requestId){
	
		System.out.println(requestId);
		UserRequest request = requestDAO.getRequest(requestId);

		String username = request.getUsername();
		User user = userDAO.getUser(username);
		
		
		// prekini prosli racun
		Bill oldBill = user.getLastBill();
		oldBill.setEndDate(new Date());
							
		String requestedRoomType = request.getValue();
		
		// promjeni sobu
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
			newRoom.setOccupied(true);
			
			requestDAO.deleteRequest(request);
		}
		
		// napravi novi racun sa izmjenama
		Bill newBill = oldBill.copyBill();
		newBill.setRoomType(newRoom.getRoomType());
		newBill.setStartDate(new Date());
		user.getBills().add(newBill);
		
		userDAO.updateUser(user);
		
		return "home";
	}
	
	@RequestMapping("/approveServiceChange/{requestId}")
	public String changeUserService(@PathVariable Integer requestId){
	
		System.out.println(requestId);
		UserRequest request = requestDAO.getRequest(requestId);

		String username = request.getUsername();
		User user = userDAO.getUser(username);
		Room room = user.getRoom();
		
		// prekini prosli racun
		Bill oldBill = user.getLastBill();
		oldBill.setEndDate(new Date());
							
		Services services = user.getServices();
		Bill newBill = new Bill();
		newBill.setUsername(user.getUsername());
		newBill.setRoomType(room.getRoomType());
		newBill.setStartDate(new Date());
		
		
		if(request.isGym()) {
			services.setGym(true);
			newBill.setGym(true);
		} else {
			services.setGym(false);
		}
		
		if(request.isCinema()) {
			services.setCinema(true);
			newBill.setCinema(true);
		} else {
			services.setCinema(false);
		}
		
		if(request.isRestaurant()) {
			services.setRestaurant(true);
			newBill.setRestaurant(true);
		} else {
			services.setRestaurant(false);
		}
		
		if(request.isPool()) {
			services.setPool(true);
			newBill.setPool(true);
		} else {
			services.setPool(false);
		}
	
		if(request.isSauna()) {
			services.setSauna(true);
			newBill.setSauna(true);
		} else {
			services.setSauna(false);
		}
		
		System.out.println(services);
		user.setServices(services);
		user.getBills().add(newBill);
		userDAO.updateUser(user);
		requestDAO.deleteRequest(request);
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
	
	@RequestMapping(value = "searchUsers", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody Map<String, Object> addPostit(@RequestBody Map<String, Object> data){

		String username = (String)data.get("username");
		String idNumber = (String)data.get("idNumber");
		
		List<User> filteredUsers = userDAO.searchUsers(username, idNumber);
		
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("users", filteredUsers);
		return response;
	}
	
}
