package com.invessence.bean.ltam;

import java.io.Serializable;
import java.util.*;
import javax.annotation.PostConstruct;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import com.invessence.converter.SQLData;
import com.invessence.dao.ltam.*;
import com.invessence.data.ltam.LTAMCustomerData;
import com.invessence.util.*;
import com.invessence.util.Impl.PagesImpl;
import com.invmodel.ltam.LTAMOptimizer;
import com.invmodel.ltam.data.LTAMTheme;


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
   private String amount;
   private String firstname;
   private String lastname;
   private WebUtil webutil = new WebUtil();

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

   public void clientPortal() {
      Map<String,String> args = new LinkedHashMap<String, String>();

      if (advisor != null && ! advisor.isEmpty()) {
         args.put("advisor", advisor);

         if (rep != null && ! rep.isEmpty())
            args.put("rep", rep);

         profilebean.resetBean();
         webutil.redirect("/try/ltam/profile.xhtml", args);
      }
      else {
         webutil.showMessage(null, "W", "Advisor cannot be empty");
      }
   }

   public void timetoSavePortal() {
      Map<String,String> args = new LinkedHashMap<String, String>();
      Boolean ok2save = true;

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
         webutil.redirect("/try/ltam/profile.xhtml", args);
      }
      else {
         webutil.showMessage(null, "W", "Firstname, Lastname,and Amount all are required.");
      }
   }
}

