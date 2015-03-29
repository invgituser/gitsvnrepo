package com.invessence.dao.consumer;

import java.io.Serializable;
import java.util.*;
import javax.faces.bean.*;
import javax.sql.DataSource;

import com.invessence.bean.consumer.ClientBean;
import com.invessence.converter.SQLData;
import com.invessence.dao.advisor.AdvisorListSP;
import com.invessence.data.common.ManageGoals;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

@ManagedBean(name = "consumerListDataDAO")
@SessionScoped
public class ConsumerListDataDAO extends JdbcDaoSupport implements Serializable
{
   SQLData convert = new SQLData();

   public List<ManageGoals> getClientProfileData(Long logonid, Long acctnum) {
      DataSource ds = getDataSource();
      ConsumerListSP sp = new ConsumerListSP(ds, "sel_ClientProfileData2",0);
      List<ManageGoals> listProfiles = new ArrayList<ManageGoals>();
      Map outMap = sp.loadClientProfileData(logonid, acctnum);
      if (outMap != null)
      {
         ArrayList<Map<String, Object>> rows = (ArrayList<Map<String, Object>>) outMap.get("#result-set-1");
         int i = 0;
         for (Map<String, Object> map : rows)
         {
            Map rs = (Map) rows.get(i);
            ManageGoals data = new ManageGoals();

            data.setLogonid(convert.getLongData(rs.get("logonid")));
            data.setAcctnum(convert.getLongData(rs.get("acctnum")));
            data.setPortfolioName(convert.getStrData(rs.get("portfolioName")));
            data.setEmail(convert.getStrData(rs.get("email")));
            data.setUserid(convert.getStrData(rs.get("userid")));
            data.setAdvisor(convert.getStrData(rs.get("advisor")));
            data.setBasket(convert.getStrData(rs.get("theme")));
            data.setLastname(convert.getStrData(rs.get("lastname")));
            data.setFirstname(convert.getStrData(rs.get("firstname")));
            data.setName(convert.getStrData(rs.get("firstname")) + " " + convert.getStrData(rs.get("lastname")));
            data.setRegisteredState(convert.getStrData(rs.get("state")));
            data.setClientAccountID(convert.getStrData(rs.get("clientAccountID")));

            String action = (convert.getStrData(rs.get("acctstatus")));
            data.setAcctstatus(action);
            if (action.equalsIgnoreCase("Pending")) {
               data.setManaged(false);
            }
            else {
               data.setManaged(true);
            }
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
            data.setDateOpened(convert.getStrData(rs.get("dateOpened")));

            data.setTotalIncome(convert.getIntData(rs.get("totalIncomeAnnulized")));
            data.setTotalExpense(convert.getIntData(rs.get("totalExpenseAnnulized")));
            data.setTotalAsset(convert.getIntData(rs.get("totalAsset")));
            data.setTotalLiability(convert.getIntData(rs.get("totalDebt")));
            data.setLiquidAsset(convert.getIntData(rs.get("liquidnetworth")));
            //data.setNetWorth(convert.getIntData(rs.get("networth")));

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

            listProfiles.add(i, data);
            i++;
         }
         return listProfiles;
      }
      return null;
   }

   public void getNewClientProfileData(ManageGoals data) {
      DataSource ds = getDataSource();
      ConsumerListSP sp = new ConsumerListSP(ds, "sel_AccountProfile",1);
      Map outMap = sp.loadClientProfileData(data.getLogonid(), 0L);
      if (outMap != null)
      {
         ArrayList<Map<String, Object>> rows = (ArrayList<Map<String, Object>>) outMap.get("#result-set-1");
         int i = 0;
         for (Map<String, Object> map : rows)
         {
            Map rs = (Map) rows.get(i);

            data.setLogonid(convert.getLongData(rs.get("logonid")));
            data.setEmail(convert.getStrData(rs.get("email")));
            data.setUserid(convert.getStrData(rs.get("userid")));
            data.setLastname(convert.getStrData(rs.get("lastname")));
            data.setFirstname(convert.getStrData(rs.get("firstname")));
            data.setName(convert.getStrData(rs.get("firstname")) + " " + convert.getStrData(rs.get("lastname")));
            data.setRegisteredState(convert.getStrData(rs.get("state")));
            break;
         }
      }
   }

   public void getProfileData(ManageGoals data) {
      DataSource ds = getDataSource();
      ConsumerListSP sp = new ConsumerListSP(ds, "sel_AccountProfile",1);
      Map outMap = sp.loadClientProfileData(data);
      String action;
      try {
         if (outMap != null)
         {
            ArrayList<Map<String, Object>> rows = (ArrayList<Map<String, Object>>) outMap.get("#result-set-1");
            int i = 0;
            for (Map<String, Object> map : rows)
            {
               Map rs = (Map) rows.get(i);
               data.setLogonid(convert.getLongData(rs.get("logonid")));
               data.setAcctnum(convert.getLongData(rs.get("acctnum")));
               data.setPortfolioName(convert.getStrData(rs.get("portfolioName")));
               data.setEmail(convert.getStrData(rs.get("email")));
               data.setUserid(convert.getStrData(rs.get("userid")));
               data.setAdvisor(convert.getStrData(rs.get("advisor")));
               data.setBasket(convert.getStrData(rs.get("theme")));
               data.setLastname(convert.getStrData(rs.get("lastname")));
               data.setFirstname(convert.getStrData(rs.get("firstname")));
               data.setName(convert.getStrData(rs.get("firstname")) + " " + convert.getStrData(rs.get("lastname")));
               data.setRegisteredState(convert.getStrData(rs.get("state")));
               data.setClientAccountID(convert.getStrData(rs.get("clientAccountID")));

               action = (convert.getStrData(rs.get("acctstatus")));
               data.setAcctstatus(action);
               if (action.equalsIgnoreCase("Pending")) {
                  data.setManaged(false);
               }
               else {
                  data.setManaged(true);
               }
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
               data.setDateOpened(convert.getStrData(rs.get("dateOpened")));

               data.setTotalIncome(convert.getIntData(rs.get("totalIncomeAnnulized")));
               data.setTotalExpense(convert.getIntData(rs.get("totalExpenseAnnulized")));
               data.setTotalAsset(convert.getIntData(rs.get("totalAsset")));
               data.setTotalLiability(convert.getIntData(rs.get("totalDebt")));
               data.setLiquidAsset(convert.getIntData(rs.get("liquidnetworth")));
               //data.setNetWorth(convert.getIntData(rs.get("networth")));

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

   public void getNewClientProfileData(ClientBean data) {

   }

   public void getClientData(ClientBean data) {
      DataSource ds = getDataSource();
      ConsumerListSP sp = new ConsumerListSP(ds, "sp_clientinfo_sel",2);
      Map outMap = sp.loadClientProfileData(data);
      if (outMap != null)
      {
         ArrayList<Map<String, Object>> rows = (ArrayList<Map<String, Object>>) outMap.get("#result-set-1");
         int i = 0;
         for (Map<String, Object> map : rows)
         {
            Map rs = (Map) rows.get(i);

            data.setLogonid(convert.getLongData(rs.get("logonid")));
            data.setAcctnum(convert.getLongData(rs.get("acctnum")));
            data.setPrefix(convert.getStrData(rs.get("prefix")));
            data.setFirstName(convert.getStrData(rs.get("firstname")));
            data.setMiddleName(convert.getStrData(rs.get("middlename")));
            data.setLastName(convert.getStrData(rs.get("lastname")));
            data.setSuffix(convert.getStrData(rs.get("suffix")));
            data.setAddress1(convert.getStrData(rs.get("address")));
            data.setAddress2(convert.getStrData(rs.get("address2")));
            data.setCity(convert.getStrData(rs.get("city")));
            data.setStateName(convert.getStrData(rs.get("state")));
            data.setCountry(convert.getStrData(rs.get("country")));
            data.setZipCode(convert.getStrData(rs.get("zip")));
            data.setMailingAddress1(convert.getStrData(rs.get("addressalt")));
            data.setMailingAddress2(convert.getStrData(rs.get("address2alt")));
            data.setMailingCity(convert.getStrData(rs.get("cityalt")));
            data.setMailingStateName(convert.getStrData(rs.get("statealt")));
            data.setMailingCountry(convert.getStrData(rs.get("countryalt")));
            data.setMailingZipCode(convert.getStrData(rs.get("zipalt")));
            data.setDateOfBirth(convert.getDateFormatData(rs.get("dob")));
            data.setMaritalStatus(convert.getStrData(rs.get("maritalstatus")));
            data.setDependents(convert.getStrData(rs.get("dependents")));
            data.setGender(convert.getStrData(rs.get("gender")));
            data.setCountryOfCitizenship(convert.getStrData(rs.get("citizenship")));
            data.setSocialSecurity(convert.getStrData(rs.get("ssn")));
            data.setChecked(convert.getBooleanData(rs.get("altselect")));
            break;
         }
      }
   }

   public void getClientEmpData(ClientBean data) {
      DataSource ds = getDataSource();
      ConsumerListSP sp = new ConsumerListSP(ds, "sp_client_empinfo_sel",2);
      Map outMap = sp.loadClientProfileData(data);
      if (outMap != null)
      {
         ArrayList<Map<String, Object>> rows = (ArrayList<Map<String, Object>>) outMap.get("#result-set-1");
         int i = 0;
         for (Map<String, Object> map : rows)
         {
            Map rs = (Map) rows.get(i);

            data.setLogonid(convert.getLongData(rs.get("logonid")));
            data.setAcctnum(convert.getLongData(rs.get("acctnum")));
            data.setEmploymentStatus(convert.getStrData(rs.get("empstatus")));
            data.setEmployerName(convert.getStrData(rs.get("employer")));
            data.setNatureOfBusiness(convert.getStrData(rs.get("natureofbusiness")));
            data.setOccupation(convert.getStrData(rs.get("occupation")));
            data.setEmployerAddress1(convert.getStrData(rs.get("address")));
            data.setEmployerAddress2(convert.getStrData(rs.get("address2")));
            data.setEmployerCity(convert.getStrData(rs.get("city")));
            data.setEmployerStateName(convert.getStrData(rs.get("state")));
            data.setEmployerCountry(convert.getStrData(rs.get("country")));
            data.setEmployerZipCode(convert.getStrData(rs.get("zip")));
            break;
         }
      }
   }

}