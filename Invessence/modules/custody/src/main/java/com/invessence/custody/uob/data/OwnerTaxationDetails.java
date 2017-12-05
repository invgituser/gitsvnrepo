package com.invessence.custody.uob.data;

/**
 * Created by abhangp on 11/20/2017.
 */
public class OwnerTaxationDetails
{
   private String isTINAvailable;
   private String jurisdictionOfTaxResidence;
   private String taxIdentificationNumber;
   private String tinUnavailableReason;
   private String tinUnavailableValue;
   private int id;
   private String category="Taxation";

   public OwnerTaxationDetails(){

   }
   public OwnerTaxationDetails(int id)
   {
      this.id = id;
   }

   @Override
   public String toString()
   {
      return "OwnerTaxationDetails{" +
         "isTINAvailable='" + isTINAvailable + '\'' +
         ", jurisdictionOfTaxResidence='" + jurisdictionOfTaxResidence + '\'' +
         ", taxIdentificationNumber='" + taxIdentificationNumber + '\'' +
         ", tinUnavailableReason='" + tinUnavailableReason + '\'' +
         ", tinUnavailableValue='" + tinUnavailableValue + '\'' +
         ", id=" + id +
         ", category='" + category + '\'' +
         '}';
   }

   public boolean getTinFlag()
   {
      if(getIsTINAvailable()==null || getIsTINAvailable().equalsIgnoreCase("No")){

         return false;
      }else{
         return true;
      }
   }

   public String getIsTINAvailable()
   {
      return isTINAvailable;
   }

   public void setIsTINAvailable(String isTINAvailable)
   {
      this.isTINAvailable = isTINAvailable;
   }

   public String getJurisdictionOfTaxResidence()
   {
      return jurisdictionOfTaxResidence;
   }

   public void setJurisdictionOfTaxResidence(String jurisdictionOfTaxResidence)
   {
      this.jurisdictionOfTaxResidence = jurisdictionOfTaxResidence;
   }

   public String getTaxIdentificationNumber()
   {
      return taxIdentificationNumber;
   }

   public void setTaxIdentificationNumber(String taxIdentificationNumber)
   {
      this.taxIdentificationNumber = taxIdentificationNumber;
   }

   public String getTinUnavailableReason()
   {
      return tinUnavailableReason;
   }

   public void setTinUnavailableReason(String tinUnavailableReason)
   {
      this.tinUnavailableReason = tinUnavailableReason;
   }

   public String getTinUnavailableValue()
   {
      return tinUnavailableValue;
   }

   public void setTinUnavailableValue(String tinUnavailableValue)
   {
      this.tinUnavailableValue = tinUnavailableValue;
   }

   public int getId()
   {
      return id;
   }

   public void setId(int id)
   {
      this.id = id;
   }

   public String getCategory()
   {
      return category;
   }

   public void setCategory(String category)
   {
      this.category = category;
   }
}
