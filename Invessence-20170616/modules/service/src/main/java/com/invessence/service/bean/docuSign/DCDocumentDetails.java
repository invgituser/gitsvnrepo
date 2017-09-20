package com.invessence.service.bean.docuSign;

import java.util.*;

/**
 * Created by abhangp on 9/8/2016.
 */
public class DCDocumentDetails
{

   private String company;
   private String service;
   private String docCode;
   private String docName;
   private String docType;
   private String fileName;
   private String editable;
   private String status;
   private String remark;

   private Map<String, List<DCDocumentMapping>> dcDocumentMappings;

   public Map<String, List<DCDocumentMapping>> getDcDocumentMappings()
   {
      return dcDocumentMappings;
   }

   public void setDcDocumentMappings(Map<String, List<DCDocumentMapping>> dcDocumentMappings)
   {
      this.dcDocumentMappings = dcDocumentMappings;
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

   public String getFileName()
   {
      return fileName;
   }

   public void setFileName(String fileName)
   {
      this.fileName = fileName;
   }

   public void setService(String service)
   {
      this.service = service;
   }

   public String getDocCode()
   {
      return docCode;
   }

   public void setDocCode(String docCode)
   {
      this.docCode = docCode;
   }

   public String getDocName()
   {
      return docName;
   }

   public void setDocName(String docName)
   {
      this.docName = docName;
   }

   public String getDocType()
   {
      return docType;
   }

   public void setDocType(String docType)
   {
      this.docType = docType;
   }

   public String getEditable()
   {
      return editable;
   }

   public void setEditable(String editable)
   {
      this.editable = editable;
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

   @Override
   public String toString()
   {
      return "DCDocumentDetails{" +
         "company='" + company + '\'' +
         ", service='" + service + '\'' +
         ", docCode='" + docCode + '\'' +
         ", docName='" + docName + '\'' +
         ", docType='" + docType + '\'' +
         ", editable='" + editable + '\'' +
         ", status='" + status + '\'' +
         ", remark='" + remark + '\'' +
         '}';
   }
}
