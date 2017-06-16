package com.invessence.web.util;

import javax.faces.bean.*;

import org.primefaces.component.tabview.TabView;
import org.primefaces.event.TabChangeEvent;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 9/6/14
 * Time: 5:01 PM
 * To change this template use File | Settings | File Templates.
 */

@ManagedBean(name = "tabHelper")
@SessionScoped
public class TabHelper
{
   private TabView messagesTab = new TabView();
   Integer fromTab, toTab;

   public TabView getMessagesTab () {
      return messagesTab;
   }

   public void setMessagesTab(TabView messagesTab ) {
      this.messagesTab = messagesTab;
   }

   public void onTabChange(TabChangeEvent event) {
      TabView tabView = (TabView) event.getComponent();

      int activeIndex = tabView.getChildren().indexOf(event.getTab());
      fromTab = toTab;  // prior tab
      toTab = activeIndex; // new tab.

      this.messagesTab.setActiveIndex(activeIndex);

   }
}
