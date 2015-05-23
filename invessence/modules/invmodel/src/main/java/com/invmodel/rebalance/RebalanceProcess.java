package com.invmodel.rebalance;

import java.util.*;

import com.invmodel.Const.InvConst;
import com.invmodel.asset.AssetAllocationModel;
import com.invmodel.asset.data.*;
import com.invmodel.dao.*;
import com.invmodel.inputData.ProfileData;
import com.invmodel.portfolio.PortfolioModel;
import com.invmodel.portfolio.data.*;
import com.invmodel.rebalance.data.*;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 11/18/14
 * Time: 10:51 AM
 * To change this template use File | Settings | File Templates.
 */
public class RebalanceProcess
{
   private static RebalanceProcess instance = null;

   private ProfileData profile;
   private TaxHarvestingDAO thlDAO = new TaxHarvestingDAO();
   private Map<String, SecurityTLHData> tlhSecMap = null;
   private Map<String, SecurityTLHData> tlhReverseSecMap = null;
   private TLHSecurityCollection tlhSecurityCollection = null;
   private SecurityDBCollection securityDAO = null;
   private PortfolioOptimizer portfolioOptimizer = null;

   private String advisor;

   private RebalanceProcess()
   {
      super();
   }

   public static synchronized RebalanceProcess getInstance()
   {
      if (instance == null)
      {
         instance = new RebalanceProcess();
      }

      return instance;
   }

   public void setTlhSecurityCollection(TLHSecurityCollection tlhSecurityCollection)
   {
      this.tlhSecurityCollection = tlhSecurityCollection;
   }

   public void setPortfolioOptimizer(PortfolioOptimizer portfolioOptimizer)
   {
      this.portfolioOptimizer = portfolioOptimizer;
   }

   public void setSecurityDAO(SecurityDBCollection securityDAO)
   {
      this.securityDAO = securityDAO;
   }

   public List<ProfileData> loadCustomerProfile(Long logonid, Long acctnum, String filter) {
      List<ProfileData> data = null;
      try
      {
         data = thlDAO.getListOfAccounts(logonid, acctnum, filter);
         return data;
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
      return (data);

   }

   public CurrentHolding loadHoldings(Long acctnum) {
      CurrentHolding data = null;
      try
      {
         data = thlDAO.loadDBHolding(acctnum);
         if (data != null) {
            data.addTotals();
         }
         return data;
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
      return (data);

   }

   public Map<String, ArrayList> loadExecuteTrades(Long acctnum) {
      Map <String, ArrayList> data = new HashMap<String, ArrayList>();
      try
      {
         data = thlDAO.loadDBExecutedTrades(acctnum);
         return data;
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
      return data;

   }

   public Map<String, Asset> loadCurrentAssetAllocation(Long acctnum) {
      Map<String, Asset> data = null;
      try
      {
         data = thlDAO.getAllocation(acctnum);
         return data;
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
      return (data);
   }

   public List<PortfolioSubclass> loadExcludedSubclass(Long acctnum) {
      List<PortfolioSubclass> data = null;
      try
      {
         data = thlDAO.getExcludedSubclass(acctnum);
         return data;
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
      return (data);
   }

   public void loadAssetClass(ProfileData pdata, Integer years) {
      try {
         if (pdata.getAssetData() == null) {
            AssetAllocationModel assetAllocationModel = AssetAllocationModel.getInstance();
            assetAllocationModel.setPortfolioOptimizer(portfolioOptimizer);
            AssetClass[] aamc;
            if (years == null)
               years = pdata.getHorizon();
            pdata.setNumOfAllocation(years);
            aamc = assetAllocationModel.buildAllocation(pdata);
            if (aamc != null)  {
               pdata.setAssetData(aamc);
            }
         }
      }
      catch (Exception ex) {
         ex.printStackTrace();
      }
   }

   public void loadPortfolio(ProfileData pdata, Integer years) {
      AssetClass[] aamc;
      Portfolio[] pfclass;
      try
      {
         if (pdata.getPortfolioData() == null) {
            loadAssetClass(pdata,years);
            PortfolioModel portfolioModel = new PortfolioModel();
            portfolioModel.setPortfolioOptimizer(portfolioOptimizer);
            portfolioModel.setSecurityDao(securityDAO);
            pdata.setNumOfPortfolio(years);
            Integer displayYear = 0;
            aamc = pdata.getAssetData();
            if (aamc != null)
            {
               pfclass = portfolioModel.buildPortfolio(aamc, pdata);
               if (pfclass != null)
               {
                  pdata.setPortfolioData(pfclass);
               }
            }

         }

      }
      catch (Exception ex)
      {
         ex.printStackTrace();
      }
   }

   public void saveTrades(Long acctnum, ArrayList<TradeData> tData) {
      thlDAO.deleteTradeData(acctnum);
      thlDAO.saveTradeData(tData);
   }

   public ArrayList<TradeData> process(Long logonid, Long acctnum) throws Exception
   {
      ArrayList <TradeData> tradeDataList = null;
      List <ProfileData> profileList;
      Integer currentYear;

      try {
         if (tlhSecurityCollection == null)
            return null;

         if (securityDAO == null)
            return null;

         if (portfolioOptimizer == null)
            return null;

         profileList =  loadCustomerProfile(logonid, acctnum, null);
         // Note: Data is already collected, we are just assigning a local variable.
         tlhSecMap = tlhSecurityCollection.getTlhSecurityMap();
         tlhReverseSecMap = tlhSecurityCollection.getTlhReverseSecMap();

         for (ProfileData pdata : profileList) {
            advisor = pdata.getAdvisor();
            pdata.setNumOfAllocation(1);
            /*
            Questions1: loadCurrentAssetAllocation loads what is currently allocated from allocation table.
            Do we want the allocation from our DB or should we look at the holding and get the allocation from holding?
             */

            Map<String, Asset> asset = loadCurrentAssetAllocation(pdata.getAcctnum());
            /*
            Questions2: loadPortfolio recreates the Asset allocation and portfolio.  Should we get only one year or two years?
            Currently this process get it for size as defined in horizon (Performance issue).
            Question3: Also, the loadPortfolio saves the allocation and portfolio on pdata class.
            Info: So,newHoldings is just getting that data.
             */
            loadPortfolio(pdata, pdata.getHorizon());

            //Number of years is 1
            loadAssetClass(pdata, 1);
            //AssetDBCollection assetDAO = AssetDBCollection.getInstance();
            pdata.setNumOfPortfolio(1);

            loadPortfolio(pdata, 1);
            Portfolio[] newHoldings = pdata.getPortfolioData();

            // Now load Excluded subclass if defined by Advisor
            List<PortfolioSubclass> exclude = loadExcludedSubclass(pdata.getAcctnum());

            // Load Position Data (Current Holding)
            CurrentHolding curHolding = loadHoldings(pdata.getAcctnum());

            // Load Trade History.  This can be eliminated once we can identify the Realized Loss/Gain
            Map<String, ArrayList> tradesExecutedMap = loadExecuteTrades(pdata.getAcctnum());

            //pdata.setShortLossCarry(5000.00);
            //pdata.setLongLossCarry(-20000.00);

            currentYear = 0;  // Need to determine the Asset year to use.  Currently we are using 0

            //check is it a taxable or non taxable account
            //Check client tax rate, if tax rate below threshold then no need to tax optimize
            if(!pdata.getAccountTaxable()) {

               //Is one of the conditions met to rebalance
               //Is there extra cash
               //Boolean cashAvailable = checkCashHolding(curHolding,aamc[0],asset);

               //Is there more then 3% difference in assets
               //Boolean assetDifferenceFlag = checkOtherAssets(curHolding,aamc[0],asset, orderedAssets);

               tradeDataList = createTradeObjectNonTaxAccounts(newHoldings, pdata.getAssetData(), pdata, curHolding);
            }
            else { // Account is taxable and some tax optimization must be applied
               //If there is a long or short term loss carry in client profile
               if (pdata.getLongLossCarry() != null)
                  curHolding.setLongGains(pdata.getLongLossCarry()+curHolding.getLongGains());

               if (pdata.getShortLossCarry() != null)
                  curHolding.setShortGains(pdata.getShortLossCarry() + curHolding.getShortGains());


               //If there is a external long or short gain in client profile
               if (pdata.getLongExternalGain() != null)
                  curHolding.setLongGains(pdata.getLongExternalGain()+curHolding.getLongGains());

               if (pdata.getShortExternalGain() != null)
                  curHolding.setShortGains(pdata.getShortExternalGain()+curHolding.getShortGains() );


               HoldingData holdingData = null;
               //Add all the long and short gains and loss
/*             Prashant 12/20/2014 -- This code is move to Data loading process.
               for (int i = 0; i < curHolding.getHoldingList().size(); i++){

                  holdingData = curHolding.getHoldingList().get(i);

                  if(holdingData.getRelLongLoss() != null)
                     curHolding.setLongGains(curHolding.getLongGains() + holdingData.getRelLongLoss());
                  if(holdingData.getRelLongGain() != null)
                     curHolding.setLongGains(curHolding.getLongGains() + holdingData.getRelLongGain());

                  if (holdingData.getRelShortLoss() !=null)
                     curHolding.setShortGains(curHolding.getShortGains() + holdingData.getRelShortLoss());
                  if (holdingData.getRelShortGain() != null)
                     curHolding.setShortGains(curHolding.getShortGains() + holdingData.getRelShortGain());
               }
*/
               //Is there a short unrealized gain/Loss in a ticker
               Boolean sell;

               //Compute tax lot proceeds and asign to long and short buckets
               for (int i = 0; i < curHolding.getHoldingList().size(); i++){

                  holdingData = curHolding.getHoldingList().get(i);
                  if(!holdingData.getTicker().equalsIgnoreCase("cash"))
                     computeLongShortReturns(holdingData, tradesExecutedMap);
               }

               //Generate a trade list
               tradeDataList = createTradeObjectTaxAccounts(newHoldings, curHolding);
            }
         }
         saveTrades(acctnum, tradeDataList);
      }
      catch (Exception ex) {
         ex.printStackTrace();
      }
      return tradeDataList;
   }

   public  void setTaxLossFlags(Portfolio[] nHoldings,  CurrentHolding curHolding, Integer year) throws Exception
   {
      HoldingData holdingData;
      Map<String, PortfolioSecurityData> portfolioMap = nHoldings[year].getPortfolioMap();

      PortfolioSecurityData portfolioSecurityData;
      String ticker;

      //Set tax loss flag for trades
      for (int i = 0; i < curHolding.getHoldingList().size(); i++){

         holdingData = curHolding.getHoldingList().get(i);
         holdingData.setTlhSellLongFlag(false);
         holdingData.setTlhSellShortFlag(false);
         boolean tlhReverseFlag;

         ticker = holdingData.getTicker();
         //If the ticker is also in the new holding then set the flag otherwise it will be sold regardless
         if(portfolioMap.containsKey(ticker)) {

            if(!holdingData.getTicker().equalsIgnoreCase("cash") ){
               if (holdingData.getWashSaleFlag() == false){

                  //check if tax loss needs reverse
                  tlhReverseFlag = tlhReverse(holdingData);
                  if (!tlhReverseFlag){

                     //Check long tax loss opportunity
                     if (holdingData.getLongTimeShares() > 0.0){

                        double tradeCost = holdingData.getLongProceeds()/holdingData.getLongTimeShares();
                        Double longGL = (holdingData.getMarkPrice() - tradeCost)*holdingData.getLongTimeShares();
                        //Sell if there is a long tax opportunity
                        if((1-InvConst.TLH_Long_THRESHOLd)* holdingData.getMarkPrice() < tradeCost ){
                           holdingData.setTlhSellLongFlag(true);
                           curHolding.setLongGains(curHolding.getLongGains() + longGL);
                        }
                     }
                     //If not long then, Check both Long and short tax opportunity, is it worth selling the whole position?
                     //You can not sell shortShares unless you also sell long shares.
                     if (!holdingData.isTlhSellLongFlag() &&
                        (1-InvConst.TLH_SHORT_THRESHOLd)* holdingData.getMarkPrice() < holdingData.getCostBasisPrice()){
                        //Add a condition so that we are not taking long term cap gains over short term
                        Double tradeCostLong = 0.0;
                        Double longGL = 0.0;
                        Double tradeCostShort = holdingData.getShortProceeds()/holdingData.getShortTimeShares();
                        Double shortGL = (holdingData.getMarkPrice() - tradeCostShort) * holdingData.getShortTimeShares();
                        if(holdingData.getLongTimeShares() > 0.0){
                           tradeCostLong = holdingData.getLongProceeds()/holdingData.getLongTimeShares();
                           longGL = (holdingData.getMarkPrice() - tradeCostLong) * holdingData.getLongTimeShares();
                        }
                        //If Long Gains then they need to be less than threshold to take a larger short term loss
                        if (longGL < InvConst.TLH_Long_THRESHOLd * holdingData.getCostBasisMoney() &&
                           shortGL < -1.0 * InvConst.TLH_SHORT_THRESHOLd *holdingData.getCostBasisMoney()) {
                           if(curHolding.getShortGains() > 0.0) {
                              //Sell if there is a short tax opportunity
                              holdingData.setTlhSellShortFlag(true);
                              //Adjust ShortGains
                              curHolding.setShortGains(curHolding.getShortGains() + shortGL);
                           }
                        }
                     }
                  }
               }
            }
         }
      }
   }

   public Boolean tlhReverse (HoldingData holdingData){

      String ticker;
      Boolean reverseFlag = false;
      SecurityTLHData tlhsecuritydata = null;

      ticker = holdingData.getTicker();

      if (holdingData.getWashSaleFlag() == false) {
         //Is ticker one of the TLH substitute ticker

         //If yes then sell set the tlhShortFlag to reverse the trade to original ticker
         //Replace this later,  Added this section for multiple Tax Loss Harvsting
         if(tlhSecMap != null){
            if(tlhSecMap.containsKey(ticker)) {
               tlhsecuritydata = tlhSecMap.get(holdingData.getTicker());
               for (int thlcounter = 0; thlcounter < tlhsecuritydata.getTlhdata().size(); thlcounter++) {
                  TLHData tlhdata = tlhsecuritydata.getSortedTLHDataByWeight().get(thlcounter);
                  String tlhTicker = tlhdata.getTlhticker();
                  if(ticker.equals(tlhTicker)){
                     holdingData.setTlhSellShortFlag(true);
                     reverseFlag = true;
                  }
               }
            }
         }
      }
      return reverseFlag;
   }

   public static void computeLongShortReturns(HoldingData hData, Map<String, ArrayList> tradesExecutedMap){

      ExecutedTradesData xTrades;
      String ticker = hData.getTicker();
      double longProceeds = 0.0;
      double shortProceeds = 0.0;
      double shortTimeShares = 0.0;
      double longTimeShares = 0.0;
      hData.setWashSaleFlag(true);
      hData.setOutRightSellFlag(false);

      if (tradesExecutedMap.containsKey(ticker)){
         //Loop is from last to first, since it is ordered
         for(int i = tradesExecutedMap.get(ticker).size()-1; i >= 0; i--){

            xTrades =  (ExecutedTradesData) tradesExecutedMap.get(ticker).get(i);
            int tdays = xTrades.getLastExecuted();

            if(tdays > 31 && i == 0)
               hData.setOutRightSellFlag(true);

            if (xTrades.getQty() > 0.0){

               //Set the wash sale rule if trade was a sell and greater than 31 days
               if(tdays > 31 && i == 0)
                  hData.setWashSaleFlag(false);

               if (tdays > 365) {
                  //Only care about positions currently held
                  longProceeds = longProceeds + StrictMath.abs(xTrades.getTradePrice()*xTrades.getQty());
                  longTimeShares = longTimeShares + StrictMath.abs(xTrades.getQty());
               }
               if(tdays > 31 && tdays < 365){
                  shortProceeds = shortProceeds + StrictMath.abs(xTrades.getTradePrice()*xTrades.getQty());
                  shortTimeShares = shortTimeShares + StrictMath.abs(xTrades.getQty());
               }
            }
            //Subtract sells from the total long first and then short positions
            else {
               if (StrictMath.abs(xTrades.getQty()) < longTimeShares){
                  longProceeds = longProceeds - StrictMath.abs(xTrades.getTradePrice()*xTrades.getQty());
                  longTimeShares = longTimeShares - StrictMath.abs(xTrades.getQty());
               }
               else {
                     if( longTimeShares > 0.0 || shortTimeShares > 0.0){
                     Double shares = StrictMath.abs(xTrades.getQty()) - longTimeShares;
                     Double proceeds = StrictMath.abs(xTrades.getTradePrice()*xTrades.getQty()) - longProceeds;
                     longTimeShares = 0.0;
                     longProceeds = 0.0;
                     shortTimeShares = shortTimeShares - StrictMath.abs(xTrades.getQty());
                     shortProceeds = shortProceeds - StrictMath.abs(xTrades.getTradePrice()*xTrades.getQty());
                  }
               }
            }
         }
         //save the data for ticker
         hData.setLongProceeds(longProceeds);
         hData.setLongTimeShares(longTimeShares);
         hData.setShortProceeds(shortProceeds);
         hData.setShortTimeShares(shortTimeShares);
      }
   }


   public Boolean checkOtherAssets(CurrentHolding cHoldings, AssetClass newAssets, Map<String, Asset> asset, ArrayList<String> orderedAssets){

      Map<String, HoldingData> hMap = cHoldings.getHoldingDataMap();
      HoldingData holdingData = null;
      holdingData = hMap.get("Cash");

      //compare cash level beetween new and old
      Boolean assetFlag = false;
      for (int i = 0; i < orderedAssets.size(); i++){

         //get old cash percent
         String assetname =  orderedAssets.get(i);
         Asset oldAsset = asset.get(assetname);

         // compare asset weights
         Double assetWeightDiff = newAssets.getAsset(assetname).getUserweight() - oldAsset.getUserweight();
         Double assetAmount = assetWeightDiff*  cHoldings.getTotalmoney();

         if (StrictMath.abs(assetWeightDiff) > InvConst.ASSET_TRIGGER) {
            assetFlag = true;
            return assetFlag;
         }
      }
      return assetFlag;
   }

   public Boolean checkCashHolding(CurrentHolding cHoldings, AssetClass aamc, Map<String, Asset> asset){

      Map<String, HoldingData> hMap = cHoldings.getHoldingDataMap();
      HoldingData holdingData = null;
      holdingData = hMap.get("Cash");

      //compare cash level beetween new and old
      Boolean cashFlag = false;

      //get old cash percent
      Asset oldAsset = asset.get("Cash");

      Double cashDiff = aamc.getAsset("Cash").getUserweight() - oldAsset.getUserweight();
      //is the cash available more than 3 months of fees?
      Double cashAmount = cashDiff*  cHoldings.getTotalmoney();
      if (StrictMath.abs(cashDiff) > InvConst.CASH_TRIGGER && cashAmount > cHoldings.getTotalmoney()*(InvConst.MNGT_FEES/4.0)) {
         cashFlag = true;
      }

      return cashFlag;
   }

   public ArrayList<TradeData> createTradeObjectTaxAccounts(Portfolio[] nHoldings, CurrentHolding cHoldings) throws Exception
   {


      //Print Current Holdings New Holdings
      //createHoldingsFile (cHoldings, nHoldings);

      //Two situations, First take any tax loss, raise cash
          //If Tax Loss and noBasisRiskFlag then sell whole position and buy substitute
      //Sell other positions only so that  CapGainsLong <= lossLong and CapGainsShort <= LossShort

      //Buy only amount of cash available. Buy the largest position first

      ArrayList <TradeData> tradeDataList = new ArrayList<TradeData>();

      TradeData tdata = new TradeData();

      Integer year = 0;

      //Set tax loss flag for trades
      setTaxLossFlags(nHoldings,  cHoldings, year);

      //Sell what is outright sells
      ArrayList <TradeData> outrightSellTradeList = outRightSells (nHoldings, cHoldings, year);

      //Sell any other rebalance (not TLH Sells) related gains if there are any off setting loss cary
      ArrayList <TradeData> otherSellsTradeList = otherSells (nHoldings,cHoldings, year);

      //Sell to take a tax loss
      ArrayList <TradeData> tlhSellsTradeList = tlhSells (cHoldings);

      //Check cash and then only buy from the largest buys
      //TLH Buys
      ArrayList <TradeData> tlhBuysTradeList = tlhBuys (nHoldings, cHoldings, year);

      //Buy the largest buys which are new tickers and not in current portfolio
      ArrayList <TradeData> otherBuysTradeList = otherBuys (nHoldings,cHoldings, year);

      tradeDataList.addAll(outrightSellTradeList);
      tradeDataList.addAll(otherSellsTradeList);
      tradeDataList.addAll(tlhSellsTradeList);
      tradeDataList.addAll(tlhBuysTradeList);
      tradeDataList.addAll(otherBuysTradeList);
      return tradeDataList;
   }

   public ArrayList <TradeData> otherBuys (Portfolio[] nHoldings, CurrentHolding cHoldings,Integer year)
   {
      Map<String, HoldingData> hMap = cHoldings.getHoldingDataMap();
      ArrayList <TradeData> tradeDataList = new ArrayList <TradeData>();

      PortfolioSecurityData portfolioSecurityData = null;
      String ticker;

      //Check if there is a brand new position to add
      for (int i = 0; i < nHoldings[year].getPortfolio().size(); i++)
      {
         portfolioSecurityData =  nHoldings[year].getPortfolio().get(i);

         ticker = portfolioSecurityData.getTicker();

         //Check current cash
         if(cHoldings.getCashAvailable()> nHoldings[year].getCashMoney()){

            //Position which is new and not in current holdings
            if(!hMap.containsKey(ticker)) {

               Double tShares = portfolioSecurityData.getShares();
               Double tPrice =  portfolioSecurityData.getDailyprice();
               Double cash = cHoldings.getCashAvailable()- nHoldings[year].getCashMoney();
               Double tMoney = 0.0;

               if (cash < tMoney  ){
                  tShares = (double) StrictMath.round(tMoney/portfolioSecurityData.getDailyprice() - 0.5);
               }

               tMoney = tShares * tPrice;
               cHoldings.setCashAvailable(cHoldings.getCashAvailable()- tMoney);

               TradeData tdata = new TradeData(advisor,
                                               cHoldings.getClientAccountID(),
                                               cHoldings.getAcctnum(),
                                               portfolioSecurityData.getAssetclass(),
                                               portfolioSecurityData.getSubclass(),
                                               ticker,
                                               tShares,
                                               tPrice,
                                               tMoney,
                                               "",
                                               0.0,
                                               0.0,
                                               0.0,
                                               0.0,
                                               0.0,
                                               portfolioSecurityData.getTicker(),  // allocTicker
                                               portfolioSecurityData.getShares(), // allocQty
                                               portfolioSecurityData.getDailyprice(), // allocPrice
                                               portfolioSecurityData.getMoney(), // allocValue
                                               portfolioSecurityData.getTickerWeights(), // allocWeight
                                               "New Buy",   // tradeType
                                               "" // reason
               );
               tradeDataList.add( tdata);
            }
         }
      }

      HoldingData holdingData = null;
      //Check if there is cash for adding more shares or an existing position which is not TLH
      for (int i = 0; i < nHoldings[year].getPortfolio().size(); i++)
      {
         portfolioSecurityData =  nHoldings[year].getPortfolio().get(i);
         ticker = portfolioSecurityData.getTicker();

         //Check current cash
         if(cHoldings.getCashAvailable()> nHoldings[year].getCashMoney()){

            if(hMap.containsKey(ticker)) {
               holdingData = hMap.get(ticker);

               if(!holdingData.isTlhSellLongFlag() && !holdingData.isTlhSellShortFlag())
               {
                  //Rebalance trade only buy more
                  Double tShares = portfolioSecurityData.getShares() - holdingData.getQty();
                  Double tPrice = holdingData.getMarkPrice();
                  Double tMoney = 0.0;

                  //Buy
                  if (tShares > 0.0)
                  {

                     double cash = cHoldings.getCashAvailable() - nHoldings[year].getCashMoney();
                     tMoney = tShares * holdingData.getMarkPrice();

                     if (cash < tMoney)
                     {
                        tMoney = cash;
                        tShares = (double) StrictMath.round((tMoney / tPrice) - 0.5);
                     }

                     cHoldings.setCashAvailable(cHoldings.getCashAvailable() - tMoney);
                     holdingData.setProcessed(true);

                     TradeData tdata = new TradeData(advisor,
                                                     cHoldings.getClientAccountID(),
                                                     cHoldings.getAcctnum(),
                                                     portfolioSecurityData.getAssetclass(),
                                                     portfolioSecurityData.getSubclass(),
                                                     ticker,
                                                     tShares,
                                                     tPrice,
                                                     tMoney,
                                                     holdingData.getTicker(), // Ticker
                                                     holdingData.getQty(),    // Qty
                                                     holdingData.getMarkPrice(), // Price
                                                     holdingData.getPositionValue(), // Money
                                                     holdingData.getWeight(),  // Weight
                                                     holdingData.getCostBasisMoney(), // CostBasis
                                                     portfolioSecurityData.getTicker(),  // allocTicker
                                                     portfolioSecurityData.getShares(), // allocQty
                                                     portfolioSecurityData.getDailyprice(), // allocPrice
                                                     portfolioSecurityData.getMoney(), // allocValue
                                                     portfolioSecurityData.getTickerWeights(), // allocWeight
                                                     "Rebal Buy",   // tradeType
                                                     "" // reason
                     );

                     tradeDataList.add(tdata);

                  }
               }
            }
         }
      }

      return tradeDataList;
   }

   public ArrayList<TradeData> outRightSells (Portfolio[] nHoldings, CurrentHolding cHoldings, Integer year
   ){
      ArrayList<TradeData> tradeDataList = new ArrayList<TradeData>();
      HoldingData holdingData = null;
      String ticker;
      PortfolioSecurityData portfolioSecurityData = null;
      Map<String, PortfolioSecurityData> portMap = nHoldings[year].getPortfolioMap();
      //TradeData tdata = new TradeData();

      for (int i = 0; i < cHoldings.getHoldingList().size(); i++){

         // Sell all the shares not in new portfolio
         holdingData = cHoldings.getHoldingList().get(i);
         ticker = holdingData.getTicker();

         if (!holdingData.getTicker().equalsIgnoreCase("cash")){

            if(!portMap.containsKey(ticker)){

               if (holdingData.getOutRightSellFlag().equals(true)){

                  double tShares = -1.0 * holdingData.getQty();
                  double tPrice = holdingData.getMarkPrice();
                  double tmoney = (tShares* tPrice);

                  Double gainLoss = 0.0;
                  Double tradeCostShort = holdingData.getShortProceeds()/holdingData.getShortTimeShares();
                  Double tradeCost = holdingData.getCostBasisPrice();
                  Double costBasisMon = holdingData.getCostBasisMoney();

                  gainLoss = holdingData.getMarkPrice() *holdingData.getLongTimeShares() - holdingData.getLongProceeds();
                  cHoldings.setLongGains(cHoldings.getLongGains() + gainLoss);

                  tradeCost = (holdingData.getShortProceeds()/(tShares - holdingData.getLongTimeShares()));
                  costBasisMon = tradeCost * tShares;
                  gainLoss = tPrice * (tShares - holdingData.getLongTimeShares()) - costBasisMon;
                  cHoldings.setShortGains(cHoldings.getShortGains() + gainLoss);

                  cHoldings.setCashAvailable(cHoldings.getCashAvailable()+ StrictMath.abs(tmoney));
                  TradeData tdata = new TradeData(advisor,
                                                  cHoldings.getClientAccountID(),
                                                  cHoldings.getAcctnum(),
                                                  holdingData.getAssetclass(),
                                                  holdingData.getSubclass(),
                                                  holdingData.getTicker(),
                                                  tShares,
                                                  tPrice,
                                                  tmoney,
                                                  holdingData.getTicker(),
                                                  holdingData.getQty(),
                                                  tPrice,
                                                  holdingData.getPositionValue(),
                                                  0.0,
                                                  holdingData.getCostBasisMoney(),
                                                  "",  // allocTicker
                                                  0.0, // allocQty
                                                  0.0, // allocPrice
                                                  0.0, // allocValue
                                                  0.0, // allocWeight
                                                  "Total Sell",   // tradeType
                                                  "" // reason
                                                  );

                  tradeDataList.add( tdata);
               }
            }
         }
      }

      return tradeDataList;
   }


   public ArrayList<TradeData> otherSells ( Portfolio[] nHoldings, CurrentHolding cHoldings,
                              Integer year){

      HoldingData holdingData = null;
      String ticker;
      PortfolioSecurityData portfolioSecurityData = null;
      Map<String, HoldingData> hMap =  cHoldings.getHoldingDataMap();
      ArrayList<TradeData> tradeDataList = new ArrayList<TradeData>();
      for (int i = 0; i < nHoldings[year].getPortfolio().size(); i++)
      {
         portfolioSecurityData =  nHoldings[year].getPortfolio().get(i);
         ticker = portfolioSecurityData.getTicker();
         TradeData tdata = new TradeData();

         if(hMap.containsKey(ticker)){
            holdingData = hMap.get(ticker);

            if(!holdingData.getTicker().equalsIgnoreCase("cash")){

               double tShares = portfolioSecurityData.getShares() - holdingData.getQty();
               Double tPrice = holdingData.getMarkPrice();
               Double tMoney = 0.0;
               String tlhType = "Rebal Sell";

               //Checking for sells only
               if (tShares < 0.0 ){

                  if (holdingData.isTlhSellLongFlag() || holdingData.isTlhSellShortFlag()){
                     if(holdingData.getLongTimeShares() > StrictMath.abs(tShares)) {
                        holdingData.setLongTimeShares(holdingData.getLongTimeShares() + tShares);


                     }
                     else{
                        double tmpShrs = holdingData.getLongTimeShares() + tShares;
                        holdingData.setShortTimeShares(holdingData.getShortTimeShares() + tmpShrs);
                        holdingData.setLongTimeShares(0.0);

                     }
                  }


                  tMoney = tShares * tPrice;

                  //Double gainLoss = 0.0;
                  //Double tradeCostShort = holdingData.getShortProceeds()/holdingData.getShortTimeShares();
                  //Double tradeCost = holdingData.getCostBasisPrice();
                  //Double costBasisMon = holdingData.getCostBasisMoney();

                  /*if (StrictMath.abs(tShares) > holdingData.getLongTimeShares()){
                     gainLoss = holdingData.getMarkPrice() *holdingData.getLongTimeShares() - holdingData.getLongProceeds();
                     cHoldings.setLongGains(cHoldings.getLongGains() + gainLoss);

                     tradeCost = (holdingData.getShortProceeds()/(tShares - holdingData.getLongTimeShares()));
                     costBasisMon = tradeCost * tShares;
                     gainLoss = holdingData.getMarkPrice() * (tShares - holdingData.getLongTimeShares()) - costBasisMon;
                     cHoldings.setShortGains(cHoldings.getShortGains() + gainLoss);
                  }
                  else {

                     gainLoss = holdingData.getMarkPrice() *holdingData.getLongTimeShares() - holdingData.getLongProceeds();
                     cHoldings.setLongGains(cHoldings.getLongGains() + gainLoss);
                  }
*/
                  cHoldings.setCashAvailable(cHoldings.getCashAvailable()+ StrictMath.abs(tMoney));

                  tdata = new TradeData(advisor,
                                        cHoldings.getClientAccountID(),
                                         cHoldings.getAcctnum(),
                                         holdingData.getAssetclass(),
                                         holdingData.getSubclass(),
                                         holdingData.getTicker(),
                                         tShares,
                                         tPrice,
                                         tMoney,
                                         holdingData.getTicker(),
                                         holdingData.getQty(),
                                         tPrice,
                                         holdingData.getPositionValue(),
                                         holdingData.getWeight(),
                                         holdingData.getCostBasisMoney(),
                                         portfolioSecurityData.getTicker(),  // allocTicker
                                         portfolioSecurityData.getShares(), // allocQty
                                         portfolioSecurityData.getDailyprice(), // allocPrice
                                         portfolioSecurityData.getMoney(), // allocValue
                                         portfolioSecurityData.getTickerWeights(), // allocWeight
                                         tlhType,   // tradeType
                                         "" // reason
         );

                  tradeDataList.add( tdata);

               }
            }
         }
      }
      return tradeDataList;
   }

   public ArrayList<TradeData> tlhSells ( CurrentHolding cHoldings){

      Map<String, HoldingData> hMap = cHoldings.getHoldingDataMap();
      ArrayList<TradeData> tradeDataList = new ArrayList<TradeData>();
      for (int i = 0; i < cHoldings.getHoldingList().size(); i++){

         // Sell all the shares, marked by no basis risk and false washSale flag
         HoldingData holdingData = null;
         holdingData = cHoldings.getHoldingList().get(i);
         Double tShares = 0.0;
         Double tPrice = 0.0;
         Double tMoney = 0.0;
         String tlhType = "thlSells";

         if(!holdingData.getTicker().equalsIgnoreCase("cash")){
            if (holdingData.isTlhSellLongFlag()){
               //Only sell the longTimeShares
               tShares = -1.0* holdingData.getLongTimeShares();
               tlhType = "Long Loss Sell";
            }
            else if (holdingData.isTlhSellShortFlag()) {
               tShares = -1.0* holdingData.getShortTimeShares();
               tlhType = "Short Loss Sell";
            }

            if (tShares != 0.0)  {
               tPrice = holdingData.getMarkPrice();
               tMoney = tShares * tPrice;
               //Sell for tax loss
               holdingData.setLongProceeds( tMoney);
               cHoldings.setCashAvailable(cHoldings.getCashAvailable() + StrictMath.abs(tMoney));
               TradeData tdata = new TradeData(advisor,
                                               cHoldings.getClientAccountID(),
                                                 cHoldings.getAcctnum(),
                                                 holdingData.getAssetclass(),
                                                 holdingData.getSubclass(),
                                                 holdingData.getTicker(),
                                                 tShares,
                                                 tPrice,
                                                 tMoney,
                                                 holdingData.getTicker(),
                                                 holdingData.getQty(),
                                                 tPrice,
                                                 holdingData.getPositionValue(),
                                                 holdingData.getWeight(),
                                                 holdingData.getCostBasisMoney(),
                                                 holdingData.getTicker(),  // allocTicker
                                                 holdingData.getQty() + tShares, // allocQty
                                                 tPrice, // allocPrice
                                                 tPrice * (holdingData.getQty() + tShares), // allocValue
                                                 tPrice * (holdingData.getQty() + tShares)/cHoldings.getTotalvalue(), // allocWeight
                                                 tlhType,   // tradeType
                                                 "" // reason
               );
               tradeDataList.add( tdata);
            }
         }
      }
   return tradeDataList ;
   }

   public ArrayList<TradeData> tlhBuys ( Portfolio[] nHoldings, CurrentHolding cHoldings, Integer year){

      Map<String, HoldingData> hMap = cHoldings.getHoldingDataMap();
      ArrayList<TradeData> tradeDataList = new ArrayList<TradeData>();
      HoldingData holdingData = null;
      PortfolioSecurityData portfolioSecurityData = null;
      hMap = cHoldings.getHoldingDataMap();
      SecurityTLHData tlhsecuritydata = null;

      String ticker;

      for (int i = 0; i < nHoldings[year].getPortfolio().size(); i++)
      {
         portfolioSecurityData =  nHoldings[year].getPortfolio().get(i);
         ticker = portfolioSecurityData.getTicker();
         TradeData tdata = new TradeData();

         //Check current cash
         if(cHoldings.getCashAvailable()> nHoldings[year].getCashMoney()){

            if(hMap.containsKey(ticker)) {
               holdingData = hMap.get(ticker);
               //Rebalance trade only buy more
               if(!holdingData.getTicker().equalsIgnoreCase("cash")){
                  if (holdingData.isTlhSellLongFlag() || holdingData.isTlhSellShortFlag()){

                     Double hShares = 0.0;
                     Double hMoney = 0.0;
                     Double hPrice = 0.0;
                     String tlhType = "tlhBuys";

                     if(holdingData.isTlhSellLongFlag())  {
                        //Get new shares for the substitute tax loss position
                        hShares = holdingData.getLongTimeShares();
                     }

                     if(holdingData.isTlhSellShortFlag()) {
                        hShares = holdingData.getShortTimeShares();
                     }

                     hPrice = holdingData.getMarkPrice();
                     hMoney = hShares * hPrice;

                     //Buy
                     if (hShares > 0.0){


                        //tmoney = portfolioSecurityData.getMoney();
                        Double availMoney = hMoney;

                        //Replace this later,  Added this section for multiple Tax Loss Harvsting
                        if(tlhSecMap != null){
                           if(tlhSecMap.containsKey(holdingData.getTicker())) {
                              tlhsecuritydata = tlhSecMap.get(holdingData.getTicker());
                              for (int thlcounter = 0; thlcounter < tlhsecuritydata.getTlhdata().size(); thlcounter++) {
                                 TLHData tlhdata = tlhsecuritydata.getSortedTLHDataByWeight().get(thlcounter);
                                 Double tlhweight = tlhdata.getWeight();
                                 String tlhTicker = tlhdata.getTlhticker();
                                 Double tlhPrice =  tlhdata.getPrice();
                                 Double allocMoney = hMoney *tlhweight;
                                 Double cash = cHoldings.getCashAvailable()- nHoldings[year].getCashMoney();

                                 if (holdingData.isTlhSellLongFlag())
                                    tlhType = "Long Loss Buy";
                                 if (holdingData.isTlhSellShortFlag())
                                    tlhType = "Short Loss Buy";

                                 if(allocMoney > availMoney)
                                    allocMoney = availMoney;

                                 if (cash < allocMoney){
                                    allocMoney = cash;
                                 }

                                 Double tlhShares = 0.0;
                                 tlhShares = (double) StrictMath.round((StrictMath.abs(allocMoney)/tlhPrice)-0.5);

                                 double tlhMoney = tlhShares * tlhPrice;

                                 //tlhdata.setTLHFlag(true);
                                 tdata = new TradeData(advisor,
                                                       cHoldings.getClientAccountID(),
                                                       cHoldings.getAcctnum(),
                                                       tlhdata.getAssetclass(),
                                                       tlhdata.getSubclass(),
                                                       tlhTicker,
                                                       tlhShares,
                                                       tlhPrice,
                                                       tlhMoney,
                                                       holdingData.getTicker(),
                                                       holdingData.getQty(),
                                                       hPrice,
                                                       holdingData.getPositionValue(),
                                                       holdingData.getWeight(),
                                                       holdingData.getCostBasisMoney(),
                                                       tlhTicker,  // allocTicker
                                                       tlhShares, // allocQty
                                                       tlhPrice, // allocPrice
                                                       tlhMoney, // allocValue
                                                       tlhMoney/nHoldings[year].getTotalMoney(), // allocWeight
                                                       tlhType,   // tradeType
                                                       "" // reason
                                 );
                                 tradeDataList.add( tdata);

                                 cHoldings.setCashAvailable(cHoldings.getCashAvailable()- tlhMoney);
                                 availMoney = availMoney - tlhMoney;

                              }
                           }
                        }
                     }
                  }
               }
            }
         }
      }
      return tradeDataList ;
   }

   public ArrayList<TradeData> createTradeObjectNonTaxAccounts(Portfolio[] nHoldings,
                                                               AssetClass[] aamc,
                                                               ProfileData profileData,
                                                               CurrentHolding cHoldings) throws Exception
   {

      ArrayList <TradeData> tradeDataList = new ArrayList<TradeData>();
      Map<String, HoldingData> hMap = cHoldings.getHoldingDataMap();
      int year = 0;
      HoldingData holdingData = null;
      int counter = 0;

      //* First deal with all those that match are new trades.
      for (int i = 0; i < nHoldings[year].getPortfolio().size(); i++)
      {

         PortfolioSecurityData portfolioSecurityData =  nHoldings[year].getPortfolio().get(i);

         //newTickerList[i] = newList[year].getTicker();
         //secWeight[i] = (newList[year].getMoney() / totalMoney);

         String ticker = portfolioSecurityData.getTicker();
         String hTicker = "";
         Double tShares = 0.0, tMoney = 0.0, tPrice = 0.0;
         Double hShares = 0.0, hMoney = 0.0, hPrice = 0.0, hWeight = 0.0, hCostBasis = 0.0;
         if(hMap.containsKey(ticker)){
            //Rebalance trade - buy or sell
            holdingData = hMap.get(ticker);
            hTicker = holdingData.getTicker();
            hShares = holdingData.getQty();
            hPrice = holdingData.getMarkPrice();
            hMoney = holdingData.getPositionValue();
            hWeight = holdingData.getWeight();
            hCostBasis = holdingData.getCostBasisMoney();

            tShares = portfolioSecurityData.getShares()- holdingData.getQty();
            tMoney = tShares* holdingData.getMarkPrice();
            tPrice = holdingData.getMarkPrice();
            hMap.remove(ticker);
            holdingData.setProcessed(true);
         }
         else{
            holdingData = null;
            //Totally new Buy trade
            tShares = portfolioSecurityData.getShares();
            tPrice =  portfolioSecurityData.getDailyprice();
         }

         tMoney = tShares* tPrice;
         TradeData tdata = new TradeData(advisor,
                                         cHoldings.getClientAccountID(),
                                         cHoldings.getAcctnum(),
                                         portfolioSecurityData.getAssetclass(),
                                         portfolioSecurityData.getSubclass(),
                                         ticker,
                                         tShares,
                                         tPrice,
                                         tMoney,
                                         hTicker,
                                         hShares,
                                         hPrice,
                                         hMoney,
                                         hWeight, // Holding Weight
                                         hCostBasis,
                                         portfolioSecurityData.getTicker(),  // allocTicker
                                         portfolioSecurityData.getShares(), // allocQty
                                         portfolioSecurityData.getDailyprice(), // allocPrice
                                         portfolioSecurityData.getMoney(), // allocValue
                                         portfolioSecurityData.getTickerWeights(), // allocWeight
                                         "NonTax",   // tradeType
                                         "" // reason
         );
         tradeDataList.add(counter,tdata);
         counter++;
      }

      // Process remaining Holding Position,  We have to sell all of them.
      // NOTE:  Previous code removed all matched keys.  So, we are left with smaller list.
      for(String ticker : hMap.keySet())
      {
         // Sell all the shares, not in the new portfolio
         holdingData = hMap.get(ticker);
         String hTicker = "";
         Double tShares = 0.0, tMoney = 0.0, tPrice = 0.0;
         Double hShares = 0.0, hMoney = 0.0, hPrice = 0.0, hWeight = 0.0, hCostBasis = 0.0;
         if (!holdingData.isProcessed()){
            hTicker = holdingData.getTicker();
            hShares = holdingData.getQty();
            hPrice = holdingData.getMarkPrice();
            hMoney = holdingData.getPositionValue();
            hWeight = holdingData.getWeight();
            hCostBasis = holdingData.getCostBasisMoney();

            tShares = -1.0 * hShares;
            tMoney = -1*hMoney;
            tPrice = hPrice;
            holdingData.setProcessed(true);

            TradeData tdata = new TradeData(advisor,
                                            cHoldings.getClientAccountID(),
                                            cHoldings.getAcctnum(),
                                            holdingData.getAssetclass(),
                                            holdingData.getSubclass(),
                                            ticker,
                                            tShares,
                                            tPrice,
                                            tMoney,
                                            hTicker,
                                            hShares,
                                            hPrice,
                                            hMoney,
                                            hWeight, // Holding Weight
                                            hCostBasis,
                                            "",  // allocTicker
                                            0.0, // allocQty
                                            0.0, // allocPrice
                                            0.0, // allocValue
                                            0.0, // allocWeight
                                            "NonTax",   // tradeType
                                            "" // reason
            );
            tradeDataList.add(counter,tdata);
            counter++;
         }
      }

      return tradeDataList;
   }
}
