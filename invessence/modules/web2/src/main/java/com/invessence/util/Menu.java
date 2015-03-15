package com.invessence.util;

import java.io.Serializable;
import java.util.Collection;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;

import com.invessence.constant.Const;
import com.invessence.data.*;
import org.primefaces.component.tabview.TabView;
import org.primefaces.event.TabChangeEvent;
import org.springframework.context.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.*;

import static javax.faces.context.FacesContext.getCurrentInstance;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 10/20/14
 * Time: 10:03 AM
 * To change this template use File | Settings | File Templates.
 */

@ManagedBean(name = "menu")
@SessionScoped
public class Menu extends MenuBarWidgets implements MessageSourceAware, Serializable
{
   private static final long serialVersionUID = -1992L;

   private UserValidation uv = new UserValidation();
   private Integer tabMenu = 0;
   private TabView menuTab = new TabView();
   private String cid;
   private String default_page;
   private MessageSource messageSource;

   public void setMessageSource(MessageSource messageSource)
   {
      this.messageSource = messageSource;
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

   public String getAccess()
   {
      String access= "User";
      try {
         UserInfoData userInfoData = uv.getUserInfoData();
         if (userInfoData != null) {
            access = userInfoData.getAccess();
         }
      }
      catch (Exception ex) {
         ex.printStackTrace();
      }
      return access;
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


   public Long getLogonID() {

      UserInfoData userInfoData = uv.getUserInfoData();
      if (userInfoData != null) {
         return userInfoData.getLogonID();
      }
      else {
         return 0L;
      }
   }

   public boolean hasRole(String role) {


      boolean grantedRole = false;
      try {
         Object principal = (Object) ((SecurityContext)
            SecurityContextHolder.getContext()).getAuthentication().getPrincipal();

         if (principal instanceof UserInfoData ) {
            Collection<GrantedAuthority> roleCollection = ((UserInfoData)principal).getAuthorities();

            for (GrantedAuthority auth: roleCollection) {

               if ( (auth.getAuthority().equalsIgnoreCase(role))
                  ) {
                  grantedRole = true;
                  break;
               }
            }

         }
      }
      catch (Exception ex) {
        System.out.println("Context Object is null, no priviledges.  User is not logged on.");
      }
      return grantedRole;
   }

   public String getIsUserLoggedIn() {

      if (uv.getUserInfoData() != null)
         return "true";
      else
         return "false";

   }

   public String getHasAdvisorAccess() {

      String access = getAccess();
      if (access != null) {
         if (access.equalsIgnoreCase(Const.WEB_ADVISOR))
            return "true";
         else
            return "false";
      }
      return "false";

   }

   public String getHasConsumerAccess() {

      String access = getAccess();
      if (access != null) {
         if (access.equalsIgnoreCase(Const.WEB_CONSUMER))
            return "true";
         else
            return "false";
      }
      return "false";
   }

   public String enableMenu(String role) {
      if (hasRole(Const.ROLE_ADMIN))
         return "true";
      else if (hasRole(role))
         return "true";
      else return "false";
   }

   public void redirectStartPage() {
      if (default_page == null) {
         doMenuAction(1, 0);
      }
   }
   public void doMenuAction(Integer menuid, Integer submenuid){
      String URL;

      try {
         if (menuid == null) {
            URL = "/common/invalid.xhtml";
         }
         else {
            setTabMenu(menuid);
            if (getAccess().equalsIgnoreCase(Const.WEB_ADVISOR))
               URL = "/advisor/" + advisormenuURL();
            else
            if (getAccess().equalsIgnoreCase(Const.WEB_INTERNAL))
               URL = "/advisor/" + advisormenuURL();
            else
               URL = "/consumer/" + consumermenuURL();
         }
         uv.redirect(URL,null);
         // If We get invalid, URL, we may want to redirect to Under Construction ...
      }
      catch (Exception ex) {
         uv.redirect("/common/construction.xhtml",null);
      }
   }

   public void setDefaultPage()
      {
         String accttype;
         String url = "/manage.xhtml";
         try {
            if (getCurrentInstance().getExternalContext().getSessionMap().get(Const.USERLOGON_ACCTTYPE) != null)
            {
               accttype = getCurrentInstance().getExternalContext().getSessionMap().get(Const.USERLOGON_ACCTTYPE).toString();
               if (accttype.equalsIgnoreCase(Const.ROLE_ADVISOR)) {
                  url = "/advisor/add.xhtml";
               }
               else  if (accttype.equalsIgnoreCase(Const.ROLE_ADMIN)) {
                  url = "/admin/welcome.xhtml";
               }
               else
                  url = "/manage.xhtml";
            }
            else {
               url = "/manage.xhtml";
            }

         }
         catch (Exception ex) {
            ex.printStackTrace();
         }
         finally {
            uv.redirect(url,null);
         }
   }

   public void logout() {
      try {
         FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
      }
      catch (Exception ex) {

      }
      uv.redirect("/j_spring_security_logout",null);
   }

}
