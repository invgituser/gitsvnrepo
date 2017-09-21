package com.invessence.web.dao;

/**
 * Created by abhangp on 9/12/2017.
 */
public class LoginAudit
{
   private Long id;
   private Long logonid;
   private String userid;
   private String sessionid;
   private String ip;
   private String status;
   private String remarks;
   private String logoutWay;

   public LoginAudit(Long id, Long logonid,String userid, String sessionid, String ip, String status, String remarks, String logoutWay)
   {
      this.id = id;
      this.logonid = logonid;
      this.userid=userid;
      this.sessionid = sessionid;
      this.ip = ip;
      this.status = status;
      this.remarks = remarks;
      this.logoutWay = logoutWay;
   }

   public String getUserid()
   {
      return userid;
   }

   public void setUserid(String userid)
   {
      this.userid = userid;
   }

   public String getLogoutWay()
   {
      return logoutWay;
   }

   public void setLogoutWay(String logoutWay)
   {
      this.logoutWay = logoutWay;
   }

   public String getRemarks()
   {
      return remarks;
   }

   public void setRemarks(String remarks)
   {
      this.remarks = remarks;
   }

   public Long getId()
   {
      return id;
   }

   public void setId(Long id)
   {
      this.id = id;
   }

   public Long getLogonid()
   {
      return logonid;
   }

   public void setLogonid(Long logonid)
   {
      this.logonid = logonid;
   }

   public String getSessionid()
   {
      return sessionid;
   }

   public void setSessionid(String sessionid)
   {
      this.sessionid = sessionid;
   }

   public String getIp()
   {
      return ip;
   }

   public void setIp(String ip)
   {
      this.ip = ip;
   }

   public String getStatus()
   {
      return status;
   }

   public void setStatus(String status)
   {
      this.status = status;
   }
}
