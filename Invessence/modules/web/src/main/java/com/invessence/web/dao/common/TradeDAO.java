package com.invessence.web.dao.common;

import java.io.Serializable;
import java.util.*;
import javax.faces.bean.*;
import javax.sql.DataSource;

import com.invessence.converter.SQLData;
import com.invessence.web.dao.admin.AdminSP;
import com.invessence.web.data.TradeDetails;
import com.invessence.web.data.common.*;
import com.invmodel.asset.data.Asset;
import com.invmodel.rebalance.data.*;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

@ManagedBean(name = "tradeDAO")
@SessionScoped
public class TradeDAO extends JdbcDaoSupport implements Serializable
{
   SQLData convert = new SQLData();

   public void saveAllocation(CustomerData data)
   {
      DataSource ds = getDataSource();
      TradeSP sp1 = new TradeSP(ds, "del_asset_alloc", 0);
      sp1.deleteAllocation(data);
      TradeSP sp = new TradeSP(ds, "sp_asset_alloc_add_mod", 1);
      sp.saveAllocation(data);
   }

   public void savePortfolio(CustomerData data)
   {
      DataSource ds = getDataSource();
      TradeSP sp1 = new TradeSP(ds, "del_virtual_portfolio", 2);
      sp1.deletePortfolio(data);
      TradeSP sp = new TradeSP(ds, "sp_virtual_portfolio_add_mod", 3);
      sp.savePortfolio(data);
   }

   public void createTrades(Long acctnum)
   {
      DataSource ds = getDataSource();
      TradeSP sp = new TradeSP(ds, "sp_createTrades", 4);
      sp.createTrades(acctnum);
   }

   public void saveNextRebalDate(String nextRebaldate)
   {
      try
      {
         DataSource ds = getDataSource();
         AdminSP sp = new AdminSP(ds, "sp_invessence_switch_post", 5);
         sp.updateNextRebalDate("NEXT_REBALANCE", nextRebaldate);
      }
      catch (Exception ex)
      {
         ex.printStackTrace();
      }
   }

   public void saveTradeProcessIdentifier(Long acctnum, String tradeStatus, String processStatus, String reason)
   {
      DataSource ds = getDataSource();
      TradeSP sp = new TradeSP(ds, "save_trade_process_identifier", 6);
      sp.saveTradeProcessIdentifier(acctnum, tradeStatus, processStatus, reason);
   }

   public void deletePendingTrades()
   {
      try
      {
         DataSource ds = getDataSource();
         TradeSP sp = new TradeSP(ds, "delete_pending_trades", 99);
         sp.delete_pending_trades();
      }
      catch (Exception ex)
      {
         ex.printStackTrace();
      }
   }


   public void updateExecutedTrades()
   {
      DataSource ds = getDataSource();
      TradeSP sp = new TradeSP(ds, "sp_updateExecutedTrades", 99);
      sp.updateExecutedTrades();
   }

   public List<Position> loadVirtualPorfolio(Long acctnum)
   {
      try
      {
         DataSource ds = getDataSource();
         TradeSP sp = new TradeSP(ds, "admin_sel_virtual_portfolio", 101);
         List<Position> posList = new ArrayList<Position>();

         Map outMap = sp.getVirtualPosition(acctnum);
         if (outMap != null)
         {
            ArrayList<Map<String, Object>> rows = (ArrayList<Map<String, Object>>) outMap.get("#result-set-1");
            int i = 0;
            for (Map<String, Object> map : rows)
            {
               Map rs = (Map) rows.get(i);
               Position data = new Position();

               data.setAcctnum(convert.getLongData(rs.get("acctnum")));
               data.setInstrumentid(convert.getLongData(rs.get("instrumentid")));
               data.setTicker(convert.getStrData(rs.get("ticker")));
               data.setAssetclass(convert.getStrData(rs.get("assetclass")));
               data.setSubclass(convert.getStrData(rs.get("subclass")));
               data.setColor(convert.getStrData(rs.get("color")));
               data.setName(convert.getStrData(rs.get("name")));
               data.setQty(convert.getIntData(rs.get("qty")));
               data.setWeight(convert.getDoubleData(rs.get("weight")));
               data.setCostBasisMoney(convert.getDoubleData(rs.get("origPrice")));
               data.setCostBasisMoney(convert.getDoubleData(rs.get("origInvested")));
               data.setMarkPrice(convert.getDoubleData(rs.get("price")));
               data.setPositionValue(convert.getDoubleData(rs.get("newValue")));
               data.setFirstname(convert.getStrData(rs.get("firstname")));
               data.setLastname(convert.getStrData(rs.get("lastname")));
               data.setClientAccountID(convert.getStrData(rs.get("IB_acctnum")));
               // data.setClientAccountID(convert.getStrData(rs.get("IB_acctnum")));
               posList.add(i, data);
               i++;
            }
            return posList;
         }
      }
      catch (Exception ex)
      {
         ex.printStackTrace();
      }
      return null;
   }

   public List<UserTradePreprocess> loadRebalTrades(Long acctnum)
   {
      DataSource ds = getDataSource();
      TradeSP sp;

      sp = new TradeSP(ds, "sel_user_trade_preprocess", 102);
      Map outMap = sp.loadTradesDetails(acctnum);

      ArrayList<UserTradePreprocess> userTradePreprocess = new ArrayList<UserTradePreprocess>();
      int i = 0;
      if (outMap != null)
      {
         ArrayList<Map<String, Object>> rows = (ArrayList<Map<String, Object>>) outMap.get("#result-set-1");
         if (rows != null)
         {
            i = 0;
            for (Map<String, Object> map : rows)
            {
               Map rs = (Map) rows.get(i);
               UserTradePreprocess tradedata = new UserTradePreprocess(
                  convert.getStrData(rs.get("advisor")),
                  convert.getStrData(rs.get("clientAccountID")),
                  convert.getLongData(rs.get("acctnum")),
                  convert.getStrData(rs.get("processed")),
                  convert.getStrData(rs.get("tradeDate")),
                  convert.getStrData(rs.get("tradeCurrency")),
                  convert.getStrData(rs.get("assetclass")),
                  convert.getStrData(rs.get("subclass")),
                  convert.getStrData(rs.get("assetcolor")),
                  convert.getStrData(rs.get("holdingTicker")),
                  convert.getDoubleData(rs.get("curQty")),
                  convert.getDoubleData(rs.get("curPrice")),
                  convert.getDoubleData(rs.get("curValue")),
                  convert.getStrData(rs.get("newTicker")),
                  convert.getDoubleData(rs.get("newQty")),
                  convert.getDoubleData(rs.get("newPrice")),
                  convert.getDoubleData(rs.get("newValue")),
                  convert.getStrData(rs.get("settleCurrency")),
                  convert.getDoubleData(rs.get("setleCurQty")),
                  convert.getDoubleData(rs.get("settleCurPrice")),
                  convert.getDoubleData(rs.get("settleCurValue")),
                  convert.getDoubleData(rs.get("exchangeRate")),
                  convert.getDoubleData(rs.get("settleNewQty")),
                  convert.getDoubleData(rs.get("settleNewPrice")),
                  convert.getDoubleData(rs.get("settleNewValue")),
                  convert.getStrData(rs.get("tradeType")),
                  convert.getStrData(rs.get("reason"))
                  );

               userTradePreprocess.add(tradedata);
                i++;
            }
         }
      }

      return userTradePreprocess;
   }

   public List<TradeClientData> getTradeProfileData(Long logonid, String filter)
   {
      DataSource ds = getDataSource();
      TradeSP sp = new TradeSP(ds, "sel_collectTradeProfile", 103);
      List<TradeClientData> listProfiles = new ArrayList<TradeClientData>();
      Map outMap = sp.loadProfile(logonid, filter);
      if (outMap != null)
      {
         ArrayList<Map<String, Object>> rows = (ArrayList<Map<String, Object>>) outMap.get("#result-set-1");
         int i = 0;
         for (Map<String, Object> map : rows)
         {
            Map rs = (Map) rows.get(i);
            TradeClientData data = new TradeClientData();

            data.setAcctnum(convert.getLongData(rs.get("acctnum")));
            data.setClientAccountID(convert.getStrData(rs.get("clientAccountID")));
            data.setTradeStatus(convert.getStrData(rs.get("tradeStatus")));
            data.setProcessStatus(convert.getStrData(rs.get("processStatus")));
            data.setReason(convert.getStrData(rs.get("reason")));
            data.setFirstname(convert.getStrData(rs.get("applicantFName")));
            data.setLastname(convert.getStrData(rs.get("applicantLName")));
            data.setLastTraded(convert.getStrData(rs.get("lastTraded")));
            data.setCash(convert.getDoubleData(rs.get("cash")));
            data.setInvestment(convert.getDoubleData(rs.get("investment")));
            listProfiles.add(i, data);
            i++;
         }
         return listProfiles;
      }
      return null;
   }

   public List<TradeSummary> getTradeSummaryData(Long logonid, String filter)
   {
      DataSource ds = getDataSource();
      TradeSP sp = new TradeSP(ds, "sel_TradeSummaryDetail", 104);
      List<TradeSummary> listProfiles = new ArrayList<TradeSummary>();
      Map outMap = sp.loadTradeSummaryData(logonid, filter);
      if (outMap != null)
      {
         ArrayList<Map<String, Object>> rows = (ArrayList<Map<String, Object>>) outMap.get("#result-set-1");
         int i = 0;
         for (Map<String, Object> map : rows)
         {
            Map rs = (Map) rows.get(i);
            TradeSummary data = new TradeSummary();

            data.setAcctnum(convert.getLongData(rs.get("acctnum")));
            data.setClientAccountID(convert.getStrData(rs.get("clientAccountID")));
            data.setFirstName(convert.getStrData(rs.get("applicantFName")));
            data.setLastName(convert.getStrData(rs.get("applicantLName")));
            data.setTradeStatus(convert.getStrData(rs.get("tradeStatus")));
            data.setProcessStatus(convert.getStrData(rs.get("processStatus")));
            data.setTotalInvestment(convert.getDoubleData(rs.get("totalInvestment")));

            data.setSumcurQty(convert.getDoubleData(rs.get("sumcurQty")));
            data.setSumcurValue(convert.getDoubleData(rs.get("sumcurValue")));

            data.setSumholdingQty(convert.getDoubleData(rs.get("sumholdingQty")));
            data.setSumholdingValue(convert.getDoubleData(rs.get("sumholdingValue")));

            data.setSumnewQty(convert.getDoubleData(rs.get("sumnewQty")));
            data.setSumnewValue(convert.getDoubleData(rs.get("sumnewValue")));
            listProfiles.add(i, data);
            i++;

         }
         return listProfiles;
      }
      return null;
   }

   public void executeTrade(Long acctnum)
   {
      DataSource ds = getDataSource();
      TradeSP sp = new TradeSP(ds, "save_executedTrades", 105);
      Map outMap = sp.executeTrade(acctnum);
   }


}