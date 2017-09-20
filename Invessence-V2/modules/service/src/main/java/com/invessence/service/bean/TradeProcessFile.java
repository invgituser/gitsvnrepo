package com.invessence.service.bean;

/**
 * Created by abhangp on 2/24/2017.
 */
public class TradeProcessFile
{
   private String vendor;
   private String fileName;
   private String fileType;
   private String fileExtension;
   private String delimeter;
   private String containsHeader;
   private String active;
   private Integer seqNum;
   private String uploadDir;
   private String dbStoredProc;
   private String preInstruction;
   private String postInstruction;

   @Override
   public String toString()
   {
      return "TradeProcessFile{" +
         "vendor='" + vendor + '\'' +
         ", fileName='" + fileName + '\'' +
         ", fileType='" + fileType + '\'' +
         ", fileExtension='" + fileExtension + '\'' +
         ", delimeter='" + delimeter + '\'' +
         ", containsHeader='" + containsHeader + '\'' +
         ", active='" + active + '\'' +
         ", seqNum=" + seqNum +
         ", uploadDir='" + uploadDir + '\'' +
         ", dbStoredProc='" + dbStoredProc + '\'' +
         ", preInstruction='" + preInstruction + '\'' +
         ", postInstruction='" + postInstruction + '\'' +
         '}';
   }

   public TradeProcessFile(String vendor, String fileName, String fileType, String fileExtension, String delimeter, String containsHeader, String active, Integer seqNum, String uploadDir, String dbStoredProc, String preInstruction, String postInstruction)
   {
      this.vendor = vendor;
      this.fileName = fileName;
      this.fileType = fileType;
      this.fileExtension = fileExtension;
      this.delimeter = delimeter;
      this.containsHeader = containsHeader;
      this.active = active;
      this.seqNum = seqNum;
      this.uploadDir = uploadDir;
      this.dbStoredProc = dbStoredProc;
      this.preInstruction = preInstruction;
      this.postInstruction = postInstruction;
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

   public String getDelimeter()
   {
      return delimeter;
   }

   public void setDelimeter(String delimeter)
   {
      this.delimeter = delimeter;
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

   public Integer getSeqNum()
   {
      return seqNum;
   }

   public void setSeqNum(Integer seqNum)
   {
      this.seqNum = seqNum;
   }

   public String getUploadDir()
   {
      return uploadDir;
   }

   public void setUploadDir(String uploadDir)
   {
      this.uploadDir = uploadDir;
   }

   public String getDbStoredProc()
   {
      return dbStoredProc;
   }

   public void setDbStoredProc(String dbStoredProc)
   {
      this.dbStoredProc = dbStoredProc;
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
}
