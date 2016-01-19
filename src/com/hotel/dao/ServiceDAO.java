package com.hotel.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class ServiceDAO {  

	@Autowired
	private SessionFactory sessionFactory;
	
	public Session session(){
		return sessionFactory.getCurrentSession();
	}
	
	/** Saves a Services object to the database */
	public void createService(com.hotel.beans.Services service) {
		session().save(service);
	}
	
	/** Return a list of all Services objects from the database */
	@SuppressWarnings("unchecked")
	public List<com.hotel.beans.Services> getAllServices(){
		Criteria criteria = session().createCriteria(com.hotel.beans.Services.class);
		return criteria.list();
	}

	
	
}
