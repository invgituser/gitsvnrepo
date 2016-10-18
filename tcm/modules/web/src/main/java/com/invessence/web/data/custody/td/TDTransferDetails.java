package com.invessence.web.data.custody.td;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 9/2/16
 * Time: 10:39 PM
 * To change this template use File | Settings | File Templates.
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
   private String advisorID;;
   private boolean removeAdvisor;
   private boolean addAdvisor;
   private String ssn;
   private String retilFlag;

   public TDTransferDetails()
   {
   }

   public TDTransferDetails(Long acctnum, Long reqId)
   {
      this.acctnum = acctnum;
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

   public String getAdvisorID()
   {
      return advisorID;
   }

   public void setAdvisorID(String advisorID)
   {
      this.advisorID = advisorID;
   }

   public boolean isRemoveAdvisor()
   {
      return removeAdvisor;
   }

   public void setRemoveAdvisor(boolean removeAdvisor)
   {
      this.removeAdvisor = removeAdvisor;
   }

   public boolean isAddAdvisor()
   {
      return addAdvisor;
   }

   public void setAddAdvisor(boolean addAdvisor)
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

   public String getRetilFlag()
   {
      return retilFlag;
   }

   public void setRetilFlag(String retilFlag)
   {
      this.retilFlag = retilFlag;
   }
}
