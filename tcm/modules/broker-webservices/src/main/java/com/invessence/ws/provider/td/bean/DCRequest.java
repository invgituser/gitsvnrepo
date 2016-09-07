package com.invessence.ws.provider.td.bean;

/**
 * Created by abhangp on 8/18/2016.
 */
public class DCRequest
{
   private Long acctnum;
   private Long reqId;
   private String reqType;
   private String envelopeId;
   private String status;
   private Integer eventNum;
      private String envelopeHeading;

   public String getEnvelopeHeading()
   {
      return envelopeHeading;
   }

   public void setEnvelopeHeading(String envelopeHeading)
   {
      this.envelopeHeading = envelopeHeading;
   }

   public Integer getEventNum()
   {
      return eventNum;
   }

   public void setEventNum(Integer eventNum)
   {
      this.eventNum = eventNum;
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

   @Override
   public String toString()
   {
      return "DCRequest{" +
         "acctnum=" + acctnum +
         ", reqId=" + reqId +
         ", reqType='" + reqType + '\'' +
         ", envelopeId='" + envelopeId + '\'' +
         ", status='" + status + '\'' +
         ", eventNum=" + eventNum +
         ", envelopeHeading='" + envelopeHeading + '\'' +
         '}';
   }
}
