package com.invessence.data.common;

import java.util.*;


import com.invessence.util.*;
import com.invessence.data.*;
import com.invessence.constant.*;

public class UserData
{
   private SecurityQuestions securityQuestions = new SecurityQuestions();

   private static UserData instance = null;
   private long logonID = 0;
   private String logonstatus = "A";

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
   private String ip;
   Integer resetID;
   private String cid;
   private String advisor;
   private Long rep;
   private String access;

   private String emailmsgtype = null;


   public UserData()
   {
      super();
      instance = this;
   }

   public static UserData getInstance()
   {
/*
      if (instance == null) {
         instance = new UserData();
      }
*/

      return instance;
   }

   public long getLogonID()

   {
      return logonID;
   }

   public void setLogonID(long logonID)
   {
      this.logonID = logonID;
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

   public String getIp()
   {
      return ip;
   }

   public void setIp(String ip)
   {
      this.ip = ip;
   }

   public Integer getResetID()
   {
      return resetID;
   }

   public void setResetID(Integer resetID)
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

   public Long getRep()
   {
      return rep;
   }

   public void setRep(Long rep)
   {
      this.rep = rep;
   }

   public String getAccess()
   {
      return access;
   }

   public void setAccess(String access)
   {
      this.access = access;
   }
}