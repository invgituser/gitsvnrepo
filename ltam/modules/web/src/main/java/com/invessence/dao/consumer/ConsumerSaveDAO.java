package com.invessence.dao.consumer;

import java.io.Serializable;
import java.util.*;
import javax.faces.bean.*;
import javax.sql.DataSource;

import com.invessence.converter.SQLData;
import com.invessence.dao.ltam.LTAMSaveSP;
import com.invessence.data.common.AccountData;
import com.invessence.data.consumer.*;
import com.invessence.data.ltam.LTAMCustomerData;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

@ManagedBean(name = "consumerSaveDAO")
@SessionScoped
public class ConsumerSaveDAO extends JdbcDaoSupport implements Serializable
{
   SQLData convert = new SQLData();

   public Long saveVisitor(LTAMCustomerData data)
   {
      DataSource ds = getDataSource();
      ConsumerSaveSP sp = new ConsumerSaveSP(ds, "save_visitor",0);
      Long logonid = 0L;
      Map outMap = sp.saveVisitor(data);
      if (outMap != null) {
         logonid = ((Long) outMap.get("p_logonid")).longValue();
         // data.setLogonid(logonid);
      }
      return (logonid);
   }

   public Long saveUserData(LTAMCustomerData data)
   {
      DataSource ds = getDataSource();
      ConsumerSaveSP sp = new ConsumerSaveSP(ds, "save_acct_info",1);
      Long acctnum = 0L;
      Map outMap = sp.saveUserData(data);
      acctnum = ((Long) outMap.get("p_acctnum")).longValue();
      return (acctnum);
   }

   public String savePostBack(String myacctnum, String advisor, String ext_acctnum)
   {
      DataSource ds = getDataSource();
      ConsumerSaveSP sp = new ConsumerSaveSP(ds, "save_acct_info_ack",2);
      Map outMap = sp.savePostBack(myacctnum, advisor, ext_acctnum);
      String msg = ((String) outMap.get("p_msg"));
      return (msg);
   }


   public void saveAddress(AccountData data) {
      try {
         DataSource ds = getDataSource();
         ConsumerSaveSP sp = new ConsumerSaveSP(ds, "sp_update_addressInfo",3);
         Map outMap = sp.saveAddress(data);
      }
      catch (Exception ex) {
         ex.printStackTrace();
      }
   }

   public Long saveTradeInfo(TradeData data) {
      Long transnum = null;
      try {
         DataSource ds = getDataSource();
         ConsumerSaveSP sp = new ConsumerSaveSP(ds, "save_tradeInfo",4);
         Map outMap = sp.saveTradeInfo(data);
         transnum = ((Long) outMap.get("p_transactionnum")).longValue();
      }
      catch (Exception ex) {
         ex.printStackTrace();
      }
      return transnum;
   }

}