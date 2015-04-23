package org.primefaces.spark.invessence;

import javax.faces.bean.*;

import org.primefaces.event.SlideEndEvent;

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
   private Integer defaultHorizon;
   private Integer recurringInvestment;
   private Integer experience;
   private Integer selectedchoice1;
   private Integer selectedchoice2;
   private Boolean visible;

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

   public Integer getDefaultHorizon()
   {
      return defaultHorizon;
   }

   public void setDefaultHorizon(Integer defaultHorizon)
   {
      this.defaultHorizon = defaultHorizon;
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
}
