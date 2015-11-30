package com.invessence.yodlee.service;

import java.util.*;

import com.invessence.yodlee.util.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.invessence.yodlee.dao.AccountDetailsDAO;
import com.invessence.yodlee.dao.BankDetailsDAO;
import com.invessence.yodlee.dao.CardDetailsDAO;
import com.invessence.yodlee.dao.InvestmentDetailsDAO;
import com.invessence.yodlee.dao.ItemDetailsDAO;
import com.invessence.yodlee.dao.LoanDetailsDAO;
import com.invessence.yodlee.dao.SiteDetailsDAO;
import com.invessence.yodlee.dao.UserLogonDAO;
import com.invessence.yodlee.dao.ConsolidateDataDAO;
import com.invessence.yodlee.model.AccountDetail;
import com.invessence.yodlee.model.BankDetail;
import com.invessence.yodlee.model.CardDetail;
import com.invessence.yodlee.model.InvestmentDetail;
import com.invessence.yodlee.model.ItemDetail;
import com.invessence.yodlee.model.LoanDetail;
import com.invessence.yodlee.model.SiteDetail;
import com.invessence.yodlee.model.UserLogon;
import com.invessence.yodlee.model.ConsolidateData;
import com.invessence.yodlee.model.YodleeError;

public class YodleeAPIServiceImpl implements YodleeAPIService {
	
/*	@Value("${COBRAND_LOGIN}") private String COBRAND_LOGIN;
	@Value("${COBRAND_PASSWORD}") private String COBRAND_PASSWORD;
	
	@Value("${BRIDGE_APP_ID}") private String BRIDGE_APP_ID;
	@Value("${APPLICATION_KEY}") private String APPLICATION_KEY;
	@Value("${APPLICATION_TOKEN}") private String APPLICATION_TOKEN;
	
	@Value("${FL_ADD_ACC_URL}") private String FL_ADD_ACC_URL;
	@Value("${FL_ADD_ACC_PARAM}") private String FL_ADD_ACC_PARAM;
	
	@Value("${FL_EDIT_ACC_URL}") private String FL_EDIT_ACC_URL;
	@Value("${FL_EDIT_ACC_PARAM}") private String FL_EDIT_ACC_PARAM;
	
	@Value("${FL_REFR_URL}") private String FL_REFR_URL;
	@Value("${FL_REFR_PARAM}") private String FL_REFR_PARAM;*/
	/*private String COBRAND_LOGIN = "sandbox124";
	private String COBRAND_PASSWORD = "Yodlee@123";

	private String BRIDGE_APP_ID = "10003200";
	private String APPLICATION_KEY = "5e1597a5f8fd4401a25c654c93df73b6";
	private String APPLICATION_TOKEN = " 1063669881884e458e606b6194d4e0a4";

	private String FL_ADD_ACC_URL = "https://yisandboxfl.yodleeinteractive.com/appscenter/private-sandbox124/linkAccount.yisandboxfl.action";
	private String FL_ADD_ACC_PARAM = "access_type=oauthdeeplink&displayMode=desktop&oauth_callback=https://www.google.com";

	private String FL_EDIT_ACC_URL = "https://yisandboxfl.yodleeinteractive.com/appscenter/private-sandbox124/prepareEditSiteAccounts.yisandboxfl.action";
	private String FL_EDIT_ACC_PARAM = "access_type=oauthdeeplink&oauth_callback=http://www.google.com&siteAccountId=$SITE_ACC_ID$";

	private String FL_REFR_URL = "https://yisandboxfl.yodleeinteractive.com/appscenter/private-sandbox124/refreshSiteAccount.yisandboxfl.action";
	private String FL_REFR_PARAM = "access_type=oauthdeeplink&oauth_callback=http://www.google.com&siteAccountId=$SITE_ACC_ID$&_flowId=refresh";
	*/private static String cobrandSessionToken;

	public YodleeAPIServiceImpl() {
		System.out.println("**********************************************************************************************************");
		loggedInUsers=new HashMap<Long, UserLogon>();

		System.out.println(Parameters.COBRAND_LOGIN+" : "+Parameters.COBRAND_LOGIN);
	}

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
	private CardDetailsDAO cardDetailsDAO;

	@Autowired
	private LoanDetailsDAO loanDetailsDAO;

	@Autowired
	private ConsolidateDataDAO consolidateDataDAO;


	private HashMap<Long, UserLogon> loggedInUsers=null;

	public Map<String, Object> getInvUserList(){
		Map<String, Object> resultMap=null;
		try{
			System.out.println("YodleeAPIServiceImpl.getUserLogonList()");
			resultMap=new HashMap<String, Object>();
			List<UserLogon> userLst=userLogonDAO.getInvUserList();
			System.out.println("User List Size :"+userLst.size());
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
			if (ulLst == null || ulLst.size() == 0)
			{
				return false;
			}
			else
			{
				return true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}
	public Map<String, Object> userRegistration(Long invUserId) {
		Map<String, Object> resultMap=null;
		JSONObject jb=null;
		try{
			System.out.println("YodleeAPIServiceImpl.advisorLogin()");
			UserLogon invUserDetails=userLogonDAO.getInvUserDetails(invUserId);
			if(invUserDetails==null){
				resultMap=new HashMap<String, Object>();
				System.out.println("Invessence User Details object get Null");
				YodleeError ye=new YodleeError();
				ye.setMessage("User not available in Invessence Database.");
				resultMap.put("errorDetails", ye);
			}else{
				resultMap=new HashMap<String, Object>();
				String password=CommonUtil.passGenerator();

				if(cobrandSessionToken==null){
					coBrandSessionManager();
				}
				System.out.println(cobrandSessionToken+" : "+ invUserDetails.getUserId()+" : "+ password+ "com.yodlee.ext.login.PasswordCredentials"+ invUserDetails.getEmail());

				jb=yodleeAPIRepo.registerUser(cobrandSessionToken, invUserDetails.getUserId(), password, "com.yodlee.ext.login.PasswordCredentials", invUserDetails.getEmail());

				Boolean errCheck = jb.has("errorOccurred");
				if(errCheck==true){
					YodleeError ye=new YodleeError();
					ye.setErrorOccurred(jb.getString("errorOccurred"));
					ye.setExceptionType(jb.getString("exceptionType"));
					ye.setReferenceCode(jb.getString("referenceCode"));
					ye.setMessage(jb.getString("message"));
					resultMap.put("errorDetails", ye);

				}else{
					if(jb.length()>0){
						UserLogon ur=new UserLogon();


						System.out.println("********************************");
						System.out.println(invUserDetails.getUserId());
						System.out.println(invUserId);
						System.out.println(AESencrp.encrypt(password.toString()));
						System.out.println(invUserDetails.getEmail());
						System.out.println(CommonUtil.getCurrentTimeStamp());
						System.out.println(invUserId);
						System.out.println("********************************");

						ur.setUserId(invUserDetails.getUserId());
						ur.setInvUserId(invUserId);
						ur.setPassword(AESencrp.encrypt(password.toString()));
						ur.setEmail(invUserDetails.getEmail());
						ur.setRegisteredOn(CommonUtil.getCurrentTimeStamp());
						ur.setRegisteredBy(invUserId);

						userLogonDAO.insertUserLogon(ur);
						System.out.println("ur.getID() :"+ur.getId());
						JSONObject userConvCreds = jb.getJSONObject("userContext").getJSONObject("conversationCredentials");
						ur.setUserSessionToken((String) userConvCreds.get("sessionToken"));
						//ur.setREGISTERED_BY(uSR_REGISTERED_BY);
						loggedInUsers.put(ur.getInvUserId(), ur);
						ur.setPassword(null);
						ur.setConsolidateDatas(null);
						ur.setSiteDetails(null);
						resultMap.put("userDetails", ur);
					}else{
						System.out.println("-------------------------------");
						System.out.println("Object is empty or null!");
					}
				}
			}

			//resultMap.put("cobrandSessionToken", (String) userConvCreds.get("sessionToken"));
		}catch(Exception e){
			e.printStackTrace();
		}
		return resultMap;
	}


	public Map<String, Object> userUnRegistration(Long invUserId) {
		Map<String, Object> resultMap=null;
		JSONObject jb=null;
		try{
			System.out.println("YodleeAPIServiceImpl.advisorLogin()");

			resultMap=new HashMap<String, Object>();
			jb=yodleeAPIRepo.unRegisterUser(cobrandSessionToken, loggedInUsers.get(invUserId).getUserSessionToken());
			Boolean errCheck = jb.has("errorOccurred");
			if(errCheck==true){
				YodleeError ye=new YodleeError();
				ye.setErrorOccurred(jb.getString("errorOccurred"));
				ye.setExceptionType(jb.getString("exceptionType"));
				ye.setReferenceCode(jb.getString("referenceCode"));
				ye.setMessage(jb.getString("message"));
				resultMap.put("errorDetails", ye);
			}else{
				UserLogon ur=loggedInUsers.get(invUserId);

				loggedInUsers.remove(ur.getInvUserId());
				userLogonDAO.deleteUserLogon(ur);
				if(jb.length()>0){


					//ur.setREGISTERED_BY(uSR_REGISTERED_BY);
				}else{
					System.out.println("-------------------------------");
					System.out.println("Object is empty or null!");
				}
			}
			//resultMap.put("cobrandSessionToken", (String) userConvCreds.get("sessionToken"));
		}catch(Exception e){
			e.printStackTrace();
		}
		return resultMap;
	}




	public Map<String, Object> getUserRegistrationList() {
		Map<String, Object> resultMap=null;
		try{
			System.out.println("YodleeAPIServiceImpl.getUserLogonList()");
			resultMap=new HashMap<String, Object>();
			List<UserLogon> userLst=userLogonDAO.getUserLogonList();
			System.out.println("User List Size :"+userLst.size());
			resultMap.put("userList", userLst);
		}catch(Exception e){
			e.printStackTrace();
		}
		return resultMap;
	}

	public Map<String, Object> advisorLogin() {
		Map<String, Object> resultMap=null;
		JSONObject jb=null;
		try{
			System.out.println("YodleeAPIServiceImpl.advisorLogin()");
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
	}

	public Map<String, Object> userLogin(Long invUserId) {
		Map<String, Object> resultMap=null;
		JSONObject jb=null;
		try{
			if(cobrandSessionToken==null){
				coBrandSessionManager();
			}
			List<UserLogon> ulLst=userLogonDAO.findByWhereCluase("invUserId="+invUserId);
			resultMap=new HashMap<String, Object>();
			//List <SiteDetail> sdl= siteDetailsDAO.findByWhereCluase("SITE_ACC_ID="+sd.getSiteAccId());
			if(ulLst==null || ulLst.size()==0){
				System.out.println("Invessence User Details object get Null");
				YodleeError ye=new YodleeError();
				ye.setMessage("User not available in YodleeProject Database.");
				resultMap.put("errorDetails", ye);
			}else{
				UserLogon ur=ulLst.get(0);
				ur.setSiteDetails(null);
				ur.setConsolidateDatas(null);
				System.out.println("YodleeAPIServiceImpl.usertLogin()");
				jb=yodleeAPIRepo.loginUser(cobrandSessionToken, ur.getUserId(), AESencrp.decrypt(ur.getPassword()));

				Boolean errCheck = jb.has("errorOccurred");
				if(errCheck==true){
					YodleeError ye=new YodleeError();
					ye.setErrorOccurred(jb.getString("errorOccurred")==null?null:jb.getString("errorOccurred"));
					ye.setExceptionType(jb.getString("exceptionType")==null?null:jb.getString("exceptionType"));
					ye.setReferenceCode(jb.getString("referenceCode")==null?null:jb.getString("referenceCode"));
					ye.setMessage(jb.getString("message")==null?null:jb.getString("message"));
					resultMap.put("errorDetails", ye);
				}else if(jb.has("Error")==true){

					JSONArray errJA=jb.getJSONArray("Error");

					int accArrLen=errJA.length();
					if(accArrLen>0){

						for (int j = 0; j < errJA.length(); j++) {
							JSONObject errJO=errJA.getJSONObject(j);
							YodleeError ye=new YodleeError();
							ye.setErrorOccurred(null);
							ye.setExceptionType(null);
							ye.setReferenceCode(null);
							ye.setMessage(errJO.getString("errorDetail")==null?null:errJO.getString("errorDetail"));
							resultMap.put("errorDetails", ye);
							break;
						}

					}

				}else{
					if(jb.length()>0){

						JSONObject userConvCreds = jb.getJSONObject("userContext").getJSONObject("conversationCredentials");
						//resultMap.put("userSessionToken", (String) userConvCreds.get("sessionToken"));
						ur.setUserSessionToken((String) userConvCreds.get("sessionToken"));
						resultMap.put("userDetails", ur);
						loggedInUsers.put(ur.getInvUserId(), ur);

					}
				}


			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return resultMap;
	}

	public Map<String, Object> getAllSiteAccounts(Long invUserId) {
		Map<String, Object> resultMap=null;
		JSONArray ja=null;
		try{
			System.out.println("YodleeAPIServiceImpl.getAllSiteAccounts()");
			System.out.println("cobrandSessionToken :"+cobrandSessionToken+" userSessionToken:"+loggedInUsers.get(invUserId).getUserSessionToken());
			ja=yodleeAPIRepo.getAllSiteAccounts(cobrandSessionToken,loggedInUsers.get(invUserId).getUserSessionToken());

			int arrLen=ja.length();
			System.out.println("Array Size :"+arrLen);

			resultMap=new HashMap<String, Object>();
			List<SiteDetail> siteAccLst=new ArrayList<SiteDetail>();
			if(arrLen==0){
				YodleeError ye=new YodleeError();
				ye.setMessage("Sites are not available for this users.");
				resultMap.put("errorDetails", ye);
			}else if(arrLen>0){

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
					getItemSummariesForSite(sd.getSiteAccId().toString(),invUserId);
					System.out.println(jo.getString("siteAccountId")+" : "+jo.getString("isCustom")+sd.getSiteName());
				}

				resultMap.put("siteDetails", siteAccLst);

			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return resultMap;

	}



	public Map<String, Object> getItemSummariesForSite(String siteAccId, Long invUserId) {
		Map<String, Object> resultMap=null;
		JSONArray ja=null;
		try{
			System.out.println("YodleeAPIServiceImpl.getItemSummariesForSite()");
			resultMap=new HashMap<String, Object>();

			List<SiteDetail> sdLst=siteDetailsDAO.findByWhereCluase("siteAccId="+siteAccId);
			if(sdLst==null || sdLst.size()==0){
				System.out.println("Invessence User Details object get Null");
			}else{
				SiteDetail sd=sdLst.get(0);

				ja=yodleeAPIRepo.getItemSummariesForSite(cobrandSessionToken,loggedInUsers.get(invUserId).getUserSessionToken(), siteAccId);

				int arrLen=ja.length();
				System.out.println("Array Size :"+arrLen);

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
						System.out.println("contentServiceId: "+jo.getLong("contentServiceId"));
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

								System.out.println("containerName: "+id.getContServName());

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
													System.out.println("as of date"+accJO.getString("asOfDate"));

													if(accJO.has("asOfDate")){
														System.out.println(accJO.has("asOfDate") );
														System.out.println(accJO.has("asOfDate") );
														JSONObject adnJO=accJO.getJSONObject("asOfDate");
														System.out.println(accJO.has("asOfDate") );


													}
													//bd.setAsOfDate(accJO.getString("asOfDate")==null?null:new Date(accJO.getString("asOfDate")));
													//bd.setMatDate(accJO.getString("maturityDate")==null?null:new Date(accJO.getString("maturityDate")));

													bd.setAccName(accJO.getString("accountName")==null?null:accJO.getString("accountName"));

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
														bd.setAvilbBal(adnJO.getString("amount")==null?null:adnJO.getDouble("amount"));
													}

													if(accJO.has("currentBalance")){
														JSONObject adnJO=accJO.getJSONObject("currentBalance");
														bd.setCurBal(adnJO.getString("amount")==null?null:adnJO.getDouble("amount"));
													}

													bd.setInsertedOn(CommonUtil.getCurrentTimeStamp());
													bd.setInsertedBy(loggedInUsers.get(invUserId).getId());
													bankDetailsDAO.insertBankDetails(bd);

													System.out.println(loggedInUsers.get(invUserId).getId()+" : "+sd.getId()+" : "+id.getId()+" : "+ad.getId());
													List<ConsolidateData> conDataLst=consolidateDataDAO.findByWhereCluase(" USER_LOG_ID=? AND  SITE_DET_ID=? AND ITEM_DET_ID=? AND ACC_DET_ID=?", new Object[]{loggedInUsers.get(invUserId).getId(),sd.getId(),id.getId(),ad.getId()});

													if(conDataLst==null || conDataLst.size()==0){
														System.out.println("Consilidated data not available.");
														ConsolidateData consData=new ConsolidateData();
														UserLogon ul=new UserLogon();
														ul.setId(loggedInUsers.get(invUserId).getId());
														consData.setUserLogon(ul);
														consData.setSiteDetail(sd);
														consData.setItemDetail(id);
														consData.setAccountDetail(ad);
														consData.setPfolioDetId(bd.getId());
														consData.setAccType(bd.getAccType());
														consData.setAvilbBal(bd.getAvilbBal());
														consData.setInsertedOn(CommonUtil.getCurrentTimeStamp());
														consData.setInsertedBy(loggedInUsers.get(invUserId).getId());

														consolidateDataDAO.insertConsolidateData(consData);
													}else{

														ConsolidateData consData=conDataLst.get(0);
														System.out.println("Consilidated data available : "+consData.getId());
														consData.setPfolioDetId(bd.getId());
														consData.setAccType(bd.getAccType());
														consData.setAvilbBal(bd.getAvilbBal());
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
														cardDet.setAvilbCash(adnJO.getString("amount")==null?null:adnJO.getDouble("amount"));
													}

													if(accJO.has("availableCredit")){
														JSONObject adnJO=accJO.getJSONObject("availableCredit");
														cardDet.setAvilbCredit(adnJO.getString("amount")==null?null:adnJO.getDouble("amount"));
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


													System.out.println(loggedInUsers.get(invUserId).getId()+" : "+sd.getId()+" : "+id.getId()+" : "+ad.getId());
													List<ConsolidateData> conDataLst=consolidateDataDAO.findByWhereCluase(" USER_LOG_ID=? AND  SITE_DET_ID=? AND ITEM_DET_ID=? AND ACC_DET_ID=?", new Object[]{loggedInUsers.get(invUserId).getId(),sd.getId(),id.getId(),ad.getId()});

													if(conDataLst==null || conDataLst.size()==0){
														System.out.println("Consilidated data not available.");
														ConsolidateData consData=new ConsolidateData();
														UserLogon ul=new UserLogon();
														ul.setId(loggedInUsers.get(invUserId).getId());
														consData.setUserLogon(ul);
														consData.setSiteDetail(sd);
														consData.setItemDetail(id);
														consData.setAccountDetail(ad);
														consData.setPfolioDetId(cardDet.getId());
														consData.setAccType(cardDet.getAccType());
														consData.setAvilbBal(cardDet.getAvilbCredit());
														consData.setInsertedOn(CommonUtil.getCurrentTimeStamp());
														consData.setInsertedBy(loggedInUsers.get(invUserId).getId());

														consolidateDataDAO.insertConsolidateData(consData);
													}else{

														ConsolidateData consData=conDataLst.get(0);
														System.out.println("Consilidated data available : "+consData.getId());
														consData.setPfolioDetId(cardDet.getId());
														consData.setAccType(cardDet.getAccType());
														consData.setAvilbBal(cardDet.getAvilbCredit());
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


													System.out.println(loggedInUsers.get(invUserId).getId()+" : "+sd.getId()+" : "+id.getId()+" : "+ad.getId());
													List<ConsolidateData> conDataLst=consolidateDataDAO.findByWhereCluase(" USER_LOG_ID=? AND  SITE_DET_ID=? AND ITEM_DET_ID=? AND ACC_DET_ID=?", new Object[]{loggedInUsers.get(invUserId).getId(),sd.getId(),id.getId(),ad.getId()});

													if(conDataLst==null || conDataLst.size()==0){
														System.out.println("Consilidated data not available.");
														ConsolidateData consData=new ConsolidateData();
														UserLogon ul=new UserLogon();
														ul.setId(loggedInUsers.get(invUserId).getId());
														consData.setUserLogon(ul);
														consData.setSiteDetail(sd);
														consData.setItemDetail(id);
														consData.setAccountDetail(ad);
														consData.setPfolioDetId(invDet.getId());
														consData.setAccType(invDet.getAccType());
														consData.setAvilbBal(invDet.getTotBal());
														consData.setInsertedOn(CommonUtil.getCurrentTimeStamp());
														consData.setInsertedBy(loggedInUsers.get(invUserId).getId());

														consolidateDataDAO.insertConsolidateData(consData);
													}else{

														ConsolidateData consData=conDataLst.get(0);
														System.out.println("Consilidated data available : "+consData.getId());
														consData.setPfolioDetId(invDet.getId());
														consData.setAccType(invDet.getAccType());
														consData.setAvilbBal(invDet.getTotBal());
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


														/*loanDet.setDueDate(loanAccJO.getString("dueDate")==null?null:loanAccJO.getString("dueDate"));
														loanDet.setLastPayDate(loanAccJO.getString("lastPaymentDate")==null?null:loanAccJO.getString("lastPaymentDate"));
														loanDet.setMatDate(loanAccJO.getString("maturityDate")==null?null:loanAccJO.getString("maturityDate"));
														loanDet.setOriginationDate(loanAccJO.getString("originationDate")==null?null:loanAccJO.getString("originationDate"));
														loanDet.setFirstPayDate(loanAccJO.getString("firstPaymentDate")==null?null:loanAccJO.getString("firstPaymentDate"));
													*/

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


																System.out.println(loggedInUsers.get(invUserId).getId()+" : "+sd.getId()+" : "+id.getId()+" : "+ad.getId());
																List<ConsolidateData> conDataLst=consolidateDataDAO.findByWhereCluase(" USER_LOG_ID=? AND  SITE_DET_ID=? AND ITEM_DET_ID=? AND ACC_DET_ID=?", new Object[]{loggedInUsers.get(invUserId).getId(),sd.getId(),id.getId(),ad.getId()});

																if(conDataLst==null || conDataLst.size()==0){
																	System.out.println("Consilidated data not available.");
																	ConsolidateData consData=new ConsolidateData();
																	UserLogon ul=new UserLogon();
																	ul.setId(loggedInUsers.get(invUserId).getId());
																	consData.setUserLogon(ul);
																	consData.setSiteDetail(sd);
																	consData.setItemDetail(id);
																	consData.setAccountDetail(ad);
																	consData.setPfolioDetId(loanDet.getId());
																	consData.setAccType(loanDet.getTypeLoan());
																	consData.setAvilbBal(loanDet.getPrincBal());
																	consData.setInsertedOn(CommonUtil.getCurrentTimeStamp());
																	consData.setInsertedBy(loggedInUsers.get(invUserId).getId());

																	consolidateDataDAO.insertConsolidateData(consData);
																}else{

																	ConsolidateData consData=conDataLst.get(0);
																	System.out.println("Consilidated data available : "+consData.getId());
																	consData.setPfolioDetId(loanDet.getId());
																	consData.setAccType(loanDet.getTypeLoan());
																	consData.setAvilbBal(loanDet.getPrincBal());
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


						System.out.println(" ID :"+id.getContServId()+" : "+id.getContServName());

						id.setConsolidateDatas(null);
						itemList.add(id);
					}
					System.out.println("itemList Size : "+itemList.size());
					resultMap.put("itemDetails", itemList);

				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return resultMap;

	}

	public Map<String, Object> getFastLinkDetails(String operation, String siteAccId, Long invUserId) {

		Map<String, Object> resultMap=null;
		JSONObject jb=null;
		try{

			System.out.println("YodleeAPIServiceImpl.getFastLinkDetails()");
			jb=yodleeAPIRepo.getToken(cobrandSessionToken, loggedInUsers.get(invUserId).getUserSessionToken(), Parameters.BRIDGE_APP_ID);
			resultMap=new HashMap<String, Object>();
			Boolean errCheck = jb.has("errorOccurred");
			if(errCheck==true){

				YodleeError ye=new YodleeError();
				ye.setErrorOccurred(jb.getString("errorOccurred"));
				ye.setExceptionType(jb.getString("exceptionType"));
				ye.setReferenceCode(jb.getString("referenceCode"));
				ye.setMessage(jb.getString("message"));
				resultMap.put("errorDetails", ye);
			}else{
				if(jb.length()>0){
					Map<String, String> map=new HashMap<String, String>();

					map.put("OAUTH_TOKEN", jb.getString("token"));
					map.put("OAUTH_TOKEN_SECRET", jb.getString("tokenSecret"));

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
					System.out.println("-------------------------------");
					System.out.println("Object is empty or null!");
				}


				System.out.println("From Session cobrandSessionToken:"+cobrandSessionToken+" & userSessionToken:"+loggedInUsers.get(invUserId).getUserSessionToken());
			}


		}catch(Exception e){
			e.printStackTrace();
		}
		return resultMap;
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


	public static void main(String[] args) {

		ApplicationContext appContext = new ClassPathXmlApplicationContext("bean.xml");
	}

	public YodleeAPIRepository getYodleeAPI() {
		return yodleeAPIRepo;
	}

	public void setYodleeAPI(YodleeAPIRepository yodleeAPI) {
		this.yodleeAPIRepo = yodleeAPI;
	}


	public void coBrandSessionManager(){
		System.out.println("****************************************************************************************");
		System.out.println(new Date());
		System.out.println("coBrandSession "+cobrandSessionToken);

		JSONObject jb=null;
		try{
			System.out.println("YodleeAPIServiceImpl.advisorLogin()");
			jb=yodleeAPIRepo.loginCobrand(Parameters.COBRAND_LOGIN, Parameters.COBRAND_PASSWORD);
			JSONObject userConvCreds = jb.getJSONObject("cobrandConversationCredentials");
			cobrandSessionToken=(String) userConvCreds.get("sessionToken");
			//resultMap.put("cobrandSessionToken", (String) userConvCreds.get("sessionToken"));

			System.out.println("****************************************************************************************");
		}catch(Exception e){
			e.printStackTrace();
		}



	}


	public static String getCobrandSessionToken() {
		return cobrandSessionToken;
	}

	public static void setCobrandSessionToken(String cobrandSessionToken) {
		YodleeAPIServiceImpl.cobrandSessionToken = cobrandSessionToken;
	}

	public HashMap<Long, UserLogon> getLoggedInUsers() {
		return loggedInUsers;
	}

	public void setLoggedInUsers(HashMap<Long, UserLogon> loggedInUsers) {
		this.loggedInUsers = loggedInUsers;
	}

	public Map<String, Object> getUserAccountsDetail(Long invUserId){

		Map<String, Object> resultMap=null;
		try{
			List<UserLogon> ulLst=userLogonDAO.findByWhereCluase("invUserId="+invUserId);
			resultMap=new HashMap<String, Object>();
			//List <SiteDetail> sdl= siteDetailsDAO.findByWhereCluase("SITE_ACC_ID="+sd.getSiteAccId());
			if(ulLst==null || ulLst.size()==0){
				System.out.println("Invessence User Details object get Null");
				YodleeError ye=new YodleeError();
				ye.setMessage("User not available in YodleeProject Database.");
				resultMap.put("errorDetails", ye);
			}else{
				UserLogon ur=ulLst.get(0);

				List<ConsolidateData> consDataList=consolidateDataDAO.findByWhereCluase("USER_LOG_ID="+ur.getId()+" ORDER BY itemDetail.contServName");
				resultMap=new HashMap<String, Object>();
				if(consDataList==null || consDataList.size()==0){
					System.out.println("Invessence User Details object get Null");
					YodleeError ye=new YodleeError();
					ye.setMessage("User not available in YodleeProject Database.");
					resultMap.put("errorDetails", ye);
				}else{
					Iterator<ConsolidateData> itr=consDataList.iterator();
					while (itr.hasNext()) {
						ConsolidateData consolidateData = (ConsolidateData) itr.next();
						System.out.println(consolidateData.getSiteDetail().getSiteName());
						System.out.println(consolidateData.getItemDetail().getItemDispName());
						System.out.println(consolidateData.getItemDetail().getItemId());
						System.out.println(consolidateData.getAccountDetail().getAccName());

					}
					resultMap.put("consDataList", consDataList);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return resultMap;
	}


}
