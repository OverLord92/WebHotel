package com.hotel.dao;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
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

	public void createUser(User user) {
		String passEncoded = encoder.encode(user.getPassword());
		user.setEncodedPassword(passEncoded);
		user.setCheckInDate(new Date());
		session().save(user);
	}

	public User getUser(String username) {
		return (User) session().get(User.class, username);
	}

	public User getUserById(String idNumber) {
		Criteria criteria = session().createCriteria(User.class);
		criteria.add(Restrictions.eq("idNumber", idNumber));

		return (User) criteria.uniqueResult();
	}

	public void updateUser(User user) {
		session().update(user);
	}

	@SuppressWarnings("unchecked")
	public List<User> getAllUsers() {
		Criteria criteria = session().createCriteria(User.class);
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	public List<User> searchUsers(String username, String idNumber) {
		Criteria criteria = session().createCriteria(User.class);
		criteria.add(Restrictions.like("username", username, MatchMode.ANYWHERE));

		if (idNumber != "") {
			 criteria.add(Restrictions.eq("idNumber", idNumber));
		}

		System.out.println(criteria.list().size());

		return criteria.list();
	}
}
