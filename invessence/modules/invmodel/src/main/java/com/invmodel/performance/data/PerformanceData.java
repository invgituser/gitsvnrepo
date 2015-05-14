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
   double investmentCost;
   double investmentCapital;
   double investmentYield;
   double invevestmentEarnings;
   double upperBand1;
   double upperBand2;
   double lowerBand1;
   double lowerBand2;


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

   public double getInvevestmentEarnings()
   {
      return invevestmentEarnings;
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

   public void setInvevestmentEarnings(double invevestmentEarnings)
   {
      this.invevestmentEarnings = invevestmentEarnings;
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

    public double getInvestmentCost() {
        return investmentCost;
    }

    public void setInvestmentCost(double investmentCost) {
        this.investmentCost = investmentCost;
    }

    public double getInvestmentCapital() {
        return investmentCapital;
    }

    public void setInvestmentCapital(double investmentCapital) {
           this.investmentCapital = investmentCapital;
       }
}
