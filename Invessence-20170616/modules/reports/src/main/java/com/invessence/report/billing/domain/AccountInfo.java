package com.invessence.report.billing.domain;

public class AccountInfo
{
   private final String clientAccountId;
   private final String firstName;
   private final String lastName;
   private final String email;
   private final String advisor;
   private final Long   invessenceAccountId;
   private final String filename;
   private final String reportDate;
   private final String currentMonth;

   public AccountInfo(String clientAccountId, String firstName, String lastName, String email, String advisor,
                      Long invessenceAccountId, String filename, String reportDate, String currentMonth)
   {
      this.clientAccountId = clientAccountId;
      this.firstName = firstName;
      this.lastName = lastName;
      this.email = email;
      this.advisor = advisor;
      this.invessenceAccountId = invessenceAccountId;
      this.filename = filename;
      this.reportDate = reportDate;
      this.currentMonth = currentMonth;
   }

   public String getClientAccountId()
   {
      return clientAccountId;
   }

   public String getFirstName()
   {
      return firstName;
   }

   public String getLastName()
   {
      return lastName;
   }

   public String getEmail()
   {
      return email;
   }

   public String getAdvisor()
   {
      return advisor;
   }

   public Long getInvessenceAccountId()
   {
      return invessenceAccountId;
   }

   public String getFilename()
   {
      return filename;
   }

   public String getReportDate()
   {
      return reportDate;
   }

   public String getCurrentMonth()
   {
      return currentMonth;
   }
}

