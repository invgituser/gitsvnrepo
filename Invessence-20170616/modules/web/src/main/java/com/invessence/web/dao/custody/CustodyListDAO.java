package com.invessence.web.dao.custody;

import java.io.Serializable;
import java.util.*;
import javax.faces.bean.*;
import javax.sql.DataSource;

import com.invessence.web.data.custody.td.*;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import com.invessence.converter.SQLData;
import com.invessence.web.data.custody.TDMasterData;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 8/20/16
 * Time: 11:26 AM
 * To change this template use File | Settings | File Templates.
 */

@ManagedBean(name = "custodyListDAO")
@SessionScoped
public class CustodyListDAO extends JdbcDaoSupport implements Serializable
{
   SQLData convert = new SQLData();


   public void getTDAccountDetails(TDMasterData data)
   {
      DataSource ds = getDataSource();
      CustodyListSP sp = new CustodyListSP(ds, "sel_tddc_acct_details",0);
      Map outMap = sp.getTDAccountDetails(data.getAcctnum());
      try
      {
         if (outMap != null)
         {
            Integer whichAcct;
            ArrayList<Map<String, Object>> rows = (ArrayList<Map<String, Object>>) outMap.get("#result-set-1");
            if (rows == null)
               return;
            int i = 0;
            for (Map<String, Object> map : rows)
            {
               Map rs = (Map) rows.get(i);
               String accttype = convert.getStrData(rs.get("acctTypeId"));
               Long advisorID = convert.getLongData(rs.get("advisorId"));
               advisorID = (advisorID == null || advisorID == 0) ? 1 : advisorID;

               data.getAcctdetail().setAcctnum(convert.getLongData(rs.get("acctnum")));
               data.getAcctdetail().setClientAccountID(convert.getStrData(rs.get("clientAccountID")));
               data.getAcctdetail().setCaseNumber(convert.getStrData(rs.get("caseNumber")));
               data.getAcctdetail().setAdvisorId(advisorID);
               data.getAcctdetail().setAcctTypeId(accttype);
               data.loadAcctType(accttype);

               data.getAcctdetail().setCashSweepVehicleChoiceId(convert.getStrData(rs.get("cashSweepVehicleChoiceId")));
               data.getAcctdetail().setDivIntPrefId(convert.getStrData(rs.get("divIntPrefId")));
               data.getAcctdetail().setMonthStmtId(convert.getStrData(rs.get("monthStmtId")));
               data.getAcctdetail().setTradConfId(convert.getStrData(rs.get("tradConfId")));
               data.getAcctdetail().setDupStatement(convert.getStrData(rs.get("dupStatement")));
               data.getAcctdetail().setDupTradeConfirm(convert.getStrData(rs.get("tradConfId")));
               data.getAcctdetail().setProxyAuthorizationId(convert.getStrData(rs.get("proxyAuthorizationId")));
               if(convert.getStrData(rs.get("optoutRegulatory"))==null || convert.getStrData(rs.get("optoutRegulatory")).equalsIgnoreCase("N") )
               {
                  data.setOptoutRegulatory(false);
               }
               else
               {
                  data.setOptoutRegulatory(true);
               }

               if(convert.getStrData(rs.get("optoutBeneficiary"))==null || convert.getStrData(rs.get("optoutBeneficiary")).equalsIgnoreCase("N") )
               {
                  data.setOptoutBeneficiary(false);
               }
               else
               {
                  data.setOptoutBeneficiary(true);
               }
               if(convert.getStrData(rs.get("optoutFunding"))==null || convert.getStrData(rs.get("optoutFunding")).equalsIgnoreCase("N") )
               {
                  data.setOptoutFunding(false);
               }
               else
               {
                  data.setOptoutFunding(true);
               }
               if(convert.getStrData(rs.get("optoutRecurring"))==null || convert.getStrData(rs.get("optoutRecurring")).equalsIgnoreCase("N") )
               {
                  data.setOptoutRecurring(false);
               }
               else
               {
                  data.setOptoutRecurring(true);
               }

            }
         }
      }
      catch (Exception ex) {
         logger.error("Error occurred in getTDAccountDetails  :" +ex);
         ex.printStackTrace();
      }
   }

   public void getTDAccountHolder( TDMasterData data)
   {
      DataSource ds = getDataSource();
      CustodyListSP sp = new CustodyListSP(ds, "sel_tddc_acct_owner",0);
      Map outMap = sp.getTDAccountHolder(data.getAcctnum());
      try {
         if (outMap != null)
         {
            Integer whichAcct;
            ArrayList<Map<String, Object>> rows = (ArrayList<Map<String, Object>>) outMap.get("#result-set-1");
            if (rows == null)
               return;
            int i = 0;
            for (Map<String, Object> map : rows)
            {
               Map rs = (Map) rows.get(i);
               AcctOwnersDetails acctHolder = null;
               whichAcct = convert.getIntData(rs.get("acctOwnerId"));
               switch (whichAcct) {
                  case 1:
                     acctHolder = data.getAcctOwnersDetail();
                     break;
                  case 2:
                     acctHolder = data.getJointAcctOwnersDetail();
                     break;
                  default:
               }
               if (acctHolder != null) {
               acctHolder.setAcctnum(convert.getLongData(rs.get("acctnum")));
               acctHolder.setAcctOwnerId(convert.getIntData(rs.get("acctOwnerId")));
               acctHolder.setOwnership(convert.getStrData(rs.get("ownership")));
               acctHolder.setFirstName(convert.getStrData(rs.get("firstName")));
               acctHolder.setMidInitial(convert.getStrData(rs.get("midInitial")));
               acctHolder.setLastName(convert.getStrData(rs.get("lastname")));
               acctHolder.setSsn(convert.getStrData(rs.get("ssn")));
               acctHolder.setDob(convert.getStrData(rs.get("dob")));
               acctHolder.setPhoneNumber(convert.getStrData(rs.get("phoneNumber")));
               acctHolder.setPhoneNumberNonUS(convert.getStrData(rs.get("phoneNumberNonUS")));
               acctHolder.setSecondPhoneNumber(convert.getStrData(rs.get("secondPhoneNumber")));
               acctHolder.setSecondPhoneNumberNonUS(convert.getStrData(rs.get("secondPhoneNumberNonUS")));
               acctHolder.setEmailAddress(convert.getStrData(rs.get("emailAddress")));
               acctHolder.setPhysicalAddressStreet(convert.getStrData(rs.get("physicalAddressStreet")));
               acctHolder.setPhysicalAddressCity(convert.getStrData(rs.get("physicalAddressCity")));
               acctHolder.setPhysicalAddressState(convert.getStrData(rs.get("physicalAddressState")));
               acctHolder.setPhysicalAddressZipCode(convert.getStrData(rs.get("physicalAddressZipCode")));
               acctHolder.setMailingAddressStreet(convert.getStrData(rs.get("mailingAddressStreet")));
               acctHolder.setMailingAddressCity(convert.getStrData(rs.get("mailingAddressCity")));
               acctHolder.setMailingAddressState(convert.getStrData(rs.get("mailingAddressState")));
               acctHolder.setMailingAddressZipCode(convert.getStrData(rs.get("mailingAddressZipCode")));
               acctHolder.setCitizenshiId(convert.getStrData(rs.get("citizenshiId")));
                  if(acctHolder.getCitizenshiId()!=null && acctHolder.getCitizenshiId().equalsIgnoreCase("USCITZ"))
                     acctHolder.setCitizenShipFlag(true);
               acctHolder.setCountryOfCitizenship(convert.getStrData(rs.get("countryOfCitizenship")));
               acctHolder.setCountryOfBirth(convert.getStrData(rs.get("countryOfBirth")));
                  if (whichAcct == 1)
                  {
                     acctHolder.setSPF(convert.getStrData(rs.get("isSPF")));
                     data.setSenoirPolitical(false);
                     if (acctHolder.getSPF() != null && acctHolder.getSPF().equalsIgnoreCase("Y"))
                     {
                        data.setSenoirPolitical(true);
                        String spfDet = convert.getStrData(rs.get("spfDetail"));
                        String[] array = spfDet.split(",");
                        acctHolder.setSpfName(array[0]);
                        acctHolder.setSpfRelationship(array[1]);
                        acctHolder.setSpfTitle(array[2]);
                        acctHolder.setSpfCountry(array[3]);
                     }

                     acctHolder.setDirectorShareholder(convert.getStrData(rs.get("isDirectorShareholder")));
                     data.setOwnerShare(false);
                     if (acctHolder.getDirectorShareholder() != null && acctHolder.getDirectorShareholder().equalsIgnoreCase("Y"))
                     {
                        data.setOwnerShare(true);
                        String shreDet = convert.getStrData(convert.getStrData(rs.get("directorShareholderDetail")));
                        String[] array = shreDet.split(",");
                        acctHolder.setShareholderCompany(array[0]);
                        acctHolder.setShareholderAddress(array[1]);
                        acctHolder.setShareholderCity(array[2]);
                        acctHolder.setShareholderState(array[3]);

                     }
                     acctHolder.setBd(convert.getStrData(rs.get("bd")));
                     data.setOwnerBD(false);
                     if (acctHolder.getBd() != null && acctHolder.getBd().equalsIgnoreCase("Y"))
                     {
                        data.setOwnerBD(true);
                        acctHolder.setBdDetail(convert.getStrData(rs.get("bdDetail")));
                     }

                  }
                  acctHolder.setOwnership(convert.getStrData(rs.get("ownershipPercent")));
               acctHolder.setCreatedBy(convert.getStrData(rs.get("createdBy")));
                  i++;
                  if(whichAcct==1)
                  {
                     data.setAcctOwnersDetail(acctHolder);
                     if(convert.getStrData(rs.get("mailingAddressStreet"))!=null  && !convert.getStrData(rs.get("mailingAddressStreet")).equals(""))
                        data.setAcctholderhasMailing(true);

                  }
                  else
                  {
                     if(convert.getStrData(rs.get("mailingAddressStreet"))!=null && !convert.getStrData(rs.get("mailingAddressStreet")).equals(""))
                        data.setJointhasMailing(true);
                     data.setJointAcctOwnersDetail(acctHolder);
                  }

                  if(whichAcct==2)
                 {
                    if (data.getAcctOwnersDetail().getPhysicalAddressStreet()==null || data.getJointAcctOwnersDetail().getPhysicalAddressStreet()==null ){
                       data.setJointhasDifferent(false);
                    }else if (data.getAcctOwnersDetail().getPhysicalAddressStreet().equalsIgnoreCase(data.getJointAcctOwnersDetail().getPhysicalAddressStreet())){
                       data.setJointhasDifferent(false);
                    }else{
                       data.setJointhasDifferent(true);
                    }
                  }
               }
            }
         }
      }
      catch (Exception ex) {
         logger.error("Error occurred in getTDAccountHolder  :" +ex);
         ex.printStackTrace();

      }
   }

   public void getTDEmployment(TDMasterData data)
   {
      DataSource ds = getDataSource();
      CustodyListSP sp = new CustodyListSP(ds, "sel_tddc_employment",1);
      Map outMap = sp.getTDEmployment(data.getAcctnum());
      try
      {
         if (outMap != null)
         {
            Integer whichAcct;
            ArrayList<Map<String, Object>> rows = (ArrayList<Map<String, Object>>) outMap.get("#result-set-1");
            if (rows == null)
               return;
            int i = 0;
            for (Map<String, Object> map : rows)
            {
               Map rs = (Map) rows.get(i);
               EmploymentDetails employmentDetails = null;
               whichAcct = convert.getIntData(rs.get("acctOwnerId"));
               switch (whichAcct)
               {
                  case 1:
                     employmentDetails = data.getOwneremploymentDetail();
                     break;
                  case 2:
                     employmentDetails = data.getJointEmploymentDetail();
                     break;
                  default:
               }
               if (employmentDetails != null)
               {
                  employmentDetails.setAcctnum(convert.getLongData(rs.get("acctnum")));
                  employmentDetails.setAcctOwnerId(convert.getIntData(rs.get("acctOwnerId")));
                  employmentDetails.setEmplId(convert.getIntData(rs.get("emplId")));
                  employmentDetails.setEmplTypeId(convert.getStrData(rs.get("emplTypeId")));
                  employmentDetails.setSourceOfIncome(convert.getStrData(rs.get("sourceOfIncome")));
                  employmentDetails.setEmployerName(convert.getStrData(rs.get("employerName")));
                  employmentDetails.setOccupation(convert.getStrData(rs.get("occupation")));;
                  employmentDetails.setTypeOfBusiness(convert.getStrData(rs.get("typeOfBusiness")));
                  employmentDetails.setEmployerStreetAddress(convert.getStrData(rs.get("employerStreetAddress")));
                  employmentDetails.setEmployerCity(convert.getStrData(rs.get("employerCity")));
                  employmentDetails.setEmployerState(convert.getStrData(rs.get("employerState")));
                  employmentDetails.setEmployerZipCode(convert.getStrData(rs.get("employerZipCode")));
                  i++;
                  if(whichAcct==1)
                  {
                     data.setOwneremploymentDetail(employmentDetails);
                  }
                  else
                  {
                     data.setJointEmploymentDetail(employmentDetails);
                  }

               }
            }
         }
      }
      catch (Exception ex) {
         logger.error("Error occurred in getTDEmployment  :" +ex);
         ex.printStackTrace();
      }
   }

   public void getTDBeneficiary(TDMasterData data)
   {
      DataSource ds = getDataSource();
      CustodyListSP sp = new CustodyListSP(ds, "sel_tddc_benefiaciary",2);
      Map outMap = sp.getTDBeneficiary(data.getAcctnum());
      try
      {
         if (outMap != null)
         {
            Integer whichAcct;
            ArrayList<Map<String, Object>> rows = (ArrayList<Map<String, Object>>) outMap.get("#result-set-1");
            if (rows == null)
               return;
            int i = 0;
            ArrayList<BenefiaciaryDetails> benefiaciaryDetailsList =new ArrayList<BenefiaciaryDetails>();
            for (Map<String, Object> map : rows)
            {
               Map rs = (Map) rows.get(i);
               BenefiaciaryDetails benefiaciaryDetails=new BenefiaciaryDetails();
               benefiaciaryDetails.setAcctnum(convert.getLongData(rs.get("acctnum")));
               benefiaciaryDetails.setBeneId(convert.getIntData(rs.get("beneId")));
               benefiaciaryDetails.setBeneFirstName(convert.getStrData(rs.get("beneFirstName")));
               benefiaciaryDetails.setBeneMidInitial(convert.getStrData(rs.get("beneMidInitial")));
               benefiaciaryDetails.setBeneLastName(convert.getStrData(rs.get("beneLastName")));
               benefiaciaryDetails.setBeneSSN(convert.getStrData(rs.get("beneSSN")));
               benefiaciaryDetails.setBeneDOB(convert.getStrData(rs.get("beneDOB")));
               benefiaciaryDetails.setBeneRel(convert.getStrData(rs.get("beneRel")));
               benefiaciaryDetails.setTypeOfBeneficiary(convert.getStrData(rs.get("typeOfBeneficiary")));
               benefiaciaryDetails.setPerStripes(convert.getStrData(rs.get("perStripes")));
               benefiaciaryDetails.setSharePerc(convert.getIntData(rs.get("sharePerc")).longValue());

               benefiaciaryDetailsList.add(benefiaciaryDetails);
               i++;
            }
            data.setBenefiaciaryDetailsList(benefiaciaryDetailsList);
         }
      }
      catch (Exception ex) {
         logger.error("Error occurred in getTDBeneficiary  :" +ex);
         ex.printStackTrace();
      }
   }


   public void getTDACHDetails(TDMasterData data)
   {
      DataSource ds = getDataSource();
      CustodyListSP sp = new CustodyListSP(ds, "sel_tddc_ach_bank",3);
      Map outMap = sp.getTDBeneficiary(data.getAcctnum());
      try
      {
         if (outMap != null)
         {
            Integer whichAcct;
            ArrayList<Map<String, Object>> rows = (ArrayList<Map<String, Object>>) outMap.get("#result-set-1");
            if (rows == null)
               return;
            int i = 0;
            AchBankDetail achBankDetail=new AchBankDetail();
            for (Map<String, Object> map : rows)
            {
               Map rs = (Map) rows.get(i);
               achBankDetail.setMoveMoneyPayMethodID(convert.getLongData(rs.get("moveMoneyPayMethodID")));
               achBankDetail.setAchId(convert.getLongData(rs.get("achId")));
               achBankDetail.setBankAcctType(convert.getStrData(rs.get("bankAcctType")));
               achBankDetail.setBankName(convert.getStrData(rs.get("bankName")));
               achBankDetail.setBankABARouting(convert.getStrData(rs.get("bankABARouting")));
               achBankDetail.setBankCityState(convert.getStrData(rs.get("bankCityState")));
               achBankDetail.setBankPhoneNumber(convert.getStrData(rs.get("bankPhoneNumber")));
               achBankDetail.setBankAcctName(convert.getStrData(rs.get("bankAcctName")));
               achBankDetail.setBankAcctNumber(convert.getStrData(rs.get("bankAcctNumber")));
               data.setInitialInvestment(convert.getDoubleData(rs.get("tranAmount")));
            }
            data.setAchBankDetail(achBankDetail);
         }
      }
      catch (Exception ex) {
         logger.error("Error occurred in getTDACHDetails  :" +ex);
         ex.printStackTrace();
      }
   }

   public void getTDACATDetails(TDMasterData data)
   {
      DataSource ds = getDataSource();
      CustodyListSP sp = new CustodyListSP(ds, "sel_tddc_account_transfer",3);
      Map outMap = sp.getTDACAT(data.getAcctnum());
      try
      {
         if (outMap != null)
         {
            Integer whichAcct;
            ArrayList<Map<String, Object>> rows = (ArrayList<Map<String, Object>>) outMap.get("#result-set-1");
            if (rows == null)
               return;
            int i = 0;
            ACATDetails acatDetails=new ACATDetails();
            for (Map<String, Object> map : rows)
            {
               Map rs = (Map) rows.get(i);
               acatDetails.setAcctnum(convert.getLongData(rs.get("acctnum")));
               acatDetails.setReqId(convert.getLongData(rs.get("reqId")));
               acatDetails.setFromAccountTitle(convert.getStrData(rs.get("fromAccountTitle")));
               acatDetails.setAccountNumber2(convert.getStrData(rs.get("accountNumber2")));
               acatDetails.setFromFirmAddress(convert.getStrData(rs.get("fromFirmAddress")));
               acatDetails.setFromFirmPhoneNumber(convert.getStrData(rs.get("fromFirmPhoneNumber")));
               acatDetails.setFromOtherAccountType(convert.getStrData(rs.get("fromOtherAccountType")));
               acatDetails.setTransferTypeId(convert.getStrData(rs.get("transferTypeId")));
               acatDetails.setContraFirmList(convert.getStrData(rs.get("contraFirmList")));
               acatDetails.setOtherContraFirmList(convert.getStrData(rs.get("othercontraFirmList")));
            }
            data.setAcatDetails(acatDetails);
         }
      }
      catch (Exception ex) {
         logger.error("Error occurred in getTDACATDetails  :" +ex);
         ex.printStackTrace();
      }
   }

   public void getTDTRFDetails(TDMasterData data)
   {
      DataSource ds = getDataSource();
      CustodyListSP sp = new CustodyListSP(ds, "sel_tddc_td_transfer",3);
      Map outMap = sp.getTDTRF(data.getAcctnum());
      try
      {
         if (outMap != null)
         {
            Integer whichAcct;
            ArrayList<Map<String, Object>> rows = (ArrayList<Map<String, Object>>) outMap.get("#result-set-1");
            if (rows == null)
               return;
            int i = 0;
            TDTransferDetails tdTransferDetails=new TDTransferDetails();
            for (Map<String, Object> map : rows)
            {
               Map rs = (Map) rows.get(i);
               tdTransferDetails.setAcctnum(convert.getLongData(rs.get("acctnum")));
               tdTransferDetails.setReqId(convert.getLongData(rs.get("reqId")));
               tdTransferDetails.setAccountTitle(convert.getStrData(rs.get("accountTitle")));
               tdTransferDetails.setFirmName(convert.getStrData(rs.get("firmName")));
               tdTransferDetails.setPrimaryContact(convert.getStrData(rs.get("primaryContact")));
               tdTransferDetails.setPriorFirmName(convert.getStrData(rs.get("priorFirmName")));
               tdTransferDetails.setFirmAccountNo(convert.getStrData(rs.get("firmAccountNo")));
               tdTransferDetails.setRetailAccountNumber(convert.getStrData(rs.get("retailAccountNumber")));
               tdTransferDetails.setAdvisorID(convert.getStrData(rs.get("advisorID")));
               if(rs.get("removeAdvisor")!=null && rs.get("removeAdvisor").equals("Y"))
               {
                  tdTransferDetails.setRemoveAdvisor(true);
                  tdTransferDetails.setAddAdvisor(false);
                  tdTransferDetails.setRetilFlag("Y");
               }
               else
               {
                  tdTransferDetails.setRemoveAdvisor(false);
                  tdTransferDetails.setAddAdvisor(true);
                  tdTransferDetails.setRetilFlag("N");
               }
               tdTransferDetails.setSsn(convert.getStrData(rs.get("ssn")));
            }
            data.setTdTransferDetails(tdTransferDetails);
         }
      }
      catch (Exception ex) {
         logger.error("Error occurred in getTDTRFDetails  :" +ex);
         ex.printStackTrace();
      }
   }

   public void getTDElectronicDetails(TDMasterData data)
   {
      DataSource ds = getDataSource();
      CustodyListSP sp = new CustodyListSP(ds, "sel_tddc_electronic_fund",3);
      Map outMap = sp.getTDACAT(data.getAcctnum());
      try
      {
         if (outMap != null)
         {
            Integer whichAcct;
            ArrayList<Map<String, Object>> rows = (ArrayList<Map<String, Object>>) outMap.get("#result-set-1");
            if (rows == null)
               return;
            int i = 0;
            ElectronicFundDetails acatDetails=new ElectronicFundDetails();
            for (Map<String, Object> map : rows)
            {
               Map rs = (Map) rows.get(i);
               acatDetails.setAcctnum(convert.getLongData(rs.get("acctnum")));
               acatDetails.setDirectionId(convert.getStrData(rs.get("directionId")));
               acatDetails.setMoveMoneyPayMethodID(convert.getLongData(rs.get("moveMoneyPayMethodID")));
               acatDetails.setReqId(convert.getLongData(rs.get("reqid")));
               acatDetails.setAchId(convert.getIntData(rs.get("achid")));
               acatDetails.setTranAmount(convert.getDoubleData(rs.get("tranAmount")));
               acatDetails.setTranFreqId(convert.getStrData(rs.get("tranFreqId")));
               acatDetails.setBankAcctType(convert.getStrData(rs.get("bankAcctType")));
               acatDetails.setBankName(convert.getStrData(rs.get("bankName")));
               acatDetails.setBankABARouting(convert.getStrData(rs.get("bankABARouting")));
               acatDetails.setBankAcctName(convert.getStrData(rs.get("bankAcctName")));
               acatDetails.setBankAcctNumber(convert.getStrData(rs.get("bankAcctNumber")));
               acatDetails.setTranStartDate(convert.getStrData(rs.get("transtartdate")));
               acatDetails.setBankCityState(convert.getStrData(rs.get("bankCityState")));
               acatDetails.setBankPhoneNumber(convert.getStrData(rs.get("bankPhoneNumber")));

            }

            data.setElectroicBankDetail(acatDetails);
            if(data.getElectroicBankDetail().getAchId().toString().equalsIgnoreCase(data.getAchBankDetail().getAchId().toString()))
               data.setCopyAchInstructions(true);
            else
               data.setCopyAchInstructions(false);

         }
      }
      catch (Exception ex) {
         logger.error("Error occurred in getTDTRFDetails  :" +ex);
         ex.printStackTrace();
      }
   }
   public void getfundingData(TDMasterData data)
   {
      DataSource ds = getDataSource();
      CustodyListSP sp = new CustodyListSP(ds, "sel_tddc_requests_type",3);
      Map outMap = sp.getTDRequestType(data.getAcctnum());
      try
      {
         if (outMap != null)
         {
            Integer whichAcct;
            ArrayList<Map<String, Object>> rows = (ArrayList<Map<String, Object>>) outMap.get("#result-set-1");
            if (rows == null)
               return;
            int i = 0;
            data.setFundNow(false);
            data.setRecurringFlag(false);
            AchBankDetail achBankDetail=new AchBankDetail();
            for (Map<String, Object> map : rows)
            {
               Map rs = (Map) rows.get(i);
               data.getRequest().setEventNum(convert.getIntData(rs.get("eventNum")));
               data.setFundNow(false);
               if(convert.getStrData(rs.get("reqfor")).equalsIgnoreCase("ACH"))
               {
                  getTDACHDetails(data);
                  data.setFundType("PMACH");
               }
              else  if(convert.getStrData(rs.get("reqfor")).equalsIgnoreCase("ACAT"))
               {
                  getTDACATDetails(data);
                  data.setFundType("PMFEDW");
               }
               else  if(convert.getStrData(rs.get("reqfor")).equalsIgnoreCase("TDTRF"))
               {
                  getTDTRFDetails(data);
                  data.setFundType("TDTRF");
               }
               else  if(convert.getStrData(rs.get("reqfor")).equalsIgnoreCase("EFT"))
               {
                  if(data.getOptoutRecurring())
                  {
                     data.setRecurringFlag(true);
                  }
                  else
                  {
                     getTDElectronicDetails(data);
                     data.setRecurringFlag(false);
                  }
               }
               i++;
            }
         }
      }
      catch (Exception ex) {
         logger.error("Error occurred in getfundingData  :" +ex);
         ex.printStackTrace();
      }
   }


   public void getAcatFirmList(TDMasterData data)
   {

      Map<String, MastACATFirm> acatFirmLinkedHashMap = new LinkedHashMap<String, MastACATFirm>();
      DataSource ds = getDataSource();
      CustodyListSP sp = new CustodyListSP(ds, "sel_tddc_ACATFirm_list",7);
      Map outMap = sp.getACATFirm();
      try
      {
         if (outMap != null)
         {
            Integer whichAcct;
            ArrayList<Map<String, Object>> rows = (ArrayList<Map<String, Object>>) outMap.get("#result-set-1");
            if (rows == null)
               return;
            int i = 0;

            List<String> firmList=new ArrayList<>();
            for (Map<String, Object> map : rows)
            {
               MastACATFirm acatDetails=new MastACATFirm();
               Map rs = (Map) rows.get(i);
               acatDetails.setLookupSet(convert.getStrData(rs.get("lookupSet")));
               acatDetails.setLookupCode(convert.getStrData(rs.get("lookupCode")));
               acatDetails.setDisplayName(convert.getStrData(rs.get("displayName")));
               acatDetails.setParentLookupId(convert.getStrData(rs.get("parentLookupId")));
               acatDetails.setValue(convert.getStrData(rs.get("value")));
               acatDetails.setRemark(convert.getStrData(rs.get("remark")));
               acatDetails.setSortOrder(convert.getIntData(rs.get("sortOrder")));
               acatDetails.setStatus(convert.getStrData(rs.get("status")));
               acatDetails.setIsRequired(convert.getStrData(rs.get("isRequired")));
               acatDetails.setAddress(convert.getStrData(rs.get("address")));
               acatDetails.setPhoneNumber(convert.getStrData(rs.get("phoneNumber")));

               firmList.add(convert.getStrData(rs.get("displayName")));
               acatFirmLinkedHashMap.put(acatDetails.getDisplayName(),acatDetails);
                  i++;
            }
            data.setAcatFirmList(firmList);
            data.setAcatFirmMap(acatFirmLinkedHashMap);
         }
      }
      catch (Exception ex) {
         logger.error("Error occurred in getAcatFirmList  :" +ex);
         ex.printStackTrace();

      }
   }

   public void getFundEFTData(TDMasterData data)
   {
      DataSource ds = getDataSource();
      CustodyListSP sp = new CustodyListSP(ds, "sel_fund_eft_data",3);
      Map outMap = sp.getTDACAT(data.getAcctnum());
      try
      {
         if (outMap != null)
         {
            Integer whichAcct;
            ArrayList<Map<String, Object>> rows = (ArrayList<Map<String, Object>>) outMap.get("#result-set-1");
            if (rows == null)
               return;
            int i = 0;
            ElectronicFundDetails acatDetails=new ElectronicFundDetails();
            for (Map<String, Object> map : rows)
            {
               Map rs = (Map) rows.get(i);
               acatDetails.setAcctnum(convert.getLongData(rs.get("acctnum")));
               acatDetails.setMoveMoneyPayMethodID(convert.getLongData(rs.get("moveMoneyPayMethodID")));
               acatDetails.setReqId(convert.getLongData(rs.get("reqid")));
               acatDetails.setAchId(convert.getIntData(rs.get("achid")));
              // acatDetails.setTranAmount(convert.getDoubleData(rs.get("tranAmount")));
               acatDetails.setTranFreqId(convert.getStrData(rs.get("tranFreqId")));
               acatDetails.setBankAcctType(convert.getStrData(rs.get("bankAcctType")));
               acatDetails.setBankName(convert.getStrData(rs.get("bankName")));
               acatDetails.setBankABARouting(convert.getStrData(rs.get("bankABARouting")));
               acatDetails.setBankAcctName(convert.getStrData(rs.get("bankAcctName")));
               acatDetails.setBankAcctNumber(convert.getStrData(rs.get("bankAcctNumber")));
               //acatDetails.setTranStartDate(convert.getStrData(rs.get("transtartdate")));
               acatDetails.setBankCityState(convert.getStrData(rs.get("bankCityState")));
               acatDetails.setBankPhoneNumber(convert.getStrData(rs.get("bankPhoneNumber")));
               data.setNewRecurring(false);

            }

            data.setElectroicBankDetail(acatDetails);
            if(data.getElectroicBankDetail().getAchId().toString().equalsIgnoreCase(data.getAchBankDetail().getAchId().toString()))
               data.setCopyAchInstructions(true);
            else
               data.setCopyAchInstructions(false);

         }
      }
      catch (Exception ex) {
         logger.error("Error occurred in getFundEFTData  :" +ex);
         ex.printStackTrace();

      }
   }
   public void getFundTDData(TDMasterData data)
   {
      DataSource ds = getDataSource();
      CustodyListSP sp = new CustodyListSP(ds, "sel_fund_td_data",3);
      Map outMap = sp.getTDTRF(data.getAcctnum());
      try
      {
         if (outMap != null)
         {
            Integer whichAcct;
            ArrayList<Map<String, Object>> rows = (ArrayList<Map<String, Object>>) outMap.get("#result-set-1");
            if (rows == null)
               return;
            int i = 0;
            TDTransferDetails tdTransferDetails=new TDTransferDetails();
            for (Map<String, Object> map : rows)
            {
               Map rs = (Map) rows.get(i);
               tdTransferDetails.setAcctnum(convert.getLongData(rs.get("acctnum")));
               tdTransferDetails.setReqId(convert.getLongData(rs.get("reqId")));
               tdTransferDetails.setAccountTitle(convert.getStrData(rs.get("accountTitle")));
               tdTransferDetails.setFirmName(convert.getStrData(rs.get("firmName")));
               tdTransferDetails.setPrimaryContact(convert.getStrData(rs.get("primaryContact")));
               tdTransferDetails.setPriorFirmName(convert.getStrData(rs.get("priorFirmName")));
               tdTransferDetails.setFirmAccountNo(convert.getStrData(rs.get("firmAccountNo")));
               tdTransferDetails.setRetailAccountNumber(convert.getStrData(rs.get("retailAccountNumber")));
               tdTransferDetails.setAdvisorID(convert.getStrData(rs.get("advisorID")));
               if(rs.get("removeAdvisor")!=null && rs.get("removeAdvisor").equals("Y"))
               {
                  tdTransferDetails.setRemoveAdvisor(true);
                  tdTransferDetails.setAddAdvisor(false);
                  tdTransferDetails.setRetilFlag("Y");
               }
               else
               {
                  tdTransferDetails.setRemoveAdvisor(false);
                  tdTransferDetails.setAddAdvisor(true);
                  tdTransferDetails.setRetilFlag("N");
               }
               tdTransferDetails.setSsn(convert.getStrData(rs.get("ssn")));
            }
            data.setTdTransferDetails(tdTransferDetails);
         }
      }
      catch (Exception ex) {
         logger.error("Error occurred in getFundTDData  :" +ex);
         ex.printStackTrace();

      }
   }

   public void getFundACATData(TDMasterData data)
   {
      DataSource ds = getDataSource();
      CustodyListSP sp = new CustodyListSP(ds, "sel_fund_acat_data",3);
      Map outMap = sp.getTDACAT(data.getAcctnum());
      try
      {
         if (outMap != null)
         {
            Integer whichAcct;
            ArrayList<Map<String, Object>> rows = (ArrayList<Map<String, Object>>) outMap.get("#result-set-1");
            if (rows == null)
               return;
            int i = 0;
            ACATDetails acatDetails=new ACATDetails();
            for (Map<String, Object> map : rows)
            {
               Map rs = (Map) rows.get(i);
               acatDetails.setAcctnum(convert.getLongData(rs.get("acctnum")));
               acatDetails.setReqId(convert.getLongData(rs.get("reqId")));
               acatDetails.setFromAccountTitle(convert.getStrData(rs.get("fromAccountTitle")));
               acatDetails.setAccountNumber2(convert.getStrData(rs.get("accountNumber2")));
               acatDetails.setFromFirmAddress(convert.getStrData(rs.get("fromFirmAddress")));
               acatDetails.setFromFirmPhoneNumber(convert.getStrData(rs.get("fromFirmPhoneNumber")));
               acatDetails.setFromOtherAccountType(convert.getStrData(rs.get("fromOtherAccountType")));
               acatDetails.setTransferTypeId(convert.getStrData(rs.get("transferTypeId")));
               acatDetails.setContraFirmList(convert.getStrData(rs.get("contraFirmList")));
               acatDetails.setOtherContraFirmList(convert.getStrData(rs.get("othercontraFirmList")));
            }
            data.setAcatDetails(acatDetails);
         }
      }
      catch (Exception ex) {

         logger.error("Error occurred in getFundACATData  :" +ex);
         ex.printStackTrace();
      }
   }

   public void getFundACHData(TDMasterData data)
   {
      DataSource ds = getDataSource();
      CustodyListSP sp = new CustodyListSP(ds, "sel_fund_ach_data",3);
      Map outMap = sp.getTDBeneficiary(data.getAcctnum());
      try
      {
         if (outMap != null)
         {
            Integer whichAcct;
            ArrayList<Map<String, Object>> rows = (ArrayList<Map<String, Object>>) outMap.get("#result-set-1");
            if (rows == null)
               return;
            int i = 0;
            AchBankDetail achBankDetail=new AchBankDetail();
            for (Map<String, Object> map : rows)
            {
               Map rs = (Map) rows.get(i);
               achBankDetail.setMoveMoneyPayMethodID(convert.getLongData(rs.get("moveMoneyPayMethodID")));
               achBankDetail.setAchId(convert.getLongData(rs.get("achId")));
               achBankDetail.setBankAcctType(convert.getStrData(rs.get("bankAcctType")));
               achBankDetail.setBankName(convert.getStrData(rs.get("bankName")));
               achBankDetail.setBankABARouting(convert.getStrData(rs.get("bankABARouting")));
               achBankDetail.setBankCityState(convert.getStrData(rs.get("bankCityState")));
               achBankDetail.setBankPhoneNumber(convert.getStrData(rs.get("bankPhoneNumber")));
               achBankDetail.setBankAcctName(convert.getStrData(rs.get("bankAcctName")));
               achBankDetail.setBankAcctNumber(convert.getStrData(rs.get("bankAcctNumber")));
               //data.setInitialInvestment(convert.getDoubleData(rs.get("tranAmount")));
            }
            data.setAchBankDetail(achBankDetail);
         }
      }
      catch (Exception ex) {

         logger.error("Error occurred in getFundACHData  :" +ex);
         ex.printStackTrace();
      }
   }
}
