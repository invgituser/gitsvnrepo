package com.invessence.ws.provider.td.bean;

import java.util.List;

/**
 * Created by abhangp on 8/18/2016.
 */
public class DCRequest
{
   private Long acctnum;
   private Long reqId;
   private Long refReqId;
   private String reqType;
   private String envelopeId;
   private String status;
   private Integer eventNum;
   private String envelopeHeading;
   private Integer seqNo;
   private String formType;
   private AcctDetails acctDetails;
   private List<MoveMoneyDetails> moveMoneyDetails;
   private ElecFundTransferDetails elecFundTransferDetails;
   private AcctTransferDetails acctTransferDetails;
   private TDTransferDetails tdTransferDetails;
   private GetAcctChngAddrDetails getAcctChngAddrDetails;

   public Long getRefReqId()
   {
      return refReqId;
   }

   public void setRefReqId(Long refReqId)
   {
      this.refReqId = refReqId;
   }

   public Integer getSeqNo()
   {
      return seqNo;
   }

   public void setSeqNo(Integer seqNo)
   {
      this.seqNo = seqNo;
   }

   public String getFormType()
   {
      return formType;
   }

   public void setFormType(String formType)
   {
      this.formType = formType;
   }

   public List<MoveMoneyDetails> getMoveMoneyDetails()
   {
      return moveMoneyDetails;
   }

   public void setMoveMoneyDetails(List<MoveMoneyDetails> moveMoneyDetails)
   {
      this.moveMoneyDetails = moveMoneyDetails;
   }

   public ElecFundTransferDetails getElecFundTransferDetails()
   {
      return elecFundTransferDetails;
   }

   public void setElecFundTransferDetails(ElecFundTransferDetails elecFundTransferDetails)
   {
      this.elecFundTransferDetails = elecFundTransferDetails;
   }

   public AcctTransferDetails getAcctTransferDetails()
   {
      return acctTransferDetails;
   }

   public void setAcctTransferDetails(AcctTransferDetails acctTransferDetails)
   {
      this.acctTransferDetails = acctTransferDetails;
   }

   public TDTransferDetails getTdTransferDetails()
   {
      return tdTransferDetails;
   }

   public void setTdTransferDetails(TDTransferDetails tdTransferDetails)
   {
      this.tdTransferDetails = tdTransferDetails;
   }

   public GetAcctChngAddrDetails getGetAcctChngAddrDetails()
   {
      return getAcctChngAddrDetails;
   }

   public void setGetAcctChngAddrDetails(GetAcctChngAddrDetails getAcctChngAddrDetails)
   {
      this.getAcctChngAddrDetails = getAcctChngAddrDetails;
   }

   public AcctDetails getAcctDetails()
   {
      return acctDetails;
   }

   public void setAcctDetails(AcctDetails acctDetails)
   {
      this.acctDetails = acctDetails;
   }

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
         ", refReqId=" + refReqId +
         ", reqType='" + reqType + '\'' +
         ", envelopeId='" + envelopeId + '\'' +
         ", status='" + status + '\'' +
         ", eventNum=" + eventNum +
         ", envelopeHeading='" + envelopeHeading + '\'' +
         ", seqNo=" + seqNo +
         ", formType='" + formType + '\'' +
         '}';
   }
}
