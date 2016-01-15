package com.hotel.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
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
	
	public void updateRequest(UserRequest request){
		session().update(request);
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
	
	/** Provjeri da li je korisnik vec trazio mjenjanje tipa sobe */
	public UserRequest doesRoomChangeRequestAllreadyExists(String username){
		Criteria criteria = session().createCriteria(UserRequest.class);
		criteria.add(Restrictions.eq("username", username));
		criteria.add(Restrictions.eq("type", "roomChange"));
		
		return (UserRequest)criteria.uniqueResult();
	}
	
	/** provjeri da li je korisnik vec zahtjevao mjenjanje servisa */
	public UserRequest doesServiceChangeRequestAllreadyExists(String username){
		Criteria criteria = session().createCriteria(UserRequest.class);
		criteria.add(Restrictions.eq("username", username));
		criteria.add(Restrictions.eq("type", "serviceChange"));
		
		return (UserRequest)criteria.uniqueResult();
	}

	public void createOrUpdateRequest(UserRequest request) {
		session().saveOrUpdate(request);
	}
	
}
