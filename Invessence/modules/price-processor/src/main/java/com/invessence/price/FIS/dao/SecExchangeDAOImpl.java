package com.invessence.price.FIS.dao;

import java.sql.*;
import java.sql.Date;
import java.util.*;

import com.invessence.price.FIS.bean.*;
import com.invessence.price.processor.bean.PriceData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class SecExchangeDAOImpl implements SecExchangeDao
{

   @Autowired
   private JdbcTemplate invJdbcTemplate;
   @Autowired
   private JdbcTemplate rbsaJdbcTemplate;

   public void delete(String symbol, String exchangeDate) throws SQLException
   {
      String sql = "DELETE from rbsa.sec_exchangerate_data where symbol='" + symbol + "' and exchangeDate='" + exchangeDate + "'";
      //rbsaJdbcTemplate = new JdbcTemplate(dataSource);
      rbsaJdbcTemplate.execute(sql);
   }

   public List<SecExchangeMaster> getSymbol() throws SQLException
   {
      List<SecExchangeMaster> lst = null;

      System.out.println("SecMasterDAOImpl.getTicker()");
      //String sql = "SELECT instrumentid, status,sm.ticker,ssm.tickersource, cusip, isin, name, assetclass, subclass, type, style, expenseRatio, lowerBoundReturn, upperBoundReturn, taxableReturn, nontaxableReturn, issuer, adv3months, aum, beta, securityRiskSTD, lowerbound, upperbound, yield, rbsaFlag FROM invdb.sec_master sm left join invdb.sec_source_mapping ssm on (sm.ticker=ssm.ticker)";
      String sql = "SELECT sem.symbol,ses.source,sem.fromCurrency,sem.toCurrency,sem.description,(CASE WHEN sed.symbol IS NULL OR sed.symbol = '' THEN 'YES'  ELSE 'NO' END) AS 'onDemand' FROM invdb.sec_exchangerate_master sem JOIN invdb.sec_exchangerate_source ses ON (ses.symbol = sem.symbol) left join rbsa.sec_exchangerate_data sed ON (sed.symbol = ses.symbol) WHERE ses.status = 'A' AND sem.status = 'A' GROUP BY sem.symbol order by onDemand";
      lst = rbsaJdbcTemplate.query(sql, ParameterizedBeanPropertyRowMapper.newInstance(SecExchangeMaster.class));
      System.out.println("lst size**************** :" + lst.size());
      return lst;
   }

   @Override
   public void insertBatch(final List<HistoricalDataRates> TimeSeriesPoint, final String symbol) throws SQLException
   {

      java.util.Date utilDate = new java.util.Date();
      final java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
      String sql = "INSERT INTO rbsa.sec_exchangerate_data "
         + "(symbol,exchangeDate,exchangeRate, reverseExchangeRate,created) VALUES (?, ?, ?,?,?)";


      rbsaJdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter()
      {
         public int getBatchSize()
         {
            return TimeSeriesPoint.size();
         }

         public void setValues(PreparedStatement ps, int i) throws SQLException
         {
            HistoricalDataRates exchangeData = TimeSeriesPoint.get(i);
            ps.setString(1, symbol);
            ps.setString(2, exchangeData.getDate());
            ps.setDouble(3, Double.parseDouble(exchangeData.getClose()));
            ps.setDouble(4, 1 / Double.parseDouble(exchangeData.getClose()));
            ps.setDate(5, sqlDate);

         }
      });
   }

   @Override
   public void insert(HistoricalData objDailyRates, String symbol) throws SQLException
   {
      java.util.Date utilDate = new java.util.Date();
      final java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
      String sql = "INSERT INTO rbsa.sec_exchangerate_data "
         + "(symbol,exchangeDate,exchangeRate, reverseExchangeRate,created) VALUES (?, ?, ?,?,?)";

      rbsaJdbcTemplate.update(sql,
                              new Object[]{symbol,
                                 objDailyRates.getHistoricalExchangeRates().getTimeSeriesPoint().get(0).getDate(),
                                 Double.parseDouble(objDailyRates.getHistoricalExchangeRates().getTimeSeriesPoint().get(0).getClose()),
                                 1 / Double.parseDouble(objDailyRates.getHistoricalExchangeRates().getTimeSeriesPoint().get(0).getClose()),
                                 sqlDate
                              });
   }

}
