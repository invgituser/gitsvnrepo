package com.invessence.util;

import java.util.*;
import java.text.*;


public class DateUtil {
    
    public static String getDate() {

        Date today = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("MMddyyyy");

        return formatter.format(today);
    }

    public static String getDate(String pattern) {

        Date today = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        return formatter.format(today);
    }
    
    public static String getDate(Date date, String pattern) {

        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        return formatter.format(date);
    }
    
    public static boolean isDateValid(String date, String format) {
    	
    	//if (Util.isNull(date))
    		//return false;
    	
        try {
	        Date enteredDate = new SimpleDateFormat(format).parse(date);
			  
			if (enteredDate == null)
	            return false;
            else 
                return true;
		} catch(ParseException e) {
		    return false;
		}
	}
    
    public static String convertDateFormat(String date, String format) {
    	
    	try {
	        Date enteredDate = DateFormat.getInstance().parse(date);
	        
	        return getDate(enteredDate, format);
			  			
		} catch(ParseException e) {
			e.printStackTrace();
		    return "";
		}
    	
    }
    
    public static String  convertDateFormat2(String date, String format) { 
        String[] ddmmyy = date.split("-");
    
        Calendar c = Calendar.getInstance();

        c.set(Calendar.MONTH, Integer.parseInt(ddmmyy[1]) -1);
        c.set(Calendar.DAY_OF_MONTH, Integer.parseInt(ddmmyy[2]));
        c.set(Calendar.YEAR, Integer.parseInt(ddmmyy[0]));
        
     
 
        //return c.getTime();
        
        return getDate(c.getTime(), format);
    }
    
    public static Date getDate(String date, String format) {
    	
       	if (WebUtil.isNull(date))
    		return null;
    	
        try {
	        Date enteredDate = new SimpleDateFormat(format).parse(date);
			  
			if (enteredDate == null)
	            return null;
            else 
                return enteredDate;
		} catch(ParseException e) {
		    return null;
		}
	}
    
    public static String  getDateTime(String date, String format) { 
        String[] ddmmyy = date.split("/");
    
        Calendar c = Calendar.getInstance();

        c.set(Calendar.MONTH, Integer.parseInt(ddmmyy[0]) -1);
        c.set(Calendar.DAY_OF_MONTH, Integer.parseInt(ddmmyy[1]));
        c.set(Calendar.YEAR, Integer.parseInt(ddmmyy[2]));
        
     
 
        //return c.getTime();
        
        return getDate(c.getTime(), format);
    }
    
    public static String  getMysqlDate(String date) { 
        String[] mmddyyyy = date.split("/");
        
        return mmddyyyy[2] + "-" + mmddyyyy[0] + "-" + mmddyyyy[1];
    
    }
    
    public static Calendar getCalendar() {
    	
    	Calendar rightNow = Calendar.getInstance(); 
		int year = rightNow.get(Calendar.YEAR);
		int month = rightNow.get(Calendar.MONTH);
		int day = rightNow.get(Calendar.DAY_OF_MONTH);
		
		Calendar now = new GregorianCalendar(year, month, day); 
		return now;
    }
    
 



    public static void main(String[] args) {

        System.out.println("Today's date = " + DateUtil.getDate());
        System.out.println("Today's date = " + DateUtil.getDate(args[0]));
    }
}
