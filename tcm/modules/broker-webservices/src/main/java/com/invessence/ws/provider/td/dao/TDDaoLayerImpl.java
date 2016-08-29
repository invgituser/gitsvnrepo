package com.invessence.ws.provider.td.dao;

import java.sql.SQLException;
import java.util.*;

import com.docusign.esign.model.EnvelopeSummary;
import com.invessence.ws.provider.td.bean.VisaDetails;
import com.invessence.ws.provider.td.bean.*;
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
   private final String getDCRequests="select * from vwdc_requests where eventNum =? and acctnum=? ";
   private final String getAcctDetails="select * from vwdc_acct_details where acctnum=? ";
   private final String getBenefiaciaryDetails="select * from vwdc_benefiaciary_details where acctnum=? ";
   private final String getDupDocReqPartyDetails="select * from vwdc_employment_details where acctnum=? and 1=2";


   private final String getAcctOwnerDetails="select * from vwdc_acct_owners_details where acctnum=? ";
   private final String getEmploymentDetails="select * from vwdc_employment_details where acctnum=? ";
   private final String getVisaDetails="select * from vwdc_employment_details where acctnum=? and 1=2";
   private final String updateEnvelopDetails="update dc_requests set envelopeId=? where eventNum=?";
   private final String insertReqAudit = "insert into dc_request_audit(eventNum, envelopeDetails, status, remarks, reqTime, resTime) value (?,?,?,?,?,?)";

   @Override
   public List<DCRequest> getDCRequests(Long acctNum, int eventNum) throws SQLException
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
            emplyLst = webServiceJdbcTemplate.query(getEmploymentDetails, new Object[]{acctNum}, ParameterizedBeanPropertyRowMapper.newInstance(EmploymentDetails.class));
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

   @Override
   public void updateEnvelopDetails(int eventNum, EnvelopeSummary envelopeSummary) throws SQLException
   {
      logger.debug("insertReqAudit = "+insertReqAudit);
      webServiceJdbcTemplate.update(insertReqAudit, new Object[]{eventNum});

      logger.debug("updateEnvelopDetails = "+updateEnvelopDetails);
      webServiceJdbcTemplate.update(updateEnvelopDetails, new Object[]{envelopeSummary.getEnvelopeId(),eventNum});
   }
}
