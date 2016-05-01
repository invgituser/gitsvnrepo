package com.invessence.dao.ltam;

import java.io.Serializable;
import java.util.Map;
import javax.faces.bean.*;
import javax.sql.DataSource;

import com.invessence.converter.SQLData;
import com.invessence.data.ltam.LTAMCustomerData;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

@ManagedBean(name = "ltamSaveDataDAO")
@SessionScoped
public class LTAMSaveDataDAO extends JdbcDaoSupport implements Serializable
{
   private static LTAMSaveDataDAO instance = null;
   DBConnection dbconnection;
   SQLData convert;
   DataSource localds;

/*
   public static synchronized LTAMSaveDataDAO getInstance()
   {
      if (instance == null)
      {
         instance = new LTAMSaveDataDAO();
      }

      return instance;
   }

   public LTAMSaveDataDAO()
   {
      dbconnection = DBConnection.getInstance();
      convert = new SQLData();
      localds = dbconnection.getMySQLDataSource();
   }

   private DataSource getDS() {
      DataSource datasource;

      datasource = getDataSource();
      if (datasource == null)
         datasource = localds;

      return datasource;

   }

   public Long saveLTAMVisitor(LTAMCustomerData data)
   {
      DataSource ds = getDS();
      LTAMSaveSP sp = new LTAMSaveSP(ds, "save_visitor",0);
      Long logonid = 0L;
      Map outMap = sp.saveLTAMVisitor(data);
      if (outMap != null) {
         logonid = ((Long) outMap.get("p_logonid")).longValue();
         // data.setLogonid(logonid);
      }
      return (logonid);
   }

   public Long saveLTAMUserData(LTAMCustomerData data)
   {
      DataSource ds = getDS();
      LTAMSaveSP sp = new LTAMSaveSP(ds, "save_acct_info",1);
      Long acctnum = 0L;
      Map outMap = sp.saveLTAMUserData(data);
      acctnum = ((Long) outMap.get("p_acctnum")).longValue();
      return (acctnum);
   }

   public String savePostBack(String myacctnum, String advisor, String ext_acctnum)
   {
      DataSource ds = getDS();
      LTAMSaveSP sp = new LTAMSaveSP(ds, "save_acct_info_ack",2);
      Map outMap = sp.savePostBack(myacctnum, advisor, ext_acctnum);
      String msg = ((String) outMap.get("p_msg"));
      return (msg);
   }
*/

}