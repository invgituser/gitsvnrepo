package com.invessence.web.dao.advisor;

import java.io.Serializable;
import java.util.*;
import javax.faces.bean.*;
import javax.sql.DataSource;

import com.invessence.converter.SQLData;
import com.invessence.web.data.advisor.*;
import com.invessence.web.data.common.AccountData;
import com.invmodel.asset.data.*;
import com.invmodel.portfolio.data.*;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

@ManagedBean(name = "advisorListDataDAO")
@ApplicationScoped
public class AdvisorListDataDAO extends JdbcDaoSupport implements Serializable
{
   SQLData convert = new SQLData();

   public ArrayList<AccountData> getListOfAccounts(Long logonid, Long acctnum) {
      DataSource ds = getDataSource();
      AdvisorListSP sp = new AdvisorListSP(ds, "sel_ClientProfileData2",0);
      ArrayList<AccountData> listProfiles = new ArrayList<AccountData>();
      Map outMap = sp.collectProfileData(logonid, acctnum);
      String action;
      try {
         if (outMap != null)
         {
            ArrayList<Map<String, Object>> rows = (ArrayList<Map<String, Object>>) outMap.get("#result-set-1");
            int i = 0;
            for (Map<String, Object> map : rows)
            {
               Map rs = (Map) rows.get(i);
               AccountData data = new AccountData();

               data.setAcctnum(convert.getLongData(rs.get("acctnum")));
               data.setEmail(convert.getStrData(rs.get("email")));

               data.setAdvisor(convert.getStrData(rs.get("advisor")));
               data.setTheme(convert.getStrData(rs.get("theme")));
               data.setLogonid(convert.getLongData(rs.get("logonid")));
               data.setLastname(convert.getStrData(rs.get("lastname")));
               data.setFirstname(convert.getStrData(rs.get("firstname")));
               data.setClientAccountID(convert.getStrData(rs.get("ClientAccountID")));

               data.setAcctStatus(convert.getStrData(rs.get("acctstatus")));
               data.setTradePreference(convert.getStrData(rs.get("tradePreference")));
               data.setGoal(convert.getStrData(rs.get("goal")));
               data.setAccttype(convert.getStrData(rs.get("accttype")));
               data.setAge(convert.getIntData(rs.get("age")));
               data.setHorizon(convert.getIntData(rs.get("horizon")));
               data.setRiskIndex(convert.getDoubleData(rs.get("riskIndex")));
               data.setInitialInvestment(convert.getDoubleData(rs.get("initialInvestment")));
               data.setKeepLiquid(convert.getDoubleData(rs.get("keepLiquid")));
               data.setActualCapital(convert.getDoubleData(rs.get("actualCapital")));
               data.setRecurringInvestment(convert.getDoubleData(rs.get("recurringInvestment")));
               data.setLongTermGoal(convert.getIntData(rs.get("longTermGoal")));
               data.setStayInvested(convert.getIntData(rs.get("stayInvested")));
               data.setDependent(convert.getIntData(rs.get("dependent")));
               data.setDateOpened(convert.getStrData(rs.get("dateOpened")));
               data.setTotalIncomeAnnulized(convert.getDoubleData(rs.get("totalIncomeAnnulized")));
               data.setTotalExpenseAnnulized(convert.getDoubleData(rs.get("totalExpenseAnnulized")));

               data.setTotalAsset(convert.getDoubleData(rs.get("totalAsset")));
               data.setTotalDebt(convert.getDoubleData(rs.get("totalDebt")));
               data.setLiquidnetworth(convert.getDoubleData(rs.get("liquidnetworth")));
               data.setNetworth(convert.getDoubleData(rs.get("networth")));
               data.setCreated(convert.getStrData(rs.get("created")));
               listProfiles.add(i, data);
               i++;
            }
         }
         return listProfiles;
      }
      catch (Exception ex) {
            ex.printStackTrace();
      }
      return null;
   }

   public void getProfileData(AdvisorData data) {
      DataSource ds = getDataSource();
      AdvisorListSP sp = new AdvisorListSP(ds, "sel_AdvisorAcctProfile",1);
      Map outMap = sp.getProfileData(data.getAcctnum());
      String action;
      try {
         if (outMap != null)
         {
            ArrayList<Map<String, Object>> rows = (ArrayList<Map<String, Object>>) outMap.get("#result-set-1");
            int i = 0;
            for (Map<String, Object> map : rows)
            {
               Map rs = (Map) rows.get(i);
               data.setAcctnum(convert.getLongData(rs.get("acctnum")));

               data.setAdvisor(convert.getStrData(rs.get("advisor")));
               data.setTheme(convert.getStrData(rs.get("theme")));
               data.setGoal(convert.getStrData(rs.get("goal")));
               data.setClientLogonID(convert.getLongData(rs.get("logonid")));
               data.setClientLastname(convert.getStrData(rs.get("lastname")));
               data.setClientFirstName(convert.getStrData(rs.get("firstname")));
               data.setName(convert.getStrData(rs.get("firstname")) + " " + convert.getStrData(rs.get("lastname")));
               data.setClientEmail(convert.getStrData(rs.get("email")));
               data.setClientAccountID(convert.getStrData(rs.get("IB_acctnum")));

               action = (convert.getStrData(rs.get("acctstatus")));
               // data.setAcctstatus(action);
               if (action.equalsIgnoreCase("Pending")) {
                  data.setAction("Edit");
               }
               else {
                  data.setAction("View");
               }
               //data.setTradePreference(convert.getStrData(rs.get("tradePreference")));
               data.setAccountType(convert.getStrData(rs.get("accttype")));
               data.setAge(convert.getIntData(rs.get("age")));
               data.setHorizon(convert.getIntData(rs.get("horizon")));
               data.setRiskIndex(convert.getDoubleData(rs.get("riskIndex")));
               data.setInitialInvestment(convert.getIntData(rs.get("initialInvestment")));
               data.setKeepLiquid(convert.getIntData(rs.get("keepLiquid")));
               data.setActualInvestment(convert.getDoubleData(rs.get("actualCapital")));
               data.setRecurringInvestment(convert.getIntData(rs.get("recurringInvestment")));
               data.setObjective(convert.getIntData(rs.get("longTermGoal")));
               data.setStayInvested(convert.getIntData(rs.get("stayInvested")));
               data.setStock(convert.getDoubleData(rs.get("stock")));
               data.setAccrual(convert.getDoubleData(rs.get("accrual")));
               data.setDateOpened(convert.getStrData(rs.get("created")));
               i++;
               break;  // Only load the first account info.
            }
         }
      }
      catch (Exception ex) {
         ex.printStackTrace();
      }
   }

   public Map<String, String> getBasket(String advisor, String strategy) {
      DataSource ds = getDataSource();
      AdvisorListSP sp = new AdvisorListSP(ds, "sel_AdvisorBaskets",2);
      Map<String, String> listBasket= new LinkedHashMap<String, String>();
      Map outMap = sp.collectBasket(advisor, strategy);
      try {
         if (outMap != null)
         {
            ArrayList<Map<String, Object>> rows = (ArrayList<Map<String, Object>>) outMap.get("#result-set-1");
            int i = 0;
            for (Map<String, Object> map : rows)
            {
               Map rs = (Map) rows.get(i);
               String theme = convert.getStrData(rs.get("theme"));
               String basket = convert.getStrData(rs.get("basket"));
               listBasket.put(theme, basket);
               i++;
            }

         }
         return listBasket;
      }
      catch (Exception ex) {
         ex.printStackTrace();
      }
      return null;
   }

   public Map<String, Asset> getAllocation(AdvisorData adata) {
      DataSource ds = getDataSource();
      AdvisorListSP sp = new AdvisorListSP(ds, "sel_asset_alloc",3);
      Map outMap = sp.collectAllocation(adata.getAcctnum());
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
            if (modelflag)
               adata.setUserAssetOverride(true);
            return (uaseetdata);
         }
      }
      catch (Exception ex) {
         ex.printStackTrace();
      }
      return null;
   }

   public List<PortfolioSubclass> getExcludedSubclass(AdvisorData adata) {
      DataSource ds = getDataSource();
      AdvisorListSP sp = new AdvisorListSP(ds, "sel_ExcludedSubclass",4);
      if (adata.getPortfolioData() == null) {
         adata.setPortfolioData(new Portfolio[1]);
      }
      Map outMap = sp.collectSubClassExclusionList(adata.getAcctnum());
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

   public void reloadAdvisorDashBoard(AdvisorDashData addata) {
      if (addata == null)
         return;

      DataSource ds = getDataSource();
      AdvisorListSP sp = new AdvisorListSP(ds, "sel_advisorDashBoard",5);
      Map outMap = sp.collectDashBoardData(addata.getLogonid());
      try {
         if (outMap != null)
         {
            ArrayList<Map<String, Object>> rows;
            rows = (ArrayList<Map<String, Object>>) outMap.get("#result-set-1");
            if (rows != null)  {
               Integer i = 0;
               for (Map<String, Object> map : rows)
               {
                  Map rs = (Map) rows.get(i);
                  addata.addSalesInfo(convert.getStrData(rs.get("src")),
                                      convert.getIntData(rs.get("value")));
                  i++;
               }
            }

            rows = (ArrayList<Map<String, Object>>) outMap.get("#result-set-2");
            if (rows != null)  {
               Integer i = 0;
               for (Map<String, Object> map : rows)
               {
                  Map rs = (Map) rows.get(i);
                  addata.addSecurityInfo(convert.getStrData(rs.get("ticker")),
                                         convert.getDoubleData(rs.get("close_price")));
                  i++;
               }
            }

         }
      }
      catch (Exception ex) {
         ex.printStackTrace();
      }
   }

   public void collectAssetClass(Long logonid, AdvisorTheme advisorTheme) {
      if (advisorTheme == null)
         return;

      advisorTheme.getAssetdataMap().clear();
      DataSource ds = getDataSource();
      AdvisorListSP sp = new AdvisorListSP(ds, "advisor_sel_assetclass",6);
      Map outMap = sp.collectAssetClass(logonid);
      try {
         if (outMap != null)
         {
            ArrayList<Map<String, Object>> rows;
            rows = (ArrayList<Map<String, Object>>) outMap.get("#result-set-1");
            if (rows != null)  {
               Integer i = 0;
               for (Map<String, Object> map : rows)
               {
                  Map rs = (Map) rows.get(i);
                  advisorTheme.addAssetData(
                     // convert.getStrData(rs.get("advisor")),
                     convert.getStrData(rs.get("theme")), // theme
                     convert.getStrData(rs.get("themename")), // themename
                     convert.getStrData(rs.get("status")), // status
                     convert.getStrData(rs.get("assetclass")), // assetclass
                     convert.getStrData(rs.get("displayName")), // displayName
                     convert.getStrData(rs.get("ticker")), // indexTicker
                     convert.getIntData(rs.get("sortorder")), // sortorder
                     convert.getDoubleData(rs.get("lowerBound")),  //  lowerBound
                     convert.getDoubleData(rs.get("upperBound")),   //  upperbound
                     convert.getDoubleData(rs.get("endAllocation")), //  endAllocation
                     convert.getDoubleData(rs.get("riskAdjustment")),//  riskAdjustment
                     convert.getStrData(rs.get("color"))  // color
                  );
                  i++;
               }
            }
         }
      }
      catch (Exception ex) {
         ex.printStackTrace();
      }
   }

   public void collectPrimeAssetClass(Long logonid, AdvisorTheme advisorTheme) {
      if (advisorTheme == null)
         return;

      advisorTheme.getPrimeassetdataMap().clear();
      DataSource ds = getDataSource();
      AdvisorListSP sp = new AdvisorListSP(ds, "advisor_sel_primeassetclass",7);
      Map outMap = sp.collectPrimeAssetClass(logonid);
      try {
         if (outMap != null)
         {
            ArrayList<Map<String, Object>> rows;
            rows = (ArrayList<Map<String, Object>>) outMap.get("#result-set-1");
            if (rows != null)  {
               Integer i = 0;
               for (Map<String, Object> map : rows)
               {
                  Map rs = (Map) rows.get(i);
                  advisorTheme.addPrimeAssetData(
                     convert.getStrData(rs.get("theme")), // theme
                     convert.getStrData(rs.get("assetclass")), // assetclass
                     convert.getStrData(rs.get("primeassetclass")), // primeassetclass
                     convert.getStrData(rs.get("ticker")), // ticker
                     convert.getStrData(rs.get("status")), // active
                     convert.getIntData(rs.get("sortorder")), // sortorder
                     convert.getDoubleData(rs.get("lowerBound")),  //  lowerBound
                     convert.getDoubleData(rs.get("upperBound")),   //  upperbound
                     convert.getDoubleData(rs.get("expectedReturn")) //  expectedReturn
                  );
                  i++;
               }
            }
         }
      }
      catch (Exception ex) {
        ex.printStackTrace();
      }
   }
}