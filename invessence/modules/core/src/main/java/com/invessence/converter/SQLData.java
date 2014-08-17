package com.invessence.converter;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 4/14/14
 * Time: 12:46 PM
 * To change this template use File | Settings | File Templates.
 */
public class SQLData
{
   public String getStrData(Object dataobj)
   {
      String val = "";
      try
      {
         if (dataobj == null)
            return "";
         else
            val = dataobj.toString();
      }
      catch (Exception ex)
      {
         return "";
      }
      return val;
   }

   public Integer getIntData(Object dataobj)
   {
      Integer val = null;
      try
      {
         if (dataobj == null)
            return null;
         else {
            Double dValue =  getDoubleData(dataobj);
            val = dValue.intValue();
         }
      }
      catch (Exception ex)
      {
         return null;
      }
      return val;
   }

   public Long getLongData(Object dataobj)
   {
      Long val = null;
      try
      {
         if (dataobj == null)
            return null;
         else
            val = Long.parseLong(dataobj.toString());
      }
      catch (Exception ex)
      {
         return null;
      }
      return val;
   }

   public Double getDoubleData(Object dataobj)
   {
      Double val = null;
      try
      {
         if (dataobj == null)
            return null;
         else
            val = Double.parseDouble(dataobj.toString());
      }
      catch (Exception ex)
      {
         return null;
      }
      return val;
   }

   public String getDateData(Object dataobj)
   {
      String val = "";
      try
      {
         if (dataobj == null)
            return "";
         else
            val = dataobj.toString();
      }
      catch (Exception ex)
      {
         return "";
      }
      return val;
   }

}
