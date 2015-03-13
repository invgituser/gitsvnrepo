package com.invmodel.dao;

import java.sql.*;
import java.util.*;
import java.util.concurrent.locks.*;
import java.util.logging.Logger;

import com.invmodel.Const.InvConst;
import com.invmodel.dao.data.SecurityData;
import com.invmodel.inputData.CustomAllocation;
import com.invmodel.rebalance.data.SecurityTLHData;
import org.apache.commons.dbutils.DbUtils;


public class TLHSecurityCollection
{
   private static TLHSecurityCollection instance = null;
   private TaxHarvestingDAO thlDAO = new TaxHarvestingDAO();
   Map<String, SecurityTLHData> tlhSecurityMap = null;
   private Map<String, SecurityTLHData> tlhReverseSecMap = null;

   public static synchronized TLHSecurityCollection getInstance()
   {
      if (instance == null)
      {
         instance = new TLHSecurityCollection();
      }

      return instance;
   }

   private TLHSecurityCollection()
   {
      super();
      refreshDataFromDB();
   }


   public Map<String, SecurityTLHData> getTlhSecurityMap()
   {
      return tlhSecurityMap;
   }

   public Map<String, SecurityTLHData> getTlhReverseSecMap()
   {
      return tlhReverseSecMap;
   }

   public void refreshDataFromDB() {
      try {
         tlhSecurityMap = loadTLHSecurities();
         tlhReverseSecMap = loadTLHReverseSecurities();
      }
      catch (Exception ex) {
         ex.printStackTrace();
      }
   }

   public Map loadTLHReverseSecurities() {
      Map<String, SecurityTLHData> data = null;
      try
      {
         data = thlDAO.loadTLHReverseSecurities();
         return data;
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
      return (data);
   }

   public Map loadTLHSecurities() {
      Map<String, SecurityTLHData> data = null;
      try
      {
         data = thlDAO.loadTLHSecurities();
         return data;
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
      return (data);
   }

}
