package com.invessence.fileProcessor.dao;

import java.sql.*;
import java.util.*;

import com.invessence.converter.SQLData;
import com.invessence.fileProcessor.bean.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.core.simple.*;
import org.springframework.stereotype.Repository;

/**
 * Created by abhangp on 1/19/2016.
 */
@Repository("fileProcessorDao")
public class FileProcessorDaoImpl implements FileProcessorDao
{
   private static final Logger logger = Logger.getLogger(FileProcessorDaoImpl.class);
   @Autowired
   JdbcTemplate invJdbcTemplate;

   SQLData convert = new SQLData();

   @Override
   public Object dbCall(String product, String service, String type, String procedureName) throws SQLException
   {
      logger.info("FileProcessorDaoImpl.dbCall");
      logger.info("product = [" + product + "], service = [" + service + "], type = [" + type + "], procedureName = [" + procedureName + "]");
      try
      {
         FileProcessorSP serviceSP = new FileProcessorSP(invJdbcTemplate, procedureName, 100);
         try
         {
            Map outMap = serviceSP.nonParamProcCall();
            if (outMap != null)
            {
               ArrayList<LinkedHashMap<String, Object>> rows = (ArrayList<LinkedHashMap<String, Object>>) outMap.get("#result-set-1");
               System.out.println("rows = " + rows);
               return rows;
            }
         }
         catch (Exception ex)
         {
            ex.printStackTrace();
         }
      }
      catch (Exception e)
      {
         logger.error("Issue while storing web request in DB :" + e.getMessage());
      }

      return null;
   }

   public Object dbCallAudit(FileProcessAudit fileProcessAudit) throws SQLException
   {
      try
      {
         FileProcessorSP serviceSP = new FileProcessorSP(invJdbcTemplate, "file_process_auditrial", 0);
         try
         {
            Map outMap = serviceSP.callFileProcessorAuditSP(fileProcessAudit);
            if (outMap != null)
            {
               ArrayList<LinkedHashMap<String, Object>> rows = (ArrayList<LinkedHashMap<String, Object>>) outMap.get("#result-set-1");
               System.out.println("rows = " + rows);
               return rows;
            }
         }
         catch (Exception ex)
         {
            ex.printStackTrace();
         }
      }
      catch (Exception e)
      {
         logger.error("Issue while storing web request in DB :" + e.getMessage());
      }

      return null;
   }

   public Map<String, DBParameters> getDBParametres() throws SQLException
   {
      List<DBParameters> dbParamsLst = null;
      Map<String, DBParameters> dbParamsMap = null;
      // try {
      logger.info("Fetching DB parameters");
      String sql = "SELECT name, value, format, description FROM invdb.vw_invessence_switch";
      dbParamsLst = invJdbcTemplate.query(sql, ParameterizedBeanPropertyRowMapper.newInstance(DBParameters.class));
      if (dbParamsLst.size() > 0)
      {
         dbParamsMap = new HashMap<String, DBParameters>();
         Iterator<DBParameters> itr = dbParamsLst.iterator();
         while (itr.hasNext())
         {
            DBParameters dbParameters = (DBParameters) itr.next();
            dbParamsMap.put(dbParameters.getName(), dbParameters);
            System.out.println("dbParameters = " + dbParameters);

         }

      }

      return dbParamsMap;
   }

   @Override
   public boolean insertBatch(final List<String[]> dataArrLst, String sql, String proc) throws SQLException
   {

      boolean bflag = false;

      logger.info("Processing batch insertion");
      if (sql == null || sql.equals(""))
      {
         System.out.println("Insertion sql is not valid");
         logger.info("Insertion sql is not valid");
      }
      else
      {
         invJdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter()
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
                  ps.setString(ip, inData[ip - 1].trim().replaceAll("\"", ""));
               }
            }
         });
      }
      bflag = true;

      return bflag;
   }

   @Override
   public boolean insertBatch(final List<List<String>> dataArrLst, String sql, String proc, String str) throws SQLException
   {
      boolean bflag = false;

      logger.info("Processing batch insertion");
      if (sql == null || sql.equals(""))
      {
         System.out.println("Insertion sql is not valid");
         logger.info("Insertion sql is not valid");
      }
      else
      {
         invJdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter()
         {
            public int getBatchSize()
            {
               return dataArrLst.size();
            }

            public void setValues(PreparedStatement ps, int i) throws SQLException
            {
               //String[] inData = (String[]) dataArrLst.get(i).toArray();
//               String[] inData = new String[dataArrLst.get(i).size()];
//               inData = dataArrLst.toArray(inData);
               String [] inData = dataArrLst.get(i).toArray(new String[dataArrLst.get(i).size()]);
               for (int ip = 1; ip <= inData.length; ip++)
               {
                  ps.setString(ip, inData[ip - 1].trim().replaceAll("\"", ""));
               }
            }
         });
      }
      bflag = true;

      return bflag;
   }

   public void truncateTable(String tableName) throws SQLException
   {
      String sql = "delete from " + tableName;
      invJdbcTemplate.execute(sql);
   }

   public void callProcedure(String proc) throws SQLException
   {
//      new SimpleJdbcCall(invJdbcTemplate).withProcedureName(proc).execute();
      FileProcessorSP serviceSP = new FileProcessorSP(invJdbcTemplate, proc, 100);

      Map outMap = serviceSP.nonParamProcCall();
      if (outMap != null)
      {
         ArrayList<LinkedHashMap<String, Object>> rows = (ArrayList<LinkedHashMap<String, Object>>) outMap.get("#result-set-1");
         System.out.println("rows = " + rows);
      }
   }

}