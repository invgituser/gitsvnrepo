package com.invessence.dao.advisor;

import java.io.Serializable;
import java.util.*;
import javax.faces.bean.*;
import javax.sql.DataSource;

import com.invessence.converter.SQLData;
import com.invessence.data.DataPortfolio;
import com.invessence.data.advisor.AdvisorData;
import com.invmodel.Const.InvConst;
import com.invmodel.asset.data.*;
import com.invmodel.portfolio.data.*;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;

@ManagedBean(name = "advisorListDataDAO")
@ApplicationScoped
public class AdvisorListDataDAO extends SimpleJdbcDaoSupport implements Serializable
{
   SQLData convert = new SQLData();

   public List<AdvisorData> getListOfAccounts(Long acctnum, String filter) {
      DataSource ds = getDataSource();
      AdvisorListSP sp = new AdvisorListSP(ds, "sel_AdvisorAcctList",0);
      List<AdvisorData> listProfiles = new ArrayList<AdvisorData>();
      Map outMap = sp.collectProfileData(acctnum, filter);
      String action;
      try {
         if (outMap != null)
         {
            ArrayList<Map<String, Object>> rows = (ArrayList<Map<String, Object>>) outMap.get("#result-set-1");
            int i = 0;
            for (Map<String, Object> map : rows)
            {
               Map rs = (Map) rows.get(i);
               AdvisorData data = new AdvisorData();

               data.setAcctnum(convert.getLongData(rs.get("acctnum")));

               data.setAdvisor(convert.getStrData(rs.get("advisor")));
               data.setTheme(convert.getStrData(rs.get("theme")));
               data.setClientLogonID(convert.getLongData(rs.get("logonid")));
               data.setClientLastname(convert.getStrData(rs.get("lastname")));
               data.setClientFirstName(convert.getStrData(rs.get("firstname")));
               data.setName(convert.getStrData(rs.get("firstname")) + " " + convert.getStrData(rs.get("lastname")));
               data.setClientAccountID(convert.getStrData(rs.get("IB_acctnum")));

               action = (convert.getStrData(rs.get("acctstatus")));
               data.setAcctstatus(action);
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
               data.setRiskIndex(convert.getIntData(rs.get("riskIndex")));
               data.setInitialInvestment(convert.getIntData(rs.get("initialInvestment")));
               data.setKeepLiquid(convert.getIntData(rs.get("keepLiquid")));
               data.setActualInvestment(convert.getIntData(rs.get("actualCapital")));
               data.setRecurringInvestment(convert.getIntData(rs.get("recurringInvestment")));
               data.setObjective(convert.getIntData(rs.get("longTermGoal")));
               data.setStayInvested(convert.getIntData(rs.get("stayInvested")));
               data.setStock(convert.getDoubleData(rs.get("stock")));
               data.setAccrual(convert.getDoubleData(rs.get("accrual")));
               data.setDateOpened(convert.getStrData(rs.get("created")));
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
               data.setClientLogonID(convert.getLongData(rs.get("logonid")));
               data.setClientLastname(convert.getStrData(rs.get("lastname")));
               data.setClientFirstName(convert.getStrData(rs.get("firstname")));
               data.setName(convert.getStrData(rs.get("firstname")) + " " + convert.getStrData(rs.get("lastname")));
               data.setClientEmail(convert.getStrData(rs.get("email")));
               data.setClientAccountID(convert.getStrData(rs.get("IB_acctnum")));

               action = (convert.getStrData(rs.get("acctstatus")));
               data.setAcctstatus(action);
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
               data.setConvertRiskIndex(convert.getIntData(rs.get("riskIndex")));
               data.setInitialInvestment(convert.getIntData(rs.get("initialInvestment")));
               data.setKeepLiquid(convert.getIntData(rs.get("keepLiquid")));
               data.setActualInvestment(convert.getIntData(rs.get("actualCapital")));
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

   public ArrayList<String> getBasket(String advisor) {
      DataSource ds = getDataSource();
      AdvisorListSP sp = new AdvisorListSP(ds, "sel_AdvisorBaskets",2);
      ArrayList<String> listBasket = new ArrayList<String>();
      Map outMap = sp.collectBasket(advisor);
      try {
         if (outMap != null)
         {
            ArrayList<Map<String, Object>> rows = (ArrayList<Map<String, Object>>) outMap.get("#result-set-1");
            int i = 0;
            for (Map<String, Object> map : rows)
            {
               Map rs = (Map) rows.get(i);
               listBasket.add(convert.getStrData(rs.get("theme")));
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
                                        savedweight,
                                        0.0,
                                        "",
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
            for (Map<String, Object> map : rows)
            {
               Map rs = (Map) rows.get(i);
               PortfolioSubclass dp = new PortfolioSubclass();
               dp.setParentclass(convert.getStrData(rs.get("assetclass")));
               dp.setSubasset(convert.getStrData(rs.get("subclass")));
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

}