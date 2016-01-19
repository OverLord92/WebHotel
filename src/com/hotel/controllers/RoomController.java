package com.hotel.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hotel.beans.Room;
import com.hotel.dao.RoomDAO;

@Controller
public class RoomController {

	@Autowired
	RoomDAO roomDAO;
	
	/** Called with AJAX to check if a room of certain type is available */
	@RequestMapping(value = "checkIfFreeRoomExists", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody Map<String, Object> addPostit(@RequestBody Map<String, Object> data){

		String roomType = (String)data.get("roomType");
		System.out.println("pozvana kontroler metoda" + roomType);
		
		Room room = roomDAO.getRoomOfCertainType(roomType);
		
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("room", room);
		return response;
	}
	
}
