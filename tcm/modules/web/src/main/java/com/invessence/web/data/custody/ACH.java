package com.invessence.web.data.custody;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 8/1/16
 * Time: 11:34 AM
 * To change this template use File | Settings | File Templates.
 */
public class ACH
{
   private Long   acctnum;           // acctOwnerId
   private Integer id;
   private String moveMoneyPayMethodID;
   private String bankAcctType;
   private String bankName;
   private String bankABARouting;
   private String bankCityState;
   private String bankPhoneNumber;
   private String bankAcctName;
   private String bankAcctNumber;

   public ACH()
   {
   }

   public Long getAcctnum()
   {
      return acctnum;
   }

   public void setAcctnum(Long acctnum)
   {
      this.acctnum = acctnum;
   }

   public Integer getId()
   {
      return id;
   }

   public void setId(Integer id)
   {
      this.id = id;
   }

   public String getMoveMoneyPayMethodID()
   {
      return moveMoneyPayMethodID;
   }

   public void setMoveMoneyPayMethodID(String moveMoneyPayMethodID)
   {
      this.moveMoneyPayMethodID = moveMoneyPayMethodID;
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
}
