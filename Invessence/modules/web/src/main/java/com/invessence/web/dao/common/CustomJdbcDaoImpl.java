package com.invessence.web.dao.common;

import com.invessence.emailer.data.MsgData;
import com.invessence.web.dao.*;
import com.invessence.web.data.common.*;
import com.invessence.web.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.*;
import org.springframework.security.core.authority.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.*;
import java.util.*;

import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.servlet.http.*;

import org.springframework.dao.*;

import com.invessence.web.constant.*;

public class CustomJdbcDaoImpl extends JdbcDaoImpl
{

   private MsgData data = new MsgData();

   private String lockUserSql = null;
   private String listofQAQuery = null;

   private String usersByEmailQuery=null;

   @Autowired
   private WebUtil webutl;
   @Autowired
   private AuditDAO auditDAO;

   public AuditDAO getAuditDAO()
   {
      return auditDAO;
   }

   public void setAuditDAO(AuditDAO auditDAO)
   {
      this.auditDAO = auditDAO;
   }

   boolean enabled = true;
   boolean accountNonLocked = true;
   boolean accountNonExpired = true;
   boolean credentialsNonExpired = true;

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

   @Override
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

   public String getUsersByEmailQuery()
   {
      return usersByEmailQuery;
   }

   public void setUsersByEmailQuery(String usersByEmailQuery)
   {
      this.usersByEmailQuery = usersByEmailQuery;
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
      String cid = null;
      String advisor = null;
      String rep = null;
      Collection<GrantedAuthority> authorities;
      String firstname, lastname;
      String ip, resetID;
      String stateRegistered;
      Integer randomQuestion;
      Integer rnumber;
      String sql;
      Map qa;
      Boolean fetchData;
      String emailmsgtype = null;
      String access = "User";
      String atstart = "index.xhtml";

      Long logonAuditID=null;

      String exception = null;

      String myIP = webutl.getClientIpAddr((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest());
      //System.out.println("Attempting Logon >> " + username + " from: " + myIP );
      DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
      Date date = new Date();
      System.out.println("Attempting Logon >> " + username + " from: " + myIP + " date:" + dateFormat.format(date));
      userInfo = (UserInfoData) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(WebConst.USER_INFO);

      if (userInfo != null)
      {
         if (username.equalsIgnoreCase(userInfo.getUserID()))
         {
            attempts = (Integer) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(WebConst.USERLOGON_ATTEMPTS);
         }
         else
         {
            attempts = 0;
         }
      }
      else
      {
         attempts = 0;
      }


      // We need to fetch data all the time (for username and password)...
      // Don't use the buffered session data.
      SqlRowSet rs=null;
      if(username.contains("@")){
         sql = getUsersByEmailQuery();
         rs = getJdbcTemplate().queryForRowSet(sql, new Object[]{username});
         if(rs==null){
            sql = getUsersByUsernameQuery();
            rs = getJdbcTemplate().queryForRowSet(sql, new Object[]{username});
         }

      }else{
         sql = getUsersByUsernameQuery();
         rs = getJdbcTemplate().queryForRowSet(sql, new Object[]{username});
      }



      if ((rs != null) && (rs.next()))
      {

         logonID = rs.getLong("logonid");
         userid = rs.getString("userid");
         logonStatus = rs.getString("logonstatus");
         savedemail = rs.getString("email");
         savedpassword = rs.getString("pwd");
         // attempts = rs.getInt("attempts");
         ip = rs.getString("ip");
         firstname = rs.getString("firstname");
         lastname = rs.getString("lastname");
         stateRegistered = ""; // rs.getString("stateRegistered");
         resetID = rs.getString("resetID");
         cid = rs.getString("cid");
         advisor = rs.getString("advisor").equalsIgnoreCase("ADMIN") || rs.getString("advisor").equalsIgnoreCase("DEMO")?webutl.getWebprofile().getDefaultAdvisor():rs.getString("advisor");
         rep = rs.getString("advisor").equalsIgnoreCase("ADMIN") || rs.getString("advisor").equalsIgnoreCase("DEMO")?webutl.getWebprofile().getInvSiteRep():rs.getString("rep");
         rs.getString("rep");
         emailmsgtype = rs.getString("emailmsgtype");
         access = rs.getString("access");
         atstart = rs.getString("atstart");
         // get List of questions...
         qa = getQA(logonID);
         authorities = getAuthorities(logonID);
         // Note: it is either set with number of attempts or it was set in past attempt.
         randomQuestion = webutl.randomGenerator(0, 2);
         String urlbasedadvisor = webutl.getWebprofile().getDefaultAdvisor();
         String testmode = webutl.getWebprofile().getMode();
         if (! (advisor.equalsIgnoreCase("admin")||advisor.equalsIgnoreCase("demo")) &&
            advisor != null && ! advisor.equalsIgnoreCase(urlbasedadvisor.toLowerCase())) {
            enabled = false;
            exception="Username is not valid!";
//            auditDAO.loginAuditEntry(new LoginAudit(null,logonID,sessionId,req.getRemoteAddr(),"F",exception,null));
            throw new BadCredentialsException(exception);
         }

         if (logonStatus != null && logonStatus.equalsIgnoreCase("A"))
         {
            enabled = true;
         } else if (logonStatus != null)
         {
            enabled = false;
            exception="This account is LOCKED.";
            if (logonStatus.equalsIgnoreCase("X"))
            {
               exception = "Username is DISABLED!";
            }
            else if (logonStatus.equalsIgnoreCase("T") || logonStatus.equalsIgnoreCase("I"))
            {
               exception = "User is NOT ACTIVE, please activate account!";
            }
         }
      }
      else
      {
         logonStatus = "X";
         savedemail = "";
         savedpassword = "";
         // attempts = rs.getInt("attempts");
         firstname = "";
         lastname = "";
         ip = "";
         resetID = "";
         cid = "";
         advisor = "";
         rep = null;
         stateRegistered = "";
         emailmsgtype = "";
         access = "User";
         atstart = "index.xhtml";
         qa = null;
         authorities = null;
         // Note: it is either set with number of attempts or it was set in past attempt.
         randomQuestion = 0;
         enabled = false;
         exception="Username is not valid!";
//         auditDAO.loginAuditEntry(new LoginAudit(null,logonID,sessionId,req.getRemoteAddr(),"F",exception,null));
         throw new BadCredentialsException(exception);
      }

      accountNonLocked = true;
      if (enabled)
      {
         if (attempts == null)
         {
            attempts = 0;
         }

         if ((logonStatus != null) && (logonStatus.equalsIgnoreCase("L")))
         {
            accountNonLocked = false;
            attempts = (attempts < 5) ? 5 : attempts;
            exception="Account is LOCKED!";
//            auditDAO.loginAuditEntry(new LoginAudit(null,logonID,sessionId,req.getRemoteAddr(),"F",exception,null));
            throw new BadCredentialsException(exception);
         }
         else if (logonStatus != null)
         {
            // If userStatus is empty and it is not locked then add attempts made.
            attempts = attempts + 1;
            if (attempts > WebConst.MAX_ATTEMPTS)
            {
               // if more then MAX_ATTEMPTS are made, then get a resetID and lock the user.
               logonStatus = "L";
               // Create new ResetID

               rnumber = webutl.randomGenerator(0, 578965);
               resetID = rnumber.toString();
               sql = getLockUserSql();
               getJdbcTemplate().update(sql, new Object[]{logonStatus, rnumber, username});

               // System.out.println("LOGIN MIME TYPE: "+ uid.getEmailmsgtype());
               UserData userdata = new UserData();
               userdata.setLogonstatus(logonStatus);
               userdata.setEmail(savedemail);
               userdata.setLogonID(logonID);
               userdata.setResetID(resetID);
               webutl.sendConfirmation(userdata,"L");
               exception="Too many attemps.  Account Locked!";
//               auditDAO.loginAuditEntry(new LoginAudit(null,logonID,sessionId,req.getRemoteAddr(),"F",exception,null));
               throw new BadCredentialsException(exception);
            }
         }
      }
      else {
         if (exception != null)
            attempts = 0;
//            auditDAO.loginAuditEntry(new LoginAudit(null,logonID,sessionId,req.getRemoteAddr(),"F",exception,null));
            throw new BadCredentialsException(exception);
      }

      // Note:  We are always re-createating userINFO
      credentialsNonExpired = true; // Reset for now.  We need logic to redirect.
      accountNonExpired = true;
      access = (access == null) ? "USER" : access;
      atstart = (atstart == null) ? "index.xhtml" : atstart;
      userInfo = new UserInfoData(logonID, userid, username, savedemail, savedpassword,
                                  enabled, accountNonExpired, credentialsNonExpired,
                                  accountNonLocked, authorities,
                                  lastname, firstname,
                                  ip, resetID,
                                  cid, advisor, rep, stateRegistered,
                                  qa, attempts, access, atstart,
                                  logonStatus, randomQuestion, emailmsgtype, logonAuditID);

      FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove(WebConst.USER_INFO);
      FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(WebConst.USER_INFO, userInfo);
      FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(WebConst.USERLOGON_ATTEMPTS, attempts);

      return userInfo;
   }

   private Map getQA(Long username)
   {
      Map<Integer, String[]> qa = new HashMap<Integer, String[]>();
      String[] questAns = new String[2];
      String sql = getListofQAQuery();
      String question, answer;
      SqlRowSet rs2 = getJdbcTemplate().queryForRowSet(sql, new Object[]{username});
      if ((rs2 != null) && (rs2.next()))
      {
         question = rs2.getString("question1");
         answer = rs2.getString("answer1");

         if (question == null || question.isEmpty())
         {
            this.credentialsNonExpired = false;
         }
         if (answer == null || answer.isEmpty())
         {
            this.credentialsNonExpired = false;
         }
         questAns[0] = question;
         questAns[1] = answer;
         qa.put(0, questAns);

         question = rs2.getString("question2");
         answer = rs2.getString("answer2");

         if (question == null || question.isEmpty())
         {
            this.credentialsNonExpired = false;
         }
         if (answer == null || answer.isEmpty())
         {
            this.credentialsNonExpired = false;
         }
         questAns[0] = question;
         questAns[1] = answer;
         qa.put(1, questAns);

         question = rs2.getString("question3");
         answer = rs2.getString("answer3");

         if (question == null || question.isEmpty())
         {
            this.credentialsNonExpired = false;
         }
         if (answer == null || answer.isEmpty())
         {
            this.credentialsNonExpired = false;
         }
         questAns[0] = question;
         questAns[1] = answer;
         qa.put(2, questAns);
      }
      return qa;
   }

   private Collection<GrantedAuthority> getAuthorities(Long username)
   {

      String sql = getAuthoritiesByUsernameQuery();
      SqlRowSet rs = getJdbcTemplate().queryForRowSet(sql, new Object[]{username});
      Collection<GrantedAuthority> list = new ArrayList<GrantedAuthority>();

      if (rs == null)
      {
         list.add(new SimpleGrantedAuthority(WebConst.WEB_USER));
         return list;
      }


      while (rs.next())
      {
         String auth = rs.getString(1);
         list.add(new SimpleGrantedAuthority(auth));
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

