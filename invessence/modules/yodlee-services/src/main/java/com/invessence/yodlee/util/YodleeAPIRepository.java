package com.invessence.yodlee.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class YodleeAPIRepository
{
	private static String HOST_URI;// ="https://rest.developer.yodlee.com/services/srest/restserver/";
	//public static String HOST_URI = "https://yisandbox.yodleeinteractive.com/services/srest/private-sandbox124/";
								   //"https://rest.developer.yodlee.com/services/srest/restserver/";

	private static String COB_LOGIN_URL = "v1.0/authenticate/coblogin";
	private static String USER_LOGIN_URL = "v1.0/authenticate/login";
	private static String ITEM_MTMT_URL = "v1.0/jsonsdk/ItemManagement/getLoginFormForContentService";
	private static String SEARCH_SITE_URL = "v1.0/jsonsdk/SiteTraversal/searchSite";
	private static String USER_REGISTER_URL = "v1.0/jsonsdk/UserRegistration/register3";
	private static String USER_UNREGISTER_URL = "v1.0/jsonsdk/UserRegistration/unregister";
	private static String USER_TRANSAC_SERVICE = "v1.0/jsonsdk/TransactionSearchService/executeUserSearchRequest";
	private static String DATA_SERVICE = "v1.0/jsonsdk/DataService/getItemSummaries";
	private static String GET_SITE_INFO = "v1.0/jsonsdk/SiteTraversal/getSiteInfo";
	private static String GET_ALL_SITES = "v1.0/jsonsdk/SiteTraversal/getAllSites";
	private static String GET_POPULAR_SITES = "v1.0/jsonsdk/SiteTraversal/getPopularSites";
	private static String ITEM_SUMM_FOR_SITE = "v1.0/jsonsdk/DataService/getItemSummariesForSite";
	private static String ADD_SITE_ACC = "v1.0/jsonsdk/SiteAccountManagement/addSiteAccount1";
	
	private static String GET_ALL_SITE_ACCS = "v1.0/jsonsdk/SiteAccountManagement/getAllSiteAccounts";
	
	private static String GET_TOKEN = "v1.0/jsonsdk/OAuthAccessTokenManagementService/getOAuthAccessToken";

	//Common parameters for all APIs except for cobrand login or cobrand creation APIs
	private String paramNameCobSessionToken = "cobSessionToken";
	private String paramNameUserSessionToken = "userSessionToken";
	
	//Cobrand login API parameters
	private String paramNameCobrandLogin = "cobrandLogin";
	private String paramNameCobrandPassword = "cobrandPassword";
	
	private String paramNameFinAppId = "bridgetAppId"; 
	

	//User login API parameters
	private String paramNameUserLogin = "login";
	private String paramNameUserPassword = "password";

	//Create cobrand credentials API parameters
	private String paramNameNewUserLogin = "userCredentials.loginName";
	private String paramNameNewUserPassword = "userCredentials.password";
	private String paramNameInstanceType = "userCredentials.objectInstanceType";
	private String paramNameUserEmail = "userProfile.emailAddress";

	private String paramNamecontainerType = "transactionSearchRequest.containerType";
	private String paramNamehigherFetchLimit = "transactionSearchRequest.higherFetchLimit";
	private String paramNamelowerFetchLimit = "transactionSearchRequest.lowerFetchLimit";
	private String paramNameendNumber = "transactionSearchRequest.resultRange.endNumber";
	private String paramNamestartNumber = "transactionSearchRequest.resultRange.startNumber";
	private String paramNameclientId = "transactionSearchRequest.searchClients.clientId";
	private String paramNameclientName = "transactionSearchRequest.searchClients.clientName";
	private String paramNamecurrencyCode = "transactionSearchRequest.searchFilter.currencyCode";
	private String paramNamefromDate = "transactionSearchRequest.searchFilter.postDateRange.fromDate";
	private String paramNametoDate = "transactionSearchRequest.searchFilter.postDateRange.toDate";
	private String paramNametransactionSplitType = "transactionSearchRequest.searchFilter.transactionSplitType";
	private String paramNameignoreUserInput = "transactionSearchRequest.ignoreUserInput";

	public JSONObject loginCobrand(String cobrandLoginValue,
			String cobrandPasswordValue) {
		DefaultHttpClient httpclient = new DefaultHttpClient();

		String url = HOST_URI + COB_LOGIN_URL;

		System.out.println("Validating Cobrand by Connecting to URL " + url);
		String cobrandSessionToken = null;
		JSONObject jsonObject =null;
		try {
			HttpsURLConnection
					.setDefaultHostnameVerifier(new NullHostnameVerifier());

			PostMethod pm = new PostMethod(url);
			pm.addParameter(paramNameCobrandLogin, cobrandLoginValue);
			pm.addParameter(paramNameCobrandPassword, cobrandPasswordValue);

			HttpClient hc = new HttpClient();
			hc.executeMethod(pm);
			System.out.println(pm.getResponseBodyAsString());
			System.out.println(pm.getResponseBodyAsStream());

			String source = pm.getResponseBodyAsString();

			jsonObject = new JSONObject(source);
			JSONObject cobConvCreds = jsonObject
					.getJSONObject("cobrandConversationCredentials");
			cobrandSessionToken = (String) cobConvCreds.get("sessionToken");

			System.out.println("\n\n"+jsonObject);

			System.out.println("Cobrand Session " + cobrandSessionToken);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			httpclient.getConnectionManager().shutdown();
		}

		return jsonObject;
	}

	public JSONObject loginUser(String cobrandSessionToken, String usernameValue,
			String passwordValue) {
		String userSessionToken = null;
		DefaultHttpClient httpclient = new DefaultHttpClient();
		JSONObject jsonObject =null;
		String url = HOST_URI + USER_LOGIN_URL;
		try {
			HttpsURLConnection
					.setDefaultHostnameVerifier(new NullHostnameVerifier());

			PostMethod pm = new PostMethod(url);
			pm.addParameter(paramNameUserLogin, usernameValue);
			pm.addParameter(paramNameUserPassword, passwordValue);
			pm.addParameter(paramNameCobSessionToken, cobrandSessionToken);

			HttpClient hc = new HttpClient();
			hc.executeMethod(pm);

			String source = pm.getResponseBodyAsString();
			System.out.println("Source :"+source);
			 jsonObject = new JSONObject(source);
	/*		JSONObject userContext = jsonObject.getJSONObject("userContext");
			JSONObject userConvCreds = userContext
					.getJSONObject("conversationCredentials");
			userSessionToken = (String) userConvCreds.get("sessionToken");

			System.out.println(pm.getResponseBodyAsString());*/

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			httpclient.getConnectionManager().shutdown();
		}

		return jsonObject;
	}

	public String getLoginFormDetails(String cobrandSessionToken,
			String userSessionToken) {
		DefaultHttpClient httpclient = new DefaultHttpClient();

		String url = HOST_URI + ITEM_MTMT_URL;
		try {
			HttpsURLConnection
					.setDefaultHostnameVerifier(new NullHostnameVerifier());

			PostMethod pm = new PostMethod(url);
			pm.addParameter(paramNameCobSessionToken, cobrandSessionToken);
			pm.addParameter(paramNameUserSessionToken, userSessionToken);
			pm.addParameter("contentServiceId", "11175");
			pm.addParameter("contentServiceId.objectInstanceType",
					"java.lang.Long");

			HttpClient hc = new HttpClient();
			hc.executeMethod(pm);

			String source = pm.getResponseBodyAsString();

			System.out.println(pm.getResponseBodyAsString());

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			httpclient.getConnectionManager().shutdown();
		}

		return userSessionToken;
	}

	public JSONObject registerUser(String cobrandSessionToken, String newUsername,
			String newPassword, String instanceType, String newEmail) {
		// String userSessionToken=null;
		DefaultHttpClient httpclient = new DefaultHttpClient();
		JSONObject jsonObject =null;
		String url = HOST_URI + USER_REGISTER_URL;
		try {
			HttpsURLConnection
					.setDefaultHostnameVerifier(new NullHostnameVerifier());

			PostMethod pm = new PostMethod(url);
			//Cobrand session token
			pm.addParameter(paramNameCobSessionToken, cobrandSessionToken);
			
			//New cobrand credentials parameters
			pm.addParameter(paramNameNewUserLogin, newUsername);
			pm.addParameter(paramNameNewUserPassword, newPassword);
			pm.addParameter(paramNameInstanceType, instanceType);
			pm.addParameter(paramNameUserEmail, newEmail);

			HttpClient hc = new HttpClient();
			hc.executeMethod(pm);
			
			String source = pm.getResponseBodyAsString();
			jsonObject = new JSONObject(source);
			System.out.println(pm.getResponseBodyAsString());

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			httpclient.getConnectionManager().shutdown();
		}

		return jsonObject;
	}
	
	
	public JSONObject unRegisterUser(String cobrandSessionToken, String userSessionToken) {
		// String userSessionToken=null;
		DefaultHttpClient httpclient = new DefaultHttpClient();
		JSONObject jsonObject =null;
		String url = HOST_URI + USER_UNREGISTER_URL;
		try {
			HttpsURLConnection
					.setDefaultHostnameVerifier(new NullHostnameVerifier());

			PostMethod pm = new PostMethod(url);
			//Cobrand session token
			pm.addParameter(paramNameCobSessionToken, cobrandSessionToken);
			pm.addParameter(paramNameUserSessionToken, userSessionToken);
			
			HttpClient hc = new HttpClient();
			hc.executeMethod(pm);
			
			String source = pm.getResponseBodyAsString();
			jsonObject = new JSONObject(source);
			System.out.println(pm.getResponseBodyAsString());

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			httpclient.getConnectionManager().shutdown();
		}

		return jsonObject;
	}

	public String transactionSearchService(String cobrandSessionToken,
			String userSessionToken, String containerType,
			String higherFetchLimit, String lowerFetchLimit, String endNumber,
			String startNumber, String clientId, String clientName,
			String currencyCode, String fromDate, String toDate,
			String transactionSplitType, String ignoreUserInput) {
		// String userSessionToken=null;
		DefaultHttpClient httpclient = new DefaultHttpClient();

		String url = HOST_URI + USER_TRANSAC_SERVICE;
		try {
			HttpsURLConnection
					.setDefaultHostnameVerifier(new NullHostnameVerifier());

			PostMethod pm = new PostMethod(url);
			pm.addParameter(paramNameCobSessionToken, cobrandSessionToken);
			pm.addParameter(paramNameUserSessionToken, userSessionToken);
			pm.addParameter(paramNamecontainerType, containerType);
			pm.addParameter(paramNamehigherFetchLimit, higherFetchLimit);
			pm.addParameter(paramNamelowerFetchLimit, lowerFetchLimit);
			pm.addParameter(paramNameendNumber, endNumber);
			pm.addParameter(paramNamestartNumber, startNumber);
			pm.addParameter(paramNameclientId, clientId);
			pm.addParameter(paramNameclientName, clientName);
			pm.addParameter(paramNamecurrencyCode, currencyCode);
			pm.addParameter(paramNamefromDate, fromDate);
			pm.addParameter(paramNametoDate, toDate);
			pm.addParameter(paramNametransactionSplitType, transactionSplitType);
			pm.addParameter(paramNameignoreUserInput, ignoreUserInput);

			HttpClient hc = new HttpClient();
			hc.executeMethod(pm);

			// String source=pm.getResponseBodyAsString();
			// JSONObject jsonObject= new JSONObject(source);
			// JSONObject userContext= jsonObject.getJSONObject("userContext");
			// JSONObject userConvCreds=
			// userContext.getJSONObject("conversationCredentials");
			// userSessionToken= (String) userConvCreds.get("sessionToken");

			System.out.println(pm.getResponseBodyAsString());

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			httpclient.getConnectionManager().shutdown();
		}

		return null;
	}

	public JSONArray getItemSummaries(String cobrandSessionToken,
												 String userSessionToken) {
		// String userSessionToken=null;
		DefaultHttpClient httpclient = new DefaultHttpClient();
		JSONArray jsonObject=null;
		String url = HOST_URI + DATA_SERVICE;
		try {
			HttpsURLConnection
				.setDefaultHostnameVerifier(new NullHostnameVerifier());

			PostMethod pm = new PostMethod(url);
			pm.addParameter(paramNameCobSessionToken, cobrandSessionToken);
			pm.addParameter(paramNameUserSessionToken, userSessionToken);

			HttpClient hc = new HttpClient();
			hc.executeMethod(pm);

			/*
			 * String source=pm.getResponseBodyAsString(); JSONObject
			 * jsonObject= new JSONObject(source); JSONObject userContext=
			 * jsonObject.getJSONObject("userContext"); JSONObject
			 * userConvCreds=
			 * userContext.getJSONObject("conversationCredentials");
			 * userSessionToken= (String) userConvCreds.get("sessionToken");
			 */

			String source=pm.getResponseBodyAsString().trim();
			System.out.println("Source :"+source);

			if(source==null || source.isEmpty() || source.trim().equals("{\"key\":{}}"))
			{
				jsonObject = new JSONArray();
			}else{
				jsonObject = new JSONArray(source);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			httpclient.getConnectionManager().shutdown();
		}

		return jsonObject;
	}


	public JSONArray getItemSummariesForSite(String cobrandSessionToken,
			String userSessionToken, String siteAccId) {
		// String userSessionToken=null;
		DefaultHttpClient httpclient = new DefaultHttpClient();

		String url = HOST_URI + ITEM_SUMM_FOR_SITE;
		JSONArray jsonObject=null;
		try {
			HttpsURLConnection
					.setDefaultHostnameVerifier(new NullHostnameVerifier());

			PostMethod pm = new PostMethod(url);
			pm.addParameter(paramNameCobSessionToken, cobrandSessionToken);
			pm.addParameter(paramNameUserSessionToken, userSessionToken);
			pm.addParameter("memSiteAccId", siteAccId);
			pm.addParameter("memSiteAccId.objectInstanceType", "java.lang.Long");

			HttpClient hc = new HttpClient();
			hc.executeMethod(pm);

			/*
			 * String source=pm.getResponseBodyAsString(); JSONObject
			 * jsonObject= new JSONObject(source); JSONObject userContext=
			 * jsonObject.getJSONObject("userContext"); JSONObject
			 * userConvCreds=
			 * userContext.getJSONObject("conversationCredentials");
			 * userSessionToken= (String) userConvCreds.get("sessionToken");
			 */

			String source=pm.getResponseBodyAsString().trim(); 
			System.out.println("Source :"+source);
			
			if(source==null || source.isEmpty() || source.trim().equals("{\"key\":{}}"))
	    	{		    	
	    		jsonObject = new JSONArray();
	    	}else{
	    		jsonObject = new JSONArray(source);
	    	}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			httpclient.getConnectionManager().shutdown();
		}

		return jsonObject;
	}

	public String searchSite(String cobrandSessionToken, String userSessionToken) {
		DefaultHttpClient httpclient = new DefaultHttpClient();

		String url = HOST_URI + SEARCH_SITE_URL;
		try {
			HttpsURLConnection
					.setDefaultHostnameVerifier(new NullHostnameVerifier());

			PostMethod pm = new PostMethod(url);
			pm.addParameter(paramNameCobSessionToken, cobrandSessionToken);
			pm.addParameter(paramNameUserSessionToken, userSessionToken);
			pm.addParameter("siteSearchString", "america");
			// pm.addParameter("siteSearchString.objectInstanceType",
			// "java.lang.String");

			HttpClient hc = new HttpClient();
			hc.executeMethod(pm);

				
			BufferedReader br = new BufferedReader(new InputStreamReader(pm.getResponseBodyAsStream()));
		        String readLine;
		        while(((readLine = br.readLine()) != null)) {
		          System.err.println(readLine);
		      }
			/*String source = pm.getResponseBodyAsString();

			System.out.println(pm.getResponseBodyAsString());*/

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			httpclient.getConnectionManager().shutdown();
		}

		return userSessionToken;
	}

	public JSONArray getAllSites(String cobrandSessionToken,
			String userSessionToken) {
		DefaultHttpClient httpclient = new DefaultHttpClient();

		String url = HOST_URI + GET_ALL_SITES;
		JSONArray jsonObject=null;
		try {
			HttpsURLConnection
					.setDefaultHostnameVerifier(new NullHostnameVerifier());

			PostMethod pm = new PostMethod(url);
			pm.addParameter(paramNameCobSessionToken, cobrandSessionToken);
			pm.addParameter(paramNameUserSessionToken, userSessionToken);

			HttpClient hc = new HttpClient();
			hc.executeMethod(pm);
			String source = pm.getResponseBodyAsString();
			/*String source = pm.getResponseBodyAsString();

			System.out.println(pm.getResponseBodyAsString());*/
		        
		    System.out.println(pm.getResponseBodyAsString());
		    jsonObject = new JSONArray(source);
					
			/*BufferedReader br = null;
			 br = new BufferedReader(new InputStreamReader(pm.getResponseBodyAsStream()));
		        String readLine;
		        while(((readLine = br.readLine()) != null)) {
		          System.err.println(readLine);
		      }
			*/
			/*String source = pm.getResponseBodyAsString();

			System.out.println(pm.getResponseBodyAsString());*/

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			httpclient.getConnectionManager().shutdown();
		}

		return jsonObject;
	}
	
	
	public JSONArray getAllSiteAccounts(String cobrandSessionToken,
			String userSessionToken) {
		DefaultHttpClient httpclient = new DefaultHttpClient();

		String url = HOST_URI + GET_ALL_SITE_ACCS;
		JSONArray jsonObject=null;
		try {
			HttpsURLConnection
					.setDefaultHostnameVerifier(new NullHostnameVerifier());

			PostMethod pm = new PostMethod(url);
			pm.addParameter(paramNameCobSessionToken, cobrandSessionToken);
			pm.addParameter(paramNameUserSessionToken, userSessionToken);

			HttpClient hc = new HttpClient();
			hc.executeMethod(pm);
			/*BufferedReader br = null;
			 br = new BufferedReader(new InputStreamReader(pm.getResponseBodyAsStream()));
		        String readLine;
		        while(((readLine = br.readLine()) != null)) {
		          System.err.println(readLine);
		      }*/
		        String source = pm.getResponseBodyAsString();
			/*String source = pm.getResponseBodyAsString();

			System.out.println(pm.getResponseBodyAsString());*/
		        
		    	
		    	if(source==null || source.isEmpty() || source.trim().equals("{\"key\":{}}"))
		    	{		    	
		    		jsonObject = new JSONArray();
		    	}else{
		    		jsonObject = new JSONArray(source);
		    	}
				 

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			httpclient.getConnectionManager().shutdown();
		}

		return jsonObject;
	}
	
	

	public String addSiteAccount(String cobrandSessionToken,
			String userSessionToken) {
		DefaultHttpClient httpclient = new DefaultHttpClient();

		String url = HOST_URI + ADD_SITE_ACC;
		try {
			HttpsURLConnection
					.setDefaultHostnameVerifier(new NullHostnameVerifier());

			PostMethod pm = new PostMethod(url);
			pm.addParameter(paramNameCobSessionToken, cobrandSessionToken);
			pm.addParameter(paramNameUserSessionToken, userSessionToken);

			pm.addParameter("credentialFields[0].name", "LOGIN");
			pm.addParameter("credentialFields[0].displayName", "Username");
			pm.addParameter("credentialFields[0].isEditable", "true");
			pm.addParameter("credentialFields[0].isOptional", "false");
			pm.addParameter("credentialFields[0].helpText", "22059");
			pm.addParameter("credentialFields[0].valuePattern", "null");
			pm.addParameter("credentialFields[0].defaultValue", "null");
			pm.addParameter("credentialFields[0].value", "AbhangCatlog.site16486.2");
			pm.addParameter("credentialFields[0].validValues", "AbhangCatlog.site16486.2");
			pm.addParameter("credentialFields[0].displayValidValues", "null");
			pm.addParameter("credentialFields[0].valueIdentifier", "LOGIN");
			pm.addParameter("credentialFields[0].valueMask", "LOGIN_FIELD");
			pm.addParameter("credentialFields[0].fieldType", "LOGIN");
			pm.addParameter("credentialFields[0].validationRules", "null");
			pm.addParameter("credentialFields[0].size", "20");
			pm.addParameter("credentialFields[0].maxlength", "40");
			pm.addParameter("credentialFields[0].userProfileMappingExpression","null");
			pm.addParameter("credentialFields[0].fieldErrorCode", "1");
			pm.addParameter("credentialFields[0].fieldErrorMessage", "null");

			pm.addParameter("credentialFields[1].name", "PASSWORD");
			pm.addParameter("credentialFields[1].displayName", "Password");
			pm.addParameter("credentialFields[1].isEditable", "true");
			pm.addParameter("credentialFields[1].isOptional", "false");
			pm.addParameter("credentialFields[1].helpText", "AUS_Row_Name");
			pm.addParameter("credentialFields[1].valuePattern", "null");
			pm.addParameter("credentialFields[1].defaultValue", "null");
			pm.addParameter("credentialFields[1].value", "site16486.2");
			pm.addParameter("credentialFields[1].validValues", "site16486.2");
			pm.addParameter("credentialFields[1].displayValidValues", "null");
			pm.addParameter("credentialFields[1].valueIdentifier", "PASSWORD");
			pm.addParameter("credentialFields[1].valueMask", "LOGIN_FIELD");
			pm.addParameter("credentialFields[1].fieldType", "PASSWORD");
			pm.addParameter("credentialFields[1].validationRules", "null");
			pm.addParameter("credentialFields[1].size", "20");
			pm.addParameter("credentialFields[1].maxlength", "40");
			pm.addParameter("credentialFields[1].userProfileMappingExpression","null");
			pm.addParameter("credentialFields[1].fieldErrorCode", "1");
			pm.addParameter("credentialFields[1].fieldErrorMessage", "null");
			pm.addParameter("credentialFields.objectInstanceType","[Lcom.yodlee.common.FieldInfoSingle;");

			pm.addParameter("siteId", "16486");
			// pm.addParameter("siteId.objectInstanceType", "long");

			HttpClient hc = new HttpClient();
			hc.executeMethod(pm);

			String source = pm.getResponseBodyAsString();

			System.out.println(pm.getResponseBodyAsString());

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			httpclient.getConnectionManager().shutdown();
		}

		return userSessionToken;
	}

	public String getSiteInfo(String cobrandSessionToken,
			String userSessionToken) {
		DefaultHttpClient httpclient = new DefaultHttpClient();

		// String excludeContentServiceInfo = "false";
		String reqSpecifier = "128";
		String siteId = "16441";

		String url = HOST_URI + GET_SITE_INFO;
		try {
			HttpsURLConnection
					.setDefaultHostnameVerifier(new NullHostnameVerifier());

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

			String source = pm.getResponseBodyAsString();

			System.out.println(pm.getResponseBodyAsString());

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			httpclient.getConnectionManager().shutdown();
		}

		return userSessionToken;
	}

	public JSONArray getPopularSites(String cobrandSessionToken,
			String userSessionToken) {
		DefaultHttpClient httpclient = new DefaultHttpClient();

		// String excludeContentServiceInfo = "false";
		/*
		 * String reqSpecifier = "128"; String siteId = "16441";
		 */

		String url = HOST_URI + GET_POPULAR_SITES;
		JSONArray jsonObject=null;;
		try {
			HttpsURLConnection
					.setDefaultHostnameVerifier(new NullHostnameVerifier());

			PostMethod pm = new PostMethod(url);
			pm.addParameter(paramNameCobSessionToken, cobrandSessionToken);
			pm.addParameter(paramNameUserSessionToken, userSessionToken);

			// spm.addParameter("siteFilter.excludeContentServiceInfo" ,
			// excludeContentServiceInfo);
			// pm.addParameter("siteFilter.excludeContentServiceInfo.objectInstanceType","java.lang.Boolean");
			pm.addParameter("siteFilter.siteLevel", "POPULAR_ZIP");
			// pm.addParameter("siteFilter.siteLevel" , "ZIP");
			/*
			 * pm.addParameter("siteFilter.siteLevel.CODE_CITY" , "CA");
			 * pm.addParameter("siteFilter.siteLevel.CODE_STATE" , "TX");
			 * pm.addParameter("siteFilter.siteLevel.CODE_COUNTRY" , "4");
			 */
			// pm.addParameter("siteFilter.reqSpecifier.objectInstanceType" ,
			// "java.lang.Integer");
			// pm.addParameter("siteFilter.siteId" , siteId);
			// pm.addParameter("siteFilter.siteId.objectInstanceType" ,
			// "java.lang.Long");

			HttpClient hc = new HttpClient();
			hc.executeMethod(pm);

			String source = pm.getResponseBodyAsString();

			System.out.println(pm.getResponseBodyAsString());
			 jsonObject = new JSONArray(source);
			

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			httpclient.getConnectionManager().shutdown();
		}

		return jsonObject;
	}
	
	
	public JSONObject getToken(String cobrandSessionToken, String userSessionToken,
			String finAppId) {
		DefaultHttpClient httpclient = new DefaultHttpClient();
		JSONObject jsonObject =null;
		String url = HOST_URI + GET_TOKEN;
		try {
			PostMethod pm = new PostMethod(url);
			pm.addParameter(paramNameCobSessionToken, cobrandSessionToken);
			pm.addParameter(paramNameUserSessionToken, userSessionToken);
			pm.addParameter(paramNameFinAppId, finAppId);
			
			System.out.println("***************************");
			System.out.println(cobrandSessionToken);
			System.out.println(userSessionToken);
			System.out.println(finAppId);
			System.out.println("***************************");

			HttpClient hc = new HttpClient();
			int RC = hc.executeMethod(pm);
			System.out.println("Response Status Code : " + RC);

			String source = pm.getResponseBodyAsString();
			System.out.println("Source :"+source);
			 jsonObject = new JSONObject(source);
			

			System.out.println(pm.getResponseBodyAsString());

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			httpclient.getConnectionManager().shutdown();
		}

		return jsonObject;
	}
	
	public static void main(String[] args) {
		String cobrandLoginValue = "sbCobuabc";
		String cobrandPasswordValue = "b04031a3-98d9-4a83-8341-d2895e5c45a6";

		String usernameValue = "sbMemuabc1";
		String passwordValue = "sbMemuabc1#123";

		YodleeAPIRepository tPost = new YodleeAPIRepository();
		String cobrandSessionToken = null;

		System.out.println("\n\n\n\n loginCobrand ");
		//cobrandSessionToken = tPost.loginCobrand(cobrandLoginValue, cobrandPasswordValue);
		
		System.out.println("\n\n\n\n loginUser ");
		//String userSessionToken = tPost.loginUser(cobrandSessionToken, usernameValue, passwordValue);

		// System.out.println("createUser ----------\n"+value);
//		cobrandPasswordValue = tPost.createAndRegisterUser(cobrandLoginValue);
		
		// System.out.println("createUserData ----------\n"+value);
//		cobrandSessionToken = tPost.loginCobrand(cobrandLoginValue, cobrandPasswordValue);
//		tPost.createUserData(cobrandSessionToken);

		// System.out.println("getUserData ----------\n"+value);
//		 String value = tPost.getUserData(cobrandSessionToken);

		// System.out.println("getLoginFormDetails ----------\n"+value);
		// tPost.getLoginFormDetails(cobrandSessionToken, userSessionToken);

		// System.out.println("\n\n\n\n searchSite ");
		// tPost.searchSite(cobrandSessionToken, userSessionToken);
		
		// System.out.println("\n\n\n\n Register ");
//			String newUsernameValue = "mem";
//			String newPasswordValue = "yodlee123";
//			String newInstanceTypeValue = "com.yodlee.ext.login.PasswordCredentials";
//			String newEmailValue = "a@b.com";
		 
		// tPost.registerUser(cobrandSessionToken, newUsernameValue,
		// newPasswordValue,newInstanceTypeValue, newEmailValue);
		
//		System.out.println("\n Transaction \n\n\n");
//		tPost.transactionSearchService(cobrandSessionToken,userSessionToken,
//		 "all", "500", "1", "500", "1", "1", "DataSearchService", "USD",
//		 "07-09-2011", "07-09-2013", "ALL_TRANSACTION", "true");
		 
		// System.out.println("\n ItemSummaries \n\n\n");
		// tPost.getItemSummaries(cobrandSessionToken,userSessionToken);
		 
		// System.out.println("\n GetSiteInfo \n\n\n");
		// tPost.getSiteInfo(cobrandSessionToken, userSessionToken);
		
		// System.out.println("\n GetAllSites \n\n\n");
		// tPost.getAllSites(cobrandSessionToken, userSessionToken);

		// System.out.println("\n GetPopularSites \n\n\n");
		// tPost.getPopularSites(cobrandSessionToken, userSessionToken);

		// System.out.println("\n GetItemSummariesForSite \n\n\n");
		// tPost.getItemSummariesForSite(cobrandSessionToken,userSessionToken);

		// System.out.println("\n addSiteAccount \n\n\n");
		// tPost.addSiteAccount(cobrandSessionToken,userSessionToken);
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