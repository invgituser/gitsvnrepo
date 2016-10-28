package com.invessence.dao;


import java.sql.Types;
import java.util.*;
import javax.sql.DataSource;

import com.invessence.data.SurveyData;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;


public class SurveySP extends StoredProcedure
{

   public SurveySP(DataSource datasource, String sp_name, Integer mode)
   {
      super(datasource, sp_name);
      switch (mode) {
         case 0:
            declareParameter(new SqlParameter("p_email", Types.VARCHAR));
            declareParameter(new SqlParameter("p_lastname", Types.VARCHAR));
            declareParameter(new SqlParameter("p_firstname", Types.VARCHAR));
            declareParameter(new SqlParameter("p_leadsource", Types.VARCHAR));
            declareParameter(new SqlParameter("p_passwrd", Types.VARCHAR));
            declareParameter(new SqlParameter("p_followup", Types.VARCHAR));
            declareParameter(new SqlParameter("p_surveylink", Types.VARCHAR));
            declareParameter(new SqlParameter("p_emailMimeType", Types.VARCHAR));
            break;
         case 1:
            declareParameter(new SqlParameter("p_filter", Types.VARCHAR));
            break;
         case 2:
            declareParameter(new SqlParameter("p_source", Types.VARCHAR));
            break;
         default:
      }
      compile();
   }

   @SuppressWarnings({"unchecked", "rawtypes"})
   public Map saveData(SurveyData data)
   {

      Map inputMap = new HashMap();
      inputMap.put("p_email", data.getEmail());
      inputMap.put("p_lastname", data.getLastname());
      inputMap.put("p_firstname", data.getFirstname());
      inputMap.put("p_leadsource", data.getLeadsource());
      inputMap.put("p_passwrd", data.getPasswrd());
      inputMap.put("p_followup", data.getFollowup());
      inputMap.put("p_surveylink", data.getSurveylink());
      inputMap.put("p_emailMimeType", data.getEmailMimeType());
      return super.execute(inputMap);
   }

   @SuppressWarnings({"unchecked", "rawtypes"})
   public Map getVisitors(String filter)
   {

      Map inputMap = new HashMap();
      inputMap.put("p_filter", filter);
      return super.execute(inputMap);
   }

   @SuppressWarnings({"unchecked", "rawtypes"})
   public Map getPasswords(String filter)
   {

      Map inputMap = new HashMap();
      inputMap.put("p_source", filter);
      return super.execute(inputMap);
   }

}
