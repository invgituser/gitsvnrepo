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
   private Boolean newRegistration;
   private String action;
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

   public Boolean getNewRegistration()
   {
      return newRegistration;
   }

   public UserData getUserdata()
   {
      return userdata;
   }

   public String getAction()
   {
      return action;
   }

   public void setAction(String action)
   {
      this.action = action;
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

   private void resetALL()
   {

      newRegistration = true;
      action = "N";
      beanUserID = null;
      beanResetID = null;
      beanEmail = null;
      beanCustID = null;
      beanLogonID = null;
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
      beanCustID = null;
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
      userInfoDAO.updLogonStatus(beanLogonID);
   }

   public void preRenderResetUser()
   {
      String msg, msgheader;
      try
      {
         if (!FacesContext.getCurrentInstance().isPostback())
         {
            resetBean();
            logger.debug("LOG: Reset UserID = " + beanUserID);
            if (beanLogonID != null && getLongBeanLogonID() >= 0L)
            {
                  userdata.setLogonID(getLongBeanLogonID());
                  collectUserAccount();
                  if (!beanResetID.equals(userdata.getResetID()))
                  {
                     logger.debug("Info: ResetID does not match >> " + userdata.getEmail());
                     msgheader = "signup.U109";
                     webutil.redirecttoMessagePage("ERROR", "Invalid link", msgheader);
                  }
                  beanEmail = userdata.getEmail();
                  beanUserID = userdata.getUserID();

                  setRandomQuestion();
            }
            else
            {
               logger.debug("Info: No LogonID> ");
               msgheader = "signup.U109";
               webutil.redirecttoMessagePage("ERROR", "Invalid link", msgheader);
            }
         }
      }
      catch (Exception ex)
      {
         logger.debug("LOG: Exception = " + ex.getMessage());
         logger.debug("Message", ex);
         msgheader = "signup.EX.101";
         webutil.redirecttoMessagePage("ERROR", "Invalid link", msgheader);
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
            if (beanLogonID == null || beanLogonID.isEmpty())
            {
               logger.debug("Info: During activation section: " + beanUserID + " is missing!");
               webutil.redirecttoMessagePage("ERROR", "Invalid link", "Sorry, this link contains invalid reset data.");
               return;
            }
            resetBean();
            logger.debug("LOG: fetch data for, UserID = " + beanUserID + ", Reset " + beanResetID);
            userdata.setLogonID(getLongBeanLogonID());
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

   private Integer checkRegistrationInfo() {
      // If DB object returns false, then fetch failed.
      if (! userInfoDAO.selectUserInfo(userdata))
         return -2;

      // If we got no data.
      if (userdata.getLogonID() == null || userdata.getLogonID() < 0L)
         return -1;

      // Check that logoid that was passed matches the DB
      if (beanLogonID != null) {
         if (userdata.getLogonID().equals(getLongBeanLogonID())) {
            if (userdata.getLogonstatus() != null) {
               if (userdata.getLogonstatus().startsWith("T"))
                  return 1;
               else if (userdata.getLogonstatus().startsWith("I"))
                  return 2;
               else if (userdata.getLogonstatus().startsWith("A"))
                  return 3;
            }
         }
      }
      else {
         if (beanEmail != null) {
            // It there was NO match with LOGONID, then Check that email matches the DB.
            if (userdata.getEmail().equalsIgnoreCase(beanEmail)) {
               if (userdata.getLogonstatus() != null) {
                  if (userdata.getLogonstatus().startsWith("T"))
                     return 1;
                  else if (userdata.getLogonstatus().startsWith("I"))
                     return 2;
                  else if (userdata.getLogonstatus().startsWith("A"))
                     return 3;
               }
            }
         }
      }
      // Cannot continue, the data does not match.
      return 4;
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
            if (action != null) {
               resetALL();
               newRegistration = true;
            }
            else {
               beanUserID = null;
               beanResetID = null;
               newRegistration = false;
               resetBean();
               if (beanEmail != null && ! beanEmail.isEmpty())
               {
                 userdata.setEmail(beanEmail);
              }
               else {
                  userdata.setEmail(null);
               }

               if (beanLogonID != null && ! beanLogonID.isEmpty()) {
                  userdata.setLogonID(getLongBeanLogonID());
               }
               else {
                  userdata.setLogonID(null);
               }
            }

            // If either email or logonid is provided, then find the correspinding record.
            if (! newRegistration) {
               userdata.setUserID(null);
               userdata.setLogonstatus(null);

               Integer checkStat =  checkRegistrationInfo();
               if (checkStat == 1)
               {
                  // NOTE:  This signup method is called from either welcome link or it is called from register on main page.
                  // If the user is not found or it is valid reset, then continue.
                  beanEmail = userdata.getEmail(); // If data was found then add to interface.
                  beanUserID = userdata.getUserID(); // If data found then add to interface.
                  logger.info("Info: Start registration process for: " + userdata.getEmail());
               }
               else {
                  logger.debug("Info: Data fetch issue>> " + userdata.getEmail());
                  if (checkStat == 3) {
                     msgheader = "signup.U103";
                  }
                  else {
                     msgheader = "signup.U102";
                  }
                  // msg= webutil.getMessageText().getDisplayMessage(msgheader, "Sorry, you are attempting to sign-up for account that is already registered.  Either, follow the instruction to activate the account or use forgot password to reset your access.", null);
                  webutil.redirecttoMessagePage("ERROR", "Invalid link", msgheader);
               }
            }
         }
      }
      catch (Exception ex)
      {
         logger.debug("ERROR: Exception: " + ex.getMessage());
         logger.debug("Message", ex);
         msgheader = "signup.EX.102";
         // msg = webutil.getMessageText().getDisplayMessage(msgheader, "Sorry, you are attempting to activate account, but the link contains invalid data. Call Support.", null);
         webutil.redirecttoMessagePage("ERROR", "Invalid link", msgheader);
      }
   }

   // This method is used during Pre-signup process.  Validate the Email.
   public Integer simpleSignup()
   {
      String msg;
      String msgheader;
      try
      {
         // beanUserID = userdata.getEmail();
         beanEmail = userdata.getEmail();
         userdata.setUserID(null);
         userdata.setLogonID(null);
         // userdata.setEmail(beanUserID);
         if (userInfoDAO.validateUserID(userdata))
         {
            logger.debug("LOG: Validate UserID failed: " + beanUserID);
            msgheader = "signup.U100";
            msg= webutil.getMessageText().getDisplayMessage(msgheader, "This Email is already registered!", null);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msgheader));
            return -1;
         }
         else
         {
            Long acctnum = webutil.getConverter().getLongData(beanCustID);
            userdata.setAcctnum(acctnum);
            userdata.setUserID(beanEmail);
            Integer myResetID = webutil.randomGenerator(0, 347896);
            userdata.setUserInfo(WebConst.ROLE_USER,
                                 webutil.getWebprofile().getDefaultAdvisor(),
                                 webutil.getWebprofile().getDefaultRep(),
                                 myResetID);
            String supportInfo = webutil.getWebprofile().getSupportemail();

            // Save data to database....
            long loginID = userInfoDAO.addUserInfo(userdata);

            if (loginID <= 0L)
            {
               logger.debug("ERROR: Had issue with this userid when attempting to save: " + loginID);
               msgheader = "signup.U106";
               msg = webutil.getMessageText().getDisplayMessage(msgheader, "There was some error when attempting to save this userid.  Please reach out to support desk.", null);
               webutil.redirecttoMessagePage("ERROR", msg, "Failed Signup" + msgheader);
               webutil.alertSupport("Userbean.saveUser", "Save -" + beanEmail, "Save Registration Error", null);
               return -2;
            }

            if (loginID > 0L)
            {
               userdata.setLogonID(loginID);
               logger.debug("Info: Saving data UserID/Password, all checks were successful for: " + userdata.getUserID());
               //uiLayout.doMenuAction("consumer","aftersignup.xhtml?log="+userdata.getLogonID()+"&acct="+userdata.getAcctnum().toString());
               return 0;
            }
         }
         return 0;
      }
      catch (Exception ex)
      {
         logger.debug("Exception: " + ex.getMessage());
         logger.debug("Message", ex);
         msgheader = "signup.EX.100";
         msg= webutil.getMessageText().getDisplayMessage(msgheader, "Exception: Create UserID/Pwd, problem attempting to create simpleuser", null);
         webutil.alertSupport("Userbean.preRenderSimpleSignup", msgheader, msg, ex.getMessage());
         webutil.redirecttoMessagePage("ERROR", "Signup Failure", msgheader);
         return -100;
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

         if (newRegistration)
         {
            if (beanEmail == null || beanEmail.isEmpty()) {
               logger.debug("LOG: Invalidate Email (either empty or null): ");
               msgheader = "signup.U111";
               msg= webutil.getMessageText().getDisplayMessage(msgheader, "Email is required", null);
               FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msgheader));
               return;
            }
            userdata.setEmail(beanEmail);
         }

         if (userInfoDAO.validateUserID(userdata))
         {
            logger.debug("LOG: Validate UserID failed: " + beanUserID);
            msgheader = "signup.U104";
            msg= webutil.getMessageText().getDisplayMessage(msgheader, "UserID already taken", null);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msgheader));
            return;
         }

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
         //String logonStatus = (userdata.getLogonID() == null) ? "R" : "A";
         String logonStatus = (newRegistration) ? "R" : "T";
         userdata.setLogonstatus(logonStatus);
         userdata.setEmail(beanEmail);  // Since, this may be called from General Register, add Email to userdata.
         userdata.setUserID(beanUserID);// Since, this may be called from General Register, add UserID to userdata.
         userdata.setSecCode(pwd1);
         userdata.setPassword(pwd1);
         userdata.setConfirmNewPassword(pwd2);
         Integer myResetID = webutil.randomGenerator(0, 347896);
         userdata.setUserInfo(WebConst.ROLE_USER,
                              webutil.getWebprofile().getDefaultAdvisor(),
                              webutil.getWebprofile().getDefaultRep(),
                              myResetID);
         long loginID = userInfoDAO.addUserInfo(userdata);
         if (loginID > 0L)
         {
            userdata.setLogonID(loginID);
            // If the Welcome message was already sent, then they not new registration.  No need to send another confirmation email.
            if (newRegistration)
            {
               webutil.sendConfirmation(userdata,"A");
            }
            webutil.redirect("/signup2.xhtml", null);
         }
         else {
            logger.debug("ERROR: Save to DB failed: " + beanEmail);
            msgheader = "signup.U108";
            msg= webutil.getMessageText().getDisplayMessage(msgheader, "Failed to create user-logon session.", null);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msgheader));

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

         if (userdata.getLogonstatus() != null &&
            (getUserdata().getLogonstatus().startsWith("T") ||
               getUserdata().getLogonstatus().startsWith("A")) )
         {
            webutil.redirect("/signup4.xhtml", null);
         }
         else
         {
            webutil.redirect("/signup3.xhtml", null);
         }
         beanEmail = null;
         beanLogonID = null;
         beanUserID = null;
         resetBean();
         webutil.resetSession();
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
         webutil.resetSession(); // Force a logout to clear session and cache.
         webutil.redirect("/signup4.xhtml", null);
      }
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
         if (newRegistration) {
            // If user is using the register button, then status is set to "R" for reset.
            userdata.setLogonstatus("R");
         }
         else {
            // If user is activating account from welcome message, then we make them active user.
            userdata.setLogonstatus("A");
         }
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
      webutil.sendConfirmation(userdata,"A");
      webutil.resetSession();
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
         userInfoDAO.updResetID(userdata.getLogonID(), myResetID.toString());
         userdata.setLogonstatus("R");
         userdata.setResetID(myResetID.toString());
         webutil.sendConfirmation(userdata,"R");
         msgheader = "signup.U110";
         msg = webutil.getMessageText().getDisplayMessage(msgheader, "Reset email sent.", null);
         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msgheader));
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

   Integer qnum = null;
   Integer prevQ = null;

   public void setRandomQuestion() {
      prevQ = qnum;
      // First time generate random number, then sequentially steps through the others.
      qnum = (qnum == null) ? webutil.randomGenerator(1,3) : qnum;
      if (prevQ != null && prevQ == qnum) {  // If the random picked the same question, then increment by 1.
        qnum++;
        qnum = (qnum % 3);
      }
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

}