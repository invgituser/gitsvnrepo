package com.invessence.custody.uob.data;

/**
 * Created by sagar on 12/8/2017.
 */
public class CustodyFileDetails
{
   private String product;
   private String action;
   private String subaction;
   private String requestFor;
   private Integer seqno;
   private String fileName;
   private String fileType;
   private String fileExtensions;
   private String isActive;

   public String getProduct()
   {
      return product;
   }

   public void setProduct(String product)
   {
      this.product = product;
   }

   public String getAction()
   {
      return action;
   }

   public void setAction(String action)
   {
      this.action = action;
   }

   public String getSubaction()
   {
      return subaction;
   }

   public void setSubaction(String subaction)
   {
      this.subaction = subaction;
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

   public String getFileType()
   {
      return fileType;
   }

   public void setFileType(String fileType)
   {
      this.fileType = fileType;
   }

   public String getFileExtensions()
   {
      return fileExtensions;
   }

   public void setFileExtensions(String fileExtensions)
   {
      this.fileExtensions = fileExtensions;
   }

   public String getIsActive()
   {
      return isActive;
   }

   public void setIsActive(String isActive)
   {
      this.isActive = isActive;
   }
}
