package com.invmodel.risk.data;

import java.util.*;

/**
 * Created by prashant on 11/9/2017.
 */
public class AdvisorRiskMaster
{
   String advisor;
   Map<String, AdvisorRiskMasterData> masterdata;
   Map<Integer, AdvisorRiskMapping> mappingdata;

   public AdvisorRiskMaster()
   {
      masterdata = new HashMap<String, AdvisorRiskMasterData>();
      mappingdata = new HashMap<Integer, AdvisorRiskMapping>();
   }

   public AdvisorRiskMaster(String advisor)
   {
      this.advisor = advisor;
      masterdata = new HashMap<String, AdvisorRiskMasterData>();
      mappingdata = new HashMap<Integer, AdvisorRiskMapping>();
   }

   public AdvisorRiskMaster(String advisor, Map<String, AdvisorRiskMasterData> data)
   {
      this.advisor = advisor;
      this.masterdata = data;
   }

   public String getAdvisor()
   {
      return advisor;
   }

   public void setAdvisor(String advisor)
   {
      this.advisor = advisor;
   }

   public void removeAdvisorMasterData(String advisor, String key) {
      if (key != null) {
         if (this.masterdata.containsKey(key))
            this.masterdata.remove(key);
      }
   }

   public void addAdvisorMasterData(String advisor, String key, AdvisorRiskMasterData masterdata) {
      if (masterdata == null) {
         removeAdvisorMasterData(advisor,key);
      }
      if (key != null) {
         this.masterdata.put(key,masterdata);
      }
   }

   public Map<String, AdvisorRiskMasterData> getAdvisorMasterdata(String advisor)
   {
      if (this.advisor.equalsIgnoreCase(advisor)) {
         return masterdata;
      }
      return null;
   }

   public void removeAdvisorMapping(String advisor, Integer riskNum) {
      if (riskNum != null) {
         if (this.mappingdata.containsKey(riskNum))
            this.mappingdata.remove(riskNum);
      }
   }

   public void addAdvisorMapping(String advisor, Integer riskNum, AdvisorRiskMapping mappingdata) {
      if (mappingdata == null) {
         removeAdvisorMapping(advisor,riskNum);
      }
      if (riskNum != null) {
         this.mappingdata.put(riskNum,mappingdata);
      }
   }

   public Map<Integer, AdvisorRiskMapping> getAdvisorMappings(String advisor)
   {
      if (this.advisor.equalsIgnoreCase(advisor)) {
         return mappingdata;
      }
      return null;
   }


}
