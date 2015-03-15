package com.invessence.data;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 12/11/14
 * Time: 10:00 PM
 * To change this template use File | Settings | File Templates.
 */

public class MenuBarWidgets
{
   private Integer consumermenuID;
   private Integer advisormenuID;
   private Integer submenuid;

   public Integer getConsumermenuID()
   {
      return consumermenuID;
   }

   public void setConsumermenuID(Integer consumermenuID)
   {
      this.consumermenuID = consumermenuID;
   }

   public Integer getAdvisormenuID()
   {
      return advisormenuID;
   }

   public Integer getSubmenuid()
   {
      return submenuid;
   }

   public void setSubmenuid(Integer submenuid)
   {
      this.submenuid = submenuid;
   }

   public void setAdvisormenuID(Integer advisormenuID)
   {
      this.advisormenuID = advisormenuID;
   }

   public  String consumermenuURL() {
       return "editor.xhtml";
   }

   public String advisormenuURL() {
      return "test.xhtml";
   }

/*
   public String consumermenuURL()
   {
      Integer topmenu = 0;
      Integer submenu = 0;
      try
      {
         if (getConsumermenuID() != null)
         {
            if (getSubmenuid() != null)
            {
               submenu = getSubmenuid();
            }
            topmenu = (getConsumermenuID() * 100) + submenu;
            switch (topmenu)
            {
               case 100:
                  return "view.xhtml";
               case 200:
               case 201:
               case 202:
               case 203:
               case 204:
               case 205:
                  return "reports.xhtml";
               case 300:
                  return "marketing.xhtml";
               case 400:
                  return "manage.xhtml";
               case 401:
                  return "rebal.xhtml";
               case 402:
                  return "edit.xhtml";
               case 800:
                  return "add.xhtml";
               case 900:
                  return "settings.xhtml";
               default:
                  return "view.xhtml";
            }
         }
         else
         {
            return "view.xhtml";
         }

      }
      catch (Exception ex)
      {
      }
      return "view.xhtml";
   }

   public String advisormenuURL()
   {
      Integer topmenu = 0;
      Integer submenu = 0;
      try
      {
         if (getConsumermenuID() != null)
         {
            if (getSubmenuid() != null)
            {
               submenu = getSubmenuid();
            }
            topmenu = (getConsumermenuID() * 100) + submenu;
            switch (topmenu)
            {
               case 100:
                  return "view.xhtml";
               case 200:
               case 201:
               case 202:
               case 203:
               case 204:
               case 205:
                  return "reports.xhtml";
               case 300:
                  return "marketing.xhtml";
               case 400:
                  return "manage.xhtml";
               case 401:
                  return "rebal.xhtml";
               case 402:
                  return "edit.xhtml";
               case 800:
                  return "add.xhtml";
               case 900:
                  return "settings.xhtml";
               default:
                  return "view.xhtml";
            }
         }
         else
         {
            return "view.xhtml";
         }

      }
      catch (Exception ex)
      {
      }
      return "view.xhtml";
   }
*/

}
