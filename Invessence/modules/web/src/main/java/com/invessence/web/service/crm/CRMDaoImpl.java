package com.invessence.web.service.crm;

import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import com.invessence.ws.util.WSConstants;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

/**
 * Created by abhangp on 1/19/2016.
 */
@Repository("crmDao")
public class CRMDaoImpl implements CRMDao
{
   private static final Logger logger = Logger.getLogger(CRMDao.class);

   @Autowired
   DataSource dataSource;

   private JdbcTemplate jdbcTemplate ;

   private final String getUserAccDetailsByAccNumber="select * from vwcrm_user_details where logonid=?";

   public CRMUserDetails getUserAccDetailsByLogonId(Long logonId)throws SQLException{
      logger.info("CRMDaoImpl.getUserAccDetailsByLogonId");
      List<CRMUserDetails> lst = null;
      logger.debug("getUserAccDetailsByLogonId = "+getUserAccDetailsByAccNumber);
      jdbcTemplate = new JdbcTemplate(dataSource);
      lst = jdbcTemplate.query(getUserAccDetailsByAccNumber, new Object[]{logonId}, ParameterizedBeanPropertyRowMapper.newInstance(CRMUserDetails.class));
      return lst==null?null:lst.size()==0?null:lst.get(0);
   }

   public boolean insertUserAcctDetails(CRMUserDetails crmUserDetails)throws SQLException
   {
      logger.info("CRMDaoImpl.insertUserAcctDetails");
      logger.debug("wsRequest = " + crmUserDetails);
      jdbcTemplate = new JdbcTemplate(dataSource);
      SPCRMUserMgmt extInfoSP= new SPCRMUserMgmt(jdbcTemplate);
      crmUserDetails.setOpt(WSConstants.dbInsertOpt);
      logger.debug("wsRequest.setOpt = "+ crmUserDetails.getOpt());
      DBResponse dbResponse= (DBResponse) extInfoSP.execute(crmUserDetails);
      logger.debug("dbResponse = [" + dbResponse.toString() + "]");
      return true;
   }

}
