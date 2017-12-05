package com.invessence.ws.provider.td.bean;

/**
 * Created by abhangp on 9/13/2016.
 */
public class AcctTransferDetails
{
   private Long acctnum;
   private Long reqId;
   private String accountTitle;
   private String ssn;
   private String accountType;
   private String eePlanType;
   private String otherAccountType;
   private String fromAccountTitle;
   private String accountNumber2;
   private String fromAccountType;
   private String fromFirmAddress;
   private String fromFirmPhoneNumber;
   private String fromEEPlanType;
   private String simpleFunded;
   private String fromOtherAccountType;
   private String transferTypeId;
   private String contraFirmList;

   @Override
   public String toString()
   {
      return "AcctTransferDetails{" +
         "acctnum=" + acctnum +
         ", reqId=" + reqId +
         ", accountTitle='" + accountTitle + '\'' +
         ", ssn='" + ssn + '\'' +
         ", accountType='" + accountType + '\'' +
         ", eePlanType='" + eePlanType + '\'' +
         ", otherAccountType='" + otherAccountType + '\'' +
         ", fromAccountTitle='" + fromAccountTitle + '\'' +
         ", accountNumber2='" + accountNumber2 + '\'' +
         ", fromAccountType='" + fromAccountType + '\'' +
         ", fromFirmAddress='" + fromFirmAddress + '\'' +
         ", fromFirmPhoneNumber='" + fromFirmPhoneNumber + '\'' +
         ", fromEEPlanType='" + fromEEPlanType + '\'' +
         ", simpleFunded='" + simpleFunded + '\'' +
         ", fromOtherAccountType='" + fromOtherAccountType + '\'' +
         ", transferTypeId='" + transferTypeId + '\'' +
         ", contraFirmList='" + contraFirmList + '\'' +
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

   public String getAccountTitle()
   {
      return accountTitle;
   }

   public void setAccountTitle(String accountTitle)
   {
      this.accountTitle = accountTitle;
   }

   public String getSsn()
   {
      return ssn;
   }

   public void setSsn(String ssn)
   {
      this.ssn = ssn;
   }

   public String getAccountType()
   {
      return accountType;
   }

   public void setAccountType(String accountType)
   {
      this.accountType = accountType;
   }

   public String getEePlanType()
   {
      return eePlanType;
   }

   public void setEePlanType(String eePlanType)
   {
      this.eePlanType = eePlanType;
   }

   public String getOtherAccountType()
   {
      return otherAccountType;
   }

   public void setOtherAccountType(String otherAccountType)
   {
      this.otherAccountType = otherAccountType;
   }

   public String getFromAccountTitle()
   {
      return fromAccountTitle;
   }

   public void setFromAccountTitle(String fromAccountTitle)
   {
      this.fromAccountTitle = fromAccountTitle;
   }

   public String getAccountNumber2()
   {
      return accountNumber2;
   }

   public void setAccountNumber2(String accountNumber2)
   {
      this.accountNumber2 = accountNumber2;
   }

   public String getFromAccountType()
   {
      return fromAccountType;
   }

   public void setFromAccountType(String fromAccountType)
   {
      this.fromAccountType = fromAccountType;
   }

   public String getFromFirmAddress()
   {
      return fromFirmAddress;
   }

   public void setFromFirmAddress(String fromFirmAddress)
   {
      this.fromFirmAddress = fromFirmAddress;
   }

   public String getFromFirmPhoneNumber()
   {
      return fromFirmPhoneNumber;
   }

   public void setFromFirmPhoneNumber(String fromFirmPhoneNumber)
   {
      this.fromFirmPhoneNumber = fromFirmPhoneNumber;
   }

   public String getFromEEPlanType()
   {
      return fromEEPlanType;
   }

   public void setFromEEPlanType(String fromEEPlanType)
   {
      this.fromEEPlanType = fromEEPlanType;
   }

   public String getSimpleFunded()
   {
      return simpleFunded;
   }

   public void setSimpleFunded(String simpleFunded)
   {
      this.simpleFunded = simpleFunded;
   }

   public String getFromOtherAccountType()
   {
      return fromOtherAccountType;
   }

   public void setFromOtherAccountType(String fromOtherAccountType)
   {
      this.fromOtherAccountType = fromOtherAccountType;
   }

   public String getTransferTypeId()
   {
      return transferTypeId;
   }

   public void setTransferTypeId(String transferTypeId)
   {
      this.transferTypeId = transferTypeId;
   }

   public String getContraFirmList()
   {
      return contraFirmList;
   }

   public void setContraFirmList(String contraFirmList)
   {
      this.contraFirmList = contraFirmList;
   }
}
