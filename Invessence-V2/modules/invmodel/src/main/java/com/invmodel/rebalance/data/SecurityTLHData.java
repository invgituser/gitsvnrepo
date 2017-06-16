package com.invmodel.rebalance.data;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 11/11/14
 * Time: 10:15 PM
 * To change this template use File | Settings | File Templates.
 */
public class SecurityTLHData
{
   private String ticker;
   private ArrayList<TLHData> tlhdata;

   public SecurityTLHData()
   {
   }

   public String getTicker()
   {
      return ticker;
   }

   public void setTicker(String ticker)
   {
      this.ticker = ticker;
   }

   public void addTLHData(String tlhticker, String active, Double price, Double weight, String type, String style, String assetclass, String subclass)
   {
      if (tlhdata == null)
         tlhdata = new ArrayList<TLHData>();

      TLHData data = new TLHData(tlhticker, active, price, weight, type, style, assetclass,subclass);
      tlhdata.add(data);
   }

   public ArrayList<TLHData> getTlhdata()
   {
      return tlhdata;
   }

   public void setTlhdata(ArrayList<TLHData> tlhdata)
   {
      this.tlhdata = tlhdata;
   }

   public ArrayList<TLHData> getSortedTLHDataByWeight() {
      Collections.sort(tlhdata);
      return tlhdata;
   }


}
