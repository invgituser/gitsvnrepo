package com.invessence.bean.common;

import java.io.Serializable;
import java.util.*;
import java.util.regex.Pattern;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import com.invessence.constant.*;
import com.invessence.converter.SQLData;
import com.invessence.dao.common.UserInfoDAO;
import com.invessence.data.*;
import com.invessence.data.common.UserData;
import com.invessence.util.*;
import org.primefaces.context.RequestContext;

@ManagedBean(name = "userBean")
@SessionScoped
public class UserBean extends UserData implements Serializable
{
   private String beanUserID, beanResetID, beanEmail;
   private String beanCustID;

   private String pwd1, pwd2;
   private String q1, q2, q3, ans1, ans2, ans3;

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

   public String getQ1()
   {
      return q1;
   }

   public void setQ1(String q1)
   {
      this.q1 = q1;
   }

   public String getQ2()
   {
      return q2;
   }

   public void setQ2(String q2)
   {
      this.q2 = q2;
   }

   public String getQ3()
   {
      return q3;
   }

   public void setQ3(String q3)
   {
      this.q3 = q3;
   }

   public String getAns1()
   {
      return ans1;
   }

   public void setAns1(String ans1)
   {
      this.ans1 = ans1;
   }

   public String getAns2()
   {
      return ans2;
   }

   public void setAns2(String ans2)
   {
      this.ans2 = ans2;
   }

   public String getAns3()
   {
      return ans3;
   }

   public void setAns3(String ans3)
   {
      this.ans3 = ans3;
   }

   public SecurityQuestions getSecurityQuestions()
   {
      return securityQuestions;
   }

   public Map<String, String> getUsstates()
   {
      return usstates.getStates();
   }

   public void collectUserData()
   {
      setEmail(beanEmail);
      userInfoDAO.getUserByEmail(getInstance());
   }

   /*
      public void preRenderActivateUser() {
         String msg;
         try {
            if (!FacesContext.getCurrentInstance().isPostback()) {
               if (beanUserID != null && beanResetID != null) {
                  if (! beanUserID.isEmpty() && ! beanResetID.isEmpty()) {
                     int ind = userInfoDAO.checkReset("R", beanUserID, beanResetID);
                     if (ind == 0) {
                        webutil.redirect("/rdone.xhtml", null); }
                     else {
                        webutil.redirecttoMessagePage("ERROR", "Invalid link", "Sorry, you are attempting to activate account, but the link contains invalid data.");
                     }
                  }
               }
            }
         }
         catch (Exception ex) {
            webutil.redirecttoMessagePage("ERROR", "Invalid link", "Sorry, you are attempting to activate account, but the link contains invalid data.");
         }
      }
   */


   // This method is used during Pre-signup process.  Validate the Email.
   public void preRenderSignup()
   {
      String msg;
      try
      {
         if (!FacesContext.getCurrentInstance().isPostback())
         {
            collectUserData();
            if (getUserID() != null && !getUserID().isEmpty())
            {
               webutil.redirecttoMessagePage("ERROR", "Invalid link", "Sorry, you are attempting to sign-up for account that is already registered.  Either, follow the instruction to activate the account or use forgot password to reset your access.");
            }
/*          Commented out to do beta testing of anyone able to sign up..
            if (getRep() == null || getRep() == 0L)
            { // Userid is UserData.
               webutil.redirecttoMessagePage("ERROR", "Cannot Signup", "Sorry, you are attempting to activate account, but you have to be invited.  Please click on the email invitation link.");
            }
*/
         }
      }
      catch (Exception ex)
      {
         webutil.redirecttoMessagePage("ERROR", "Invalid link", "Sorry, you are attempting to activate account, but the link contains invalid data.");
      }
   }

   public void gotoSignPage2()
   {
      try {
         Integer status = userInfoDAO.validateUserID(beanUserID);
         if (status != 0) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "UserID taken", "Try different UserID, this one is taken."));
         }
         else {
            // Check if the username/password match.  Cannot change the Email Address.
            String msg = webutil.validateNewPass(pwd1, pwd2);
            if (!msg.toUpperCase().equals("SUCCESS"))
            {
               FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg));
               return;
            }
            webutil.redirect("/signup2.xhtml", null);
         }

      }
      catch (Exception ex) {
         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "UserID issue", "Contact Support"));
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

      System.out.println("Trying Registering by: " + beanEmail + ", UserID: " + beanUserID);
      try
      {
         if (messageText == null)
         {
            System.out.println("Email alert system is down!!!!!!");
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
         setQ1(q1);
         setQ2(q2);
         setQ3(q3);
         setAns1(ans1);
         setAns2(ans2);
         setAns3(ans3);
         setIp(myIP);
         setResetID(myResetID);
         setEmailmsgtype(emailMsgType);
         // Save data to database....
         long loginID = userInfoDAO.addUserInfo(getInstance());

         if (loginID < 0L)
         {
            String msg = "This userid is already registered.  Either reset password, via FORGOT Password,or try another ID";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg));
            return;
         }
         else
         {
            // Now send email support.
            data.setSource("User");  // This is set to User to it insert into appropriate table.
            data.setSender(Const.MAIL_SENDER);
            data.setReceiver(beanEmail);
            data.setSubject("Successfully registered");
            String secureUrl = messageText.buildInternalMessage("secure.url", new Object[]{});
            String name = getFullName();

            // System.out.println("MIME Type :" + getEmailmsgtype());
            String msg = messageText.buildMessage(emailMsgType, "signup.email.template", "signup.email", new Object[]{secureUrl, beanUserID, beanEmail, beanResetID, null});
            data.setMsg(msg);

            messageText.writeMessage("signup", data);

            FacesContext.getCurrentInstance().getExternalContext().redirect("/signup3.xhtml");
         }

      }
      catch (Exception ex)
      {
         webutil.redirecttoMessagePage("ERROR", "Failed Signup", "Sorry, there was an issue with signup.  Please call support.");
         webutil.alertSupport("signup", "Signup -" + beanEmail, "Exception: " + ex.getMessage(), null);
         ex.printStackTrace();
      }
   }

}