package com.hotel.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Room {
	
	// constant room type names
	public static final String ONE_BED = "one";
	public static final String TWO_BED = "two";
	public static final String APARTMENT = "apartment";
	
	// constant room prices
	public static final int ONE_BED_PRICE = 20;
	public static final int TWO_BED_PRICE = 40;
	public static final int APARTMENT_PRICE = 60;
	
	@Id
	private int roomNumber;
	
	private String roomType;
	
	@Column(columnDefinition="TINYINT(1)")
	private boolean occupied;
	
	private int roomPrice;
	
	
	// plain getter and setters
	public int getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public boolean isOccupied() {
		return occupied;
	}

	public void setOccupied(boolean occupied) {
		this.occupied = occupied;
	}

	public int getRoomPrice() {
		return roomPrice;
	}

	public void setRoomPrice(int roomPrice) {
		this.roomPrice = roomPrice;
	}

	
	@Override
	public String toString() {
		return "Room [roomNumber=" + roomNumber + 
				", roomType=" + roomType + 
				", occupied=" + occupied + ", "
				+ "roomPrice=" + roomPrice + "]";
	}
	
	
	
}
