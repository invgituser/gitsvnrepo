package com.invessence.web.dao.custody;

import java.io.Serializable;
import java.util.Map;
import javax.faces.bean.*;
import javax.sql.DataSource;

import com.invessence.converter.SQLData;
import com.invessence.web.data.consumer.CTO.AccountHolder;
import com.invessence.web.data.custody.TDMasterData;
import com.invessence.ws.data.common.AcctOwnersDetails;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 8/20/16
 * Time: 11:26 AM
 * To change this template use File | Settings | File Templates.
 */

@ManagedBean(name = "custodySaveDAO")
@SessionScoped
public class CustodySaveDAO extends JdbcDaoSupport implements Serializable
{
   SQLData convert = new SQLData();

   public Boolean td_saveRequest(TDMasterData data)
   {
      DataSource ds = getDataSource();
      CustodySaveSP sp = new CustodySaveSP(ds, "save_tddc_requests",0);
      Map outMap = sp.td_saveRequest(data);
      if (outMap == null)
         return (false);
      else
         return true;
   }

   public Boolean td_saveAccountDetail(TDMasterData data )
   {
      DataSource ds = getDataSource();
      CustodySaveSP sp = new CustodySaveSP(ds, "save_tddc_acct_details",1);
      Map outMap = sp.td_saveAccountDetail(data);
      if (outMap == null)
         return (false);
      else
         return true;
   }

   public Boolean td_saveAccountOwner(AcctOwnersDetails data )
   {
      DataSource ds = getDataSource();
      CustodySaveSP sp = new CustodySaveSP(ds, "save_tddc_acct_owners_details",1);
      Map outMap = sp.td_saveAccountOwner(data);
      if (outMap == null)
         return (false);
      else
         return true;
   }


}
