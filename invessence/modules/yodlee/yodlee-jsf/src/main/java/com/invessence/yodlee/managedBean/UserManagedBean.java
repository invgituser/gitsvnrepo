package com.invessence.yodlee.managedBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import com.invessence.yodlee.model.UserLogon;
import com.invessence.yodlee.service.YodleeAPIService;

@ManagedBean(name="userMB")
@RequestScoped
public class UserManagedBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private static final String SUCCESS = "success";
	private static final String ERROR   = "error";
	
	//Spring User Service is injected...
	/*@ManagedProperty(value="#{UserService}")
	IUserService userService;*/
	
	@ManagedProperty(value="#{YodleeAPIService}")
	YodleeAPIService yodleeAPIService;
	
	List<UserLogon> userList;
	
	private String id;
	private String password;
	private String email;
	
	private Long invUserId;
	
	/**
	 * Add User
	 * 
	 * @return String - Response Message
	 */
	/*public String addUser() {
		try {
			UserLogon user = new UserLogon();
			user.setUSR_USER_ID(getId());
			user.setUSR_EMAIL(getEmail());
			user.setUSR_PASSWORD(getPassword());
			//getUserService().addUser(user);
			return SUCCESS;
		} catch (DataAccessException e) {
			e.printStackTrace();
		} 	
		
		return ERROR;
	}*/
	
	public Map<String, Object> userRegistration(Long invUserId) {
		System.out.println("UserManagedBean.userRegistration()");
		System.out.println("Long invUserId :"+invUserId);
		Map<String, Object> resultMap=null;
		try {
			getYodleeAPIService().userRegistration(invUserId);
			return resultMap;
		} catch (Exception e) {
			e.printStackTrace();
		} 	
		
		return resultMap;
	}
	
	
	public Map<String, Object> userLogin(Long invUserId) {
		System.out.println("UserManagedBean.userLogin()");
		System.out.println("Long invUserId :"+invUserId);
		Map<String, Object> resultMap=null;
		try {
			getYodleeAPIService().userLogin(invUserId);
			return resultMap;
		} catch (Exception e) {
			e.printStackTrace();
		} 	
		
		return resultMap;
	}
	
	
	public Map<String, Object> getUserSites() {
		Map<String, Object> resultMap=null;
		try {
			yodleeAPIService.getAllSiteAccounts(invUserId);
			return resultMap;
		} catch (Exception e) {
			e.printStackTrace();
		} 	
		
		return resultMap;
	}
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}




	/**
	 * Reset Fields
	 * 
	 */
	public void reset() {
		this.setId("");
		this.setEmail("");
		this.setPassword("");
	}
	
	public List<UserLogon> getUserList() {
		userList = new ArrayList<UserLogon>();
		userList=(List<UserLogon>) yodleeAPIService.getInvUserList().get("userList");
		System.out.println(userList.size()+" LIST SIZE");		
		return userList;
	}	
	

	public void setUserList(List<UserLogon> userList) {
		this.userList = userList;
	}
		
	
	public YodleeAPIService getYodleeAPIService() {
		return yodleeAPIService;
	}


	public void setYodleeAPIService(YodleeAPIService yodleeAPIService) {
		this.yodleeAPIService = yodleeAPIService;
	}


	public Long getInvUserId() {
		return invUserId;
	}


	public void setInvUserId(Long invUserId) {
		this.invUserId = invUserId;
	}




	

}