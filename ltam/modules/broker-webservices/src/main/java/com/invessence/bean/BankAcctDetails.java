package com.invessence.bean;

/**
 * Created by abhangp on 4/6/2016.
 */
public class BankAcctDetails
{

   private String accountNumber;
   private int achPayeeId;
   private String bankName;
   private String bankRoutingNumber;
   private String bankAccountNumber;
   private String nameOnAccount;
   private int bankAccountType;
   private int preferredCurrencyId;
   private int accountPayeeId;

   public BankAcctDetails(String accountNumber, int achPayeeId, String bankName, String bankRoutingNumber, String bankAccountNumber, String nameOnAccount, int bankAccountType, int preferredCurrencyId, int accountPayeeId, short powerAgentUserId, short isAccountPayeeBeingUsed)
   {
      this.accountNumber = accountNumber.trim();
      this.achPayeeId = achPayeeId;
      this.bankName = bankName.trim();
      this.bankRoutingNumber = bankRoutingNumber.trim();
      this.bankAccountNumber = bankAccountNumber.trim();
      this.nameOnAccount = nameOnAccount.trim();
      this.bankAccountType = bankAccountType;
      this.preferredCurrencyId = preferredCurrencyId;
      this.accountPayeeId = accountPayeeId;
      this.powerAgentUserId = powerAgentUserId;
      this.isAccountPayeeBeingUsed = isAccountPayeeBeingUsed;
   }

   private short powerAgentUserId;
   private short isAccountPayeeBeingUsed;


   public String getAccountNumber()
   {
      return accountNumber;
   }

   public void setAccountNumber(String accountNumber)
   {
      this.accountNumber = accountNumber;
   }

   public int getAchPayeeId()
   {
      return achPayeeId;
   }

   public void setAchPayeeId(int achPayeeId)
   {
      this.achPayeeId = achPayeeId;
   }

   public String getBankName()
   {
      return bankName;
   }

   public void setBankName(String bankName)
   {
      this.bankName = bankName;
   }

   public String getBankRoutingNumber()
   {
      return bankRoutingNumber;
   }

   public void setBankRoutingNumber(String bankRoutingNumber)
   {
      this.bankRoutingNumber = bankRoutingNumber;
   }

   public String getBankAccountNumber()
   {
      return bankAccountNumber;
   }

   public void setBankAccountNumber(String bankAccountNumber)
   {
      this.bankAccountNumber = bankAccountNumber;
   }

   public String getNameOnAccount()
   {
      return nameOnAccount;
   }

   public void setNameOnAccount(String nameOnAccount)
   {
      this.nameOnAccount = nameOnAccount;
   }

   public int getBankAccountType()
   {
      return bankAccountType;
   }

   public void setBankAccountType(int bankAccountType)
   {
      this.bankAccountType = bankAccountType;
   }

   public int getPreferredCurrencyId()
   {
      return preferredCurrencyId;
   }

   public void setPreferredCurrencyId(int preferredCurrencyId)
   {
      this.preferredCurrencyId = preferredCurrencyId;
   }

   public int getAccountPayeeId()
   {
      return accountPayeeId;
   }

   public void setAccountPayeeId(int accountPayeeId)
   {
      this.accountPayeeId = accountPayeeId;
   }

   public short getPowerAgentUserId()
   {
      return powerAgentUserId;
   }

   public void setPowerAgentUserId(short powerAgentUserId)
   {
      this.powerAgentUserId = powerAgentUserId;
   }

   public short getIsAccountPayeeBeingUsed()
   {
      return isAccountPayeeBeingUsed;
   }

   public void setIsAccountPayeeBeingUsed(short isAccountPayeeBeingUsed)
   {
      this.isAccountPayeeBeingUsed = isAccountPayeeBeingUsed;
   }

   @Override
   public String toString()
   {
      return "BankAcctDetails{" +
         "accountNumber='" + accountNumber + '\'' +
         ", achPayeeId=" + achPayeeId +
         ", bankName='" + bankName + '\'' +
         ", bankRoutingNumber='" + bankRoutingNumber + '\'' +
         ", bankAccountNumber='" + bankAccountNumber + '\'' +
         ", nameOnAccount='" + nameOnAccount + '\'' +
         ", bankAccountType=" + bankAccountType +
         ", preferredCurrencyId=" + preferredCurrencyId +
         ", accountPayeeId=" + accountPayeeId +
         ", powerAgentUserId=" + powerAgentUserId +
         ", isAccountPayeeBeingUsed=" + isAccountPayeeBeingUsed +
         '}';
   }
}
