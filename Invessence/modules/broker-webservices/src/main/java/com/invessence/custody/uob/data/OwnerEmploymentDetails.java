package com.invessence.custody.uob.data;

/**
 * Created by abhangp on 11/21/2017.
 */
public class OwnerEmploymentDetails
{
   private Long acctnum;
   private Integer acctOwnerId;
   private Integer emplId;
   private String emplTypeId;
   private String sourceOfIncome;
   private String employerName;
   private String occupation;
   private String typeOfBusiness;
   private String employerStreetAddress1;
   private String employerStreetAddress2;
   private String employerStreetAddress3;
   private String employerStreetAddress4;
   private String employerCity;
   private String employerState;
   private String employerZipCode;
   private String employerZipCountry;
   private String fromDate;
   private String toDate;
   private String createdBy;
   private String updatedBy;

   @Override
   public String toString()
   {
      return "OwnerEmploymentDetails{" +
         "acctnum=" + acctnum +
         ", acctOwnerId=" + acctOwnerId +
         ", emplId=" + emplId +
         ", emplTypeId='" + emplTypeId + '\'' +
         ", sourceOfIncome='" + sourceOfIncome + '\'' +
         ", employerName='" + employerName + '\'' +
         ", occupation='" + occupation + '\'' +
         ", typeOfBusiness='" + typeOfBusiness + '\'' +
         ", employerStreetAddress1='" + employerStreetAddress1 + '\'' +
         ", employerStreetAddress2='" + employerStreetAddress2 + '\'' +
         ", employerStreetAddress3='" + employerStreetAddress3 + '\'' +
         ", employerStreetAddress4='" + employerStreetAddress4 + '\'' +
         ", employerCity='" + employerCity + '\'' +
         ", employerState='" + employerState + '\'' +
         ", employerZipCode='" + employerZipCode + '\'' +
         ", employerZipCountry='" + employerZipCountry + '\'' +
         ", fromDate='" + fromDate + '\'' +
         ", toDate='" + toDate + '\'' +
         ", createdBy='" + createdBy + '\'' +
         ", updatedBy='" + updatedBy + '\'' +
         '}';
   }

   public Long getAcctnum()
   {
      return acctnum;
   }

   public void setAcctnum(Long acctnum)
   {
      this.acctnum = acctnum;
   }

   public Integer getAcctOwnerId()
   {
      return acctOwnerId;
   }

   public void setAcctOwnerId(Integer acctOwnerId)
   {
      this.acctOwnerId = acctOwnerId;
   }

   public Integer getEmplId()
   {
      return emplId;
   }

   public void setEmplId(Integer emplId)
   {
      this.emplId = emplId;
   }

   public String getEmplTypeId()
   {
      return emplTypeId;
   }

   public void setEmplTypeId(String emplTypeId)
   {
      this.emplTypeId = emplTypeId;
   }

   public String getSourceOfIncome()
   {
      return sourceOfIncome;
   }

   public void setSourceOfIncome(String sourceOfIncome)
   {
      this.sourceOfIncome = sourceOfIncome;
   }

   public String getEmployerName()
   {
      return employerName;
   }

   public void setEmployerName(String employerName)
   {
      this.employerName = employerName;
   }

   public String getOccupation()
   {
      return occupation;
   }

   public void setOccupation(String occupation)
   {
      this.occupation = occupation;
   }

   public String getTypeOfBusiness()
   {
      return typeOfBusiness;
   }

   public void setTypeOfBusiness(String typeOfBusiness)
   {
      this.typeOfBusiness = typeOfBusiness;
   }

   public String getEmployerStreetAddress1()
   {
      return employerStreetAddress1;
   }

   public void setEmployerStreetAddress1(String employerStreetAddress1)
   {
      this.employerStreetAddress1 = employerStreetAddress1;
   }

   public String getEmployerStreetAddress2()
   {
      return employerStreetAddress2;
   }

   public void setEmployerStreetAddress2(String employerStreetAddress2)
   {
      this.employerStreetAddress2 = employerStreetAddress2;
   }

   public String getEmployerStreetAddress3()
   {
      return employerStreetAddress3;
   }

   public void setEmployerStreetAddress3(String employerStreetAddress3)
   {
      this.employerStreetAddress3 = employerStreetAddress3;
   }

   public String getEmployerStreetAddress4()
   {
      return employerStreetAddress4;
   }

   public void setEmployerStreetAddress4(String employerStreetAddress4)
   {
      this.employerStreetAddress4 = employerStreetAddress4;
   }

   public String getEmployerCity()
   {
      return employerCity;
   }

   public void setEmployerCity(String employerCity)
   {
      this.employerCity = employerCity;
   }

   public String getEmployerState()
   {
      return employerState;
   }

   public void setEmployerState(String employerState)
   {
      this.employerState = employerState;
   }

   public String getEmployerZipCode()
   {
      return employerZipCode;
   }

   public void setEmployerZipCode(String employerZipCode)
   {
      this.employerZipCode = employerZipCode;
   }

   public String getEmployerZipCountry()
   {
      return employerZipCountry;
   }

   public void setEmployerZipCountry(String employerZipCountry)
   {
      this.employerZipCountry = employerZipCountry;
   }

   public String getFromDate()
   {
      return fromDate;
   }

   public void setFromDate(String fromDate)
   {
      this.fromDate = fromDate;
   }

   public String getToDate()
   {
      return toDate;
   }

   public void setToDate(String toDate)
   {
      this.toDate = toDate;
   }

   public String getCreatedBy()
   {
      return createdBy;
   }

   public void setCreatedBy(String createdBy)
   {
      this.createdBy = createdBy;
   }

   public String getUpdatedBy()
   {
      return updatedBy;
   }

   public void setUpdatedBy(String updatedBy)
   {
      this.updatedBy = updatedBy;
   }
}
