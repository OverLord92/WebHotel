package com.hotel.utility;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class UtilitiMethods {
	
	
	
	public static void main(String[] args) {
		
		Date startDate = new Date(1000 * 3600 * 24 * 5);
		Date endDate = new Date(1000 * 3600 * 24 * 10);
		
		System.out.println(getDateDiff(startDate, endDate));
		
		
	}

	public static int getDateDiff(Date startDate, Date endDate) {
		
		return (int)((endDate.getTime() - startDate.getTime())/(1000 * 3600 * 24));
		
	}
	
	
}
