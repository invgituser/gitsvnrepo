package com.invessence.dao;


import java.sql.Types;
import java.util.*;
import javax.sql.DataSource;

import com.invessence.data.admin.AdminTradeClient;
import com.invmodel.portfolio.data.*;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;


public class AdminSP extends StoredProcedure
{

   public AdminSP(DataSource datasource, String sp_name, Integer mode)
   {
      super(datasource, sp_name);
      switch (mode) {
         case 0:
            declareParameter(new SqlParameter("p_filter", Types.VARCHAR));
            break;
         case 1:
            declareParameter(new SqlParameter("p_acctnum", Types.VARCHAR));
            declareParameter(new SqlParameter("p_riskIndex", Types.INTEGER));
            declareParameter(new SqlParameter("p_investment", Types.INTEGER));
            declareParameter(new SqlParameter("p_keepliquid", Types.INTEGER));
            declareParameter(new SqlParameter("p_tradepreference", Types.VARCHAR));
            break;
         case 2:
            declareParameter(new SqlParameter("p_addmod", Types.VARCHAR));
            declareParameter(new SqlParameter("p_acctnum", Types.BIGINT));
            declareParameter(new SqlParameter("p_status", Types.VARCHAR));
            declareParameter(new SqlParameter("p_ibacctnum", Types.VARCHAR));
            break;
         case 3:
            declareParameter(new SqlParameter("p_acctnum", Types.VARCHAR));
            break;
         case 4:
            declareParameter(new SqlParameter("p_name", Types.VARCHAR));
            declareParameter(new SqlParameter("p_value", Types.VARCHAR));
            break;
         case 5:
            // No args...
            break;
         case 6: // loadProfileData
            declareParameter(new SqlParameter("p_filter", Types.VARCHAR));
            break;
         case 7:
            declareParameter(new SqlParameter("p_acctnum", Types.BIGINT));
            declareParameter(new SqlParameter("p_allocationmodel", Types.VARCHAR));
            declareParameter(new SqlParameter("p_assetyear", Types.INTEGER));
            break;
         case 8:
            declareParameter(new SqlParameter("p_addmodflag", Types.VARCHAR));
            declareParameter(new SqlParameter("p_acctnum", Types.BIGINT));
            declareParameter(new SqlParameter("p_assetclass", Types.VARCHAR));
            declareParameter(new SqlParameter("p_themecode", Types.VARCHAR));
            declareParameter(new SqlParameter("p_allocationmodel", Types.VARCHAR));
            declareParameter(new SqlParameter("p_assetyear", Types.INTEGER));
            declareParameter(new SqlParameter("p_active", Types.VARCHAR));
            declareParameter(new SqlParameter("p_weight", Types.FLOAT));
            break;
         case 9:
            declareParameter(new SqlParameter("p_acctnum", Types.BIGINT));
            break;
         case 10:
            declareParameter(new SqlParameter("p_addmodflag", Types.VARCHAR));
            declareParameter(new SqlParameter("p_acctnum", Types.BIGINT));
            declareParameter(new SqlParameter("p_itemnum", Types.INTEGER));
            declareParameter(new SqlParameter("p_ticker", Types.VARCHAR));
            declareParameter(new SqlParameter("p_active", Types.VARCHAR));
            declareParameter(new SqlParameter("p_qty", Types.INTEGER));
            declareParameter(new SqlParameter("p_weightByAsset", Types.FLOAT));
            declareParameter(new SqlParameter("p_tradeprice", Types.FLOAT));
            declareParameter(new SqlParameter("p_investmentvalue", Types.DOUBLE));
            break;
         case 11:
            declareParameter(new SqlParameter("p_acctnum", Types.VARCHAR));
            break;
         case 12:
            declareParameter(new SqlParameter("p_acctnum", Types.VARCHAR));
         default:
      }
      compile();
   }

   @SuppressWarnings({"unchecked", "rawtypes"})
   public Map getIBData(String filter)
   {

      Map inputMap = new HashMap();
      inputMap.put("p_filter", filter);
      return super.execute(inputMap);
   }

   public Map getInvData(String filter)
   {

      Map inputMap = new HashMap();
      inputMap.put("p_filter", filter);
      return super.execute(inputMap);
   }

   public Map addDelIBPair(String action, Long acctnum, String IBStatus, String ibacctnum)
   {

      Map inputMap = new HashMap();

      inputMap.put("p_addmod", action);
      inputMap.put("p_acctnum", acctnum);
      inputMap.put("p_status", IBStatus);
      inputMap.put("p_ibacctnum", ibacctnum);

      return super.execute(inputMap);
   }

   public Map updateProfile(Long acctnum, Integer riskIndex, Long investment, Long keepliquid, String tradepreference)
   {

      Map inputMap = new HashMap();

      inputMap.put("p_acctnum", acctnum);
      inputMap.put("p_riskIndex", riskIndex);
      inputMap.put("p_investment", investment);
      inputMap.put("p_keepliquid", keepliquid);
      inputMap.put("p_tradepreference", tradepreference);

      return super.execute(inputMap);
   }
   public Map getVirtualPosition(Long acctnum) {
      Map inputMap = new HashMap();

      inputMap.put("p_acctnum", acctnum);
      return super.execute(inputMap);

   }

   public void updateNextRebalDate(String name, String nextRebalDate)
   {

      Map inputMap = new HashMap();

      inputMap.put("p_name", name);
      inputMap.put("p_value", nextRebalDate);

      super.execute(inputMap);
   }


   public Map reloadClients4Trading()
   {

      Map inputMap = new HashMap();
      return super.execute(inputMap);
   }

   @SuppressWarnings({"unchecked", "rawtypes"})
   public Map loadProfile(String filter)
   {
      Map inputMap = new HashMap();
      inputMap.put("p_filter", filter);
      return super.execute(inputMap);
   }

   @SuppressWarnings({"unchecked", "rawtypes"})
   public void deleteAllocation(AdminTradeClient data)
   {
      Map inputAssetMap = new HashMap();
      inputAssetMap.put("p_acctnum", data.getAcctnum());
      inputAssetMap.put("p_allocationmodel", "D");
      inputAssetMap.put("p_assetyear", data.getCalendarYear());
      super.execute(inputAssetMap);
   }

   @SuppressWarnings({"unchecked", "rawtypes"})
   public void saveAllocation(AdminTradeClient data)
   {
      com.invmodel.asset.data.AssetClass aac[] = data.getAssetData();
      int rowSize = aac[0].getOrderedAsset().size();

      for (int loop = 0; loop < rowSize; loop++)
      {
         Map inputAssetMap = new HashMap();
         String assetname = aac[0].getOrderedAsset().get(loop);
         inputAssetMap.put("p_addmodflag", "A");
         inputAssetMap.put("p_acctnum", data.getAcctnum());
         inputAssetMap.put("p_assetclass", assetname);
         inputAssetMap.put("p_themecode", "ETF");
         inputAssetMap.put("p_allocationmodel", "R");
         inputAssetMap.put("p_assetyear", data.getCalendarYear());
         inputAssetMap.put("p_active", "A");
         inputAssetMap.put("p_weight", data.getAssetData()[0].getAssetRoundedActualWeight(assetname));
         super.execute(inputAssetMap);
      }
   }

   @SuppressWarnings({"unchecked", "rawtypes"})
   public void deletePortfolio(AdminTradeClient data)
   {
      Map inputAssetMap = new HashMap();
      inputAssetMap.put("p_acctnum", data.getAcctnum());
      super.execute(inputAssetMap);
   }

   @SuppressWarnings({"unchecked", "rawtypes"})
   public void savePortfolio(AdminTradeClient data)
   {

      Portfolio pfclass[] = data.getPortfolioData();

      if (pfclass == null)
         return;

      int rowSize = pfclass[0].getPortfolio().size();
      for (int loop = 0; loop < rowSize; loop++)
      {
         PortfolioSecurityData pfList = pfclass[0].getPortfolio().get(loop);
         Map inputPortfolioMap = new HashMap();
         inputPortfolioMap.put("p_addmodflag", "A");
         inputPortfolioMap.put("p_acctnum", data.getAcctnum());
         inputPortfolioMap.put("p_itemnum", loop + 1);
         inputPortfolioMap.put("p_ticker", pfList.getTicker());
         inputPortfolioMap.put("p_active", "A");
         inputPortfolioMap.put("p_qty", pfList.getShares());
         inputPortfolioMap.put("p_weightByAsset", pfList.getWeight());
         inputPortfolioMap.put("p_tradeprice", pfList.getDailyprice());
         inputPortfolioMap.put("p_investmentvalue", pfList.getMoney());
         //inputPortfolioMap.put("p_weightByPortfolio", pfList.getTickerWeights());

         super.execute(inputPortfolioMap);
      }
   }

   public void createTrades(Long acctnum)
   {
      Map inputMap = new HashMap();

      inputMap.put("p_acctnum", acctnum);
      super.execute(inputMap);
   }

   @SuppressWarnings({"unchecked", "rawtypes"})
   public Map loadTradesDetails(Long acctnum)
   {
      Map inputMap = new HashMap();
      inputMap.put("p_acctnum", acctnum);
      return super.execute(inputMap);
   }

   @SuppressWarnings({"unchecked", "rawtypes"})
   public Map getTradesAllocationData()
   {
      Map inputMap = new HashMap();
      return super.execute(inputMap);
   }

   public void updateExecutedTrades()
   {
      Map inputMap = new HashMap();
      super.execute(inputMap);
   }

}
