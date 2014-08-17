package com.invessence.data.admin;

import com.invessence.converter.JavaUtil;
import com.invmodel.inputData.ProfileData;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 4/14/14
 * Time: 10:32 PM
 * To change this template use File | Settings | File Templates.
 */
public class AdminTradeClient extends ProfileData
{
   private JavaUtil javaUtil = new JavaUtil();
   private AdminTradeClient tradeClientinstance = null;
   private ProfileData profileInstance = null;
   private Long acctnum;
   private Long logonid;
   private String clientAccountID;
   private String userid;
   private String tradePreference;

   // Income/Expenses
   private Integer householdwages;
   private Integer mortgagePayment;
   private Integer otherIncome;
   private Integer otherExpense;

   // Asset/Liability
   private Integer moneymarket;
   private Integer autoLoan;
   private Integer investment;
   private Integer medical;
   private Integer mortgateEquity;
   private Integer mortgageLoan;
   private Integer otherSavings;
   private Integer otherDebt;


   private String selectedchoice1;
   private String selectedchoice2;
   private String selectedchoice3;
   private String selectedchoice4;
   private String selectedchoice5;
   private String selectedchoice6;
   private String selectedchoice7;
   private String selectedchoice8;
   private String selectedchoice9;
   private String selectedchoice10;
   private String selectedchoice11;
   private String selectedchoice12;
   private String selectedchoice13;
   private String selectedchoice14;
   private String selectedchoice15;

   private String processStatus;
   private String reason;
   private String lastTraded;
   private String assetAllocationOffset;
   private String created;
   private String lastUpdated;
   private String assetClass;
   private Double position, currentAllocation, requiredAllocation;

   public AdminTradeClient()
   {
      super();
      this.tradeClientinstance = this;
   }

   public AdminTradeClient getInstance()
   {
      return tradeClientinstance;
   }

   public Long getAcctnum()
   {
      return acctnum;
   }

   public void setAcctnum(Long acctnum)
   {
      this.acctnum = acctnum;
   }

   public Long getLogonid()
   {
      return logonid;
   }

   public void setLogonid(Long logonid)
   {
      this.logonid = logonid;
   }

   public String getClientAccountID()
   {
      return clientAccountID;
   }

   public void setClientAccountID(String clientAccountID)
   {
      this.clientAccountID = clientAccountID;
   }

   public String getUserid()
   {
      return userid;
   }

   public void setUserid(String userid)
   {
      this.userid = userid;
   }

   public String getTradePreference()
   {
      return tradePreference;
   }

   public void setTradePreference(String tradePreference)
   {
      this.tradePreference = tradePreference;
   }

   public Integer getHouseholdwages()
   {
      return householdwages;
   }

   public void setHouseholdwages(Integer householdwages)
   {
      this.householdwages = householdwages;
   }

   public Integer getMortgagePayment()
   {
      return mortgagePayment;
   }

   public void setMortgagePayment(Integer mortgagePayment)
   {
      this.mortgagePayment = mortgagePayment;
   }

   public Integer getOtherIncome()
   {
      return otherIncome;
   }

   public void setOtherIncome(Integer otherIncome)
   {
      this.otherIncome = otherIncome;
   }

   public Integer getOtherExpense()
   {
      return otherExpense;
   }

   public void setOtherExpense(Integer otherExpense)
   {
      this.otherExpense = otherExpense;
   }

   public Integer getMoneymarket()
   {
      return moneymarket;
   }

   public void setMoneymarket(Integer moneymarket)
   {
      this.moneymarket = moneymarket;
   }

   public Integer getAutoLoan()
   {
      return autoLoan;
   }

   public void setAutoLoan(Integer autoLoan)
   {
      this.autoLoan = autoLoan;
   }

   public Integer getInvestment()
   {
      return investment;
   }

   public void setInvestment(Integer investment)
   {
      this.investment = investment;
   }

   public Integer getMedical()
   {
      return medical;
   }

   public void setMedical(Integer medical)
   {
      this.medical = medical;
   }

   public Integer getMortgateEquity()
   {
      return mortgateEquity;
   }

   public void setMortgateEquity(Integer mortgateEquity)
   {
      this.mortgateEquity = mortgateEquity;
   }

   public Integer getMortgageLoan()
   {
      return mortgageLoan;
   }

   public void setMortgageLoan(Integer mortgageLoan)
   {
      this.mortgageLoan = mortgageLoan;
   }

   public Integer getOtherSavings()
   {
      return otherSavings;
   }

   public void setOtherSavings(Integer otherSavings)
   {
      this.otherSavings = otherSavings;
   }

   public Integer getOtherDebt()
   {
      return otherDebt;
   }

   public void setOtherDebt(Integer otherDebt)
   {
      this.otherDebt = otherDebt;
   }

   public String getSelectedchoice1()
   {
      return selectedchoice1;
   }

   public void setSelectedchoice1(String selectedchoice1)
   {
      this.selectedchoice1 = selectedchoice1;
   }

   public String getSelectedchoice2()
   {
      return selectedchoice2;
   }

   public void setSelectedchoice2(String selectedchoice2)
   {
      this.selectedchoice2 = selectedchoice2;
   }

   public String getSelectedchoice3()
   {
      return selectedchoice3;
   }

   public void setSelectedchoice3(String selectedchoice3)
   {
      this.selectedchoice3 = selectedchoice3;
   }

   public String getSelectedchoice4()
   {
      return selectedchoice4;
   }

   public void setSelectedchoice4(String selectedchoice4)
   {
      this.selectedchoice4 = selectedchoice4;
   }

   public String getSelectedchoice5()
   {
      return selectedchoice5;
   }

   public void setSelectedchoice5(String selectedchoice5)
   {
      this.selectedchoice5 = selectedchoice5;
   }

   public String getSelectedchoice6()
   {
      return selectedchoice6;
   }

   public void setSelectedchoice6(String selectedchoice6)
   {
      this.selectedchoice6 = selectedchoice6;
   }

   public String getSelectedchoice7()
   {
      return selectedchoice7;
   }

   public void setSelectedchoice7(String selectedchoice7)
   {
      this.selectedchoice7 = selectedchoice7;
   }

   public String getSelectedchoice8()
   {
      return selectedchoice8;
   }

   public void setSelectedchoice8(String selectedchoice8)
   {
      this.selectedchoice8 = selectedchoice8;
   }

   public String getSelectedchoice9()
   {
      return selectedchoice9;
   }

   public void setSelectedchoice9(String selectedchoice9)
   {
      this.selectedchoice9 = selectedchoice9;
   }

   public String getSelectedchoice10()
   {
      return selectedchoice10;
   }

   public void setSelectedchoice10(String selectedchoice10)
   {
      this.selectedchoice10 = selectedchoice10;
   }

   public String getSelectedchoice11()
   {
      return selectedchoice11;
   }

   public void setSelectedchoice11(String selectedchoice11)
   {
      this.selectedchoice11 = selectedchoice11;
   }

   public String getSelectedchoice12()
   {
      return selectedchoice12;
   }

   public void setSelectedchoice12(String selectedchoice12)
   {
      this.selectedchoice12 = selectedchoice12;
   }

   public String getSelectedchoice13()
   {
      return selectedchoice13;
   }

   public void setSelectedchoice13(String selectedchoice13)
   {
      this.selectedchoice13 = selectedchoice13;
   }

   public String getSelectedchoice14()
   {
      return selectedchoice14;
   }

   public void setSelectedchoice14(String selectedchoice14)
   {
      this.selectedchoice14 = selectedchoice14;
   }

   public String getSelectedchoice15()
   {
      return selectedchoice15;
   }

   public void setSelectedchoice15(String selectedchoice15)
   {
      this.selectedchoice15 = selectedchoice15;
   }

   public String getProcessStatus()
   {
      return processStatus;
   }

   public void setProcessStatus(String processStatus)
   {
      this.processStatus = processStatus;
   }

   public String getReason()
   {
      return reason;
   }

   public void setReason(String reason)
   {
      this.reason = reason;
   }
   public String getDisplayLastTraded() {
      return javaUtil.displayDateFormat(getLastTraded());
   }

   public String getLastTraded()
   {
      return lastTraded;
   }

   public void setLastTraded(String lastTraded)
   {
      this.lastTraded = lastTraded;
   }

   public String getDisplayAAOffsetAPercent()
   {
      return (javaUtil.displayFormat(getAssetAllocationOffset(),"###.00"));
   }

   public String getAssetAllocationOffset()
   {
      return assetAllocationOffset;
   }

   public void setAssetAllocationOffset(String assetAllocationOffset)
   {
      this.assetAllocationOffset = assetAllocationOffset;
   }

   public String getCreated()
   {
      return created;
   }

   public void setCreated(String created)
   {
      this.created = created;
   }

   public String getLastUpdated()
   {
      return lastUpdated;
   }

   public void setLastUpdated(String lastUpdated)
   {
      this.lastUpdated = lastUpdated;
   }

   public String getDisplayAsActualInvestment() {
       if (getActualInvestment() == null)
          return javaUtil.displayFormat(getInitialInvestment(),"##,###,###,###.00");
       else
         return javaUtil.displayFormat(getActualInvestment(),"##,###,###,###.00");
   }

   public String getDisplayKeepLiquid() {
      return javaUtil.displayFormat(getKeepLiquid(),"###,###");
   }

   public String getAssetClass()
   {
      return assetClass;
   }

   public void setAssetClass(String assetClass)
   {
      this.assetClass = assetClass;
   }

   public Double getPosition()
   {
      return position;
   }

   public void setPosition(Double position)
   {
      this.position = position;
   }

   public Double getCurrentAllocation()
   {
      return currentAllocation;
   }

   public void setCurrentAllocation(Double currentAllocation)
   {
      this.currentAllocation = currentAllocation;
   }

   public Double getRequiredAllocation()
   {
      return requiredAllocation;
   }

   public void setRequiredAllocation(Double requiredAllocation)
   {
      this.requiredAllocation = requiredAllocation;
   }
}
