package com.invmodel.rebalance;

import java.util.*;

import com.invmodel.dao.invdb.InvModelDAO;
import com.invmodel.rebalance.data.SecurityTLHData;


public class TLHSecurityCollection
{
   private static TLHSecurityCollection instance = null;
   private InvModelDAO thlDAO = new InvModelDAO();
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

   public Map<String, SecurityTLHData> loadTLHReverseSecurities() {
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

   public Map<String, SecurityTLHData> loadTLHSecurities() {
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
