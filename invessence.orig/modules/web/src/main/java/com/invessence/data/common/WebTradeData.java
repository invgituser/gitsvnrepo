package com.invessence.data.common;

import com.invmodel.rebalance.data.TradeData;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 1/11/15
 * Time: 6:33 PM
 * To change this template use File | Settings | File Templates.
 */
public class WebTradeData extends TradeData
{
   private String processed;
   private String assetClass;
   private String subclass;
   private Integer sortorder;
   private String name;

   public WebTradeData()
   {
      super();
   }

   public String getProcessed()
   {
      return processed;
   }

   public void setProcessed(String processed)
   {
      this.processed = processed;
   }

   public String getAssetClass()
   {
      return assetClass;
   }

   public void setAssetClass(String assetClass)
   {
      this.assetClass = assetClass;
   }

   public String getSubclass()
   {
      return subclass;
   }

   public void setSubclass(String subclass)
   {
      this.subclass = subclass;
   }

   public Integer getSortorder()
   {
      return sortorder;
   }

   public void setSortorder(Integer sortorder)
   {
      this.sortorder = sortorder;
   }

   public String getName()
   {
      return name;
   }

   public void setName(String name)
   {
      this.name = name;
   }
}
