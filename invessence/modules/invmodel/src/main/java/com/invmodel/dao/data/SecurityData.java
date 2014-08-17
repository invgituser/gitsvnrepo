package com.invmodel.dao.data;

import com.google.common.base.Joiner;

import java.util.Arrays;

import static com.invmodel.utils.XMLBuilder.buildElement;
import static java.lang.String.valueOf;


public class SecurityData
{
   private Long instrumentid = 0L;
   private String ticker = "";
   private String name = "";
   private String assetclass = "";
   private String subassetclass = "";
   private String type = "";
   private String style;
   private double dailyprice = 0.0;
   private double expenseRatio = 0.0;
   private double adv3Month = 0.0;
   private double aum = 0.0;
   private double beta = 0.0;
   private double riskSTD = 0.0;
   private double taxableReturn = 0.0;
   private double nonTaxableReturn = 0.0;
   private double ubConstraint = 0.0;
   private double lbConstraint = 0.0;
   private double yield = 0.0;
   private int sortorder = 0;

   public SecurityData()
   {
      super();
   }

   public SecurityData(Long instrumentid, String ticker, String name,
                       String assetclass, String subassetclass, String type, String style,
                       double dailyprice, double expenseRatio, double adv3Month,
                       double aum, double beta, double riskSTD,
                       double taxableReturn, double nonTaxableReturn, double ubConstraint,
                       double lbConstraint, double yield, int sortorder)
   {
      super();
      this.instrumentid = instrumentid;
      this.ticker = ticker;
      this.name = name;
      this.assetclass = assetclass;
      this.subassetclass = subassetclass;
      this.type = type;
      this.style = style;
      this.style = style;
      this.dailyprice = dailyprice;
      this.expenseRatio = expenseRatio;
      this.adv3Month = adv3Month;
      this.aum = aum;
      this.beta = beta;
      this.riskSTD = riskSTD;
      this.taxableReturn = taxableReturn;
      this.nonTaxableReturn = nonTaxableReturn;
      this.ubConstraint = ubConstraint;
      this.lbConstraint = lbConstraint;
      this.yield = yield;
      this.sortorder = sortorder;
   }

   public SecurityData resetSecurityData(Long instrumentid, String ticker, String name,
                                         String assetclass, String subassetclass, String type, String style,
                                         double dailyprice, double expenseRatio, double adv3Month,
                                         double aum, double beta, double riskSTD,
                                         double taxableReturn, double nonTaxableReturn, double ubConstraint,
                                         double lbConstraint, double yield, int sortorder)
   {
      this.instrumentid = instrumentid;
      this.ticker = ticker;
      this.name = name;
      this.assetclass = assetclass;
      this.subassetclass = subassetclass;
      this.type = type;
      this.style = style;
      this.dailyprice = dailyprice;
      this.expenseRatio = expenseRatio;
      this.adv3Month = adv3Month;
      this.aum = aum;
      this.beta = beta;
      this.riskSTD = riskSTD;
      this.taxableReturn = taxableReturn;
      this.nonTaxableReturn = nonTaxableReturn;
      this.ubConstraint = ubConstraint;
      this.lbConstraint = lbConstraint;
      this.yield = yield;
      this.sortorder = sortorder;
      return this;
   }

   public Long getInstrumentid()
   {
      return instrumentid;
   }

   public void setInstrumentid(Long instrumentid)
   {
      this.instrumentid = instrumentid;
   }

   public String getTicker()
   {
      return ticker;
   }

   public void setTicker(String ticker)
   {
      this.ticker = ticker;
   }

   public String getName()
   {
      return name;
   }

   public void setName(String name)
   {
      this.name = name;
   }

   public String getAssetclass()
   {
      return assetclass;
   }

   public void setAssetclass(String assetclass)
   {
      this.assetclass = assetclass;
   }

   public String getSubassetclass()
   {
      return subassetclass;
   }

   public void setSubassetclass(String subassetclass)
   {
      this.subassetclass = subassetclass;
   }

   public String getType()
   {
      return type;
   }

   public void setType(String type)
   {
      this.type = type;
   }

   public String getStyle()
   {
      return style;
   }

   public void setStyle(String style)
   {
      this.style = style;
   }

   public double getDailyprice()
   {
      return dailyprice;
   }

   public void setDailyprice(double dailyprice)
   {
      this.dailyprice = dailyprice;
   }

   public double getExpenseRatio()
   {
      return expenseRatio;
   }

   public void setExpenseRatio(double expenseRatio)
   {
      this.expenseRatio = expenseRatio;
   }

   public double getAdv3Month()
   {
      return adv3Month;
   }

   public void setAdv3Month(double adv3Month)
   {
      this.adv3Month = adv3Month;
   }

   public double getAum()
   {
      return aum;
   }

   public void setAum(double aum)
   {
      this.aum = aum;
   }

   public double getBeta()
   {
      return beta;
   }

   public void setBeta(double beta)
   {
      this.beta = beta;
   }

   public double getRiskSTD()
   {
      return riskSTD;
   }

   public void setRiskSTD(double riskSTD)
   {
      this.riskSTD = riskSTD;
   }

   public double getTaxableReturn()
   {
      return taxableReturn;
   }

   public void setTaxableReturn(double taxableReturn)
   {
      this.taxableReturn = taxableReturn;
   }

   public double getNonTaxableReturn()
   {
      return nonTaxableReturn;
   }

   public void setNonTaxableReturn(double nonTaxableReturn)
   {
      this.nonTaxableReturn = nonTaxableReturn;
   }

   public double getUbConstraint()
   {
      return ubConstraint;
   }

   public void setUbConstraint(double ubConstraint)
   {
      this.ubConstraint = ubConstraint;
   }

   public double getLbConstraint()
   {
      return lbConstraint;
   }

   public void setLbConstraint(double lbConstraint)
   {
      this.lbConstraint = lbConstraint;
   }

   public double getYield()
   {
      return yield;
   }

   public void setYield(double yield)
   {
      this.yield = yield;
   }

   public int getSortorder()
   {
      return sortorder;
   }

   public void setSortorder(int sortorder)
   {
      this.sortorder = sortorder;
   }

   public String getHeader()
   {
      String str = Joiner.on(",").join(Arrays.asList("Ticker", "Name",
                                                     "Assetclass", "AssetSubType", "Price", "Sortorder"));
      return str;
   }


   @Override
   public String toString()
   {
      try
      {
         String str = this.ticker + ":" + Joiner.on(",").join(Arrays.asList(getTicker(),
                                                                            getName(),
                                                                            getAssetclass(),
                                                                            getSubassetclass(),
                                                                            getDailyprice(),
                                                                            getSortorder()
                                                                            ));
         return str;
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
      return this.ticker;
   }

   public String toXml()
   {
      String xmlData = "";
      try
      {
         xmlData = xmlData + buildElement("Ticker", getTicker()) +
            buildElement("Name", getName()) +
            buildElement("AssetType", getAssetclass()) +
            buildElement("AssetSubtype", getSubassetclass()) +
            buildElement("DailyPrice", valueOf(getDailyprice())) +
            buildElement("Sortorder", valueOf(getSortorder()));
         return buildElement("SecurityInfo", xmlData);

      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
      return (buildElement("SecurityInfo", this.toString()));
   }

}
