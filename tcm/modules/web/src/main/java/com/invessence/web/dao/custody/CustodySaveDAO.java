package com.invessence.web.dao.custody;

import java.io.Serializable;
import java.util.*;
import javax.faces.bean.*;
import javax.sql.DataSource;

import com.invessence.converter.SQLData;
import com.invessence.web.data.custody.td.*;
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

   public Boolean tdSaveRequest(Request data)
   {
      DataSource ds = getDataSource();
      CustodySaveSP sp = new CustodySaveSP(ds, "save_tddc_requests",0);
      Map outMap = sp.tdSaveRequest(data);
      if (outMap == null)
         return (false);
      else
         return true;
   }

   public Boolean tdSaveAccountDetail(Acctdetails data )
   {
      DataSource ds = getDataSource();
      CustodySaveSP sp = new CustodySaveSP(ds, "save_tddc_acct_details",1);
      Map outMap = sp.tdSaveAccountDetail(data);
      if (outMap == null)
         return (false);
      else
         return true;
   }

   public Boolean tdSaveAccountOwner(AcctOwnersDetails data )
   {
      DataSource ds = getDataSource();
      CustodySaveSP sp = new CustodySaveSP(ds, "save_tddc_acct_owners_details",2);
      Map outMap = sp.tdSaveAccountOwner(data);
      if (outMap == null)
         return (false);
      else
         return true;
   }

   public Boolean tdSaveEmployment(EmploymentDetails data)
   {
      DataSource ds = getDataSource();
      CustodySaveSP sp = new CustodySaveSP(ds, "save_tddc_employment_details",3);
      Map outMap = sp.tdSaveEmployment(data);
      if (outMap == null)
         return (false);
      else
         return true;
   }
   public Boolean saveBenefiaciaryDetails(ArrayList<BenefiaciaryDetails> beneTempList)
   {

      CustodySaveSP sp = new CustodySaveSP(getDataSource(), "save_tddc_benefiaciary_details",4);
      Map outMap= null;
      for(BenefiaciaryDetails data:beneTempList)
      {
           outMap = sp.tdSaveBeneficiary(data);
      }

      if (outMap == null)
         return (false);
      else
         return true;
   }

   public Boolean saveBenefiaciaryDetails(BenefiaciaryDetails data)
   {
      DataSource ds = getDataSource();
      CustodySaveSP sp = new CustodySaveSP(ds, "save_tddc_benefiaciary_details",4);
      Map outMap = sp.tdSaveBeneficiary(data);
      if (outMap == null)
         return (false);
      else
         return true;
   }

   public Boolean tdSaveACH(Long acctnum,Double initialInv,String fundType, AchBankDetail data)
   {
      DataSource ds = getDataSource();
      CustodySaveSP sp = new CustodySaveSP(ds, "save_tddc_ach_bank_details",5);
      Map outMap = sp.tdSaveACH(acctnum,initialInv,fundType, data);
      if (outMap == null)
         return (false);
      else
         return true;
   }

   public Boolean tdSaveACAT(Long acctnum, ACATDetails data)
   {
      DataSource ds = getDataSource();
      CustodySaveSP sp = new CustodySaveSP(ds, "save_tddc_acct_transfer_details",6);
      Map outMap = sp.tdSaveACAT(acctnum, data);
      if (outMap == null)
         return (false);
      else
         return true;
   }

   public Boolean tdSaveFEDWire(Long acctnum, FedwireAcctDetails data)
   {
      DataSource ds = getDataSource();
      CustodySaveSP sp = new CustodySaveSP(ds, "save_tddc_fedwire_acct_details",7);
      Map outMap = sp.tdSaveFEDWire(acctnum, data);
      if (outMap == null)
         return (false);
      else
         return true;
   }

   public Boolean tdSaveElectronicPayment(Long acctnum, ElectronicFundDetails data)
   {
      DataSource ds = getDataSource();
      CustodySaveSP sp = new CustodySaveSP(ds, "save_tddc_elecfund_transfer_details",8);
      Map outMap = sp.tdSaveElectronicPayment(acctnum, data);
      if (outMap == null)
         return (false);
      else
         return true;
   }
}
