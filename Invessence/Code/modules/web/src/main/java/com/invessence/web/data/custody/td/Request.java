package com.invessence.web.data.custody.td;

import java.util.Date;

public class Request
{
      private Long reqId;
      private Long acctnum;
      private Integer eventNum;
      private String reqType;
      private String envelopeHeading;
      private String envelopeId;
      private String status;
      private Date created;
      private Date updated;
      private String terminalDetails;
      private String requestFor;

   public Request()
   {
   }

   public Request(Long reqId, Long acctnum)
   {
      this.reqId = reqId;
      this.acctnum = acctnum;
   }

   public Long getAcctnum()
   {
      return acctnum;
   }

   public void setAcctnum(Long acctnum)
   {
      this.acctnum = acctnum;
   }

   public Long getReqId()
   {
      return reqId;
   }

   public void setReqId(Long reqId)
   {
      this.reqId = reqId;
   }

   public Integer getEventNum()
   {
      return eventNum;
   }

   public void setEventNum(Integer eventNum)
   {
      this.eventNum = eventNum;
   }

   public String getReqType()
   {
      return reqType;
   }

   public void setReqType(String reqType)
   {
      this.reqType = reqType;
   }

   public String getEnvelopeHeading()
   {
      return envelopeHeading;
   }

   public void setEnvelopeHeading(String envelopeHeading)
   {
      this.envelopeHeading = envelopeHeading;
   }

   public String getEnvelopeId()
   {
      return envelopeId;
   }

   public void setEnvelopeId(String envelopeId)
   {
      this.envelopeId = envelopeId;
   }

   public String getStatus()
   {
      return status;
   }

   public void setStatus(String status)
   {
      this.status = status;
   }

   public Date getCreated()
   {
      return created;
   }

   public void setCreated(Date created)
   {
      this.created = created;
   }

   public Date getUpdated()
   {
      return updated;
   }

   public void setUpdated(Date updated)
   {
      this.updated = updated;
   }

   public String getTerminalDetails()
   {
      return terminalDetails;
   }

   public void setTerminalDetails(String terminalDetails)
   {
      this.terminalDetails = terminalDetails;
   }

   public String getRequestFor()
   {
      return requestFor;
   }

   public void setRequestFor(String requestFor)
   {
      this.requestFor = requestFor;
   }
}
