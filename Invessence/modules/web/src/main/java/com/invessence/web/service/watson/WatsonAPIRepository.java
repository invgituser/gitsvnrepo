package com.invessence.web.service.watson;

import javax.net.ssl.*;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.*;
import org.json.*;
import org.springframework.stereotype.Component;

@Component("watsonAPIRepository")
public class WatsonAPIRepository
{
//	private static final Logger logger = Logger.getLogger(WatsonAPIRepository.class);
	private static String HOST_URI=null;//http://192.168.100.122:8080/SocialNetwork/rest/";
	private static String GET_LINKEDIN_URL = "rest/linkedin/getURL";
	private static String GET_LINKEDIN_AUTH_CODE = "rest/linkedin/authCode/{AUTHCODE}/{TOKEN}/{SECRET}";
	private static String GET_LINKEDIN_PERSONALITY_INSIGHT = "rest/linkedin/personalityInsight";

	public JSONObject getLinkedInUrl(String hostUrl, String callBackUrl) {
		HOST_URI=hostUrl;
		System.out.println("WatsonAPIRepository.getLinkedInUrl");
		System.out.println("callBackUrl = [" + callBackUrl + "]");
		String url = HOST_URI + GET_LINKEDIN_URL;
			System.out.println("url = " + url);
		JSONObject jsonObject = null;
		try {
			PostMethod pm = new PostMethod(url);
			pm.addParameter("callBackUrl",callBackUrl);

			HttpClient hc = new HttpClient();
			hc.executeMethod(pm);

			String ydlRes = pm.getResponseBodyAsString();
			System.out.println("ydlRes = " + ydlRes);

			jsonObject = new JSONObject(ydlRes);
		} catch (Exception e) {
			e.printStackTrace();
		}
//		logger.info("Out YodleeAPIRepository.loginCobrand()");
		return jsonObject;
	}

	public JSONObject getLinkedInProfileSummary(String token, String secret, String oauthVerifier) {
		System.out.println("WatsonAPIRepository.getLinkedInProfileSummary");

		String url = HOST_URI + GET_LINKEDIN_AUTH_CODE.replace("{AUTHCODE}",oauthVerifier).replace("{TOKEN}",token).replace("{SECRET}",secret);
		System.out.println("url = " + url);
		JSONObject jsonObject = null;
		try {

			HttpsURLConnection.setDefaultHostnameVerifier(new NullHostnameVerifier());
			GetMethod pm = new GetMethod(url);

			HttpClient hc = new HttpClient();
			hc.executeMethod(pm);

			String ydlRes = pm.getResponseBodyAsString();
			System.out.println("ydlRes = " + ydlRes);

			jsonObject = new JSONObject(ydlRes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObject;
	}

	public JSONObject getWatsonPersonalityInsight(String summary) {
		System.out.println("WatsonAPIRepository.getLinkedInProfileSummary");

		JSONObject jsonObject = null;
		try {
//			String decodedUrl = URLDecoder.decode(summary, "UTF-8");
//			System.out.println("Dncoded URL " + decodedUrl);
//			System.out.println("---------------");
//			String encodedUrl = URLEncoder.encode(summary, "UTF-8");
//			System.out.println("Encoded URL " + encodedUrl);
//			System.out.println("---------------");
//			encodedUrl = URLEncoder.encode(decodedUrl, "UTF-8");
//			System.out.println("Encoded URL " + encodedUrl);
//			System.out.println("---------------");

			String url = HOST_URI + GET_LINKEDIN_PERSONALITY_INSIGHT;
//				.replace("{SUMMARY}",summary
//				.replaceAll("’","")
//				.replaceAll("[\\\\t\\\\n\\\\r]","")
//				.replaceAll("\\\\u2019","")
//					.replaceAll("\\(","")
//					.replaceAll("\\)","")
//					.replaceAll("\"","")
//					.replaceAll("/","")
//				.replaceAll(" ","%20")
//							//" < > # % { } | \ ^ ~ [ ] `
//					.replaceAll("<","")
//					.replaceAll(">","")
//					.replaceAll("#","")
//					.replaceAll("%","")
//					.replaceAll("\\{","")
//					.replaceAll("}","")
//					.replaceAll("|","")
//					.replaceAll("^","")
//					.replaceAll("~","")
//					.replaceAll("\\[","")
//					.replaceAll("]","")
//					.replaceAll("`","")
//
// ); //\u2019
			System.out.println("url = " + url);

			HttpsURLConnection.setDefaultHostnameVerifier(new NullHostnameVerifier());
			PostMethod pm = new PostMethod(url);
		pm.addParameter("summary",summary);
			HttpClient hc = new HttpClient();
			hc.executeMethod(pm);

			String ydlRes = pm.getResponseBodyAsString();
			System.out.println("ydlRes = " + ydlRes);

			jsonObject = new JSONObject(ydlRes);
//			JSONArray jsonArray=jsonObject.getJSONArray("personality");
//			Double openness=0.0, conscientiousness=0.0, extraversion=0.0, agreeableness=0.0, neuroticism=0.0, riskValue=0.0;
//			for (int j = 0; j < jsonArray.length(); j++) {
//				JSONObject jo = jsonArray.getJSONObject(j);
//				System.out.println(jo.get("trait_id")+" : percentile :"+jo.get("percentile")+" : "+getNumericVal(jo.get("percentile")));
//				if(jo.get("trait_id").toString().trim().equals("big5_openness")){
//					openness=getNumericVal(jo.get("percentile"));
//				}else if(jo.get("trait_id").toString().trim().equals("big5_conscientiousness")){
//					conscientiousness=getNumericVal(jo.get("percentile"));
//				}else if(jo.get("trait_id").toString().trim().equals("big5_extraversion")){
//					extraversion=getNumericVal(jo.get("percentile"));
//				}else if(jo.get("trait_id").toString().trim().equals("big5_agreeableness")){
//					agreeableness=getNumericVal(jo.get("percentile"));
//				}else if(jo.get("trait_id").toString().trim().equals("big5_neuroticism")){
//					neuroticism=getNumericVal(jo.get("percentile"));
//				}
//			}
//			System.out.println("openness = " + openness);
//			System.out.println("conscientiousness = " + conscientiousness);
//			System.out.println("extraversion = " + extraversion);
//			System.out.println("agreeableness = " + agreeableness);
//			System.out.println("neuroticism = " + neuroticism);
//			riskValue=(openness+ conscientiousness+ extraversion+ agreeableness+ (1-neuroticism))/5;
//			System.out.println("riskValue = " + riskValue);
//			riskValue=((openness*100)+ (conscientiousness*100)+ (extraversion*100)+ (agreeableness*100)+ (1-(neuroticism*100)))/5;
//			System.out.println("riskValue = " + riskValue);


		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObject;
	}

	public static String unescape(String data)
	{
		String [] charArr= new String[]{"\\t","\\n","’","\"","\\\\u2019"};
		for (int i = 0; i < charArr.length; i++) {
			data.replaceAll(charArr[i]," ");
		}
		System.out.println("data = [" + data + "]");
		return data;
	}
	public static void main(String[] args)
	{
		try{
		System.out.println("WatsonAPIRepository.main");
//			new WatsonAPIRepository().getLinkedInUrl("http://localhost:8094/SocialNetwork/","http://localhost:8084/pages/consumer/citi/demo/demo.xhtml");

//			JSONObject jsonObject=new WatsonAPIRepository().getWatsonPersonalityInsight("\"I am Web Designer/ Graphic Designer & Front-End Web Developer Form Mumbai, India.\\nI enjoy taking complex problems and turning them into simple and beautiful interface designs. I also love the logic and structure of coding and always strive to write elegant and efficient code, whether it be HTML, CSS or jQuery.\"");
//			JSONObject jsonObject=new WatsonAPIRepository().getWatsonPersonalityInsight("I\u2019m a marketing manager with 10 years of experience in both web and traditional advertising, promotions, events, and campaigns. I have worked on integrated campaigns for major clients such as Etrade, Bank of America, Sony Music, and Microsoft and have been recognized with several awards during my career.\\n\\nUntil recently, I led marketing for XYZ Corp, a software developer focused on middleware for the video game industry. In this role I was focused on B2B marketing, although I have done extensive B2C work in the past. Successes included creating a social media and online advertising campaign that generated enormous media buzz and was key to the successful launch of the Zwango software in 2010. Previous experience includes agency work with XYZ & Partners and Red Dog Marketing.\\n\\nColleagues know me as a highly creative marketer who can always be trusted to come up with a new approach. But I know that the client\\u2019s business comes first, and I never try to impose my ideas on others. Instead, I spend a lot of time understanding the business and the audience before suggesting ideas. I can (and often do) work well alone, but I\\u2019m at my best collaborating with others.\\n\\nI have an MBA from New York University and a BA from the University of Southern California");
//		System.out.println("authURL :"+jsonObject.getString("authURL"));
//		System.out.println("jsonObject = " + jsonObject);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public double getNumericVal(Object val){

		try
		{ if(val!=null && !val.equals(""))
			{
				return Double.parseDouble(val.toString());
			}else{
				System.out.println(val+ " is empty or null");
			}
		}catch(Exception e){
			e.getMessage();
			System.out.println("Value Parsing issue "+val);
		}
		return 0.0;
	}


//	private static String AUTH_URL = "v1.0/authenticate/coblogin";

//	private static String COB_LOGIN_URL = "v1.0/authenticate/coblogin";
//	private static String USER_LOGIN_URL = "v1.0/authenticate/login";
//	private static String USER_REGISTER_URL = "v1.0/jsonsdk/UserRegistration/register3";
//	private static String USER_UNREGISTER_URL = "v1.0/jsonsdk/UserRegistration/unregister";
//	private static String DATA_SERVICE = "v1.0/jsonsdk/DataService/getItemSummaries";
//	private static String GET_SITE_INFO = "v1.0/jsonsdk/SiteTraversal/getSiteInfo";
//	private static String ITEM_SUMM_FOR_SITE = "v1.0/jsonsdk/DataService/getItemSummariesForSite";
//	private static String GET_ALL_SITE_ACCS = "v1.0/jsonsdk/SiteAccountManagement/getAllSiteAccounts";
//	private static String GET_TOKEN = "v1.0/jsonsdk/OAuthAccessTokenManagementService/getOAuthAccessToken";
//	private static String GET_ITEM_SUMMERY_FOR_ITEM1 = "v1.0/jsonsdk/DataService/getItemSummaryForItem1";
//
//	// Common parameters for all APIs except for cobrand login or cobrand
//	// creation APIs
//	private String paramNameCobSessionToken = "cobSessionToken";
//	private String paramNameUserSessionToken = "userSessionToken";
//
//	// Cobrand login API parameters
//	private String paramNameCobrandLogin = "cobrandLogin";
//	private String paramNameCobrandPassword = "cobrandPassword";
//
//	private String paramNameFinAppId = "bridgetAppId";
//
//	// User login API parameters
//	private String paramNameUserLogin = "login";
//	private String paramNameUserPassword = "password";
//
//	// Create cobrand credentials API parameters
//	private String paramNameNewUserLogin = "userCredentials.loginName";
//	private String paramNameNewUserPassword = "userCredentials.password";
//	private String paramNameInstanceType = "userCredentials.objectInstanceType";
//	private String paramNameUserEmail = "userProfile.emailAddress";
//
//	/*
//	 * private String paramNamecontainerType =
//	 * "transactionSearchRequest.containerType"; private String
//	 * paramNamehigherFetchLimit = "transactionSearchRequest.higherFetchLimit";
//	 * private String paramNamelowerFetchLimit =
//	 * "transactionSearchRequest.lowerFetchLimit"; private String
//	 * paramNameendNumber = "transactionSearchRequest.resultRange.endNumber";
//	 * private String paramNamestartNumber =
//	 * "transactionSearchRequest.resultRange.startNumber"; private String
//	 * paramNameclientId = "transactionSearchRequest.searchClients.clientId";
//	 * private String paramNameclientName =
//	 * "transactionSearchRequest.searchClients.clientName"; private String
//	 * paramNamecurrencyCode =
//	 * "transactionSearchRequest.searchFilter.currencyCode"; private String
//	 * paramNamefromDate =
//	 * "transactionSearchRequest.searchFilter.postDateRange.fromDate"; private
//	 * String paramNametoDate =
//	 * "transactionSearchRequest.searchFilter.postDateRange.toDate"; private
//	 * String paramNametransactionSplitType =
//	 * "transactionSearchRequest.searchFilter.transactionSplitType"; private
//	 * String paramNameignoreUserInput =
//	 * "transactionSearchRequest.ignoreUserInput";
//	 */
//
//	public JSONObject loginCobrand(String cobrandLoginValue, String cobrandPasswordValue) {
//		logger.info("In YodleeAPIRepository.loginCobrand()");
//		logger.debug("cobrandLoginValue : "+cobrandLoginValue+", cobrandPasswordValue : "+cobrandPasswordValue);
//		DefaultHttpClient httpclient = new DefaultHttpClient();
//
//		String url = HOST_URI + COB_LOGIN_URL;
//		JSONObject jsonObject = null;
//		try {
//			HttpsURLConnection.setDefaultHostnameVerifier(new NullHostnameVerifier());
//
//			PostMethod pm = new PostMethod(url);
//			pm.addParameter(paramNameCobrandLogin, cobrandLoginValue);
//			pm.addParameter(paramNameCobrandPassword, cobrandPasswordValue);
//
//			HttpClient hc = new HttpClient();
//			hc.executeMethod(pm);
//
//			String ydlRes = pm.getResponseBodyAsString();
//			logger.debug("Yodlee Response :"+ydlRes);
//			jsonObject = new JSONObject(ydlRes);
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			httpclient.getConnectionManager().shutdown();
//		}
//		logger.info("Out YodleeAPIRepository.loginCobrand()");
//		return jsonObject;
//	}
//
//	public JSONObject loginUser(String cobrandSessionToken, String usernameValue, String passwordValue) {
//		logger.info("InYodleeAPIRepository.loginUser()");
//		logger.debug("cobrandSessionToken :"+cobrandSessionToken+", usernameValue :"+usernameValue);
//		DefaultHttpClient httpclient = new DefaultHttpClient();
//		JSONObject jsonObject = null;
//		String url = HOST_URI + USER_LOGIN_URL;
//		try {
//			HttpsURLConnection.setDefaultHostnameVerifier(new NullHostnameVerifier());
//
//			PostMethod pm = new PostMethod(url);
//			pm.addParameter(paramNameUserLogin, usernameValue);
//			pm.addParameter(paramNameUserPassword, passwordValue);
//			pm.addParameter(paramNameCobSessionToken, cobrandSessionToken);
//
//			HttpClient hc = new HttpClient();
//			hc.executeMethod(pm);
//
//			String ydlRes = pm.getResponseBodyAsString();
//			logger.debug("Yodlee Response :"+ydlRes);
//			jsonObject = new JSONObject(ydlRes);
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			httpclient.getConnectionManager().shutdown();
//		}
//		logger.info("Out YodleeAPIRepository.loginUser()");
//		return jsonObject;
//	}
//
//	public JSONObject registerUser(String cobrandSessionToken, String newUsername, String newPassword,
//			String instanceType, String newEmail) {
//		logger.info("InYodleeAPIRepository.registerUser()");
//		logger.debug("cobrandSessionToken :"+cobrandSessionToken+", newUsername :"+newUsername+", newEmail :"+newEmail);
//
//		DefaultHttpClient httpclient = new DefaultHttpClient();
//		JSONObject jsonObject = null;
//		String url = HOST_URI + USER_REGISTER_URL;
//		try {
//			HttpsURLConnection.setDefaultHostnameVerifier(new NullHostnameVerifier());
//
//			PostMethod pm = new PostMethod(url);
//			// Cobrand session token
//			pm.addParameter(paramNameCobSessionToken, cobrandSessionToken);
//
//			// New cobrand credentials parameters
//			pm.addParameter(paramNameNewUserLogin, newUsername);
//			pm.addParameter(paramNameNewUserPassword, newPassword);
//			pm.addParameter(paramNameInstanceType, instanceType);
//			pm.addParameter(paramNameUserEmail, newEmail);
//
//			HttpClient hc = new HttpClient();
//			hc.executeMethod(pm);
//
//			String ydlRes = pm.getResponseBodyAsString();
//			logger.debug("Yodlee Response :"+ydlRes);
//			jsonObject = new JSONObject(ydlRes);
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			httpclient.getConnectionManager().shutdown();
//		}
//		logger.info("Out YodleeAPIRepository.registerUser()");
//		return jsonObject;
//	}
//
//	public JSONObject unRegisterUser(String cobrandSessionToken, String userSessionToken) {
//		logger.info("InYodleeAPIRepository.unRegisterUser()");
//		logger.debug("cobrandSessionToken :"+cobrandSessionToken+", userSessionToken :"+userSessionToken);
//
//		DefaultHttpClient httpclient = new DefaultHttpClient();
//		JSONObject jsonObject = null;
//		String url = HOST_URI + USER_UNREGISTER_URL;
//		try {
//			HttpsURLConnection.setDefaultHostnameVerifier(new NullHostnameVerifier());
//
//			PostMethod pm = new PostMethod(url);
//			pm.addParameter(paramNameCobSessionToken, cobrandSessionToken);
//			pm.addParameter(paramNameUserSessionToken, userSessionToken);
//
//			HttpClient hc = new HttpClient();
//			hc.executeMethod(pm);
//
//			String ydlRes = pm.getResponseBodyAsString();
//			logger.debug("Yodlee Response :"+ydlRes);
//			jsonObject = new JSONObject(ydlRes);
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			httpclient.getConnectionManager().shutdown();
//		}
//		logger.info("Out YodleeAPIRepository.unRegisterUser()");
//		return jsonObject;
//	}
//
//	public Object getItemSummaries(String cobrandSessionToken, String userSessionToken) {
//		logger.info("InYodleeAPIRepository.getItemSummaries()");
//		logger.debug("cobrandSessionToken : "+cobrandSessionToken+", userSessionToken : "+userSessionToken);
//		DefaultHttpClient httpclient = new DefaultHttpClient();
//		Object object = null;
//		String url = HOST_URI + DATA_SERVICE;
//		try {
//			HttpsURLConnection.setDefaultHostnameVerifier(new NullHostnameVerifier());
//
//			PostMethod pm = new PostMethod(url);
//			pm.addParameter(paramNameCobSessionToken, cobrandSessionToken);
//			pm.addParameter(paramNameUserSessionToken, userSessionToken);
//
//			HttpClient hc = new HttpClient();
//			hc.executeMethod(pm);
//
//			String ydlRes = pm.getResponseBodyAsString().trim();
//			logger.debug("Yodlee Response :"+ydlRes);
//
//			try {
//				object = new JSONArray(ydlRes);
//			} catch (JSONException je) {
//				object = new JSONObject(ydlRes);
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			httpclient.getConnectionManager().shutdown();
//		}
//		logger.info("Out YodleeAPIRepository.getItemSummaries()");
//		return object;
//	}
//
//	public Object getItemSummariesForSite(String cobrandSessionToken, String userSessionToken, String siteAccId) {
//		logger.info("InYodleeAPIRepository.getItemSummariesForSite()");
//		logger.debug("cobrandSessionToken :"+cobrandSessionToken+", userSessionToken :"+userSessionToken+", siteAccId :"+siteAccId);
//		DefaultHttpClient httpclient = new DefaultHttpClient();
//
//		String url = HOST_URI + ITEM_SUMM_FOR_SITE;
//		Object object = null;
//		try {
//			HttpsURLConnection.setDefaultHostnameVerifier(new NullHostnameVerifier());
//
//			PostMethod pm = new PostMethod(url);
//			pm.addParameter(paramNameCobSessionToken, cobrandSessionToken);
//			pm.addParameter(paramNameUserSessionToken, userSessionToken);
//			pm.addParameter("memSiteAccId", siteAccId);
//			pm.addParameter("memSiteAccId.objectInstanceType", "java.lang.Long");
//
//			HttpClient hc = new HttpClient();
//			hc.executeMethod(pm);
//
//			String ydlRes = pm.getResponseBodyAsString().trim();
//			logger.debug("Yodlee Response :"+ydlRes);
//
//			try {
//				object = new JSONArray(ydlRes);
//			} catch (JSONException je) {
//				object = new JSONObject(ydlRes);
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			httpclient.getConnectionManager().shutdown();
//		}
//		logger.info("Out YodleeAPIRepository.getItemSummariesForSite()");
//		return object;
//	}
//
//	public Object getAllSiteAccounts(String cobrandSessionToken, String userSessionToken) {
//		logger.info("In YodleeAPIRepository.getAllSiteAccounts()");
//		logger.debug("cobrandSessionToken : "+cobrandSessionToken+", userSessionToken : "+userSessionToken);
//		DefaultHttpClient httpclient = new DefaultHttpClient();
//
//		String url = HOST_URI + GET_ALL_SITE_ACCS;
//		Object object = null;
//		try {
//			HttpsURLConnection.setDefaultHostnameVerifier(new NullHostnameVerifier());
//
//			PostMethod pm = new PostMethod(url);
//			pm.addParameter(paramNameCobSessionToken, cobrandSessionToken);
//			pm.addParameter(paramNameUserSessionToken, userSessionToken);
//
//			HttpClient hc = new HttpClient();
//			hc.executeMethod(pm);
//
//			String ydlRes = pm.getResponseBodyAsString();
//			logger.debug("Yodlee Response :"+ydlRes);
//			try {
//				object = new JSONArray(ydlRes);
//			} catch (JSONException je) {
//				object = new JSONObject(ydlRes);
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			httpclient.getConnectionManager().shutdown();
//		}
//		logger.info("Out YodleeAPIRepository.getAllSiteAccounts()");
//		return object;
//	}
//
//	public JSONObject getSiteInfo(String cobrandSessionToken, String userSessionToken, String siteId) {
//		logger.info("In YodleeAPIRepository.getSiteInfo()");
//		logger.debug("cobrandSessionToken :"+cobrandSessionToken+", userSessionToken :"+userSessionToken+", siteId :"+siteId);
//		DefaultHttpClient httpclient = new DefaultHttpClient();
//		JSONObject jsonObject = null;
//		// String excludeContentServiceInfo = "false";
//		String reqSpecifier = "128";
//		// String siteId = "16441";
//
//		String url = HOST_URI + GET_SITE_INFO;
//		try {
//			HttpsURLConnection.setDefaultHostnameVerifier(new NullHostnameVerifier());
//
//			PostMethod pm = new PostMethod(url);
//			pm.addParameter(paramNameCobSessionToken, cobrandSessionToken);
//			pm.addParameter(paramNameUserSessionToken, userSessionToken);
//
//			// spm.addParameter("siteFilter.excludeContentServiceInfo" ,
//			// excludeContentServiceInfo);
//			// pm.addParameter("siteFilter.excludeContentServiceInfo.objectInstanceType","java.lang.Boolean");
//			pm.addParameter("siteFilter.reqSpecifier", reqSpecifier);
//			// pm.addParameter("siteFilter.reqSpecifier.objectInstanceType" ,
//			// "java.lang.Integer");
//			pm.addParameter("siteFilter.siteId", siteId);
//			// pm.addParameter("siteFilter.siteId.objectInstanceType" ,
//			// "java.lang.Long");
//
//			HttpClient hc = new HttpClient();
//			hc.executeMethod(pm);
//
//			String ydlRes = pm.getResponseBodyAsString();
//			logger.debug("Yodlee Response :"+ydlRes);
//			jsonObject = new JSONObject(ydlRes);
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			httpclient.getConnectionManager().shutdown();
//		}
//		logger.info("Out YodleeAPIRepository.getSiteInfo()");
//		return jsonObject;
//	}
//
//	public JSONObject getToken(String cobrandSessionToken, String userSessionToken, String finAppId) {
//		logger.info("In YodleeAPIRepository.getToken()");
//		logger.debug("cobrandSessionToken :"+cobrandSessionToken+", userSessionToken :"+userSessionToken+", finAppId :"+finAppId);
//		DefaultHttpClient httpclient = new DefaultHttpClient();
//		JSONObject jsonObject = null;
//		String url = HOST_URI + GET_TOKEN;
//		try {
//			PostMethod pm = new PostMethod(url);
//			pm.addParameter(paramNameCobSessionToken, cobrandSessionToken);
//			pm.addParameter(paramNameUserSessionToken, userSessionToken);
//			pm.addParameter(paramNameFinAppId, finAppId);
//
//			HttpClient hc = new HttpClient();
//			hc.executeMethod(pm);
//
//			String ydlRes = pm.getResponseBodyAsString();
//			logger.debug("Yodlee Response :"+ydlRes);
//			jsonObject = new JSONObject(ydlRes);
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			httpclient.getConnectionManager().shutdown();
//		}
//		logger.info("Out YodleeAPIRepository.getToken()");
//		return jsonObject;
//	}
//
//	public String getItemSummaryForItem1(String  cobrandSessionToken, String  userSessionToken, String memItemId)
//	{
//		String userSessionToken_LoginForm=null;
//		DefaultHttpClient httpclient = new DefaultHttpClient();
//		String url = HOST_URI + GET_ITEM_SUMMERY_FOR_ITEM1;
//		try {
//			HttpsURLConnection.setDefaultHostnameVerifier(new NullHostnameVerifier());
//			PostMethod pm = new PostMethod(url);
//			pm.addParameter(paramNameCobSessionToken, cobrandSessionToken);
//			pm.addParameter(paramNameUserSessionToken,userSessionToken);
//			pm.addParameter("itemId", memItemId);
//			pm.addParameter("dex.extentLevels[0]","0");//Please use the data extent guidelines
//			pm.addParameter("dex.extentLevels[1]","0");
//			HttpClient hc = new HttpClient();
//			int RC = hc.executeMethod(pm);
//			userSessionToken_LoginForm=pm.getResponseBodyAsString();
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			httpclient.getConnectionManager().shutdown();
//		}
//		return userSessionToken_LoginForm;
//	}
//
	private static class NullHostnameVerifier implements HostnameVerifier {
		public boolean verify(String hostname, SSLSession session) {
			return true;
		}
	}
//
//	public static void setHOST_URI(String hOST_URI) {
//		HOST_URI = hOST_URI;
//	}

}