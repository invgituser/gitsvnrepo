package com.invessence.ws.bean;

import java.util.Date;

/**
 * Created by abhangp on 3/11/2016.
 */
public class UserAcctDetails
{
   private String clientAccountID, internalRepID, repNum, repName, email, invite, applicantFName, applicantMName,
      applicantLName, mailAddrs1, mailAddrs2, mailCity, mailState, mailZipCode, primaryPhoneNbr, initialCusip, ssn;
   private Long acctnum;
   private Double initialInvestment;
   private String accountType;
   private Date dateOfBirth;
   private Long webLogonID;
   private String userID, pwd, fundGroupName, securityQuestion, securityAnswer, status, remarks;
   private String opt;

   public String getOpt()
   {
      return opt;
   }

   public void setOpt(String opt)
   {
      this.opt = opt;
   }

   public String getAccountType()
   {
      return accountType;
   }

   public void setAccountType(String accountType)
   {
      this.accountType = accountType;
   }

   public Date getDateOfBirth()
   {
      return dateOfBirth;
   }

   public void setDateOfBirth(Date dateOfBirth)
   {
      this.dateOfBirth = dateOfBirth;
   }

   public Long getAcctnum()
   {
      return acctnum;
   }

   public void setAcctnum(Long acctnum)
   {
      this.acctnum = acctnum;
   }

   public String getApplicantFName()
   {
      return applicantFName;
   }

   public void setApplicantFName(String applicantFName)
   {
      this.applicantFName = applicantFName;
   }

   public String getApplicantLName()
   {
      return applicantLName;
   }

   public void setApplicantLName(String applicantLName)
   {
      this.applicantLName = applicantLName;
   }

   public String getApplicantMName()
   {
      return applicantMName;
   }

   public void setApplicantMName(String applicantMName)
   {
      this.applicantMName = applicantMName;
   }

   public String getClientAccountID()
   {
      return clientAccountID;
   }

   public void setClientAccountID(String clientAccountID)
   {
      this.clientAccountID = clientAccountID;
   }

   public String getEmail()
   {
      return email;
   }

   public void setEmail(String email)
   {
      this.email = email;
   }

   public String getInitialCusip()
   {
      return initialCusip;
   }

   public void setInitialCusip(String initialCusip)
   {
      this.initialCusip = initialCusip;
   }

   public Double getInitialInvestment()
   {
      return initialInvestment;
   }

   public void setInitialInvestment(Double initialInvestment)
   {
      this.initialInvestment = initialInvestment;
   }

   public String getInternalRepID()
   {
      return internalRepID;
   }

   public void setInternalRepID(String internalRepID)
   {
      this.internalRepID = internalRepID;
   }

   public String getInvite()
   {
      return invite;
   }

   public void setInvite(String invite)
   {
      this.invite = invite;
   }

   public String getMailAddrs1()
   {
      return mailAddrs1;
   }

   public void setMailAddrs1(String mailAddrs1)
   {
      this.mailAddrs1 = mailAddrs1;
   }

   public String getMailAddrs2()
   {
      return mailAddrs2;
   }

   public void setMailAddrs2(String mailAddrs2)
   {
      this.mailAddrs2 = mailAddrs2;
   }

   public String getMailCity()
   {
      return mailCity;
   }

   public void setMailCity(String mailCity)
   {
      this.mailCity = mailCity;
   }

   public String getMailState()
   {
      return mailState;
   }

   public void setMailState(String mailState)
   {
      this.mailState = mailState;
   }

   public String getMailZipCode()
   {
      return mailZipCode;
   }

   public void setMailZipCode(String mailZipCode)
   {
      this.mailZipCode = mailZipCode;
   }

   public String getPrimaryPhoneNbr()
   {
      return primaryPhoneNbr;
   }

   public void setPrimaryPhoneNbr(String primaryPhoneNbr)
   {
      this.primaryPhoneNbr = primaryPhoneNbr;
   }

   public String getRepName()
   {
      return repName;
   }

   public void setRepName(String repName)
   {
      this.repName = repName;
   }

   public String getRepNum()
   {
      return repNum;
   }

   public void setRepNum(String repNum)
   {
      this.repNum = repNum;
   }

   public String getSsn()
   {
      return ssn;
   }

   public void setSsn(String ssn)
   {
      this.ssn = ssn;
   }

   public String getFundGroupName()
   {
      return fundGroupName;
   }

   public void setFundGroupName(String fundGroupName)
   {
      this.fundGroupName = fundGroupName;
   }

   public String getPwd()
   {
      return pwd;
   }

   public void setPwd(String pwd)
   {
      this.pwd = pwd;
   }

   public String getRemarks()
   {
      return remarks;
   }

   public void setRemarks(String remarks)
   {
      this.remarks = remarks;
   }

   public String getSecurityAnswer()
   {
      return securityAnswer;
   }

   public void setSecurityAnswer(String securityAnswer)
   {
      this.securityAnswer = securityAnswer;
   }

   public String getSecurityQuestion()
   {
      return securityQuestion;
   }

   public void setSecurityQuestion(String securityQuestion)
   {
      this.securityQuestion = securityQuestion;
   }

   public String getStatus()
   {
      return status;
   }

   public void setStatus(String status)
   {
      this.status = status;
   }

   public String getUserID()
   {
      return userID;
   }

   public void setUserID(String userID)
   {
      this.userID = userID;
   }

   public Long getWebLogonID()
   {
      return webLogonID;
   }

   public void setWebLogonID(Long webLogonID)
   {
      this.webLogonID = webLogonID;
   }

   @Override
   public String toString()
   {
      return "UserAcctDetails{" +
         "clientAccountID='" + clientAccountID + '\'' +
         ", email='" + email + '\'' +
         ", fundGroupName='" + fundGroupName + '\'' +
         ", mailZipCode='" + mailZipCode + '\'' +
         ", primaryPhoneNbr='" + primaryPhoneNbr + '\'' +
         ", pwd='" + pwd + '\'' +
         ", securityAnswer='" + securityAnswer + '\'' +
         ", securityQuestion='" + securityQuestion + '\'' +
         ", ssn='" + ssn + '\'' +
         ", status='" + status + '\'' +
         ", userID='" + userID + '\'' +
         ", webLogonID=" + webLogonID +
         '}';
   }
}
