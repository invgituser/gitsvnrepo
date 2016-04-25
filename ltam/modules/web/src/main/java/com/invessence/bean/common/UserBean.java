package com.invessence.bean.common;

import java.io.Serializable;
import java.util.*;
import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import com.invessence.constant.*;
import com.invessence.dao.common.UserInfoDAO;
import com.invessence.data.*;
import com.invessence.data.common.UserData;
import com.invessence.util.*;
import org.apache.commons.logging.*;

@ManagedBean(name = "userBean")
@SessionScoped
public class UserBean extends UserData implements Serializable
{
   protected final Log logger = LogFactory.getLog(getClass());
   private String beanUserID, beanResetID, beanEmail;
   private String beanCustID;

   private String pwd1, pwd2;
   private String beanq1, beanq2, beanq3, beanans1, beanans2, beanans3;
   private Integer attempts;

   private SecurityQuestions securityQuestions;

   @ManagedProperty("#{webutil}")
   private WebUtil webutil;
   public void setWebutil(WebUtil webutil)
   {
      this.webutil = webutil;
   }

   @ManagedProperty("#{emailMessage}")
   private EmailMessage messageText;

   public void setMessageText(EmailMessage messageText)
   {
      this.messageText = messageText;
   }

   @ManagedProperty("#{userInfoDAO}")
   private UserInfoDAO userInfoDAO;

   public void setUserInfoDAO(UserInfoDAO userInfoDAO)
   {
      this.userInfoDAO = userInfoDAO;
   }

   @ManagedProperty("#{uiLayout}")
   private UILayout uiLayout;

   public void setUiLayout(UILayout uiLayout)
   {
      this.uiLayout = uiLayout;
   }

   private USMaps usstates = USMaps.getInstance();
   private String[] uscountry;

   public String getBeanUserID()
   {
      return beanUserID;
   }

   public void setBeanUserID(String beanUserID)
   {
      this.beanUserID = beanUserID;
   }

   public String getBeanResetID()
   {
      return beanResetID;
   }

   public void setBeanResetID(String beanResetID)
   {
      this.beanResetID = beanResetID;
   }

   public String getBeanEmail()
   {
      return beanEmail;
   }

   public void setBeanEmail(String beanEmail)
   {
      this.beanEmail = beanEmail;
   }

   public String getBeanCustID()
   {
      return beanCustID;
   }

   public void setBeanCustID(String beanCustID)
   {
      this.beanCustID = beanCustID;
   }

   public EmailMessage getMessageText()
   {
      return messageText;
   }

   public String getPwd1()
   {
      return pwd1;
   }

   public void setPwd1(String pwd1)
   {
      this.pwd1 = pwd1;
   }

   public String getPwd2()
   {
      return pwd2;
   }

   public void setPwd2(String pwd2)
   {
      this.pwd2 = pwd2;
   }

   public String getBeanq1()
   {
      return beanq1;
   }

   public void setBeanq1(String beanq1)
   {
      this.beanq1 = beanq1;
   }

   public String getBeanq2()
   {
      return beanq2;
   }

   public void setBeanq2(String beanq2)
   {
      this.beanq2 = beanq2;
   }

   public String getBeanq3()
   {
      return beanq3;
   }

   public void setBeanq3(String beanq3)
   {
      this.beanq3 = beanq3;
   }

   public String getBeanans1()
   {
      return beanans1;
   }

   public void setBeanans1(String beanans1)
   {
      this.beanans1 = beanans1;
   }

   public String getBeanans2()
   {
      return beanans2;
   }

   public void setBeanans2(String beanans2)
   {
      this.beanans2 = beanans2;
   }

   public String getBeanans3()
   {
      return beanans3;
   }

   public void setBeanans3(String beanans3)
   {
      this.beanans3 = beanans3;
   }

   public SecurityQuestions getSecurityQuestions()
   {
      return securityQuestions;
   }

   public Map<String, String> getUsstates()
   {
      return usstates.getStates();
   }

   private void resetBean() {
      pwd1 = null;
      pwd2 = null;
      beanq1 = null;
      beanq2 = null;
      beanq3 = null;
      beanans1 = null;
      beanans2 = null;
      beanans3 = null;
      attempts = 0;
      resetData();

   }

   public void collectClientData()
   {
     logger.debug("Calling userInfoDAO.getUserByEmail(" + beanEmail + ")");
     System.out.println("Info: Calling userInfoDAO.getUserByEmail(" + beanEmail + ")");
     userInfoDAO.getUserByEmail(beanEmail, getInstance());

   }

   public void collectUserLogon(String userid, String email)
   {
      logger.debug("Calling userInfoDAO.selectUserInfo(null," + userid +"," + email + ")");
      System.out.println("Calling userInfoDAO.selectUserInfo(null," + userid +"," + email + ")");
      userInfoDAO.selectUserInfo(null, userid, email, getInstance());
      logger.debug("After Calling userInfoDAO.selectUserInfo: UID=" + getUserID() + ", Email=" + getEmail() + ",Logon" + getLogonID() );
      System.out.println("After Calling userInfoDAO.selectUserInfo: UID=" + getUserID() + ", Email=" + getEmail() + ",Logon" + getLogonID() );
   }

   public void preRenderResetUser()
   {
      String msg;
      try
      {
         if (!FacesContext.getCurrentInstance().isPostback())
         {
            resetBean();
            logger.debug("Calling preRenderResetUser, UserID = " + beanUserID);
            if (beanUserID != null && beanResetID != null)
            {
               if (!beanUserID.isEmpty() && !beanResetID.isEmpty())
               {
                  collectUserLogon(beanUserID, null);
                  System.out.println("Password Reset ID: " + getEmail() + ", WebResetID=" + beanResetID + ", DBResetID=" + getResetID() );
                  if (!beanResetID.equals(getResetID().toString()))
                  {
                     webutil.redirecttoMessagePage("ERROR", "Invalid link", "Sorry, this link contains invalid Reset data.");
                  }
                  setRandomQuestion();
               }
               else {
                  webutil.redirecttoMessagePage("ERROR", "Invalid link", "Sorry, this link contains invalid User data.");
               }
            }
         }
      }
      catch (Exception ex)
      {
         webutil.redirecttoMessagePage("ERROR", "Invalid link", "Sorry, this link contains invalid data.  Exception raised:" + ex.getMessage());
      }
   }

   public void preRenderActivateUser()
   {
      String msg;
      try
      {
         if (!FacesContext.getCurrentInstance().isPostback())
         {
            if (beanUserID == null || beanUserID.isEmpty()) {
               System.out.println("LOG: During activation section: " + beanUserID + " is missing!");
               webutil.redirecttoMessagePage("ERROR", "Invalid link", "Sorry, this link contains invalid reset data.");
               return;
            }
            resetBean();
            logger.debug("LOG: fetch data for, UserID = " + beanUserID + ", Reset " + beanResetID);
            collectUserLogon(beanUserID, null);
            // Due to multiple access, it seems that ResetID is blank
            System.out.println("LOG: Activate User: " + getEmail() + ", WebResetID = " + beanResetID + ", DBResetID=" + getResetID() );
            if (getUserID() != null && ! getUserID().isEmpty()) // Found the user
            {
               if (beanResetID != null && ! beanResetID.isEmpty()) {
                  if (getResetID() != null) {
                     if (!beanResetID.equals(getResetID().toString()))
                     {
                        System.out.println("LOG: Show Invalid Data: " + beanUserID + ", WebResetID = " + beanResetID + ", DBResetID=" + getResetID() );
                        webutil.redirecttoMessagePage("ERROR", "Invalid link", "Sorry, this link contains invalid reset data.");
                     }
                  }
               }
            }
            userInfoDAO.updLogonStatus(beanUserID);
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(Const.USERLOGON_ATTEMPTS, 0);
         }
      }
      catch (Exception ex)
      {
         webutil.redirecttoMessagePage("ERROR", "Invalid link", "Sorry, this link contains invalid data. Call Support:");
         System.out.println("ERROR: Exception raised when attempting to activate account");
         ex.printStackTrace();
      }
   }

   // This method is used during Pre-signup process.  Validate the Email.
   public void preRenderSignup()
   {
      String msg;

      try
      {
         if (!FacesContext.getCurrentInstance().isPostback())
         {
            beanUserID = null;
            beanResetID = null;
            resetBean();
            if (beanEmail == null || beanEmail.isEmpty()) {
               webutil.redirecttoMessagePage("WARN", "Cannot Signup", "Attempting to register on site, but valid email address is not provided.");
            }
            collectClientData();
            if (getUserID() != null && !getUserID().isEmpty())
            {
               System.out.println("Info: UserID is already registered: " + getUserID() );
               webutil.redirecttoMessagePage("ERROR", "Invalid link", "Sorry, you are attempting to sign-up for account that is already registered.  Either, follow the instruction to activate the account or use forgot password to reset your access.");
            }
            if (getEmail() == null || getEmail().isEmpty())  // If no email in the system, then warn
            { // Userid is UserData.
               System.out.println("Info: Email is not found as valid registered: " + getEmail() );
               webutil.redirecttoMessagePage("WARN", "Cannot Signup", "Sorry, you are attempting to activate account, but you have to be invited.  If you received this email, please click on the email invitation link.");
            }
            System.out.println("Info: Start registration process for: " + getEmail() );
         }
      }
      catch (Exception ex)
      {
         webutil.redirecttoMessagePage("ERROR", "Invalid link", "Sorry, you are attempting to activate account, but the link contains invalid data. Call Support" );
         System.out.println("LOG: Signup Issue for: " + beanEmail);
         ex.printStackTrace();
      }
   }

   public void gotoSignPage2()
   {
      try {
         Integer status = userInfoDAO.validateUserID(beanUserID);
         if (status != 0) {
            System.out.println("LOG: Validate UserID failed: " + beanUserID + " with status = " + status.toString());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "UserID taken", "Try different UserID, this one is taken."));
         }
         else {
            // Check if the username/password match.  Cannot change the Email Address.
            String msg = webutil.validateNewPass(pwd1, pwd2);
            if (!msg.toUpperCase().equals("SUCCESS"))
            {
               System.out.println("LOG: Validate Password failed: " + beanUserID + " with status = " + msg);
               FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg));
               return;
            }
            System.out.println("LOG: UserID and password test successful: " + beanUserID + " email = " + beanEmail);
            webutil.redirect("/signup2.xhtml", null);
         }

      }
      catch (Exception ex) {
         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "UserID issue", "Contact Support"));
         System.out.println("LOG: Signup Issue for Page 2: " + beanEmail);
         ex.printStackTrace();
      }
   }

   public void resetPassword() {

      String msg;
      Boolean validUser = true;  //  Assume that all is fine.
      if (beanans1 == null) {
         msg="Answer to this question is required.";
         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg));
         validUser = false;
      }
      else if (! beanans1.equals(getRandomAns())) {
            msg="Answer does not match, lets try again.";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg));
            setRandomQuestion();
            attempts++;
            validUser = false;
      } else {
            msg = webutil.validateNewPass(pwd1, pwd2);
            if (!msg.toUpperCase().equals("SUCCESS"))
            {
               FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg));
               validUser = false;
               return;
            }
      }

      if (! validUser) {
         if (attempts > 2) {
            webutil.redirecttoMessagePage("ERROR", "Too Many attempts.", "Sorry, cannot reset your information.  Your credentials don't match.");
         }
      }
      else {
         String passwordEncrypted = MsgDigester.getMessageDigest(pwd1);
         userInfoDAO.resetPassword(beanUserID, passwordEncrypted);
         FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(Const.USERLOGON_ATTEMPTS, 0);
         webutil.redirect("/approved.xhtml", null);
      }
   }

   public void saveUser()
   {
      if (beanUserID == null || beanUserID.length() < 5)
      {
         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid UserID", "Invalid UserID"));
      }
      MsgData data = new MsgData();
      //String websiteUrl = messageSource.getMessage("website.url", new Object[]{}, null);

      logger.debug("Attempting to Register: " + beanEmail + ", UserID: " + beanUserID);
      System.out.println("LOG: Saving data for registeration: " + beanEmail + ", UserID: " + beanUserID);
      try
      {
         if (messageText == null)
         {
            logger.debug("Email alert system is down!!!!!!");
            System.out.println("LOG: Email alert system is down!!!!!!");
            FacesContext.getCurrentInstance().getExternalContext().redirect("/message.xhtml?message=System error:  Error code (signup failure)");
            return;
         }

         // We are using the First Name, Last Name from UserData as entered.
         String passwordEncrypted = MsgDigester.getMessageDigest(pwd1);
         String myIP = webutil.getClientIpAddr((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest());
         Integer myResetID = webutil.randomGenerator(0, 347896);
         //Map<String, String> cookieInfo = new HashMap<String, String>();
         //cookieInfo.put("img", img);
         //utl.setCookie(Const.COMPANY_NAME,"image",cookieInfo);

         String emailMsgType = "HTML";
         setLogonID(0L);
         setLogonstatus("T");
         setEmail(beanEmail);
         setUserID(beanUserID);
         setPasswordEncrypted(passwordEncrypted);
         setQ1(beanq1);
         setQ2(beanq2);
         setQ3(beanq3);
         setAns1(beanans1);
         setAns2(beanans2);
         setAns3(beanans3);
         setIp(myIP);
         setResetID(myResetID);
         setEmailmsgtype(emailMsgType);
         // Save data to database....
         long loginID = userInfoDAO.addUserInfo(getInstance());
         logger.debug("After addUserInfo got " + loginID);

         if (loginID < 0L)
         {
            System.out.println("ERROR: Had issue with this userid when attempting to save: " + loginID);
            String msg = "There was some error when attempting to save this userid.  Please reach out to support desk.";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg));
            return;
         }
         else
         {
            // Now send email support.
            System.out.println("LOG: User save, logonID: " + loginID);
            System.out.println("LOG: Sending Activation email to: " + beanEmail);
            data.setSource("User");  // This is set to User to it insert into appropriate table.
            data.setSender(Const.MAIL_SENDER);
            data.setReceiver(beanEmail);
            data.setSubject("Successfully registered");
            String secureUrl = uiLayout.getUiprofile().getSecurehomepage();
            String name = getFullName();

            String msg = messageText.buildMessage(emailMsgType, "html.activate.email", "txt.activate.email", new Object[]{secureUrl, beanUserID, myResetID.toString()});
            data.setMsg(msg);

            messageText.writeMessage("signup", data);

            webutil.redirect("/signup3.xhtml", null);
         }

      }
      catch (Exception ex)
      {
         logger.debug("Exception " + ex.getMessage());
         webutil.redirecttoMessagePage("ERROR", "Failed Signup", "Sorry, there was an issue with signup.  Please call support.");
         webutil.alertSupport("signup", "Signup -" + beanEmail, "Registration Error", null);
         System.out.println("Error: Attempting to save UserID, for: " + beanEmail);
         ex.printStackTrace();
      }
   }

   public void setRandomQuestion() {
      Integer qnum = webutil.randomGenerator(1,3);
      switch (qnum) {
         case 0:
         case 1:
            if (getQ1() != null && getAns1() != null) {
               setRandomQ(getQ1());
               setRandomAns(getAns1());
               break;
            }
         case 2:
            if (getQ2() != null && getAns2() != null) {
               setRandomQ(getQ2());
               setRandomAns(getAns2());
               break;
            }

         case 3:
            if (getQ3() != null && getAns3() != null) {
               setRandomQ(getQ3());
               setRandomAns(getAns3());
               break;
            }
         default:
            if (getRandomQ() == null) {
               Integer num1 = webutil.randomGenerator(1,9);
               Integer num2 = webutil.randomGenerator(1,9);
               Integer sum = num1 + num2;
               setRandomQ("What is sum of " + num1.toString() + " + " + num2.toString());
               setRandomAns(sum.toString());
            }
            break;
      }
   }

   public void emailResetInfo() {
      if (beanEmail == null) {
         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Email is required", "Email is required"));
      }
      collectUserLogon(null, beanEmail);
      System.out.println("LOG: After call collectUserLogon: " + getLogonID().toString());


      if (getLogonID() != null && getLogonID() > 0L) {
         // Now send email support.

         System.out.println("LOG: Sending reset instructions:");
         Integer myResetID = webutil.randomGenerator(0, 347896);
         userInfoDAO.updResetID(getUserID(), myResetID.toString());

         MsgData data = new MsgData();
         data.setSource("User");  // This is set to User to it insert into appropriate table.
         data.setSender(Const.MAIL_SENDER);
         data.setReceiver(beanEmail);
         data.setSubject("Reset Instructions");
         String secureUrl = uiLayout.getUiprofile().getSecurehomepage();

         String name = getFullName();

         // System.out.println("MIME Type :" + getEmailmsgtype());
         String msg = messageText.buildMessage(data.getMimeType(), "html.password.reset.email", "txt.password.reset.email", new Object[]{secureUrl, getUserID(), myResetID.toString()});
         data.setMsg(msg);

         messageText.writeMessage("reset", data);

         String displayMessage = messageText.buildInternalMessage("txt.reset.info",null);
         webutil.redirecttoMessagePage("INFO", "Reset Instructions sent", displayMessage);
         System.out.println("LOG: Email Sent, succcesfully: " + beanEmail);
      }
      else {
         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "This email is not valid. Not a registered user", "This email is not valid. Not a registered user"));
         System.out.println("ERROR: Attempting to reset, but valid email: " + beanEmail);
      }
   }

}