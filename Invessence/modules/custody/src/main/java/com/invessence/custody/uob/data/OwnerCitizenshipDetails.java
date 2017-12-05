package com.invessence.custody.uob.data;

/**
 * Created by abhangp on 11/6/2017.
 */
public class OwnerCitizenshipDetails
{
   private String countryOfBirth;
   private String countryOfCitizenship;
   private String countryOfDualCitizenship;
   private String nationality;
   private String nationalitySpecify;
   private String permanentResidentOfSingapore;

   @Override
   public String toString()
   {
      return "OwnerCitizenshipDetails{" +
         "countryOfBirth='" + countryOfBirth + '\'' +
         ", countryOfCitizenship='" + countryOfCitizenship + '\'' +
         ", countryOfDualCitizenship='" + countryOfDualCitizenship + '\'' +
         ", nationality='" + nationality + '\'' +
         ", nationalitySpecify='" + nationalitySpecify + '\'' +
         ", permanentResidentOfSingapore='" + permanentResidentOfSingapore + '\'' +
         '}';
   }

   public String getCountryOfBirth()
   {
      return countryOfBirth;
   }

   public void setCountryOfBirth(String countryOfBirth)
   {
      this.countryOfBirth = countryOfBirth;
   }

   public String getCountryOfCitizenship()
   {
      return countryOfCitizenship;
   }

   public void setCountryOfCitizenship(String countryOfCitizenship)
   {
      this.countryOfCitizenship = countryOfCitizenship;
   }

   public String getCountryOfDualCitizenship()
   {
      return countryOfDualCitizenship;
   }

   public void setCountryOfDualCitizenship(String countryOfDualCitizenship)
   {
      this.countryOfDualCitizenship = countryOfDualCitizenship;
   }

   public String getNationality()
   {
      return nationality;
   }

   public void setNationality(String nationality)
   {
      this.nationality = nationality;
   }

   public String getNationalitySpecify()
   {
      return nationalitySpecify;
   }

   public void setNationalitySpecify(String nationalitySpecify)
   {
      this.nationalitySpecify = nationalitySpecify;
   }

   public String getPermanentResidentOfSingapore()
   {
      return permanentResidentOfSingapore;
   }

   public void setPermanentResidentOfSingapore(String permanentResidentOfSingapore)
   {
      this.permanentResidentOfSingapore = permanentResidentOfSingapore;
   }
}
