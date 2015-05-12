package com.invessence.bean.common;

import javax.annotation.PostConstruct;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;

import com.invessence.converter.SQLData;
import com.invessence.dao.common.UserInfoDAO;
import com.invessence.data.common.UserData;
import com.invessence.util.*;
import org.primefaces.event.TabChangeEvent;

@ManagedBean(name = "profileBean")
@SessionScoped
public class ProfileBean
{

   private String beanlogonidID;
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

   public String getBeanlogonidID()
   {
      return beanlogonidID;
   }

   public void setBeanlogonidID(String beanlogonidID)
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
            Long acctnum = converter.getLongData(beanlogonidID);
            if (acctnum != null && acctnum > 0L)
               loadData(acctnum);
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
         return "success";
      }
      catch (Exception ex)
      {
         return "failed";
      }
   }

   public void onTabChange(TabChangeEvent event) {
      //FacesMessage msg = new FacesMessage("Tab Changed", "Active Tab: " + event.getTab().getTitle());
      //FacesContext.getCurrentInstance().addMessage(null, msg);
   }



}