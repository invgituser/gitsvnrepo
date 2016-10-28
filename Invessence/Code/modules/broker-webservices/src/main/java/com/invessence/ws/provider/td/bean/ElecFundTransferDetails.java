package com.invessence.ws.provider.td.bean;

/**
 * Created by abhangp on 9/13/2016.
 */
public class ElecFundTransferDetails
{
   private Long acctnum;
   private Long reqId;
   private String eftInstId;
   private String tdaiAcctNum;
   private String directionId;
   private Long moveMoneyPayMethodID;
   private Integer achId;
   private String tranStartDate;
   private Double tranAmount;
   private String bankAcctType;
   private String bankName;
   private String bankABARouting;
   private String bankCityState;
   private String bankPhoneNumber;
   private String bankAcctName;
   private String bankAcctNumber;
   private String tranFreqId;

   @Override
   public String toString()
   {
      return "ElecFundTransferDetails{" +
         "acctnum=" + acctnum +
         ", reqId=" + reqId +
         ", eftInstId='" + eftInstId + '\'' +
         ", tdaiAcctNum='" + tdaiAcctNum + '\'' +
         ", directionId='" + directionId + '\'' +
         ", moveMoneyPayMethodID=" + moveMoneyPayMethodID +
         ", achId=" + achId +
         ", tranStartDate='" + tranStartDate + '\'' +
         ", tranAmount=" + tranAmount +
         ", bankAcctType='" + bankAcctType + '\'' +
         ", bankName='" + bankName + '\'' +
         ", bankABARouting='" + bankABARouting + '\'' +
         ", bankCityState='" + bankCityState + '\'' +
         ", bankPhoneNumber='" + bankPhoneNumber + '\'' +
         ", bankAcctName='" + bankAcctName + '\'' +
         ", bankAcctNumber='" + bankAcctNumber + '\'' +
         ", tranFreqId='" + tranFreqId + '\'' +
         '}';
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

   public String getEftInstId()
   {
      return eftInstId;
   }

   public void setEftInstId(String eftInstId)
   {
      this.eftInstId = eftInstId;
   }

   public String getTdaiAcctNum()
   {
      return tdaiAcctNum;
   }

   public void setTdaiAcctNum(String tdaiAcctNum)
   {
      this.tdaiAcctNum = tdaiAcctNum;
   }

   public String getDirectionId()
   {
      return directionId;
   }

   public void setDirectionId(String directionId)
   {
      this.directionId = directionId;
   }

   public Long getMoveMoneyPayMethodID()
   {
      return moveMoneyPayMethodID;
   }

   public void setMoveMoneyPayMethodID(Long moveMoneyPayMethodID)
   {
      this.moveMoneyPayMethodID = moveMoneyPayMethodID;
   }

   public Integer getAchId()
   {
      return achId;
   }

   public void setAchId(Integer achId)
   {
      this.achId = achId;
   }

   public String getTranStartDate()
   {
      return tranStartDate;
   }

   public void setTranStartDate(String tranStartDate)
   {
      this.tranStartDate = tranStartDate;
   }

   public Double getTranAmount()
   {
      return tranAmount;
   }

   public void setTranAmount(Double tranAmount)
   {
      this.tranAmount = tranAmount;
   }

   public String getBankAcctType()
   {
      return bankAcctType;
   }

   public void setBankAcctType(String bankAcctType)
   {
      this.bankAcctType = bankAcctType;
   }

   public String getBankName()
   {
      return bankName;
   }

   public void setBankName(String bankName)
   {
      this.bankName = bankName;
   }

   public String getBankABARouting()
   {
      return bankABARouting;
   }

   public void setBankABARouting(String bankABARouting)
   {
      this.bankABARouting = bankABARouting;
   }

   public String getBankCityState()
   {
      return bankCityState;
   }

   public void setBankCityState(String bankCityState)
   {
      this.bankCityState = bankCityState;
   }

   public String getBankPhoneNumber()
   {
      return bankPhoneNumber;
   }

   public void setBankPhoneNumber(String bankPhoneNumber)
   {
      this.bankPhoneNumber = bankPhoneNumber;
   }

   public String getBankAcctName()
   {
      return bankAcctName;
   }

   public void setBankAcctName(String bankAcctName)
   {
      this.bankAcctName = bankAcctName;
   }

   public String getBankAcctNumber()
   {
      return bankAcctNumber;
   }

   public void setBankAcctNumber(String bankAcctNumber)
   {
      this.bankAcctNumber = bankAcctNumber;
   }

   public String getTranFreqId()
   {
      return tranFreqId;
   }

   public void setTranFreqId(String tranFreqId)
   {
      this.tranFreqId = tranFreqId;
   }
}
