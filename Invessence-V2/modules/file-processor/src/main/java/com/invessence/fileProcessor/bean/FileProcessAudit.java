package com.invessence.fileProcessor.bean;

import java.util.Date;

/**
 * Created by abhangp on 5/8/2017.
 */
public class FileProcessAudit
{
   private Long id;
   private String product;
   private String mode;
   private String process;
   private String processId;
   private String fileName;
   private String status;
   private String remarks;
   private Date executionTime;
   private String opt;

   public FileProcessAudit(Long id, String product, String mode, String process, String processId, String fileName, String status, String remarks, Date executionTime, String opt)
   {
      this.id = id;
      this.product = product;
      this.mode = mode;
      this.process = process;
      this.processId = processId;
      this.fileName = fileName;
      this.status = status;
      this.remarks = remarks;
      this.executionTime = executionTime;
      this.opt = opt;
   }

   public String getOpt()
   {
      return opt;
   }

   public void setOpt(String opt)
   {
      this.opt = opt;
   }

   @Override
   public String toString()
   {
      return "FileProcessAudit{" +
         "id=" + id +
         ", product='" + product + '\'' +
         ", mode='" + mode + '\'' +
         ", process='" + process + '\'' +
         ", processId='" + processId + '\'' +
         ", fileName='" + fileName + '\'' +
         ", status='" + status + '\'' +
         ", remarks='" + remarks + '\'' +
         ", executionTime=" + executionTime +
         '}';
   }

   public Long getId()
   {
      return id;
   }

   public void setId(Long id)
   {
      this.id = id;
   }

   public String getProduct()
   {
      return product;
   }

   public void setProduct(String product)
   {
      this.product = product;
   }

   public String getMode()
   {
      return mode;
   }

   public void setMode(String mode)
   {
      this.mode = mode;
   }

   public String getProcess()
   {
      return process;
   }

   public void setProcess(String process)
   {
      this.process = process;
   }

   public String getProcessId()
   {
      return processId;
   }

   public void setProcessId(String processId)
   {
      this.processId = processId;
   }

   public String getFileName()
   {
      return fileName;
   }

   public void setFileName(String fileName)
   {
      this.fileName = fileName;
   }

   public String getStatus()
   {
      return status;
   }

   public void setStatus(String status)
   {
      this.status = status;
   }

   public String getRemarks()
   {
      return remarks;
   }

   public void setRemarks(String remarks)
   {
      this.remarks = remarks;
   }

   public Date getExecutionTime()
   {
      return executionTime;
   }

   public void setExecutionTime(Date executionTime)
   {
      this.executionTime = executionTime;
   }
}
