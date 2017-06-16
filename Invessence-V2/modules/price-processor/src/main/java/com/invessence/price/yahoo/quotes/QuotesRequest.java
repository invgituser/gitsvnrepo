package com.invessence.price.yahoo.quotes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.invessence.price.yahoo.Utils;
import com.invessence.price.yahoo.YahooFinance;

public abstract class QuotesRequest<T>
{
  protected final String query;
  protected List<QuotesProperty> properties;
  
  public QuotesRequest(String query, List<QuotesProperty> properties)
  {
    this.query = query;
    this.properties = properties;
  }
  
  public String getQuery()
  {
    return this.query;
  }
  
  public List<QuotesProperty> getProperties()
  {
    return this.properties;
  }
  
  public void setProperties(List<QuotesProperty> properties)
  {
    this.properties = properties;
  }
  
  protected abstract T parseCSVLine(String paramString);
  
  private String getFieldsString()
  {
    StringBuilder result = new StringBuilder();
    for (QuotesProperty property : this.properties) {
      result.append(property.getTag());
      //System.out.println("Param :"+property.getTag());
    }
    return result.toString();
  }
  
  public T getSingleResult()
    throws IOException
  {
    List<T> results = getResult();
    if (results.size() > 0) {
      return results.get(0);
    }
    return null;
  }
  
  public List<T> getResult()
    throws IOException
  {
    List<T> result = new ArrayList();
    
    Map<String, String> params = new LinkedHashMap();
    params.put("s", this.query);
    params.put("f", getFieldsString());
    params.put("e", ".csv");
    
    String url = "http://finance.yahoo.com/d/quotes.csv?" + Utils.getURLParameters(params);
    

    YahooFinance.logger.log(Level.INFO, "Sending request: " + url);
    
    URL request = new URL(url);
    URLConnection connection = request.openConnection();
    InputStreamReader is = new InputStreamReader(connection.getInputStream());
    BufferedReader br = new BufferedReader(is);
    for (String line = br.readLine(); line != null; line = br.readLine()) {
      if (line.equals("Missing Symbols List."))
      {
        YahooFinance.logger.log(Level.SEVERE, "The requested symbol was not recognized by Yahoo Finance");
      }
      else
      {
        YahooFinance.logger.log(Level.INFO, "Parsing CSV line: " + Utils.unescape(line));
        
        T data = parseCSVLine(line);
        result.add(data);
      }
    }
    return result;
  }
}
