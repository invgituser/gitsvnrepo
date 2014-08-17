package com.invessence.data;

import java.io.Serializable;
import java.util.*;

public class Allocation implements Serializable
{

   /**
    *
    */
   private static final long serialVersionUID = 11L;

   private Long acctnum;
   private Long userid;
   private String addmodflag;
   private Integer investment;
   private Map<String, Double> assetAllocation = new HashMap<String,Double>();
   private Integer total;
   private String model;
   private Integer assetyear;
   private String active;
   private Integer age;
   private Integer horizon;
   private String risk;
   private String stockscolor;
   private String bondscolor;
   private String commoditiescolor;
   private String cashcolor;
   private Integer slices;

   public Allocation()
   {
   }

   public Long getUserid()
   {
      return userid;
   }

   public void setUserid(Long userid)
   {
      this.userid = userid;
   }

   public Long getAcctnum()
   {
      return acctnum;
   }

   public void setAcctnum(Long acctnum)
   {
      this.acctnum = acctnum;
   }

   public String getAddmodflag()
   {
      return addmodflag;
   }

   public void setAddmodflag(String addmodflag)
   {
      this.addmodflag = addmodflag;
   }

   public Integer getInvestment()
   {
      return investment;
   }

   public void setInvestment(Integer investment)
   {
      this.investment = investment;
   }

   public String getModel()
   {
      return model;
   }

   public void setModel(String model)
   {
      this.model = model;
   }

   public Integer getAssetyear()
   {
      return assetyear;
   }

   public void setAssetyear(Integer assetyear)
   {
      this.assetyear = assetyear;
   }

   public String getActive()
   {
      return active;
   }

   public void setActive(String active)
   {
      this.active = active;
   }

   public Integer getAge()
   {
      return age;
   }

   public void setAge(Integer age)
   {
      this.age = age;
   }

   public Integer getHorizon()
   {
      return horizon;
   }

   public void setHorizon(Integer horizon)
   {
      this.horizon = horizon;
   }

   public String getRisk()
   {
      return risk;

   }

   public void setRisk(String risk)
   {
      this.risk = risk;
   }

   public String getStockscolor()
   {
      return stockscolor;
   }

   public void setStockscolor(String stockscolor)
   {
      this.stockscolor = stockscolor;
   }

   public String getBondscolor()
   {
      return bondscolor;
   }

   public void setBondscolor(String bondscolor)
   {
      this.bondscolor = bondscolor;
   }

   public String getCommoditiescolor()
   {
      return commoditiescolor;
   }

   public void setCommoditiescolor(String commoditiescolor)
   {
      this.commoditiescolor = commoditiescolor;
   }

   public String getCashcolor()
   {
      return cashcolor;
   }

   public void setCashcolor(String cashcolor)
   {
      this.cashcolor = cashcolor;
   }

   public Integer getSlices()
   {
      return slices;
   }

   public void setSlices(Integer slices)
   {
      this.slices = slices;
   }

}
