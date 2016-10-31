package com.invessence.rbsa;

import com.invessence.rbsa.dao.data.RBSAData;
import com.joptimizer.functions.*;
import com.joptimizer.optimizers.*;
import com.joptimizer.util.Utils;

/**
 * Unit test for simple InvModelConsolTesting.
 */

public class RBSATest
{
   Utils jOptimizerUtils = new Utils();
   Integer numofIndex, maxRowsofIndex;

   public static void main(String[] args) throws Exception
   {
      RBSAData rbsaData;
      RBSA2 rp = new RBSA2();
/*
      String [] tickAcct1 =  {"FFKEX", "OAKIX", "TWGTX"};
      String [] tickAcct2 =  {"FCNTX", "LEXCX", "MALOX"};
      String [] tickAcct3 =  {"LSHIX", "MEDAX", "NEZYX"};
      String [] tickers = concatStringArrays(tickAcct1, tickAcct2);
      tickers = concatStringArrays(tickers, tickAcct3);
*/
      String [] tickers =  {"FFKEX", "OAKIX", "LSHIX", "MALOX"};
      // rp.optimizeSecurity("FFKEX");
      // solution = rp.optimizeSecurity("FFXMX");
      // rbsaData = rp.optimizeSecurity("TWGTX");
      for(int i = 0; i< tickers.length; i++){
         rbsaData = rp.optimizeSecurity(tickers[i]);
         Double val = 0.0;
         Double totalAlloc = 0.0;
         if (rbsaData != null) {
            for (String key: rbsaData.getSolution().keySet()) {
               val = (Math.round(rbsaData.getSolution().get(key) * 10000.0) / 100.0);
               totalAlloc = totalAlloc + val;
               System.out.println("Index (" + key + "): " + rbsaData.getSolution().get(key) + "(" + val +"%)");
            }
            System.out.println("Total Allocated: " + totalAlloc);
            System.out.println("Tracking Error: " + (rbsaData.getTrackingError() * 100.00) + "%");
         }
      }
      int temp =  0;
   }

   public static String[] concatStringArrays(String[] string1, String[] string2){
      String[] resultString = new String[string1.length + string2.length];
      int j = 0;
      for (int i = 0; i< string1.length; i++)
      {
         resultString[j]= string1[i];
         j++;
      }
      for (int i = 0; i< string2.length; i++)
      {
         resultString[j] = string2[i];
         j++;
      }

      return resultString;
   }
}
