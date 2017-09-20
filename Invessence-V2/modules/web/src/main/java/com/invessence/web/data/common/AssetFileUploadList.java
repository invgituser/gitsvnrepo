package com.invessence.web.data.common;

/**
 * Created by sagar on 7/18/2017.
 */
public class AssetFileUploadList
{
   private String templatename;
   private String fileName;
   private String modelName;
   private String fileType;
   private Long advisorid;
   private String validation;
   private String tableName;
   private String fileDtl;
   private String projApproved;
   private String perfApproved;
   private String prodTemplateName;
   private String savedTemplateName;

   public AssetFileUploadList(String modelName,
                              String templatename,
                              String prodTemplateName,
                              String savedTemplateName)
   {
      this.modelName = modelName;
      this.templatename = templatename;
      this.prodTemplateName = prodTemplateName;
      this.savedTemplateName = savedTemplateName;
   }


   public AssetFileUploadList(String templatename,
                              String fileName,
                              String modelName,
                              String fileType,
                              Long advisorid,
                              String validation,
                              String tableName,
                              String fileDtl,
                              String projApproved,
                              String perfApproved)
   {
      this.templatename = templatename;
      this.fileName = fileName;
      this.modelName = modelName;
      this.fileType = fileType;
      this.advisorid = advisorid;
      this.validation = validation;
      this.tableName=tableName;
      this.fileDtl=fileDtl;
      this.projApproved=projApproved;
      this.perfApproved=perfApproved;
   }


   public String getTemplatename()
   {
      return templatename;
   }

   public void setTemplatename(String templatename)
   {
      this.templatename = templatename;
   }

   public String getFileName()
   {
      return fileName;
   }

   public void setFileName(String fileName)
   {
      this.fileName = fileName;
   }

   public String getModelName()
   {
      return modelName;
   }

   public void setModelName(String modelName)
   {
      this.modelName = modelName;
   }

   public String getFileType()
   {
      return fileType;
   }

   public void setFileType(String fileType)
   {
      this.fileType = fileType;
   }

   public Long getAdvisorid()
   {
      return advisorid;
   }

   public void setAdvisorid(Long advisorid)
   {
      this.advisorid = advisorid;
   }

   public String getValidation()
   {
      return validation;
   }

   public void setValidation(String validation)
   {
      this.validation = validation;
   }

   public String getTableName()
   {
      return tableName;
   }

   public void setTableName(String tableName)
   {
      this.tableName = tableName;
   }


   public String getFileDtl()
   {
      return fileDtl;
   }

   public void setFileDtl(String fileDtl)
   {
      this.fileDtl = fileDtl;
   }

   public String getProjApproved()
   {
      return projApproved;
   }

   public void setProjApproved(String projApproved)
   {
      this.projApproved = projApproved;
   }

   public String getPerfApproved()
   {
      return perfApproved;
   }

   public void setPerfApproved(String perfApproved)
   {
      this.perfApproved = perfApproved;
   }

   public String getSavedTemplateName()
   {
      return savedTemplateName;
   }

   public void setSavedTemplateName(String savedTemplateName)
   {
      this.savedTemplateName = savedTemplateName;
   }

   public String getProdTemplateName()
   {
      return prodTemplateName;
   }

   public void setProdTemplateName(String prodTemplateName)
   {
      this.prodTemplateName = prodTemplateName;
   }
}
