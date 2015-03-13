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
   private String primeassetclass = "";
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
   private double rbsaweight = 0.0;

   public SecurityData()
   {
      super();
   }

   public SecurityData(Long instrumentid, String ticker, String name,
                       String assetclass, String primeassetclass, String subassetclass, String type, String style,
                       double dailyprice, double expenseRatio, double adv3Month,
                       double aum, double beta, double riskSTD,
                       double taxableReturn, double nonTaxableReturn, double ubConstraint,
                       double lbConstraint, double yield, int sortorder, double rbsaweight)
   {
      super();
      resetSecurityData(instrumentid, ticker, name,
                        assetclass, primeassetclass, subassetclass, type, style,
                        dailyprice, expenseRatio, adv3Month,
                        aum, beta, riskSTD,
                        taxableReturn, nonTaxableReturn, ubConstraint,
                        lbConstraint, yield, sortorder, rbsaweight);
   }

   public SecurityData resetSecurityData(Long instrumentid, String ticker, String name,
                                         String assetclass, String subassetclass, String primeassetclass,
                                         String type, String style,
                                         double dailyprice, double expenseRatio, double adv3Month,
                                         double aum, double beta, double riskSTD,
                                         double taxableReturn, double nonTaxableReturn, double ubConstraint,
                                         double lbConstraint, double yield, int sortorder, double rbsaweight)
   {
      this.instrumentid = instrumentid;
      this.ticker = ticker;
      this.name = name;
      this.assetclass = assetclass;
      this.primeassetclass = primeassetclass;
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
      this.rbsaweight = rbsaweight;
      return this;
   }

   public Long getInstrumentid()
   {
      return instrumentid;
   }

   public String getTicker()
   {
      return ticker;
   }

   public String getName()
   {
      return name;
   }

   public String getAssetclass()
   {
      return assetclass;
   }

   public String getPrimeassetclass()
   {
      return primeassetclass;
   }

   public String getSubassetclass()
   {
      return subassetclass;
   }

   public String getType()
   {
      return type;
   }

   public String getStyle()
   {
      return style;
   }

   public double getDailyprice()
   {
      return dailyprice;
   }

   public double getExpenseRatio()
   {
      return expenseRatio;
   }

   public double getAdv3Month()
   {
      return adv3Month;
   }

   public double getAum()
   {
      return aum;
   }

   public double getBeta()
   {
      return beta;
   }

   public double getRiskSTD()
   {
      return riskSTD;
   }

   public double getTaxableReturn()
   {
      return taxableReturn;
   }

   public double getNonTaxableReturn()
   {
      return nonTaxableReturn;
   }

   public double getUbConstraint()
   {
      return ubConstraint;
   }

   public double getLbConstraint()
   {
      return lbConstraint;
   }

   public double getYield()
   {
      return yield;
   }

   public int getSortorder()
   {
      return sortorder;
   }

   public double getRbsaweight()
   {
      return rbsaweight;
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
