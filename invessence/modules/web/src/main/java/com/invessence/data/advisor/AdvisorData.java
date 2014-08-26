package com.invessence.data.advisor;

import java.util.*;

import com.invessence.data.*;
import com.invmodel.asset.AssetAllocationModel;
import com.invmodel.portfolio.PortfolioModel;
import com.invmodel.portfolio.data.PortfolioSubclass;

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
   private Integer advisorRiskIndex;

   private String action;
   private String actionIcon;

   private List<String> filteredOption;
   private List<PortfolioSubclass> excludedSubAsset = new ArrayList<PortfolioSubclass>();
   private ArrayList<String> advisorBasket = new ArrayList<String>();

   public AdvisorData getInstance() {
      return this;
   }

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

   public Integer getAdvisorRiskIndex()
   {
      return advisorRiskIndex;
   }

   public void setConvertRiskIndex(Integer riskIndex)
   {
      Integer weightedRisk;
      weightedRisk = (int) (10.0 - (Math.round(riskIndex.doubleValue() / 2.9)));
      this.advisorRiskIndex = weightedRisk;
      setRiskIndex(riskIndex);
   }

   public void setAdvisorRiskIndex(Integer advisorRiskIndex)
   {
      Double weightedRisk;
      this.advisorRiskIndex = advisorRiskIndex;
      weightedRisk = 28 - ((2.0 * advisorRiskIndex.doubleValue()) + Math.round(advisorRiskIndex.doubleValue() / 1.2));
      setRiskIndex(weightedRisk.intValue());
   }

   public List<String> getFilteredOption()
   {
      return filteredOption;
   }

   public void setFilteredOption(List<String> filteredOption)
   {
      this.filteredOption = filteredOption;
   }

   public ArrayList<String> getAdvisorBasket()
   {
      return advisorBasket;
   }

   public void setAdvisorBasket(ArrayList<String> advisorBasket)
   {
      this.advisorBasket = advisorBasket;
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

   public List<PortfolioSubclass> getExcludedSubAsset()
   {
      return excludedSubAsset;
   }

   public void setExcludedSubAsset(List<PortfolioSubclass> excludedSubAsset)
   {
      this.excludedSubAsset = excludedSubAsset;
   }

   public void resetAdvisorData() {
      // Clean up ManageGoals Data first.
      resetManagedGoalData();

      setClientLogonID(null);
      setClientFirstName(null);
      setClientLastname(null);
      setClientEmail(null);
      setAction(null);
      setActionIcon(null);

      if (filteredOption != null)
         filteredOption.clear();
      if (excludedSubAsset != null)
         excludedSubAsset.clear();
      if (advisorBasket != null)
         advisorBasket.clear();



   }

}
