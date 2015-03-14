package com.invessence.data.advisor;

import java.util.*;

import com.invessence.data.common.ManageGoals;
import com.invmodel.Const.InvConst;

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
   private Map<String, String> advisorBasket;
   private List<String> rebalanceOption;


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
      weightedRisk = convertRiskWeight2Index(riskIndex.doubleValue());
      this.advisorRiskIndex = weightedRisk;
      setRiskIndex(riskIndex);
   }

   public void setAdvisorRiskIndex(Integer advisorRiskIndex)
   {
      Double weightedRisk;
      this.advisorRiskIndex = advisorRiskIndex;
      weightedRisk = convertIndex2RiskWeight(advisorRiskIndex);
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

   public Map<String,String> getAdvisorBasket()
   {
      return advisorBasket;
   }

   public void setAdvisorBasket(Map<String,String> advisorBasket)
   {
      this.advisorBasket = advisorBasket;
   }

   public String getThisBasket()
   {
      return getBasket();
   }

   // This happens in dropdown.  They select the KEY, so we are setting both KEY and value.
   public void setThisBasket(String value)
   {
      if (value == null) {
         setBasket(value, InvConst.DEFAULT_BASKET);
         setTheme(InvConst.DEFAULT_THEME);
      }
      else {
         if (this.advisorBasket.containsKey(value)) {
            setBasket(value, this.advisorBasket.get(value));
            setTheme(value);
         }
      }
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
      if (advisorBasket != null)
         advisorBasket.clear();
   }



}
