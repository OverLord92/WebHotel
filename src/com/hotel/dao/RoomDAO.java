package com.hotel.dao;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotel.beans.Room;

@Transactional
@Service 
public class RoomDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	public Session session(){
		return sessionFactory.getCurrentSession();
	}
	
	public Room getRoomOfCertainType(String roomType){
		
		Criteria criteria = session().createCriteria(Room.class);
		criteria.add(Restrictions.eq("occupied", false));
		criteria.add(Restrictions.eq("roomType", roomType));
	
		Room room = (Room)criteria.list().get(0); // treba hendlati nullpointer
	
		System.out.println(room);
		
		if(room != null) {
			return room;
		} else {
			return null;
		}
	}

	public Room getRoom(int roomNumber) {
		return (Room)session().get(Room.class, roomNumber);
	}

	public void updateRoom(Room room) {
		session().update(room);
	}
}
