package com.invessence.web.bean.common;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;

import com.invessence.converter.SQLData;
import com.invessence.web.dao.common.UserInfoDAO;
import com.invessence.web.data.common.UserData;
import com.invessence.web.util.*;
import org.primefaces.event.TabChangeEvent;

@ManagedBean(name = "profileBean")
@SessionScoped
public class ProfileBean
{

   private Long beanlogonidID;
   private UserData userData = new UserData();

   @ManagedProperty("#{userInfoDAO}")
   private UserInfoDAO userInfoDAO;

   @ManagedProperty("#{webMessage}")
   private WebMessage messageText;

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

   public void setMessageText(WebMessage messageText)
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

   public void loadData(Long acctnum) {

      try {
         userData = userInfoDAO.selectUserInfo(webutil.getLogonid());
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
            if (userData.getPassword() != null && ! userData.getPassword().isEmpty()) {
               String msg = webutil.validateNewPass(userData.getPassword(), userData.getConfirmNewPassword());
               if (msg.toUpperCase().equals("SUCCESS")) {
                  String passwordEncrypted = MsgDigester.getMessageDigest(userData.getPassword());
                  userData.setPassword(passwordEncrypted);
               }
               else {
                  FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg));
                  return "failed";
               }
            }
            String updtmsg = userInfoDAO.updateUserProfile(userData);
            if (updtmsg != null && ! updtmsg.isEmpty()) {
               FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, updtmsg, updtmsg));
               return "failed";
            }
         }
         String success = "Successfully, updated";
         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, success, success));
         return "success";
      }
      catch (Exception ex)
      {
         String failed = "Failed, system error";
         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, failed, failed));
         return "failed";
      }
   }

   public void onTabChange(TabChangeEvent event) {
      //FacesMessage msg = new FacesMessage("Tab Changed", "Active Tab: " + event.getTab().getTitle());
      //FacesContext.getCurrentInstance().addMessage(null, msg);
   }



}