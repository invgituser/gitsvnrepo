package com.invessence.price.processor.bean;

import java.util.Date;

public class SecMaster
{
   private Long instrumentId;
   private String status;
   private String ticker;
   private String tickerSource;
   private String cusip;
   private String isin;
   private String name;
   private String assetClass;
   private String subClass;
   private String type;
   private String style;
   private Double expenseRatio;
   private Double lowerBoundReturn;
   private Double upperBoundReturn;
   private Double taxableReturn;
   private Double nontaxableReturn;
   private Date inception;
   private Double adv3months;
   private String issuer;
   private Double aum;
   private Double beta;
   private Double securityRiskSTD;
   private Double lowerbound;
   private Double upperbound;
   private Double yield;
   private String rbsaFlag;
   private String businessdate;
   private String onDemand;

   public String getTickerSource()
   {
      return tickerSource;
   }

   public void setTickerSource(String tickerSource)
   {
      this.tickerSource = tickerSource;
   }

   public Long getInstrumentId()
   {
      return instrumentId;
   }

   public void setInstrumentId(Long instrumentId)
   {
      this.instrumentId = instrumentId;
   }

   public String getStatus()
   {
      return status;
   }

   public void setStatus(String status)
   {
      this.status = status;
   }

   public String getTicker()
   {
      return ticker;
   }

   public void setTicker(String ticker)
   {
      this.ticker = ticker;
   }




   public String getCusip()
   {
      return cusip;
   }

   public void setCusip(String cusip)
   {
      this.cusip = cusip;
   }

   public String getIsin()
   {
      return isin;
   }

   public void setIsin(String isin)
   {
      this.isin = isin;
   }

   public String getName()
   {
      return name;
   }

   public void setName(String name)
   {
      this.name = name;
   }

   public String getAssetClass()
   {
      return assetClass;
   }

   public void setAssetClass(String assetClass)
   {
      this.assetClass = assetClass;
   }

   public String getSubClass()
   {
      return subClass;
   }

   public void setSubClass(String subClass)
   {
      this.subClass = subClass;
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

   public Double getExpenseRatio()
   {
      return expenseRatio;
   }

   public void setExpenseRatio(Double expenseRatio)
   {
      this.expenseRatio = expenseRatio;
   }

   public Double getLowerBoundReturn()
   {
      return lowerBoundReturn;
   }

   public void setLowerBoundReturn(Double lowerBoundReturn)
   {
      this.lowerBoundReturn = lowerBoundReturn;
   }

   public Double getUpperBoundReturn()
   {
      return upperBoundReturn;
   }

   public void setUpperBoundReturn(Double upperBoundReturn)
   {
      this.upperBoundReturn = upperBoundReturn;
   }

   public Double getTaxableReturn()
   {
      return taxableReturn;
   }

   public void setTaxableReturn(Double taxableReturn)
   {
      this.taxableReturn = taxableReturn;
   }

   public Double getNontaxableReturn()
   {
      return nontaxableReturn;
   }

   public void setNontaxableReturn(Double nontaxableReturn)
   {
      this.nontaxableReturn = nontaxableReturn;
   }

   public Date getInception()
   {
      return inception;
   }

   public void setInception(Date inception)
   {
      this.inception = inception;
   }

   public String getIssuer()
   {
      return issuer;
   }

   public void setIssuer(String issuer)
   {
      this.issuer = issuer;
   }

   public Double getAdv3months()
   {
      return adv3months;
   }

   public void setAdv3months(Double adv3months)
   {
      this.adv3months = adv3months;
   }

   public Double getAum()
   {
      return aum;
   }

   public void setAum(Double aum)
   {
      this.aum = aum;
   }

   public Double getBeta()
   {
      return beta;
   }

   public void setBeta(Double beta)
   {
      this.beta = beta;
   }

   public Double getSecurityRiskSTD()
   {
      return securityRiskSTD;
   }

   public void setSecurityRiskSTD(Double securityRiskSTD)
   {
      this.securityRiskSTD = securityRiskSTD;
   }

   public Double getLowerbound()
   {
      return lowerbound;
   }

   public void setLowerbound(Double lowerbound)
   {
      this.lowerbound = lowerbound;
   }

   public Double getUpperbound()
   {
      return upperbound;
   }

   public void setUpperbound(Double upperbound)
   {
      this.upperbound = upperbound;
   }

   public Double getYield()
   {
      return yield;
   }

   public void setYield(Double yield)
   {
      this.yield = yield;
   }

   public String getRbsaFlag()
   {
      return rbsaFlag;
   }

   public void setRbsaFlag(String rbsaFlag)
   {
      this.rbsaFlag = rbsaFlag;
   }

   public String getBusinessdate()
   {
      return businessdate;
   }

   public void setBusinessdate(String businessdate)
   {
      this.businessdate = businessdate;
   }

   public String getOnDemand()
   {
      return onDemand;
   }

   public void setOnDemand(String onDemand)
   {
      this.onDemand = onDemand;
   }

   @Override
   public String toString()
   {
      return "SecMaster{" +
         "instrumentId=" + instrumentId +
         ", status='" + status + '\'' +
         ", ticker='" + ticker + '\'' +
         ", tickerSource='" + tickerSource + '\'' +
         ", cusip='" + cusip + '\'' +
         ", isin='" + isin + '\'' +
         ", name='" + name + '\'' +
         ", assetClass='" + assetClass + '\'' +
         ", subClass='" + subClass + '\'' +
         ", type='" + type + '\'' +
         ", style='" + style + '\'' +
         ", expenseRatio=" + expenseRatio +
         ", lowerBoundReturn=" + lowerBoundReturn +
         ", upperBoundReturn=" + upperBoundReturn +
         ", taxableReturn=" + taxableReturn +
         ", nontaxableReturn=" + nontaxableReturn +
         ", inception=" + inception +
         ", adv3months=" + adv3months +
         ", issuer='" + issuer + '\'' +
         ", aum=" + aum +
         ", beta=" + beta +
         ", securityRiskSTD=" + securityRiskSTD +
         ", lowerbound=" + lowerbound +
         ", upperbound=" + upperbound +
         ", yield=" + yield +
         ", rbsaFlag='" + rbsaFlag + '\'' +
         ", businessdate='" + businessdate + '\'' +
         ", onDemand='" + onDemand + '\'' +
         '}';
   }
}
