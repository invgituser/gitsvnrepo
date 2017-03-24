package com.invessence.ws.provider.td.dao;

import java.sql.SQLException;
import java.util.*;

import com.docusign.esign.model.EnvelopeSummary;
import com.invessence.ws.bean.*;
import com.invessence.ws.provider.td.bean.VisaDetails;
import com.invessence.ws.provider.td.bean.*;
import com.invessence.ws.util.WSConstants;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

/**
 * Created by abhangp on 8/17/2016.
 */
@Repository
public class TDDaoLayerImpl implements TDDaoLayer
{
   private static final Logger logger = Logger.getLogger(TDDaoLayerImpl.class);

   @Autowired
   JdbcTemplate webServiceJdbcTemplate;
   private final String getDCRequests="select * from vwdc_requests where eventNum =? and acctnum=? and status='I'";
   private final String getAcctDetails="select * from vwdc_acct_details where acctnum=? ";
   private final String getBenefiaciaryDetails="select * from vwdc_benefiaciary_details where acctnum=? ";
   private final String getDupDocReqPartyDetails="select * from vwdc_employment_details where acctnum=? and 1=2";


   private final String getAcctOwnerDetails="select * from vwdc_acct_owners_details where acctnum=? ";
   private final String getEmploymentDetails="select * from vwdc_employment_details where acctnum=? and acctOwnerId=?";
   private final String getVisaDetails="select * from vwdc_visa_details where acctnum=? and 1=2";
   private final String updateEnvelopDetails="update dc_requests set envelopeId=? where eventNum=?";
   private final String insertReqAudit = "insert into dc_request_audit(eventNum, envelopeDetails, status, remarks, reqTime, resTime) value (?,?,?,?,?,?)";

   // Move Money
   private final String getMove_money_details="select * from vwdc_move_money_details where reqId =? and acctnum=? ";
   private final String getMMAchBankDetails="select * from vwdc_mm_ach_bank_details where moveMoneyPayMethodID =? ";
   private final String getMMFedwireAcctDetails="select * from vwdc_mm_internal_transfer_details where moveMoneyPayMethodID =? ";
   private final String getMMInternalTransferDetails="select * from vwdc_mm_fedwire_acct_details where moveMoneyPayMethodID =? ";

   private final String getAcctTransferDetails="select * from vwdc_acct_transfer_details where reqId=? and acctnum=?";
   private final String getTDTransferDetails="select * from vwdc_td_transfer_details where reqId=? and acctnum=?";

   private final String getElecFundTransferDetails="select * from vwdc_elecfund_transfer_details where reqId=? and acctnum=?";

   private final String getAcctChngAddrDetails ="select * from vwdc_td_chng_addrs_details where accountNumber=?";




   @Override
   public List<DCRequest> getDCRequests(Long acctNum, Integer eventNum) throws SQLException
   {
      List<DCRequest> lst = null;
      logger.debug("getDCRequests = "+getDCRequests);
      lst = webServiceJdbcTemplate.query(getDCRequests, new Object[]{eventNum, acctNum},ParameterizedBeanPropertyRowMapper.newInstance(DCRequest.class));
      return lst;
   }

   @Override
   public AcctDetails getAcctDetails(Long acctNum, Long reqId, boolean reqAllData) throws SQLException
   {
      List<AcctDetails> lst = null;
      List<BenefiaciaryDetails> beneLst = null;
      List<DupDocReqParty> dupDocReqDetails = null;
      logger.debug("getAcctDetails = "+getAcctDetails);
      lst = webServiceJdbcTemplate.query(getAcctDetails, new Object[]{acctNum},ParameterizedBeanPropertyRowMapper.newInstance(AcctDetails.class));
      if (reqAllData && lst.size() > 0)
      {
         Iterator<AcctDetails> itr = lst.iterator();
         while (itr.hasNext())
         {
            AcctDetails ad = (AcctDetails) itr.next();
            beneLst = webServiceJdbcTemplate.query(getBenefiaciaryDetails, new Object[]{acctNum}, ParameterizedBeanPropertyRowMapper.newInstance(BenefiaciaryDetails.class));
            dupDocReqDetails = webServiceJdbcTemplate.query(getDupDocReqPartyDetails, new Object[]{acctNum}, ParameterizedBeanPropertyRowMapper.newInstance(DupDocReqParty.class));
            ad.setBenefiaciaryDetails(beneLst);
            ad.setDupDocReqParty((dupDocReqDetails==null || dupDocReqDetails.size()<=0)?null:dupDocReqDetails.get(0));
         }
      }
      return lst==null || lst.size()<=0 ? null: lst.get(0);
   }

   @Override
   public List<AcctOwnerDetails> getAcctOwnerDetails(Long acctNum, Long reqId, boolean reqAllData) throws SQLException
   {
      List<AcctOwnerDetails> lst = null;
      List<EmploymentDetails> emplyLst = null;
      List<VisaDetails> visaDetails = null;
      logger.debug("getAcctOwnerDetails = "+getAcctOwnerDetails);
      lst = webServiceJdbcTemplate.query(getAcctOwnerDetails, new Object[]{acctNum},ParameterizedBeanPropertyRowMapper.newInstance(AcctOwnerDetails.class));

      if (reqAllData && lst.size() > 0)
      {
         Iterator<AcctOwnerDetails> itr = lst.iterator();
         while (itr.hasNext())
         {
            AcctOwnerDetails aod = (AcctOwnerDetails) itr.next();
            emplyLst = webServiceJdbcTemplate.query(getEmploymentDetails, new Object[]{acctNum, aod.getAcctOwnerId()}, ParameterizedBeanPropertyRowMapper.newInstance(EmploymentDetails.class));
            visaDetails = webServiceJdbcTemplate.query(getVisaDetails, new Object[]{acctNum}, ParameterizedBeanPropertyRowMapper.newInstance(VisaDetails.class));
            aod.setEmploymentDetails(emplyLst);
            aod.setVisaDetails((visaDetails==null || visaDetails.size()<=0)?null:visaDetails.get(0));
         }
      }
      return lst;
   }

   @Override
   public List<BenefiaciaryDetails> getBenefiaciaryDetails(Long acctNum, Long reqId) throws SQLException
   {
      List<BenefiaciaryDetails> lst = null;
      logger.debug("getBenefiaciaryDetails = "+getBenefiaciaryDetails);
      lst = webServiceJdbcTemplate.query(getBenefiaciaryDetails, new Object[]{acctNum}, ParameterizedBeanPropertyRowMapper.newInstance(BenefiaciaryDetails.class));

      return lst;
   }

//   @Override
//   public MoveMoneyDetails getMoveMoneyDetail(Long acctNum, Integer reqId) throws SQLException
//   {
//      List<MoveMoneyDetails> lst = null;
//      logger.debug("getMoveMoneyDetail = "+getMove_money_details);
//      lst = webServiceJdbcTemplate.query(getMove_money_details, new Object[]{reqId,acctNum}, ParameterizedBeanPropertyRowMapper.newInstance(MoveMoneyDetails.class));
//
//      return lst==null || lst.size()<=0 ? null: lst.get(0);
//   }

   @Override
   public List<MoveMoneyDetails> getMoveMoneyDetails(Long acctNum, Long reqId) throws SQLException
   {
      List<MoveMoneyDetails> lst = null;
      logger.debug("getMoveMoneyDetail = "+getMove_money_details);
      lst = webServiceJdbcTemplate.query(getMove_money_details, new Object[]{reqId,acctNum}, ParameterizedBeanPropertyRowMapper.newInstance(MoveMoneyDetails.class));

      return lst;
   }

   @Override
   public List<MMAchBankDetails> getMMAchBankDetails(Long acctNum, Long moveMoneyPayMethodID) throws SQLException
   {
      List<MMAchBankDetails> lst = null;
      logger.debug("getMMAchBankDetails = "+getMMAchBankDetails);
      lst = webServiceJdbcTemplate.query(getMMAchBankDetails, new Object[]{moveMoneyPayMethodID}, ParameterizedBeanPropertyRowMapper.newInstance(MMAchBankDetails.class));

      return lst;
   }

   @Override
   public List<MMFedwireAcctDetails> getMMFedwireAcctDetails(Long acctNum, Long moveMoneyPayMethodID) throws SQLException
   {
      List<MMFedwireAcctDetails> lst = null;
      logger.debug("getMMFedwireAcctDetails = "+getMMFedwireAcctDetails);
      lst = webServiceJdbcTemplate.query(getMMFedwireAcctDetails, new Object[]{moveMoneyPayMethodID}, ParameterizedBeanPropertyRowMapper.newInstance(MMFedwireAcctDetails.class));

      return lst;
   }

   @Override
   public List<MMInternalTransferDetails> getMMInternalTransferDetails(Long acctNum, Long moveMoneyPayMethodID) throws SQLException
   {
      List<MMInternalTransferDetails> lst = null;
      logger.debug("getMMInternalTransferDetails = "+getMMInternalTransferDetails);
      lst = webServiceJdbcTemplate.query(getMMInternalTransferDetails, new Object[]{moveMoneyPayMethodID}, ParameterizedBeanPropertyRowMapper.newInstance(MMInternalTransferDetails.class));

      return lst;
   }

   @Override
   public AcctTransferDetails getAcctTransferDetails(Long acctNum, Long reqId) throws SQLException
   {
      List<AcctTransferDetails> lst = null;
      logger.debug("getAcctTransferDetails = "+getAcctTransferDetails);
      lst = webServiceJdbcTemplate.query(getAcctTransferDetails, new Object[]{reqId, acctNum}, ParameterizedBeanPropertyRowMapper.newInstance(AcctTransferDetails.class));

      return lst==null || lst.size()<=0 ? null: lst.get(0);
   }

   @Override
   public TDTransferDetails getTDTransferDetails(Long acctNum, Long reqId) throws SQLException
   {
      List<TDTransferDetails> lst = null;
      logger.debug("getTDTransferDetails = "+getTDTransferDetails);
      lst = webServiceJdbcTemplate.query(getTDTransferDetails, new Object[]{reqId, acctNum}, ParameterizedBeanPropertyRowMapper.newInstance(TDTransferDetails.class));

      return lst==null || lst.size()<=0 ? null: lst.get(0);
   }


   @Override
   public ElecFundTransferDetails getElecFundTransferDetails(Long acctNum, Long reqId) throws SQLException
   {
      List<ElecFundTransferDetails> lst = null;
      logger.debug("getElecFundTransferDetails = "+getElecFundTransferDetails);
      lst = webServiceJdbcTemplate.query(getElecFundTransferDetails, new Object[]{reqId, acctNum}, ParameterizedBeanPropertyRowMapper.newInstance(ElecFundTransferDetails.class));

      return lst==null || lst.size()<=0 ? null: lst.get(0);
   }

   @Override
   public GetAcctChngAddrDetails getAcctChngAddrDetails(Long acctNum) throws SQLException
   {
      List<GetAcctChngAddrDetails> lst = null;
      logger.debug("getAcctChngAddrDetails = "+getAcctChngAddrDetails);
      lst = webServiceJdbcTemplate.query(getAcctChngAddrDetails, new Object[]{acctNum}, ParameterizedBeanPropertyRowMapper.newInstance(GetAcctChngAddrDetails.class));

      return lst==null || lst.size()<=0 ? null: lst.get(0);
   }


   @Override
   public void updateEnvelopDetails(int eventNum, EnvelopeSummary envelopeSummary) throws SQLException
   {
      logger.debug("insertReqAudit = "+insertReqAudit);
      webServiceJdbcTemplate.update(insertReqAudit, new Object[]{eventNum});

      logger.debug("updateEnvelopDetails = "+updateEnvelopDetails);
      webServiceJdbcTemplate.update(updateEnvelopDetails, new Object[]{envelopeSummary.getEnvelopeId(),eventNum});
   }

   @Override
   public boolean callDCAuditSP(DCRequestAudit dcRequest)
   {
      try
      {
         logger.info("TDDaoLayerImpl.callDCAuditSP");
         logger.debug("dcRequest = " + dcRequest);

         SPDCRequestAuditrial requestAuditrialSP = new SPDCRequestAuditrial(webServiceJdbcTemplate);
         dcRequest.setResTime(new Date());
         dcRequest.setOpt(WSConstants.dbInsertOpt);
         logger.debug("dcRequest.setOpt = " + dcRequest.getOpt());
         DBResponse dbResponse = requestAuditrialSP.execute(dcRequest);
         logger.debug("dbResponse = [" + dbResponse.toString() + "]");
      }catch (Exception e){
         logger.error("Issue while storing web request in DB :"+e.getMessage());

      }
      return true;
   }
}
