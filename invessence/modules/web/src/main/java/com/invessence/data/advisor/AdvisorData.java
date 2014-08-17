package com.invessence.data.advisor;

import java.util.*;

import com.invessence.data.*;
import com.invmodel.asset.AssetAllocationModel;
import com.invmodel.portfolio.PortfolioModel;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 1/9/14
 * Time: 8:58 PM
 * To change this template use File | Settings | File Templates.
 */
@SuppressWarnings("UnusedDeclaration")
public class AdvisorData extends ManageGoals
{
   private Long clientLogonID;
   private String clientFirstName;
   private String clientLastname;
   private String clientEmail;
   private String action;
   private String actionIcon;

   private List<String> filteredOption;
   private List<DataPortfolio> excludeList = new ArrayList<DataPortfolio>();


   public Long getClientLogonID()
   {
      return clientLogonID;
   }

   public void setClientLogonID(Long clientLogonID)
   {
      this.clientLogonID = clientLogonID;
   }

   public String getClientFirstName()
   {
      return clientFirstName;
   }

   public void setClientFirstName(String clientFirstName)
   {
      this.clientFirstName = clientFirstName;
   }

   public String getClientLastname()
   {
      return clientLastname;
   }

   public void setClientLastname(String clientLastname)
   {
      this.clientLastname = clientLastname;
   }

   public String getClientsFullName() {
      String name = null;
      if (getClientLastname() != null)
         name = getClientLastname();

      if (getClientFirstName() != null) {
         if (name != null)
            name = name + ", " + getClientFirstName();
         else
            name =  getClientFirstName();
      }

      return name;
   }

   public String getClientEmail()
   {
      return clientEmail;
   }

   public void setClientEmail(String clientEmail)
   {
      this.clientEmail = clientEmail;
   }

   public List<String> getFilteredOption()
   {
      return filteredOption;
   }

   public void setFilteredOption(List<String> filteredOption)
   {
      this.filteredOption = filteredOption;
   }

   public List<DataPortfolio> getExcludeList()
   {
      return excludeList;
   }

   public void setExcludeList(List<DataPortfolio> excludeList)
   {
      this.excludeList = excludeList;
   }

   public String getAction()
   {
      return action;
   }

   public void setAction(String action)
   {
      this.action = action;
      if (this.action != null)
      {
         if (this.action.equalsIgnoreCase("Edit"))
         {
            setActionIcon("ui-icon-pencil");
         }
         else
         {
            setActionIcon("ui-icon-circle-plus");
         }
      }
   }

   public String getActionIcon()
   {
      return this.actionIcon;
   }

   public void setActionIcon(String actionIcon)
   {
      this.actionIcon = actionIcon;
   }
}
