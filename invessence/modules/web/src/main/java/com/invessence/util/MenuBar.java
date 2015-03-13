package com.invessence.util;

import java.io.Serializable;
import java.util.Collection;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;

import com.invessence.bean.advisor.AdvisorBean;
import com.invessence.constant.Const;
import com.invessence.data.*;
import org.primefaces.component.tabview.TabView;
import org.primefaces.event.TabChangeEvent;
import org.springframework.context.*;
import org.springframework.security.core.*;
import org.springframework.security.core.context.*;

import static javax.faces.context.FacesContext.getCurrentInstance;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 10/20/14
 * Time: 10:03 AM
 * To change this template use File | Settings | File Templates.
 */

public class MenuBar extends MenuBarWidgets implements MessageSourceAware, Serializable
{
   private static final long serialVersionUID = -1992L;

   @ManagedProperty("#{advisorBean}")
   private AdvisorBean advisorbean;
   private WebUtil webutil = new WebUtil();
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

   public AdvisorBean getAdvisorbean()
   {
      return advisorbean;
   }

   public void setAdvisorbean(AdvisorBean advisorbean)
   {
      this.advisorbean = advisorbean;
   }

   public String getRole()
   {
      String role= "Client";
      try {
         if (getCurrentInstance().getExternalContext().getSessionMap().get(Const.USERLOGON_ACCTTYPE) != null)
         {
            role = getCurrentInstance().getExternalContext().getSessionMap().get(Const.USERLOGON_ACCTTYPE).toString();
         }
      }
      catch (Exception ex) {
         ex.printStackTrace();
      }
      return role;
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


   public String getLogonid()
   {
      return getUserInfoID().toString();
   }



   public Long getUserInfoID() {

      UserInfoData userInfoData = webutil.getUserInfoData();
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

   public boolean isUserLoggedIn() {

      if (webutil.getUserInfoData() != null)
         return true;
      else
         return false;

   }

   public String enableMenu(String role) {
      if (hasRole(Const.ROLE_ADMIN))
         return "true";
      else if (hasRole(role))
         return "true";
      else return "false";
   }

   public void add() {
      String URL = "/add.xhtml";
      if (hasRole(Const.ROLE_ADVISOR)) {
         advisorbean.resetAdvisorBean();
         URL = "/advisor/add.xhtml";
      }
      webutil.redirect(URL,null);
   }


   public void trade() {
      String URL = "/manage.xhtml";
      if (hasRole(Const.ROLE_ADVISOR))
         URL = "/advisor/add.xhtml";
      webutil.redirect(URL,null);
   }

   public void manage() {
      String URL = "/manage.xhtml";
      if (hasRole(Const.ROLE_ADVISOR))
         URL = "/advisor/manage.xhtml";

      webutil.redirect(URL,null);
   }

   public void profile() {
      String URL = "/profile.xhtml";
      if (hasRole(Const.ROLE_ADVISOR))
         URL = "/advisor/profile.xhtml";
      webutil.redirect(URL,null);
   }

   public void salesUser(String role) {
      String URL = "#";
      if (hasRole(Const.ROLE_ADMIN) || hasRole(role))
         URL =  "/admin/marketing.xhtml";

      webutil.redirect(URL,null);
   }

   public void adminProfile() {
      String URL = "#";
      if (hasRole(Const.ROLE_ADMIN))
         URL =  "/admin/profile.xhtml";

      webutil.redirect(URL,null);
   }

   public void adminUsers() {
      String URL = "#";
      if (hasRole(Const.ROLE_ADMIN) || hasRole(Const.ROLE_SALES))
         URL =  "/admin/users.xhtml";

      webutil.redirect(URL,null);
   }

   public void adminTrade() {
      String URL = "#";
      if (hasRole(Const.ROLE_ADMIN))
         URL =  "/admin/trade.xhtml";

      webutil.redirect(URL,null);
   }

   public void adminManage() {
      String URL = "#";
      if (hasRole(Const.ROLE_ADMIN))
         URL =  "/admin/manage.xhtml";

      webutil.redirect(URL,null);
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
            webutil.redirect(url,null);
         }
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
