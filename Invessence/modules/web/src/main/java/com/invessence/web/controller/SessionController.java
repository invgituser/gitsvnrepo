package com.invessence.web.controller;

import java.io.*;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.context.*;
import javax.faces.event.*;
import javax.servlet.*;

import com.invessence.web.constant.WebConst;
import com.invessence.web.dao.common.CommonDAO;
import com.invessence.web.data.common.UserInfoData;
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

   public String getLogonStart() {

      if (webutil != null) {
         if (webutil.isUserLoggedIn()) {
            if (webutil.getUserInfoData() != null) {
               // On logon, if the Advisor and rep is defined to the user, then use that instead.
               resetUserCIDByAdvisor(webutil.getUserInfoData().getAdvisor());  // Since user is loging on, use the User's Advisor's Setup
               if (webutil.getUserInfoData().getAdvisor() != null ) {
                  webutil.getWebprofile().setDefaultAdvisor(webutil.getUserInfoData().getAdvisor());
                  webutil.getWebprofile().setDefaultRep(webutil.getUserInfoData().getRep());
               }
               if (webutil.getUserInfoData().getAtstart() != null) {
                  uiLayout.whichPage(webutil.getUserInfoData().getAccess(), webutil.getUserInfoData().getAtstart());
                  return "success";
               }
            }
            else {
               goToDash();
               return "success";
            }
         }
      }
      tryOut();
      return "success";
   }


   public String getAtStart()
   {
      resetCIDByURL(null);
      if (webutil != null) {
         if (webutil.isUserLoggedIn()) {
            if (webutil.getUserInfoData() != null) {
               if (webutil.getUserInfoData().getAtstart() != null) {
                  uiLayout.whichPage(webutil.getUserInfoData().getAccess(), webutil.getUserInfoData().getAtstart());
                  return "success";
               }
            }
            else {
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
      uiLayout.doMenuAction("consumer", "cadd.xhtml?app=N");
      // return "success";
   }

   public String getDemoStart()
   {
      uiLayout.doMenuAction("consumer", "cadd.xhtml?app=N");
      return "success";
   }

   public void emulateClient(String clienturl) {
      if (clienturl != null)
      {
         webutil.webprofile.setLocked(false);
         loadWebProfile(clienturl);
         loadAdvisorProfile(webutil.getWebprofile().getDefaultAdvisor());
         webutil.webprofile.setLocked(true);
      }
   }

   private void loadWebProfile(String url) {
      webutil.getWebprofile().initWebProfile();
      webutil.getWebprofile().setUrl(url);
      if (commonDAO != null)
      {
         webutil.getWebprofile().setWebInfo(commonDAO.getWebSiteInfo(url));
      }
   }

   private void loadAdvisorProfile(String advisor) {
      if (commonDAO != null)
      {
         webutil.getWebprofile().addToMap(commonDAO.getAdvisorWebInfo(advisor));
      }
   }

   private void resetUserCIDByAdvisor(String advisor)
   {
      if (webutil == null)
         return;

      Map<String, String> advisorMap;
      if (advisor == null)
         advisor = "Invessence";

      if (! webutil.getWebprofile().getLocked()) {
         if (! webutil.getWebprofile().getDefaultAdvisor().equalsIgnoreCase(advisor)) {
            if (commonDAO != null)
            {
               advisorMap = commonDAO.getAdvisorWebInfo(advisor);
               if (advisorMap != null) {
                  if (advisorMap.containsKey("WEB.URL")) {
                     loadWebProfile(advisorMap.get("WEB.URL"));
                     webutil.getWebprofile().addToMap(advisorMap);
                  }
               }
            }
         }
      }
   }

   // This process will load data based on URL (assuming the env is not locked)
   private void resetCIDByURL(String uri)
   {
      if (webutil == null)
         return;

      String origurl = webutil.getWebprofile().getUrl();
      if (uri == null)
         uri = webutil.getURLAddress("Invessence");

      if (! webutil.getWebprofile().getLocked()) {
         if (origurl == null || (! origurl.equalsIgnoreCase(uri)))
         {
            loadWebProfile(uri);
            loadAdvisorProfile(webutil.getWebprofile().getDefaultAdvisor());
         }
      }
   }

}
