package com.invessence.dao.common;

import java.util.*;
import javax.faces.bean.*;
import javax.sql.DataSource;

import com.invessence.converter.SQLData;
import com.invessence.dao.admin.AdminSP;
import com.invessence.data.*;
import com.invessence.data.admin.*;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

@ManagedBean(name = "adminDAO")
@ApplicationScoped
public class AdminDAO extends JdbcDaoSupport
{
   SQLData convert = new SQLData();

   public void addDelIBPair(String action, Long acctnum, String ibstatus, String ibacctnum)
   {
      try
      {
         DataSource ds = getDataSource();
         AdminSP sp = new AdminSP(ds, "admin_sp_IB_Accounts_addmoddel",1);
         sp.addDelIBPair(action, acctnum, ibstatus, ibacctnum);
      }
      catch (Exception ex)
      {
         ex.printStackTrace();
      }
   }

   public void saveNextRebalDate(String nextRebaldate)
   {
      try
      {
         DataSource ds = getDataSource();
         AdminSP sp = new AdminSP(ds, "sp_invessence_switch_post", 2);
         sp.updateNextRebalDate("NEXT_REBALANCE", nextRebaldate);
      }
      catch (Exception ex)
      {
         ex.printStackTrace();
      }
   }

   public void reloadClientData()
   {
      try
      {
         DataSource ds = getDataSource();
         AdminSP sp = new AdminSP(ds, "sp_addClients2Trade", 99);
         sp.reloadClients4Trading();
      }
      catch (Exception ex)
      {
         ex.printStackTrace();
      }
   }

   public void updateExecutedTrades() {
      DataSource ds = getDataSource();
      AdminSP sp = new AdminSP(ds, "sp_updateExecutedTrades",99);
      sp.updateExecutedTrades();
   }


   public List<IBData> collectIBData(String filter)
   {
      DataSource ds = getDataSource();
      AdminSP sp = new AdminSP(ds, "admin_sel_collectIBData",100);
      List<IBData> ibData = new ArrayList<IBData>();

      Map outMap = sp.getIBData(filter);
      String lastname, firstname;
      if (outMap != null)
      {
         ArrayList<Map<String, Object>> rows = (ArrayList<Map<String, Object>>) outMap.get("#result-set-1");
         int i = 0;
         for (Map<String, Object> map : rows)
         {
            Map rs = (Map) rows.get(i);
            IBData data = new IBData();
            data.setClientAccountID(convert.getStrData(rs.get("IB_acctnum")));
            //data.setAccountAlias(convert.getStrData(rs.get("accountAlias")));
            //data.setCurrencyPrimary(convert.getStrData(rs.get("currencyPrimary")));
            lastname=convert.getStrData(rs.get("lastName"));
            firstname=convert.getStrData(rs.get("firstname"));
            if (lastname == null || lastname.length() == 0) {
               data.setName(convert.getStrData(rs.get("name")));
            }
            else {
               data.setName(lastname + "," + firstname);
            }

            // data.setAccountType(convert.getStrData(rs.get("accountStatus")));
            data.setCustomerType(convert.getStrData(rs.get("customerType")));
            data.setAccountCapabilities(convert.getStrData(rs.get("accountCapabilities")));
/*
            data.setTradingPermissions(convert.getStrData(rs.get("tradingPermissions")));
            data.setDateOpened(convert.getStrData(rs.get("dateOpened")));
            data.setDateClosed(convert.getStrData(rs.get("dateClosed")));
            data.setStreet(convert.getStrData(rs.get("street")));
            data.setStreet2(convert.getStrData(rs.get("street2")));
            data.setCity(convert.getStrData(rs.get("city")));
            data.setPostalCode(convert.getStrData(rs.get("postalCode")));
            data.setEmailPrimary(convert.getStrData(rs.get("emailPrimary")));
            data.setEmailSecondary(convert.getStrData(rs.get("emailSecondary")));
            data.setPhoneNo(convert.getStrData(rs.get("phoneNo")));
            data.setAltPhoneNo(convert.getStrData(rs.get("altPhoneNo")));
*/
            data.setState(convert.getStrData(rs.get("state")));
            data.setStartingCash(convert.getDoubleData(rs.get("startingCash")));
            data.setStatus(convert.getStrData(rs.get("status")));
            data.setAcctnum(convert.getStrData(rs.get("acctnum")));
            ibData.add(i, data);
            i++;
         }
      }
      return ibData;

   }

   public List<InvessenceData> collectInvData(String filter)
   {
      DataSource ds = getDataSource();
      AdminSP sp = new AdminSP(ds, "admin_sel_collectInvData",101);
      List<InvessenceData> invList = new ArrayList<InvessenceData>();

      Map outMap = sp.getInvData(filter);
      if (outMap != null)
      {
         ArrayList<Map<String, Object>> rows = (ArrayList<Map<String, Object>>) outMap.get("#result-set-1");
         int i = 0;
         for (Map<String, Object> map : rows)
         {
            Map rs = (Map) rows.get(i);
            InvessenceData data = new InvessenceData();

            data.setLogonID(convert.getLongData(rs.get("logonid")));
            data.setEmail(convert.getStrData(rs.get("email")));
            data.setFirstname(convert.getStrData(rs.get("firstname")));
            data.setLastname(convert.getStrData(rs.get("lastname")));
            data.setAcctnum(convert.getLongData(rs.get("acctnum")));
            data.setGoal(convert.getStrData(rs.get("goal")));
            data.setAcctType(convert.getStrData(rs.get("accttype")));
            data.setAge(convert.getIntData(rs.get("age")));
            data.setHorizon(convert.getIntData(rs.get("horizon")));
            data.setRiskIndex(convert.getDoubleData(rs.get("riskIndex")));
            data.setInitialInvestment(convert.getLongData(rs.get("initialInvestment")));
            data.setRecurringInvestment(convert.getLongData(rs.get("recurringInvestment")));
            data.setTradepreference(convert.getStrData(rs.get("tradepreference")));
            data.setKeepliquid(convert.getLongData(rs.get("keepliquid")));
            data.setLongTermGoal(convert.getIntData(rs.get("longTermGoal")));
            data.setStayInvested(convert.getIntData(rs.get("stayInvested")));
            data.setDependent(convert.getIntData(rs.get("dependent")));
            data.setTotalIncomeAnnulized(convert.getLongData(rs.get("totalIncomeAnnulized")));
            data.setTotalExpenseAnnulized(convert.getLongData(rs.get("totalExpenseAnnulized")));
            data.setTotalAsset(convert.getLongData(rs.get("totalAsset")));
            data.setTotalDebt(convert.getLongData(rs.get("totalDebt")));
            data.setLiquidnetworth(convert.getLongData(rs.get("liquidnetworth")));
            data.setNetworth(convert.getLongData(rs.get("networth")));
            data.setCreated(convert.getStrData(rs.get("created")));
            //data.setLastUpdated(convert.getStrData(rs.get("lastUpdated")));
            invList.add(i, data);
            i++;
         }
      }

      return invList;

   }

}

