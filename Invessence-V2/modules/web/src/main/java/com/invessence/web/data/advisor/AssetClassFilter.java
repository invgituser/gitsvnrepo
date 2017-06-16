package com.invessence.web.data.advisor;

import java.io.Serializable;


/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 1/9/14
 * Time: 8:58 PM
 * To change this template use File | Settings | File Templates.
 */
@SuppressWarnings("UnusedDeclaration")
public class AssetClassFilter implements Serializable
{
   private String datakey;
   private String name;
   private String parentclass;
   private Integer level;
   private String color;
   private Double weight;
   private Double  value;
   private Double  risk;
   private Double  expectedReturn;

   public AssetClassFilter(String datakey, String name, String parentclass, String color,
                           Double weight, Double value,
                           Double risk, Double expectedReturn)
   {
      this.datakey = datakey;
      this.name = name;
      this.parentclass = parentclass;
      if (parentclass == null)
         this.level = 1;
      else
         this.level = 2;
      this.color = color;
      this.weight = weight;
      this.value = value;
      this.risk = risk;
      this.expectedReturn = expectedReturn;
   }

   public Boolean isSelectable() {
      return ((getLevel() != null) && (getLevel() >= 2));
   }

   public String getDatakey()
   {
      return datakey;
   }

   public void setDatakey(String datakey)
   {
      this.datakey = datakey;
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

   public Integer getLevel()
   {
      return level;
   }

   public void setLevel(Integer level)
   {
      this.level = level;
   }

   public String getColor()
   {
      return color;
   }

   public void setColor(String color)
   {
      this.color = color;
   }

   public Double getWeight()
   {
      return weight;
   }

   public void setWeight(Double weight)
   {
      this.weight = weight;
   }

   public Double getValue()
   {
      return value;
   }

   public void setValue(Double value) {
      this.value = value;
   }

   public Double getRisk()
   {
      return risk;
   }

   public void setRisk(Double risk)
   {
      this.risk = risk;
   }

   public Double getExpectedReturn()
   {
      return expectedReturn;
   }

   public void setExpectedReturn(Double expectedReturn)
   {
      this.expectedReturn = expectedReturn;
   }

}
