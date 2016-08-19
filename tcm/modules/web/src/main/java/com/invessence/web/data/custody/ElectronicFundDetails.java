package com.invessence.web.data.custody;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 8/17/16
 * Time: 4:33 PM
 * To change this template use File | Settings | File Templates.
 */
public class ElectronicFundDetails
{

   private Long acctnum;
   private Long reqId;
   private String eftInstId;
   private String tdaiAcctNum;
   private String directionId;
   private Long moveMoneyPayMethodID;
   private Integer achId;
   private Date tranStartDate;
   private Double tranAmount;
   private String tranFreqId;
   private Date created;
   private String createdBy;
   private Date updated;
   private String updatedBy;

   public ElectronicFundDetails()
   {
   }

   public Long getAcctnum()
   {
      return acctnum;
   }

   public void setAcctnum(Long acctnum)
   {
      this.acctnum = acctnum;
   }

   public Long getReqId()
   {
      return reqId;
   }

   public void setReqId(Long reqId)
   {
      this.reqId = reqId;
   }

   public String getEftInstId()
   {
      return eftInstId;
   }

   public void setEftInstId(String eftInstId)
   {
      this.eftInstId = eftInstId;
   }

   public String getTdaiAcctNum()
   {
      return tdaiAcctNum;
   }

   public void setTdaiAcctNum(String tdaiAcctNum)
   {
      this.tdaiAcctNum = tdaiAcctNum;
   }

   public String getDirectionId()
   {
      return directionId;
   }

   public void setDirectionId(String directionId)
   {
      this.directionId = directionId;
   }

   public Long getMoveMoneyPayMethodID()
   {
      return moveMoneyPayMethodID;
   }

   public void setMoveMoneyPayMethodID(Long moveMoneyPayMethodID)
   {
      this.moveMoneyPayMethodID = moveMoneyPayMethodID;
   }

   public Integer getAchId()
   {
      return achId;
   }

   public void setAchId(Integer achId)
   {
      this.achId = achId;
   }

   public Date getTranStartDate()
   {
      return tranStartDate;
   }

   public void setTranStartDate(Date tranStartDate)
   {
      this.tranStartDate = tranStartDate;
   }

   public Double getTranAmount()
   {
      return tranAmount;
   }

   public void setTranAmount(Double tranAmount)
   {
      this.tranAmount = tranAmount;
   }

   public String getTranFreqId()
   {
      return tranFreqId;
   }

   public void setTranFreqId(String tranFreqId)
   {
      this.tranFreqId = tranFreqId;
   }

   public Date getCreated()
   {
      return created;
   }

   public void setCreated(Date created)
   {
      this.created = created;
   }

   public String getCreatedBy()
   {
      return createdBy;
   }

   public void setCreatedBy(String createdBy)
   {
      this.createdBy = createdBy;
   }

   public Date getUpdated()
   {
      return updated;
   }

   public void setUpdated(Date updated)
   {
      this.updated = updated;
   }

   public String getUpdatedBy()
   {
      return updatedBy;
   }

   public void setUpdatedBy(String updatedBy)
   {
      this.updatedBy = updatedBy;
   }
}
