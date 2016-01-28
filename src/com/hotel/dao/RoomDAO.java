package com.hotel.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hotel.beans.Room;

@Transactional
@Repository
public class RoomDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	public Session session(){
		return sessionFactory.getCurrentSession();
	}
	
	/** Retrieve the room with certain roomNuber from the database */
	public Room getRoom(int roomNumber) {
		return (Room)session().get(Room.class, roomNumber);
	}
	
	/** Update the room in the database */
	public void updateRoom(Room room) {
		session().update(room);
	}
	
	/** Return a non occupied room of certain type from the database */
	public Room getRoomOfCertainType(String roomType){
		
		Criteria criteria = session().createCriteria(Room.class);
		criteria.add(Restrictions.eq("occupied", false));
		criteria.add(Restrictions.eq("roomType", roomType));
	
		@SuppressWarnings("unchecked")
		List<Room> availableRooms = criteria.list();
		
		Room room = null;
		
		if(availableRooms != null && availableRooms.size() > 0)
			room = (Room)criteria.list().get(0); 
	
		return room;
	}

}
