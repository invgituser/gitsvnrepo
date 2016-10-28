package com.invmodel.dao.invdb;


import java.util.*;
import javax.sql.DataSource;

import org.springframework.jdbc.object.StoredProcedure;


public class FixedModelSP extends StoredProcedure
{

   public FixedModelSP(DataSource datasource, String storedProcName, int process, int which)
   {
      super(datasource, storedProcName);
      switch (process) {
         default: // All others with no args.
      }
      compile();
   }

   @SuppressWarnings({"unchecked", "rawtypes"})
   public Map loadThemes()
   {
      Map inputMap = new HashMap();
      return super.execute(inputMap);
   }

   @SuppressWarnings({"unchecked", "rawtypes"})
   public Map loadAsset()
   {
      Map inputMap = new HashMap();
      return super.execute(inputMap);
   }

   @SuppressWarnings({"unchecked", "rawtypes"})
   public Map loadPortfolio()
   {
      Map inputMap = new HashMap();
      return super.execute(inputMap);
   }

   @SuppressWarnings({"unchecked", "rawtypes"})
   public Map loadPerformance()
   {
      Map inputMap = new HashMap();
      return super.execute(inputMap);
   }

   @SuppressWarnings({"unchecked", "rawtypes"})
   public Map loadFixedProjectionChart()
   {
      Map inputMap = new HashMap();
      return super.execute(inputMap);
   }
}
