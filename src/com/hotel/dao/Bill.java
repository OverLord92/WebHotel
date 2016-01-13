package com.hotel.dao;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


/** Ovo je klasa koju je Ognjen zamislio kao Nalog (engleski ORDER)
 * Patio sam se 2 sata, i mislio zasto nece da kreira order tabelu
 * zasto mi izbacuje sql syntax error i onda sam se sjetio da je ORDER rezervisana rijec
 * zato se ova klasa zove Bill, onda se krajnji racun moze zvati totalBill ili tako nekako
 *  */
@Entity
public class Bill {

	@Id @GeneratedValue(strategy=GenerationType.TABLE)
	private int id;
	
	@ManyToOne
	private User user;
	
	private Date startDate;
	private Date endDate;
	
	private String serviceName;
	private int servicePrice;
	private int serviceQuantity;
	
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
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
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public int getServicePrice() {
		return servicePrice;
	}
	public void setServicePrice(int servicePrice) {
		this.servicePrice = servicePrice;
	}
	public int getServiceQuantity() {
		return serviceQuantity;
	}
	public void setServiceQuantity(int serviceQuantity) {
		this.serviceQuantity = serviceQuantity;
	}
		
}
