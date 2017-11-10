package com.invessence.custody.uob.data;

/**
 * Created by abhangp on 10/26/2017.
 */
public class OwnerIdentificationDetails
{
   private String  aadhaar;
   private String  nric;
   private String  pan;
   private String  passport;
   private String  ssn;

   @Override
   public String toString()
   {
      return "OwnerIdentificationDetails{" +
         "aadhaar='" + aadhaar + '\'' +
         ", nric='" + nric + '\'' +
         ", pan='" + pan + '\'' +
         ", passport='" + passport + '\'' +
         ", ssn='" + ssn + '\'' +
         '}';
   }

   public String getAadhaar()
   {
      return aadhaar;
   }

   public void setAadhaar(String aadhaar)
   {
      this.aadhaar = aadhaar;
   }

   public String getNric()
   {
      return nric;
   }

   public void setNric(String nric)
   {
      this.nric = nric;
   }

   public String getPan()
   {
      return pan;
   }

   public void setPan(String pan)
   {
      this.pan = pan;
   }

   public String getPassport()
   {
      return passport;
   }

   public void setPassport(String passport)
   {
      this.passport = passport;
   }

   public String getSsn()
   {
      return ssn;
   }

   public void setSsn(String ssn)
   {
      this.ssn = ssn;
   }
}
