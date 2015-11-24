package com.invessence.yodlee.util;

import java.util.Random;

public class CommonUtil {
	
	private static final String dCase = "abcdefghijklmnopqrstuvwxyz";
	private static final String uCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static final String sChar = "!@#$%^&*";
	private static final String intChar = "0123456789";

	public static java.sql.Timestamp getCurrentTimeStamp() {

		java.util.Date today = new java.util.Date();
		return new java.sql.Timestamp(today.getTime());

	}

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

	/*
	 * public static void main(String[] args) { System.out.println(new
	 * CommonUtil().passGenerator()); }
	 */

	public static void main(String[] args) {

		
		
	}

}
