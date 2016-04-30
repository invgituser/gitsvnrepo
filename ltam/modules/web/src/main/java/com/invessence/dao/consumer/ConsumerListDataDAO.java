package com.invessence.dao.consumer;

import java.io.Serializable;
import java.util.*;
import javax.faces.bean.*;
import javax.sql.DataSource;

import com.invessence.converter.SQLData;
import com.invessence.dao.ltam.LTAMListSP;
import com.invessence.data.common.AccountData;
import com.invessence.data.consumer.*;
import com.invessence.data.ltam.LTAMCustomerData;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

@ManagedBean(name = "consumerListDataDAO")
@SessionScoped
public class ConsumerListDataDAO extends JdbcDaoSupport implements Serializable
{
   SQLData convert = new SQLData();

   public List<ConsumerData> getClientProfileData(Long logonid, String filter, Integer days) {
      DataSource ds = getDataSource();
      List<ConsumerData> listProfiles = new ArrayList<ConsumerData>();
      ConsumerListSP sp = new ConsumerListSP(ds, "sel_ClientProfileData2",0);
      Map outMap = sp.loadClientProfileData(logonid, filter, days);
      if (outMap != null)
      {
         ArrayList<Map<String, Object>> rows = (ArrayList<Map<String, Object>>) outMap.get("#result-set-1");
         if (rows != null) {
            int i = 0;
            for (Map<String, Object> map : rows)
            {
               Map rs = (Map) rows.get(i);
               ConsumerData data = new ConsumerData(
                  convert.getLongData(rs.get("acctnum")),
                  convert.getStrData(rs.get("companyname")),
                  convert.getStrData(rs.get("repNum")),
                  convert.getStrData(rs.get("firstName")),
                  convert.getStrData(rs.get("lastName")),
                  convert.getDoubleData(rs.get("investment")),
                  convert.getDoubleData(rs.get("riskIndex")),
                  convert.getStrData(rs.get("managed")),
                  convert.getStrData(rs.get("dateOpened")),
                  convert.getStrData(rs.get("clientAccountID")),
                  convert.getStrData(rs.get("description")
                  )
               );

               listProfiles.add(i, data);
               i++;
            }
         }
      }
      return listProfiles;
   }

   public PositionSummaryData getPositionData(Long logonid, Long acctnum ) {
      DataSource ds = getDataSource();
      ConsumerListSP sp = new ConsumerListSP(ds, "sel_position",1);
      Map outMap = sp.getPositionData(logonid, acctnum);
      if (outMap != null)
      {
         ArrayList<Map<String, Object>> rows = (ArrayList<Map<String, Object>>) outMap.get("#result-set-1");
         if (rows != null ) {
            PositionSummaryData data = new PositionSummaryData();
            int i = 0;
            for (Map<String, Object> map : rows)
            {
               Map rs = (Map) rows.get(i);
               data.addPosition(
                  convert.getIntData(rs.get("sortorder")),
                  convert.getLongData(rs.get("acctnum")),
                  convert.getStrData(rs.get("clientAccountID")),
                  convert.getStrData(rs.get("name")),
                  convert.getStrData(rs.get("repName")),
                  convert.getStrData(rs.get("description")),
                  convert.getStrData(rs.get("theme")),
                  convert.getStrData(rs.get("dateOpened")),
                  convert.getStrData(rs.get("assetClass")),
                  convert.getStrData(rs.get("assetname")),
                  convert.getStrData(rs.get("subasset")),
                  convert.getStrData(rs.get("displayname")),
                  convert.getStrData(rs.get("color")),
                  convert.getStrData(rs.get("status")),
                  convert.getDoubleData(rs.get("allocation")),
                  convert.getStrData(rs.get("reportDate")),
                  convert.getStrData(rs.get("side")),
                  convert.getDoubleData(rs.get("quantity")),
                  convert.getDoubleData(rs.get("costBasisPrice")),
                  convert.getDoubleData(rs.get("costBasisMoney")),
                  convert.getDoubleData(rs.get("markPrice")),
                  convert.getDoubleData(rs.get("positionValue"))
               );
               i++;
            }
            return data;
         }
      }
      return null;
   }

   public AccountData getAccountData(Long logonid, Long acctnum) {
      DataSource ds = getDataSource();
      AccountData accountdata = null;
      ConsumerListSP sp = new ConsumerListSP(ds, "sel_AccountInfo",2);
      Map outMap = sp.loadAccountInfo(logonid,acctnum);
      if (outMap != null)
      {
         ArrayList<Map<String, Object>> rows = (ArrayList<Map<String, Object>>) outMap.get("#result-set-1");
         if (rows != null) {
            int i = 0;
            for (Map<String, Object> map : rows)
            {
               Map rs = (Map) rows.get(i);
               accountdata = new AccountData();
               accountdata.setAcctnum(convert.getLongData(rs.get("acctnum")));
               accountdata.setFirstname(convert.getStrData(rs.get("firstname")));
               accountdata.setLastname(convert.getStrData(rs.get("lastname")));
               accountdata.setInitialInvestment(convert.getDoubleData(rs.get("investment")));
               accountdata.setAdvisor(convert.getStrData(rs.get("advisor")));
               accountdata.setTheme(convert.getStrData(rs.get("theme")));
               accountdata.setRiskIndex(convert.getDoubleData(rs.get("riskIndex")));
               accountdata.setAge(convert.getIntData(rs.get("age")));
               accountdata.setHorizon(convert.getIntData(rs.get("horizon")));
               accountdata.setAccttype(convert.getStrData(rs.get("acctType")));
               accountdata.setEmail(convert.getStrData(rs.get("email")));
               accountdata.setActualCapital(convert.getDoubleData(rs.get("actualInvestment")));
               accountdata.setAns1(convert.getIntData(rs.get("ans1")));
               accountdata.setAns2(convert.getIntData(rs.get("ans2")));
               accountdata.setAns3(convert.getIntData(rs.get("ans3")));
               accountdata.setAns4(convert.getIntData(rs.get("ans4")));
               accountdata.setAns5(convert.getIntData(rs.get("ans5")));
               accountdata.setAns6(convert.getIntData(rs.get("ans6")));
               accountdata.setAns7(convert.getIntData(rs.get("ans7")));
               accountdata.setAns8(convert.getIntData(rs.get("ans8")));
               accountdata.setAns9(convert.getIntData(rs.get("ans9")));
               accountdata.setAns10(convert.getIntData(rs.get("ans10")));
               accountdata.setFormula(convert.getStrData(rs.get("formula")));
               accountdata.setDateOpened(convert.getStrData(rs.get("dateOpened")));
               accountdata.setClientAccountID(convert.getStrData(rs.get("clientAccountID")));
               accountdata.setCusip(convert.getStrData(rs.get("cusip")));
               accountdata.setSecurityName(convert.getStrData(rs.get("securityname")));
               accountdata.setFundID(convert.getIntData(rs.get("fundID")));
               i++;
               break;
            }
         }
      }
      return accountdata;
   }

}