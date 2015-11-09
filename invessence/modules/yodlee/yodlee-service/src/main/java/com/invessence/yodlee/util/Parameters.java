package com.invessence.yodlee.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Parameters {

	@Value("${COBRAND_LOGIN}") public static String COBRAND_LOGIN;
	@Value("${COBRAND_PASSWORD}") public static String COBRAND_PASSWORD;
	
	@Value("${BRIDGE_APP_ID}") public static String BRIDGE_APP_ID;
	@Value("${APPLICATION_KEY}") public static String APPLICATION_KEY;
	@Value("${APPLICATION_TOKEN}") public static String APPLICATION_TOKEN;
	
	@Value("${FL_ADD_ACC_URL}") public static String FL_ADD_ACC_URL;
	@Value("${FL_ADD_ACC_PARAM}") public static String FL_ADD_ACC_PARAM;
	
	@Value("${FL_EDIT_ACC_URL}") public static String FL_EDIT_ACC_URL;
	@Value("${FL_EDIT_ACC_PARAM}") public static String FL_EDIT_ACC_PARAM;
	
	@Value("${FL_REFR_URL}") public static String FL_REFR_URL;
	@Value("${FL_REFR_PARAM}") public static String FL_REFR_PARAM;
	
	public Parameters() {
		System.out.println("-----------------------------------------------------------------");
		System.out.println(COBRAND_LOGIN +" : "+COBRAND_PASSWORD);
	System.out.println("Initialising Parameters.Parame ers()");
	}
}
