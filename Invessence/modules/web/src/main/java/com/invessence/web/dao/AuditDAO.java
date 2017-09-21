package com.invessence.web.dao;

import java.sql.SQLException;
import javax.sql.DataSource;

import com.invessence.web.service.crm.*;
import com.invessence.ws.util.WSConstants;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * Created by abhangp on 9/8/2017.
 */
@Repository("auditDAO")
public class AuditDAO
{
   private static final Logger logger = Logger.getLogger(AuditDAO.class);

   @Autowired
   DataSource dataSource;

   private JdbcTemplate jdbcTemplate ;

   public Long loginAuditEntry(LoginAudit loginAudit)
   {
      Long loginAuditId=null;
      try
      {
         System.out.println("AuditDAO.loginAuditEntry");
         System.out.println("loginAudit = [" + loginAudit + "]");

         jdbcTemplate = new JdbcTemplate(dataSource);
         AuditSP auditSP = new AuditSP(jdbcTemplate, "sp_login_audit", 0);
         loginAuditId = auditSP.loginAuditEntry(loginAudit);
         System.out.println("loginAuditId = " + loginAuditId);

      }catch(Exception e){
         e.printStackTrace();
      }
      return loginAuditId;
   }

}
