package org.primefaces.spark.invessence;

import javax.faces.bean.*;

import org.primefaces.component.tabview.Tab;
import org.primefaces.event.*;
import org.primefaces.model.chart.*;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 4/22/15
 * Time: 3:46 PM
 * To change this template use File | Settings | File Templates.
 */
@ManagedBean(name = "tc")
@SessionScoped
public class TabControl
{
   private Integer pTab = 0, rTab = 0;


   public Integer getpTab()
   {
      return pTab;
   }

   public void setpTab(Integer pTab)
   {
      this.pTab = pTab;
   }

   public Integer getrTab()
   {
      return rTab;
   }

   public void setrTab(Integer rTab)
   {
      this.rTab = rTab;
   }

   public void onChange() {
      System.out.println("Debug");
   }

   public void onPTabChange(TabChangeEvent event) {
      Tab active = event.getTab();
      if (active.getTitle().startsWith("O"))
         pTab = 0;
      if (active.getTitle().startsWith("F"))
         pTab = 1;
      if (active.getTitle().startsWith("R"))
         pTab = 2;

      if (pTab < 2)
         rTab = 0;
   }

   public void onRTabChange(TabChangeEvent event) {
      Tab active = event.getTab();
      String tabName= active.getTitle();
      Integer tabID = Integer.getInteger(tabName);
      if (tabID != null)
         rTab = tabID - 1;

      if (rTab <= 0)
         rTab = 0;
   }

   public Boolean getNextPersonalButtonEnable() {
      if (pTab > 1 & rTab > 6)
         return false;
      return true;
   }

   public Boolean getPrevPersonalButtonEnable() {
      if (pTab == 1)
         return false;
      return true;
   }

   public void gotoPrevTab() {
     switch (rTab) {
        case 0:
           switch (pTab) {
              case 0:
                 break;
              case 1:
              case 2:
                 pTab--;
              default:
                 break;
           }
           return;
        case 1:
        case 2:
        case 3:
        case 4:
        case 5:
        case 6:
        case 7:
        default:
           rTab--;
           break;
     }
   }

   public void gotoNextTab() {
      switch (pTab) {
         case 0:
         case 1:
            pTab ++;
            break;
         case 2:
         default:
            if (rTab < 6)
               rTab ++;
            break;

      }
   }
}
