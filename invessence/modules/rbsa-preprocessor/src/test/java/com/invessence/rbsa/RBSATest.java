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
      // rp.optimizeSecurity("FFKEX");
      //solution = rp.optimizeSecurity("FFXMX");
      //rbsaData = rp.optimizeSecurity("TWGTX");
      rbsaData = rp.optimizeSecurity("FFKEX");
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
      int temp =  0;
   }
}
