package com.invessence.ws.provider.td.bean;

/**
 * Created by abhangp on 8/31/2016.
 */
public class MMAchBankDetails
{
   private Long moveMoneyPayMethodID;
   private Long achId;
   private String bankAcctType;
   private String ACHBankName;
   private String ACHBankABARouting;
   private String ACHBankCityState;
   private String ACHBankPhoneNumber;
   private String ACHBankAcctName;
   private String ACHBankAcctNumber;

   public Long getMoveMoneyPayMethodID()
   {
      return moveMoneyPayMethodID;
   }

   public void setMoveMoneyPayMethodID(Long moveMoneyPayMethodID)
   {
      this.moveMoneyPayMethodID = moveMoneyPayMethodID;
   }

   public Long getAchId()
   {
      return achId;
   }

   public void setAchId(Long achId)
   {
      this.achId = achId;
   }

   public String getBankAcctType()
   {
      return bankAcctType;
   }

   public void setBankAcctType(String bankAcctType)
   {
      this.bankAcctType = bankAcctType;
   }

   public String getACHBankName()
   {
      return ACHBankName;
   }

   public void setACHBankName(String ACHBankName)
   {
      this.ACHBankName = ACHBankName;
   }

   public String getACHBankABARouting()
   {
      return ACHBankABARouting;
   }

   public void setACHBankABARouting(String ACHBankABARouting)
   {
      this.ACHBankABARouting = ACHBankABARouting;
   }

   public String getACHBankCityState()
   {
      return ACHBankCityState;
   }

   public void setACHBankCityState(String ACHBankCityState)
   {
      this.ACHBankCityState = ACHBankCityState;
   }

   public String getACHBankPhoneNumber()
   {
      return ACHBankPhoneNumber;
   }

   public void setACHBankPhoneNumber(String ACHBankPhoneNumber)
   {
      this.ACHBankPhoneNumber = ACHBankPhoneNumber;
   }

   public String getACHBankAcctName()
   {
      return ACHBankAcctName;
   }

   public void setACHBankAcctName(String ACHBankAcctName)
   {
      this.ACHBankAcctName = ACHBankAcctName;
   }

   public String getACHBankAcctNumber()
   {
      return ACHBankAcctNumber;
   }

   public void setACHBankAcctNumber(String ACHBankAcctNumber)
   {
      this.ACHBankAcctNumber = ACHBankAcctNumber;
   }
}
