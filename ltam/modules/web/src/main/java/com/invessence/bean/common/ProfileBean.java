package com.invessence.bean.common;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;

import com.invessence.constant.Const;
import com.invessence.converter.SQLData;
import com.invessence.dao.common.UserInfoDAO;
import com.invessence.data.common.UserData;
import com.invessence.util.*;
import org.primefaces.event.TabChangeEvent;
import org.springframework.beans.factory.annotation.Autowired;

@ManagedBean(name = "profileBean")
@SessionScoped
public class ProfileBean implements Serializable
{

   private Long beanlogonidID;
   private String password, confirmPassword;
   private UserData userData = new UserData();

   @ManagedProperty("#{userInfoDAO}")
   private UserInfoDAO userInfoDAO;

   @ManagedProperty("#{emailMessage}")
   private EmailMessage messageText;

   @ManagedProperty("#{webutil}")
   private WebUtil webutil;
   public void setWebutil(WebUtil webutil)
   {
      this.webutil = webutil;
   }

   public void setUserInfoDAO(UserInfoDAO userInfoDAO)
   {
      this.userInfoDAO = userInfoDAO;
   }

   public void setMessageText(EmailMessage messageText)
   {
      this.messageText = messageText;
   }

   public Long getBeanlogonidID()
   {
      return beanlogonidID;
   }

   public void setBeanlogonidID(Long beanlogonidID)
   {
      this.beanlogonidID = beanlogonidID;
   }

   public UserData getUserData()
   {
      return userData;
   }

   public void setUserData(UserData userData)
   {
      this.userData = userData;
   }

   public String getPassword()
   {
      return password;
   }

   public void setPassword(String password)
   {
      this.password = password;
   }

   public String getConfirmPassword()
   {
      return confirmPassword;
   }

   public void setConfirmPassword(String confirmPassword)
   {
      this.confirmPassword = confirmPassword;
   }

   public void preRenderView()
   {

      try {
         if (!FacesContext.getCurrentInstance().isPostback())
         {
            SQLData converter = new SQLData();
            if (webutil != null) {
               beanlogonidID = webutil.getLogonid();
               if (beanlogonidID != null && beanlogonidID > 0L)
                  loadData(beanlogonidID);
            }
         }

      }
      catch (Exception e)
      {
      }
   }

   @PostConstruct
   public void init()
   {
      try
      {
         webutil.validatePriviledge(null);
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
   }

   private void resetData() {
      password = null;
      confirmPassword = null;
      userData = new UserData();
   }

   public void loadData(Long acctnum) {

      try {
         // logger.debug("Calling userInfoDAO.selectUserInfo(null," + userid +"," + email + ")");
         // System.out.println("Calling userInfoDAO.selectUserInfo(null," + userid +"," + email + ")");
         resetData();
         userInfoDAO.selectUserInfo(webutil.getLogonid(), null, null, userData);
      }
      catch (Exception ex) {

      }
   }

   public String save(String info)
   {
      try
      {
         if (info.toUpperCase().startsWith("Q")) {
            userInfoDAO.updateSecurityQuestions(userData);
         }
         if (info.toUpperCase().startsWith("S")) {
            if (password != null && ! password.isEmpty()) {
               String msg = webutil.validateNewPass(password, confirmPassword);
               if (msg.toUpperCase().equals("SUCCESS")) {
                  String passwordEncrypted = MsgDigester.getMessageDigest(password);
                  userData.setPasswordEncrypted(passwordEncrypted);
                  userInfoDAO.updateUserProfile(userData);
                  FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(Const.USERLOGON_ATTEMPTS, 0);
               }
               else {
                  FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg));
                  return "failed";
               }
            }
         }
         String success = "Successfully, updated";
         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, success, success));
         return "success";
      }
      catch (Exception ex)
      {
         String msg = ex.getMessage();
         String failed = "Failed, system error: " + msg;
         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, failed, failed));
         return "failed";
      }
   }

   public void onTabChange(TabChangeEvent event) {
      //FacesMessage msg = new FacesMessage("Tab Changed", "Active Tab: " + event.getTab().getTitle());
      //FacesContext.getCurrentInstance().addMessage(null, msg);
   }



}