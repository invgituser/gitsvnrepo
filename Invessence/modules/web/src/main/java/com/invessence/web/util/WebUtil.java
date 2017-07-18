package com.invessence.web.util;

import java.io.*;
import java.net.*;
import java.util.*;
import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import javax.servlet.http.*;

import com.invessence.converter.SQLData;
import com.invessence.emailer.data.MsgData;
import com.invessence.web.constant.WebConst;
import com.invessence.web.data.common.*;
import org.apache.commons.logging.*;
import org.primefaces.context.RequestContext;
import org.primefaces.model.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.core.*;
import org.springframework.security.core.context.*;
import org.springframework.security.web.authentication.logout.*;
import org.springframework.security.web.authentication.rememberme.AbstractRememberMeServices;

import static javax.faces.context.FacesContext.getCurrentInstance;

@ManagedBean(name = "webutil")
@SessionScoped
public class WebUtil implements Serializable
{
   protected final Log webutillooger = LogFactory.getLog(getClass());
   //   public WebProfile webprofile = null;
   public SQLData converter = new SQLData();
   public DataDisplayConverter dataDisplayConverter = new DataDisplayConverter();

   @Autowired
   private WebMessage messageText;

   public WebMessage getMessageText()
   {
      return messageText;
   }

   public WebUtil()
   {
   }

   public SQLData getConverter()
   {
      return converter;
   }

   public DataDisplayConverter getDataDisplayConverter()
   {
      return dataDisplayConverter;
   }

   public WebProfile getWebprofile()
   {
      WebProfile wp = (WebProfile) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(WebConst.WEB_INFO);
      return (wp);
   }

   public boolean isWebProdMode()
   {
      if (getWebprofile() == null)
      {
         return false;
      }

      String mode = getWebprofile().getWebmode();
      if (mode == null)
      {
         return false;
      }
      else if (mode.toUpperCase().equals("PROD"))
      {
         return true;
      }

      return false;
   }

   public static boolean isNull(String val)
   {

      if ((val == null) || (val.equals("")))
      {
         return true;
      }
      else
      {
         return false;
      }
   }


   public static boolean isInteger(String input)
   {

      try
      {
         Integer.parseInt(input);

      }
      catch (NumberFormatException ex)
      {
         return false;
      }

      return true;
   }

   public static String getURLAddress(String defaultVal)
   {

      HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();

      if (request == null)
         return defaultVal;

      String uri = request.getServerName();
      Integer portnum = request.getServerPort();
      String port = portnum.toString();

      if (WebUtil.isNull(uri))
      {
         return defaultVal;
      }
      else
      {
         if (uri.equalsIgnoreCase("localhost")) {
            uri += ':' + port;
         }
         return uri;
      }
   }

   public Boolean getHasSSL()
   {
      return (getIsSecure() && getWebprofile().getSslseal() != null);
   }

   public Boolean getIsSecure()
   {
      HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();

      return (request != null) && (request.isSecure());
   }

   public static String getValByAttr(HttpServletRequest request, String arg, String defaultVal)
   {

      String val = (String) request.getAttribute(arg);

      if (WebUtil.isNull(val))
      {
         return defaultVal;
      }
      else
      {
         return val;
      }
   }


   public static int getIntValByAttr(HttpServletRequest request, String arg, int defaultVal)
   {

      String integerVal = (String) request.getAttribute(arg);

      if (isInteger(integerVal))
      {
         return Integer.parseInt(integerVal);
      }
      else
      {
         return defaultVal;
      }

   }

   public static int getIdxByAttribute(HttpServletRequest request, String idxVal)
   {

      String idx = (String) request.getAttribute(idxVal);

      if (WebUtil.isNull(idx))
      {
         idx = "0";
      }

      return Integer.parseInt(idx);
   }

   public String getSource(String urlAddress)
   {

      try
      {
         URL url = new URL(urlAddress);
         String host = url.getHost();
         //System.out.println("host = " + host);

         String source = host;

         if ((host != null) && (host.length() > 4))
         {
            String startStr = host.substring(0, 3);
            if (startStr.equalsIgnoreCase("www"))
            {
               source = host.substring(4);
            }
         }

         //System.out.println("source = " + source);
         return source;
      }
      catch (Exception e)
      {

         System.out.println(e);
         return "";
      }
   }

   public String getMacAddress()
   {

      InetAddress ip;
      try
      {

         ip = InetAddress.getLocalHost();
         //System.out.println("Current IP address : " + ip.getHostAddress());

         NetworkInterface network = NetworkInterface.getByInetAddress(ip);

         byte[] mac = network.getHardwareAddress();

         //System.out.print("Current MAC address : ");

         StringBuilder sb = new StringBuilder();
         for (int i = 0; i < mac.length; i++)
         {
            sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
         }
         //System.out.println(sb.toString());
         return sb.toString();

      }
      catch (UnknownHostException e)
      {

         e.printStackTrace();

      }
      catch (SocketException e)
      {

         e.printStackTrace();

      }
      return null;

   }

   public String getClientIpAddr(HttpServletRequest request)
   {
      String ip = request.getHeader("X-Forwarded-For");
      if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
      {
         ip = request.getHeader("Proxy-Client-IP");
      }
      if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
      {
         ip = request.getHeader("WL-Proxy-Client-IP");
      }
      if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
      {
         ip = request.getHeader("HTTP_CLIENT_IP");
      }
      if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
      {
         ip = request.getHeader("HTTP_X_FORWARDED_FOR");
      }
      if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
      {
         ip = request.getRemoteAddr();
      }
      //System.out.println("Current IP:" + ip);
      return ip;
   }

   public void setCookie(String cookieName, String info, Map map)
   {
      FacesContext.getCurrentInstance()
         .getExternalContext()
         .addResponseCookie(cookieName, info, map);
   }


   public Map<String, Object> getCookie()
   {
      Map<String, Object> requestCookieMap = FacesContext.getCurrentInstance()
         .getExternalContext()
         .getRequestCookieMap();
      return requestCookieMap;
   }

   public Integer randomGenerator(Integer min, Integer max)
   {
      Random generator = new Random();
      try
      {
         Integer newNum = generator.nextInt(max);
         if (newNum < min)
         {
            newNum = min;
         }
         return newNum;
      }
      catch (Exception ex)
      {
         return (max);
      }
   }

   public Boolean redirecttoMessagePage(String type, String title, String body)
   {
      String spMsg = "";
      Map<String, String> args = new HashMap<String, String>();
      try
      {
         if (type != null)
         {
            args.put("type", type);
         }
         if (title != null)
         {
            args.put("title", title);
         }
         if (body != null)
         {
            args.put("message", body);
            redirect("/message.xhtml?faces-redirect=true", args);
         }
      }
      catch (Exception ex)
      {
         args.put("message", "mbse");
         args.put("type", "Error");
         args.put("title", "mtse");
         redirect("/message.xhtml?faces-redirect=true", args);
         return false;
      }
      return true;
   }

   public Boolean redirecttoMessagePage(String type, String title, String body, String errorMessagePage, String redirectPage)
   {
      String spMsg = "";
      Map<String, String> args = new HashMap<String, String>();
      try
      {
         if (type != null)
         {
            args.put("type", type);
         }
         if (title != null)
         {
            args.put("title", title);
         }
         if (body != null)
         {
            args.put("message", body);
            redirect(errorMessagePage+"?faces-redirect=true", args);
         }
      }
      catch (Exception ex)
      {
         args.put("message", "mbse");
         args.put("type", "Error");
         args.put("title", "mtse");
         redirect(errorMessagePage+"?faces-redirect=true", args);
         return false;
      }
      return true;
   }


   public String getMode()
   {
      return ("Prod");
   }

   public UserInfoData getUserInfoData()
   {

      try
      {
         Authentication auth = (Authentication) ((SecurityContext)
            SecurityContextHolder.getContext()).getAuthentication();

         if ((auth != null) && (auth.getPrincipal() instanceof UserInfoData))
         {
            return (UserInfoData) auth.getPrincipal();

         }
         else
         {
            return null;
         }

      }
      catch (Exception ex)
      {
         String url = "/message.xhtml?faces-redirect=true&type=Error&title=mtse&message=mbse";
         redirect(url, null);
         System.out.println("Warning: Context data is null, user must not be logged on.");
      }
      return null;

   }

   public void setUserInfoData(Long logonID, String userID, String  password,
                               String advisor, String rep, Collection<GrantedAuthority> authorities) {
      UserInfoData userInfo = new UserInfoData(logonID,userID, password, advisor,rep,authorities);
      FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove(WebConst.USER_INFO);
      FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(WebConst.USER_INFO, userInfo);
   }

   public String getWelcome()
   {
      String username = null;
      if (isUserLoggedIn())
      {
         UserInfoData uid = getUserInfoData();
         if (uid != null)
         {
            username = uid.getFullName();
         }
      }

      if (username == null)
      {
         return "Welcome";
      }
      else
      {
         return username + ", Welcome";
      }
   }

   public String getFullname()
   {
      String username = null;
      if (isUserLoggedIn())
      {
         UserInfoData uid = getUserInfoData();
         if (uid != null)
         {
            username = uid.getFullName();
         }
      }

      if (username == null)
      {
         return "";
      }
      else
      {
         return username;
      }
   }

   public String getLastFirstName()
   {
      String username = null;
      if (isUserLoggedIn())
      {
         UserInfoData uid = getUserInfoData();
         if (uid != null)
         {
            username = uid.getLastFirstName();
         }
      }

      if (username == null)
      {
         return "User";
      }
      else
      {
         return username;
      }
   }

   public String getFirstName()
   {
      String username = null;
      if (isUserLoggedIn())
      {
         UserInfoData uid = getUserInfoData();
         if (uid != null)
         {
            username = uid.getFirstname();
         }
      }

      if (username == null)
      {
         return "User";
      }
      else
      {
         return username;
      }
   }

   public String getAccess()
   {
      String access = "User";
      try
      {
         UserInfoData userInfoData = getUserInfoData();
         if (userInfoData != null)
         {
            access = userInfoData.getAccess();
         }
      }
      catch (Exception ex)
      {
         ex.printStackTrace();
      }
      return access;
   }

   public String hasrole()
   {
      String access = "User";
      try
      {
         UserInfoData userInfoData = getUserInfoData();
         if (userInfoData != null)
         {
            access = userInfoData.getAccess();
         }
      }
      catch (Exception ex)
      {
         ex.printStackTrace();
      }
      return access;
   }

   public Boolean isUserLoggedIn()
   {

      if (getUserInfoData() != null)
      {
         return (getUserInfoData().getLogonID() != null);
      }
      else
      {
         return false;
      }

   }

   public Boolean validatePriviledge(String access)
   {
      try
      {
         if (!isUserLoggedIn())
         {
            redirect("/login.xhtml", null);
            return false;
         }
         else if (access == null)
         {  // This means, that coder is giving full access to this function (do not check)
            return true;
         }
         else
         {
            if (hasAccess(access))
            {
               return true;
            }
            else
            {
               accessdenied();
               return false;
            }
         }
      }
      catch (Exception ex)
      {
         String url = "/message.xhtml?faces-redirect=true&type=Error&title=mtse&message=mbse";
         redirect(url, null);
         return false;
      }
   }

   public void accessdenied() {
      redirect("/access-denied.xhtml", null);
   }

   public boolean hasRole(String role)
   {

      try
      {
         Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

         if (principal instanceof UserInfoData)
         {
            Collection<GrantedAuthority> roleCollection = ((UserInfoData) principal).getAuthorities();

            if (roleCollection != null)
            {
               for (GrantedAuthority auth : roleCollection)
               {
                  if (auth.getAuthority().equalsIgnoreCase(role))
                  {
                     return true;
                  }
                  // Sales and Support are part of Admin functions.  So, Admin will have same access.
                  // However, Sales and Support will not have ADMIN functions.
                  if (auth.getAuthority().equalsIgnoreCase(WebConst.ROLE_ADMIN))
                  {
                     if (role.contains(WebConst.ROLE_SALES) || role.contains(WebConst.ROLE_SUPPORT))
                     {
                        return true;
                     }
                  }
               }
            }
            // Changed Roles and Access are two different functions....
            // if (role.equalsIgnoreCase(Const.ROLE_USER) || role.equalsIgnoreCase(Const.ROLE_OWNER))
            //   return true;
         }
      }
      catch (Exception ex)
      {
         return false;
      }
      return false;
   }

   public Boolean hasAccess(String role)
   {

      String access = getAccess();
      if (access != null)
      {
         if (role.equalsIgnoreCase(WebConst.WEB_ALL))
         {
            return true;
         }
         else if (access.equalsIgnoreCase(WebConst.WEB_ADMIN))
         {
            return true;
         }
         else if (access.equalsIgnoreCase(role))
         {
            return true;
         }
         else
         {
            return false;
         }
      }
      return false;
   }

   public boolean hasMenu(String Access, String role)
   {
      try
      {
         if (getAccess().equalsIgnoreCase(WebConst.WEB_ADMIN))
         {
            return true;
         }
         Map<String, String> webMap = (Map<String, String>) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(WebConst.WEB_MENU);
         if (webMap != null && webMap.get(Access).length() > 0)
         {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            if (principal instanceof UserInfoData)
            {
               Collection<GrantedAuthority> roleCollection = ((UserInfoData) principal).getAuthorities();

               if (roleCollection != null)
               {
                  for (GrantedAuthority auth : roleCollection)
                  {
                     if (auth.getAuthority().equalsIgnoreCase(role))
                     {
                        return true;
                     }
                     if (auth.getAuthority().equalsIgnoreCase(WebConst.ROLE_ADMIN))
                     {
                        if (role.contains(WebConst.ROLE_SALES) || role.contains(WebConst.ROLE_SUPPORT))
                        {
                           return true;
                        }
                     }
                  }
               }
            }
         }
      }
      catch (Exception ex)
      {
         return false;
      }
      return false;
   }

   public boolean hasSubMenu(String menu)
   {
      try
      {
         Map<String, String> webMap = (Map<String, String>) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(WebConst.WEB_MENU);
         if(webMap!=null && webMap.size()>0)
         {
            Iterator it = webMap.entrySet().iterator();
            while (it.hasNext())
            {
               Map.Entry pair = (Map.Entry) it.next();
               if(pair.getValue().toString().equalsIgnoreCase(menu)){
                  return true;
               }
            }
         }
      }
      catch (Exception ex)
      {
         return false;
      }
      return false;
   }

   public Long getLogonid()
   {

      try
      {
         Long logonid = null;
         if (getUserInfoData() != null)
         {
            logonid = getUserInfoData().getLogonID();
         }

         return logonid;
      }
      catch (Exception ex)
      {
         String url = "/message.xhtml?faces-redirect=true&type=Error&title=mtse&message=mbse";
         redirect(url, null);
         System.out.println("Warning: Context data is null, user must not be logged on.");
      }
      return null;
   }

   public Long getAcctnum()
   {

      try
      {
         Long acctnum = (Long) getCurrentInstance().getExternalContext().getSessionMap().get(WebConst.ACCTNO_PARAM);

         return acctnum;
      }
      catch (Exception ex)
      {
         String url = "/message.xhtml?faces-redirect=true&type=Error&title=mtse&message=mbse";
         redirect(url, null);
         System.out.println("Warning: Context data is null, user must not be logged on.");
      }
      return null;
   }

   public void setAcctnum(Long acctnum)
   {

      try
      {
         getCurrentInstance().getExternalContext().getSessionMap().put(WebConst.ACCTNO_PARAM, acctnum);

      }
      catch (Exception ex)
      {
         String url = "/message.xhtml?faces-redirect=true&type=Error&title=mtse&message=mbse";
         redirect(url, null);
         System.out.println("Warning: Context data is null, user must not be logged on.");
      }
   }


   public void redirect(String url, Map obj)
   {
      String strToPass = "";
      try
      {
         if (url == null)
         {
            return;
         }
         if (obj != null)
         {
            String key, val, delimiter;
            Iterator it = obj.entrySet().iterator();
            while (it.hasNext())
            {
               Map.Entry pairs = (Map.Entry) it.next();
               key = pairs.getKey().toString();
               val = pairs.getValue().toString();
               if (strToPass.contains("?") || url.contains("?"))
               {
                  delimiter = "&";
               }
               else
               {
                  delimiter = "?";
               }

               strToPass = strToPass + delimiter +
                  key + "=" + val;
            }
         }
         // System.out.println("Redirecting to:" + url + strToPass);
         getCurrentInstance().getExternalContext().redirect(url + strToPass);
      }
      catch (Exception ex)
      {
      }
   }

   public void showMessage(String growl, String type, String subject, String msg)
   {
      FacesMessage message;
      if (type.toUpperCase().startsWith("W"))
      {
         message = new FacesMessage(FacesMessage.SEVERITY_WARN, subject, msg);
      }
      else if (type.toUpperCase().startsWith("E"))
      {
         message = new FacesMessage(FacesMessage.SEVERITY_ERROR, subject, msg);
      }
      else
      {
         message = new FacesMessage(FacesMessage.SEVERITY_INFO, subject, msg);
      }

      FacesContext.getCurrentInstance().addMessage(growl, message);
   }

   public void showMessage(String type, String subject, String msg)
   {
      FacesMessage message;
      if (type.toUpperCase().startsWith("W"))
      {
         message = new FacesMessage(FacesMessage.SEVERITY_WARN, subject, msg);
      }
      else if (type.toUpperCase().startsWith("E"))
      {
         message = new FacesMessage(FacesMessage.SEVERITY_ERROR, subject, msg);
      }
      else
      {
         message = new FacesMessage(FacesMessage.SEVERITY_INFO, subject, msg);
      }

      RequestContext.getCurrentInstance().showMessageInDialog(message);
   }

   public void alertSupport(String module, String subject,
                            String message_line, String stacktrace)
   {
      //messageText.alertSupport(module, subject, message_line, stacktrace, getUserInfoData().getUserID());
   }

   public void seriousError(String module, String subject,
                            String message_line, String stacktrace)
   {
      String user = "User:" + getUserInfoData().getEmail() + "\n\n";
      alertSupport(module, subject,
                   message_line, user + stacktrace);

      String url = "/message.xhtml?faces-redirect=true&type=Error&title=mtse&message=mbse";
      redirect(url, null);

   }

   public String validateNewPass(String pass1, String pass2)
   {
      StringBuilder retVal = new StringBuilder();

      if (pass1 == null || pass2 == null)
      {
         return "Empty Password";
      }

      if (pass1.length() < 1 || pass2.length() < 1)
      {
         return ("Empty Password");
      }


      if (pass1.equals(pass2))
      {
         boolean hasUppercase = !pass1.equals(pass1.toLowerCase());
         boolean hasLowercase = !pass1.equals(pass1.toUpperCase());
         boolean hasNumber = pass1.matches(".*\\d.*");
         boolean noSpecialChar = pass1.matches("[a-zA-Z0-9 ]*");

         if (pass1.length() < 8)
         {
            return ("Password is too short. Must have minimum of 8 characters");
         }

         if (!hasUppercase)
         {
            return ("Password must contain at least one upper case");
         }

         if (!hasLowercase)
         {
            return ("Password must have at least one lowercase character");
         }

         if (!hasNumber)
         {
            return ("Password must contain at least one numeric value");
         }

         if (noSpecialChar)
         {
            return ("Password must contain at lease one special character i.e. !,@,#, etc.");
         }
      }
      else
      {
         return ("Two passwords don't match");
      }

      return ("Success");

   }

   private Integer progressbar;

   public Integer getProgressbar()
   {
      if (progressbar == null)
      {
         progressbar = 0;
      }
      else
      {
         if (progressbar > 100)
         {
            progressbar = 100;
         }
      }

      return progressbar;
   }

   public void setProgressbar(Integer progressbar)
   {
      this.progressbar = progressbar;
   }

   public void progessreset()
   {
      progressbar = null;
   }

   public Double MathAbs(Double num)
   {
      if (num != null)
      {
         return Math.abs(num);
      }
      return num;
   }

   public void sendConfirmation(UserData userdata, String whichConfirmation) {
      if (userdata.getEmail() == null) {
         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Session timed out.  Use Forgot password to get access to your account.", "System timed out"));
      }

      if (userdata.getLogonstatus() != null && userdata.getLogonstatus().startsWith("A")) {
         return;
      }
      webutillooger.debug("Debug: Sending confirmation email: " + userdata.getEmail());

      MsgData data = new MsgData();
      data.setSource("User");  // This is set to User to it insert into appropriate table.
      data.setSender(getWebprofile().getEmailUser());
      data.setReceiver(userdata.getEmail());
      String secureUrl = getWebprofile().getSecurehomepage();
      String emailMsgType;
      String htmlfile = null, htmltempate = null, textInfo = null;
      String whichLink = null;
      String subject = "No subject";

      Integer whichEmail = 0;
      // Values: W = Welcome, R=Reset, L=Locked, A=Activate, I=Inactive, F=Forgot
      if (whichConfirmation == null) {
         return; // No email
      }
      else {
         if (whichConfirmation.startsWith("W"))
            whichEmail = 0;
         else if (whichConfirmation.startsWith("R"))
            whichEmail = 1;
         else if (whichConfirmation.startsWith("L"))
            whichEmail = 2;
         else  if (whichConfirmation.startsWith("A"))
            whichEmail = 3;
         else  if (whichConfirmation.startsWith("F"))
            whichEmail = 4;
         else  whichEmail = 99;
      }

      switch (whichEmail) {
         case 0:
            whichLink=secureUrl + "/signup.xhtml?l="+userdata.getLogonID().toString()+"&r="+userdata.getResetID();
            if (userdata.getAccess() != null && userdata.getAccess().equalsIgnoreCase("Advisor")) {
               subject = getWebprofile().getEmailSubject(WebConst.EMAIL_WELCOME_ADV_SUBJECT);
               htmltempate = getWebprofile().getEmailSubject(WebConst.HTML_BASE_PATH) + getWebprofile().getEmailTemplate(WebConst.HTML_WELCOME_ADV);
            }
            else {
               subject = getWebprofile().getEmailSubject(WebConst.EMAIL_WELCOME_SUBJECT);
               htmltempate = getWebprofile().getEmailSubject(WebConst.HTML_BASE_PATH) + getWebprofile().getEmailTemplate(WebConst.HTML_WELCOME);
            }
            break;
         case 1:
            subject = getWebprofile().getEmailSubject(WebConst.EMAIL_RESET_SUBJECT);
            htmltempate = getWebprofile().getEmailSubject(WebConst.HTML_BASE_PATH) + getWebprofile().getEmailTemplate(WebConst.HTML_RESET);
            whichLink=secureUrl + "/setPassword.xhtml?l="+userdata.getLogonID().toString()+"&r="+userdata.getResetID();
            break;
         case 2:
            subject = getWebprofile().getEmailSubject(WebConst.EMAIL_LOCKED_SUBJECT);
            htmltempate = getWebprofile().getEmailSubject(WebConst.HTML_BASE_PATH) + getWebprofile().getEmailTemplate(WebConst.HTML_LOCKED);
            whichLink=secureUrl + "/setPassword.xhtml?l="+userdata.getLogonID().toString()+"&r="+userdata.getResetID();
            break;
         case 3:
            subject = getWebprofile().getEmailSubject(WebConst.EMAIL_ACTIVATE_SUBJECT);
            htmltempate = getWebprofile().getEmailSubject(WebConst.HTML_BASE_PATH) + getWebprofile().getEmailTemplate(WebConst.HTML_ACTIVATED);
            whichLink=secureUrl + "/activate.xhtml?l="+userdata.getLogonID().toString()+"&r="+userdata.getResetID();
            break;
         case 4:
            subject = getWebprofile().getEmailSubject(WebConst.EMAIL_FORGOT_SUBJECT);
            htmltempate = getWebprofile().getEmailSubject(WebConst.HTML_BASE_PATH) + getWebprofile().getEmailTemplate(WebConst.HTML_FORGOT);
            whichLink=secureUrl + "/setPassword.xhtml?l="+userdata.getLogonID().toString()+"&r="+userdata.getResetID();
            break;
         default:
            return;
      }

      emailMsgType = "HTML";
      htmlfile = htmltempate;
      // htmlfile = "/template/html/" + htmltempate;

      if (subject == null) {
         subject = getWebprofile().getCompanyname() + "Email";
      }

      data.setSubject(subject);
      Map <String, String> object = new HashMap<String, String>();

      object.put("CUSTOM_LINK", whichLink);
      object.put("LOGON_ID", userdata.getLogonID().toString());
      object.put("USER_ID", userdata.getUserID());
      object.put("USER_EMAIL", userdata.getEmail());
      object.put("RESET_ID", userdata.getResetID());
      object.put("FIRST_NAME", userdata.getFirstName());
      object.put("LAST_NWME", userdata.getLastName());
      object.put("SUPPORT_EMAIL", getWebprofile().getSupportemail());
      object.put("SUPPORT_PHONE", getWebprofile().getSupportphone());
      object.put("COMPANY_NAME", getWebprofile().getCompanyname());
      object.put("LOGO", getWebprofile().getLogo());

      String msg = generateEmailMessage(htmlfile,object);

      if (msg != null && ! msg.isEmpty())
      {
         data.setMsg(msg);
         getMessageText().writeMessage("custom", data);
         webutillooger.debug("Debug: Sending Activation email to: " + userdata.getEmail());
      }
      else
      {
         webutillooger.debug("Debug: COULD NOT Sending email to: " + userdata.getEmail() + " as message text is blank");
      }

   }


   public void resetSession() {
      HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
      HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
      SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
      CookieClearingLogoutHandler cookieClearingLogoutHandler = new CookieClearingLogoutHandler(AbstractRememberMeServices.SPRING_SECURITY_REMEMBER_ME_COOKIE_KEY);
      cookieClearingLogoutHandler.logout(request, response, null);
      securityContextLogoutHandler.logout(request, response, null);
   }

   public String generateEmailMessage(String filename,Map <String, String>object)
   {
      String message = "";
      FileReader fr = null;
      BufferedReader br = null;
      try
      {
         fr=new FileReader(filename);

         if (fr == null)
         {
            return message;
         }

         br= new BufferedReader(fr);
         StringBuilder content=new StringBuilder(1024);
         String line = "";
         while((line=br.readLine())!=null)
         {
            content.append(line);
         }
         message = content.toString();

      } catch (IOException e) {
         e.printStackTrace();
      } finally {
         try {
            if (br != null)br.close();
         } catch (IOException ex) {
            ex.printStackTrace();
         }
      }

      try {
         if (object != null)
         {
            for (String key : object.keySet())
            {
               String replaceValue = key;
               String strReplace = "~" + replaceValue + "~";
               String strReplaceWith = object.get(key);
               message = message.replaceAll(strReplace, strReplaceWith);
            }
         }

      }
      catch (Exception ex) {
         ex.printStackTrace();
      }
      return message;
   }

   private StreamedContent downloadFile(String filenamename, String fileformat, String outputName) {
      StreamedContent file = null;
      try {
         InputStream stream = new FileInputStream(new File(filenamename))
         {
            @Override
            public int read() throws IOException
            {
               return 0;
            }
         };
         stream.close();
         if (fileformat != null) {
            if (fileformat.equalsIgnoreCase("csv")) {
               file = new DefaultStreamedContent(stream, "application/csv", outputName);
            }else {
               if (fileformat.equalsIgnoreCase("text")) {
                  file = new DefaultStreamedContent(stream, "application/text", outputName);
               }
               else
               {
                  if (fileformat.equalsIgnoreCase("pdf")) {
                     file = new DefaultStreamedContent(stream, "application/pdf", outputName);
                  }
               }
            }
         }
      }
      catch (Exception ex) {

      }
      return file;
   }
   public boolean isAggregatorMenuActive()
   {

      try
      {
         System.out.print("Print Menu value" + getWebprofile().getWebInfo().get("MENU.AGGREGATOR").toString());
         if (getWebprofile().getWebInfo().get("MENU.AGGREGATOR") != null && getWebprofile().getWebInfo().get("MENU.AGGREGATOR").toString().equalsIgnoreCase("A"))
         {
            return true;
         }
      }
      catch (Exception ex)
      {
         return false;
      }
      return false;
   }
}
