package com.invessence.dao.advisor;

import java.util.*;
import javax.faces.bean.*;
import javax.sql.DataSource;

import com.invessence.converter.SQLData;
import com.invessence.data.advisor.AdvisorData;
import com.invmodel.asset.data.*;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;

@ManagedBean(name = "advisorListDataDAO")
@ApplicationScoped
public class AdvisorListDataDAO extends SimpleJdbcDaoSupport
{
   SQLData convert = new SQLData();

   public List<AdvisorData> getAccountData(Long acctnum, String filter) {
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

   public List<String> getBasket(String advisor) {
      DataSource ds = getDataSource();
      AdvisorListSP sp = new AdvisorListSP(ds, "sel_AdvisorBaskets",1);
      List<String> listBasket = new ArrayList<String>();
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
            }

         }
         return listBasket;
      }
      catch (Exception ex) {
         ex.printStackTrace();
      }
      return null;
   }

   public void getAllocation(AdvisorData adata) {
      DataSource ds = getDataSource();
      AdvisorListSP sp = new AdvisorListSP(ds, "sel_Allocation",2);
      if (adata.getAssetData() == null) {
         adata.setAssetData(new AssetClass[1]);
      }
      Map outMap = sp.collectAllocation(adata.getAcctnum());
      String theme = "Balance";
      String asset;
      Double weight;
      try {
         if (outMap != null)
         {
            ArrayList<Map<String, Object>> rows = (ArrayList<Map<String, Object>>) outMap.get("#result-set-1");
            int i = 0;
            for (Map<String, Object> map : rows)
            {
               Map rs = (Map) rows.get(i);
               asset = convert.getStrData(rs.get("assetclass"));
               weight = convert.getDoubleData(rs.get("weight"));
               weight = weight/100.00;
               theme = convert.getStrData(rs.get("theme"));
               adata.getAssetData()[0].setAssetWeight(asset,weight);
            }

         }
         adata.setTheme(theme);
      }
      catch (Exception ex) {
         ex.printStackTrace();
      }
   }

}