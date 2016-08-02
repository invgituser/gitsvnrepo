package com.invessence.web.data.custody;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 8/1/16
 * Time: 11:25 AM
 * To change this template use File | Settings | File Templates.
 */
public class Beneficiary
{
   private Long   acctnum;           // acctOwnerId
   private Integer id;
   private String beneFirstName;
   private String beneMidInitial;
   private String beneLastName;
   private String beneSSN;
   private String beneDOB;
   private String beneRel;
   private String typeOfBeneficiary;
   private String perStripes;
   private Double sharePerc;

   public Beneficiary()
   {
   }

   public Integer getId()
   {
      return id;
   }

   public void setId(Integer id)
   {
      this.id = id;
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
}
