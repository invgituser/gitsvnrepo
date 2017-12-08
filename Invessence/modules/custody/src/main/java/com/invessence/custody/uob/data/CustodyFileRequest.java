package com.invessence.custody.uob.data;

import java.util.Date;

/**
 * Created by sagar on 12/8/2017.
 */
public class CustodyFileRequest
{
   private Long reqId;
   private String product;
   private Long acctnum;
   private String action;
   private String requestFor;
   private Integer seqno;
   private String fileName;
   private String filePath;
   private String fileType;

   public Long getReqId()
   {
      return reqId;
   }

   public void setReqId(Long reqId)
   {
      this.reqId = reqId;
   }

   public String getProduct()
   {
      return product;
   }

   public void setProduct(String product)
   {
      this.product = product;
   }

   public Long getAcctnum()
   {
      return acctnum;
   }

   public void setAcctnum(Long acctnum)
   {
      this.acctnum = acctnum;
   }

   public String getAction()
   {
      return action;
   }

   public void setAction(String action)
   {
      this.action = action;
   }

   public String getRequestFor()
   {
      return requestFor;
   }

   public void setRequestFor(String requestFor)
   {
      this.requestFor = requestFor;
   }

   public Integer getSeqno()
   {
      return seqno;
   }

   public void setSeqno(Integer seqno)
   {
      this.seqno = seqno;
   }

   public String getFileName()
   {
      return fileName;
   }

   public void setFileName(String fileName)
   {
      this.fileName = fileName;
   }

   public String getFilePath()
   {
      return filePath;
   }

   public void setFilePath(String filePath)
   {
      this.filePath = filePath;
   }

   public String getFileType()
   {
      return fileType;
   }

   public void setFileType(String fileType)
   {
      this.fileType = fileType;
   }
}
