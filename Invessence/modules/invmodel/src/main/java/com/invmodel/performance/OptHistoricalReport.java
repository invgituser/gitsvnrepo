package com.invmodel.performance;

import java.util.*;

import com.invessence.web.constant.Const;
import com.invmodel.dao.rbsa.HistoricalReportsReturns;
import com.invmodel.model.fixedmodel.FixedModelOptimizer;
import com.invmodel.model.fixedmodel.data.FMData;
import com.invmodel.performance.data.*;
import com.invmodel.portfolio.data.Portfolio;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 6/17/16
 * Time: 5:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class OptHistoricalReport
{
   private HistoricalReportsReturns historicalReportsReturns;
   private final static String portfolioStr = "portfolio";

   public OptHistoricalReport()
   {
      historicalReportsReturns = new HistoricalReportsReturns();
   }

   public HistoricalReportsReturns getHistoricalReportsReturns()
   {
      return historicalReportsReturns;
   }

   public Map<String, ArrayList<Double>> calcuatePerformance(String theme,
                                                             Portfolio[] portfolio) {
      try {
         if (theme == null || portfolio == null)
            return null;

         if (historicalReportsReturns == null)
            return null;

         Map <String, ArrayList<Double>> histPerformance = new HashMap<String, ArrayList<Double>>();
         historicalReportsReturns.fetchHistoricalReportsReturns(theme);
         Map <String, OptPerformanceMasterData> data = historicalReportsReturns.getMonthlyReturnsArrayforCharting(theme);
         if (data != null && data.size() > 0) {
            Double lastReturn = 0.0;
            Integer year = 0;
            for (OptPerformanceMasterData masterData : data.values()) {
               // If it is portfolio, then take the return and multiply by individual weight, and money.
               String name = "Total." + masterData.getName();
               String ticker = masterData.getTicker();
               Double thisTotalReturn = 0.0;
               Double thisTotalMoney = 0.0;
               String businessdate = "";
               Double weight = portfolio[0].getPortfolioMap().get(ticker).getWeight();
               Double money = portfolio[0].getPortfolioMap().get(ticker).getMoney();
               if (name.contains(portfolioStr)) {
                  for (OptPerformanceHistData histData : masterData.getOptPerformanceHistDataArrayList()) {
                     businessdate = histData.getBusinessdate();
                     Double wghtedReturn = histData.getReturns() * weight;
                     histData.setWeight(wghtedReturn);
                     Double newMoney = wghtedReturn * money;
                     histData.setMoney(newMoney);
                     thisTotalReturn += wghtedReturn;
                     thisTotalMoney += newMoney;
                  }
               }
               else { // Else, then take the return and multiply by money.
                  for (OptPerformanceHistData histData : masterData.getOptPerformanceHistDataArrayList()) {
                     businessdate = histData.getBusinessdate();
                     Double wghtedReturn = weight;
                     histData.setWeight(wghtedReturn);
                     Double newMoney = wghtedReturn * money;
                     histData.setMoney(newMoney);
                     thisTotalReturn += wghtedReturn;
                     thisTotalMoney += newMoney;
                  }
                  if (year == 0) {
                     masterData.initTotals();
                     masterData.addTotalHistDataArrayList(ticker, name, businessdate, 0.0, 0.0, portfolio[0].getActualInvestments());
                  }
                  else {
                     masterData.addTotalHistDataArrayList(ticker, name, businessdate, thisTotalReturn, 0.0, thisTotalMoney);
                  }
                  year++;
               }
            }
         }


         return histPerformance;
      }
      catch (Exception ex) {
         return null;
      }
   }
}
