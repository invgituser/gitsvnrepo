package com.invessence.web.dao.consumer;

import java.io.Serializable;
import java.util.*;
import javax.faces.bean.*;
import javax.sql.DataSource;

import com.invessence.web.bean.custody.ClientBean;
import com.invessence.converter.SQLData;
import com.invessence.web.dao.advisor.AdvisorListSP;
import com.invessence.web.data.common.CustomerData;
import com.invessence.web.data.consumer.*;
import com.invmodel.inputData.GoalsData;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

@SuppressWarnings({"unchecked", "DuplicateStringLiteralInspection"})
@ManagedBean(name = "consumerListDataDAO")
@SessionScoped
public class ConsumerListDataDAO extends JdbcDaoSupport implements Serializable
{
   SQLData convert = new SQLData();

   public void getProfileData(CustomerData data) {
      DataSource ds = getDataSource();
      ConsumerListSP sp = new ConsumerListSP(ds, "sel_ClientProfileData",0);
      Map outMap = sp.loadClientProfileData(data);
      String managed, currentstatus;
      try {
         if (outMap != null)
         {
            ArrayList<Map<String, Object>> rows = (ArrayList<Map<String, Object>>) outMap.get("#result-set-1");
            if (rows == null)
               return;
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
               data.setRep(convert.getStrData(rs.get("rep")));
               data.setBasket(convert.getStrData(rs.get("theme")));
               data.setTheme(convert.getStrData(rs.get("theme")));
               data.setLastname(convert.getStrData(rs.get("lastname")));
               data.setFirstname(convert.getStrData(rs.get("firstname")));
               data.setName(convert.getStrData(rs.get("firstname")) + " " + convert.getStrData(rs.get("lastname")));
               data.setRegisteredState(convert.getStrData(rs.get("state")));
               data.setClientAccountID(convert.getStrData(rs.get("clientAccountID")));

               managed = (convert.getStrData(rs.get("acctstatus")));
               data.setManagedFlag(managed);
               data.setManaged(getManaged(managed));

               currentstatus = convert.getStrData(rs.get("status"));
               data.setCurrentStatus(currentstatus);
               data.setEditable(getEditable(currentstatus));
               data.setUnopened(getUnopened(currentstatus));

               data.setTradePreference(convert.getStrData(rs.get("tradePreference")));
               data.setGoal(convert.getStrData(rs.get("goal")));
               data.setAccountType(convert.getStrData(rs.get("accttype")));
               data.setAge(convert.getIntData(rs.get("age")));
               data.setHorizon(convert.getIntData(rs.get("horizon")));
               data.setCalendarYear(convert.getIntData(rs.get("yearnum")));
               data.setRiskIndex(convert.getDoubleData(rs.get("riskIndex")));
               data.setInitialInvestment(convert.getIntData(rs.get("initialInvestment")));
               data.setKeepLiquid(convert.getIntData(rs.get("keepLiquid")));
               data.setActualInvestment(convert.getDoubleData(rs.get("actualCapital")));
               data.setRecurringInvestment(convert.getIntData(rs.get("recurringInvestment")));
               data.setObjective(convert.getIntData(rs.get("longTermGoal")));
               data.setStayInvested(convert.getIntData(rs.get("stayInvested")));

               data.setRiskCalcMethod(convert.getStrData(rs.get("calcModel")));
               data.setAllocationIndex(convert.getIntData(rs.get("assetIndex")));
               data.setPortfolioIndex(convert.getIntData(rs.get("portfolioIndex")));

               if (convert.getStrData(rs.get("taxable")) == null)
                  data.setAccountTaxable(true);
               else if (convert.getStrData(rs.get("taxable")).startsWith("N"))
                  data.setAccountTaxable(false);
               else
                  data.setAccountTaxable(true);

               data.setRole(convert.getStrData(rs.get("role")));
               data.setPrivileges(convert.getStrData(rs.get("privileges")));
               data.setDateOpened(convert.getStrData(rs.get("dateOpened")));

               data.setDependent(convert.getIntData(rs.get("dependent")));
               data.getAccountFinancials().setDependent(convert.getIntData(rs.get("dependent")));
               data.getAccountFinancials().setEstdDependentExpense(convert.getLongData(rs.get("estdDependentExpense")));
               data.getAccountFinancials().setHouseholdwages(convert.getLongData(rs.get("householdwages")));
               data.getAccountFinancials().setOtherincome(convert.getLongData(rs.get("otherincome")));
               data.getAccountFinancials().setBonusincome(convert.getLongData(rs.get("bonusincome")));
               data.getAccountFinancials().setInterestincome(convert.getLongData(rs.get("interestincome")));
               data.getAccountFinancials().setDividentincome(convert.getLongData(rs.get("dividentincome")));
               data.getAccountFinancials().setRentalIncome(convert.getLongData(rs.get("rentalIncome")));
               data.getAccountFinancials().setTotalIncome(convert.getLongData(rs.get("totalIncome")));
               data.getAccountFinancials().setTotalIncomeAnnulized(convert.getLongData(rs.get("totalIncomeAnnulized")));

               data.getAccountFinancials().setTotalIncomeAnnulized(convert.getLongData(rs.get("householdPayment")));
               data.getAccountFinancials().setOtherPropertiesPayment(convert.getLongData(rs.get("otherPropertiesPayment")));
               data.getAccountFinancials().setAutomobilePayment(convert.getLongData(rs.get("automobilePayment")));
               data.getAccountFinancials().setMedicalPayment(convert.getLongData(rs.get("medicalPayment")));

               data.getAccountFinancials().setFederaltaxes(convert.getLongData(rs.get("federaltaxes")));
               data.getAccountFinancials().setStateTaxes(convert.getLongData(rs.get("stateTaxes")));
               data.getAccountFinancials().setPropertyTax(convert.getLongData(rs.get("propertyTax")));
               data.getAccountFinancials().setOtherPropertyTax(convert.getLongData(rs.get("otherPropertyTax")));

               data.getAccountFinancials().setHomeInsurance(convert.getLongData(rs.get("homeInsurance")));
               data.getAccountFinancials().setLifeInsurance(convert.getLongData(rs.get("lifeInsurance")));
               data.getAccountFinancials().setAutoInsurance(convert.getLongData(rs.get("autoInsurance")));

               data.getAccountFinancials().setEducationPayment(convert.getLongData(rs.get("educationPayment")));
               data.getAccountFinancials().setCreditCardPayment(convert.getLongData(rs.get("creditCardPayment")));
               data.getAccountFinancials().setMiscExpenses(convert.getLongData(rs.get("miscExpenses")));
               data.getAccountFinancials().setTotalExpense(convert.getLongData(rs.get("totalExpense")));
               data.getAccountFinancials().setTotalExpenseAnnulized(convert.getLongData(rs.get("totalExpenseAnnulized")));

               data.getAccountFinancials().setHomeEquity(convert.getLongData(rs.get("homeEquity")));
               data.getAccountFinancials().setAutoValue(convert.getLongData(rs.get("autoValue")));
               data.getAccountFinancials().setMoneyMarket(convert.getLongData(rs.get("moneyMarket")));
               data.getAccountFinancials().setCheckingAcct(convert.getLongData(rs.get("checkingAcct")));
               data.getAccountFinancials().setSavingAcct(convert.getLongData(rs.get("savingAcct")));
               data.getAccountFinancials().setInvestment(convert.getLongData(rs.get("investment")));
               data.getAccountFinancials().setEquityOtherProperties(convert.getLongData(rs.get("equityOtherProperties")));
               data.getAccountFinancials().setRetirementInvestement(convert.getLongData(rs.get("retirementInvestement")));
               data.getAccountFinancials().setMiscInvestment(convert.getLongData(rs.get("miscInvestment")));
               data.getAccountFinancials().setTotalAsset(convert.getLongData(rs.get("totalAsset")));

               data.getAccountFinancials().setMortgageLoan(convert.getLongData(rs.get("mortgageLoan")));
               data.getAccountFinancials().setAutoLoan(convert.getLongData(rs.get("autoLoan")));
               data.getAccountFinancials().setEducationLoan(convert.getLongData(rs.get("educationLoan")));
               data.getAccountFinancials().setCreditCardDebt(convert.getLongData(rs.get("creditCardDebt")));
               data.getAccountFinancials().setOtherPropertiesLoan(convert.getLongData(rs.get("otherPropertiesLoan")));
               data.getAccountFinancials().setMedicalDebt(convert.getLongData(rs.get("medicalDebt")));
               data.getAccountFinancials().setOtherDebt(convert.getLongData(rs.get("otherDebt")));
               data.getAccountFinancials().setTotalDebt(convert.getLongData(rs.get("totalDebt")));

               data.getAccountFinancials().setLiquidnetworth(convert.getLongData(rs.get("liquidnetworth")));
               data.getAccountFinancials().setNetworth(convert.getLongData(rs.get("networth")));

               if (data.getGoalData() == null )
                  data.setGoalData(new GoalsData());

               data.getGoalData().setGoalDesired(convert.getDoubleData(rs.get("goalDesired")));

               i++;
               break;  // Only load the first account info.
            }
         }
      }
      catch (Exception ex) {
         ex.printStackTrace();
      }
   }

   private Boolean getManaged(String managed) {
      if (managed == null) {
         return false;
      }
      else {
         if (managed.equalsIgnoreCase("Active")) {
            return true;
         }
         else {
            return false;
         }
      }
   }

   private Boolean getEditable(String currentstatus) {
      if (currentstatus == null) {
         return true;
      }
      else {
         if (currentstatus.equalsIgnoreCase("visitor") ||
            currentstatus.equalsIgnoreCase("pending") ||
            currentstatus.equalsIgnoreCase("active")) {
            return true;
         }
         else {
            return false;
         }
      }
   }

   private Boolean getUnopened(String currentstatus) {
      if (currentstatus == null) {
         return true;
      }
      else {
         if (currentstatus.equalsIgnoreCase("visitor") ||
            currentstatus.equalsIgnoreCase("pending")) {
            return true;
         }
         else {
            return false;
         }
      }
   }

   public ArrayList<CustomerData> getClientProfileList(Long logonid, Long acctnum, Integer days) {
      DataSource ds = getDataSource();
      ConsumerListSP sp = new ConsumerListSP(ds, "sel_ClientProfileData",0);
      ArrayList<CustomerData> listProfiles = new ArrayList<CustomerData>();
      Map outMap = sp.loadClientProfileData(logonid, acctnum, days);
      String managed, currentstatus;
      if (outMap != null)
      {
         ArrayList<Map<String, Object>> rows = (ArrayList<Map<String, Object>>) outMap.get("#result-set-1");
         int i = 0;
         for (Map<String, Object> map : rows)
         {
            Map rs = (Map) rows.get(i);
            CustomerData data = new CustomerData();

            data.setLogonid(convert.getLongData(rs.get("logonid")));
            data.setAcctnum(convert.getLongData(rs.get("acctnum")));
            data.setPortfolioName(convert.getStrData(rs.get("portfolioName")));
            data.setEmail(convert.getStrData(rs.get("email")));
            data.setUserid(convert.getStrData(rs.get("userid")));
            data.setAdvisor(convert.getStrData(rs.get("advisor")));
            data.setRep(convert.getStrData(rs.get("rep")));
            data.setBasket(convert.getStrData(rs.get("theme")));
            data.setTheme(convert.getStrData(rs.get("theme")));
            data.setLastname(convert.getStrData(rs.get("lastname")));
            data.setFirstname(convert.getStrData(rs.get("firstname")));
            data.setName(convert.getStrData(rs.get("firstname")) + " " + convert.getStrData(rs.get("lastname")));
            data.setRegisteredState(convert.getStrData(rs.get("state")));
            data.setClientAccountID(convert.getStrData(rs.get("clientAccountID")));

            managed = (convert.getStrData(rs.get("acctstatus")));
            data.setManagedFlag(managed);
            data.setManaged(getManaged(managed));

            currentstatus = convert.getStrData(rs.get("status"));
            data.setCurrentStatus(currentstatus);
            data.setEditable(getEditable(currentstatus));
            data.setUnopened(getUnopened(currentstatus));

            data.setTradePreference(convert.getStrData(rs.get("tradePreference")));
            data.setPortfolioName(convert.getStrData(rs.get("portfolioName")));
            data.setGoal(convert.getStrData(rs.get("goal")));
            data.setAccountType(convert.getStrData(rs.get("accttype")));
            data.setAge(convert.getIntData(rs.get("age")));
            data.setHorizon(convert.getIntData(rs.get("horizon")));
            data.setCalendarYear(convert.getIntData(rs.get("yearnum")));
            data.setRiskIndex(convert.getDoubleData(rs.get("riskIndex")));
            data.setInitialInvestment(convert.getIntData(rs.get("initialInvestment")));
            data.setKeepLiquid(convert.getIntData(rs.get("keepLiquid")));
            data.setActualInvestment(convert.getDoubleData(rs.get("actualCapital")));
            data.setRecurringInvestment(convert.getIntData(rs.get("recurringInvestment")));
            data.setObjective(convert.getIntData(rs.get("longTermGoal")));
            data.setStayInvested(convert.getIntData(rs.get("stayInvested")));

            data.setRiskCalcMethod(convert.getStrData(rs.get("calcModel")));
            data.setAllocationIndex(convert.getIntData(rs.get("assetIndex")));
            data.setPortfolioIndex(convert.getIntData(rs.get("portfolioIndex")));

            String taxable = convert.getStrData(rs.get("taxable"));

            if (taxable == null)
               data.setAccountTaxable(false);
            else if (taxable.startsWith("N"))
               data.setAccountTaxable(false);
            else
               data.setAccountTaxable(true);


            data.setDateOpened(convert.getStrData(rs.get("dateOpened")));

            //data.setNetWorth(convert.getIntData(rs.get("networth")));
            data.setDependent(convert.getIntData(rs.get("dependent")));
            data.getAccountFinancials().setDependent(convert.getIntData(rs.get("dependent")));
            data.getAccountFinancials().setEstdDependentExpense(convert.getLongData(rs.get("estdDependentExpense")));
            data.getAccountFinancials().setHouseholdwages(convert.getLongData(rs.get("householdwages")));
            data.getAccountFinancials().setOtherincome(convert.getLongData(rs.get("otherincome")));
            data.getAccountFinancials().setBonusincome(convert.getLongData(rs.get("bonusincome")));
            data.getAccountFinancials().setInterestincome(convert.getLongData(rs.get("interestincome")));
            data.getAccountFinancials().setDividentincome(convert.getLongData(rs.get("dividentincome")));
            data.getAccountFinancials().setRentalIncome(convert.getLongData(rs.get("rentalIncome")));
            data.getAccountFinancials().setTotalIncome(convert.getLongData(rs.get("totalIncome")));
            data.getAccountFinancials().setTotalIncomeAnnulized(convert.getLongData(rs.get("totalIncomeAnnulized")));

            data.getAccountFinancials().setTotalIncomeAnnulized(convert.getLongData(rs.get("householdPayment")));
            data.getAccountFinancials().setOtherPropertiesPayment(convert.getLongData(rs.get("otherPropertiesPayment")));
            data.getAccountFinancials().setAutomobilePayment(convert.getLongData(rs.get("automobilePayment")));
            data.getAccountFinancials().setMedicalPayment(convert.getLongData(rs.get("medicalPayment")));

            data.getAccountFinancials().setFederaltaxes(convert.getLongData(rs.get("federaltaxes")));
            data.getAccountFinancials().setStateTaxes(convert.getLongData(rs.get("stateTaxes")));
            data.getAccountFinancials().setPropertyTax(convert.getLongData(rs.get("propertyTax")));
            data.getAccountFinancials().setOtherPropertyTax(convert.getLongData(rs.get("otherPropertyTax")));

            data.getAccountFinancials().setHomeInsurance(convert.getLongData(rs.get("homeInsurance")));
            data.getAccountFinancials().setLifeInsurance(convert.getLongData(rs.get("lifeInsurance")));
            data.getAccountFinancials().setAutoInsurance(convert.getLongData(rs.get("autoInsurance")));

            data.getAccountFinancials().setEducationPayment(convert.getLongData(rs.get("educationPayment")));
            data.getAccountFinancials().setCreditCardPayment(convert.getLongData(rs.get("creditCardPayment")));
            data.getAccountFinancials().setMiscExpenses(convert.getLongData(rs.get("miscExpenses")));
            data.getAccountFinancials().setTotalExpense(convert.getLongData(rs.get("totalExpense")));
            data.getAccountFinancials().setTotalExpenseAnnulized(convert.getLongData(rs.get("totalExpenseAnnulized")));

            data.getAccountFinancials().setHomeEquity(convert.getLongData(rs.get("homeEquity")));
            data.getAccountFinancials().setAutoValue(convert.getLongData(rs.get("autoValue")));
            data.getAccountFinancials().setMoneyMarket(convert.getLongData(rs.get("moneyMarket")));
            data.getAccountFinancials().setCheckingAcct(convert.getLongData(rs.get("checkingAcct")));
            data.getAccountFinancials().setSavingAcct(convert.getLongData(rs.get("savingAcct")));
            data.getAccountFinancials().setInvestment(convert.getLongData(rs.get("investment")));
            data.getAccountFinancials().setEquityOtherProperties(convert.getLongData(rs.get("equityOtherProperties")));
            data.getAccountFinancials().setRetirementInvestement(convert.getLongData(rs.get("retirementInvestement")));
            data.getAccountFinancials().setMiscInvestment(convert.getLongData(rs.get("miscInvestment")));
            data.getAccountFinancials().setTotalAsset(convert.getLongData(rs.get("totalAsset")));

            data.getAccountFinancials().setMortgageLoan(convert.getLongData(rs.get("mortgageLoan")));
            data.getAccountFinancials().setAutoLoan(convert.getLongData(rs.get("autoLoan")));
            data.getAccountFinancials().setEducationLoan(convert.getLongData(rs.get("educationLoan")));
            data.getAccountFinancials().setCreditCardDebt(convert.getLongData(rs.get("creditCardDebt")));
            data.getAccountFinancials().setOtherPropertiesLoan(convert.getLongData(rs.get("otherPropertiesLoan")));
            data.getAccountFinancials().setMedicalDebt(convert.getLongData(rs.get("medicalDebt")));
            data.getAccountFinancials().setOtherDebt(convert.getLongData(rs.get("otherDebt")));
            data.getAccountFinancials().setTotalDebt(convert.getLongData(rs.get("totalDebt")));

            data.getAccountFinancials().setLiquidnetworth(convert.getLongData(rs.get("liquidnetworth")));
            data.getAccountFinancials().setNetworth(convert.getLongData(rs.get("networth")));


            if (data.getGoalData() == null )
               data.setGoalData(new GoalsData());

            data.getGoalData().setGoalDesired(convert.getDoubleData(rs.get("goalDesired")));

            listProfiles.add(i, data);
            i++;
         }
         return listProfiles;
      }
      return null;
   }

   public void getNewClientProfileData(CustomerData data) {
      DataSource ds = getDataSource();
      ConsumerListSP sp = new ConsumerListSP(ds, "sel_NewAccountProfile",1);
      Map outMap = sp.getNewClientProfileData(data);
      if (outMap != null)
      {
         ArrayList<Map<String, Object>> rows = (ArrayList<Map<String, Object>>) outMap.get("#result-set-1");
         int i = 0;
         if (rows != null) {
            for (Map<String, Object> map : rows)
            {
               Map rs = (Map) rows.get(i);

               data.setAdvisor(convert.getStrData(rs.get("advisor")));
               data.setRep(convert.getStrData(rs.get("rep")));
               data.setAdvisorDisplayName(convert.getStrData(rs.get("displayName")));
               data.setTheme(convert.getStrData(rs.get("theme")));
               break;
            }
         }
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
               String basket = convert.getStrData(rs.get("displayname"));
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

   public void getClientData(ClientBean data) {
      DataSource ds = getDataSource();
/*
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
*/
   }

   public void getClientEmpData(ClientBean data) {
      DataSource ds = getDataSource();
/*
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
*/
   }

   public String validateState(Long logonid, String state) {
      DataSource ds = getDataSource();
      ConsumerListSP sp = new ConsumerListSP(ds, "sp_validate_state",3);
      Map outMap = sp.validateState(logonid, state);
      String info="quota";
      if (outMap != null)
      {
         ArrayList<Map<String, Object>> rows = (ArrayList<Map<String, Object>>) outMap.get("#result-set-1");
         int i = 0;
         for (Map<String, Object> map : rows)
         {
            Map rs = (Map) rows.get(i);
            info=convert.getStrData(rs.get("license"));
            i++;
            break;
         }
      }
      return info;

   }

   public ArrayList<ReportData> loadReports(Long logonid, String fromDate, String toDate) {
      DataSource ds = getDataSource();
      ConsumerListSP sp = new ConsumerListSP(ds, "sel_reports",4);
      Map outMap = sp.loadReports(logonid, fromDate, toDate);
      ArrayList<ReportData> reports = new ArrayList<ReportData>();
      if (outMap != null)
      {
         ArrayList<Map<String, Object>> rows = (ArrayList<Map<String, Object>>) outMap.get("#result-set-1");
         if (rows != null) {
            int i = 0;
            String filename;
            for (Map<String, Object> map : rows)
            {
               Map rs = (Map) rows.get(i);
               ReportData rdata = new ReportData();
               rdata.setAcctnum(convert.getStrData(rs.get("acctnum")));
               rdata.setBusinessdate(convert.getStrData(rs.get("reportDate")));
               rdata.setReportName(convert.getStrData(rs.get("reportName")));
               rdata.setSource(convert.getStrData(rs.get("src")));
               filename = convert.getStrData(rs.get("filename"));
               if (filename.contains(".pdf")) {
                  rdata.setDownloadReport(true);
                  rdata.setViewReport(false);
               }
               else {
                  rdata.setViewReport(true);
                  rdata.setDownloadReport(false);
               }
               rdata.setFilename(filename);
               reports.add(rdata);
               i++;
            }
         }
      }
      return reports;
   }

   public void getRiskProfileData(Long acctnum, RiskCalculator data) {
      DataSource ds = getDataSource();
      ConsumerListSP sp = new ConsumerListSP(ds, "sel_risk_questions",5);
      Map outMap = sp.loadRiskProfileData(acctnum);
      try {
         if (outMap != null)
         {
            ArrayList<Map<String, Object>> rows = (ArrayList<Map<String, Object>>) outMap.get("#result-set-1");
            if (rows == null)
               return;

            for (Map<String, Object> map : rows)
            {
               Map rs = (Map) rows.get(0);
               data.setRiskAge(convert.getIntData(rs.get("age")));
               data.setInvestmentobjective(convert.getStrData(rs.get("investmentgoal")));
               data.setRetired(convert.getIntData(rs.get("retired")));
               data.setRetireAge(convert.getIntData(rs.get("retireage")));
               data.setRiskHorizon(convert.getIntData(rs.get("horizon")));
               data.setRiskFormula(convert.getStrData(rs.get("formula")));
               data.setAnswer(1, convert.getStrData(rs.get("ans1")));
               data.setAnswer(2, convert.getStrData(rs.get("ans2")));
               data.setAnswer(3, convert.getStrData(rs.get("ans3")));
               data.setAnswer(4, convert.getStrData(rs.get("ans4")));
               data.setAnswer(5, convert.getStrData(rs.get("ans5")));
               data.setAnswer(6, convert.getStrData(rs.get("ans6")));
               data.setAnswer(7, convert.getStrData(rs.get("ans7")));
               data.setAnswer(8, convert.getStrData(rs.get("ans8")));
               data.setAnswer(9, convert.getStrData(rs.get("ans9")));
               data.setAnswer(10, convert.getStrData(rs.get("ans10")));
               data.setAnswer(11, convert.getStrData(rs.get("ans11")));
               data.setAnswer(12, convert.getStrData(rs.get("ans12")));
               data.setAnswer(13, convert.getStrData(rs.get("ans13")));
               data.setAnswer(14, convert.getStrData(rs.get("ans14")));
               data.setAnswer(15, convert.getStrData(rs.get("ans15")));
               data.setRiskValue(1, convert.getDoubleData(rs.get("risk1")));
               data.setRiskValue(2, convert.getDoubleData(rs.get("risk2")));
               data.setRiskValue(3, convert.getDoubleData(rs.get("risk3")));
               data.setRiskValue(4, convert.getDoubleData(rs.get("risk4")));
               data.setRiskValue(5, convert.getDoubleData(rs.get("risk5")));
               data.setRiskValue(6, convert.getDoubleData(rs.get("risk6")));
               data.setRiskValue(7, convert.getDoubleData(rs.get("risk7")));
               data.setRiskValue(8, convert.getDoubleData(rs.get("risk8")));
               data.setRiskValue(9, convert.getDoubleData(rs.get("risk9")));
               data.setRiskValue(10, convert.getDoubleData(rs.get("risk10")));
               data.setRiskValue(11, convert.getDoubleData(rs.get("risk11")));
               data.setRiskValue(12, convert.getDoubleData(rs.get("risk12")));
               data.setRiskValue(13, convert.getDoubleData(rs.get("risk13")));
               data.setRiskValue(14, convert.getDoubleData(rs.get("risk14")));
               data.setRiskValue(15, convert.getDoubleData(rs.get("risk15")));
               data.setTotalRisk(convert.getDoubleData(rs.get("totalrisk")));
               break;  // Only load the first account info.
            }
         }
      }
      catch (Exception ex) {
         ex.printStackTrace();
      }
   }

   public String geteditFundData(Long acctnum)
   {

      DataSource ds = getDataSource();
      ConsumerListSP sp = new ConsumerListSP(ds, "sp_sel_editfund_data",6);
      Map outMap = sp.validatefundData(acctnum);
      String info="quota";
      if (outMap != null)
      {
         ArrayList<Map<String, Object>> rows = (ArrayList<Map<String, Object>>) outMap.get("#result-set-1");
         int i = 0;
         for (Map<String, Object> map : rows)
         {
            Map rs = (Map) rows.get(i);
            info=convert.getStrData(rs.get("license"));
            i++;
            break;
         }
      }
      return info;
   }
}