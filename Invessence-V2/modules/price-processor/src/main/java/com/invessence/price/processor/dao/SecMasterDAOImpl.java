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
   private JdbcTemplate invJdbcTemplate;

   // code to get the list of tickers from  sec_master table whose status=A
   public List<SecMaster> findByWhere(String where) throws SQLException
   {
      List<SecMaster> lst = null;

      System.out.println("SecMasterDAOImpl.findByWhere()");
      String sql = "SELECT instrumentid, status, ticker, cusip, isin, name, assetclass, subclass, type, style, expenseRatio, lowerBoundReturn, upperBoundReturn, taxableReturn, nontaxableReturn, issuer, adv3months, aum, beta, securityRiskSTD, lowerbound, upperbound, yield, rbsaFlag FROM invdb.sec_master where " + where;
      lst = invJdbcTemplate.query(sql, ParameterizedBeanPropertyRowMapper.newInstance(SecMaster.class));
      System.out.println("lst size**************** :" + lst.size());
      return lst;
   }

   //In this method we select the ticker for ondemand process
   public SecMaster findByTicker(String ticker) throws SQLException
   {
      List<SecMaster> lst = null;
      System.out.println("SecMasterDAOImpl.findByTicker()");
      String sql = "SELECT instrumentid, status,sm.ticker,ssm.tickersource, cusip, isin, name, assetclass, subclass, type, style, expenseRatio, lowerBoundReturn, upperBoundReturn, taxableReturn, nontaxableReturn, issuer, adv3months, aum, beta, securityRiskSTD, lowerbound, upperbound, yield, rbsaFlag FROM invdb.sec_master sm left join invdb.sec_source_mapping ssm on (sm.ticker=ssm.ticker) where sm.ticker='" + ticker + "'";
      lst = invJdbcTemplate.query(sql, ParameterizedBeanPropertyRowMapper.newInstance(SecMaster.class));
      System.out.println("lst size***************** :" + lst.size());
      return lst == null ? null : lst.size() == 0 ? null : lst.get(0);
   }


   //To get api which are active
   public List<APIDetails> getSwitch(String companyName, String service_operation) throws SQLException
   {
      List<APIDetails> switchTable = null;
      String sql = "SELECT  vendor service_provider, operation service_operation FROM service.service_operation_details WHERE status='A' " +
         "and company='"+companyName+"' AND operation='" + service_operation + "' ORDER BY  priority  ASC";

//      String sql = "SELECT service_provider,service_operation  FROM invdb.company_service_details WHERE status='A' " +
//         "and company='"+companyName+"' AND service_operation='" + service_operation + "' ORDER BY  priority  ASC";
//      //where ticker='"+ticker+"'";
      System.out.println("***************************" + sql);
      switchTable = invJdbcTemplate.query(sql, ParameterizedBeanPropertyRowMapper.newInstance(APIDetails.class));
      System.out.println("lst size**************** :" + switchTable.size());
      return switchTable;

   }

   public List<SecMaster> getTicker() throws SQLException
   {
      List<SecMaster> lst = null;

      System.out.println("SecMasterDAOImpl.getTicker()");
      //String sql = "SELECT instrumentid, status,sm.ticker,ssm.tickersource, cusip, isin, name, assetclass, subclass, type, style, expenseRatio, lowerBoundReturn, upperBoundReturn, taxableReturn, nontaxableReturn, issuer, adv3months, aum, beta, securityRiskSTD, lowerbound, upperbound, yield, rbsaFlag FROM invdb.sec_master sm left join invdb.sec_source_mapping ssm on (sm.ticker=ssm.ticker)";
     // String sql = "SELECT instrumentid, status, sm.ticker, ssm.tickersource, cusip, isin, name, assetclass, subclass, type, style, expenseRatio, lowerBoundReturn, upperBoundReturn,taxableReturn, nontaxableReturn, issuer, adv3months, aum, beta, securityRiskSTD, lowerbound, upperbound, yield, rbsaFlag,   MAX(rd.businessdate) AS 'businessdate',(CASE  WHEN rd.ticker IS NULL OR rd.ticker = '' THEN 'YES'  ELSE 'NO' END) AS 'onDemand', ssm.pricing_required FROM invdb.sec_source_mapping ssm inner JOIN invdb.sec_master sm ON (sm.ticker = ssm.ticker) LEFT JOIN rbsa.rbsa_daily rd ON (rd.ticker = sm.ticker) where  ssm.pricing_required='Y' and sm.status='A' GROUP BY sm.ticker  order by onDemand";
      String sql="select ssm.sec_ticker,ssm.ticker_source_name,ssm.tickersource,ssm.exchange_required, ifnull(max(rd.businessdate),'-') AS 'businessdate',(CASE  WHEN rd.ticker IS NULL OR rd.ticker = '' THEN 'YES'  ELSE 'NO' END) AS 'onDemand',ssm.base_currency,ssm.dest_currency FROM invdb.sec_source_mapping ssm  LEFT JOIN rbsa.rbsa_daily rd ON (rd.ticker = ssm.ticker_source_name and rd.dest_currency=ssm.dest_currency) where ssm.pricing_required='Y' GROUP BY ssm.sec_ticker,ssm.dest_currency  order by onDemand";
      lst = invJdbcTemplate.query(sql, ParameterizedBeanPropertyRowMapper.newInstance(SecMaster.class));
      System.out.println("lst size**************** :" + lst.size());
      return lst;
   }

}
