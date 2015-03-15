package com.invessence.dao.common;

import java.io.Serializable;
import java.util.*;
import javax.faces.bean.*;
import javax.sql.DataSource;

import com.invessence.converter.SQLData;
import com.invessence.dao.admin.AdminSP;
import com.invessence.data.TradeDetails;
import com.invessence.data.common.*;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

@ManagedBean(name = "tradeDAO")
@ApplicationScoped
public class TradeDAO extends JdbcDaoSupport implements Serializable
{
   SQLData convert = new SQLData();

   public void saveAllocation(ManageGoals data) {
      DataSource ds = getDataSource();
      TradeSP sp1 = new TradeSP(ds, "del_asset_alloc",0);
      sp1.deleteAllocation(data);
      TradeSP sp = new TradeSP(ds, "sp_asset_alloc_add_mod",1);
      sp.saveAllocation(data);
   }

   public void savePortfolio(ManageGoals data) {
      DataSource ds = getDataSource();
      TradeSP sp1 = new TradeSP(ds, "del_virtual_portfolio",2);
      sp1.deletePortfolio(data);
      TradeSP sp = new TradeSP(ds, "sp_virtual_portfolio_add_mod",3);
      sp.savePortfolio(data);
   }

   public void createTrades(Long acctnum) {
      DataSource ds = getDataSource();
      TradeSP sp = new TradeSP(ds, "sp_createTrades",4);
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

   public void updateExecutedTrades() {
      DataSource ds = getDataSource();
      TradeSP sp = new TradeSP(ds, "sp_updateExecutedTrades",99);
      sp.updateExecutedTrades();
   }

   public List<Position> loadVirtualPorfolio(Long acctnum) {
      try {
         DataSource ds = getDataSource();
         TradeSP sp = new TradeSP(ds, "admin_sel_virtual_portfolio",101);
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
            return  posList;
         }
      }
      catch (Exception ex) {
         ex.printStackTrace();
      }
      return null;
   }

   public List<WebTradeData> loadTradesDetails(Long acctnum) {
      DataSource ds = getDataSource();
      TradeSP sp = new TradeSP(ds, "sel_displayTradeDetail",102);
      List<WebTradeData> tradeDetails = new ArrayList<WebTradeData>();
      Map outMap = sp.loadTradesDetails(acctnum);
      if (outMap != null)
      {
         ArrayList<Map<String, Object>> rows = (ArrayList<Map<String, Object>>) outMap.get("#result-set-1");
         int i = 0;
         for (Map<String, Object> map : rows)
         {
            Map rs = (Map) rows.get(i);
            WebTradeData data = new WebTradeData();
            data.setClientAccountID(convert.getStrData(rs.get("clientAccountID")));
            data.setAcctnum(convert.getLongData(rs.get("acctnum")));
            data.setProcessed(convert.getStrData(rs.get("processed")));

            data.setTicker(convert.getStrData(rs.get("ticker")));
            data.setQty(convert.getDoubleData(rs.get("curQty")));
            data.setCurPrice(convert.getDoubleData(rs.get("curPrice")));
            data.setMoney(convert.getDoubleData(rs.get("curValue")));

            data.setHoldingTicker(convert.getStrData(rs.get("holdingTicker")));
            data.setHoldingQty(convert.getDoubleData(rs.get("holdingQty")));
            data.setHoldingPrice(convert.getDoubleData(rs.get("holdingPrice")));
            data.setHoldingValue(convert.getDoubleData(rs.get("holdingValue")));
            data.setHoldingWeight(convert.getDoubleData(rs.get("holdingWeight")));
            data.setCostBasisValue(convert.getDoubleData(rs.get("holdingCostBasis")));

            data.setAllocTicker(convert.getStrData(rs.get("allocTicker")));
            data.setAllocQty(convert.getDoubleData(rs.get("allocQty")));
            data.setAllocPrice(convert.getDoubleData(rs.get("allocPrice")));
            data.setAllocValue(convert.getDoubleData(rs.get("allocValue")));
            data.setAllocWeight(convert.getDoubleData(rs.get("allocWeight")));

            data.setTradeType(convert.getStrData(rs.get("tradeType")));
            data.setReason(convert.getStrData(rs.get("reason")));
            data.setCreated(convert.getStrData(rs.get("created")));

            data.setAssetClass(convert.getStrData(rs.get("assetclass")));
            data.setSubclass(convert.getStrData(rs.get("subclass")));
            data.setName(convert.getStrData(rs.get("name")));
            data.setSortorder(i);
            tradeDetails.add(i, data);
            i++;
         }
         return tradeDetails;
      }
      return null;
   }

   public List<TradeClientData> getTradeProfileData(String filter) {
      DataSource ds = getDataSource();
      AdminSP sp = new AdminSP(ds, "sel_collectTradeProfile",103);
      List<TradeClientData> listProfiles = new ArrayList<TradeClientData>();
      Map outMap = sp.loadProfile(filter);
      if (outMap != null)
      {
         ArrayList<Map<String, Object>> rows = (ArrayList<Map<String, Object>>) outMap.get("#result-set-1");
         int i = 0;
         for (Map<String, Object> map : rows)
         {
            Map rs = (Map) rows.get(i);
            TradeClientData data = new TradeClientData();

            data.setAcctnum(convert.getLongData(rs.get("acctnum")));
            //data.setLogonid(convert.getLongData(rs.get("logonid")));
            data.setName(convert.getStrData(rs.get("firstname")) + " " + convert.getStrData(rs.get("lastname")));
            data.setClientAccountID(convert.getStrData(rs.get("IB_acctnum")));
            data.setTradePreference(convert.getStrData(rs.get("tradePreference")));
            data.setGoal(convert.getStrData(rs.get("goal")));
            data.setAccountType(convert.getStrData(rs.get("accttype")));
            data.setAge(convert.getIntData(rs.get("age")));
            data.setHorizon(convert.getIntData(rs.get("horizon")));
            data.setCalendarYear(convert.getIntData(rs.get("yearnum")));
            data.setRiskIndex(convert.getIntData(rs.get("riskIndex")));
            data.setInitialInvestment(convert.getIntData(rs.get("initialInvestment")));
            data.setKeepLiquid(convert.getIntData(rs.get("keepLiquid")));
            data.setActualInvestment(convert.getIntData(rs.get("actualCapital")));
            data.setRecurringInvestment(convert.getIntData(rs.get("recurringInvestment")));
            data.setObjective(convert.getIntData(rs.get("longTermGoal")));
            data.setStayInvested(convert.getIntData(rs.get("stayInvested")));
            data.setDependent(convert.getIntData(rs.get("dependent")));

            data.setTotalIncome(convert.getIntData(rs.get("totalIncomeAnnulized")));
            data.setTotalExpense(convert.getIntData(rs.get("totalExpenseAnnulized")));
            data.setTotalAsset(convert.getIntData(rs.get("totalAsset")));
            data.setTotalLiability(convert.getIntData(rs.get("totalDebt")));
            data.setLiquidAsset(convert.getIntData(rs.get("liquidnetworth")));
            // data.setNetWorth(convert.getIntData(rs.get("networth")));

            data.setSelectedchoice1(convert.getStrData(rs.get("ans1")));
            data.setSelectedchoice2(convert.getStrData(rs.get("ans2")));
            data.setSelectedchoice3(convert.getStrData(rs.get("ans3")));
            data.setSelectedchoice4(convert.getStrData(rs.get("ans4")));
            data.setSelectedchoice5(convert.getStrData(rs.get("ans5")));
            data.setSelectedchoice6(convert.getStrData(rs.get("ans6")));
            data.setSelectedchoice7(convert.getStrData(rs.get("ans7")));
            data.setSelectedchoice8(convert.getStrData(rs.get("ans8")));
            data.setSelectedchoice9(convert.getStrData(rs.get("ans9")));
            data.setSelectedchoice10(convert.getStrData(rs.get("ans10")));
            data.setSelectedchoice11(convert.getStrData(rs.get("ans11")));
            data.setSelectedchoice12(convert.getStrData(rs.get("ans12")));
            data.setSelectedchoice13(convert.getStrData(rs.get("ans13")));
            data.setSelectedchoice14(convert.getStrData(rs.get("ans14")));
            data.setSelectedchoice15(convert.getStrData(rs.get("ans15")));

            data.setProcessStatus(convert.getStrData(rs.get("processStatus")));
            data.setLastTraded(convert.getStrData(rs.get("lastTraded")));
            data.setReason(convert.getStrData(rs.get("reason")));
            data.setAssetAllocationOffset(convert.getDoubleData(rs.get("assetAllocationOffset")));
            data.setPosition(convert.getDoubleData(rs.get("position")));
            data.setAssetClass(convert.getStrData(rs.get("assetclass")));
            data.setCurrentAllocation(convert.getDoubleData(rs.get("currentAllocation")));
            data.setRequiredAllocation(convert.getDoubleData(rs.get("requiredAllocation")));
            data.setCreated(convert.getStrData(rs.get("created")));
            data.setLastUpdated(convert.getStrData(rs.get("lastUpdated")));
            listProfiles.add(i, data);
            i++;
         }
         return listProfiles;
      }
      return null;
   }

   public ArrayList<Map<String, Object>> getTradesAllocationData() {
      DataSource ds = getDataSource();
      AdminSP sp = new AdminSP(ds, "sel_displayTrades2Execute",199);
      List<TradeDetails> tradeDetails = new ArrayList<TradeDetails>();
      Map outMap = sp.getTradesAllocationData();
      if (outMap != null)
         return (ArrayList<Map<String, Object>>) outMap.get("#result-set-1");
      else
         return null;
   }



}