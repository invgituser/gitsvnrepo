package com.invessence.web.service.aggr;

import java.sql.SQLException;
import java.util.*;
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
@Repository("aggregationDao")
public class AggregationDaoImpl implements AggregationDao
{
   private static final Logger logger = Logger.getLogger(AggregationDao.class);

   @Autowired
   DataSource dataSource;

   private JdbcTemplate jdbcTemplate ;

   private final String getUserAccDetailsByAccNumber="select * from vwaggr_user_details where logonid=?";

   public UserAcctDetails getUserAccDetailsByLogonId(Long logonId)throws SQLException{
      logger.info("AggregationDaoImpl.getUserAccDetailsByLogonId");
      List<UserAcctDetails> lst = null;
      logger.debug("getUserAccDetailsByLogonId = "+getUserAccDetailsByAccNumber);
      jdbcTemplate = new JdbcTemplate(dataSource);
      lst = jdbcTemplate.query(getUserAccDetailsByAccNumber, new Object[]{logonId}, ParameterizedBeanPropertyRowMapper.newInstance(UserAcctDetails.class));
      return lst==null?null:lst.size()==0?null:lst.get(0);
   }

   public boolean insertUserAcctDetails(UserAcctDetails userAcctDetails)throws SQLException
   {
      logger.info("AggregationDaoImpl.insertUserAcctDetails");
      logger.debug("wsRequest = " + userAcctDetails);
      jdbcTemplate = new JdbcTemplate(dataSource);
      SPAggrUserMgmt extInfoSP= new SPAggrUserMgmt(jdbcTemplate);
      userAcctDetails.setOpt(WSConstants.dbInsertOpt);
      logger.debug("wsRequest.setOpt = "+ userAcctDetails.getOpt());
      DBResponse dbResponse= (DBResponse) extInfoSP.execute(userAcctDetails);
      logger.debug("dbResponse = [" + dbResponse.toString() + "]");
      return true;
   }

}
