package com.hotel.beans;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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

	private String username;
	
	
	@Column(columnDefinition="TINYINT(1)")
	private boolean gym;
	@Column(columnDefinition="TINYINT(1)")
	private boolean cinema;
	@Column(columnDefinition="TINYINT(1)")
	private boolean restaurant;
	@Column(columnDefinition="TINYINT(1)")
	private boolean pool;
	@Column(columnDefinition="TINYINT(1)")
	private boolean sauna;

	
	
	
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	
	
	public boolean isGym() {
		return gym;
	}

	public void setGym(boolean gym) {
		this.gym = gym;
	}

	public boolean isCinema() {
		return cinema;
	}

	public void setCinema(boolean cinema) {
		this.cinema = cinema;
	}

	public boolean isRestaurant() {
		return restaurant;
	}

	public void setRestaurant(boolean restaurant) {
		this.restaurant = restaurant;
	}

	public boolean isPool() {
		return pool;
	}

	public void setPool(boolean pool) {
		this.pool = pool;
	}

	public boolean isSauna() {
		return sauna;
	}

	public void setSauna(boolean sauna) {
		this.sauna = sauna;
	}

	@Override
	public String toString() {
		return "UserRequest [id=" + id + ", type=" + type + ", value=" + value + ", username=" + username + "]";
	}
	
	

}
