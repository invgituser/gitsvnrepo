package com.invessence.web.data.custody.td;

public class MapMovemoneyPaymethod {

      private Long acctnum;
      private Long moveMoneyPayMethId;
      private String payMethodId;
      private String status;

   public MapMovemoneyPaymethod()
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

   public Long getMoveMoneyPayMethId()
   {
      return moveMoneyPayMethId;
   }

   public void setMoveMoneyPayMethId(Long moveMoneyPayMethId)
   {
      this.moveMoneyPayMethId = moveMoneyPayMethId;
   }

   public String getPayMethodId()
   {
      return payMethodId;
   }

   public void setPayMethodId(String payMethodId)
   {
      this.payMethodId = payMethodId;
   }

   public String getStatus()
   {
      return status;
   }

   public void setStatus(String status)
   {
      this.status = status;
   }
}
