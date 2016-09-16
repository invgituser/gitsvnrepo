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
public class UserInfoDAO extends JdbcDaoSupport
{
   SQLData convert = new SQLData();

   public Long addUserInfo(UserData userData)
   {
      UserInfoSP sp = new UserInfoSP(getDataSource(),"sp_login_add_mod",0);
      String action = "A";

      Map outMap = sp.addUser(action, userData);

      return ((Long) outMap.get(WebConst.LOGONID_PARAM));
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
               return true;
            }
         }
         return false;
    }


   // Called by UserBean to check email, when signup process is called.
   public Boolean validateUserID(String userid)
   {
      if (userid == null) {
         return true;
      }

      // This code will check by logonid, userid, and email to see if it exists
      UserInfoSP sp = new UserInfoSP(getDataSource(),"sel_user_logon",1);

      Map outMap = sp.selectUserProfile(userid);
      if (outMap != null)
      {
         ArrayList<Map<String, Object>> rows = (ArrayList<Map<String, Object>>) outMap.get("#result-set-1");
         if (rows != null && rows.size() > 0) {
            // We don't need the data.  If there is any results, then we know that data was there.
            return true;
         }
      }
      return false;

   }

   public int checkReset(UserData data)
   {
      /* 0 = User found, and resetID matches and user was not in active status.
         1 = User found, not in active status but resetID do not match.
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

               if (logonstatus.equalsIgnoreCase("A"))
               {
                  return -2;
               }
               else
               {
                  if (dbresetID != null && dbresetID.equalsIgnoreCase(data.getResetID().toString()))
                  {
                     return 0;
                  }
                  else
                  {
                     return 1;
                  }
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

   public int updLogonStatus(String userID)
   {
      String sql = "update user_logon set logonstatus = 'A', resetID = null, lastupdated = now() " +
         "where userid = ?";

      return this.getJdbcTemplate().update(sql, new Object[]{userID});
   }

   public int updResetID(String userID, String resetID)
   {
      String sql = "update user_logon set resetID = ?, lastupdated = now(), logonstatus = 'R' " +
         "where userid = ?";

      return this.getJdbcTemplate().update(sql, new Object[]{resetID, userID});
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

