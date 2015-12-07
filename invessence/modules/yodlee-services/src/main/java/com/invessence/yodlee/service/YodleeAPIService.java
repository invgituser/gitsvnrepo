package com.invessence.yodlee.service;

import java.util.Map;

import org.json.JSONArray;

public interface YodleeAPIService {
	
	public Map<String, Object> getInvUserList();

	public Boolean isUserRegisteredAtYodlee(Long invUserId);

	public Map<String, Object> userRegistration(Long invUserId);
	
	public Map<String, Object> userUnRegistration(Long invUserId);
	
	public Map<String, Object> getUserRegistrationList();
	
	public Map<String, Object> advisorLogin();
	
	public Map<String, Object> userLogin(Long invUserId);
	
	public Map<String, Object> getAllSiteAccounts(Long invUserId) ;

	public Map<String, Object> getItemSummariesForSite(String siteAccId, Long invUserId) ;
	
	public Map<String, Object> getFastLinkDetails(String operation, String siteAccId, Long invUserId);

	//public Map<String, Object> getUserSiteDetails(Long invUserId);

	public Map<String, Object> getUserAccountsDetail(Long invUserId);

	public Map<String, Object> refreshUserAccDetails(Long invUserId);

	public Map<String, Object> getInvestmentHoldings(Long invUserId);

	public Map<String, Object> getInvestmentTransactions(Long invUserId);


}
