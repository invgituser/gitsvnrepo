package com.invessence.data;

import java.util.*;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;



import com.invessence.util.*;
import com.invessence.dao.*;
import com.invessence.constant.*;

@RequestScoped
public class ResetPasswordData
{

   private Util utl = new Util();
   private String emailID;
   private String resetCode;
   private String password;
   private String confirmPassword = null;

   private String question1 = null;
   private String answer1 = null;

   private EmailMessage messageText;

   private AccountDAO accountDAO;

   public EmailMessage getMessageText()
   {
      return messageText;
   }

   public void setMessageText(EmailMessage messageText)
   {
      this.messageText = messageText;
   }

   public AccountDAO getAccountDAO()
   {
      return accountDAO;
   }

   public void setAccountDAO(AccountDAO accountDAO)
   {
      this.accountDAO = accountDAO;
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
         int ind = accountDAO.checkReset(emailID, resetCode);

         if ((Util.isNull(emailID)) || (Util.isNull(resetCode)) || (ind != 1))
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

      accountDAO.resetPassword(emailID, passwordEncrypted);

      String msg = "password.set.success";
      return "message.xhtml?faces-redirect=true&message=" + msg;
   }

   public String forgotPassword()
   {
      try {
         String check;
         check = accountDAO.checkEmailID(emailID);
         if (check == null || check.length() == 0) {
            return "message.xhtml?faces-redirect=true&message=User not found";
         }
         else {
            Integer myResetID = utl.randomGenerator(0,347896);
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
      accountDAO.updResetID(getEmailID(), getResetCode());

      //String websiteName = messageSource.getMessage("website.name", new Object[]{}, null);
      MsgData data = new MsgData();
      //data.setLogonID(loginID);
      data.setSource("User");
      data.setSender(Const.MAIL_SENDER);
      data.setReceiver(getEmailID());
      data.setSubject(Const.COMPANY_NAME + ": Forgot Password");
      //data.setMsg(MsgConst.getSignupMsg(userData));

      if (messageText == null)
      {
         return "message.xhtml?faces-redirect=true&type=E&&message=Email process is down";
      }

      String websiteUrl = messageText.getMessagetext("website.url", new Object[]{});
      String name = "User";

      String msg = messageText.getMessagetext("password.reset.email",
                                              new Object[]{websiteUrl, getEmailID(), getResetCode().toString()});

      data.setMsg(msg);
      messageText.writeMessage(name,data);

      String resetMsg = "password.reset";
      return "message.xhtml?faces-redirect=true&message=" + resetMsg;

   }

}
