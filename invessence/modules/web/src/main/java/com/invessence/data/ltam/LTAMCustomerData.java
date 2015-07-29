package com.invessence.data.ltam;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 7/20/15
 * Time: 2:34 PM
 * To change this template use File | Settings | File Templates.
 */
public class LTAMCustomerData extends LTAMRiskQuestions
{
   private String cid;
   private String advisor;
   private String rep;
   private Long useruid;
   private String prefix, firstname, lastname, suffix;
   private String displayFullName;
   private Integer age;
   private String accttype;
   private Boolean taxable;
   private Double horizon;
   private Double Investment;

   public String getCid()
   {
      return cid;
   }

   public void setCid(String cid)
   {
      this.cid = cid;
   }

   public String getAdvisor()
   {
      return advisor;
   }

   public void setAdvisor(String advisor)
   {
      this.advisor = advisor;
   }

   public String getRep()
   {
      return rep;
   }

   public void setRep(String rep)
   {
      this.rep = rep;
   }

   public Long getUseruid()
   {
      return useruid;
   }

   public void setUseruid(Long useruid)
   {
      this.useruid = useruid;
   }

   public String getPrefix()
   {
      return prefix;
   }

   public void setPrefix(String prefix)
   {
      this.prefix = prefix;
   }

   public String getFirstname()
   {
      return firstname;
   }

   public void setFirstname(String firstname)
   {
      this.firstname = firstname;
   }

   public String getLastname()
   {
      return lastname;
   }

   public void setLastname(String lastname)
   {
      this.lastname = lastname;
   }

   public String getSuffix()
   {
      return suffix;
   }

   public void setSuffix(String suffix)
   {
      this.suffix = suffix;
   }

   public String getDisplayFullName()
   {
      return displayFullName;
   }

   public void setDisplayFullName(String displayFullName)
   {
      this.displayFullName = displayFullName;
   }

   public Integer getAge()
   {
      return age;
   }

   public void setAge(Integer age)
   {
      this.age = age;
   }

   public String getAccttype()
   {
      return accttype;
   }

   public void setAccttype(String accttype)
   {
      this.accttype = accttype;
   }

   public Boolean getTaxable()
   {
      return taxable;
   }

   public void setTaxable(Boolean taxable)
   {
      this.taxable = taxable;
   }

   public Double getHorizon()
   {
      return horizon;
   }

   public void setHorizon(Double horizon)
   {
      this.horizon = horizon;
   }

   public Double getInvestment()
   {
      return Investment;
   }

   public void setInvestment(Double investment)
   {
      Investment = investment;
   }
}
