package com.invessence.ws.provider.td.bean;

import java.util.List;

/**
 * Created by abhangp on 8/22/2016.
 */
public class AcctDetails
{
   private Long acctnum;
   private String clientAccountID;
   private String caseNumber;
   private Long advisorId;
   private String advisorCode;
   private String firmName;
   private String primaryContact;
   private String accountType;
   private String cashsweepvehiclechoice;
   private String divIntPref;
   private String monthlyStatements;
   private String tradeConfirmations;
   private String dupStatement;
   private String dupTradeConfirm;
   private String proxy;

   private List<AcctOwnerDetails> acctOwnerDetails;
   private List<BenefiaciaryDetails> benefiaciaryDetails;
   private DupDocReqParty dupDocReqParty;

   public String getAdvisorCode()
   {
      return advisorCode;
   }

   public void setAdvisorCode(String advisorCode)
   {
      this.advisorCode = advisorCode;
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

   public List<BenefiaciaryDetails> getBenefiaciaryDetails()
   {
      return benefiaciaryDetails;
   }

   public void setBenefiaciaryDetails(List<BenefiaciaryDetails> benefiaciaryDetails)
   {
      this.benefiaciaryDetails = benefiaciaryDetails;
   }

   public DupDocReqParty getDupDocReqParty()
   {
      return dupDocReqParty;
   }

   public void setDupDocReqParty(DupDocReqParty dupDocReqParty)
   {
      this.dupDocReqParty = dupDocReqParty;
   }

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

   public String getAccountType()
   {
      return accountType;
   }

   public void setAccountType(String accountType)
   {
      this.accountType = accountType;
   }

   public String getCashsweepvehiclechoice()
   {
      return cashsweepvehiclechoice;
   }

   public void setCashsweepvehiclechoice(String cashsweepvehiclechoice)
   {
      this.cashsweepvehiclechoice = cashsweepvehiclechoice;
   }

   public String getDivIntPref()
   {
      return divIntPref;
   }

   public void setDivIntPref(String divIntPref)
   {
      this.divIntPref = divIntPref;
   }

   public String getMonthlyStatements()
   {
      return monthlyStatements;
   }

   public void setMonthlyStatements(String monthlyStatements)
   {
      this.monthlyStatements = monthlyStatements;
   }

   public String getTradeConfirmations()
   {
      return tradeConfirmations;
   }

   public void setTradeConfirmations(String tradeConfirmations)
   {
      this.tradeConfirmations = tradeConfirmations;
   }

   public String getDupStatement()
   {
      return dupStatement;
   }

   public void setDupStatement(String dupStatement)
   {
      this.dupStatement = dupStatement;
   }

   public String getDupTradeConfirm()
   {
      return dupTradeConfirm;
   }

   public void setDupTradeConfirm(String dupTradeConfirm)
   {
      this.dupTradeConfirm = dupTradeConfirm;
   }

   public String getProxy()
   {
      return proxy;
   }

   public void setProxy(String proxy)
   {
      this.proxy = proxy;
   }

   public List<AcctOwnerDetails> getAcctOwnerDetails()
   {
      return acctOwnerDetails;
   }

   public void setAcctOwnerDetails(List<AcctOwnerDetails> acctOwnerDetails)
   {
      this.acctOwnerDetails = acctOwnerDetails;
   }

   @Override
   public String toString()
   {
      return "AcctDetails{" +
         "acctnum=" + acctnum +
         ", clientAccountID='" + clientAccountID + '\'' +
         ", caseNumber='" + caseNumber + '\'' +
         ", advisorId=" + advisorId +
         ", advisorCode='" + advisorCode + '\'' +
         ", firmName='" + firmName + '\'' +
         ", primaryContact='" + primaryContact + '\'' +
         ", accountType='" + accountType + '\'' +
         ", cashsweepvehiclechoice='" + cashsweepvehiclechoice + '\'' +
         ", divIntPref='" + divIntPref + '\'' +
         ", monthlyStatements='" + monthlyStatements + '\'' +
         ", tradeConfirmations='" + tradeConfirmations + '\'' +
         ", dupStatement='" + dupStatement + '\'' +
         ", dupTradeConfirm='" + dupTradeConfirm + '\'' +
         ", proxy='" + proxy + '\'' +
         ", acctOwnerDetails=" + acctOwnerDetails +
         ", benefiaciaryDetails=" + benefiaciaryDetails +
         ", dupDocReqParty=" + dupDocReqParty +
         '}';
   }
}
