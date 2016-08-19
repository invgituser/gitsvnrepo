package com.invessence.web.data.common;

import java.util.*;


import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import com.invessence.emailer.data.MsgData;
import com.invessence.web.dao.common.UserInfoDAO;
import com.invessence.web.util.*;
import com.invessence.web.constant.*;
import org.apache.commons.logging.*;

public class UserData
{
   protected final Log logger = LogFactory.getLog(getClass());

   private SecurityQuestions securityQuestions = new SecurityQuestions();

   private UserData instance = null;
   private Long logonID = 0L;
   private String logonstatus = "A";
   private Long acctnum;

   private String prefix = null;
   private String firstName = null;
   private String middleInitial = null;
   private String lastName = null;
   private String suffix = null;

   private String email = null;
   private String emailalt = null;

   private String userID = null;
   private String emailID = null;
   private String emailIDVerify = null;
   private String password = null;

   private String leadsource = null;

   private String confirmNewPassword = null;
   private String currentPassword = null;
   private String secCode = null;

   private RoleData roleData = new RoleData();
   private List<RoleData> roleDataList = new ArrayList<RoleData>();
   private int sendText = -1;
   private int status = 0;
   private String captchaAnswer = null;
   private String fullName = null;
   private String message = null;

   private String stateCode;

   private String q1, q2, q3;
   private String ans1, ans2, ans3;
   private String randomQ, randomAns;
   private String ip;
   private String resetID;
   private String cid;
   private String advisor;
   private String rep;
   private String access;
   private String atstart;

   private String emailmsgtype = null;

   @ManagedProperty("#{webutil}")
   private WebUtil webutil;
   public void setWebutil(WebUtil webutil)
   {
      this.webutil = webutil;
   }

   @ManagedProperty("#{userInfoDAO}")
   private UserInfoDAO userInfoDAO;

   public UserInfoDAO getUserInfoDAO()
   {
      return userInfoDAO;
   }

   public void setUserInfoDAO(UserInfoDAO userInfoDAO)
   {
      this.userInfoDAO = userInfoDAO;
   }


   public UserData()
   {
      super();
      instance = this;
   }

   public UserData getInstance()
   {
/*
      if (instance == null) {
         instance = new UserData();
      }
*/

      return instance;
   }

   public Long getLogonID()

   {
      return logonID;
   }

   public void setLogonID(Long logonID)
   {
      this.logonID = logonID;
   }

   public Long getAcctnum()
   {
      return acctnum;
   }

   public void setAcctnum(Long acctnum)
   {
      this.acctnum = acctnum;
   }

   public String getLogonstatus()
   {
      return logonstatus;
   }

   public void setLogonstatus(String logonstatus)
   {
      this.logonstatus = logonstatus;
   }

   public String getPassword()
   {
      return password;
   }

   public void setPassword(String password)
   {
      this.password = password;
   }

   public String getStateCode()
   {
      return stateCode;
   }

   public void setStateCode(String stateCode)
   {
      this.stateCode = stateCode;
   }


   public String getEmail()
   {
      return email;
   }

   public void setEmail(String email)
   {
      this.email = email;
   }

   public String getEmailalt()
   {
      return emailalt;
   }

   public void setEmailalt(String emailalt)
   {
      this.emailalt = emailalt;
   }


   public String getUserID()
   {
      return userID;
   }

   public void setUserID(String userID)
   {
      this.userID = userID;
   }

   public String getEmailID()
   {
      return emailID;
   }

   public void setEmailID(String emailID)
   {
      System.out.println("Signon Try:" + emailID);
      this.emailID = emailID;
   }

   public String getConfirmNewPassword()
   {
      return confirmNewPassword;
   }

   public void setConfirmNewPassword(String confirmNewPassword)
   {
      this.confirmNewPassword = confirmNewPassword;
   }

   public String getCurrentPassword()
   {
      return currentPassword;
   }

   public void setCurrentPassword(String currentPassword)
   {
      this.currentPassword = currentPassword;
   }

   public RoleData getRoleData()
   {
      return roleData;
   }

   public void setRoleData(RoleData roleData)
   {
      this.roleData = roleData;
   }

   public int getSendText()
   {
      return sendText;
   }

   public void setSendText(int sendText)
   {
      this.sendText = sendText;
   }

   public List<RoleData> getRoleDataList()
   {
      return roleDataList;
   }

   public void setRoleDataList(List<RoleData> roleDataList)
   {
      this.roleDataList = roleDataList;
   }

   public int getStatus()
   {
      return status;
   }

   public void setStatus(int status)
   {
      this.status = status;
   }

   public String getSecCode()
   {
      return secCode;
   }

   public void setSecCode(String secCode)
   {
      this.secCode = secCode;
   }

   public String getCaptchaAnswer()
   {
      return captchaAnswer;
   }

   public void setCaptchaAnswer(String captchaAnswer)
   {
      this.captchaAnswer = captchaAnswer;
   }

   public String getFullName()
   {

      if (!WebUtil.isNull(firstName))
      {
         this.fullName = firstName;
      }

      if (!WebUtil.isNull(middleInitial))
      {
         this.fullName += " " + middleInitial;
      }

      if (!WebUtil.isNull(lastName))
      {
         this.fullName += " " + lastName;
      }

      return this.fullName;


   }

   public void setFullName(String fullName)
   {
      this.fullName = fullName;
   }

   public String getEmailIDVerify()
   {
      return emailIDVerify;
   }

   public void setEmailIDVerify(String emailIDVerify)
   {
      this.emailIDVerify = emailIDVerify;
   }

   public String getPrefix()
   {
      return prefix;
   }

   public void setPrefix(String prefix)
   {
      this.prefix = prefix;
   }

   public String getFirstName()
   {
      return firstName;
   }

   public void setFirstName(String firstName)
   {
      this.firstName = firstName;
   }

   public String getMiddleInitial()
   {
      return middleInitial;
   }

   public void setMiddleInitial(String middleInitial)
   {
      this.middleInitial = middleInitial;
   }

   public String getLastName()
   {
      return lastName;
   }

   public void setLastName(String lastName)
   {
      this.lastName = lastName;
   }

   public String getSuffix()
   {
      return suffix;
   }

   public void setSuffix(String suffix)
   {
      this.suffix = suffix;
   }

   public String getLeadsource()
   {
      return leadsource;
   }

   public void setLeadsource(String leadsource)
   {
      this.leadsource = leadsource;
   }

   public String getMessage()
   {
      return message;
   }


   public void setMessage(String message)
   {
      this.message = message;
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

   public String getRandomQ()
   {
      return randomQ;
   }

   public void setRandomQ(String randomQ)
   {
      this.randomQ = randomQ;
   }

   public String getRandomAns()
   {
      return randomAns;
   }

   public void setRandomAns(String randomAns)
   {
      this.randomAns = randomAns;
   }

   public String getIp()
   {
      return ip;
   }

   public void setIp(String ip)
   {
      this.ip = ip;
   }

   public String getResetID()
   {
      return resetID;
   }

   public void setResetID(String resetID)
   {
      this.resetID = resetID;
   }

   public Map<String, String> getQuestion(Integer qnum)
   {
      return securityQuestions.getQuestion(qnum);
   }

   public String getEmailmsgtype()
   {
      return emailmsgtype;
   }

   public void setEmailmsgtype(String emailmsgtype)
   {
      this.emailmsgtype = emailmsgtype;
   }

   public String getCid()
   {
      return cid;
   }

   public void setCid(String cid)
   {
      this.cid = cid;
   }

   public String getAdvisor()
   {
      return advisor;
   }

   public void setAdvisor(String advisor)
   {
      this.advisor = advisor;
   }

   public void setRep(String rep)
   {
      this.rep = rep;
   }

   public String getRep()
   {
      return rep;
   }

   public String getAccess()
   {
      return access;
   }

   public void setAccess(String access)
   {
      this.access = access;
   }

   public String getAtstart()
   {
      return atstart;
   }

   public void setAtstart(String atstart)
   {
      this.atstart = atstart;
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
            int ind = getUserInfoDAO().checkReset(this);
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

   public void collectUserAccount()
   {
      Boolean found;
      found = userInfoDAO.selectUserInfo(this);
   }

   public void updateLogonStatus()
   {
      Boolean found;
      userInfoDAO.updLogonStatus(getUserID());
   }

   public Long addUserLogon()
   {
      if (getUserID() == null || getUserID().length() < 5)
      {
         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid Email", "Invalid Email"));
      }
      MsgData data = new MsgData();
      //String websiteUrl = messageSource.getMessage("website.url", new Object[]{}, null);
      String dataText = "Email: " + getEmail() + "/n" +
         "UserID: " + getUserID() + "/n" +
         "Name: " + getFirstName() + " " + getLastName();


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
            System.out.println("Email alert system is down!!!!!!");
            FacesContext.getCurrentInstance().getExternalContext().redirect("/message.xhtml?message=System error:  Error code (signup failure)");
            webutil.alertSupport("Userdata.saveUser", "Error: MessageText object", "Message Text object is null \n" + dataText, null);
            return 0L;
         }

         // We are using the First Name, Last Name from UserData as entered.
         String rndmPassword = PasswordGenerator.getSecCode();
         String tmpCode = MsgDigester.getMessageDigest(rndmPassword);
         String myIP = webutil.getClientIpAddr((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest());
         Integer myResetID = webutil.randomGenerator(0, 347896);
         String img = "123";
         Map<String, String> cookieInfo = new HashMap<String, String>();
         cookieInfo.put("img", img);
         //utl.setCookie(Const.COMPANY_NAME,"image",cookieInfo);
         setIp(myIP);
         setAdvisor(webutil.getUiprofile().getAdvisor());
         setRep(webutil.getUiprofile().getRep());
         setResetID(myResetID.toString());
         setLogonstatus("T");
         setSecCode(tmpCode);
         setPassword(tmpCode);
         setCid("0");
         // secCode = "Default123";
         setEmailmsgtype(getEmailmsgtype());
         String supportInfo = webutil.getUiprofile().getSupportemail();

         // Save data to database....
         long loginID = userInfoDAO.addUserInfo(this);

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
            data.setReceiver(getEmailID());
            data.setSubject(Const.COMPANY_NAME + " - Successfully registered");
            String secureUrl = webutil.getUiprofile().getSecurehomepage();
            String name = getFirstName() + " " + getLastName();

            // System.out.println("MIME Type :" + getEmailmsgtype());
            if (getEmailmsgtype() == null || getEmailmsgtype().isEmpty())
               data.setMimeType("HTML");
            else
               data.setMimeType(getEmailmsgtype());

            String msg = webutil.getMessageText().buildMessage(getEmailmsgtype(), "signup.email.template", "signup.email", new Object[]{name, getUserID(), secureUrl, getEmailID(), getResetID().toString(), supportInfo});
            data.setMsg(msg);

            webutil.getMessageText().writeMessage("signup", data);

            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(WebConst.LOGONID_PARAM, loginID);
            return getLogonID();
         }

      }
      catch (Exception ex)
      {
         String username = getUserID();
         String stackTrace = "User: " + username + " \n" + ex.getMessage();
         webutil.alertSupport("Userdata.saveUser", "Exception: Unable to create LogonID", "Unable to create proper logonid on database \n" + dataText, stackTrace);
         return 0L;
      }
   }

   public void saveQnA()
   {
      userInfoDAO.updateSecurityQuestions(this);
   }

   public String updateUserProfile()
   {
      String updtStr = userInfoDAO.updateUserProfile(this);
      return updtStr;
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

   public void sendConfirmation() {
      if (getEmail() == null) {
         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Session timed out.  Use Forgot password to get access to your account.", "System timed out"));
      }

      logger.debug("Debug: Sending confirmation email: " + getEmail());

      MsgData data = new MsgData();
      String emailMsgType = "HTML";
      data.setSource("User");  // This is set to User to it insert into appropriate table.
      data.setSender(webutil.getUiprofile().getEmailUser());
      data.setReceiver(getEmail());
      data.setSubject("Successfully registered");
      String secureUrl = webutil.getUiprofile().getSecurehomepage();
      String name = getFullName();

      if (getAccess().equalsIgnoreCase("Advisor")) {
         String msg = webutil.getMessageText().buildMessage(emailMsgType,
                                                            "html.advisor_activate.email",
                                                            "txt.advisor_activate.email",
                                                            new Object[]{secureUrl, getUserID(), getResetID().toString()});
         data.setMsg(msg);
         webutil.getMessageText().writeMessage("custom", data);
         logger.debug("Info: Sending Advisor activation email: " + getEmail());
      }
      else {
         String msg = webutil.getMessageText().buildMessage(emailMsgType,
                                                            "html.activate.email",
                                                            "txt.activate.email",
                                                            new Object[]{secureUrl, getUserID(), getResetID().toString()});
         data.setMsg(msg);
         webutil.getMessageText().writeMessage("custom", data);
         logger.debug("Debug: Sending Activation email to: " + getEmail());
      }

   }




}