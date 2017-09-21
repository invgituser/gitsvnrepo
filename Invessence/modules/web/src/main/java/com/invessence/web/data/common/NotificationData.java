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
   String rep;
   Long acctnum;
   String noticetype;
   String tagid;
   String businessdate;
   String message;
   String link;
   String color;
   String javaDate;
   final String JAVA_FORMAT = "EEE MMM dd HH:mm:ss z yyyy";
   final String SQL_FORMAT = "yyyy/MM/dd HH:mm";
   String clientAccountID;
   boolean linkAvailable;

   public NotificationData()
   {
   }

   public NotificationData(Long messageid, String status,
                           Long advisorlogonid,
                           String advisor, String rep,
                           Long acctnum, String noticetype,
                           String tagid, String businessdate,
                           String message, String link)
   {
      this.messageid = messageid;
      this.status = status;
      this.advisorlogonid = advisorlogonid;
      this.advisor = advisor;
      this.rep = rep;
      this.acctnum = acctnum;
      setNoticetype(noticetype);
      this.tagid = tagid;
      this.businessdate = businessdate;
      this.message = message;
      this.link = link;
   }

   public NotificationData(Long messageid, String status,
                           Long advisorlogonid,
                           String advisor, String rep,
                           Long acctnum, String noticetype,
                           String tagid, String businessdate,
                           String message, String link,String clientAccountID)
   {
      this.messageid = messageid;
      this.status = status;
      this.advisorlogonid = advisorlogonid;
      this.advisor = advisor;
      this.rep = rep;
      this.acctnum = acctnum;
      setNoticetype(noticetype);
      this.tagid = tagid;
      this.businessdate = businessdate;
      this.message = message;
      this.link = link;
      this.clientAccountID=clientAccountID;
      if(this.link==null){
         this.linkAvailable=false;
      }else{
         this.linkAvailable=true;
      }
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

   public String getRep()
   {
      return rep;
   }

   public void setRep(String rep)
   {
      this.rep = rep;
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

   public String getLink()
   {
      return link;
   }

   public void setLink(String link)
   {
      this.link = link;
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

   public String getClientAccountID()
   {
      return clientAccountID;
   }

   public void setClientAccountID(String clientAccountID)
   {
      this.clientAccountID = clientAccountID;
   }

   public boolean isLinkAvailable()
   {
      return linkAvailable;
   }

   public void setLinkAvailable(boolean linkAvailable)
   {
      this.linkAvailable = linkAvailable;
   }
}
