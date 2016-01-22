package com.invessence.dao.common;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import com.invessence.converter.SQLData;
import com.invessence.data.common.UserData;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.faces.bean.*;

import com.invessence.constant.*;

@ManagedBean(name = "userInfoDAO")
@ApplicationScoped
public class UserInfoDAO extends JdbcDaoSupport
{
   SQLData convert = new SQLData();

   public Long addUserInfo(UserData userData)
   {
      UserInfoSP sp = new UserInfoSP(getDataSource(),"sp_add_ltam_login",0);
      Map outMap = sp.addUser(userData);

      Long logonid = convert.getLongData(outMap.get(Const.LOGONID_PARAM));
      return logonid;
   }

   // Called By profileBean;
   public void selectUserInfo(Long logonid, String userid, UserData data)
   {
      UserInfoSP sp = new UserInfoSP(getDataSource(),"sel_user_logon",1);
      Map outMap = sp.selectUserProfile(logonid, userid);
      try {
         if (outMap != null)
         {
            ArrayList<Map<String, Object>> rows = (ArrayList<Map<String, Object>>) outMap.get("#result-set-1");
            if (rows != null) {
               int i = 0;
               for (Map<String, Object> map : rows)
               {
                  Map rs = (Map) rows.get(i);

                  data.setLogonID(convert.getLongData(rs.get("logonid")));
                  data.setUserID(convert.getStrData(rs.get("userid")));
                  data.setEmail(convert.getStrData(rs.get("email")));
                  data.setPasswordEncrypted(convert.getStrData(rs.get("pwd")));
                  data.setLastName(convert.getStrData(rs.get("lastname")));
                  data.setFirstName(convert.getStrData(rs.get("firstname")));
                  data.setQ1(convert.getStrData(rs.get("question1")));
                  data.setAns1(convert.getStrData(rs.get("answer1")));
                  data.setQ2(convert.getStrData(rs.get("question2")));
                  data.setAns2(convert.getStrData(rs.get("answer2")));
                  data.setQ3(convert.getStrData(rs.get("question3")));
                  data.setAns3(convert.getStrData(rs.get("answer3")));
                  data.setEmailmsgtype(convert.getStrData(rs.get("emailmsgtype")));
                  data.setCid(convert.getStrData(rs.get("cid")));
                  data.setAdvisor(convert.getStrData(rs.get("advisor")));
                  data.setRep(convert.getLongData(rs.get("rep")));
                  break;
               }
            }
         }
      }
      catch (Exception ex) {
         ex.printStackTrace();
      }
   }

   // Called by ProfileBean: To update userid/password.
   public String updateUserProfile(UserData userData)
   {
      String message;
      UserInfoSP sp = new UserInfoSP(getDataSource(),"sp_update_userid_pwd",2);

      Map outMap = sp.updateUserProfile(userData);
      return ( outMap.get("message").toString());


   }

   // Called by ProfileBean: To update security questions.
   public void updateSecurityQuestions(UserData userData)
   {

      UserInfoSP sp = new UserInfoSP(getDataSource(),"sp_update_user_security_questions",3);

      sp.updateSecurityQuestions(userData);

   }

   // Called by UserBean to reset password.
   public void resetPassword(String userID, String password)
   {

      UserInfoSP sp = new UserInfoSP(getDataSource(),"sp_update_password",4);

      sp.updatePassword(userID, password);
   }



   // Called by UserBean to check email, when signup process is called.
   public Integer validateUserID(String userid)
   {

      String sql = "select userid from user_logon where userid = ?";
      SqlRowSet rs = getJdbcTemplate().queryForRowSet(sql, new Object[]{userid});
      String dbuserid = "";
      while (rs.next())
      {
         dbuserid = rs.getString(1);
         break; // Only get the first email.
      }

      if (dbuserid == null || dbuserid.isEmpty()) {
         return 0;
      }
      else {
         return -1;
      }
   }

   public String checkEmailID(String userid)
   {

      String sql = "select email from user_logon where userid = ?";

      SqlRowSet rs = getJdbcTemplate().queryForRowSet(sql, new Object[]{userid});
      String email = "";
      while (rs.next())
      {
         email = rs.getString(1);
      }
      return email;

   }

   // Called by UserBean to check resetID, for given User. (Used during Registration and Reset Password)
   public int checkReset(String check, String userID, String resetID)
   {
      int sqlStatus = 0;
      int count=0;
      String sql = "";
      // First check that we have valid userid.
      sql = "select sel_user_status(?,?,?)";
      sqlStatus = getJdbcTemplate().queryForInt(sql, new Object[]{check,userID,resetID});
      return sqlStatus;
   }


   // Called by Logon process when locking user
   public int updResetID(String userID, String resetID)
   {
      String sql = "update user_logon set resetID = ?, lastupdated = now(), logonstatus = 'R' " +
         "where userid = ?";

      return this.getJdbcTemplate().update(sql, new Object[]{resetID, userID});
   }

   // Userbean: Collect UserInfo
   public void getUserByEmail(UserData userdata) throws DataAccessException
   {

      if (userdata != null) {
         UserInfoSP sp = new UserInfoSP(getDataSource(),"sel_user_info",5);

         Map outMap = sp.getUserByEmail(userdata.getEmail());
         try {
            if (outMap != null)
            {
               ArrayList<Map<String, Object>> rows = (ArrayList<Map<String, Object>>) outMap.get("#result-set-1");
               if (rows != null) {
                  int i = 0;
                  for (Map<String, Object> map : rows)
                  {
                     Map rs = (Map) rows.get(i);

                     userdata.setFullName(convert.getStrData(rs.get("name")));
                     userdata.setRep(convert.getLongData(rs.get("repNum")));
                     userdata.setAdvisor(convert.getStrData(rs.get("repName")));
                     userdata.setLogonID(convert.getLongData(rs.get("logonid")));
                     userdata.setUserID(convert.getStrData(rs.get("userid")));
                     // We only need the first data.
                     i++;
                     break;
                  }

                  if (i == 0){
                     userdata.setFullName(null);
                     userdata.setRep(0L);
                     userdata.setAdvisor(null);
                     userdata.setLogonID(0L);
                     userdata.setUserID(null);

                  }

               }
            }
         }
         catch (Exception ex) {
            userdata.setFullName(null);
            userdata.setRep(0L);
            userdata.setAdvisor(null);
            userdata.setLogonID(0L);
            userdata.setUserID(null);
         }
      }
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

