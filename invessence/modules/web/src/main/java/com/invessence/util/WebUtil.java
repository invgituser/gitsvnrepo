package com.invessence.util;

import java.net.*;
import java.util.*;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import com.invessence.constant.Const;
import com.invessence.data.common.UserInfoData;
import org.primefaces.context.RequestContext;
import org.springframework.security.core.*;
import org.springframework.security.core.context.*;

import static javax.faces.context.FacesContext.getCurrentInstance;


public class WebUtil
{

    public static boolean isNull(String val) {
        
        if ( (val == null) || (val.equals("")) )
            return true;
        else
            return false;
    }
    
    
    public static boolean isInteger(String input) {
        
        try {
            Integer.parseInt(input);  
            
        } catch (NumberFormatException ex) {
            return false;
        }
        
        return true;
    }
    

	public static String getValByAttr(HttpServletRequest request, String arg, String defaultVal) {
		
   	 String val = (String) request.getAttribute(arg);
    	
	    if ( WebUtil.isNull(val) ) {
	    	return defaultVal;	    	
	    } else {   	
    	    return val;
	    }
	}
	
	
	public static int getIntValByAttr(HttpServletRequest request, String arg, int defaultVal) {
		
		String integerVal = (String) request.getAttribute(arg);
		
		if (isInteger(integerVal)){
			return Integer.parseInt(integerVal);    	
		} else {   	
			return defaultVal;
		}
	
	}
	
	public static int getIdxByAttribute(HttpServletRequest request, String idxVal) {
		
   	    String idx = (String) request.getAttribute(idxVal);
    	
	    if ( WebUtil.isNull(idx) ) {
	    	idx = "0";	    	
	    }
       	   	
    	return Integer.parseInt(idx);
	}
    
     public String getSource(String urlAddress) {
    	 
         try {
             URL url = new URL(urlAddress);
             String host = url.getHost();
             //System.out.println("host = " + host);
  
             String source = host;
  
             if ( (host != null) && (host.length() > 4) ) {
                 String startStr = host.substring(0, 3);
                 if (startStr.equalsIgnoreCase("www")) {
                     source = host.substring(4);
                 }
             }
  
             //System.out.println("source = " + source);
             return source;
         } catch (Exception e) {
  
             System.out.println(e);
             return "";
         }
     }

   public String getMacAddress(){

      InetAddress ip;
      try {

         ip = InetAddress.getLocalHost();
         //System.out.println("Current IP address : " + ip.getHostAddress());

         NetworkInterface network = NetworkInterface.getByInetAddress(ip);

         byte[] mac = network.getHardwareAddress();

         //System.out.print("Current MAC address : ");

         StringBuilder sb = new StringBuilder();
         for (int i = 0; i < mac.length; i++) {
            sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
         }
         //System.out.println(sb.toString());
         return sb.toString();

      } catch (UnknownHostException e) {

         e.printStackTrace();

      } catch (SocketException e){

         e.printStackTrace();

      }
      return null;

   }

   public String getClientIpAddr(HttpServletRequest request) {
      String ip = request.getHeader("X-Forwarded-For");
      if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
         ip = request.getHeader("Proxy-Client-IP");
      }
      if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
         ip = request.getHeader("WL-Proxy-Client-IP");
      }
      if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
         ip = request.getHeader("HTTP_CLIENT_IP");
      }
      if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
         ip = request.getHeader("HTTP_X_FORWARDED_FOR");
      }
      if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
         ip = request.getRemoteAddr();
      }
      //System.out.println("Current IP:" + ip);
      return ip;
   }

   public void setCookie(String cookieName, String info, Map map) {
      FacesContext.getCurrentInstance()
         .getExternalContext()
         .addResponseCookie(cookieName, info, map);
   }


   public Map<String, Object> getCookie () {
      Map<String, Object> requestCookieMap = FacesContext.getCurrentInstance()
         .getExternalContext()
         .getRequestCookieMap();
      return requestCookieMap;
   }

   public Integer randomGenerator(Integer min, Integer max) {
      Random generator = new Random();
      try {
         Integer newNum = generator.nextInt(max);
         if (newNum < min)
            newNum = min;
         return newNum;
      }
      catch (Exception ex) {
         return (max);
      }
   }

   public Boolean redirecttoMessagePage(String type, String title, String body) {
      String spMsg = "";
      Map<String,String> args = new HashMap<String, String>();
      try {
         if (type != null)
            args.put("type",type);
         if (title != null)
            args.put("title",title);
         if (body != null) {
            args.put("message",body);
            redirect("/message.xhtml?faces-redirect=true",args);
         }
      }
      catch (Exception ex) {
         args.put("message","mbse");
         args.put("type","Error");
         args.put("title","mtse");
         redirect("/message.xhtml?faces-redirect=true",args);
         return false;
      }
      return true;
   }


   public String getMode() {
      return ("Prod");
   }

   public UserInfoData getUserInfoData() {

      try {
         Authentication auth = (Authentication) ((SecurityContext)
            SecurityContextHolder.getContext()).getAuthentication();

         if ( (auth != null) && (auth.getPrincipal() instanceof UserInfoData )) {
            return (UserInfoData) auth.getPrincipal();

         } else {
            return null;
         }

      }
      catch (Exception ex) {
         String url="/message.xhtml?faces-redirect=true&type=Error&title=mtse&message=mbse";
         redirect(url,null);
         System.out.println("Warning: Context data is null, user must not be logged on.");
      }
      return null;

   }

   public String getAccess()
   {
      String access= "User";
      try {
         UserInfoData userInfoData = getUserInfoData();
         if (userInfoData != null) {
            access = userInfoData.getAccess();
         }
      }
      catch (Exception ex) {
         ex.printStackTrace();
      }
      return access;
   }

   public Boolean isUserLoggedIn() {

      if (getUserInfoData() != null)
         return true;
      else
         return false;

   }

   public Boolean validatePriviledge(String role)
   {
      try {
         if (! isUserLoggedIn())
         {
            redirect("/login.xhtml", null);
            return false;
         }
         else
            if (role != null) {
               if (! hasRole(role)) {
                  redirect("/pages/common/AccessDenied.xhtml", null);
                  return false;
               }
            }
      }
      catch (Exception ex) {
         String url="/message.xhtml?faces-redirect=true&type=Error&title=mtse&message=mbse";
         redirect(url,null);
         return false;
      }
      return true;
   }

   public boolean hasRole(String role) {

      try {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            if (principal instanceof UserInfoData ) {
               Collection<GrantedAuthority> roleCollection = ((UserInfoData)principal).getAuthorities();

               if (roleCollection != null ) {
                  for (GrantedAuthority auth: roleCollection) {
                     if (auth.getAuthority().equalsIgnoreCase(role))
                        return true;
                     // Sales and Support are part of Admin functions.  So, Admin will have same access.
                     // However, Sales and Support will not have ADMIN functions.
                     if (auth.getAuthority().equalsIgnoreCase(Const.ROLE_ADMIN))
                        if (role.contains(Const.ROLE_SALES) || role.contains(Const.ROLE_SUPPORT))
                           return true;
                  }
               }
               // If roles table is blank, then they must be USER or OWNER of account.
               if (role.equalsIgnoreCase(Const.ROLE_USER) || role.equalsIgnoreCase(Const.ROLE_OWNER))
                  return true;
            }
      }
      catch (Exception ex) {
         return false;
      }
      return false;
   }

   public Boolean hasAccess(String role) {

      String access = getAccess();
      if (access != null) {
         if (access.equalsIgnoreCase("ADMIN"))
            return true;
         else if (access.equalsIgnoreCase(role))
            return true;
         else
            return false;
      }
      return false;
   }

   public Long getLogonid() {

      try {
         Long logonid = null;
         if (getUserInfoData() != null)
            logonid = getUserInfoData().getLogonID();

         return logonid;
      }
      catch (Exception ex) {
         String url="/message.xhtml?faces-redirect=true&type=Error&title=mtse&message=mbse";
         redirect(url,null);
         System.out.println("Warning: Context data is null, user must not be logged on.");
      }
      return null;
   }

   public Long getAcctnum() {

      try {
         Long acctnum = (Long) getCurrentInstance().getExternalContext().getSessionMap().get(Const.ACCTNO_PARAM);

         return acctnum;
      }
      catch (Exception ex) {
         String url="/message.xhtml?faces-redirect=true&type=Error&title=mtse&message=mbse";
         redirect(url,null);
         System.out.println("Warning: Context data is null, user must not be logged on.");
      }
      return null;
   }

   public void setAcctnum(Long acctnum) {

      try {
         getCurrentInstance().getExternalContext().getSessionMap().put(Const.ACCTNO_PARAM, acctnum);

      }
      catch (Exception ex) {
         String url="/message.xhtml?faces-redirect=true&type=Error&title=mtse&message=mbse";
         redirect(url,null);
         System.out.println("Warning: Context data is null, user must not be logged on.");
      }
   }



   public void redirect(String url, Map obj) {
      String strToPass = "";
      try {
         if (url == null)
            return;
         if (obj != null) {
            String key, val,delimiter;
            Iterator it = obj.entrySet().iterator();
            while (it.hasNext())
            {
               Map.Entry pairs = (Map.Entry) it.next();
               key = pairs.getKey().toString();
               val = pairs.getValue().toString();
               if (strToPass.contains("?") || url.contains("?"))
                  delimiter = "&";
               else
                  delimiter = "?";

               strToPass = strToPass + delimiter +
                  key + "=" + val;
            }
         }
         //System.out.println("Redirecting to:" + url + strToPass);
         getCurrentInstance().getExternalContext().redirect(url + strToPass );
      }
      catch (Exception ex) {
      }
   }

   public void showMessage(String type, String subject, String msg) {
      FacesMessage message;
      if (type.toUpperCase().startsWith("W"))
         message = new FacesMessage(FacesMessage.SEVERITY_WARN, subject, msg);
      else if(type.toUpperCase().startsWith("E"))
         message = new FacesMessage(FacesMessage.SEVERITY_ERROR, subject, msg);
      else
         message = new FacesMessage(FacesMessage.SEVERITY_INFO, subject, msg);

      RequestContext.getCurrentInstance().showMessageInDialog(message);
   }

}
