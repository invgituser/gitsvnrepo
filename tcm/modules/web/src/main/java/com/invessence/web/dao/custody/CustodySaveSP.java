package com.invessence.web.dao.custody;

import java.util.*;
import javax.sql.DataSource;

import com.invessence.web.data.custody.*;
import org.springframework.jdbc.object.StoredProcedure;

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

   public Map td_saveAccountOwner(AcctOwnersDetails data) {
      Map inputMap = new HashMap();
      return super.execute(inputMap);
   }
}
