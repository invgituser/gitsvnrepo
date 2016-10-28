package com.invessence.dao;

import java.sql.*;
import java.util.*;
import javax.faces.bean.*;
import javax.sql.DataSource;

import com.invessence.converter.SQLData;
import com.invessence.data.*;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.rowset.SqlRowSet;

@ManagedBean(name = "surveyDAO")
@ApplicationScoped
public class SurveyDAO extends JdbcDaoSupport
{
   SQLData convert = new SQLData();

   public static class PasswrdData
   {
      private Integer passwordID;
      private String passwrd;

      public Integer getPasswordID()
      {
         return passwordID;
      }

      public void setPasswordID(Integer passwordID)
      {
         this.passwordID = passwordID;
      }

      public String getPasswrd()
      {
         return passwrd;
      }

      public void setPasswrd(String passwrd)
      {
         this.passwrd = passwrd;
      }
   }

   public String saveData(SurveyData surveydata)
   {
      try {
         DataSource ds = getDataSource();
         SurveySP sp = new SurveySP(ds, "sp_add_survey_visitor",0);

         Map outMap = sp.saveData(surveydata);

      }
      catch (Exception ex) {
         return "Failed";
      }
      return "Success";
   }

   public List<SurveyData> listVisitors(String filter)
   {
      DataSource ds = getDataSource();
      SurveySP sp = new SurveySP(ds, "sel_survey_visitor",1);
      List<SurveyData> surveyList = new ArrayList<SurveyData>();

      Map outMap = sp.getVisitors(filter);
      if (outMap != null)
      {
         ArrayList<Map<String, Object>> rows = (ArrayList<Map<String, Object>>) outMap.get("#result-set-1");
         int i = 0;
         for (Map<String, Object> map : rows)
         {
            Map rs = (Map) rows.get(i);
            SurveyData data = new SurveyData();

            data.setEmail(convert.getStrData(rs.get("email")));
            data.setFirstname(convert.getStrData(rs.get("firstname")));
            data.setLastname(convert.getStrData(rs.get("lastname")));
            data.setLeadsource(convert.getStrData(rs.get("leadsource")));
            data.setPasswrd(convert.getStrData(rs.get("passwrd")));
            data.setSurveylink(convert.getStrData(rs.get("surveylink")));
            data.setFollowup(convert.getStrData(rs.get("followup")));
            data.setCreated(convert.getStrData(rs.get("created")));
            data.setLastUpdated(convert.getStrData(rs.get("lastUpdated")));
            surveyList.add(i, data);
            i++;
         }
      }

      return surveyList;

   }

   public Map getPasswords(String source)
   {
      DataSource ds = getDataSource();
      SurveySP sp = new SurveySP(ds, "sel_survey_password",2);
      Map<Integer, String> passwrdMap = new HashMap<Integer,String>();

      Map outMap = sp.getPasswords(source);
      if (outMap != null)
      {
         ArrayList<Map<String, Object>> rows = (ArrayList<Map<String, Object>>) outMap.get("#result-set-1");
         int i = 0;
         for (Map<String, Object> map : rows)
         {
            Map rs = (Map) rows.get(i);
            passwrdMap.put(convert.getIntData(rs.get("passwordID")),
                           convert.getStrData(rs.get("passwrd")));

            i++;
         }
      }
       return passwrdMap;
   }


   public List<PasswrdData> getPasswordDB(String source)
   {
      Map<Integer, String> passwrdMap = new HashMap<Integer, String>();
      String sql = "select srvey.passwordID, srvey.passwrd \n" +
         "\tfrom survey_passwords srvey \n" +
         "\twhere (srvey.source = ?)\n";

      SqlRowSet rs = getJdbcTemplate().queryForRowSet(sql, new Object[]{source});
      ParameterizedRowMapper<PasswrdData> mapper = new ParameterizedRowMapper<PasswrdData>()
      {
         public PasswrdData mapRow(ResultSet rs, int rowNum) throws SQLException
         {

            PasswrdData data = new PasswrdData();
            data.setPasswordID(convert.getIntData(rs.getString("passwordID")));
            data.setPasswrd(convert.getStrData(rs.getString("passwrd")));
            return data;
         }
      };
      return getJdbcTemplate().query(sql, mapper, source);
   }


}

