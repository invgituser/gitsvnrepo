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
   private Long beanLogonID;

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
     // System.out.println("Info: Calling userInfoDAO.getUserByEmail(" + beanEmail + ")");
     userInfoDAO.getUserByEmail(beanEmail, getInstance());

   }

   public void collectUserLogon(String userid, String email)
   {
      userInfoDAO.selectUserInfo(null, userid, email, getInstance());
   }

   public void preRenderResetUser()
   {
      String msg;
      try
      {
         if (!FacesContext.getCurrentInstance().isPostback())
         {
            resetBean();
            logger.debug("LOG: Reset UserID = " + beanUserID);
            if (beanUserID != null && beanResetID != null)
            {
               if (!beanUserID.isEmpty() && !beanResetID.isEmpty())
               {
                  collectUserLogon(beanUserID, null);
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
         logger.debug("LOG: Exception = " + ex.getMessage());
         logger.debug("Message", ex);
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
            logger.debug("LOG: Activate UserID = " + beanUserID);
            if (beanUserID == null || beanUserID.isEmpty()) {
               logger.debug("Info: During activation section: " + beanUserID + " is missing!");
               webutil.redirecttoMessagePage("ERROR", "Invalid link", "Sorry, this link contains invalid reset data.");
               return;
            }
            resetBean();
            logger.debug("LOG: fetch data for, UserID = " + beanUserID + ", Reset " + beanResetID);
            collectUserLogon(beanUserID, null);
            // Due to multiple access, it seems that ResetID is blank
            if (getUserID() != null && ! getUserID().isEmpty()) // Found the user
            {
               if (beanResetID != null && ! beanResetID.isEmpty()) {
                  if (getResetID() != null) {
                     if (!beanResetID.equals(getResetID().toString()))
                     {
                        logger.debug("LOG: Invalid ResetID: " + beanUserID + ", got-> " + beanResetID + ", Expected->" + getResetID() );
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
         logger.debug("ERROR: Exception: " + ex.getMessage());
         logger.debug("Message", ex);
         webutil.redirecttoMessagePage("ERROR", "Invalid link", "Sorry, this link contains invalid data. Call Support:");
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
               logger.debug("WARN, Cannot Signup, Attempting to register on site, but NO email address is included.");
               webutil.redirecttoMessagePage("WARN", "Cannot Signup", "Attempting to register on site, but valid email address is not provided.");
            }
            collectClientData();
            if (getUserID() != null && !getUserID().isEmpty())
            {
               logger.debug("Info: Email is already registered: " + beanEmail + " with userid: " + getUserID() );
               webutil.redirecttoMessagePage("ERROR", "Invalid link", "Sorry, you are attempting to sign-up for account that is already registered.  Either, follow the instruction to activate the account or use forgot password to reset your access.");
            }
            if (getEmail() == null || getEmail().isEmpty())  // If no email in the system, then warn
            { // Userid is UserData.
               logger.debug("Info: Email is not found as valid registered: " + getEmail() );
               webutil.redirecttoMessagePage("WARN", "Cannot Signup", "Sorry, you are attempting to activate account, but you have to be invited.  If you received this email, please click on the email invitation link.");
            }
            logger.info("Info: Start registration process for: " + getEmail() );
         }
      }
      catch (Exception ex)
      {
         logger.debug("ERROR: Exception: " + ex.getMessage());
         logger.debug("Message", ex);
         webutil.redirecttoMessagePage("ERROR", "Invalid link", "Sorry, you are attempting to activate account, but the link contains invalid data. Call Support" );
      }
   }

   public void gotoSignPage2()
   {
      try {
         Integer status = userInfoDAO.validateUserID(beanUserID);
         if (status != 0) {
            logger.debug("LOG: Validate UserID failed: " + beanUserID + " with status = " + status.toString());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "UserID taken", "Try different UserID, this one is taken."));
         }
         else {
            // Check if the username/password match.  Cannot change the Email Address.
            String msg = webutil.validateNewPass(pwd1, pwd2);
            if (!msg.toUpperCase().equals("SUCCESS"))
            {
               logger.debug("LOG: Validate Password failed: " + beanUserID + " with status = " + msg);
               FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg));
               return;
            }
            logger.debug("Info: Intial Page of UserID/Password checks were successful for: " + beanEmail );
            logger.debug("Info: Start Question/Answer Section: " + beanUserID );
            logger.debug("LOG: UserID and password test successful: " + beanUserID + " email = " + beanEmail);
            beanLogonID = saveUser();
            if (beanLogonID > 0L) {
               setLogonID(beanLogonID);
               sendConfirmation();
               webutil.redirect("/signup2.xhtml", null);
            }
         }

      }
      catch (Exception ex) {
         logger.debug("ERROR: Exception: " + ex.getMessage());
         logger.debug("Message", ex);
         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "UserID issue", "Contact Support"));
      }
   }

   public void acceptQA() {
      try {

         saveQA();


         // If there is no error with QA save, then redirect to proper page.
         if (getAccess().equalsIgnoreCase("advisor")) {
            webutil.redirect("/signup4.xhtml", null);
         }
         else {
            webutil.redirect("/signup3.xhtml", null);
         }



      }
      catch (Exception ex) {
         logger.debug("ERROR: Exception: " + ex.getMessage());
         logger.debug("Message", ex);
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

   public Long saveUser()
   {
      if (beanUserID == null || beanUserID.length() < 5)
      {
         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid UserID", "Invalid UserID"));
      }
      //String websiteUrl = messageSource.getMessage("website.url", new Object[]{}, null);

      try
      {
         if (messageText == null)
         {
            logger.debug("Email alert system is down!!!!!!");
            System.out.println("LOG: Email alert system is down!!!!!!");
            FacesContext.getCurrentInstance().getExternalContext().redirect("/message.xhtml?message=System error:  Error code (signup failure)");
            return 0L;
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
         System.out.println("Info: Saving data for registeration: " + beanEmail + ", UserID: " + beanUserID);
         if (getAccess().equalsIgnoreCase("advisor")) {
            setLogonstatus("A");
         }
         else {
            setLogonstatus("T");
         }
         long loginID = userInfoDAO.addUserInfo(getInstance());

         if (loginID < 0L)
         {
            System.out.println("ERROR: Had issue with this userid when attempting to save: " + loginID);
            String msg = "There was some error when attempting to save this userid.  Please reach out to support desk.";
            webutil.redirecttoMessagePage("ERROR", "Failed Signup", "Had issue when attempting to save your credentials.  Please try again in 30 min..");
            webutil.alertSupport("signup", "Save -" + beanEmail, "Save Registration Error", null);
            return loginID;
         }

         return loginID;

      }
      catch (Exception ex)
      {
         logger.debug("Exception " + ex.getMessage());
         webutil.redirecttoMessagePage("ERROR", "Failed Signup", "Sorry, there was an issue with signup.  Please call support.");
         webutil.alertSupport("signup", "Signup -" + beanEmail, "Registration Error", null);
         System.out.println("Error: Attempting to save UserID, for: " + beanEmail);
         ex.printStackTrace();
      }
      return 0L;
   }

   public void saveQA() {
      try {
         System.out.println("Info: Save Question/Answer section: " + beanUserID );

         logger.debug("Attempting to Save Q/A: " + beanEmail + ", UserID: " + beanUserID);

         setQ1(beanq1);
         setQ2(beanq2);
         setQ3(beanq3);
         setAns1(beanans1);
         setAns2(beanans2);
         setAns3(beanans3);
         // Save data to database....
         System.out.println("Info: Saving data for registeration: " + beanEmail + ", UserID: " + beanUserID);

         userInfoDAO.updateSecurityQuestions(getInstance());  // If there is error, then exception is raised
      }
      catch (Exception ex) {
         logger.debug("Exception " + ex.getMessage());
         webutil.redirecttoMessagePage("ERROR", "Q/A Save", "It seems that there was an error attempting to save Questions/Ans. Section.  Your account has been saved.");
         webutil.alertSupport("signup", "Q/A Signup -" + beanEmail, "Q/A save Error", null);
         System.out.println("Error: Attempting to save Q/A for: " + beanEmail);
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
         System.out.println("Info: Email Sent, succcesfully: " + beanEmail);
      }
      else {
         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "This email is not valid. Not a registered user", "This email is not valid. Not a registered user"));
         System.out.println("ERROR: Attempting to reset, but valid email: " + beanEmail);
      }
   }

   public void sendConfirmation() {
      if (beanEmail == null) {
         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Session timed out.  Use Forgot password to get access to your account.", "System timed out"));
      }

      System.out.println("Info: Sending confirmation email: " + beanEmail);

      MsgData data = new MsgData();
      String emailMsgType = "HTML";
      data.setSource("User");  // This is set to User to it insert into appropriate table.
      data.setSender(Const.MAIL_SENDER);
      data.setReceiver(beanEmail);
      data.setSubject("Successfully registered");
      String secureUrl = uiLayout.getUiprofile().getSecurehomepage();
      String name = getFullName();

      if (getAccess().equalsIgnoreCase("Advisor")) {
         String msg = messageText.buildMessage(emailMsgType,
                                               "html.advisor_activate.email",
                                               "txt.advisor_activate.email",
                                               new Object[]{secureUrl, beanUserID, getResetID().toString()});
         data.setMsg(msg);
         messageText.writeMessage("custom", data);
         System.out.println("Info: Sending Advisor activation email: " + beanEmail);
      }
      else {
         String msg = messageText.buildMessage(emailMsgType,
                                               "html.activate.email",
                                               "txt.activate.email",
                                               new Object[]{secureUrl, beanUserID, getResetID().toString()});
         data.setMsg(msg);
         messageText.writeMessage("custom", data);
         System.out.println("Info: Sending Activation email to: " + beanEmail);
      }

   }


}