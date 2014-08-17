package com.invessence.dao;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.faces.bean.*;

import com.invessence.data.*;
import com.invessence.constant.*;
import com.invessence.util.*;

@ManagedBean
@RequestScoped
public class AccountDAO extends SimpleJdbcDaoSupport
{


   public Long addUserInfo(UserData userData)
   {
      SignupFunctionSP sp = new SignupFunctionSP(getDataSource());
      String action = Const.DB_FUNCTION_ADD;

      Map outMap = sp.addUser(action, userData);

      return ((Long) outMap.get(Const.LOGONID_PARAM)).longValue();
   }

   public void editDetails(UserData userData)
   {

      SignupFunctionSP sp = new SignupFunctionSP(getDataSource());
      String action = Const.DB_FUNCTION_MOD;
      Map outMap = sp.addUser(action, userData);
   }

   public void resetPassword(String emailID, String password)
   {

      String sql = "update user_logon set pwd = ?,  logonstatus = 'A',  lastupdated = now(), " +
         "attempts=0, resetID=null " +
         "where email = ?";

      this.getJdbcTemplate().update(sql, new Object[]{password, emailID});
   }


   public int checkUserID(String userID)
   {

      String sql = "select count(*) from user_logon where userid = ?";

      return getJdbcTemplate().queryForInt(sql, new Object[]{userID});
   }

   public int checkUserLogonID(UserData userData)
   {

      String sql = "select count(*) from user_logon where logonid != ?";

      return getJdbcTemplate().queryForInt(sql, new Object[]{userData.getLogonID()});
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

   public int checkEmailAccount(String emailID, String resetID)
   {

      String sql = "select logonid from user_logon where email_id = ? and resetID = ?"; //fix status

      SqlRowSet rs = getJdbcTemplate().queryForRowSet(sql, new Object[]{emailID, resetID});

      int logonid = 0;
      while (rs.next())
      {

         logonid = rs.getInt(1);
      }

      return logonid;

      //return getJdbcTemplate().queryForInt(sql, new Object[] {emailID, password});
   }

   public int checkEmailLogonID(UserData userData)
   {

      String sql = "select count(*) from user_logon where email_id = ? and logonid != ?";

      return getJdbcTemplate().queryForInt(sql, new Object[]{userData.getEmailID(), userData.getLogonID()});
   }

   public int checkReset(String emailID, String resetID)
   {

      String sql = "select count(*) from user_logon where email = ? and resetID = ?";

      return getJdbcTemplate().queryForInt(sql, new Object[]{emailID, resetID});
   }


   public void editAccountEmailUserID(UserData userData)
   {

      //String today        = DateUtil.getDate("yyyy-MM-dd HH:mm:ss");

      String sql = "update user_logon set email_id = ?, user_id = ?, updated_date = now() " +
         "where logonid = ?";

      this.getJdbcTemplate().update(sql, new Object[]{userData.getEmailID(), userData.getUserID(), userData.getLogonID()});
   }

   public void editAccountPassword(UserData userData)
   {

      String sql = "update user_info set  password = ?, updated_date = now() " +
         "where user_info_id = ?";

      this.getJdbcTemplate().update(sql, new Object[]{userData.getPassword(), userData.getLogonID()});
   }

   public void editAccountImage(UserData userData)
   {


      //String today  = DateUtil.getDate("yyyy-MM-dd HH:mm:ss");

      String sql = "update user_info set image_file = ?, updated_date = now() " +
         "where user_info_id = ?";

      this.getJdbcTemplate().update(sql, new Object[]{userData.getMediaData().getMediaFile(), userData.getLogonID()});
   }


   public int updSecCode(String emailID, String secCode)
   {


      String sql = "update user_logon set pwd = ?, lastupdated = now(), logonstatus = 'A' " +
         "where email = ?";

      return this.getJdbcTemplate().update(sql, new Object[]{secCode, emailID});
   }

   public int updResetID(String emailID, String resetID)
   {
      String sql = "update user_logon set resetID = ?, lastupdated = now(), logonstatus = 'R' " +
         "where email = ?";

      return this.getJdbcTemplate().update(sql, new Object[]{resetID, emailID});
   }

   public UserData getUserInfoByEmail(String emailID)
   {

      List<UserData> userDataList = getUserByEmail(emailID);

      if ((userDataList != null) && (userDataList.size() > 0))
      {
         UserData userData = userDataList.get(0);
         userData.setRoleDataList(getRoleList(emailID));

         return userData;

      }
      else
      {
         return new UserData();
      }

   }

   public List<UserData> getUserByEmail(final String emailID) throws DataAccessException
   {

      String sql = "select usr.logonid, usr.user_id, usr.status, \n" +
         "\tusr.phone,  \n" +
         "\tfrom user_logon usr \n" +
         "\twhere (usr.email_id = ?)\n";

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

      return getSimpleJdbcTemplate().query(sql, mapper, emailID);

   }

   public List<RoleData> getRoleList(String logonid)
   {
      String sql = "select ua.logonid, ua.acctnum, ua.functionid, ua.role, ua.privileges " +
         "\tfrom user_access_role ua \n" +
         "\twhere (user.logonid = ?)\n";

      ParameterizedRowMapper<RoleData> mapper = new ParameterizedRowMapper<RoleData>()
      {
         public RoleData mapRow(ResultSet rs, int rowNum) throws SQLException
         {

            RoleData data = new RoleData();
            data.setUserlogon(rs.getInt(1));
            data.setUseracct(rs.getLong(2));
            data.setFunctionid(rs.getInt(3));
            data.setRole(rs.getString(4));
            data.setPrivilege(rs.getString(5));

            return data;
         }
      };

      return getSimpleJdbcTemplate().query(sql, mapper, logonid);


   }

   public void updateUserStatus(Long logonid, int status)
   {
      //final int imageSize = in.available();

      String sql = "update user_logon set status = ? where (logonid = ?)";

      getJdbcTemplate().update(sql, new Object[]{status, logonid});

   }

   public void updateRole(Long logonid, String privilege)
   {
      //final int imageSize = in.available();
      String sql = "update user_access_rol set privilegs = ? where logonid = ?";

      getJdbcTemplate().update(sql, new Object[]{privilege, logonid});

   }


   public List<UserData> getUserList(final String sql) throws DataAccessException
   {

      ParameterizedRowMapper<UserData> mapper = new ParameterizedRowMapper<UserData>()
      {
         public UserData mapRow(ResultSet rs, int rowNum) throws SQLException
         {

            UserData data = new UserData();

            int i = 1;
            data.setLogonID(rs.getInt(i++));

            String userID = rs.getString(i++);
            data.setUserID(userID);
            data.setEmailID(rs.getString(i++));

            data.setFirstName(rs.getString(i++));
            data.setMiddleInitial(rs.getString(i++));
            data.setLastName(rs.getString(i++));

            data.setMotto(rs.getString(i++));

            String imageFile = rs.getString(i++);
            if (Util.isNull(imageFile))
            {
               data.getMediaData().setMediaFile(Const.DEFAULT_IMAGE);
            }
            else
            {
               data.getMediaData().setMediaFile(imageFile);
            }
            data.setAboutMe(rs.getString(i++));

            data.setCity(rs.getString(i++));
            data.setStateProvince(rs.getString(i++));
            data.setCountry(rs.getString(i++));
            data.setJoinedDate(rs.getString(i++));

            return data;
         }
      };


      return getSimpleJdbcTemplate().query(sql, mapper);
   }


   public int getUserCount(String searchStr)
   {

      String sql = "select count(*) from user_logon where status >= 0 ";

      if (!Util.isNull(searchStr))
      {
         sql += " and user_id like '" + searchStr + "%' ";

      }

      return getJdbcTemplate().queryForInt(sql);
   }


}

