package com.invessence.price.yahoo;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Utils
{
  public static final BigDecimal HUNDRED = new BigDecimal(100);
  public static final BigDecimal THOUSAND = new BigDecimal(1000);
  public static final BigDecimal MILLION = new BigDecimal(1000000);
  public static final BigDecimal BILLION = new BigDecimal(1000000000);
  
  public static String join(String[] data, String d)
  {
    if (data.length == 0) {
      return "";
    }
    StringBuilder sb = new StringBuilder();
    int i;
    for (i = 0; i < data.length - 1; i++) {
      sb.append(data[i]).append(d);
    }
    return data[i];
  }
  
  private static String cleanNumberString(String data)
  {
    return join(data.trim().split(","), "");
  }
  
  private static boolean isParseable(String data)
  {
    return (data != null) && (!data.equals("N/A")) && (!data.equals("-")) && (!data.equals(""));
  }
  
  public static BigDecimal getBigDecimal(String data)
  {
    BigDecimal result = BigDecimal.ZERO;
    if (!isParseable(data)) {
      return result;
    }
    try
    {
      data = cleanNumberString(data);
      char lastChar = data.charAt(data.length() - 1);
      BigDecimal multiplier = BigDecimal.ONE;
      switch (lastChar)
      {
      case 'B': 
        data = data.substring(0, data.length() - 1);
        multiplier = BILLION;
        break;
      case 'M': 
        data = data.substring(0, data.length() - 1);
        multiplier = MILLION;
        break;
      case 'K': 
        data = data.substring(0, data.length() - 1);
        multiplier = THOUSAND;
      }
      result = new BigDecimal(data).multiply(multiplier);
    }
    catch (NumberFormatException e)
    {
      YahooFinance.logger.log(Level.INFO, "Failed to parse: " + data, e);
    }
    return result;
  }
  
  public static BigDecimal getBigDecimal(String dataMain, String dataSub)
  {
    BigDecimal main = getBigDecimal(dataMain);
    BigDecimal sub = getBigDecimal(dataSub);
    if (main.compareTo(BigDecimal.ZERO) == 0) {
      return sub;
    }
    return main;
  }
  
  public static double getDouble(String data)
  {
    double result = 0.0D;
    if (!isParseable(data)) {
      return result;
    }
    try
    {
      data = cleanNumberString(data);
      char lastChar = data.charAt(data.length() - 1);
      int multiplier = 1;
      switch (lastChar)
      {
      case 'B': 
        data = data.substring(0, data.length() - 1);
        multiplier = 1000000000;
        break;
      case 'M': 
        data = data.substring(0, data.length() - 1);
        multiplier = 1000000;
        break;
      case 'K': 
        data = data.substring(0, data.length() - 1);
        multiplier = 1000;
      }
      result = Double.parseDouble(data) * multiplier;
    }
    catch (NumberFormatException e)
    {
      YahooFinance.logger.log(Level.INFO, "Failed to parse: " + data, e);
    }
    return result;
  }
  
  public static int getInt(String data)
  {
    int result = 0;
    if (!isParseable(data)) {
      return result;
    }
    try
    {
      data = cleanNumberString(data);
      result = Integer.parseInt(data);
    }
    catch (NumberFormatException e)
    {
      YahooFinance.logger.log(Level.INFO, "Failed to parse: " + data, e);
    }
    return result;
  }
  
  public static long getLong(String data)
  {
    long result = 0L;
    if (!isParseable(data)) {
      return result;
    }
    try
    {
      data = cleanNumberString(data);
      result = Long.parseLong(data);
    }
    catch (NumberFormatException e)
    {
      YahooFinance.logger.log(Level.INFO, "Failed to parse: " + data, e);
    }
    return result;
  }
  
  public static BigDecimal getPercent(BigDecimal numerator, BigDecimal denominator)
  {
    if (denominator.compareTo(BigDecimal.ZERO) == 0) {
      return BigDecimal.ZERO;
    }
    return numerator.divide(denominator, 4, 6).multiply(HUNDRED).setScale(2, 6);
  }
  
  public static double getPercent(double numerator, double denominator)
  {
    if (denominator == 0.0D) {
      return 0.0D;
    }
    return numerator / denominator * 100.0D;
  }
  
  private static String getDividendDateFormat(String date)
  {
    if (date.matches("[0-9][0-9]-...-[0-9][0-9]")) {
      return "dd-MMM-yy";
    }
    if (date.matches("[0-9]-...-[0-9][0-9]")) {
      return "d-MMM-yy";
    }
    if (date.matches("...[ ]+[0-9]+")) {
      return "MMM d";
    }
    return "M/d/yy";
  }
  
  public static Calendar parseDividendDate(String date)
  {
    if (!isParseable(date)) {
      return null;
    }
    date = date.trim();
    SimpleDateFormat format = new SimpleDateFormat(getDividendDateFormat(date), Locale.US);
    format.setTimeZone(TimeZone.getTimeZone("America/New_York"));
    try
    {
      Calendar today = Calendar.getInstance(TimeZone.getTimeZone("America/New_York"));
      Calendar parsedDate = Calendar.getInstance(TimeZone.getTimeZone("America/New_York"));
      parsedDate.setTime(format.parse(date));
      if (parsedDate.get(1) == 1970)
      {
        int monthDiff = parsedDate.get(2) - today.get(2);
        int year = today.get(1);
        if (monthDiff > 6) {
          year--;
        } else if (monthDiff < -6) {
          year++;
        }
        parsedDate.set(1, year);
      }
      return parsedDate;
    }
    catch (ParseException ex)
    {
      YahooFinance.logger.log(Level.SEVERE, ex.getMessage(), ex);
    }
    return null;
  }
  
  public static Calendar parseDateTime(String date, String time, TimeZone timeZone)
  {
    String datetime = date + " " + time;
    SimpleDateFormat format = new SimpleDateFormat("M/d/yyyy h:mma", Locale.US);
    
    format.setTimeZone(timeZone);
    try
    {
      if ((isParseable(date)) && (isParseable(time)))
      {
        Calendar c = Calendar.getInstance();
        c.setTime(format.parse(datetime));
        return c;
      }
    }
    catch (ParseException ex)
    {
      YahooFinance.logger.log(Level.SEVERE, ex.getMessage(), ex);
    }
    return null;
  }
  
  public static Calendar parseHistDate(String date)
  {
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
    format.setTimeZone(TimeZone.getTimeZone("America/New_York"));
    try
    {
      if (isParseable(date))
      {
        Calendar c = Calendar.getInstance();
        c.setTime(format.parse(date));
        return c;
      }
    }
    catch (ParseException ex)
    {
      YahooFinance.logger.log(Level.SEVERE, ex.getMessage(), ex);
    }
    return null;
  }
  
  public static String getURLParameters(Map<String, String> params)
  {
    StringBuilder sb = new StringBuilder();
    for (Map.Entry<String, String> entry : params.entrySet())
    {
      if (sb.length() > 0) {
        sb.append("&");
      }
      String key = (String)entry.getKey();
      String value = (String)entry.getValue();
      //System.out.println(key+" : "+value);
      try
      {
        key = URLEncoder.encode(key, "UTF-8");
        value = URLEncoder.encode(value, "UTF-8");
      }
      catch (UnsupportedEncodingException ex)
      {
        YahooFinance.logger.log(Level.SEVERE, ex.getMessage(), ex);
      }
      sb.append(String.format("%s=%s", new Object[] { key, value }));
    }
    return sb.toString();
  }
  
  public static String stripOverhead(String line)
  {
    return line.replaceAll("\"", "");
  }
  
  public static String unescape(String data)
  {
    StringBuilder buffer = new StringBuilder(data.length());
    for (int i = 0; i < data.length(); i++) {
    if (data.charAt(i) > 'Ā') {
        buffer.append("\\u").append(Integer.toHexString(data.charAt(i)));
      } else if (data.charAt(i) == '\n') {
        buffer.append("\\n");
      } else if (data.charAt(i) == '\t') {
        buffer.append("\\t");
      } else if (data.charAt(i) == '\r') {
        buffer.append("\\r");
      } else if (data.charAt(i) == '\b') {
        buffer.append("\\b");
      } else if (data.charAt(i) == '\f') {
        buffer.append("\\f");
      } else if (data.charAt(i) == '\'') {
        buffer.append("\\'");
      } else if (data.charAt(i) == '"') {
        buffer.append("\\\"");
      } else if (data.charAt(i) == '\\') {
        buffer.append("\\\\");
      } else {
        buffer.append(data.charAt(i));
      }
    }
    return buffer.toString();
  }
}
