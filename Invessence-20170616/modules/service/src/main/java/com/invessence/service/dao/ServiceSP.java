package com.invessence.service.dao;

import java.sql.Types;
import java.util.*;

import org.springframework.jdbc.core.*;
import org.springframework.jdbc.object.StoredProcedure;

/**
 * Created by abhangp on 2/22/2017.
 */
public class ServiceSP extends StoredProcedure
{
   public ServiceSP(JdbcTemplate datasource, String SPROC_NAME, Integer mode)
   {
      super(datasource, SPROC_NAME);
      switch (mode)
      {
         case 0:  // del_asset_alloc

            declareParameter(new SqlParameter("p_product", Types.VARCHAR));
            declareParameter(new SqlParameter("p_service", Types.VARCHAR));
            declareParameter(new SqlParameter("p_type", Types.VARCHAR));
            declareParameter(new SqlParameter("p_info", Types.VARCHAR));
            break;
         default:  // All other (no arg)
            break;
      }
   }
   public Map getCommonDetails(String product,String service, String type, String infoType) {
      System.out.println("service = [" + service + "], type = [" + type + "]");
      Map inputMap = new HashMap();

      inputMap.put("p_product", product);
      inputMap.put("p_service", service);
      inputMap.put("p_type", type);
      inputMap.put("p_info", infoType);
      return super.execute(inputMap);

   }
}
