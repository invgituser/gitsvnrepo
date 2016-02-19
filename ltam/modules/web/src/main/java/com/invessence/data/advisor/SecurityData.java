package com.invessence.data.advisor;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 2/6/16
 * Time: 10:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class SecurityData
{
   private String source;
   private String cusip;
   private String ticker;
   private String description;
   private Double price;
   private Double position;
   private Integer count;

   public SecurityData()
   {
      source = null;
      cusip = null;
      ticker = null;
      description = null;
      price = 0.0;
      position = 0.0;
      count = 0;
   }

   public SecurityData(String source,
                       String cusip, String ticker, String description,
                       Double price, Double position, Integer count)
   {
      this.source = source;
      this.cusip = cusip;
      this.ticker = ticker;
      this.description = description;
      this.price = price;
      this.position = position;
      this.count = count;
   }

   public String getSource()
   {
      return source;
   }

   public void setSource(String source)
   {
      this.source = source;
   }

   public String getCusip()
   {
      return cusip;
   }

   public void setCusip(String cusip)
   {
      this.cusip = cusip;
   }

   public String getTicker()
   {
      return ticker;
   }

   public void setTicker(String ticker)
   {
      this.ticker = ticker;
   }

   public String getDescription()
   {
      return description;
   }

   public void setDescription(String description)
   {
      this.description = description;
   }

   public Double getPrice()
   {
      return price;
   }

   public void setPrice(Double price)
   {
      this.price = price;
   }

   public Double getPosition()
   {
      return position;
   }

   public void setPosition(Double position)
   {
      this.position = position;
   }

   public Integer getCount()
   {
      return count;
   }

   public void setCount(Integer count)
   {
      this.count = count;
   }
}
