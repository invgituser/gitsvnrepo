package com.invessence.service.bean;

/**
 * Created by abhangp on 8/19/2016.
 */
public class LookupDetails
{
   private String lookupSet;
   private String lookupCode;
   private String displayName;
   private String parentLookupId;
   private String value;
   private String remark;
   private Integer sortOrder;
   private String status;

   @Override
   public String toString()
   {
      return "Lookup{" +
         "lookupSet='" + lookupSet + '\'' +
         ", lookupCode='" + lookupCode + '\'' +
         ", displayName='" + displayName + '\'' +
         ", parentLookupId='" + parentLookupId + '\'' +
         ", value='" + value + '\'' +
         ", remark='" + remark + '\'' +
         ", sortOrder=" + sortOrder +
         ", status='" + status + '\'' +
         '}';
   }

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
}
