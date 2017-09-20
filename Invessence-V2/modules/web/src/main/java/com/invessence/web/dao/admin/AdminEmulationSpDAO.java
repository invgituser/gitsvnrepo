package com.invessence.web.dao.admin;

import java.io.Serializable;
import java.util.*;
import javax.faces.bean.ManagedBean;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import com.invessence.converter.SQLData;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;

/**
 * Created by sagar on 6/15/2017.
 */

@Repository("adminEmulationSpDAO")
public class AdminEmulationSpDAO
{

   @Autowired
   private JdbcTemplate invJdbcTemplate;
   @Autowired
   DataSource dataSource;

   private JdbcTemplate jdbcTemplate;

   public String processAccountRequest(Long acctnum, Double amount, String procedure, int mode)
   {
      SQLData convert = new SQLData();
      String response = null;
      jdbcTemplate = new JdbcTemplate(dataSource);
      AdminEmulationSp extInfoSP = new AdminEmulationSp(jdbcTemplate, procedure, mode);
      Map outMap = null;
      try
      {
         if (mode == 0)
         {
            outMap = extInfoSP.execute(acctnum);
         }
         else
         {
            outMap = extInfoSP.execute(acctnum, amount);
         }
         System.out.println("dbResponse = [" + outMap.toString() + "]");
         if (outMap != null)
         {
            ArrayList<Map<String, Object>> rows = (ArrayList<Map<String, Object>>) outMap.get("#result-set-1");
            if (rows == null)
            {
               response = null;
            }
            int i = 0;
            outerloop:
            for (Map<String, Object> map : rows)
            {
               Map rs = (Map) rows.get(i);

               for (String key : map.keySet())
               {
                  System.out.println("The key is: " + key + ",value is :" + map.get(key));
                  response = map.get(key).toString();
                  break outerloop;
               }
            }
         }
      }
      catch (Exception e)
      {

         System.out.println("processDCRequest Exception " + e);
         response ="Error occurred while execution";
      }
      System.out.println("processDCRequest Exception " + response);
      return response;
   }

   public void updateBusinessDate(String businessdate) throws SQLException
   {

      String sql = "update invdb.invessence_switch set value='" + businessdate + "' where name='BUSINESS_DATE' ;";
      invJdbcTemplate.execute(sql);
   }


}
