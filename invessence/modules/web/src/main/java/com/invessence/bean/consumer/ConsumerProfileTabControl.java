package com.invessence.bean.consumer;

import javax.annotation.PostConstruct;
import javax.faces.bean.*;

import com.invessence.constant.Const;
import org.primefaces.component.tabview.Tab;
import org.primefaces.event.TabChangeEvent;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 4/22/15
 * Time: 3:46 PM
 * To change this template use File | Settings | File Templates.
 */
@ManagedBean(name = "cptc")
@SessionScoped
public class ConsumerProfileTabControl
{
   private Integer pTab = 0, rTab = 0;
   private Integer mTab = 0;


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

   @PostConstruct
   public void init()
   {
        pTab = 0;
        rTab = 0;
        mTab = 0;
   }

   public void onPTabChange(TabChangeEvent event) {
      Tab active = event.getTab();
      String pTabID = active.getId().toLowerCase();

      if (pTabID.equals("p1"))
         pTab = 0;
      if (pTabID.equals("p2"))
         pTab = 1;
      if (pTabID.equals("p3"))
         pTab = 2;
      if (pTabID.equals("p4"))
         pTab = 3;
   }

   public void onRTabChange(TabChangeEvent event) {
      Tab active = event.getTab();
      String pTabID = active.getId().toLowerCase();

      if (pTabID.equals("q1"))
         rTab = 0;
      if (pTabID.equals("q2"))
         rTab = 1;
      if (pTabID.equals("q3"))
         rTab = 2;
      if (pTabID.equals("q4"))
         rTab = 3;
      if (pTabID.equals("q5"))
         rTab = 4;
      if (pTabID.equals("q6"))
         rTab = 5;
      if (pTabID.equals("q7"))
         rTab = 6;
   }

   public String getEnableNextButton() {
      if (rTab >= 6 && pTab == 3)
         return "false";
      return "true";
   }

   public String getEnablePrevButton() {
      if (pTab == 0)
         return "false";
      return "true";
   }

   public void gotoPrevTab() {
     switch (rTab) {
        case 0:
           switch (pTab) {
              case 0:
                 break;
              case 1:
              case 2:
              case 3:
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
         case 2:
            pTab ++;
            rTab = 0;
            break;
         case 3:
         default:
            if (rTab < 6)
               rTab ++;
            break;

      }
   }
}
