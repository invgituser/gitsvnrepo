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
   private String encryptionMethod;
   private String decrFileExtension;
   private String tmpTableName;
   private String canBeDups;
   private String delimiter;
   private String delFlagServerFile;
   private int delDayServerFile;
   private String delFlagLocalFile;
   private int delDayLocalFile;
   private String delFlagDecrFile;
   private String fileProcessType;
   private String parentPreDBProcess;
   private String parentPostDBProcess;
   private String parentPreInstruction;
   private String parentPostInstruction;
   private boolean processStatus;

   public FileDetails(String vendor,String fileName, String processId, boolean processStatus, String fileProcessType, String downloadDir)
   {
      this.vendor = vendor;
      this.fileName = fileName;
      this.processId = processId;
      this.processStatus = processStatus;
      this.fileProcessType =fileProcessType;
      this.downloadDir=downloadDir;
   }

   public FileDetails(String vendor, String fileName, String processId, String process, String fileType, String fileExtension, String fileId, String containsHeader, String active, Integer seqNo, String uploadDir, String preDBProcess, String postDBProcess, String preInstruction, String postInstruction, String fileNameAppender, String appenderFormat, String available, String sourcePath, String downloadDir, String loadFormat, String required, String canBeEmpty, int keyData,
                      String encryptionMethod ,
                      String decrFileExtension, String encColumns, String tmpTableName, String canBeDups, String delimiter,
                      String delFlagServerFile,
                      int delDayServerFile,
                      String delFlagLocalFile,
                      int delDayLocalFile, String delFlagDecrFile,
                      String fileProcessType,
                      String parentPreDBProcess,
                         String parentPostDBProcess,
                         String parentPreInstruction,
                         String parentPostInstruction
                      )
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
      this.encryptionMethod = encryptionMethod;
      this.decrFileExtension=decrFileExtension;
      this.tmpTableName = tmpTableName;
      this.canBeDups = canBeDups;
      this.delimiter=        delimiter;
      this.delFlagServerFile=delFlagServerFile;
      this.delDayServerFile= delDayServerFile;
      this.delFlagLocalFile= delFlagLocalFile;
      this.delDayLocalFile=  delDayLocalFile;
      this.delFlagDecrFile=  delFlagDecrFile;
      this.fileProcessType=  fileProcessType;
      this.parentPreDBProcess= parentPreDBProcess;
         this.parentPostDBProcess=parentPostDBProcess;
         this.parentPreInstruction=parentPreInstruction;
         this.parentPostInstruction=parentPostInstruction;
   }

   public String getDecrFileExtension()
   {
      return decrFileExtension;
   }

   public void setDecrFileExtension(String decrFileExtension)
   {
      this.decrFileExtension = decrFileExtension;
   }

   public String getParentPreDBProcess()
   {
      return parentPreDBProcess;
   }

   public void setParentPreDBProcess(String parentPreDBProcess)
   {
      this.parentPreDBProcess = parentPreDBProcess;
   }

   public String getParentPostDBProcess()
   {
      return parentPostDBProcess;
   }

   public void setParentPostDBProcess(String parentPostDBProcess)
   {
      this.parentPostDBProcess = parentPostDBProcess;
   }

   public String getParentPreInstruction()
   {
      return parentPreInstruction;
   }

   public void setParentPreInstruction(String parentPreInstruction)
   {
      this.parentPreInstruction = parentPreInstruction;
   }

   public String getParentPostInstruction()
   {
      return parentPostInstruction;
   }

   public void setParentPostInstruction(String parentPostInstruction)
   {
      this.parentPostInstruction = parentPostInstruction;
   }

   public String getFileProcessType()
   {
      return fileProcessType;
   }

   public void setFileProcessType(String fileProcessType)
   {
      this.fileProcessType = fileProcessType;
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

   public boolean isProcessStatus()
   {
      return processStatus;
   }

   public void setProcessStatus(boolean processStatus)
   {
      this.processStatus = processStatus;
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

   public String getEncryptionMethod()
   {
      return encryptionMethod;
   }

   public void setEncryptionMethod(String encryptionMethod)
   {
      this.encryptionMethod = encryptionMethod;
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

   public String getDelimiter()
   {
      return delimiter;
   }

   public void setDelimiter(String delimiter)
   {
      this.delimiter = delimiter;
   }

   public String getDelFlagServerFile()
   {
      return delFlagServerFile;
   }

   public void setDelFlagServerFile(String delFlagServerFile)
   {
      this.delFlagServerFile = delFlagServerFile;
   }

   public int getDelDayServerFile()
   {
      return delDayServerFile;
   }

   public void setDelDayServerFile(int delDayServerFile)
   {
      this.delDayServerFile = delDayServerFile;
   }

   public String getDelFlagLocalFile()
   {
      return delFlagLocalFile;
   }

   public void setDelFlagLocalFile(String delFlagLocalFile)
   {
      this.delFlagLocalFile = delFlagLocalFile;
   }

   public int getDelDayLocalFile()
   {
      return delDayLocalFile;
   }

   public void setDelDayLocalFile(int delDayLocalFile)
   {
      this.delDayLocalFile = delDayLocalFile;
   }

   public String getDelFlagDecrFile()
   {
      return delFlagDecrFile;
   }

   public void setDelFlagDecrFile(String delFlagDecrFile)
   {
      this.delFlagDecrFile = delFlagDecrFile;
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
         ", encryptionMethod='" + encryptionMethod + '\'' +
         ", decrFileExtension='" + decrFileExtension + '\'' +
         ", tmpTableName='" + tmpTableName + '\'' +
         ", canBeDups='" + canBeDups + '\'' +
         ", delimiter='" + delimiter + '\'' +
         ", delFlagServerFile='" + delFlagServerFile + '\'' +
         ", delDayServerFile=" + delDayServerFile +
         ", delFlagLocalFile='" + delFlagLocalFile + '\'' +
         ", delDayLocalFile=" + delDayLocalFile +
         ", delFlagDecrFile='" + delFlagDecrFile + '\'' +
         ", fileProcessType='" + fileProcessType + '\'' +
         ", parentPreDBProcess='" + parentPreDBProcess + '\'' +
         ", parentPostDBProcess='" + parentPostDBProcess + '\'' +
         ", parentPreInstruction='" + parentPreInstruction + '\'' +
         ", parentPostInstruction='" + parentPostInstruction + '\'' +
         ", processStatus=" + processStatus +
         '}';
   }
}
