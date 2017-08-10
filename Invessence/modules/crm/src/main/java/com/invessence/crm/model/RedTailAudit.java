package com.invessence.crm.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 * Created by abhangp on 12/9/2016.
 */
@Entity
@Table(name = "crm_redtail_audit", catalog = "invdb")
public class RedTailAudit implements Serializable
{
   private Integer id;
   private String product;
   private String mode;
   private String processId;
   private String reqObj;
   private String reqFor;
   private String resObj;
   private String status;
   private String remarks;
   private Integer resStatusCode;
   private Date reqTime;
   private Date resTime;

   @Id
   @GeneratedValue
   @Column(name = "id", unique = true, nullable = false)
   public Integer getId()
   {
      return id;
   }

   public String getReqFor()
   {
      return reqFor;
   }

   public void setReqFor(String reqFor)
   {
      this.reqFor = reqFor;
   }

   public Integer getResStatusCode()
   {
      return resStatusCode;
   }

   public void setResStatusCode(Integer resStatusCode)
   {
      this.resStatusCode = resStatusCode;
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

   public String getProcessId()
   {
      return processId;
   }

   public void setProcessId(String processId)
   {
      this.processId = processId;
   }

   public RedTailAudit(Integer id, String product, String mode, String processId, String reqFor, String reqObj, String resObj, String status, String remarks, Date reqTime, Date resTime, Integer resStatusCode)
   {
      this.id = id;
      this.product = product;
      this.mode = mode;
      this.processId = processId;
      this.reqFor = reqFor;
      this.reqObj = reqObj;
      this.resObj = resObj;
      this.status = status;
      this.remarks = remarks;
      this.reqTime = reqTime;
      this.resTime = resTime;
      this.resStatusCode=resStatusCode;
   }

   @Override
   public String toString()
   {
      return "RedTailAudit{" +
         "id=" + id +
         ", product='" + product + '\'' +
         ", mode='" + mode + '\'' +
         ", processId='" + processId + '\'' +
         ", reqObj='" + reqObj + '\'' +
         ", reqFor='" + reqFor + '\'' +
         ", resObj='" + resObj + '\'' +
         ", status='" + status + '\'' +
         ", remarks='" + remarks + '\'' +
         ", resStatusCode=" + resStatusCode +
         ", reqTime=" + reqTime +
         ", resTime=" + resTime +
         '}';
   }

   public void setId(Integer id)
   {
      this.id = id;
   }

   @Lob
   public String getReqObj()
   {
      return reqObj;
   }

   public void setReqObj(String reqObj)
   {
      this.reqObj = reqObj;
   }

   @Lob
   public String getResObj()
   {
      return resObj;
   }

   public void setResObj(String resObj)
   {
      this.resObj = resObj;
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

   public Date getReqTime()
   {
      return reqTime;
   }

   public void setReqTime(Date reqTime)
   {
      this.reqTime = reqTime;
   }

   public Date getResTime()
   {
      return resTime;
   }

   public void setResTime(Date resTime)
   {
      this.resTime = resTime;
   }
}
