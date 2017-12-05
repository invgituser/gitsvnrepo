package com.invessence.service.util;

/**
 * Created by abhangp on 5/19/2016.
 */
public class Constant
{

   public enum SERVICES
   {
      PRICING("PRICING"), DOWNLOAD_SERVICES("DOWNLOAD-SERVICES"),
      BROKER_WEBSERVICES("BROKER-WEBSERVICES"), DOCUSIGN_SERVICES("DOCUSIGN-SERVICES"),
      EMAIL_SERVICE("EMAIL-SERVICE"), AGGREGATION_SERVICES("AGGREGATION-SERVICES"),
      FILE_PROCESS("FILE-PROCESS"),
      CRM_SERVICES("CRM-SERVICES"),
      DOCUMENT_SERVICES("DOCUMENT-SERVICES"),
      CUSTODY_SERVICES("CUSTODY-SERVICES");
      private String value;

      private SERVICES(String value)
      {
         this.value = value;
      }

      private String getValue()
      {
         return value;
      }

      @Override
      public String toString()
      {
         return this.getValue();
      }
   }

   public enum CUSTODY_SERVICES
   {
      TD("TD"), IB("IB"), UOB("UOB");
      private String value;

      private CUSTODY_SERVICES(String value)
      {
         this.value = value;
      }

      private String getValue()
      {
         return value;
      }

      @Override
      public String toString()
      {
         return this.getValue();
      }
   }
   public enum DOCUMENT_SERVICES
   {
      iText("iText"), DROPBOX("DROPBOX"), DOCUSIGN("DOCUSIGN");
      private String value;

      private DOCUMENT_SERVICES(String value)
      {
         this.value = value;
      }

      private String getValue()
      {
         return value;
      }

      @Override
      public String toString()
      {
         return this.getValue();
      }
   }


   public enum CRM_SERVICES
   {
      REDTAIL("REDTAIL"), SALESFORCE("SALESFORCE");
      private String value;

      private CRM_SERVICES(String value)
      {
         this.value = value;
      }

      private String getValue()
      {
         return value;
      }

      @Override
      public String toString()
      {
         return this.getValue();
      }
   }

   public enum AGGREGATION_SERVICES
   {
      MX("MX"), YODLEE("YODLEE");
      private String value;

      private AGGREGATION_SERVICES(String value)
      {
         this.value = value;
      }

      private String getValue()
      {
         return value;
      }

      @Override
      public String toString()
      {
         return this.getValue();
      }
   }

   public enum DOCUSIGN_SERVICES
   {
      DOCUSIGN("DOCUSIGN");
      private String value;

      private DOCUSIGN_SERVICES(String value)
      {
         this.value = value;
      }

      private String getValue()
      {
         return value;
      }

      @Override
      public String toString()
      {
         return this.getValue();
      }
   }

   public enum BROKER_WEBSERVICES
   {
      GEMINI("GEMINI"), TD("TD");
      private String value;

      private BROKER_WEBSERVICES(String value)
      {
         this.value = value;
      }

      private String getValue()
      {
         return value;
      }

      @Override
      public String toString()
      {
         return this.getValue();
      }
   }

   public enum DOWNLOAD_SERVICES
   {
      GEMINI("GEMINI");
      private String value;

      private DOWNLOAD_SERVICES(String value)
      {
         this.value = value;
      }

      private String getValue()
      {
         return value;
      }

      @Override
      public String toString()
      {
         return this.getValue();
      }
   }

   public enum GENERIC_DETAILS
   {
      COMMON_EXCEPTION_DETAILS("COMMON_EXCEPTION_DETAILS"), LOOKUP_DETAILS("LOOKUP_DETAILS"), COUNTRY("COUNTRY");
      private String value;

      private GENERIC_DETAILS(String value)
      {
         this.value = value;
      }

      private String getValue()
      {
         return value;
      }

      @Override
      public String toString()
      {
         return this.getValue();
      }
   }

   public enum EMAIL_SERVICE
   {
      INVESSENCE_GMAIL("INVESSENCE-GMAIL"), SYMBIL_MAILGUN("SYMBIL-MAILGUN"), BB_GMAL("BB-GMAIL");
      private String value;

      private EMAIL_SERVICE(String value)
      {
         this.value = value;
      }

      private String getValue()
      {
         return value;
      }

      @Override
      public String toString()
      {
         return this.getValue();
      }
   }

   public enum SERVICES_DETAILS
   {
      CONFIG_DETAILS("CONFIG_DETAILS"),
      ADDITIONAL_DETAILS("ADDITIONAL_DETAILS"),
      EXCEPTION_DETAILS("EXCEPTION_DETAILS"),
      OPERATION_DETAILS("OPERATION_DETAILS"),
      COMMON_DETAILS("COMMON_DETAILS"),;
      private String value;

      private SERVICES_DETAILS(String value)
      {
         this.value = value;
      }

      private String getValue()
      {
         return value;
      }

      @Override
      public String toString()
      {
         return this.getValue();
      }
   }

   public enum ADDITIONAL_DETAILS
   {
      TEMPLATE_DETAILS("TEMPLATE_DETAILS"), DOCUMENT_DETAILS("DOCUMENT_DETAILS"),
      FILE_DETAILS("FILE_DETAILS"), PDF_FILE_DETAILS("PDF_FILE_DETAILS");
      private String value;

      private ADDITIONAL_DETAILS(String value)
      {
         this.value = value;
      }

      private String getValue()
      {
         return value;
      }

      @Override
      public String toString()
      {
         return this.getValue();
      }
   }


   public enum COMMON_DETAILS
   {
      DOCUSIGN_MAPPING("DOCUSIGN_MAPPING"),FILE_RULES("FILE_RULES"),PDF_FILE_RULES("PDF_FILE_RULES");
      private String value;

      private COMMON_DETAILS(String value)
      {
         this.value = value;
      }

      private String getValue()
      {
         return value;
      }

      @Override
      public String toString()
      {
         return this.getValue();
      }
   }

   public enum PRICING
   {
      CSIDATA("CSIDATA"), YAHOO("YAHOO"), XIGNITE("XIGNITE"),FIS("FIS");
      private String value;

      private PRICING(String value)
      {
         this.value = value;
      }

      private String getValue()
      {
         return value;
      }

      @Override
      public String toString()
      {
         return this.getValue();
      }
   }
}
