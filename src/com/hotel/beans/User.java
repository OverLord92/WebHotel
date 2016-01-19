package com.hotel.beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
public class User {
	
	@Id
	private String username;
	
	// holds the unencrypted password
	@Transient
	private String password; 
	
	@Column(name="password")   
	private String encodedPassword;
	
	private String name;
	private String lastName;
	private String idNumber;
	private Date dob;
	private String gender;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="roomNumber")
	private Room room;
	
	@OneToOne(cascade=CascadeType.ALL)
	private Services services;
	
	@Temporal(TemporalType.DATE)
	private Date checkInDate;
	
	@Column(columnDefinition="TINYINT(1)")
	private boolean online; 
	
	@Column(columnDefinition="TINYINT(1)")
	private boolean enabled;
	private String authority;
	
	@OneToMany(mappedBy="username", fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	private List<Bill> bills = new ArrayList<>();
	
	private int totalAmountToPay;
	
	
	// plain getters and setters
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEncodedPassword() {
		return encodedPassword;
	}

	public void setEncodedPassword(String encodedPassword) {
		this.encodedPassword = encodedPassword;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public Services getServices() {
		return services;
	}

	public void setServices(Services services) {
		this.services = services;
	}

	public Date getCheckInDate() {
		return checkInDate;
	}

	public void setCheckInDate(Date checkInDate) {
		this.checkInDate = checkInDate;
	}

	public boolean isOnline() {
		return online;
	}

	public void setOnline(boolean online) {
		this.online = online;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}
	
	public List<Bill> getBills() {
		return bills;
	}

	public void setBills(List<Bill> bills) {
		this.bills = bills;
	}

	public int getTotalAmountToPay() {
		return totalAmountToPay;
	}

	public void setTotalAmountToPay(int totalAmountToPay) {
		this.totalAmountToPay = totalAmountToPay;
	}

	public Bill getLastBill(){
		return this.bills.get(bills.size() - 1);
	}

	/** After admin approves a bill this method adds  the 
	 * total amount of the bill to the users total */
	public void addToTotalAmount(int amount) {
		totalAmountToPay += amount;
	}
	
	
	@Override
	public String toString() {
		return "User [username=" + username 
				+ ", password=" + password 
				+ ", encodedPassword=" + encodedPassword
				+ ", name=" + name 
				+ ", lastName=" + lastName 
				+ ", idNumber=" + idNumber 
				+ ", dob=" + dob
				+ ", gender=" + gender 
				+ ", room=" + room 
				+ ", checkInDate=" + checkInDate
				+ ", online=" + online 
				+ ", enabled=" + enabled 
				+ ", authority=" + authority + "]";
	}
	
	
	
	
}
