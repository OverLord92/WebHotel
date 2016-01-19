package com.hotel.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user_requests")
public class UserRequest {
	
	public static final String CHANGE_ROOM_TYPE_REQUEST = "roomChange";
	public static final String CHANGE_SERVICES_REQUEST = "serviceChange";
	public static final String LOGOUT_REQUEST = "logOut";
	public static final String CHARGE_REQUEST = "charge";
	
	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	private int id;
	
	//type can be roomChange, serviceChange, logOut, charge
	private String type;
	
	// if the request type is roomChange - the value is the type of the requested room
	// if the request type is charge - the value is the amount the user has to pay
	private String value; 

	// the user who created the request
	private String username;
	
	// when the request is created set the services that are required
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

	
	
	// plain getters and setters
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
		return "UserRequest [id=" + id 
				+ ", type=" + type
				+ ", value=" + value 
				+ ", username=" + username + "]";
	}
	
	

}
