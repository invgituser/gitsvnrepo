package com.invessence.custody.uob.dao;

import java.lang.reflect.Field;
import java.util.*;

import com.invessence.converter.SQLData;
import com.invessence.custody.dao.CustodySP;
import com.invessence.custody.uob.UOBDataMaster;
import com.invessence.custody.uob.data.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * Created by abhangp on 10/26/2017.
 */
@Repository("uobDaoImpl")
public class UOBDaoImpl implements UOBDao
{

      private static final Logger logger = Logger.getLogger(UOBDaoImpl.class);

   @Autowired
      JdbcTemplate webServiceJdbcTemplate;

      SQLData convert = new SQLData();

      @Override
      public void save()
      {

      }

   @Override
      public Object fetch(Long acctNum)
      {
         UOBDataMaster uobDataMaster=new UOBDataMaster();
         try
         {
            CustodySP serviceSP = new CustodySP(webServiceJdbcTemplate, "spao_uob_fetch_data", 0);
            try
            {
               Map outMap = serviceSP.fetchData(acctNum);
               if (outMap != null)
               {
                  ArrayList<LinkedHashMap<String, Object>> rows = (ArrayList<LinkedHashMap<String, Object>>) outMap.get("#result-set-1");

                  if(rows.size()>0)
                  {
                     getObjectFormCOLUMN(rows,uobDataMaster.getAccountDetails());
                  }
                  uobDataMaster.setAccountDetails(uobDataMaster.getAccountDetails());

                  rows =null;
                  rows = (ArrayList<LinkedHashMap<String, Object>>) outMap.get("#result-set-2");
                  if(rows.size()>0)
                  {
                     getObjectFormROW(rows,uobDataMaster.getAccountDetails().getAccountMiscDetails());
                  }

                  rows=null;
                  rows = (ArrayList<LinkedHashMap<String, Object>>) outMap.get("#result-set-3");
                  if(rows.size()>0)
                  {
                     Iterator<LinkedHashMap<String, Object>> itr = rows.iterator();

                     while (itr.hasNext())
                     {
                        StringBuilder fileRow = new StringBuilder();
                        LinkedHashMap<String, Object> map = itr.next();
                        OwnerDetails ownerDetails = new OwnerDetails();
                        getObjectFormCOLUMN(map, ownerDetails);
                        if(ownerDetails.getOwnership().equals("Individual"))
                        {
                           getOwnerDetails( ownerDetails, outMap);
                           uobDataMaster.setIndividualOwnersDetails(ownerDetails);
                        }else if(ownerDetails.getOwnership().equals("Joint"))
                        {
                           getOwnerDetails( ownerDetails, outMap);
                           uobDataMaster.setJointOwnersDetails(ownerDetails);
                        }
                     }
                  }
//                  rows=null;
//                  rows = (ArrayList<LinkedHashMap<String, Object>>) outMap.get("#result-set-3");
//                  if(rows.size()>0)
//                  {
//                     OwnerContactDetails ownerContactDetails=new OwnerContactDetails();
//                     getObjectFormROW(rows,ownerContactDetails,"acctOwnerId",ownerDetails.getAcctOwnerId());
//                     System.out.println(ownerContactDetails);
//                  }
//                  System.out.println("rows = " + rows);
                  return uobDataMaster;
               }
            }
            catch (Exception ex)
            {
               ex.printStackTrace();
            }
         }
         catch (Exception e)
         {
            logger.error("Issue while storing web request in DB :" + e.getMessage());
         }
         return uobDataMaster;
      }

   private void getOwnerDetails(OwnerDetails ownerDetails, Map map){

      ArrayList<LinkedHashMap<String, Object>> rows =null;
      rows = (ArrayList<LinkedHashMap<String, Object>>) map.get("#result-set-4");
      if(rows.size()>0)
      {
         getObjectFormROW(rows,ownerDetails.getOwnerContactDetails(),"acctOwnerId",ownerDetails.getAcctOwnerId());
      }

      rows =null;
      rows = (ArrayList<LinkedHashMap<String, Object>>) map.get("#result-set-5");
      if(rows.size()>0)
      {
         getObjectFormROW(rows,ownerDetails.getOwnersFinancialDetails(),"acctOwnerId",ownerDetails.getAcctOwnerId());
      }

      rows =null;
      rows = (ArrayList<LinkedHashMap<String, Object>>) map.get("#result-set-6");
      if(rows.size()>0)
      {
         getObjectFormROW(rows,ownerDetails.getOwnerRegularityDetails(),"acctOwnerId",ownerDetails.getAcctOwnerId());
      }

      rows =null;
      rows = (ArrayList<LinkedHashMap<String, Object>>) map.get("#result-set-7");
      if(rows.size()>0)
      {
        getObjectFormROW(rows,ownerDetails.getOwnerMiscDetails(),"acctOwnerId",ownerDetails.getAcctOwnerId());
      }

      rows =null;
      rows = (ArrayList<LinkedHashMap<String, Object>>) map.get("#result-set-8");
      if(rows.size()>0)
      {
         getObjectFormROW(rows,ownerDetails.getOwnerIdentificationDetails(),"acctOwnerId",ownerDetails.getAcctOwnerId());
      }

      rows =null;
      rows = (ArrayList<LinkedHashMap<String, Object>>) map.get("#result-set-9");
      if(rows.size()>0)
      {
         getObjectFormROW(rows,ownerDetails.getOwnerCitizenshipDetails(),"acctOwnerId",ownerDetails.getAcctOwnerId());
      }
   }

   public static Object getInstanceValue(final Object classInstance, final String fieldName) throws SecurityException, NoSuchFieldException, ClassNotFoundException, IllegalArgumentException, IllegalAccessException
   {

      // Get the private field
      final Field field = classInstance.getClass().getDeclaredField(fieldName);
      // Allow modification on the field
      field.setAccessible(true);
      // Return the Obect corresponding to the field
      return field.get(classInstance);

   }

   public static void setInstanceValue(final Object classInstance, final String fieldName, final Object value) throws SecurityException, NoSuchFieldException, ClassNotFoundException, IllegalArgumentException, IllegalAccessException
   {

      // Get the private field
      final Field field = classInstance.getClass().getDeclaredField(fieldName);
      // Allow modification on the field
      field.setAccessible(true);
      // Return the Obect corresponding to the field
      field.set(classInstance,value);

   }

   private void getObjectFormROW(ArrayList<LinkedHashMap<String, Object>> dbRows, Object object, String column, Object value)
   {
      Boolean isDetailsAvailable=false;
      Iterator<LinkedHashMap<String, Object>> itr = dbRows.iterator();

      while (itr.hasNext())
      {
         StringBuilder fileRow = new StringBuilder();
         LinkedHashMap<String, Object> map = itr.next();
//         System.out.println(map.get("name") + ":" + map.get("value"));
//         for (Map.Entry<String, Object> entry : map.entrySet()) {
//            System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue());
         try
         {
            if(map.get(column).equals(value))
            {
               isDetailsAvailable = true;
//               System.out.println(" column = [" + column + "], value = [" + value + "]");
               setInstanceValue(object, map.get("name").toString(), map.get("value"));
            }
         }
         catch (NoSuchFieldException e)
         {
            logger.error(e.getMessage());
//               e.printStackTrace();
         }
         catch (ClassNotFoundException e)
         {
            logger.error(e.getMessage());
//               e.printStackTrace();
         }
         catch (IllegalAccessException e)
         {
            logger.error(e.getMessage());
//               e.printStackTrace();
         }
//         }

      }
//      if(isDetailsAvailable.equals(false)){
//         object=null;
//         System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
//
//      }
   }

   private void getObjectFormROW(ArrayList<LinkedHashMap<String, Object>> dbRows, Object object){

      Iterator<LinkedHashMap<String, Object>> itr = dbRows.iterator();

      while (itr.hasNext())
      {
         StringBuilder fileRow=new StringBuilder();
         LinkedHashMap<String, Object> map = itr.next();
         System.out.println(map.get("name")+":"+map.get("value"));
//         for (Map.Entry<String, Object> entry : map.entrySet()) {
//            System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue());
            try
            {
               setInstanceValue(object,map.get("name").toString(),map.get("value"));
            }
            catch (NoSuchFieldException e)
            {
               logger.error(e.getMessage());
//               e.printStackTrace();
            }
            catch (ClassNotFoundException e)
            {
               logger.error(e.getMessage());
//               e.printStackTrace();
            }
            catch (IllegalAccessException e)
            {
               logger.error(e.getMessage());
//               e.printStackTrace();
            }
//         }

      }
   }


      private void getObjectFormCOLUMN(ArrayList<LinkedHashMap<String, Object>> dbRow, Object object){
         Iterator<LinkedHashMap<String, Object>> itr = dbRow.iterator();

         while (itr.hasNext())
         {
            StringBuilder fileRow = new StringBuilder();
            LinkedHashMap<String, Object> map = itr.next();

            for (Map.Entry<String, Object> entry : map.entrySet())
            {
               System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue());
               try
               {
                  setInstanceValue(object, entry.getKey(), entry.getValue());
               }
               catch (NoSuchFieldException e)
               {
                  logger.error(e.getMessage());
//               e.printStackTrace();
               }
               catch (ClassNotFoundException e)
               {
                  logger.error(e.getMessage());
//               e.printStackTrace();
               }
               catch (IllegalAccessException e)
               {
                  logger.error(e.getMessage());
//               e.printStackTrace();
               }

            }
         }
   }

   private void getObjectFormCOLUMN(LinkedHashMap<String, Object> dbRow, Object object){


         for (Map.Entry<String, Object> entry : dbRow.entrySet()) {
            System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue());
            try
            {
               setInstanceValue(object,entry.getKey(),entry.getValue());
            }
            catch (NoSuchFieldException e)
            {
               logger.error(e.getMessage());
//               e.printStackTrace();
            }
            catch (ClassNotFoundException e)
            {
               logger.error(e.getMessage());
//               e.printStackTrace();
            }
            catch (IllegalAccessException e)
            {
               logger.error(e.getMessage());
//               e.printStackTrace();
            }

      }
   }
}
