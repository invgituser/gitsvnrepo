package com.invessence.web.dao;

import java.sql.Types;
import java.util.*;

import org.springframework.jdbc.core.*;
import org.springframework.jdbc.object.StoredProcedure;

/**
 * Created by abhangp on 9/8/2017.
 */
public class AuditSP extends StoredProcedure
{

   public AuditSP(JdbcTemplate datasource, String sp_name, Integer mode){

      super(datasource, sp_name);
      switch (mode) {
         case 0:
            declareParameter(new SqlParameter("p_id", Types.NUMERIC));
            declareParameter(new SqlParameter("p_logonid", Types.NUMERIC));
            declareParameter(new SqlParameter("p_userid", Types.VARCHAR));
            declareParameter(new SqlParameter("p_sessionid", Types.VARCHAR));
            declareParameter(new SqlParameter("p_ip", Types.VARCHAR));
            declareParameter(new SqlParameter("p_status", Types.VARCHAR));
            declareParameter(new SqlParameter("p_remarks", Types.VARCHAR));
            declareParameter(new SqlParameter("p_logoutWay", Types.VARCHAR));
            break;
         case 1:
            declareParameter(new SqlParameter("p_filter", Types.VARCHAR));
            break;
         default:
      }
      compile();
   }
   public Long loginAuditEntry(LoginAudit loginAudit)
   {
      Long loginAuditId=null;
      Map inputMap = new HashMap();
      inputMap.put("p_id", loginAudit.getId());
      inputMap.put("p_logonid", loginAudit.getLogonid());
      inputMap.put("p_userid", loginAudit.getUserid());
      inputMap.put("p_sessionid", loginAudit.getSessionid());
      inputMap.put("p_ip", loginAudit.getIp());
      inputMap.put("p_status", loginAudit.getStatus());
      inputMap.put("p_remarks", loginAudit.getRemarks());
      inputMap.put("p_logoutWay", loginAudit.getLogoutWay());
//      super.execute(inputMap);
      Map<String,Object> results = super.execute(inputMap);

      if(results !=null){
         ArrayList<LinkedHashMap<String, Object>> rows = (ArrayList<LinkedHashMap<String, Object>>) results.get("#result-set-1");
         if (rows != null)
         {
            loginAuditId=(Long)getDBResult(rows, "login_audit_id");
         }
      }

      return loginAuditId;
   }

   private Object getDBResult(ArrayList<LinkedHashMap<String, Object>> rows, String valForObj){

      try{
         Iterator<LinkedHashMap<String, Object>> itr = rows.iterator();
         String fileId = null;
         while (itr.hasNext())
         {
            LinkedHashMap<String, Object> map = itr.next();
            return  map.get(valForObj);
         }
      }catch(Exception e)
      {
         e.printStackTrace();
      }
      return null;
   }


}
