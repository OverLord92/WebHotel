package com.hotel.dao;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.DistinctRootEntityResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hotel.beans.User;

@Transactional
@Service
public class UserDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public Session session() {
		return sessionFactory.getCurrentSession();
	}

	@Autowired
	private PasswordEncoder encoder;

	/** Saves a user to the database */
	public void createUser(User user) {
		
		// set encoded password
		String passEncoded = encoder.encode(user.getPassword());
		user.setEncodedPassword(passEncoded);
		
		// set check in Date
		user.setCheckInDate(new Date());
		session().save(user);
	}
	
	/** Updates the user in the database */
	public void updateUser(User user) {
		session().update(user);
	}
	
	/** If User is already in the database update,
	 * otherwise save a new user object to the database */
	public void createOrUpdateUser(User user) {
		String idNumber = user.getIdNumber();
		
		if(getUserById(idNumber) == null)
			createUser(user);
		else
			updateUser(user);
	}

	/** Returns a user with certain username from the database */
	public User getUser(String username) {
		return (User) session().get(User.class, username);
	}
	

	/** Return a user with certain idNumber */
	public User getUserById(String idNumber) {
		Criteria criteria = session().createCriteria(User.class);
		criteria.add(Restrictions.eq("idNumber", idNumber));

		return (User) criteria.uniqueResult();
	}


	/** Returns a list with all users from the database */
	@SuppressWarnings("unchecked")
	public List<User> getAllUsers() {
		Criteria criteria = session().createCriteria(User.class);
		
		// this line prevents criteria from returning instances multiple time
		// eager fetch causes this issue
		criteria.setResultTransformer(DistinctRootEntityResultTransformer.INSTANCE);
				
		return criteria.list();
	}

	/** Searches users based on username and idNumber */
	@SuppressWarnings("unchecked")
	public List<User> searchUsers(String username, String idNumber) {
		
		System.out.println("search metho called");
		
		// check if users username contains the forwarded username
		Criteria criteria = session().createCriteria(User.class);
		
		// this line prevents criteria from returning instances multiple time
		// eager fetch causes this issue
		criteria.setResultTransformer(DistinctRootEntityResultTransformer.INSTANCE);
		
		criteria.add(Restrictions.like("username", username, MatchMode.ANYWHERE));

		// if the idNumber is empty skip the idNumber restriction
		if (idNumber != "") {
			 criteria.add(Restrictions.eq("idNumber", idNumber));
		}

		
		return criteria.list();
	}
}
