package com.invessence.web.dao.custody;

import java.io.Serializable;
import java.util.*;
import javax.faces.bean.*;
import javax.sql.DataSource;

import com.invessence.converter.SQLData;
import com.invessence.web.data.custody.TDMasterData;
import com.invessence.web.data.custody.td.*;
import org.apache.xpath.operations.Bool;
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
   public Long tdSaveMoveMoneyPay(TDMasterData tdMasterData)
   {
      long  payId=0;
      DataSource ds = getDataSource();
      CustodySaveSP sp = new CustodySaveSP(ds, "save_tddc_movemoney_paymethod",10);
      Map outMap = sp.tdSaveMoveMoneyPay(tdMasterData);
      if (outMap != null)
      {
         payId=(Long) outMap.get("p_moveMoneyPayMethId");
      }
     return payId;
   }
   public Boolean tdSaveMoveMoneyDetails(Long reqId,TDMasterData tdMasterData)
   {
      Boolean saveFalg=true;
      DataSource ds = getDataSource();
      CustodySaveSP sp = new CustodySaveSP(ds, "save_tddc_move_money_details",11);
      Map outMap = sp.tdSaveMoveMoneyDetails(reqId,tdMasterData);
      if (outMap == null)
         saveFalg=false;
      return saveFalg;
   }

   public boolean tdSaveElectronicPaymentData(TDMasterData tdMasterData )
   {
      if(tdMasterData.getFundType().equalsIgnoreCase("PMACH") && tdMasterData.getOwnerSPF())
      {
         DataSource ds = getDataSource();
         Request reqdata = new Request();
         reqdata.setReqId(new Long(0));
         reqdata.setEventNum(0);
         reqdata.setRequestFor("EFT");
         reqdata.setAcctnum(tdMasterData.getAcctnum());
         reqdata.setReqType("ELEC_FUND_TRAN_NEW");
         reqdata.setEnvelopeHeading("Please sign electronic fund transfer document.");
         tdOpenAccount(reqdata);
         tdMasterData.getRequest().setEventNum(reqdata.getEventNum());
            if (reqdata.getReqId() > 0)
            {
               Long moveMoneyPayMethId = tdSaveMoveMoneyPay(tdMasterData);
               ElectronicFundDetails electronicFundDetails = tdMasterData.getElectroicBankDetail();
               electronicFundDetails.setAcctnum(tdMasterData.getAcctnum());
               tdMasterData.getElectroicBankDetail().setReqId(reqdata.getReqId());
               tdMasterData.getElectroicBankDetail().setEftInstId("EFINEW");
               tdMasterData.getElectroicBankDetail().setDirectionId("EFDTOTD");
               tdMasterData.getElectroicBankDetail().setMoveMoneyPayMethodID(moveMoneyPayMethId);
               tdMasterData.getElectroicBankDetail().setAchId(tdMasterData.getAchBankDetail().getAchId().intValue());
               electronicFundDetails.setTranAmount(tdMasterData.getInitialInvestment());

               Boolean elecFundSave = tdSaveElectronicPayment(tdMasterData.getAcctnum(), tdMasterData.getElectroicBankDetail());
            }
         }
         else
         {
            DataSource ds = getDataSource();
            Long moveMoneyPayMethId=tdSaveMoveMoneyPay(tdMasterData);
            if(moveMoneyPayMethId==0)
            {
               return false;
            }
            tdMasterData.getElectroicBankDetail().setMoveMoneyPayMethodID(moveMoneyPayMethId);
            long achId=0;
            tdMasterData.getElectroicBankDetail().setAchId(0);
            achId=tdSaveACHFund(tdMasterData.getElectroicBankDetail());

            Request reqdata=new Request();
            if(achId>0)
            {
                     reqdata.setReqId(new Long(0));
                     reqdata.setEventNum(0);
                     reqdata.setAcctnum(tdMasterData.getAcctnum());
                     reqdata.setReqType("ELEC_FUND_TRAN_NEW");
                     reqdata.setRequestFor("EFT");
                     reqdata.setEnvelopeHeading("Please sign electronic fund transfer document.");
                     tdOpenAccount(reqdata);
                     if(reqdata.getReqId()>0)
                     {
                        ElectronicFundDetails electronicFundDetails=tdMasterData.getElectroicBankDetail();
                        electronicFundDetails.setAcctnum(tdMasterData.getAcctnum());
                        electronicFundDetails.setReqId(reqdata.getReqId());
                        electronicFundDetails.setEftInstId("EFINEW");
                        electronicFundDetails.setDirectionId("EFDTOTD");
                        electronicFundDetails.setMoveMoneyPayMethodID(moveMoneyPayMethId);
                        electronicFundDetails.setAchId((int)achId);
                        electronicFundDetails.setTranAmount(tdMasterData.getElectroicBankDetail().getTranAmount());
                       Boolean elecFundSave=tdSaveElectronicPayment(tdMasterData.getAcctnum(),tdMasterData.getElectroicBankDetail());
                     }

            }
         }
      return true;
   }

  public Boolean tdsaveACHData(TDMasterData tdMasterData,String dataFlag )
  {
     DataSource ds = getDataSource();
     Long moveMoneyPayMethId=tdSaveMoveMoneyPay(tdMasterData);
     if(moveMoneyPayMethId==0)
     {
        return false;
     }
     if(dataFlag.equalsIgnoreCase("ACH"))
         tdMasterData.getAchBankDetail().setMoveMoneyPayMethodID(moveMoneyPayMethId);
     else
         tdMasterData.getElectroicBankDetail().setMoveMoneyPayMethodID(moveMoneyPayMethId);
     long achId=0;
        if(dataFlag.equalsIgnoreCase("EFT"))
           achId=tdSaveACHFund(tdMasterData.getElectroicBankDetail());
      else
        {
           achId= tdSaveACH(tdMasterData.getAchBankDetail());
        }
     Request reqdata=new Request();
     if(achId>0)
     {
        tdMasterData.getAchBankDetail().setAchId(achId);
        reqdata.setReqId(new Long(0));
        reqdata.setEventNum(0);
        reqdata.setAcctnum(tdMasterData.getAcctnum());
        reqdata.setRequestFor("ACH");

        if(tdMasterData.getAcctdetail().getAcctTypeId().equalsIgnoreCase("ACINDIV") ||
           tdMasterData.getAcctdetail().getAcctTypeId().equalsIgnoreCase("ACJOINT") ||
           tdMasterData.getAcctdetail().getAcctTypeId().equalsIgnoreCase("ACCSTD") )
                  reqdata.setReqType("MOVE_MONEY_NEW");
        else
           reqdata.setReqType("IRA_MOVE_MONEY_NEW");


        reqdata.setEnvelopeHeading("Please sign move money document.");
        tdOpenAccount(reqdata);
        if(reqdata.getReqId()>0)
        {
           tdMasterData.getRequest().setEventNum(reqdata.getEventNum());
           Boolean movemoneySave=tdSaveMoveMoneyDetails(reqdata.getReqId(),tdMasterData);
           if(movemoneySave)
           {
              reqdata.setReqId(new Long(0));
              reqdata.setEventNum(0);
              reqdata.setAcctnum(tdMasterData.getAcctnum());
              reqdata.setReqType("ELEC_FUND_TRAN_NEW");
              reqdata.setRequestFor("ACH");
              reqdata.setEnvelopeHeading("Please sign electronic fund transfer document.");
              tdOpenAccount(reqdata);
              if(reqdata.getReqId()>0)
              {
                 ElectronicFundDetails electronicFundDetails=tdMasterData.getElectroicBankDetail();
                 electronicFundDetails.setAcctnum(tdMasterData.getAcctnum());
                 electronicFundDetails.setReqId(reqdata.getReqId());
                 electronicFundDetails.setEftInstId("EFINEW");
                 electronicFundDetails.setDirectionId("EFDTOTD");
                 electronicFundDetails.setMoveMoneyPayMethodID(moveMoneyPayMethId);
                 electronicFundDetails.setAchId(tdMasterData.getAchBankDetail().getAchId().intValue());
                 if(dataFlag.equalsIgnoreCase("EFT"))
                 {
                    electronicFundDetails.setTranAmount(tdMasterData.getElectroicBankDetail().getTranAmount());
                 }
                 else
                 {
                    electronicFundDetails.setTranAmount(tdMasterData.getInitialInvestment());
                    electronicFundDetails.setTranFreqId("ONETIME");
                 }
                 Boolean elecFundSave=tdSaveElectronicPayment(tdMasterData.getAcctnum(),tdMasterData.getElectroicBankDetail());
              }
           }
           else
              return false;
        }
     }


     return true;
  }
  // public Boolean tdSaveACH(String ftype, Boolean ownerSPF, Long acctnum, Double initialInv, String fundType, AchBankDetail data)
   public Long tdSaveACH(AchBankDetail bankDetail )
{
   long  achId=0;
   DataSource ds = getDataSource();
   CustodySaveSP sp = new CustodySaveSP(ds, "save_tddc_ach_bank_details",5);
   Map outMap = sp.tdSaveACH(bankDetail);

   if (outMap != null)
   {
      achId=Long.valueOf(outMap.get("p_achId").toString());
   }

   return achId;
}
   public Long tdSaveACHFund(ElectronicFundDetails bankDetail )
   {
      long  achId=0;
      DataSource ds = getDataSource();
      CustodySaveSP sp = new CustodySaveSP(ds, "save_tddc_ach_bank_details",5);
      Map outMap = sp.tdSaveACHFund(bankDetail);

      if (outMap != null)
      {
         achId=Long.valueOf(outMap.get("p_achId").toString());
      }

      return achId;
   }

   public Boolean tdSaveACAT(TDMasterData tdMasterData,Long acctnum, ACATDetails data)
   {
      DataSource ds = getDataSource();
      Request reqdata=new Request();
      reqdata.setReqId(new Long(0));
      reqdata.setEventNum(0);
      reqdata.setAcctnum(acctnum);
      reqdata.setReqType("ACCT_TRAN_NEW");
      reqdata.setRequestFor("ACAT");
      reqdata.setEnvelopeHeading("Please sign account transfer document.");
      tdOpenAccount(reqdata);
      tdMasterData.getRequest().setEventNum(reqdata.getEventNum());
      CustodySaveSP sp = new CustodySaveSP(ds, "save_tddc_acct_transfer_details",6);
      Map outMap = sp.tdSaveACAT(acctnum,reqdata.getReqId(), data);
      if (outMap == null)
         return (false);
      else
         return true;
   }

   public Boolean tdSaveTDTransferData(TDMasterData tdMasterData,Long acctnum, TDTransferDetails data)
   {
      DataSource ds = getDataSource();
      Request reqdata=new Request();
      reqdata.setReqId(new Long(0));
      reqdata.setEventNum(0);
      reqdata.setAcctnum(acctnum);
      reqdata.setReqType("TD_TRAN_NEW");
      reqdata.setRequestFor("TDTRF");
      reqdata.setEnvelopeHeading("Please sign TD transfer document.");
      tdOpenAccount(reqdata);
      tdMasterData.getRequest().setEventNum(reqdata.getEventNum());
      CustodySaveSP sp = new CustodySaveSP(ds, "save_tddc_TD_transfer_details",14);
      Map outMap = sp.tdSaveTDTransferData(acctnum,reqdata.getReqId(), data);
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

   public Boolean tdSaveElectronicPayment(Long acctnum,ElectronicFundDetails data)
   {
      DataSource ds = getDataSource();
      // comment as same procedure being called for ACH and electronic fund
      CustodySaveSP sp = new CustodySaveSP(ds, "save_tddc_elecfund_transfer_details",8);
      Map outMap = sp.tdSaveElectronicPayment(acctnum, data);
      if (outMap == null)
         return (false);
      else
         return true;

     /* Request reqdata=new Request();
      reqdata.setReqId(new Long(0));
      reqdata.setEventNum(0);
      reqdata.setAcctnum(acctnum);
      reqdata.setReqType("MOVE_MONEY_NEW");
      reqdata.setEnvelopeHeading("Please sign move money document.");
      tdOpenAccount(reqdata);
      CustodySaveSP sp = new CustodySaveSP(ds, "save_tddc_ach_bank_details",5);
      Map outMap = sp.tdSaveElectronicPayment(ftype,ownerSPF,acctnum,initialInv,fundType, data);*/

   }

   public Boolean tdOpenAccount(Request data )
   {
      DataSource ds = getDataSource();
      CustodySaveSP sp = new CustodySaveSP(ds, "save_tddc_requests",0);
      Map outMap = sp.tdSaveRequest(data);
      if (outMap == null)
         return (false);
      else
         return true;
   }
   public Boolean tdMangedUserProfile(long data )
   {
      DataSource ds = getDataSource();
      CustodySaveSP sp = new CustodySaveSP(ds, "sp_user_profile_manage",13);
      Map outMap = sp.tdMangedUserProfile(data);
      if (outMap == null)
         return (false);
      else
         return true;
   }
   public Boolean tdCheckRequest(TDMasterData data )
   {
      DataSource ds = getDataSource();
      CustodySaveSP sp = new CustodySaveSP(ds, "save_tddc_requests_final",12);
      Map outMap = sp.tdCheckRequest(data);
      if (outMap == null)
         return (false);
      else
         return true;
   }
}
