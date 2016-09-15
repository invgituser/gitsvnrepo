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
   private Long beanLogonID;

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
        // System.out.println("Info: Calling userInfoDAO.getUserByEmail(" + beanEmail + ")");
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

   public Boolean validateUserAccount()
   {
      return (userInfoDAO.validateUserID(userdata));
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
            userdata.setLogonID(beanLogonID);
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

   // This method is used during Pre-signup process.  Validate the Email.
   public void preRenderCreateUserID()
   {
      String msg;

      try
      {
         if (!FacesContext.getCurrentInstance().isPostback())
         {
            beanUserID = null;
            beanResetID = null;
            resetBean();
            if (beanEmail == null || beanEmail.isEmpty())
            {
               logger.debug("WARN, Cannot Signup, Attempting to register on site, but NO email address is included.");
               webutil.redirecttoMessagePage("WARN", "Cannot Signup", "Attempting to register on site, but valid email address is not provided.");
            }
            userdata.setLogonID(null);
            userdata.setUserID(null);
            userdata.setEmail(beanEmail);
            collectUserAccount();
            if (userdata.getUserID() != null && !userdata.getUserID().isEmpty())
            {
               logger.debug("Info: Email is already registered: " + beanEmail);
               webutil.redirecttoMessagePage("ERROR", "Invalid link", "Sorry, you are attempting to sign-up for account that is already registered.  Either, follow the instruction to activate the account or use forgot password to reset your access.");
            }
            logger.info("Info: Start registration process for: " + userdata.getEmail());
         }
      }
      catch (Exception ex)
      {
         logger.debug("ERROR: Exception: " + ex.getMessage());
         logger.debug("Message", ex);
         webutil.redirecttoMessagePage("ERROR", "Invalid link", "Sorry, you are attempting to activate account, but the link contains invalid data. Call Support");
      }
   }

   // This method is used during Pre-signup process.  Validate the Email.
   public void simpleSignup()
   {
      try
      {
         String msg;
         beanUserID = userdata.getEmail();
         beanEmail = userdata.getEmail();
         userdata.setUserID(beanUserID);
         if (validateUserAccount())
         {
            logger.debug("LOG: Validate UserID failed: " + beanUserID);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "UserID taken", "Try different UserID, this one is taken."));
         }
         else
         {
            Long acctnum = webutil.getConverter().getLongData(beanCustID);
            userdata.setAcctnum(acctnum);
            Long logonID = saveUser();
            if (logonID > 0L)
            {
               logger.debug("Info: Saving data UserID/Password, all checks were successful for: " + userdata.getUserID());
               sendConfirmation();
               //uiLayout.doMenuAction("consumer","aftersignup.xhtml?log="+userdata.getLogonID()+"&acct="+userdata.getAcctnum().toString());
               uiLayout.doMenuAction("custody", "index.xhtml?acct=" + userdata.getAcctnum().toString());
            }
            else
            {
               msg = "Failed to save data.  Your session must have timed out.";
               logger.debug("Debug: DB failure: " + beanUserID);
               FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg));
            }
         }

      }
      catch (Exception ex)
      {
         logger.debug("Exception: " + ex.getMessage());
         logger.debug("Message", ex);
         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "System issue", "Contact Support"));
         webutil.alertSupport("Userbean.preRenderSimpleSignup", "Exception: Create UserID/Pwd", "Problem attempting to create simpleuser", ex.getMessage());
      }
   }

   public void validateSignOn()
   {
      try
      {
         userdata.setUserID(beanUserID);
         collectUserAccount();
         if (userdata.getEmail() != null && !userdata.getEmail().isEmpty())
         {
            logger.debug("LOG: Validate UserID failed: " + beanUserID);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "UserID/Email already registered!", "Try different UserID"));
         }
         else
         {
            // Check if the username/password match.  Cannot change the Email Address.
            String msg = webutil.validateNewPass(pwd1, pwd2);
            if (!msg.toUpperCase().equals("SUCCESS"))
            {
               logger.debug("LOG: Validate Password failed: " + beanUserID + " with status = " + msg);
               FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg));
               return;
            }
            logger.debug("Debug: Intial Page of UserID/Password checks were successful for: " + userdata.getUserID());
            Long logonID = saveUser();
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
         if (userdata.getAccess().equalsIgnoreCase("advisor"))
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

   public Long saveUser()
   {
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
         userdata.setEmailmsgtype(emailMsgType);
         // Save data to database....
         if (userdata.getAccess() != null && userdata.getAccess().equalsIgnoreCase("advisor"))
         {
            userdata.setLogonstatus("A");
         }
         else
         {
            userdata.setLogonstatus("T");
         }
         // We are using the First Name, Last Name from UserData as entered.

         String rndmPassword = PasswordGenerator.getSecCode();
         String tmpCode = com.invessence.web.util.MsgDigester.getMessageDigest(rndmPassword);
         String myIP = webutil.getClientIpAddr((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest());
         Integer myResetID = webutil.randomGenerator(0, 347896);
         String img = "123";
         Map<String, String> cookieInfo = new HashMap<String, String>();
         cookieInfo.put("img", img);
         //utl.setCookie(Const.COMPANY_NAME,"image",cookieInfo);
         userdata.setIp(myIP);
         userdata.setResetID(myResetID.toString());
         userdata.setLogonstatus("T");
         userdata.setSecCode(tmpCode);
         userdata.setPassword(tmpCode);
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
            String msg = "There was some error when attempting to save this userid.  Please reach out to support desk.";
            webutil.redirecttoMessagePage("ERROR", "Failed Signup", "Had issue when attempting to save your credentials.  Please try again in 30 min..");
            webutil.alertSupport("signup", "Save -" + beanEmail, "Save Registration Error", null);
            return loginID;
         }
         userdata.setLogonID(loginID);
         return loginID;

      }
      catch (Exception ex)
      {
         logger.debug("Exception " + ex.getMessage());
         webutil.redirecttoMessagePage("ERROR", "Failed Signup", "Sorry, there was an issue with signup.  Please call support.");
         webutil.alertSupport("signup", "Signup -" + beanEmail, "Registration Error", null);
         logger.debug("Error: Attempting to save UserID, for: " + beanEmail);
         ex.printStackTrace();
      }
      return 0L;
   }

   public void saveQnA()
   {
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
         saveQnA();  // If there is error, then exception is raised
      }
      catch (Exception ex)
      {
         logger.debug("Exception " + ex.getMessage());
         webutil.redirecttoMessagePage("ERROR", "Q/A Save", "It seems that there was an error attempting to save Questions/Ans. Section.  Your account has been saved.");
         webutil.alertSupport("signup", "Q/A Signup -" + beanEmail, "Q/A save Error", null);
      }
   }


   public void emailResetInfo()
   {
      if (beanEmail == null)
      {
         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Email is required", "Email is required"));
      }
      userdata.setEmail(beanEmail);
      collectUserAccount();

      if (userdata.getLogonID() != null && userdata.getLogonID() > 0L)
      {
         // Now send email support.

         Integer myResetID = webutil.randomGenerator(0, 347896);
         userInfoDAO.updResetID(userdata.getUserID(), myResetID.toString());

         MsgData data = new MsgData();
         data.setSource("User");  // This is set to User to it insert into appropriate table.
         data.setSender(Const.MAIL_SENDER);
         data.setReceiver(beanEmail);
         data.setSubject("Reset Instructions");
         String secureUrl = uiLayout.getUiprofile().getSecurehomepage();

         String name = userdata.getFullName();
         String emailMsgType;
         String htmlfile, htmltempate, textInfo;
         textInfo = WebConst.TEXT_RESET;
         htmltempate = WebConst.HTML_RESET;
         // htmltempate = webutil.getUiprofile().getEmailTemplate(WebConst.HTML_RESET);

         if (htmltempate == null) {
            emailMsgType = "TEXT";
            htmlfile = null;
         }
         else {
            emailMsgType = "HTML";
            htmlfile = htmltempate;
            // htmlfile = "/template/html/" + htmltempate;
         }

         userdata.setEmailmsgtype(userdata.getEmailmsgtype());
         String msg = webutil.getMessageText().buildMessage(emailMsgType,
                                                            htmlfile,
                                                            textInfo,
                                                            new Object[]{
                                                               secureUrl,
                                                               userdata.getUserID(),
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

         String displayMessage = webutil.getMessageText().buildInternalMessage("txt.reset.info", null);
         webutil.redirecttoMessagePage("INFO", "Reset Instructions sent", displayMessage);
         logger.info("Info: Email Sent, succcesfully: " + beanEmail);
      }
      else
      {
         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "This email is not valid. Not a registered user", "This email is not valid. Not a registered user"));
         logger.debug("ERROR: Attempting to reset, but valid email: " + beanEmail);
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

   public Long addUserLogon()
   {
      String dataText = "Email: " + userdata.getEmail() + "/n" +
         "UserID: " + userdata.getUserID() + "/n" +
         "Name: " + userdata.getFirstName() + " " + userdata.getLastName();

      if (userdata.getUserID() == null || userdata.getUserID().length() < 5)
      {
         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid Email", "Invalid Email"));
      }
      MsgData data = new MsgData();
      //String websiteUrl = messageSource.getMessage("website.url", new Object[]{}, null);

      logger.info("Registered by: " + dataText);
      try
      {
         if (webutil == null) {
            logger.debug("Initilization Error!!!!!!");
            FacesContext.getCurrentInstance().getExternalContext().redirect("/message.xhtml?message=System error:  Error code (signup failure)");
            webutil.alertSupport("Userdata.saveUser", "Error: Webutil object", "Java object is null \n" + dataText, null);
            return 0L;
         }

         if (webutil.getMessageText() == null)
         {
            logger.debug("Webutil.getMessageText() is null!!!!!!");
            FacesContext.getCurrentInstance().getExternalContext().redirect("/message.xhtml?message=System error:  Error code (signup failure)");
            webutil.alertSupport("Userdata.saveUser", "Error: MessageText object", "Message Text object is null \n" + dataText, null);
            return 0L;
         }

         // We are using the First Name, Last Name from UserData as entered.
         String rndmPassword = PasswordGenerator.getSecCode();
         String tmpCode = com.invessence.web.util.MsgDigester.getMessageDigest(rndmPassword);
         String myIP = webutil.getClientIpAddr((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest());
         Integer myResetID = webutil.randomGenerator(0, 347896);
         String img = "123";
         Map<String, String> cookieInfo = new HashMap<String, String>();
         cookieInfo.put("img", img);
         //utl.setCookie(Const.COMPANY_NAME,"image",cookieInfo);
         userdata.setIp(myIP);
         userdata.setResetID(myResetID.toString());
         userdata.setLogonstatus("T");
         userdata.setSecCode(tmpCode);
         userdata.setPassword(tmpCode);
         userdata.setCid(webutil.getUiprofile().getCid());
         userdata.setAdvisor(webutil.getUiprofile().getAdvisor());
         userdata.setRep(webutil.getUiprofile().getRep());
         // secCode = "Default123";
         String supportInfo = webutil.getUiprofile().getSupportemail();

         // Save data to database....
         long loginID = userInfoDAO.addUserInfo(userdata);

         if (loginID < 0L) {
            String msg="Database could not create logonID.  Serious Error";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg));
            webutil.alertSupport("Userdata.saveUser", "Error: Unable to create LogonID", "Unable to create proper logonid on database \n" + dataText, null);
            return 0L;
         }
         else {
            // Now send email support.
            data.setSource("User");  // This is set to User to it insert into appropriate table.
            data.setSender(Const.MAIL_SENDER);
            data.setReceiver(userdata.getEmailID());
            data.setSubject(Const.COMPANY_NAME + " - Successfully registered");
            String secureUrl = webutil.getUiprofile().getSecurehomepage();
            String name = userdata.getFirstName() + " " + userdata.getLastName();

            String emailMsgType;
            String htmlfile, htmltempate, textInfo;
            textInfo = WebConst.TEXT_WELCOME;
            htmltempate = WebConst.HTML_WELCOME;
            // htmltempate = webutil.getUiprofile().getEmailTemplate(WebConst.HTML_WELCOME);

            if (htmltempate == null) {
               emailMsgType = "TEXT";
               htmlfile = null;
            }
            else {
               emailMsgType = "HTML";
               htmlfile = htmltempate;
               // htmlfile = "/template/html/" + htmltempate;
            }

            userdata.setEmailmsgtype(userdata.getEmailmsgtype());
            String msg = webutil.getMessageText().buildMessage(emailMsgType,
                                                            htmlfile,
                                                            textInfo,
                                                            new Object[]{
                                                               secureUrl,
                                                               userdata.getUserID(),
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


            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(WebConst.LOGONID_PARAM, loginID);
            return userdata.getLogonID();
         }

      }
      catch (Exception ex)
      {
         String username = userdata.getUserID();
         String stackTrace = "User: " + username + " \n" + ex.getMessage();
         webutil.alertSupport("Userdata.saveUser", "Exception: Unable to create LogonID", "Unable to create proper logonid on database \n" + dataText, stackTrace);
         return 0L;
      }
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

      logger.debug("Debug: Sending confirmation email: " + userdata.getEmail());

      MsgData data = new MsgData();
      data.setSource("User");  // This is set to User to it insert into appropriate table.
      data.setSender(webutil.getUiprofile().getEmailUser());
      data.setReceiver(userdata.getEmail());
      data.setSubject("Successfully registered");
      String secureUrl = webutil.getUiprofile().getSecurehomepage();
      String name = userdata.getFullName();

      String emailMsgType;
      String htmlfile, htmltempate, textInfo;
      if (userdata.getAccess() != null && userdata.getAccess().equalsIgnoreCase("Advisor")) {
         textInfo = WebConst.TEXT_WELCOME_ADV;
         htmltempate = WebConst.HTML_WELCOME_ADV;
         // htmltempate = webutil.getUiprofile().getEmailTemplate(WebConst.HTML_WELCOME_ADV);

         if (htmltempate == null) {
            emailMsgType = "TEXT";
            htmlfile = null;
         }
         else {
            emailMsgType = "HTML";
            htmlfile = htmltempate;
            // htmlfile = "/template/html/" + htmltempate;
         }
      }
      else {
         textInfo = WebConst.TEXT_WELCOME;
         htmltempate = WebConst.HTML_WELCOME;
         // htmltempate = webutil.getUiprofile().getEmailTemplate(WebConst.HTML_WELCOME);

         if (htmltempate == null) {
            emailMsgType = "TEXT";
            htmlfile = null;
         }
         else {
            emailMsgType = "HTML";
            htmlfile = htmltempate;
            // htmlfile = "/template/html/" + htmltempate;
         }
      }

      String msg = webutil.getMessageText().buildMessage(emailMsgType,
                                                         htmlfile,
                                                         textInfo,
                                                         new Object[]{
                                                            secureUrl,
                                                            userdata.getUserID(),
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