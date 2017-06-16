package com.invmodel.performance;

import java.util.*;

import com.invmodel.model.fixedmodel.FixedModelOptimizer;
import com.invmodel.model.fixedmodel.data.FMData;
import com.invmodel.performance.data.ProjectionData;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 6/17/16
 * Time: 5:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class FMProjectionReport
{
   private FixedModelOptimizer fixedModelOptimizer;
   private Map<String, Boolean> canCreatePortfolio;

   public FMProjectionReport()
   {
      canCreatePortfolio = new HashMap<String, Boolean>();
   }

   public void setFixedModelOptimizer(FixedModelOptimizer fixedModelOptimizer)
   {
      this.fixedModelOptimizer = fixedModelOptimizer;
      canDoProjection(fixedModelOptimizer);
   }

   public void canDoProjection(FixedModelOptimizer fixedModelOptimizer)
   {
      if (fixedModelOptimizer != null) {
         for (String theme: fixedModelOptimizer.getListOfThemes()) {
            Boolean cando = true;
            for (FMData data : fixedModelOptimizer.getThemes(theme)) {
               if (data.getExpectedreturn() == null || data.getExpectedrisk() == null)
                  cando = false;
               else  if (data.getExpectedreturn() == 0.0 || data.getExpectedrisk() == 0.0)
                  cando = false;
            }
            canCreatePortfolio.put(theme,cando);
         }
      }
   }

   public ArrayList<ArrayList<ProjectionData>> calcuatePerformance(String theme,
                                                           Integer age,
                                                           Integer horizon,
                                                           Double money) {
      try {
         if (fixedModelOptimizer == null)
            return null;

         if (theme == null)
            return null;

         if (fixedModelOptimizer.getThemes(theme) == null)
            return null;

         if (! canCreatePortfolio.containsKey(theme))
            return null;

         if (canCreatePortfolio.get(theme)) {
            Integer numOfPortfolio = fixedModelOptimizer.getThemes(theme).size();
            Integer numOfYears = (horizon <= 0) ? 1 : horizon;
            ArrayList<ArrayList<ProjectionData>> perfdata = new ArrayList<ArrayList<ProjectionData>>();
            Integer portfolio = 0;
            Double investmentReturn, investmentRisk, invested, recurring, totalCost;
            for (FMData data: fixedModelOptimizer.getThemes(theme)) {
               ArrayList array = new ArrayList<ProjectionData>();
               invested = money;
               recurring = 0.0;
               investmentReturn = data.getExpectedreturn();
               investmentRisk = data.getExpectedrisk();
               totalCost = 0.0;
               double portGrowth = invested;
               double investmentCapital = invested;
               double totalcapitalgain = invested;
               Double cost;
               double upper1 = invested;
               double upper2 = invested;
               double lower1 = invested;
               double lower2 = invested;
               String key = data.getTheme() + "." + data.getLevel();
               for (Integer year = 0; year < numOfYears; year++) {
                  // Safety, that we don't over-run
                  if (year > horizon)
                     break;

                  ProjectionData perf = new ProjectionData();
                  perf.setTotalCapitalWithGains(totalcapitalgain);
                  perf.setTotalCost(0.0);
                  perf.setInvestmentReturns(investmentReturn);
                  perf.setInvestmentRisk(investmentRisk);
                  perf.setUpperBand1(upper1);
                  perf.setUpperBand2(upper2);
                  perf.setLowerBand1(lower1);
                  perf.setLowerBand2(lower2);
                  perf.setInvestedCapital(investmentCapital);
                  perf.setRecurInvestments(recurring);
                  perf.setUpperBand1(upper1);
                  perf.setUpperBand2(upper2);
                  if (lower1 < 0.0)
                     lower1 = 0.0;
                  perf.setLowerBand1(lower1);
                  if (lower2 < 0.0)
                     lower2 = 0.0;
                  perf.setLowerBand2(lower2);

                  array.add(perf);
                  investmentCapital += recurring;
                  cost = (totalCost / investmentCapital) * investmentCapital;
                  totalcapitalgain = (investmentReturn * totalcapitalgain) + (totalcapitalgain - cost);
                  portGrowth = investmentReturn * investmentCapital + portGrowth - cost;
                  upper1 = ( investmentRisk * totalcapitalgain ) + totalcapitalgain;
                  upper2 = ( 2 * investmentRisk * totalcapitalgain ) + totalcapitalgain;
                  lower1 = ( -1 * investmentRisk * totalcapitalgain ) + totalcapitalgain;
                  lower2 = ( -2 * investmentRisk * totalcapitalgain) + totalcapitalgain;
               }
               perfdata.add(array);
               portfolio++;
            }
            return perfdata;
         }
      }
      catch (Exception ex) {
         return null;
      }
      return null;
   }
}
