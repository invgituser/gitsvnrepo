package com.invessence.dao;

import java.sql.*;
import java.util.*;
import java.util.Date;

import com.invessence.bean.*;
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
public class CommonDaoImpl implements CommonDao
{
   private static final Logger logger = Logger.getLogger(CommonDaoImpl.class);
   @Autowired
   JdbcTemplate jdbcTemplate;

   public UserAcctDetails getUserAccDetailsByAccNumber(String accountNumber)throws SQLException{
      List<UserAcctDetails> lst = null;
      logger.info("Fetching UserAccDetails ByAccNumber");
//      String sql = "select clientAccountID, acctnum, internalRepID, repNum, repName, email, " +
//         "invite, applicantFName, applicantMName, applicantLName, mailAddrs1, mailAddrs2, mailCity, " +
//         "mailState, mailZipCode, primaryPhoneNbr, initialCusip, initialInvestment, ssn, created, lastUpdated " +
//         "from  ltam.ltam_acct_info where clientAccountID="+accountNumber;
      String sql="select * from ltam_acct_info lai , user_logon_webservice ulw " +
      "where /*ulw.status='P' and*/ ulw.clientAccountID=lai.clientAccountID " +
         "and lai.clientAccountID="+accountNumber;;
      lst = jdbcTemplate.query(sql, ParameterizedBeanPropertyRowMapper.newInstance(UserAcctDetails.class));
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
      lst = jdbcTemplate.query(sql, ParameterizedBeanPropertyRowMapper.newInstance(UserAcctDetails.class));
      return lst;
   }

   public List<UserAcctDetails> getPendingUserAccDetails()throws SQLException{
      List<UserAcctDetails> lst = null;
      logger.info("Fetching Pending User Account Details");
      String sql="select * from ltam_acct_info lai , user_logon_webservice ulw " +
         "where /*ulw.status='P' and*/ ulw.clientAccountID=lai.clientAccountID";
      System.out.println("SQL : "+sql);
//      String sql = "select * from ltam_acct_info lai right join user_logon_webservice ulw " +
//         "on ulw.status='P' and ulw.clientAccountID=lai.clientAccountID;";
      lst = jdbcTemplate.query(sql, ParameterizedBeanPropertyRowMapper.newInstance(UserAcctDetails.class));
      return lst;
   }

   public boolean updatePendingUserAccDetails(UserAcctDetails userAcctDetails)throws SQLException{

      String sql = "update user_logon_webservice set userID=?," +
         "pwd=?," +
         "fundGroupName=?," +
         "securityQuestion=?," +
         "securityAnswer=?," +
         "status=?," +
         "remarks=?," +
         "lastupdated=?" +
         "where clientAccountID=?";
         int i= jdbcTemplate.update(sql,userAcctDetails.getUserID(),userAcctDetails.getPwd(),userAcctDetails.getFundGroupName(),
                          userAcctDetails.getSecurityQuestion(),userAcctDetails.getSecurityAnswer(),
                          userAcctDetails.getStatus(),userAcctDetails.getRemarks(),new Date(),userAcctDetails.getClientAccountID());
//      if(){
//
//      }else if(){
//
//      }
      //System.out.println("Update query result :"+i);
      return true;
   }

   public boolean updateUserEmail(UserAcctDetails userAcctDetails, String newEmail)throws SQLException{
      String sql = "update ltam_acct_info set " +
         "email=?, lastupdated=?" +
         "where clientAccountID=?";
      int i= jdbcTemplate.update(sql,newEmail ,new Date(),userAcctDetails.getClientAccountID());

      return true;
   }


   public List<BrokerHostDetails> getBrokerHostDetails()throws SQLException
   {
      List<BrokerHostDetails> lst = null;
      logger.info("Fetching broker host details");
      String sql = "select vendor, environment, host, username, password, sourcedir, encrDecrKey from host_info ";
      lst = jdbcTemplate.query(sql, ParameterizedBeanPropertyRowMapper.newInstance(BrokerHostDetails.class));
      return lst;
   }

   public BrokerHostDetails getBrokerHostDetails(String where)throws SQLException
   {
      List<BrokerHostDetails> lst = null;
      logger.info("Fetching broker host details");
      String sql = "select vendor, environment, host, username, password, sourcedir, encrDecrKey from host_info where "+where;
      lst = jdbcTemplate.query(sql, ParameterizedBeanPropertyRowMapper.newInstance(BrokerHostDetails.class));
      return lst==null?null:lst.size()==0?null:lst.get(0);
   }

   public void truncateTable(String tableName) throws SQLException{
      String sql = "delete from "+tableName;
      jdbcTemplate.execute(sql);
   }


   @Transactional
   public void insertBatch(final List<String[]> dataArrLst, String sql, String proc)throws SQLException{
      logger.info("Processing batch insertion");
      if(sql==null || sql.equals("")){
         System.out.println("Insertion sql is not valid");
         logger.info("Insertion sql is not valid");
      }else
      {
         jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter()
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
         new SimpleJdbcCall(jdbcTemplate).withProcedureName(proc).execute();
      }

//      SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName(proc);
//      simpleJdbcCall.execute();
//      Map<String, Object> inParamMap = new HashMap<String, Object>();
//      inParamMap.put("process", "MONTHLY");
//      SqlParameterSource in = new MapSqlParameterSource(inParamMap);
//      Map<String, Object> simpleJdbcCallResult = simpleJdbcCall.execute(in);
//      System.out.println(simpleJdbcCallResult);
//      System.out.println("******************************");


   }
   public void callEODProcess(String proc) throws SQLException{
      new SimpleJdbcCall(jdbcTemplate).withProcedureName(proc).execute();
//      SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
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
