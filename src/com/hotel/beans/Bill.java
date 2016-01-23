package com.hotel.beans;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/** Every user has a list property with Bill objects.
 * 
 * During registration the user gets an inital bill, 
 * and then on every room or service change the admin
 * approves the last bill and creates a new one.
 * 
 * At the sign out of the user the admin approves the 
 * last bill and prepares them to charge the user. */
@Entity
public class Bill {
	
	// this constants are used to calculate bills
	public static final int GYM_PRICE = 10;
	public static final int CINEMA_PRICE = 10;
	public static final int RESTAURANT_PRICE = 20;
	public static final int POOL_PRICE = 10;
	public static final int SAUNA_PRICE = 10;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	// username of the user to with the user belongs
	private String username;
	
	// start and end date properties used to calculate
	// the duration of the usage of the room and services
	private Date startDate;
	private Date endDate;
	
	// how long the room and services were used by the user
	private int numberOfDays;
	
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
	
	// the room type used to calculate the cost of the room
	private String roomType;
	
	// the total amount for this Bill object
	private int total;
	
	@Column(columnDefinition="TINYINT(1)")
	private boolean payed;
	
	

	// plain getters and setters
	public static int getGymPrice() {
		return GYM_PRICE;
	}

	public static int getCinemaPrice() {
		return CINEMA_PRICE;
	}

	public static int getRestaurantPrice() {
		return RESTAURANT_PRICE;
	}

	public static int getPoolPrice() {
		return POOL_PRICE;
	}

	public static int getSaunaPrice() {
		return SAUNA_PRICE;
	}

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
	
	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public boolean isPayed() {
		return payed;
	}

	public void setPayed(boolean payed) {
		this.payed = payed;
	}
	
	
	/** Calculate the total amount of a Bill object */
	public int calculateTotalForThisBill(){
		int total = 0;
		
		// add cost of all used services
		if(gym)
		total += numberOfDays * GYM_PRICE;
		if(cinema)
			total += numberOfDays * CINEMA_PRICE;
		if(restaurant)
			total += numberOfDays * RESTAURANT_PRICE;
		if(pool)
			total += numberOfDays * POOL_PRICE;
		if(sauna)
			total += numberOfDays * SAUNA_PRICE;
		
		// add the cost of the room 
		if(roomType.equals(Room.ONE_BED)){
			total += numberOfDays * Room.ONE_BED_PRICE;
		}else if(roomType.equals(Room.TWO_BED)){
			total += numberOfDays * Room.TWO_BED_PRICE;
		}else if(roomType.equals(Room.APARTMENT)){
			total += numberOfDays * Room.APARTMENT_PRICE;
		}
		
		
		return total;
	}
	
	/** Copy a bill object. Useful during change of room.
	 * Use this method instead of setting all service property
	 * values of the Bill object. */
	public Bill copyBill(){
		Bill copy = new Bill();
		copy.setTotal(0);
		
		copy.username = this.username;
		copy.gym = this.gym;
		copy.cinema = this.cinema;
		copy.restaurant = this.restaurant;
		copy.pool = this.pool;
		copy.sauna = this.sauna;
		
		return copy;
	}
	
	
	
}
