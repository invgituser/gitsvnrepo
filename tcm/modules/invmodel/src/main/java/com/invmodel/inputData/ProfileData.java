package com.invmodel.inputData;

import java.util.*;

import com.invmodel.Const.InvConst;
import com.invmodel.asset.data.*;
import com.invmodel.model.fixedmodel.data.FMData;
import com.invmodel.performance.data.ProjectionData;
import com.invmodel.portfolio.data.Portfolio;

/**
 * Created with IntelliJ IDEA.
 * User: Jigar
 * Date: 11/4/13
 * Time: 2:30 PM
 * To change this template use File | Settings | File Templates.
 */


public class
   ProfileData
{
   private Long logonid;
   private Long advisorlogonid;
   private Long acctnum;
   private String clientAccountID;
   private Integer yearly = 12;
   private String goal;
   private String accountType;
   private String tradePreference;
   private String name;
   public Integer age = 30;
   public Integer horizon;
   private Integer calendarYear;
   private Integer numOfAllocation = 1;
   private Integer numOfPortfolio = 1;
   public Integer initialInvestment;
   private Double actualInvestment;
   private GoalsData goalData = new GoalsData();
   private Integer keepLiquid;
   private Integer recurringInvestment;
   private Integer experience = 2; // 1 = Experienced, 2 = inExperienced (See method strExpeience)
   private Integer objective = 2; // 1 = Preservation, 2 = Accumulation; (See method strObjective)
   private String advisor;
   private String rep;
   private String theme;
   private String basket;
   private Integer numOfQuestions = 15;
   private Integer[] riskAnswers = new Integer[numOfQuestions];


   private Integer stayInvested = 1;  // 1 = go to cash, 2 = stayInvested (See method strStayInvested)
   private Integer charitableGoals;

   private Integer dependent = 0;

   private Integer currentIncome = 0;
   private Integer liquidAsset = 0;

   private Integer totalIncome = 0;
   private Integer totalExpense = 0;
   private Integer totalAsset = 0;
   private Integer totalLiability = 0;

   private boolean accountTaxable = false; //1 (True) for accountTaxable 0 (False) for nonTaxable
   private Double taxrate = 0.1;
   private Double shortLossCarry = 0.0;
   private Double longLossCarry = 0.0;
   private Double shortExternalGain = 0.0;
   private Double longExternalGain = 0.0;

   private Double riskIndex = 0.0;        // On riskIndex 0 = highest risk, 28 = lowest risk.
   private Integer displayRiskIndex = 10; // On displayRiskIndex 0 = lowest risk, 10 = highest risk.

   private String riskCalcMethod = "C";  // Choices: C - Consumer, A - Advisor
   private Integer allocationIndex = InvConst.ASSET_DEFAULT_POINT;
   private Integer portfolioIndex = InvConst.PORTFOLIO_DEFAULT_POINT;
   private Integer meterRiskIndicator = 5;
   private ArrayList<Asset> editableAsset = new ArrayList<Asset>();
   private Integer maxAssetAllocatonPoints;
   private Integer maxPortfolioAllocationPoints;
   private Boolean fixedModel = false;
   private FMData  fixedFMModel = null;
   private Boolean hasReturn = false;
   private Boolean hasRisk = false;
   private AssetClass[] assetData;
   private Portfolio[] portfolioData;   // Although the arrary is not required, we are using to show performace data.
   private ProjectionData[] projectionData;

   private String iblinkmaster = "https://www.interactivebrokers.com/Universal/servlet/formWelcome?&partnerID=Invessence&invitedBy=dmNtMDMxNzE2";
   // Invessence version with PartnerID = Invessence and invitedBy=NDE4aW52ZXN0
   // private String iblinkmaster = "https://www.interactivebrokers.com/Universal/servlet/formWelcome?&partnerID=Invessence&invitedBy=NDE4aW52ZXN0&invitation_id=6596230&token=56551";
   private String iblink = "https://www.interactivebrokers.com/Universal/servlet/formWelcome?&invitation_id=11204955&token=81843&partnerID=Invessence&invitedBy=dmNtMDMxNzE2";

   private Map<String, CustomAllocation> customAllocations = new HashMap<String, CustomAllocation>();

   public Double getShortLossCarry()
   {
      return shortLossCarry;
   }

   public void setShortLossCarry(Double shortLossCarry)
   {
      this.shortLossCarry = shortLossCarry;
   }

   public Double getLongLossCarry()
   {
      return longLossCarry;
   }

   public void setLongLossCarry(Double longLossCarry)
   {
      this.longLossCarry = longLossCarry;
   }

   public Double getShortExternalGain()
   {
      return shortExternalGain;
   }

   public void setShortExternalGain(Double shortExternalGain)
   {
      this.shortExternalGain = shortExternalGain;
   }

   public Double getLongExternalGain()
   {
      return longExternalGain;
   }

   public void setLongExternalGain(Double longExternalGain)
   {
      this.longExternalGain = longExternalGain;
   }

   public ProfileData getProfileInstance()
   {
      return this;
   }

   public String getDisplayActiveAcctNum()
   {
      if (getClientAccountID() != null && getClientAccountID().length() > 0)
      {
         return getClientAccountID();
      }
      else
      {
         return getAcctnum().toString();
      }
   }

   public Long getAcctnum()
   {
      return acctnum;
   }

   public void setAcctnum(Long acctnum)
   {
      this.acctnum = acctnum;
   }

   public String getClientAccountID()
   {
      return clientAccountID;
   }

   public void setClientAccountID(String clientAccountID)
   {
      this.clientAccountID = clientAccountID;
   }

   public Long getAdvisorlogonid()
   {
      return advisorlogonid;
   }

   public void setAdvisorlogonid(Long advisorlogonid)
   {
      this.advisorlogonid = advisorlogonid;
   }

   public Long getLogonid()
   {
      return logonid;
   }

   public void setLogonid(Long logonid)
   {
      this.logonid = logonid;
   }

   public String getGoal()
   {
      if (goal == null)
      {
         return "";
      }
      else
      {
         return goal;
      }
   }

   public void setGoal(String goal)
   {
      this.goal = goal;
      // In case the goal is reselected, we want to reset the tax type.
      // Introduced 4/4/2015 , now taxable is a flag on Interface.
      // determineTaxable(getGoal(), getAccountType());
      // NOTE: Explore page overrides this behaviour, because the account type is not selected.
   }

   public void determineTaxable(String goal, String acctType)
   {
      Boolean taxable = true;
      // If any of these account type, then it is a non-taxable account
      if (acctType != null)
      {
         if (acctType.toUpperCase().contains("NON"))   // non-taxable
         {
            taxable = false;
         }
         else if (acctType.toUpperCase().contains("IRA"))   // IRA
         {
            taxable = false;
         }
         else if (acctType.toUpperCase().contains("SEP"))   // SEP
         {
            taxable = false;
         }
         else if (acctType.toUpperCase().contains("ROTH"))  // ROTH
         {
            taxable = false;
         }
         else // Using general words
            if (acctType.toUpperCase().contains("RETIRE"))
            {
               taxable = false;
            }
            else
            {
               taxable = true;
            }

      }
      setAccountTaxable(taxable);

   }

   public String getAccountType()
   {
      return accountType;
   }

   public void setAccountType(String accountType)
   {
      this.accountType = accountType;
      // determineTaxable(getGoal(), accountType);
      // setIblink(accountType);
   }

   public String getDisplayGoals()
   {
      if (goal == null)
      {
         return "Retirement";
      }
      else
      {
         if (goal.toUpperCase().contains("RETIRE"))
         {
            return "Retirement";
         }
         if (goal.toUpperCase().contains("INCOME"))
         {
            return "Income";
         }
         else
         {
            return "Growth";
         }
      }
   }

   public void setDisplayGoals(String goal)
   {
      if (goal == null)
      {
         setGoal("Growth");
      }
      else
      {
         setGoal(goal);
      }

   }


   public String getTradePreference()
   {
      return tradePreference;
   }

   public void setTradePreference(String tradePreference)
   {
      this.tradePreference = tradePreference;
   }

   public Integer getYearly()
   {
      return yearly;
   }

   public void setYearly(Integer yearly)
   {
      this.yearly = yearly;
   }

   public String getName()
   {
      return name;
   }

   public void setName(String name)
   {
      this.name = name;
   }

   public Integer getDefaultAge()
   {
      return (getAge() == null) ? 30 : getAge();
   }

   public Integer getAge()
   {
      return age;
   }

   public Integer getDefaultHorizon()
   {
      Integer adjduration;
      Integer defaultAge;

      defaultAge = getDefaultAge();
      if ((70 - defaultAge) > 0)
      {
         adjduration = 70 - defaultAge;
      }
      else
      {
         adjduration = 1;
      }
      Integer duration = (getHorizon() == null) ? adjduration : getHorizon();
      duration = (duration <= 0) ? 1 : duration;
      return duration;

   }

   public Integer getHorizon()
   {
      return horizon;
   }

   public void setHorizon(Integer horizon)
   {
      // If Horizon < 5 year then go to cash (2)
      // However, if horizon < 5 and it is taxfree, then stay invested.
      if (horizon != null)
      {
         setStayInvested((horizon <= 5) ? ((getAccountTaxable() ? 2 : 1)) : 1);
      }
      this.horizon = horizon;
   }

   public Integer getCalendarYear()
   {
      return calendarYear;
   }

   public void setCalendarYear(Integer calendarYear)
   {
      this.calendarYear = calendarYear;
   }

   public Integer getNumOfAllocation()
   {
      if (numOfAllocation == null)
      {
         return 1;
      }
      return numOfAllocation;
   }

   public void setNumOfAllocation(Integer numOfAllocation)
   {
      this.numOfAllocation = numOfAllocation;
   }

   public Integer getNumOfPortfolio()
   {
      if (numOfPortfolio == null)
      {
         return 1;
      }
      return numOfPortfolio;
   }

   public void setNumOfPortfolio(Integer numOfPortfolio)
   {
      this.numOfPortfolio = numOfPortfolio;
   }

/*
   public void setDefaultInvestment(Integer investment)
   {
      initialInvestment = investment;
   }
*/

   public Double getDefaultInvestment()
   {
      if (actualInvestment != null && actualInvestment != 0)
      {
         return actualInvestment;
      }
      else
      {
         if (initialInvestment != null && initialInvestment != 0)
         {
            return initialInvestment.doubleValue();
         }
         else
         {
            return 100000.00;
         }
      }
   }


   public Integer getInitialInvestment()
   {
      return initialInvestment;
   }

   public void setOnlyInitialInvestment(Integer initialInvestment)
   {
      this.initialInvestment = initialInvestment;
   }

   public void setInitialInvestment(Integer initialInvestment)
   {
      this.initialInvestment = initialInvestment;
      if ((getGoalData() != null) && (initialInvestment != null))
      {
         getGoalData().setActualInitialAmount(initialInvestment.doubleValue());
      }
   }

   public Double getActualInvestment()
   {
      Integer value = 0;

      if (initialInvestment == null)
         return actualInvestment;
      else {
         if (actualInvestment == null || actualInvestment == 0.0)
         {
            value = initialInvestment;
         }
         return value.doubleValue();
      }
   }

   public void setActualInvestment(Double actualInvestment)
   {
      this.actualInvestment = actualInvestment;
   }

   public GoalsData getGoalData()
   {
      if (goalData == null)
      {
         return new GoalsData();
      }
      else
      {
         return goalData;
      }
   }

   public void setGoalData(GoalsData goalData)
   {
      this.goalData = goalData;
   }

   public Integer getKeepLiquid()
   {
      return keepLiquid;
   }

   public void setKeepLiquid(Integer keepLiquid)
   {
      this.keepLiquid = keepLiquid;
   }

   public Integer getRecurringInvestment()
   {
      if (recurringInvestment == null)
         return 0;
      return recurringInvestment;
   }

   public void setOnlyRecurringInvestment(Integer recurringInvestment)
   {
      this.recurringInvestment = recurringInvestment;
   }

   public void setRecurringInvestment(Integer recurringInvestment)
   {
      this.recurringInvestment = recurringInvestment;
      if ((getGoalData() != null) && (recurringInvestment != null))
      {
         getGoalData().setActualRecurringAmount(recurringInvestment.doubleValue());
      }
   }

   public Integer getExperience()
   {
      if (experience == null)
      {
         return 1;
      }
      else
      {
         return experience;
      }
   }

   public void setExperience(Integer experience)
   {
      this.experience = experience;
   }

   public Integer getObjective()
   {
      // WealthPreservation = 1; WealthAccumulation = 2
      if (objective == null)
      {
         return 1;
      }
      else
      {
         return objective;
      }
   }

   public void setObjective(Integer objective)
   {
      // WealthPreservation = 1; WealthAccumulation = 2
      this.objective = objective;
   }

   public String getTheme()
   {
      return theme;
   }

   public void setTheme(String theme)
   {
      this.theme = theme;
   }

   public String getBasket()
   {
      return basket;
   }

   public void setBasket(String basket)
   {
      this.basket = basket;
   }

   public void setBasket(String theme, String basket)
   {
      this.theme = theme;
      this.basket = basket;
   }

   public Integer[] getRiskAnswers()
   {
      return riskAnswers;
   }

   public void setRiskAnswers(Integer[] riskAnswers)
   {
      if (riskAnswers == null)
      {
         this.riskAnswers = new Integer[numOfQuestions];
      }
      else
      {
         this.riskAnswers = riskAnswers;
      }
   }

   public void setRiskAnswers(Integer question, String value)
   {
      Integer riskNum = 0;
      if (question >= 0 && question < riskAnswers.length)
      {
         if (value != null)
         {
            try
            {
               riskNum = Integer.valueOf(value);
            }
            catch (Exception ex)
            {
               riskNum = 0;
            }
            riskAnswers[question] = riskNum;
         }
      }
   }

   public String getAdvisor()
   {
      // Either it is null or it is "" (Empty), then assume default
      if (advisor == null || advisor.length() == 0)
      {
         return InvConst.INVESSENCE_ADVISOR;
      }
      return advisor;
   }

   public void setAdvisor(String advisor)
   {
      this.advisor = advisor;
   }

   public String getRep()
   {
      return rep;
   }

   public void setRep(String rep)
   {
      this.rep = rep;
   }

   public Integer getStayInvested()
   {
      if (stayInvested == null)
      {
         return 1;
      }
      else
      {
         return stayInvested;
      }
   }

   public void setStayInvested(Integer stayInvested)
   {
      this.stayInvested = stayInvested;
   }

   public Integer getCharitableGoals()
   {
      return charitableGoals;
   }

   public void setCharitableGoals(Integer charitablegoals)
   {
      this.charitableGoals = charitablegoals;
   }

   public String getStrExperience()
   {
      String strExperience = "Inexperience";
      // 1 = Experienced, 2 = inExperienced
      // Default: Inexperience;
      if (getExperience() != null)
      {
         switch (getExperience())
         {
            case 1:
               strExperience = "Experience";
               break;
            case 2:
               strExperience = "Inexperience";
               break;
            default:
               strExperience = "Inexperience";
               break;
         }
      }
      return strExperience;
   }

   public String getStrObjective()
   {
      String strObjective = "Wealth Accumulation";
      // 1 = Preservation, 2 = Accumulation
      // Default: Accumulation;
      if (getObjective() != null)
      {
         switch (getObjective())
         {
            case 1:
               strObjective = "Wealth Preservation";
               break;
            case 2:
               strObjective = "Wealth Accumulation";
               break;
            default:
               strObjective = "Wealth Accumulation";
               break;
         }
      }
      return strObjective;
   }

   public String getStrStayInvested()
   {
      // 2 = go to cash, 1 = stayInvested
      // Default: Go To Cash;
      String strStayInvested = "Go To Cash";
      if (getStayInvested() != null)
      {
         switch (getStayInvested())
         {
            case 1:
               strStayInvested = "Stay Invested";
               break;
            case 2:
               strStayInvested = "Go To Cash";
               break;
            default:
               strStayInvested = "Go To Cash";
               break;
         }
      }
      return strStayInvested;
   }

   public Integer getDependent()
   {
      if (dependent == null)
      {
         return 0;
      }
      else
      {
         return dependent;
      }
   }

   public void setDependent(Integer dependent)
   {
      this.dependent = dependent;
   }

   public Integer getCurrentIncome()
   {
      return currentIncome;
   }

   public void setCurrentIncome(Integer currentIncome)
   {
      this.currentIncome = currentIncome;
   }

   public Integer getTotalIncome()
   {
      return totalIncome;
   }

   public void setTotalIncome(Integer totalIncome)
   {
      this.totalIncome = totalIncome;
   }

   public Integer getTotalExpense()
   {
      return totalExpense;
   }

   public void setTotalExpense(Integer totalExpense)
   {
      this.totalExpense = totalExpense;
   }

   public Integer getTotalAsset()
   {
      return totalAsset;
   }

   public void setTotalAsset(Integer totalAsset)
   {
      this.totalAsset = totalAsset;
   }

   public Integer getTotalLiability()
   {
      return totalLiability;
   }

   public void setTotalLiability(Integer totalLiability)
   {
      this.totalLiability = totalLiability;
   }

   public Integer getLiquidAsset()
   {
      return liquidAsset;
   }

   public void setLiquidAsset(Integer liquidAsset)
   {
      this.liquidAsset = liquidAsset;
   }

   public void setAge(Integer age)
   {
      this.age = age;
   }

   public void setTaxrate(double taxrate)
   {
      this.taxrate = taxrate;
   }

   public boolean isAccountTaxable()
   {
      return accountTaxable;
   }

   public boolean getAccountTaxable()
   {
      return accountTaxable;
   }

   public void setAccountTaxable(boolean accountTaxable)
   {
      this.accountTaxable = accountTaxable;
   }

   public Double getTaxrate()
   {
      // Added currentIncome == 0 for rebalancing when income is missing
      if (currentIncome == null || currentIncome == 0)
      {

         return 0.30;
      }

      else
      {
         return taxrate;
      }
   }

   public void setTaxrate(Double taxrate)
   {
      if (taxrate == null)
      {
         this.taxrate = 0.1;
      }
      else
      {
         this.taxrate = taxrate;
      }
   }

   public void taxRate()
   {
      if (currentIncome == null)
      {
         setTaxrate(0.10);
      }
      else if (currentIncome > 400000)
      {
         setTaxrate(0.396);
      }
      else if (currentIncome > 398350 && currentIncome <= 400000)
      {
         setTaxrate(0.35);
      }
      else if (currentIncome > 183250 && currentIncome <= 398350)
      {
         setTaxrate(0.33);
      }
      else if (currentIncome > 87850 && currentIncome <= 183250)
      {
         setTaxrate(0.28);
      }
      else if (currentIncome > 36250 && currentIncome <= 87850)
      {
         setTaxrate(0.25);
      }
      else if (currentIncome > 8925 && currentIncome <= 36250)
      {
         setTaxrate(0.15);
      }
      else if (currentIncome > 0 && currentIncome <= 8925)
      {
         setTaxrate(0.10);
      }
   }

   public String getRiskCalcMethod()
   {
      if (riskCalcMethod == null)
      {
         return ("C");
      }
      else
      {
         return riskCalcMethod;
      }
   }

   public void setRiskCalcMethod(String riskCalcMethod)
   {
      this.riskCalcMethod = riskCalcMethod;
   }

   public Double getRiskIndex()
   {
      return riskIndex;
   }

   public Integer getDisplayRiskIndex()
   {
      return displayRiskIndex;
   }

   public void setDisplayRiskIndex(Double displayRiskIndex)
   {
      this.displayRiskIndex = displayRiskIndex.intValue();
   }

   public void setRiskIndex(Double riskIndex)
   {
      this.riskIndex = riskIndex;
   }

   public Integer getAllocationIndex()
   {
      if (allocationIndex == null)
      {
         return InvConst.ASSET_DEFAULT_POINT;
      }
      return allocationIndex;
   }

   public void setAllocationIndex(Integer allocationIndex)
   {
      this.allocationIndex = allocationIndex;
      determineMeterRisk();
   }

   public Integer getPortfolioIndex()
   {
      if (portfolioIndex == null)
      {
         return InvConst.PORTFOLIO_DEFAULT_POINT;
      }
      return portfolioIndex;
   }

   public void setPortfolioIndex(Integer portfolioIndex)
   {
      this.portfolioIndex = portfolioIndex;
      determineMeterRisk();
   }

   public Integer getMeterRiskIndicator()
   {
      return meterRiskIndicator;
   }

   public void setMeterRiskIndicator(Integer meterRiskIndicator)
   {
      this.meterRiskIndicator = meterRiskIndicator;
   }

   public void determineMeterRisk()
   {
      if (portfolioIndex == null || allocationIndex == null)
      {
         setMeterRiskIndicator(5);
      }
      else
      {
         Integer calc;
         if (allocationIndex == 0 && portfolioIndex == 0)
         {
            calc = 0;
         }
         else
         {
            calc = (allocationIndex * portfolioIndex) / (allocationIndex + portfolioIndex);
         }
         setMeterRiskIndicator(calc);
      }
   }

   public Integer getMaxAssetAllocatonPoints()
   {
      return maxAssetAllocatonPoints;
   }

   public void setMaxAssetAllocatonPoints(Integer maxAssetAllocatonPoints)
   {
      this.maxAssetAllocatonPoints = maxAssetAllocatonPoints;
   }

   public Integer getMaxPortfolioAllocationPoints()
   {
      return maxPortfolioAllocationPoints;
   }

   public void setMaxPortfolioAllocationPoints(Integer maxPortfolioAllocationPoints)
   {
      this.maxPortfolioAllocationPoints = maxPortfolioAllocationPoints;
   }

   public Boolean getFixedModel()
   {
      if (fixedModel == null)
      {
         return false;
      }

      return fixedModel;
   }

   public void setFixedModel(Boolean fixedModel)
   {
      this.fixedModel = fixedModel;
   }

   public FMData getFixedFMModel()
   {
      return fixedFMModel;
   }

   public String getFixedModelName()
   {
      String value = "";
      if (getFixedFMModel() != null) {
         value = getFixedFMModel().getDisplayname();
      }
      return value;
   }

   public String getFixedModelDescription()
   {
      String  value = "";
      if (getFixedFMModel() != null) {
         value = getFixedFMModel().getDescription();
      }
      return value;
   }

   public void setFixedFMModel(FMData fixedFMModel)
   {
      this.fixedFMModel = fixedFMModel;
   }

   public Boolean getHasReturn()
   {
      return hasReturn;
   }

   public void setHasReturn(Boolean hasReturn)
   {
      this.hasReturn = hasReturn;
   }

   public Boolean getHasRisk()
   {
      return hasRisk;
   }

   public void setHasRisk(Boolean hasRisk)
   {
      this.hasRisk = hasRisk;
   }

   public AssetClass[] getAssetData()
   {
      return assetData;
   }

   public void setAssetData(AssetClass[] assetData)
   {
      this.assetData = assetData;
   }

   public Portfolio[] getPortfolioData()
   {
      return portfolioData;
   }

   public void setPortfolioData(Portfolio[] portfolioData)
   {
      this.portfolioData = portfolioData;
   }

   public ProjectionData[] getProjectionData()
   {
      return projectionData;
   }

   public void setProjectionData(ProjectionData[] projectionData)
   {
      this.projectionData = projectionData;
   }

   public String getAgetip()
   {
      return "The variation in portfolio return is determined by asset allocation. " +
         "A well diversified portfolio may be the critical factor in determining how well your portfolio performs in " +
         "good and bad markets. Your age in one factor used to determine the right asset mix for you. " +
         "As you get older your financial responsibilities changes and also your ability to take risk. " +
         "For more information on asset allocation watch this video.";
   }

   public String getExperiencetip()
   {
      return "Your level of investment experience will be used to guide you through each step to help you make the right choice." +
         "If you are an experienced investor, you will be able to customize your portfolios with themes to your choice.";
   }

   public String getInvestmenttip()
   {
      return "Your investment objective will help create the right plan for you with tax efficient investments.";
   }

   public String getHorizontip()
   {
      return "How long will you invest is the most critical question. " +
         "Can you live without making withdrawal,or are you counting on this money for expenses? " +
         "Your investment choices and the risk associated with them depends on your comfort and security you " +
         "feel about your current income and savings in case of job loss or other unpredicted events." +
         "Watch a video to learn more on this topic.";
   }

   public String getInitialinvestmenttip()
   {
      return "It is important to save and stick with the goals you make for the longer term planning. " +
         "Make realistic goals,use budget calculator here to set your goals. " +
         "Think about your other savings for emergency and how stable is your income.";
   }

   public String getRisktip()
   {
      return "How do you feel when you take a loss? How secure is your job situation? " +
         "It is important to take some risk but not when you have too much to lose, " +
         "watch a video on understanding your own risk.";
   }

   public String getDependenttip()
   {
      return "Your liabilities go hand in hand with your ability to set your risk and create the right investment plan. " +
         "Your children will require money for college, or perhaps a new home. " +
         "Watch a video on understanding your liabilities and your investment options.";
   }

   public Map<String, String> getDisplayAcctType()
   {
      Map<String, String> displayAcctType = new LinkedHashMap<String, String>();
      try
      {
         if (getGoal() == null)
         {
            return null;
         }
         else
         {
            if (getGoal().equalsIgnoreCase("retirement"))
            {
               displayAcctType.put("IRA Traditional", "IRA Traditional");
               displayAcctType.put("IRA SEP", "IRA SEP");
               displayAcctType.put("IRA ROTH", "IRA ROTH");
               displayAcctType.put("Taxable (Individual)", "Taxable (Individual)");
               displayAcctType.put("Taxable (Joint)", "Taxable (Joint)");
            }
            else if (getGoal().equalsIgnoreCase("other"))
            {
               displayAcctType.put("Individual", "Taxable (Individual)");
               displayAcctType.put("Joint", "Taxable (Joint)");
               displayAcctType.put("Trust", "Taxable (Trust)");
            }
            else
            {
               displayAcctType.put("Individual", "Taxable (Individual)");
               displayAcctType.put("Joint", "Taxable (Joint)");
            }
         }
         return displayAcctType;
      }
      catch (Exception ex)
      {
         return null;
      }
   }

   public Map<String, String> getDisplayGoalList()
   {
      Map<String, String> displayList = new LinkedHashMap<String, String>();
      try
      {
         displayList.put("Retirement", "Retirement");
         displayList.put("Home", "Home");
         displayList.put("Education", "Education");
/*
         displayList.put("Wedding","Wedding");
         displayList.put("Automobile","Automobile");
 */
         displayList.put("Saving", "Saving");
      }
      catch (Exception ex)
      {
      }
      return displayList;
   }

   public Map getCustomAllocations()
   {
      return customAllocations;
   }

   public void resetCustomAllocation()
   {
      this.customAllocations.clear();
   }

   public void addCustomAllocation(String assetclass, String subclass, double risk)
   {
      String key;
      try
      {
         if (assetclass == null)
         {
            assetclass = "";
         }
         if (subclass == null)
         {
            subclass = "";
         }

         key = assetclass.toUpperCase() + "." + subclass.toUpperCase();
         CustomAllocation ca = new CustomAllocation(assetclass, subclass, risk);
         if (this.customAllocations == null)
         {
            this.customAllocations = new HashMap<String, CustomAllocation>();
         }
         this.customAllocations.put(key, ca);
      }

      catch (Exception ex)
      {
         ex.printStackTrace();
      }

   }

   public String getIblink()
   {
      if (iblink == null)
      {
         return iblinkmaster;
      }
      return iblink;
   }

   public void setIblink(String accounttype)
   {
      // First set the default as Individual.
      this.iblink = iblinkmaster;
      if (accounttype != null)
      {
         if (accounttype.toUpperCase().startsWith("NON"))    // General Non-taxable (IRA/SEP/ROTH...)
         {
            this.iblink = iblinkmaster + "&invitation_id=11204963&token=16584";
         }
         if (accounttype.toUpperCase().contains("JOINT"))        // Joint
         {
            this.iblink = iblinkmaster + "&invitation_id=11204963&token=16584";
         }
         else if (accounttype.toUpperCase().contains("TRUST"))   // Trust
         {
            this.iblink = iblinkmaster + "&invitation_id=11204963&token=16584";
         }
         else if (accounttype.toUpperCase().contains("ORGAN"))   // Organization
         {
            this.iblink = iblinkmaster + "&invitation_id=11204963&token=16584";
         }
         else if (accounttype.toUpperCase().contains("IRA"))    // IRA  (IRA/SEP/ROTH...)
         {
            this.iblink = iblinkmaster + "invitation_id=11204955&token=81843";
         }
      }
   }

   public ArrayList<Asset> getEditableAsset()
   {
      return editableAsset;
   }

   public void setEditableAsset(Asset data)
   {
      this.editableAsset.add(data);
   }

   public void recreateEditableAsset()
   {
      editableAsset = new ArrayList<Asset>();
   }

   public void resetPortfolioData()
   {
      setLogonid(null);
      setAcctnum(null);
      setClientAccountID(null);

      setYearly(12);
      setGoal(null);
      setAccountType(null);
      setTradePreference(InvConst.TRADE_MODE);
      setName(null);
      setHorizon(20);
      setAge(30);
      setCalendarYear(null);
      setNumOfAllocation(1);
      setNumOfPortfolio(1);
      setInitialInvestment(100000);
      setActualInvestment(null);
      setKeepLiquid(null);
      setRecurringInvestment(null);
      setExperience(2);   // 1 = Experienced, 2 = inExperienced (See method strExpeience)
      setObjective(2);    // 1 = Preservation, 2 = Accumulation; (See method strObjective)

      setAdvisor(null);
      setRep(null);
      setTheme(null);
      setBasket(null);
      setRiskAnswers(null);
      setFixedModel(false);
      goalData = new GoalsData();

      setStayInvested(1); // 1 = go to cash, 2 = stayInvested (See method strStayInvested)
      setCharitableGoals(null);

      setDependent(0);

      setCurrentIncome(0);
      setLiquidAsset(0);
      setTotalIncome(0);
      setTotalExpense(0);
      setTotalAsset(0);
      setTotalLiability(0);

      setAccountTaxable(false); //1 (True) for accountTaxable (False) for nonTaxable
      setTaxrate(0.1);
      setRiskCalcMethod("C");
      setRiskIndex(0.0);
      setAllocationIndex(InvConst.ASSET_DEFAULT_POINT);
      setPortfolioIndex(InvConst.PORTFOLIO_DEFAULT_POINT);
      setNumOfAllocation(InvConst.ASSET_INTERPOLATION);
      setNumOfPortfolio(InvConst.PORTFOLIO_INTERPOLATION);
      setMeterRiskIndicator(10);

      if (getEditableAsset() != null)
      {
         getEditableAsset().clear();
      }

      if (getAssetData() != null)
      {
         setAssetData(null);
      }

      if (getPortfolioData() != null)
      {
         setPortfolioData(null);
      }

      if (getProjectionData() != null)
      {
         setProjectionData(null);
      }

      setIblink("https://www.clientam.com/Universal/servlet/formWelcome?partnerID=Invessence&invitation_id=6596230&token=56551&invitedBy=NDE4aW52ZXN0&.");

   }
}
