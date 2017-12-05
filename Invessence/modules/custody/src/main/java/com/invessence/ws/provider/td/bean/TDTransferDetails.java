package com.invessence.ws.provider.td.bean;

/**
 * Created by abhangp on 10/13/2016.
 */
public class TDTransferDetails
{
   private Long acctnum;
   private Long reqId;
   private String accountTitle;
   private String firmName;
   private String primaryContact;
   private String priorFirmName;
   private String retailAccountNumber;
   private String isBrokerAcct;
   private String advisorID;
   private String removeAdvisor;
   private String addAdvisor;
   private String ssn;
   private String naAcctNumber;

   @Override
   public String toString()
   {
      return "TDTransferDetails{" +
         "acctnum=" + acctnum +
         ", reqId=" + reqId +
         ", accountTitle='" + accountTitle + '\'' +
         ", firmName='" + firmName + '\'' +
         ", primaryContact='" + primaryContact + '\'' +
         ", priorFirmName='" + priorFirmName + '\'' +
         ", retailAccountNumber='" + retailAccountNumber + '\'' +
         ", isBrokerAcct='" + isBrokerAcct + '\'' +
         ", advisorID='" + advisorID + '\'' +
         ", removeAdvisor='" + removeAdvisor + '\'' +
         ", addAdvisor='" + addAdvisor + '\'' +
         ", ssn='" + ssn + '\'' +
         '}';
   }

   public String getNaAcctNumber()
   {
      return naAcctNumber;
   }

   public void setNaAcctNumber(String naAcctNumber)
   {
      this.naAcctNumber = naAcctNumber;
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

   public String getFirmName()
   {
      return firmName;
   }

   public void setFirmName(String firmName)
   {
      this.firmName = firmName;
   }

   public String getPrimaryContact()
   {
      return primaryContact;
   }

   public void setPrimaryContact(String primaryContact)
   {
      this.primaryContact = primaryContact;
   }

   public String getPriorFirmName()
   {
      return priorFirmName;
   }

   public void setPriorFirmName(String priorFirmName)
   {
      this.priorFirmName = priorFirmName;
   }

   public String getRetailAccountNumber()
   {
      return retailAccountNumber;
   }

   public void setRetailAccountNumber(String retailAccountNumber)
   {
      this.retailAccountNumber = retailAccountNumber;
   }

   public String getIsBrokerAcct()
   {
      return isBrokerAcct;
   }

   public void setIsBrokerAcct(String isBrokerAcct)
   {
      this.isBrokerAcct = isBrokerAcct;
   }

   public String getAdvisorID()
   {
      return advisorID;
   }

   public void setAdvisorID(String advisorID)
   {
      this.advisorID = advisorID;
   }

   public String getRemoveAdvisor()
   {
      return removeAdvisor;
   }

   public void setRemoveAdvisor(String removeAdvisor)
   {
      this.removeAdvisor = removeAdvisor;
   }

   public String getAddAdvisor()
   {
      return addAdvisor;
   }

   public void setAddAdvisor(String addAdvisor)
   {
      this.addAdvisor = addAdvisor;
   }

   public String getSsn()
   {
      return ssn;
   }

   public void setSsn(String ssn)
   {
      this.ssn = ssn;
   }
}
