package com.invessence.web.data.common;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 1/3/15
 * Time: 10:28 PM
 * To change this template use File | Settings | File Templates.
 */
public class AccountData
{
   private	Long	logonid	;
   private	Long	acctnum	;
   private  String advisor_priviledge;
   private	String	email	;
   private	String	userid	;
   private	String	advisor	;
   private	String	theme	;
   private	String	lastname	;
   private	String	firstname	;
   private	String	state	;
   private	String	clientAccountID	;
   private	String	acctStatus	;
   private	String	currentstatus	;
   private	String	tradePreference	;
   private	String	goal	;
   private  String   portfolioName;
   private	String	accttype	;
   private	Integer	age	;
   private	Integer	horizon	;
   private	Integer	yearnum	;
   private	Double	riskIndex	;
   private	Double	initialInvestment	;
   private	Double	actualCapital	;
   private	Double	keepLiquid	;
   private	Double	recurringInvestment	;
   private	Integer	longTermGoal	;
   private	Integer	stayInvested	;
   private	Integer	dependent	;
   private	String	dateOpened	;
   private	Double	totalIncomeAnnulized	;
   private	Double	totalExpenseAnnulized	;
   private	Double	totalAsset	;
   private	Double	totalDebt	;
   private	Double	liquidnetworth	;
   private	Double	networth	;
   private	Integer	ans1	;
   private	Integer	ans2	;
   private	Integer	ans3	;
   private	Integer	ans4	;
   private	Integer	ans5	;
   private	Integer	ans6	;
   private	Integer	ans7	;
   private	Integer	ans8	;
   private	Integer	ans9	;
   private	Integer	ans10	;
   private	Integer	ans11	;
   private	Integer	ans12	;
   private	Integer	ans13	;
   private	Integer	ans14	;
   private	Integer	ans15	;
   private	String	created	;

   public AccountData()
   {
   }

   public Long getLogonid()
   {
      return logonid;
   }

   public void setLogonid(Long logonid)
   {
      this.logonid = logonid;
   }

   public Long getAcctnum()
   {
      return acctnum;
   }

   public void setAcctnum(Long acctnum)
   {
      this.acctnum = acctnum;
   }

   public String getAdvisor_priviledge()
   {
      return advisor_priviledge;
   }

   public void setAdvisor_priviledge(String advisor_priviledge)
   {
      this.advisor_priviledge = advisor_priviledge;
   }

   public String getDisplayAcct()
   {
     if (getClientAccountID() == null || getClientAccountID().length() == 0)
        return getCurrentstatus() + " (" + getAcctnum().toString() +")";
     else
        return getClientAccountID();
   }

   public String getEmail()
   {
      return email;
   }

   public void setEmail(String email)
   {
      this.email = email;
   }

   public String getUserid()
   {
      return userid;
   }

   public void setUserid(String userid)
   {
      this.userid = userid;
   }

   public String getAdvisor()
   {
      return advisor;
   }

   public void setAdvisor(String advisor)
   {
      this.advisor = advisor;
   }

   public String getTheme()
   {
      return theme;
   }

   public void setTheme(String theme)
   {
      this.theme = theme;
   }

   public String getLastname()
   {
      return lastname;
   }

   public void setLastname(String lastname)
   {
      this.lastname = lastname;
   }

   public String getFirstname()
   {
      return firstname;
   }

   public String getFullname()
   {
      if (getLastname() == null || getLastname().length() == 0)
         return firstname;
      else {
         if (getFirstname() == null || getFirstname().length() == 0)
            return getLastname();
         else
            return getLastname() + ", " + getFirstname();
      }
   }

   public void setFirstname(String firstname)
   {
      this.firstname = firstname;
   }

   public String getState()
   {
      return state;
   }

   public void setState(String state)
   {
      this.state = state;
   }

   public String getClientAccountID()
   {
      return clientAccountID;
   }

   public void setClientAccountID(String clientAccountID)
   {
      this.clientAccountID = clientAccountID;
   }

   public String getAcctStatus()
   {
      if (acctStatus == null)
         return "Pending";
      return acctStatus;
   }

   public void setAcctStatus(String acctStatus)
   {
      this.acctStatus = acctStatus;
   }

   public Boolean getAcctStatusBoolean()
   {
      if (acctStatus == null)
         return false;

      if (acctStatus.startsWith("A"))
         return true;
      else
         return false;
   }

   public String getCurrentstatus()
   {
      return currentstatus;
   }

   public void setCurrentstatus(String currentstatus)
   {
      this.currentstatus = currentstatus;
   }

   public String getTradePreference()
   {
      return tradePreference;
   }

   public void setTradePreference(String tradePreference)
   {
      this.tradePreference = tradePreference;
   }

   public String getGoal()
   {
      return goal;
   }

   public void setGoal(String goal)
   {
      this.goal = goal;
   }

   public String getPortfolioName()
   {
      return portfolioName;
   }

   public void setPortfolioName(String portfolioName)
   {
      this.portfolioName = portfolioName;
   }

   public String getAccttype()
   {
      return accttype;
   }

   public void setAccttype(String accttype)
   {
      this.accttype = accttype;
   }

   public Integer getAge()
   {
      return age;
   }

   public void setAge(Integer age)
   {
      this.age = age;
   }

   public Integer getHorizon()
   {
      return horizon;
   }

   public void setHorizon(Integer horizon)
   {
      this.horizon = horizon;
   }

   public Integer getYearnum()
   {
      return yearnum;
   }

   public void setYearnum(Integer yearnum)
   {
      this.yearnum = yearnum;
   }

   public Double getRiskIndex()
   {
      return riskIndex;
   }

   public void setRiskIndex(Double riskIndex)
   {
      this.riskIndex = riskIndex;
   }

   public Double getInitialInvestment()
   {
      return initialInvestment;
   }

   public void setInitialInvestment(Double initialInvestment)
   {
      this.initialInvestment = initialInvestment;
   }

   public Double getActualCapital()
   {
      if (actualCapital == null || actualCapital == 0.0)
         return getInitialInvestment();
      return actualCapital;
   }

   public Double getInvested()
   {
      if (getAcctStatus().startsWith("P") || !getCurrentstatus().startsWith("A"))
         return getInitialInvestment();
      return actualCapital;
   }

   public void setActualCapital(Double actualCapital)
   {
      this.actualCapital = actualCapital;
   }

   public Double getKeepLiquid()
   {
      return keepLiquid;
   }

   public void setKeepLiquid(Double keepLiquid)
   {
      this.keepLiquid = keepLiquid;
   }

   public Double getRecurringInvestment()
   {
      return recurringInvestment;
   }

   public void setRecurringInvestment(Double recurringInvestment)
   {
      this.recurringInvestment = recurringInvestment;
   }

   public Integer getLongTermGoal()
   {
      return longTermGoal;
   }

   public void setLongTermGoal(Integer longTermGoal)
   {
      this.longTermGoal = longTermGoal;
   }

   public Integer getStayInvested()
   {
      return stayInvested;
   }

   public void setStayInvested(Integer stayInvested)
   {
      this.stayInvested = stayInvested;
   }

   public Integer getDependent()
   {
      return dependent;
   }

   public void setDependent(Integer dependent)
   {
      this.dependent = dependent;
   }

   public String getDateOpened()
   {
      if (dateOpened.contains("-"))
         return dateOpened;
      else {
         return dateOpened.substring(0,4) + "-" + dateOpened.substring(4,6) + "-" + dateOpened.substring(6);
      }
   }

   public void setDateOpened(String dateOpened)
   {
      this.dateOpened = dateOpened;
   }

   public Double getTotalIncomeAnnulized()
   {
      return totalIncomeAnnulized;
   }

   public void setTotalIncomeAnnulized(Double totalIncomeAnnulized)
   {
      this.totalIncomeAnnulized = totalIncomeAnnulized;
   }

   public Double getTotalExpenseAnnulized()
   {
      return totalExpenseAnnulized;
   }

   public void setTotalExpenseAnnulized(Double totalExpenseAnnulized)
   {
      this.totalExpenseAnnulized = totalExpenseAnnulized;
   }

   public Double getTotalAsset()
   {
      return totalAsset;
   }

   public void setTotalAsset(Double totalAsset)
   {
      this.totalAsset = totalAsset;
   }

   public Double getTotalDebt()
   {
      return totalDebt;
   }

   public void setTotalDebt(Double totalDebt)
   {
      this.totalDebt = totalDebt;
   }

   public Double getLiquidnetworth()
   {
      return liquidnetworth;
   }

   public void setLiquidnetworth(Double liquidnetworth)
   {
      this.liquidnetworth = liquidnetworth;
   }

   public Double getNetworth()
   {
      return networth;
   }

   public void setNetworth(Double networth)
   {
      this.networth = networth;
   }

   public Integer getAns1()
   {
      return ans1;
   }

   public void setAns1(Integer ans1)
   {
      this.ans1 = ans1;
   }

   public Integer getAns2()
   {
      return ans2;
   }

   public void setAns2(Integer ans2)
   {
      this.ans2 = ans2;
   }

   public Integer getAns3()
   {
      return ans3;
   }

   public void setAns3(Integer ans3)
   {
      this.ans3 = ans3;
   }

   public Integer getAns4()
   {
      return ans4;
   }

   public void setAns4(Integer ans4)
   {
      this.ans4 = ans4;
   }

   public Integer getAns5()
   {
      return ans5;
   }

   public void setAns5(Integer ans5)
   {
      this.ans5 = ans5;
   }

   public Integer getAns6()
   {
      return ans6;
   }

   public void setAns6(Integer ans6)
   {
      this.ans6 = ans6;
   }

   public Integer getAns7()
   {
      return ans7;
   }

   public void setAns7(Integer ans7)
   {
      this.ans7 = ans7;
   }

   public Integer getAns8()
   {
      return ans8;
   }

   public void setAns8(Integer ans8)
   {
      this.ans8 = ans8;
   }

   public Integer getAns9()
   {
      return ans9;
   }

   public void setAns9(Integer ans9)
   {
      this.ans9 = ans9;
   }

   public Integer getAns10()
   {
      return ans10;
   }

   public void setAns10(Integer ans10)
   {
      this.ans10 = ans10;
   }

   public Integer getAns11()
   {
      return ans11;
   }

   public void setAns11(Integer ans11)
   {
      this.ans11 = ans11;
   }

   public Integer getAns12()
   {
      return ans12;
   }

   public void setAns12(Integer ans12)
   {
      this.ans12 = ans12;
   }

   public Integer getAns13()
   {
      return ans13;
   }

   public void setAns13(Integer ans13)
   {
      this.ans13 = ans13;
   }

   public Integer getAns14()
   {
      return ans14;
   }

   public void setAns14(Integer ans14)
   {
      this.ans14 = ans14;
   }

   public Integer getAns15()
   {
      return ans15;
   }

   public void setAns15(Integer ans15)
   {
      this.ans15 = ans15;
   }

   public String getCreated()
   {
      return created;
   }

   public void setCreated(String created)
   {
      this.created = created;
   }
}
