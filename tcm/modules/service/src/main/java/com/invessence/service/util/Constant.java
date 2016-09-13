package com.invessence.service.util;

/**
 * Created by abhangp on 5/19/2016.
 */
public class Constant
{

   public enum SERVICES
   {
      PRICING("PRICING"),BROKER_SERVICES("BROKER-SERVICES"),
      BROKER_WEBSERVICES("BROKER-WEBSERVICES"),DOCUSIGN_SERVICES("DOCUSIGN-SERVICES"),
      EMAIL_SERVICE("EMAIL-SERVICE");
      private String value;

      private SERVICES(String value) {
         this.value = value;
      }
      private String getValue() {
         return value;
      }

      @Override
      public String toString() {
         return this.getValue();
      }
   }

   public enum DOCUSIGN_SERVICES {
      DOCUSIGN("DOCUSIGN");
      private String value;

      private DOCUSIGN_SERVICES(String value) {
         this.value = value;
      }
      private String getValue() {
         return value;
      }

      @Override
      public String toString() {
         return this.getValue();
      }
   }

   public enum BROKER_WEBSERVICES {
      GEMINI("GEMINI"),TD("TD");
      private String value;

      private BROKER_WEBSERVICES(String value) {
         this.value = value;
      }
      private String getValue() {
         return value;
      }

      @Override
      public String toString() {
         return this.getValue();
      }
   }

   public enum BROKER_SERVICES {
      GEMINI("GEMINI");
      private String value;

      private BROKER_SERVICES(String value) {
         this.value = value;
      }
      private String getValue() {
         return value;
      }

      @Override
      public String toString() {
         return this.getValue();
      }
   }

   public enum GENERIC_DETAILS {
      LOOKUP_DETAILS("LOOKUP_DETAILS");
      private String value;

      private GENERIC_DETAILS(String value) {
         this.value = value;
      }
      private String getValue() {
         return value;
      }

      @Override
      public String toString() {
         return this.getValue();
      }
   }

   public enum ADDITIONAL_DETAILS {
      TEMPLATE_DETAILS("TEMPLATE_DETAILS"),DOCUMENT_DETAILS("DOCUMENT_DETAILS");
      private String value;

      private ADDITIONAL_DETAILS(String value) {
         this.value = value;
      }
      private String getValue() {
         return value;
      }

      @Override
      public String toString() {
         return this.getValue();
      }
   }

   public enum EMAIL_SERVICE {
      INVESSENCE_GMAIL("INVESSENCE-GMAIL"),SYMBIL_MAILGUN("SYMBIL-MAILGUN"),BB_GMAL("BB-GMAIL");
      private String value;

      private EMAIL_SERVICE(String value) {
         this.value = value;
      }
      private String getValue() {
         return value;
      }

      @Override
      public String toString() {
         return this.getValue();
      }
   }
}
