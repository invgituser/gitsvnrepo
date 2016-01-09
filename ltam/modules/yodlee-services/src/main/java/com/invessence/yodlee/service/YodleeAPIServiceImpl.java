package com.invessence.yodlee.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.*;
import com.invessence.yodlee.dao.*;
import com.invessence.yodlee.model.*;
import com.invessence.yodlee.util.*;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

public class YodleeAPIServiceImpl implements YodleeAPIService {
	private static final Logger logger = Logger.getLogger(YodleeAPIServiceImpl.class);
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
	private String cobrandSessionToken;
	private String appAbsPath;
	
	@Autowired
	private YodleeAPIRepository yodleeAPIRepo;

	@Autowired
	private UserLogonDAO userLogonDAO;

	@Autowired
	private SiteDetailsDAO siteDetailsDAO;

	@Autowired
	private ItemDetailsDAO itemDetailsDAO;

	@Autowired
	private AccountDetailsDAO accountDetailsDAO;

	@Autowired
	private BankDetailsDAO bankDetailsDAO;

	@Autowired
	private InvestmentDetailsDAO investmentDetailsDAO;

	@Autowired
	private InvestmentHoldingsDAO investmentHoldingsDAO;

	@Autowired
	private InvestmentTransactionsDAO investmentTransactionsDAO;

	@Autowired
	private CardDetailsDAO cardDetailsDAO;

	@Autowired
	private LoanDetailsDAO loanDetailsDAO;

	@Autowired
	private ConsolidateDataDAO consolidateDataDAO;

	private HashMap<Long, UserLogon> loggedInUsers;
	
	public YodleeAPIServiceImpl() {
		logger.info("**********************************************************************************************************");
				
        File jarPath=new File(YodleeAPIServiceImpl.class.getProtectionDomain().getCodeSource().getLocation().getPath());
        String propertiesPath=jarPath.getParentFile().getAbsolutePath();
        logger.info("propertiesPath :"+propertiesPath);
        appAbsPath=propertiesPath.substring(0,propertiesPath.indexOf("WEB-INF"));
        logger.info("appAbsPath :"+appAbsPath);
		System.out.println("appAbsPath :"+appAbsPath);
		loggedInUsers=new HashMap<Long, UserLogon>();
		logger.info("COBRAND_LOGIN :"+Parameters.COBRAND_LOGIN);
	}

	public Map<String, Object> getInvUserList(){
		Map<String, Object> resultMap=null;
		try{
			resultMap=new HashMap<String, Object>();
			List<UserLogon> userLst=userLogonDAO.getInvUserList();
			resultMap.put("userList", userLst);
		}catch(Exception e){
			e.printStackTrace();
		}
		return resultMap;
	}
	
	public Map<String, Object> getUserRegistrationList() {
		Map<String, Object> resultMap=null;
		try{
			resultMap=new HashMap<String, Object>();
			List<UserLogon> userLst=userLogonDAO.getUserLogonList();
			resultMap.put("userList", userLst);
		}catch(Exception e){
			e.printStackTrace();
		}
		return resultMap;
	}

	public Boolean isUserRegisteredAtYodlee(Long invUserId){
		try
		{
			List<UserLogon> ulLst = userLogonDAO.findByWhereCluase("invUserId=" + invUserId);
			return ulLst == null || ulLst.size() == 0?false:true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}
	
	public Map<String, Object> userRegistration(Long invUserId) {
		Map<String, Object> resultMap=null;
		JSONObject jb=null;
		try{
			UserLogon invUserDetails=userLogonDAO.getInvUserDetails(invUserId);
			if(invUserDetails==null){
				resultMap=new HashMap<String, Object>();
				logger.info("User not available in Invessence Database.");
				YodleeError ye=new YodleeError();
				ye.setMessage("User not available in Invessence Database.");
				resultMap.put("errorDetails", ye);
			}else{
				resultMap=new HashMap<String, Object>();
				String password=CommonUtil.passGenerator();

				if(cobrandSessionToken==null){
					coBrandSessionManager();
					if(cobrandSessionToken==null){
						YodleeError ye=new YodleeError(1,null,null,null,"Cobrand Login Issue",1);
						resultMap.put("errorDetails", ye);
						return resultMap;
					}
				}
				
				jb=yodleeAPIRepo.registerUser(cobrandSessionToken, invUserDetails.getUserId(), password, "com.yodlee.ext.login.PasswordCredentials", invUserDetails.getEmail());

				if(jb.has("errorOccurred")){
					YodleeError ye=new YodleeError(1,jb.getString("errorOccurred"),jb.getString("exceptionType"),jb.getString("referenceCode"),jb.getString("message"),1);
					resultMap.put("errorDetails", ye);

				}else{
					if(jb.length()>0){
						UserLogon ur=new UserLogon();

						ur.setUserId(invUserDetails.getUserId());
						ur.setInvUserId(invUserId);
						ur.setPassword(AESencrp.encrypt(password.toString()));
						ur.setEmail(invUserDetails.getEmail());
						ur.setRegisteredOn(CommonUtil.getCurrentTimeStamp());
						ur.setRegisteredBy(invUserId);
						
						userLogonDAO.insertUserLogon(ur);
						logger.info(ur.toString());
						
						JSONObject userConvCreds = jb.getJSONObject("userContext").getJSONObject("conversationCredentials");
						ur.setUserSessionToken((String) userConvCreds.get("sessionToken"));
						loggedInUsers.put(ur.getInvUserId(), ur);
						ur.setPassword(null);
						ur.setConsolidateDatas(null);
						ur.setSiteDetails(null);
						resultMap.put("userDetails", ur);
					}else{
						logger.info("User Registration JSON Object is empty or null!");
					}
				}
			}

		
		}catch(Exception e){			
			return errorDetails(e);
		}
		return resultMap;
	}


	public Map<String, Object> userUnRegistration(Long invUserId) {
		Map<String, Object> resultMap=null;
		JSONObject jb=null;
		try{
			
			resultMap=new HashMap<String, Object>();
			jb=yodleeAPIRepo.unRegisterUser(cobrandSessionToken, loggedInUsers.get(invUserId).getUserSessionToken());
			Boolean errCheck = jb.has("errorOccurred");
			if(errCheck==true){
				YodleeError ye=new YodleeError(1,jb.getString("errorOccurred"),jb.getString("exceptionType"),jb.getString("referenceCode"),jb.getString("message"),1);
				resultMap.put("errorDetails", ye);
			}else{
				UserLogon ur=loggedInUsers.get(invUserId);

				loggedInUsers.remove(ur.getInvUserId());
				userLogonDAO.deleteUserLogon(ur);
				if(jb.length()>0){

				}else{
					logger.info("User UnRegistration JSON Object is empty or null!");
				}
			}
		}catch(Exception e){			
			
			return errorDetails(e);
		}
		return resultMap;
	}


	public Map<String, Object> userLogin(Long invUserId) {
		Map<String, Object> resultMap=null;
		JSONObject jb=null;
		try{
			resultMap=new HashMap<String, Object>();
			if(cobrandSessionToken==null){
				coBrandSessionManager();
				if(cobrandSessionToken==null){
					YodleeError ye=new YodleeError(1,null,null,null,"Cobrand Login Issue",1);
					resultMap.put("errorDetails", ye);
					return resultMap;
				}
			}
			List<UserLogon> ulLst=userLogonDAO.findByWhereCluase("invUserId="+invUserId);
			//List <SiteDetail> sdl= siteDetailsDAO.findByWhereCluase("SITE_ACC_ID="+sd.getSiteAccId());
			if(ulLst==null || ulLst.size()==0){
				logger.info("User not available in YodleeProject Database");
				YodleeError ye=new YodleeError(1,null,null,null,"User not available in YodleeProject Database",1);
				resultMap.put("errorDetails", ye);
			}else{
				UserLogon ur=ulLst.get(0);
				ur.setSiteDetails(null);
				ur.setConsolidateDatas(null);
				jb=yodleeAPIRepo.loginUser(cobrandSessionToken, ur.getUserId(), AESencrp.decrypt(ur.getPassword()));

				//{"Error":[{"errorDetail":"Invalid User Credentials"}]}
				
				if(jb.has("Error")){
					
					JSONArray errJA=jb.getJSONArray("Error");
					int accArrLen=errJA.length();
					if(accArrLen>0){

						for (int j = 0; j < errJA.length(); j++) {
							JSONObject errJO=errJA.getJSONObject(j);							
							YodleeError ye=new YodleeError(1,null,null,null,errJO.getString("errorDetail")==null?null:errJO.getString("errorDetail"),1);							
							resultMap.put("errorDetails", ye);
							break;
						}

					}

				}else if(jb.has("errorOccurred")){
					YodleeError ye=new YodleeError(1,jb.getString("errorOccurred"),jb.getString("exceptionType"),jb.getString("referenceCode"),jb.getString("message"),1);
					resultMap.put("errorDetails", ye);
				}else {
					if(jb.length()>0){

						JSONObject userConvCreds = jb.getJSONObject("userContext").getJSONObject("conversationCredentials");
						ur.setUserSessionToken((String) userConvCreds.get("sessionToken"));
						loggedInUsers.put(ur.getInvUserId(), ur);
						resultMap.put("userDetails", ur);
					}
				}

			}
		}catch(Exception e){
			return errorDetails(e);
		}
		return resultMap;
	}

	public Map<String, Object> getAllSiteAccounts(Long invUserId) {
		Map<String, Object> resultMap=null;
		Object obj=null;
	
		try{
			
			resultMap=new HashMap<String, Object>();
			obj=yodleeAPIRepo.getAllSiteAccounts(cobrandSessionToken,loggedInUsers.get(invUserId).getUserSessionToken());
			//source :{"errorCode":"415","errorDetail":"Token authentication failed for cobrand/user Invalid conversation credentials"}
			if (obj instanceof JSONObject) {
				JSONObject jo = (JSONObject) obj;
				//{"errorCode":"415","errorDetail":"Token authentication failed for cobrand/user Invalid conversation credentials"}
				if(jo.has("errorCode")){
					YodleeError ye=new YodleeError(1,null,null,jo.getString("errorCode"),jo.getString("errorDetail"),1);
					logger.info(jo.getString("errorDetail"));
					resultMap.put("errorDetails", ye);
				}else if(jo.has("key")){
					YodleeError ye=new YodleeError(1,null,null,null,"Sites are not available for this users",1);
					logger.info("Sites are not available for this users");
					resultMap.put("errorDetails", ye);
				}
				
			}else if (obj instanceof JSONArray) {
				JSONArray ja = (JSONArray) obj;	
				List<SiteDetail> siteAccLst=new ArrayList<SiteDetail>();
			
				for (int i = 0; i < ja.length(); i++) {
					SiteDetail sd=new SiteDetail();
					JSONObject jo= ja.getJSONObject(i);
					sd.setSiteAccId(jo.getString("siteAccountId")==null?null:jo.getLong("siteAccountId"));
					
					if(jo.has("siteRefreshInfo")){
						JSONObject siteRefreshJO=jo.getJSONObject("siteRefreshInfo");
						if(jo.has("siteRefreshMode")){
							JSONObject siteRefreshModeJO=jo.getJSONObject("siteRefreshMode");
							sd.setRefreshMode(siteRefreshModeJO.getString("refreshMode")==null?null:siteRefreshModeJO.getString("refreshMode"));
						}
					}
					if(jo.has("siteInfo")){
						JSONObject siteInforJO=jo.getJSONObject("siteInfo");
						sd.setSiteId(siteInforJO.getString("siteId")==null?null:siteInforJO.getLong("siteId"));
						sd.setOrgId(siteInforJO.getString("orgId")==null?null:siteInforJO.getLong("orgId"));
						sd.setSiteName(siteInforJO.getString("defaultDisplayName")==null?null:siteInforJO.getString("defaultDisplayName"));

						JSONObject jsonObject= yodleeAPIRepo.getSiteInfo(cobrandSessionToken,loggedInUsers.get(invUserId).getUserSessionToken(), sd.getSiteId().toString());
					
						File file = new File(appAbsPath+"/resources/yodlee/images/IconImage_" + sd.getSiteId() + ".png");
						if(!file.exists()){
							if(jsonObject.has("defaultFavIcon")){
								JSONArray imageval = (JSONArray) jsonObject.get("defaultFavIcon");
		
		                        byte[] byteArr = new byte[imageval.length()];		
		                        for (int p = 0; p < imageval.length(); p++) {		
		                        	byteArr[p] = (byte)imageval.getInt(p);
								}								
								
								BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file)); 
								out.write(byteArr);								
								out.close();	
							}
						}	
						File logoFile = new File(appAbsPath+"/resources/yodlee/images/LogoImage_" + sd.getSiteId() + ".png");
						if(!logoFile.exists()){									
								
							if(jsonObject.has("defaultSiteLogo")){
								
								JSONObject inJO = (JSONObject) jsonObject.get("defaultSiteLogo");
								if(inJO.has("bytes")){
								
									JSONArray logoVal = (JSONArray) inJO.get("bytes");	
				                    byte[] logoByteArr = new byte[logoVal.length()];	
				                    for (int p = 0; p < logoVal.length(); p++) {	
				                    	logoByteArr[p] = (byte)logoVal.getInt(p);
									}									
									
									BufferedOutputStream logoOut = new BufferedOutputStream(new FileOutputStream(logoFile)); 
									logoOut.write(logoByteArr);								
									logoOut.close();	
									}
								}
							}
								
						}					

					List <SiteDetail> sdl= siteDetailsDAO.findByWhereCluase("siteAccId="+sd.getSiteAccId());
					if(sdl == null || sdl.size()==0){
						sd.setInsertedOn(CommonUtil.getCurrentTimeStamp());
						sd.setInsertedBy(loggedInUsers.get(invUserId).getId());
						UserLogon ul=new UserLogon();
						ul.setId(loggedInUsers.get(invUserId).getId());
						sd.setUserLogon(ul);
						siteDetailsDAO.insertSiteDetails(sd);
					}
					siteAccLst.add(sd);
					//getItemSummariesForSite(sd.getSiteAccId().toString(),invUserId);
					logger.info(jo.getString("siteAccountId")+" : "+jo.getString("isCustom")+sd.getSiteName());
				}

				resultMap.put("siteDetails", siteAccLst);

			}else{
				logger.info("AllSiteAccounts JSON Object is empty or null!");
			}
		}catch(Exception e){
			return errorDetails(e);
		}
		return resultMap;

	}

	public Map<String, Object> getFastLinkDetails(String operation, String siteAccId, Long invUserId) {

		Map<String, Object> resultMap=null;
		JSONObject jo=null;
		try{

			jo=yodleeAPIRepo.getToken(cobrandSessionToken, loggedInUsers.get(invUserId).getUserSessionToken(), Parameters.BRIDGE_APP_ID);
			resultMap=new HashMap<String, Object>();
			//{"errorOccurred":"true","exceptionType":"com.yodlee.core.oauth.exceptions.InvalidApplicationIdentifierException","referenceCode":"_de4e8b2e-29cb-41aa-b8ef-4ade467546d2","message":"100032001"}
			//{"errorCode":"415","errorDetail":"Token authentication failed for cobrand/user Invalid conversation credentials"}
			if(jo.has("errorOccurred")){
				YodleeError ye=new YodleeError(1,jo.getString("errorOccurred"),jo.getString("exceptionType"),jo.getString("referenceCode"),jo.getString("message"),1);
				logger.info(jo.getString("exceptionType")+" : " +jo.getString("message"));
				resultMap.put("errorDetails", ye);
			}else if(jo.has("errorDetail")){
				YodleeError ye=new YodleeError(1,null,null,jo.getString("errorCode"),jo.getString("errorDetail"),1);
				logger.info(jo.getString("errorDetail"));
				resultMap.put("errorDetails", ye);
			}else{
				if(jo.length()>0){
					Map<String, String> map=new HashMap<String, String>();

					map.put("OAUTH_TOKEN", jo.getString("token"));
					map.put("OAUTH_TOKEN_SECRET", jo.getString("tokenSecret"));

					map.put("APPLICATION_KEY", Parameters.APPLICATION_KEY);
					map.put("APPLICATION_TOKEN", Parameters.APPLICATION_TOKEN);

					if(operation.equals("ADD_ACC")){

						map.put("FL_API_URL", Parameters.FL_ADD_ACC_URL);
						map.put("FL_API_PARAM", Parameters.FL_ADD_ACC_PARAM);
					}else if (operation.equals("EDIT_ACC")) {

						map.put("FL_API_URL", Parameters.FL_EDIT_ACC_URL);
						map.put("FL_API_PARAM", Parameters.FL_EDIT_ACC_PARAM.replace("$SITE_ACC_ID$", siteAccId));
					}else if (operation.equals("REFRESH_ACC")) {

						map.put("FL_API_URL", Parameters.FL_REFR_URL);
						map.put("FL_API_PARAM", Parameters.FL_REFR_PARAM.replace("$SITE_ACC_ID$", siteAccId));
					}
					/*map.put("FL_API_URL", FL_ADD_ACC_URL);
					map.put("FL_API_PARAM", FL_ADD_ACC_URL);
					*/
					resultMap.put("flDetails", map);
				}else{
					logger.info("FastLink token JSON Object is empty or null!");
				}
			}


		}catch(Exception e){
			return errorDetails(e);
		}
		return resultMap;
	}



	public void coBrandSessionManager(){
		logger.info("****************************************************************************************");
		logger.info(new Date()+" coBrandSession "+cobrandSessionToken);

		JSONObject jb=null;
		try{
			jb=yodleeAPIRepo.loginCobrand(Parameters.COBRAND_LOGIN, Parameters.COBRAND_PASSWORD);
			//{"Error":[{"errorDetail":"Invalid Cobrand Credentials"}]}
			//{"Error":[{"errorDetail":"The account for cobrand sandbox124 is locked"}]}
			if(jb.has("Error")==true){
				JSONArray errJA=jb.getJSONArray("Error");
				int accArrLen=errJA.length();
				System.out.println(accArrLen+" accArrLen");
				if(accArrLen>0){

					for (int j = 0; j < errJA.length(); j++) {
						JSONObject errJO=errJA.getJSONObject(j);
						YodleeError ye=new YodleeError(1,null,null,null,errJO.getString("errorDetail")==null?null:errJO.getString("errorDetail"),1);
						logger.error(ye.getMessage());
						break;
					}
				}
			}else if(jb.has("errorOccurred")==true){
				YodleeError ye=new YodleeError(1,jb.getString("errorOccurred"),jb.getString("exceptionType"),jb.getString("referenceCode"),jb.getString("message"),1);
				logger.error(ye.getMessage());
			}else {					
				if(jb.has("cobrandConversationCredentials")){
					JSONObject userConvCreds = jb.getJSONObject("cobrandConversationCredentials");
					cobrandSessionToken=(String) userConvCreds.get("sessionToken");					
				}
			}

		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void refreshUserAccDetailsManager(){
		logger.info("****************************************************************************************");
		logger.info(new Date()+" coBrandSession "+cobrandSessionToken);

		JSONObject jb=null;
		Map<String, Object> resultMap=null;
		try{
			resultMap=new HashMap<String, Object>();
			if(cobrandSessionToken==null){
				coBrandSessionManager();
				if(cobrandSessionToken==null){
					YodleeError ye=new YodleeError(1,null,null,null,"Cobrand Login Issue",1);
					resultMap.put("errorDetails", ye);
				}
			}
			List<UserLogon> userLst=userLogonDAO.getUserLogonList();
			Iterator<UserLogon> ul=userLst.iterator();
			while (ul.hasNext()) {
				UserLogon userLogon = (UserLogon) ul.next();				
				
				jb=yodleeAPIRepo.loginUser(cobrandSessionToken, userLogon.getUserId(), AESencrp.decrypt(userLogon.getPassword()));

				//{"Error":[{"errorDetail":"Invalid User Credentials"}]}
				if(jb.has("Error")){

					JSONArray errJA=jb.getJSONArray("Error");
					int accArrLen=errJA.length();
					if(accArrLen>0){

						for (int j = 0; j < errJA.length(); j++) {
							JSONObject errJO=errJA.getJSONObject(j);							
							YodleeError ye=new YodleeError(1,null,null,null,errJO.getString("errorDetail")==null?null:errJO.getString("errorDetail"),1);							
							resultMap.put("errorDetails", ye);
							break;
						}

					}

				}else if(jb.has("errorOccurred")){
					YodleeError ye=new YodleeError(1,jb.getString("errorOccurred"),jb.getString("exceptionType"),jb.getString("referenceCode"),jb.getString("message"),1);
					resultMap.put("errorDetails", ye);
				}else {
					if(jb.length()>0){

						JSONObject userConvCreds = jb.getJSONObject("userContext").getJSONObject("conversationCredentials");
						userLogon.setUserSessionToken((String) userConvCreds.get("sessionToken"));
						loggedInUsers.put(userLogon.getInvUserId(), userLogon);
						refreshUserAccDetails(userLogon.getInvUserId());
					}else{
						logger.info("User JSON Object is empty or null!");
					}
				}
			}			

		}catch(Exception e){
			e.printStackTrace();
		}
	}

	
	public Map<String, Object> errorDetails(Exception ex){
		Map<String, Object> errorDetails=new HashMap<String, Object>();
		try{
			ex.printStackTrace();
			logger.error(ex.getMessage());
			logger.error(CommonUtil.stackTraceToString(ex.getStackTrace()));
			if(ex instanceof NullPointerException){
				YodleeError ye=new YodleeError(1,null,null,null,"Expected object not getting",1);
				errorDetails.put("errorDetails", ye);
			}else if(ex instanceof JSONException){
				YodleeError ye=new YodleeError(1,null,null,null,"JSON Object parsing error",1);
				errorDetails.put("errorDetails", ye);
			}else if(ex instanceof UnknownHostException){
				YodleeError ye=new YodleeError(1,null,null,null,"Unknown Host Exception",1);
				errorDetails.put("errorDetails", ye);
			}else{
				YodleeError ye=new YodleeError(1,null,null,null,"Yodlee Service Technical Issue",1);
				errorDetails.put("errorDetails", ye);
			}
		}catch(Exception e){
			e.printStackTrace();
			logger.error(ex.getMessage());
			YodleeError ye=new YodleeError(1,null,null,null,"Yodlee Service Technical Issue",1);
			errorDetails.put("errorDetails", ye);
		}
		return errorDetails;
	}
	


	public String getCobrandSessionToken() {
		return cobrandSessionToken;
	}

	public void setCobrandSessionToken(String cobrandSessionToken) {
		this.cobrandSessionToken = cobrandSessionToken;
	}

	public HashMap<Long, UserLogon> getLoggedInUsers() {
		return loggedInUsers;
	}

	public void setLoggedInUsers(HashMap<Long, UserLogon> loggedInUsers) {
		this.loggedInUsers = loggedInUsers;
	}
	
	public UserLogonDAO getUserLogonDAO() {
		return userLogonDAO;
	}

	public void setUserLogonDAO(UserLogonDAO userLogonDAO) {
		this.userLogonDAO = userLogonDAO;
	}

	public SiteDetailsDAO getSiteDetailsDAO() {
		return siteDetailsDAO;
	}

	public void setSiteDetailsDAO(SiteDetailsDAO siteDetailsDAO) {
		this.siteDetailsDAO = siteDetailsDAO;
	}

	public ItemDetailsDAO getItemDetailsDAO() {
		return itemDetailsDAO;
	}

	public void setItemDetailsDAO(ItemDetailsDAO itemDetailsDAO) {
		this.itemDetailsDAO = itemDetailsDAO;
	}

	public AccountDetailsDAO getAccountDetailsDAO() {
		return accountDetailsDAO;
	}

	public void setAccountDetailsDAO(AccountDetailsDAO accountDetailsDAO) {
		this.accountDetailsDAO = accountDetailsDAO;
	}

	public YodleeAPIRepository getYodleeAPI() {
		return yodleeAPIRepo;
	}

	public void setYodleeAPI(YodleeAPIRepository yodleeAPI) {
		this.yodleeAPIRepo = yodleeAPI;
	}


	public Map<String, Object> getUserAccountsDetail(Long invUserId){

		Map<String, Object> resultMap=null;
		try{
			List<UserLogon> ulLst=userLogonDAO.findByWhereCluase("invUserId="+invUserId);
			resultMap=new HashMap<String, Object>();
			//List <SiteDetail> sdl= siteDetailsDAO.findByWhereCluase("SITE_ACC_ID="+sd.getSiteAccId());
			if(ulLst==null || ulLst.size()==0){
				YodleeError ye=new YodleeError(1,null,null,null,"User not available in YodleeProject Database",1);
				logger.info("User not available in YodleeProject Database");
				resultMap.put("errorDetails", ye);
			}else{
				UserLogon ur=ulLst.get(0);

				List<ConsolidateData> consDataList=consolidateDataDAO.findByWhereCluase("USER_LOG_ID="+ur.getId()+" ORDER BY itemDetail.contServName");
				resultMap=new HashMap<String, Object>();
				if(consDataList==null || consDataList.size()==0){
					YodleeError ye=new YodleeError(1,null,null,null,"Consolidate details are not available for this User",1);
					logger.info("Consolidate details are not available for this User");
					resultMap.put("errorDetails", ye);
				}else{
					
					resultMap.put("consDataList", consDataList);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return resultMap;
	}


	public Map<String, Object> refreshUserAccDetails(Long invUserId) {
		Map<String, Object> resultMap=null;
		Object obj=null;
		try{
			resultMap=new HashMap<String, Object>();

			getAllSiteAccounts(invUserId);

			logger.info("******************************************************** getItemSummaries *********************************************************");
			obj=yodleeAPIRepo.getItemSummaries(cobrandSessionToken,loggedInUsers.get(invUserId).getUserSessionToken());

			if (obj instanceof JSONObject) {
					JSONObject jo = (JSONObject) obj;
					//{"errorCode":"415","errorDetail":"Token authentication failed for cobrand/user Invalid conversation credentials"}
					if(jo.has("errorCode")){
						YodleeError ye=new YodleeError(1,null,null,jo.getString("errorCode"),jo.getString("errorDetail"),1);
						logger.info(jo.getString("errorDetail"));
						resultMap.put("errorDetails", ye);
					}else if(jo.has("key")){
						YodleeError ye=new YodleeError(1,null,null,null,"Sites are not available for this users",1);
						logger.info("Sites are not available for this users");
						resultMap.put("errorDetails", ye);
					}
					
				}else if (obj instanceof JSONArray) {
			
					JSONArray ja = (JSONArray) obj;	
					int siteAccLen=ja.length();
					if(siteAccLen==0){
						YodleeError ye=new YodleeError(1,null,null,null,"User does't have summaries site details",1);
						logger.info("User does't have summaries site details");
						resultMap.put("errorDetails", ye);
					}else if(siteAccLen>0){
		
						for (int i = 0; i < ja.length(); i++) {
							JSONObject jo= ja.getJSONObject(i);
							String siteAccId=jo.getString("memSiteAccId");
							logger.info("siteAccId :"+siteAccId);
		
							List<SiteDetail> sdLst=siteDetailsDAO.findByWhereCluase("siteAccId="+siteAccId);
							if(sdLst==null || sdLst.size()==0){
								logger.info("Invessence Site Details object get Null");
							}else{
		
								SiteDetail sd=sdLst.get(0);
								
								ItemDetail id=new ItemDetail();
								id.setItemId(jo.getString("itemId")==null?null:jo.getLong("itemId"));
								id.setItemDispName(jo.getString("itemDisplayName")==null?null:jo.getString("itemDisplayName"));
								id.setContServId(jo.getString("contentServiceId")==null?null:jo.getLong("contentServiceId"));
								logger.info("contentServiceId :"+jo.getLong("contentServiceId"));
								if(jo.has("contentServiceInfo")){
									JSONObject contentServiceJO=jo.getJSONObject("contentServiceInfo");
									if(contentServiceJO.has("containerInfo")){
										JSONObject containerInfoJO=contentServiceJO.getJSONObject("containerInfo");
										id.setContServName(containerInfoJO.getString("containerName")==null?null:containerInfoJO.getString("containerName"));
		
										List<ItemDetail> itemLst=itemDetailsDAO.findByWhereCluase("itemId="+id.getItemId());
										if(itemLst==null || itemLst.size()==0){
											id.setInsertedOn(CommonUtil.getCurrentTimeStamp());
											id.setInsertedBy(loggedInUsers.get(invUserId).getId());
											SiteDetail siteDetails=new SiteDetail();
											siteDetails.setId(sd.getId());
											id.setSiteDetail(siteDetails);
											itemDetailsDAO.insertItemDetails(id);
										}else{
											id.setId(itemLst.get(0).getId());
										}
		
										logger.info("containerName: "+id.getContServName());
		
										if(id.getContServName().equals("bank")){
		
											if(jo.has("itemData")){
		
												JSONObject itemDataJO=jo.getJSONObject("itemData");
												if(itemDataJO.has("accounts")){
													JSONArray accountsJA=itemDataJO.getJSONArray("accounts");
		
													int accArrLen=accountsJA.length();
													if(accArrLen>0){
		
														for (int j = 0; j < accountsJA.length(); j++) {
		
															JSONObject accJO= accountsJA.getJSONObject(j);
		
															AccountDetail ad=new AccountDetail();
															ad.setItemDetail(id);
		
															//id.setContServName(siteRefreshModeJO.getString("containerName")==null?null:siteRefreshModeJO.getString("containerName"));
															if(accJO.has("accountId")){ad.setAccId(accJO.getString("accountId")==null?null:accJO.getLong("accountId"));}
															if(accJO.has("itemAccountId")){ad.setItemAccId(accJO.getString("itemAccountId")==null?null:accJO.getLong("itemAccountId"));}
															if(accJO.has("accountName")){ad.setAccName(accJO.getString("accountName")==null?null:accJO.getString("accountName"));}
		
															ad.setInsertedOn(CommonUtil.getCurrentTimeStamp());
															ad.setInsertedBy(loggedInUsers.get(invUserId).getId());
															List<AccountDetail> accLst=accountDetailsDAO.findByWhereCluase("itemAccId="+ad.getItemAccId());
															if(accLst==null || accLst.size()==0){
																accountDetailsDAO.insertAccountDetails(ad);
															}else{
																ad.setId(accLst.get(0).getId());
															}
				
															BankDetail bd=new BankDetail();
															bd.setAccountDetail(ad);
															if(accJO.has("accountHolder")){bd.setAccHolder(accJO.getString("accountHolder")==null?null:accJO.getString("accountHolder"));}
															if(accJO.has("accountName")){bd.setAccName(accJO.getString("accountName")==null?null:accJO.getString("accountName"));}
		
															if(accJO.has("accountNumber")){bd.setAccNum(accJO.getString("accountNumber")==null?null:accJO.getString("accountNumber"));}
															if(accJO.has("acctType")){bd.setAccType(accJO.getString("acctType")==null?null:accJO.getString("acctType"));}
		
															if(accJO.has("asOfDate")){
																JSONObject adnJO=accJO.getJSONObject("asOfDate");
																if(adnJO.has("date")){
																	bd.setAsOfDate(adnJO.getString("date")==null || adnJO.getString("date").equals("{}")?null:sdf.parse(adnJO.getString("date")));
																}
															}
															if(accJO.has("maturityDate")){
																JSONObject adnJO=accJO.getJSONObject("maturityDate");
																if(adnJO.has("date")){
																	bd.setMatDate(adnJO.getString("date")==null || adnJO.getString("date").equals("{}")?null:sdf.parse(adnJO.getString("date")));
																}
															}
		
															if(accJO.has("accountName")){bd.setAccName(accJO.getString("accountName")==null?null:accJO.getString("accountName"));}
		
															/*if(accJO.has("accountDisplayName")){
																JSONObject adnJO=accJO.getJSONObject("accountDisplayName");
																bd.set(adnJO.getString("defaultNormalAccountName")==null?null:adnJO.getString("defaultNormalAccountName"));
															}*/
		
															if(accJO.has("accountClassification")){
																JSONObject adnJO=accJO.getJSONObject("accountClassification");
																bd.setAccClassification(adnJO.getString("accountClassification")==null?null:adnJO.getString("accountClassification"));
															}
		
															if(accJO.has("availableBalance")){
																JSONObject adnJO=accJO.getJSONObject("availableBalance");
																bd.setAvailBal(adnJO.getString("amount")==null?null:adnJO.getDouble("amount"));
															}
		
															if(accJO.has("currentBalance")){
																JSONObject adnJO=accJO.getJSONObject("currentBalance");
																bd.setCurBal(adnJO.getString("amount")==null?null:adnJO.getDouble("amount"));
															}
		
															bd.setInsertedOn(CommonUtil.getCurrentTimeStamp());
															bd.setInsertedBy(loggedInUsers.get(invUserId).getId());
															//bankDetailsDAO.insertBankDetails(bd);
															List<BankDetail> bankDataLst=bankDetailsDAO.findByWhereCluase(" ACC_DET_ID=?", new Object[]{ad.getId()});
															
															if(bankDataLst==null || bankDataLst.size()==0){		
																bankDetailsDAO.insertBankDetails(bd);
															}else{
																bd.setId(bankDataLst.get(0).getId());
																bd.setUpdatedOn(CommonUtil.getCurrentTimeStamp());
																bd.setUpdatedBy(loggedInUsers.get(invUserId).getId());
																bankDetailsDAO.updateBankDetails(bd);
															}
		
															
															logger.info(loggedInUsers.get(invUserId).getId()+" : "+sd.getId()+" : "+id.getId()+" : "+ad.getId());
															List<ConsolidateData> conDataLst=consolidateDataDAO.findByWhereCluase(" USER_LOG_ID=? AND  SITE_DET_ID=? AND ITEM_DET_ID=? AND ACC_DET_ID=?", new Object[]{loggedInUsers.get(invUserId).getId(),sd.getId(),id.getId(),ad.getId()});
		
															if(conDataLst==null || conDataLst.size()==0){
		
																UserLogon ul=new UserLogon();
																ul.setId(loggedInUsers.get(invUserId).getId());
																ConsolidateData consData=new ConsolidateData(ul, sd, id, ad, bd.getId(),bd.getAccType(), bd.getAvailBal(), CommonUtil.getCurrentTimeStamp(), loggedInUsers.get(invUserId).getId());
		
																consolidateDataDAO.insertConsolidateData(consData);
															}else{
		
																ConsolidateData consData=conDataLst.get(0);
																consData.setPfolioDetId(bd.getId());
																consData.setAccType(bd.getAccType());
																consData.setAvailBal(bd.getAvailBal());
																consData.setUpdatedOn(CommonUtil.getCurrentTimeStamp());
																consData.setUpdatedBy(loggedInUsers.get(invUserId).getId());
																consolidateDataDAO.updateConsolidateData(consData);
															}
//		
														}
		
													}
												}
											}
										}
		
		
										if(id.getContServName().equals("credits")){
		
											if(jo.has("itemData")){
		
												JSONObject itemDataJO=jo.getJSONObject("itemData");
												if(itemDataJO.has("accounts")){
													JSONArray accountsJA=itemDataJO.getJSONArray("accounts");
		
													int accArrLen=accountsJA.length();
													if(accArrLen>0){
		
														for (int j = 0; j < accountsJA.length(); j++) {
		
															JSONObject accJO= accountsJA.getJSONObject(j);
		
															AccountDetail ad=new AccountDetail();
															ad.setItemDetail(id);
		
															//id.setContServName(siteRefreshModeJO.getString("containerName")==null?null:siteRefreshModeJO.getString("containerName"));
															if(accJO.has("accountId")){ad.setAccId(accJO.getString("accountId")==null?null:accJO.getLong("accountId"));}
															if(accJO.has("itemAccountId")){ad.setItemAccId(accJO.getString("itemAccountId")==null?null:accJO.getLong("itemAccountId"));}
															if(accJO.has("accountName")){ad.setAccName(accJO.getString("accountName")==null?null:accJO.getString("accountName"));}
		
															ad.setInsertedOn(CommonUtil.getCurrentTimeStamp());
															ad.setInsertedBy(loggedInUsers.get(invUserId).getId());
															List<AccountDetail> accLst=accountDetailsDAO.findByWhereCluase("itemAccId="+ad.getItemAccId());
															if(accLst==null || accLst.size()==0){
																accountDetailsDAO.insertAccountDetails(ad);
															}else{
																ad.setId(accLst.get(0).getId());
															}
		
		
															CardDetail cardDet=new CardDetail();
															cardDet.setAccountDetail(ad);
															if(accJO.has("accountHolder")){cardDet.setAccHolder(accJO.getString("accountHolder")==null?null:accJO.getString("accountHolder"));}
															if(accJO.has("accountName")){cardDet.setAccName(accJO.getString("accountName")==null?null:accJO.getString("accountName"));}
															if(accJO.has("accountNumber")){cardDet.setAccNum(accJO.getString("accountNumber")==null?null:accJO.getString("accountNumber"));}
															if(accJO.has("acctType")){cardDet.setAccType(accJO.getString("acctType")==null?null:accJO.getString("acctType"));}
		
															if(accJO.has("dueDate")){
																JSONObject adnJO=accJO.getJSONObject("dueDate");
																if(adnJO.has("date")){
																	cardDet.setDueDate(adnJO.getString("date")==null || adnJO.getString("date").equals("{}")?null:sdf.parse(adnJO.getString("date")));
																}
															}
															if(accJO.has("lastPaymentDate")){
																JSONObject adnJO=accJO.getJSONObject("lastPaymentDate");
																if(adnJO.has("date")){
																	cardDet.setLastPayDate(adnJO.getString("date")==null || adnJO.getString("date").equals("{}")?null:sdf.parse(adnJO.getString("date")));
																}
															}
		
															if(accJO.has("accountClassification")){
																JSONObject adnJO=accJO.getJSONObject("accountClassification");
																cardDet.setAccClassification(adnJO.getString("accountClassification")==null?null:adnJO.getString("accountClassification"));
															}
		
															if(accJO.has("availableCash")){
																JSONObject adnJO=accJO.getJSONObject("availableCash");
																cardDet.setAvailCash(adnJO.getString("amount")==null?null:adnJO.getDouble("amount"));
															}
		
															if(accJO.has("availableCredit")){
																JSONObject adnJO=accJO.getJSONObject("availableCredit");
																cardDet.setAvailCredit(adnJO.getString("amount")==null?null:adnJO.getDouble("amount"));
															}
		
		
															if(accJO.has("lastPayment")){
																JSONObject adnJO=accJO.getJSONObject("lastPayment");
																cardDet.setLastPay(adnJO.getString("amount")==null?null:adnJO.getDouble("amount"));
															}
		
		
															if(accJO.has("totalCashLimit")){
																JSONObject adnJO=accJO.getJSONObject("totalCashLimit");
																cardDet.setTotCashLimit(adnJO.getString("amount")==null?null:adnJO.getDouble("amount"));
															}
		
															if(accJO.has("totalCreditLine")){
																JSONObject adnJO=accJO.getJSONObject("totalCreditLine");
																cardDet.setTotCreditLine(adnJO.getString("amount")==null?null:adnJO.getDouble("amount"));
															}
		
															if(accJO.has("runningBalance")){
																JSONObject adnJO=accJO.getJSONObject("runningBalance");
																cardDet.setRunningBal(adnJO.getString("amount")==null?null:adnJO.getDouble("amount"));
															}
		
															cardDet.setInsertedOn(CommonUtil.getCurrentTimeStamp());
															cardDet.setInsertedBy(loggedInUsers.get(invUserId).getId());
															//cardDetailsDAO.insertCardDetails(cardDet);
															List<CardDetail> cardDataLst=cardDetailsDAO.findByWhereCluase(" ACC_DET_ID=?", new Object[]{ad.getId()});
															
															if(cardDataLst==null || cardDataLst.size()==0){		
																cardDetailsDAO.insertCardDetails(cardDet);
															}else{
																cardDet.setId(cardDataLst.get(0).getId());
																cardDet.setUpdatedOn(CommonUtil.getCurrentTimeStamp());
																cardDet.setUpdatedBy(loggedInUsers.get(invUserId).getId());
																cardDetailsDAO.updateCardDetails(cardDet);
															}
															
															
															if(accJO.has("cardStatements")){
																JSONArray accStmtJA=accJO.getJSONArray("cardStatements");
		
		
																int accTranLen=accStmtJA.length();
																if(accTranLen>0){
																	for (int a = 0; a < accStmtJA.length(); a++) {
		
																		JSONObject accTranJO= accStmtJA.getJSONObject(a);
																	/*logger.info("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
																	logger.info("accTranJO: "+accTranJO);
		*/
																		/*CardStatement crdStmt=new CardStatement();
																		
																		crdStmt.setAccHolder(accHolder);
																		crdStmt.setAccName(accName);
																		crdStmt.setAccNum(accNum);
																		crdStmt.setAccountDetail(accountDetail);
																		crdStmt.setAccType(accType);
																		crdStmt.setAmtDue(amtDue);
																		crdStmt.setApr(apr);
																		crdStmt.setAvailCash(availCash);
																		crdStmt.setAvailCredit(availCredit);
																		crdStmt.setBillDate(billDate);
																		crdStmt.setBillingPrdEnd(billingPrdEnd);
																		crdStmt.setBillingPrdStart(billingPrdStart);
																		crdStmt.setCashAdvance(cashAdvance);
																		crdStmt.setCredit(credit);
																		crdStmt.setDueDate(dueDate);
																		crdStmt.setEndingBal(endingBal);
																		crdStmt.setFinanceCharges(financeCharges);
																		crdStmt.setInsertedBy(insertedBy);
																		crdStmt.setInsertedOn(insertedOn);
																		crdStmt.setIntPaidThisPrd(intPaidThisPrd);
																		crdStmt.setIntPaidYtd(intPaidYtd);
																		crdStmt.setLastPay(lastPay);
																		crdStmt.setLastPayDate(lastPayDate);
																		crdStmt.setMinPay(minPay);
																		crdStmt.setNewCharges(newCharges);
																		crdStmt.setPastDueAmt(pastDueAmt);
																		crdStmt.setPays(pays);
																		crdStmt.setPrevAmtDue(prevAmtDue);
																		crdStmt.setPrevEndingBal(prevEndingBal);
																		crdStmt.setTotCashLimit(totCashLimit);
																		*/
																		/*
																		
																		InvestmentTransaction invTran=new InvestmentTransaction();
																		invTran.setAccountDetail(ad);
		
																		if(accTranJO.has("amount")){
																			JSONObject intJO=accTranJO.getJSONObject("amount");
																			invTran.setAmt(intJO.getString("amount")==null?null:intJO.getDouble("amount"));
																		}
																		if(accTranJO.has("commission")){
																			JSONObject intJO=accTranJO.getJSONObject("commission");
																			invTran.setCommission(intJO.getString("amount")==null?null:intJO.getString("amount"));
																		}
																		if(accTranJO.has("cusipNumber")){
																			invTran.setCusipNum(accTranJO.getString("cusipNumber")==null?null:accTranJO.getString("cusipNumber"));
																		}
																		if(accTranJO.has("description")){
																			invTran.setDescription(accTranJO.getString("description")==null?null:accTranJO.getString("description"));
																		}if(accTranJO.has("price")){
																			JSONObject intJO=accTranJO.getJSONObject("price");
																			invTran.setPrice(intJO.getString("amount")==null?null:intJO.getDouble("amount"));
																		}if(accTranJO.has("quantity")){
																			invTran.setQuantity(accTranJO.getString("quantity")==null?null:accTranJO.getDouble("quantity"));
																		}if(accTranJO.has("secFee")){
																			JSONObject intJO=accTranJO.getJSONObject("secFee");
																			invTran.setSecFee(intJO.getString("amount")==null?null:intJO.getDouble("amount"));
																		}if(accTranJO.has("settleDate")){
																			JSONObject intJO=accTranJO.getJSONObject("settleDate");
																			if(intJO.has("date")){
																				invTran.setSettleDate(intJO.getString("date")==null || intJO.getString("date").equals("{}")?null:sdf.parse(intJO.getString("date")));
																			}
																		}
		
																		if(accTranJO.has("symbol")){
																			invTran.setSymbol(accTranJO.getString("symbol")==null?null:accTranJO.getString("symbol"));
																		}if(accTranJO.has("transactionBaseType")){
																			invTran.setTranBaseType(accTranJO.getString("transactionBaseType")==null?null:accTranJO.getString("transactionBaseType"));
																		}if(accTranJO.has("transDate")){
																			JSONObject intJO=accTranJO.getJSONObject("transDate");
																			if(intJO.has("date")){
																				invTran.setTranDate(intJO.getString("date")==null || intJO.getString("date").equals("{}")?null:sdf.parse(intJO.getString("date")));
																			}
																		}
																		if(accTranJO.has("transactionType")){
																			invTran.setTranType(accTranJO.getString("transactionType")==null?null:accTranJO.getString("transactionType"));
																		}
																		invTran.setInsertedBy(loggedInUsers.get(invUserId).getId());
																		invTran.setInsertedOn(CommonUtil.getCurrentTimeStamp());
		
																		investmentTransactionsDAO.insertInvestmentTransactions(invTran);*/
		
																	}
																}
															}
															
															
		
															logger.info(loggedInUsers.get(invUserId).getId()+" : "+sd.getId()+" : "+id.getId()+" : "+ad.getId());
															List<ConsolidateData> conDataLst=consolidateDataDAO.findByWhereCluase(" USER_LOG_ID=? AND  SITE_DET_ID=? AND ITEM_DET_ID=? AND ACC_DET_ID=?", new Object[]{loggedInUsers.get(invUserId).getId(),sd.getId(),id.getId(),ad.getId()});
		
															if(conDataLst==null || conDataLst.size()==0){
																
																UserLogon ul=new UserLogon();
																ul.setId(loggedInUsers.get(invUserId).getId());
																ConsolidateData consData=new ConsolidateData(ul, sd, id, ad, cardDet.getId(),cardDet.getAccType(),cardDet.getRunningBal(), CommonUtil.getCurrentTimeStamp(), loggedInUsers.get(invUserId).getId());
																consolidateDataDAO.insertConsolidateData(consData);
															}else{
		
																ConsolidateData consData=conDataLst.get(0);
																consData.setPfolioDetId(cardDet.getId());
																consData.setAccType(cardDet.getAccType());
																consData.setAvailBal(cardDet.getRunningBal());
																consData.setUpdatedOn(CommonUtil.getCurrentTimeStamp());
																consData.setUpdatedBy(loggedInUsers.get(invUserId).getId());
																consolidateDataDAO.updateConsolidateData(consData);
															}
		
														}
		
		
													}
												}
											}
										}
		
										if(id.getContServName().equals("stocks")){
		
											if(jo.has("itemData")){
		
												JSONObject itemDataJO=jo.getJSONObject("itemData");
												if(itemDataJO.has("accounts")){
													JSONArray accountsJA=itemDataJO.getJSONArray("accounts");
		
		
													int accArrLen=accountsJA.length();
													if(accArrLen>0){
		
														for (int j = 0; j < accountsJA.length(); j++) {
		
															JSONObject accJO= accountsJA.getJSONObject(j);
		
															AccountDetail ad=new AccountDetail();
															ad.setItemDetail(id);
		
															//id.setContServName(siteRefreshModeJO.getString("containerName")==null?null:siteRefreshModeJO.getString("containerName"));
															ad.setAccId(accJO.getString("accountId")==null?null:accJO.getLong("accountId"));
															ad.setItemAccId(accJO.getString("itemAccountId")==null?null:accJO.getLong("itemAccountId"));
															if(accJO.has("accountDisplayName")){
																JSONObject adnJO=accJO.getJSONObject("accountDisplayName");
																ad.setAccName(adnJO.getString("defaultNormalAccountName")==null?null:adnJO.getString("defaultNormalAccountName"));
															}
		
															ad.setInsertedOn(CommonUtil.getCurrentTimeStamp());
															ad.setInsertedBy(loggedInUsers.get(invUserId).getId());
		
															List<AccountDetail> accLst=accountDetailsDAO.findByWhereCluase("itemAccId="+ad.getItemAccId());
															if(accLst==null || accLst.size()==0){
																accountDetailsDAO.insertAccountDetails(ad);
															}else{
																ad.setId(accLst.get(0).getId());
															}
		
		
		
															InvestmentDetail invDet=new InvestmentDetail();
															invDet.setAccountDetail(ad);
															if(accJO.has("accountHolder")){invDet.setAccHolder(accJO.getString("accountHolder")==null?null:accJO.getString("accountHolder"));}
															//invDet.setAccName(accJO.getString("accountName")==null?null:accJO.getString("accountName"));
															if(accJO.has("accountDisplayName")){
																JSONObject adnJO=accJO.getJSONObject("accountDisplayName");
																invDet.setAccName(adnJO.getString("defaultNormalAccountName")==null?null:adnJO.getString("defaultNormalAccountName"));
															}
															if(accJO.has("accountNumber")){invDet.setAccNum(accJO.getString("accountNumber")==null?null:accJO.getString("accountNumber"));}
															if(accJO.has("acctType")){invDet.setAccType(accJO.getString("acctType")==null?null:accJO.getString("acctType"));}
															//invDet.setPlanName(accJO.getString("planName")==null?null:accJO.getString("planName"));
		
		
															if(accJO.has("accountClassification")){
																JSONObject adnJO=accJO.getJSONObject("accountClassification");
																invDet.setAccClassification(adnJO.getString("accountClassification")==null?null:adnJO.getString("accountClassification"));
															}
		
															if(accJO.has("cash")){
																JSONObject adnJO=accJO.getJSONObject("cash");
																invDet.setCash(adnJO.getString("amount")==null?null:adnJO.getDouble("amount"));
															}
		
															if(accJO.has("buyingPower")){
																JSONObject adnJO=accJO.getJSONObject("buyingPower");
																invDet.setDayTradMargBuyingPower(adnJO.getString("amount")==null?null:adnJO.getDouble("amount"));
															}
		
															if(accJO.has("loan_401k")){
																JSONObject adnJO=accJO.getJSONObject("loan_401k");
																invDet.setLoan401k(adnJO.getString("amount")==null?null:adnJO.getDouble("amount"));
															}
		
															if(accJO.has("marginBalance")){
																JSONObject adnJO=accJO.getJSONObject("marginBalance");
																invDet.setMargBal(adnJO.getString("amount")==null?null:adnJO.getDouble("amount"));
															}
		
															if(accJO.has("moneyMarketBalance")){
																JSONObject adnJO=accJO.getJSONObject("moneyMarketBalance");
																invDet.setMoneyMarketBal(adnJO.getString("amount")==null?null:adnJO.getDouble("amount"));
															}
		
															if(accJO.has("shortBalance")){
																JSONObject adnJO=accJO.getJSONObject("shortBalance");
																invDet.setShortBal(adnJO.getString("amount")==null?null:adnJO.getDouble("amount"));
															}
		
															if(accJO.has("totalBalance")){
																JSONObject adnJO=accJO.getJSONObject("totalBalance");
																invDet.setTotBal(adnJO.getString("amount")==null?null:adnJO.getDouble("amount"));
															}
		
															if(accJO.has("totalVestedBalance")){
																JSONObject adnJO=accJO.getJSONObject("totalVestedBalance");
																invDet.setTotVestedBal(adnJO.getString("amount")==null?null:adnJO.getDouble("amount"));
															}
		
															if(accJO.has("totalUnvestedBalance")){
																JSONObject adnJO=accJO.getJSONObject("totalUnvestedBalance");
																invDet.setTotUnvestedBal(adnJO.getString("amount")==null?null:adnJO.getDouble("amount"));
															}
		
		
															invDet.setInsertedOn(CommonUtil.getCurrentTimeStamp());
															invDet.setInsertedBy(loggedInUsers.get(invUserId).getId());
															//investmentDetailsDAO.insertInvestmentDetails(invDet);
															List<InvestmentDetail> invDataLst=investmentDetailsDAO.findByWhereCluase(" ACC_DET_ID=?", new Object[]{ad.getId()});
															
															if(invDataLst==null || invDataLst.size()==0){		
																investmentDetailsDAO.insertInvestmentDetails(invDet);
															}else{
																invDet.setId(invDataLst.get(0).getId());
																invDet.setUpdatedOn(CommonUtil.getCurrentTimeStamp());
																invDet.setUpdatedBy(loggedInUsers.get(invUserId).getId());
																investmentDetailsDAO.updateInvestmentDetails(invDet);
															}
		
															logger.info(loggedInUsers.get(invUserId).getId()+" : "+sd.getId()+" : "+id.getId()+" : "+ad.getId());
															List<ConsolidateData> conDataLst=consolidateDataDAO.findByWhereCluase(" USER_LOG_ID=? AND  SITE_DET_ID=? AND ITEM_DET_ID=? AND ACC_DET_ID=?", new Object[]{loggedInUsers.get(invUserId).getId(),sd.getId(),id.getId(),ad.getId()});
		
															if(conDataLst==null || conDataLst.size()==0){
																
																UserLogon ul=new UserLogon();
																ul.setId(loggedInUsers.get(invUserId).getId());
																ConsolidateData consData=new ConsolidateData(ul, sd, id, ad, invDet.getId(),invDet.getAccType(),invDet.getTotBal(), CommonUtil.getCurrentTimeStamp(), loggedInUsers.get(invUserId).getId());
																consolidateDataDAO.insertConsolidateData(consData);
															}else{
		
																ConsolidateData consData=conDataLst.get(0);
																consData.setPfolioDetId(invDet.getId());
																consData.setAccType(invDet.getAccType());
																consData.setAvailBal(invDet.getTotBal());
																consData.setUpdatedOn(CommonUtil.getCurrentTimeStamp());
																consData.setUpdatedBy(loggedInUsers.get(invUserId).getId());
																consolidateDataDAO.updateConsolidateData(consData);
															}
		
															if(accJO.has("holdings")){
																JSONArray accHoldJA=accJO.getJSONArray("holdings");
																logger.info("accHoldJA :"+accHoldJA);		
		
																int accHoldLen=accHoldJA.length();
																if(accHoldLen>0){
																	List<InvestmentHolding> ihList=investmentHoldingsDAO.findByWhereCluase("accountDetail.itemDetail.siteDetail.userLogon="+loggedInUsers.get(invUserId).getId()+" and accountDetail.accId="+ad.getAccId());
																	Iterator<InvestmentHolding> itr=ihList.iterator();
																	while (itr.hasNext()) {
																		InvestmentHolding investmentHolding = (InvestmentHolding) itr.next();
																		investmentHoldingsDAO.deleteInvestmentHoldings(investmentHolding);
																	}
																	for (int a = 0; a < accHoldJA.length(); a++) {
		
																		JSONObject accHoldJO= accHoldJA.getJSONObject(a);
																/*logger.info("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
																	logger.info("accTranJO: "+accHoldJO);
		*/															InvestmentHolding invHold=new InvestmentHolding();
																		invHold.setAccountDetail(ad);
																		if (accHoldJO.has("commodityType")) {
																			invHold.setCommodityType(accHoldJO.getString("commodityType")==null?null:accHoldJO.getString("commodityType"));
																		}
																		if (accHoldJO.has("costBasis")) {
																			JSONObject intJO=accHoldJO.getJSONObject("costBasis");
																			invHold.setCostBasis(intJO.getString("amount")==null?null:intJO.getDouble("amount"));
																		}
																		if (accHoldJO.has("couponFreq")) {
																			invHold.setCouponFrequency(accHoldJO.getString("couponFreq")==null?null:accHoldJO.getString("couponFreq"));
																		}
																		if (accHoldJO.has("couponRate")) {
																			invHold.setCouponRate(accHoldJO.getString("couponRate")==null?null:accHoldJO.getDouble("couponRate"));
																		}
																		if (accHoldJO.has("cusipNumber")) {
																			invHold.setCusipNum(accHoldJO.getString("cusipNumber")==null?null:accHoldJO.getString("cusipNumber"));
																		}
																		if (accHoldJO.has("description")) {
																			invHold.setDescription(accHoldJO.getString("description")==null?null:accHoldJO.getString("description"));
																		}
																		if (accHoldJO.has("employeeContribution")) {
																			JSONObject intJO=accHoldJO.getJSONObject("employeeContribution");
																			invHold.setEmpContr(intJO.getString("amount")==null?null:intJO.getDouble("amount"));
																		}
																		/*if (accTranJO.has("commission")) {
																			invHold.setEspType(accTranJO.getString("commodityType")==null?null:accTranJO.getString("commodityType"));
																		}*/
																		if (accHoldJO.has("expirationDate")) {
																			JSONObject intJO=accHoldJO.getJSONObject("expirationDate");
																			if(intJO.has("date")){
																				invHold.setExpirationDate(intJO.getString("date")==null || intJO.getString("date").equals("{}")?null:sdf.parse(intJO.getString("date")));
																			}
																		}
																		if (accHoldJO.has("faceValue")) {
																			JSONObject intJO=accHoldJO.getJSONObject("faceValue");
																			invHold.setFaceValue(intJO.getString("amount")==null?null:intJO.getDouble("amount"));
																		}
																		if (accHoldJO.has("holdingType")) {
																			invHold.setHoldingType(accHoldJO.getString("holdingType")==null?null:accHoldJO.getString("holdingType"));
																		}
		
																		invHold.setInsertedBy(loggedInUsers.get(invUserId).getId());
																		invHold.setInsertedOn(CommonUtil.getCurrentTimeStamp());
		
																		if (accHoldJO.has("interestRate")) {
																			invHold.setIntRate(accHoldJO.getString("interestRate")==null?null:accHoldJO.getDouble("interestRate"));
																		}
																		if (accHoldJO.has("lastContribution")) {
																			JSONObject intJO=accHoldJO.getJSONObject("lastContribution");
																			invHold.setLastContr(intJO.getString("amount")==null?null:intJO.getDouble("amount"));
																		}
																		/*if (accTranJO.has("commission")) {
																			invHold.setLinkedBankAccNum(accTranJO.getString("commodityType")==null?null:accTranJO.getString("commodityType"));
																		}
																		if (accTranJO.has("commission")) {
																			invHold.setLotSize(accTranJO.getString("commodityType")==null?null:accTranJO.getString("commodityType"));
																		}*/
																		if (accHoldJO.has("maturityDate")) {
																			JSONObject intJO=accHoldJO.getJSONObject("maturityDate");
																			if(intJO.has("date")){
																				invHold.setMatDate(intJO.getString("date")==null || intJO.getString("date").equals("{}")?null:sdf.parse(intJO.getString("date")));
																			}
																		}
																		if (accHoldJO.has("mutualFundType")) {
																			invHold.setMutualFundType(accHoldJO.getString("mutualFundType")==null?null:accHoldJO.getString("mutualFundType"));
																		}
																		if (accHoldJO.has("optionType")) {
																			invHold.setOptionType(accHoldJO.getString("optionType")==null?null:accHoldJO.getString("optionType"));
																		}
																		if (accHoldJO.has("parValue")) {
																			JSONObject intJO=accHoldJO.getJSONObject("parValue");
																			invHold.setParValue(intJO.getString("amount")==null?null:intJO.getDouble("amount"));
																		}
																		if (accHoldJO.has("percentAllocation")) {
																			invHold.setPercAlloc(accHoldJO.getString("percentAllocation")==null?null:accHoldJO.getDouble("percentAllocation"));
																		}
																		/*if (accTranJO.has("commission")) {
																			invHold.setPlanName(accTranJO.getString("commodityType")==null?null:accTranJO.getString("commodityType"));
																		}
																		if (accTranJO.has("commission")) {
																			invHold.setPlanNum(accTranJO.getString("commodityType")==null?null:accTranJO.getString("commodityType"));
																		}*/
																		if (accHoldJO.has("price")) {
																			JSONObject intJO=accHoldJO.getJSONObject("price");
																			invHold.setPrice(intJO.getString("amount")==null?null:intJO.getDouble("amount"));
																		}
																		if (accHoldJO.has("symbol")) {
																			invHold.setSymbol(accHoldJO.getString("symbol")==null?null:accHoldJO.getString("symbol"));
																		}
																		/*if (accHoldJO.has("term")) {
																			invHold.setTerm(accHoldJO.getString("term")==null?null:accHoldJO.getLong("term"));
																		}*/
																		if (accHoldJO.has("value")) {
																			JSONObject intJO=accHoldJO.getJSONObject("value");
																			invHold.setTotValue(intJO.getString("amount")==null?null:intJO.getDouble("amount"));
																		}
		
																		investmentHoldingsDAO.insertInvestmentHoldings(invHold);
		
																	}
																}
															}
		
		
		
															if(accJO.has("investmentTransactions")){
																JSONArray accTranJA=accJO.getJSONArray("investmentTransactions");
		
		
																int accTranLen=accTranJA.length();
																if(accTranLen>0){
																	for (int a = 0; a < accTranJA.length(); a++) {
		
																		JSONObject accTranJO= accTranJA.getJSONObject(a);
																/*logger.info("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
																	logger.info("accTranJO: "+accTranJO);
		*/
																		InvestmentTransaction invTran=new InvestmentTransaction();
																		invTran.setAccountDetail(ad);
		
																		if(accTranJO.has("amount")){
																			JSONObject intJO=accTranJO.getJSONObject("amount");
																			invTran.setAmt(intJO.getString("amount")==null?null:intJO.getDouble("amount"));
																		}
																		if(accTranJO.has("commission")){
																			JSONObject intJO=accTranJO.getJSONObject("commission");
																			invTran.setCommission(intJO.getString("amount")==null?null:intJO.getString("amount"));
																		}
																		if(accTranJO.has("cusipNumber")){
																			invTran.setCusipNum(accTranJO.getString("cusipNumber")==null?null:accTranJO.getString("cusipNumber"));
																		}
																		if(accTranJO.has("description")){
																			invTran.setDescription(accTranJO.getString("description")==null?null:accTranJO.getString("description"));
																		}if(accTranJO.has("price")){
																			JSONObject intJO=accTranJO.getJSONObject("price");
																			invTran.setPrice(intJO.getString("amount")==null?null:intJO.getDouble("amount"));
																		}if(accTranJO.has("quantity")){
																			invTran.setQuantity(accTranJO.getString("quantity")==null?null:accTranJO.getDouble("quantity"));
																		}if(accTranJO.has("secFee")){
																			JSONObject intJO=accTranJO.getJSONObject("secFee");
																			invTran.setSecFee(intJO.getString("amount")==null?null:intJO.getDouble("amount"));
																		}if(accTranJO.has("settleDate")){
																			JSONObject intJO=accTranJO.getJSONObject("settleDate");
																			if(intJO.has("date")){
																				invTran.setSettleDate(intJO.getString("date")==null || intJO.getString("date").equals("{}")?null:sdf.parse(intJO.getString("date")));
																			}
																		}
		
																		if(accTranJO.has("symbol")){
																			invTran.setSymbol(accTranJO.getString("symbol")==null?null:accTranJO.getString("symbol"));
																		}if(accTranJO.has("transactionBaseType")){
																			invTran.setTranBaseType(accTranJO.getString("transactionBaseType")==null?null:accTranJO.getString("transactionBaseType"));
																		}if(accTranJO.has("transDate")){
																			JSONObject intJO=accTranJO.getJSONObject("transDate");
																			if(intJO.has("date")){
																				invTran.setTranDate(intJO.getString("date")==null || intJO.getString("date").equals("{}")?null:sdf.parse(intJO.getString("date")));
																			}
																		}
																		if(accTranJO.has("transactionType")){
																			invTran.setTranType(accTranJO.getString("transactionType")==null?null:accTranJO.getString("transactionType"));
																		}
																		invTran.setInsertedBy(loggedInUsers.get(invUserId).getId());
																		invTran.setInsertedOn(CommonUtil.getCurrentTimeStamp());
		
																		investmentTransactionsDAO.insertInvestmentTransactions(invTran);
		
																	}
																}
															}
		
														}
													}
												}
											}
										}
		
		
		
										if(id.getContServName().equals("loans") || id.getContServName().equals("mortgage")){
		
											if(jo.has("itemData")){
		
												JSONObject itemDataJO=jo.getJSONObject("itemData");
												if(itemDataJO.has("accounts")){
													JSONArray accountsJA=itemDataJO.getJSONArray("accounts");
													int accArrLen=accountsJA.length();
													if(accArrLen>0){
		
														for (int j = 0; j < accountsJA.length(); j++) {
															JSONObject accJO= accountsJA.getJSONObject(j);
		
															if(accJO.has("loans")){
																JSONArray loanAccountsJA=accJO.getJSONArray("loans");
																int loanArrLen=loanAccountsJA.length();
																if(loanArrLen>0){
		
																	for (int k = 0; k < loanAccountsJA.length(); k++) {
																		JSONObject loanAccJO= loanAccountsJA.getJSONObject(k);
		
																		AccountDetail ad=new AccountDetail();
																		ad.setItemDetail(id);
		
																		//id.setContServName(siteRefreshModeJO.getString("containerName")==null?null:siteRefreshModeJO.getString("containerName"));
																		if(loanAccJO.has("accountId")){ad.setAccId(loanAccJO.getString("accountId")==null?null:loanAccJO.getLong("accountId"));}
																		if(loanAccJO.has("itemAccountId")){ad.setItemAccId(loanAccJO.getString("itemAccountId")==null?null:loanAccJO.getLong("itemAccountId"));}
																		if(loanAccJO.has("accountName")){ad.setAccName(loanAccJO.getString("accountName")==null?null:loanAccJO.getString("accountName"));}
		
																		ad.setInsertedOn(CommonUtil.getCurrentTimeStamp());
																		ad.setInsertedBy(loggedInUsers.get(invUserId).getId());
																		List<AccountDetail> accLst=accountDetailsDAO.findByWhereCluase("itemAccId="+ad.getItemAccId());
																		if(accLst==null || accLst.size()==0){
																			accountDetailsDAO.insertAccountDetails(ad);
																		}else{
																			ad.setId(accLst.get(0).getId());
																		}
		
		
		
																		LoanDetail loanDet=new LoanDetail();
																		loanDet.setAccountDetail(ad);
		
																		if(loanAccJO.has("accountName")){loanDet.setAccName(loanAccJO.getString("accountName")==null?null:loanAccJO.getString("accountName"));}
																		if(loanAccJO.has("accountNumber")){loanDet.setAccNum(loanAccJO.getString("accountNumber")==null?null:loanAccJO.getString("accountNumber"));}
																		if(loanAccJO.has("collateral")){loanDet.setCollateral(loanAccJO.getString("collateral")==null?null:loanAccJO.getString("collateral"));}
		
		
																		if(loanAccJO.has("description")){loanDet.setDescription(loanAccJO.getString("description")==null?null:loanAccJO.getString("description"));}
																		if(loanAccJO.has("loanType")){loanDet.setTypeLoan(loanAccJO.getString("loanType")==null?null:loanAccJO.getString("loanType"));}
		
																		if(loanAccJO.has("interestRate")){loanDet.setIntRate(loanAccJO.getString("interestRate")==null?null:loanAccJO.getDouble("interestRate"));}
																		if(loanAccJO.has("loanInterestRateType")){loanDet.setIntRateType(loanAccJO.getString("loanInterestRateType")==null?null:loanAccJO.getString("loanInterestRateType"));}
		
																		if(loanAccJO.has("dueDate")){
																			JSONObject adnJO=loanAccJO.getJSONObject("dueDate");
																			if(adnJO.has("date")){
																				loanDet.setDueDate(adnJO.getString("date")==null || adnJO.getString("date").equals("{}")?null:sdf.parse(adnJO.getString("date")));
																			}
																		}
																		if(loanAccJO.has("lastPaymentDate")){
																			JSONObject adnJO=loanAccJO.getJSONObject("lastPaymentDate");
																			if(adnJO.has("date")){
																				loanDet.setLastPayDate(adnJO.getString("date")==null || adnJO.getString("date").equals("{}")?null:sdf.parse(adnJO.getString("date")));
																			}
																		}
																		if(loanAccJO.has("maturityDate")){
		
																			JSONObject adnJO=loanAccJO.getJSONObject("maturityDate");
																			if(adnJO.has("date")){
																				loanDet.setMatDate(adnJO.getString("date")==null || adnJO.getString("date").equals("{}")?null:sdf.parse(adnJO.getString("date")));
																			}
																		}
																		if(loanAccJO.has("originationDate")){
																			JSONObject adnJO=loanAccJO.getJSONObject("originationDate");
																			if(adnJO.has("date")){
																				loanDet.setOriginationDate(adnJO.getString("date")==null || adnJO.getString("date").equals("{}")?null:sdf.parse(adnJO.getString("date")));
																			}
																		}
																		if(loanAccJO.has("firstPaymentDate")){
																			JSONObject adnJO=loanAccJO.getJSONObject("firstPaymentDate");
																			if(adnJO.has("date")){
																				loanDet.setFirstPayDate(adnJO.getString("date")==null || adnJO.getString("date").equals("{}")?null:sdf.parse(adnJO.getString("date")));
																			}
																		}
		
		
																		if(loanAccJO.has("accountClassification")){
																			JSONObject adnJO=loanAccJO.getJSONObject("accountClassification");
																			loanDet.setAccClassification(adnJO.getString("accountClassification")==null?null:adnJO.getString("accountClassification"));
																		}
		
																		if(loanAccJO.has("interestPaidLastYear")){
																			JSONObject adnJO=loanAccJO.getJSONObject("interestPaidLastYear");
																			loanDet.setIntPaidLastYear(adnJO.getString("amount")==null?null:adnJO.getDouble("amount"));
																		}
		
																		if(loanAccJO.has("lastPaymentAmount")){
																			JSONObject adnJO=loanAccJO.getJSONObject("lastPaymentAmount");
																			loanDet.setLastPayAmt(adnJO.getString("amount")==null?null:adnJO.getDouble("amount"));
																		}
		
																		if(loanAccJO.has("originalLoanAmount")){
																			JSONObject adnJO=loanAccJO.getJSONObject("originalLoanAmount");
																			loanDet.setOrgnlLoanAmt(adnJO.getString("amount")==null?null:adnJO.getDouble("amount"));
																		}
		
																		if(loanAccJO.has("principalBalance")){
																			JSONObject adnJO=loanAccJO.getJSONObject("principalBalance");
																			loanDet.setPrincBal(adnJO.getString("amount")==null?null:adnJO.getDouble("amount"));
																		}
		
																		if(loanAccJO.has("interestPaidYtd")){
																			JSONObject adnJO=loanAccJO.getJSONObject("interestPaidYtd");
																			loanDet.setIntPaidYtd(adnJO.getString("amount")==null?null:adnJO.getDouble("amount"));
																		}
		
		
		
																		loanDet.setInsertedOn(CommonUtil.getCurrentTimeStamp());
																		loanDet.setInsertedBy(loggedInUsers.get(invUserId).getId());
																		//loanDetailsDAO.insertLoanDetails(loanDet);
																		List<LoanDetail> loanDataLst=loanDetailsDAO.findByWhereCluase(" ACC_DET_ID=?", new Object[]{ad.getId()});
																		
																		if(loanDataLst==null || loanDataLst.size()==0){		
																			loanDetailsDAO.insertLoanDetails(loanDet);
																		}else{
																			loanDet.setId(loanDataLst.get(0).getId());
																			loanDet.setUpdatedOn(CommonUtil.getCurrentTimeStamp());
																			loanDet.setUpdatedBy(loggedInUsers.get(invUserId).getId());
																			loanDetailsDAO.updateLoanDetails(loanDet);
																		}
		
		
																		logger.info(loggedInUsers.get(invUserId).getId()+" : "+sd.getId()+" : "+id.getId()+" : "+ad.getId());
																		List<ConsolidateData> conDataLst=consolidateDataDAO.findByWhereCluase(" USER_LOG_ID=? AND  SITE_DET_ID=? AND ITEM_DET_ID=? AND ACC_DET_ID=?", new Object[]{loggedInUsers.get(invUserId).getId(),sd.getId(),id.getId(),ad.getId()});
		
																		if(conDataLst==null || conDataLst.size()==0){
																			UserLogon ul=new UserLogon();
																			ul.setId(loggedInUsers.get(invUserId).getId());
																			ConsolidateData consData=new ConsolidateData(ul, sd, id, ad, loanDet.getId(),loanDet.getTypeLoan(),loanDet.getPrincBal(), CommonUtil.getCurrentTimeStamp(), loggedInUsers.get(invUserId).getId());
																			consolidateDataDAO.insertConsolidateData(consData);
																		}else{
		
																			ConsolidateData consData=conDataLst.get(0);
																			consData.setPfolioDetId(loanDet.getId());
																			consData.setAccType(loanDet.getTypeLoan());
																			consData.setAvailBal(loanDet.getPrincBal());
																			consData.setUpdatedOn(CommonUtil.getCurrentTimeStamp());
																			consData.setUpdatedBy(loggedInUsers.get(invUserId).getId());
																			consolidateDataDAO.updateConsolidateData(consData);
																		}
		
																	}
		
																}
		
		
															}
														}
		
													}
												}
											}
										}
		
									}
								}
		
							}
		
		
						}
					}
				}
		}catch(Exception e){
			return errorDetails(e);
		}
		return resultMap;

	}
	public Map<String, Object> getInvestmentHoldings(Long invUserId){
		Map<String, Object> resultMap=null;
		try{
			List<UserLogon> ulLst=userLogonDAO.findByWhereCluase("invUserId="+invUserId);
			resultMap=new HashMap<String, Object>();
			if(ulLst==null || ulLst.size()==0){
				logger.info("Invessence User Details object get Null");
				YodleeError ye=new YodleeError();
				ye.setMessage("User dont have  available in YodleeProject Database.");
				resultMap.put("errorDetails", ye);
			}else{
				UserLogon ur=ulLst.get(0);
				
				List<InvestmentHolding> invHoldDataList=investmentHoldingsDAO.findByWhereCluase("accountDetail.itemDetail.siteDetail.userLogon="+ur.getId());
				resultMap=new HashMap<String, Object>();
				if(invHoldDataList==null || invHoldDataList.size()==0){
					logger.info("User do not have Investment Holding details.");
					YodleeError ye=new YodleeError();
					ye.setMessage("User do not have Investment Holding details.");
					resultMap.put("errorDetails", ye);
				}else{

					resultMap.put("invHoldDataList", invHoldDataList);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return resultMap;
	}
	

	public Map<String, Object> getInvestmentTransactions(Long invUserId){

		Map<String, Object> resultMap=null;
		try{
			List<UserLogon> ulLst=userLogonDAO.findByWhereCluase("invUserId="+invUserId);
			resultMap=new HashMap<String, Object>();
			if(ulLst==null || ulLst.size()==0){
				logger.info("Invessence User Details object get Null");
				YodleeError ye=new YodleeError();
				ye.setMessage("User dont have  available in YodleeProject Database.");
				resultMap.put("errorDetails", ye);
			}else{
				UserLogon ur=ulLst.get(0);

				List<InvestmentTransaction> invTranDataList=investmentTransactionsDAO.findByWhereCluase("accountDetail.itemDetail.siteDetail.userLogon="+ur.getId());
				resultMap=new HashMap<String, Object>();
				if(invTranDataList==null || invTranDataList.size()==0){
					logger.info("User do not have Investment Transaction details.");
					YodleeError ye=new YodleeError();
					ye.setMessage("User do not have Investment Transaction details.");
					resultMap.put("errorDetails", ye);
				}else{

					resultMap.put("invTranDataList", invTranDataList);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return resultMap;
	}


	/*public Map<String, Object> getItemSummariesForSite(String siteAccId, Long invUserId) {
		Map<String, Object> resultMap=null;
		JSONArray ja=null;
		try{
			logger.info("YodleeAPIServiceImpl.getItemSummariesForSite()");
			resultMap=new HashMap<String, Object>();

			List<SiteDetail> sdLst=siteDetailsDAO.findByWhereCluase("siteAccId="+siteAccId);
			if(sdLst==null || sdLst.size()==0){
				logger.info("Invessence User Details object get Null");
			}else{
				SiteDetail sd=sdLst.get(0);

				ja=yodleeAPIRepo.getItemSummariesForSite(cobrandSessionToken,loggedInUsers.get(invUserId).getUserSessionToken(), siteAccId);

				int arrLen=ja.length();
				logger.info("Array Size :"+arrLen);

				resultMap=new HashMap<String, Object>();
				List<ItemDetail> itemList=new ArrayList<ItemDetail>();
				if(arrLen==0){
					YodleeError ye=new YodleeError();
					ye.setMessage("Site does't have summaries item Details.");
					resultMap.put("errorDetails", ye);
				}else if(arrLen>0){

					for (int i = 0; i < ja.length(); i++) {
						ItemDetail id=new ItemDetail();
						JSONObject jo= ja.getJSONObject(i);
						id.setItemId(jo.getString("itemId")==null?null:jo.getLong("itemId"));
						id.setItemDispName(jo.getString("itemDisplayName")==null?null:jo.getString("itemDisplayName"));
						id.setContServId(jo.getString("contentServiceId")==null?null:jo.getLong("contentServiceId"));
						logger.info("contentServiceId: "+jo.getLong("contentServiceId"));
						if(jo.has("contentServiceInfo")){
							JSONObject contentServiceJO=jo.getJSONObject("contentServiceInfo");
							if(contentServiceJO.has("containerInfo")){
								JSONObject containerInfoJO=contentServiceJO.getJSONObject("containerInfo");
								id.setContServName(containerInfoJO.getString("containerName")==null?null:containerInfoJO.getString("containerName"));

								List<ItemDetail> itemLst=itemDetailsDAO.findByWhereCluase("itemId="+id.getItemId());
								if(itemLst==null || itemLst.size()==0){
									id.setInsertedOn(CommonUtil.getCurrentTimeStamp());
									id.setInsertedBy(loggedInUsers.get(invUserId).getId());
									SiteDetail siteDetails=new SiteDetail();
									siteDetails.setId(sd.getId());
									id.setSiteDetail(siteDetails);
									itemDetailsDAO.insertItemDetails(id);
								}else{
									id.setId(itemLst.get(0).getId());
								}

								logger.info("containerName: "+id.getContServName());

								if(id.getContServName().equals("bank")){

									if(jo.has("itemData")){

										JSONObject itemDataJO=jo.getJSONObject("itemData");
										if(itemDataJO.has("accounts")){
											JSONArray accountsJA=itemDataJO.getJSONArray("accounts");

											int accArrLen=accountsJA.length();
											Set<AccountDetail> accSet=new LinkedHashSet<AccountDetail>();
											if(accArrLen>0){

												for (int j = 0; j < accountsJA.length(); j++) {

													JSONObject accJO= accountsJA.getJSONObject(j);

													Set<BankDetail> bankSet=new LinkedHashSet<BankDetail>();
													AccountDetail ad=new AccountDetail();
													ad.setItemDetail(id);

													//id.setContServName(siteRefreshModeJO.getString("containerName")==null?null:siteRefreshModeJO.getString("containerName"));
													ad.setAccId(accJO.getString("accountId")==null?null:accJO.getLong("accountId"));
													ad.setItemAccId(accJO.getString("itemAccountId")==null?null:accJO.getLong("itemAccountId"));
													ad.setAccName(accJO.getString("accountName")==null?null:accJO.getString("accountName"));

													ad.setInsertedOn(CommonUtil.getCurrentTimeStamp());
													ad.setInsertedBy(loggedInUsers.get(invUserId).getId());
													List<AccountDetail> accLst=accountDetailsDAO.findByWhereCluase("itemAccId="+ad.getItemAccId());
													if(accLst==null || accLst.size()==0){
														accountDetailsDAO.insertAccountDetails(ad);
													}else{
														ad.setId(accLst.get(0).getId());
													}

													BankDetail bd=new BankDetail();
													bd.setAccountDetail(ad);
													bd.setAccHolder(accJO.getString("accountHolder")==null?null:accJO.getString("accountHolder"));
													bd.setAccName(accJO.getString("accountName")==null?null:accJO.getString("accountName"));

													bd.setAccNum(accJO.getString("accountNumber")==null?null:accJO.getString("accountNumber"));
													bd.setAccType(accJO.getString("acctType")==null?null:accJO.getString("acctType"));
													logger.info("as of date"+accJO.getString("asOfDate"));

													if(accJO.has("asOfDate")){
														logger.info(accJO.has("asOfDate") );
														logger.info(accJO.has("asOfDate") );
														JSONObject adnJO=accJO.getJSONObject("asOfDate");
														logger.info(accJO.has("asOfDate") );


													}
													//bd.setAsOfDate(accJO.getString("asOfDate")==null?null:new Date(accJO.getString("asOfDate")));
													//bd.setMatDate(accJO.getString("maturityDate")==null?null:new Date(accJO.getString("maturityDate")));

													bd.setAccName(accJO.getString("accountName")==null?null:accJO.getString("accountName"));

													if(accJO.has("accountDisplayName")){
														JSONObject adnJO=accJO.getJSONObject("accountDisplayName");
														bd.set(adnJO.getString("defaultNormalAccountName")==null?null:adnJO.getString("defaultNormalAccountName"));
													}
													if(accJO.has("accountClassification")){
														JSONObject adnJO=accJO.getJSONObject("accountClassification");
														bd.setAccClassification(adnJO.getString("accountClassification")==null?null:adnJO.getString("accountClassification"));
													}

													if(accJO.has("availableBalance")){
														JSONObject adnJO=accJO.getJSONObject("availableBalance");
														bd.setAvailBal(adnJO.getString("amount")==null?null:adnJO.getDouble("amount"));
													}

													if(accJO.has("currentBalance")){
														JSONObject adnJO=accJO.getJSONObject("currentBalance");
														bd.setCurBal(adnJO.getString("amount")==null?null:adnJO.getDouble("amount"));
													}

													bd.setInsertedOn(CommonUtil.getCurrentTimeStamp());
													bd.setInsertedBy(loggedInUsers.get(invUserId).getId());
													bankDetailsDAO.insertBankDetails(bd);

													logger.info(loggedInUsers.get(invUserId).getId()+" : "+sd.getId()+" : "+id.getId()+" : "+ad.getId());
													List<ConsolidateData> conDataLst=consolidateDataDAO.findByWhereCluase(" USER_LOG_ID=? AND  SITE_DET_ID=? AND ITEM_DET_ID=? AND ACC_DET_ID=?", new Object[]{loggedInUsers.get(invUserId).getId(),sd.getId(),id.getId(),ad.getId()});

													if(conDataLst==null || conDataLst.size()==0){
														logger.info("Consilidated data not available.");
														ConsolidateData consData=new ConsolidateData();
														UserLogon ul=new UserLogon();
														ul.setId(loggedInUsers.get(invUserId).getId());
														consData.setUserLogon(ul);
														consData.setSiteDetail(sd);
														consData.setItemDetail(id);
														consData.setAccountDetail(ad);
														consData.setPfolioDetId(bd.getId());
														consData.setAccType(bd.getAccType());
														consData.setAvailBal(bd.getAvailBal());
														consData.setInsertedOn(CommonUtil.getCurrentTimeStamp());
														consData.setInsertedBy(loggedInUsers.get(invUserId).getId());

														consolidateDataDAO.insertConsolidateData(consData);
													}else{

														ConsolidateData consData=conDataLst.get(0);
														logger.info("Consilidated data available : "+consData.getId());
														consData.setPfolioDetId(bd.getId());
														consData.setAccType(bd.getAccType());
														consData.setAvailBal(bd.getAvailBal());
														consData.setUpdatedOn(CommonUtil.getCurrentTimeStamp());
														consData.setUpdatedBy(loggedInUsers.get(invUserId).getId());
														consolidateDataDAO.updateConsolidateData(consData);
													}


													bd.setAccountDetail(null);
													bankSet.add(bd);

													ad.setItemDetail(null);
													ad.setBankDetails(bankSet);
													ad.setBankTransaction(null);

													ad.setCardDetails(null);
													ad.setCardStatements(null);
													ad.setCardTransactiones(null);

													ad.setLoanDetails(null);
													ad.setLoanHoldinges(null);
													ad.setLoanTransaction(null);

													ad.setInvestmentDetails(null);
													ad.setInvestmentHoldinges(null);
													ad.setInvestmentTransaction(null);
													accSet.add(ad);


												}

												id.setAccountDetails(accSet);
											}
										}
									}
								}


								if(id.getContServName().equals("credits")){

									if(jo.has("itemData")){

										JSONObject itemDataJO=jo.getJSONObject("itemData");
										if(itemDataJO.has("accounts")){
											JSONArray accountsJA=itemDataJO.getJSONArray("accounts");


											int accArrLen=accountsJA.length();
											Set<AccountDetail> accSet=new LinkedHashSet<AccountDetail>();
											if(accArrLen>0){

												for (int j = 0; j < accountsJA.length(); j++) {

													JSONObject accJO= accountsJA.getJSONObject(j);

													Set<CardDetail> cardSet=new LinkedHashSet<CardDetail>();
													AccountDetail ad=new AccountDetail();
													ad.setItemDetail(id);

													//id.setContServName(siteRefreshModeJO.getString("containerName")==null?null:siteRefreshModeJO.getString("containerName"));
													ad.setAccId(accJO.getString("accountId")==null?null:accJO.getLong("accountId"));
													ad.setItemAccId(accJO.getString("itemAccountId")==null?null:accJO.getLong("itemAccountId"));
													ad.setAccName(accJO.getString("accountName")==null?null:accJO.getString("accountName"));

													ad.setInsertedOn(CommonUtil.getCurrentTimeStamp());
													ad.setInsertedBy(loggedInUsers.get(invUserId).getId());
													List<AccountDetail> accLst=accountDetailsDAO.findByWhereCluase("itemAccId="+ad.getItemAccId());
													if(accLst==null || accLst.size()==0){
														accountDetailsDAO.insertAccountDetails(ad);
													}else{
														ad.setId(accLst.get(0).getId());
													}


													CardDetail cardDet=new CardDetail();
													cardDet.setAccountDetail(ad);
													cardDet.setAccHolder(accJO.getString("accountHolder")==null?null:accJO.getString("accountHolder"));
													cardDet.setAccName(accJO.getString("accountName")==null?null:accJO.getString("accountName"));
													cardDet.setAccNum(accJO.getString("accountNumber")==null?null:accJO.getString("accountNumber"));
													cardDet.setAccType(accJO.getString("acctType")==null?null:accJO.getString("acctType"));

													//crdDet.setDueDate(accJO.getString("dueDate")==null?null:new Date(accJO.getString("dueDate")));
													//crdDet.setLastPayDate(accJO.getString("lastPaymentDate")==null?null:new Date(accJO.getString("lastPaymentDate")));

													if(accJO.has("accountClassification")){
														JSONObject adnJO=accJO.getJSONObject("accountClassification");
														cardDet.setAccClassification(adnJO.getString("accountClassification")==null?null:adnJO.getString("accountClassification"));
													}

													if(accJO.has("availableCash")){
														JSONObject adnJO=accJO.getJSONObject("availableCash");
														cardDet.setAvailCash(adnJO.getString("amount")==null?null:adnJO.getDouble("amount"));
													}

													if(accJO.has("availableCredit")){
														JSONObject adnJO=accJO.getJSONObject("availableCredit");
														cardDet.setAvailCredit(adnJO.getString("amount")==null?null:adnJO.getDouble("amount"));
													}


													if(accJO.has("lastPayment")){
														JSONObject adnJO=accJO.getJSONObject("lastPayment");
														cardDet.setLastPay(adnJO.getString("amount")==null?null:adnJO.getDouble("amount"));
													}


													if(accJO.has("totalCashLimit")){
														JSONObject adnJO=accJO.getJSONObject("totalCashLimit");
														cardDet.setTotCashLimit(adnJO.getString("amount")==null?null:adnJO.getDouble("amount"));
													}

													if(accJO.has("totalCreditLine")){
														JSONObject adnJO=accJO.getJSONObject("totalCreditLine");
														cardDet.setTotCreditLine(adnJO.getString("amount")==null?null:adnJO.getDouble("amount"));
													}

													if(accJO.has("runningBalance")){
														JSONObject adnJO=accJO.getJSONObject("runningBalance");
														cardDet.setRunningBal(adnJO.getString("amount")==null?null:adnJO.getDouble("amount"));
													}

													cardDet.setInsertedOn(CommonUtil.getCurrentTimeStamp());
													cardDet.setInsertedBy(loggedInUsers.get(invUserId).getId());
													cardDetailsDAO.insertCardDetails(cardDet);


													logger.info(loggedInUsers.get(invUserId).getId()+" : "+sd.getId()+" : "+id.getId()+" : "+ad.getId());
													List<ConsolidateData> conDataLst=consolidateDataDAO.findByWhereCluase(" USER_LOG_ID=? AND  SITE_DET_ID=? AND ITEM_DET_ID=? AND ACC_DET_ID=?", new Object[]{loggedInUsers.get(invUserId).getId(),sd.getId(),id.getId(),ad.getId()});

													if(conDataLst==null || conDataLst.size()==0){
														logger.info("Consilidated data not available.");
														ConsolidateData consData=new ConsolidateData();
														UserLogon ul=new UserLogon();
														ul.setId(loggedInUsers.get(invUserId).getId());
														consData.setUserLogon(ul);
														consData.setSiteDetail(sd);
														consData.setItemDetail(id);
														consData.setAccountDetail(ad);
														consData.setPfolioDetId(cardDet.getId());
														consData.setAccType(cardDet.getAccType());
														consData.setAvailBal(cardDet.getAvailCredit());
														consData.setInsertedOn(CommonUtil.getCurrentTimeStamp());
														consData.setInsertedBy(loggedInUsers.get(invUserId).getId());

														consolidateDataDAO.insertConsolidateData(consData);
													}else{

														ConsolidateData consData=conDataLst.get(0);
														logger.info("Consilidated data available : "+consData.getId());
														consData.setPfolioDetId(cardDet.getId());
														consData.setAccType(cardDet.getAccType());
														consData.setAvailBal(cardDet.getAvailCredit());
														consData.setUpdatedOn(CommonUtil.getCurrentTimeStamp());
														consData.setUpdatedBy(loggedInUsers.get(invUserId).getId());
														consolidateDataDAO.updateConsolidateData(consData);
													}

													cardDet.setAccountDetail(null);
													cardSet.add(cardDet);

													ad.setItemDetail(null);

													ad.setBankDetails(null);
													ad.setBankTransaction(null);

													ad.setCardDetails(cardSet);
													ad.setCardStatements(null);
													ad.setCardTransactiones(null);

													ad.setLoanDetails(null);
													ad.setLoanHoldinges(null);
													ad.setLoanTransaction(null);

													ad.setInvestmentDetails(null);
													ad.setInvestmentHoldinges(null);
													ad.setInvestmentTransaction(null);
													accSet.add(ad);

												}

												id.setAccountDetails(accSet);

											}
										}
									}
								}

								if(id.getContServName().equals("stocks")){

									if(jo.has("itemData")){

										JSONObject itemDataJO=jo.getJSONObject("itemData");
										if(itemDataJO.has("accounts")){
											JSONArray accountsJA=itemDataJO.getJSONArray("accounts");


											int accArrLen=accountsJA.length();
											Set<AccountDetail> accSet=new LinkedHashSet<AccountDetail>();
											if(accArrLen>0){

												for (int j = 0; j < accountsJA.length(); j++) {

													JSONObject accJO= accountsJA.getJSONObject(j);

													Set<InvestmentDetail> invSet=new LinkedHashSet<InvestmentDetail>();
													AccountDetail ad=new AccountDetail();
													ad.setItemDetail(id);

													//id.setContServName(siteRefreshModeJO.getString("containerName")==null?null:siteRefreshModeJO.getString("containerName"));
													ad.setAccId(accJO.getString("accountId")==null?null:accJO.getLong("accountId"));
													ad.setItemAccId(accJO.getString("itemAccountId")==null?null:accJO.getLong("itemAccountId"));
													if(accJO.has("accountDisplayName")){
														JSONObject adnJO=accJO.getJSONObject("accountDisplayName");
														ad.setAccName(adnJO.getString("defaultNormalAccountName")==null?null:adnJO.getString("defaultNormalAccountName"));
													}

													ad.setInsertedOn(CommonUtil.getCurrentTimeStamp());
													ad.setInsertedBy(loggedInUsers.get(invUserId).getId());
													List<AccountDetail> accLst=accountDetailsDAO.findByWhereCluase("itemAccId="+ad.getItemAccId());
													if(accLst==null || accLst.size()==0){
														accountDetailsDAO.insertAccountDetails(ad);
													}else{
														ad.setId(accLst.get(0).getId());
													}



													InvestmentDetail invDet=new InvestmentDetail();
													invDet.setAccountDetail(ad);
													invDet.setAccHolder(accJO.getString("accountHolder")==null?null:accJO.getString("accountHolder"));
													//invDet.setAccName(accJO.getString("accountName")==null?null:accJO.getString("accountName"));
													if(accJO.has("accountDisplayName")){
														JSONObject adnJO=accJO.getJSONObject("accountDisplayName");
														invDet.setAccName(adnJO.getString("defaultNormalAccountName")==null?null:adnJO.getString("defaultNormalAccountName"));
													}
													invDet.setAccNum(accJO.getString("accountNumber")==null?null:accJO.getString("accountNumber"));
													invDet.setAccType(accJO.getString("acctType")==null?null:accJO.getString("acctType"));
													//invDet.setPlanName(accJO.getString("planName")==null?null:accJO.getString("planName"));


													if(accJO.has("accountClassification")){
														JSONObject adnJO=accJO.getJSONObject("accountClassification");
														invDet.setAccClassification(adnJO.getString("accountClassification")==null?null:adnJO.getString("accountClassification"));
													}

													if(accJO.has("cash")){
														JSONObject adnJO=accJO.getJSONObject("cash");
														invDet.setCash(adnJO.getString("amount")==null?null:adnJO.getDouble("amount"));
													}

													if(accJO.has("buyingPower")){
														JSONObject adnJO=accJO.getJSONObject("buyingPower");
														invDet.setDayTradMargBuyingPower(adnJO.getString("amount")==null?null:adnJO.getDouble("amount"));
													}

													if(accJO.has("loan_401k")){
														JSONObject adnJO=accJO.getJSONObject("loan_401k");
														invDet.setLoan401k(adnJO.getString("amount")==null?null:adnJO.getDouble("amount"));
													}

													if(accJO.has("marginBalance")){
														JSONObject adnJO=accJO.getJSONObject("marginBalance");
														invDet.setMargBal(adnJO.getString("amount")==null?null:adnJO.getDouble("amount"));
													}

													if(accJO.has("moneyMarketBalance")){
														JSONObject adnJO=accJO.getJSONObject("moneyMarketBalance");
														invDet.setMoneyMarketBal(adnJO.getString("amount")==null?null:adnJO.getDouble("amount"));
													}

													if(accJO.has("shortBalance")){
														JSONObject adnJO=accJO.getJSONObject("shortBalance");
														invDet.setShortBal(adnJO.getString("amount")==null?null:adnJO.getDouble("amount"));
													}

													if(accJO.has("totalBalance")){
														JSONObject adnJO=accJO.getJSONObject("totalBalance");
														invDet.setTotBal(adnJO.getString("amount")==null?null:adnJO.getDouble("amount"));
													}

													if(accJO.has("totalVestedBalance")){
														JSONObject adnJO=accJO.getJSONObject("totalVestedBalance");
														invDet.setTotVestedBal(adnJO.getString("amount")==null?null:adnJO.getDouble("amount"));
													}

													if(accJO.has("totalUnvestedBalance")){
														JSONObject adnJO=accJO.getJSONObject("totalUnvestedBalance");
														invDet.setTotUnvestedBal(adnJO.getString("amount")==null?null:adnJO.getDouble("amount"));
													}


													invDet.setInsertedOn(CommonUtil.getCurrentTimeStamp());
													invDet.setInsertedBy(loggedInUsers.get(invUserId).getId());
													investmentDetailsDAO.insertInvestmentDetails(invDet);


													logger.info(loggedInUsers.get(invUserId).getId()+" : "+sd.getId()+" : "+id.getId()+" : "+ad.getId());
													List<ConsolidateData> conDataLst=consolidateDataDAO.findByWhereCluase(" USER_LOG_ID=? AND  SITE_DET_ID=? AND ITEM_DET_ID=? AND ACC_DET_ID=?", new Object[]{loggedInUsers.get(invUserId).getId(),sd.getId(),id.getId(),ad.getId()});

													if(conDataLst==null || conDataLst.size()==0){
														logger.info("Consilidated data not available.");
														ConsolidateData consData=new ConsolidateData();
														UserLogon ul=new UserLogon();
														ul.setId(loggedInUsers.get(invUserId).getId());
														consData.setUserLogon(ul);
														consData.setSiteDetail(sd);
														consData.setItemDetail(id);
														consData.setAccountDetail(ad);
														consData.setPfolioDetId(invDet.getId());
														consData.setAccType(invDet.getAccType());
														consData.setAvailBal(invDet.getTotBal());
														consData.setInsertedOn(CommonUtil.getCurrentTimeStamp());
														consData.setInsertedBy(loggedInUsers.get(invUserId).getId());

														consolidateDataDAO.insertConsolidateData(consData);
													}else{

														ConsolidateData consData=conDataLst.get(0);
														logger.info("Consilidated data available : "+consData.getId());
														consData.setPfolioDetId(invDet.getId());
														consData.setAccType(invDet.getAccType());
														consData.setAvailBal(invDet.getTotBal());
														consData.setUpdatedOn(CommonUtil.getCurrentTimeStamp());
														consData.setUpdatedBy(loggedInUsers.get(invUserId).getId());
														consolidateDataDAO.updateConsolidateData(consData);
													}


													invDet.setAccountDetail(null);
													invSet.add(invDet);

													ad.setItemDetail(null);

													ad.setBankDetails(null);
													ad.setBankTransaction(null);

													ad.setCardDetails(null);
													ad.setCardStatements(null);
													ad.setCardTransactiones(null);

													ad.setLoanDetails(null);
													ad.setLoanHoldinges(null);
													ad.setLoanTransaction(null);

													ad.setInvestmentDetails(invSet);
													ad.setInvestmentHoldinges(null);
													ad.setInvestmentTransaction(null);

													accSet.add(ad);


												}

												id.setAccountDetails(accSet);

											}
										}
									}
								}



								if(id.getContServName().equals("loans")){

									if(jo.has("itemData")){

										JSONObject itemDataJO=jo.getJSONObject("itemData");
										if(itemDataJO.has("accounts")){
											JSONArray accountsJA=itemDataJO.getJSONArray("accounts");
											int accArrLen=accountsJA.length();
											Set<AccountDetail> accSet=new LinkedHashSet<AccountDetail>();
											if(accArrLen>0){

												for (int j = 0; j < accountsJA.length(); j++) {
													JSONObject accJO= accountsJA.getJSONObject(j);

													Set<LoanDetail> loanSet=new LinkedHashSet<LoanDetail>();
													if(accJO.has("loans")){
														JSONArray loanAccountsJA=accJO.getJSONArray("loans");
														int loanArrLen=loanAccountsJA.length();
														if(loanArrLen>0){

															for (int k = 0; k < loanAccountsJA.length(); k++) {
																JSONObject loanAccJO= loanAccountsJA.getJSONObject(k);

																AccountDetail ad=new AccountDetail();
																ad.setItemDetail(id);

																//id.setContServName(siteRefreshModeJO.getString("containerName")==null?null:siteRefreshModeJO.getString("containerName"));
																ad.setAccId(loanAccJO.getString("accountId")==null?null:loanAccJO.getLong("accountId"));
																ad.setItemAccId(loanAccJO.getString("itemAccountId")==null?null:loanAccJO.getLong("itemAccountId"));
																ad.setAccName(loanAccJO.getString("accountName")==null?null:loanAccJO.getString("accountName"));

																ad.setInsertedOn(CommonUtil.getCurrentTimeStamp());
																ad.setInsertedBy(loggedInUsers.get(invUserId).getId());
																List<AccountDetail> accLst=accountDetailsDAO.findByWhereCluase("itemAccId="+ad.getItemAccId());
																if(accLst==null || accLst.size()==0){
																	accountDetailsDAO.insertAccountDetails(ad);
																}else{
																	ad.setId(accLst.get(0).getId());
																}

																LoanDetail loanDet=new LoanDetail();
																loanDet.setAccountDetail(ad);

																loanDet.setAccName(loanAccJO.getString("accountName")==null?null:loanAccJO.getString("accountName"));
																loanDet.setAccNum(loanAccJO.getString("accountNumber")==null?null:loanAccJO.getString("accountNumber"));
																loanDet.setCollateral(loanAccJO.getString("collateral")==null?null:loanAccJO.getString("collateral"));

																loanDet.setDescription(loanAccJO.getString("description")==null?null:loanAccJO.getString("description"));
																loanDet.setTypeLoan(loanAccJO.getString("loanType")==null?null:loanAccJO.getString("loanType"));

																loanDet.setIntRate(loanAccJO.getString("interestRate")==null?null:loanAccJO.getDouble("interestRate"));
																loanDet.setIntRateType(loanAccJO.getString("loanInterestRateType")==null?null:loanAccJO.getString("loanInterestRateType"));


														loanDet.setDueDate(loanAccJO.getString("dueDate")==null?null:loanAccJO.getString("dueDate"));
														loanDet.setLastPayDate(loanAccJO.getString("lastPaymentDate")==null?null:loanAccJO.getString("lastPaymentDate"));
														loanDet.setMatDate(loanAccJO.getString("maturityDate")==null?null:loanAccJO.getString("maturityDate"));
														loanDet.setOriginationDate(loanAccJO.getString("originationDate")==null?null:loanAccJO.getString("originationDate"));
														loanDet.setFirstPayDate(loanAccJO.getString("firstPaymentDate")==null?null:loanAccJO.getString("firstPaymentDate"));
													

																//date yyyy-MM-dd




																if(loanAccJO.has("accountClassification")){
																	JSONObject adnJO=loanAccJO.getJSONObject("accountClassification");
																	loanDet.setAccClassification(adnJO.getString("accountClassification")==null?null:adnJO.getString("accountClassification"));
																}

																if(loanAccJO.has("interestPaidLastYear")){
																	JSONObject adnJO=loanAccJO.getJSONObject("interestPaidLastYear");
																	loanDet.setIntPaidLastYear(adnJO.getString("amount")==null?null:adnJO.getDouble("amount"));
																}

																if(loanAccJO.has("lastPaymentAmount")){
																	JSONObject adnJO=loanAccJO.getJSONObject("lastPaymentAmount");
																	loanDet.setLastPayAmt(adnJO.getString("amount")==null?null:adnJO.getDouble("amount"));
																}

																if(loanAccJO.has("originalLoanAmount")){
																	JSONObject adnJO=loanAccJO.getJSONObject("originalLoanAmount");
																	loanDet.setOrgnlLoanAmt(adnJO.getString("amount")==null?null:adnJO.getDouble("amount"));
																}

																if(loanAccJO.has("principalBalance")){
																	JSONObject adnJO=loanAccJO.getJSONObject("principalBalance");
																	loanDet.setPrincBal(adnJO.getString("amount")==null?null:adnJO.getDouble("amount"));
																}

																if(loanAccJO.has("interestPaidYtd")){
																	JSONObject adnJO=loanAccJO.getJSONObject("interestPaidYtd");
																	loanDet.setIntPaidYtd(adnJO.getString("amount")==null?null:adnJO.getDouble("amount"));
																}



																loanDet.setInsertedOn(CommonUtil.getCurrentTimeStamp());
																loanDet.setInsertedBy(loggedInUsers.get(invUserId).getId());
																loanDetailsDAO.insertLoanDetails(loanDet);


																logger.info(loggedInUsers.get(invUserId).getId()+" : "+sd.getId()+" : "+id.getId()+" : "+ad.getId());
																List<ConsolidateData> conDataLst=consolidateDataDAO.findByWhereCluase(" USER_LOG_ID=? AND  SITE_DET_ID=? AND ITEM_DET_ID=? AND ACC_DET_ID=?", new Object[]{loggedInUsers.get(invUserId).getId(),sd.getId(),id.getId(),ad.getId()});

																if(conDataLst==null || conDataLst.size()==0){
																	logger.info("Consilidated data not available.");
																	ConsolidateData consData=new ConsolidateData();
																	UserLogon ul=new UserLogon();
																	ul.setId(loggedInUsers.get(invUserId).getId());
																	consData.setUserLogon(ul);
																	consData.setSiteDetail(sd);
																	consData.setItemDetail(id);
																	consData.setAccountDetail(ad);
																	consData.setPfolioDetId(loanDet.getId());
																	consData.setAccType(loanDet.getTypeLoan());
																	consData.setAvailBal(loanDet.getPrincBal());
																	consData.setInsertedOn(CommonUtil.getCurrentTimeStamp());
																	consData.setInsertedBy(loggedInUsers.get(invUserId).getId());

																	consolidateDataDAO.insertConsolidateData(consData);
																}else{

																	ConsolidateData consData=conDataLst.get(0);
																	logger.info("Consilidated data available : "+consData.getId());
																	consData.setPfolioDetId(loanDet.getId());
																	consData.setAccType(loanDet.getTypeLoan());
																	consData.setAvailBal(loanDet.getPrincBal());
																	consData.setUpdatedOn(CommonUtil.getCurrentTimeStamp());
																	consData.setUpdatedBy(loggedInUsers.get(invUserId).getId());
																	consolidateDataDAO.updateConsolidateData(consData);
																}


																loanDet.setAccountDetail(null);
																loanSet.add(loanDet);


																ad.setItemDetail(null);

																ad.setBankDetails(null);
																ad.setBankTransaction(null);

																ad.setCardDetails(null);
																ad.setCardStatements(null);
																ad.setCardTransactiones(null);

																ad.setLoanDetails(loanSet);
																ad.setLoanHoldinges(null);
																ad.setLoanTransaction(null);

																ad.setInvestmentDetails(null);
																ad.setInvestmentHoldinges(null);
																ad.setInvestmentTransaction(null);

																accSet.add(ad);
															}

															id.setAccountDetails(accSet);
														}


													}
												}

											}
										}
									}
								}

							}
						}


						logger.info(" ID :"+id.getContServId()+" : "+id.getContServName());

						id.setConsolidateDatas(null);
						itemList.add(id);
					}
					logger.info("itemList Size : "+itemList.size());
					resultMap.put("itemDetails", itemList);

				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return resultMap;

	}

	*/
	
	/*public Map<String, Object> advisorLogin() {
	Map<String, Object> resultMap=null;
	JSONObject jb=null;
	try{
		logger.info("YodleeAPIServiceImpl.advisorLogin()");
		jb=yodleeAPIRepo.loginCobrand(Parameters.COBRAND_LOGIN, Parameters.COBRAND_PASSWORD);
		JSONObject userConvCreds = jb.getJSONObject("cobrandConversationCredentials");
		resultMap=new HashMap<String, Object>();
		cobrandSessionToken=(String) userConvCreds.get("sessionToken");
		resultMap.put("cobrandSessionToken", cobrandSessionToken);
		//resultMap.put("cobrandSessionToken", (String) userConvCreds.get("sessionToken"));
	}catch(Exception e){
		e.printStackTrace();
	}
	return resultMap;
}*/
}