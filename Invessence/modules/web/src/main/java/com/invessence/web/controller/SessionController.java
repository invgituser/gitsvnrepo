package com.invessence.web.controller;

import java.io.*;
import java.util.*;
import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.context.*;
import javax.faces.event.*;
import javax.servlet.*;

import com.invessence.converter.SQLData;
import com.invessence.web.constant.WebConst;
import com.invessence.web.dao.*;
import com.invessence.web.dao.common.CommonDAO;
import com.invessence.web.data.common.*;
import com.invessence.web.util.*;
import org.apache.commons.logging.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.web.WebAttributes;

import static javax.faces.context.FacesContext.getCurrentInstance;

@ManagedBean(name = "sessionControl")
@SessionScoped
public class SessionController implements Serializable
{

   protected final Log logger = LogFactory.getLog(getClass());
   private Long logonid;
   private String rep;
   private String visitorID;
   private String device;
   private int cstmSessionTimeout;
   private boolean allowVisitorReg;
   private List<UserRepData> lstUserRepDetails;

   private String site, mode;

   @ManagedProperty("#{webutil}")
   private WebUtil webutil;

   public void setWebutil(WebUtil webutil)
   {
      this.webutil = webutil;
   }

   @ManagedProperty("#{uiLayout}")
   private UILayout uiLayout;

   public void setUiLayout(UILayout uiLayout)
   {
      this.uiLayout = uiLayout;
   }

   @ManagedProperty("#{commonDAO}")
   private CommonDAO commonDAO;

   @ManagedProperty("#{auditDAO}")
   private AuditDAO auditDAO;

   public AuditDAO getAuditDAO()
   {
      return auditDAO;
   }

   public void setAuditDAO(AuditDAO auditDAO)
   {
      this.auditDAO = auditDAO;
   }

   public void setCommonDAO(CommonDAO commonDAO)
   {
      this.commonDAO = commonDAO;
   }

   public Long getLogonid()
   {
      return logonid;
   }

   public void setLogonid(Long logonid)
   {
      this.logonid = logonid;
   }

   public String getSite()
   {
      return site;
   }

   public void setSite(String site)
   {
      this.site = site;
   }

   public String getMode()
   {
      return mode;
   }

   public void setMode(String mode)
   {
      this.mode = mode;
   }

   public String getRep()
   {
      return (rep == null) ? "" : rep;
   }

   public void setRep(String rep)
   {
      this.rep = rep;
   }

   public String getVisitorID()
   {
      return (visitorID == null) ? "" : visitorID;
   }

   public void setVisitorID(String visitorID)
   {
      this.visitorID = visitorID;
   }

   public void preRenderView()
   {
      if (!FacesContext.getCurrentInstance().isPostback())
      {
         logger.info("Module: preRenderView() called");
         resetCIDByURL(null);  // This method, will find the URL if not defined.
      }
   }

   public void logout()
   {
      UserInfoData userInfo=(UserInfoData)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(WebConst.USER_INFO);
      if(userInfo!=null && userInfo.getLogonAuditID() != null)
      {
         auditDAO.loginAuditEntry(new LoginAudit(userInfo.getLogonAuditID(), userInfo.getLogonID(), userInfo.getUsername(), null, null, null, null, "Proper Logout"));
         FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove(WebConst.USER_INFO);
      }

      String mobileUserFlag = null;
      String homeurl = webutil.getWebprofile().getHomepage();
      if(webutil.getWebprofile().getInvSiteReq()==true){
         if(webutil.getWebprofile().getInvSiteUrl()!=null){
            homeurl= "http://"+webutil.getWebprofile().getInvSiteUrl()+"/invsite.xhtml?site="+webutil.getWebprofile().getUrl()
               +"&rep="+webutil.getWebprofile().getInvSiteRep()+"&mode="+webutil.getWebprofile().getMode();
         }
      }


      mobileUserFlag=getDevice();

      if (mobileUserFlag!=null && mobileUserFlag.equalsIgnoreCase("mobile"))
      {
         homeurl = webutil.getWebprofile().getMobileHomepage();
         System.out.println("Redirecting mobileUserFlag [" + mobileUserFlag +"]\n");
      }
      System.out.println("Redirecting homeurl " + homeurl );
      try
      {
         uiLayout.logout();
         FacesContext.getCurrentInstance().getExternalContext().redirect(homeurl);
      }
      catch (Exception ex)
      {
      }
//      reset();
//      if (homeurl != null && !homeurl.isEmpty())
//      {
//         webutil.redirect(homeurl, null);
////         return;
//      }
//      webutil.redirect("/j_spring_security_logout", null);
   }

   public String getLogonStart()
   {

      logger.info("Module: getLogonStart() called");
      if (webutil != null)
      {
         if (webutil.isUserLoggedIn())
         {
            if (webutil.getUserInfoData() != null)
            {

               FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(WebConst.USERLOGON_ATTEMPTS, 0);
               // On logon, if the Advisor and rep is defined to the user, then use that instead.  Removed this check on May 17th, 2017
               // resetUserCIDByAdvisor(webutil.getUserInfoData().getAdvisor());  // Since user is loging on, use the User's Advisor's Setup
               resetCIDByURL(null);  // Use the URL Mapping (May 17th, 2017)
               loadWebMenu(webutil.getWebprofile().getUrl());
               if (webutil.getUserInfoData().getAdvisor() != null)
               {
                  webutil.getWebprofile().setDefaultAdvisor(webutil.getUserInfoData().getAdvisor());
                  webutil.getWebprofile().setDefaultRep(webutil.getUserInfoData().getRep());
               }
               if (webutil.getUserInfoData().getAtstart() != null)
               {
                  uiLayout.whichPage(webutil.getUserInfoData().getAccess(), webutil.getUserInfoData().getAtstart());
                  return "success";
               }
            }
            else
            {
               goToDash();
               return "success";
            }
         }
      }
      tryOut();
      return "success";
   }

   public String getInvSiteStart(){

//      System.out.println("SessionController.getInvSiteStart");
//      System.out.println("site = " + site+" rep = " + rep+" mode = " + mode);

      resetWebSiteProfile(site==null||site.trim().equals("")?"Invessence":site);

//    uiLayout.whichPage(webutil.getUserInfoData().getAccess(), webutil.getUserInfoData().getAtstart());
      uiLayout.forwardURL("/index.xhtml");
      return "success";
   }
   public String getAtStart()
   {
      resetCIDByURL(null);
      if (webutil != null)
      {
         if (webutil.isUserLoggedIn())
         {
            if (webutil.getUserInfoData() != null)
            {
               if (webutil.getUserInfoData().getAtstart() != null)
               {
                  uiLayout.whichPage(webutil.getUserInfoData().getAccess(), webutil.getUserInfoData().getAtstart());
                  return "success";
               }
            }
            else
            {
               goToDash();
               return "success";
            }
         }
      }
      tryOut();
      return "success";
   }

   public void goToDash()
   {
      uiLayout.doMenuAction("consumer", "index.xhtml");
      // return "success";
   }

   public void tryOut()
   {
      resetCIDByURL(null);
      String arg = "?app=N";
      if (rep != null && !rep.isEmpty())
      {
         webutil.getWebprofile().setDefaultRep(rep);
         arg += "&rep=" + rep;
      }

      if (visitorID != null && !visitorID.isEmpty())
      {
         Long visitorlogonid = webutil.converter.getLongData(visitorID);
         if (logonid != null)
         {
            webutil.getUserInfoData().setLogonID(visitorlogonid);
         }
         arg += "&vid=" + visitorID;

      }

      uiLayout.doMenuAction("consumer", "cadd.xhtml" + arg);
      // return "success";
   }

   public void reset()
   {
//      webutil.getWebprofile().setLocked(false);
      // NOTE; We don't want to load the advisor, because it resets to actual base site.
      loadWebProfile(WebUtil.getURLAddress("Invessence"));
   }

   public void indexGetStarted()
   {
      reset();
      getAtStart();
   }

   public void indexLogin()
   {
      reset();
      uiLayout.forwardURL("/login.xhtml");
   }

   public void emulateClient(String clienturl)
   {
      logger.info("Module: emulateClient ->" + clienturl);
      if (clienturl != null)
      {
         webutil.getWebprofile().setLocked(false);
         loadWebProfile(clienturl);
         loadAdvisorProfile(webutil.getWebprofile().getDefaultAdvisor());
         webutil.getWebprofile().finalConfig();
         webutil.getWebprofile().setLocked(true);
         logger.info("Status: LOCK for this client: " + clienturl);
      }
   }

   private void loadWebProfile(String url)
   {
      if (commonDAO != null)
      {
         // logger.info("Load WEB property for:" + url);
         if(webutil.getWebprofile()!=null)
         {
            System.out.println("Load WEB property for:" + url);
            WebProfile webProfile = webutil.getWebprofile();
            webProfile.initWebProfile();
            webProfile.setUrl(url);
            webProfile.setWebInfo(commonDAO.getWebSiteInfo(url));
            webProfile.finalConfig();
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove(WebConst.WEB_INFO);
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(WebConst.WEB_INFO, webProfile);
         }
      }
   }

   private void loadWebMenu(String url)
   {
      // logger.info("Load WEB property for:" + url);
      if(webutil.isUserLoggedIn())
      {
         Map<String, String> webMap=commonDAO.getWebMenuDetails(url, webutil.getAccess());
         lstUserRepDetails=commonDAO.getUserRepDetails(webutil.getLogonid());
         FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove(WebConst.WEB_MENU);
         FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(WebConst.WEB_MENU, webMap);
      }
   }


   private void loadAdvisorProfile(String advisor)
   {
      if (commonDAO != null)
      {
         logger.info("Load WEB Advisor for:" + advisor);
         webutil.getWebprofile().addToMap(commonDAO.getAdvisorWebInfo(advisor));
      }
   }

   private void resetUserCIDByAdvisor(String advisor)
   {
      logger.info("Module: resetCIDByURL called");
      if (webutil == null)
      {
         return;
      }

      // Not it is not prod mode then don't override the properties based on advisor.
      if (webutil.getWebprofile() != null)
      {
         if (!webutil.getWebprofile().getMode().equalsIgnoreCase("prod"))
         {
            return;
         }
      }

      Map<String, String> advisorMap;
      if (advisor == null)
      {
         advisor = "Invessence";
      }

      if (webutil.getWebprofile().getLocked())
      {
         logger.info("Status: Advisor cannot revised the locked mode");
      }

      if (!webutil.getWebprofile().getLocked())
      {
         if (commonDAO != null)
         {
            advisorMap = commonDAO.getAdvisorWebInfo(advisor);
            if (advisorMap != null)
            {
               if (advisorMap.containsKey("WEB.URL"))
               {
                  logger.info("Override the URL for: " + advisorMap.get("WEB.URL"));
                  loadWebProfile(advisorMap.get("WEB.URL"));
                  logger.info("Override the Advisor Property: " + advisor);
                  webutil.getWebprofile().addToMap(advisorMap);
                  webutil.getWebprofile().finalConfig();
               }
            }
         }
      }
   }

   // This process will load data based on URL (assuming the env is not locked)
   private void resetWebSiteProfile(String uri)
   {
//      System.out.println("SessionController.resetWebSiteProfile");
      if (webutil == null)
      {
         return;
      }
      if(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(WebConst.WEB_INFO)==null){
         WebProfile webProfile=new WebProfile();
         FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove(WebConst.WEB_INFO);
         FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(WebConst.WEB_INFO, webProfile);
      }


      String origurl = webutil.getWebprofile().getUrl();

//      if (uri == null)
//      {
//         uri = WebUtil.getURLAddress("Invessence");
//      }
      if(origurl==null || !origurl.equals(uri)
         || !webutil.getWebprofile().getInvSiteRep().equals(rep)
         || !webutil.getWebprofile().getMode().equals(mode)){
         System.out.println("New URL:" + uri + " is different with Original URL : " + origurl);
         webutil.getWebprofile().setLocked(false);
      }

      if (webutil.getWebprofile().getLocked())
      {
         logger.info("Status: Attempting to reset: " + uri + " But the profile is locked in: " + origurl);
      }
      // We we are doing demo, then it will be locked. Don't reset (regarless of URL)

      else //if (!webutil.getWebprofile().getLocked())
      {
         // Was Webprofile parsed in past.  If se, then we set the origurl.

         // Now get the new profile.

         logger.info("Compare: ORIG URL: " + origurl + " and New One: " + uri);
         // Now try to determine, we we need to reset, based on URL (either by logged user, or change of URL)
         Boolean reload = true;
//         if (origurl == null)
//         {  // If this is first time, go reload as normal.
//            reload = true;
//         }
//         else
//         {  // If doing it again, then is the new URL NOT localhost or is different from current origurl
//            if (!uri.equalsIgnoreCase("localhost") && !origurl.equalsIgnoreCase(uri))
//            {
//               reload = true;
//            }
//         }

         logger.info("Status: " + ((reload) ? "reload" + uri : "Skip reload"));
         if (reload)
         {
            // System.out.println("Load WEB property for: " + uri);
            System.out.println("Reloading Profile origurl = " + origurl +" uri = [" + uri + "]");
            loadWebProfile(uri);
            loadAdvisorProfile(webutil.getWebprofile().getDefaultAdvisor());
            webutil.getWebprofile().finalConfig();
            webutil.getWebprofile().setLocked(true);
            webutil.getWebprofile().setInvSiteReq(true);
            webutil.getWebprofile().setInvSiteUrl(WebUtil.getURLAddress("Invessence"));
            webutil.getWebprofile().setMode(mode==null||mode.equals("")?"UAT":mode);
            webutil.getWebprofile().setInvSiteRep(rep==null?"":rep);

//            webutil.getWebprofile().setLocked(false);
//            loadWebProfile(clienturl);
//            loadAdvisorProfile(webutil.getWebprofile().getDefaultAdvisor());
//            webutil.getWebprofile().finalConfig();
//            webutil.getWebprofile().setLocked(true);
         }
      }
   }

   // This process will load data based on URL (assuming the env is not locked)
   private void resetCIDByURL(String uri)
   {
//      System.out.println("SessionController.resetCIDByURL");
//      System.out.println("uri = [" + uri + "]");
//      System.out.println("site = " + site+" rep = " + rep+" mode = " + mode);
      if (webutil == null)
      {
         return;
      }
//      System.out.println(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(WebConst.WEB_INFO));
      if(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(WebConst.WEB_INFO)==null){
         WebProfile webProfile=new WebProfile();
         FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove(WebConst.WEB_INFO);
         FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(WebConst.WEB_INFO, webProfile);
      }


      String origurl = webutil.getWebprofile().getUrl();
      if (uri == null)
      {
         uri = WebUtil.getURLAddress("Invessence");
      }
//      System.out.println("Is Locked :"+webutil.getWebprofile().getLocked());
      if (webutil.getWebprofile().getLocked())
      {
         logger.info("Status: Attempting to reset: " + uri + " But the profile is locked in: " + origurl);
      }
      // We we are doing demo, then it will be locked. Don't reset (regarless of URL)

      else //if (!webutil.getWebprofile().getLocked())
      {
         // Was Webprofile parsed in past.  If se, then we set the origurl.

         // Now get the new profile.

         logger.info("Compare: ORIG URL: " + origurl + " and New One: " + uri);
         // Now try to determine, we we need to reset, based on URL (either by logged user, or change of URL)
         Boolean reload = false;
         if (origurl == null)
         {  // If this is first time, go reload as normal.
            reload = true;
         }
         else
         {  // If doing it again, then is the new URL NOT localhost or is different from current origurl
            if (!uri.equalsIgnoreCase("localhost") && !origurl.equalsIgnoreCase(uri))
            {
               reload = true;
            }
         }

         logger.info("Status: " + ((reload) ? "reload" + uri : "Skip reload"));
         if (reload)
         {
            // System.out.println("Load WEB property for: " + uri);
            loadWebProfile(uri);
            loadAdvisorProfile(webutil.getWebprofile().getDefaultAdvisor());
//            webutil.getWebprofile().finalConfig();
         }
      }
   }

   public void onIdleSessionLogout()
   {
      // System.out.println("onIdleSessionLogout Session Logout Start");
      logout();
      // System.out.println("onIdleSessionLogout Session Logout End");
   }

   public void onActive()
   {
      System.out.println("onActive User is Active");
   }

   public int getCustomSessionTimeout()
   {
      cstmSessionTimeout = 10 * 60 * 1000;
      try
      {
         if(webutil==null || webutil.getWebprofile()==null || webutil.getWebprofile().getSessionTimeout()==null){
            return cstmSessionTimeout;
         }else {
            cstmSessionTimeout = webutil.getWebprofile().getSessionTimeout();
            cstmSessionTimeout = cstmSessionTimeout * 60 * 1000;
         // System.out.println("SessionController.getCustomSessionTimeout session timeout " + cstmSessionTimeout);
         }
      }
      catch (Exception e)
      {
         cstmSessionTimeout = 10*60*1000;
         logger.error("SessionController.getCustomSessionTimeout Error " + e);
         e.printStackTrace();
      }
      return cstmSessionTimeout;
   }

   public int getSessionCountdownTime()
   {
      int sessionCountdownTime;
      try
      {
         // If the properties are not loaded, then no timeout.
         sessionCountdownTime = (webutil.getWebprofile() == null) ? -1: webutil.getWebprofile().getSessionCountdownTimeout();

         sessionCountdownTime = sessionCountdownTime * 60 ;
         // System.out.println("SessionController.getSessionCountdownTime session countdown time" + sessionCountdownTime);
      }
      catch (Exception e)
      {
         sessionCountdownTime = 60;
         logger.error("SessionController.getSessionCountdownTime Error " + e);
         e.printStackTrace();
      }
      return sessionCountdownTime;
   }



   public String getDevice()
   {
      return device;
   }

   public void setDevice(String device)
   {
      this.device = device;
   }

   public void mobileRenderView()
   {
      if (!FacesContext.getCurrentInstance().isPostback())
      {
         logger.info("Module: preRenderView() called");
         resetCIDByURL(null);  // This method, will find the URL if not defined.
         webutil.getWebprofile().setDevice("Mobile");
         setDevice("mobile");
      }
   }

   public int getCstmSessionTimeout()
   {
      return cstmSessionTimeout;
   }

   public void setCstmSessionTimeout(int cstmSessionTimeout)
   {
      this.cstmSessionTimeout = cstmSessionTimeout;
   }

   public boolean isAllowVisitorReg()
   {
      allowVisitorReg = false;
      if (webutil.getWebprofile().getWebInfo().get("ALLOW_VISITOR_REGISTER") != null)
      {
         String allowVisitorRegFlag = webutil.getWebprofile().getWebInfo().get("ALLOW_VISITOR_REGISTER").toString();
         if (allowVisitorRegFlag.equalsIgnoreCase("Y"))
         {
            allowVisitorReg = true;
         }
      }
//      System.out.println("allowVisitorReg ~~>" + allowVisitorReg);
      return allowVisitorReg;
   }

   public void setAllowVisitorReg(boolean allowVisitorReg)
   {
      this.allowVisitorReg = allowVisitorReg;
   }

   public List<UserRepData> getLstUserRepDetails()
   {
      return lstUserRepDetails;
   }

   public void setLstUserRepDetails(List<UserRepData> lstUserRepDetails)
   {
      this.lstUserRepDetails = lstUserRepDetails;
   }
}
