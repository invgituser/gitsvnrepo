package com.invessence.dao.common;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import com.invessence.converter.SQLData;
import com.invessence.data.common.*;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.faces.bean.*;

import com.invessence.constant.*;

@ManagedBean(name = "userInfoDAO")
@SessionScoped
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
   public void selectUserInfo(Long logonID, String userid, String email, UserData data)
   {
      UserInfoSP sp = new UserInfoSP(getDataSource(),"sel_user_logon",1);
      Map outMap = sp.selectUserProfile(logonID, userid, email);
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
                  data.setCid(convert.getStrData(rs.get("cid")));
                  data.setAdvisor(convert.getStrData(rs.get("advisor")));
                  data.setRep(convert.getStrData(rs.get("rep")));
                  data.setLogonstatus(convert.getStrData(rs.get("logonstatus")));
                  data.setResetID(convert.getIntData(rs.get("resetID")));
                  data.setAccess(convert.getStrData(rs.get("access")));
                  data.setIp(convert.getStrData(rs.get("ip")));
                  data.setEmailmsgtype(convert.getStrData(rs.get("emailmsgtype")));
                  break;
               }
            }
         }
      }
      catch (Exception ex) {
         System.out.print("Error when attempting to collect data from sel_user_logon: "+ logonID + "," + userid + "," + email);
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

   public String checkEmailID(String email)
   {

      String sql = "select email from user_logon where email = ?";

      SqlRowSet rs = getJdbcTemplate().queryForRowSet(sql, new Object[]{email});
      String value = "";
      while (rs.next())
      {
         value = rs.getString(1);
      }
      return value;

   }

   public String checkUserID(String userid)
   {

      String sql = "select email from user_logon where userid = ?";

      SqlRowSet rs = getJdbcTemplate().queryForRowSet(sql, new Object[]{userid});
      String value = "";
      while (rs.next())
      {
         value = rs.getString(1);
      }
      return value;

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


   public int updLogonStatus(String userID)
   {
      String sql = "update user_logon set logonstatus = 'A', resetID = null, lastupdated = now() " +
         "where userid = ?";

      return this.getJdbcTemplate().update(sql, new Object[]{userID});
   }

   // Called by Logon process when locking user
   public int updResetID(String userID, String resetID)
   {
      String sql = "update user_logon set resetID = ?, lastupdated = now(), logonstatus = 'R' " +
         "where userid = ?";

      return this.getJdbcTemplate().update(sql, new Object[]{resetID, userID});
   }

   // Userbean: Collect UserInfo
   public void getUserByEmail(String email, UserData userdata) throws DataAccessException
   {

      if (userdata != null) {
         UserInfoSP sp = new UserInfoSP(getDataSource(),"sel_user_info",5);
         userdata.resetData();
         Map outMap = sp.getUserByEmail(email);
         try {
            if (outMap != null)
            {
               ArrayList<Map<String, Object>> rows = (ArrayList<Map<String, Object>>) outMap.get("#result-set-1");
               if (rows != null) {
                  int i = 0;
                  for (Map<String, Object> map : rows)
                  {
                     Map rs = (Map) rows.get(i);
                     userdata.setEmail(convert.getStrData(rs.get("email")));
                     userdata.setFullName(convert.getStrData(rs.get("name")));
                     userdata.setRep(convert.getStrData(rs.get("repNum")));
                     // userdata.setAdvisor(convert.getStrData(rs.get("repName")));
                     userdata.setFirstName(convert.getStrData(rs.get("firstname")));
                     userdata.setLastName(convert.getStrData(rs.get("lastname")));
                     userdata.setAccess(convert.getStrData(rs.get("access")));
                     userdata.setLogonID(convert.getLongData(rs.get("logonid")));
                     userdata.setUserID(convert.getStrData(rs.get("userid")));
                     // We only need the first data.
                     i++;
                     break;
                  }

                  if (i == 0){
                     userdata.setFullName(null);
                     userdata.setFirstName(null);
                     userdata.setLastName(null);
                     userdata.setAccess(null);
                     userdata.setRep(null);
                     userdata.setAdvisor(null);
                     userdata.setLogonID(0L);
                     userdata.setUserID(null);
                     userdata.setEmail(null);

                  }

               }
            }
         }
         catch (Exception ex) {
            System.out.println("Error, when attempting to fetch from `sel_user_info`: " + email);
            ex.printStackTrace();
            userdata.setFullName(null);
            userdata.setRep(null);
            userdata.setAdvisor(null);
            userdata.setLogonID(0L);
            userdata.setUserID(null);
            userdata.setEmail(null);
         }
      }
   }


   public List<AccountData> getListofAccount(Long logonid, Long acctnum) throws DataAccessException
   {

         UserInfoSP sp = new UserInfoSP(getDataSource(),"sel_active_accountInfo_list",6);
         List<AccountData> accountDataList = null;
         Map outMap = sp.getListofAccount(logonid, acctnum);
         try {
            if (outMap != null)
            {
               ArrayList<Map<String, Object>> rows = (ArrayList<Map<String, Object>>) outMap.get("#result-set-1");
               accountDataList = new ArrayList<AccountData>();
               if (rows != null) {
                  int i = 0;
                  for (Map<String, Object> map : rows)
                  {
                     Map rs = (Map) rows.get(i);
                     AccountData data = new AccountData();

                     data.setLogonid(convert.getLongData(rs.get("logonid")));
                     data.setAcctnum(convert.getLongData(rs.get("acctnum")));
                     data.setFirstname(convert.getStrData(rs.get("firstname")));
                     data.setMiddle(convert.getStrData(rs.get("middle")));
                     data.setLastname(convert.getStrData(rs.get("lastname")));
                     data.setInitialInvestment(convert.getDoubleData(rs.get("investment")));
                     data.setAdvisor(convert.getStrData(rs.get("advisor")));
                     data.setTheme(convert.getStrData(rs.get("cusip")));
                     data.setRiskIndex(convert.getDoubleData(rs.get("riskIndex")));
                     data.setAge(convert.getIntData(rs.get("age")));
                     data.setHorizon(convert.getIntData(rs.get("horizon")));
                     data.setAccttype(convert.getStrData(rs.get("acctType")));
                     data.setEmail(convert.getStrData(rs.get("email")));
                     data.setActualCapital(convert.getDoubleData(rs.get("actualInvestment")));
                     data.setAns1(convert.getIntData(rs.get("ans1")));
                     data.setAns2(convert.getIntData(rs.get("ans2")));
                     data.setAns3(convert.getIntData(rs.get("ans3")));
                     data.setAns4(convert.getIntData(rs.get("ans4")));
                     data.setAns5(convert.getIntData(rs.get("ans5")));
                     data.setAns6(convert.getIntData(rs.get("ans6")));
                     data.setAns7(convert.getIntData(rs.get("ans7")));
                     data.setAns8(convert.getIntData(rs.get("ans8")));
                     data.setAns9(convert.getIntData(rs.get("ans9")));
                     data.setAns10(convert.getIntData(rs.get("ans10")));
                     data.setFormula(convert.getStrData(rs.get("formula")));
                     data.setDateOpened(convert.getStrData(rs.get("dateOpened")));

                     data.setClientAccountID(convert.getStrData(rs.get("clientAccountID")));
                     data.setRole(convert.getStrData(rs.get("role")));
                     data.setPrivileges(convert.getStrData(rs.get("privileges")));
                     data.setEmail(convert.getStrData(rs.get("email")));
                     data.setUserid(convert.getStrData(rs.get("userid")));
                     data.setMailAddrs1(convert.getStrData(rs.get("mailAddrs1")));
                     data.setMailAddrs2(convert.getStrData(rs.get("mailAddrs2")));
                     data.setMailAddrs3(convert.getStrData(rs.get("mailAddrs3")));
                     data.setMailCity(convert.getStrData(rs.get("mailCity")));
                     data.setMailState(convert.getStrData(rs.get("mailState")));
                     data.setMailZipCode(convert.getStrData(rs.get("mailZipCode")));
                     data.setMailCountry(convert.getStrData(rs.get("mailCountry")));
                     data.setPrimaryPhoneNbr(convert.getStrData(rs.get("primaryPhoneNbr")));
                     data.setSecondayPhoneNbr(convert.getStrData(rs.get("secondayPhoneNbr")));
                     data.setWorkPhoneNbr(convert.getStrData(rs.get("workPhoneNbr")));
                     data.setFaxNbr(convert.getStrData(rs.get("faxNbr")));

                     // We only need the first data.
                     accountDataList.add(data);
                     i++;
                     break;
                  }
               }
            }
            return accountDataList;
         }
         catch (Exception ex) {
            System.out.println("Error, when attempting to fetch from `sel_active_accountInfo_list`: " + logonid.toString());
         }
         return null;
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

