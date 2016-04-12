package com.invessence.dao;

import java.sql.*;
import java.util.*;
import java.util.Date;

import com.invessence.bean.*;
import com.invessence.constant.Const;
import com.invessence.util.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.core.simple.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by abhangp on 1/19/2016.
 */
@Repository
public class WSCommonDaoImpl implements WSCommonDao
{
   private static final Logger logger = Logger.getLogger(WSCommonDaoImpl.class);
   @Autowired
   JdbcTemplate webServiceJdbcTemplate;

   public UserAcctDetails getUserAccDetailsByAccNumber(String accountNumber)throws SQLException{
      System.out.println("WSCommonDaoImpl.getUserAccDetailsByAccNumber");
      List<UserAcctDetails> lst = null;
//      String sql = "select clientAccountID, acctnum, internalRepID, repNum, repName, email, " +
//         "invite, applicantFName, applicantMName, applicantLName, mailAddrs1, mailAddrs2, mailCity, " +
//         "mailState, mailZipCode, primaryPhoneNbr, initialCusip, initialInvestment, ssn, created, lastUpdated " +
//         "from  ltam.ltam_acct_info where clientAccountID="+accountNumber;
 //     ExtInfoSP extInfoSP= new ExtInfoSP(webServiceJdbcTemplate);

//      DBResponse dbResponse= extInfoSP.execute(new UserAcctExt());

   //   System.out.println("extInfoSP = [" + dbResponse.toString() + "]");
//      String sql="select * from ltam_acct_info lai , user_logon_webservice ulw " +
//      "where /*ulw.status='P' and*/ ulw.clientAccountID=lai.clientAccountID " +
//         "and lai.clientAccountID=?";;
      lst = webServiceJdbcTemplate.query(SysParameters.getUserAccDetailsByAccNumber, new Object[]{accountNumber}, ParameterizedBeanPropertyRowMapper.newInstance(UserAcctDetails.class));
      return lst==null?null:lst.size()==0?null:lst.get(0);
   }
   public List<UserAcctDetails> getUserAccDetailsByWhereClause(String where)throws SQLException
   {
      List<UserAcctDetails> lst = null;
      logger.info("Fetching UserAccDetails ByWhere Clause");
      String sql = "select clientAccountID, acctnum, internalRepID, repNum, repName, email, " +
         "invite, applicantFName, applicantMName, applicantLName, mailAddrs1, mailAddrs2, mailCity, " +
         "mailState, mailZipCode, primaryPhoneNbr, initialCusip, initialInvestment, ssn, created, lastUpdated " +
         "from  ltam.ltam_acct_info "+where;
      lst = webServiceJdbcTemplate.query(sql, ParameterizedBeanPropertyRowMapper.newInstance(UserAcctDetails.class));
      return lst;
   }

   public List<UserAcctDetails> getPendingUserAccDetails()throws SQLException{
      List<UserAcctDetails> lst = null;
      logger.info("Fetching Pending User Account Details");
//      String sql="select * from ltam_acct_info lai , user_logon_webservice ulw " +
//         "where ulw.status='P' and ulw.clientAccountID=lai.clientAccountID";
//      System.out.println("SQL : "+sql);
//      String sql = "select * from ltam_acct_info lai right join user_logon_webservice ulw " +
//         "on ulw.status='P' and ulw.clientAccountID=lai.clientAccountID;";
      lst = webServiceJdbcTemplate.query(SysParameters.getPendingUserAccDetails, ParameterizedBeanPropertyRowMapper.newInstance(UserAcctDetails.class));
      return lst;
   }

   public boolean updatePendingUserAccDetails(UserAcctDetails userAcctDetails)throws SQLException{

      System.out.println(userAcctDetails.getOpt()+"userAcctDetails.getOpt()");
      if(userAcctDetails.getOpt().equals(Constants.succesResult))
      {
         webServiceJdbcTemplate.update(SysParameters.updatePendingUserAccDetailsOnSuccess,/*userAcctDetails.getUserID(),userAcctDetails.getPwd(),userAcctDetails.getFundGroupName(),
                          userAcctDetails.getSecurityQuestion(),userAcctDetails.getSecurityAnswer(),*/
                                       userAcctDetails.getPwd(), userAcctDetails.getStatus(), userAcctDetails.getRemarks(), new Date(), userAcctDetails.getClientAccountID());
      }else if(userAcctDetails.getOpt().equals(Constants.failureResult))
      {
         webServiceJdbcTemplate.update(SysParameters.updatePendingUserAccDetailsOnFailure,/*userAcctDetails.getUserID(),userAcctDetails.getPwd(),userAcctDetails.getFundGroupName(),
                          userAcctDetails.getSecurityQuestion(),userAcctDetails.getSecurityAnswer(),*/
                                       userAcctDetails.getStatus(), userAcctDetails.getRemarks(), new Date(), userAcctDetails.getClientAccountID());
      }


      return true;
   }

   public boolean updateUserEmail(UserAcctDetails userAcctDetails, String newEmail)throws SQLException{

      int i= webServiceJdbcTemplate.update(SysParameters.updateUserEmail,newEmail ,new Date(),userAcctDetails.getClientAccountID());

      return true;
   }

   public UserAcctExt getAccountExtInfo(String accountNumber)throws SQLException{
      List<UserAcctExt> lst = null;
      logger.info("Fetching UserAccDetails ByAccNumber");
      lst = webServiceJdbcTemplate.query(SysParameters.getAccountExtInfo, new Object[] {accountNumber}, ParameterizedBeanPropertyRowMapper.newInstance(UserAcctExt.class));
      return lst==null?null:lst.size()==0?null:lst.get(0);
   }

  //clientAccountID, accountType, dateOfBirth, mailingAddressId, mailingAddressType, created, lastUpdated, clientAccountID, id
   public boolean insertAccountExtInfo(UserAcctExt userAcctExt)throws SQLException
   {
      ExtInfoSP extInfoSP= new ExtInfoSP(webServiceJdbcTemplate);
      userAcctExt.setOpt(Constants.dbInsertOpt+"_"+userAcctExt.getOpt());
      System.out.println(userAcctExt.getOpt()+"userAcctExt.setOpt");
      DBResponse dbResponse= extInfoSP.execute(userAcctExt);

      System.out.println("dbResponse = [" + dbResponse.toString() + "]");
//      String sql = "insert into ltam_acct_info_ext (clientAccountID, accountType, dateOfBirth,mailingAddressId, mailingAddressType, created) " +
//         "values (?,?,?,?,?,?) ";
//      int i= webServiceJdbcTemplate.update(sql,userAcctExt.getClientAccountID(),userAcctExt.getAccountType(),userAcctExt.getDateOfBirth(),userAcctExt.getMailingAddressId(), userAcctExt.getMailingAddressType(), new Date());

      return true;
   }

   @Override
   public boolean updateAccountExtInfo(UserAcctExt userAcctExt)throws SQLException
   {
     ExtInfoSP extInfoSP= new ExtInfoSP(webServiceJdbcTemplate);

      extInfoSP.execute();

      String sql = "update ltam_acct_info_ext set " +
         "accountType=?, dateOfBirth=?, lastupdated=?" +
         "where clientAccountID=?";
      int i= webServiceJdbcTemplate.update(sql,userAcctExt.getAccountType(), userAcctExt.getDateOfBirth() ,new Date(),userAcctExt.getClientAccountID());

      return true;
   }


   public List<BrokerHostDetails> getBrokerHostDetails()throws SQLException
   {
      List<BrokerHostDetails> lst = null;
      logger.info("Fetching broker host details");
      String sql = "select vendor, environment, host, username, password, sourcedir, encrDecrKey from host_info ";
      lst = webServiceJdbcTemplate.query(sql, ParameterizedBeanPropertyRowMapper.newInstance(BrokerHostDetails.class));
      return lst;
   }

   public BrokerHostDetails getBrokerHostDetails(String where)throws SQLException
   {
      List<BrokerHostDetails> lst = null;
      logger.info("Fetching broker host details");
      String sql = "select vendor, environment, host, username, password, sourcedir, encrDecrKey from host_info where "+where;
      lst = webServiceJdbcTemplate.query(sql, ParameterizedBeanPropertyRowMapper.newInstance(BrokerHostDetails.class));
      return lst==null?null:lst.size()==0?null:lst.get(0);
   }

   public void truncateTable(String tableName) throws SQLException{
      String sql = "delete from "+tableName;
      webServiceJdbcTemplate.execute(sql);
   }


   @Transactional
   public void insertBatch(final List<String[]> dataArrLst, String sql, String proc)throws SQLException{
      logger.info("Processing batch insertion");
      if(sql==null || sql.equals("")){
         System.out.println("Insertion sql is not valid");
         logger.info("Insertion sql is not valid");
      }else
      {
         webServiceJdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter()
         {
            public int getBatchSize()
            {
               return dataArrLst.size();
            }

            public void setValues(PreparedStatement ps, int i) throws SQLException
            {
               String[] inData = dataArrLst.get(i);
               for (int ip = 1; ip <= inData.length; ip++)
               {
                  //System.out.print((inData[ip - 1].trim().replaceAll("\"", "")) + ",");
                  ps.setString(ip, inData[ip - 1].trim().replaceAll("\"", ""));
               }
               //System.out.println("");
            }
         });
      }
//      System.out.println("******************************");
      if(proc==null || proc.equals("")){
         logger.info("Procedure name is not valid");
      }else
      {
         logger.info("Calling post process procedure :"+proc);
         new SimpleJdbcCall(webServiceJdbcTemplate).withProcedureName(proc).execute();
      }

//      SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(webServiceJdbcTemplate).withProcedureName(proc);
//      simpleJdbcCall.execute();
//      Map<String, Object> inParamMap = new HashMap<String, Object>();
//      inParamMap.put("process", "MONTHLY");
//      SqlParameterSource in = new MapSqlParameterSource(inParamMap);
//      Map<String, Object> simpleJdbcCallResult = simpleJdbcCall.execute(in);
//      System.out.println(simpleJdbcCallResult);
//      System.out.println("******************************");


   }
   public void callEODProcess(String proc) throws SQLException{
      new SimpleJdbcCall(webServiceJdbcTemplate).withProcedureName(proc).execute();
//      SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(webServiceJdbcTemplate)
//         .withProcedureName(proc);
//
//      Map<String, Object> inParamMap = new HashMap<String, Object>();
//      inParamMap.put("firstName", "Smita");
//      inParamMap.put("lastName", "Chaudhari");
//      SqlParameterSource in = new MapSqlParameterSource(inParamMap);
//
//
//      Map<String, Object> simpleJdbcCallResult = simpleJdbcCall.execute(in);
//      System.out.println(simpleJdbcCallResult);
   }

}
