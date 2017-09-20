package com.invessence.web.data.custody.td;

import java.util.Date;

/**
 * Created by Sandeep N on 11/03/2016.
 */
public class MastACATFirm
{
   private String lookupSet;
   private String lookupCode;
   private String displayName;
   private String parentLookupId;
   private String value;
   private String remark;
   private Integer sortOrder;
   private String status;
   private String address;
   private String isRequired;
   private String phoneNumber;

   public String getLookupSet()
   {
      return lookupSet;
   }

   public void setLookupSet(String lookupSet)
   {
      this.lookupSet = lookupSet;
   }

   public String getLookupCode()
   {
      return lookupCode;
   }

   public void setLookupCode(String lookupCode)
   {
      this.lookupCode = lookupCode;
   }

   public String getDisplayName()
   {
      return displayName;
   }

   public void setDisplayName(String displayName)
   {
      this.displayName = displayName;
   }

   public String getParentLookupId()
   {
      return parentLookupId;
   }

   public void setParentLookupId(String parentLookupId)
   {
      this.parentLookupId = parentLookupId;
   }

   public String getValue()
   {
      return value;
   }

   public void setValue(String value)
   {
      this.value = value;
   }

   public String getRemark()
   {
      return remark;
   }

   public void setRemark(String remark)
   {
      this.remark = remark;
   }

   public Integer getSortOrder()
   {
      return sortOrder;
   }

   public void setSortOrder(Integer sortOrder)
   {
      this.sortOrder = sortOrder;
   }

   public String getStatus()
   {
      return status;
   }

   public void setStatus(String status)
   {
      this.status = status;
   }

   public String getIsRequired()
   {
      return isRequired;
   }

   public void setIsRequired(String isRequired)
   {
      this.isRequired = isRequired;
   }

   public String getAddress()
   {
      return address;
   }

   public void setAddress(String address)
   {
      this.address = address;
   }

   public String getPhoneNumber()
   {
      return phoneNumber;
   }

   public void setPhoneNumber(String phoneNumber)
   {
      this.phoneNumber = phoneNumber;
   }
}
