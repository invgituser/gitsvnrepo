package com.invmodel.dao;


import java.sql.Types;
import java.util.*;
import java.util.logging.Logger;
import javax.sql.DataSource;

import com.invmodel.rebalance.data.*;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;

public class InvModelSP extends StoredProcedure
{
   private final Logger logger = Logger.getLogger(InvModelSP.class.getName());

   public InvModelSP(DataSource datasource, String storedProcName, int process, int which)
   {
      super(datasource, storedProcName);
      switch (process) {
         case 0: // PortfolioOptimizer
            switch (which) {
               case 1:
                  declareParameter(new SqlParameter("p_theme", Types.VARCHAR));
                  break;
               case 2:
                  declareParameter(new SqlParameter("p_theme", Types.VARCHAR));
                  break;
               default:
                  break;
            }
            break;
         case 1: // Rebalance/TaxLossHarvesting
            switch (which) {
               case 0:
                  declareParameter(new SqlParameter("p_logonid", Types.BIGINT));
                  declareParameter(new SqlParameter("p_acctnum", Types.BIGINT));
                  break;
               case 1:
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
                  declareParameter(new SqlParameter("p_processed", Types.VARCHAR));
                  declareParameter(new SqlParameter("p_tradeDate", Types.VARCHAR));
                  declareParameter(new SqlParameter("p_tradeCurrency", Types.VARCHAR));
                  declareParameter(new SqlParameter("p_assetclass", Types.VARCHAR));
                  declareParameter(new SqlParameter("p_subclass", Types.VARCHAR));
                  declareParameter(new SqlParameter("p_color", Types.VARCHAR));
                  declareParameter(new SqlParameter("p_holdingTicker", Types.VARCHAR));
                  declareParameter(new SqlParameter("p_curQty", Types.INTEGER));
                  declareParameter(new SqlParameter("p_curPrice", Types.DOUBLE));
                  declareParameter(new SqlParameter("p_curValue", Types.DOUBLE));
                  declareParameter(new SqlParameter("p_newTicker", Types.VARCHAR));
                  declareParameter(new SqlParameter("p_newQty", Types.INTEGER));
                  declareParameter(new SqlParameter("p_newPrice", Types.INTEGER));
                  declareParameter(new SqlParameter("p_newValue", Types.DOUBLE));
                  declareParameter(new SqlParameter("p_settleCurrency", Types.VARCHAR));
                  declareParameter(new SqlParameter("p_setleCurQty", Types.DOUBLE));
                  declareParameter(new SqlParameter("p_settleCurPrice", Types.DOUBLE));
                  declareParameter(new SqlParameter("p_settleCurValue", Types.DOUBLE));
                  declareParameter(new SqlParameter("p_exchangeRate", Types.DOUBLE));
                  declareParameter(new SqlParameter("p_setleNewQty", Types.DOUBLE));
                  declareParameter(new SqlParameter("p_settleNewPrice", Types.DOUBLE));
                  declareParameter(new SqlParameter("p_settleNewValue", Types.DOUBLE));
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
         case 5: // Projection Module
            break;
         case 6: // report_historical_data
            declareParameter(new SqlParameter("p_theme", Types.VARCHAR));
            break;
         case 7: // Security Master (Collecting Security Data
            declareParameter(new SqlParameter("p_advisor", Types.VARCHAR));
            declareParameter(new SqlParameter("p_theme", Types.VARCHAR));
            break;
         default: // All others with no args.
      }
      compile();
   }

   @SuppressWarnings({"unchecked", "rawtypes"})
   public Map loadDBData(Long p_logonid, Long p_acctnum)
   {

      Map inputMap = new HashMap();
      inputMap.put("p_logonid", p_logonid);
      inputMap.put("p_acctnum", p_acctnum);
      return super.execute(inputMap);
   }

   @SuppressWarnings({"unchecked", "rawtypes"})
   public Map collectTradeCustomerProfile(Long p_logonid, Long acctnum)
   {

      Map inputMap = new HashMap();
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
   public Map saveTradeData(UserTradePreprocess tData)
   {
      Map inputMap = new HashMap();
      inputMap.put("p_advisor", tData.getAdvisor());
      inputMap.put("p_clientAccountID", tData.getClientAccountID());
      inputMap.put("p_acctnum", tData.getAcctnum());
      inputMap.put("p_processed", "N");
      inputMap.put("p_tradeDate", null);
      inputMap.put("p_tradeCurrency", tData.getTradeCurrency());
      inputMap.put("p_assetclass", tData.getAssetclass());
      inputMap.put("p_subclass", tData.getSubclass());
      inputMap.put("p_color", tData.getColor());
      inputMap.put("p_holdingTicker", tData.getHoldingTicker());
      inputMap.put("p_curQty", tData.getCurQty());
      inputMap.put("p_curPrice", tData.getCurPrice());
      inputMap.put("p_curValue", tData.getCurValue());
      inputMap.put("p_newTicker", tData.getNewTicker());
      inputMap.put("p_newQty", tData.getNewQty());
      inputMap.put("p_newPrice", tData.getNewValue());
      inputMap.put("p_newValue", tData.getNewValue());
      inputMap.put("p_settleCurrency", tData.getSettleCurrency());
      inputMap.put("p_setleCurQty", tData.getSettleCurQty());
      inputMap.put("p_settleCurPrice", tData.getSettleCurPrice());
      inputMap.put("p_settleCurValue", tData.getSettleCurValue());
      inputMap.put("p_exchangeRate", tData.getExchangeRate());
      inputMap.put("p_setleNewQty", tData.getSettleNewQty());
      inputMap.put("p_settleNewPrice", tData.getSettleNewPrice());
      inputMap.put("p_settleNewValue", tData.getSettleNewValue());
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

   @SuppressWarnings({"unchecked", "rawtypes"})
   public Map reportloadMonthlyHistoricalData(String p_theme)
   {
      Map inputMap = new HashMap();
      inputMap.put("p_theme", p_theme);
      return super.execute(inputMap);
   }

   @SuppressWarnings({"unchecked", "rawtypes"})
   public Map collectSecurityData(String p_advisor, String p_theme)
   {
      Map inputMap = new HashMap();
      inputMap.put("p_advisor", p_advisor);
      inputMap.put("p_theme", p_theme);
      return super.execute(inputMap);
   }

   @SuppressWarnings({"unchecked", "rawtypes"})
   public Map assetDataFromDB(String p_theme)
   {
      Map inputMap = new HashMap();
      inputMap.put("p_theme", p_theme);
      return super.execute(inputMap);
   }

   @SuppressWarnings({"unchecked", "rawtypes"})
   public Map loadPrimeAssetsFromDB(String p_theme)
   {
      Map inputMap = new HashMap();
      inputMap.put("p_theme", p_theme);
      return super.execute(inputMap);
   }

}
