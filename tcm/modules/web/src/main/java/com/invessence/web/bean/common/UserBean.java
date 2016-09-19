package com.invessence.web.bean.common;

import java.io.Serializable;
import java.util.*;
import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import com.invessence.emailer.data.MsgData;
import com.invessence.util.MsgDigester;
import com.invessence.web.constant.*;
import com.invessence.web.dao.common.UserInfoDAO;
import com.invessence.web.data.common.UserData;
import com.invessence.web.util.*;
import org.apache.commons.logging.*;

@ManagedBean(name = "userBean")
@SessionScoped
public class UserBean implements Serializable
{
   protected final Log logger = LogFactory.getLog(getClass());
   private String beanUserID, beanResetID, beanEmail;
   private String beanCustID;
   private String beanLogonID;

   private String pwd1, pwd2;
   private String beanq1, beanq2, beanq3, beanans1, beanans2, beanans3;
   private Integer attempts;

   private SecurityQuestions securityQuestions;

   private UserData userdata;

   @ManagedProperty("#{webutil}")
   private WebUtil webutil;

   public void setWebutil(WebUtil webutil)
   {
      this.webutil = webutil;
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

   public UserBean()
   {
      userdata = new UserData();
   }

   public UserData getUserdata()
   {
      return userdata;
   }

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

   public Long getLongBeanLogonID()
   {
      if (beanLogonID == null)
         return null;

      return(Long.valueOf(beanLogonID));
   }

   public String getBeanLogonID()
   {
      return beanLogonID;
   }

   public void setBeanLogonID(String beanLogonID)
   {
      this.beanLogonID = beanLogonID;
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

   private void resetBean()
   {
      pwd1 = null;
      pwd2 = null;
      beanq1 = null;
      beanq2 = null;
      beanq3 = null;
      beanans1 = null;
      beanans2 = null;
      beanans3 = null;
      attempts = 0;
      userdata = new UserData();

   }

   /*
      public void collectClientData()
      {
        userdata = userInfoDAO.getUserByEmail(userdata);

      }
   */
   public void collectUserAccount()
   {
      Boolean found;
      found = userInfoDAO.selectUserInfo(userdata);
   }

   public void updateLogonStatus()
   {
      Boolean found;
      userInfoDAO.updLogonStatus(beanUserID);
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
                  collectUserAccount();
                  if (!beanResetID.equals(userdata.getResetID()))
                  {
                     webutil.redirecttoMessagePage("ERROR", "Invalid link", "Sorry, this link contains invalid Reset data.");
                  }
                  setRandomQuestion();
               }
               else
               {
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
         webutil.alertSupport("Exception", "UserBean.ResetUser", "Sorry, this link contains invalid data.", ex.getMessage());
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
            if (beanUserID == null || beanUserID.isEmpty())
            {
               logger.debug("Info: During activation section: " + beanUserID + " is missing!");
               webutil.redirecttoMessagePage("ERROR", "Invalid link", "Sorry, this link contains invalid reset data.");
               return;
            }
            resetBean();
            logger.debug("LOG: fetch data for, UserID = " + beanUserID + ", Reset " + beanResetID);
            userdata.setUserID(beanUserID);
            if (beanLogonID != null)
               userdata.setLogonID(getLongBeanLogonID());
            else
               userdata.setLogonID(null);
            userdata.setResetID(beanResetID);
            String validateMsg = validateReset();
            if (validateMsg != null && !validateMsg.isEmpty())
            {
               logger.debug("DEBUG: Invalid ResetID: Email =" + userdata.getEmail() + ", Balidate Message-> " + validateMsg);
               webutil.redirecttoMessagePage("ERROR", "Invalid link", "Sorry, this link contains invalid reset data.");
            }
            updateLogonStatus();
         }
      }
      catch (Exception ex)
      {
         logger.debug("ERROR: Exception: " + ex.getMessage());
         logger.debug("Message", ex);
         webutil.redirecttoMessagePage("ERROR", "Invalid link", "Sorry, this link contains invalid data. Call Support:");
         webutil.alertSupport("Userbean.preRenderActivateUser", "Invalid link", "Sorry, this link contains invalid data. Call Support:", ex.getMessage());
      }
   }

   // This method is used twice.  Either the email address is provided and we need to find the corresponding record.
   // Or there is not data and register is clicked from logon window.
   public void preRenderCreateUserID()
   {
      String msg, msgheader;

      try
      {
         if (!FacesContext.getCurrentInstance().isPostback())
         {
            Boolean canCreateID = false;
            beanUserID = null;
            beanResetID = null;
            resetBean();
            if (beanEmail != null && ! beanEmail.isEmpty())
            {
              userdata.setEmail(beanEmail);
              canCreateID = true;
           }
            else {
               userdata.setEmail(null);
            }

            if (beanLogonID != null && ! beanLogonID.isEmpty()) {
               userdata.setLogonID(getLongBeanLogonID());
               canCreateID = true;
            }
            else {
               userdata.setLogonID(null);
            }

            // If either email or logonid is provided, then find the correspinding record.
            if (canCreateID) {
               userdata.setUserID(null);
               userdata.setLogonstatus(null);
               Integer check = userInfoDAO.checkReset(userdata);
               if (check > 0)
               {
                  logger.debug("Info: User is already registered: " + userdata.getEmail());
                  msgheader = "signup.U103";
                  msg= webutil.getMessageText().getDisplayMessage(msgheader, "Sorry, you are attempting to sign-up for account that is already registered.  Either, follow the instruction to activate the account or use forgot password to reset your access.", null);
                  webutil.redirecttoMessagePage("ERROR", "Invalid link", msg);
               }
               else {
                  // NOTE:  This signup method is called from either welcome link or it is called from register on main page.
                  // If the user is not found or it is valid reset, then continue.
                  beanEmail = userdata.getEmail(); // If data was found then add to interface.
                  beanUserID = userdata.getUserID(); // If data found then add to interface.
                  logger.info("Info: Start registration process for: " + userdata.getEmail());
               }
            }
         }
      }
      catch (Exception ex)
      {
         logger.debug("ERROR: Exception: " + ex.getMessage());
         logger.debug("Message", ex);
         msgheader = "signup.EX.U102";
         msg = webutil.getMessageText().getDisplayMessage(msgheader, "Sorry, you are attempting to activate account, but the link contains invalid data. Call Support.", null);
         webutil.redirecttoMessagePage("ERROR", "Exception: Invalid link", msg);
      }
   }

   // This method is used during Pre-signup process.  Validate the Email.
   public void simpleSignup()
   {
      String msg;
      String msgheader;
      try
      {
         // beanUserID = userdata.getEmail();
         beanEmail = userdata.getEmail();
         userdata.setUserID(null);
         // userdata.setEmail(beanUserID);
         if (userInfoDAO.validateUserID(userdata))
         {
            logger.debug("LOG: Validate UserID failed: " + beanUserID);
            msgheader = "signup.U100";
            msg= webutil.getMessageText().getDisplayMessage(msgheader, "This userid is already taken, please try another", null);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msgheader));
         }
         else
         {
            Long acctnum = webutil.getConverter().getLongData(beanCustID);
            userdata.setAcctnum(acctnum);
            userdata.setUserID(beanEmail);
            Long logonID = saveUser("I");
            if (logonID > 0L)
            {
               logger.debug("Info: Saving data UserID/Password, all checks were successful for: " + userdata.getUserID());
               sendConfirmation();
               //uiLayout.doMenuAction("consumer","aftersignup.xhtml?log="+userdata.getLogonID()+"&acct="+userdata.getAcctnum().toString());
               uiLayout.doMenuAction("custody", "index.xhtml?acct=" + userdata.getAcctnum().toString());
            }
            else
            {
               logger.debug("Debug: DB failure: " + beanUserID);
               msgheader = "signup.U101";
               msg= webutil.getMessageText().getDisplayMessage(msgheader, "Failed to save data.  Your session must have timed out.", null);
               FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msgheader));
            }
         }

      }
      catch (Exception ex)
      {
         logger.debug("Exception: " + ex.getMessage());
         logger.debug("Message", ex);
         msgheader = "signup.EX.100";
         msg= webutil.getMessageText().getDisplayMessage(msgheader, "Exception: Create UserID/Pwd, problem attempting to create simpleuser", null);
         webutil.alertSupport("Userbean.preRenderSimpleSignup", msgheader, msg, ex.getMessage());
      }
   }

   public void validateSignOn()
   {
      String msg;
      String msgheader;
      try
      {
         userdata.setLogonID(getLongBeanLogonID());
         userdata.setUserID(beanUserID);            // Since both logonid and email is forced to
         if (userInfoDAO.validateUserID(userdata))
         {
            logger.debug("LOG: Validate UserID failed: " + beanUserID);
            msgheader = "signup.U104";
            msg= webutil.getMessageText().getDisplayMessage(msgheader, "UserID already taken", null);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msgheader));
         }
         else
         {
            // Check if the username/password match.  Cannot change the Email Address.
            String validatePwd = webutil.validateNewPass(pwd1, pwd2);
            if (!validatePwd.toUpperCase().equals("SUCCESS"))
            {
               logger.debug("LOG: Validate Password failed: " + beanUserID + " with status = " + validatePwd);
               msgheader = "signup.U105";
               msg= webutil.getMessageText().getDisplayMessage(msgheader, "Password don't match criteria or two passwords are not same.", null);
               FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msgheader));
               return;
            }
            logger.debug("Debug: Intial Page of UserID/Password checks were successful for: " + userdata.getUserID());

            // If this user data was collected and they already have logonid, then add the userid and change the 'A'
            String logonStatus = (userdata.getLogonID() == null) ? "T" : "A";
            userdata.setUserID(beanUserID);
            userdata.setSecCode(pwd1);
            userdata.setPassword(pwd1);
            userdata.setConfirmNewPassword(pwd2);
            Long logonID = saveUser(logonStatus);
            if (logonID > 0L)
            {
               sendConfirmation();
               webutil.redirect("/signup2.xhtml", null);
            }
         }

      }
      catch (Exception ex)
      {
         logger.debug("ERROR: Exception: " + ex.getMessage());
         logger.debug("Message", ex);
         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "UserID issue", "Contact Support"));
         webutil.alertSupport("Userbean.ValidatePage1", "Exception: Validate UserID/Pwd", "Problem attempting to validate user", ex.getMessage());
      }
   }

   public void acceptQA()
   {
      try
      {

         saveQnA();
         // If there is no error with QA save, then redirect to proper page.
         if (userdata.getLogonstatus() != null && getUserdata().getLogonstatus().startsWith("A"))
         {
            webutil.redirect("/signup4.xhtml", null);
         }
         else
         {
            webutil.redirect("/signup3.xhtml", null);
         }


      }
      catch (Exception ex)
      {
         logger.debug("ERROR: Exception: " + ex.getMessage());
         logger.debug("Message", ex);
      }
   }

   public void resetPassword()
   {

      String msg;
      Boolean validUser = true;  //  Assume that all is fine.
      if (beanans1 == null)
      {
         msg = "Answer to this question is required.";
         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg));
         validUser = false;
      }
      else if (!beanans1.equals(userdata.getRandomAns()))
      {
         msg = "Answer does not match, lets try again.";
         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg));
         setRandomQuestion();
         attempts++;
         validUser = false;
      }
      else
      {
         msg = webutil.validateNewPass(pwd1, pwd2);
         if (!msg.toUpperCase().equals("SUCCESS"))
         {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg));
            validUser = false;
            return;
         }
      }

      if (!validUser)
      {
         if (attempts > 2)
         {
            webutil.redirecttoMessagePage("ERROR", "Too Many attempts.", "Sorry, cannot reset your information.  Your credentials don't match.");
         }
      }
      else
      {
         String passwordEncrypted = MsgDigester.getMessageDigest(pwd1);
         userInfoDAO.resetPassword(beanUserID, passwordEncrypted);
         webutil.redirect("/approved.xhtml", null);
      }
   }

   public Long saveUser(String logonStatus)
   {
      String msg;
      String msgheader;

      if (beanUserID == null || beanUserID.length() < 5)
      {
         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid UserID", "Invalid UserID"));
      }

      try
      {

         String emailMsgType = "HTML";
/*
         userdata.setEmail(beanEmail);
         userdata.setUserID(beanUserID);
         userdata.setQ1(beanq1);
         userdata.setQ2(beanq2);
         userdata.setQ3(beanq3);
         userdata.setAns1(beanans1);
         userdata.setAns2(beanans2);
         userdata.setAns3(beanans3);
*/

         String dataText = "Email: " + userdata.getEmail() + "/n" +
            "UserID: " + userdata.getUserID() + "/n" +
            "Name: " + userdata.getFirstName() + " " + userdata.getLastName();

         userdata.setEmailmsgtype(emailMsgType);
         // Save data to database....
         if (userdata.getAccess() != null && userdata.getAccess().equalsIgnoreCase("advisor"))
         {
            userdata.setLogonstatus("A");
         }
         else
         {
            userdata.setLogonstatus(logonStatus);
         }
         // We are using the First Name, Last Name from UserData as entered.

         if (logonStatus == null || logonStatus.isEmpty() || logonStatus.startsWith("I")) {
            String rndmPassword = PasswordGenerator.getSecCode();
            String tmpCode = com.invessence.web.util.MsgDigester.getMessageDigest(rndmPassword);
            userdata.setSecCode(tmpCode);
            userdata.setPassword(tmpCode);
         }
         else {
            String tmpCode = com.invessence.web.util.MsgDigester.getMessageDigest(userdata.getPassword());
            userdata.setSecCode(tmpCode);
            userdata.setPassword(tmpCode);
         }

         if (userdata.getLogonstatus() != null && userdata.getLogonstatus().startsWith("A")) {
            userdata.setResetID(null);
         }
         else {
            Integer myResetID = webutil.randomGenerator(0, 347896);
            userdata.setResetID(myResetID.toString());
         }

         String myIP = webutil.getClientIpAddr((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest());
         String img = "123";
         // Map<String, String> cookieInfo = new HashMap<String, String>();
         // cookieInfo.put("img", img);
         //utl.setCookie(Const.COMPANY_NAME,"image",cookieInfo);
         userdata.setIp(myIP);
         userdata.setCid(webutil.getUiprofile().getCid());
         userdata.setAdvisor(webutil.getUiprofile().getAdvisor());
         userdata.setRep(webutil.getUiprofile().getRep());
         // secCode = "Default123";
         userdata.setEmailmsgtype(userdata.getEmailmsgtype());
         String supportInfo = webutil.getUiprofile().getSupportemail();

         // Save data to database....
         long loginID = userInfoDAO.addUserInfo(userdata);

         if (loginID <= 0L)
         {
            logger.debug("ERROR: Had issue with this userid when attempting to save: " + loginID);
            msgheader = "signup.U106";
            msg = webutil.getMessageText().getDisplayMessage(msgheader, "There was some error when attempting to save this userid.  Please reach out to support desk.", null);
            webutil.redirecttoMessagePage("ERROR", msg, "Failed Signup" + msgheader);
            webutil.alertSupport("Userbean.saveUser", "Save -" + beanEmail, "Save Registration Error", null);
            return loginID;
         }
         userdata.setLogonID(loginID);
         return loginID;

      }
      catch (Exception ex)
      {
         logger.debug("Exception " + ex.getMessage());
         logger.debug("Error: Attempting to save UserID, for: " + beanEmail);

         msgheader = "signup.EX.100";
         msg= webutil.getMessageText().getDisplayMessage(msgheader, "Exception: Sorry, there was an issue with signup.  Please call support.", null);
         webutil.alertSupport("Userbean.saveUser", "Signup -" + msgheader, msg, ex.getMessage());
      }
      return 0L;
   }

   public void saveQnA()
   {
      String msgheader, msg;
      try
      {
         logger.info("Info: Save Question/Answer section: " + beanUserID);

         logger.debug("Attempting to Save Q/A: " + beanEmail + ", UserID: " + beanUserID);

         userdata.setQ1(beanq1);
         userdata.setQ2(beanq2);
         userdata.setQ3(beanq3);
         userdata.setAns1(beanans1);
         userdata.setAns2(beanans2);
         userdata.setAns3(beanans3);
         userInfoDAO.updateSecurityQuestions(userdata);  // If there is error, then exception is raised
      }
      catch (Exception ex)
      {
         logger.debug("Exception " + ex.getMessage());
         msgheader = "signup.EX.105";
         msg= webutil.getMessageText().getDisplayMessage(msgheader, "Exception: Sorry, there was an issue with signup.  Please call support.", null);
         webutil.redirecttoMessagePage("ERROR", "Q/A Save", msg);
         webutil.alertSupport("Userbean.saveQnA", "Signup -" + msgheader, msg, ex.getMessage());
      }
   }

   public void sendActivatedEmail()
   {
      if (beanEmail == null)
      {
         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Email is required", "Email is required"));
      }
      sendConfirmation();
   }

   public void sendResetEmail()
   {
      String msgheader, msg;
      if (beanEmail == null)
      {
         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Email is required", "Email is required"));
      }
      userdata.setEmail(beanEmail);
      userdata.setUserID(null);
      userdata.setLogonID(null);
      collectUserAccount();

      if (userdata.getLogonID() != null && userdata.getLogonID() > 0L)
      {
         // Now send email support.

         Integer myResetID = webutil.randomGenerator(0, 347896);
         userInfoDAO.updResetID(userdata.getUserID(), myResetID.toString());
         userdata.setLogonstatus("R");
         sendConfirmation();
      }
      else {
         logger.debug("Warning: Reset Denied: " + beanEmail);
         msgheader = "signup.U107";
         msg = webutil.getMessageText().getDisplayMessage(msgheader, "Email Address is invalid, or it is not registered.", null);
         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, msg, msgheader));
      }
   }

   public String validateReset()
   {
      String msg = null;
      try
      {
      /* 0 = Good: User found, and resetID matches and user was not in active status.
         1 = User found, not in active status but resetID do not match.
         -2 = User is not found on the database.
         -3 = data object to fetch was not provided
       */
         int ind = userInfoDAO.checkReset(userdata);
         switch (ind)
         {
            case -1:
               msg = "User is already registered!";
               break;
            case -2:
               msg = "Invalid link, no such user.";
               break;
            case 0:
               break;
            case 1:
               msg = "Invalid link or list has expired.";
               break;
            default:
               msg = "System error: contact support.";
         }
      }
      catch (Exception e)
      {
         msg = "System error: contact support.";
      }
      return msg;
   }

   public String updateUserProfile()
   {
      String updtStr = userInfoDAO.updateUserProfile(userdata);
      return updtStr;
   }

   public void setRandomQuestion() {
      Integer qnum = webutil.randomGenerator(1,3);
      switch (qnum) {
         case 0:
         case 1:
            if (userdata.getQ1() != null && userdata.getAns1() != null) {
               userdata.setRandomQ(userdata.getQ1());
               userdata.setRandomAns(userdata.getAns1());
               break;
            }
         case 2:
            if (userdata.getQ2() != null && userdata.getAns2() != null) {
               userdata.setRandomQ(userdata.getQ2());
               userdata.setRandomAns(userdata.getAns2());
               break;
            }

         case 3:
            if (userdata.getQ3() != null && userdata.getAns3() != null) {
               userdata.setRandomQ(userdata.getQ3());
               userdata.setRandomAns(userdata.getAns3());
               break;
            }
         default:
            if (userdata.getRandomQ() == null) {
               Integer num1 = webutil.randomGenerator(1,9);
               Integer num2 = webutil.randomGenerator(1,9);
               Integer sum = num1 + num2;
               userdata.setRandomQ("What is sum of " + num1.toString() + " + " + num2.toString());
               userdata.setRandomAns(sum.toString());
            }
            break;
      }
   }

   public void sendConfirmation() {
      if (userdata.getEmail() == null) {
         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Session timed out.  Use Forgot password to get access to your account.", "System timed out"));
      }

      if (userdata.getLogonstatus() != null && userdata.getLogonstatus().startsWith("A")) {
         return;
      }
      logger.debug("Debug: Sending confirmation email: " + userdata.getEmail());

      MsgData data = new MsgData();
      data.setSource("User");  // This is set to User to it insert into appropriate table.
      data.setSender(webutil.getUiprofile().getEmailUser());
      data.setReceiver(userdata.getEmail());
      String secureUrl = webutil.getUiprofile().getSecurehomepage();
      String name = userdata.getFullName();

      String emailMsgType;
      String htmlfile = null, htmltempate = null, textInfo = null;
      String whichURL = null;
      String subject = "No subject";

      Integer whichEmail = 0;
      if (userdata.getLogonstatus() == null) {
         return; // No email
      }
      else {
         if (userdata.getLogonstatus().startsWith("A"))
            whichEmail = 0;
         else if (userdata.getLogonstatus().startsWith("R"))
               whichEmail = 1;
         else  if (userdata.getLogonstatus().startsWith("I"))
            whichEmail = 8;
         else  whichEmail = 9;
      }

      switch (whichEmail) {
         case 0:
            subject = "Account Activated";
            textInfo = WebConst.TEXT_ACTIVATED;
            htmltempate = WebConst.HTML_ACTIVATED;
            // htmltempate = webutil.getUiprofile().getEmailTemplate(WebConst.HTML_ACTIVATED);
            whichURL=secureUrl + "/activate.xhtml?l="+userdata.getLogonID().toString()+"&r="+userdata.getResetID();
            break;
         case 1:
            subject = "Account Reset";
            textInfo = WebConst.TEXT_RESET;
            htmltempate = WebConst.HTML_RESET;
            // htmltempate = webutil.getUiprofile().getEmailTemplate(WebConst.HTML_RESET);
            whichURL=secureUrl + "/setPassword.xhtml?l="+userdata.getLogonID().toString()+"&r="+userdata.getResetID();
            break;
         case 2:
         case 3:
         case 4:
         case 5:
         case 6:
         case 7:
         case 8:
         case 9:
            subject = "Welcome";
            whichURL=secureUrl + "/signup.xhtml?l="+userdata.getLogonID().toString()+"&r="+userdata.getResetID();
            if (userdata.getAccess() != null && userdata.getAccess().equalsIgnoreCase("Advisor")) {
               textInfo = WebConst.TEXT_WELCOME_ADV;
               htmltempate = WebConst.HTML_WELCOME_ADV;
               // htmltempate = webutil.getUiprofile().getEmailTemplate(WebConst.HTML_WELCOME_ADV);
            }
            else {
               textInfo = WebConst.TEXT_WELCOME;
               htmltempate = WebConst.HTML_WELCOME;
               // htmltempate = webutil.getUiprofile().getEmailTemplate(WebConst.HTML_WELCOME);
            }
            break;
         default:

      }

      if (htmltempate == null) {
         emailMsgType = "TEXT";
         htmlfile = null;
      }
      else {
         emailMsgType = "HTML";
         htmlfile = htmltempate;
         // htmlfile = "/template/html/" + htmltempate;
      }

      data.setSubject(subject);
      String msg = webutil.getMessageText().buildMessage(emailMsgType,
                                                         htmlfile,
                                                         textInfo,
                                                         new Object[]{
                                                            whichURL,
                                                            userdata.getLogonID().toString(),
                                                            userdata.getUserID(),
                                                            userdata.getEmail(),
                                                            userdata.getResetID(),
                                                            userdata.getFirstName(),
                                                            userdata.getLastName(),
                                                            userdata.getEmail(),
                                                            webutil.getUiprofile().getSupportemail(),
                                                            webutil.getUiprofile().getSupportphone(),
                                                            webutil.getUiprofile().getCompanyname(),
                                                            webutil.getUiprofile().getLogo()
                                                         });
      data.setMsg(msg);
      webutil.getMessageText().writeMessage("custom", data);
      logger.debug("Debug: Sending Activation email to: " + userdata.getEmail());

   }




}