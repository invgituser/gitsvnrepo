package com.invessence.dao.common;

import java.io.Serializable;
import java.util.*;
import javax.faces.bean.*;
import javax.sql.DataSource;

import com.invessence.converter.SQLData;
import com.invessence.data.common.*;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

@ManagedBean(name = "commonDAO")
@ApplicationScoped
public class CommonDAO extends JdbcDaoSupport implements Serializable
{
   SQLData convert = new SQLData();

   public ArrayList<CustomerData> getListOfAccounts(Long logonid, Long acctnum) {
      DataSource ds = getDataSource();
      CommonSP sp = new CommonSP(ds, "sel_ClientProfileData2",0);
      ArrayList<CustomerData> listProfiles = new ArrayList<CustomerData>();
      Map outMap = sp.collectProfileData(logonid, acctnum);
      String action;
      try {
         if (outMap != null)
         {
            ArrayList<Map<String, Object>> rows = (ArrayList<Map<String, Object>>) outMap.get("#result-set-1");
            int i = 0;
            Double actualCapital = 0.0;
            for (Map<String, Object> map : rows)
            {
               Map rs = (Map) rows.get(i);
               CustomerData data = new CustomerData();

               data.setAcctnum(convert.getLongData(rs.get("acctnum")));
               data.setEmail(convert.getStrData(rs.get("email")));

               data.setAdvisor(convert.getStrData(rs.get("advisor")));
               data.setTheme(convert.getStrData(rs.get("theme")));
               data.setLogonid(convert.getLongData(rs.get("logonid")));
               data.setLastname(convert.getStrData(rs.get("lastname")));
               data.setFirstname(convert.getStrData(rs.get("firstname")));
               data.setClientAccountID(convert.getStrData(rs.get("ClientAccountID")));

               String acctStatus = convert.getStrData(rs.get("acctstatus"));
               // data.setAcctstatus(acctStatus);
               if (acctStatus.equalsIgnoreCase("pending")) {
                  data.setManaged(false);
                  actualCapital = convert.getDoubleData(rs.get("initialInvestment"));
               }
               else {
                  data.setManaged(true);
                  actualCapital = convert.getDoubleData(rs.get("actualCapital"));
                  if (actualCapital != null && actualCapital > 0) {
                     data.setActualInvestment(actualCapital);
                     data.setManagedtotalMoney(convert.getDoubleData(rs.get("actualCapital")));
                  }
               }
               data.setActualInvestment(actualCapital);
               data.setTradePreference(convert.getStrData(rs.get("tradePreference")));
               data.setGoal(convert.getStrData(rs.get("goal")));
               data.setAccountType(convert.getStrData(rs.get("accttype")));
               data.setAge(convert.getIntData(rs.get("age")));
               data.setHorizon(convert.getIntData(rs.get("horizon")));
               data.setRiskIndex(convert.getIntData(rs.get("riskIndex")));
               data.setInitialInvestment(convert.getIntData(rs.get("initialInvestment")));
               data.setKeepLiquid(convert.getIntData(rs.get("keepLiquid")));
               data.setRecurringInvestment(convert.getIntData(rs.get("recurringInvestment")));
               data.setObjective(convert.getIntData(rs.get("longTermGoal")));
               data.setStayInvested(convert.getIntData(rs.get("stayInvested")));
               data.setDateOpened(convert.getStrData(rs.get("dateOpened")));
               data.setDependent(convert.getIntData(rs.get("dependent")));

               data.setHouseholdwages(convert.getIntData(rs.get("householdwages")));
               data.setMortgagePayment(convert.getIntData(rs.get("householdPayment")));
               data.setOtherExpense(convert.getIntData(rs.get("miscExpenses")));
               data.setMoneymarket(convert.getIntData(rs.get("moneyMarket")));
               data.setInvestment(convert.getIntData(rs.get("investment")));
               data.setOtherIncome(convert.getIntData(rs.get("otherincome")));
               data.setOtherDebt(convert.getIntData(rs.get("otherDebt")));

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


   public CustomerData getSingleAccounts(Long acctnum) {
      DataSource ds = getDataSource();
      CommonSP sp = new CommonSP(ds, "sel_ClientProfileData2",0);
      Map outMap = sp.collectProfileData(null, acctnum);
      CustomerData data = new CustomerData();
      try {
         if (outMap != null)
         {
            ArrayList<Map<String, Object>> rows = (ArrayList<Map<String, Object>>) outMap.get("#result-set-1");
            int i = 0;
            Double actualCapital = 0.0;
            for (Map<String, Object> map : rows)
            {
               Map rs = (Map) rows.get(i);

               data.setAcctnum(convert.getLongData(rs.get("acctnum")));
               data.setEmail(convert.getStrData(rs.get("email")));

               data.setAdvisor(convert.getStrData(rs.get("advisor")));
               data.setTheme(convert.getStrData(rs.get("theme")));
               data.setLogonid(convert.getLongData(rs.get("logonid")));
               data.setLastname(convert.getStrData(rs.get("lastname")));
               data.setFirstname(convert.getStrData(rs.get("firstname")));
               data.setClientAccountID(convert.getStrData(rs.get("ClientAccountID")));

               String acctStatus = convert.getStrData(rs.get("acctstatus"));
               // data.setAcctstatus(acctStatus);
               if (acctStatus.equalsIgnoreCase("pending")) {
                  data.setManaged(false);
               }
               else {
                  data.setManaged(true);
                  actualCapital = convert.getDoubleData(rs.get("actualCapital"));
                  if (actualCapital != null && actualCapital > 0) {
                     data.setActualInvestment(actualCapital);
                     data.setManagedtotalMoney(convert.getDoubleData(rs.get("actualCapital")));
                  }
               }
               data.setTradePreference(convert.getStrData(rs.get("tradePreference")));
               data.setGoal(convert.getStrData(rs.get("goal")));
               data.setAccountType(convert.getStrData(rs.get("accttype")));
               data.setAge(convert.getIntData(rs.get("age")));
               data.setHorizon(convert.getIntData(rs.get("horizon")));
               data.setRiskIndex(convert.getIntData(rs.get("riskIndex")));
               data.setInitialInvestment(convert.getIntData(rs.get("initialInvestment")));
               data.setKeepLiquid(convert.getIntData(rs.get("keepLiquid")));
               data.setRecurringInvestment(convert.getIntData(rs.get("recurringInvestment")));
               data.setObjective(convert.getIntData(rs.get("longTermGoal")));
               data.setStayInvested(convert.getIntData(rs.get("stayInvested")));
               data.setDateOpened(convert.getStrData(rs.get("dateOpened")));
               data.setDependent(convert.getIntData(rs.get("dependent")));

               data.setHouseholdwages(convert.getIntData(rs.get("householdwages")));
               data.setMortgagePayment(convert.getIntData(rs.get("householdPayment")));
               data.setOtherExpense(convert.getIntData(rs.get("miscExpenses")));
               data.setMoneymarket(convert.getIntData(rs.get("moneyMarket")));
               data.setInvestment(convert.getIntData(rs.get("investment")));
               data.setOtherIncome(convert.getIntData(rs.get("otherincome")));
               data.setOtherDebt(convert.getIntData(rs.get("otherDebt")));

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

               data.setCreated(convert.getStrData(rs.get("created")));
               i++;
            }
         }
         return data;
      }
      catch (Exception ex) {
         ex.printStackTrace();
      }
      return null;
   }

   public void saveUserIDPwd(UserData data) {

   }

   public String validateState(Long logonid, String state) {
      DataSource ds = getDataSource();
      CommonSP sp = new CommonSP(ds, "sp_validate_state",1);
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

   public ArrayList<NotificationData> getNotification(Long logonid, String messageType, String status)
   {
      DataSource ds = getDataSource();

      CommonSP sp = new CommonSP(ds, "sel_notification", 2);
      ArrayList<NotificationData> notificationList = new ArrayList<NotificationData>();

      Map outMap = sp.getNotification(logonid, messageType, status);
      if (outMap != null)
      {
         ArrayList<Map<String, Object>> rows = (ArrayList<Map<String, Object>>) outMap.get("#result-set-1");
         if (rows != null)
         {
            int i = 0;
            for (Map<String, Object> map : rows)
            {
               Map rs = (Map) rows.get(i);
               NotificationData ndata = new NotificationData(
                  convert.getLongData(rs.get("messageid")),
                  convert.getStrData(rs.get("status")),
                  convert.getLongData(rs.get("advisorlogonid")),
                  convert.getStrData(rs.get("advisor")),
                  convert.getLongData(rs.get("acctnum")),
                  convert.getStrData(rs.get("noticetype")),
                  convert.getStrData(rs.get("tagid")),
                  convert.getStrData(rs.get("alertdatetime")),
                  convert.getStrData(rs.get("message"))
                  // convert.getStrData(rs.get("created")),

               );

               notificationList.add(i, ndata);
               i++;
            }

         }
      }
      return notificationList;

   }

   public void saveNotice(NotificationData data) {
      DataSource ds = getDataSource();
      CommonSP sp = new CommonSP(ds, "sp_advisor_notification",3);
      sp.saveNotice(data);
   }

   public Map<String, Integer> getNotificationInfo(Long logonid) {
      if (logonid == null)
         return null;

      DataSource ds = getDataSource();
      CommonSP sp = new CommonSP(ds, "sel_notificationInfo",99);
      Map outMap = sp.getNotificationInfo(logonid);
      Map<String, Integer> statInfo = new HashMap<String, Integer>();
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
                  statInfo.put(convert.getStrData(rs.get("src")),
                               convert.getIntData(rs.get("value")));
                  i++;
               }
            }
         }
         return statInfo;
      }
      catch (Exception ex) {
         ex.printStackTrace();
      }
      return null;
   }

}