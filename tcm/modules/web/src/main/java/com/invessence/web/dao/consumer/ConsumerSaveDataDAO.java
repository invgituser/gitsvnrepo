package com.invessence.web.dao.consumer;

import java.io.Serializable;
import java.util.*;
import javax.faces.bean.*;
import javax.sql.DataSource;

import com.invessence.converter.SQLData;
import com.invessence.web.data.common.CustomerData;
import com.invessence.web.data.consumer.CTO.ClientData;
import com.invessence.web.data.consumer.RiskCalculator;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

@ManagedBean(name = "consumerSaveDataDAO")
@SessionScoped
public class ConsumerSaveDataDAO extends JdbcDaoSupport implements Serializable
{
   SQLData convert = new SQLData();

   public Long saveProfileData( CustomerData mgoal)
   {
      DataSource ds = getDataSource();
      ConsumerSaveSP sp = new ConsumerSaveSP(ds, "save_user_trade_profile",0);
      Long acctnum = 0L;
      Map outMap = sp.saveProfileData(mgoal);
      acctnum = ((Long) outMap.get("p_acctnum")).longValue();
      mgoal.setAcctnum(acctnum);
      return (acctnum);
   }

   public void saveFinancials(CustomerData mgoal)
   {
      DataSource ds = getDataSource();
      ConsumerSaveSP sp = new ConsumerSaveSP(ds, "save_user_financial_data",1);
      sp.saveFinancials(mgoal);
   }

   public void saveRiskProfile(Long acctnum, RiskCalculator data)
   {
      DataSource ds = getDataSource();
      ConsumerSaveSP sp = new ConsumerSaveSP(ds, "sav_user_risk_questions",2);
      sp.saveRiskProfile(acctnum, data);
   }

   public void saveAllocation(CustomerData mgoal )
   {
      DataSource ds = getDataSource();
      ConsumerSaveSP sp = new ConsumerSaveSP(ds, "del_asset_alloc",4);
      sp.deleteAllocation(mgoal);
      ConsumerSaveSP sp2 = new ConsumerSaveSP(ds, "sp_asset_alloc_add_mod",5);
      sp2.saveAllocation(mgoal);
   }

   public void savePortfolio(CustomerData mgoal )
   {
      DataSource ds = getDataSource();
      ConsumerSaveSP sp = new ConsumerSaveSP(ds, "del_virtual_portfolio",6);
      sp.deletePortfolio(mgoal);
      ConsumerSaveSP sp2 = new ConsumerSaveSP(ds, "sp_virtual_portfolio_add_mod",7);
      sp2.savePortfolio(mgoal);
   }

   public void saveClientInfo(ClientData data)
   {
      DataSource ds = getDataSource();
      ConsumerSaveSP sp = new ConsumerSaveSP(ds, "sp_clientinfo_add_mod",8);
      sp.saveClientInfo(data);
   }

   public void saveClientInfo2(ClientData data)
   {
      DataSource ds = getDataSource();
      ConsumerSaveSP sp = new ConsumerSaveSP(ds, "sp_clientinfo_mod",9);
      sp.saveClientInfo(data);
   }

   public void createTrades(Long acctnum) {
      DataSource ds = getDataSource();
      ConsumerSaveSP sp = new ConsumerSaveSP(ds, "sp_createTrades",10);
      sp.createTrades(acctnum);
   }

   public void saveClientEmpInfo(ClientData data)
   {
      DataSource ds = getDataSource();
      ConsumerSaveSP sp = new ConsumerSaveSP(ds, "sp_clientemp_info_add_mod",11);
      sp.saveClientEmpInfo(data);
   }

}