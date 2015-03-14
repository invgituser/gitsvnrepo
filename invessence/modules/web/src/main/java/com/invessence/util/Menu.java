package com.invessence.util;

import java.io.Serializable;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;

import com.invessence.constant.Const;
import com.invessence.data.common.UserInfoData;
import org.primefaces.component.tabview.TabView;
import org.primefaces.event.TabChangeEvent;
import org.springframework.context.*;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 10/20/14
 * Time: 10:03 AM
 * To change this template use File | Settings | File Templates.
 */

@ManagedBean(name = "menu")
@SessionScoped
public class Menu implements MessageSourceAware, Serializable
{
   private static final long serialVersionUID = -1992L;

   private WebUtil webutil = new WebUtil();
   private Integer tabMenu = 0;
   private TabView menuTab = new TabView();
   private String cid;
   private String default_page;
   private MessageSource messageSource;

   private String forwardcustodianURL = "Your have made a request to visit Interactive Broker site (Your custodian).  You will be logged out of this site.  Do you want to continue?";

   public void setMessageSource(MessageSource messageSource)
   {
      this.messageSource = messageSource;
   }

   public String getForwardcustodianURL()
   {
      return forwardcustodianURL;
   }

   public String getMessagetext(String inputText, Object [] obj) {
      String msgText = null;
      try {
         if (obj == null) {
            obj = new Object[]{};
         }
         if (inputText != null) {
            msgText =  messageSource.getMessage(inputText, obj, null);
            if (msgText != null)
               return msgText;
            return inputText;
         }

      }
      catch (Exception ex) {
         ex.printStackTrace();
      }
      return inputText;
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
      UserInfoData uid;
      try {
         if (getCid() != null) {
            String logoid = "logo." + getCid();
            logo = getMessagetext(logoid,null);
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
         if (getCid() != null) {
            String logoid = "company." + getCid();
            logoAlt = getMessagetext(logoid,null);
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
         if (getCid() != null) {
            String logoid = "homeURL." + getCid();
            homeURL = getMessagetext(logoid,null);
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
      if (webutil.hasRole(Const.ROLE_ADMIN))
         return "true";
      else if (webutil.hasRole(role))
         return "true";
      else return "false";
   }

   public void redirectStartPage() {
      if (default_page == null) {
         if (webutil.getAccess().equalsIgnoreCase(Const.WEB_ADVISOR) || webutil.getAccess().equalsIgnoreCase(Const.WEB_INTERNAL))
            doMenuAction("/advisor/manage.xhtml");
         else
            doMenuAction("/consumer/manage.xhtml");
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
                     if (menuItem.contains("add.xhtml"))
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
      }
      catch (Exception ex) {

      }
      webutil.redirect("/j_spring_security_logout",null);
   }

}
