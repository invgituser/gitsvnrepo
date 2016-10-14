package com.invessence.web.dao.custody;

import java.sql.Types;
import java.util.*;
import javax.sql.DataSource;

import com.invessence.web.data.custody.TDMasterData;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.object.StoredProcedure;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 8/20/16
 * Time: 11:27 AM
 * To change this template use File | Settings | File Templates.
 */
@SuppressWarnings({"unchecked", "DuplicateStringLiteralInspection"})
public class CustodyListSP extends StoredProcedure
{

   public CustodyListSP(DataSource datasource, String spname)
   {
      super(datasource, spname);
      compile();
   }

   public CustodyListSP(DataSource datasource, String spname, Integer mode)
   {
      super(datasource, spname);
      switch (mode) {
         case 0:
            declareParameter(new SqlParameter("p_acctnum", Types.BIGINT));
            break;
         case 1:
            declareParameter(new SqlParameter("p_acctnum", Types.BIGINT));
            break;
         case 2:
            declareParameter(new SqlParameter("p_acctnum", Types.BIGINT));
            break;
         case 3:
            declareParameter(new SqlParameter("p_acctnum", Types.BIGINT));
            break;
         case 4:
            declareParameter(new SqlParameter("p_acctnum", Types.BIGINT));
            break;
         case 5:
            declareParameter(new SqlParameter("p_acctnum", Types.BIGINT));
            break;
         case 6:
            declareParameter(new SqlParameter("p_acctnum", Types.BIGINT));
            break;
         case 7:
         case 8:
         case 9:
            break;
         default:
            break;
      }
      compile();
   }

   public Map getTDAccountHolder(Long acctnum) {
      Map inputMap = new HashMap();
      inputMap.put("p_acctnum", acctnum);
      return super.execute(inputMap);
   }

   public Map getTDEmployment(Long acctnum) {
      Map inputMap = new HashMap();
      inputMap.put("p_acctnum", acctnum);
      return super.execute(inputMap);
   }


   public Map getTDBeneficiary(Long acctnum) {
      Map inputMap = new HashMap();
      inputMap.put("p_acctnum", acctnum);
      return super.execute(inputMap);
   }
   public Map getTDACAT(Long acctnum) {
      Map inputMap = new HashMap();
      inputMap.put("p_acctnum", acctnum);
      return super.execute(inputMap);
   }
   public Map getTDTRF(Long acctnum) {
      Map inputMap = new HashMap();
      inputMap.put("p_acctnum", acctnum);
      return super.execute(inputMap);
   }

   public Map getTDAccountDetails(Long acctnum) {
      Map inputMap = new HashMap();
      inputMap.put("p_acctnum", acctnum);
      return super.execute(inputMap);
   }
   public Map getTDRequestType(Long acctnum) {
      Map inputMap = new HashMap();
      inputMap.put("p_acctnum", acctnum);
      return super.execute(inputMap);
   }
}
