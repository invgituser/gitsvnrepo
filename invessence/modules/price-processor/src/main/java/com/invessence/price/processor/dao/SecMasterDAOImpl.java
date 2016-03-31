package com.invessence.price.processor.dao;

import java.sql.SQLException;
import java.util.List;

import com.invessence.price.processor.bean.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class SecMasterDAOImpl implements SecMasterDao
{

   @Autowired
   private JdbcTemplate jdbcTemplate;

   // code to get the list of tickers from  sec_master table whose status=A
   public List<SecMaster> findByWhere(String where) throws SQLException
   {
      List<SecMaster> lst = null;

      System.out.println("SecMasterDAOImpl.findByWhere()");
      String sql = "SELECT instrumentid, status, ticker, cusip, isin, name, assetclass, subclass, type, style, expenseRatio, lowerBoundReturn, upperBoundReturn, taxableReturn, nontaxableReturn, issuer, adv3months, aum, beta, securityRiskSTD, lowerbound, upperbound, yield, rbsaFlag FROM invdb.sec_master where " + where;
      lst = jdbcTemplate.query(sql, ParameterizedBeanPropertyRowMapper.newInstance(SecMaster.class));
      System.out.println("lst size**************** :" + lst.size());
      return lst;
   }

   //In this method we select the ticker for ondemand process
   public SecMaster findByTicker(String ticker) throws SQLException
   {
      List<SecMaster> lst = null;
      System.out.println("SecMasterDAOImpl.findByTicker()");
      String sql = "SELECT instrumentid, status, ticker, cusip, isin, name, assetclass, subclass, type, style, expenseRatio, lowerBoundReturn, upperBoundReturn, taxableReturn, nontaxableReturn, issuer, adv3months, aum, beta, securityRiskSTD, lowerbound, upperbound, yield, rbsaFlag FROM invdb.sec_master where ticker='" + ticker + "'";
      lst = jdbcTemplate.query(sql, ParameterizedBeanPropertyRowMapper.newInstance(SecMaster.class));
      System.out.println("lst size***************** :" + lst.size());
      return lst == null ? null : lst.size() == 0 ? null : lst.get(0);
   }


   //To get api which are active
   public List<APIDetails> getSwitch(String status, String service_operation) throws SQLException


   {

      List<APIDetails> switchTable = null;


      String sql = "SELECT service_provider,service_operation  FROM company_service_details WHERE " + status + " AND " + service_operation + " ORDER BY  priority  ASC";
      //where ticker='"+ticker+"'";
      System.out.println("***************************" + sql);
      switchTable = jdbcTemplate.query(sql, ParameterizedBeanPropertyRowMapper.newInstance(APIDetails.class));
      System.out.println("lst size**************** :" + switchTable.size());
      return switchTable;

   }

}
