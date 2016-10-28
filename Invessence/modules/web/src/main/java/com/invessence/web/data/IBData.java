package com.invessence.web.data;

import java.text.DecimalFormat;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 1/9/14
 * Time: 8:58 PM
 * To change this template use File | Settings | File Templates.
 */
@SuppressWarnings("UnusedDeclaration")
public class IBData
{
   private String clientAccountID, accountAlias;
   private String currencyPrimary;
   private String name, accountType, customerType;
   private String accountCapabilities, tradingPermissions;
   private String dateOpened, dateClosed;
   private String street, street2, city, state, country, postalCode;
   private String emailPrimary, emailSecondary, phoneNo, altPhoneNo, status;
   private Double startingCash;
   private String acctnum;

   public String getClientAccountID()
   {
      return clientAccountID;
   }

   public void setClientAccountID(String clientAccountID)
   {
      this.clientAccountID = clientAccountID;
   }

   public String getAccountAlias()
   {
      return accountAlias;
   }

   public void setAccountAlias(String accountAlias)
   {
      this.accountAlias = accountAlias;
   }

   public String getCurrencyPrimary()
   {
      return currencyPrimary;
   }

   public void setCurrencyPrimary(String currencyPrimary)
   {
      this.currencyPrimary = currencyPrimary;
   }

   public String getName()
   {
      return name;
   }

   public void setName(String name)
   {
      this.name = name;
   }

   public String getAccountType()
   {
      return accountType;
   }

   public void setAccountType(String accountType)
   {
      this.accountType = accountType;
   }

   public String getCustomerType()
   {
      return customerType;
   }

   public void setCustomerType(String customerType)
   {
      this.customerType = customerType;
   }

   public String getAccountCapabilities()
   {
      return accountCapabilities;
   }

   public void setAccountCapabilities(String accountCapabilities)
   {
      this.accountCapabilities = accountCapabilities;
   }

   public String getTradingPermissions()
   {
      return tradingPermissions;
   }

   public void setTradingPermissions(String tradingPermissions)
   {
      this.tradingPermissions = tradingPermissions;
   }

   public String getDateOpened()
   {
      return dateOpened;
   }

   public void setDateOpened(String dateOpened)
   {
      this.dateOpened = dateOpened;
   }

   public String getDateClosed()
   {
      return dateClosed;
   }

   public void setDateClosed(String dateClosed)
   {
      this.dateClosed = dateClosed;
   }

   public String getStreet()
   {
      return street;
   }

   public void setStreet(String street)
   {
      this.street = street;
   }

   public String getStreet2()
   {
      return street2;
   }

   public void setStreet2(String street2)
   {
      this.street2 = street2;
   }

   public String getCity()
   {
      return city;
   }

   public void setCity(String city)
   {
      this.city = city;
   }

   public String getState()
   {
      return state;
   }

   public void setState(String state)
   {
      this.state = state;
   }

   public String getCountry()
   {
      return country;
   }

   public void setCountry(String country)
   {
      this.country = country;
   }

   public String getPostalCode()
   {
      return postalCode;
   }

   public void setPostalCode(String postalCode)
   {
      this.postalCode = postalCode;
   }

   public String getEmailPrimary()
   {
      return emailPrimary;
   }

   public void setEmailPrimary(String emailPrimary)
   {
      this.emailPrimary = emailPrimary;
   }

   public String getEmailSecondary()
   {
      return emailSecondary;
   }

   public void setEmailSecondary(String emailSecondary)
   {
      this.emailSecondary = emailSecondary;
   }

   public String getPhoneNo()
   {
      return phoneNo;
   }

   public void setPhoneNo(String phoneNo)
   {
      this.phoneNo = phoneNo;
   }

   public String getAltPhoneNo()
   {
      return altPhoneNo;
   }

   public void setAltPhoneNo(String altPhoneNo)
   {
      this.altPhoneNo = altPhoneNo;
   }

   public String getStatus()
   {
      return status;
   }

   public void setStatus(String status)
   {
      this.status = status;
   }

   public Double getStartingCash()
   {
      return startingCash;
   }

   public void setStartingCash(Double startingCash)
   {
      this.startingCash = startingCash;
   }

   public String getStrStartingCash() {
      if (startingCash != null) {
         DecimalFormat df = new DecimalFormat("###,####,###.00");
         String strValue = df.format(startingCash);
         return strValue;
      }
      return "";

   }

   public String getAcctnum()
   {
      return acctnum;
   }

   public void setAcctnum(String acctnum)
   {
      this.acctnum = acctnum;
   }
}
