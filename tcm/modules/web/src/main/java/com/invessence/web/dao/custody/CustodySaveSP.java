package com.invessence.web.dao.custody;

import java.sql.Types;
import java.util.*;
import javax.sql.DataSource;

import com.invessence.web.data.custody.TDMasterData;
import com.invessence.web.data.custody.td.*;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.object.StoredProcedure;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 8/20/16
 * Time: 11:26 AM
 * To change this template use File | Settings | File Templates.
 */
@SuppressWarnings({"unchecked", "DuplicateStringLiteralInspection"})
public class CustodySaveSP extends StoredProcedure
{

   public CustodySaveSP(DataSource datasource, String spname)
   {
      super(datasource, spname);
      compile();
   }

   public CustodySaveSP(DataSource datasource, String spname, Integer mode)
   {
      super(datasource, spname);
      switch (mode) {
         case 0:
            declareParameter(new SqlInOutParameter("p_reqId", Types.BIGINT));
            declareParameter(new SqlParameter("p_acctnum", Types.BIGINT));
            declareParameter(new SqlInOutParameter("p_eventNum", Types.INTEGER));
            declareParameter(new SqlParameter("p_reqType", Types.VARCHAR));
            declareParameter(new SqlParameter("p_envelopeHeading", Types.VARCHAR));
            declareParameter(new SqlParameter("p_envelopeId", Types.VARCHAR));
            declareParameter(new SqlParameter("p_status", Types.VARCHAR));
            declareParameter(new SqlParameter("p_terminalDetails", Types.VARCHAR));
            declareParameter(new SqlParameter("p_requestFor", Types.VARCHAR));
            break;
         case 1:
            declareParameter(new SqlParameter("p_acctnum", Types.BIGINT));
            declareParameter(new SqlParameter("p_clientAccountID", Types.VARCHAR));
            declareParameter(new SqlParameter("p_caseNumber", Types.VARCHAR));
            declareParameter(new SqlParameter("p_advisorId", Types.BIGINT));
            declareParameter(new SqlParameter("p_acctTypeId", Types.VARCHAR));
            declareParameter(new SqlParameter("p_cashSweepVehicleChoiceId", Types.VARCHAR));
            declareParameter(new SqlParameter("p_divIntPrefId", Types.VARCHAR));
            declareParameter(new SqlParameter("p_monthStmtId", Types.VARCHAR));
            declareParameter(new SqlParameter("p_tradConfId", Types.VARCHAR));
            declareParameter(new SqlParameter("p_dupStatement", Types.VARCHAR));
            declareParameter(new SqlParameter("p_dupTradeConfirm", Types.VARCHAR));
            declareParameter(new SqlParameter("p_proxyAuthorizationId", Types.VARCHAR));
            declareParameter(new SqlParameter("p_createdBy", Types.VARCHAR));
            break;
         case 2:  // Account Holder
            declareParameter(new SqlParameter("p_acctnum", Types.BIGINT));
            declareParameter(new SqlParameter("p_acctOwnerId", Types.INTEGER));
            declareParameter(new SqlParameter("p_ownership", Types.VARCHAR));
            declareParameter(new SqlParameter("p_firstName", Types.VARCHAR));
            declareParameter(new SqlParameter("p_midInitial", Types.VARCHAR));
            declareParameter(new SqlParameter("p_lastName", Types.VARCHAR));
            declareParameter(new SqlParameter("p_ssn", Types.VARCHAR));
            declareParameter(new SqlParameter("p_dob", Types.VARCHAR));
            declareParameter(new SqlParameter("p_phoneNumber", Types.VARCHAR));
            declareParameter(new SqlParameter("p_phoneNumberNonUS", Types.VARCHAR));
            declareParameter(new SqlParameter("p_secondPhoneNumber", Types.VARCHAR));
            declareParameter(new SqlParameter("p_secondPhoneNumberNonUS", Types.VARCHAR));
            declareParameter(new SqlParameter("p_emailAddress", Types.VARCHAR));
            declareParameter(new SqlParameter("p_physicalAddressStreet", Types.VARCHAR));
            declareParameter(new SqlParameter("p_physicalAddressCity", Types.VARCHAR));
            declareParameter(new SqlParameter("p_physicalAddressState", Types.VARCHAR));
            declareParameter(new SqlParameter("p_physicalAddressZipCode", Types.VARCHAR));
            declareParameter(new SqlParameter("p_mailingAddressStreet", Types.VARCHAR));
            declareParameter(new SqlParameter("p_mailingAddressCity", Types.VARCHAR));
            declareParameter(new SqlParameter("p_mailingAddressState", Types.VARCHAR));
            declareParameter(new SqlParameter("p_mailingAddressZipCode", Types.VARCHAR));
            declareParameter(new SqlParameter("p_citizenshiId", Types.VARCHAR));
            declareParameter(new SqlParameter("p_countryOfCitizenship", Types.VARCHAR));
            declareParameter(new SqlParameter("p_countryOfDualCitizenship", Types.VARCHAR));
            declareParameter(new SqlParameter("p_countryOfBirth", Types.VARCHAR));
            declareParameter(new SqlParameter("p_isSPF", Types.VARCHAR));
            declareParameter(new SqlParameter("p_spfDetail", Types.VARCHAR));
            declareParameter(new SqlParameter("p_isDirectorShareholder", Types.VARCHAR));
            declareParameter(new SqlParameter("p_directorShareholderDetail", Types.VARCHAR));
            declareParameter(new SqlParameter("p_bd", Types.VARCHAR));
            declareParameter(new SqlParameter("p_bdDetail", Types.VARCHAR));
            declareParameter(new SqlParameter("p_ownershipPercent", Types.VARCHAR));
            declareParameter(new SqlParameter("p_createdBy", Types.VARCHAR));
            break;
         case 3:  // Employment
            declareParameter(new SqlParameter("p_acctnum", Types.BIGINT));
            declareParameter(new SqlParameter("p_acctOwnerId", Types.INTEGER));
            declareParameter(new SqlParameter("p_emplId", Types.INTEGER));
            declareParameter(new SqlParameter("p_emplTypeId", Types.VARCHAR));
            declareParameter(new SqlParameter("p_sourceOfIncome", Types.VARCHAR));
            declareParameter(new SqlParameter("p_employerName", Types.VARCHAR));
            declareParameter(new SqlParameter("p_occupation", Types.VARCHAR));
            declareParameter(new SqlParameter("p_typeOfBusiness", Types.VARCHAR));
            declareParameter(new SqlParameter("p_employerStreetAddress", Types.VARCHAR));
            declareParameter(new SqlParameter("p_employerCity", Types.VARCHAR));
            declareParameter(new SqlParameter("p_employerState", Types.VARCHAR));
            declareParameter(new SqlParameter("p_employerZipCode", Types.VARCHAR));
            declareParameter(new SqlParameter("p_fromDate", Types.VARCHAR));
            declareParameter(new SqlParameter("p_toDate", Types.VARCHAR));
            declareParameter(new SqlParameter("p_createdBy", Types.VARCHAR));
            break;
         case 4:  // Beneficiary
            declareParameter(new SqlParameter("p_acctnum", Types.BIGINT));
            declareParameter(new SqlParameter("p_beneId", Types.INTEGER));
            declareParameter(new SqlParameter("p_beneFirstName", Types.VARCHAR));
            declareParameter(new SqlParameter("p_beneMidInitial", Types.VARCHAR));
            declareParameter(new SqlParameter("p_beneLastName", Types.VARCHAR));
            declareParameter(new SqlParameter("p_beneSSN", Types.VARCHAR));
            declareParameter(new SqlParameter("p_beneDOB", Types.VARCHAR));
            declareParameter(new SqlParameter("p_beneRel", Types.VARCHAR));
            declareParameter(new SqlParameter("p_typeOfBeneficiary", Types.VARCHAR));
            declareParameter(new SqlParameter("p_perStripes", Types.VARCHAR));
            declareParameter(new SqlParameter("p_sharePerc", Types.DOUBLE));
            declareParameter(new SqlParameter("p_createdBy", Types.VARCHAR));
            break;
         case 5: // ACH
            declareParameter(new SqlParameter("p_moveMoneyPayMethodID", Types.INTEGER));
            declareParameter(new SqlInOutParameter("p_achId", Types.VARCHAR));
            declareParameter(new SqlParameter("p_bankAcctType", Types.VARCHAR));
            declareParameter(new SqlParameter("p_bankName", Types.VARCHAR));
            declareParameter(new SqlParameter("p_bankABARouting", Types.VARCHAR));
            declareParameter(new SqlParameter("p_bankCityState", Types.VARCHAR));
            declareParameter(new SqlParameter("p_bankPhoneNumber", Types.VARCHAR));
            declareParameter(new SqlParameter("p_bankAcctName", Types.VARCHAR));
            declareParameter(new SqlParameter("p_bankAcctNumber", Types.VARCHAR));
            declareParameter(new SqlParameter("p_createdBy", Types.VARCHAR));
            break;
         case 6: // ACAT
            declareParameter(new SqlParameter("p_acctnum", Types.BIGINT));
            declareParameter(new SqlParameter("p_reqId", Types.INTEGER));
            declareParameter(new SqlParameter("p_fromAccountTitle", Types.VARCHAR));
            declareParameter(new SqlParameter("p_accountNumber2", Types.VARCHAR));
            declareParameter(new SqlParameter("p_contraFirmList", Types.VARCHAR));
            declareParameter(new SqlParameter("p_fromFirmAddress", Types.VARCHAR));
            declareParameter(new SqlParameter("p_fromFirmPhoneNumber", Types.VARCHAR));
            declareParameter(new SqlParameter("p_fromEEPlanType", Types.VARCHAR));
            declareParameter(new SqlParameter("p_simpleFunded", Types.VARCHAR));
            declareParameter(new SqlParameter("p_fromOtherAccountType", Types.VARCHAR));
            declareParameter(new SqlParameter("p_transferTypeId", Types.VARCHAR));
            declareParameter(new SqlParameter("p_createdBy", Types.VARCHAR));
            declareParameter(new SqlParameter("p_otherContraFirmList", Types.VARCHAR));
            break;
         case 7: // Fed Wire
            declareParameter(new SqlParameter("p_acctnum", Types.BIGINT));
            declareParameter(new SqlParameter("p_moveMoneyPayMethodID", Types.INTEGER));
            declareParameter(new SqlParameter("p_fedwireId", Types.INTEGER));
            declareParameter(new SqlParameter("p_wireBankName", Types.VARCHAR));
            declareParameter(new SqlParameter("p_wireBankCityState", Types.VARCHAR));
            declareParameter(new SqlParameter("p_wireBankPhoneNumber", Types.VARCHAR));
            declareParameter(new SqlParameter("p_wireABARouting", Types.VARCHAR));
            declareParameter(new SqlParameter("p_wireBankAccountNumber", Types.VARCHAR));
            declareParameter(new SqlParameter("p_wireBankAcctName", Types.VARCHAR));
            declareParameter(new SqlParameter("p_wireFurtherCreditName", Types.VARCHAR));
            declareParameter(new SqlParameter("p_furtherCreditAcctType", Types.VARCHAR));
            declareParameter(new SqlParameter("p_internatBankName", Types.VARCHAR));
            declareParameter(new SqlParameter("p_internatBankStreetAddress", Types.VARCHAR));
            declareParameter(new SqlParameter("p_internatBankCityCountry", Types.VARCHAR));
            declareParameter(new SqlParameter("p_internatBankPhoneNumber", Types.VARCHAR));
            declareParameter(new SqlParameter("p_internatSWIFTCode", Types.VARCHAR));
            declareParameter(new SqlParameter("p_internatAddBankRouting", Types.VARCHAR));
            declareParameter(new SqlParameter("p_internatBankAcctName", Types.VARCHAR));
            declareParameter(new SqlParameter("p_internatBankAcctNumber", Types.VARCHAR));
            declareParameter(new SqlParameter("p_internatFurtherCreditInfo", Types.VARCHAR));
            declareParameter(new SqlParameter("p_internatPurpose", Types.VARCHAR));
            declareParameter(new SqlParameter("p_createdBy", Types.VARCHAR));
            break;
         case 8: // Electronic Payment
            declareParameter(new SqlParameter("p_acctnum", Types.BIGINT));
            declareParameter(new SqlParameter("p_reqId", Types.INTEGER));
            declareParameter(new SqlParameter("p_eftInstId", Types.VARCHAR));
            declareParameter(new SqlParameter("p_tdaiAcctNum", Types.VARCHAR));
            declareParameter(new SqlParameter("p_directionId", Types.VARCHAR));
            declareParameter(new SqlParameter("p_moveMoneyPayMethodID", Types.INTEGER));
            declareParameter(new SqlParameter("p_achId", Types.INTEGER));
            declareParameter(new SqlParameter("p_tranStartDate", Types.VARCHAR));
            declareParameter(new SqlParameter("p_tranAmount", Types.DOUBLE));
            declareParameter(new SqlParameter("p_tranFreqId", Types.VARCHAR));
            declareParameter(new SqlParameter("p_createdBy", Types.VARCHAR));
         default:
            break;
         case 9: // OPEN Account
            declareParameter(new SqlParameter("p_acctnum", Types.BIGINT));
            declareParameter(new SqlParameter("p_reqId", Types.INTEGER));
            declareParameter(new SqlParameter("p_eftInstId", Types.VARCHAR));
            declareParameter(new SqlParameter("p_tdaiAcctNum", Types.VARCHAR));
            declareParameter(new SqlParameter("p_directionId", Types.VARCHAR));
            declareParameter(new SqlParameter("p_moveMoneyPayMethodID", Types.INTEGER));
            declareParameter(new SqlParameter("p_achId", Types.INTEGER));
            declareParameter(new SqlParameter("p_tranStartDate", Types.VARCHAR));
            declareParameter(new SqlParameter("p_tranAmount", Types.DOUBLE));
            declareParameter(new SqlParameter("p_tranFreqId", Types.VARCHAR));
            declareParameter(new SqlParameter("p_createdBy", Types.VARCHAR));
            break;
         case 10: // move money payment details
            declareParameter(new SqlParameter("p_acctnum", Types.BIGINT));
            declareParameter(new SqlInOutParameter("p_moveMoneyPayMethId", Types.BIGINT));
            declareParameter(new SqlParameter("p_payMethodId", Types.VARCHAR));
            declareParameter(new SqlParameter("p_status", Types.VARCHAR));
            break;
         case 11: // move money details
            declareParameter(new SqlParameter("p_acctnum", Types.BIGINT));
            declareParameter(new SqlParameter("p_reqId", Types.BIGINT));
            declareParameter(new SqlParameter("p_reqType", Types.VARCHAR));
            declareParameter(new SqlParameter("p_createdBy", Types.VARCHAR));
            break;
         case 12: // request for final save and open acoount
            declareParameter(new SqlParameter("p_acctnum", Types.BIGINT));
            declareParameter(new SqlParameter("p_optfund", Types.BOOLEAN));
            declareParameter(new SqlParameter("p_optrec", Types.BOOLEAN));
            break;
         case 13:
            declareParameter(new SqlParameter("p_acctnum", Types.BIGINT));
            declareParameter(new SqlParameter("p_managed", Types.VARCHAR));
            break;
         case 14: // TD Transfer
            declareParameter(new SqlParameter("p_acctnum", Types.BIGINT));
            declareParameter(new SqlParameter("p_reqId", Types.INTEGER));
            declareParameter(new SqlParameter("p_accountTitle", Types.VARCHAR));
            declareParameter(new SqlParameter("p_firmName", Types.VARCHAR));
            declareParameter(new SqlParameter("p_primaryContact", Types.VARCHAR));
            declareParameter(new SqlParameter("p_priorFirmName", Types.VARCHAR));
            declareParameter(new SqlParameter("p_retailAccountNumber", Types.VARCHAR));
            declareParameter(new SqlParameter("p_advisorID", Types.VARCHAR));
            declareParameter(new SqlParameter("p_removeAdvisor", Types.VARCHAR));
            declareParameter(new SqlParameter("p_addAdvisor", Types.VARCHAR));
            declareParameter(new SqlParameter("p_ssn", Types.VARCHAR));
            declareParameter(new SqlParameter("p_createdBy", Types.VARCHAR));
            break;
      }
      compile();
   }

     public Map tdSaveRequest(Request data) {
   Map<String, Object> inputMap = new HashMap<String, Object>();
   inputMap.put("p_reqId", data.getReqId());
   inputMap.put("p_acctnum", data.getAcctnum());
   inputMap.put("p_eventNum", data.getEventNum());
   inputMap.put("p_reqType", data.getReqType());
   inputMap.put("p_envelopeHeading", data.getEnvelopeHeading());
   inputMap.put("p_envelopeId", data.getEnvelopeId());
   inputMap.put("p_status", data.getStatus());
   inputMap.put("p_terminalDetails", data.getTerminalDetails());
   inputMap.put("p_requestFor", data.getRequestFor());

   Map outMap =  super.execute(inputMap);
   if (outMap != null) {
      data.setReqId((Long) outMap.get("p_reqId"));
      data.setEventNum((Integer) outMap.get("p_eventNum"));
   }
   return outMap;
}
   public Map tdCheckRequest(TDMasterData data) {
      Map<String, Object> inputMap = new HashMap<String, Object>();
      inputMap.put("p_acctnum", data.getAcctnum());
      inputMap.put("p_optfund", data.getFundNow());
      inputMap.put("p_optrec", data.getRecurringFlag());
      return super.execute(inputMap);
   }

   public Map tdSaveMoveMoneyPay(TDMasterData tdMasterData) {
      Map<String, Object> inputMap = new HashMap<String, Object>();
      inputMap.put("p_acctnum", tdMasterData.getAcctnum());
      inputMap.put("p_moveMoneyPayMethId", new Long(0));
      if(tdMasterData.getFundType()==null)
         inputMap.put("p_payMethodId", "PMACH");
      else
         inputMap.put("p_payMethodId", tdMasterData.getFundType());
      inputMap.put("p_status", "A");
      Map outMap =  super.execute(inputMap);
      //if (outMap != null) {
        // data.setReqId((Long) outMap.get("p_moveMoneyPayMethId"));
      //}
      return outMap;
   }
   public Map tdSaveMoveMoneyDetails(Long reqId,TDMasterData tdMasterData) {
      Map<String, Object> inputMap = new HashMap<String, Object>();
      inputMap.put("p_acctnum", tdMasterData.getAcctnum());
      inputMap.put("p_reqId",reqId );
      inputMap.put("p_reqType","MMINEW" );
      inputMap.put("p_createdBy", "Invessence");
      return super.execute(inputMap);
   }


   public Map tdSaveAccountDetail(Acctdetails data) {
      Map<String, Object> inputMap = new HashMap<String, Object>();
      inputMap.put("p_acctnum", data.getAcctnum());
      inputMap.put("p_clientAccountID", data.getClientAccountID());
      inputMap.put("p_caseNumber", data.getCaseNumber());
      inputMap.put("p_advisorId", data.getAdvisorId());
      inputMap.put("p_acctTypeId", data.getAcctTypeId());
      inputMap.put("p_cashSweepVehicleChoiceId", data.getCashSweepVehicleChoiceId());
      inputMap.put("p_divIntPrefId", data.getDivIntPrefId());
      inputMap.put("p_monthStmtId", data.getMonthStmtId());
      inputMap.put("p_tradConfId", data.getTradConfId());
      inputMap.put("p_dupStatement", data.getDupStatement());
      inputMap.put("p_dupTradeConfirm", data.getDupTradeConfirm());
      inputMap.put("p_proxyAuthorizationId", data.getProxyAuthorizationId());
      inputMap.put("p_createdBy", "Invessence");
      return super.execute(inputMap);
   }

   public Map tdSaveAccountOwner(AcctOwnersDetails data) {
      Map<String, Object> inputMap = new HashMap<String, Object>();
      inputMap.put("p_acctnum", data.getAcctnum());
      inputMap.put("p_acctOwnerId", data.getAcctOwnerId());
      inputMap.put("p_ownership",data.getOwnership());
      inputMap.put("p_firstName", data.getFirstName());
      inputMap.put("p_midInitial", data.getMidInitial());
      inputMap.put("p_lastName", data.getLastName());
      inputMap.put("p_ssn", data.getSsn());
      inputMap.put("p_dob", data.getDob());
      inputMap.put("p_phoneNumber", data.getPhoneNumber());
      inputMap.put("p_phoneNumberNonUS", data.getPhoneNumberNonUS());
      inputMap.put("p_secondPhoneNumber", data.getSecondPhoneNumber());
      inputMap.put("p_secondPhoneNumberNonUS", data.getSecondPhoneNumberNonUS());
      inputMap.put("p_emailAddress", data.getEmailAddress());
      inputMap.put("p_physicalAddressStreet", data.getPhysicalAddressStreet());
      inputMap.put("p_physicalAddressCity", data.getPhysicalAddressCity());
      inputMap.put("p_physicalAddressState", data.getPhysicalAddressState());
      inputMap.put("p_physicalAddressZipCode", data.getPhysicalAddressZipCode());
      inputMap.put("p_mailingAddressStreet", data.getMailingAddressStreet());
      inputMap.put("p_mailingAddressCity", data.getMailingAddressCity());
      inputMap.put("p_mailingAddressState", data.getMailingAddressState());
      inputMap.put("p_mailingAddressZipCode", data.getMailingAddressZipCode());
      inputMap.put("p_citizenshiId", data.getCitizenshiId());
      inputMap.put("p_countryOfCitizenship", data.getCountryOfCitizenship());
      inputMap.put("p_countryOfDualCitizenship", data.getCountryOfDualCitizenship());
      inputMap.put("p_countryOfBirth", data.getCountryOfBirth());
      inputMap.put("p_isSPF", data.getSPF());
      inputMap.put("p_spfDetail", data.getSpfDetail());
      inputMap.put("p_isDirectorShareholder", data.getDirectorShareholder());
      inputMap.put("p_directorShareholderDetail", data.getDirectorShareholderDetail());
      inputMap.put("p_bd", data.getBd());
      inputMap.put("p_bdDetail", data.getBdDetail());
      inputMap.put("p_ownershipPercent", data.getOwnershipPercent());
      inputMap.put("p_createdBy", "Invessence");
      return super.execute(inputMap);
   }

   public Map tdSaveEmployment(EmploymentDetails data) {
      Map<String, Object> inputMap = new HashMap<String, Object>();
      inputMap.put("p_acctnum", data.getAcctnum());
      inputMap.put("p_acctOwnerId", data.getAcctOwnerId());
      inputMap.put("p_emplId", data.getEmplId());
      inputMap.put("p_emplTypeId", data.getEmplTypeId());
      inputMap.put("p_sourceOfIncome", data.getSourceOfIncome());
      inputMap.put("p_employerName", data.getEmployerName());
      inputMap.put("p_occupation",data.getOccupation());
      inputMap.put("p_typeOfBusiness", data.getTypeOfBusiness());
      inputMap.put("p_employerStreetAddress", data.getEmployerStreetAddress());
      inputMap.put("p_employerCity", data.getEmployerCity());
      inputMap.put("p_employerState", data.getEmployerState());
      inputMap.put("p_employerZipCode", data.getEmployerZipCode());
      inputMap.put("p_fromDate", data.getFromDate());
      inputMap.put("p_toDate", data.getToDate());
      inputMap.put("p_createdBy", "Invessence");
      return super.execute(inputMap);
   }

   public Map tdSaveBeneficiary(BenefiaciaryDetails data) {
      Map<String, Object> inputMap = new HashMap<String, Object>();
      inputMap.put("p_acctnum", data.getAcctnum());
      inputMap.put("p_beneId", data.getBeneId());
      inputMap.put("p_beneFirstName", data.getBeneFirstName());
      inputMap.put("p_beneMidInitial", data.getBeneMidInitial());
      inputMap.put("p_beneLastName", data.getBeneLastName());
      inputMap.put("p_beneSSN", data.getBeneSSN());
      inputMap.put("p_beneDOB", data.getBeneDOB());
      inputMap.put("p_beneRel", data.getBeneRel());
      inputMap.put("p_typeOfBeneficiary", data.getTypeOfBeneficiary());
      inputMap.put("p_perStripes", data.getPerStripes());
      inputMap.put("p_sharePerc", data.getSharePerc());
      inputMap.put("p_createdBy", "Invessence");
      return super.execute(inputMap);
   }

  // public Map tdSaveACH(String ftype,Boolean ownerSPF,Long acctnum,Long reqId,Double initialInv,String fundType, AchBankDetail data) {
  public Map tdSaveACH(AchBankDetail data) {
   Map<String, Object> inputMap = new HashMap<String, Object>();
   inputMap.put("p_moveMoneyPayMethodID", data.getMoveMoneyPayMethodID());
   if(data.getAchId()==null)
      inputMap.put("p_achId", new Long(0));
   else
      inputMap.put("p_achId", data.getAchId());
   inputMap.put("p_bankAcctType", data.getBankAcctType());
   inputMap.put("p_bankName", data.getBankName());
   inputMap.put("p_bankABARouting", data.getBankABARouting());
   inputMap.put("p_bankCityState", data.getBankCityState());
   inputMap.put("p_bankPhoneNumber", data.getBankPhoneNumber());
   inputMap.put("p_bankAcctName", data.getBankAcctName());
   inputMap.put("p_bankAcctNumber", data.getBankAcctNumber());
   inputMap.put("p_createdBy", "Invessence");
   return super.execute(inputMap);
}
   public Map tdSaveACHFund(ElectronicFundDetails data) {
      Map<String, Object> inputMap = new HashMap<String, Object>();
      inputMap.put("p_moveMoneyPayMethodID", data.getMoveMoneyPayMethodID());
      if(data.getAchId()==null)
         inputMap.put("p_achId", new Long(0));
      else
         inputMap.put("p_achId", data.getAchId());
      inputMap.put("p_bankAcctType", data.getBankAcctType());
      inputMap.put("p_bankName", data.getBankName());
      inputMap.put("p_bankABARouting", data.getBankABARouting());
      inputMap.put("p_bankCityState", data.getBankCityState());
      inputMap.put("p_bankPhoneNumber", data.getBankPhoneNumber());
      inputMap.put("p_bankAcctName", data.getBankAcctName());
      inputMap.put("p_bankAcctNumber", data.getBankAcctNumber());
      inputMap.put("p_createdBy", "Invessence");
      return super.execute(inputMap);
   }

   public Map tdSaveACAT(Long acctnum, Long reqId,ACATDetails data) {
      Map<String, Object> inputMap = new HashMap<String, Object>();
      inputMap.put("p_acctnum", acctnum);
      inputMap.put("p_reqId", reqId);
      inputMap.put("p_fromAccountTitle", data.getFromAccountTitle());
      inputMap.put("p_accountNumber2", data.getAccountNumber2());
      inputMap.put("p_contraFirmList", data.getContraFirmList());
      inputMap.put("p_fromFirmAddress", data.getFromFirmAddress());
      inputMap.put("p_fromFirmPhoneNumber", data.getFromFirmPhoneNumber());
      inputMap.put("p_fromEEPlanType", data.getFromEEPlanType());
      inputMap.put("p_simpleFunded", data.getSimpleFunded());
      inputMap.put("p_fromOtherAccountType", data.getFromOtherAccountType());
      inputMap.put("p_transferTypeId", data.getTransferTypeId());
      inputMap.put("p_createdBy", "Invesence");
      inputMap.put("p_otherContraFirmList", data.getOtherContraFirmList());
      return super.execute(inputMap);
   }

   public Map tdSaveTDTransferData(Long acctnum, Long reqId,TDTransferDetails data) {
      Map<String, Object> inputMap = new HashMap<String, Object>();
      inputMap.put("p_acctnum", acctnum);
      inputMap.put("p_reqId", reqId);
      inputMap.put("p_accountTitle", data.getAccountTitle());
      inputMap.put("p_firmName", data.getFirmName());
      inputMap.put("p_primaryContact", data.getPrimaryContact());
      inputMap.put("p_priorFirmName", data.getPriorFirmName());
      inputMap.put("p_retailAccountNumber", data.getRetailAccountNumber());
      inputMap.put("p_advisorID", data.getAdvisorID());
      if(data.isRemoveAdvisor())
      {
         inputMap.put("p_removeAdvisor", "Y");
         inputMap.put("p_addAdvisor", "N");
      }
      else
      {
         inputMap.put("p_removeAdvisor", "N");
         inputMap.put("p_addAdvisor", "Y");
      }
      inputMap.put("p_ssn", data.getSsn());
      inputMap.put("p_createdBy", "Invesence");
      return super.execute(inputMap);
   }

   public Map tdSaveFEDWire(Long acctnum, FedwireAcctDetails data) {
      Map<String, Object> inputMap = new HashMap<String, Object>();
      inputMap.put("p_acctnum", acctnum);
      inputMap.put("p_moveMoneyPayMethodID", data.getMoveMoneyPayMethodID());
      inputMap.put("p_fedwireId", data.getFedwireId());
      inputMap.put("p_wireBankName", data.getWireBankName());
      inputMap.put("p_wireBankCityState", data.getWireBankCityState());
      inputMap.put("p_wireBankPhoneNumber", data.getInternatBankPhoneNumber());
      inputMap.put("p_wireABARouting", data.getWireABARouting());
      inputMap.put("p_wireBankAccountNumber", data.getWireBankAccountNumber());
      inputMap.put("p_wireBankAcctName", data.getWireBankAcctName());
      inputMap.put("p_wireFurtherCreditName", data.getWireFurtherCreditName());
      inputMap.put("p_furtherCreditAcctType", data.getFurtherCreditAcctType());
      inputMap.put("p_internatBankName", data.getInternatBankName());
      inputMap.put("p_internatBankStreetAddress",data.getInternatBankStreetAddress());
      inputMap.put("p_internatBankCityCountry", data.getInternatBankCityCountry());
      inputMap.put("p_internatBankPhoneNumber", data.getInternatBankPhoneNumber());
      inputMap.put("p_internatSWIFTCode", data.getInternatSWIFTCode());
      inputMap.put("p_internatAddBankRouting", data.getInternatAddBankRouting());
      inputMap.put("p_internatBankAcctName", data.getInternatBankAcctName());
      inputMap.put("p_internatBankAcctNumber", data.getInternatBankAcctNumber());
      inputMap.put("p_internatFurtherCreditInfo", data.getInternatFurtherCreditInfo());
      inputMap.put("p_internatPurpose", data.getInternatPurpose());
      inputMap.put("p_createdBy", "Invessence");
      return super.execute(inputMap);
   }

   public Map tdSaveElectronicPayment(Long acctnum,ElectronicFundDetails data) {
      Map<String, Object> inputMap = new HashMap<String, Object>();
      inputMap.put("p_acctnum", acctnum);
      inputMap.put("p_reqId", data.getReqId());
      inputMap.put("p_eftInstId", data.getEftInstId());
      inputMap.put("p_tdaiAcctNum", data.getTdaiAcctNum());
      inputMap.put("p_directionId", data.getDirectionId());
      inputMap.put("p_moveMoneyPayMethodID", data.getMoveMoneyPayMethodID());
      inputMap.put("p_achId", data.getAchId());
      inputMap.put("p_tranStartDate", data.getTranStartDate());
      inputMap.put("p_tranAmount", data.getTranAmount());
      inputMap.put("p_tranFreqId", data.getTranFreqId());
      inputMap.put("p_createdBy", "Invessence");
    /*  inputMap.put("p_fType",ftype);
      inputMap.put("p_ownerSPF",ownerSPF);
      inputMap.put("p_tranFreqId",data.getTranFreqId());
      inputMap.put("p_acctnum",acctnum);
      inputMap.put("p_fundType",fundType);
      inputMap.put("p_moveMoneyPayMethodID", data.getMoveMoneyPayMethodID());
      inputMap.put("p_initialInvestment", data.getTranAmount());
      inputMap.put("p_achId", data.getAchId());
      inputMap.put("p_bankAcctType", data.getBankAcctType());
      inputMap.put("p_bankName", data.getBankName());
      inputMap.put("p_bankABARouting", data.getBankABARouting());
      inputMap.put("p_bankCityState", data.getBankCityState());
      inputMap.put("p_bankPhoneNumber", data.getBankPhoneNumber());
      inputMap.put("p_bankAcctName", data.getBankAcctName());
      inputMap.put("p_bankAcctNumber", data.getBankAcctNumber());
      inputMap.put("p_createdBy", "Invessence");*/
      return super.execute(inputMap);
   }

   public Map tdMangedUserProfile(Long acctnum)
   {
      Map<String, Object> inputMap = new HashMap<String, Object>();
      inputMap.put("p_acctnum", acctnum);
      inputMap.put("p_managed", "P");
      return super.execute(inputMap);
   }


}
