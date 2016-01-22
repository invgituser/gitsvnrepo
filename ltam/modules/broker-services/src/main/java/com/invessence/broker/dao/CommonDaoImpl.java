package com.invessence.broker.dao;

import java.io.IOException;
import java.sql.*;
import java.util.*;
import java.util.Date;

import javax.sql.DataSource;

import com.invessence.broker.bean.*;
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
   @Autowired
   JdbcTemplate jdbcTemplate;

   public List<BrokerHostDetails> getBrokerHostDetails(String where)throws SQLException
   {
      List<BrokerHostDetails> lst = null;
         System.out.println("CommonDaoImpl.getBrokerHostDetails()");
         String sql = "select vendor, environment, host, username, password, sourcedir from host_info "+where;
         lst = jdbcTemplate.query(sql, ParameterizedBeanPropertyRowMapper.newInstance(BrokerHostDetails.class));
         System.out.println("lst size :" + lst.size());
         return lst;
   }

   public List<DownloadFileDetails> getDownloadFileDetails(String where)throws SQLException
   {
      List<DownloadFileDetails> lst = null;
      System.out.println("CommonDaoImpl.getDownloadFileDetails()");
      String sql = "SELECT vendor, filename, active, tmp_tableName, available, sourcepath, downloaddir, format, required, canbeempty, postProcess, postInstruction,containsheader,keyData FROM download_files "+where;
      lst = jdbcTemplate.query(sql, ParameterizedBeanPropertyRowMapper.newInstance(DownloadFileDetails.class));
      System.out.println("lst size :" + lst.size());
      return lst;

   }

   public Map<String, DBParameters> getDBParametres() throws SQLException{
      List<DBParameters> dbParamsLst = null;
      Map<String, DBParameters> dbParamsMap = null;
     // try {
         System.out.println("CommonDaoImpl.getDBParametres()");
         String sql = "SELECT name, value, format, description FROM invessence_switch /*where name in('LAST_BDATE_OF_MONTH','PRICE_DATE')*/";
         dbParamsLst = jdbcTemplate.query(sql, ParameterizedBeanPropertyRowMapper.newInstance(DBParameters.class));
         if(dbParamsLst.size()>0){
            dbParamsMap=new HashMap<String, DBParameters>();
            Iterator<DBParameters> itr=dbParamsLst.iterator();
            while (itr.hasNext()) {
               DBParameters dbParameters = (DBParameters) itr.next();
               dbParamsMap.put(dbParameters.getName(), dbParameters);
            }

         }

         return dbParamsMap;
//      } catch (Exception e) {
//         e.printStackTrace();
//      }
   }
   public void trancateTable(String tableName) throws SQLException{
      String sql = "delete from "+tableName;
      jdbcTemplate.execute(sql);
   }


   @Transactional
   public void insertBatch(final List<String[]> dataArrLst, String sql, String proc)throws SQLException{

      System.out.println("sql :"+sql+"   proc :"+proc);
      if(sql==null || sql.equals("")){
         System.out.println("Insertion sql is empty");
      }else
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
                  System.out.print((inData[ip - 1].trim().replaceAll("\"", "")) + ",");
                  ps.setString(ip, inData[ip - 1].trim().replaceAll("\"", ""));
               }
               System.out.println("");
            }
         });
      }
//      System.out.println("******************************");
   new SimpleJdbcCall(jdbcTemplate).withProcedureName(proc).execute();
//      SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName(proc);
//      simpleJdbcCall.execute();
//      Map<String, Object> inParamMap = new HashMap<String, Object>();
//      inParamMap.put("process", "MONTHLY");
//      SqlParameterSource in = new MapSqlParameterSource(inParamMap);
//      Map<String, Object> simpleJdbcCallResult = simpleJdbcCall.execute(in);
//      System.out.println(simpleJdbcCallResult);
//      System.out.println("******************************");


   }

}
