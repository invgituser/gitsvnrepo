package com.invessence.yodlee.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.util.ClassUtils;

public class CommonUtil {
	
	private static final String dCase = "abcdefghijklmnopqrstuvwxyz";
	private static final String uCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static final String sChar = "!@#$%^&*";
	private static final String intChar = "0123456789";

	public static java.sql.Timestamp getCurrentTimeStamp() {

		java.util.Date today = new java.util.Date();
		return new java.sql.Timestamp(today.getTime());
	}

	/*  To generate random password */
	
	public static String passGenerator() {

		Random r = new Random();
		String pass = "";
		try {
			while (pass.length() != 10) {
				int rPick = r.nextInt(4);
				if (rPick == 0) {
					int spot = r.nextInt(25);
					pass += dCase.charAt(spot);
				} else if (rPick == 1) {
					int spot = r.nextInt(25);
					pass += uCase.charAt(spot);
				} else if (rPick == 2) {
					int spot = r.nextInt(7);
					pass += sChar.charAt(spot);
				} else if (rPick == 3) {
					int spot = r.nextInt(9);
					pass += intChar.charAt(spot);
				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//return pass==null ||pass.equals("")? passGenerator() : pass;
		return "Password@2015";//pass;
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
