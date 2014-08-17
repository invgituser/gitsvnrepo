package com.invessence.bean;

import java.util.*;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import com.invessence.constant.*;
import com.invessence.dao.AccountDAO;
import com.invessence.data.*;
import com.invessence.util.*;

public class UserBean extends UserData
{

   private Util utl = new Util();
   private String beanUserID;
   private String beanEmail;
   private String beanPasswd;

   private USMaps usstates = USMaps.getInstance();
   private String[] uscountry;


   private EmailMessage messageText;
   private AccountDAO accountDAO;

   public EmailMessage getMessageText()
   {
      return messageText;
   }

   public String getBeanUserID()
   {
      return beanUserID;
   }

   public void setBeanUserID(String beanUserID)
   {
      this.beanUserID = beanUserID;
   }

   public String getBeanEmail()
   {
      return beanEmail;
   }

   public void setBeanEmail(String beanEmail)
   {
      this.beanEmail = beanEmail;
   }

   public String getBeanPasswd()
   {
      return beanPasswd;
   }

   public void setBeanPasswd(String beanPasswd)
   {
      this.beanPasswd = beanPasswd;
   }

   public void setMessageText(EmailMessage messageText)
   {
      this.messageText = messageText;
   }

   public Map<String, String> getUsstates()
   {
      return usstates.getStates();
   }

   public AccountDAO getAccountDAO()
   {
      return accountDAO;
   }

   public void setAccountDAO(AccountDAO accountDAO)
   {
      this.accountDAO = accountDAO;
   }

   public String signUp2()
   {
      MsgData data = new MsgData();
      //String websiteUrl = messageSource.getMessage("website.url", new Object[]{}, null);

      System.out.println("Registered by: " + getFullName() + ", EmailID: " + getBeanEmail());
      try
      {
         if (messageText == null)
         {
            System.out.println("Email alert system is down!!!!!!");
            FacesContext.getCurrentInstance().getExternalContext().redirect("/message.xhtml?message=System error:  Error code (signup failure)");
            return "Error";
         }

         // We are using the First Name, Last Name from UserData as entered.
         setUserID(getBeanEmail());
         setEmail(getBeanEmail());
         setEmailID(getBeanEmail());
         String rndmPassword = PasswordGenerator.getSecCode();
         String tmpCode = MsgDigester.getMessageDigest(rndmPassword);
         String myIP = utl.getClientIpAddr((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest());
         Integer myResetID = utl.randomGenerator(0, 347896);
         String img = "123";
         Map<String, String> cookieInfo = new HashMap<String, String>();
         cookieInfo.put("img", img);
         //utl.setCookie(Const.COMPANY_NAME,"image",cookieInfo);
         setIp(myIP);
         setCookieID(img);
         setResetID(myResetID);
         setLogonstatus("T");
         setSecCode(tmpCode);
         setPassword(tmpCode);
         // secCode = "Default123";
         String supportInfo = messageText.getMessagetext("secure.url", new Object[]{});

         // Save data to database....
         long loginID = accountDAO.addUserInfo(getInstance());

         // Now send email support.
         data.setSource("User");  // This is set to User to it insert into appropriate table.
         data.setSender(Const.MAIL_SENDER);
         data.setReceiver(getEmailID());
         data.setSubject(Const.COMPANY_NAME + " - Successfully registered");
         String secureUrl = messageText.getMessagetext("secure.url", new Object[]{});
         String name = getFirstName() + " " + getLastName();

         String msg = messageText.getMessagetext("signup.email",
                                                 new Object[]{name, getUserID(), secureUrl
                                                    , getEmailID(), getResetID().toString(), supportInfo});

         data.setMsg(msg);
         messageText.writeMessage("signup", data);

         FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(Const.LOGONID_PARAM, loginID);
         FacesContext.getCurrentInstance().getExternalContext().redirect("/signupApproval.xhtml");
         return "success";

      }
      catch (Exception ex)
      {
         String stackTrace = ex.getMessage();
         data.setMsg(messageText.getMessagetext("signup.failure", new Object[]{stackTrace}));
         messageText.writeMessage("Error", data);
         return "failed";
      }
   }

   public String tryus()
   {
      MsgData data = new MsgData();
      //String websiteUrl = messageSource.getMessage("website.url", new Object[]{}, null);

      System.out.println("Demo by: " + getFullName() + ", EmailID: " + getBeanEmail());
      try
      {
         /*  // For Demo if the email is down, then don't worry.  Let the user continue to test.  They can reset the password later.
         if (messageText == null)
         {
            System.out.println("Email alert system is down!!!!!!");
            FacesContext.getCurrentInstance().getExternalContext().redirect("/message.xhtml?message=System error:  Error code (signup failure)");
            return "Error";
         }
         */

         // We are using the First Name, Last Name from UserData as entered.
         setUserID(getBeanEmail());
         setEmail(getBeanEmail());
         setEmailID(getBeanEmail());
         String rndmPassword = PasswordGenerator.getSecCode();
         String tmpCode = MsgDigester.getMessageDigest(rndmPassword);
         String myIP = utl.getClientIpAddr((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest());
         Integer myResetID = utl.randomGenerator(0, 347896);
         String img = "123";
         Map<String, String> cookieInfo = new HashMap<String, String>();
         cookieInfo.put("img", img);
         //utl.setCookie(Const.COMPANY_NAME,"image",cookieInfo);
         setIp(myIP);
         setCookieID(img);
         setResetID(myResetID);
         setLogonstatus("T");
         setSecCode(tmpCode);
         setPassword(tmpCode);
         // secCode = "Default123";


         String name = getFirstName() + " " + getLastName();
         // Save data to database....
         long loginID = accountDAO.addUserInfo(getInstance());

         Boolean sendEmail = false;
         String redirectTo = null;
         String msg;
         if (loginID <= -90)
         {
            sendEmail = true;
            msg = "demouser.id.taken";
            redirectTo = "message.xhtml?message=" + msg;
         }
         else if (loginID <= 0)
         {
            redirectTo = null;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "This email is already registered.  Use Login to view account.", null));
            return "failed";
         }
         else
         {
            sendEmail=true;
            msg = "demouser.id.taken";
            redirectTo = "/createInvestment.xhtml";
         }

         if (sendEmail) {
            // Now send email support.
            if (messageText != null)
            {
               String supportInfo = messageText.getMessagetext("support.info", new Object[]{});
               String secureUrl = messageText.getMessagetext("secure.url", new Object[]{});
               data.setSource("User");  // This is set to User to it insert into appropriate table.
               data.setSender(Const.MAIL_SENDER);
               data.setReceiver(getEmailID());
               data.setSubject(Const.COMPANY_NAME + " - Successfully registered");
               String emailmsg = messageText.getMessagetext("signup.email",
                                                       new Object[]{name, getUserID(), secureUrl
                                                          , getEmailID(), getResetID().toString(), supportInfo});

               data.setMsg(emailmsg);
               messageText.writeMessage("User", data);
            }
         }

         if (redirectTo != null) {
            if (loginID > 0)
               FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(Const.LOGONID_PARAM, loginID);
            FacesContext.getCurrentInstance().getExternalContext().redirect(redirectTo);
            return "success";
         }
         else
            return "failed";

      }
      catch (Exception ex)
      {
         String stackTrace = ex.getMessage();
         if (messageText != null)
         {
            String msg = "Demo failed for ( " + getEmailID() + "), " + stackTrace;
            data.setMsg(messageText.getMessagetext("signup.failure", new Object[]{msg}));
            messageText.writeMessage("Error", data);
         }
         System.out.println("Demo failed: " + getFullName());
         System.out.println("Messsage:" + stackTrace);
         ex.printStackTrace();

         return "failed";
      }
   }

}