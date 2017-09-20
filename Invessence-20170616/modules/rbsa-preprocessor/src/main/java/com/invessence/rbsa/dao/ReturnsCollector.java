package com.invessence.rbsa.dao;

import java.io.Serializable;
import java.sql.*;
import java.util.*;
import java.util.logging.Logger;

import com.invessence.rbsa.dao.data.ReturnInfo;
import org.apache.commons.dbutils.DbUtils;

public class ReturnsCollector implements Serializable{
   private final Logger logger = Logger.getLogger(ReturnsCollector.class.getName());
   private Map<String, ReturnInfo> returnsDataMap = new HashMap<String, ReturnInfo>();
   private RBSADAO rbsaDAO = RBSADAO.getInstance();


   public ReturnsCollector() {
        super();
    }

   public ReturnsCollector(String ticker, String primeasset) {
      super();
      loadDatafromDB(ticker, primeasset);
   }


   public void loadDatafromDB(String ticker, String primeasset)
    {
      logger.info("Loading FUNDS information from DB");
      try
      {
         returnsDataMap = rbsaDAO.loadRBSAData(ticker, primeasset);
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
      finally
      {
      }
    }

   public double[] getFundReturnsData(String ticker, Integer numOfDays) {
      double [] fundArray = null;

      try {
         Integer actualdays;
         if (returnsDataMap.containsKey(ticker)) {
            if (getNoofFundsData(ticker) < numOfDays)
               actualdays = getNoofFundsData(ticker);
            else
               actualdays = numOfDays;

            fundArray = new double[actualdays];

            for (int i = 0; i < actualdays; i++) {
               fundArray[i] = returnsDataMap.get(ticker).getReturnData(ticker).get(i).getValue();
            }
            return fundArray;
         }
      }
      catch (Exception ex) {
         System.out.println("Error fetching funds Returns");
      }
      return fundArray;
   }

   public Integer getNoofIndex(String primeasset) {
      if (returnsDataMap.containsKey(primeasset))
         return (returnsDataMap.get(primeasset).getOrderedList().size());
      else
         return 0;
   }

   public Integer getNoofFundsData(String ticker) {
      if (returnsDataMap.containsKey(ticker))
         return (returnsDataMap.get(ticker).getNoofReturns(ticker));
      else
         return 0;
   }

   public Integer getNoofIndexData(String primeasset) {
      if (returnsDataMap.containsKey(primeasset))
         return (returnsDataMap.get(primeasset).getShortestNumofReturnsData());
      else
         return 0;
   }

   public double[][] getAllIndexReturnsArray(String primeasset, Integer numOfDays) {
      double [][] indexArray = null;

      try {
         if (returnsDataMap.containsKey(primeasset)) {
            indexArray =  returnsDataMap.get(primeasset).getAllReturnsData(numOfDays);
         }
      }
      catch (Exception ex) {
         System.out.println("Error fetching funds Returns");
      }
      return indexArray;
   }

   public ArrayList<String> getOrderedIndex(String primeasset) {
      if (returnsDataMap.containsKey(primeasset)) {
         return returnsDataMap.get(primeasset).getOrderedList();
      }
      return null;

   }

}
