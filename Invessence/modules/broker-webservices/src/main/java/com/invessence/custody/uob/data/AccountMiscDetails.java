package com.invessence.custody.uob.data;

/**
 * Created by abhangp on 10/24/2017.
 */
public class AccountMiscDetails
{
   private String divIntPrefId;
   private String monthStmtId;
   private String tradConfId;
   private String dupStatement;
   private String dupTradeConfirm;

   public String getDivIntPrefId()
   {
      return divIntPrefId;
   }

   public void setDivIntPrefId(String divIntPrefId)
   {
      this.divIntPrefId = divIntPrefId;
   }

   public String getMonthStmtId()
   {
      return monthStmtId;
   }

   public void setMonthStmtId(String monthStmtId)
   {
      this.monthStmtId = monthStmtId;
   }

   public String getTradConfId()
   {
      return tradConfId;
   }

   public void setTradConfId(String tradConfId)
   {
      this.tradConfId = tradConfId;
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
}
