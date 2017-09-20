package com.invessence.price.processor.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.invessence.price.processor.bean.DBParameters;

@Repository
public class DBParametersDAOImpl implements DBParametersDao
{
   @Autowired
   private JdbcTemplate invJdbcTemplate;

   public Map<String, DBParameters> getDBParametres() throws SQLException
   {
      List<DBParameters> dbParamsLst = null;
      Map<String, DBParameters> dbParamsMap = null;
      try
      {
         System.out.println("SecMasterDAOImpl.findByWhere()");
         String sql = "SELECT name, value, format, description FROM invessence_switch where name in('BUSINESS_DATE','LAST_BDATE_OF_MONTH','PRICE_DATE')";
         System.out.println(sql);
         dbParamsLst = invJdbcTemplate.query(sql, ParameterizedBeanPropertyRowMapper.newInstance(DBParameters.class));
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
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
      return null;

   }

   public Map<String, DBParameters> getPrevBusinessDate(String businessDate) throws SQLException
   {
      List<DBParameters> dbParamsLst = null;
      Map<String, DBParameters> dbParamsMap = null;
      try
      {
         System.out.println("DBParametersDAOImpl.getPrevBusinessDate() Business Date "+businessDate);
         String sql = "select 'PREV_BUSINESSDATE' as name,idt.prev_businessdate as value  from invdb.inv_date_table idt where idt.businessdate='"+businessDate+"'";
         System.out.println(sql);
         dbParamsLst = invJdbcTemplate.query(sql, ParameterizedBeanPropertyRowMapper.newInstance(DBParameters.class));
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
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
      return null;

   }
}
