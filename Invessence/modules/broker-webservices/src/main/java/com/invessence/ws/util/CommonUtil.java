package com.invessence.ws.util;

import java.io.*;
import java.lang.reflect.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class CommonUtil
{

	static SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
	static SimpleDateFormat mmddyyyy=new SimpleDateFormat("MM/dd/yyyy");
	
	public static boolean todaysDateCompare(String lbDate){
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

	public static String getDateMMDDYYYY(Date tDate){
		String td=null;
		try {

			 td=mmddyyyy.format(tDate);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return td;
	}


	public static void main(String[] args) {
		System.out.println(getDateMMDDYYYY(new Date()));

}



	/*  To print strackTrace in log file	 */

	public static String stackTraceToString(StackTraceElement[] stackTrace) {
		StringWriter sw = new StringWriter();
		printStackTrace(stackTrace, new PrintWriter(sw));
		return sw.toString();
	}
	public static void printStackTrace(StackTraceElement[] stackTrace, PrintWriter pw) {
		for(StackTraceElement stackTraceEl : stackTrace) {
			pw.println(stackTraceEl);
		}
	}


	/*  To print all class object in console */
	public static String objectToString(Object o) {
		ArrayList<String> list = new ArrayList<String>();
		CommonUtil.objectToString(o, o.getClass(), list);
		return o.getClass().getName().concat(list.toString());
	}

	private static void objectToString(Object o, Class<?> clazz, List<String> list) {
		Field f[] = clazz.getDeclaredFields();
		AccessibleObject.setAccessible(f, true);
		for (int i = 0; i < f.length; i++) {
			try {
				list.add(f[i].getName() + "=" + f[i].get(o));
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		if (clazz.getSuperclass().getSuperclass() != null) {
			objectToString(o, clazz.getSuperclass(), list);
		}
	}
}
