package com.invessence.ws.provider.td.bean;

import java.util.Date;

/**
 * Created by abhangp on 9/9/2016.
 */
public class DCRequestAudit
{
   private Long id;
   private String product;
   private String mode;
   private String requestIds;
   private Integer eventNum;
   private Long acctnum;
   private String dcRequest;
   private String dcResponce;
   private String status;
   private String remarks;
   private Date reqTime;
   private Date resTime;
   private String envelopId, opt;

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

   @Override
   public String toString()
   {
      return "DCRequestAudit{" +
         "id=" + id +
         ", requestIds=" + requestIds +
         ", eventNum=" + eventNum +
         ", acctnum=" + acctnum +
         ", dcRequest='" + dcRequest + '\'' +
         ", dcResponce='" + dcResponce + '\'' +
         ", status='" + status + '\'' +
         ", remarks='" + remarks + '\'' +
         ", reqTime=" + reqTime +
         ", resTime=" + resTime +
         ", envelopId='" + envelopId + '\'' +
         ", opt='" + opt + '\'' +
         '}';
   }

   public DCRequestAudit(String product, String  mode, String requestIds, Long acctnum,Integer eventNum, String dcRequest, String dcResponce, String status, Date reqTime, String envelopId)
   {
      this.product=product;
      this.mode=mode;
      this.requestIds=requestIds;
      this.acctnum = acctnum;
      this.eventNum = eventNum;
      this.dcRequest = dcRequest;
      this.dcResponce = dcResponce;
      this.status = status;
      this.reqTime = reqTime;
      this.envelopId = envelopId;
   }

   public String getOpt()
   {
      return opt;
   }

   public void setOpt(String opt)
   {
      this.opt = opt;
   }

   public Long getId()
   {
      return id;
   }

   public void setId(Long id)
   {
      this.id = id;
   }

   public String getRequestIds()
   {
      return requestIds;
   }

   public void setRequestIds(String requestIds)
   {
      this.requestIds = requestIds;
   }

   public Integer getEventNum()
   {
      return eventNum;
   }

   public void setEventNum(Integer eventNum)
   {
      this.eventNum = eventNum;
   }

   public Long getAcctnum()
   {
      return acctnum;
   }

   public void setAcctnum(Long acctnum)
   {
      this.acctnum = acctnum;
   }

   public String getDcRequest()
   {
      return dcRequest;
   }

   public void setDcRequest(String dcRequest)
   {
      this.dcRequest = dcRequest;
   }

   public String getDcResponce()
   {
      return dcResponce;
   }

   public void setDcResponce(String dcResponce)
   {
      this.dcResponce = dcResponce;
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

   public String getEnvelopId()
   {
      return envelopId;
   }

   public void setEnvelopId(String envelopId)
   {
      this.envelopId = envelopId;
   }
}
