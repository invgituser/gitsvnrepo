package com.invessence.price.xignite.util;

import java.text.SimpleDateFormat;
import java.util.*;

import com.invessence.price.processor.bean.*;
import com.invessence.price.xignite.bo.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.*;

/**
 * This calls can be used as utility class, contains methods for conversion of
 * json to different objects or business objects
 */
public class JsonToObject
{
   SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
   SimpleDateFormat nf = new SimpleDateFormat("yyyy-MM-dd");

   /**
    * this method take Json array and  object of list type of End of the day
    * quotes upon parsing json array object is populated as a list
    *
    * @param jsonArray
    * @param
    * @return
    */
   public EndOfDayQuotes parseToEndOfDayQuote(JSONArray jsonArray)
   {
      //System.out.println("water" +jsonArray);
      EndOfDayQuotes endOfDayQuotes = new EndOfDayQuotes();
      // iterate json array and store each iteration in a list element
      String outcome = "";
      String symbol = "";
      System.out.println("jsonArray.length(): " + jsonArray.length());
      List<EndOfDayQuote> endOfDayQuotesLst = new ArrayList<EndOfDayQuote>();
      try
      {
         for (int i = 0; i < jsonArray.length(); i++)
         {
            JSONObject jsonObject = new JSONObject();
            JSONObject jsonObject1 = new JSONObject();
            jsonObject = (JSONObject) jsonArray.get(i);
            //jsonObject1 = (JSONObject) jsonArray.getJSONObject()
            // TODO now you have json object instance with you, each
            // instance
            // contains data for a ticker, while parsing this you need to
            // check
            // for api errors
            outcome = jsonObject.get("Outcome").toString();

            //jsonObject1 = (JSONObject) jsonObject.get("Security");
            //symbol = jsonObject1.get("Symbol").toString();
            if (outcome.equals("RegistrationError"))
            {
               // code to handle registration errors , collect all the
               // errror details here

               System.out.println("RegistrationError for XIGNITE:");
               throw new Exception("Registration Error for XIGNITE");
               //mailAlertMsg.append("RegistrationError for  ticker   "  +symbol);

            }
            else if (outcome.equals("RequestError"))
            {
               // code to handle request errors , collect all the errror
               // details here
               //mailAlertMsg.append("Request Error for  ticker   "  +symbol);
               System.out.println("RequestError");
               throw new Exception("RequestError");
               //continue;
            }
            else if (outcome.equals("SystemError"))
            {
               // code to handle system errors , collect all the errror
               // details here
               //mailAlertMsg.append("System  Error for  ticker   "  +symbol);

               System.out.println("SystemError   ");
               continue;
            }
            else
            { // Success
               System.out.println("Object Parsing ");
               EndOfDayQuote endOfDayQuote = new EndOfDayQuote();
               CommonTypes commonTypes = new CommonTypes();
               endOfDayQuote.setDate(jsonObject.get("Date") == null ? null : jsonObject.getString("Date")); //tmp
               //System.out.println("***********************DATE*********" +endOfDayQuote.getDate());
               endOfDayQuote.setLast(jsonObject.getDouble("Last"));
               endOfDayQuote.setOpen(jsonObject.getDouble("Open")); //tmp
               endOfDayQuote.setHigh(jsonObject.getDouble("High")); //tmp
               endOfDayQuote.setLow(jsonObject.getDouble("Low"));   //tmp
               endOfDayQuote.setVolume(jsonObject.getDouble("Volume"));  //tmp
               endOfDayQuote.setLastClose(jsonObject.getDouble("LastClose")); //tmp
               endOfDayQuote.setChangeFromOpen(jsonObject.getDouble("ChangeFromOpen"));
               endOfDayQuote.setPercentChangeFromOpen(jsonObject.getDouble("PercentChangeFromOpen"));
               endOfDayQuote.setChangeFromLastClose(jsonObject.getDouble("ChangeFromLastClose"));
               endOfDayQuote.setPercentChangeFromLastClose(jsonObject.getDouble("PercentChangeFromLastClose"));
               endOfDayQuote.setSplitRatio(jsonObject.getDouble("SplitRatio"));
               endOfDayQuote.setCummulativeCashDividend(jsonObject.getDouble("CummulativeCashDividend"));
               endOfDayQuote.setCummulativeStockDividend(jsonObject.getDouble("CummulativeStockDividend"));
               endOfDayQuote
                  .setCummulativeStockDividendRatio(jsonObject.getDouble("CummulativeStockDividendRatio"));
               endOfDayQuote
                  .setCurrency(jsonObject.get("Currency") == null ? null : jsonObject.getString("Currency"));
               endOfDayQuote.setAdjustmentMethods(jsonObject.get("AdjustmentMethodUsed") == null ? null
                                                     : jsonObject.getString("AdjustmentMethodUsed"));
               endOfDayQuote.setDataConfidence(
                  jsonObject.get("DataConfidence") == null ? null : jsonObject.getString("DataConfidence"));
               endOfDayQuote.setLastPriceSource(
                  jsonObject.get("LastPriceSource") == null ? null : jsonObject.getString("LastPriceSource"));
               endOfDayQuote.setLastPriceType(
                  jsonObject.get("LastPriceType") == null ? null : jsonObject.getString("LastPriceType"));
               endOfDayQuote.setEndOfDayPrice(jsonObject.getDouble("EndOfDayPrice")); //tmp
               endOfDayQuote.setEndOfDayPriceDate(jsonObject.get("EndOfDayPriceDate") == null ? null
                                                     : jsonObject.getString("EndOfDayPriceDate"));
               endOfDayQuote.setEndOfDayPriceMethodUsed(jsonObject.get("EndOfDayPriceMethodUsed") == null ? null
                                                           : jsonObject.getString("EndOfDayPriceMethodUsed"));
               endOfDayQuote.setEndOfDayPriceTimings(jsonObject.get("EndOfDayPriceTiming") == null ? null
                                                        : jsonObject.getString("EndOfDayPriceTiming"));
               endOfDayQuote.setEndOfDayPriceSource(jsonObject.get("EndOfDayPriceSource") == null ? null
                                                       : jsonObject.getString("EndOfDayPriceSource"));
               endOfDayQuote.setExchangeClose(jsonObject.getDouble("ExchangeClose"));

               endOfDayQuote.setValuation(jsonObject.getDouble("Valuation"));
               endOfDayQuote.setLastTradeDate(
                  jsonObject.get("LastTradeDate") == null ? null : jsonObject.getString("LastTradeDate"));
               endOfDayQuote.setExchangeCloseDate(jsonObject.get("ExchangeCloseDate") == null ? null
                                                     : jsonObject.getString("ExchangeCloseDate"));
               /*if(jsonObject.get("ValuationDate").equals("null")){
                  System.out.println("1");
					}else{
						System.out.println("2");
					}*/
               endOfDayQuote.setValuationDate(
                  jsonObject.get("ValuationDate") == null ? null : jsonObject.getString("ValuationDate"));
               System.out.println("************common types****************" + endOfDayQuote.getValuationDate());
               commonTypes.setOutcome(jsonObject.get("Outcome") == null ? null : jsonObject.getString("Outcome"));
               commonTypes.setMessage(jsonObject.get("Message") == null ? null : jsonObject.getString("Message"));
               commonTypes.setIdentity(jsonObject.get("Identity") == null ? null : jsonObject.getString("Identity"));
               commonTypes.setDelay(jsonObject.getDouble("Delay"));
               endOfDayQuote.setCommTypes(commonTypes);
               System.out.println("************common types****************" + endOfDayQuote.getCommTypes().getOutcome());
               //  if(jsonObject.get("Security").equals("null")){
               // setting security object
               JSONObject sec = (JSONObject) jsonObject.get("Security");
               Securities securities = new Securities();
               CommonTypes commonsecurities = new CommonTypes();

               securities.setCik(sec.get("CIK") == null ? null : sec.getString("CIK"));
               System.out.println("***********security CIK*******" + securities.getCik());
               securities.setCusip(sec.get("Cusip") == null ? null : sec.getString("Cusip"));
               securities.setSymbol(sec.get("Symbol") == null ? null : sec.getString("Symbol")); //tmp
               securities.setIsin(sec.get("ISIN") == null ? null : sec.getString("ISIN"));
               securities.setValoren(sec.get("Valoren") == null ? null : sec.getString("Valoren"));
               securities.setName(sec.get("Name") == null ? null : sec.getString("Name"));
               //System.out.println("water:"+ securities);
               securities.setMarket(sec.get("Market") == null ? null : sec.getString("Market"));
               securities.setCategoryOrIndustry(
                  sec.get("CategoryOrIndustry") == null ? null : sec.getString("CategoryOrIndustry"));
               commonsecurities.setOutcome(sec.get("Outcome") == null ? null : sec.getString("Outcome"));
               commonsecurities.setMessage(sec.get("Message") == null ? null : sec.getString("Message"));
               commonsecurities.setIdentity(sec.get("Identity") == null ? null : sec.getString("Identity"));
               commonsecurities.setDelay(sec.getDouble("Delay"));


               securities.setCommonTypes(commonsecurities);
               endOfDayQuote.setSecurity(securities);

               System.out.println("Security is : " + jsonObject.get("Security"));

               System.out.println("" + jsonObject.get("Date").toString());
               // Adding object to list for each iteration
               endOfDayQuotesLst.add(endOfDayQuote);
               //endOfDayQuotesLst.add(commonTypes);
            }
            endOfDayQuotes.setEndOfDayQuotes(endOfDayQuotesLst);
            // System.out.println("List*************"+ endOfDayQuotes.getEndOfDayQuotes());


         }
      }
      catch (JSONException e)
      {
         // TODO Auto-generated catch block
         System.out.println("*********JSON Exception ");
         e.printStackTrace();
      }
      catch (Exception e)
      {
         System.out.println(e.getMessage());
      }
      // TODO call email sending program to send collected errors
      //System.out.println("important:::" );
      return endOfDayQuotes;
      // System.out.println("***************"+ );
   }

   // converting object to calling program format
   public List<PriceData> objectConversion(EndOfDayQuotes endOfDayQuotes, String priceDate)
   {
      List<PriceData> pdList = null;
      pdList = new ArrayList<PriceData>();
      List<EndOfDayQuote> dayQuotes = endOfDayQuotes.getEndOfDayQuotes();
      for (EndOfDayQuote x : dayQuotes)
      {


         //Iterator<SecMaster> sec = tickerList.iterator();
         //while (sec.hasNext())


         //SecMaster secMaster = new SecMaster();
         try
         {
            //SecMaster secMaster1 = (SecMaster) sec.next();

            //double d = 1234.56;
            //long x = (long) d;
            Double d = x.getVolume();
            long volume = (new Double(d)).longValue();

            //String date = x.getDate();

            Date date1 = sdf.parse(x.getDate());

            Date buDate = nf.parse(priceDate);
            //Date date2 = nf.format(date1);
            //System.out.println("new format of date" +date1);
            // String s = sdf.format(date1);
            //Long volume = (Long) d;
            //Long volume = Long.parseLong(String.valueOf(x.getVolume()));

            PriceData pd = new PriceData(x.getSecurity().getSymbol(), nf.format(date1), x.getOpen(), x.getEndOfDayPrice(), x.getHigh(), x.getLow(), volume, new Date(), x.getLastClose(), new Long(2), new Date());

            //if(nf.format(date1).equals(nf.format(buDate)))
            //	{


            if (!x.getEndOfDayPrice().equals(0.0))
            {
               pdList.add(pd);
            }
            else
            {
               //mailAlertMsg.append("close price is zero for ticker" + x.getSecurity().getSymbol());
               System.out.println("close price is zero for ticker");
            }
            //	}
            //	else
            //	{
            //	mailAlertMsg.append("Price not available for ticker:" + x.getSecurity().getSymbol() + " for busunessdate :" + priceDate + "\n");
            //	}
            //System.out.println("*******************"+ x.getSecurity().getSymbol());
            //System.out.println("list" + pd);
         }
         catch (Exception e)
         {

            System.out.println("Price api exception for ticker:" + e.getMessage());
            e.printStackTrace();
         }


      }


      return pdList;
   }


}
