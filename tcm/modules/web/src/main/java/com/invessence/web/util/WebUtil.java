package com.invessence.web.util;

import java.io.Serializable;
import java.net.*;
import java.util.*;
import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequest;

import com.invessence.converter.SQLData;
import com.invessence.web.constant.WebConst;
import com.invessence.web.data.common.*;
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.core.*;
import org.springframework.security.core.context.*;
import org.springframework.stereotype.Component;

import static javax.faces.context.FacesContext.getCurrentInstance;

@ManagedBean(name = "webutil")
@SessionScoped
public class WebUtil implements Serializable
{

   public UIProfile uiprofile = new UIProfile();
   public SQLData converter = new SQLData();

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

   public UIProfile getUiprofile()
   {
      return uiprofile;
   }

   public boolean isWebProdMode()
   {
      if (uiprofile == null)
      {
         return false;
      }

      String mode = uiprofile.getWebmode();
      if (mode == null)
      {
         return false;
      }
      else if (mode.toUpperCase().equals("PROD") || mode.toUpperCase().equals("UAT"))
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

   public Boolean isUserLoggedIn()
   {

      if (getUserInfoData() != null)
      {
         return true;
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
         {
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
               redirect("/access-denied.xhtml", null);
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

   public static String validateNewPass(String pass1, String pass2)
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


}
