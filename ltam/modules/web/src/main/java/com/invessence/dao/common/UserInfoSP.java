package com.invessence.dao.common;


import com.invessence.constant.Const;


import javax.sql.DataSource;

import com.invessence.data.common.UserData;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.object.StoredProcedure;

import java.sql.Types;
import java.util.*;

import org.springframework.jdbc.core.SqlParameter;

public class UserInfoSP extends StoredProcedure
{

   public UserInfoSP(DataSource datasource, String sp_name, Integer mode)
   {

      //super(datasource, "sp_login_add_mod");
      super(datasource, sp_name);
      switch (mode) {
         case 0:
            declareParameter(new SqlOutParameter(Const.LOGONID_PARAM, Types.BIGINT));
            declareParameter(new SqlParameter("p_userid", Types.VARCHAR));
            declareParameter(new SqlParameter("p_email", Types.VARCHAR));
            declareParameter(new SqlParameter("p_pwd", Types.VARCHAR));
            declareParameter(new SqlParameter("p_logonstatus", Types.VARCHAR));
            declareParameter(new SqlParameter("p_lastname", Types.VARCHAR));
            declareParameter(new SqlParameter("p_firstname", Types.VARCHAR));
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
            declareParameter(new SqlParameter("p_rep", Types.BIGINT));
            declareParameter(new SqlParameter("p_access", Types.BIGINT));
            break;

         case 1:
            declareParameter(new SqlParameter("p_logonid", Types.BIGINT));
            declareParameter(new SqlParameter("p_userid", Types.VARCHAR));
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
            break;
         case 4:
            declareParameter(new SqlParameter("p_userid", Types.VARCHAR));
            declareParameter(new SqlParameter("p_pwd", Types.VARCHAR));
            break;
         case 5:
            declareParameter(new SqlParameter("p_email", Types.VARCHAR));
            break;
         default:
            break;
      }


     compile();
   }


   public Map addUser(UserData data)
   {


      Map inputMap = new HashMap();

      inputMap.put("p_logonid", data.getLogonID());
      inputMap.put("p_userid", data.getUserID());
      inputMap.put("p_email", data.getEmail());
      inputMap.put("p_pwd", data.getPasswordEncrypted());
      inputMap.put("p_logonstatus", "T");

      inputMap.put("p_lastname", data.getLastName());
      inputMap.put("p_firstname", data.getFirstName());

      inputMap.put("p_question1", data.getQ1());
      inputMap.put("p_answer1", data.getAns1());
      inputMap.put("p_question2", data.getQ2());
      inputMap.put("p_answer2", data.getAns2());
      inputMap.put("p_question3", data.getQ3());
      inputMap.put("p_answer3", data.getAns3());
      inputMap.put("p_ip", data.getIp());
      inputMap.put("p_resetID", data.getResetID());
      inputMap.put("p_emailmsgtype", data.getEmailmsgtype());
      inputMap.put("p_leadsource", data.getLeadSource());
      inputMap.put("p_cid", data.getCid());
      inputMap.put("p_advisor", data.getAdvisor());
      inputMap.put("p_rep", data.getRep());
      inputMap.put("p_access", data.getAccess());

      return super.execute(inputMap);
   }

   public Map selectUserProfile(Long logonid, String userid)
   {
      Map inputMap = new HashMap();
      inputMap.put("p_logonid", logonid);
      inputMap.put("p_userid", userid);
      return super.execute(inputMap);
   }

   public Map updateUserProfile(UserData data)
   {
      Map inputMap = new HashMap();
      inputMap.put("message", "");
      inputMap.put("p_logonid", data.getLogonID());
      inputMap.put("p_userid", data.getUserID());
      inputMap.put("p_email", data.getEmail());
      inputMap.put("p_pwd", data.getPasswordEncrypted());
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
      return super.execute(inputMap);
   }

   public Map updatePassword(String userID, String password)
   {
      Map inputMap = new HashMap();
      inputMap.put("p_userid", userID);
      inputMap.put("p_pwd", password);
      return super.execute(inputMap);
   }

   public Map getUserByEmail(String email)
   {
      Map inputMap = new HashMap();
      inputMap.put("p_email", email);
      return super.execute(inputMap);
   }
}

