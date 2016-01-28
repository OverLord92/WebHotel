package com.hotel.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hotel.beans.UserRequest;

@Transactional
@Repository
public class RequestDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	public Session session(){
		return sessionFactory.getCurrentSession();
	}
	
	/** Saves the UserRequest object to the database */
	public void saveRequest(UserRequest request){
		session().save(request);
	}
	
	/** Gets a UserRequest object with certain id from database */
	public UserRequest getRequest(int requestId) {
		return (UserRequest)session().get(UserRequest.class, requestId);
	}
	
	/** Updates the UserRequest object in the database */
	public void updateRequest(UserRequest request){
		session().update(request);
	}
	
	/** Deletes the UserRequest object from the database */
	public void deleteRequest(UserRequest request) {
		session().delete(request);
	}
	
	/** Returns a list with all UserRequest objects from the database */
	@SuppressWarnings("unchecked")
	public List<UserRequest> getAllRequests(){
		Criteria criteria = session().createCriteria(UserRequest.class);
		return criteria.list();
	}

	
	/** Returns a users roomChange UserRequest from the database.
	 * Returns null if no roomChange UserRequest of the user is present.  */
	public UserRequest doesRoomChangeRequestAllreadyExists(String username){
		
		Criteria criteria = session().createCriteria(UserRequest.class);
		criteria.add(Restrictions.eq("username", username));
		criteria.add(Restrictions.eq("type", "roomChange"));
		
		return (UserRequest)criteria.uniqueResult();
	}
	
	/** Returns a users serviceChange UserRequest from the database.
	 * Returns null if no serviceChange UserRequest of the user is present.  */
	public UserRequest doesServiceChangeRequestAllreadyExists(String username){
		Criteria criteria = session().createCriteria(UserRequest.class);
		criteria.add(Restrictions.eq("username", username));
		criteria.add(Restrictions.eq("type", "serviceChange"));
		
		return (UserRequest)criteria.uniqueResult();
	}

	/** Returns a users logOut UserRequest from the database.
	 * Returns null if no logOut UserRequest of the user is present.  */
	public UserRequest doesLogOutRequestAllreadyExists(String username) {
		Criteria criteria = session().createCriteria(UserRequest.class);
		criteria.add(Restrictions.eq("username", username));
		criteria.add(Restrictions.eq("type", "logOut"));
		
		return (UserRequest)criteria.uniqueResult();
	}
	
	public void createOrUpdateRequest(UserRequest request) {
		session().saveOrUpdate(request);
	}

}
