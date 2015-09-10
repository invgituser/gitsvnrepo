package com.invessence.bean.ltam;

import java.io.Serializable;
import java.util.*;
import javax.annotation.PostConstruct;
import javax.faces.bean.*;

import com.invessence.util.*;


/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 2/4/15
 * Time: 1:18 PM
 * To change this template use File | Settings | File Templates.
 */

@ManagedBean(name = "ltamtest")
@SessionScoped
public class LTAMTest implements Serializable
{
   private String advisor;
   private String rep;
   private String timeToSaveID;
   private String amount;
   private String firstname;
   private String lastname;
   private Double investment;
   private Integer uid;
   private String fund;
   private WebUtil webutil = new WebUtil();

   @ManagedProperty("#{uiLayout}")
   private UILayout UILayout;
   public void setUILayout(UILayout UILayout)
   {
      this.UILayout = UILayout;
   }

   @ManagedProperty("#{ltamprofile}")
   private LTAMProfileBean profilebean;
   public void setProfilebean(LTAMProfileBean profilebean)
   {
      this.profilebean = profilebean;
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

   public String getTimeToSaveID()
   {
      return timeToSaveID;
   }

   public void setTimeToSaveID(String timeToSaveID)
   {
      this.timeToSaveID = timeToSaveID;
   }

   public String getAmount()
   {
      return amount;
   }

   public void setAmount(String amount)
   {
      this.amount = amount;
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

   public Double getInvestment()
   {
      return investment;
   }

   public void setInvestment(Double investment)
   {
      this.investment = investment;
   }

   public Integer getUid()
   {
      return uid;
   }

   public void setUid(Integer uid)
   {
      this.uid = uid;
   }

   public String getFund()
   {
      return fund;
   }

   public void setFund(String fund)
   {
      this.fund = fund;
   }

   @PostConstruct
   public void init() {
       //menu.resetTheme("1");
   }

   public void demoStart() {
      if (amount != null && ! amount.isEmpty())
         timetoSavePortal();
      else
         clientPortal();
   }


      public void clientPortal() {
      Map<String,String> args = new LinkedHashMap<String, String>();

      if (advisor != null && ! advisor.isEmpty()) {
         args.put("advisor", advisor);

         if (rep != null && ! rep.isEmpty())
            args.put("rep", rep);

         profilebean.resetBean();
         args.put("cid","1");
         webutil.redirect("/start.xhtml", args);
      }
      else {
         webutil.showMessage(null, "W", "Advisor cannot be empty");
      }
   }

   public void timetoSavePortal() {
      Map<String,String> args = new LinkedHashMap<String, String>();
      Boolean ok2save = true;

      if (timeToSaveID != null && ! timeToSaveID.isEmpty())
         args.put("TimeToSaveID", timeToSaveID);
      else
         ok2save = false;

      if (amount != null && ! amount.isEmpty())
         args.put("amount", amount);
      else
         ok2save = false;

      if (firstname != null && ! firstname.isEmpty())
            args.put("first", firstname);
      else
         ok2save = false;

         if (lastname != null && ! lastname.isEmpty())
            args.put("last", lastname);
      else
            ok2save = false;

      if (ok2save) {
         profilebean.resetBean();
         args.put("cid", "1");
         webutil.redirect("/start.xhtml", args);
      }
      else {
         webutil.showMessage(null, "W", "Firstname, Lastname,and Amount all are required.");
      }
   }

   public void postMethod() {
      webutil.redirect("/gemini.xhtml", null);
   }

   public void dummyStart() {

   }
}

