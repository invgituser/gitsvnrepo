package com.invessence.web.util;

import java.io.Serializable;
import java.util.*;
import javax.annotation.PostConstruct;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;

import com.invessence.service.bean.WebConfigDetails;
import com.invessence.service.util.ServiceParameters;
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
   private WebUtil webutil;

   public WebUtil getWebutil() {
      return webutil;
   }

   public UIProfile getUiprofile() {
      return webutil.getUiprofile();
   }

   private String webserviceDB(Map<String,WebConfigDetails> webDB, String name)   {
      String value = null;
      if (webDB != null) {
         if (webDB.containsKey(name)) {
            value = webDB.get(name).getValue();
         }
      }
      return value;
   }

   public void resetCIDProfile(String cid)
   {
      if (cid != null)
      {
         if (!cid.equals(webutil.getUiprofile().getCid()))
         {
            String companyname= null;
            String websiteurl = null, secureurl = null;
            String logo = null, logosize = null, logolib = null;
            String mainemail = null, supportemail = null;
            String mainphone = null, supportphone = null;
            String copyright = null;
            String forwardservice = null, custodyURL = null, accountOpeningURL = null;
            String theme = null, themelib = null;
            String consumerdir = null;
            String templatedir = null, cssdir = null, customcss = null;
            String webmode = null;

            if (ServiceParameters.webSiteConfigDetailsMap != null) {
               Map<String,WebConfigDetails> webSiteConfigDetails = ServiceParameters.webSiteConfigDetailsMap.get("0");

               if (webSiteConfigDetails != null) {
                  companyname = webserviceDB(webSiteConfigDetails,"COMPANYNAME");
                  websiteurl = webserviceDB(webSiteConfigDetails,"WEBSITE.URL");
                  secureurl = webserviceDB(webSiteConfigDetails,"SECURE.URL");
                  logo = webserviceDB(webSiteConfigDetails,"LOGO");
                  logosize = webserviceDB(webSiteConfigDetails,"LOGO.SIZE");
                  logolib = webserviceDB(webSiteConfigDetails,"LOGO.LIB");
                  mainemail = webserviceDB(webSiteConfigDetails,"MAIN.EMAIL");
                  supportemail = webserviceDB(webSiteConfigDetails,"SUPPORT.EMAIL");
                  mainphone = webserviceDB(webSiteConfigDetails,"MAIN.PHONE");
                  supportphone = webserviceDB(webSiteConfigDetails,"SUPPORT.PHONE");
                  copyright = webserviceDB(webSiteConfigDetails,"COPYRIGHT");
                  forwardservice = webserviceDB(webSiteConfigDetails,"FORWARD.SERVICE");
                  custodyURL = webserviceDB(webSiteConfigDetails,"CUSTODY.URL");
                  accountOpeningURL = webserviceDB(webSiteConfigDetails,"ACCOUNTOPENING.URL");
                  theme = webserviceDB(webSiteConfigDetails,"THEME");
                  themelib = webserviceDB(webSiteConfigDetails,"THEME.LIB");
                  templatedir = webserviceDB(webSiteConfigDetails,"TEMPLATE.DIR");
                  consumerdir = webserviceDB(webSiteConfigDetails,"CONSUMER.DIR");
                  cssdir = webserviceDB(webSiteConfigDetails,"CSS.DIR");
                  customcss = webserviceDB(webSiteConfigDetails,"CUSTOM.CSS");
                  webmode = webserviceDB(webSiteConfigDetails,"WEB.MODE");
               }
            }

            /* In case we cannot load from service, then default to default company.properties file as backup */
            if (companyname == null) {

               companyname = webutil.getMessageText().lookupMessage("COMPANYNAME." + cid, null);
               websiteurl = webutil.getMessageText().lookupMessage("WEBSITE.URL." + cid, null);
               secureurl = webutil.getMessageText().lookupMessage("SECURE.URL." + cid, null);
               logo = webutil.getMessageText().lookupMessage("LOGO." + cid, null);
               logosize = webutil.getMessageText().lookupMessage("LOGO.SIZE." + cid, null);
               logolib = webutil.getMessageText().lookupMessage("LOGO.LIB." + cid, null);
               mainemail = webutil.getMessageText().lookupMessage("MAIN.EMAIL." + cid, null);
               supportemail = webutil.getMessageText().lookupMessage("SUPPORT.EMAIL." + cid, null);
               mainphone = webutil.getMessageText().lookupMessage("MAIN.PHONE." + cid, null);
               supportphone = webutil.getMessageText().lookupMessage("SUPPORT.PHONE." + cid, null);
               copyright = webutil.getMessageText().lookupMessage("COPYRIGHT." + cid, null);
               forwardservice = webutil.getMessageText().lookupMessage("FORWARD.SERVICE." + cid, null);
               custodyURL = webutil.getMessageText().lookupMessage("CUSTODY.URL." + cid, null);
               accountOpeningURL = webutil.getMessageText().lookupMessage("ACCOUNTOPENING.URL." + cid, null);
               theme = webutil.getMessageText().lookupMessage("THEME." + cid, null);
               themelib = webutil.getMessageText().lookupMessage("THEME.LIB." + cid, null);
               templatedir = webutil.getMessageText().lookupMessage("TEMPLATE.DIR." + cid, null);
               consumerdir = webutil.getMessageText().lookupMessage("CUSTOM.DIR." + cid, null);
               cssdir = webutil.getMessageText().lookupMessage("CSS.DIR." + cid, null);
               customcss = webutil.getMessageText().lookupMessage("CUSTOM.CSS." + cid, null);
               webmode = webutil.getMessageText().lookupMessage("WEB.MODE" + cid, null);
            }


            webutil.getUiprofile().resetAllInfo(cid,
                         companyname, websiteurl, secureurl,
                         logo, logosize, logolib,
                         mainemail, supportemail, mainphone, supportphone,
                         copyright,
                         forwardservice, custodyURL, accountOpeningURL,
                         theme, themelib,
                         templatedir,
                         consumerdir, cssdir, customcss,
                         webmode);

         }
      }
   }

   @PostConstruct
   public void init() {
      if (getCid() == null)
      {
         setCid("0");
         setRep("");
         resetCIDProfile(getCid());
      }
   }

   public void preRenderView()
   {

      try {
         if (!FacesContext.getCurrentInstance().isPostback())
         {
            if (getCid() == null || getCid().length() == 0) {
               Map<String, String> params =FacesContext.getCurrentInstance().
                  getExternalContext().getRequestParameterMap();
               if (params != null) {
                  String tcid = params.get("cid");
                  if (tcid != null)
                     cid = tcid;
               }
            }

            if (getCid() == null)
            {
               setCid("0");
               setRep("");
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

   public TabView getMessagesTab () {
      return menuTab;
   }

   public void setMessagesTab(TabView messagesTab ) {
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
         if (defaultHome == null) {
            return getDefaultDashBoard();
         }
         else {
            return defaultHome;
         }
   }

   public void setDefaultHome(String defaultHome)
   {
      if (this.defaultHome == null) {
            this.defaultHome = getDefaultDashBoard();
      }
      else {
         this.defaultHome = defaultHome;
      }
    }

   public void goToStartPage() {
     if ((defaultHome != null) && (! defaultHome.isEmpty())) {
         webutil.redirect(defaultHome, null);
     }
     else {
       webutil.redirect(getDefaultDashBoard(), null);
     }
   }

   public String getDefaultDashBoard() {
      String dashboard;

      if (webutil.isUserLoggedIn()) {
         if (webutil.hasAccess(WebConst.WEB_ADVISOR)) {
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
         else {
            if (webutil.getUiprofile().getConsumerdir() != null) {
               dashboard = "/pages/consumer/"+webutil.getUiprofile().getConsumerdir().trim()+"/index.xhtml";
            }
            else {
               dashboard = "/pages/consumer/index.xhtml";
            }
         }
      }
      else {
         dashboard = "/index.xhtml";
      }
      return dashboard;

   }

   public void forwardURL(String menuItem){
      webutil.redirect(menuItem,null);
   }

   public void logout() {
      try {
         FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
         cid="0";
         rep="0";
      }
      catch (Exception ex) {

      }
      webutil.redirect("/j_spring_security_logout",null);
   }

   public void doMenuAction(String menuItem){
      String URL;

      try {
         if (menuItem == null) {
            URL = "/pages/common/invalid.xhtml";
         }
         else {
            if (menuItem.startsWith("http")) {
               forwardURL(menuItem);
            }

            if ((menuItem.startsWith("/"))) {
               forwardURL(menuItem);
            }
            else {
               forwardURL("/pages/" + menuItem);
            }
         }
      }
      catch (Exception ex) {
         webutil.redirect("/pages/common/invalid.xhtml",null);
      }
   }

   public void doMenuAction(String location, String menuItem){
      String URL;

      try {
         if (location == null || location.trim().length() == 0)
            doMenuAction(menuItem);
         else {
            if (getConsumerDIR() == null || getConsumerDIR().trim().length() == 0) {
               forwardURL("/pages/" + location.toLowerCase() + "/" + menuItem);
            }
            else {
               forwardURL("/pages/" + location.toLowerCase() + "/" + getConsumerDIR() + "/" + menuItem);
            }
         }
      }
      catch (Exception ex) {
         webutil.redirect("/pages/common/invalid.xhtml",null);
      }
   }

   public void whichPage(String access, String pageinfo)
   {
      if (access == null)
         access = "user";

      if (pageinfo == null)
         pageinfo = "index.xhtml";

      if (pageinfo.contains(".xhtml")) {
         doMenuAction(pageinfo);
         return;
      }

      if (pageinfo.equalsIgnoreCase("settings") || pageinfo.equalsIgnoreCase("security")) {
         doMenuAction("common", "setting.xhtml");
         return;
      }

      if (access.equalsIgnoreCase("user")) {
         doMenuAction("consumer", pageinfo);
         return;
      }

      if (access.equalsIgnoreCase("advisor")) {
         doMenuAction("advisor", pageinfo);
         return;
      }

      if (access.equalsIgnoreCase("admin")) {
         doMenuAction("admin", pageinfo);
         return;
      }

      doMenuAction("consumer", "index.xhtml");
   }

   public String getDisclaimer() {
      String txt = null;
      if (webutil != null) {
         txt = webutil.getMessageText().lookupMessage("disclaimer." + cid, null);
      }
      if (txt != null && txt.length() > 0)
         return txt;
      else
         return null;

   }

   public String getTemplateDIR() {
      String templateDIR = "/template/common/spark";
      if (webutil.getUiprofile() != null) {
         if (webutil.getUiprofile().getTemplatedir() == null || webutil.getUiprofile().getTemplatedir().trim().length() == 0)
            return (templateDIR);
         else
            return (webutil.getUiprofile().getTemplatedir());
      }
      return templateDIR;
   }

   public String getThemeLib() {
      String themeLib = WebConst.DEFAULT_THEME + "-layout";
      if (webutil.getUiprofile() == null)
         return (themeLib);
      else
      if (webutil.getUiprofile().getThemelib() == null || webutil.getUiprofile().getThemelib().trim().length() == 0)
         return (themeLib);
      else
         return (webutil.getUiprofile().getThemelib());
   }

   public String getTheme() {
      String theme = WebConst.DEFAULT_THEME;
      if (webutil.getUiprofile() == null)
         return (theme);
      else
      if (webutil.getUiprofile().getTheme() == null || webutil.getUiprofile().getTheme().trim().length() == 0)
         return (theme);
      else
         return (webutil.getUiprofile().getTheme());
   }

   public String getLogo()
   {
      String logo = WebConst.DEFAULT_LOGO;
      if (webutil.getUiprofile() == null)
         return (logo);
      else
      if (webutil.getUiprofile().getLogo() == null || webutil.getUiprofile().getLogo().trim().length() == 0)
         return (logo);
      else
         return (webutil.getUiprofile().getLogo());
   }

   public String getCssDIR()
   {
      if (webutil.getUiprofile() == null)
         return (null);
      else
         return (webutil.getUiprofile().getCssdir());
   }

   public String getCustomCSS()
   {
      if (webutil.getUiprofile() == null)
         return (null);
      else
         return (webutil.getUiprofile().getCustomcss());
   }

   public String getConsumerDIR()
   {
      if (webutil.getUiprofile() == null)
         return (null);
      else
         return (webutil.getUiprofile().getConsumerdir());
   }
}
