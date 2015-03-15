package com.invessence.bean;

import javax.faces.bean.*;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 9/20/13
 * Time: 4:02 PM
 * To change this template use File | Settings | File Templates.
 */
@ManagedBean(name = "surveyBean")
@SessionScoped
public class SurveyBean
{
   private String firstname, lastname, email, leadsource;

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

   public String getEmail()
   {
      return email;
   }

   public void setEmail(String email)
   {
      this.email = email;
   }

   public String getLeadsource()
   {
      return leadsource;
   }

   public void setLeadsource(String leadsource)
   {
      this.leadsource = leadsource;
   }

   public void doSurvey() {

   }
}
