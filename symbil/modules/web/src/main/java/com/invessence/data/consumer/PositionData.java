package com.invessence.data.consumer;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: pichaimanir
 * Date: 8/19/13
 * Time: 4:50 PM
 * To change this template use File | Settings | File Templates.
 */
public class PositionData implements Serializable
{

   private Integer sortorder;
   private Long    acctnum;
   private String  clientAccountID;
   private String  name;
   private String  repName;
   private String  theme;
   private String  dateOpened;
   private String  assetClass;
   private String  assetname;
   private String  subasset;
   private String  displayname;
   private String  color;
   private String  status;
   private Double  allocation;
   private String  reportDate;
   private String  side;
   private Double  quantity;
   private Double  costBasisPrice;
   private Double  costBasisMoney;
   private Double  markPrice;
   private Double  positionValue;

   public PositionData()
   {
   }

   public PositionData(Integer sortorder, Long acctnum,
                       String clientAccountID, String name, String repName,
                       String theme, String dateOpened, String assetClass, String assetname,
                       String subasset, String displayname, String color, String status,
                       Double allocation, String reportDate, String side, Double quantity,
                       Double costBasisPrice, Double costBasisMoney,
                       Double markPrice, Double positionValue)
   {
      this.sortorder = sortorder;
      this.acctnum = acctnum;
      this.clientAccountID = clientAccountID;
      this.name = name;
      this.repName = repName;
      this.theme = theme;
      this.dateOpened = dateOpened;
      this.assetClass = assetClass;
      this.assetname = assetname;
      this.subasset = subasset;
      this.displayname = displayname;
      this.color = color;
      this.status = status;
      this.allocation = allocation;
      this.reportDate = reportDate;
      this.side = side;
      this.quantity = quantity;
      this.costBasisPrice = costBasisPrice;
      this.costBasisMoney = costBasisMoney;
      this.markPrice = markPrice;
      this.positionValue = positionValue;
   }

   public Integer getSortorder()
   {
      return sortorder;
   }

   public void setSortorder(Integer sortorder)
   {
      this.sortorder = sortorder;
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

   public String getName()
   {
      return name;
   }

   public void setName(String name)
   {
      this.name = name;
   }

   public String getRepName()
   {
      return repName;
   }

   public void setRepName(String repName)
   {
      this.repName = repName;
   }

   public String getTheme()
   {
      return theme;
   }

   public void setTheme(String theme)
   {
      this.theme = theme;
   }

   public String getDateOpened()
   {
      return dateOpened;
   }

   public void setDateOpened(String dateOpened)
   {
      this.dateOpened = dateOpened;
   }

   public String getAssetClass()
   {
      return assetClass;
   }

   public void setAssetClass(String assetClass)
   {
      this.assetClass = assetClass;
   }

   public String getAssetname()
   {
      return assetname;
   }

   public void setAssetname(String assetname)
   {
      this.assetname = assetname;
   }

   public String getSubasset()
   {
      return subasset;
   }

   public void setSubasset(String subasset)
   {
      this.subasset = subasset;
   }

   public String getDisplayname()
   {
      return displayname;
   }

   public void setDisplayname(String displayname)
   {
      this.displayname = displayname;
   }

   public String getColor()
   {
      return color;
   }

   public void setColor(String color)
   {
      this.color = color;
   }

   public String getStatus()
   {
      return status;
   }

   public void setStatus(String status)
   {
      this.status = status;
   }

   public Double getAllocation()
   {
      return allocation;
   }

   public void setAllocation(Double allocation)
   {
      this.allocation = allocation;
   }

   public String getReportDate()
   {
      return reportDate;
   }

   public void setReportDate(String reportDate)
   {
      this.reportDate = reportDate;
   }

   public String getSide()
   {
      return side;
   }

   public void setSide(String side)
   {
      this.side = side;
   }

   public Double getQuantity()
   {
      return quantity;
   }

   public void setQuantity(Double quantity)
   {
      this.quantity = quantity;
   }

   public Double getCostBasisPrice()
   {
      return costBasisPrice;
   }

   public void setCostBasisPrice(Double costBasisPrice)
   {
      this.costBasisPrice = costBasisPrice;
   }

   public Double getCostBasisMoney()
   {
      return costBasisMoney;
   }

   public void setCostBasisMoney(Double costBasisMoney)
   {
      this.costBasisMoney = costBasisMoney;
   }

   public Double getMarkPrice()
   {
      return markPrice;
   }

   public void setMarkPrice(Double markPrice)
   {
      this.markPrice = markPrice;
   }

   public Double getPositionValue()
   {
      return positionValue;
   }

   public void setPositionValue(Double positionValue)
   {
      this.positionValue = positionValue;
   }
}
