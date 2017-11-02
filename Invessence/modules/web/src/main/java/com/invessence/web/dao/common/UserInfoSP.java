package com.invessence.web.dao.common;


import javax.sql.DataSource;

import com.invessence.web.data.common.UserData;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.object.StoredProcedure;

import java.sql.Types;
import java.util.*;

@SuppressWarnings({"unchecked", "DuplicateStringLiteralInspection"})
public class UserInfoSP extends StoredProcedure
{

   public UserInfoSP(DataSource datasource, String sp_name, Integer mode)
   {

      //super(datasource, "sp_login_add_mod");
      super(datasource, sp_name);
      switch (mode)
      {
         case 0:
            declareParameter(new SqlParameter("p_addmod", Types.VARCHAR));
            declareParameter(new SqlInOutParameter("p_logonid", Types.BIGINT));
            declareParameter(new SqlParameter("p_acctnum", Types.BIGINT));
            declareParameter(new SqlParameter("p_userid", Types.VARCHAR));
            declareParameter(new SqlParameter("p_email", Types.VARCHAR));
            declareParameter(new SqlParameter("p_pwd", Types.VARCHAR));
            declareParameter(new SqlParameter("p_logonstatus", Types.VARCHAR));
            declareParameter(new SqlParameter("p_lastname", Types.VARCHAR));
            declareParameter(new SqlParameter("p_firstname", Types.VARCHAR));
            declareParameter(new SqlParameter("p_stateRegistered", Types.VARCHAR));
            declareParameter(new SqlParameter("p_emailalt", Types.VARCHAR));
            declareParameter(new SqlParameter("p_question1", Types.VARCHAR));
            declareParameter(new SqlParameter("p_answer1", Types.VARCHAR));
            declareParameter(new SqlParameter("p_question2", Types.VARCHAR));
            declareParameter(new SqlParameter("p_answer2", Types.VARCHAR));
            declareParameter(new SqlParameter("p_question3", Types.VARCHAR));
            declareParameter(new SqlParameter("p_answer3", Types.VARCHAR));
            declareParameter(new SqlParameter("p_ip", Types.VARCHAR));
            declareParameter(new SqlParameter("p_resetID", Types.VARCHAR));
            declareParameter(new SqlParameter("p_emailmsgtype", Types.VARCHAR));
            declareParameter(new SqlParameter("p_leadsource", Types.VARCHAR));
            declareParameter(new SqlParameter("p_cid", Types.VARCHAR));
            declareParameter(new SqlParameter("p_advisor", Types.VARCHAR));
            declareParameter(new SqlParameter("p_rep", Types.VARCHAR));
            declareParameter(new SqlParameter("p_access", Types.VARCHAR));
            declareParameter(new SqlParameter("p_fullname", Types.VARCHAR));
            break;
         case 1:
            declareParameter(new SqlParameter("p_logonid", Types.BIGINT));
            declareParameter(new SqlParameter("p_userid", Types.VARCHAR));
            declareParameter(new SqlParameter("p_email", Types.VARCHAR));
            break;
         case 2:
            declareParameter(new SqlOutParameter("message", Types.VARCHAR));
            declareParameter(new SqlParameter("p_logonid", Types.BIGINT));
            declareParameter(new SqlParameter("p_userid", Types.VARCHAR));
            declareParameter(new SqlParameter("p_email", Types.VARCHAR));
            declareParameter(new SqlParameter("p_pwd", Types.VARCHAR));
            break;
         case 3:
            declareParameter(new SqlParameter("p_logonid", Types.BIGINT));
            declareParameter(new SqlParameter("p_question1", Types.VARCHAR));
            declareParameter(new SqlParameter("p_answer1", Types.VARCHAR));
            declareParameter(new SqlParameter("p_question2", Types.VARCHAR));
            declareParameter(new SqlParameter("p_answer2", Types.VARCHAR));
            declareParameter(new SqlParameter("p_question3", Types.VARCHAR));
            declareParameter(new SqlParameter("p_answer3", Types.VARCHAR));
            declareParameter(new SqlParameter("p_logonstatus", Types.VARCHAR));
            break;
         case 4:
            declareParameter(new SqlParameter("p_userid", Types.VARCHAR));
            declareParameter(new SqlParameter("p_pwd", Types.VARCHAR));
            break;
         case 5: // sp_user_access_add_mod
            declareParameter(new SqlParameter("p_addmodflag", Types.VARCHAR));
            declareParameter(new SqlParameter("p_loginid", Types.BIGINT));
            declareParameter(new SqlParameter("p_acctnum", Types.BIGINT));
            declareParameter(new SqlParameter("p_functionid", Types.VARCHAR));
            declareParameter(new SqlParameter("p_role", Types.VARCHAR));
            declareParameter(new SqlParameter("p_privileges", Types.VARCHAR));
         default:
            break;
      }
      compile();
   }


   public Map addUser(String action, UserData data)
   {


      Map inputMap = new HashMap();

      inputMap.put("p_addmod", action);
      if (data.getLogonID() == null) {
         inputMap.put("p_logonid", 0L);
      }
      else {
         inputMap.put("p_logonid", data.getLogonID());
      }

      if (data.getAcctnum() == null) {
         inputMap.put("p_acctnum", 0L); // If acctnum is null or 0L then don't link account.  Else create a linked account.
      }
      else {
         inputMap.put("p_acctnum", data.getAcctnum()); // If acctnum is null or 0L then don't link account.  Else create a linked account.
      }
      inputMap.put("p_userid", data.getUserID());
      inputMap.put("p_email", data.getEmail());
      inputMap.put("p_pwd", data.getPassword());
      if (data.getLogonstatus() == null)
      {
         inputMap.put("p_logonstatus", "T");
      }
      else
      {
         inputMap.put("p_logonstatus", data.getLogonstatus());
      }

      inputMap.put("p_lastname", data.getLastName());
      inputMap.put("p_firstname", data.getFirstName());


      inputMap.put("p_stateRegistered", data.getStateCode());

      inputMap.put("p_emailalt", data.getEmailalt());

      inputMap.put("p_question1", data.getQ1());
      inputMap.put("p_answer1", data.getAns1());
      inputMap.put("p_question2", data.getQ2());
      inputMap.put("p_answer2", data.getAns2());
      inputMap.put("p_question3", data.getQ3());
      inputMap.put("p_answer3", data.getAns3());
      inputMap.put("p_ip", data.getIp());
      inputMap.put("p_resetID", data.getResetID());
      inputMap.put("p_emailmsgtype", data.getEmailmsgtype());
      inputMap.put("p_leadsource", data.getLeadsource());
      inputMap.put("p_cid", data.getCid());
      inputMap.put("p_advisor", data.getAdvisor());
      inputMap.put("p_rep", data.getRep());
      inputMap.put("p_access", data.getAccess());
      inputMap.put("p_fullname",data.getRegfullname());

      return super.execute(inputMap);
   }

   public Map selectUserProfile(UserData data)
   {
      Map inputMap = new HashMap();
      inputMap.put("p_logonid", data.getLogonID());
      inputMap.put("p_userid", data.getUserID());
      inputMap.put("p_email", data.getEmail());
      return super.execute(inputMap);
   }

   public Map selectUserProfile(String userid)
   {
      Map inputMap = new HashMap();
      inputMap.put("p_logonid", null);
      inputMap.put("p_userid", userid);
      inputMap.put("p_email", null);
      return super.execute(inputMap);
   }

   public Map updateUserProfile(UserData data)
   {
      Map inputMap = new HashMap();
      inputMap.put("message", "");
      inputMap.put("p_logonid", data.getLogonID());
      inputMap.put("p_userid", data.getUserID());
      inputMap.put("p_email", data.getEmailID());
      inputMap.put("p_pwd", data.getPassword());
      return super.execute(inputMap);
   }

   public Map updateSecurityQuestions(UserData data)
   {
      Map inputMap = new HashMap();
      inputMap.put("p_logonid", data.getLogonID());
      inputMap.put("p_question1", data.getQ1());
      inputMap.put("p_answer1", data.getAns1());
      inputMap.put("p_question2", data.getQ2());
      inputMap.put("p_answer2", data.getAns2());
      inputMap.put("p_question3", data.getQ3());
      inputMap.put("p_answer3", data.getAns3());
      inputMap.put("p_logonstatus", data.getLogonstatus());
      return super.execute(inputMap);
   }

   public Map updatePassword(String userID, String password)
   {
      Map inputMap = new HashMap();
      inputMap.put("p_userid", userID);
      inputMap.put("p_pwd", password);
      return super.execute(inputMap);
   }

   public Map addUserAccess(UserData data)
   {
      Map inputMap = new HashMap();

      inputMap.put("p_addmod", null);
      inputMap.put("p_logonid", data.getLogonID());
      inputMap.put("p_acctnum", data.getAcctnum()); // If acctnum is null or 0L then don't link account.  Else create a linked account.
      inputMap.put("p_functionid", "A");
      inputMap.put("p_role", "OWNER");
      inputMap.put("p_privileges", null);
      return super.execute(inputMap);
   }

}

