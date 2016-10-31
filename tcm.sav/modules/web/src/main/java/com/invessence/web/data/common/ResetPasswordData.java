package com.invessence.web.data.common;

import javax.faces.bean.*;
import javax.faces.context.FacesContext;


import com.invessence.web.dao.common.UserInfoDAO;
import com.invessence.emailer.data.MsgData;
import com.invessence.web.util.*;
import com.invessence.web.constant.*;

@RequestScoped
public class ResetPasswordData
{

   private String emailID;
   private String resetCode;
   private String password;
   private String confirmPassword = null;

   private String question1 = null;
   private String answer1 = null;

/*
   @ManagedProperty("#{webutil}")
   private WebUtil webutil;
   public void setWebutil(WebUtil webutil)
   {
      this.webutil = webutil;
   }

   private UserInfoDAO userInfoDAO;

   public UserInfoDAO getUserInfoDAO()
   {
      return userInfoDAO;
   }

   public void setUserInfoDAO(UserInfoDAO userInfoDAO)
   {
      this.userInfoDAO = userInfoDAO;
   }

   public String getEmailID()
   {
      return emailID;
   }

   public void setEmailID(String emailID)
   {
      this.emailID = emailID;
   }

   public String getResetCode()
   {
      return resetCode;
   }

   public void setResetCode(String resetCode)
   {
      this.resetCode = resetCode;
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

   public String getQuestion1()
   {
      return question1;
   }

   public void setQuestion1(String question1)
   {
      this.question1 = question1;
   }

   public String getAnswer1()
   {
      return answer1;
   }

   public void setAnswer1(String answer1)
   {
      this.answer1 = answer1;
   }

   public void preRenderView()
   {

      if (!FacesContext.getCurrentInstance().isPostback())
      {
         int ind = 0;
         //int ind = userInfoDAO.checkReset(emailID, resetCode);

         if ((webutil.isNull(emailID)) || (webutil.isNull(resetCode)) || (ind != 1))
         {
            String msg = "Reset link is invalid or incomplete. Please try again.";


            try
            {
               FacesContext.getCurrentInstance().getExternalContext().redirect("message.xhtml?message=" + msg);
            }
            catch (Exception e)
            {
               e.printStackTrace();
            }
         }
      }
   }

   public String set()
   {
      String passwordEncrypted = MsgDigester.getMessageDigest(password);

      userInfoDAO.resetPassword(emailID, passwordEncrypted);

      String msg = "password.set.success";
      return "message.xhtml?faces-redirect=true&message=" + msg;
   }

   public String forgotPassword()
   {
      try {
         String check = null;
         // check = userInfoDAO.checkEmailID(emailID);
         if (check == null || check.length() == 0) {
            return "message.xhtml?faces-redirect=true&message=User not found";
         }
         else {
            Integer myResetID = webutil.randomGenerator(0,347896);
            setResetCode(myResetID.toString());
            return validateSecurityQuestions();
         }
      }
      catch (Exception ex) {
         return "message.xhtml?faces-redirect=true&type=E&message=Error when attempting to reset.";
      }
   }

   public String validateSecurityQuestions()
   {

      // Save ResetID
      String logonID = get
      userInfoDAO.updResetID(, getResetCode());

      //String websiteName = messageSource.getMessage("website.name", new Object[]{}, null);
      MsgData data = new MsgData();
      //data.setLogonID(loginID);
      data.setSource("User");
      data.setSender(Const.MAIL_SENDER);
      data.setReceiver(getEmailID());
      data.setSubject(Const.COMPANY_NAME + ": Forgot Password");
      //data.setMsg(MsgConst.getSignupMsg(userData));

      System.out.println("MIME TYPE :" + userInfoDAO.checkMimeType(getEmailID()));
      if (webutil.getMessageText() == null)
      {
         return "message.xhtml?faces-redirect=true&type=E&&message=Email process is down";
      }

      String websiteUrl = webutil.getMessageText().buildInternalMessage("website.url", new Object[]{});
      String name = "User";
      data.setMimeType(userInfoDAO.checkMimeType(getEmailID()));
      String msg = webutil.getMessageText().buildMessage(userInfoDAO.checkMimeType(getEmailID()), "password.reset.email.template", "password.reset.email", new Object[]{websiteUrl, getEmailID(), getResetCode().toString()});

      data.setMsg(msg);
      webutil.getMessageText().writeMessage(name,data);

      String resetMsg = "password.reset";
      return "message.xhtml?faces-redirect=true&message=" + resetMsg;

   }
*/

}
