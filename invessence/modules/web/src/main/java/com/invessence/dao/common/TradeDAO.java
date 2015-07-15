package com.invessence.dao.common;

import java.io.Serializable;
import java.util.*;
import javax.faces.bean.*;
import javax.sql.DataSource;

import com.invessence.converter.SQLData;
import com.invessence.dao.admin.AdminSP;
import com.invessence.data.TradeDetails;
import com.invessence.data.common.*;
import com.invmodel.asset.data.Asset;
import com.invmodel.rebalance.data.TradeData;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

@ManagedBean(name = "tradeDAO")
@ApplicationScoped
public class TradeDAO extends JdbcDaoSupport implements Serializable
{
   SQLData convert = new SQLData();

   public void saveAllocation(CustomerData data) {
      DataSource ds = getDataSource();
      TradeSP sp1 = new TradeSP(ds, "del_asset_alloc",0);
      sp1.deleteAllocation(data);
      TradeSP sp = new TradeSP(ds, "sp_asset_alloc_add_mod",1);
      sp.saveAllocation(data);
   }

   public void savePortfolio(CustomerData data) {
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

   public Map<String, TradeSummary> loadTradesDetails(Long acctnum)
   {
      DataSource ds = getDataSource();
      TradeSP sp;

      sp = new TradeSP(ds, "sel_displayTradeDetail", 102);
      Map outMap = sp.loadTradesDetails(acctnum);

      Map<String, TradeSummary> tradesummary = new HashMap<String, TradeSummary>();
      Map<String, Map<String,TradeSummary>> possummary = new HashMap<String, Map<String,TradeSummary>>();

      int i = 0;
      TradeSummary summary;
      Map<String, Asset> posAsset = new HashMap<String, Asset>();
      Asset assetclass;
      Boolean cashonRebal = false;
      Double totalCash = 0.0, remainingBalance;

      String clientAccountID = "Blank",taxable, theme;
      Double allocValue, holdingValue;
      if (outMap != null)
      {
         //NOTE: SP returns two datasets
         ArrayList<Map<String, Object>> posrows = (ArrayList<Map<String, Object>>) outMap.get("#result-set-2");
         if (posrows != null)
         {
            i = 0;
            for (Map<String, Object> map : posrows)
            {
               Map rs = (Map) posrows.get(i);
               clientAccountID = convert.getStrData(rs.get("clientAccountID"));
               String ticker = convert.getStrData(rs.get("ticker"));
               String asset = convert.getStrData(rs.get("assetClass"));
               String subclass = convert.getStrData(rs.get("subclass"));
               String color = convert.getStrData(rs.get("color"));
               Double posValue = convert.getDoubleData(rs.get("positionValue"));
               Double posQty = convert.getDoubleData(rs.get("quantity"));
               Map <String, TradeSummary> tinfo;
               if (!possummary.containsKey(clientAccountID)) {
                  tinfo = new HashMap<String, TradeSummary>();
                  possummary.put(clientAccountID, tinfo);
               }
               else {
                  tinfo = possummary.get(clientAccountID);
               }

               if (possummary.get(clientAccountID).containsKey(ticker))  {
                  summary = possummary.get(clientAccountID).get(ticker);
               }
               else
               {
                  summary = new TradeSummary();
                  TradeData tradedata = new TradeData("", // advisor
                                            clientAccountID,
                                            null,
                                            asset,
                                            subclass,
                                            ticker,
                                            0.0,    // curQty
                                            null,   // curPrice
                                            0.0,   // curValue
                                            ticker,   // holdingTicker
                                            posQty,  //  holdingQty
                                            null,  // holdingPrice
                                            posValue,  // holdingValue
                                            null,      // holdingWeight
                                            null,      // holdingCostBasis
                                            null,      // allocTicker
                                            null,      // allocQty
                                            null,      // allocPrice
                                            null,      // allocValue
                                            null,      // allocWeight
                                            "No-trade", // tradeType
                                            "",          // reason
                                            0.0        // cashAvailable
                  );
                  tradedata.setColor(color);
                  summary.getTradeData().add(tradedata);
                }
               String posKey = clientAccountID + "." + asset;

               if (!posAsset.containsKey(posKey))
               {
                  assetclass = new Asset();
                  assetclass.setColor(color);
                  assetclass.setAsset(asset);
                  assetclass.setValue(0.0);
                  assetclass.setHoldingValue(posValue);
               }
               else
               {
                  assetclass = posAsset.get(posKey);
                  assetclass.setHoldingValue(assetclass.getHoldingValue() + posValue);
               }

               posAsset.put(posKey, assetclass);

               summary.setTotalHoldingValue(summary.getTotalHoldingValue() + posValue);
               if (summary.getListofHoldingTickers().containsKey(ticker)) {
                  posQty = summary.getListofHoldingTickers().get(ticker).getQty() + posQty;
                  posValue = summary.getListofHoldingTickers().get(ticker).getValue() + posValue;
               }
               summary.addListofHoldingTickers(ticker,
                                               posQty,
                                               posValue);

               tinfo.put(ticker,summary);
               possummary.put(clientAccountID, tinfo);
               i++;
            } // end Position Loop
         } // Endif Posrow

         //NOTE: SP returns two datasets, note the #result-set- value below.
         ArrayList<Map<String, Object>> rows = (ArrayList<Map<String, Object>>) outMap.get("#result-set-1");
         if (rows != null)
         {
            i = 0;
            for (Map<String, Object> map : rows)
            {
               Map rs = (Map) rows.get(i);
               clientAccountID = convert.getStrData(rs.get("clientAccountID"));
               acctnum = convert.getLongData(rs.get("acctnum"));
               if (tradesummary.containsKey(clientAccountID))
               {
                  summary = tradesummary.get(clientAccountID);
               }
               else
               {
                  summary = new TradeSummary();
                  summary.setClientAccountID(clientAccountID);
                  summary.setAcctnum(acctnum);
                  summary.setEmail(convert.getStrData(rs.get("email")));
                  summary.setLastName(convert.getStrData(rs.get("lastName")));
                  summary.setFirstName(convert.getStrData(rs.get("firstName")));
                  taxable = convert.getStrData(rs.get("taxable"));
                  theme = convert.getStrData(rs.get("theme"));

                  if (taxable == null || taxable.isEmpty() || taxable.toLowerCase().startsWith("n"))
                  {
                     taxable = "Non-taxable";
                  }
                  else
                  {
                     taxable = "Taxable";
                  }

                  if (theme == null)
                  {
                     theme = "Default";
                  }

                  summary.setTaxable(taxable);
                  summary.setTheme(theme);
                  summary.setGoal(convert.getStrData(rs.get("goal")));

                  summary.setTotalNewValue(0.0);
                  summary.setTotalHoldingValue(0.0);
                  summary.setTotalAllocValue(0.0);

               }

               String ticker = convert.getStrData(rs.get("ticker"));
               String asset = convert.getStrData(rs.get("assetclass"));
               String subclass = convert.getStrData(rs.get("subclass"));
               Double curValue = convert.getDoubleData(rs.get("curValue"));
               Double curQty = convert.getDoubleData(rs.get("curQty"));
               allocValue = convert.getDoubleData(rs.get("allocValue"));
               holdingValue = convert.getDoubleData(rs.get("holdingValue"));
               Double runningHolding = 0.0, runningBalance = 0.0;
               Double runningQty = 0.0, runningBalQty = 0.0;

               if (possummary.get(clientAccountID).containsKey(ticker)  && (! ticker.equalsIgnoreCase("cash"))) {
                  runningHolding = possummary.get(clientAccountID).get(ticker).getListofHoldingTickers().get(ticker).getValue();
                  runningQty = possummary.get(clientAccountID).get(ticker).getListofHoldingTickers().get(ticker).getQty();
                  runningBalQty = runningQty + curQty;
                  runningBalance = runningHolding + curValue;
                  possummary.get(clientAccountID).get(ticker).addListofHoldingTickers(ticker, curQty, curValue);
                  // possummary.get(clientAccountID).get(ticker).setTotalHoldingValue(runningBalance);
               }
               else {
                  runningBalance = curValue;
                  runningHolding = 0.0;
               }


               Double posHolding = 0.0;
               if (!summary.getAsset().containsKey(asset))
               {
                  assetclass = new Asset();
                  assetclass.setColor(convert.getStrData(rs.get("color")));
                  assetclass.setAsset(asset);
                  // If we have position, then use that holding.
                  String posKey = clientAccountID + "." + asset;
                  posHolding = 0.0;
                  if (posAsset.containsKey(posKey)) {
                     posHolding = posAsset.get(posKey).getHoldingValue();
                     summary.setTotalHoldingValue(summary.getTotalHoldingValue() + posHolding);
                     posAsset.remove(posKey);
                  }
                  assetclass.setHoldingValue(posHolding); // Only add once, because it is from position.
                  if (asset.equalsIgnoreCase("cash")) {
                     assetclass.setValue(curValue);

                  }
                  else {
                     assetclass.setValue(posHolding + curValue); // Set current value to same as position, then keep adding trades.
                     summary.setTotalAllocValue(summary.getTotalHoldingValue() + curValue);
                  }
               }
               else
               {
                  assetclass = summary.getAsset().get(asset);
                  assetclass.setValue(assetclass.getValue() + curValue);
               }
               summary.setTotalNewValue(summary.getTotalNewValue() + curValue);

               summary.getAsset().put(asset, assetclass);

               TradeData tradeData = new TradeData("",
                                         clientAccountID,
                                         convert.getLongData(rs.get("acctnum")),
                                         asset,
                                         subclass,
                                         ticker,
                                         convert.getDoubleData(rs.get("curQty")),
                                         convert.getDoubleData(rs.get("curPrice")),
                                         curValue,
                                         convert.getStrData(rs.get("holdingTicker")),
                                         runningQty,
                                         convert.getDoubleData(rs.get("holdingPrice")),
                                         runningHolding,
                                         convert.getDoubleData(rs.get("holdingWeight")),
                                         convert.getDoubleData(rs.get("holdingCostBasis")),
                                         convert.getStrData(rs.get("allocTicker")),
                                         runningBalQty,
                                         convert.getDoubleData(rs.get("allocPrice")),
                                         runningBalance,
                                         convert.getDoubleData(rs.get("allocWeight")),
                                         convert.getStrData(rs.get("tradeType")),
                                         convert.getStrData(rs.get("reason")),
                                         0.0  // cash Remaining
               );
               tradeData.setColor(convert.getStrData(rs.get("color")));

               summary.getTradeDetails().add(tradeData);
               summary.addListofHoldingTickers(ticker,
                                               convert.getDoubleData(rs.get("curQty")),
                                               curValue);
               tradesummary.put(clientAccountID, summary);
               i++;
            }
         }


         // Now Adjust trades

      }

      return tradesummary;
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

   public ArrayList<Map<String, Object>> getTradeData() {
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