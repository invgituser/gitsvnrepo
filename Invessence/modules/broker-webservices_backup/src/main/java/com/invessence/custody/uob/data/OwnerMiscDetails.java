package com.invessence.custody.uob.data;

/**
 * Created by abhangp on 10/26/2017.
 */
public class OwnerMiscDetails
{

   private String fatherName;
   private String motherMaidenName;
   private String nameOfPrimarySchool;
   private String qualifications;
   private String qualificationsSpecify;
   private String mailAddressSameAsPhysical;

   @Override
   public String toString()
   {
      return "OwnerMiscDetails{" +
         "fatherName='" + fatherName + '\'' +
         ", motherMaidenName='" + motherMaidenName + '\'' +
         ", nameOfPrimarySchool='" + nameOfPrimarySchool + '\'' +
         ", qualifications='" + qualifications + '\'' +
         ", qualificationsSpecify='" + qualificationsSpecify + '\'' +
         ", mailAddressSameAsPhysical='" + mailAddressSameAsPhysical + '\'' +
         '}';
   }

   public String getMailAddressSameAsPhysical()
   {
      return mailAddressSameAsPhysical;
   }

   public void setMailAddressSameAsPhysical(String mailAddressSameAsPhysical)
   {
      this.mailAddressSameAsPhysical = mailAddressSameAsPhysical;
   }

   public String getFatherName()
   {
      return fatherName;
   }

   public void setFatherName(String fatherName)
   {
      this.fatherName = fatherName;
   }

   public String getMotherMaidenName()
   {
      return motherMaidenName;
   }

   public void setMotherMaidenName(String motherMaidenName)
   {
      this.motherMaidenName = motherMaidenName;
   }

   public String getNameOfPrimarySchool()
   {
      return nameOfPrimarySchool;
   }

   public void setNameOfPrimarySchool(String nameOfPrimarySchool)
   {
      this.nameOfPrimarySchool = nameOfPrimarySchool;
   }

   public String getQualifications()
   {
      return qualifications;
   }

   public void setQualifications(String qualifications)
   {
      this.qualifications = qualifications;
   }

   public String getQualificationsSpecify()
   {
      return qualificationsSpecify;
   }

   public void setQualificationsSpecify(String qualificationsSpecify)
   {
      this.qualificationsSpecify = qualificationsSpecify;
   }
}
