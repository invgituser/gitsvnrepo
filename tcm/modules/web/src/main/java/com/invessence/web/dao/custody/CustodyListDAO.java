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
            }
         }
      }
      catch (Exception ex) {

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
               acctHolder.setSPF(convert.getStrData(rs.get("isSPF")));
                  if(acctHolder.getSPF()!=null && acctHolder.getSPF().equalsIgnoreCase("Y"))
                  {
                     data.setOwnerSPF(true);
                     String spfDet=convert.getStrData(rs.get("spfDetail"));
                     String[] array = spfDet.split(",");
                     acctHolder.setSpfName(array[0]);
                     acctHolder.setSpfRelationship(array[1]);
                     acctHolder.setSpfTitle(array[2]);
                     acctHolder.setSpfCountry(array[3]);
                  }

               acctHolder.setDirectorShareholder(convert.getStrData(rs.get("isDirectorShareholder")));
                  if(acctHolder.getDirectorShareholder()!=null  && acctHolder.getDirectorShareholder().equalsIgnoreCase("Y"))
                  {
                     data.setOwnerShare(true);
                     String shreDet=convert.getStrData(convert.getStrData(rs.get("directorShareholderDetail")));
                     String[] array = shreDet.split(",");
                     acctHolder.setShareholderCompany(array[0]);
                     acctHolder.setShareholderAddress(array[1]);
                     acctHolder.setShareholderCity(array[2]);
                     acctHolder.setShareholderState(array[3]);

                  }
               acctHolder.setBd(convert.getStrData(rs.get("bd")));
                  if(acctHolder.getBd()!=null && acctHolder.getBd().equalsIgnoreCase("Y"))
                  {
                     data.setOwnerBD(true);
                     acctHolder.setBdDetail(convert.getStrData(rs.get("bdDetail")));
                  }
               acctHolder.setOwnership(convert.getStrData(rs.get("ownershipPercent")));
               acctHolder.setCreatedBy(convert.getStrData(rs.get("createdBy")));
                  i++;
                  if(whichAcct==1)
                  {
                     data.setAcctOwnersDetail(acctHolder);
                     if(convert.getStrData(rs.get("mailingAddressStreet"))!=null)
                        data.setAcctholderhasMailing(true);

                  }
                  else
                  {
                     if(convert.getStrData(rs.get("mailingAddressStreet"))!=null)
                        data.setJointhasMailing(true);
                     data.setJointAcctOwnersDetail(acctHolder);
                  }

                  if(data.getAcctOwnersDetail().getPhysicalAddressStreet().equalsIgnoreCase(data.getJointAcctOwnersDetail().getMailingAddressStreet()))
                        data.setJointhasDifferent(false);
                  else
                     data.setJointhasDifferent(true);
               }
            }
         }
      }
      catch (Exception ex) {

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
               benefiaciaryDetails.setSharePerc(convert.getDoubleData(rs.get("sharePerc")));

               benefiaciaryDetailsList.add(benefiaciaryDetails);
               i++;
            }
            data.setBenefiaciaryDetailsList(benefiaciaryDetailsList);
         }
      }
      catch (Exception ex) {

      }
   }



}
