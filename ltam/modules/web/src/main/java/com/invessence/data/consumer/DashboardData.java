package com.invessence.data.consumer;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 1/12/16
 * Time: 4:38 PM
 * To change this template use File | Settings | File Templates.
 */
public class DashboardData
{
   private Long logonid;
   private Long acctnum;
   private String functionid;
   private String role;
   private String privileges;
   private String firstName;
   private String lastName;
   private Double investment;
   private Double riskIndex;
   private String managed;
   private String dateOpened;
   private String clientAccountID;
   private String description;

   public DashboardData()
   {
   }

   public DashboardData(Long logonid, Long acctnum,
                        String functionid, String role,
                        String privileges, String firstName, String lastName,
                        Double investment, Double riskIndex, String managed,
                        String dateOpened, String clientAccountID, String description)
   {
      this.logonid = logonid;
      this.acctnum = acctnum;
      this.functionid = functionid;
      this.role = role;
      this.privileges = privileges;
      this.firstName = firstName;
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

   public String getFunctionid()
   {
      return functionid;
   }

   public void setFunctionid(String functionid)
   {
      this.functionid = functionid;
   }

   public String getRole()
   {
      return role;
   }

   public void setRole(String role)
   {
      this.role = role;
   }

   public String getPrivileges()
   {
      return privileges;
   }

   public void setPrivileges(String privileges)
   {
      this.privileges = privileges;
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
}
