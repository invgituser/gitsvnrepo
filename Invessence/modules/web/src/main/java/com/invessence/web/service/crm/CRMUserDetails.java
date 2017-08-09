package com.invessence.web.service.crm;

/**
 * Created by abhangp on 12/15/2016.
 */
public class CRMUserDetails
{
   private Long logonid;
   private String crmUserId;
   private String crmPwd;
   private String crmStatus;
   private String crmUserKey;
   private String userid;
   private String logonstatus;
   private String prefix;
   private String lastname;
   private String firstname;
   private String middlename;
   private String suffix;
   private String email;
   private String opt;

   public CRMUserDetails()
   {
   }

   public CRMUserDetails(Long logonid, String crmUserId, String crmPwd, String crmUserKey, String email)
   {
      this.logonid = logonid;
      this.crmUserId = crmUserId;
      this.crmPwd = crmPwd;
      this.crmUserKey = crmUserKey;
      this.email = email;
   }

   public String getCrmUserKey()
   {
      return crmUserKey;
   }

   public void setCrmUserKey(String crmUserKey)
   {
      this.crmUserKey = crmUserKey;
   }

   public Long getLogonid()
   {
      return logonid;
   }

   public void setLogonid(Long logonid)
   {
      this.logonid = logonid;
   }

   public String getCrmUserId()
   {
      return crmUserId;
   }

   public void setCrmUserId(String crmUserId)
   {
      this.crmUserId = crmUserId;
   }

   public String getCrmPwd()
   {
      return crmPwd;
   }

   public void setCrmPwd(String crmPwd)
   {
      this.crmPwd = crmPwd;
   }

   public String getCrmStatus()
   {
      return crmStatus;
   }

   public void setCrmStatus(String crmStatus)
   {
      this.crmStatus = crmStatus;
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

   public String getOpt()
   {
      return opt;
   }

   public void setOpt(String opt)
   {
      this.opt = opt;
   }

   @Override
   public String toString()
   {
      return "CRMUserDetails{" +
         "logonid=" + logonid +
         ", crmUserId='" + crmUserId + '\'' +
         ", crmPwd='" + crmPwd + '\'' +
         ", crmStatus='" + crmStatus + '\'' +
         ", userid='" + userid + '\'' +
         ", logonstatus='" + logonstatus + '\'' +
         ", prefix='" + prefix + '\'' +
         ", lastname='" + lastname + '\'' +
         ", firstname='" + firstname + '\'' +
         ", middlename='" + middlename + '\'' +
         ", suffix='" + suffix + '\'' +
         ", email='" + email + '\'' +
         ", opt='" + opt + '\'' +
         '}';
   }
}
