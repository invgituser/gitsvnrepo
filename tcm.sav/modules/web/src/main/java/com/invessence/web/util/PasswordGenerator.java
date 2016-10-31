package com.invessence.web.util;

import java.util.*;

public class PasswordGenerator {
	
	private static Map<String, String> map = new HashMap<String, String>();
	static {		
		map.put("0", "A");
		map.put("1", "A");
		map.put("2", "B");
		map.put("3", "C");
		map.put("4", "D");
		map.put("5", "E");
		map.put("6", "F");
		map.put("7", "G");
		map.put("8", "H");
		map.put("9", "I");
		
	}
	
	public static String getTempPassword() {
		
		Random random = new Random();
		String password = "";
		for (int i = 0; i < 8; i ++ ) {
	      
	        int number1 = random.nextInt(2);
	        int number = random.nextInt(9);
	        
	        if (number1 == 0) { 
	        	password = password + number;
	        } else {
	        	password = password + map.get(String.valueOf(number));
	        }
		}
	    
	    return password;
	    
	}
	
	public static String getSecCode() {
		return getTempPassword();
		
	}
	
	public static void main(String[] args) {
		String pw = PasswordGenerator.getTempPassword();
		System.out.println("password = " + pw);
	}
	
	

}
