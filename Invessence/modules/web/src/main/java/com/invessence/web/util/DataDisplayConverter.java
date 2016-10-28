package com.invessence.web.util;

import java.io.Serializable;
import java.net.*;
import java.text.DecimalFormat;
import java.util.*;
import java.io.Serializable.*;
import java.text.DecimalFormat;
import javax.faces.bean.*;
import javax.servlet.http.HttpServletRequest;

@ManagedBean(name = "dataDisplayConverter")
@SessionScoped
public class DataDisplayConverter implements Serializable
{

   public String displayAsMoneyRounded(Integer value)
   {
      if (value != null)
      {
         DecimalFormat df = new DecimalFormat("###,####,###");
         String strValue = df.format(value);
         return "$" + strValue;
      }
      else
      {
         return "-";
      }
   }

      public String displayAsMoney(Integer value)
      {
         if (value != null)
         {
            DecimalFormat df = new DecimalFormat("###,####,###.00");
            String strValue = df.format(value);
            return "$" + strValue;
         }
         else
         {
            return "-";
         }
      }

   public String displayAsMoney(Double value)
   {
      if (value != null)
      {
         DecimalFormat df = new DecimalFormat("###,####,##0.00");
         String strValue = df.format(value);
         return "$" + strValue;
      }
      else
      {
         return "-";
      }
   }



   public String displayAsPercent(Double value)
   {
      if (value != null)
      {
         DecimalFormat df = new DecimalFormat("##0.00");
         String strValue = df.format(value);
         return strValue +"%";
      }
      else
      {
         return "0.00%";
      }
   }

   public String displayAsPercent(Integer value)
   {
      if (value != null)
      {
         DecimalFormat df = new DecimalFormat("##0");
         String strValue = df.format(value);
         return strValue + "%";
      }
      else
      {
         return "0%";
      }
   }

   public String displayWithComma(Double value)
   {
      if (value != null)
      {
         DecimalFormat df = new DecimalFormat("###,###,###.##");
         String strValue = df.format(value);
         return strValue;
      }
      else
      {
         return "";
      }
   }

   public String displayWithComma(Integer value)
   {
      if (value != null)
      {
         DecimalFormat df = new DecimalFormat("###,###,##0");
         String strValue = df.format(value);
         return strValue;
      }
      else
      {
         return "";
      }
   }

   public String displayWithFormatAppender(Double value, String format, String appender)
   {
      if (value != null)
      {
         DecimalFormat df = new DecimalFormat(format);
         String strValue = df.format(value);
         return strValue + appender;
      }
      else
      {
         return "";
      }

   }
}
