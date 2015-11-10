package com.invmodel.performance.data;

/**
 * Created with IntelliJ IDEA.
 * User: Jigar
 * Date: 10/4/13
 * Time: 5:17 PM
 * To change this template use File | Settings | File Templates.
 */
public class PerformanceData {
   double investmentRisk;
   double investmentReturns;
   double totalCost;
   double totalCapitalWithGains;
   double investedCapital;
   double recurInvestments;
   double investmentYield;
   double totalGains;
   double upperBand1;
   double upperBand2;
   double lowerBand1;
   double lowerBand2;
   double goalsrequired;


   public PerformanceData() {
   }

   public double getInvestmentYield()
   {
      return investmentYield;
   }

   public void setInvestmentYield(double investmentYield)
   {
      this.investmentYield = investmentYield;
   }

   public double getInvestedCapital()
   {
      return investedCapital;
   }

   public void setInvestedCapital(double investedCapital)
   {
      this.investedCapital = investedCapital;
   }

   public double getRecurInvestments()
   {
      return recurInvestments;
   }

   public void setRecurInvestments(double recurInvestments)
   {
      this.recurInvestments = recurInvestments;
   }

   public double getTotalGains()
   {
      return totalGains;
   }

   public double getUpperBand1()
   {
      return upperBand1;
   }

   public void setUpperBand1(double upperBand1)
   {
      this.upperBand1 = upperBand1;
   }

   public double getUpperBand2()
   {
      return upperBand2;
   }

   public void setUpperBand2(double upperBand2)
   {
      this.upperBand2 = upperBand2;
   }

   public double getLowerBand1()
   {
      return lowerBand1;
   }

   public void setLowerBand1(double lowerBand1)
   {
      this.lowerBand1 = lowerBand1;
   }

   public double getLowerBand2()
   {
      return lowerBand2;
   }

   public void setLowerBand2(double lowerBand2)
   {
      this.lowerBand2 = lowerBand2;
   }

   public void setTotalGains(double totalGains)
   {
      this.totalGains = totalGains;
   }

   public double getInvestmentRisk() {
        return investmentRisk;
    }

    public void setInvestmentRisk(double investmentRisk) {
        this.investmentRisk = investmentRisk;
    }

    public double getInvestmentReturns() {
        return investmentReturns;
    }

    public void setInvestmentReturns(double investmentReturns) {
        this.investmentReturns = investmentReturns;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public double getTotalCapitalWithGains() {
        return totalCapitalWithGains;
    }

    public void setTotalCapitalWithGains(double totalCapitalWithGains) {
           this.totalCapitalWithGains = totalCapitalWithGains;
    }

   public double getGoalsrequired()
   {
      return goalsrequired;
   }

   public void setGoalsrequired(double goalsrequired)
   {
      this.goalsrequired = goalsrequired;
   }
}
