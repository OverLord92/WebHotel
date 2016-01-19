package com.hotel.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**  
 * Every User has a Services property to represent the services
 * which the user uses.
 * */
@Entity
public class Services {
	
	@Id @GeneratedValue(strategy=GenerationType.TABLE)
	private int id;
	
	// this constants are used to calculate bills
	public static final int GYM_PRICE = 10;
	public static final int CINEMA_PRICE = 10;
	public static final int RESTAURANT_PRICE = 20;
	public static final int POOL_PRICE = 10;
	public static final int SAUNA_PRICE = 10;
	
	// if gym is true the user uses the gym,
	// if cinema is true the user uses the cineman 
	// and so on
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
		return "Services [id=" + id
				+ ", gym=" + gym 
				+ ", cinema=" + cinema 
				+ ", restaurant=" + restaurant 
				+ ", pool=" + pool 
				+ ", sauna=" + sauna + "]";
	}
}
