package com.invessence.web.data.custody;

import java.util.Date;

public class TDRequest
{
      private Long acctnum;
      private Long reqId;
      private String reqType;
      private String envelopeId;
      private String status;
      private Date created;
      private Date updated;
      private String terminalDetails;

   public TDRequest()
   {
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

   public String getReqType()
   {
      return reqType;
   }

   public void setReqType(String reqType)
   {
      this.reqType = reqType;
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
}
