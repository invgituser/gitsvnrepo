package com.invessence.web.data.custody;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 8/1/16
 * Time: 11:01 AM
 * To change this template use File | Settings | File Templates.
 */
public class Employment
{
   private Long   acctnum;           // acctOwnerId
   private Integer id;
   private String employerName;
   private String occupation;
   private String typeOfBusiness;
   private String employerStreetAddress;
   private String employerCity;
   private String employerState;
   private String employerZipCode;
   private String fromDate;
   private String toDate;

   public Employment()
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

   public String getEmployerStreetAddress()
   {
      return employerStreetAddress;
   }

   public void setEmployerStreetAddress(String employerStreetAddress)
   {
      this.employerStreetAddress = employerStreetAddress;
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

}
