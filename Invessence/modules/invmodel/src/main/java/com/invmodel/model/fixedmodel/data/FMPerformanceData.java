package com.invmodel.model.fixedmodel.data;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 7/27/15
 * Time: 12:05 PM
 * To change this template use File | Settings | File Templates.
 */
public class FMPerformanceData
{
   private String theme;
   private String index;
   private FMProjection data;

   public FMPerformanceData()
   {
   }

   public FMPerformanceData(String theme, String index,
                            FMProjection data)
   {
      this.theme = theme;
      this.index = index;
      this.data = data;
   }

   public String getTheme()
   {
      return theme;
   }

   public String getIndex()
   {
      return index;
   }

   public FMProjection getPerformance()
   {
      return data;
   }
}
