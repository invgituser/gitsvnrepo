package com.invessence.rbsa.dao.data;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 3/2/15
 * Time: 9:04 PM
 * To change this template use File | Settings | File Templates.
 */
public class ReturnInfo
{
   private static final int MAX_NO_ASSETS = 30; // Max number of indexname supported
   private static final int MAX_NO_RETURNS = 500;
   private ArrayList<String> orderedList = new ArrayList<String>();
   private Map<String, ArrayList<ReturnsData>> returnMap = new HashMap<String, ArrayList<ReturnsData>>();
   private Map<String, Integer> numofElements = new HashMap<String, Integer>();
  // private Map<String, String[]> dateElements = new HashMap<String, String[]>();

   public ReturnInfo()
   {
   }

/*
   public String getMinBusinessDate(String ticker)
   {
      ticker = ticker.toUpperCase();
      if (dateElements.containsKey(ticker))
         return dateElements.get(ticker)[0];
      else
         return "";
   }
*/


/*
   public String getMaxBusinessDate(String ticker)
   {
      ticker = ticker.toUpperCase();
      if (dateElements.containsKey(ticker))
         return dateElements.get(ticker)[1];
      else
         return "";
   }
*/

   public ArrayList<ReturnsData> getReturnData(String ticker)
   {
      ticker = ticker.toUpperCase();
      if (returnMap.containsKey(ticker))
         return returnMap.get(ticker);
      return null;
   }

   public void addData (String ticker, String businessdate, Double value) {
      try {
         ticker = ticker.toUpperCase();
         int element = 0;
         if (orderedList.size() <= MAX_NO_ASSETS) {
            if (! numofElements.containsKey(ticker))
               element = 1;
            else {
               element = numofElements.get(ticker) + 1;
            }

            if (element <= MAX_NO_RETURNS) {
               ReturnsData data = new ReturnsData(ticker, businessdate, value);

/*
               if (!dateElements.containsKey(ticker)) {
                  String [] dateData = new String[2];
                  dateData[0] = businessdate;
                  dateData[1] = businessdate;
                  dateElements.put(ticker,dateData);
               }
               else {
                  if (businessdate.compareTo(dateElements.get(ticker)[0]) < 0)
                     dateElements.get(ticker)[0] = businessdate;

                  if (businessdate.compareTo(dateElements.get(ticker)[1]) > 0)
                     dateElements.get(ticker)[1] = businessdate;

               }
*/

               if (! returnMap.containsKey(ticker)) {
                  ArrayList<ReturnsData> arraydata = new ArrayList<ReturnsData>();
                  arraydata.add(data);
                  returnMap.put(ticker, arraydata);
                  orderedList.add(ticker);
               }
               else {
                  returnMap.get(ticker).add(data);
               }

               numofElements.put(ticker,element);

            }


         }
      }
      catch (Exception ex) {

      }
   }

   public double[] getReturns(String ticker) {
      try {
         int count = 0;
         ticker = ticker.toUpperCase();
         double[] data = new double[returnMap.get(ticker).size()];
         for (int i=0; i < returnMap.get(ticker).size(); i++) {
            ReturnsData returnData = returnMap.get(ticker).get(i);
            data[count++] = returnData.getValue();
         }

         return data;
      }
      catch (Exception ex) {
         ex.printStackTrace();
      }

      return null;
   }


   public double[] getReturns(String ticker, String minDate, String maxDate, Integer numofElements) {
     double[] data = new double[numofElements];
     try {
        int count = 0;
        ticker = ticker.toUpperCase();
        for (int i=0; i < returnMap.get(ticker).size(); i++) {
           ReturnsData returnData = returnMap.get(ticker).get(i);
           if (returnData.getBusinessdate().compareTo(minDate) < 0)
              continue;
           if (returnData.getBusinessdate().compareTo(maxDate) > 0)
              continue;

           data[count++] = returnData.getValue();

        }

     }
     catch (Exception ex) {
        ex.printStackTrace();
     }

     return data;
   }

   public ArrayList<String> getOrderedList() {
      return orderedList;
   }

   public Integer getNoofReturns(String ticker) {
      ticker = ticker.toUpperCase();
      if (numofElements.containsKey(ticker))
         return numofElements.get(ticker);
      else
         return 0;
   }

   public Integer getNoofIndex() {
      return orderedList.size();
   }


   public Integer getShortestNumofReturnsData() {
      Integer numofReturns = MAX_NO_RETURNS;
      for (String tickers : orderedList) {
         numofReturns = (numofElements.get(tickers) < numofReturns) ? numofElements.get(tickers): numofReturns;
      }
      return numofReturns;
   }

   public double[][] getAllReturnsData(Integer numofDays) {
      try {
         Integer numofReturns = getShortestNumofReturnsData();
         numofDays = (numofReturns < numofDays) ? numofReturns : numofDays;
         Integer numofIndex =  getNoofIndex();
         double[][] data = new double[numofIndex][numofDays];
         String ticker;
         for (int i = 0; i < numofIndex; i++ ) {
            ticker = orderedList.get(i);
            for (int j= 0; j < numofDays; j++) {
               data[i][j] = returnMap.get(ticker).get(j).getValue();
            }
         }
         return data;
      }
      catch (Exception ex) {

      }
      return null;
   }
}
