package com.invessence.report.billing.dao;

import javax.sql.DataSource;

import com.invessence.report.billing.domain.AccountInfo;
import com.invessence.report.billing.util.EmailMessage;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

public class BillingProcessDBUpdate
{
   private DataSource dataSource;

   @Autowired
   private EmailMessage messageText;

   private Logger logger = Logger.getLogger(BillingProcessDBUpdate.class.getName());

   public DataSource getDataSource()
   {
      return dataSource;
   }

   public void setDataSource(DataSource dataSource)
   {
      this.dataSource = dataSource;
   }


   public void process(AccountInfo acctinfo)
   {

      markCompleted(acctinfo);
      addReportProduced(acctinfo);
      sendEmailtoCustomer(acctinfo);
   }

   private void markCompleted(AccountInfo acctinfo)
   {
      {
         try
         {
            MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource()
               .addValue("p_clientAccountID", acctinfo.getClientAccountId());

            new SimpleJdbcCall(dataSource).withProcedureName("updt_mark_billingStatement").execute(sqlParameterSource);
         }
         catch (Exception e)
         {
            logger.error("Unable to update Billing Statement", e);
         }
      }
   }

   private void addReportProduced(AccountInfo acctinfo)
   {
      {
         try
         {
            MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource()
               .addValue("p_acctnum", acctinfo.getInvessenceAccountId())
               .addValue("p_reportDate", acctinfo.getReportDate())
               .addValue("p_reportName", "Billing")
               .addValue("p_filename", acctinfo.getFilename());

            new SimpleJdbcCall(dataSource).withProcedureName("sp_add_user_reports").execute(sqlParameterSource);
         }
         catch (Exception e)
         {
            logger.error("Unable to add Billing Statement to saved table", e);
         }
      }
   }

   private void sendEmailtoCustomer(AccountInfo acctinfo)
   {
      String mySubject;
      Object[] obj = new Object[]{};
      if (acctinfo != null)
      {
         try
         {
            mySubject = acctinfo.getCurrentMonth() + " Invoice";
            String mimetype= findByUserId(acctinfo.getEmail());
            String msg = "";
            if(mimetype != null && mimetype.equalsIgnoreCase("html")){
               msg = messageText.getHTMLMessagetext("billing.email.template",
                                                 new Object[]{acctinfo.getFirstName(),
                                                               acctinfo.getLastName(),
                                                               acctinfo.getCurrentMonth()});
            }else
            {
               msg = messageText.getMessagetext("billing.email",
                                                new Object[]{acctinfo.getFirstName(),
                                                   acctinfo.getLastName(),
                                                   acctinfo.getCurrentMonth()});
            }
            MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource()
               .addValue("p_addmodflag", "A")
               .addValue("p_source", "User")
               .addValue("p_messageid", null)
               .addValue("p_sender", "no-replay@invessence.com")
               .addValue("p_receiver", acctinfo.getEmail())
               .addValue("p_cc", null)
               .addValue("p_bcc", null)
               .addValue("p_subject", mySubject)
               .addValue("p_status", 0)
               .addValue("p_category", null)
               .addValue("p_priority", null)
               .addValue("p_logonid", null)
               .addValue("p_sentdate", null)
               .addValue("p_msg", msg)
               .addValue("p_comment", "")
               .addValue("p_mimetype",mimetype)
               .addValue("p_attachments",acctinfo.getFilename());
            new SimpleJdbcCall(dataSource).withProcedureName("sp_email_messages_add_mod").execute(sqlParameterSource);
            logger.info("Sending Mail Message ->  " + acctinfo.getEmail());
            logger.info("Subject -> " + mySubject);
         }
         catch (Exception e)
         {
            logger.error("Error Sending eMail message to " + acctinfo.getEmail() +"\nError Messsage:", e);
         }
      }
   }

   private void postMsgtoDB(String subject, String msg)
   {
      String mySubject;
      if (msg != null)
      {
         try
         {
            mySubject = "Billing Statement";

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
               .addValue("p_comment", "")

               .addValue("p_mimetype", null)
               .addValue("p_attachments",null);
            new SimpleJdbcCall(dataSource).withProcedureName("sp_email_messages_add_mod").execute(sqlParameterSource);
            logger.info("Sending Mail Message -> Subject: " + subject);
         }
         catch (Exception e)
         {
            logger.error("Sending eMail message", e);
         }
      }
   }

   public String findByUserId(String userid)
   {

      String emailmsgtype = null;
      String sql = "select emailmsgtype from user_logon where userid = ?";
      try
      {
         emailmsgtype = new JdbcTemplate(dataSource).queryForObject(sql, String.class, userid);
      }catch(Exception ex){
         logger.error("Error getting emailmsgtype from user_logon table" + ex);
      }
      return emailmsgtype;
   }

}
