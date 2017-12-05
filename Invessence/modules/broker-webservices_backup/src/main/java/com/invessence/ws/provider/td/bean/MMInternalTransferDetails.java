package com.invessence.ws.provider.td.bean;

/**
 * Created by abhangp on 8/31/2016.
 */
public class MMInternalTransferDetails
{
   private Long moveMoneyPayMethodID;
   private Integer intTranId;
   private String destAccountNumber;
   private String destAccountTitle;

   public Long getMoveMoneyPayMethodID()
   {
      return moveMoneyPayMethodID;
   }

   public void setMoveMoneyPayMethodID(Long moveMoneyPayMethodID)
   {
      this.moveMoneyPayMethodID = moveMoneyPayMethodID;
   }

   public Integer getIntTranId()
   {
      return intTranId;
   }

   public void setIntTranId(Integer intTranId)
   {
      this.intTranId = intTranId;
   }

   public String getDestAccountNumber()
   {
      return destAccountNumber;
   }

   public void setDestAccountNumber(String destAccountNumber)
   {
      this.destAccountNumber = destAccountNumber;
   }

   public String getDestAccountTitle()
   {
      return destAccountTitle;
   }

   public void setDestAccountTitle(String destAccountTitle)
   {
      this.destAccountTitle = destAccountTitle;
   }
}
