package com.invessence.data.common;

import java.util.*;


import com.invessence.util.*;
import com.invessence.data.*;
import com.invessence.constant.*;
import org.springframework.beans.factory.annotation.*;

public class UserData
{
   private SecurityQuestions securityQuestions = new SecurityQuestions();

   private UserData instance = null;
   private Long logonID = null;
   private String logonstatus = "A";

   private String firstName = null;
   private String lastName = null;
   private String email = null;
   private String newemail = null;

   private String userID = null;
   private String passwordEncrypted = null;

   private RoleData roleData = new RoleData();
   private List<RoleData> roleDataList = new ArrayList<RoleData>();
   private String fullName = null;

   private String q1, q2, q3;
   private String ans1, ans2, ans3;
   private String ip;
   private Integer resetID;
   private String cid;
   private String leadSource;
   private String advisor;
   private String rep;
   private String access;

   private String emailmsgtype = null;
   private String randomQ = null;
   private String randomAns = null;

   public UserData()
   {
      super();
      instance = this;
   }

   public UserData(long logonID, String logonstatus, String email,
                   String userID,
                   String q1, String q2, String q3, String ans1, String ans2, String ans3,
                   String ip, Integer resetID, String emailmsgtype
                  )
      {
      super();
      instance = this;
      this.logonID = logonID;
      this.logonstatus = logonstatus;
      this.email = email;
      this.userID = userID;
      this.q1 = q1;
      this.q2 = q2;
      this.q3 = q3;
      this.ans1 = ans1;
      this.ans2 = ans2;
      this.ans3 = ans3;
      this.ip = ip;
      this.resetID = resetID;
      this.emailmsgtype = emailmsgtype;
   }

   public void resetData() {
      logonID = null;
      logonstatus = "A";

      securityQuestions = new SecurityQuestions();

      firstName = null;
      lastName = null;
      email = null;

      userID = null;
      passwordEncrypted = null;

      roleData = new RoleData();
      roleDataList = new ArrayList<RoleData>();
      fullName = null;

      q1 = null;
      q2 = null;
      q3 = null;
      ans1 = null;
      ans2 = null;
      ans3 = null;
      ip = null;
      resetID = null;
      cid = null;
      leadSource = null;
      advisor = null;
      rep = null;
      access = null;

      emailmsgtype = null;
      randomQ = null;
      randomAns = null;

   }

   public UserData getInstance()
   {
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

   public String getLogonstatus()
   {
      return logonstatus;
   }

   public void setLogonstatus(String logonstatus)
   {
      this.logonstatus = logonstatus;
   }

   public String getEmail()
   {
      return email;
   }

   public void setEmail(String email)
   {
      this.email = email;
   }

   public String getNewemail()
   {
      if (newemail == null) {
         return email;
      }
      else {
         return newemail;
      }
   }

   public String getEnteredNewemail()
   {
      return newemail;
   }

   public void setNewemail(String newemail)
   {
      this.newemail = newemail;
   }

   public String getUserID()
   {
      return userID;
   }

   public void setUserID(String userID)
   {
      this.userID = userID;
   }

   public RoleData getRoleData()
   {
      return roleData;
   }

   public void setRoleData(RoleData roleData)
   {
      this.roleData = roleData;
   }

   public List<RoleData> getRoleDataList()
   {
      return roleDataList;
   }

   public void setRoleDataList(List<RoleData> roleDataList)
   {
      this.roleDataList = roleDataList;
   }

   public String getPasswordEncrypted()
   {
      return passwordEncrypted;
   }

   public void setPasswordEncrypted(String passwordEncrypted)
   {
      this.passwordEncrypted = passwordEncrypted;
   }

   public String getFullName()
   {
      return fullName;
   }

   public void setFullName(String fullName)
   {
      this.fullName = fullName;
   }

   public String getFirstName()
   {
      return firstName;
   }

   public void setFirstName(String firstName)
   {
      this.firstName = firstName;
   }

   public String getLastName()
   {
      return lastName;
   }

   public void setLastName(String lastName)
   {
      this.lastName = lastName;
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

   public String getLeadSource()
   {
      return leadSource;
   }

   public void setLeadSource(String leadSource)
   {
      this.leadSource = leadSource;
   }

   public String getAdvisor()
   {
      return advisor;
   }

   public void setAdvisor(String advisor)
   {
      this.advisor = advisor;
   }

   public String getRep()
   {
      return rep;
   }

   public void setRep(String rep)
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
}