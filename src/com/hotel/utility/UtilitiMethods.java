package com.hotel.utility;

import java.util.Date;

public class UtilitiMethods {
	
	/** Calculate the difference between two dates */ 
	public static int getDateDiff(Date startDate, Date endDate) {
		return (int)((endDate.getTime() - startDate.getTime())/(1000 * 3600 * 24));
	}
	
	
}
