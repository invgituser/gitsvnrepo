package com.invessence.constant;

import com.invessence.data.*;

/*

public class MsgConst {
	
	public final static String MSG_TAG = "msg";
	public final static String NAV_LINK_TAG = "navLink";
	
	public final static String DEF_LOGIN_ERROR= "Login Error.";
	public final static String DEF_SIGNUP_ERROR = "Signup Error.";
	public final static String DEF_PASSWORD_RESET_ERROR = "Password Reset Error.";
	
	public final static String DEF_ADD_EXP_ERROR = "Error.";
	
	public final static String DEF_EMAIL_GROUP_ERROR = "Error.";
	public final static String DEF_EMAIL_ERROR = "Invalid email.";
	public final static String DEF_USER_ERROR = "Invalid user.";
	
	
	
	public final static String DEF_SIGNUP_SUCCESSFUL = "Signup Successful.";
	public final static String DEF_LOGOUT_SUCCESSFUL = "Logout Successful.";
	
	public static String getSignupMsg(UserData userData) {
		
		StringBuffer sb = new StringBuffer();
    	sb.append("Dear User,\n");
    	sb.append("Welcome to " + Const.WEBSITE_NAME + "\n");
    	sb.append("Please follow the below link to set up your password:\n");
    	sb.append("http://localhost:8080/Godhuli/ResetPassword.htm?emailID=" + userData.getEmailID());
    	sb.append("&secCode=" + userData.getSecCode());
    	
    	sb.append("\n\n");
    	sb.append("If clicking on the link doesn't work, try copying and pasting it into your browser.\n");
    	sb.append("If you did not signup for " + Const.WEBSITE_NAME + ", please disregard this message.\n\n");
    	
    	sb.append("Thank you,\n");
    	sb.append("Admin, " + Const.WEBSITE_NAME);
    	
    	return sb.toString();
		
	}
	
	public static String getResetPasswordMsg(UserData userData) {
		
		StringBuffer sb = new StringBuffer();
    	sb.append("Dear User,\n");
    	sb.append("Please follow the below link to reset your password:\n");
    	sb.append("http://localhost:8080/Godhuli/ResetPassword.htm?emailID=" + userData.getEmailID());
    	sb.append("&secCode=" + userData.getSecCode());
    	
    	sb.append("\n\n");
    	sb.append("If clicking on the link doesn't work, try copying and pasting it into your browser.\n");
    	sb.append("If you did not request the password reset, please disregard this message.\n\n");
    	
    	sb.append("Thank you,\n");
    	sb.append("Admin, " + Const.WEBSITE_NAME);
    	
    	return sb.toString();
		
	}
	


}

*/
