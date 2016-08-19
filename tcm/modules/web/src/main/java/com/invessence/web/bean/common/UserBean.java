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
import com.invessence.util.*;
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
      userdata = new UserData();

   }

/*
   public void collectClientData()
   {
     // System.out.println("Info: Calling userInfoDAO.getUserByEmail(" + beanEmail + ")");
     userdata = userInfoDAO.getUserByEmail(userdata);

   }
*/

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
                  userdata.collectUserAccount();
                  if (!beanResetID.equals(userdata.getResetID().toString()))
                  {
                     webutil.redirecttoMessagePage("ERROR", "Invalid link", "Sorry, this link contains invalid Reset data.");
                  }
                  userdata.setRandomQuestion();
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
            if (beanUserID == null || beanUserID.isEmpty()) {
               logger.debug("Info: During activation section: " + beanUserID + " is missing!");
               webutil.redirecttoMessagePage("ERROR", "Invalid link", "Sorry, this link contains invalid reset data.");
               return;
            }
            resetBean();
            logger.debug("LOG: fetch data for, UserID = " + beanUserID + ", Reset " + beanResetID);
            userdata.setUserID(beanUserID);
            userdata.setLogonID(beanLogonID);
            userdata.setResetID(beanResetID);
            String validateMsg = userdata.validateReset();
            if (validateMsg != null && ! validateMsg.isEmpty()) {
               logger.debug("DEBUG: Invalid ResetID: Email =" + userdata.getEmail() + ", Balidate Message-> " + validateMsg );
               webutil.redirecttoMessagePage("ERROR", "Invalid link", "Sorry, this link contains invalid reset data.");
            }
            userdata.updateLogonStatus();
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
            userdata.setLogonID(null);
            userdata.setUserID(null);
            userdata.setEmail(beanEmail);
            userdata.collectUserAccount();
            if (userdata.getUserID() != null && ! userdata.getUserID().isEmpty())
            {
               logger.debug("Info: Email is already registered: " + beanEmail );
               webutil.redirecttoMessagePage("ERROR", "Invalid link", "Sorry, you are attempting to sign-up for account that is already registered.  Either, follow the instruction to activate the account or use forgot password to reset your access.");
            }
            logger.info("Info: Start registration process for: " + userdata.getEmail() );
         }
      }
      catch (Exception ex)
      {
         logger.debug("ERROR: Exception: " + ex.getMessage());
         logger.debug("Message", ex);
         webutil.redirecttoMessagePage("ERROR", "Invalid link", "Sorry, you are attempting to activate account, but the link contains invalid data. Call Support" );
      }
   }

   public void validateSignOn()
   {
      try {
         userdata.setUserID(beanUserID);
         userdata.collectUserAccount();
         if (userdata.getEmail() != null && ! userdata.getEmail().isEmpty()) {
            logger.debug("LOG: Validate UserID failed: " + beanUserID);
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
            logger.debug("Debug: Intial Page of UserID/Password checks were successful for: " + userdata.getUserID() );
            saveUser();
            if (userdata.getLogonID() > 0L) {
               userdata.sendConfirmation();
               webutil.redirect("/signup2.xhtml", null);
            }
         }

      }
      catch (Exception ex) {
         logger.debug("ERROR: Exception: " + ex.getMessage());
         logger.debug("Message", ex);
         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "UserID issue", "Contact Support"));
         webutil.alertSupport("Userbean.ValidatePage1","Exception: Validate UserID/Pwd", "Problem attempting to validate user", ex.getMessage());
      }
   }

   public void acceptQA() {
      try {

         saveQnA();
         // If there is no error with QA save, then redirect to proper page.
         if (userdata.getAccess().equalsIgnoreCase("advisor")) {
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
      else if (! beanans1.equals(userdata.getRandomAns())) {
            msg="Answer does not match, lets try again.";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg));
            userdata.setRandomQuestion();
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
         userdata.setEmail(beanEmail);
         userdata.setUserID(beanUserID);
         userdata.setQ1(beanq1);
         userdata.setQ2(beanq2);
         userdata.setQ3(beanq3);
         userdata.setAns1(beanans1);
         userdata.setAns2(beanans2);
         userdata.setAns3(beanans3);
         userdata.setEmailmsgtype(emailMsgType);
         // Save data to database....
         if (userdata.getAccess().equalsIgnoreCase("advisor")) {
            userdata.setLogonstatus("A");
         }
         else {
            userdata.setLogonstatus("T");
         }
         long loginID = userdata.addUserLogon();

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

   public void saveQnA() {
      try {
         System.out.println("Info: Save Question/Answer section: " + beanUserID );

         logger.debug("Attempting to Save Q/A: " + beanEmail + ", UserID: " + beanUserID);

         userdata.setQ1(beanq1);
         userdata.setQ2(beanq2);
         userdata.setQ3(beanq3);
         userdata.setAns1(beanans1);
         userdata.setAns2(beanans2);
         userdata.setAns3(beanans3);
         userdata.saveQnA();  // If there is error, then exception is raised
      }
      catch (Exception ex) {
         logger.debug("Exception " + ex.getMessage());
         webutil.redirecttoMessagePage("ERROR", "Q/A Save", "It seems that there was an error attempting to save Questions/Ans. Section.  Your account has been saved.");
         webutil.alertSupport("signup", "Q/A Signup -" + beanEmail, "Q/A save Error", null);
      }
   }


   public void emailResetInfo() {
      if (beanEmail == null) {
         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Email is required", "Email is required"));
      }
      userdata.setEmail(beanEmail);
      userdata.collectUserAccount();

      if (userdata.getLogonID() != null && userdata.getLogonID() > 0L) {
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

         // System.out.println("MIME Type :" + getEmailmsgtype());
         String msg = webutil.getMessageText().buildMessage(data.getMimeType(), "html.password.reset.email", "txt.password.reset.email", new Object[]{secureUrl, userdata.getUserID(), myResetID.toString()});
         data.setMsg(msg);

         webutil.getMessageText().writeMessage("reset", data);

         String displayMessage = webutil.getMessageText().buildInternalMessage("txt.reset.info",null);
         webutil.redirecttoMessagePage("INFO", "Reset Instructions sent", displayMessage);
         System.out.println("Info: Email Sent, succcesfully: " + beanEmail);
      }
      else {
         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "This email is not valid. Not a registered user", "This email is not valid. Not a registered user"));
         System.out.println("ERROR: Attempting to reset, but valid email: " + beanEmail);
      }
   }



}