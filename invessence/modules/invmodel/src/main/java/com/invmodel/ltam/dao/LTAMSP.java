package com.invmodel.ltam.dao;


import java.sql.Types;
import java.util.*;
import javax.sql.DataSource;

import com.invmodel.rebalance.data.TradeData;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;


public class LTAMSP extends StoredProcedure
{

   public LTAMSP(DataSource datasource, String storedProcName, int process, int which)
   {
      super(datasource, storedProcName);
      switch (process) {
         default: // All others with no args.
      }
      compile();
   }

   @SuppressWarnings({"unchecked", "rawtypes"})
   public Map loadLTAMThemes()
   {
      Map inputMap = new HashMap();
      return super.execute(inputMap);
   }

   @SuppressWarnings({"unchecked", "rawtypes"})
   public Map loadLTAMAsset()
   {
      Map inputMap = new HashMap();
      return super.execute(inputMap);
   }

   @SuppressWarnings({"unchecked", "rawtypes"})
   public Map loadLTAMPortfolio()
   {
      Map inputMap = new HashMap();
      return super.execute(inputMap);
   }

   @SuppressWarnings({"unchecked", "rawtypes"})
   public Map loadLTAMPerformance()
   {
      Map inputMap = new HashMap();
      return super.execute(inputMap);
   }
}
