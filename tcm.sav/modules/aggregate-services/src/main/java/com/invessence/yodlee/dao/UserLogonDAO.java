package com.invessence.yodlee.dao;

import java.util.List;

import com.invessence.yodlee.model.UserLogon;

public interface UserLogonDAO {

	public List<UserLogon> getInvUserList();
	
	public UserLogon getInvUserDetails(Long id);
	
	public UserLogon insertUserLogon(UserLogon userLogon);
	
	public UserLogon updateUserLogon(UserLogon userLogon);
	
	public UserLogon deleteUserLogon(UserLogon userLogon);
	
	public List<UserLogon> getUserLogonList();
	
	public UserLogon findByPK(Long Id);
	
	public List<UserLogon> findByWhereCluase(String where,Object[] values);
	
	public List<UserLogon> findByWhereCluase(String where);
}
