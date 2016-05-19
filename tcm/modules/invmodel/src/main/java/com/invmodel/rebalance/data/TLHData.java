package com.invmodel.rebalance.data;

import java.util.Comparator;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 11/11/14
 * Time: 10:15 PM
 * To change this template use File | Settings | File Templates.
 */
public class TLHData implements Comparable<TLHData>
{
   private String tlhticker;
   private String active, type, style, assetclass, subclass;
   private Double weight, expenseRatio,lowerBoundReturn, upperBoundReturn,taxableReturn,adv3months,aum, beta,securityRiskSTD, yield;
   private Double price;
   //private Boolean TLHFlag;

   public TLHData()
   {
   }

   public TLHData(String tlhticker, String active, Double price, Double weight, String type, String style, String assetclass, String subclass)
   {
      this.tlhticker = tlhticker;
      this.active = active;
      this.price = price;
      this.weight = weight;
      this.type = type;
      this.style = style;
      this.assetclass = assetclass;
      this.subclass = subclass;
      //this.TLHFlag = TLHFlag;
   }

   /*public Boolean isTLHFlag()
   {
      return TLHFlag;
   }

   public void setTLHFlag(Boolean TLHFlag)
   {
      this.TLHFlag = TLHFlag;
   }*/

   public String getTlhticker()
   {
      return tlhticker;
   }

   public void setTlhticker(String tlhticker)
   {
      this.tlhticker = tlhticker;
   }

   public String getActive()
   {
      return active;
   }

   public void setActive(String active)
   {
      this.active = active;
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

   public String getAssetclass()
   {
      return assetclass;
   }

   public void setAssetclass(String assetclass)
   {
      this.assetclass = assetclass;
   }

   public String getSubclass()
   {
      return subclass;
   }

   public void setSubclass(String subclass)
   {
      this.subclass = subclass;
   }

   public Double getWeight()
   {
      return weight;
   }

   public void setWeight(Double weight)
   {
      this.weight = weight;
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

   public Double getYield()
   {
      return yield;
   }

   public void setYield(Double yield)
   {
      this.yield = yield;
   }

   public Double getPrice()
   {
      return price;
   }

   public void setPrice(Double price)
   {
      this.price = price;
   }
   @Override
   public int compareTo(TLHData tdata) {

      Double compareWeight=((TLHData)tdata).getWeight();
        /* For Ascending order*/
      return (int) ((this.weight-compareWeight) * 100.00);

        /* For Descending order do like this */
      //return (int) ((compareWeight- this.weight) * 100.00)
   }   /*Comparator for sorting the list by Student Name*/

   public static Comparator<TLHData> TickerComparator = new Comparator<TLHData>() {


      public int compare(TLHData t1, TLHData t2) {
         String ticker1 = t1.getTlhticker().toUpperCase();
         String ticker2 = t1.getTlhticker().toUpperCase();

         //ascending order
         return ticker1.compareTo(ticker2);

         //descending order
         //return ticker2.compareTo(ticker1);
      }};

}
