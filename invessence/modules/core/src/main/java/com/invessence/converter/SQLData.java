package com.invessence.converter;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 4/14/14
 * Time: 12:46 PM
 * To change this template use File | Settings | File Templates.
 */
public class SQLData implements Serializable
{
   private static final long serialVersionUID = -1983L;

   public String getStrData(Object dataobj)
   {
      String val = "";
      try
      {
         if (dataobj == null)
            return null;
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
            return 0;
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
            return 0L;
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
            return 0.0;
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
