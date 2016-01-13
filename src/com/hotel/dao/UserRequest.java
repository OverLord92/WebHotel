package com.hotel.dao;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="user_requests")
public class UserRequest {
	
	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	private int id;
	
	//tip requesta moze biti changeRoomType, reserveService i signOut
	private String type;
	
	// ukoliko je request changeRoomType value moze biti novi tip sobe
	// ako je request reserveService onda je value naziv servisa koji
	// korisnik zeli rezervisati
	private String value; 
	
	@OneToOne(cascade=CascadeType.ALL)
	private User user;

	
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
}
