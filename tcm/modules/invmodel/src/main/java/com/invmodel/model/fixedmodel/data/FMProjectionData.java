package com.invmodel.model.fixedmodel.data;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 8/1/16
 * Time: 3:04 PM
 * To change this template use File | Settings | File Templates.
 */
public class FMProjectionData
{
   private Integer model;
   private Integer year;
   private Double lower1;
   private Double lower2;
   private Double mid;
   private Double upper1;
   private Double upper2;

   public FMProjectionData()
   {
   }

   public Integer getModel()
   {
      return model;
   }

   public void setModel(Integer model)
   {
      this.model = model;
   }

   public Integer getYear()
   {
      return year;
   }

   public void setYear(Integer year)
   {
      this.year = year;
   }

   public Double getLower1()
   {
      return lower1;
   }

   public void setLower1(Double lower1)
   {
      this.lower1 = lower1;
   }

   public Double getLower2()
   {
      return lower2;
   }

   public void setLower2(Double lower2)
   {
      this.lower2 = lower2;
   }

   public Double getMid()
   {
      return mid;
   }

   public void setMid(Double mid)
   {
      this.mid = mid;
   }

   public Double getUpper1()
   {
      return upper1;
   }

   public void setUpper1(Double upper1)
   {
      this.upper1 = upper1;
   }

   public Double getUpper2()
   {
      return upper2;
   }

   public void setUpper2(Double upper2)
   {
      this.upper2 = upper2;
   }
}
