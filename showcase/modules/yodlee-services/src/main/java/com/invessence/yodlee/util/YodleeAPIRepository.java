package com.invessence.yodlee.util;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import com.invessence.yodlee.service.YodleeAPIServiceImpl;

@Component
public class YodleeAPIRepository {
	private static final Logger logger = Logger.getLogger(YodleeAPIServiceImpl.class);
	private static String HOST_URI;

	private static String COB_LOGIN_URL = "v1.0/authenticate/coblogin";
	private static String USER_LOGIN_URL = "v1.0/authenticate/login";
	private static String USER_REGISTER_URL = "v1.0/jsonsdk/UserRegistration/register3";
	private static String USER_UNREGISTER_URL = "v1.0/jsonsdk/UserRegistration/unregister";
	private static String DATA_SERVICE = "v1.0/jsonsdk/DataService/getItemSummaries";
	private static String GET_SITE_INFO = "v1.0/jsonsdk/SiteTraversal/getSiteInfo";
	private static String ITEM_SUMM_FOR_SITE = "v1.0/jsonsdk/DataService/getItemSummariesForSite";
	private static String GET_ALL_SITE_ACCS = "v1.0/jsonsdk/SiteAccountManagement/getAllSiteAccounts";
	private static String GET_TOKEN = "v1.0/jsonsdk/OAuthAccessTokenManagementService/getOAuthAccessToken";
	private static String GET_ITEM_SUMMERY_FOR_ITEM1 = "v1.0/jsonsdk/DataService/getItemSummaryForItem1";

	// Common parameters for all APIs except for cobrand login or cobrand
	// creation APIs
	private String paramNameCobSessionToken = "cobSessionToken";
	private String paramNameUserSessionToken = "userSessionToken";

	// Cobrand login API parameters
	private String paramNameCobrandLogin = "cobrandLogin";
	private String paramNameCobrandPassword = "cobrandPassword";

	private String paramNameFinAppId = "bridgetAppId";

	// User login API parameters
	private String paramNameUserLogin = "login";
	private String paramNameUserPassword = "password";

	// Create cobrand credentials API parameters
	private String paramNameNewUserLogin = "userCredentials.loginName";
	private String paramNameNewUserPassword = "userCredentials.password";
	private String paramNameInstanceType = "userCredentials.objectInstanceType";
	private String paramNameUserEmail = "userProfile.emailAddress";

	/*
	 * private String paramNamecontainerType =
	 * "transactionSearchRequest.containerType"; private String
	 * paramNamehigherFetchLimit = "transactionSearchRequest.higherFetchLimit";
	 * private String paramNamelowerFetchLimit =
	 * "transactionSearchRequest.lowerFetchLimit"; private String
	 * paramNameendNumber = "transactionSearchRequest.resultRange.endNumber";
	 * private String paramNamestartNumber =
	 * "transactionSearchRequest.resultRange.startNumber"; private String
	 * paramNameclientId = "transactionSearchRequest.searchClients.clientId";
	 * private String paramNameclientName =
	 * "transactionSearchRequest.searchClients.clientName"; private String
	 * paramNamecurrencyCode =
	 * "transactionSearchRequest.searchFilter.currencyCode"; private String
	 * paramNamefromDate =
	 * "transactionSearchRequest.searchFilter.postDateRange.fromDate"; private
	 * String paramNametoDate =
	 * "transactionSearchRequest.searchFilter.postDateRange.toDate"; private
	 * String paramNametransactionSplitType =
	 * "transactionSearchRequest.searchFilter.transactionSplitType"; private
	 * String paramNameignoreUserInput =
	 * "transactionSearchRequest.ignoreUserInput";
	 */

	public JSONObject loginCobrand(String cobrandLoginValue, String cobrandPasswordValue) {
		logger.info("In YodleeAPIRepository.loginCobrand()");
		logger.debug("cobrandLoginValue : "+cobrandLoginValue+", cobrandPasswordValue : "+cobrandPasswordValue);
		DefaultHttpClient httpclient = new DefaultHttpClient();

		String url = HOST_URI + COB_LOGIN_URL;
		JSONObject jsonObject = null;
		try {
			HttpsURLConnection.setDefaultHostnameVerifier(new NullHostnameVerifier());

			PostMethod pm = new PostMethod(url);
			pm.addParameter(paramNameCobrandLogin, cobrandLoginValue);
			pm.addParameter(paramNameCobrandPassword, cobrandPasswordValue);

			HttpClient hc = new HttpClient();
			hc.executeMethod(pm);
			
			String ydlRes = pm.getResponseBodyAsString();
			logger.debug("Yodlee Response :"+ydlRes);
			jsonObject = new JSONObject(ydlRes);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			httpclient.getConnectionManager().shutdown();
		}
		logger.info("Out YodleeAPIRepository.loginCobrand()");
		return jsonObject;
	}

	public JSONObject loginUser(String cobrandSessionToken, String usernameValue, String passwordValue) {
		logger.info("InYodleeAPIRepository.loginUser()");
		logger.debug("cobrandSessionToken :"+cobrandSessionToken+", usernameValue :"+usernameValue);
		DefaultHttpClient httpclient = new DefaultHttpClient();
		JSONObject jsonObject = null;
		String url = HOST_URI + USER_LOGIN_URL;
		try {
			HttpsURLConnection.setDefaultHostnameVerifier(new NullHostnameVerifier());

			PostMethod pm = new PostMethod(url);
			pm.addParameter(paramNameUserLogin, usernameValue);
			pm.addParameter(paramNameUserPassword, passwordValue);
			pm.addParameter(paramNameCobSessionToken, cobrandSessionToken);

			HttpClient hc = new HttpClient();
			hc.executeMethod(pm);

			String ydlRes = pm.getResponseBodyAsString();
			logger.debug("Yodlee Response :"+ydlRes);
			jsonObject = new JSONObject(ydlRes);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			httpclient.getConnectionManager().shutdown();
		}
		logger.info("Out YodleeAPIRepository.loginUser()");
		return jsonObject;
	}

	public JSONObject registerUser(String cobrandSessionToken, String newUsername, String newPassword,
			String instanceType, String newEmail) {		
		logger.info("InYodleeAPIRepository.registerUser()");
		logger.debug("cobrandSessionToken :"+cobrandSessionToken+", newUsername :"+newUsername+", newEmail :"+newEmail);
		
		DefaultHttpClient httpclient = new DefaultHttpClient();
		JSONObject jsonObject = null;
		String url = HOST_URI + USER_REGISTER_URL;
		try {
			HttpsURLConnection.setDefaultHostnameVerifier(new NullHostnameVerifier());

			PostMethod pm = new PostMethod(url);
			// Cobrand session token
			pm.addParameter(paramNameCobSessionToken, cobrandSessionToken);

			// New cobrand credentials parameters
			pm.addParameter(paramNameNewUserLogin, newUsername);
			pm.addParameter(paramNameNewUserPassword, newPassword);
			pm.addParameter(paramNameInstanceType, instanceType);
			pm.addParameter(paramNameUserEmail, newEmail);

			HttpClient hc = new HttpClient();
			hc.executeMethod(pm);

			String ydlRes = pm.getResponseBodyAsString();
			logger.debug("Yodlee Response :"+ydlRes);
			jsonObject = new JSONObject(ydlRes);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			httpclient.getConnectionManager().shutdown();
		}
		logger.info("Out YodleeAPIRepository.registerUser()");
		return jsonObject;
	}

	public JSONObject unRegisterUser(String cobrandSessionToken, String userSessionToken) {
		logger.info("InYodleeAPIRepository.unRegisterUser()");
		logger.debug("cobrandSessionToken :"+cobrandSessionToken+", userSessionToken :"+userSessionToken);
		
		DefaultHttpClient httpclient = new DefaultHttpClient();
		JSONObject jsonObject = null;
		String url = HOST_URI + USER_UNREGISTER_URL;
		try {
			HttpsURLConnection.setDefaultHostnameVerifier(new NullHostnameVerifier());

			PostMethod pm = new PostMethod(url);
			pm.addParameter(paramNameCobSessionToken, cobrandSessionToken);
			pm.addParameter(paramNameUserSessionToken, userSessionToken);

			HttpClient hc = new HttpClient();
			hc.executeMethod(pm);

			String ydlRes = pm.getResponseBodyAsString();
			logger.debug("Yodlee Response :"+ydlRes);
			jsonObject = new JSONObject(ydlRes);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			httpclient.getConnectionManager().shutdown();
		}
		logger.info("Out YodleeAPIRepository.unRegisterUser()");
		return jsonObject;
	}

	public Object getItemSummaries(String cobrandSessionToken, String userSessionToken) {
		logger.info("InYodleeAPIRepository.getItemSummaries()");
		logger.debug("cobrandSessionToken : "+cobrandSessionToken+", userSessionToken : "+userSessionToken);
		DefaultHttpClient httpclient = new DefaultHttpClient();
		Object object = null;
		String url = HOST_URI + DATA_SERVICE;
		try {
			HttpsURLConnection.setDefaultHostnameVerifier(new NullHostnameVerifier());

			PostMethod pm = new PostMethod(url);
			pm.addParameter(paramNameCobSessionToken, cobrandSessionToken);
			pm.addParameter(paramNameUserSessionToken, userSessionToken);

			HttpClient hc = new HttpClient();
			hc.executeMethod(pm);

			String ydlRes = pm.getResponseBodyAsString().trim();
			logger.debug("Yodlee Response :"+ydlRes);

			try {
				object = new JSONArray(ydlRes);
			} catch (JSONException je) {
				object = new JSONObject(ydlRes);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			httpclient.getConnectionManager().shutdown();
		}
		logger.info("Out YodleeAPIRepository.getItemSummaries()");
		return object;
	}

	public Object getItemSummariesForSite(String cobrandSessionToken, String userSessionToken, String siteAccId) {
		logger.info("InYodleeAPIRepository.getItemSummariesForSite()");
		logger.debug("cobrandSessionToken :"+cobrandSessionToken+", userSessionToken :"+userSessionToken+", siteAccId :"+siteAccId);
		DefaultHttpClient httpclient = new DefaultHttpClient();

		String url = HOST_URI + ITEM_SUMM_FOR_SITE;
		Object object = null;
		try {
			HttpsURLConnection.setDefaultHostnameVerifier(new NullHostnameVerifier());

			PostMethod pm = new PostMethod(url);
			pm.addParameter(paramNameCobSessionToken, cobrandSessionToken);
			pm.addParameter(paramNameUserSessionToken, userSessionToken);
			pm.addParameter("memSiteAccId", siteAccId);
			pm.addParameter("memSiteAccId.objectInstanceType", "java.lang.Long");

			HttpClient hc = new HttpClient();
			hc.executeMethod(pm);

			String ydlRes = pm.getResponseBodyAsString().trim();
			logger.debug("Yodlee Response :"+ydlRes);

			try {
				object = new JSONArray(ydlRes);
			} catch (JSONException je) {
				object = new JSONObject(ydlRes);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			httpclient.getConnectionManager().shutdown();
		}
		logger.info("Out YodleeAPIRepository.getItemSummariesForSite()");
		return object;
	}

	public Object getAllSiteAccounts(String cobrandSessionToken, String userSessionToken) {
		logger.info("In YodleeAPIRepository.getAllSiteAccounts()");
		logger.debug("cobrandSessionToken : "+cobrandSessionToken+", userSessionToken : "+userSessionToken);
		DefaultHttpClient httpclient = new DefaultHttpClient();

		String url = HOST_URI + GET_ALL_SITE_ACCS;
		Object object = null;
		try {
			HttpsURLConnection.setDefaultHostnameVerifier(new NullHostnameVerifier());

			PostMethod pm = new PostMethod(url);
			pm.addParameter(paramNameCobSessionToken, cobrandSessionToken);
			pm.addParameter(paramNameUserSessionToken, userSessionToken);

			HttpClient hc = new HttpClient();
			hc.executeMethod(pm);

			String ydlRes = pm.getResponseBodyAsString();
			logger.debug("Yodlee Response :"+ydlRes);
			try {
				object = new JSONArray(ydlRes);
			} catch (JSONException je) {
				object = new JSONObject(ydlRes);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			httpclient.getConnectionManager().shutdown();
		}
		logger.info("Out YodleeAPIRepository.getAllSiteAccounts()");
		return object;
	}

	public JSONObject getSiteInfo(String cobrandSessionToken, String userSessionToken, String siteId) {
		logger.info("In YodleeAPIRepository.getSiteInfo()");
		logger.debug("cobrandSessionToken :"+cobrandSessionToken+", userSessionToken :"+userSessionToken+", siteId :"+siteId);
		DefaultHttpClient httpclient = new DefaultHttpClient();
		JSONObject jsonObject = null;
		// String excludeContentServiceInfo = "false";
		String reqSpecifier = "128";
		// String siteId = "16441";

		String url = HOST_URI + GET_SITE_INFO;
		try {
			HttpsURLConnection.setDefaultHostnameVerifier(new NullHostnameVerifier());

			PostMethod pm = new PostMethod(url);
			pm.addParameter(paramNameCobSessionToken, cobrandSessionToken);
			pm.addParameter(paramNameUserSessionToken, userSessionToken);

			// spm.addParameter("siteFilter.excludeContentServiceInfo" ,
			// excludeContentServiceInfo);
			// pm.addParameter("siteFilter.excludeContentServiceInfo.objectInstanceType","java.lang.Boolean");
			pm.addParameter("siteFilter.reqSpecifier", reqSpecifier);
			// pm.addParameter("siteFilter.reqSpecifier.objectInstanceType" ,
			// "java.lang.Integer");
			pm.addParameter("siteFilter.siteId", siteId);
			// pm.addParameter("siteFilter.siteId.objectInstanceType" ,
			// "java.lang.Long");

			HttpClient hc = new HttpClient();
			hc.executeMethod(pm);

			String ydlRes = pm.getResponseBodyAsString();
			logger.debug("Yodlee Response :"+ydlRes);
			jsonObject = new JSONObject(ydlRes);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			httpclient.getConnectionManager().shutdown();
		}
		logger.info("Out YodleeAPIRepository.getSiteInfo()");
		return jsonObject;
	}

	public JSONObject getToken(String cobrandSessionToken, String userSessionToken, String finAppId) {
		logger.info("In YodleeAPIRepository.getToken()");
		logger.debug("cobrandSessionToken :"+cobrandSessionToken+", userSessionToken :"+userSessionToken+", finAppId :"+finAppId);
		DefaultHttpClient httpclient = new DefaultHttpClient();
		JSONObject jsonObject = null;
		String url = HOST_URI + GET_TOKEN;
		try {
			PostMethod pm = new PostMethod(url);
			pm.addParameter(paramNameCobSessionToken, cobrandSessionToken);
			pm.addParameter(paramNameUserSessionToken, userSessionToken);
			pm.addParameter(paramNameFinAppId, finAppId);

			HttpClient hc = new HttpClient();
			hc.executeMethod(pm);

			String ydlRes = pm.getResponseBodyAsString();
			logger.debug("Yodlee Response :"+ydlRes);
			jsonObject = new JSONObject(ydlRes);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			httpclient.getConnectionManager().shutdown();
		}
		logger.info("Out YodleeAPIRepository.getToken()");
		return jsonObject;
	}
	
	public String getItemSummaryForItem1(String  cobrandSessionToken, String  userSessionToken, String memItemId)
	{
		String userSessionToken_LoginForm=null;
		DefaultHttpClient httpclient = new DefaultHttpClient();
		String url = HOST_URI + GET_ITEM_SUMMERY_FOR_ITEM1;		
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

	private static class NullHostnameVerifier implements HostnameVerifier {
		public boolean verify(String hostname, SSLSession session) {
			return true;
		}
	}

	public static void setHOST_URI(String hOST_URI) {
		HOST_URI = hOST_URI;
	}

}