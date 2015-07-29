package com.invmodel.dao;


import java.sql.Types;
import java.util.*;
import javax.sql.DataSource;

import com.invmodel.rebalance.data.*;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;


public class InvModelSP extends StoredProcedure
{

   public InvModelSP(DataSource datasource, String storedProcName, int process, int which)
   {
      super(datasource, storedProcName);
      switch (process) {
         case 0: // PortfolioOptimizer
            break;
         case 1: // Rebalance/TaxLossHarvesting
            switch (which) {
               case 0:
                  declareParameter(new SqlParameter("p_acctnum", Types.BIGINT));
                  break;
               case 1:
                  declareParameter(new SqlParameter("p_logonid", Types.BIGINT));
                  declareParameter(new SqlParameter("p_acctnum", Types.BIGINT));

                  break;
               case 2:
                  declareParameter(new SqlParameter("p_acctnum", Types.BIGINT));
                  break;
               case 3:
                  declareParameter(new SqlParameter("p_acctnum", Types.BIGINT));
                  break;
               case 4:
                  declareParameter(new SqlParameter("p_acctnum", Types.BIGINT));
                  break;
               case 10:
                  declareParameter(new SqlParameter("p_advisor", Types.VARCHAR));
                  declareParameter(new SqlParameter("p_clientAccountID", Types.VARCHAR));
                  declareParameter(new SqlParameter("p_acctnum", Types.BIGINT));
                  declareParameter(new SqlParameter("p_ticker", Types.VARCHAR));
                  declareParameter(new SqlParameter("p_curQty", Types.INTEGER));
                  declareParameter(new SqlParameter("p_curPrice", Types.DOUBLE));
                  declareParameter(new SqlParameter("p_curValue", Types.DOUBLE));
                  declareParameter(new SqlParameter("p_holdingTicker", Types.VARCHAR));
                  declareParameter(new SqlParameter("p_holdingQty", Types.INTEGER));
                  declareParameter(new SqlParameter("p_holdingPrice", Types.DOUBLE));
                  declareParameter(new SqlParameter("p_holdingValue", Types.DOUBLE));
                  declareParameter(new SqlParameter("p_newQty", Types.INTEGER));
                  declareParameter(new SqlParameter("p_newValue", Types.DOUBLE));
                  declareParameter(new SqlParameter("p_tradeType", Types.VARCHAR));
                  declareParameter(new SqlParameter("p_reason", Types.VARCHAR));
                  break;
               case 11:
                  declareParameter(new SqlParameter("p_acctnum", Types.BIGINT));
                  break;
               case 12:
                  declareParameter(new SqlParameter("p_familyacctnum", Types.BIGINT));
                  break;
               default:
                  break;
            }
            break;
         case 2: // Daily Historical Data (FROM invdb)
            break;
         case 3: // Daily Historical Data (FROM rbsa)
             break;
         case 4: // Monthly Historical Data (FROM rbsa)
            break;
         case 5: // Performance Module
            break;
         default: // All others with no args.
      }
      compile();
   }

   @SuppressWarnings({"unchecked", "rawtypes"})
   public Map loadDBData(Long p_acctnum)
   {

      Map inputMap = new HashMap();
      inputMap.put("p_acctnum", p_acctnum);
      return super.execute(inputMap);
   }

   @SuppressWarnings({"unchecked", "rawtypes"})
   public Map collectProfileData(Long p_logonid, Long acctnum)
   {

      Map inputMap = new HashMap();
      inputMap.put("p_logonid", p_logonid);
      inputMap.put("p_acctnum", acctnum);

      return super.execute(inputMap);
   }

   @SuppressWarnings({"unchecked", "rawtypes"})
   public Map collectAllocation(Long acctnum)
   {
      Map inputMap = new HashMap();
      inputMap.put("p_acctnum", acctnum);
      return super.execute(inputMap);
   }

   @SuppressWarnings({"unchecked", "rawtypes"})
   public Map loadSecurityData()
   {
      return super.execute();
   }

   @SuppressWarnings({"unchecked", "rawtypes"})
   public Map collectSubClassExclusionList(Long acctnum)
   {
      Map inputMap = new HashMap();
      inputMap.put("p_acctnum", acctnum);
      return super.execute(inputMap);
   }

   @SuppressWarnings({"unchecked", "rawtypes"})
   public Map loadDBExecutedTrades(Long acctnum)
   {
      Map inputMap = new HashMap();
      inputMap.put("p_acctnum", acctnum);
      return super.execute(inputMap);
   }

   @SuppressWarnings({"unchecked", "rawtypes"})
   public Map saveTradeData(RebalanceTradeData tData)
   {
      Map inputMap = new HashMap();
      inputMap.put("p_advisor", tData.getAdvisor());
      inputMap.put("p_clientAccountID", tData.getClientAccountID());
      inputMap.put("p_acctnum", tData.getAcctnum());
      inputMap.put("p_ticker", tData.getTicker());
      inputMap.put("p_curQty", tData.getQty().intValue());
      inputMap.put("p_curPrice", tData.getCurPrice());
      inputMap.put("p_curValue", tData.getMoney());
      inputMap.put("p_holdingTicker", tData.getHoldingTicker());
      inputMap.put("p_holdingQty", tData.getHoldingQty());
      inputMap.put("p_holdingPrice", tData.getHoldingPrice());
      inputMap.put("p_holdingValue", tData.getHoldingValue());
      inputMap.put("p_newQty", tData.getNewQty());
      inputMap.put("p_newValue", tData.getNewValue());
      inputMap.put("p_tradeType", tData.getTradeType());
      inputMap.put("p_reason", tData.getReason());
      return super.execute(inputMap);
   }

   @SuppressWarnings({"unchecked", "rawtypes"})
   public Map deleteTradeData(Long acctnum)
   {
      Map inputMap = new HashMap();
      inputMap.put("p_acctnum", acctnum);
      return super.execute(inputMap);
   }

   @SuppressWarnings({"unchecked", "rawtypes"})
   public Map mhloadMonthlyHistoricalData()
   {
      return super.execute();
   }

   @SuppressWarnings({"unchecked", "rawtypes"})
   public Map dhloadDailyHistoricalData()
   {
      return super.execute();
   }

   @SuppressWarnings({"unchecked", "rawtypes"})
   public Map loadAllExternalPositions(Long familyacctnum)
   {
      Map inputMap = new HashMap();
      inputMap.put("p_familyacctnum", familyacctnum);
      return super.execute(inputMap);
   }

}
