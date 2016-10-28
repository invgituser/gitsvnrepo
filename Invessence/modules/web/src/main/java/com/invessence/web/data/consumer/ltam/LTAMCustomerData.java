package com.invessence.web.data.consumer.ltam;

import org.primefaces.model.chart.*;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 7/20/15
 * Time: 2:34 PM
 * To change this template use File | Settings | File Templates.
 */
public class LTAMCustomerData extends LTAMRiskData
{
/*
   // This data is for visitor.
   private Long logonid;
   private String advisor;
   private String rep;
   private String ipaddress;
   private String source;
   private String data;
   // -- Data below is when creating account
   private String timeToSaveID;
   private Long acctnum;
   private String geminiAcctNum;
   private String prefix, firstname, lastname, suffix;
   private String displayFullName;
   private Integer age;
   private String accttype;
   private String theme;
   private Integer horizon;
   private Double Investment;
   private String forwarded;
   private String formula;
   private String acknowledged;
   private LTAMTheme themeData;
   private LTAMAllocationData allocationData;
   private PieChartModel pieChart;
   private String cusip;
   private String securityname;
   private Integer fundID;
   private String acceptterms;


   public LTAMCustomerData()
   {
      super();
      allocationData = new LTAMAllocationData();
      horizon = null;
   }

   public LTAMCustomerData getInstance() {
      return this;
   }

   public Long getLogonid()
   {
      if (logonid == null)
         return 0L;
      return logonid;
   }

   public void setLogonid(Long logonid)
   {
      this.logonid = logonid;
   }

   public Long getActualAcctnum()
   {
      return acctnum;
   }

   public Long getAcctnum()
   {
      if (acctnum == null)
         return 0L;
      return acctnum;
   }

   public void setAcctnum(Long acctnum)
   {
      this.acctnum = acctnum;
   }

   public String getGeminiAcctNum()
   {
      return geminiAcctNum;
   }

   public void setGeminiAcctNum(String geminiAcctNum)
   {
      this.geminiAcctNum = geminiAcctNum;
   }

   public String getAdvisor()
   {
      if (advisor == null || advisor.isEmpty())
         return Const.DEFAULT_ADVISOR;
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

   public String getIpaddress()
   {
      return ipaddress;
   }

   public void setIpaddress(String ipaddress)
   {
      this.ipaddress = ipaddress;
   }

   public String getSource()
   {
      return source;
   }

   public void setSource(String source)
   {
      this.source = source;
   }

   public String getData()
   {
      return data;
   }

   public void setData(String data)
   {
      this.data = data;
   }

   public String getTimeToSaveID()
   {
      return timeToSaveID;
   }

   public void setTimeToSaveID(String timeToSaveID)
   {
      this.timeToSaveID = timeToSaveID;
   }

   public String getPrefix()
   {
      return prefix;
   }

   public void setPrefix(String prefix)
   {
      this.prefix = prefix;
   }

   public String getFirstname()
   {
      return firstname;
   }

   public void setFirstname(String firstname)
   {
      this.firstname = firstname;
   }

   public String getLastname()
   {
      return lastname;
   }

   public void setLastname(String lastname)
   {
      this.lastname = lastname;
   }

   public String getSuffix()
   {
      return suffix;
   }

   public void setSuffix(String suffix)
   {
      this.suffix = suffix;
   }

   public String getDisplayFullName()
   {
      return displayFullName;
   }

   public void setDisplayFullName(String displayFullName)
   {
      this.displayFullName = displayFullName;
   }

   public Integer getAge()
   {
      return age;
   }

   public void setAge(Integer age)
   {
      this.age = age;
      setAgeforRisk(age);

*/
/*    Orginal version - Changed on April 6, 2016 Algo. model (Jigar/Prashant)
      if (age <= 30 ) {
         setAns1(1);
         setDefaultAns6(5);
      }
      else if (age <= 40) {
         setAns1(2);
         setDefaultAns6(4);
      }
      else if (age <= 50) {
         setAns1(3);
         setDefaultAns6(3);
      }
      else if (age <= 60) {
         setAns1(4);
         setDefaultAns6(2);
      }
      else if (age > 60) {
         setAns1(5);
         setDefaultAns6(1);
      }
*//*

   }

   public String getAccttype()
   {
      return accttype;
   }

   public void setAccttype(String accttype)
   {
      this.accttype = accttype;
   }

   public String getTheme()
   {
      return theme;
   }

   public void setTheme(String theme)
   {
      this.theme = theme;
   }

   public Integer getHorizon()
   {
      return horizon;
   }

   public void setHorizon(Integer horizon)
   {
      if (horizon == null)
         return;

      this.horizon = horizon;
      if (horizon <= 3 )
         setAns3(1);
      else if (horizon <= 6)
         setAns3(2);
      else if (horizon <= 9)
         setAns3(3);
      else if (horizon <= 12)
         setAns3(4);
      else if (horizon >= 13)
         setAns3(5);
   }

   public Double getInvestment()
   {
      return Investment;
   }

   public void setInvestment(Double investment)
   {
      Investment = investment;
   }

   public String getForwarded()
   {
      return forwarded;
   }

   public void setForwarded(String forwarded)
   {
      this.forwarded = forwarded;
   }

   public String getFormula()
   {
      return formula;
   }

   public void setFormula(String formula)
   {
      this.formula = formula;
   }

   public String getAcknowledged()
   {
      return acknowledged;
   }

   public void setAcknowledged(String acknowledged)
   {
      this.acknowledged = acknowledged;
   }

   public LTAMTheme getThemeData()
   {
      return themeData;
   }

   public void setThemeData(LTAMTheme themeData)
   {
      if (themeData == null)
         return;

      try {
         this.themeData = themeData;
         allocationData.copyAssetAllocation(themeData.getAssetsData(),getInvestment());
         allocationData.copyAssetSubAllocation(themeData.getPortfolioData(),getInvestment());
         setCusip(themeData.getCusip());
         setSecurityname(themeData.getSecurityname());
         Integer fundID = Integer.valueOf(themeData.getFundID());
         setFundID(fundID);
      }
      catch (Exception ex) {

      }
   }

   public LTAMAllocationData getAllocationData()
   {
      return allocationData;
   }

   public void setAllocationData(LTAMAllocationData allocationData)
   {
      this.allocationData = allocationData;
   }

   public PieChartModel getPieChart()
   {
      return pieChart;
   }

   public void setPieChart(PieChartModel pieChart)
   {
      this.pieChart = pieChart;
   }

   public String getCusip()
   {
      return cusip;
   }

   public void setCusip(String cusip)
   {
      this.cusip = cusip;
   }

   public String getSecurityname()
   {
      return securityname;
   }

   public void setSecurityname(String securityname)
   {
      this.securityname = securityname;
   }

   public Integer getFundID()
   {
      return fundID;
   }

   public void setFundID(Integer fundID)
   {
      this.fundID = fundID;
   }

   public String getAcceptterms()
   {
      return acceptterms;
   }

   public void setAcceptterms(String acceptterms)
   {
      this.acceptterms = acceptterms;
   }

   public void resetAllData() {
      timeToSaveID = null;
      logonid = null;
      acctnum = null;
      geminiAcctNum = null;
      advisor = null;
      rep = null;
      ipaddress = null;
      prefix = null;
      firstname = null;
      lastname = null;
      suffix = null;
      displayFullName = null;
      age = null;
      accttype = null;
      theme = "66538B164";
      horizon = null;
      Investment = null;
      formula="Q";
      super.resetAllData();
      allocationData = new LTAMAllocationData();
      forwarded = null;
      pieChart = null;
      cusip = null;
      securityname = null;
      fundID = null;
   }

   public void copyData(AccountData accountData) {
      if (accountData != null) {
         setTimeToSaveID(null);
         setLogonid(accountData.getLogonid());
         setAcctnum(accountData.getAcctnum());
         setGeminiAcctNum(accountData.getClientAccountID());
         setAdvisor(accountData.getAdvisor());
         setRep(accountData.getAdvisor());
         setIpaddress(null);
         setPrefix(null);
         setFirstname(accountData.getFirstname());
         setLastname(accountData.getLastname());
         setSuffix(null);
         setDisplayFullName(getFirstname() + " " + getLastname());
         setAge(accountData.getAge());
         setAccttype(accountData.getAccttype());
         setTheme(accountData.getTheme());
         setHorizon(accountData.getHorizon());
         setInvestment(accountData.getActualCapital());
         setFormula(accountData.getFormula());
         super.resetAllData();
         setAllocationData(new LTAMAllocationData());
         setForwarded(null);
         setCusip(accountData.getCusip());
         setSecurityname(accountData.getSecurityName());
         setFundID(accountData.getFundID());

         setAns2(accountData.getAns2());
         setAns3(accountData.getAns3());
         setAns4(accountData.getAns4());
         setAns5(accountData.getAns5());
         setAns6(accountData.getAns6());
         calcRiskIndex();
      }
   }


*/

}
