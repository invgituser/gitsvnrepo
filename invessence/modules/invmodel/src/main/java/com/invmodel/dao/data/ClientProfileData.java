package com.invmodel.dao.data;

import java.text.SimpleDateFormat;
import java.util.*;

import com.google.common.base.Joiner;

import static com.invmodel.utils.XMLBuilder.buildElement;
import static java.lang.String.valueOf;


public class ClientProfileData
{

   // name, goal, type,
   //IB_acctNum, inception, sortorder);
    private String name = "";
    private String taxType = "";
    private String goal = "";

   private int age = 0;
   private int duration = 0;
   private boolean stayInvested = true;
   private double totalCapital = 0.0;
   private String dateCreated = "";
   private int riskIndex = 0;
   private double totalIncomeAnnulized = 0;

    private String IB_acctNum;



    public ClientProfileData() {
    }


    public ClientProfileData(String name, String taxType, String goal,
                             String IB_acctNum, int age, int duration, int riskIndex, boolean stayInvested,
                             double totalCapital, String dateCreated , double totalIncomeAnnulized
                             ) {
       super();

       this.name = name;
       this.taxType = taxType;
       this.goal = goal;

       this.IB_acctNum = IB_acctNum;

       this.age = age;
       this.duration = duration;
       this.riskIndex = riskIndex;
       this.stayInvested = stayInvested;
       this.totalCapital = totalCapital;
       this.dateCreated = dateCreated;
       this.totalIncomeAnnulized = totalIncomeAnnulized;

    }

   public double getTotalIncomeAnnulized()
   {
      return totalIncomeAnnulized;
   }

   public void setTotalIncomeAnnulized(double totalIncomeAnnulized)
   {
      this.totalIncomeAnnulized = totalIncomeAnnulized;
   }

   public int getRiskIndex()
   {
      return riskIndex;
   }

   public void setRiskIndex(int riskIndex)
   {
      this.riskIndex = riskIndex;
   }

   public int getAge()
   {
      return age;
   }

   public void setAge(int age)
   {
      this.age = age;
   }

   public int getDuration()
   {
      return duration;
   }

   public void setDuration(int duration)
   {
      this.duration = duration;
   }

   public boolean isStayInvested()
   {
      return stayInvested;
   }

   public void setStayInvested(boolean stayInvested)
   {
      this.stayInvested = stayInvested;
   }

   public double getTotalCapital()
   {
      return totalCapital;
   }

   public void setTotalCapital(double totalCapital)
   {
      this.totalCapital = totalCapital;
   }

   public String getDateCreated()
   {
      return dateCreated;
   }

   public void setDateCreated(String dateCreated)
   {
      this.dateCreated = dateCreated;
   }



   public String getName() {
        return name;
    }

   public String getTaxType()
   {
      return taxType;
   }

   public String getGoal()
   {
      return goal;
   }

   public String getIB_acctNum()
   {
      return IB_acctNum;
   }

   public void setIB_acctNum(String IB_acctNum)
   {
      this.IB_acctNum = IB_acctNum;
   }

   public void setGoal(String goal)
   {
      this.goal = goal;
   }

   public void setTaxType(String taxType)
   {
      this.taxType = taxType;
   }

   public void setName(String name) {
        this.name = name;
    }




}
