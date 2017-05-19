package com.invessence.web.data.common;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 4/14/14
 * Time: 10:32 PM
 * To change this template use File | Settings | File Templates.
 */
public class TradeClientData
{
   private Long acctnum;
   private String clientAccountID;
   private String tradeStatus;
   private String processStatus;
   private String reason;
   private String firstname;
   private String lastname;
   private String lastTraded;
   private Double cash;
   private Double investment;

   public TradeClientData getInstance()
   {
      return this;
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

   public String getFirstname()
   {
      return firstname;
   }

   public void setFirstname(String firstname)
   {
      this.firstname = firstname;
   }

   public String getLastname()
   {
      return lastname;
   }

   public void setLastname(String lastname)
   {
      this.lastname = lastname;
   }

   public String getName() {
      if (getFirstname() != null) {
         return (getLastname() == null) ? getFirstname() : getLastname() + ", " + getFirstname();
      }
      else
         return getLastname();
   }
   public String getProcessStatus()
   {
      return processStatus;
   }

   public void setProcessStatus(String processStatus)
   {
      this.processStatus = processStatus;
   }

   public String getReason()
   {
      return reason;
   }

   public void setReason(String reason)
   {
      this.reason = reason;
   }

   public String getLastTraded()
   {
      return lastTraded;
   }

   public void setLastTraded(String lastTraded)
   {
      this.lastTraded = lastTraded;
   }

   public String getTradeStatus()
   {
      return tradeStatus;
   }

   public void setTradeStatus(String tradeStatus)
   {
      this.tradeStatus = tradeStatus;
   }

   public String getTradeDisplayStatus()
   {
      if (tradeStatus != null) {
         if (tradeStatus.startsWith("N"))
            return "New";
         if (tradeStatus.startsWith("D"))
            return "Date";
         if (tradeStatus.startsWith("A"))
            return "Allocation";
      }
      return null;
   }

   public Double getCash()
   {
      return cash;
   }

   public void setCash(Double cash)
   {
      this.cash = cash;
   }

   public Double getInvestment()
   {
      return investment;
   }

   public void setInvestment(Double investment)
   {
      this.investment = investment;
   }
}
