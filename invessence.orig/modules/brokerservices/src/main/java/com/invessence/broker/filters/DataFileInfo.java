package com.invessence.broker.filters;

public class DataFileInfo
{
   private final String downloadDir;
   private String vendor;
   private String filename;
   private String tmp_tableName;
   private String format;
   private String required;
   private String canbeempty;
   private String available;
   private String sourcepath;
   private String postProcess;
   private String postInstruction;
   private Integer numOfRecords = 0;
   private Boolean downloaded = false;

   public DataFileInfo(String vendor, String filename, String tmp_tableName, String format, String required, String canbeempty,
                       String available, String sourcepath, String postProcess, String postInstruction, String downloadDir)
   {
      this.vendor = vendor;
      this.filename = filename;
      this.tmp_tableName = tmp_tableName;
      this.format = format;
      this.required = required;
      this.canbeempty = canbeempty;
      this.available = available;
      this.sourcepath = sourcepath;
      this.postProcess = postProcess;
      this.postInstruction = postInstruction;
      this.downloadDir = downloadDir;
   }

   public String getVendor()
   {
      return vendor;
   }

   public String getFilename()
   {
      return filename;
   }

   public String getTmp_tableName()
   {
      return tmp_tableName;
   }

   public String getFormat()
   {
      return format;
   }

   public String getRequired()
   {
      return required;
   }

   public String getCanbeempty()
   {
      return canbeempty;
   }

   public String getAvailable()
   {
      return available;
   }

   public String getSourcepath()
   {
      return sourcepath;
   }

   public String getPostProcess()
   {
      return postProcess;
   }

   public String getPostInstruction()
   {
      return postInstruction;
   }

   public String getDownloadDir()
   {
      return downloadDir;
   }

   public Integer getNumOfRecords()
   {
      return numOfRecords;
   }

   public void setNumOfRecords(Integer numOfRecords)
   {
      this.numOfRecords += numOfRecords;
   }

   public Boolean isDownloaded()
   {
      return downloaded;
   }

   public void setDownloaded(Boolean downloaded)
   {
      this.downloaded = downloaded;
   }
}
