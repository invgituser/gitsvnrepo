package com.invessence.web.dao.admin;


import java.sql.Types;
import java.util.*;
import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;


@SuppressWarnings({"unchecked", "DuplicateStringLiteralInspection"})
public class AdminSP extends StoredProcedure
{

   public AdminSP(DataSource datasource, String sp_name, Integer mode)
   {
      super(datasource, sp_name);
      switch (mode) {
         case 0: //admin_updateProfile
            declareParameter(new SqlParameter("p_acctnum", Types.VARCHAR));
            declareParameter(new SqlParameter("p_riskIndex", Types.INTEGER));
            declareParameter(new SqlParameter("p_investment", Types.INTEGER));
            declareParameter(new SqlParameter("p_keepliquid", Types.INTEGER));
            declareParameter(new SqlParameter("p_tradepreference", Types.VARCHAR));
            break;
         case 1: // admin_sp_IB_Accounts_addmoddel
            declareParameter(new SqlParameter("p_addmod", Types.VARCHAR));
            declareParameter(new SqlParameter("p_acctnum", Types.BIGINT));
            declareParameter(new SqlParameter("p_status", Types.VARCHAR));
            declareParameter(new SqlParameter("p_ibacctnum", Types.VARCHAR));
            break;
         case 2: // sp_invessence_switch_post
            declareParameter(new SqlParameter("p_name", Types.VARCHAR));
            declareParameter(new SqlParameter("p_value", Types.VARCHAR));
            break;
         case 3:  // del_asset_alloc
            declareParameter(new SqlParameter("p_acctnum", Types.BIGINT));
            declareParameter(new SqlParameter("p_allocationmodel", Types.VARCHAR));
            declareParameter(new SqlParameter("p_assetyear", Types.INTEGER));
            break;
         case 4: // sp_asset_alloc_add_mod
            declareParameter(new SqlParameter("p_addmodflag", Types.VARCHAR));
            declareParameter(new SqlParameter("p_acctnum", Types.BIGINT));
            declareParameter(new SqlParameter("p_assetclass", Types.VARCHAR));
            declareParameter(new SqlParameter("p_themecode", Types.VARCHAR));
            declareParameter(new SqlParameter("p_allocationmodel", Types.VARCHAR));
            declareParameter(new SqlParameter("p_assetyear", Types.INTEGER));
            declareParameter(new SqlParameter("p_active", Types.VARCHAR));
            declareParameter(new SqlParameter("p_weight", Types.FLOAT));
            break;
         case 5: // del_virtual_portfolio
            declareParameter(new SqlParameter("p_acctnum", Types.BIGINT));
            break;
         case 6: // sp_virtual_portfolio_add_mod
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
         case 7: // sp_createTrades
            declareParameter(new SqlParameter("p_acctnum", Types.VARCHAR));
            break;
         case 99:  // sel_displayTrades2Execute
            break;



         case 100: //admin_sel_collectIBData
            declareParameter(new SqlParameter("p_filter", Types.VARCHAR));
            break;
         case 101: //admin_sel_collectInvData
            declareParameter(new SqlParameter("p_filter", Types.VARCHAR));
            break;
         case 102:  // admin_sel_virtual_portfolio
            declareParameter(new SqlParameter("p_acctnum", Types.VARCHAR));
            break;
         case 103: // sel_collectTradeProfile
            declareParameter(new SqlParameter("p_filter", Types.VARCHAR));
            break;
         case 104: // sel_displayTradeDetail
            declareParameter(new SqlParameter("p_acctnum", Types.VARCHAR));
            break;
         case 199: // All others
         default:  // All other (no arg)
            break;
      }
      compile();
   }

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

   public Map loadProfile(String filter)
   {
      Map inputMap = new HashMap();
      inputMap.put("p_filter", filter);
      return super.execute(inputMap);
   }

   public Map loadTradesDetails(Long acctnum)
   {
      Map inputMap = new HashMap();
      inputMap.put("p_acctnum", acctnum);
      return super.execute(inputMap);
   }

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
