//package com.invessence.yodlee.util;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import org.json.JSONObject;
//
//import com.invessence.yodlee.model.UserLogon;
//import com.invessence.yodlee.model.YodleeError;
//
//public class JOParser {
//
//	public Map<String, Object> userRegistration(JSONObject jo) {
//		Map<String, Object> resultMap=null;
//		JSONObject jb=null;
//		try{ 	
//				resultMap=new HashMap<String, Object>();
//				Boolean errCheck = jb.has("errorOccurred");
//				if(errCheck==true){
//					YodleeError ye=new YodleeError();
//					ye.setErrorOccurred(jb.getString("errorOccurred"));
//					ye.setExceptionType(jb.getString("exceptionType"));
//					ye.setReferenceCode(jb.getString("referenceCode"));
//					ye.setMessage(jb.getString("message"));
//					resultMap.put("errorDetails", ye);				
//				}else{
//					if(jb.length()>0){
//						UserLogon ur=new UserLogon();
//						//ur.setUserId(userName);
//						/*ur.setPassword(AESencrp.encrypt(password.toString()));
//						//ur.setEmail(email);
//						ur.setRegisteredOn(CommonUtil.getCurrentTimeStamp());
//						
//						userLogonService.insertUserLogon(ur);	*/
//						System.out.println("ur.getID() :"+ur.getId());
//						
//						//ur.setREGISTERED_BY(uSR_REGISTERED_BY);
//					}else{
//						System.out.println("-------------------------------");
//						System.out.println("Object is empty or null!");
//					}
//				}			
//			//resultMap.put("cobrandSessionToken", (String) userConvCreds.get("sessionToken"));	
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//		return resultMap;
//		
//	}
//	
//	
//	public Map<String, Object> siteAccount(JSONObject jo) {
//		Map<String, Object> resultMap=null;
//		JSONObject jb=null;
//		try{ 	
//				resultMap=new HashMap<String, Object>();
//				Boolean errCheck = jb.has("errorOccurred");
//				if(errCheck==true){
//					YodleeError ye=new YodleeError();
//					ye.setErrorOccurred(jb.getString("errorOccurred"));
//					ye.setExceptionType(jb.getString("exceptionType"));
//					ye.setReferenceCode(jb.getString("referenceCode"));
//					ye.setMessage(jb.getString("message"));
//					resultMap.put("errorDetails", ye);				
//				}else{
//					if(jb.length()>0){
//						UserLogon ur=new UserLogon();
//						//ur.setUserId(userName);
//						/*ur.setPassword(AESencrp.encrypt(password.toString()));
//						//ur.setEmail(email);
//						ur.setRegisteredOn(CommonUtil.getCurrentTimeStamp());
//						
//						userLogonService.insertUserLogon(ur);	*/
//						System.out.println("ur.getID() :"+ur.getId());
//						
//						//ur.setREGISTERED_BY(uSR_REGISTERED_BY);
//					}else{
//						System.out.println("-------------------------------");
//						System.out.println("Object is empty or null!");
//					}
//				}			
//			//resultMap.put("cobrandSessionToken", (String) userConvCreds.get("sessionToken"));	
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//		return resultMap;
//		
//	}
//}
