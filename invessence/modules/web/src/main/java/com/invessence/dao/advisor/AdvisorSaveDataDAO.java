package com.invessence.dao.advisor;

import java.io.Serializable;
import java.util.Map;
import javax.faces.bean.*;
import javax.sql.DataSource;

import com.invessence.data.advisor.AdvisorData;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;

@ManagedBean(name = "advisorSaveDataDAO")
@ApplicationScoped
public class AdvisorSaveDataDAO extends SimpleJdbcDaoSupport implements Serializable
{
   public Long saveProfile(AdvisorData data) {
      DataSource ds = getDataSource();
      AdvisorSaveSP sp = new AdvisorSaveSP(ds, "sp_adv_user_trade_profile",0);
      Map outMap = sp.saveProfile(data);
      Long acctnum = ((Long) outMap.get("p_acctnum")).longValue();
      data.setAcctnum(acctnum);
      return acctnum;
   }

   public void saveAllocation(AdvisorData data) {
      DataSource ds = getDataSource();
      AdvisorSaveSP sp1 = new AdvisorSaveSP(ds, "del_asset_alloc",1);
      sp1.deleteAllocation(data);
      AdvisorSaveSP sp = new AdvisorSaveSP(ds, "sp_asset_alloc_add_mod",2);
      sp.saveAllocation(data);
   }

   public void savePortfolio(AdvisorData data) {
      DataSource ds = getDataSource();
      AdvisorSaveSP sp1 = new AdvisorSaveSP(ds, "del_virtual_portfolio",3);
      sp1.deletePortfolio(data);
      AdvisorSaveSP sp = new AdvisorSaveSP(ds, "sp_virtual_portfolio_add_mod",4);
      sp.savePortfolio(data);
   }

   public void saveExcludeSubClass(AdvisorData data) {
      DataSource ds = getDataSource();
      AdvisorSaveSP sp1 = new AdvisorSaveSP(ds, "del_ExcludedSubclass",5);
      sp1.deleteExcludedSubclass(data);
      AdvisorSaveSP sp = new AdvisorSaveSP(ds, "sp_ExcludedSubclass_add_mod",6);
      sp.saveExcludedSubclass(data);
   }

}

