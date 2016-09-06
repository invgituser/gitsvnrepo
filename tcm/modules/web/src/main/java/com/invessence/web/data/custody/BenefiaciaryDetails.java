package com.invessence.web.data.custody;

import java.io.Serializable;
import java.util.*;
import javax.faces.bean.ManagedProperty;
import javax.management.monitor.StringMonitor;

import com.invessence.web.bean.consumer.CTOBean;
import com.invessence.web.data.common.CustomerData;
import com.invessence.web.util.*;

public class BenefiaciaryDetails implements Serializable
{

      private static final long serialVersionUID = 1L;
      private Long acctnum;
      private int beneId =1;

      private String beneFirstName;
      private String beneMidInitial;

   public int getBeneId()
   {
      return beneId++;

    }

   public void setBeneId(int beneId)
   {
      this.beneId = beneId;
   }

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
      private Boolean managed =true;


   public BenefiaciaryDetails()
   {

   }

   public Boolean getManaged()
   {
      return managed;
   }

   public void setManaged(Boolean managed)
   {
      this.managed = managed;
   }

   @ManagedProperty("#{webutil}")
   private WebUtil webutil;
   public void setWebutil(WebUtil webutil)
   {
      this.webutil = webutil;
   }

   @ManagedProperty("#{uiLayout}")
   private UILayout uiLayout;
   public void setUiLayout(UILayout uiLayout)
   {
      this.uiLayout = uiLayout;
   }
   public String doSelectedAction()
   {
      String whichXML;
      try
      {

         if (getManaged())
         {

         }
         else {

         }
      }
      catch (Exception ex)
      {
         return ("failed");
      }

      return ("success");
   }
   public Long getAcctnum()
   {
      return acctnum;
   }

   public void setAcctnum(Long acctnum)
   {
      this.acctnum = acctnum;
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

   @Override
   public String toString()
   {
      return "BenefiaciaryDetails{" +
         "acctnum=" + acctnum +
         ", beneId=" + beneId +
         ", beneFirstName='" + beneFirstName + '\'' +
         ", beneMidInitial='" + beneMidInitial + '\'' +
         ", beneLastName='" + beneLastName + '\'' +
         ", beneSSN='" + beneSSN + '\'' +
         ", beneDOB='" + beneDOB + '\'' +
         ", beneRel='" + beneRel + '\'' +
         ", beneCitizenshiId='" + beneCitizenshiId + '\'' +
         ", typeOfBeneficiary='" + typeOfBeneficiary + '\'' +
         ", perStripes='" + perStripes + '\'' +
         ", sharePerc=" + sharePerc +
         ", created=" + created +
         ", createdBy='" + createdBy + '\'' +
         ", updated=" + updated +
         ", updatedBy='" + updatedBy + '\'' +
         ", managed=" + managed +
         ", webutil=" + webutil +
         ", uiLayout=" + uiLayout +
         '}';
   }
}