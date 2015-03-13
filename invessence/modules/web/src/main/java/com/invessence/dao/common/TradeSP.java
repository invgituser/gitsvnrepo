package com.invessence.dao.common;


import java.sql.Types;
import java.util.*;
import javax.sql.DataSource;

import com.invessence.data.ManageGoals;
import com.invmodel.portfolio.data.*;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;


public class TradeSP extends StoredProcedure
{

   public TradeSP(DataSource datasource, String sp_name, Integer mode)
   {
      super(datasource, sp_name);
      switch (mode) {
         case 0:  // del_asset_alloc
            declareParameter(new SqlParameter("p_acctnum", Types.BIGINT));
            declareParameter(new SqlParameter("p_allocationmodel", Types.VARCHAR));
            declareParameter(new SqlParameter("p_assetyear", Types.INTEGER));
            break;
         case 1: // sp_asset_alloc_add_mod
            declareParameter(new SqlParameter("p_addmodflag", Types.VARCHAR));
            declareParameter(new SqlParameter("p_acctnum", Types.BIGINT));
            declareParameter(new SqlParameter("p_assetclass", Types.VARCHAR));
            declareParameter(new SqlParameter("p_themecode", Types.VARCHAR));
            declareParameter(new SqlParameter("p_allocationmodel", Types.VARCHAR));
            declareParameter(new SqlParameter("p_assetyear", Types.INTEGER));
            declareParameter(new SqlParameter("p_active", Types.VARCHAR));
            declareParameter(new SqlParameter("p_weight", Types.FLOAT));
            break;
         case 2: // del_virtual_portfolio
            declareParameter(new SqlParameter("p_acctnum", Types.BIGINT));
            break;
         case 3: // sp_virtual_portfolio_add_mod
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
         case 4: // sp_createTrades
            declareParameter(new SqlParameter("p_acctnum", Types.VARCHAR));
            break;
         case 99:  // sel_displayTrades2Execute
            break;



         case 101:  // admin_sel_virtual_portfolio
            declareParameter(new SqlParameter("p_acctnum", Types.VARCHAR));
            break;
         case 102: // sel_displayTradeDetail
            declareParameter(new SqlParameter("p_acctnum", Types.VARCHAR));
            break;
         case 103: // sel_collectTradeProfile
            declareParameter(new SqlParameter("p_filter", Types.VARCHAR));
            break;
         case 199: // All others
         default:  // All other (no arg)
            break;
      }
      compile();
   }

   @SuppressWarnings({"unchecked", "rawtypes"})
   public Map getVirtualPosition(Long acctnum) {
      Map inputMap = new HashMap();

      inputMap.put("p_acctnum", acctnum);
      return super.execute(inputMap);

   }

   @SuppressWarnings({"unchecked", "rawtypes"})
   public void updateNextRebalDate(String name, String nextRebalDate)
   {

      Map inputMap = new HashMap();

      inputMap.put("p_name", name);
      inputMap.put("p_value", nextRebalDate);

      super.execute(inputMap);
   }


   @SuppressWarnings({"unchecked", "rawtypes"})
   public void deleteAllocation(ManageGoals data)
   {
      Map inputAssetMap = new HashMap();
      inputAssetMap.put("p_acctnum", data.getAcctnum());
      inputAssetMap.put("p_allocationmodel", "D");
      inputAssetMap.put("p_assetyear", data.getCalendarYear());
      super.execute(inputAssetMap);
   }

   @SuppressWarnings({"unchecked", "rawtypes"})
   public void saveAllocation(ManageGoals data)
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
         inputAssetMap.put("p_weight", data.getAssetData()[0].getAsset(assetname).getUserweight());
         super.execute(inputAssetMap);
      }
   }

   @SuppressWarnings({"unchecked", "rawtypes"})
   public void deletePortfolio(ManageGoals data)
   {
      Map inputAssetMap = new HashMap();
      inputAssetMap.put("p_acctnum", data.getAcctnum());
      super.execute(inputAssetMap);
   }

   @SuppressWarnings({"unchecked", "rawtypes"})
   public void savePortfolio(ManageGoals data)
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

   @SuppressWarnings({"unchecked", "rawtypes"})
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

   @SuppressWarnings({"unchecked", "rawtypes"})
   public void updateExecutedTrades()
   {
      Map inputMap = new HashMap();
      super.execute(inputMap);
   }

}
