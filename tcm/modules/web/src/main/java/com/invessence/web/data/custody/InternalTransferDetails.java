package com.invessence.web.data.custody;

import java.util.Date;

public class InternalTransferDetails {

      private Long moveMoneyPayMethodID;
      private Integer intTranId;
      private String destAccountNumber;
      private String destAccountTitle;
      private Date created;
      private String createdBy;
      private Date updated;
      private String updatedBy;

   public InternalTransferDetails()
   {
   }

   public Long getMoveMoneyPayMethodID()
   {
      return moveMoneyPayMethodID;
   }

   public void setMoveMoneyPayMethodID(Long moveMoneyPayMethodID)
   {
      this.moveMoneyPayMethodID = moveMoneyPayMethodID;
   }

   public Integer getIntTranId()
   {
      return intTranId;
   }

   public void setIntTranId(Integer intTranId)
   {
      this.intTranId = intTranId;
   }

   public String getDestAccountNumber()
   {
      return destAccountNumber;
   }

   public void setDestAccountNumber(String destAccountNumber)
   {
      this.destAccountNumber = destAccountNumber;
   }

   public String getDestAccountTitle()
   {
      return destAccountTitle;
   }

   public void setDestAccountTitle(String destAccountTitle)
   {
      this.destAccountTitle = destAccountTitle;
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

