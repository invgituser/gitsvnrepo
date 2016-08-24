package com.invessence.ws.provider.td.dao;

import java.sql.SQLException;
import java.util.*;

import com.docusign.esign.model.EnvelopeSummary;
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
   private final String getAcctOwnerDetails="select * from vwdc_acct_owners_details where acctnum=? ";
   private final String getBenefiaciaryDetails="select * from vwdc_benefiaciary_details where acctnum=? ";
   private final String getEmploymentDetails="select * from vwdc_employment_details where acctnum=? ";
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
   public AcctDetails getAcctDetails(Long acctNum, Long reqId) throws SQLException
   {
      List<AcctDetails> lst = null;
      logger.debug("getAcctDetails = "+getAcctDetails);
      lst = webServiceJdbcTemplate.query(getAcctDetails, new Object[]{acctNum},ParameterizedBeanPropertyRowMapper.newInstance(AcctDetails.class));
      return lst!=null ? lst.get(0):null;
   }

   @Override
   public List<AcctOwnerDetails> getAcctOwnerDetails(Long acctNum, Long reqId) throws SQLException
   {
      List<AcctOwnerDetails> lst = null;
      List<EmploymentDetails> emplyLst = null;
      List<BenefiaciaryDetails> beneLst = null;
      logger.debug("getAcctOwnerDetails = "+getAcctOwnerDetails);
      lst = webServiceJdbcTemplate.query(getAcctOwnerDetails, new Object[]{acctNum},ParameterizedBeanPropertyRowMapper.newInstance(AcctOwnerDetails.class));
      if(lst.size()>0){
         Iterator<AcctOwnerDetails> itr=lst.iterator();
         while(itr.hasNext()){
            AcctOwnerDetails aod=(AcctOwnerDetails)itr.next();
            emplyLst = webServiceJdbcTemplate.query(getEmploymentDetails, new Object[]{acctNum},ParameterizedBeanPropertyRowMapper.newInstance(EmploymentDetails.class));
            beneLst = webServiceJdbcTemplate.query(getBenefiaciaryDetails, new Object[]{acctNum},ParameterizedBeanPropertyRowMapper.newInstance(BenefiaciaryDetails.class));
            aod.setEmploymentDetails(emplyLst);
            aod.setBenefiaciaryDetails(beneLst);
         }
      }
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
