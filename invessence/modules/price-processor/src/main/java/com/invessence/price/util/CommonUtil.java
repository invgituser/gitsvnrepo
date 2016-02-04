package com.invessence.price.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonUtil {

	static SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
	
	public static boolean dateCompare(String lbDate){
		try {
			
			Date tDate =new Date();
			String td=sdf.format(tDate);
			
			System.out.println(lbDate+" : "+td);
			if(lbDate.equals(td)){
				return true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
		
	}
	
	public static void main(String[] args) {
		System.out.println(new CommonUtil().dateCompare("20151130"));
	}

	public static Object stackTraceToString(StackTraceElement[] stackTrace) {
		// TODO Auto-generated method stub
		return null;
	}
}
