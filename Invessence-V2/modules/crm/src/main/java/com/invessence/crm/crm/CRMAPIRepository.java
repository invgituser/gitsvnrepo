//package com.invessence.aggr.crm;
//
//import java.util.*;
//import java.util.regex.*;
//import javax.net.ssl.*;
//
//import com.fasterxml.jackson.annotation.JsonInclude;
//import com.fasterxml.jackson.databind.*;
//import com.invessence.aggr.bean.*;
//import com.invessence.aggr.bean.Url;
//import com.invessence.aggr.dao.RedTailAuditsDAO;
//import com.invessence.aggr.model.RedTailAudit;
//import com.invessence.aggr.provider.redTail.ResponseParser;
//import com.invessence.aggr.provider.redTail.bean.Error;
//import com.invessence.aggr.provider.redTail.bean.*;
//import com.invessence.aggr.util.AggregatorMessages;
//import com.invessence.service.util.*;
//import org.apache.http.HttpResponse;
//import org.apache.http.client.methods.*;
//import org.apache.http.entity.*;
//import org.apache.http.impl.client.DefaultHttpClient;
//import org.apache.http.util.EntityUtils;
//import org.apache.log4j.Logger;
//import org.json.JSONObject;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//public class CRMAPIRepository<T>
//{
//
//	private static final Logger logger = Logger.getLogger(CRMAPIRepository.class);
//	private String MDX_REAL_TIME_API = null;
//	private String SSO_API_URI = null;
//	private String USER_REGISTER_URL =null;// "/users";
//	private String WIDGET_URL =null;// "/users/U1234/urls/master_widget"; //master_widget mobile_master_widget mini_accounts_widget
//
//	public UserProfile registerUser(UserProfile userProfile)
//	{
//		//UserProfile userProfile = null;
//
//		logger.info("In RedTailAPIRepository.registerUser()");
//		DefaultHttpClient httpclient = new DefaultHttpClient();
//		String responseJSON =null;
//		User user1=null;
//		User user=null;
//		try
//		{
//			MDX_REAL_TIME_API = replaceParamsWithIPValue(ServiceParameters.getConfigProperty(Constant.SERVICES.CRM_SERVICES.toString(), Constant.CRM_SERVICES.REDTAIL.toString(),"MDX_REAL_TIME_API"),
//									"CLIENT_ID", "$$",ServiceParameters.getConfigProperty(Constant.SERVICES.CRM_SERVICES.toString(), Constant.CRM_SERVICES.REDTAIL.toString(), "CLIENT_ID"));
//
//			USER_REGISTER_URL = ServiceParameters.getConfigProperty(Constant.SERVICES.CRM_SERVICES.toString(), Constant.CRM_SERVICES.REDTAIL.toString(), "USER_REGISTER_URL");
//
//			String requestUrl = MDX_REAL_TIME_API + USER_REGISTER_URL + ".json";
//
//			user=userProfile.convertToRedTailBean();
//
//			ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//			mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
//			mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
//			mapper.enable(SerializationFeature.WRAP_ROOT_VALUE);
//
//			String jsonString = mapper.writeValueAsString(user);
//			System.out.println("jsonString = " + jsonString);
//
//			responseJSON=processRedTailRequest("POST", "REGISTER_USER", requestUrl, "application/vnd.moneydesktop.mdx.v5+json", jsonString);
//
//			user1 = new ResponseParser<User>().parse(User.class,responseJSON);
//			System.out.println("userProfile1 = " + user1);
//			if(user1==null){
//				error = new ResponseParser<Error>().parse(Error.class,responseJSON);
//				if(error==null){
//					userProfile.setErrorStatus(new Status(AggregatorMessages.wsEGenErrCode, AggregatorMessages.wsEGenErrMsg));
//				}else{
//					userProfile.setErrorStatus(new Status(555,error.getMessage()));
//				}
//			}else{
//				user1.convertToAggrBean(userProfile);
//				userProfile.setPwd("XXXX");
//				userProfile.setErrorStatus(new Status(0,"Success"));
//			}
//		}
//		catch (Exception e)
//		{
//			e.printStackTrace();
//			userProfile.setErrorStatus(new Status(AggregatorMessages.wsEGenErrCode, AggregatorMessages.wsEGenErrMsg));
//		}
//		logger.info("Out RedTailAPIRepository.registerUser()");
//		return userProfile;
//	}
//
//	public String readUser(String userID)
//	{
//		logger.info("In RedTailAPIRepository.readUser()");
//		MDX_REAL_TIME_API = replaceParamsWithIPValue(ServiceParameters.getConfigProperty(Constant.SERVICES.CRM_SERVICES.toString(), Constant.CRM_SERVICES.REDTAIL.toString(),
//																													"MDX_REAL_TIME_API"), "CLIENT_ID", "$$",
//																	ServiceParameters.getConfigProperty(Constant.SERVICES.CRM_SERVICES.toString(), Constant.CRM_SERVICES.REDTAIL.toString(), "CLIENT_ID"));
//
//		USER_REGISTER_URL = ServiceParameters.getConfigProperty(Constant.SERVICES.CRM_SERVICES.toString(), Constant.CRM_SERVICES.REDTAIL.toString(), "USER_REGISTER_URL");
//
//		String responseJSON =null;
//		String url = MDX_REAL_TIME_API + USER_REGISTER_URL + "/" + userID + ".json";
//		try
//		{
//			ObjectMapper mapper = new ObjectMapper();
//			mapper.enable(DeserializationFeature.UNWRAP_ROOT_VALUE);
//			responseJSON=processRedTailRequest("GET", "READ_USER", url, "application/vnd.moneydesktop.mdx.v5+json", null);
//		}
//		catch (Exception e)
//		{
//			e.printStackTrace();
//		}
//		logger.info("Out RedTailAPIRepository.readUser()");
//		return responseJSON;
//	}
//
//	public String updateUser(String userID)
//	{
//		logger.info("In RedTailAPIRepository.updateUser()");
//
//		DefaultHttpClient httpclient = new DefaultHttpClient();
//		JSONObject jsonObject = null;
//		String url = MDX_REAL_TIME_API + USER_REGISTER_URL + "/" + userID + ".json";
//		try
//		{
//			HttpsURLConnection.setDefaultHostnameVerifier(new NullHostnameVerifier());
//
//			DefaultHttpClient httpClient = new DefaultHttpClient();
//			HttpPut httpRequest = new HttpPut(url);
//			httpRequest.addHeader("Content-Type", "application/vnd.moneydesktop.sso.v3+json");
//			httpRequest.addHeader("Accept", "application/vnd.moneydesktop.sso.v3+json");
//			httpRequest.addHeader("MD-API-KEY", "66c8b276799a15d090a8a1d522d60acbbc3132e5");
//			System.out.println("pm = " + httpRequest);
//			HttpResponse httpresponse = httpclient.execute(httpRequest);
//			System.out.println("httpresponse = " + httpresponse.getEntity());
//			String responseXml = EntityUtils.toString(httpresponse.getEntity());
//			System.out.println("responseXml = " + responseXml);
//
//		}
//		catch (Exception e)
//		{
//			e.printStackTrace();
//		}
//		finally
//		{
//			httpclient.getConnectionManager().shutdown();
//		}
//		logger.info("Out RedTailAPIRepository.updateUser()");
//		return null;
//	}
//
//	public String deleteUser(String userID)
//	{
//		logger.info("In RedTailAPIRepository.deleteUser()");
//		MDX_REAL_TIME_API = replaceParamsWithIPValue(ServiceParameters.getConfigProperty(Constant.SERVICES.CRM_SERVICES.toString(), Constant.CRM_SERVICES.REDTAIL.toString(),
//																													"MDX_REAL_TIME_API"), "CLIENT_ID", "$$",
//																	ServiceParameters.getConfigProperty(Constant.SERVICES.CRM_SERVICES.toString(), Constant.CRM_SERVICES.REDTAIL.toString(), "CLIENT_ID"));
//
//		USER_REGISTER_URL = ServiceParameters.getConfigProperty(Constant.SERVICES.CRM_SERVICES.toString(), Constant.CRM_SERVICES.REDTAIL.toString(), "USER_REGISTER_URL");
//
//		String responseJSON =null;
//		String url = MDX_REAL_TIME_API + USER_REGISTER_URL + "/" + userID + ".json";
//		try
//		{
//			ObjectMapper mapper = new ObjectMapper();
//			mapper.enable(DeserializationFeature.UNWRAP_ROOT_VALUE);
//			responseJSON=processRedTailRequest("DELETE", "DELETE_USER", url, "application/vnd.moneydesktop.mdx.v5+json", null);
//		}
//		catch (Exception e)
//		{
//			e.printStackTrace();
//		}
//
//		logger.info("Out RedTailAPIRepository.deleteUser()");
//		return null;
//	}
//
//	public Url getWidgetUrls(UserProfile userProfile)
//	{
//		logger.info("In RedTailAPIRepository.getWidgetUrls()");
//		Url result=null;
//		com.invessence.aggr.provider.redTail.bean.Url url = null;
//		String responseJSON =null;
//		String requestUrl = null;
//		String reqFor="GET_MAST_WIDGET_URL";
//		try
//		{
//			SSO_API_URI = replaceParamsWithIPValue(ServiceParameters.getConfigProperty(Constant.SERVICES.CRM_SERVICES.toString(), Constant.CRM_SERVICES.REDTAIL.toString(), "SSO_API_URI"),
//																"CLIENT_ID", "$$", ServiceParameters.getConfigProperty(Constant.SERVICES.CRM_SERVICES.toString(), Constant.CRM_SERVICES.REDTAIL.toString(), "CLIENT_ID"));
//			if (userProfile.getMode() == null || userProfile.getMode().equals("") || userProfile.getMode().equalsIgnoreCase("D")){
//				WIDGET_URL = replaceParamsWithIPValue(ServiceParameters.getConfigProperty(Constant.SERVICES.CRM_SERVICES.toString(), Constant.CRM_SERVICES.REDTAIL.toString(), "MAST_WIDGET_URL"),
//																  "USER_ID", "$$", userProfile.getUserName());
//			}else{
//				WIDGET_URL = replaceParamsWithIPValue(ServiceParameters.getConfigProperty(Constant.SERVICES.CRM_SERVICES.toString(), Constant.CRM_SERVICES.REDTAIL.toString(), "MOB_MAST_WIDGET_URL"),
//																  "USER_ID", "$$", userProfile.getUserName());
//				reqFor="GET_MOB_MAST_WIDGET_URL";
//			}
//			requestUrl = SSO_API_URI + WIDGET_URL + ".json";
//
//			responseJSON=processRedTailRequest("GET", reqFor, requestUrl, "application/vnd.moneydesktop.sso.v3+json", null);
//			url = new ResponseParser<com.invessence.aggr.provider.redTail.bean.Url>().parse(com.invessence.aggr.provider.redTail.bean.Url.class, responseJSON);
//			System.out.println("url = " + url);
//			if(url==null){
//				error = new ResponseParser<Error>().parse(Error.class,responseJSON);
//				if(error==null){
//					result=new Url(null,null,null,new Status(AggregatorMessages.wsEGenErrCode, AggregatorMessages.wsEGenErrMsg));
//				}else{
//					result=new Url(null,null,null,new Status(error.getStatus()==null ?555:error.getStatus(),error.getMessage()));
//				}
//			}else{
//				result=url.convertToAggrBean();
//				result.setErrorStatus(new Status(0,"Success"));
//			}
//		}
//		catch (Exception e)
//		{
//			e.printStackTrace();
//			result=new Url(null,null,null,new Status(AggregatorMessages.wsEGenErrCode, AggregatorMessages.wsEGenErrMsg));
//		}
//		logger.info("Out RedTailAPIRepository.getWidgetUrls()");
//		return result;
//	}
//
//	private static class NullHostnameVerifier implements HostnameVerifier
//	{
//		public boolean verify(String hostname, SSLSession session)
//		{
//			return true;
//		}
//	}
//
//	public static void main(String[] args)
//	{
////		new RedTailAPIRepository().registerUser();
////		new RedTailAPIRepository().readUser("INV-12");
////		new RedTailAPIRepository().deleteUser("U1235");
////		new RedTailAPIRepository().getWidgetUrls("1234");
////		new RedTailAPIRepository().replaceParamsWithConfigProperty("https://int-live.moneydesktop.com/$$CLIENT_ID$$//users//U1234.json", "$$");
//	}
//
//	public List<String> getEditParams(String msgBody, String delimiter)
//	{
//		List<String> lstObj = null;
//		try
//		{
//			lstObj = new ArrayList<String>();
//
//			Matcher m = Pattern.compile(
//				Pattern.quote(delimiter) + "(.*?)" + Pattern.quote(delimiter)
//			).matcher(msgBody);
//			while (m.find())
//			{
//				String match = m.group(1);
//				System.out.println(">" + match + "<");
//				//here you insert 'match' into the list
//				lstObj.add(match);
//			}
//		}
//		catch (Exception e)
//		{
//			e.printStackTrace();
//		}
//		return lstObj;
//	}
//
//	public String replaceParamsWithConfigProperty(String msgBody, String delimiter)
//	{
//
//		List<String> lstEditParams = null;
//		try
//		{
//			if (msgBody == null || msgBody.equals(""))
//			{
//				logger.info("MsgBody are not available for replacement!");
//			}
//			else
//			{
//				lstEditParams = getEditParams(msgBody, delimiter);
//				if (lstEditParams == null || lstEditParams.size() <= 0)
//				{
//					logger.info("Parameters are not available in MsgBody for replacement!");
//				}
//				else
//				{
//					Iterator<String> itr = lstEditParams.iterator();
//					while (itr.hasNext())
//					{
//						String param = (String) itr.next();
//						System.out.println("Param trim:" + param.trim());
//						System.out.println("Param :" + delimiter + param + delimiter);
//						String paramWithDelimiter = delimiter + param + delimiter;
//						String paramValue = ServiceParameters.getConfigProperty(Constant.SERVICES.CRM_SERVICES.toString(), Constant.CRM_SERVICES.REDTAIL.toString(), param);
//						System.out.println("paramValue = " + paramValue);
//						msgBody = msgBody.replace(paramWithDelimiter, paramValue);
//						System.out.println("msgBody = " + msgBody);
//					}
//				}
//			}
//		}
//		catch (Exception e)
//		{
//			e.printStackTrace();
//		}
//		return msgBody;
//	}
//
//	public String replaceParamsWithIPValue(String msgBody, String param, String delimiter, Object dataObject)
//	{
//
//		try
//		{
//			if (msgBody == null || msgBody.equals(""))
//			{
//				logger.info("MsgBody are not available for replacement!");
//			}
//			else
//			{
//				if (msgBody.contains(param))
//				{
//					String paramWithDelimiter = delimiter + param + delimiter;
//					if (dataObject == null)
//					{
//						logger.info("DataObject are not available for replacement!");
//					}
//					else
//					{
//						String paramValue = (String) dataObject;
//						System.out.println("paramValue = " + paramValue);
//						msgBody = msgBody.replace(paramWithDelimiter, paramValue);
//						System.out.println("msgBody = " + msgBody);
//					}
//				}
//				else
//				{
//					logger.info("Parameters are not available in MsgBody for replacement!");
//				}
//			}
//		}
//		catch (Exception e)
//		{
//			e.printStackTrace();
//		}
//		return msgBody;
//	}
//
//	public void replaceEditParams(String msgBody, String delimiter, List<String> lstEditParams, Object dataObject)
//	{
//
//		try
//		{
//			Iterator<String> itr = lstEditParams.iterator();
//			while (itr.hasNext())
//			{
//				String string = (String) itr.next();
//				System.out.println("Param trim:" + string.trim());
//				System.out.println("Param :" + delimiter + string + delimiter);
//			}
//		}
//		catch (Exception e)
//		{
//			e.printStackTrace();
//		}
//	}
//
//	public String processRedTailRequest(String reqType, String reqFor, String url, String contentType, String entityString)
//	{
//		RedTailAudit redTailAudit = null;
//		String responseJSON=null;
//		HttpEntityEnclosingRequestBase httpEnclRequest=null;
//		HttpRequestBase httpBaseRequest=null;
//		try
//		{
//			if (reqType.equals("POST"))
//			{
//				httpEnclRequest = new HttpPost(url);
//				System.out.println("entityString :"+entityString);
//				StringEntity xmlEntity = new StringEntity(entityString, ContentType.create("application/json"));
//				httpEnclRequest.setEntity(xmlEntity);
//
//				responseJSON = executeRedTailRequest(httpEnclRequest, null, reqType, reqFor, url, contentType);
//			}
//			else if (reqType.equals("GET"))
//			{
//				httpBaseRequest = new HttpGet(url);
//				responseJSON = executeRedTailRequest(null, httpBaseRequest, reqType, reqFor, url, contentType);
//			}else if (reqType.equals("DELETE"))
//			{
//				httpBaseRequest = new HttpDelete(url);
//				responseJSON = executeRedTailRequest(null, httpBaseRequest, reqType, reqFor, url, contentType);
//			}
//		}
//		catch (Exception e)
//		{	e.printStackTrace();
//		}
//
//		return responseJSON;
//	}
//
//	public String executeRedTailRequest(HttpEntityEnclosingRequestBase httpEnclRequest, HttpRequestBase httpBaseRequest, String reqType, String reqFor, String url, String contentType)
//	{
//		DefaultHttpClient httpClient = null;
//		String responseJSON=null;
//		HttpResponse httpResponse=null;
//		RedTailAudit redTailAudit = null;
//		try
//		{
//			httpClient = new DefaultHttpClient();
//			if(httpEnclRequest!=null){
//				httpEnclRequest.addHeader("Content-Type", contentType);
//				httpEnclRequest.addHeader("Accept", contentType);
//				httpEnclRequest.addHeader("MD-API-KEY", ServiceParameters.getConfigProperty(Constant.SERVICES.CRM_SERVICES.toString(), Constant.CRM_SERVICES.REDTAIL.toString(), "MD_API_KEY"));
//
//				redTailAudit = new RedTailAudit(null, reqFor, url + httpEnclRequest.getEntity().toString() + httpEnclRequest.getAllHeaders(), null, "I", null, new Date(), null, null);
//				//redTailAuditsDAO.insertRedTailAudits(redTailAudit);
//
//				System.out.println("httpEnclRequest = " + httpEnclRequest.toString());
//				httpResponse = httpClient.execute(httpEnclRequest);
//			}else if(httpBaseRequest!=null){
//				httpBaseRequest.addHeader("Content-Type", contentType);
//				httpBaseRequest.addHeader("Accept", contentType);
//				httpBaseRequest.addHeader("MD-API-KEY", ServiceParameters.getConfigProperty(Constant.SERVICES.CRM_SERVICES.toString(), Constant.CRM_SERVICES.REDTAIL.toString(), "MD_API_KEY"));
//
//				redTailAudit = new RedTailAudit(null, reqFor, url + httpBaseRequest.getAllHeaders(), null, "I", null, new Date(), null, null);
//				//redTailAuditsDAO.insertRedTailAudits(redTailAudit);
//
//				System.out.println("httpBaseRequest = " + httpBaseRequest.toString());
//				httpResponse = httpClient.execute(httpBaseRequest);
//			}
//			System.out.println("Response Code :"+httpResponse.getStatusLine().getStatusCode());
//			System.out.println("HttpResponse :"+getJsonResponse(httpResponse));
//			responseJSON = EntityUtils.toString(httpResponse.getEntity());
//
//			System.out.println("httpResponse = " + httpResponse.getEntity());
//			System.out.println("responseJSON = " + responseJSON);
//
//
//
//			redTailAudit.setResStatusCode(httpResponse.getStatusLine().getStatusCode());
//			redTailAudit.setResObj(responseJSON);
//			redTailAudit.setResTime(new Date());
//			redTailAudit.setStatus("S");
//			//redTailAuditsDAO.updateRedTailAudits(redTailAudit);
//		}catch (Exception e)
//		{
//			System.out.println("e.getMessage() = " + e.getMessage());
//			redTailAudit.setResObj(e.getMessage());
//			redTailAudit.setResTime(new Date());
//			redTailAudit.setStatus("E");
//			//redTailAuditsDAO.updateRedTailAudits(redTailAudit);
//			e.printStackTrace();
//		}
//		finally
//		{
//			httpClient.getConnectionManager().shutdown();
//		}
//		return responseJSON;
//	}
//
//	private String getJsonResponse(HttpResponse response){
//		int resCode=0;
//		Status reqStatus=null;
//		try{
//			if (resCode == 200)
//			{
//				System.out.println("OK - Everything worked as expected");
//				new Status(0,"Success");
//			}
//			else if (resCode == 204)
//			{
//				System.out.println("No Content - Everything worked, but no content is being returned. Typically returned from a DELETE request");
//				new Status(0,"Success");
//			}
//			else if (resCode == 400)
//			{
//				System.out.println("Bad Request - Invalid or malformed request body.");
//				new Status(101,"Bad Request - Invalid or malformed request body.");
//			}
//			else if (resCode == 401)
//			{
//				System.out.println("Unauthorized - No valid API key provided.");
//				new Status(102,"Unauthorized - No valid API key provided.");
//			}
//			else if (resCode == 403)
//			{
//				System.out.println("Forbidden - The IP address doesn’t match the API key provided.");
//			}
//			else if (resCode == 404)
//			{
//				System.out.println("Not Found - The requested item doesn’t exist.");
//			}
//			else if (resCode == 409)
//			{
//				System.out.println("Conflict - The object being created already exists. Can only be returned from a POST request.");
//			}
//			else if (resCode == 422)
//			{
//				System.out.println("Unprocessable Entity - The data provided does not meet acceptable criteria. Typically returned from a POST or PUT request, when attempting to add a field with more characters than allowed in the Data Spec, or when a field contains invalid characters.");
//			}
//			else if (resCode == 500 || resCode == 502 || resCode == 504)
//			{
//				System.out.println("Server errors - Something went wrong on RedTail’s end.");
//			}
//			else if (resCode == 503)
//			{
//				System.out.println("Service Unavailable - The RedTail Platform is being updated.");
//			}
//
//		}catch(Exception e){
//
//		}
//
//		return null;
//	}
//
//
//	private void getMethod(){
//
//	}
//
//	private void postMethod(){
//
//	}
//
//}