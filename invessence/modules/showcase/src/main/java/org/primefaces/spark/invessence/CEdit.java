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
@ManagedBean(name = "cepb")
@SessionScoped
public class CEdit
{
   private String portfolioName;
   private String displayGoal;
   private Integer age = 30;
   private Integer initialInvestment = 100000;
   private Integer defaultInvestment = 100000;
   private Integer allocationIndex = 50;
   private Integer portfolioIndex= 100;
   private String goal;
   private Boolean accountTaxable;
   private Integer defaultAge;
   private String horizonQuestion;
   private Integer horizon;
   private Integer recurringInvestment;
   private Integer experience;
   private Integer dependent;
   private Double  householdwages, otherExpense,moneymarket, investment,otherDebt;
   private Integer selectedchoice1;
   private Integer selectedchoice2;
   private Boolean visible;
   private Integer prefView = 0;
   private Integer pTab = 0, rTab = 0;

   public String getPortfolioName()
   {
      return portfolioName;
   }

   public void setPortfolioName(String portfolioName)
   {
      this.portfolioName = portfolioName;
   }

   public String getDisplayGoal()
   {
      return displayGoal;
   }

   public void setDisplayGoal(String displayGoal)
   {
      this.displayGoal = displayGoal;
   }

   public Integer getAge()
   {
      return age;
   }

   public void setAge(Integer age)
   {
      this.age = age;
   }

   public Integer getInitialInvestment()
   {
      return initialInvestment;
   }

   public void setInitialInvestment(Integer initialInvestment)
   {
      this.initialInvestment = initialInvestment;
   }

   public Integer getDefaultInvestment()
   {
      return defaultInvestment;
   }

   public void setDefaultInvestment(Integer defaultInvestment)
   {
      this.defaultInvestment = defaultInvestment;
   }

   public Integer getAllocationIndex()
   {
      return allocationIndex;
   }

   public void setAllocationIndex(Integer allocationIndex)
   {
      this.allocationIndex = allocationIndex;
   }

   public Integer getPortfolioIndex()
   {
      return portfolioIndex;
   }

   public void setPortfolioIndex(Integer portfolioIndex)
   {
      this.portfolioIndex = portfolioIndex;
   }

   public void consumerRefresh() {

   }

   public void onSlider(SlideEndEvent event) {

   }

   public void onSlider1(SlideEndEvent event) {

   }

   public void selectedGoalType(Integer num) {

   }

   public String getGoal()
   {
      return goal;
   }

   public void setGoal(String goal)
   {
      this.goal = goal;
   }

   public Boolean getAccountTaxable()
   {
      return accountTaxable;
   }

   public void setAccountTaxable(Boolean accountTaxable)
   {
      this.accountTaxable = accountTaxable;
   }

   public Integer getDefaultAge()
   {
      return defaultAge;
   }

   public void setDefaultAge(Integer defaultAge)
   {
      this.defaultAge = defaultAge;
   }

   public String getHorizonQuestion()
   {
      return horizonQuestion;
   }

   public void setHorizonQuestion(String horizonQuestion)
   {
      this.horizonQuestion = horizonQuestion;
   }

   public Integer getHorizon()
   {
      return horizon;
   }

   public void setHorizon(Integer horizon)
   {
      this.horizon = horizon;
   }

   public Integer getRecurringInvestment()
   {
      return recurringInvestment;
   }

   public void setRecurringInvestment(Integer recurringInvestment)
   {
      this.recurringInvestment = recurringInvestment;
   }

   public Integer getExperience()
   {
      return experience;
   }

   public void setExperience(Integer experience)
   {
      this.experience = experience;
   }

   public Integer getSelectedchoice1()
   {
      return selectedchoice1;
   }

   public void setSelectedchoice1(Integer selectedchoice1)
   {
      this.selectedchoice1 = selectedchoice1;
   }

   public Integer getSelectedchoice2()
   {
      return selectedchoice2;
   }

   public void setSelectedchoice2(Integer selectedchoice2)
   {
      this.selectedchoice2 = selectedchoice2;
   }

   public Integer getPrefView()
   {
      return prefView;
   }

   public void setPrefView(Integer prefView)
   {
      this.prefView = prefView;
   }

   public void save() {
      System.out.println("Saving.");
   }

   public Boolean getVisible()
   {
      return visible;
   }

   public void setVisible(Boolean visible)
   {
      this.visible = visible;
   }

   public void setVisible() {
      this.visible = true;
   }

   public BarChartModel getBarModel() {
      BarChartModel model = new BarChartModel();

      ChartSeries domestic = new ChartSeries();
      domestic.setLabel("Domestic");
      domestic.set("Assets", 33);

      ChartSeries internatonal = new ChartSeries();
      internatonal.setLabel("Internatonal");
      internatonal.set("Assets", 25);

      ChartSeries bond = new ChartSeries();
      bond.setLabel("Bond");
      bond.set("Assets", 45);

      ChartSeries commodity = new ChartSeries();
      commodity.setLabel("Commodity");
      commodity.set("Assets", 5);

      ChartSeries cash = new ChartSeries();
      cash.setLabel("Cash");
      cash.set("Assets", 1);

      model.addSeries(domestic);
      model.addSeries(internatonal);
      model.addSeries(bond);
      model.addSeries(commodity);
      model.addSeries(cash);
      model.setShowPointLabels(true);
      model.setLegendPosition("ne");
      return model;
   }

   public Integer getDependent()
   {
      return dependent;
   }

   public void setDependent(Integer dependent)
   {
      this.dependent = dependent;
   }

   public Double getHouseholdwages()
   {
      return householdwages;
   }

   public void setHouseholdwages(Double householdwages)
   {
      this.householdwages = householdwages;
   }

   public Double getOtherExpense()
   {
      return otherExpense;
   }

   public void setOtherExpense(Double otherExpense)
   {
      this.otherExpense = otherExpense;
   }

   public Double getMoneymarket()
   {
      return moneymarket;
   }

   public void setMoneymarket(Double moneymarket)
   {
      this.moneymarket = moneymarket;
   }

   public Double getInvestment()
   {
      return investment;
   }

   public void setInvestment(Double investment)
   {
      this.investment = investment;
   }

   public Double getOtherDebt()
   {
      return otherDebt;
   }

   public void setOtherDebt(Double otherDebt)
   {
      this.otherDebt = otherDebt;
   }

   public void onChange() {
      System.out.println("Debug");
   }

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
           return;
        case 1:
        case 2:
           pTab --;
           break;
        case 3:
        case 4:
        case 5:
        case 6:
        case 7:
        default:
           break;
     }
     rTab--;

   }

   public void gotoNextTab() {
      switch (rTab) {
         case 0:
         case 1:
            pTab ++;
            break;
         case 2:
         case 3:
         case 4:
         case 5:
         case 6:
            break;
         case 7:
         default:
            return;
      }
      rTab++;
   }
}
