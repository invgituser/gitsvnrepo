package com.invessence.web.data.custody;

import java.util.Date;

public class BenefiaciaryDetails {

      private Long acctnum;
      private Integer beneId;
      private String beneFirstName;
      private String beneMidInitial;
      private String beneLastName;
      private String beneSSN;
      private String beneDOB;
      private String beneRel;
      private String beneCitizenshiId;
      private String typeOfBeneficiary;
      private String perStripes;
      private Double sharePerc;
      private Date created;
      private String createdBy;
      private Date updated;
      private String updatedBy;

   public BenefiaciaryDetails()
   {

   }

   public BenefiaciaryDetails(String beneFirstName,String beneLastName,Double sharePerc)
   {
      this.beneFirstName = beneFirstName;
      this.beneLastName = beneLastName;
      this.sharePerc = sharePerc;
   }

   public Long getAcctnum()
   {
      return acctnum;
   }

   public void setAcctnum(Long acctnum)
   {
      this.acctnum = acctnum;
   }

   public Integer getBeneId()
   {
      return beneId;
   }

   public void setBeneId(Integer beneId)
   {
      this.beneId = beneId;
   }

   public String getBeneFirstName()
   {
      return beneFirstName;
   }

   public void setBeneFirstName(String beneFirstName)
   {
      this.beneFirstName = beneFirstName;
   }

   public String getBeneMidInitial()
   {
      return beneMidInitial;
   }

   public void setBeneMidInitial(String beneMidInitial)
   {
      this.beneMidInitial = beneMidInitial;
   }

   public String getBeneLastName()
   {
      return beneLastName;
   }

   public void setBeneLastName(String beneLastName)
   {
      this.beneLastName = beneLastName;
   }

   public String getBeneSSN()
   {
      return beneSSN;
   }

   public void setBeneSSN(String beneSSN)
   {
      this.beneSSN = beneSSN;
   }

   public String getBeneDOB()
   {
      return beneDOB;
   }

   public void setBeneDOB(String beneDOB)
   {
      this.beneDOB = beneDOB;
   }

   public String getBeneRel()
   {
      return beneRel;
   }

   public void setBeneRel(String beneRel)
   {
      this.beneRel = beneRel;
   }

   public String getTypeOfBeneficiary()
   {
      return typeOfBeneficiary;
   }

   public void setTypeOfBeneficiary(String typeOfBeneficiary)
   {
      this.typeOfBeneficiary = typeOfBeneficiary;
   }

   public String getPerStripes()
   {
      return perStripes;
   }

   public void setPerStripes(String perStripes)
   {
      this.perStripes = perStripes;
   }

   public Double getSharePerc()
   {
      return sharePerc;
   }

   public void setSharePerc(Double sharePerc)
   {
      this.sharePerc = sharePerc;
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

   public String getBeneCitizenshiId()
   {
      return beneCitizenshiId;
   }

   public void setBeneCitizenshiId(String beneCitizenshiId)
   {
      this.beneCitizenshiId = beneCitizenshiId;
   }
}