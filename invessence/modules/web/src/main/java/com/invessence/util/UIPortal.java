package com.invessence.util;

import java.io.Serializable;
import java.util.Map;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;

import com.invessence.constant.Const;
import com.invessence.data.common.UserInfoData;
import org.primefaces.component.tabview.TabView;
import org.primefaces.event.TabChangeEvent;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 10/20/14
 * Time: 10:03 AM
 * To change this template use File | Settings | File Templates.
 */

@ManagedBean(name = "uiportal")
@SessionScoped
@Component("config")
public class UIPortal implements Serializable
{
   private static final long serialVersionUID = -1992L;

   private Integer tabMenu = 0;
   private TabView menuTab = new TabView();
   private String theme = "modena";
   private String themeLibrary = "modena-layout";
   private String themeid;
   private String cid, rep;
   private String default_page;
   private String phone, email;
   private String forwardcustodianURL = "Your have made a request to visit Interactive Broker site (Your custodian).  You will be logged out of this site.  Do you want to continue?";

   @ManagedProperty("#{webutil}")
   private WebUtil webutil = new WebUtil();
   public void setWebutil(WebUtil webutil)
   {
      this.webutil = webutil;
   }

   public WebUtil getWebutil() {
      return webutil;
   }

   public String getForwardcustodianURL()
   {
      return forwardcustodianURL;
   }

   public String getPhone()
   {
      return phone;
   }

   public String getEmail()
   {
      return email;
   }

   public String getTheme()
   {
      return theme;
   }

   public String getThemeLibrary()
   {
      return themeLibrary;
   }

   public String getWelcome() {
      return webutil.getWelcome();
   }

   public String getUsername() {
      return webutil.getLastFirstName();
   }

   public void resetTheme(String cid) {
      if (cid != null) {
         if (! cid.equals(themeid)) {
            themeid = cid;
            email = webutil.getMessageText().lookupMessage("supportemail." + themeid, null);
            phone = webutil.getMessageText().lookupMessage("supportphone." + themeid, null);
            theme = webutil.getMessageText().lookupMessage("theme." + themeid, null);
            themeLibrary = webutil.getMessageText().lookupMessage("library." + themeid, null);

            if (email == null)
               email = "info@invessence.com";

            if (phone == null)
               phone = "(201) 977-1955";

            if (theme == null)
               theme = "modena";

            if (themeLibrary == null)
               themeLibrary = "modena-layout";

         }

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
               setRep("0");
            }
            resetTheme(getCid());
         }
      }
      catch (Exception e)
      {
         email = "info@invessence.com";
         phone = "(201) 977-2704";
         theme = "modena";
         themeLibrary = "modena-layout";
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

   public String getThemeid()
   {
      return themeid;
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

   public String getDefault_page()
   {
      return default_page;
   }

   public void setDefault_page(String default_page)
   {
      this.default_page = default_page;
   }

   public void onTabChange(TabChangeEvent event) {
      TabView tabView = (TabView) event.getComponent();
      Integer fromTab = null, toTab = null;

      int activeIndex = tabView.getChildren().indexOf(event.getTab());
      fromTab = toTab;  // prior tab
      toTab = activeIndex; // new tab.

      this.menuTab.setActiveIndex(activeIndex);
      this.setTabMenu(activeIndex);
   }

   public String getLogo()
   {
      String logo = Const.DEFAULT_LOGO;
      try {
         if (getThemeid() != null) {
            String logoid = "logo." + getThemeid();
            logo = webutil.getMessageText().buildInternalMessage(logoid,null);
         }
         if (logo == null)
            logo = Const.DEFAULT_LOGO;
      }
      catch (Exception ex) {
         logo = Const.DEFAULT_LOGO;
      }
      return logo;
   }

   public String getLogoAlt()
   {
      String logoAlt = Const.COMPANY_NAME;
      UserInfoData uid;
      try {
         if (getThemeid() != null) {
            String logoid = "companyname." + getThemeid();
            logoAlt = webutil.getMessageText().buildInternalMessage(logoid, null);
         }
         if (logoAlt == null)
            logoAlt = Const.COMPANY_NAME;
      }
      catch (Exception ex) {
         logoAlt = Const.COMPANY_NAME;
      }
      return logoAlt;
   }

   public String getDefaultHome()
   {
      String homeURL = Const.URL_HOME;
      UserInfoData uid;
      try {
         if (webutil.isUserLoggedIn()) {
            if (default_page == null)
               return "#";
            else
               return default_page;
         }

         if (getThemeid() != null) {
            String logoid = "homeURL." + getThemeid();
            homeURL = webutil.getMessageText().buildInternalMessage(logoid, null);
         }
         if (homeURL == null)
            homeURL = Const.URL_HOME;
      }
      catch (Exception ex) {
         homeURL = Const.URL_HOME;
      }
      return homeURL;
   }



   public String enableMenu(String role) {
      if (webutil.hasAccess(Const.ROLE_ADMIN))
         return "true";
      else if ((role.toUpperCase().equals(Const.ROLE_USER)) && (webutil.hasRole(role)))
         return "true";
      else if ((webutil.hasAccess(Const.WEB_ADVISOR)) && (webutil.hasRole(role)))
         return "true";
      else return "false";
   }

   public void redirectStartPage() {
      if (webutil.isUserLoggedIn())
      {
         if (default_page == null)
         {
            if (webutil.getAccess().equalsIgnoreCase(Const.WEB_ADVISOR) || webutil.getAccess().equalsIgnoreCase(Const.WEB_INTERNAL))
            {
               setDefault_page("/pages/advisor/adash.xhtml");
               doMenuAction("/advisor/adash.xhtml");
            }
            else
            {
               setDefault_page("/pages/consumer/cdash.xhtml");
               doMenuAction("/consumer/cdash.xhtml");
            }
         }
         else
         {
            webutil.redirect(default_page, null);
         }
      }
      else
      {
         webutil.redirect("/login.xhtml", null);
      }

   }

   public String getIsUserLoggedInMenu() {

      if (webutil.isUserLoggedIn())
         return "true";
      else
         return "false";

   }

   public String hasAccessMenu(String role) {

      if (webutil.hasAccess(role))
         return "true";
      else
         return "false";

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

            if (menuItem.startsWith("/")) {
               URL = "/pages" + menuItem;

               if (menuItem.contains("add.xht") ||
                  menuItem.contains("setting.xht")) {
                  if (! menuItem.contains("?")) {
                     if (menuItem.contains("add"))
                        URL = "/pages" + menuItem + "?acct=0";
                     if (menuItem.contains("setting.xht"))
                        URL = "/pages" + menuItem + "?id=" + webutil.getLogonid().toString();
                  }

               }
            }
            else {
               if (webutil.hasAccess("Advisor"))
                  URL = "/pages/advisor/" + menuItem;
               else
                  URL = "/pages/consumer/" + menuItem;
            }
          }
         webutil.redirect(URL, null);
         // If We get invalid, URL, we may want to redirect to Under Construction ...
      }
      catch (Exception ex) {
         webutil.redirect("/pages/common/invalid.xhtml",null);
      }
   }

   public void forwardURL(String menuItem){
      webutil.redirect(menuItem,null);
   }

   public void logout() {
      try {
         FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
         cid="0";
         rep="0";
         email=null;
         phone=null;
         theme="modena";
      }
      catch (Exception ex) {

      }
      webutil.redirect("/j_spring_security_logout",null);
   }

}
