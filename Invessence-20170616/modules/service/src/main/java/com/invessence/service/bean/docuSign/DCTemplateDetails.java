package com.invessence.service.bean.docuSign;

import java.util.*;

/**
 * Created by abhangp on 8/16/2016.
 */
public class DCTemplateDetails
{
   private String mode;
   private String company;
   private String service;
   private String tempCode;
   private String tempId;
   private String tempName;
   private String status;
   private String remark;
   private String configFile;
   private String authRequired;
   private Map<String, List<DCTemplateMapping>>dcTemplateMappings;

   @Override
   public String toString()
   {
      return "DCTemplateDetails{" +
         "mode='" + mode + '\'' +
         ", company='" + company + '\'' +
         ", service='" + service + '\'' +
         ", tempCode='" + tempCode + '\'' +
         ", tempId='" + tempId + '\'' +
         ", tempName='" + tempName + '\'' +
         ", status='" + status + '\'' +
         ", remark='" + remark + '\'' +
         ", authRequired='" + authRequired + '\'' +
         ", dcTemplateMappings=" + dcTemplateMappings +
         '}';
   }

   public DCTemplateDetails(String mode, String company, String service, String tempCode, String tempId, String tempName, String status, String remark, String authRequired)
   {
      this.mode = mode;
      this.company = company;
      this.service = service;
      this.tempCode = tempCode;
      this.tempId = tempId;
      this.tempName = tempName;
      this.status = status;
      this.remark = remark;
      this.authRequired = authRequired;
   }

   public String getAuthRequired()
   {
      return authRequired;
   }

   public void setAuthRequired(String authRequired)
   {
      this.authRequired = authRequired;
   }

   public String getConfigFile()
   {
      return configFile;
   }

   public void setConfigFile(String configFile)
   {
      this.configFile = configFile;
   }

   public Map<String, List<DCTemplateMapping>> getDcTemplateMappings()
   {
      return dcTemplateMappings;
   }

   public void setDcTemplateMappings(Map<String, List<DCTemplateMapping>> dcTemplateMappings)
   {
      this.dcTemplateMappings = dcTemplateMappings;
   }

   public String getMode()
   {
      return mode;
   }

   public void setMode(String mode)
   {
      this.mode = mode;
   }

   public String getCompany()
   {
      return company;
   }

   public void setCompany(String company)
   {
      this.company = company;
   }

   public String getService()
   {
      return service;
   }

   public void setService(String service)
   {
      this.service = service;
   }

   public String getTempCode()
   {
      return tempCode;
   }

   public void setTempCode(String tempCode)
   {
      this.tempCode = tempCode;
   }

   public String getTempId()
   {
      return tempId;
   }

   public void setTempId(String tempId)
   {
      this.tempId = tempId;
   }

   public String getTempName()
   {
      return tempName;
   }

   public void setTempName(String tempName)
   {
      this.tempName = tempName;
   }

   public String getStatus()
   {
      return status;
   }

   public void setStatus(String status)
   {
      this.status = status;
   }

   public String getRemark()
   {
      return remark;
   }

   public void setRemark(String remark)
   {
      this.remark = remark;
   }
}
