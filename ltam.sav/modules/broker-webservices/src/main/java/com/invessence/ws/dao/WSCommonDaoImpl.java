package com.invessence.ws.dao;

import java.sql.*;
import java.util.*;
import java.util.Date;

import com.invessence.ws.bean.*;
import com.invessence.ws.util.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.core.simple.*;
import org.springframework.stereotype.*;

/**
 * Created by abhangp on 1/19/2016.
 */
@Repository
public class WSCommonDaoImpl implements WSCommonDao
{
   private static final Logger logger = Logger.getLogger(WSCommonDaoImpl.class);
   @Autowired
   JdbcTemplate webServiceJdbcTemplate;

   private final String getSverviceDetails="select * from vw_service_details where company =? and serviceStatus='A' and operationStatus='A' order by service, provider";
   private final String getUserAccDetailsByAccNumber="select * from vw_ws_web_user_details where clientAccountID=?";
   private final String getPendingUserAccDetails="select * from vw_ws_web_user_details where status='P' ";
//   private final String updatePendingUserAccDetailsOnSuccess="update user_logon_webservice set pwd=?, status=?,remarks=?,lastupdated=? where clientAccountID=?";
//   private final String updatePendingUserAccDetailsOnFailure="update user_logon_webservice set status=?,remarks=?,lastupdated=? where clientAccountID=?";
//   //private final String updateUserEmail="update ext_acct_info set email=?, lastupdated=? where clientAccountID=?";
   //private String #getAccountExtInfo="select * from ext_acct_info_extension where  clientAccountID=?";
   private final String getAccountExtInfo="select * from vw_ws_web_user_ext_details where  clientAccountID=?";

   public UserAcctDetails getUserAccDetailsByAccNumber(String accountNumber)throws SQLException{
      logger.info("WSCommonDaoImpl.getUserAccDetailsByAccNumber");
      List<UserAcctDetails> lst = null;
      logger.debug("getUserAccDetailsByAccNumber = "+getUserAccDetailsByAccNumber);
      lst = webServiceJdbcTemplate.query(getUserAccDetailsByAccNumber, new Object[]{accountNumber}, ParameterizedBeanPropertyRowMapper.newInstance(UserAcctDetails.class));
      return lst==null?null:lst.size()==0?null:lst.get(0);
   }
   //      String sql = "select clientAccountID, acctnum, internalRepID, repNum, repName, email, " +
//         "invite, applicantFName, applicantMName, applicantLName, mailAddrs1, mailAddrs2, mailCity, " +
//         "mailState, mailZipCode, primaryPhoneNbr, initialCusip, initialInvestment, ssn, created, lastUpdated " +
//         "from  ltam.ltam_acct_info where clientAccountID="+accountNumber;

   public List<UserAcctDetails> getUserAccDetailsByWhereClause(String where)throws SQLException
   {
      logger.info("WSCommonDaoImpl.getUserAccDetailsByWhereClause");
      List<UserAcctDetails> lst = null;
      String sql = "select clientAccountID, acctnum, internalRepID, repNum, repName, email, " +
         "invite, applicantFName, applicantMName, applicantLName, mailAddrs1, mailAddrs2, mailCity, " +
         "mailState, mailZipCode, primaryPhoneNbr, initialCusip, initialInvestment, ssn, created, lastUpdated " +
         "from  ltam.ltam_acct_info "+where;
      logger.debug("getUserAccDetailsByWhereClause = "+sql);
      lst = webServiceJdbcTemplate.query(sql, ParameterizedBeanPropertyRowMapper.newInstance(UserAcctDetails.class));
      return lst;
   }

   public List<UserAcctDetails> getPendingUserAccDetails()throws SQLException{
      logger.info("WSCommonDaoImpl.getPendingUserAccDetails");
      List<UserAcctDetails> lst = null;
      logger.debug("getPendingUserAccDetails = "+getPendingUserAccDetails);
      lst = webServiceJdbcTemplate.query(getPendingUserAccDetails, ParameterizedBeanPropertyRowMapper.newInstance(UserAcctDetails.class));
      return lst;
   }

   public boolean updatePendingUserAccDetails(UserAcctDetails userAcctDetails)throws SQLException{
      logger.info("WSCommonDaoImpl.updatePendingUserAccDetails");
      logger.debug("userAcctDetails = [" + userAcctDetails + "]");


      if(userAcctDetails.getOpt().equals(WSConstants.succesResult))
      {
//         logger.debug("updatePendingUserAccDetailsOnSuccess = "+updatePendingUserAccDetailsOnSuccess);
//         webServiceJdbcTemplate.update(updatePendingUserAccDetailsOnSuccess,/*userAcctDetails.getUserID(),userAcctDetails.getPwd(),userAcctDetails.getFundGroupName(),
//                          userAcctDetails.getSecurityQuestion(),userAcctDetails.getSecurityAnswer(),*/
//                                       userAcctDetails.getPwd(), userAcctDetails.getStatus(), userAcctDetails.getRemarks(), new Date(), userAcctDetails.getClientAccountID());
         SPAccountDetails spAccountDetails= new SPAccountDetails(webServiceJdbcTemplate);
         userAcctDetails.setOpt(WSConstants.dbUpdateOpt+"_"+userAcctDetails.getOpt());
         logger.debug("wsRequest.setOpt = " + userAcctDetails.getOpt());
         DBResponse dbResponse= spAccountDetails.execute(userAcctDetails);
         logger.debug("dbResponse = [" + dbResponse.toString() + "]");
      }else if(userAcctDetails.getOpt().equals(WSConstants.failureResult))
      {
//         logger.debug("updatePendingUserAccDetailsOnFailure = "+updatePendingUserAccDetailsOnFailure);
//         webServiceJdbcTemplate.update(updatePendingUserAccDetailsOnFailure,/*userAcctDetails.getUserID(),userAcctDetails.getPwd(),userAcctDetails.getFundGroupName(),
//                          userAcctDetails.getSecurityQuestion(),userAcctDetails.getSecurityAnswer(),*/
//                                       userAcctDetails.getStatus(), userAcctDetails.getRemarks(), new Date(), userAcctDetails.getClientAccountID());
         SPAccountDetails extInfoSP= new SPAccountDetails(webServiceJdbcTemplate);
         userAcctDetails.setOpt(WSConstants.dbUpdateOpt+"_"+userAcctDetails.getOpt());
         logger.debug("wsRequest.setOpt = " + userAcctDetails.getOpt());
         DBResponse dbResponse= extInfoSP.execute(userAcctDetails);
         logger.debug("dbResponse = [" + dbResponse.toString() + "]");
      }
      return true;
   }

   @Override
   public List<ServiceDetails> getServiceDetails(String company) throws SQLException
   {
      logger.info("WSCommonDaoImpl.getServiceDetails");
      List<ServiceDetails> lst = null;
      logger.debug("getServiceDetails = "+getSverviceDetails);
      lst = webServiceJdbcTemplate.query(getSverviceDetails, new Object[]{company}, ParameterizedBeanPropertyRowMapper.newInstance(ServiceDetails.class));
      return lst;
   }

//   public boolean updateUserEmail(UserAcctDetails userAcctDetails, String newEmail)throws SQLException{
//      logger.info("WSCommonDaoImpl.updateUserEmail");
//      logger.info("userAcctDetails = [" + userAcctDetails + "], newEmail = [" + newEmail + "]");
//      int i= webServiceJdbcTemplate.update(updateUserEmail,newEmail ,new Date(),userAcctDetails.getClientAccountID());
//
//      return true;
//   }

   public UserAcctExt getAccountExtInfo(String accountNumber)throws SQLException{
      logger.info("WSCommonDaoImpl.getAccountExtInfo");
      logger.debug("accountNumber = [" + accountNumber + "]");
      List<UserAcctExt> lst = null;
     logger.debug("getAccountExtInfo = "+getAccountExtInfo);
      lst = webServiceJdbcTemplate.query(getAccountExtInfo, new Object[] {accountNumber}, ParameterizedBeanPropertyRowMapper.newInstance(UserAcctExt.class));
      return lst==null?null:lst.size()==0?null:lst.get(0);
   }

  //clientAccountID, accountType, dateOfBirth, mailingAddressId, mailingAddressType, created, lastUpdated, clientAccountID, id
   public boolean insertAccountExtInfo(UserAcctExt userAcctExt)throws SQLException
   {
      logger.info("WSCommonDaoImpl.insertAccountExtInfo");
      logger.debug("wsRequest = " + userAcctExt);

      SPExtInfo extInfoSP= new SPExtInfo(webServiceJdbcTemplate);
      userAcctExt.setOpt(WSConstants.dbInsertOpt+"_"+userAcctExt.getOpt());
      logger.debug("wsRequest.setOpt = "+userAcctExt.getOpt());
      DBResponse dbResponse= extInfoSP.execute(userAcctExt);
      logger.debug("dbResponse = [" + dbResponse.toString() + "]");

      return true;
   }

   public boolean insertWSRequest(WSRequest wsRequest)
   {
      try
      {
         logger.info("WSCommonDaoImpl.insertWSRequest");
         logger.debug("wsRequest = " + wsRequest);

         SPRequestAuditrial requestAuditrialSP = new SPRequestAuditrial(webServiceJdbcTemplate);
         wsRequest.setResTime(new Date());
         wsRequest.setOpt(WSConstants.dbInsertOpt);
         logger.debug("wsRequest.setOpt = " + wsRequest.getOpt());
         DBResponse dbResponse = requestAuditrialSP.execute(wsRequest);
         logger.debug("dbResponse = [" + dbResponse.toString() + "]");
      }catch (Exception e){
         logger.error("Issue while storing web request in DB :"+e.getMessage());

      }
      return true;
   }

   @Override
   public boolean updateAccountExtInfo(UserAcctExt userAcctExt)throws SQLException
   {
      logger.info("WSCommonDaoImpl.updateAccountExtInfo");
      logger.debug("wsRequest = " + userAcctExt);
      SPExtInfo extInfoSP= new SPExtInfo(webServiceJdbcTemplate);
      extInfoSP.execute();

      String sql = "update ltam_acct_info_ext set " +
         "accountType=?, dateOfBirth=?, lastupdated=?" +
         "where clientAccountID=?";
      int i= webServiceJdbcTemplate.update(sql,userAcctExt.getAccountType(), userAcctExt.getDateOfBirth() ,new Date(),userAcctExt.getClientAccountID());

      return true;
   }


//   public List<BrokerHostDetails> getBrokerHostDetails()throws SQLException
//   {
//      List<BrokerHostDetails> lst = null;
//      logger.info("Fetching broker host details");
//      String sql = "select vendor, environment, host, username, password, sourcedir, encrDecrKey from host_info ";
//      lst = webServiceJdbcTemplate.query(sql, ParameterizedBeanPropertyRowMapper.newInstance(BrokerHostDetails.class));
//      return lst;
//   }
//
//   public BrokerHostDetails getBrokerHostDetails(String where)throws SQLException
//   {
//      List<BrokerHostDetails> lst = null;
//      logger.info("Fetching broker host details");
//      String sql = "select vendor, environment, host, username, password, sourcedir, encrDecrKey from host_info where "+where;
//      lst = webServiceJdbcTemplate.query(sql, ParameterizedBeanPropertyRowMapper.newInstance(BrokerHostDetails.class));
//      return lst==null?null:lst.size()==0?null:lst.get(0);
//   }
//
//   public void truncateTable(String tableName) throws SQLException{
//      String sql = "delete from "+tableName;
//      webServiceJdbcTemplate.execute(sql);
//   }
//
//
//   @Transactional
//   public void insertBatch(final List<String[]> dataArrLst, String sql, String proc)throws SQLException{
//      logger.info("Processing batch insertion");
//      if(sql==null || sql.equals("")){
//         System.out.println("Insertion sql is not valid");
//         logger.info("Insertion sql is not valid");
//      }else
//      {
//         webServiceJdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter()
//         {
//            public int getBatchSize()
//            {
//               return dataArrLst.size();
//            }
//
//            public void setValues(PreparedStatement ps, int i) throws SQLException
//            {
//               String[] inData = dataArrLst.get(i);
//               for (int ip = 1; ip <= inData.length; ip++)
//               {
//                  //System.out.print((inData[ip - 1].trim().replaceAll("\"", "")) + ",");
//                  ps.setString(ip, inData[ip - 1].trim().replaceAll("\"", ""));
//               }
//               //System.out.println("");
//            }
//         });
//      }
////      System.out.println("******************************");
//      if(proc==null || proc.equals("")){
//         logger.info("Procedure name is not valid");
//      }else
//      {
//         logger.info("Calling post process procedure :"+proc);
//         new SimpleJdbcCall(webServiceJdbcTemplate).withProcedureName(proc).execute();
//      }
//
////      SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(webServiceJdbcTemplate).withProcedureName(proc);
////      simpleJdbcCall.execute();
////      Map<String, Object> inParamMap = new HashMap<String, Object>();
////      inParamMap.put("process", "MONTHLY");
////      SqlParameterSource in = new MapSqlParameterSource(inParamMap);
////      Map<String, Object> simpleJdbcCallResult = simpleJdbcCall.execute(in);
////      System.out.println(simpleJdbcCallResult);
////      System.out.println("******************************");
//
//
//   }
//   public void callEODProcess(String proc) throws SQLException{
//      new SimpleJdbcCall(webServiceJdbcTemplate).withProcedureName(proc).execute();
////      SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(webServiceJdbcTemplate)
////         .withProcedureName(proc);
////
////      Map<String, Object> inParamMap = new HashMap<String, Object>();
////      inParamMap.put("firstName", "Smita");
////      inParamMap.put("lastName", "Chaudhari");
////      SqlParameterSource in = new MapSqlParameterSource(inParamMap);
////
////
////      Map<String, Object> simpleJdbcCallResult = simpleJdbcCall.execute(in);
////      System.out.println(simpleJdbcCallResult);
//   }

}
