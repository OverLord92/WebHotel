package com.hotel.controllers;

import java.text.ParseException;
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
import com.hotel.utility.UtilitiMethods;

@Controller
public class AdminController {
	
	@Autowired
	UserDAO userDAO;
	
	@Autowired
	RoomDAO roomDAO;
	
	@Autowired
	RequestDAO requestDAO;

	/** Adds required model attributes and shows the admin page. */
	@RequestMapping("/admin")
	public String showAdminMenu(Model model){
		
		model.addAttribute("user", new User());
		System.out.println(1);
		model.addAttribute("requests", requestDAO.getAllRequests());
		System.out.println(2);
		model.addAttribute("users", userDAO.getAllUsers());
		
		return "admin";
	}
	
	/** Takes data from the user registration form, create a user and
	 * saves her/him to the database. */
	@RequestMapping(value="/registerUser", method=RequestMethod.POST)
	public String registerUser(User user, HttpServletRequest request) {
		
		// set users room and set the room to occupied
		int roomNumber = Integer.parseInt(request.getParameter("roomNumber"));   
		Room room = roomDAO.getRoom(roomNumber);
		room.setOccupied(true);
		
		user.setRoom(room);
		
		// Create an initial bill for the user
		Bill bill = new Bill();
		bill.setUsername(user.getUsername());
		bill.setRoomType(room.getRoomType());
		bill.setStartDate(new Date());
		
		// create a servies object for the user
		Services services = new Services();
		
		// set the bill and services properties based 
		// on the form information
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
		
		// set users services and add the bill to the user
		user.setServices(services);
		user.getBills().add(bill);
		
		// enable user
		user.setEnabled(true);
		
		// if the user is in the archive just update user object
		userDAO.createOrUpdateUser(user);
		
		return "redirect:/admin";
	}
	
	/** Handles admin approval of users roomChange requests */
	@RequestMapping("/approveRoomChange/{requestId}")
	public String changeUserRoom(@PathVariable Integer requestId){
	
		// get the request from the database
		UserRequest request = requestDAO.getRequest(requestId);
		
		User user = userDAO.getUser(request.getUsername());
		
		String requestedRoomType = request.getValue();
		Room oldRoom = user.getRoom();
		Room newRoom = roomDAO.getRoomOfCertainType(requestedRoomType);
		
		// if between the making of the request and approval all
		// rooms of the requested type got occupied don't make any changes
		if(newRoom == null)
			return "redirect:/admin";
		
		// if a room is available continue
		
		// end the last bill
		Bill oldBill = user.getLastBill();
		oldBill.setEndDate(new Date());
		
		int daysUsed = UtilitiMethods.getDateDiff(oldBill.getStartDate(), oldBill.getEndDate()) + 1;
		oldBill.setNumberOfDays(daysUsed);
		
		int total = oldBill.calculateTotalForThisBill();
		oldBill.setTotal(total);
		
		// add the total amount of the last bill to users totalAmountToPay
		user.addToTotalAmount(total);
							
		// free users old room
		oldRoom.setOccupied(false);
		roomDAO.updateRoom(oldRoom);
		oldRoom = null;
			
		// occupy new room
		newRoom.setOccupied(true);
		roomDAO.updateRoom(newRoom);
		user.setRoom(newRoom);
		newRoom.setOccupied(true);
			
		// delete the users request after room change
		requestDAO.deleteRequest(request);
		
		// create a new bill by copying the old one and
		// change the room type
		Bill newBill = oldBill.copyBill();
		newBill.setRoomType(newRoom.getRoomType());
		newBill.setStartDate(new Date());
		// add new bill to user
		user.getBills().add(newBill);
		
		userDAO.updateUser(user);
		
		return "redirect:/admin";
	}
	
	/** Handles admin approval of users roomChange requests */
	@RequestMapping("/approveServiceChange/{requestId}")
	public String changeUserService(@PathVariable Integer requestId){
	
		UserRequest request = requestDAO.getRequest(requestId);

		String username = request.getUsername();
		User user = userDAO.getUser(username);
		Room room = user.getRoom();
		
		// end last bill
		Bill oldBill = user.getLastBill();
		oldBill.setEndDate(new Date());
		// set the quantity (how much days the user used the room and/or services
		int daysUsed = UtilitiMethods.getDateDiff(oldBill.getStartDate(), oldBill.getEndDate()) + 1;
		oldBill.setNumberOfDays(daysUsed);
		// calculate the bill total
		int total = oldBill.calculateTotalForThisBill();
		oldBill.setTotal(total);
		
		// add the bill total to users amountToPay
		user.addToTotalAmount(total);
							
		// create a new bill
		Bill newBill = new Bill();
		newBill.setUsername(user.getUsername());
		newBill.setRoomType(room.getRoomType());
		newBill.setStartDate(new Date());
		
		Services services = user.getServices();
		
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
		
		user.setServices(services);
		user.getBills().add(newBill);
		userDAO.updateUser(user);
		requestDAO.deleteRequest(request);
		
		return "redirect:/admin";
	}
	
	/** Handles user request for logout */
	@RequestMapping("/approveLogOut/{requestId}")
	public String logOutUser(@PathVariable Integer requestId){
		UserRequest request = requestDAO.getRequest(requestId);
		
		String username = request.getUsername();
		User user = userDAO.getUser(username);
		
		// end last bill
		Bill lastBill = user.getLastBill();
		lastBill.setEndDate(new Date());
		user.addToTotalAmount(lastBill.calculateTotalForThisBill());
		
		// free room
		Room room = user.getRoom();
		room.setOccupied(false);
		roomDAO.updateRoom(room);
		
		// update user
		user.setRoom(null);
		userDAO.updateUser(user);	
		
		// update the request type
		request.setType(UserRequest.CHARGE_REQUEST);
		request.setValue(user.getTotalAmountToPay() + "");
		requestDAO.updateRequest(request);
		
		return "redirect:/admin";
	}
	
	/** Handles user request for paying */
	@RequestMapping("/charge/{requestId}")
	public String chargeAndDisableUser(@PathVariable Integer requestId){
		UserRequest request = requestDAO.getRequest(requestId);
		
		String username = request.getUsername();
		User user = userDAO.getUser(username);
		
		user.setTotalAmountToPay(0);
		user.setEnabled(false);
		userDAO.updateUser(user);
		requestDAO.deleteRequest(request);
		return "redirect:/admin";
	}
	
	/** Enables a user */
	@RequestMapping("/enableUser/{username}")
	public String enableUser(@PathVariable String username){
		
		User user = userDAO.getUser(username);
		user.setEnabled(true);
		
		userDAO.updateUser(user);
		
		return "redirect:/admin";
	}
	
	/** Disables a user */
	@RequestMapping("/disableUser/{username}")
	public String disableUser(@PathVariable String username){
		
		User user = userDAO.getUser(username);
		
		// user cannot be disabled if his/her room isn't null
		if(user.getRoom() == null){
			user.setEnabled(false);
			userDAO.updateUser(user);
		}
		
		return "redirect:/admin";
	}
	
	
	/** Called using AJAX to check if the user is in the archive */
	@RequestMapping(value = "checkIfUserExists", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody Map<String, Object> checkIfUserExistsInTheDatabase(@RequestBody Map<String, Object> data) throws ParseException {

		String idNumber = (String) data.get("idNumber");

		User user = userDAO.getUserById(idNumber);

		Map<String, Object> response = new HashMap<String, Object>();
		response.put("user", user);
		return response;
	}
	
	/** Called using AJAX to search the database for user in the database */
	@RequestMapping(value = "searchUsers", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody Map<String, Object> searchUsersInDatabase(@RequestBody Map<String, Object> data){

		String username = (String)data.get("username");
		String idNumber = (String)data.get("idNumber");
		
		List<User> filteredUsers = userDAO.searchUsers(username, idNumber);
		
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("users", filteredUsers);
		
		return response;
	}
	
}
