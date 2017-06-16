package com.invessence.web.temp;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 6/14/16
 * Time: 11:35 AM
 * To change this template use File | Settings | File Templates.
 */
public class MainTest
{

   public MainTest(){

      printMethod(new InvessenceRiskCalulator());
      printMethod(new TcmRiskCalulator());
      printMethod(new SymbilRiskCalulator());
   }
   public static void main(String args[]){
        new MainTest();
   }

   public void printMethod(RiskCalculator rc){

      System.out.println(rc.getRisk());
   }
}
