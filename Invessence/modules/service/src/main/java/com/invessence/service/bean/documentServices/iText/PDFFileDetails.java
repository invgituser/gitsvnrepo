package com.invessence.service.bean.documentServices.iText;

/**
 * Created by abhangp on 11/16/2017.
 */
public class PDFFileDetails
{
   private String vendor;
   private String fileName;
   private String fileId;
   private String fileExtension;
   private String description;
   private String active;
   private String fileNameAppender;
   private String appenderType;
   private String appenderFormat;
   private String available;
   private String sourceDir;
   private String uploadDir;
   private String isPwdProtected;
   private String pwdRules;

   public PDFFileDetails(String vendor, String fileName, String fileId, String fileExtension, String description, String active, String fileNameAppender, String appenderType, String appenderFormat, String available, String sourceDir, String uploadDir, String isPwdProtected, String pwdRules)
   {
      this.vendor = vendor;
      this.fileName = fileName;
      this.fileId = fileId;
      this.fileExtension = fileExtension;
      this.description = description;
      this.active = active;
      this.fileNameAppender = fileNameAppender;
      this.appenderType = appenderType;
      this.appenderFormat = appenderFormat;
      this.available = available;
      this.sourceDir = sourceDir;
      this.uploadDir = uploadDir;
      this.isPwdProtected = isPwdProtected;
      this.pwdRules = pwdRules;
   }

   @Override
   public String toString()
   {
      return "PDFFileDetails{" +
         "vendor='" + vendor + '\'' +
         ", fileName='" + fileName + '\'' +
         ", fileId='" + fileId + '\'' +
         ", fileExtension='" + fileExtension + '\'' +
         ", description='" + description + '\'' +
         ", active='" + active + '\'' +
         ", fileNameAppender='" + fileNameAppender + '\'' +
         ", appenderType='" + appenderType + '\'' +
         ", appenderFormat='" + appenderFormat + '\'' +
         ", available='" + available + '\'' +
         ", sourceDir='" + sourceDir + '\'' +
         ", uploadDir='" + uploadDir + '\'' +
         ", isPwdProtected='" + isPwdProtected + '\'' +
         ", pwdRules='" + pwdRules + '\'' +
         '}';
   }

   public String getVendor()
   {
      return vendor;
   }

   public void setVendor(String vendor)
   {
      this.vendor = vendor;
   }

   public String getFileName()
   {
      return fileName;
   }

   public void setFileName(String fileName)
   {
      this.fileName = fileName;
   }

   public String getFileId()
   {
      return fileId;
   }

   public void setFileId(String fileId)
   {
      this.fileId = fileId;
   }

   public String getFileExtension()
   {
      return fileExtension;
   }

   public void setFileExtension(String fileExtension)
   {
      this.fileExtension = fileExtension;
   }

   public String getDescription()
   {
      return description;
   }

   public void setDescription(String description)
   {
      this.description = description;
   }

   public String getActive()
   {
      return active;
   }

   public void setActive(String active)
   {
      this.active = active;
   }

   public String getFileNameAppender()
   {
      return fileNameAppender;
   }

   public void setFileNameAppender(String fileNameAppender)
   {
      this.fileNameAppender = fileNameAppender;
   }

   public String getAppenderType()
   {
      return appenderType;
   }

   public void setAppenderType(String appenderType)
   {
      this.appenderType = appenderType;
   }

   public String getAppenderFormat()
   {
      return appenderFormat;
   }

   public void setAppenderFormat(String appenderFormat)
   {
      this.appenderFormat = appenderFormat;
   }

   public String getAvailable()
   {
      return available;
   }

   public void setAvailable(String available)
   {
      this.available = available;
   }

   public String getSourceDir()
   {
      return sourceDir;
   }

   public void setSourceDir(String sourceDir)
   {
      this.sourceDir = sourceDir;
   }

   public String getUploadDir()
   {
      return uploadDir;
   }

   public void setUploadDir(String uploadDir)
   {
      this.uploadDir = uploadDir;
   }

   public String getIsPwdProtected()
   {
      return isPwdProtected;
   }

   public void setIsPwdProtected(String isPwdProtected)
   {
      this.isPwdProtected = isPwdProtected;
   }

   public String getPwdRules()
   {
      return pwdRules;
   }

   public void setPwdRules(String pwdRules)
   {
      this.pwdRules = pwdRules;
   }
}
