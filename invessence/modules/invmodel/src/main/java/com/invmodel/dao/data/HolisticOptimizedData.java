package com.invmodel.dao.data;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 6/8/15
 * Time: 11:17 PM
 * To change this template use File | Settings | File Templates.
 */
public class HolisticOptimizedData
{
   String advisor;
   String theme;
   ArrayList<String> primetickers = new ArrayList<String>();
   ArrayList<Double> primeweights = new ArrayList<Double>();
   String[] rbsatickers;
   double[] optimizedWeights;
   double[] risk;
   double[] portReturns;

   public String getAdvisor()
   {
      return advisor;
   }

   public void setAdvisor(String advisor)
   {
      this.advisor = advisor;
   }

   public String getTheme()
   {
      return theme;
   }

   public void setTheme(String theme)
   {
      this.theme = theme;
   }

   public ArrayList<String> getPrimetickers()
   {
      return primetickers;
   }

   public void setPrimetickers(ArrayList<String> primetickers)
   {
      this.primetickers = primetickers;
   }

   public ArrayList<Double> getPrimeweights()
   {
      return primeweights;
   }

   public void setPrimeweights(ArrayList<Double> primeweights)
   {
      this.primeweights = primeweights;
   }

   public String[] getRbsatickers()
   {
      return rbsatickers;
   }

   public void setRbsatickers(String[] rbsatickers)
   {
      this.rbsatickers = rbsatickers;
   }

   public double[] getOptimizedWeights()
   {
      return optimizedWeights;
   }

   public void setOptimizedWeights(double[] optimizedWeights)
   {
      this.optimizedWeights = optimizedWeights;
   }

   public double[] getRisk()
   {
      return risk;
   }

   public void setRisk(double[] risk)
   {
      this.risk = risk;
   }

   public double[] getPortReturns()
   {
      return portReturns;
   }

   public void setPortReturns(double[] portReturns)
   {
      this.portReturns = portReturns;
   }
}
