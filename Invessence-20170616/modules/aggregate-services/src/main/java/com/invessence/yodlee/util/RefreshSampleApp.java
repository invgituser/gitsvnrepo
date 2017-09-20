package com.invessence.yodlee.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * The Class RefreshSampleApp.
 */
public class RefreshSampleApp {

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/*
	 * Below list of attributes need to be modified based on the environment.
	 * Note: This API won't be accessible on sandbox environment, and following URL and cobrand username/password is just for reference.
	 * 
	 */
	public static String BASE_URL = "https://rest.developer.yodlee.com/services/srest/restserver/v1.0/"; //Rest server URL for your cobrand.
	public static String cobrandLoginValue = "cobrandUserName";//cobrand login name
	public static String cobrandPasswordValue = "cobrandPassword";//cobrand password
	
	/*
	 * Following Map is being used to mimic the customer's local data store, as customer needs to get the user's password
	 * from their data base to create a user session and fetch the required details.
	 *  
	 */
	private static final Map<String , String> loginCredsMap = new HashMap<String , String>() {{	//List of user names stored in map.
	    put("abhangPatil","Password@2015");
	    put("testAccount","Password@2015");
	    put("testAccount2","Password@2015");
	}};
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/** Cobrand login API sub-URL. */
	private static String COB_LOGIN_URL = "authenticate/coblogin";
	
	/** User login API sub-URL. */
	private static String USER_LOGIN_URL = "authenticate/login";
	
	/** Get Refreshed User items API sub-URL. */
	private static String GET_REFRESHED_USER_ITEMS = "jsonsdk/Refresh/getRefreshedUserItems";
	
	/** Transaction Search API sub-URL. */
	private static String USR_SEARCH_REQUEST = "jsonsdk/TransactionSearchService/executeUserSearchRequest";
	
	/** Get Item summary for Item API sub-URL. */
	private static String GET_ITEM_SUMMERY_FOR_ITEM1 = "jsonsdk/DataService/getItemSummaryForItem1";
	
	/** The param name cobrand login. */
	private String paramNameCobrandLogin = "cobrandLogin";
	
	/** The param name cobrand password. */
	private String paramNameCobrandPassword = "cobrandPassword";
	
	/** The param name cob session token. */
	private String paramNameCobSessionToken = "cobSessionToken";
	
	/** The param name user login. */
	private String paramNameUserLogin = "login";
	
	/** The param name user password. */
	private String paramNameUserPassword = "password";
	
	/** The param name user session token. */
	private String paramNameUserSessionToken = "userSessionToken";

	/**
	 * Login cobrand here.
	 *
	 * @param cobrandLoginValue the cobrand login value
	 * @param cobrandPasswordValue the cobrand password value
	 * @return the string
	 */
	public String loginCobrand(String cobrandLoginValue,
			String cobrandPasswordValue) {
		DefaultHttpClient httpclient = new DefaultHttpClient();
		String url = BASE_URL + COB_LOGIN_URL;
		String cobrandSessionToken = null;

		try {
			HttpsURLConnection
					.setDefaultHostnameVerifier(new NullHostnameVerifier());
			PostMethod pm = new PostMethod(url);
			pm.addParameter(paramNameCobrandLogin, cobrandLoginValue);
			pm.addParameter(paramNameCobrandPassword, cobrandPasswordValue);
			HttpClient hc = new HttpClient();
			int rc = hc.executeMethod(pm);
			String source = pm.getResponseBodyAsString();
			JSONObject jsonObject = new JSONObject(source);
			JSONObject cobConvCreds = jsonObject
					.getJSONObject("cobrandConversationCredentials");
			cobrandSessionToken = (String) cobConvCreds.get("sessionToken");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			httpclient.getConnectionManager().shutdown();
		}

		return cobrandSessionToken;
	}
	
	/**
	 * Login user using following method.
	 *
	 * @param cobrandSessionToken the cobrand session token
	 * @param usernameValue the username value
	 * @param passwordValue the password value
	 * @return the string
	 */
	public String loginUser(String cobrandSessionToken, String usernameValue, String passwordValue){
		String userSessionToken=null;
		DefaultHttpClient httpclient = new DefaultHttpClient();

		String url = BASE_URL + USER_LOGIN_URL;		
		try {
			HttpsURLConnection
					.setDefaultHostnameVerifier(new NullHostnameVerifier());
			PostMethod pm = new PostMethod(url);
			pm.addParameter(paramNameUserLogin, usernameValue);
			pm.addParameter(paramNameUserPassword, passwordValue);
			pm.addParameter(paramNameCobSessionToken, cobrandSessionToken);
			HttpClient hc = new HttpClient();
			int rc=hc.executeMethod(pm);
			String source=pm.getResponseBodyAsString();
			JSONObject jsonObject= new JSONObject(source);
			JSONObject userContext= jsonObject.getJSONObject("userContext");
			JSONObject userConvCreds= userContext.getJSONObject("conversationCredentials");
			userSessionToken= (String) userConvCreds.get("sessionToken");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			httpclient.getConnectionManager().shutdown();
		}
		return userSessionToken;
	}

	/**
	 * Gets the refreshed user items.
	 * This API will return only the items which successfully get refreshed during the specified time.
	 * In case you want to get failed items as well then need to mention refreshDataFilter.requiredAll flag as "true" in the request.
	 * 
	 * @param refreshDataFilter.requiredAll: This should be passed as true 
	 *
	 * @param cobrandSessionToken the cobrand session token
	 * @return the refreshed user items
	 */
	public String getRefreshedUserItems(String cobrandSessionToken, String startDate, String endDate) {
		DefaultHttpClient httpclient = new DefaultHttpClient();
		String response = null;
		String url = BASE_URL + GET_REFRESHED_USER_ITEMS;
		try {
			HttpsURLConnection
					.setDefaultHostnameVerifier(new NullHostnameVerifier());
			PostMethod pm = new PostMethod(url);
			pm.addParameter(paramNameCobSessionToken, cobrandSessionToken);
			pm.addParameter("refreshDataFilter.refreshType[0]", "1"); // CACHE
																		// REFRESH
			pm.addParameter("refreshDataFilter.refreshType[1]", "2"); // INSTANT
																		// REFRESH
			pm.addParameter("refreshDataFilter.startDate",
					startDate);
			pm.addParameter("refreshDataFilter.endDate", endDate);
			
			//pm.addParameter("refreshDataFilter.requiredAll", "false"); // default
																		// false
			HttpClient hc = new HttpClient();
			int RC = hc.executeMethod(pm);
			response = pm.getResponseBodyAsString();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			httpclient.getConnectionManager().shutdown();
		}
		return response;
	}
	
	/**
	 * Gets the item summary for item.
	 *
	 * @param cobrandSessionToken the cobrand session token
	 * @param userSessionToken the user session token
	 * @return the item summary for item1
	 */
	public String getItemSummaryForItem1(String  cobrandSessionToken, String  userSessionToken, String memItemId)
	{
		String userSessionToken_LoginForm=null;
		DefaultHttpClient httpclient = new DefaultHttpClient();
		String url = BASE_URL + GET_ITEM_SUMMERY_FOR_ITEM1;		
		try {
			HttpsURLConnection.setDefaultHostnameVerifier(new NullHostnameVerifier());
			PostMethod pm = new PostMethod(url);
			pm.addParameter(paramNameCobSessionToken, cobrandSessionToken);
			pm.addParameter(paramNameUserSessionToken,userSessionToken);
			pm.addParameter("itemId", memItemId);
			pm.addParameter("dex.extentLevels[0]","0");//Please use the data extent guidelines
			pm.addParameter("dex.extentLevels[1]","0");
			HttpClient hc = new HttpClient();
			int RC = hc.executeMethod(pm);
			userSessionToken_LoginForm=pm.getResponseBodyAsString();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			httpclient.getConnectionManager().shutdown();
		}
		return userSessionToken_LoginForm;
	}

	/**
	 * Execute user search request.
	 * Calling this API to get Transactions.
	 *
	 * @param cobrandSessionToken the cobrand session token
	 * @param userSessionToken the user session token
	 * @param itemAccountId the item account id
	 * @param startDate the start date
	 * @param endDate the end date
	 * @return the string
	 */
	public String executeUserSearchRequest(String cobrandSessionToken,
			String userSessionToken, String itemAccountId, String startDate, String endDate) {
		String site_account = null;
		DefaultHttpClient httpclient = new DefaultHttpClient();
		String url = BASE_URL + USR_SEARCH_REQUEST;
		try {
			HttpsURLConnection
					.setDefaultHostnameVerifier(new NullHostnameVerifier());
			PostMethod pm = new PostMethod(url);
			pm.addParameter(paramNameCobSessionToken, cobrandSessionToken);
			pm.addParameter(paramNameUserSessionToken, userSessionToken);
			pm.addParameter("transactionSearchRequest.containerType", "all");
			pm.addParameter("transactionSearchRequest.higherFetchLimit", "5000");
			pm.addParameter("transactionSearchRequest.lowerFetchLimit", "1");
			pm.addParameter("transactionSearchRequest.resultRange.endNumber",
					"500");
			pm.addParameter("transactionSearchRequest.resultRange.startNumber",
					"1");
			pm.addParameter("transactionSearchRequest.searchFilter.itemAccountId.identifier", itemAccountId);// need to get the itemAccountid for the specific account and pass it here.
			pm.addParameter(
					"transactionSearchRequest.searchFilter.transactionSplitType",
					"ALL_TRANSACTION");
			pm.addParameter(
					"transactionSearchRequest.searchFilter.postDateRange.fromDate",
					startDate);
			pm.addParameter(
					"transactionSearchRequest.searchFilter.postDateRange.toDate",
					endDate);
			pm.addParameter("transactionSearchRequest.ignoreUserInput", "true");
			pm.addParameter("transactionSearchRequest.ignoreManualTransactions",
			"false");
			HttpClient hc = new HttpClient();
			int RC = hc.executeMethod(pm);
			
			site_account = pm.getResponseBodyAsString();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			httpclient.getConnectionManager().shutdown();
		}
		return site_account;
	}
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		RefreshSampleApp app = new RefreshSampleApp();
		
		//Cobrand login API call
		String cobrandSessionToken = app.loginCobrand(cobrandLoginValue,
				cobrandPasswordValue);
		
		String refreshStartTime = "08-17-2014T22:00:01";//mention the start time, use best practice.

		String refreshEndTime =   "08-17-2014T22:01:01";//mention the end time, use best practice
		//Get refreshed user items API call
		String getRefreshedUserItems = app
				.getRefreshedUserItems(cobrandSessionToken, refreshStartTime, refreshEndTime);
		System.out.println("\nRefreshed User Items here:"
				+ getRefreshedUserItems);

		String loginName = null;
		String password = null;
		String memItemId = null;
		String errorCode = null;
		String refreshDate = null;
		String startDate = null;
		String endDate = null;
			
		/*
		 * Parsing the getRefreshedUserItems API response to fetch the latest items which got refreshed in provided date(with time) duration.
		 * This is how you'll get the refreshed User Items and then you can loop through each one of them and fetch the other details.
		 */
		try {
			JSONArray jsonMainArr = new JSONArray(getRefreshedUserItems);
			for (int i = 0; i < jsonMainArr.length(); i++) {
			    JSONObject childJSONObject = jsonMainArr.getJSONObject(i);
			    
			    //Extracting login name from JSON response
			    loginName = childJSONObject.getString("loginName");
			    //Getting password from the constructed map using login name
			    password = loginCredsMap.get(loginName);

			    System.out.println("===================================================================");
			    System.out.println("User: "+loginName);
			    JSONArray jsonMemItemArr = childJSONObject.getJSONArray("memItemRefreshInfo");
			    for (int j = 0; j < jsonMemItemArr.length(); j++) {
			    	JSONObject memItemJSONObject = jsonMemItemArr.getJSONObject(j);
			    	memItemId = String.valueOf(memItemJSONObject.getLong("memItemId"));
			    	errorCode = String.valueOf(memItemJSONObject.getLong("errorCode"));
			    	refreshDate = memItemJSONObject.getString("refreshTime");
			    	
			    	if(errorCode.equals("0")) {
				    	SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
				    	Date dateVal = sdf.parse(refreshDate);
				    	
				    	//User login API Call
				    	String userSessionToken = app.loginUser(cobrandSessionToken, loginName, password);
				    	
				    	//Get item summary for item API call
						String item_summary = app.getItemSummaryForItem1(cobrandSessionToken,userSessionToken, memItemId);
						System.out.println("\nItem Summary for item: "+memItemId+" is: " + item_summary);
						
						String itemAccountId = null;
						try {
							JSONObject jsonObject = new JSONObject(item_summary);
							JSONObject itemDataObj = jsonObject.getJSONObject("itemData");
							JSONArray jsonItemArr = itemDataObj.getJSONArray("accounts");
							for (int k = 0; k < jsonItemArr.length(); k++) {
							    JSONObject itemJSONObject = jsonItemArr.getJSONObject(k);
							    itemAccountId = String.valueOf(itemJSONObject.getLong("itemAccountId"));
							    
							    startDate = (dateVal.getMonth()+1)+"-"+(dateVal.getDate()-7)+"-"+(dateVal.getYear()+1900);
							    endDate = (dateVal.getMonth()+1)+"-"+dateVal.getDate()+"-"+(dateVal.getYear()+1900);
							  
							    //Transaction search API call to fetch the transactions.							    
								String executeUserSearchRequest = app.executeUserSearchRequest(cobrandSessionToken, userSessionToken, itemAccountId, startDate, endDate);
								System.out.println("Transaction details between "+startDate+" to "+endDate+" for item account id: "+itemAccountId+" is: " + executeUserSearchRequest);
							}
						} catch(Exception e) {
							e.printStackTrace();
						}
			    	}
			    }
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * The Class NullHostnameVerifier.
	 */
	private static class NullHostnameVerifier implements HostnameVerifier {
		public boolean verify(String hostname, SSLSession session) {
			return true;
		}
	}
}
