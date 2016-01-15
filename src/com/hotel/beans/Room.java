package com.hotel.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Room {
	
	@Id
	private int roomNumber;
	
	private String roomType;
	
	@Column(columnDefinition="TINYINT(1)")
	private boolean occupied;
	
	private int roomPrice;
	
	

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
		return "Room [roomNumber=" + roomNumber + ", roomType=" + roomType + ", occupied=" + occupied + ", roomPrice="
				+ roomPrice + "]";
	}
	
	
	
}
