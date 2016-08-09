package com.invessence.web.bean.common;

import java.util.*;
import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import com.invessence.emailer.data.MsgData;
import com.invessence.web.constant.*;
import com.invessence.web.dao.common.UserInfoDAO;
import com.invessence.web.data.*;
import com.invessence.web.data.common.UserData;
import com.invessence.web.util.*;
import org.springframework.beans.factory.annotation.Autowired;

@ManagedBean(name = "userBean")
@SessionScoped
public class UserBean extends UserData
{
   private String beanUserID, beanResetID, beanEmail;

   @Autowired
   private WebUtil webutil;

   private Integer sTab = 0;

   private USMaps usstates = USMaps.getInstance();
   private String[] uscountry;

   @ManagedProperty("#{webMessage}")
   private WebMessage messageText;

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

   public WebMessage getMessageText()
   {
      return messageText;
   }

   public void setMessageText(WebMessage messageText)
   {
      this.messageText = messageText;
   }

   public Integer getsTab()
   {
      return sTab;
   }

   public Map<String, String> getUsstates()
   {
      return usstates.getStates();
   }

   public void preResetView()
   {

      String msg = null;
      try
      {
         if (!FacesContext.getCurrentInstance().isPostback())
         {
            msg = validateReset(beanUserID, beanEmail,beanResetID);
            if (msg != null)
            {
               webutil.redirecttoMessagePage("ERROR","Invalid link","Sorry, you are attempting to access the registration process, but the link contains invalid data.<br>" + msg);
               webutil.alertSupport("ResetID", "Error: ResetID", msg, null);
            }
         }

      }
      catch (Exception e)
      {
         msg = "System error: contact support.";
         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg));
         webutil.alertSupport("ResetID", "Exception: ResetID", msg, e.getMessage());
      }
   }



   public String signUp()
   {
      if (getUserID() == null || getUserID().length() < 5)
      {
         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid Email", "Invalid Email"));
      }
      MsgData data = new MsgData();
      //String websiteUrl = messageSource.getMessage("website.url", new Object[]{}, null);

      System.out.println("Registered by: " + getFullName() + ", EmailID: " + getUserID());
      try
      {
         if (messageText == null)
         {
            System.out.println("Email alert system is down!!!!!!");
            FacesContext.getCurrentInstance().getExternalContext().redirect("/message.xhtml?message=System error:  Error code (signup failure)");
            return "Error";
         }

         // We are using the First Name, Last Name from UserData as entered.
         setUserID(getUserID());
         setEmail(getUserID());
         setEmailID(getUserID());
         String rndmPassword = PasswordGenerator.getSecCode();
         String tmpCode = MsgDigester.getMessageDigest(rndmPassword);
         String myIP = webutil.getClientIpAddr((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest());
         Integer myResetID = webutil.randomGenerator(0, 347896);
         String img = "123";
         Map<String, String> cookieInfo = new HashMap<String, String>();
         cookieInfo.put("img", img);
         //utl.setCookie(Const.COMPANY_NAME,"image",cookieInfo);
         setIp(myIP);
         setResetID(myResetID);
         setLogonstatus("T");
         setSecCode(tmpCode);
         setPassword(tmpCode);
         setCid("0");
         // secCode = "Default123";
         setEmailmsgtype(getEmailmsgtype());
         String supportInfo = messageText.buildInternalMessage("secure.url", new Object[]{});

         // Save data to database....
         long loginID = getUserInfoDAO().addUserInfo(getInstance());

         if (loginID < 0L) {
            String msg="This userid is already registered.  Either reset password, via FORGOT Password,or try another ID";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg));
            return "failed";
         }
         else {
            // Now send email support.
            data.setSource("User");  // This is set to User to it insert into appropriate table.
            data.setSender(Const.MAIL_SENDER);
            data.setReceiver(getEmailID());
            data.setSubject(Const.COMPANY_NAME + " - Successfully registered");
            String secureUrl = messageText.buildInternalMessage("secure.url", new Object[]{});
            String name = getFirstName() + " " + getLastName();

            // System.out.println("MIME Type :" + getEmailmsgtype());
            if (getEmailmsgtype() == null || getEmailmsgtype().isEmpty())
               data.setMimeType("HTML");
            else
               data.setMimeType(getEmailmsgtype());

            String msg = messageText.buildMessage(getEmailmsgtype(), "signup.email.template", "signup.email", new Object[]{name, getUserID(), secureUrl, getEmailID(), getResetID().toString(), supportInfo});
            data.setMsg(msg);

            messageText.writeMessage("signup", data);

            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(WebConst.LOGONID_PARAM, loginID);
            FacesContext.getCurrentInstance().getExternalContext().redirect("/signupApproval.xhtml");
            return "success";
         }

      }
      catch (Exception ex)
      {
         String username = getUserID();
         String stackTrace = "User: " + username + " \n" + ex.getMessage();
         data.setMsg(messageText.buildInternalMessage("signup.failure", new Object[]{stackTrace}));
         messageText.writeMessage("Error", data);
         return "failed";
      }
   }

   public String tryus()
   {
      MsgData data = new MsgData();
      //String websiteUrl = messageSource.getMessage("website.url", new Object[]{}, null);

      System.out.println("Demo by: " + getFullName() + ", EmailID: " + getUserID());
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
         setUserID(getUserID());
         setEmail(getUserID());
         setEmailID(getUserID());
         String rndmPassword = PasswordGenerator.getSecCode();
         String tmpCode = MsgDigester.getMessageDigest(rndmPassword);
         String myIP = webutil.getClientIpAddr((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest());
         Integer myResetID = webutil.randomGenerator(0, 347896);
         String img = "123";
         Map<String, String> cookieInfo = new HashMap<String, String>();
         cookieInfo.put("img", img);
         //utl.setCookie(Const.COMPANY_NAME,"image",cookieInfo);
         setIp(myIP);
         setResetID(myResetID);
         setLogonstatus("T");
         setSecCode(tmpCode);
         setPassword(tmpCode);
         setEmailmsgtype("Text");
         // secCode = "Default123";


         String name = getFirstName() + " " + getLastName();
         // Save data to database....
         long loginID = getUserInfoDAO().addUserInfo(getInstance());

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
            sendEmail = false;
            redirectTo = null;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "This email is already registered.  Use Login to view account.", null));
            return "failed";
         }
         else
         {
            sendEmail = true;
            msg = "";
            redirectTo = "/createInvestment.xhtml";
         }

         if (sendEmail)
         {
            // Now send email support.
            if (messageText != null)
            {
               String supportInfo = messageText.buildInternalMessage("support.info", new Object[]{});
               String secureUrl = messageText.buildInternalMessage("secure.url", new Object[]{});
               data.setSource("User");  // This is set to User to it insert into appropriate table.
               data.setSender(Const.MAIL_SENDER);
               data.setReceiver(getEmailID());
               data.setSubject(Const.COMPANY_NAME + " - Successfully registered");
               String emailmsg = messageText.buildMessage(getEmailmsgtype(), "signup.email.template", "signup.email", new Object[]{name, getUserID(), secureUrl, getEmailID(), getResetID().toString(), supportInfo});
               data.setMsg(emailmsg);
               data.setMimeType(getEmailmsgtype());
               messageText.writeMessage("User", data);
            }
         }

         if (redirectTo != null)
         {
            if (loginID > 0)
            {
               FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(WebConst.LOGONID_PARAM, loginID);
            }
            FacesContext.getCurrentInstance().getExternalContext().redirect(redirectTo);
            return "success";
         }
         else
         {
            return "failed";
         }

      }
      catch (Exception ex)
      {
         String stackTrace = ex.getMessage();
         if (messageText != null)
         {
            String msg = "Demo failed for ( " + getEmailID() + "), " + stackTrace;
            data.setMsg(messageText.buildInternalMessage("signup.failure", new Object[]{msg}));
            messageText.writeMessage("Error", data);
         }
         System.out.println("Demo failed: " + getFullName());
         System.out.println("Messsage:" + stackTrace);
         ex.printStackTrace();

         return "failed";
      }
   }


   public void register()
   {
      String msg;
      try
      {
         msg = webutil.validateNewPass(getPassword(), getConfirmNewPassword());
         if (! msg.toUpperCase().equals("SUCCESS"))
         {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg));
            return;
         }

         String passwordEncrypted = MsgDigester.getMessageDigest(getPassword());

         getUserInfoDAO().resetPassword(beanUserID, passwordEncrypted);

         msg = "registered.success";
         webutil.redirect("message.xhtml?faces-redirect=true&message=" + msg, null);
      }
      catch (Exception ex)
      {

      }
   }

   public void resetPassword()
   {
      String msg;
      try
      {
         msg = webutil.validateNewPass(getPassword(), getConfirmNewPassword());
         if (! msg.toUpperCase().equals("SUCCESS"))
         {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg));
            return;
         }

         String passwordEncrypted = MsgDigester.getMessageDigest(getPassword());

         getUserInfoDAO().resetPassword(beanUserID, passwordEncrypted);

         msg = "password.set.success";
         webutil.redirect("message.xhtml?faces-redirect=true&message=" + msg, null);
      }
      catch (Exception ex)
      {

      }
   }

   public void forgotPassword()
   {
      try
      {
         String check;
         check = getUserInfoDAO().checkEmailID(beanUserID);
         if (check == null || check.length() == 0)
         {
             webutil.redirect("message.xhtml?faces-redirect=true&message=User not found", null);
         }
         else
         {
            beanEmail = check;
            Integer myResetID = webutil.randomGenerator(0, 347896);
            beanResetID = myResetID.toString();
            validateSecurityQuestions();
         }
      }
      catch (Exception ex)
      {
         webutil.redirect("message.xhtml?faces-redirect=true&type=E&message=Error when attempting to reset.", null);
         webutil.alertSupport("ForgotPwd", "Resetting password", "Error when attempting to reset for" + beanUserID, null);
      }
   }

   public void validateSecurityQuestions()
   {

      // Save ResetID
      getUserInfoDAO().updResetID(beanUserID, beanResetID);

      //String websiteName = messageSource.getMessage("website.name", new Object[]{}, null);
      MsgData data = new MsgData();
      //data.setLogonID(loginID);
      data.setSource("User");
      data.setSender(Const.MAIL_SENDER);
      data.setReceiver(beanEmail);
      data.setSubject(Const.COMPANY_NAME + ": Forgot Password");
      //data.setMsg(MsgConst.getSignupMsg(userData));

      // System.out.println("MIME TYPE :" + userInfoDAO.checkMimeType(getEmailID()));
      if (messageText == null)
      {
         webutil.redirect("message.xhtml?faces-redirect=true&type=E&&message=Email process is down.", null);
         webutil.alertSupport("ForgotPwd", "Resetting password", "messageText Context is null for " + beanUserID, null);
      }

      String websiteUrl = messageText.buildInternalMessage("website.url", new Object[]{});
      String name = "User";
      String mimetype=getUserInfoDAO().checkMimeType(beanUserID);
      data.setMimeType(mimetype);
      String msg = messageText.buildMessage(mimetype, "password.reset.email.template", "password.reset.email", new Object[]{websiteUrl, beanUserID, beanResetID});

      data.setMsg(msg);
      messageText.writeMessage(name, data);

      String resetMsg = "password.reset";
      webutil.redirect("message.xhtml?faces-redirect=true&message=" + resetMsg,null);

   }

   public void gotoPrevTab()
   {
      if (sTab <= 0)
      {
         sTab = 0;
      }
      else
      {
         sTab--;
      }

   }

   public void gotoNextTab()
   {
      if (sTab >= 1)
      {
         String status = signUp();
      }
      else
      {
         sTab++;
      }

   }
}