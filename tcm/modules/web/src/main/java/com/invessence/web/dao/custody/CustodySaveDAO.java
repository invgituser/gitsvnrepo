package com.invessence.web.dao.custody;

import java.io.Serializable;
import java.util.*;
import javax.faces.bean.*;
import javax.sql.DataSource;

import com.invessence.converter.SQLData;
import com.invessence.web.data.custody.*;
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
public class CustodySaveDAO extends JdbcDaoSupport
{
   SQLData convert = new SQLData();
   public Boolean td_saveRequest(TDRequest data)
   {
      DataSource ds = getDataSource();
      CustodySaveSP sp = new CustodySaveSP(ds, "save_tddc_requests",0);
      Map outMap = sp.td_saveRequest(data);
      if (outMap == null)
         return (false);
      else
         return true;
   }

   public Boolean td_saveAccountDetail(TDAcctdetails data )
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

   public Boolean td_saveBenefiaciaryDetails(ArrayList<BenefiaciaryDetails> beneTempList)
   {

      CustodySaveSP sp = new CustodySaveSP(getDataSource(), "save_tddc_benefiaciary_details",8);
      Map outMap= null;
      for(BenefiaciaryDetails data:beneTempList)
      {
           outMap = sp.td_saveBenefiaciaryDetails(data);
      }

      if (outMap == null)
         return (false);
      else
         return true;
   }

}
