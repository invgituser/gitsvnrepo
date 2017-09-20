package com.invessence.rbsa.dao;

import java.util.Map;
import javax.sql.DataSource;

import com.google.common.collect.Maps;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.object.StoredProcedure;

public class
   DatabaseBean
{
   private DataSource dataSource;
   private String lastDateFundsOptimized;
   private String updateDateSP, saveFundInfoSP, saveFundDataSP;
   private Logger logger = Logger.getLogger(DatabaseBean.class.getName());

   public void setDataSource(DataSource dataSource)
   {
      this.dataSource = dataSource;
   }

   public String getUpdateDateSP()
   {
      return updateDateSP;
   }

   public void setUpdateDateSP(String updateDateSP)
   {
      this.updateDateSP = updateDateSP;
   }

   public String getSaveFundInfoSP()
   {
      return saveFundInfoSP;
   }

   public void setSaveFundInfoSP(String saveFundInfoSP)
   {
      this.saveFundInfoSP = saveFundInfoSP;
   }

   public String getSaveFundDataSP()
   {
      return saveFundDataSP;
   }

   public void setSaveFundDataSP(String saveFundDataSP)
   {
      this.saveFundDataSP = saveFundDataSP;
   }

   public void initialize() throws Exception
   {
      JdbcTemplate jdbc = new JdbcTemplate(dataSource);
      Map<String, String> map = Maps.newHashMap();

      map.put("vKey", "FUNDS_OPTIMIZED");
      lastDateFundsOptimized = new SimpleJdbcCall(jdbc).withFunctionName("funct_get_switch").executeFunction(String.class, map);
      map.clear();
      ;

   }

   public String getLastDateFundsOptimized()
   {
      return lastDateFundsOptimized;
   }

   public void cleanFundsWeightTable() throws Exception
   {
      JdbcTemplate jdbc = new JdbcTemplate(dataSource);

      String sql = "delete from fund_weights";
      jdbc.execute(sql);
   }

   public void updateLastFundDate()
   {
      new updateLastFundDateSP(dataSource, getUpdateDateSP()).execute();
   }

   public void saveFundData()
   {
      new updateLastFundDateSP(dataSource, getSaveFundDataSP()).execute();
   }

   public void saveFundInfo()
   {
      new updateLastFundDateSP(dataSource, getSaveFundInfoSP()).execute();
   }

   public void loadData()
   {
      invokeStoredProcToUploadData();
      //updateBrokerDate();
   }

   private void postMsgtoDB(String subject, String msg, Boolean stopAndExit)
   {
      String mySubject;
      if (msg != null)
      {
         try
         {
            if (stopAndExit) {
               mySubject = "ERROR (RBSA file upload)" + subject;
            }
            else {
               mySubject = "Warning (EBSA file upload)" + subject;
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

   private void invokeStoredProcToUploadData()
   {
      try
      {
         new SimpleJdbcCall(new JdbcTemplate(dataSource)).withProcedureName("sp_upload_rbsa_security").execute(Maps.newHashMap());
      }
      catch (Exception e)
      {
         // logger.error("Unable to update Accounts file", e);
         // postMsgtoDB("Data File", "Unable to update Data file", false);
      }
   }

   private class updateLastFundDateSP extends StoredProcedure
   {

      private updateLastFundDateSP(DataSource ds, String spName)
      {
         super(ds, spName);
      }
   }

}
