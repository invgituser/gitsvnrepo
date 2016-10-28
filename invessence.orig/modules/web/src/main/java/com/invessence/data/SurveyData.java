package com.invessence.data;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: pichaimanir
 * Date: 8/19/13
 * Time: 4:50 PM
 * To change this template use File | Settings | File Templates.
 */
public class SurveyData implements Serializable
{
   private SurveyData thisSurveyData = null;
   private String firstname, lastname, email, leadsource;
   private String passwrd, surveylink;
   private String followup;
   private String emailMimeType;
   private String created, lastUpdated;

   public SurveyData()
   {
      super();
      thisSurveyData = this;
   }

   public SurveyData getInstance() {
      return thisSurveyData;
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

   public String getPasswrd()
   {
      return passwrd;
   }

   public void setPasswrd(String passwrd)
   {
      this.passwrd = passwrd;
   }

   public String getSurveylink()
   {
      return surveylink;
   }

   public void setSurveylink(String surveylink)
   {
      this.surveylink = surveylink;
   }

   public String getFollowup()
   {
      return followup;
   }

   public void setFollowup(String followup)
   {
      this.followup = followup;
   }

   public String getEmailMimeType()
   {
      return emailMimeType;
   }

   public void setEmailMimeType(String emailMimeType)
   {
      this.emailMimeType = emailMimeType;
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
}
