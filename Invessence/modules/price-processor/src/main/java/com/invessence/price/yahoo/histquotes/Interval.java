package com.invessence.price.yahoo.histquotes;

public enum Interval
{
  DAILY("d"),  WEEKLY("w"),  MONTHLY("m");
  
  private final String tag;
  
  private Interval(String tag)
  {
    this.tag = tag;
  }
  
  public String getTag()
  {
    return this.tag;
  }
}
