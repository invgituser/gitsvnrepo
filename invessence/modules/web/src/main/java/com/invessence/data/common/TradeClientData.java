package com.invessence.data.common;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 4/14/14
 * Time: 10:32 PM
 * To change this template use File | Settings | File Templates.
 */
public class TradeClientData extends ManageGoals
{
   private String processStatus;
   private String reason;
   private String lastTraded;
   private Double assetAllocationOffset;
   private String created;
   private String lastUpdated;
   private String assetClass;
   private Double position, currentAllocation, requiredAllocation;

   public TradeClientData getInstance()
   {
      return this;
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

   public Double getAssetAllocationOffset()
   {
      return assetAllocationOffset;
   }

   public void setAssetAllocationOffset(Double assetAllocationOffset)
   {
      this.assetAllocationOffset = assetAllocationOffset;
   }

   public String getCreated()
   {
      return created;
   }

   public void setCreated(String created)
   {
      this.created = created;
   }

   public String getLastUpdated()
   {
      return lastUpdated;
   }

   public void setLastUpdated(String lastUpdated)
   {
      this.lastUpdated = lastUpdated;
   }

   public String getAssetClass()
   {
      return assetClass;
   }

   public void setAssetClass(String assetClass)
   {
      this.assetClass = assetClass;
   }

   public Double getPosition()
   {
      return position;
   }

   public void setPosition(Double position)
   {
      this.position = position;
   }

   public Double getCurrentAllocation()
   {
      return currentAllocation;
   }

   public void setCurrentAllocation(Double currentAllocation)
   {
      this.currentAllocation = currentAllocation;
   }

   public Double getRequiredAllocation()
   {
      return requiredAllocation;
   }

   public void setRequiredAllocation(Double requiredAllocation)
   {
      this.requiredAllocation = requiredAllocation;
   }
}
