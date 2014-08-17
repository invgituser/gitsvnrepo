package com.invessence.dao;

import com.invessence.util.Util;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import org.springframework.security.core.*;
import org.springframework.security.core.authority.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.dao.*;

import com.invessence.constant.*;
import com.invessence.data.*;
import org.springframework.security.web.WebAttributes;

public class CustomJdbcDaoImpl extends JdbcDaoImpl
{

   private String updateAttemptsSql = null;
   private String lockUserSql = null;
   private String listofQAQuery = null;
   Util utl = new Util();
   boolean enabled = true;
   boolean accountNonLocked = true;
   boolean accountNonExpired = true;
   boolean credentialsNonExpired = true;

   public String getUpdateAttemptsSql()
   {
      return updateAttemptsSql;
   }

   public void setUpdateAttemptsSql(String updateAttemptsSql)
   {
      this.updateAttemptsSql = updateAttemptsSql;
   }

   public String getLockUserSql()
   {
      return lockUserSql;
   }

   public void setLockUserSql(String lockUserSql)
   {
      this.lockUserSql = lockUserSql;
   }

   public String getListofQAQuery()
   {
      return listofQAQuery;
   }

   public void setListofQAQuery(String listofQAQuery)
   {
      this.listofQAQuery = listofQAQuery;
   }

   public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException
   {

      UserInfoData userInfo = getUserInfo(username);

      if (userInfo == null)
      {
         throw new UsernameNotFoundException(username + " is not found");
      }
      else
      {
         return userInfo;
      }
   }

   private UserInfoData getUserInfo(String username)
   { //username is email

      UserInfoData userInfo;

      long logonID = 0;
      String userid = null;
      String savedemail = null;
      String savedpassword = null;
      String logonStatus = null;
      Integer attempts = 0;
      String acctownertype = null;
      String logo = null;
      String groupname = null;
      Collection<GrantedAuthority> authorities;
      String ip, macaddress, cookieID, resetID;
      Integer randomQuestion;
      Integer rnumber;
      String sql;
      Map qa;
      Boolean fetchData;

      String myIP = utl.getClientIpAddr((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest());
      System.out.println("Attempting Logon >> " + username + " from: " + myIP);
      userInfo =  (UserInfoData) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(Const.USER_INFO);

      if (userInfo != null)
         if (username.equalsIgnoreCase(userInfo.getUserID()))
            attempts =  (Integer) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(Const.USERLOGON_ATTEMPTS);
         else
            attempts = 0;
      else
         attempts = 0;


         // We need to fetch data all the time (for username and password)...
         // Don't use the buffered session data.
         sql = getUsersByUsernameQuery();
         SqlRowSet rs = getJdbcTemplate().queryForRowSet(sql, new Object[]{username});

            if ((rs != null) && (rs.next()))
            {

               logonID = rs.getLong("logonid");
               userid = rs.getString("userid");
               logonStatus = rs.getString("logonstatus");
               savedemail = rs.getString("email");
               savedpassword = rs.getString("pwd");
               // attempts = rs.getInt("attempts");
               ip = rs.getString("ip");
               macaddress = rs.getString("macaddress");
               cookieID = rs.getString("cookieID");
               resetID = rs.getString("resetID");
               acctownertype = rs.getString("accttype");
               logo =  rs.getString("logo");
               groupname = rs.getString("groupname");
               // get List of questions...
               qa = getQA(username);
               authorities = getAuthorities(username);
               // Note: it is either set with number of attempts or it was set in past attempt.
               randomQuestion = utl.randomGenerator(0,2);

            }
         else {
               logonStatus = "X";
               savedemail = "";
               savedpassword = "";
               // attempts = rs.getInt("attempts");
               ip = "";
               macaddress = "";
               cookieID = "";
               resetID = "";
               acctownertype="";
               logo="";
               groupname="";
               qa=null;
               authorities = null;
               // Note: it is either set with number of attempts or it was set in past attempt.
               randomQuestion = 0;

            }

      if (attempts == null)
         attempts = 0;

     accountNonLocked=true;
     if ((logonStatus != null) && (! logonStatus.equalsIgnoreCase("L"))) {
         // If userStatus is empty and it is not locked then add attempts made.
         attempts = attempts + 1;
         if (attempts > Const.MAX_ATTEMPTS) {
            // if more then MAX_ATTEMPTS are made, then get a resetID and lock the user.
            logonStatus = "L";
            // Create new ResetID

            rnumber = utl.randomGenerator(0,578965);
            resetID=rnumber.toString();
            sql = getLockUserSql();
            getJdbcTemplate().update(sql,new Object[]{logonStatus, rnumber, username});
         }
      }

      if (logonStatus.equalsIgnoreCase("L")) {
         accountNonLocked = false;
      }

      // Note:  We are always re-createating userINFO
      credentialsNonExpired = true; // Reset for now.  We need logic to redirect.
      accountNonExpired=true;
      enabled=true;
      userInfo = new UserInfoData(logonID, userid, username, savedemail, savedpassword,
                                  enabled, accountNonExpired, credentialsNonExpired, accountNonLocked,
                                  authorities, ip, macaddress, cookieID, resetID,acctownertype, logo, groupname,
                                  qa, attempts, logonStatus, randomQuestion);


      FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove(Const.USER_INFO);
      FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(Const.USER_INFO, userInfo);
      FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(Const.USERLOGON_ATTEMPTS, attempts);
      FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(Const.USERLOGON_ACCTTYPE, acctownertype.toUpperCase());

      return userInfo;
   }

   private Map getQA(String username)
   {
      Map<Integer,String[]> qa = new HashMap<Integer,String[]>();
      String[]questAns=new String[2];
      String sql = getListofQAQuery();
      String question, answer;
      SqlRowSet rs2 = getJdbcTemplate().queryForRowSet(sql, new Object[]{username});
      if ((rs2 != null) && (rs2.next()))
      {
         question =  rs2.getString("question1");
         answer =    rs2.getString("answer1");

         if (question == null || question.isEmpty()) {
            this.credentialsNonExpired = false;
         }
         if (answer == null || answer.isEmpty()) {
            this.credentialsNonExpired = false;
         }
         questAns[0] = question;
         questAns[1] = answer;
         qa.put(0, questAns);

         question =  rs2.getString("question2");
         answer =    rs2.getString("answer2");

         if (question == null || question.isEmpty()) {
            this.credentialsNonExpired = false;
         }
         if (answer == null || answer.isEmpty()) {
            this.credentialsNonExpired = false;
         }
         questAns[0] = question;
         questAns[1] = answer;
         qa.put(1, questAns);

         question =  rs2.getString("question3");
         answer =    rs2.getString("answer3");

         if (question == null || question.isEmpty()) {
            this.credentialsNonExpired = false;
         }
         if (answer == null || answer.isEmpty()) {
            this.credentialsNonExpired = false;
         }
         questAns[0] = question;
         questAns[1] = answer;
         qa.put(2, questAns);
      }
      return qa;
   }

   private Collection<GrantedAuthority> getAuthorities(String username)
   {

      String sql = getAuthoritiesByUsernameQuery();
      SqlRowSet rs = getJdbcTemplate().queryForRowSet(sql, new Object[]{username});

      if (rs == null)
      {
         return null;
      }

      Collection<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
      list.add(new GrantedAuthorityImpl(Const.ROLE_USER));

      while (rs.next())
      {
         String auth = rs.getString(1);
         list.add(new GrantedAuthorityImpl(auth));
      }


      return list;
   }

   protected List<Integer> getList(String sql) throws DataAccessException
   {
      return getJdbcTemplate().query(sql,
                                     new ParameterizedRowMapper<Integer>()
                                     {
                                        public Integer mapRow(ResultSet rs, int rowNum) throws SQLException
                                        {
                                           return rs.getInt(1);
                                        }
                                     });
   }

}

