package com.invessence.web.dao.custody;

import java.sql.Types;
import java.util.*;
import javax.sql.DataSource;

import com.invessence.web.data.custody.*;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.object.StoredProcedure;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 8/20/16
 * Time: 11:26 AM
 * To change this template use File | Settings | File Templates.
 */
public class CustodySaveSP extends StoredProcedure
{

   public CustodySaveSP(DataSource datasource, String spname)
   {
      super(datasource, spname);
      compile();
   }

   public CustodySaveSP(DataSource datasource, String spname, Integer mode)
   {
      super(datasource, spname);
      switch (mode) {
         case 0:
         case 1:
         case 2:
         case 3:
         case 4:
         case 5:
         case 6:
         case 7:
         case 8:

            declareParameter(new SqlParameter("p_acctnum", Types.BIGINT));
            declareParameter(new SqlParameter("p_beneId", Types.INTEGER));
            declareParameter(new SqlParameter("p_beneFirstName", Types.VARCHAR));
            declareParameter(new SqlParameter("p_beneMidInitial", Types.VARCHAR));
            declareParameter(new SqlParameter("p_beneLastName", Types.VARCHAR));
            declareParameter(new SqlParameter("p_beneSSN", Types.VARCHAR));
            declareParameter(new SqlParameter("p_beneDOB", Types.DATE));
            declareParameter(new SqlParameter("p_beneRel", Types.VARCHAR));
            declareParameter(new SqlParameter("p_typeOfBeneficiary", Types.VARCHAR));
            declareParameter(new SqlParameter("p_perStripes", Types.VARCHAR));
            declareParameter(new SqlParameter("p_sharePerc", Types.DOUBLE));
            declareParameter(new SqlParameter("p_createdBy", Types.VARCHAR));

         case 9:
            break;
         default:
            break;
      }
      compile();
   }

   public Map td_saveRequest(TDRequest data) {
      Map inputMap = new HashMap();
      return super.execute(inputMap);
   }

   public Map td_saveAccountDetail(TDAcctdetails data) {
      Map inputMap = new HashMap();
      return super.execute(inputMap);
   }

   public Map td_saveAccountOwner(AcctOwnersDetails data)
   {
      Map inputMap = new HashMap();
      return super.execute(inputMap);
   }

   public Map td_saveBenefiaciaryDetails(BenefiaciaryDetails data) {



      Map inputMap = new HashMap();
      inputMap.put("p_acctnum", data.getAcctnum());
      inputMap.put("p_beneId", data.getBeneId());
      inputMap.put("p_beneFirstName", data.getBeneFirstName());
      inputMap.put("p_beneMidInitial", data.getBeneMidInitial());
      inputMap.put("p_beneLastName", data.getBeneLastName());
      inputMap.put("p_beneSSN", data.getBeneSSN());
      inputMap.put("p_beneDOB", "2001-01-01");
      inputMap.put("p_beneRel", data.getBeneRel());
      inputMap.put("p_typeOfBeneficiary", data.getTypeOfBeneficiary());
      inputMap.put("p_perStripes", data.getPerStripes());
      inputMap.put("p_sharePerc", data.getSharePerc());
      inputMap.put("p_createdBy", data.getCreatedBy());

      return super.execute(inputMap);
   }
}
