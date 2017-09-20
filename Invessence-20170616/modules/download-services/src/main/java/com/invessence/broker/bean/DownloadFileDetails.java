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
   private String loadFormat;
   private String required;
   private String canBeEmpty;
   private String postProcess;
   private String postInstruction;
   private String containsheader;
   private int keyData;
   private String encColumns;
   private String fileExtension;
   private String encryptionMethod;
   private String canbedups;


   private boolean isEmpty;
   private boolean isCopied;
   private boolean isError;
   private String errorMsg;

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

   public String getContainsheader()
   {
      return containsheader;
   }

   public void setContainsheader(String containsheader)
   {
      this.containsheader = containsheader;
   }

   public String getDownloadDir()
   {
      return downloadDir;
   }

   public void setDownloadDir(String downloadDir)
   {
      this.downloadDir = downloadDir;
   }

   public String getEncColumns()
   {
      return encColumns;
   }

   public void setEncColumns(String encColumns)
   {
      this.encColumns = encColumns;
   }

   public String getEncryptionMethod()
   {
      return encryptionMethod;
   }

   public void setEncryptionMethod(String encryptionMethod)
   {
      this.encryptionMethod = encryptionMethod;
   }

   public String getErrorMsg()
   {
      return errorMsg;
   }

   public void setErrorMsg(String errorMsg)
   {
      this.errorMsg = errorMsg;
   }

   public String getFileExtension()
   {
      return fileExtension;
   }

   public void setFileExtension(String fileExtension)
   {
      this.fileExtension = fileExtension;
   }

   public String getFileName()
   {
      return fileName;
   }

   public void setFileName(String fileName)
   {
      this.fileName = fileName;
   }

   public boolean isCopied()
   {
      return isCopied;
   }

   public void setCopied(boolean copied)
   {
      isCopied = copied;
   }

   public boolean isEmpty()
   {
      return isEmpty;
   }

   public void setEmpty(boolean empty)
   {
      isEmpty = empty;
   }

   public boolean isError()
   {
      return isError;
   }

   public void setError(boolean error)
   {
      isError = error;
   }

   public int getKeyData()
   {
      return keyData;
   }

   public void setKeyData(int keyData)
   {
      this.keyData = keyData;
   }

   public String getLoadFormat()
   {
      return loadFormat;
   }

   public void setLoadFormat(String loadFormat)
   {
      this.loadFormat = loadFormat;
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

   public String getTmp_TableName()
   {
      return tmp_TableName;
   }

   public void setTmp_TableName(String tmp_TableName)
   {
      this.tmp_TableName = tmp_TableName;
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

   public String getCanbedups()
   {
      return canbedups;
   }

   public void setCanbedups(String canbedups)
   {
      this.canbedups = canbedups;
   }

   @Override
   public String toString()
   {
      return "DownloadFileDetails{" +
         "vendor='" + vendor + '\'' +
         ", fileName='" + fileName + '\'' +
         ", active='" + active + '\'' +
         ", tmpTableName='" + tmpTableName + '\'' +
         ", tmp_TableName='" + tmp_TableName + '\'' +
         ", available='" + available + '\'' +
         ", sourcePath='" + sourcePath + '\'' +
         ", downloadDir='" + downloadDir + '\'' +
         ", loadFormat='" + loadFormat + '\'' +
         ", required='" + required + '\'' +
         ", canBeEmpty='" + canBeEmpty + '\'' +
         ", postProcess='" + postProcess + '\'' +
         ", postInstruction='" + postInstruction + '\'' +
         ", containsheader='" + containsheader + '\'' +
         ", keyData=" + keyData +
         ", encColumns='" + encColumns + '\'' +
         ", fileExtension='" + fileExtension + '\'' +
         ", encryptionMethod='" + encryptionMethod + '\'' +
         ", canbedups='" + canbedups + '\'' +
         ", isEmpty=" + isEmpty +
         ", isCopied=" + isCopied +
         ", isError=" + isError +
         ", errorMsg='" + errorMsg + '\'' +
         '}';
   }
}
