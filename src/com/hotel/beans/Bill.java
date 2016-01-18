package com.hotel.beans;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Bill {

	@Id @GeneratedValue(strategy=GenerationType.TABLE)
	private int id;
	
	private String username;
	private Date startDate;
	private Date endDate;
	
	// how long the services were used by the user
	private int numberOfDays;
	
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
	
	private String roomType;
	
	@Column(columnDefinition="TINYINT(1)")
	private boolean payed;
	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	public int getNumberOfDays() {
		return numberOfDays;
	}

	public void setNumberOfDays(int numberOfDays) {
		this.numberOfDays = numberOfDays;
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

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public boolean isPayed() {
		return payed;
	}

	public void setPayed(boolean payed) {
		this.payed = payed;
	}
	
	public Bill copyBill(){
		Bill copy = new Bill();
		copy.username = this.username;
		copy.gym = this.gym;
		copy.cinema = this.cinema;
		copy.restaurant = this.restaurant;
		copy.pool = this.pool;
		copy.sauna = this.sauna;
		
		return copy;
	}
	
	
	
}
