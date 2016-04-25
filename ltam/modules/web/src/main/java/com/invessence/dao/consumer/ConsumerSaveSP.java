package com.invessence.dao.consumer;


import java.sql.Types;
import java.util.*;
import javax.sql.DataSource;

import com.invessence.data.common.AccountData;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;


public class ConsumerSaveSP extends StoredProcedure
{

   public ConsumerSaveSP(DataSource datasource, String sp_name, Integer mode)
   {
      super(datasource, sp_name);
      switch (mode) {
         case 0:   // SP: sp_update_addressInfo
            declareParameter(new SqlParameter("p_acctnum", Types.BIGINT));
            declareParameter(new SqlParameter("p_firstname", Types.VARCHAR));
            declareParameter(new SqlParameter("p_middle", Types.VARCHAR));
            declareParameter(new SqlParameter("p_lastname", Types.VARCHAR));
            declareParameter(new SqlParameter("p_add1", Types.VARCHAR));
            declareParameter(new SqlParameter("p_add2", Types.VARCHAR));
            declareParameter(new SqlParameter("p_add3", Types.VARCHAR));
            declareParameter(new SqlParameter("p_city", Types.VARCHAR));
            declareParameter(new SqlParameter("p_state", Types.VARCHAR));
            declareParameter(new SqlParameter("p_zip", Types.VARCHAR));
            declareParameter(new SqlParameter("p_country", Types.VARCHAR));
            declareParameter(new SqlParameter("p_primaryphone", Types.VARCHAR));
            declareParameter(new SqlParameter("p_secondaryphone", Types.VARCHAR));
            declareParameter(new SqlParameter("p_workphone", Types.VARCHAR));
            break;
         case 1:   // SP: sel_position
            break;
         case 2:   // SP: sel_position
            break;
         default:
      }
      compile();
   }

   @SuppressWarnings({"unchecked", "rawtypes"})
   public Map saveAddress(AccountData data)
   {
      Map inputMap = new HashMap();
      inputMap.put("p_acctnum", data.getAcctnum());
      inputMap.put("p_firstname", data.getFirstname());
      inputMap.put("p_middle", data.getMiddle());
      inputMap.put("p_lastname", data.getLastname());
      inputMap.put("p_add1", data.getMailAddrs1());
      inputMap.put("p_add2", data.getMailAddrs2());
      inputMap.put("p_add3", data.getMailAddrs3());
      inputMap.put("p_city", data.getMailCity());
      inputMap.put("p_state", data.getMailState());
      inputMap.put("p_zip", data.getMailZipCode());
      inputMap.put("p_country", data.getMailCountry());
      inputMap.put("p_primaryphone", data.getPrimaryPhoneNbr());
      inputMap.put("p_secondaryphone", data.getSecondayPhoneNbr());
      inputMap.put("p_workphone", data.getWorkPhoneNbr());
      return super.execute(inputMap);
   }




}
