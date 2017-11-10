package com.invessence.custody.uob.data;

/**
 * Created by abhangp on 10/24/2017.
 */
public class AccountDetails
{
   private Long acctnum;
   private String clientAccountID;
   private String repId;
   private String caseNumber;
   private Long advisorId;
   private String acctTypeId;

   private AccountMiscDetails accountMiscDetails;


   public Long getAcctnum()
   {
      return acctnum;
   }

   public void setAcctnum(Long acctnum)
   {
      this.acctnum = acctnum;
   }

   public String getClientAccountID()
   {
      return clientAccountID;
   }

   public void setClientAccountID(String clientAccountID)
   {
      this.clientAccountID = clientAccountID;
   }

   public String getRepId()
   {
      return repId;
   }

   public void setRepId(String repId)
   {
      this.repId = repId;
   }

   public String getCaseNumber()
   {
      return caseNumber;
   }

   public void setCaseNumber(String caseNumber)
   {
      this.caseNumber = caseNumber;
   }

   public Long getAdvisorId()
   {
      return advisorId;
   }

   public void setAdvisorId(Long advisorId)
   {
      this.advisorId = advisorId;
   }

   public String getAcctTypeId()
   {
      return acctTypeId;
   }

   public void setAcctTypeId(String acctTypeId)
   {
      this.acctTypeId = acctTypeId;
   }

   @Override
   public String toString()
   {
      return "AccountDetails{" +
         "acctnum=" + acctnum +
         ", clientAccountID='" + clientAccountID + '\'' +
         ", repId='" + repId + '\'' +
         ", caseNumber='" + caseNumber + '\'' +
         ", advisorId=" + advisorId +
         ", acctTypeId='" + acctTypeId + '\'' +
         ", accountMiscDetails=" + accountMiscDetails +
         '}';
   }
}
