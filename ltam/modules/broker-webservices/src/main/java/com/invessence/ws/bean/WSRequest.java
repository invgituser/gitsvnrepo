package com.invessence.ws.bean;

import java.util.Date;

/**
 * Created by abhangp on 4/25/2016.
 */
public class WSRequest
{
   private Long id;
   private String clientAccountID, requestType, status, reqXml, resXml, remarks, opt;
   private Date reqTime, resTime;

   public Long getId()
   {
      return id;
   }

   public void setId(Long id)
   {
      this.id = id;
   }

//   public WSRequest(String status, String clientAccountID, String opt, String remarks, Date reqTime, String reqXml, Date resTime, String resXml, String requestType)
//   {
//      this.status = status;
//      this.clientAccountID = clientAccountID;
//      this.opt = opt;
//      this.remarks = remarks;
//      this.reqTime = reqTime;
//      this.reqXml = reqXml;
//      this.resTime = resTime;
//      this.resXml = resXml;
//      this.requestType = requestType;
//   }

   public WSRequest(String status, String clientAccountID, String requestType)
   {
      this.status = status;
      this.clientAccountID = clientAccountID;
      this.reqTime=new Date();
      this.requestType = requestType;
   }

   public WSRequest(String status, String clientAccountID, String requestType,Date reqTime, String remarks)
   {
      this.status = status;
      this.clientAccountID = clientAccountID;
      this.reqTime=reqTime;
      this.requestType = requestType;
      this.remarks = remarks;
   }


   @Override
   public String toString()
   {
      return "WSRequest{" +
         "clientAccountID='" + clientAccountID + '\'' +
         ", requestType='" + requestType + '\'' +
         ", status='" + status + '\'' +
         ", reqXml='" + reqXml + '\'' +
         ", resXml='" + resXml + '\'' +
         ", remarks='" + remarks + '\'' +
         ", opt='" + opt + '\'' +
         ", reqTime=" + reqTime +
         ", resTime=" + resTime +
         '}';
   }

   public String getRemarks()
   {
      return remarks;
   }

   public void setRemarks(String remarks)
   {
      this.remarks = remarks;
   }

   public String getOpt()
   {
      return opt;
   }

   public void setOpt(String opt)
   {
      this.opt = opt;
   }

   public String getClientAccountID()
   {
      return clientAccountID;
   }

   public void setClientAccountID(String clientAccountID)
   {
      this.clientAccountID = clientAccountID;
   }

   public Date getReqTime()
   {
      return reqTime;
   }

   public void setReqTime(Date reqTime)
   {
      this.reqTime = reqTime;
   }

   public String getReqXml()
   {
      return reqXml;
   }

   public void setReqXml(String reqXml)
   {
      this.reqXml = reqXml;
   }

   public Date getResTime()
   {
      return resTime;
   }

   public void setResTime(Date resTime)
   {
      this.resTime = resTime;
   }

   public String getResXml()
   {
      return resXml;
   }

   public void setResXml(String resXml)
   {
      this.resXml = resXml;
   }

   public String getRequestType()
   {
      return requestType;
   }

   public void setRequestType(String requestType)
   {
      this.requestType = requestType;
   }

   public String getStatus()
   {
      return status;
   }

   public void setStatus(String status)
   {
      this.status = status;
   }
}

