package com.invessence.dao.ltam;

import java.io.Serializable;
import java.util.Map;
import javax.faces.bean.*;
import javax.sql.DataSource;

import com.invessence.converter.SQLData;
import com.invessence.data.common.CustomerData;
import com.invessence.data.consumer.CTO.ClientData;
import com.invessence.data.ltam.LTAMCustomerData;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

@ManagedBean(name = "ltamSaveDataDAO")
@SessionScoped
public class LTAMSaveDataDAO extends JdbcDaoSupport implements Serializable
{
   SQLData convert = new SQLData();

   public Long saveLTAMVisitor(LTAMCustomerData data)
   {
      DataSource ds = getDataSource();
      LTAMSaveSP sp = new LTAMSaveSP(ds, "ltam.save_visitor",0);
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
      DataSource ds = getDataSource();
      LTAMSaveSP sp = new LTAMSaveSP(ds, "ltam.save_acct_info",1);
      Long acctnum = 0L;
      Map outMap = sp.saveLTAMUserData(data);
      acctnum = ((Long) outMap.get("p_acctnum")).longValue();
      return (acctnum);
   }
}