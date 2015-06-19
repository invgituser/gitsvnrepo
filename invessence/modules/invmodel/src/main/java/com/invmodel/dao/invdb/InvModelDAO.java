package com.invmodel.dao.invdb;

import java.util.*;
import javax.sql.DataSource;

import com.invessence.converter.SQLData;
import com.invmodel.asset.data.Asset;
import com.invmodel.dao.*;
import com.invmodel.position.data.*;
import com.invmodel.portfolio.data.*;
import com.invmodel.rebalance.data.*;
import com.invmodel.inputData.*;
import org.springframework.jdbc.core.support.JdbcDaoSupport;


public class InvModelDAO extends JdbcDaoSupport
{
   private static InvModelDAO instance = null;
   DBConnectionProvider dbconnection = DBConnectionProvider.getInstance();
   SQLData convert = new SQLData();
   DataSource ds = dbconnection.getMySQLDataSource();

   public CurrentHolding loadDBHolding(Long p_acctnum)
   {
      // DataSource ds = getDs();
      String storedProcName = "sel_position";
      InvModelSP sp = new InvModelSP(ds, storedProcName, 1, 0);
      CurrentHolding currentHolding = new CurrentHolding();
      currentHolding.setHoldingList(new ArrayList<HoldingData>());

      Map outMap = sp.loadDBData(p_acctnum);

      Double longGain = 0.0,
         longLoss= 0.0,
         shortGain = 0.0,
         shortLoss = 0.0;

      if (outMap != null)
      {
         ArrayList<Map<String, Object>> rows = (ArrayList<Map<String, Object>>) outMap.get("#result-set-1");
         int i = 0;
         Double cashAvailable = 0.0;
         for (Map<String, Object> map : rows)
         {
            Map rs = (Map) rows.get(i);
            String ticker =  convert.getStrData(rs.get("symbol"));
            // Ticker test is added, because we are loading some data for display purpose.
            if (ticker == null || ticker.length() == 0)
               continue;

            // data is good.
            HoldingData data = new HoldingData();
            if (i == 0) {
               currentHolding.setAcctnum(convert.getLongData(rs.get("acctnum")));
               currentHolding.setClientAccountID(convert.getStrData(rs.get("clientAccountID")));
               currentHolding.setFirstname(convert.getStrData(rs.get("firstname")));
               currentHolding.setLastname(convert.getStrData(rs.get("lastname")));
               currentHolding.setDateOpened(convert.getStrData(rs.get("dateOpened")));
               currentHolding.setAccountAlias(convert.getStrData(rs.get("accountAlias")));
               currentHolding.setTotalFees(convert.getDoubleData(rs.get("ytdinvoiceFee")));

            }
            data.setCurrencyPrimary(convert.getStrData(rs.get("currencyPrimary")));
            data.setTicker(ticker);
            data.setDescription(convert.getStrData(rs.get("description")));
            data.setSide(convert.getStrData(rs.get("side")));
            data.setQty(convert.getDoubleData(rs.get("quantity")));
            data.setCostBasisPrice(convert.getDoubleData(rs.get("costBasisPrice")));
            data.setCostBasisMoney(convert.getDoubleData(rs.get("costBasisMoney")));
            data.setMarkPrice(convert.getDoubleData(rs.get("markPrice")));
            data.setPositionValue(convert.getDoubleData(rs.get("positionValue")));
            data.setFifoPnlUnrealized(convert.getDoubleData(rs.get("fifoPnlUnrealized")));
            data.setLevelOfDetail(convert.getStrData(rs.get("levelOfDetail")));
            data.setAssetclass(convert.getStrData(rs.get("assetclass")));
            data.setColor(convert.getStrData(rs.get("color")));
            data.setSubclass(convert.getStrData(rs.get("subclass")));
            data.setWeight(convert.getDoubleData(rs.get("weight")));
            data.setYield(convert.getDoubleData(rs.get("yield")));
            data.setExpenseRatio(convert.getDoubleData(rs.get("expenseRatio")));
            data.setRisk(convert.getDoubleData(rs.get("risk")));

            /*
            longGain = longGain + convert.getDoubleData(rs.get("longGain"));
            longLoss = longLoss + convert.getDoubleData(rs.get("longLoss"));
            shortGain = shortGain + convert.getDoubleData(rs.get("shortGain"));
            shortLoss = shortLoss + convert.getDoubleData(rs.get("shortLoss"));
            */


            if(ticker.equalsIgnoreCase("cash")) {
               cashAvailable = cashAvailable + convert.getDoubleData(rs.get("positionValue"));
               currentHolding.setCashAvailable(cashAvailable);
            }

            currentHolding.setLongGains(longGain);
            currentHolding.setLongLoss(longLoss);
            currentHolding.setShortGains(shortGain);
            currentHolding.setShortLoss(shortLoss);
            currentHolding.getHoldingList().add(i, data);

            i++;
         }
      }

      return currentHolding;

   }

   public List<ProfileData> getListOfAccounts(Long logonid, Long acctnum, String filter) {
      // DataSource ds = getDs();
      InvModelSP sp = new InvModelSP(ds, "sel_ClientProfileData2",1, 1);
      List<ProfileData> profileList = new ArrayList<ProfileData>();
      Map outMap = sp.collectProfileData(logonid, acctnum);
      try {
         if (outMap != null)
         {
            ArrayList<Map<String, Object>> rows = (ArrayList<Map<String, Object>>) outMap.get("#result-set-1");
            int i = 0;
            for (Map<String, Object> map : rows)
            {
               Map rs = (Map) rows.get(i);
               ProfileData data = new ProfileData();
               data.setLogonid(convert.getLongData(rs.get("logoid")));
               data.setAcctnum(convert.getLongData(rs.get("acctnum")));
               data.setClientAccountID(convert.getStrData(rs.get("clientAccountID")));

               data.setAdvisor(convert.getStrData(rs.get("advisor")));
               data.setTheme(convert.getStrData(rs.get("theme")));
               data.setBasket(convert.getStrData(rs.get("basket")));
               data.setName(convert.getStrData(rs.get("firstname")) + " " + convert.getStrData(rs.get("lastname")));
               data.setGoal(convert.getStrData(rs.get("goal")));
               data.setAccountType(convert.getStrData(rs.get("accttype")));
               data.setAge(convert.getIntData(rs.get("age")));
               data.setHorizon(convert.getIntData(rs.get("horizon")));
               data.setRiskIndex(convert.getIntData(rs.get("riskIndex")));
               data.setInitialInvestment(convert.getIntData(rs.get("initialInvestment")));
               data.setKeepLiquid(convert.getIntData(rs.get("keepLiquid")));
               data.setActualInvestment(convert.getIntData(rs.get("actualCapital")));
               data.setRecurringInvestment(convert.getIntData(rs.get("recurringInvestment")));
               data.setObjective(convert.getIntData(rs.get("longTermGoal")));
               data.setStayInvested(convert.getIntData(rs.get("stayInvested")));
               data.setRiskCalcMethod(convert.getStrData(rs.get("calcModel")));
               data.setAllocationIndex(convert.getIntData(rs.get("assetIndex")));
               data.setPortfolioIndex(convert.getIntData(rs.get("portfolioIndex")));
               data.setDependent(convert.getIntData(rs.get("dependent")));
               //data.setDateOpened(convert.getStrData(rs.get("dateOpened")));
               String taxable = convert.getStrData(rs.get("taxable"));
               if (taxable == null)
                  data.setAccountTaxable(false);
               else if (taxable.startsWith("N"))
                  data.setAccountTaxable(false);
               else
                  data.setAccountTaxable(true);
               profileList.add(i, data);
               i++;
            }
         }
         return profileList;
      }
      catch (Exception ex) {
         ex.printStackTrace();
      }
      return null;
   }

   public Map<String, Asset> getAllocation(Long acctnum) {
      // DataSource ds = getDs();
      InvModelSP sp = new InvModelSP(ds, "sel_asset_alloc",1, 2);
      Map outMap = sp.collectAllocation(acctnum);
      try {
         if (outMap != null)
         {
            ArrayList<Map<String, Object>> rows = (ArrayList<Map<String, Object>>) outMap.get("#result-set-1");
            int i = 0;
            Map<String, Asset> uaseetdata = new HashMap<String, Asset>();
            String assetname;
            Boolean modelflag = false;
            Double savedweight = 0.0;
            for (Map<String, Object> map : rows)
            {
               Map rs = (Map) rows.get(i);
               if (! convert.getStrData(rs.get("allocationmodel")).equalsIgnoreCase("D"))
                  modelflag = true;
               assetname = convert.getStrData(rs.get("assetclass"));
               savedweight =  convert.getDoubleData(rs.get("weight"));
               savedweight = savedweight / 100.00;
               uaseetdata.put(assetname,
                              new Asset(assetname,
                                        "",
                                        savedweight,
                                        0.0,
                                        0.0,
                                        0.0,
                                        0.0,
                                        0.0));

               i++;
            }
            return (uaseetdata);
         }
      }
      catch (Exception ex) {
         ex.printStackTrace();
      }
      return null;
   }

   public List<PortfolioSubclass> getExcludedSubclass(Long acctnum) {
      // DataSource ds = getDs();
      InvModelSP sp = new InvModelSP(ds, "sel_ExcludedSubclass",1, 3);
      Map outMap = sp.collectSubClassExclusionList(acctnum);
      String asset;
      try {
         if (outMap != null)
         {
            ArrayList<Map<String, Object>> rows = (ArrayList<Map<String, Object>>) outMap.get("#result-set-1");
            int i = 0;
            List<PortfolioSubclass> excludeList = new ArrayList<PortfolioSubclass>();
            Portfolio pf = new Portfolio();
            for (Map<String, Object> map : rows)
            {
               Map rs = (Map) rows.get(i);
               PortfolioSubclass dp = new PortfolioSubclass();
               String assetname = convert.getStrData(rs.get("assetclass"));
               String subclassname = convert.getStrData(rs.get("subclass"));

               dp.setName(pf.getsubclasskey(assetname,subclassname));
               dp.setParentclass(assetname);
               dp.setSubasset(subclassname);
               excludeList.add(dp);
               i++;
            }
            return (excludeList);
         }
      }
      catch (Exception ex) {
         ex.printStackTrace();
      }
      return null;
   }

   public Map<String, ArrayList> loadDBExecutedTrades(Long acctnum)
   {
      // DataSource ds = getDs();
      String storedProcName = "sel_executedTrades";
      InvModelSP sp = new InvModelSP(ds, storedProcName,1, 4);
      Map<String, ArrayList> tradeMap = new HashMap<String, ArrayList>();

      ArrayList<ExecutedTradesData> executedList;
      Map outMap = sp.loadDBExecutedTrades(acctnum);
      if (outMap != null)
      {
         ArrayList<Map<String, Object>> rows = (ArrayList<Map<String, Object>>) outMap.get("#result-set-1");
         int i = 0;
         for (Map<String, Object> map : rows)
         {
            Map rs = (Map) rows.get(i);
            String ticker = convert.getStrData(rs.get("symbol"));
            ExecutedTradesData executedTradedata = new ExecutedTradesData();
            if (tradeMap.containsKey(ticker)) {
               executedList = tradeMap.get(ticker);
               if (executedList == null)
                  executedList = new ArrayList<ExecutedTradesData>();
            }
            else {
               executedList = new ArrayList<ExecutedTradesData>();
            }

            executedTradedata.setTicker(ticker);
            executedTradedata.setDateExecuted(convert.getStrData(rs.get("reportDate")));
            executedTradedata.setTradeID(convert.getStrData(rs.get("tradeID")));
            executedTradedata.setQty(convert.getIntData(rs.get("quantity")));
            executedTradedata.setTradeFee(convert.getDoubleData(rs.get("ibcommission")));
            executedTradedata.setProceed(convert.getDoubleData(rs.get("proceed")));
            executedTradedata.setTradePrice(convert.getDoubleData(rs.get("tradeprice")));
            executedTradedata.setLastExecuted(convert.getIntData(rs.get("days_last_executed")));
            executedList.add(executedTradedata);
            tradeMap.put(ticker,executedList);
            i++;
         }
      }
      return tradeMap;

   }


   public Map<String, SecurityTLHData> loadTLHSecurities()
   {
      // DataSource ds = getDs();
      String storedProcName = "sel_taxHarvestingSecurities";
      InvModelSP sp = new InvModelSP(ds, storedProcName,1, 99);
      Map<String, SecurityTLHData> tlhMap = new HashMap<String, SecurityTLHData>();

      Map outMap = sp.loadSecurityData();
      SecurityTLHData sd = null;
      if (outMap != null)
      {
         ArrayList<Map<String, Object>> rows = (ArrayList<Map<String, Object>>) outMap.get("#result-set-1");
         int i = 0;
         for (Map<String, Object> map : rows)
         {
            Map rs = (Map) rows.get(i);
            String ticker = convert.getStrData(rs.get("ticker"));
            if (! tlhMap.containsKey(ticker))
               sd = new SecurityTLHData();
            else
               sd = tlhMap.get(ticker);
            String tlhticker = convert.getStrData(rs.get("tlhticker"));
            sd.setTicker(ticker);
            sd.addTLHData(tlhticker,
                          convert.getStrData(rs.get("active")),
                          convert.getDoubleData(rs.get("price")),
                          convert.getDoubleData(rs.get("weight")),
                          convert.getStrData(rs.get("type")),
                          convert.getStrData(rs.get("style")),
                          convert.getStrData(rs.get("assetclass")),
                          convert.getStrData(rs.get("subclass"))
            );
            tlhMap.put(ticker,sd);
            i++;
         }
      }
      return tlhMap;

   }

   public Map<String, SecurityTLHData> loadTLHReverseSecurities()
   {
      // DataSource ds = getDs();
      String storedProcName = "sel_taxReverseSecurities";
      InvModelSP sp = new InvModelSP(ds, storedProcName,1, 99);
      Map<String, SecurityTLHData> tlhMap = new HashMap<String, SecurityTLHData>();
      ArrayList<TLHData>  tlhdataList = null;


      Map outMap = sp.loadSecurityData();
      if (outMap != null)
      {
         ArrayList<Map<String, Object>> rows = (ArrayList<Map<String, Object>>) outMap.get("#result-set-1");
         int i = 0;
         for (Map<String, Object> map : rows)
         {
            Map rs = (Map) rows.get(i);
            SecurityTLHData sd = new SecurityTLHData();
            String ticker = convert.getStrData(rs.get("ticker"));
            String tlhticker = convert.getStrData(rs.get("tlhticker"));
            sd.setTicker(ticker);
            sd.addTLHData(tlhticker,
                          convert.getStrData(rs.get("active")),
                          convert.getDoubleData(rs.get("price")),
                          convert.getDoubleData(rs.get("weight")),
                          convert.getStrData(rs.get("type")),
                          convert.getStrData(rs.get("style")),
                          convert.getStrData(rs.get("assetclass")),
                          convert.getStrData(rs.get("subclass"))
            );

            tlhMap.put(ticker,sd);
            i++;
         }
      }
      return tlhMap;

   }

   public void deleteTradeData(Long acctnum) {
      // DataSource ds = getDs();
      String storedProcName = "del_rebalanced_trades";
      InvModelSP sp = new InvModelSP(ds, storedProcName,1, 11);
      sp.deleteTradeData(acctnum);
      }


   public void saveTradeData(ArrayList<TradeData> tradeData) {
      // DataSource ds = getDs();
      String storedProcName = "sp_save_rebalanced_trades";
      InvModelSP sp = new InvModelSP(ds, storedProcName,1, 10);
      for (TradeData tData : tradeData) {
         sp.saveTradeData(tData);
      }
   }

   public Map<Long, PositionData> loadAllExternalPositions(Long familyacctnum)
   {
      // DataSource ds = getDs();
      String storedProcName = "sel_all_positions";
      InvModelSP sp = new InvModelSP(ds, storedProcName, 1, 11);
      Map<Long, PositionData> currentHolding = new HashMap<Long, PositionData>();

      Map outMap = sp.loadAllExternalPositions(familyacctnum);

      if (outMap != null)
      {
         ArrayList<Map<String, Object>> rows = (ArrayList<Map<String, Object>>) outMap.get("#result-set-1");
         if (rows != null)
         {
            int i = 0;
            Long datafamilyacctnum = null;
            PositionData pd = null;
            for (Map<String, Object> map : rows)
            {
               Map rs = (Map) rows.get(i);
               datafamilyacctnum = convert.getLongData(rs.get("FamilyAccountNumber"));
               if (currentHolding.containsKey(datafamilyacctnum))
               {
                  pd = currentHolding.get(datafamilyacctnum);
               }
               else
               {
                  pd = new PositionData(datafamilyacctnum);
               }

               pd.addInfo(convert.getStrData(rs.get("clientAccountID")),
                          0L,
                          convert.getStrData(rs.get("symbol")),
                          convert.getStrData(rs.get("description")),
                          "",
                          convert.getStrData(rs.get("assetClass")),
                          convert.getStrData(rs.get("assetClass")),
                          convert.getIntData(rs.get("quantity")),
                          convert.getDoubleData(rs.get("markPrice")),
                          convert.getDoubleData(rs.get("positionValue")),
                          convert.getDoubleData(rs.get("costBasisMoney")),
                          convert.getDoubleData(rs.get("fifoPnlUnrealized")),
                          0.0
               );
               i++;
            }
         }
      }
      return currentHolding;
   }

}