package com.invessence.data.consumer;

import java.util.Comparator;

import com.invessence.converter.JavaUtil;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 1/12/16
 * Time: 4:38 PM
 * To change this template use File | Settings | File Templates.
 */
public class ConsumerData implements Comparable<ConsumerData>
{
   private Long logonid;
   private Long acctnum;
   private String advisor;
   private String rep;
   private String firstName;
   private String lastName;
   private Double investment;
   private Double riskIndex;
   private String managed;
   private String dateOpened;
   private String clientAccountID;
   private String description;

   private JavaUtil jutil = new JavaUtil();

   public ConsumerData()
   {
   }

   public ConsumerData(Long logonid, Long acctnum,
                       String advisor, String rep,
                       String firstName, String lastName,
                       Double investment, Double riskIndex, String managed,
                       String dateOpened, String clientAccountID, String description)
   {
      this.acctnum = acctnum;
      this.firstName = firstName;
      this.advisor = advisor;
      this.rep = rep;
      this.lastName = lastName;
      this.investment = investment;
      this.riskIndex = riskIndex;
      this.managed = managed;
      this.dateOpened = dateOpened;
      this.clientAccountID = clientAccountID;
      this.description = description;
   }

   public Long getLogonid()
   {
      return logonid;
   }

   public void setLogonid(Long logonid)
   {
      this.logonid = logonid;
   }

   public Long getAcctnum()
   {
      return acctnum;
   }

   public void setAcctnum(Long acctnum)
   {
      this.acctnum = acctnum;
   }

   public String getDisplayAccount() {
      if (clientAccountID == null)
         return ("Pending");
      else
         return jutil.getDisplayHiddenID(clientAccountID);
   }

   public String getAdvisor()
   {
      return advisor;
   }

   public void setAdvisor(String advisor)
   {
      this.advisor = advisor;
   }

   public String getRep()
   {
      return rep;
   }

   public void setRep(String rep)
   {
      this.rep = rep;
   }

   public String getFirstName()
   {
      return firstName;
   }

   public void setFirstName(String firstName)
   {
      this.firstName = firstName;
   }

   public String getLastName()
   {
      return lastName;
   }

   public void setLastName(String lastName)
   {
      this.lastName = lastName;
   }

   public String getFullname()
   {
      if (firstName == null && lastName == null)
      {
         return "Undefined";
      }

      if (firstName == null)
      {
         return lastName;
      }

      if (lastName == null)
      {
         return firstName;
      }

      return firstName + " " + lastName;
   }

   public Double getInvestment()
   {
      return investment;
   }

   public void setInvestment(Double investment)
   {
      this.investment = investment;
   }

   public Double getRiskIndex()
   {
      return riskIndex;
   }

   public void setRiskIndex(Double riskIndex)
   {
      this.riskIndex = riskIndex;
   }

   public String getManaged()
   {
      return managed;
   }

   public void setManaged(String managed)
   {
      this.managed = managed;
   }

   public String getDateOpened()
   {
      return dateOpened;
   }

   public void setDateOpened(String dateOpened)
   {
      this.dateOpened = dateOpened;
   }

   public String getClientAccountID()
   {
      return clientAccountID;
   }

   public void setClientAccountID(String clientAccountID)
   {
      this.clientAccountID = clientAccountID;
   }

   public String getDescription()
   {
      return description;
   }

   public void setDescription(String description)
   {
      this.description = description;
   }

   public static Comparator<ConsumerData> ConsumerDataDateOpenedComparator
      = new Comparator<ConsumerData>() {

      public int compare(ConsumerData date1, ConsumerData date2) {

         String dateOpen1 = date1.getDateOpened();
         String dateOpen2 = date2.getDateOpened();

         //ascending order
         // return dateOpen1.compareTo(dateOpen2);

         //descending order
         return dateOpen2.compareTo(dateOpen1);
      }
   };

   public int compareTo(ConsumerData compareInvestment) {

      int compareAmount = ((ConsumerData) compareInvestment).getInvestment().intValue();

      //ascending order
      // return this.investment.intValue() - compareAmount;

      //descending order
      return compareAmount - this.investment.intValue();

   }

   static class DateOpenedComparator implements Comparator {
      public int compare(Object o1, Object o2) {
         if (!(o1 instanceof ConsumerData) || !(o2 instanceof ConsumerData))
            throw new ClassCastException();

         String c1 = ((ConsumerData) o1).getDateOpened();
         String c2 = ((ConsumerData) o2).getDateOpened();

         // ascending order
         // return c1.compareTo(c2);

         // descending order
         return c2.compareTo(c1);

      }
   }

}
