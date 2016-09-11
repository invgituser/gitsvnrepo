package com.invessence.web.dao.custody;

import java.util.*;
import javax.sql.DataSource;

import com.invessence.web.data.custody.TDMasterData;
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

   public Map getMasterData(Long acctnum) {
      Map inputMap = new HashMap();
      inputMap.put("p_acctnum", acctnum);
      return super.execute(inputMap);
   }
}
