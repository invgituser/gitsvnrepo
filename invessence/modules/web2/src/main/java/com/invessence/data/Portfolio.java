package com.invessence.data;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 10/17/14
 * Time: 7:01 PM
 * To change this template use File | Settings | File Templates.
 */
public class Portfolio
{
   private String ticker;
   private String name;
   private String assetclass;
   private String subclass;
   private Double price;
   private Double weight;
   private Double money;

   public Portfolio(String ticker, String name, String assetclass, String subclass,  Double price, Double weight, Double money)
   {
      this.ticker = ticker;
      this.name = name;
      this.assetclass = assetclass;
      this.subclass = subclass;
      this.price = price;
      this.weight = weight;
      this.money = money;
   }

   public String getTicker()
   {
      return ticker;
   }

   public String getAssetclass()
   {
      return assetclass;
   }

   public String getSubclass()
   {
      return subclass;
   }

   public String getName()
   {
      return name;
   }

   public Double getPrice()
   {
      return price;
   }

   public Double getWeight()
   {
      return weight;
   }

   public Double getMoney()
   {
      return money;
   }
}
