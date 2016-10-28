package com.invessence.data.consumer.CTO;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 11/2/15
 * Time: 2:40 PM
 * To change this template use File | Settings | File Templates.
 */
public class InvestorData
{
   private String accounttype;
   private Boolean taxable;
   private AccountHolder primaryaccountholder;
   private ArrayList<AccountHolder> jointOwnership;

   private AddressData legaladdress;
   private AddressData mailingaddress;
   private Boolean hasMailingAddress;

   private ArrayList<Employment> employmentArrayList;
   private Boolean ownsMoreThen10percent;

   private ArrayList<Regulatory> regulatoryArrayList;

   private Financial financial;

   private ArrayList<Beneficiary> beneficiary;

   public InvestorData()
   {
      jointOwnership = new ArrayList<AccountHolder>();
      employmentArrayList = new ArrayList<Employment>();
      regulatoryArrayList = new ArrayList<Regulatory>();
      beneficiary = new ArrayList<Beneficiary>();
   }

   public String getAccounttype()
   {
      return accounttype;
   }

   public void setAccounttype(String accounttype)
   {
      this.accounttype = accounttype;
   }

   public Boolean getTaxable()
   {
      return taxable;
   }

   public void setTaxable(Boolean taxable)
   {
      this.taxable = taxable;
   }

   public AccountHolder getPrimaryaccountholder()
   {
      return primaryaccountholder;
   }

   public void setPrimaryaccountholder(AccountHolder primaryaccountholder)
   {
      this.primaryaccountholder = primaryaccountholder;
   }

   public ArrayList<AccountHolder> getJointOwnership()
   {
      return jointOwnership;
   }

   public void setJointOwnership(ArrayList<AccountHolder> jointOwnership)
   {
      this.jointOwnership = jointOwnership;
   }

   public AddressData getLegaladdress()
   {
      return legaladdress;
   }

   public void setLegaladdress(AddressData legaladdress)
   {
      this.legaladdress = legaladdress;
   }

   public AddressData getMailingaddress()
   {
      return mailingaddress;
   }

   public void setMailingaddress(AddressData mailingaddress)
   {
      this.mailingaddress = mailingaddress;
   }

   public Boolean getHasMailingAddress()
   {
      return hasMailingAddress;
   }

   public void setHasMailingAddress(Boolean hasMailingAddress)
   {
      this.hasMailingAddress = hasMailingAddress;
   }

   public ArrayList<Employment> getEmploymentArrayList()
   {
      return employmentArrayList;
   }

   public void setEmploymentArrayList(ArrayList<Employment> employmentArrayList)
   {
      this.employmentArrayList = employmentArrayList;
   }

   public Boolean getOwnsMoreThen10percent()
   {
      return ownsMoreThen10percent;
   }

   public void setOwnsMoreThen10percent(Boolean ownsMoreThen10percent)
   {
      this.ownsMoreThen10percent = ownsMoreThen10percent;
   }

   public ArrayList<Regulatory> getRegulatoryArrayList()
   {
      return regulatoryArrayList;
   }

   public void setRegulatoryArrayList(ArrayList<Regulatory> regulatoryArrayList)
   {
      this.regulatoryArrayList = regulatoryArrayList;
   }

   public Financial getFinancial()
   {
      return financial;
   }

   public void setFinancial(Financial financial)
   {
      this.financial = financial;
   }

   public ArrayList<Beneficiary> getBeneficiary()
   {
      return beneficiary;
   }

   public void setBeneficiary(ArrayList<Beneficiary> beneficiary)
   {
      this.beneficiary = beneficiary;
   }
}


