package com.invessence.web.dao;

import java.io.Serializable;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * Created by abhangp on 9/8/2017.
 */
@Repository("auditDAO")
public class AuditDAO implements Serializable
{
    private static final long serialVersionUID = -1882L;
    private static final transient Logger logger = Logger.getLogger(AuditDAO.class);

    @Autowired
    DataSource dataSource;

    private JdbcTemplate jdbcTemplate ;

    public Long loginAuditEntry(LoginAudit loginAudit)
    {
        Long loginAuditId=null;
        try
        {
            logger.info("AuditDAO.loginAuditEntry");
            logger.info("loginAudit = [" + loginAudit + "]");

            jdbcTemplate = new JdbcTemplate(dataSource);
            AuditSP auditSP = new AuditSP(jdbcTemplate, "sp_login_audit", 0);
            loginAuditId = auditSP.loginAuditEntry(loginAudit);

        }catch(Exception e){
            e.printStackTrace();
        }
        return loginAuditId;
    }

}
