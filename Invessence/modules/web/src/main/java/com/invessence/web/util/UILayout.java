package com.invessence.web.util;

import java.io.Serializable;
import java.util.*;
import javax.annotation.PostConstruct;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;

import com.invessence.service.bean.WebConfigDetails;
import com.invessence.service.util.ServiceParameters;
import com.invessence.web.bean.custody.TdCto;
import com.invessence.web.constant.WebConst;
import com.invessence.web.data.common.UIProfile;
import org.primefaces.component.tabview.TabView;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 10/20/14
 * Time: 10:03 AM
 * To change this template use File | Settings | File Templates.
 */

@ManagedBean(name = "uiLayout")
@SessionScoped
public class UILayout implements Serializable
{
   private static final long serialVersionUID = -1992L;

   private String cid;
   private String rep;
   private Integer tabMenu = 0;
   private TabView menuTab = new TabView();
   private String defaultHome;

   @Autowired
   private TdCto tdcto;

   @Autowired
   private WebUtil webutil;

   public WebUtil getWebutil()
   {
      return webutil;
   }

   public UIProfile getUiprofile()
   {
      return webutil.getUiprofile();
   }

   private String webserviceDB(Map<String, WebConfigDetails> webDB, String name)
   {
      String value = null;
      if (webDB != null)
      {
         if (webDB.containsKey(name))
         {
            value = webDB.get(name).getValue();
         }
      }
      return value;
   }

   private String getWebServiceValue(String key)
   {
      String value = null;
      Map<String, WebConfigDetails> webSiteConfigDetails;
      String cid = getCid();

      if (cid == null || cid.isEmpty())
         return null;

      if (ServiceParameters.webSiteConfigDetailsMap != null)
      {
         // First get the Proper CID records.
         webSiteConfigDetails = ServiceParameters.webSiteConfigDetailsMap.get(cid);

         if (webSiteConfigDetails != null)
         {
            value = webserviceDB(webSiteConfigDetails, key);
            if (value != null)
            {
               return value;
            }
         }

         // If above logic did not find it, and it was zero, then return null
         if (getCid().equalsIgnoreCase("0"))
            return null;

         // If no data was found, and CID <> 0, then default the data at Master level.
         webSiteConfigDetails = ServiceParameters.webSiteConfigDetailsMap.get("0");

         if (webSiteConfigDetails != null)
         {
            value = webserviceDB(webSiteConfigDetails, key);
            if (value != null)
            {
               return value;
            }
         }

      }
      return null;
   }

   public void initialize()
   {
      if (webutil.getUiprofile() != null)
      {
         if (ServiceParameters.webSiteConfigDetailsMap != null)
         {
            // First get the Master records.
            Map<String, WebConfigDetails> webSiteConfigDetails = ServiceParameters.webSiteConfigDetailsMap.get("0");

            if (webSiteConfigDetails != null)
            {
               webutil.getUiprofile().resetAllInfo(getCid(),
                                                   getWebServiceValue("COMPANYNAME"),
                                                   getWebServiceValue("WEBSITE.URL"),
                                                   getWebServiceValue("SECURE.URL"),
                                                   getWebServiceValue("LOGO"),
                                                   getWebServiceValue("LOGO.SIZE"),
                                                   getWebServiceValue("LOGO.LIB"),
                                                   getWebServiceValue("MAIN.EMAIL"),
                                                   getWebServiceValue("SUPPORT.EMAIL"),
                                                   getWebServiceValue("MAIN.PHONE"),
                                                   getWebServiceValue("SUPPORT.PHONE"),
                                                   getWebServiceValue("COPYRIGHT"),
                                                   getWebServiceValue("FORWARD.SERVICE"),
                                                   getWebServiceValue("CUSTODY.URL"),
                                                   getWebServiceValue("CUSTODY.PROCESS"),
                                                   getWebServiceValue("THEME"),
                                                   getWebServiceValue("THEME.LIB"),
                                                   getWebServiceValue("TEMPLATE.DIR"),
                                                   getWebServiceValue("CONSUMER.DIR"),
                                                   getWebServiceValue("CSS.DIR"),
                                                   getWebServiceValue("CUSTOM.CSS"),
                                                   getWebServiceValue("WEB.MODE"),
                                                   getWebServiceValue("EMAIL.USER"));
               Map<String, String> emailMap = new HashMap<String,String>();
               emailMap.put(WebConst.HTML_WELCOME,getWebServiceValue(WebConst.HTML_WELCOME));
               emailMap.put(WebConst.HTML_WELCOME_ADV,getWebServiceValue(WebConst.HTML_WELCOME_ADV));
               emailMap.put(WebConst.HTML_LOCKED,getWebServiceValue(WebConst.HTML_LOCKED));
               emailMap.put(WebConst.HTML_RESET,getWebServiceValue(WebConst.HTML_RESET));
               webutil.getUiprofile().setEmailTemplateMap(emailMap);

               Map<String, String> emailSubjectMap = new HashMap<String,String>();
               emailSubjectMap.put(WebConst.EMAIL_WELCOME_SUBJECT,getWebServiceValue(WebConst.EMAIL_WELCOME_SUBJECT));
               emailSubjectMap.put(WebConst.EMAIL_WELCOME_ADV_SUBJECT,getWebServiceValue(WebConst.EMAIL_WELCOME_ADV_SUBJECT));
               emailSubjectMap.put(WebConst.EMAIL_LOCKED_SUBJECT,getWebServiceValue(WebConst.EMAIL_LOCKED_SUBJECT));
               emailSubjectMap.put(WebConst.EMAIL_RESET_SUBJECT,getWebServiceValue(WebConst.EMAIL_RESET_SUBJECT));
               emailSubjectMap.put(WebConst.EMAIL_ACTIVATE_SUBJECT,getWebServiceValue(WebConst.EMAIL_ACTIVATE_SUBJECT));
               webutil.getUiprofile().setEmailSubjectMap(emailSubjectMap);

               webutil.getUiprofile().setAdvisor(getWebServiceValue("DEFAULT.ADVISOR"));
               webutil.getUiprofile().setRep(getWebServiceValue("DEFAULT.REP"));
               webutil.getUiprofile().setCustodydir(getWebServiceValue("CUSTODY.DIR"));
               webutil.getUiprofile().setCustodycss(getWebServiceValue("CUSTODY.CSS"));
               webutil.getUiprofile().setGoogleAnalytic(getWebServiceValue("GOOGLE.ANALYTICS"));
            }
         }
      }
   }

   public void resetCIDProfile(String cid)
   {
      String uri = webutil.getURLAddress("Invessence");

      if (cid != null)
      {
         // If we already set the value for that CID, then don't do it again.
         if (!cid.equals(webutil.getUiprofile().getCid()))
         {
            setCid(cid);
            webutil.getUiprofile().resetAllInfo(getCid(),
                                                getWebServiceValue("COMPANYNAME"),
                                                getWebServiceValue("WEBSITE.URL"),
                                                getWebServiceValue("SECURE.URL"),
                                                getWebServiceValue("LOGO"),
                                                getWebServiceValue("LOGO.SIZE"),
                                                getWebServiceValue("LOGO.LIB"),
                                                getWebServiceValue("MAIN.EMAIL"),
                                                getWebServiceValue("SUPPORT.EMAIL"),
                                                getWebServiceValue("MAIN.PHONE"),
                                                getWebServiceValue("SUPPORT.PHONE"),
                                                getWebServiceValue("COPYRIGHT"),
                                                getWebServiceValue("FORWARD.SERVICE"),
                                                getWebServiceValue("CUSTODY.URL"),
                                                getWebServiceValue("CUSTODY.PROCESS"),
                                                getWebServiceValue("THEME"),
                                                getWebServiceValue("THEME.LIB"),
                                                getWebServiceValue("TEMPLATE.DIR"),
                                                getWebServiceValue("CONSUMER.DIR"),
                                                getWebServiceValue("CSS.DIR"),
                                                getWebServiceValue("CUSTOM.CSS"),
                                                getWebServiceValue("WEB.MODE"),
                                                getWebServiceValue("EMAIL.USER")
            );


            Map<String, String> emailMap = new HashMap<String,String>();
            emailMap.put(WebConst.HTML_WELCOME,getWebServiceValue(WebConst.HTML_WELCOME));
            emailMap.put(WebConst.HTML_LOCKED,getWebServiceValue(WebConst.HTML_LOCKED));
            emailMap.put(WebConst.HTML_RESET,getWebServiceValue(WebConst.HTML_RESET));
            emailMap.put(WebConst.HTML_WELCOME_ADV,getWebServiceValue(WebConst.HTML_WELCOME_ADV));
            webutil.getUiprofile().setEmailTemplateMap(emailMap);
            webutil.getUiprofile().setAdvisor(getWebServiceValue("DEFAULT.ADVISOR"));
            webutil.getUiprofile().setRep(getWebServiceValue("DEFAULT.REP"));
            webutil.getUiprofile().setCustodydir(getWebServiceValue("CUSTODY.DIR"));
            webutil.getUiprofile().setCustodycss(getWebServiceValue("CUSTODY.CSS"));

         }
      }
   }

   @PostConstruct
   public void init()
   {
      if (getCid() == null)
      {
         setCid("0");
         setRep("");
         initialize();
      }
   }

   public void preRenderView()
   {

      try
      {
         if (!FacesContext.getCurrentInstance().isPostback())
         {
            if (getCid() == null || getCid().length() == 0)
            {
               Map<String, String> params = FacesContext.getCurrentInstance().
                  getExternalContext().getRequestParameterMap();
               if (params != null)
               {
                  String tcid = params.get("cid");
                  if (tcid != null)
                  {
                     cid = tcid;
                  }
               }
            }

            resetCIDProfile(getCid());
         }
      }
      catch (Exception e)
      {
      }
   }

   public static long getSerialVersionUID()
   {
      return serialVersionUID;
   }

   public Integer getTabMenu()
   {
      return tabMenu;
   }

   public void setTabMenu(Integer tabMenu)
   {
      this.tabMenu = tabMenu;
   }

   public TabView getMessagesTab()
   {
      return menuTab;
   }

   public void setMessagesTab(TabView messagesTab)
   {
      this.menuTab = messagesTab;
   }

   public String getCid()
   {
      return cid;
   }

   public void setCid(String cid)
   {
      this.cid = cid;
   }

   public String getRep()
   {
      return rep;
   }

   public void setRep(String rep)
   {
      this.rep = rep;
   }

   /* This is a method to go home, used as callback */
   public void defaultHome()
   {
      forwardURL(getDefaultHome());
   }

   public String getDefaultHome()
   {
      if (defaultHome == null)
      {
         return getDefaultDashBoard();
      }
      else
      {
         return defaultHome;
      }
   }

   public void setDefaultHome(String defaultHome)
   {
      if (this.defaultHome == null)
      {
         this.defaultHome = getDefaultDashBoard();
      }
      else
      {
         this.defaultHome = defaultHome;
      }
   }

   public void goToStartPage()
   {
      if ((defaultHome != null) && (!defaultHome.isEmpty()))
      {
         webutil.redirect(defaultHome, null);
      }
      else
      {
         webutil.redirect(getDefaultDashBoard(), null);
      }
   }

   public String getDefaultDashBoard()
   {
      String dashboard;

      if (webutil.isUserLoggedIn())
      {
         if (webutil.hasAccess(WebConst.WEB_ADVISOR))
         {
/*
            if (webutil.getUiprofile().getConsumerdir() != null) {
               dashboard = "/pages/advisor/" + webutil.getUiprofile().getConsumerdir() +"/index.xhtml";
            }
            else {
*/
            dashboard = "/pages/advisor/index.xhtml";
/*
            }
*/
         }
         else
         {
            if (webutil.getUiprofile().getConsumerdir() != null)
            {
               dashboard = "/pages/consumer/" + webutil.getUiprofile().getConsumerdir().trim() + "/index.xhtml";
            }
            else
            {
               dashboard = "/pages/consumer/invessence/index.xhtml";
            }
         }
      }
      else
      {
/*       // Due to mobile app, we cannot go to unsecure site.
         if (getUiprofile().getHomepage() != null) {
            dashboard = getUiprofile().getHomepage();
         }
         else {
*/
            dashboard = webutil.getUiprofile().getHomepage();
/*
         }
*/
      }
      return dashboard;

   }

   public void forwardURL(String menuItem)
   {
      webutil.redirect(menuItem, null);
   }

   public void logout()
   {
      try
      {
         FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
         cid = "0";
         rep = "0";
      }
      catch (Exception ex)
      {

      }
      webutil.redirect("/j_spring_security_logout", null);
   }

   public void doMenuAction(String menuItem)
   {
      String URL;

      try
      {
         if (menuItem == null)
         {
            URL = "/pages/common/invalid.xhtml";
         }
         else
         {
            if (menuItem.startsWith("http"))
            {
               forwardURL(menuItem);
            }

            if ((menuItem.startsWith("/")))
            {
               forwardURL(menuItem);
            }
            else
            {
               forwardURL("/pages/" + menuItem);
            }
         }
      }
      catch (Exception ex)
      {
         webutil.redirect("/pages/common/invalid.xhtml", null);
      }
   }

   public void doCustody(Long logonid, Long acctnum)
   {
      try
      {
         if (webutil.getUiprofile().getCustodyprocess() != null) {
           if (webutil.getUiprofile().getCustodyprocess().equalsIgnoreCase("URL")) {
              if (webutil.getUiprofile().getCustodyURL() != null) {
                  forwardURL(webutil.getUiprofile().getCustodyURL());
                 return;
              }
           }
           else if (webutil.getUiprofile().getCustodyprocess().equalsIgnoreCase("INTERNAL")) {
               if (webutil.getUiprofile().getCustodydir() != null &&
                  webutil.getUiprofile().getCustodydir().equalsIgnoreCase("td")) {
                  doMenuAction("custody", "index.xhtml?l="+logonid.toString()+"&acct="+acctnum.toString());
                  // tdcto.startCTO(logonid,acctnum);
                  return;
               }
           }
         }
         String msgheader = "custody.100";
         webutil.redirecttoMessagePage("ERROR", "Service Error", msgheader);
         return;
      }
      catch (Exception ex)
      {
         webutil.redirect("/pages/common/invalid.xhtml", null);
      }
   }


   public void doMenuAction(String location, String menuItem)
   {
      String URL;

      try
      {
         if (location == null || location.trim().length() == 0)
         {
            doMenuAction(menuItem);
         }
         else
         {
            if (location.equalsIgnoreCase("consumer")) {
               // Only for consumer, they can have custom pages.  Go to Custom Directory if defined.
               if (getConsumerDIR() == null || getConsumerDIR().trim().length() == 0)
               {
                  forwardURL("/pages/consumer/invessence/" + menuItem);
               }
               else
               {
                  forwardURL("/pages/consumer/" + getConsumerDIR() + "/" + menuItem);
               }
            }
            else {
               if (location.equalsIgnoreCase("custody")) {
                  if (getUiprofile().getCustodydir() == null || getUiprofile().getCustodydir().trim().length() == 0)
                  {
                     forwardURL("/pages/custody/invessence/" + menuItem);
                  }
                  else
                  {
                     forwardURL("/pages/custody/" + getUiprofile().getCustodydir() + "/" + menuItem);
                  }
               }
               else {
                  // For other, just go to advusor as is.
                  forwardURL("/pages/" + location.toLowerCase() + "/" + menuItem);
               }
            }
         }
      }
      catch (Exception ex)
      {
         webutil.redirect("/pages/common/invalid.xhtml", null);
      }
   }

   public void whichPage(String access, String pageinfo)
   {
      if (access == null)
      {
         access = "user";
      }

      if (pageinfo == null)
      {
         pageinfo = "index.xhtml";
      }

      if (pageinfo.equalsIgnoreCase("settings") || pageinfo.equalsIgnoreCase("security"))
      {
         doMenuAction("common", "setting.xhtml");
         return;
      }

      if (access.equalsIgnoreCase("user"))
      {
         doMenuAction("consumer", pageinfo);
         return;
      }

      if (access.equalsIgnoreCase("advisor"))
      {
         doMenuAction("advisor", pageinfo);
         return;
      }

      if (access.equalsIgnoreCase("admin"))
      {
         doMenuAction("advisor", pageinfo);
         return;
      }

/*
      if (pageinfo.contains(".xhtml"))
      {
         doMenuAction(pageinfo);
         return;
      }
*/


      doMenuAction("consumer", "index.xhtml");
   }

   public String getDisclaimer()
   {
      String txt = null;
      if (webutil != null)
      {
         txt = webutil.getMessageText().lookupMessage("disclaimer." + cid, null);
      }
      if (txt != null && txt.length() > 0)
      {
         return txt;
      }
      else
      {
         return null;
      }

   }

   public String getTemplateDIR()
   {
      String templateDIR = "/template/common/spark";
      if (webutil.getUiprofile() != null)
      {
         if (webutil.getUiprofile().getTemplatedir() == null || webutil.getUiprofile().getTemplatedir().trim().length() == 0)
         {
            return (templateDIR);
         }
         else
         {
            return (webutil.getUiprofile().getTemplatedir());
         }
      }
      return templateDIR;
   }

   public String getThemeLib()
   {
      String themeLib = WebConst.DEFAULT_THEME + "-layout";
      if (webutil.getUiprofile() == null)
      {
         return (themeLib);
      }
      else if (webutil.getUiprofile().getThemelib() == null || webutil.getUiprofile().getThemelib().trim().length() == 0)
      {
         return (themeLib);
      }
      else
      {
         return (webutil.getUiprofile().getThemelib());
      }
   }

   public String getTheme()
   {
      String theme = WebConst.DEFAULT_THEME;
      if (webutil.getUiprofile() == null)
      {
         return (theme);
      }
      else if (webutil.getUiprofile().getTheme() == null || webutil.getUiprofile().getTheme().trim().length() == 0)
      {
         return (theme);
      }
      else
      {
         return (webutil.getUiprofile().getTheme());
      }
   }

   public String getLogo()
   {
      String logo = WebConst.DEFAULT_LOGO;
      if (webutil.getUiprofile() == null)
      {
         return (logo);
      }
      else if (webutil.getUiprofile().getLogo() == null || webutil.getUiprofile().getLogo().trim().length() == 0)
      {
         return (logo);
      }
      else
      {
         return (webutil.getUiprofile().getLogo());
      }
   }

   public String getCssDIR()
   {
      if (webutil.getUiprofile() == null)
      {
         return (null);
      }
      else
      {
         return (webutil.getUiprofile().getCssdir());
      }
   }

   public String getCustomCSS()
   {
      if (webutil.getUiprofile() == null)
      {
         return (null);
      }
      else
      {
         return (webutil.getUiprofile().getCustomcss());
      }
   }

   public String getConsumerDIR()
   {
      if (webutil.getUiprofile() == null)
      {
         return (null);
      }
      else
      {
         return (webutil.getUiprofile().getConsumerdir());
      }
   }
}
