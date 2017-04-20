package com.invessence.price.processor.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.*;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.invessence.price.processor.bean.PriceData;

@Repository
@Transactional
public class PriceDataDAOImpl implements PriceDataDao
{
//   @Autowired
//   DataSource dataSource;


   @Autowired
//   @Qualifier("rbsaDataSource")
   private JdbcTemplate rbsaJdbcTemplate;

   public void delete() throws SQLException
   {

      String sql = "DELETE from tmp_rbsa_daily";
      //rbsaJdbcTemplate = new JdbcTemplate(dataSource);
      rbsaJdbcTemplate.execute(sql);
   }

   public void callProcedure(String process, String businessDate, String ticker) throws SQLException
   {

      System.out.println("callProcedure process "+process+" businessDate "+businessDate+" ticker "+ticker);
      //rbsaJdbcTemplate = new JdbcTemplate(dataSource);
      SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(rbsaJdbcTemplate)
         .withProcedureName("price_processor");
      Map<String, Object> inParamMap = new HashMap<String, Object>();
      inParamMap.put("p_process", process);
      inParamMap.put("p_businessDate", businessDate);
      inParamMap.put("p_ticker", ticker);
      SqlParameterSource in = new MapSqlParameterSource(inParamMap);
      Map<String, Object> simpleJdbcCallResult = simpleJdbcCall.execute(in);
      System.out.println(simpleJdbcCallResult);
      System.out.println("******************************");


   }

   public void callEodProcedure(String process, String businessDate) throws SQLException
   {
      System.out.println("callEodProcedure Process "+process+" businessDate "+businessDate);
      //rbsaJdbcTemplate = new JdbcTemplate(dataSource);
      SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(rbsaJdbcTemplate)
         .withProcedureName("end_of_price_process");
      Map<String, Object> inParamMap = new HashMap<String, Object>();
      inParamMap.put("p_process", process);
      inParamMap.put("p_businessdate", businessDate);
      SqlParameterSource in = new MapSqlParameterSource(inParamMap);
      Map<String, Object> simpleJdbcCallResult = simpleJdbcCall.execute(in);
      System.out.println(simpleJdbcCallResult);
      System.out.println("******************************");


   }

   public void insert(PriceData priceData) throws SQLException
   {


      String sql = "INSERT INTO tmp_rbsa_daily "
         + "(TICKER, BUSINESSDATE, OPEN_PRICE, CLOSE_PRICE, HIGH_PRICE, LOW_PRICE, PREV_CLOSE_PRICE,VOLUME/*,INSERTED_BY,INSERTED_ON*/) VALUES (?, ?, ?, ?,?,?,?,?/*, ?, ?*/)";

      //rbsaJdbcTemplate = new JdbcTemplate(dataSource);

      rbsaJdbcTemplate.update(sql,
                              new Object[]{priceData.getTicker(), priceData.getBusinessDate(), priceData.getOpenPrice(),
                                 priceData.getClosePrice(), priceData.getHighPrice(), priceData.getLowPrice(), priceData
                                 .getPrevClosePrice(),
                                 priceData.getVolume()/* ,new Long (63), new Date() */
                              });


   }


   public void log(String error)
   {

   }

   public void insertBatch(final List<PriceData> customers)
   {

      String sql = "INSERT INTO tmp_rbsa_daily "
         + "(TICKER, BUSINESSDATE, OPEN_PRICE, CLOSE_PRICE, HIGH_PRICE, LOW_PRICE, ADJUSTED_PRICE,VOLUME/*,INSERTED_BY,INSERTED_ON*/) VALUES (?, ?, ?, ?,?,?,?,?/*, ?, ?*/)";

      rbsaJdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter()
      {

         public int getBatchSize()
         {
            return customers.size();
         }

         public void setValues(PreparedStatement ps, int i) throws SQLException
         {

            PriceData priceData = customers.get(i);

            ps.setString(1, priceData.getTicker());
            //ps.setDate(2,  new java.sql.Date(priceData.getBusinessDate()));
            ps.setString(2, priceData.getBusinessDate());
            ps.setDouble(3, priceData.getOpenPrice());
            ps.setDouble(4, priceData.getClosePrice());
            ps.setDouble(5, priceData.getHighPrice());
            ps.setDouble(6, priceData.getLowPrice());
            ps.setDouble(7, priceData.getAdjustedPrice());
            ps.setDouble(8, priceData.getVolume());
            //  ps.setString(9, ""+priceData.getPrevBusinessdate());

         }
      });

//			System.out.println("******************************");
//			rbsaJdbcTemplate = new JdbcTemplate(dataSource);
//			SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(rbsaJdbcTemplate)
//					.withProcedureName("price_processor");
//					Map<String, Object> inParamMap = new HashMap<String, Object>();
//					inParamMap.put("process", "DAILY");
//					SqlParameterSource in = new MapSqlParameterSource(inParamMap);
//					Map<String, Object> simpleJdbcCallResult = simpleJdbcCall.execute(in);
//					System.out.println(simpleJdbcCallResult);
//					System.out.println("******************************");
   }

   public List<PriceData> findByTicker(String ticker)
   {
      // TODO Auto-generated method stub
      return null;
   }

   public List<PriceData> findByDate(String date)
   {
      // TODO Auto-generated method stub
      return null;
   }

   public PriceData findByTickerNDate(String ticker, String date)
   {
      // TODO Auto-generated method stub
      return null;
   }

   public void callHolidayProcedure(String startDate, String endDate) throws SQLException
   {
      System.out.println("Date Range for Holiday calculation" + startDate + " endDate " + endDate);
      //rbsaJdbcTemplate = new JdbcTemplate(dataSource);
      SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(rbsaJdbcTemplate)
         .withProcedureName("get_hodidays_price_data");
      Map<String, Object> inParamMap = new HashMap<String, Object>();
      inParamMap.put("p_startdt", startDate);
      inParamMap.put("p_enddt", endDate);
      SqlParameterSource in = new MapSqlParameterSource(inParamMap);
      Map<String, Object> simpleJdbcCallResult = simpleJdbcCall.execute(in);
      System.out.println(simpleJdbcCallResult);
      System.out.println("******************************");

   }


   public void GetExchangePriceData(String ticker) throws SQLException
   {
      System.out.println("GetExchangePriceData ticker"+ticker);
      //rbsaJdbcTemplate = new JdbcTemplate(dataSource);
      SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(rbsaJdbcTemplate)
         .withProcedureName("exchange_rate_processing");
      Map<String, Object> inParamMap = new HashMap<String, Object>();
      inParamMap.put("p_ticker", ticker);
      SqlParameterSource in = new MapSqlParameterSource(inParamMap);
      Map<String, Object> simpleJdbcCallResult = simpleJdbcCall.execute(in);
//      simpleJdbcCallResult.get("")
      System.out.println(simpleJdbcCallResult);
      System.out.println("******************************");

   }

   public void GetDailyMissingData(String startDate, String ticker) throws SQLException
   {
      System.out.println("GetDailyMissingData startDate "+startDate+" ticker "+ticker);
      //rbsaJdbcTemplate = new JdbcTemplate(dataSource);
      SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(rbsaJdbcTemplate)
         .withProcedureName("get_daily_missing_price_data");
      Map<String, Object> inParamMap = new HashMap<String, Object>();
      inParamMap.put("p_startdt", startDate);
      inParamMap.put("p_ticker", ticker);
      SqlParameterSource in = new MapSqlParameterSource(inParamMap);
      Map<String, Object> simpleJdbcCallResult = simpleJdbcCall.execute(in);
      System.out.println(simpleJdbcCallResult);
      System.out.println("******************************");

   }


}
