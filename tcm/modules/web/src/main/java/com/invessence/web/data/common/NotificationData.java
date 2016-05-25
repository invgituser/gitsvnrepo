package com.invessence.web.data.common;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 6/17/15
 * Time: 1:27 PM
 * To change this template use File | Settings | File Templates.
 */
public class NotificationData
{
   Long   messageid;
   String status;
   Long advisorlogonid;
   String advisor;
   Long acctnum;
   String noticetype;
   String tagid;
   String businessdate;
   String message;
   String color;
   String javaDate;
   final String JAVA_FORMAT = "EEE MMM dd HH:mm:ss z yyyy";
   final String SQL_FORMAT = "yyyy/MM/dd HH:mm";

   public NotificationData()
   {
   }

   public NotificationData(Long messageid, String status, Long advisorlogonid, String advisor, Long acctnum, String noticetype, String tagid, String businessdate, String message)
   {
      this.messageid = messageid;
      this.status = status;
      this.advisorlogonid = advisorlogonid;
      this.advisor = advisor;
      this.acctnum = acctnum;
      setNoticetype(noticetype);
      this.tagid = tagid;
      this.businessdate = businessdate;
      this.message = message;
   }

   public Long getMessageid()
   {
      return messageid;
   }

   public void setMessageid(Long messageid)
   {
      this.messageid = messageid;
   }

   public String getStatus()
   {
      return status;
   }

   public void setStatus(String status)
   {
      this.status = status;
   }

   public Long getAdvisorlogonid()
   {
      return advisorlogonid;
   }

   public void setAdvisorlogonid(Long advisorlogonid)
   {
      this.advisorlogonid = advisorlogonid;
   }

   public String getAdvisor()
   {
      return advisor;
   }

   public void setAdvisor(String advisor)
   {
      this.advisor = advisor;
   }

   public Long getAcctnum()
   {
      return acctnum;
   }

   public void setAcctnum(Long acctnum)
   {
      this.acctnum = acctnum;
   }

   public String getNoticetype()
   {
      return noticetype;
   }

   public void setNoticetype(String noticetype)
   {
      this.noticetype = noticetype;
      if (isError())
         color="#990000";
      if (isWarning())
         color="#FFFF99";
      if (isInfo())
         color="#fffffff";
   }

   public String getTagid()
   {
      return tagid;
   }

   public void setTagid(String tagid)
   {
      this.tagid = tagid;
   }

   public String getBusinessdate()
   {
      return businessdate;
   }

   public void setBusinessdate(String businessdate)
   {
      this.businessdate = businessdate;
   }

   public String getJavaDate()
   {
      return javaDate;
/*
      try {
      if (businessdate != null) {
         SimpleDateFormat sdf = new SimpleDateFormat(SQL_FORMAT);
         Date d = sdf.parse(businessdate);
         sdf.applyPattern(JAVA_FORMAT);
         javaDate = sdf.format(d);
         return javaDate;
      }
      else
         return null;
      }
      catch (Exception ex) {

      }
      return null;
*/
   }

   public void setJavaDate(String javaDate)
   {
      this.javaDate = javaDate;
      try {
         SimpleDateFormat sdf = new SimpleDateFormat(JAVA_FORMAT);
         Date d = sdf.parse(javaDate);
         sdf.applyPattern(SQL_FORMAT);
         businessdate = sdf.format(d);
      }
      catch (Exception ex) {

      }
   }

   public String getMessage()
   {
      return message;
   }

   public void setMessage(String message)
   {
      this.message = message;
   }

   public Boolean isError() {
      if (noticetype != null)
         if (noticetype.toUpperCase().startsWith("E") || noticetype.toUpperCase().startsWith("1"))
            return true;

      return false;
   }

   public Boolean isWarning() {
      if (noticetype != null)
         if (noticetype.toUpperCase().startsWith("W"))
            return true;

      return false;
   }

   public Boolean isInfo() {
      if (noticetype != null)
         if (noticetype.toUpperCase().startsWith("I"))
            return true;

      return false;
   }

   public String getColor()
   {
      return color;
   }
}
