package com.invessence.web.dao.common;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import com.invessence.converter.SQLData;
import com.invessence.web.data.common.UserData;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.faces.bean.*;

import com.invessence.web.constant.*;

@ManagedBean(name = "userInfoDAO")
@ApplicationScoped
@SuppressWarnings({"unchecked", "DuplicateStringLiteralInspection"})
public class UserInfoDAO extends JdbcDaoSupport
{
   SQLData convert = new SQLData();

   public Long addUserInfo(UserData userData)
   {
      UserInfoSP sp = new UserInfoSP(getDataSource(),"sp_login_add_mod",0);
      String action = "A";

      Map outMap = sp.addUser(action, userData);

      return ((Long) outMap.get("p_logonid"));
   }

   public Boolean selectUserInfo(UserData data)
   {
      if (data == null)
         return false;

      UserInfoSP sp = new UserInfoSP(getDataSource(),"sel_user_logon",1); // Also see validateUserID, checkReset below

      Map outMap = sp.selectUserProfile(data);
         if (outMap != null)
         {
            ArrayList<Map<String, Object>> rows = (ArrayList<Map<String, Object>>) outMap.get("#result-set-1");
            if (rows == null)
               return false;
            int i = 0;
            for (Map<String, Object> map : rows)
            {
               Map rs = (Map) rows.get(i);

               data.setLogonID(convert.getLongData(rs.get("logonid")));
               data.setUserID(convert.getStrData(rs.get("userid")));
               data.setLogonstatus(convert.getStrData(rs.get("logonstatus")));
               data.setEmail(convert.getStrData(rs.get("email")));
               data.setCurrentPassword(convert.getStrData(rs.get("pwd")));
               data.setLastName(convert.getStrData(rs.get("lastname")));
               data.setFirstName(convert.getStrData(rs.get("firstname")));
               data.setEmailalt(convert.getStrData(rs.get("emailalt")));
               data.setQ1(convert.getStrData(rs.get("question1")));
               data.setAns1(convert.getStrData(rs.get("answer1")));
               data.setQ2(convert.getStrData(rs.get("question2")));
               data.setAns2(convert.getStrData(rs.get("answer2")));
               data.setQ3(convert.getStrData(rs.get("question3")));
               data.setAns3(convert.getStrData(rs.get("answer3")));
               data.setEmailmsgtype(convert.getStrData(rs.get("emailmsgtype")));
               data.setCid(convert.getStrData(rs.get("cid")));
               data.setAdvisor(convert.getStrData(rs.get("advisor")));
               data.setRep(convert.getStrData(rs.get("rep")));
               data.setResetID(convert.getStrData(rs.get("resetID")));
               return true;
            }
         }
         return false;
    }


   // This method attempts to find data based on UserID/Email/LogonID.
   // True - if Found.
   // false - Exception: if LogonID on DB and matches the logonID on data source.
   // false - Cannot continue
   public Boolean validateUserID(UserData data)
   {
      // No Data provided.  Then assume false.
      if (data == null) {
         return true;
      }

      // This code will userid.  LogonID and email is forced to be null.
      UserInfoSP sp = new UserInfoSP(getDataSource(),"sel_user_logon",1);
      Map outMap = sp.selectUserProfile(data);

      // If object is null, then database did not find anything.  More System Error
      if (outMap == null)
      {
         return false;  // DB issue.  It should return some object.
      }

         ArrayList<Map<String, Object>> rows = (ArrayList<Map<String, Object>>) outMap.get("#result-set-1");
         if (rows == null || rows.size() == 0)
            return false;  // No data.  Can continue

         if (data.getLogonID() == null)
            return true;    // If logon ID is not defined, then return true, because we have some data.

         Map rs = (Map) rows.get(0);
         // In this case the result=set array map was found but there is no data.  In this case
         // we can assume that no userid was found.

         Long tLogonID = convert.getLongData(rs.get("logonid"));

         // If there are records and both logonID is same (DB vs. Cache)
         return (! tLogonID.equals(data.getLogonID()));
   }

   public int checkReset(UserData data)
   {
      /* 0 = User found, and resetID matches and user was not in active status.
         1 = User found, and resetID matches and user in in active status
         -2 = User is not found on the database.
         -3 = data object to fetch was not provided
       */
      if (data == null)
      {
         return -3;
      }

      // This code will check by logonid, userid, and email to see if it exists
      UserInfoSP sp = new UserInfoSP(getDataSource(), "sel_user_logon", 1);

      Map outMap = sp.selectUserProfile(data);
      if (outMap != null)
      {
         ArrayList<Map<String, Object>> rows = (ArrayList<Map<String, Object>>) outMap.get("#result-set-1");
         if (rows != null)
         {
            int i = 0;
            for (Map<String, Object> map : rows)
            {
               Map rs = (Map) rows.get(i);
               // We don't need the data.  If just compare the data with ResetID.
               String logonstatus = convert.getStrData(rs.get("logonstatus"));
               String dbresetID = convert.getStrData(rs.get("resetID"));
               data.setLogonstatus(logonstatus);
               data.setUserID(convert.getStrData(rs.get("userid")));
               data.setEmail(convert.getStrData(rs.get("email")));

               // Don't bother checking the reset code.  Since it is active, just send status 1
               if (logonstatus.equalsIgnoreCase("A"))
               {
                  return 1;
               }

               // If anything other then active, then and resetID matches, then 0.
               if (dbresetID != null && dbresetID.equalsIgnoreCase(data.getResetID())) {
                  return 0;
               }
            }
            return -2;
         }
      }
      return -2;
   }

   public String updateUserProfile(UserData userData)
   {
      String message;
      UserInfoSP sp = new UserInfoSP(getDataSource(),"sp_update_userid_pwd",2);

      Map outMap = sp.updateUserProfile(userData);
      return ( outMap.get("message").toString());


   }

   public void updateSecurityQuestions(UserData userData)
   {

      UserInfoSP sp = new UserInfoSP(getDataSource(),"sp_update_user_security_questions",3);

      sp.updateSecurityQuestions(userData);

   }

   public void resetPassword(String userID, String password)
   {

      UserInfoSP sp = new UserInfoSP(getDataSource(),"sp_update_password",4);

      sp.updatePassword(userID, password);
   }

   public void addUserAccess(UserData data)
   {
      UserInfoSP sp = new UserInfoSP(getDataSource(),"sp_user_access_add_mod",5);
      sp.addUserAccess(data);
   }


   public int updLogonStatus(String logonid)
   {
      String sql = "update user_logon set logonstatus = 'A', resetID = null, lastupdated = now() " +
         "where logonid = ?";

      return this.getJdbcTemplate().update(sql, new Object[]{logonid});
   }

   public int updResetID(Long logonid, String resetID)
   {
      String sql = "update user_logon set resetID = ?, lastupdated = now(), logonstatus = 'R' " +
         "where logonid = ?";

      return this.getJdbcTemplate().update(sql, new Object[]{resetID, logonid.toString()});
   }

   public List<UserData> getUserByEmail(final String emailID) throws DataAccessException
   {

      String sql = "select usr.logonid, usr.userid, usr.status, \n" +
         "\tusr.phone  \n" +
         "\tfrom user_logon usr \n" +
         "\twhere (usr.email = ?)\n";

      ParameterizedRowMapper<UserData> mapper = new ParameterizedRowMapper<UserData>()
      {
         public UserData mapRow(ResultSet rs, int rowNum) throws SQLException
         {

            UserData data = new UserData();
            data.setLogonID(rs.getLong(1));
            data.setUserID(rs.getString(2));
            data.setStatus(rs.getInt(3));
            // data.setPhone(rs.getString(4));
            // data.setSendText(rs.getInt(5));

            data.setEmailID(emailID);

            return data;
         }
      };

      return getJdbcTemplate().query(sql, mapper, emailID);

   }

   public String checkMimeType(String userID)
   {

      String sql = "select emailmsgtype from user_logon where userid = ?";

      SqlRowSet rs = getJdbcTemplate().queryForRowSet(sql, new Object[]{userID});
      String emailmsgtype = "";
      while (rs.next())
      {
         emailmsgtype = rs.getString(1);
      }
      return emailmsgtype;

   }





}

