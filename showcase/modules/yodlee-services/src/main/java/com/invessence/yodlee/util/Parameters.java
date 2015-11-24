package com.invessence.yodlee.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Properties;

public class Parameters {

	protected static Properties parameters=null;

	public static String COBRAND_LOGIN;
	public static String COBRAND_PASSWORD;

	public static String BRIDGE_APP_ID;
	public static String APPLICATION_KEY;
	public static String APPLICATION_TOKEN;

	public static String FL_ADD_ACC_URL;
	public static String FL_ADD_ACC_PARAM;

	public static String FL_EDIT_ACC_URL;
	public static String FL_EDIT_ACC_PARAM;

	public static String FL_REFR_URL;
	public static String FL_REFR_PARAM;

	static{
		System.out.println("********************************************");
		System.out.println("WE CALLED PARAMETERS CLASS");
		System.out.println("********************************************");
		parameters=new Properties();
		try {

			parameters.load(Parameters.class.getResourceAsStream("/resources.properties"));

			COBRAND_LOGIN=parameters.getProperty("COBRAND_LOGIN");
			COBRAND_PASSWORD=parameters.getProperty("COBRAND_PASSWORD");
			BRIDGE_APP_ID=parameters.getProperty("BRIDGE_APP_ID");
			APPLICATION_KEY=parameters.getProperty("APPLICATION_KEY");
			APPLICATION_TOKEN=parameters.getProperty("APPLICATION_TOKEN");
			FL_ADD_ACC_URL=parameters.getProperty("FL_ADD_ACC_URL");
			FL_ADD_ACC_PARAM=parameters.getProperty("FL_ADD_ACC_PARAM");
			FL_EDIT_ACC_URL=parameters.getProperty("FL_EDIT_ACC_URL");
			FL_EDIT_ACC_PARAM=parameters.getProperty("FL_EDIT_ACC_PARAM");
			FL_REFR_URL=parameters.getProperty("FL_REFR_URL");
			FL_REFR_PARAM=parameters.getProperty("FL_REFR_PARAM");
			System.out.println("***************************************");
			System.out.println("COBRAND_LOGIN: "+COBRAND_LOGIN);
			System.out.println("COBRAND_PASSWORD: "+COBRAND_PASSWORD);
			System.out.println("BRIDGE_APP_ID: "+BRIDGE_APP_ID);
			System.out.println("APPLICATION_KEY: "+APPLICATION_KEY);
			System.out.println("APPLICATION_TOKEN: "+APPLICATION_TOKEN);
			System.out.println("FL_ADD_ACC_URL: "+FL_ADD_ACC_URL);
			System.out.println("FL_ADD_ACC_PARAM: "+FL_ADD_ACC_PARAM);
			System.out.println("FL_EDIT_ACC_URL: "+FL_EDIT_ACC_URL);
			System.out.println("FL_EDIT_ACC_PARAM: "+FL_EDIT_ACC_PARAM);
			System.out.println("FL_REFR_URL: "+FL_REFR_URL);
			System.out.println("FL_REFR_PARAM: "+FL_REFR_PARAM);
			System.out.println("***************************************");
			
			/*System.out.println("hostUser :"+hostUser);
			System.out.println("hostPwd :"+hostPwd);*/

			System.out.println("**********************************");

		}catch (Exception e) {
			System.out.println("Parameter Property file reading time error.");
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		ClassLoader loader = Parameters.class.getClassLoader();
		System.out.println(loader.getResource("com/invessence/yodlee/util/Parameters.class"));

		String path = Parameters.class.getProtectionDomain().getCodeSource().getLocation().getPath();
		String decodedPath = path;
		try {
			decodedPath = URLDecoder.decode(path, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			// return null;
		}

		String absolutePath = decodedPath.substring(0, decodedPath.lastIndexOf("/"))+"\\";

		System.out.println(absolutePath+" : " + Parameters.class.getProtectionDomain().getCodeSource());


	}
}
