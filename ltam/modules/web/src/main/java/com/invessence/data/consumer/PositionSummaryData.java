package com.invessence.data.consumer;

import java.io.Serializable;
import java.util.*;

import com.invessence.converter.JavaUtil;

/**
 * Created with IntelliJ IDEA.
 * User: pichaimanir
 * Date: 8/19/13
 * Time: 4:50 PM
 * To change this template use File | Settings | File Templates.
 */
public class PositionSummaryData implements Serializable
{

   Map<String, PositionData> positionMap;
   Map<String, PositionData> assetMap;
   Boolean hasClientData;
   String name, clientAccountID, securitydesc, repName;

   private JavaUtil jutil = new JavaUtil();

   public PositionSummaryData()
   {
      positionMap = new LinkedHashMap<String, PositionData>();
      assetMap = new LinkedHashMap<String, PositionData>();
      hasClientData = false;
   }

   public Map<String, PositionData> getPositionMap()
   {
      return positionMap;
   }

   public Map<String, PositionData> getAssetMap()
   {
      return assetMap;
   }

   public String getName()
   {
      return name;
   }

   public void setName(String name)
   {
      this.name = name;
   }

   public String getClientAccountID()
   {
      return clientAccountID;
   }

   public String getDisplayClientAccountID()
   {
      return (jutil.getDisplayHiddenID(clientAccountID));
   }


   public void setClientAccountID(String clientAccountID)
   {
      this.clientAccountID = clientAccountID;
   }

   public String getSecuritydesc()
   {
      return securitydesc;
   }

   public void setSecuritydesc(String securitydesc)
   {
      this.securitydesc = securitydesc;
   }

   public String getRepName()
   {
      return repName;
   }

   public void setRepName(String repName)
   {
      this.repName = repName;
   }

   private void addClientData(String clientAccountID, String name, String description, String repName) {
       if (! hasClientData) {
          this.name = name;
          this.clientAccountID = clientAccountID;
          this.securitydesc = description;
          this.repName = repName;
       }
   }

   public void addPosition(Integer sortorder, Long acctnum,
                      String clientAccountID, String name, String repName, String description,
                      String theme, String dateOpened, String assetClass, String assetname,
                      String subasset, String displayname, String color, String status,
                      Double allocation, String reportDate, String side, Double quantity,
                      Double costBasisPrice, Double costBasisMoney,
                      Double markPrice, Double positionValue) {

      try {
         if (positionMap == null) {
            positionMap = new LinkedHashMap<String, PositionData>();
         }

         addClientData(clientAccountID, name, description, repName);

         PositionData data;
         if (positionMap.containsKey(subasset)) {
            data = positionMap.get(subasset);
            data.setPositionValue(data.getPositionValue() + positionValue);
            data.setAllocation(data.getAllocation() + allocation);
         }
         else {
            data = new PositionData( sortorder,  acctnum,
                                     clientAccountID,  name,  repName,
                                     theme,  dateOpened,  assetClass,  assetname,
                                     subasset,  displayname,  color,  status,
                                     allocation,  reportDate,  side,  quantity,
                                     costBasisPrice,  costBasisMoney,
                                     markPrice,  positionValue);
            positionMap.put(subasset, data);
         }

         addAsset(sortorder,  acctnum,
                  clientAccountID,  name,  repName,
                  theme,  dateOpened,  assetClass,  assetname,
                  subasset,  displayname,  color,  status,
                  allocation,  reportDate,  side,  quantity,
                  costBasisPrice,  costBasisMoney,
                  markPrice,  positionValue);

      }
      catch (Exception ex) {

      }

   }

   public void addAsset(Integer sortorder, Long acctnum,
                      String clientAccountID, String name, String repName,
                      String theme, String dateOpened, String assetClass, String assetname,
                      String subasset, String displayname, String color, String status,
                      Double allocation, String reportDate, String side, Double quantity,
                      Double costBasisPrice, Double costBasisMoney,
                      Double markPrice, Double positionValue) {


      try {
         if (assetMap == null) {
            assetMap = new LinkedHashMap<String, PositionData>();
         }

         PositionData data;
         if (assetMap.containsKey(assetClass)) {
            data = assetMap.get(assetClass);
            data.setPositionValue(data.getPositionValue() + positionValue);
            data.setAllocation(data.getAllocation() + allocation);
         }
         else {
            data = new PositionData( sortorder,  acctnum,
                                     clientAccountID,  name,  repName,
                                     theme,  dateOpened,  assetClass,  assetname,
                                     subasset,  displayname,  color,  status,
                                     allocation,  reportDate,  side,  quantity,
                                     costBasisPrice,  costBasisMoney,
                                     markPrice,  positionValue);
            assetMap.put(assetClass, data);
         }
      }
      catch (Exception ex) {

      }
   }

   public ArrayList<PositionData> getPositionList() {
      ArrayList<PositionData> arrayList = new ArrayList<PositionData>();

      try {
         if (positionMap != null) {
            for (String key : positionMap.keySet()) {
               arrayList.add(positionMap.get(key));
            }
         }

         return arrayList;
      }
      catch (Exception ex) {

      }

      return arrayList;
   }

   public ArrayList<PositionData> getAssetList() {
      ArrayList<PositionData> arrayList = new ArrayList<PositionData>();

      try {
         if (assetMap != null) {
            for (String key : assetMap.keySet()) {
               arrayList.add(assetMap.get(key));
            }
         }

         return arrayList;
      }
      catch (Exception ex) {

      }

      return arrayList;
   }
}
