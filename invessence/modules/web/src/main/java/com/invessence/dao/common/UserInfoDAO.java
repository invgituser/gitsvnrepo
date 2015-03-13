package com.invessence.dao.common;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import com.invessence.converter.SQLData;
import com.invessence.dao.common.UserInfoSP;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.faces.bean.*;

import com.invessence.data.*;
import com.invessence.constant.*;

@ManagedBean(name = "userInfoDAO")
@ApplicationScoped
public class UserInfoDAO extends JdbcDaoSupport
{
   SQLData convert = new SQLData();

   public Long addUserInfo(UserData userData)
   {
      UserInfoSP sp = new UserInfoSP(getDataSource(),"sp_login_add_mod",0);
      String action = Const.DB_FUNCTION_ADD;

      Map outMap = sp.addUser(action, userData);

      return ((Long) outMap.get(Const.LOGONID_PARAM)).longValue();
   }

   public UserData selectUserInfo(Long logonid)
   {
      UserInfoSP sp = new UserInfoSP(getDataSource(),"sel_user_logon",1);
      Map outMap = sp.selectUserProfile(logonid);
      try {
         UserData data = new UserData();
         if (outMap != null)
         {
            ArrayList<Map<String, Object>> rows = (ArrayList<Map<String, Object>>) outMap.get("#result-set-1");
            int i = 0;
            for (Map<String, Object> map : rows)
            {
               Map rs = (Map) rows.get(i);

               data.setLogonID(convert.getLongData(rs.get("logonid")));
               data.setUserID(convert.getStrData(rs.get("userid")));
               data.setEmail(convert.getStrData(rs.get("email")));
               data.setCurrentPassword(convert.getStrData(rs.get("pwd")));
               data.setPrefix(convert.getStrData(rs.get("prefix")));
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
               break;
            }
         }
         return data;
      }
      catch (Exception ex) {
         ex.printStackTrace();
      }
      return null;
   }


   public void updateUserProfile(UserData userData)
   {

      UserInfoSP sp = new UserInfoSP(getDataSource(),"sp_update_userid_pwd",2);

      sp.updateUserProfile(userData);

   }

   public void updateSecurityQuestions(UserData userData)
   {

      UserInfoSP sp = new UserInfoSP(getDataSource(),"sp_update_user_security_questions",3);

      sp.updateSecurityQuestions(userData);

   }

   public void resetPassword(String emailID, String password)
   {

      String sql = "update user_logon set pwd = ?,  logonstatus = 'A',  lastupdated = now(), " +
         "attempts=0, resetID=null " +
         "where email = ?";

      this.getJdbcTemplate().update(sql, new Object[]{password, emailID});
   }



   public String checkEmailID(String emailID)
   {

      String sql = "select email from user_logon where email = ?";

      SqlRowSet rs = getJdbcTemplate().queryForRowSet(sql, new Object[]{emailID});
      String email = "";
      while (rs.next())
      {
         email = rs.getString(1);
      }
      return email;

   }

   public int checkReset(String emailID, String resetID)
   {

      String sql = "select count(*) from user_logon where email = ? and resetID = ?";

      return getJdbcTemplate().queryForInt(sql, new Object[]{emailID, resetID});
   }


   public int updResetID(String emailID, String resetID)
   {
      String sql = "update user_logon set resetID = ?, lastupdated = now(), logonstatus = 'R' " +
         "where email = ?";

      return this.getJdbcTemplate().update(sql, new Object[]{resetID, emailID});
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
            data.setLogonID(rs.getInt(1));
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

   public String checkMimeType(String emailID)
   {

      String sql = "select emailmsgtype from user_logon where userid = ?";

      SqlRowSet rs = getJdbcTemplate().queryForRowSet(sql, new Object[]{emailID});
      String emailmsgtype = "";
      while (rs.next())
      {
         emailmsgtype = rs.getString(1);
      }
      return emailmsgtype;

   }





}

