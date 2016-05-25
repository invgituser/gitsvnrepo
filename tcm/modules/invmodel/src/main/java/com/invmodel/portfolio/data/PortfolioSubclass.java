package com.invmodel.portfolio.data;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 6/25/14
 * Time: 8:53 AM
 * To change this template use File | Settings | File Templates.
 */
public class PortfolioSubclass
{
   private String name;
   private String parentclass = null;
   private String subasset = "";
   private String color = "";
   private Double weight = 0.0;
   private Double money=0.0;
   private Boolean include;

   public PortfolioSubclass() {
      super();
   }

   public PortfolioSubclass(String name, String parentclass, String subasset,
                            String color, Double weight, Double money,
                            Boolean include)
   {
      this.name =  name;
      this.parentclass = parentclass;
      this.subasset = subasset;
      this.color = color;
      this.weight = weight;
      this.money=money;
      this.include = include;
   }

   public String getName()
   {
      return name;
   }

   public void setName(String name)
   {
      this.name = name;
   }

   public String getParentclass()
   {
      return parentclass;
   }

   public void setParentclass(String parentclass)
   {
      this.parentclass = parentclass;
   }

   public String getSubasset()
   {
      return subasset;
   }

   public void setSubasset(String subasset)
   {
      this.subasset = subasset;
   }

   public String getColor()
   {
      return color;
   }

   public void setColor(String color)
   {
      this.color = color;
   }

   public Double getRoundedWeight() {
      return (Math.round(weight * 10000.00) / 100.00);
   }

   public Double getWeight()
   {
      return weight;
   }

   public void setWeight(Double weight)
   {
      this.weight = weight;
   }

   public Double getMoney()
   {
      return money;
   }

   public void setMoney(Double money)
   {
      this.money = money;
   }

   public Boolean getInclude()
   {
      return include;
   }

   public void setInclude(Boolean include)
   {
      this.include = include;
   }
}
