package com.invessence.broker.bean;

/**
 * Created by abhangp on 1/17/2016.
 */
public class DownloadFileDetails
{
   private String vendor;
   private String fileName;
   private String active;
   private String tmpTableName;
   private String tmp_TableName;
   private String available;
   private String sourcePath;
   private String downloadDir;
   private String format;
   private String required;
   private String canBeEmpty;
   private String postProcess;
   private String postInstruction;
   private String containsheader;


   private boolean isEmpty;
   private boolean isCopied;
   private boolean isError;
   private String errorMsg;

   public String getTmp_TableName()
   {
      return tmp_TableName;
   }

   public void setTmp_TableName(String tmp_TableName)
   {
      this.tmp_TableName = tmp_TableName;
   }

   public String getActive()
   {
      return active;
   }

   public void setActive(String active)
   {
      this.active = active;
   }

   public String getAvailable()
   {
      return available;
   }

   public void setAvailable(String available)
   {
      this.available = available;
   }

   public String getCanBeEmpty()
   {
      return canBeEmpty;
   }

   public void setCanBeEmpty(String canBeEmpty)
   {
      this.canBeEmpty = canBeEmpty;
   }

   public String getDownloadDir()
   {
      return downloadDir;
   }

   public void setDownloadDir(String downloadDir)
   {
      this.downloadDir = downloadDir;
   }

   public String getFileName()
   {
      return fileName;
   }

   public void setFileName(String fileName)
   {
      this.fileName = fileName;
   }

   public String getFormat()
   {
      return format;
   }

   public void setFormat(String format)
   {
      this.format = format;
   }

   public String getPostInstruction()
   {
      return postInstruction;
   }

   public void setPostInstruction(String postInstruction)
   {
      this.postInstruction = postInstruction;
   }

   public String getPostProcess()
   {
      return postProcess;
   }

   public void setPostProcess(String postProcess)
   {
      this.postProcess = postProcess;
   }

   public String getRequired()
   {
      return required;
   }

   public void setRequired(String required)
   {
      this.required = required;
   }

   public String getSourcePath()
   {
      return sourcePath;
   }

   public void setSourcePath(String sourcePath)
   {
      this.sourcePath = sourcePath;
   }

   public String getTmpTableName()
   {
      return tmpTableName;
   }

   public void setTmpTableName(String tmpTableName)
   {
      this.tmpTableName = tmpTableName;
   }

   public String getVendor()
   {
      return vendor;
   }

   public void setVendor(String vendor)
   {
      this.vendor = vendor;
   }

   public String getErrorMsg()
   {
      return errorMsg;
   }

   public void setErrorMsg(String errorMsg)
   {
      this.errorMsg = errorMsg;
   }

   public boolean isCopied()
   {
      return isCopied;
   }

   public void setIsCopied(boolean isCopied)
   {
      this.isCopied = isCopied;
   }

   public boolean isEmpty()
   {
      return isEmpty;
   }

   public void setIsEmpty(boolean isEmpty)
   {
      this.isEmpty = isEmpty;
   }

   public boolean isError()
   {
      return isError;
   }

   public void setIsError(boolean isError)
   {
      this.isError = isError;
   }

   public String getContainsheader()
   {
      return containsheader;
   }

   public void setContainsheader(String containsheader)
   {
      this.containsheader = containsheader;
   }

   @Override
   public String toString()
   {
      return "DownloadFileDetails{" +
         "active='" + active + '\'' +
         ", vendor='" + vendor + '\'' +
         ", fileName='" + fileName + '\'' +
         ", tmpTableName='" + tmpTableName + '\'' +
         ", tmp_TableName='" + tmp_TableName + '\'' +
         ", available='" + available + '\'' +
         ", sourcePath='" + sourcePath + '\'' +
         ", downloadDir='" + downloadDir + '\'' +
         ", format='" + format + '\'' +
         ", required='" + required + '\'' +
         ", canBeEmpty='" + canBeEmpty + '\'' +
         ", postProcess='" + postProcess + '\'' +
         ", postInstruction='" + postInstruction + '\'' +
         ", containsheader='" + containsheader + '\'' +
         ", isEmpty=" + isEmpty +
         ", isCopied=" + isCopied +
         ", isError=" + isError +
         ", errorMsg='" + errorMsg + '\'' +
         '}';
   }
}
