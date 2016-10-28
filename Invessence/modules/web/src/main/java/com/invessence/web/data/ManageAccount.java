package com.invessence.web.data;

import java.io.Serializable;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: pichaimanir
 * Date: 8/19/13
 * Time: 4:50 PM
 * To change this template use File | Settings | File Templates.
 */
public class ManageAccount implements Serializable
{
   private String action;
   private String actionIcon;
   private Long logonid;
   private Long acctnum;
   private String IB_acctnum;
   private String functionid;
   private String role;
   private String privileges;
   private String goal;
   private String accounttype;
   private String acctstate;
   private String created;
   private String lastupdated;
   private Map<String, String> choices = new LinkedHashMap<String, String>();

   public ManageAccount()
   {
   }

   public String getAction()
   {
      return action;
   }

   public void setAction(String action)
   {
      this.action = action;
      if (action != null) {
         if (action.equalsIgnoreCase("Edit"))
            setActionIcon("ui-icon-pencil");
         else
            setActionIcon("ui-icon-circle-plus");
      }
   }

   public String getActionIcon()
   {
      return actionIcon;
   }

   public void setActionIcon(String actionIcon)
   {
      this.actionIcon = actionIcon;
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

   public String getIB_acctnum()
   {
      return IB_acctnum;
   }

   public void setIB_acctnum(String IB_acctnum)
   {
      this.IB_acctnum = IB_acctnum;
   }

   public String getDisplayAcctNum()
   {
      if (IB_acctnum == null)
         return this.acctnum.toString();
      if (IB_acctnum.equals("-"))
         return this.acctnum.toString();
      return IB_acctnum;
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

   public String getAccounttype()
   {
      return accounttype;
   }

   public void setAccounttype(String accounttype)
   {
      this.accounttype = accounttype;
   }

   public String getGoal()
   {
      return goal;
   }

   public void setGoal(String goal)
   {
      this.goal = goal;
   }

   public String getAcctstate()
   {
      return acctstate;
   }

   public void setAcctstate(String acctstate)
   {
      this.acctstate = acctstate;
   }

   public String getCreated()
   {
      return created;
   }

   public void setCreated(String created)
   {
      this.created = created;
   }

   public String getLastupdated()
   {
      return lastupdated;
   }

   public void setLastupdated(String lastupdated)
   {
      this.lastupdated = lastupdated;
   }

   public Map<String, String> getChoices()
   {
      return choices;
   }

   public void setChoices(Map<String, String> choices)
   {
      this.choices = choices;
   }
}
