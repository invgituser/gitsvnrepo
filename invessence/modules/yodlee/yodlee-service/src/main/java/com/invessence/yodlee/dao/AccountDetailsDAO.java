package com.invessence.yodlee.dao;

import java.util.List;

import com.invessence.yodlee.model.AccountDetail;


public interface AccountDetailsDAO {

	public AccountDetail insertAccountDetails(AccountDetail accountDetails);
	
	public AccountDetail updateAccountDetails(AccountDetail accountDetails);
	
	public AccountDetail deleteAccountDetails(AccountDetail accountDetails);
	
	public List<AccountDetail> getAccountDetailsList();
	
	public AccountDetail findByPK(Long Id);
	
	public List<AccountDetail> findByWhereCluase(String where,Object[] values);
	
	public List<AccountDetail> findByWhereCluase(String where);
}
