package com.invessence.web.dao.advisor;


import java.sql.Types;
import java.util.*;
import javax.sql.DataSource;

import com.invessence.web.data.advisor.*;
import com.invmodel.asset.data.Asset;
import com.invmodel.portfolio.data.*;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.object.StoredProcedure;


public class AdvisorSaveSP extends StoredProcedure
{

   public AdvisorSaveSP(DataSource datasource, String sp_name, Integer mode)
   {
      super(datasource, sp_name);
      switch (mode) {
         case 0: // Save Profile
            declareParameter(new SqlParameter("p_logonid", Types.BIGINT));
            declareParameter(new SqlInOutParameter("p_acctnum", Types.BIGINT));
            declareParameter(new SqlParameter("p_portfolioName", Types.VARCHAR));
            declareParameter(new SqlParameter("p_advisor", Types.VARCHAR));
            declareParameter(new SqlParameter("p_theme", Types.VARCHAR));
            declareParameter(new SqlParameter("p_email", Types.VARCHAR));
            declareParameter(new SqlParameter("p_firstname", Types.VARCHAR));
            declareParameter(new SqlParameter("p_lastname", Types.VARCHAR));
            declareParameter(new SqlParameter("p_goal", Types.VARCHAR));
            declareParameter(new SqlParameter("p_acctType", Types.VARCHAR));
            declareParameter(new SqlParameter("p_age", Types.INTEGER));
            declareParameter(new SqlParameter("p_horizon", Types.INTEGER));
            declareParameter(new SqlParameter("p_initialInvestment", Types.INTEGER));
            declareParameter(new SqlParameter("p_recurringInvestment", Types.INTEGER));
            declareParameter(new SqlParameter("p_riskIndex", Types.INTEGER));
            break;
         case 1:  // Del Allocation
            declareParameter(new SqlParameter("p_acctnum", Types.BIGINT));
            declareParameter(new SqlParameter("p_allocationmodel", Types.VARCHAR));
            declareParameter(new SqlParameter("p_assetyear", Types.VARCHAR));
            break;
         case 2: // Save Allocation
            declareParameter(new SqlParameter("p_addmodflag", Types.VARCHAR));
            declareParameter(new SqlParameter("p_acctnum", Types.BIGINT));
            declareParameter(new SqlParameter("p_assetclass", Types.VARCHAR));
            declareParameter(new SqlParameter("p_themecode", Types.VARCHAR));
            declareParameter(new SqlParameter("p_allocationmodel", Types.VARCHAR));
            declareParameter(new SqlParameter("p_assetyear", Types.INTEGER));
            declareParameter(new SqlParameter("p_active", Types.VARCHAR));
            declareParameter(new SqlParameter("p_weight", Types.FLOAT));
            break;
         case 3:  // Del Portfolio
            declareParameter(new SqlParameter("p_acctnum", Types.BIGINT));
            break;
         case 4: // Create Portfolio
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
         case 5:  // Del User_Subassetclass
            declareParameter(new SqlParameter("p_acctnum", Types.BIGINT));
            break;
         case 6:  // Save User_Subassetclass
            declareParameter(new SqlParameter("p_acctnum", Types.BIGINT));
            declareParameter(new SqlParameter("p_assetclass", Types.VARCHAR));
            declareParameter(new SqlParameter("p_subclass", Types.VARCHAR));
            break;
         case 7:  // Delete User Account
            declareParameter(new SqlParameter("p_acctnum", Types.BIGINT));
            break;
         case 8:  // saveAssetData sp_save_sec_assetclass_group
            declareParameter(new SqlParameter("p_theme", Types.VARCHAR));
            declareParameter(new SqlParameter("p_status", Types.VARCHAR));
            declareParameter(new SqlParameter("p_assetclass", Types.VARCHAR));
            declareParameter(new SqlParameter("p_displayName", Types.VARCHAR));
            declareParameter(new SqlParameter("p_ticker", Types.VARCHAR));
            declareParameter(new SqlParameter("p_sortorder", Types.INTEGER));
            declareParameter(new SqlParameter("p_lower_bound", Types.FLOAT));
            declareParameter(new SqlParameter("p_upper_bound", Types.FLOAT));
            declareParameter(new SqlParameter("p_color", Types.VARCHAR));
            declareParameter(new SqlParameter("p_averageReturn", Types.FLOAT));
            declareParameter(new SqlParameter("p_riskAdjustment", Types.FLOAT));
            declareParameter(new SqlParameter("p_endAllocation", Types.FLOAT));
            break;
         case 9:  // savePrimeAssetData sp_save_sec_assetclass_group
            declareParameter(new SqlParameter("p_theme", Types.VARCHAR));
            declareParameter(new SqlParameter("p_assetclass", Types.VARCHAR));
            declareParameter(new SqlParameter("p_primeassetclass", Types.VARCHAR));
            declareParameter(new SqlParameter("p_ticker", Types.VARCHAR));
            declareParameter(new SqlParameter("p_status", Types.VARCHAR));
            declareParameter(new SqlParameter("p_sortorder", Types.INTEGER));
            declareParameter(new SqlParameter("p_color", Types.VARCHAR));
            declareParameter(new SqlParameter("p_lower_bound", Types.FLOAT));
            declareParameter(new SqlParameter("p_upper_bound", Types.FLOAT));
            declareParameter(new SqlParameter("p_expectedReturn", Types.FLOAT));
            break;
         default:
      }
      compile();
   }

   @SuppressWarnings({"unchecked", "rawtypes"})
   public Map saveProfile(AdvisorData data)
   {
         Map inputMap = new HashMap();

         try {
            if (data != null) {
               inputMap.put("p_logonid", data.getLogonid());
               if (data.getAcctnum() == null)
                  inputMap.put("p_acctnum", -1);
               else
                  inputMap.put("p_acctnum", data.getAcctnum());
               inputMap.put("p_portfolioName", data.getPortfolioName());
               inputMap.put("p_advisor", data.getAdvisor());
               inputMap.put("p_theme", data.getTheme());
               inputMap.put("p_email", data.getClientEmail());
               inputMap.put("p_firstname", data.getClientFirstName());
               inputMap.put("p_lastname", data.getClientLastname());
               if (data.getGoal() == null)
                  inputMap.put("p_goal", "Other");
               else
                  inputMap.put("p_goal", data.getGoal());
               inputMap.put("p_acctType", data.getAccountType());
               inputMap.put("p_age", data.getAge());
               inputMap.put("p_horizon", data.getHorizon());
               inputMap.put("p_initialInvestment", data.getInitialInvestment());
               inputMap.put("p_recurringInvestment", data.getRecurringInvestment());
               inputMap.put("p_riskIndex", data.getRiskIndex());
               return super.execute(inputMap);
            }
         }
         catch (Exception ex) {
             ex.printStackTrace();
         }
      return null;
   }

   public void deleteAllocation(AdvisorData data)
   {
      Map inputMap = new HashMap();
      inputMap.put("p_acctnum", data.getAcctnum());
      inputMap.put("p_allocationmodel", 'D');
      inputMap.put("p_assetyear", data.getAssetyear());
      super.execute(inputMap);
   }

  @SuppressWarnings({"unchecked", "rawtypes"})
   public void saveAllocation(AdvisorData data)
   {
      if (data.getEditableAsset() != null) {
         int rowSize = data.getEditableAsset().size();
         Asset aac;

         for (int loop = 0; loop < rowSize; loop++)
         {
            Map inputAssetMap = new HashMap();
            aac =  data.getEditableAsset().get(loop);
            String assetname = aac.getAsset();
            inputAssetMap.put("p_addmodflag", "A");
            inputAssetMap.put("p_acctnum", data.getAcctnum());
            inputAssetMap.put("p_assetclass", assetname);
            inputAssetMap.put("p_themecode", data.getAdvisor());
            if (data.getUserAssetOverride())
               inputAssetMap.put("p_allocationmodel", "U");
            else
               inputAssetMap.put("p_allocationmodel", "D");

            inputAssetMap.put("p_assetyear", data.getAssetyear());
            inputAssetMap.put("p_active", "A");
            inputAssetMap.put("p_weight", aac.getDisplayActualWeight());
            super.execute(inputAssetMap);
         }
      }
   }

   public void deletePortfolio(AdvisorData data)
   {
      Map inputMap = new HashMap();
      inputMap.put("p_acctnum", data.getAcctnum());
      super.execute(inputMap);
   }

   @SuppressWarnings({"unchecked", "rawtypes"})
   public void savePortfolio(AdvisorData data)
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

   public void deleteExcludedSubclass(AdvisorData data)
   {
      Map inputMap = new HashMap();
      inputMap.put("p_acctnum", data.getAcctnum());
      super.execute(inputMap);
   }

   @SuppressWarnings({"unchecked", "rawtypes"})
   public void saveExcludedSubclass(AdvisorData data)
   {

      int rowSize = data.getExcludedSubAsset().size();
      for (int loop = 0; loop < rowSize; loop++)
      {
         Map inputMap = new HashMap();
         inputMap.put("p_acctnum", data.getAcctnum());
         inputMap.put("p_assetclass", data.getExcludedSubAsset().get(loop).getParentclass());
         inputMap.put("p_subclass", data.getExcludedSubAsset().get(loop).getSubasset());
         super.execute(inputMap);
      }
   }

   public void deleteAccount(Long acctnum)
   {
      Map inputMap = new HashMap();
      inputMap.put("p_acctnum", acctnum);
      super.execute(inputMap);
   }

   public void saveAssetData(AssetData data) {
      Map inputMap = new HashMap();
      inputMap.put("p_theme", data.getTheme());
      inputMap.put("p_status", data.getStatus());
      inputMap.put("p_assetclass", data.getAssetclass());
      inputMap.put("p_displayName", data.getDisplayName());
      inputMap.put("p_ticker", data.getIndexticker());
      inputMap.put("p_sortorder", data.getSortorder());
      inputMap.put("p_lower_bound", data.getLowerbound());
      inputMap.put("p_upper_bound", data.getUpperbound());
      inputMap.put("p_color", data.getColor());
      inputMap.put("p_averageReturn", 0.0);
      inputMap.put("p_riskAdjustment", data.getRiskAdjustment());
      inputMap.put("p_endAllocation", data.getEndAllocation());
      super.execute(inputMap);

   }

   public void savePrimeAssetData(PrimeAssetData data) {
      Map inputMap = new HashMap();
      inputMap.put("p_theme", data.getTheme());
      inputMap.put("p_status", data.getActive());
      inputMap.put("p_assetclass", data.getAssetclass());
      inputMap.put("p_primeassetclass", data.getPrimeassetclass());
      inputMap.put("p_ticker", data.getTicker());
      inputMap.put("p_sortorder", data.getSortorder());
      inputMap.put("p_color", "");
      inputMap.put("p_lower_bound", data.getLowerbound());
      inputMap.put("p_upper_bound", data.getUpperbound());
      inputMap.put("p_expectedReturn", data.getExpectedReturn());
      super.execute(inputMap);
   }

}
