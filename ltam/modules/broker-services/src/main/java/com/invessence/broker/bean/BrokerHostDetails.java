package com.invessence.broker.bean;

/**
 * Created by abhangp on 1/19/2016.
 */
public class BrokerHostDetails
{
   private String vendor;
   private String environment;
   private String host;
   private String username;
   private String password;
   private String sourcedir;

   public String getEnvironment()
   {
      return environment;
   }

   public void setEnvironment(String environment)
   {
      this.environment = environment;
   }

   public String getHost()
   {
      return host;
   }

   public void setHost(String host)
   {
      this.host = host;
   }

   public String getPassword()
   {
      return password;
   }

   public void setPassword(String password)
   {
      this.password = password;
   }

   public String getSourcedir()
   {
      return sourcedir;
   }

   public void setSourcedir(String sourcedir)
   {
      this.sourcedir = sourcedir;
   }

   public String getUsername()
   {
      return username;
   }

   public void setUsername(String username)
   {
      this.username = username;
   }

   public String getVendor()
   {
      return vendor;
   }

   public void setVendor(String vendor)
   {
      this.vendor = vendor;
   }

   @Override
   public String toString()
   {
      return "BrokerHostDetails{" +
         "environment='" + environment + '\'' +
         ", vendor='" + vendor + '\'' +
         ", host='" + host + '\'' +
         ", username='" + username + '\'' +
         ", password='" + password + '\'' +
         ", sourcedir='" + sourcedir + '\'' +
         '}';
   }
}
