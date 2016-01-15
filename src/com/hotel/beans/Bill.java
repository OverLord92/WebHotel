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
	
}
