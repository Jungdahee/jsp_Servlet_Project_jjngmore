package com.webinfo.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class CalcDate {
	
	public HashMap<String, String> calcMonth() {
		HashMap<String, String> filter = new HashMap<String, String>();

	    Calendar cal1 = Calendar.getInstance();
	    cal1.setTime(new Date());
	    cal1.add(Calendar.MONTH, -1);
	     
	    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	    String minFilter = df.format(cal1.getTime());
	    filter.put("minFilter", minFilter);
	    
	    Calendar cal2 = Calendar.getInstance();
	    cal2.setTime(new Date());
	    cal2.add(Calendar.MONTH, 1);
	    
	    String maxFilter = df.format(cal2.getTime());
	    filter.put("maxFilter", maxFilter);
	    
	    return filter;
	}
	
	public HashMap<String, String> calcDay() {
		HashMap<String, String> filter = new HashMap<String, String>();

	    Calendar cal = Calendar.getInstance();
	    cal.setTime(new Date());
	    cal.add(Calendar.DATE, 7);
	     
	    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	    String dday7 = df.format(cal.getTime());
	    filter.put("dday7", dday7);
	    
	    cal.setTime(new Date());
	    cal.add(Calendar.DATE, 3);
	    
	    String dday3 = df.format(cal.getTime());
	    filter.put("dday3", dday3);
	    
	    cal.setTime(new Date());
	    cal.add(Calendar.DATE, 1);
	    
	    String dday1 = df.format(cal.getTime());
	    filter.put("dday1", dday1);
	    
	    return filter;
	}

}
