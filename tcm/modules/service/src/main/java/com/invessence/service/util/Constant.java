package com.invessence.service.util;

/**
 * Created by abhangp on 5/19/2016.
 */
public class Constant
{

   public enum SERVICES
   {
      PRICING("PRICING"),BROKER_SERVICES("BROKER-SERVICES"),BROKER_WEBSERVICES("BROKER-WEBSERVICES"),DOCUSIGN_SERVICES("DOCUSIGN-SERVICES");
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
}
