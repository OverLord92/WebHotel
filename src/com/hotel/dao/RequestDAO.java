package com.hotel.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotel.beans.UserRequest;

@Transactional
@Service
public class RequestDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	public Session session(){
		return sessionFactory.getCurrentSession();
	}
	
	public void createRequest(UserRequest request){
		session().save(request);
	}
	
	@SuppressWarnings("unchecked")
	public List<UserRequest> getAllRequests(){
		Criteria criteria = session().createCriteria(UserRequest.class);
		return criteria.list();
	}

	public UserRequest getRequest(int requestId) {
		return (UserRequest)session().get(UserRequest.class, requestId);
	}

	public void deleteRequest(UserRequest request) {
		session().delete(request);
	}
	
}
