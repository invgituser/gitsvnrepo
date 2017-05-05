package com.invessence.broker.dao;

import java.io.IOException;
import java.sql.*;
import java.util.*;
import java.util.Date;

import javax.sql.DataSource;

import com.invessence.broker.bean.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.core.namedparam.*;
import org.springframework.jdbc.core.simple.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by abhangp on 1/19/2016.
 */
@Service
public class CommonDaoImpl implements CommonDao
{
   private static final Logger logger = Logger.getLogger(CommonDaoImpl.class);
   @Autowired
   JdbcTemplate jdbcTemplate;

/* Code removed: Prashant 30-Oct-2016
   public List<BrokerHostDetails> getBrokerHostDetails(String where)throws SQLException
   {
      List<BrokerHostDetails> lst = null;
      logger.info("Fetching broker host details");
      String sql = "select vendor, environment, host, username, password, sourcedir, encrDecrKey from host_info "+where;
      lst = jdbcTemplate.query(sql, ParameterizedBeanPropertyRowMapper.newInstance(BrokerHostDetails.class));
      return lst;
   }
*/

   public List<DownloadFileDetails> getDownloadFileDetails(String where) throws SQLException
   {
      List<DownloadFileDetails> lst = null;
      logger.info("Fetching download files");
      //String sql = "SELECT vendor, filename, active, tmp_tableName, available, sourcepath, downloaddir, format, required, canbeempty, postProcess, postInstruction,containsheader,keyData FROM download_files "+where;
      String sql = "Select vendor, seqno, filename, active, tmp_tableName, available, sourcepath, downloaddir, loadFormat, required, " +
         "canbeempty,canbedups,postProcess, postInstruction, containsheader, ifnull(keyData,0) keyData, encColumns, fileExtension, encryptionMethod " +
         "FROM download_files " + where + " order by vendor, seqno";
      lst = jdbcTemplate.query(sql, ParameterizedBeanPropertyRowMapper.newInstance(DownloadFileDetails.class));
      return lst;
   }

   public Map<String, DBParameters> getDBParametres() throws SQLException
   {
      List<DBParameters> dbParamsLst = null;
      Map<String, DBParameters> dbParamsMap = null;
      // try {
      logger.info("Fetching DB parameters");
      String sql = "SELECT name, value, format, description FROM vw_invessence_switch /*where name in('LAST_BDATE_OF_MONTH','PRICE_DATE')*/";
      dbParamsLst = jdbcTemplate.query(sql, ParameterizedBeanPropertyRowMapper.newInstance(DBParameters.class));
      if (dbParamsLst.size() > 0)
      {
         dbParamsMap = new HashMap<String, DBParameters>();
         Iterator<DBParameters> itr = dbParamsLst.iterator();
         while (itr.hasNext())
         {
            DBParameters dbParameters = (DBParameters) itr.next();
            dbParamsMap.put(dbParameters.getName(), dbParameters);
         }

      }

      return dbParamsMap;
//      } catch (Exception e) {
//         e.printStackTrace();
//      }
   }

   public void truncateTable(String tableName) throws SQLException
   {
      String sql = "delete from " + tableName;
      jdbcTemplate.execute(sql);
   }


   @Transactional
   public void insertBatch(final List<String[]> dataArrLst, String sql, String proc) throws SQLException
   {
      logger.info("Processing batch insertion");
      if (sql == null || sql.equals(""))
      {
         System.out.println("Insertion sql is not valid");
         logger.info("Insertion sql is not valid");
      }
      else
      {
         jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter()
         {
            public int getBatchSize()
            {
               return dataArrLst.size();
            }

            public void setValues(PreparedStatement ps, int i) throws SQLException
            {
               String[] inData = dataArrLst.get(i);
               for (int ip = 1; ip <= inData.length; ip++)
               {
                  //System.out.print((inData[ip - 1].trim().replaceAll("\"", "")) + ",");
                  ps.setString(ip, inData[ip - 1].trim().replaceAll("\"", ""));
               }
               //System.out.println("");
            }
         });
      }
//      System.out.println("******************************");
      if (proc == null || proc.equals(""))
      {
         logger.info("Procedure name is not valid");
      }
      else
      {
         logger.info("Calling post process procedure :" + proc);
         new SimpleJdbcCall(jdbcTemplate).withProcedureName(proc).execute();
      }

//      SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName(proc);
//      simpleJdbcCall.execute();
//      Map<String, Object> inParamMap = new HashMap<String, Object>();
//      inParamMap.put("process", "MONTHLY");
//      SqlParameterSource in = new MapSqlParameterSource(inParamMap);
//      Map<String, Object> simpleJdbcCallResult = simpleJdbcCall.execute(in);
//      System.out.println(simpleJdbcCallResult);
//      System.out.println("******************************");


   }

   public void callEODProcess(String proc) throws SQLException
   {
      new SimpleJdbcCall(jdbcTemplate).withProcedureName(proc).execute();
//      SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
//         .withProcedureName(proc);
//
//      Map<String, Object> inParamMap = new HashMap<String, Object>();
//      inParamMap.put("firstName", "Smita");
//      inParamMap.put("lastName", "Chaudhari");
//      SqlParameterSource in = new MapSqlParameterSource(inParamMap);
//
//
//      Map<String, Object> simpleJdbcCallResult = simpleJdbcCall.execute(in);
//      System.out.println(simpleJdbcCallResult);
   }

}
