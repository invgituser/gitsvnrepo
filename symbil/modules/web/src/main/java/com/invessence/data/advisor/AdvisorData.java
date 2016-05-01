package com.invessence.data.advisor;

import java.util.*;

import com.invessence.data.common.AccountData;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 1/9/14
 * Time: 8:58 PM
 * To change this template use File | Settings | File Templates.
 */
@SuppressWarnings("UnusedDeclaration")
public class AdvisorData extends AccountData
{

   private Integer advisorRiskIndex;

   private String action;
   private String actionIcon;

   private List<String> filteredOption;
   private List<String> rebalanceOption;


   public AdvisorData getInstance() {
      return this;
   }

   public Integer getAdvisorRiskIndex()
   {
      return advisorRiskIndex;
   }

   public List<String> getFilteredOption()
   {
      return filteredOption;
   }

   public void setFilteredOption(List<String> filteredOption)
   {
      this.filteredOption = filteredOption;
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
      // Clean up AccountData Data first.
      resetData();

      setAction(null);
      setActionIcon(null);

      if (filteredOption != null)
         filteredOption.clear();
   }



}
