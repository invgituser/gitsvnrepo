package com.invessence.web.data.common;

import java.util.*;

import com.invmodel.asset.data.Asset;
import com.invmodel.rebalance.data.*;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 4/2/15
 * Time: 2:19 PM
 * To change this template use File | Settings | File Templates.
 */
public class TradeSummary
{
   private Long acctnum;
   private String clientAccountID;
   private String lastName, firstName;
   private String tradeStatus, processStatus;
   private Double totalInvestment;
   private Double sumcurQty, sumcurValue;
   private Double sumholdingQty, sumholdingValue;
   private Double sumnewQty, sumnewValue;

   public String getClientAccountID()
   {
      return clientAccountID;
   }

   public void setClientAccountID(String clientAccountID)
   {
      this.clientAccountID = clientAccountID;
   }

   public Long getAcctnum()
   {
      return acctnum;
   }

   public void setAcctnum(Long acctnum)
   {
      this.acctnum = acctnum;
   }

   public String getLastName()
   {
      return lastName;
   }

   public void setLastName(String lastName)
   {
      this.lastName = lastName;
   }

   public String getFirstName()
   {
      return firstName;
   }

   public void setFirstName(String firstName)
   {
      this.firstName = firstName;
   }

   public String getName()
   {
      if (firstName != null) {
         return ((lastName == null) ? firstName : firstName + " " + lastName);
      }
      return lastName;
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

   public String getProcessStatus()
   {
      return processStatus;
   }

   public void setProcessStatus(String processStatus)
   {
      this.processStatus = processStatus;
   }

   public Double getTotalInvestment()
   {
      return totalInvestment;
   }

   public void setTotalInvestment(Double totalInvestment)
   {
      this.totalInvestment = totalInvestment;
   }

   public Double getSumcurQty()
   {
      return sumcurQty;
   }

   public void setSumcurQty(Double sumcurQty)
   {
      this.sumcurQty = sumcurQty;
   }

   public Double getSumcurValue()
   {
      return sumcurValue;
   }

   public void setSumcurValue(Double sumcurValue)
   {
      this.sumcurValue = sumcurValue;
   }

   public Double getSumholdingQty()
   {
      return sumholdingQty;
   }

   public void setSumholdingQty(Double sumholdingQty)
   {
      this.sumholdingQty = sumholdingQty;
   }

   public Double getSumholdingValue()
   {
      return sumholdingValue;
   }

   public void setSumholdingValue(Double sumholdingValue)
   {
      this.sumholdingValue = sumholdingValue;
   }

   public Double getSumnewQty()
   {
      return sumnewQty;
   }

   public void setSumnewQty(Double sumnewQty)
   {
      this.sumnewQty = sumnewQty;
   }

   public Double getSumnewValue()
   {
      return sumnewValue;
   }

   public void setSumnewValue(Double sumnewValue)
   {
      this.sumnewValue = sumnewValue;
   }

}


