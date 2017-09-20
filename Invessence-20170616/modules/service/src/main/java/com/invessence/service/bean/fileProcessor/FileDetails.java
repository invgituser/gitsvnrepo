package com.invessence.service.bean.fileProcessor;

/**
 * Created by abhangp on 4/20/2017.
 */
public class FileDetails
{
   private String vendor;
   private String fileName;
   private String processId;
   private String process;
   private String fileType;
   private String fileExtension;
   private String fileId;
   private String containsHeader;
   private String active;
   private Integer seqNo;
   private String uploadDir;
   private String preDBProcess;
   private String postDBProcess;
   private String preInstruction;
   private String postInstruction;
   private String fileNameAppender;
   private String appenderFormat;
   private String available;
   private String sourcePath;
   private String downloadDir;
   private String loadFormat;
   private String required;
   private String canBeEmpty;
   private int keyData;
   private String encryptionMethod;
   private String encColumns;
   private String tmpTableName;
   private String canBeDups;

   public FileDetails(String vendor, String fileName, String processId, String process, String fileType, String fileExtension, String fileId, String containsHeader, String active, Integer seqNo, String uploadDir, String preDBProcess, String postDBProcess, String preInstruction, String postInstruction, String fileNameAppender, String appenderFormat, String available, String sourcePath, String downloadDir, String loadFormat, String required, String canBeEmpty, int keyData, String encryptionMethod, String encColumns, String tmpTableName, String canBeDups)
   {
      this.vendor = vendor;
      this.fileName = fileName;
      this.processId = processId;
      this.process = process;
      this.fileType = fileType;
      this.fileExtension = fileExtension;
      this.fileId = fileId;
      this.containsHeader = containsHeader;
      this.active = active;
      this.seqNo = seqNo;
      this.uploadDir = uploadDir;
      this.preDBProcess = preDBProcess;
      this.postDBProcess = postDBProcess;
      this.preInstruction = preInstruction;
      this.postInstruction = postInstruction;
      this.fileNameAppender = fileNameAppender;
      this.appenderFormat = appenderFormat;
      this.available = available;
      this.sourcePath = sourcePath;
      this.downloadDir = downloadDir;
      this.loadFormat = loadFormat;
      this.required = required;
      this.canBeEmpty = canBeEmpty;
      this.keyData = keyData;
      this.encryptionMethod = encryptionMethod;
      this.encColumns = encColumns;
      this.tmpTableName = tmpTableName;
      this.canBeDups = canBeDups;
   }


   @Override
   public String toString()
   {
      return "FileDetails{" +
         "vendor='" + vendor + '\'' +
         ", fileName='" + fileName + '\'' +
         ", processId='" + processId + '\'' +
         ", process='" + process + '\'' +
         ", fileType='" + fileType + '\'' +
         ", fileExtension='" + fileExtension + '\'' +
         ", fileId='" + fileId + '\'' +
         ", containsHeader='" + containsHeader + '\'' +
         ", active='" + active + '\'' +
         ", seqNo=" + seqNo +
         ", uploadDir='" + uploadDir + '\'' +
         ", preDBProcess='" + preDBProcess + '\'' +
         ", postDBProcess='" + postDBProcess + '\'' +
         ", preInstruction='" + preInstruction + '\'' +
         ", postInstruction='" + postInstruction + '\'' +
         ", fileNameAppender='" + fileNameAppender + '\'' +
         ", appenderFormat='" + appenderFormat + '\'' +
         ", available='" + available + '\'' +
         ", sourcePath='" + sourcePath + '\'' +
         ", downloadDir='" + downloadDir + '\'' +
         ", loadFormat='" + loadFormat + '\'' +
         ", required='" + required + '\'' +
         ", canBeEmpty='" + canBeEmpty + '\'' +
         ", keyData=" + keyData +
         ", encryptionMethod='" + encryptionMethod + '\'' +
         ", encColumns='" + encColumns + '\'' +
         ", tmpTableName='" + tmpTableName + '\'' +
         ", canBeDups='" + canBeDups + '\'' +
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

   public String getProcessId()
   {
      return processId;
   }

   public void setProcessId(String processId)
   {
      this.processId = processId;
   }

   public String getProcess()
   {
      return process;
   }

   public void setProcess(String process)
   {
      this.process = process;
   }

   public String getFileType()
   {
      return fileType;
   }

   public void setFileType(String fileType)
   {
      this.fileType = fileType;
   }

   public String getFileExtension()
   {
      return fileExtension;
   }

   public void setFileExtension(String fileExtension)
   {
      this.fileExtension = fileExtension;
   }

   public String getFileId()
   {
      return fileId;
   }

   public void setFileId(String fileId)
   {
      this.fileId = fileId;
   }

   public String getContainsHeader()
   {
      return containsHeader;
   }

   public void setContainsHeader(String containsHeader)
   {
      this.containsHeader = containsHeader;
   }

   public String getActive()
   {
      return active;
   }

   public void setActive(String active)
   {
      this.active = active;
   }

   public Integer getSeqNo()
   {
      return seqNo;
   }

   public void setSeqNo(Integer seqNo)
   {
      this.seqNo = seqNo;
   }

   public String getUploadDir()
   {
      return uploadDir;
   }

   public void setUploadDir(String uploadDir)
   {
      this.uploadDir = uploadDir;
   }

   public String getPreDBProcess()
   {
      return preDBProcess;
   }

   public void setPreDBProcess(String preDBProcess)
   {
      this.preDBProcess = preDBProcess;
   }

   public String getPostDBProcess()
   {
      return postDBProcess;
   }

   public void setPostDBProcess(String postDBProcess)
   {
      this.postDBProcess = postDBProcess;
   }

   public String getPreInstruction()
   {
      return preInstruction;
   }

   public void setPreInstruction(String preInstruction)
   {
      this.preInstruction = preInstruction;
   }

   public String getPostInstruction()
   {
      return postInstruction;
   }

   public void setPostInstruction(String postInstruction)
   {
      this.postInstruction = postInstruction;
   }

   public String getFileNameAppender()
   {
      return fileNameAppender;
   }

   public void setFileNameAppender(String fileNameAppender)
   {
      this.fileNameAppender = fileNameAppender;
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

   public String getSourcePath()
   {
      return sourcePath;
   }

   public void setSourcePath(String sourcePath)
   {
      this.sourcePath = sourcePath;
   }

   public String getDownloadDir()
   {
      return downloadDir;
   }

   public void setDownloadDir(String downloadDir)
   {
      this.downloadDir = downloadDir;
   }

   public String getLoadFormat()
   {
      return loadFormat;
   }

   public void setLoadFormat(String loadFormat)
   {
      this.loadFormat = loadFormat;
   }

   public String getRequired()
   {
      return required;
   }

   public void setRequired(String required)
   {
      this.required = required;
   }

   public String getCanBeEmpty()
   {
      return canBeEmpty;
   }

   public void setCanBeEmpty(String canBeEmpty)
   {
      this.canBeEmpty = canBeEmpty;
   }

   public int getKeyData()
   {
      return keyData;
   }

   public void setKeyData(int keyData)
   {
      this.keyData = keyData;
   }

   public String getEncryptionMethod()
   {
      return encryptionMethod;
   }

   public void setEncryptionMethod(String encryptionMethod)
   {
      this.encryptionMethod = encryptionMethod;
   }

   public String getEncColumns()
   {
      return encColumns;
   }

   public void setEncColumns(String encColumns)
   {
      this.encColumns = encColumns;
   }

   public String getTmpTableName()
   {
      return tmpTableName;
   }

   public void setTmpTableName(String tmpTableName)
   {
      this.tmpTableName = tmpTableName;
   }

   public String getCanBeDups()
   {
      return canBeDups;
   }

   public void setCanBeDups(String canBeDups)
   {
      this.canBeDups = canBeDups;
   }
}
