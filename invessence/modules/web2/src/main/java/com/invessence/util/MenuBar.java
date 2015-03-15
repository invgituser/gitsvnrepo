package com.invessence.util;

import java.io.Serializable;
import java.util.Collection;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;

import com.invessence.constant.Const;
import com.invessence.data.UserInfoData;
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

@ManagedBean(name = "menubar")
@ApplicationScoped
public class MenuBar implements Serializable
{
   private static final long serialVersionUID = -1002L;
   private UserValidation uv = new UserValidation();
   private Integer tabMenu = 0;

   public Integer getTabMenu()
   {
      return tabMenu;
   }

   public void setTabMenu(Integer tabMenu)
   {
      this.tabMenu = tabMenu;
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
         if (hasRole(Const.ROLE_ADVISOR)) {
            uid =  getUserInfoData();
            if (uid != null) {
               logo = uid.getLogo();
               if (logo == null)
                  logo= Const.DEFAULT_LOGO;
            }
         }
      }
      catch (Exception ex) {
         ex.printStackTrace();
      }
      return logo;
   }


   public String getLogonid()
   {
      return getUserInfoID().toString();
   }

   public static UserInfoData getUserInfoData() {

/*
       Authentication auth = (Authentication) ((SecurityContext)
         SecurityContextHolder.getContext()).getAuthentication();

      if ( (auth != null) && (auth.getPrincipal() instanceof UserInfoData )) {
         return (UserInfoData) auth.getPrincipal();

      } else {
         return null;
      }
*/

      return null;
   }


   public static Long getUserInfoID() {

      //Object principal = (Object) ((SecurityContext)
      //SecurityContextHolder.getContext()).getAuthentication().getPrincipal();

      UserInfoData userInfoData = null;

/*
      Authentication auth = (Authentication) ((SecurityContext)
         SecurityContextHolder.getContext()).getAuthentication();

      if ( (auth != null) && (auth.getPrincipal() instanceof UserInfoData )) {
         userInfoData = (UserInfoData) auth.getPrincipal();
         return userInfoData.getLogonID();

      } else {
         return 0L;
      }
*/
      return 0L;

   }

   public boolean hasRole(String role) {


      boolean grantedRole = false;
/*
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
*/
      return grantedRole;
   }

   public boolean isUserLoggedIn() {

      if (getUserInfoData() != null)
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
      String URL = "/createInvestment.xhtml";
      if (hasRole(Const.ROLE_ADVISOR))
         URL = "/advisor/add.xhtml";

      uv.redirect(URL,null);
   }


   public void trade() {
      String URL = "/editor.xhtml";
      if (hasRole(Const.ROLE_ADVISOR))
         URL = "/advisor/add.xhtml";
      uv.redirect(URL,null);
   }

   public void manage() {
      String URL = "/editor.xhtml";
      if (hasRole(Const.ROLE_ADVISOR))
         URL = "/advisor/manage.xhtml";
      else if (hasRole(Const.ROLE_ADMIN))
         URL =  "/admin/admin_profile.xhtml";

      uv.redirect(URL,null);
   }

   public void profile() {
      String URL = "/profile.xhtml";
      if (hasRole(Const.ROLE_ADVISOR))
         URL = "/advisor/profile.xhtml";
      else if (hasRole(Const.ROLE_ADMIN))
         URL =  "/admin/profile.xhtml";
      uv.redirect(URL,null);
   }

   public void adminAccounts() {
      String URL = "#";
      if (hasRole(Const.ROLE_ADMIN))
         URL =  "/admin/admin_profile.xhtml";

      uv.redirect(URL,null);
   }

   public void adminTrade() {
      String URL = "#";
      if (hasRole(Const.ROLE_ADMIN))
         URL =  "/admin/admin_rebal.xhtml";

      uv.redirect(URL,null);
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
