package com.invmodel.inputData;

import java.util.*;

import com.invmodel.Const.InvConst;
import com.invmodel.asset.data.*;
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
   private Integer yearly = 12;
   private String goal;
   private String accountType;
   private String name;
   private Integer age;
   private Integer horizon;
   private Integer calendarYear;
   private Integer numOfAllocation = 1;
   private Integer numOfPortfolio = 1;
   private Integer initialInvestment;
   private Integer actualInvestment;
   private Integer keepLiquid;
   private Integer recurringInvestment;
   private Integer experience = 2; // 1 = Experienced, 2 = inExperienced (See method strExpeience)
   private Integer objective = 2; // 1 = Preservation, 2 = Accumulation; (See method strObjective)
   private String advisor;
   private String theme;


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

   private String risk = "M";
   private Integer riskIndex = 0;

   private List<Asset> editableAsset = new ArrayList<Asset>();
   private AssetClass assetData[];
   private Portfolio[] portfolioData;   // Although the arrary is not required, we are using to show performace data.

   private String iblink = "https://www.clientam.com/Universal/servlet/formWelcome?partnerID=Invessence&invitation_id=6596230&token=56551&invitedBy=NDE4aW52ZXN0&.";



   private Map<String, CustomAllocation> customAllocations = new HashMap<String, CustomAllocation>();

   public ProfileData getInstance()
   {
      return this;
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
      determineTaxable(getGoal(), getAccountType());
      // NOTE: Explore page overrides this behaviour, because the account type is not selected.
   }

   public void determineTaxable(String goal, String acctType)
   {
      if (acctType != null && acctType.equalsIgnoreCase("non-tax"))
      {
         // Account is taxable, therefore we pick tax free securities like MUNI
         setAccountTaxable(false);
      }
      else if (acctType != null && acctType.equalsIgnoreCase("taxable"))
      {
         // Account is taxable, therefore we pick tax free securities like MUNI
         setAccountTaxable(true);
      }
      else
      {
         // Account is IRA (Taxfree) therefore we pick securities with high yeild.
         setAccountTaxable(false);
      }
   }

   public String getAccountType()
   {
      return accountType;
   }

   public void setAccountType(String accountType)
   {
      this.accountType = accountType;
      determineTaxable(getGoal(), getAccountType());
      setIblink(accountType);
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


   public Integer getAge()
   {
     return age;
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
         setStayInvested ((horizon <= 5) ? ((getAccountTaxable() ? 2: 1)) : 1);
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

   public Integer getInitialInvestment()
   {
      return initialInvestment;
   }

   public void setInitialInvestment(Integer initialInvestment)
   {
      this.initialInvestment = initialInvestment;
   }

   public Integer getActualInvestment()
   {
      return actualInvestment;
   }

   public void setActualInvestment(Integer actualInvestment)
   {
      this.actualInvestment = actualInvestment;
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
      return recurringInvestment;
   }

   public void setRecurringInvestment(Integer recurringInvestment)
   {
      this.recurringInvestment = recurringInvestment;
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
      if (theme == null)
      {
         return "Default";
      }
      return theme;
   }

   public void setTheme(String theme)
   {
      this.theme = theme;
   }

   public String getAdvisor()
   {
      if (advisor == null)
      {
         return "Invessence";
      }
      return advisor;
   }

   public void setAdvisor(String advisor)
   {
      this.advisor = advisor;
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

   public String getRisk()
   {
      if (risk == null)
      {
         return "M";
      }
      else
      {
         return risk;
      }
   }

   public void setRisk(String risk)
   {
      this.risk = risk;
   }

   public Integer getRiskIndex()
   {
      if (riskIndex == null)
      {
         return 0;
      }
      else
      {
         return riskIndex;
      }
   }

   public void setRiskIndex(Integer riskIndex)
   {
      this.riskIndex = riskIndex;
   }

   public void adjustRiskIndex()
   {
      Double riskOffset = 0.0;
      Double currentAssets;
      Double currentLiabilities;
      double dToEqtRatio;

      // If objective is income preservation of asset, set the riskIndex to less half if smaller than half
      if (getObjective() == 1)
      {
         if (riskIndex < InvConst.MAX_RISK_OFFSET / 2)
         {
            riskIndex = InvConst.MAX_RISK_OFFSET / 2;
            setRiskIndex(riskIndex);
         }
      }

      //12 months of liquid cash to meet expenses
      currentAssets = ((double) getTotalIncome() * 0.7 + getLiquidAsset());

      // 12 months of total liabilities expenses
      currentLiabilities = (double) getDependent() * getYearly() * InvConst.MONTHLY_CHILD_COST + getTotalExpense();


      dToEqtRatio = 0;
      riskOffset = 1.0 * getRiskIndex();

      if (currentAssets > 0)
      {
         dToEqtRatio = currentLiabilities / currentAssets;
      }

      Integer dToEqtRatioReliable = 1;

      if (currentAssets < InvConst.MIN_CURRENT_ASSET)
      {
         dToEqtRatioReliable = 0;
      }

      if (dToEqtRatioReliable >= 1)
      {
         if (dToEqtRatio > 1.5)
         {
            riskOffset = InvConst.MAX_RISK_OFFSET.doubleValue();
         }
         else if (dToEqtRatio > 0.5)
         {
            riskOffset = riskOffset + (dToEqtRatio - 0.5) * (InvConst.MAX_RISK_OFFSET - riskOffset);
         }
      }

      if (riskOffset > InvConst.MAX_RISK_OFFSET)
      {
         setRiskIndex(InvConst.MAX_RISK_OFFSET);
      }
      else
      {
         setRiskIndex((int) Math.round(riskOffset));
      }
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
         displayList.put("Other", "Other");
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
   public void resetCustomAllocation() {
      this.customAllocations.clear();
   }

   public void addCustomAllocation(String assetclass, String subclass, double risk) {
      String key;
      try
      {
         if (assetclass == null)
            assetclass = "";
         if (subclass == null)
            subclass = "";

         key = assetclass.toUpperCase() + "." + subclass.toUpperCase();
         CustomAllocation ca = new CustomAllocation(assetclass, subclass, risk);
         if (this.customAllocations == null)
         {
            this.customAllocations = new HashMap<String, CustomAllocation>();
         }
         this.customAllocations.put(key, ca);
      }

      catch(Exception ex)
      {
         ex.printStackTrace();
      }

   }


   public String getIblink()
   {
      return iblink;
   }

   public void setIblink(String accounttype)
   {
      // First set the default as Individual.
      this.iblink = "https://www.clientam.com/Universal/servlet/formWelcome?partnerID=Invessence&invitation_id=6596230&token=56551&invitedBy=NDE4aW52ZXN0&.";
      if (accounttype != null) {
         if (accounttype.toUpperCase().startsWith("NON"))    // General Non-taxable (IRA/SEP/ROTH...)
            this.iblink = "https://www.clientam.com/Universal/servlet/formWelcome?partnerID=Invessence&invitation_id=6818475&token=91070&invitedBy=NDE4aW52ZXN0&.";
         if (accounttype.toUpperCase().contains("JOINT"))        // Joint
            this.iblink = "https://www.clientam.com/Universal/servlet/formWelcome?partnerID=Invessence&invitation_id=6596232&token=78468&invitedBy=NDE4aW52ZXN0&.";
         else if (accounttype.toUpperCase().contains("TRUST"))   // Trust
            this.iblink = "https://www.clientam.com/Universal/servlet/formWelcome?partnerID=Invessence&invitation_id=6596237&token=90513&invitedBy=NDE4aW52ZXN0&.";
         else if (accounttype.toUpperCase().contains("ORGAN"))   // Organization
            this.iblink = "https://www.clientam.com/Universal/servlet/formWelcome?partnerID=Invessence&invitation_id=6596233&token=12939&invitedBy=NDE4aW52ZXN0&.";
         else if (accounttype.toUpperCase().contains("IRA"))    // IRA  (IRA/SEP/ROTH...)
            this.iblink = "https://www.clientam.com/Universal/servlet/formWelcome?partnerID=Invessence&invitation_id=6818475&token=91070&invitedBy=NDE4aW52ZXN0&.";
      }
      /*
      this.iblink = Const.IB_BASEURL +
         "?" + Const.IB_PARTNERID +
         "&" + invitation_id +
         "&" + token +
         "&" + Const.IB_INVITEDBY;
      */
   }

   public List<Asset> getEditableAsset()
   {
      return editableAsset;
   }

   public void setEditableAsset(List<Asset> editableAsset)
   {
      this.editableAsset = editableAsset;
   }

   public void resetPortfolioData() {
      setYearly(12);
      setGoal(null);
      setAccountType(null);
      setName(null);
      setAge(null);
      setHorizon(null);
      setCalendarYear(null);
      setNumOfAllocation(1);
      setNumOfPortfolio(1);
      setInitialInvestment(null);
      setActualInvestment(null);
      setKeepLiquid(null);
      setRecurringInvestment(null);
      setExperience(2);   // 1 = Experienced, 2 = inExperienced (See method strExpeience)
      setObjective(2);    // 1 = Preservation, 2 = Accumulation; (See method strObjective)

      setAdvisor(InvConst.DEFAULT_ADVISOR);
      setTheme(InvConst.DEFAULT_THEME);

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
      setRisk("M");
      setRiskIndex(0);

      if (getEditableAsset() != null)
         getEditableAsset().clear();

      if (getAssetData() != null)
         setAssetData(null);

      if (getPortfolioData() != null)
         setPortfolioData(null);

      setIblink("https://www.clientam.com/Universal/servlet/formWelcome?partnerID=Invessence&invitation_id=6596230&token=56551&invitedBy=NDE4aW52ZXN0&.");

   }
}
