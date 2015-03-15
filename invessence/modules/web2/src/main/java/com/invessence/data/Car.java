package com.invessence.data;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 10/17/14
 * Time: 7:01 PM
 * To change this template use File | Settings | File Templates.
 */
public class Car
{
   private String id;
   private String brand;
   private Integer year;
   private String color;
   private Integer price;
   private Boolean sold;

   public Car(String id, String brand, Integer year, String color,
              Integer price, Boolean sold
              )
   {
      this.id = id;
      this.brand = brand;
      this.year = year;
      this.color = color;
      this.price = price;
      this.sold = sold;
   }

   public String getId()
   {
      return id;
   }

   public void setId(String id)
   {
      this.id = id;
   }

   public String getBrand()
   {
      return brand;
   }

   public void setBrand(String brand)
   {
      this.brand = brand;
   }

   public Integer getYear()
   {
      return year;
   }

   public void setYear(Integer year)
   {
      this.year = year;
   }

   public String getColor()
   {
      return color;
   }

   public void setColor(String color)
   {
      this.color = color;
   }

   public Integer getPrice()
   {
      return price;
   }

   public void setPrice(Integer price)
   {
      this.price = price;
   }

   public Boolean getSold()
   {
      return sold;
   }

   public void setSold(Boolean sold)
   {
      this.sold = sold;
   }
}
