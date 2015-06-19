package com.invmodel.position;

import java.util.Map;

import com.invmodel.dao.invdb.InvModelDAO;
import com.invmodel.position.data.PositionData;
import com.invmodel.rebalance.data.CurrentHolding;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 6/19/15
 * Time: 4:08 PM
 * To change this template use File | Settings | File Templates.
 */
public class LinearOptimizer
{
   private Map<Long, PositionData> position;

   private static LinearOptimizer instance = null;
   private InvModelDAO invModelDAO = new InvModelDAO();

   private LinearOptimizer()
   {
      super();
   }

   public static synchronized LinearOptimizer getInstance()
   {
      if (instance == null)
      {
         instance = new LinearOptimizer();
      }

      return instance;
   }

   public Map<Long, PositionData> loadExternalPositions(Long familyacctnum) {
      Map<Long, PositionData> data = null;
      try
      {
         data = invModelDAO.loadAllExternalPositions(familyacctnum);
         return data;
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
      return (data);
   }

   public void process(Long familyacctnum) {
      position = loadExternalPositions(familyacctnum);

      for (Long datafamilyacctnum : position.keySet()) {
         int numofextacct = position.get(datafamilyacctnum).getAccountdetail().size();
         Double[] accountValue = position.get(datafamilyacctnum).getAccountValue();
         String [] tickerArray = position.get(datafamilyacctnum).getTickerArray();
         Double totalValue =  position.get(datafamilyacctnum).getTotalValue();
      }
   }


}
