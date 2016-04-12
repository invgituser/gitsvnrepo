package com.invessence.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by abhangp on 4/4/2016.
 */
public class UserAcctExt implements Serializable
{
   private String clientAccountID, accountType, status, remarks;
   private Date dateOfBirth, created, lastUpdated;
   private WSCallStatus WSCallStatus;

   private int mailingAddressId;
   private String mailingAddressType;

   private String opt;

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

   public String getStatus()
   {
      return status;
   }

   public void setStatus(String status)
   {
      this.status = status;
   }

   public int getMailingAddressId()
   {
      return mailingAddressId;
   }

   public void setMailingAddressId(int mailingAddressId)
   {
      this.mailingAddressId = mailingAddressId;
   }

   public String getMailingAddressType()
   {
      return mailingAddressType;
   }

   public void setMailingAddressType(String mailingAddressType)
   {
      this.mailingAddressType = mailingAddressType;
   }

   public WSCallStatus getWSCallStatus()
   {
      return WSCallStatus;
   }

   public void setWSCallStatus(WSCallStatus WSCallStatus)
   {
      this.WSCallStatus = WSCallStatus;
   }

   public String getAccountType()
   {
      return accountType;
   }

   public void setAccountType(String accountType)
   {
      this.accountType = accountType;
   }

   public String getClientAccountID()
   {
      return clientAccountID;
   }

   public void setClientAccountID(String clientAccountID)
   {
      this.clientAccountID = clientAccountID;
   }

   public Date getCreated()
   {
      return created;
   }

   public void setCreated(Date created)
   {
      this.created = created;
   }

   public Date getDateOfBirth()
   {
      return dateOfBirth;
   }

   public void setDateOfBirth(Date dateOfBirth)
   {
      this.dateOfBirth = dateOfBirth;
   }

   public Date getLastUpdated()
   {
      return lastUpdated;
   }

   public void setLastUpdated(Date lastUpdated)
   {
      this.lastUpdated = lastUpdated;
   }

   @Override
   public String toString()
   {
      return "UserAcctExt{" +
         "accountType='" + accountType + '\'' +
         ", clientAccountID='" + clientAccountID + '\'' +
         ", dateOfBirth=" + dateOfBirth +
         ", created=" + created +
         ", lastUpdated=" + lastUpdated +
         ", WSCallStatus=" + WSCallStatus +
         ", mailingAddressId=" + mailingAddressId +
         ", mailingAddressType='" + mailingAddressType + '\'' +
         '}';
   }
}
