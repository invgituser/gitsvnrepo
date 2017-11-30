package com.invessence.web.bean.consumer;

import java.util.ArrayList;

import com.invessence.web.data.*;
import com.invessence.web.data.consumer.*;
import com.invessence.web.util.Logger;

/**
 * Created by prashant on 11/16/2017.
 */
public class PortfolioCreationUI extends Customer implements Logger
{
   private ArrayList<Goals> goalsdata;

   private class UIManagement {
      Integer displayMode = 0; // 0=GetStarted, 1=NewAccount, 2=ChangePortfolio, etc.
      Boolean registeredUser = false;
   }

   public PortfolioCreationUI()
   {
      goalsdata = new ArrayList<Goals>();
      goalsdata.add(new Goals(1,"images/portfolio/retirement.png","Retirement","Retirement"));
      goalsdata.add(new Goals(2,"images/portfolio/education.png","Education","Fund for College"));
     // goalsdata.add(new Goals(3,"images/portfolio/income.png","Income","Generate Income"));
      goalsdata.add(new Goals(4,"images/portfolio/legacy.png","Legecy","Preserve Investment"));
      goalsdata.add(new Goals(5,"images/portfolio/property.png","Property","Buy a House"));
    goalsdata.add(new Goals(6,"images/portfolio/wealth.png","Build Wealth","Build Wealth"));
   // goalsdata.add(new Goals(6,"images/portfolio/wealth.png","other","other"));
   }

   public ArrayList<Goals> getGoalsdata()
   {
      return goalsdata;
   }
}
