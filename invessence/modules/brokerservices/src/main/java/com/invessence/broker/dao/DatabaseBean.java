package com.invessence.broker.dao;

import java.text.*;
import java.util.Map;
import javax.sql.DataSource;

import com.google.common.collect.Maps;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

import static com.invessence.broker.constants.BrokerServiceConstants.YEAR_MONTH_FORMAT;
import static com.invessence.broker.constants.BrokerServiceConstants.YEAR_MONTH_DAY_FORMAT;

public class
   DatabaseBean
{
   private String brokerDate;
   private DataSource dataSource;
   private Logger logger = Logger.getLogger(DatabaseBean.class.getName());
   private String brokerDateToUpdate;
   private String previousMonthDate;
   private String firstDayOfMonth;
   private Boolean updateFirstDayOfMonth = false;
   private boolean updateSummaryDate = false;

   public void setDataSource(DataSource dataSource)
   {
      this.dataSource = dataSource;
   }

   public void initialize() throws Exception
   {
      JdbcTemplate jdbc = new JdbcTemplate(dataSource);
      Map<String, String> map = Maps.newHashMap();

      map.put("vKey", "BROKER_BDATE");
      brokerDate = new SimpleJdbcCall(jdbc).withFunctionName("funct_get_switch").executeFunction(String.class, map);
      map.clear();
      ;

      map.put("vKey", "PREVIOUS_MONTH");
      previousMonthDate = new SimpleJdbcCall(jdbc).withFunctionName("funct_get_switch").executeFunction(String.class, map);
      map.clear();
      ;

      map.put("vKey", "FIRST_DAY_OF_MONTH");
      firstDayOfMonth = new SimpleJdbcCall(jdbc).withFunctionName("funct_get_switch").executeFunction(String.class, map);
   }

   public String getBrokerBusinessDate()
   {
      return brokerDate;
   }

   public String getPreviousMonthDate()
   {
      return previousMonthDate;
   }

   public String getFirstDayOfMonth()
   {
      return firstDayOfMonth;
   }

   public void updateDate()
   {
      invokeStoredProcToUploadPositionsFromTmpTable();
      invokeStoredProcToUploadAccountsFromTmpTable();
      invokeStoredProcToUploadCashReportsFromTmpTable();
      invokeStoredProcToUploadCashTransactionFromTmpTable();
      invokeStoredProcToUploadNavDailyFromTmpTable();
      invokeStoredProcToUploadCommissionFromTmpTable();
      invokeStoredProcToUploadTradeFromTmpTable();
      invokeSPToUploadUnbndledCommissionFromTmpTable();

      updateBrokerDate();
   }

   private String getNextSummaryDateMonth() throws ParseException
   {
      return new SimpleDateFormat(YEAR_MONTH_FORMAT).format(DateUtils.addMonths(new SimpleDateFormat(YEAR_MONTH_FORMAT).parse(previousMonthDate), 1));
   }

   private String getNewMonthDate() throws ParseException
   {
      return new SimpleDateFormat(YEAR_MONTH_DAY_FORMAT).format(DateUtils.addMonths(new SimpleDateFormat(YEAR_MONTH_DAY_FORMAT).parse(firstDayOfMonth), 1));
   }

   private void postMsgtoDB(String subject, String msg, Boolean stopAndExit)
   {
      String mySubject;
      if (msg != null)
      {
         try
         {
            if (stopAndExit) {
               mySubject = "ERROR (EOD IB file upload)" + subject;
            }
            else {
               mySubject = "Warning (EOD IB file upload)" + subject;
            }

            MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource()
               .addValue("p_addmodflag", "A")
               .addValue("p_source", "Internal")
               .addValue("p_messageid", null)
               .addValue("p_sender", "no-replay@invessence.com")
               .addValue("p_receiver", "supportl1@invessence.com")
               .addValue("p_cc", null)
               .addValue("p_bcc", null)
               .addValue("p_subject", mySubject)
               .addValue("p_status", 0)
               .addValue("p_category", null)
               .addValue("p_priority", null)
               .addValue("p_logonid", null)
               .addValue("p_sentdate", null)
               .addValue("p_msg", msg)
               .addValue("p_comment", "");
            new SimpleJdbcCall(dataSource).withProcedureName("sp_email_messages_add_mod").execute(sqlParameterSource);
            logger.info("Sending Mail Message -> Subject: " + subject);
            if (stopAndExit)
               System.exit(-1);
         }
         catch (Exception e)
         {
            logger.error("Sending eMail message", e);
         }
      }
   }

   private void updateBrokerDate()
   {
      if (brokerDateToUpdate != null)
      {
         try
         {
            MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource()
               .addValue("p_name", "BROKER_BDATE")
               .addValue("p_value", brokerDateToUpdate);
            new SimpleJdbcCall(dataSource).withProcedureName("sp_invessence_switch_eod_process").execute(sqlParameterSource);
            logger.info("Updated broker date to " + brokerDateToUpdate);
         }
         catch (Exception e)
         {
            logger.error("Unable to update IB EOD Broker Date", e);
            postMsgtoDB("Broker Date", "Unable to update IB EOD Broker Date", true);
         }
      }
   }

   private void invokeStoredProcToUploadAccountsFromTmpTable()
   {
      try
      {
         new SimpleJdbcCall(new JdbcTemplate(dataSource)).withProcedureName("sp_upload_IB_Accounts").execute(Maps.newHashMap());
      }
      catch (Exception e)
      {
         logger.error("Unable to update Accounts file", e);
         postMsgtoDB("Accounts File", "Unable to update Accounts file", false);
      }
   }

   private void invokeStoredProcToUploadPositionsFromTmpTable()
   {
      try
      {
         new SimpleJdbcCall(new JdbcTemplate(dataSource)).withProcedureName("sp_upload_position").execute(Maps.newHashMap());
      }
      catch (Exception e)
      {
         logger.error("Error invoking stored procedure to upload positions", e);
         postMsgtoDB("Position File", "Unable to update Position file", false);
      }
   }

   private void invokeStoredProcToUploadCashReportsFromTmpTable()
   {
      try
      {
         new SimpleJdbcCall(new JdbcTemplate(dataSource)).withProcedureName("sp_upload_Cash_Info").execute(Maps.newHashMap());
      }
      catch (Exception e)
      {
         logger.error("Error invoking stored procedure to upload accounts", e);
         postMsgtoDB("Cash Info", "Unable to update Cash Info table", false);
      }
   }

   private void invokeStoredProcToUploadCashTransactionFromTmpTable()
   {
      try
      {
         new SimpleJdbcCall(new JdbcTemplate(dataSource)).withProcedureName("sp_upload_cash_transaction").execute(Maps.newHashMap());
      }
      catch (Exception e)
      {
         logger.error("Error invoking stored procedure to upload cash transaction", e);
         postMsgtoDB("Cash Transaction", "Unable to update Cash Transaction table", false);
      }
   }

   private void invokeStoredProcToUploadNavDailyFromTmpTable()
   {
      try
      {
         new SimpleJdbcCall(new JdbcTemplate(dataSource)).withProcedureName("sp_upload_nav_daily").execute(Maps.newHashMap());
      }
      catch (Exception e)
      {
         logger.error("Error invoking stored procedure to upload NAV Daily", e);
         postMsgtoDB("NAV Daily", "Unable to update NAV Daily table", false);
      }
   }

   private void invokeStoredProcToUploadCommissionFromTmpTable()
   {
      try
      {
         new SimpleJdbcCall(new JdbcTemplate(dataSource)).withProcedureName("sp_upload_commission").execute(Maps.newHashMap());
      }
      catch (Exception e)
      {
         logger.error("Error invoking stored procedure to upload Commission", e);
         postMsgtoDB("Commission", "Unable to update Commission table", false);
      }
   }

   private void invokeStoredProcToUploadTradeFromTmpTable()
   {
      try
      {
         new SimpleJdbcCall(new JdbcTemplate(dataSource)).withProcedureName("sp_upload_trades").execute(Maps.newHashMap());
      }
      catch (Exception e)
      {
         logger.error("Error invoking stored procedure to upload trades", e);
         postMsgtoDB("Trade", "Unable to update Trade table", false);
      }
   }

   private void invokeSPToUploadUnbndledCommissionFromTmpTable()
   {
      try
      {
         new SimpleJdbcCall(new JdbcTemplate(dataSource)).withProcedureName("sp_upload_unbundld_commission").execute(Maps.newHashMap());
      }
      catch (Exception e)
      {
         logger.error("Error invoking stored procedure to upload trade", e);
         postMsgtoDB("Unbundld Commission", "Unable to update unbundld commission table", false);
      }
   }

   public synchronized void setBrokerDate(String s)
   {
      brokerDateToUpdate = s;
   }

   public void updateSummaryDate(boolean b)
   {
      updateSummaryDate = b;
   }

   public void updateFirstDayofMonth (boolean b)
   {
      updateFirstDayOfMonth = b;
   }

}
