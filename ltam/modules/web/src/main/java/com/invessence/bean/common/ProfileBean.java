package com.invessence.bean.common;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;

import com.invessence.constant.Const;
import com.invessence.converter.SQLData;
import com.invessence.dao.common.UserInfoDAO;
import com.invessence.data.common.*;
import com.invessence.service.gemini.UserProfile;
import com.invessence.util.*;
import com.invessence.ws.bean.WSCallStatus;
import com.invessence.ws.service.ServiceLayer;
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

   @ManagedProperty("#{serviceLayer}")
   private ServiceLayer serviceLayer;
   public void setServiceLayer(ServiceLayer serviceLayer)
   {
      this.serviceLayer = serviceLayer;
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
      String msg;
      Boolean saved;
      try
      {
         ServiceResult serviceresult = null;
         UserProfile serviceuserprfile = new UserProfile();
         if (info.toUpperCase().startsWith("Q")) {
            userInfoDAO.updateSecurityQuestions(userData);
            String success = "Questions/Answers Successfully updated";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, success, success));
            return "success";
         }

         if (info.toUpperCase().startsWith("S")) {
            Boolean change = true;
            if (password == null && userData.getEmail().equalsIgnoreCase(userData.getNewemail())) {
               String success = "No changes made!";
               FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, success, success));
               return "failed";
            }

            if (change) {
               Boolean changepwd = true;
               String passwordEncrypted = null;
               Boolean serviceflag = false;
               if (! password.isEmpty()) {
                  msg = webutil.validateNewPass(password, confirmPassword);
                  changepwd =  msg.toUpperCase().equals("SUCCESS");
                  passwordEncrypted = MsgDigester.getMessageDigest(password);
               }
               if (changepwd) {
                  userData.setPasswordEncrypted(passwordEncrypted);
                  serviceflag = userData.getAccess().equalsIgnoreCase("user");

                  /* DO these steps only if it is a USER and email address is changed */
                  /* Start WEB-Service */
                  if (serviceflag) {
                     if (! userData.getNewemail().equalsIgnoreCase(userData.getEmail())) {
                        serviceresult = serviceUpdateEmail(webutil.getLogonid(), userData.getEmail(), userData.getNewemail());
                        if (! serviceresult.getStatus()) {
                           if (serviceresult.getMessage() == null) {
                             msg="Error, when attempting to update Web-Services.";
                           }
                           else {
                              msg = serviceresult.getMessage();
                           }
                           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg));
                           return "failed";
                        }
                     }
                  }
                  /* End WEB-Service  */

                  /* If either userid or password is changed, then update database */
                  userInfoDAO.updateUserProfile(userData);
                  FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(Const.USERLOGON_ATTEMPTS, 0);
                  FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Success", "Success"));
               }
            }
         }
         String success = "No changes made!";
         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, success, success));
         return "failed";
      }
      catch (Exception ex)
      {
         msg = ex.getMessage();
         String failed = "Failed, system error: " + msg;
         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, failed, failed));
         return "failed";
      }
   }

   public void onTabChange(TabChangeEvent event) {
      //FacesMessage msg = new FacesMessage("Tab Changed", "Active Tab: " + event.getTab().getTitle());
      //FacesContext.getCurrentInstance().addMessage(null, msg);
   }

   public ServiceResult serviceUpdateEmail(Long logonid, String origemail, String newemail) {
      ServiceResult serviceResult = null;
      try {
         if (newemail == null || newemail.isEmpty()) {
            serviceResult = new ServiceResult(false, "Unchanged. No, new email address");
         }

         List<AccountData> listofAccount = null;
         WSCallStatus status;
         listofAccount = userInfoDAO.getListofAccount(logonid, null);

         if (listofAccount != null && listofAccount.size() > 0) {
            serviceResult = new ServiceResult(true, null);
            for (AccountData data: listofAccount) {
               if (data.getClientAccountID() != null) {
                  if (data.getPrivileges().equalsIgnoreCase("A")) {
                     status = serviceLayer.updateEmail(data.getClientAccountID(), newemail);
                     if (status == null || status.getErrorCode() != 0) {
                        serviceResult.setStatus(false);
                        serviceResult.setMessage(((status.getErrorMessage() != null) ? status.getErrorMessage() : "")) ;
                        break;
                     }
                  }
               }
            }
         }
      }
      catch (Exception ex) {
         serviceResult = new ServiceResult(false, ex.getMessage());
      }
      return serviceResult;
   }




}