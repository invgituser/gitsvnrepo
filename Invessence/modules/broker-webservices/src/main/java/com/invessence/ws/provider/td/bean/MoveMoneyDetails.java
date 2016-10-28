package com.invessence.ws.provider.td.bean;

/**
 * Created by abhangp on 8/31/2016.
 */
public class MoveMoneyDetails
{private Long reqId;
   private Long acctnum;
   private Integer eventNum;
   private String reqType;
   private String envelopeId;
   private String reqStatus;
   private String requestType;
   private Long moveMoneyPayMethId;
   private String payMethod;
   private String payStatus;

   public Long getReqId()
   {
      return reqId;
   }

   public void setReqId(Long reqId)
   {
      this.reqId = reqId;
   }

   public Long getAcctnum()
   {
      return acctnum;
   }

   public void setAcctnum(Long acctnum)
   {
      this.acctnum = acctnum;
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

   public String getEnvelopeId()
   {
      return envelopeId;
   }

   public void setEnvelopeId(String envelopeId)
   {
      this.envelopeId = envelopeId;
   }

   public String getReqStatus()
   {
      return reqStatus;
   }

   public void setReqStatus(String reqStatus)
   {
      this.reqStatus = reqStatus;
   }

   public String getRequestType()
   {
      return requestType;
   }

   public void setRequestType(String requestType)
   {
      this.requestType = requestType;
   }

   public Long getMoveMoneyPayMethId()
   {
      return moveMoneyPayMethId;
   }

   public void setMoveMoneyPayMethId(Long moveMoneyPayMethId)
   {
      this.moveMoneyPayMethId = moveMoneyPayMethId;
   }

   public String getPayMethod()
   {
      return payMethod;
   }

   public void setPayMethod(String payMethod)
   {
      this.payMethod = payMethod;
   }

   public String getPayStatus()
   {
      return payStatus;
   }

   public void setPayStatus(String payStatus)
   {
      this.payStatus = payStatus;
   }
}
