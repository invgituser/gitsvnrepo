package com.invessence.data.ltam;

import com.invmodel.ltam.data.*;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 7/20/15
 * Time: 2:34 PM
 * To change this template use File | Settings | File Templates.
 */
public class LTAMCustomerData extends LTAMRiskData
{
   // This data is for visitor.
   private Long logonid;
   private String advisor;
   private String rep;
   private String ipaddress;
   private String source;
   private String data;
   // -- Data below is when creating account
   private Long acctnum;
   private String geminiAcctNum;
   private String prefix, firstname, lastname, suffix;
   private String displayFullName;
   private Integer age;
   private String accttype;
   private String theme;
   private Double horizon;
   private Double Investment;
   private String forwarded;
   private String acknowledged;
   private LTAMTheme themeData;

   public LTAMCustomerData()
   {
      super();
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
      if (age <= 30 )
         setAns3(1);
      else if (age <= 40)
         setAns3(2);
      else if (age <= 50)
         setAns3(3);
      else if (age <= 60)
         setAns3(4);
      else if (age > 60)
         setAns3(5);
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

   public Double getHorizon()
   {
      return horizon;
   }

   public void setHorizon(Double horizon)
   {
      this.horizon = horizon;
      if (horizon <= 6 )
         setAns3(1);
      else if (horizon <= 8)
         setAns3(2);
      else if (horizon <= 11)
         setAns3(3);
      else if (horizon <= 13)
         setAns3(4);
      else if (horizon > 13)
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
      this.themeData = themeData;
   }

   public void resetAllData() {
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
      theme = "growth";
      horizon = null;
      Investment = null;
      super.resetAllData();
   }


}
