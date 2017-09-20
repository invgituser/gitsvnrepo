package com.invessence.web.service.aggr;

/**
 * Created by abhangp on 12/15/2016.
 */
public class UserAcctDetails
{
   private Long logonid;
   private String aggrUserId;
   private String aggrPwd;
   private String aggrStatus;
   private String userid;
   private String logonstatus;
   private String prefix;
   private String lastname;
   private String firstname;
   private String middlename;
   private String suffix;
   private String email;
   private String opt;

   public String getOpt()
   {
      return opt;
   }

   public void setOpt(String opt)
   {
      this.opt = opt;
   }

   public Long getLogonid()
   {
      return logonid;
   }

   public void setLogonid(Long logonid)
   {
      this.logonid = logonid;
   }

   public String getAggrUserId()
   {
      return aggrUserId;
   }

   public void setAggrUserId(String aggrUserId)
   {
      this.aggrUserId = aggrUserId;
   }

   public String getAggrPwd()
   {
      return aggrPwd;
   }

   public void setAggrPwd(String aggrPwd)
   {
      this.aggrPwd = aggrPwd;
   }

   public String getAggrStatus()
   {
      return aggrStatus;
   }

   public void setAggrStatus(String aggrStatus)
   {
      this.aggrStatus = aggrStatus;
   }

   public String getUserid()
   {
      return userid;
   }

   public void setUserid(String userid)
   {
      this.userid = userid;
   }

   public String getLogonstatus()
   {
      return logonstatus;
   }

   public void setLogonstatus(String logonstatus)
   {
      this.logonstatus = logonstatus;
   }

   public String getPrefix()
   {
      return prefix;
   }

   public void setPrefix(String prefix)
   {
      this.prefix = prefix;
   }

   public String getLastname()
   {
      return lastname;
   }

   public void setLastname(String lastname)
   {
      this.lastname = lastname;
   }

   public String getFirstname()
   {
      return firstname;
   }

   public void setFirstname(String firstname)
   {
      this.firstname = firstname;
   }

   public String getMiddlename()
   {
      return middlename;
   }

   public void setMiddlename(String middlename)
   {
      this.middlename = middlename;
   }

   public String getSuffix()
   {
      return suffix;
   }

   public void setSuffix(String suffix)
   {
      this.suffix = suffix;
   }

   public String getEmail()
   {
      return email;
   }

   public void setEmail(String email)
   {
      this.email = email;
   }
}
