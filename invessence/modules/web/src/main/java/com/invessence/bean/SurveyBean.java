package com.invessence.bean;

import java.io.Serializable;
import java.util.*;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;

import com.invessence.constant.Const;
import com.invessence.dao.SurveyDAO;
import com.invessence.data.*;
import com.invessence.util.*;
import com.invessence.validator.EmailValidator;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 9/20/13
 * Time: 4:02 PM
 * To change this template use File | Settings | File Templates.
 */
@ManagedBean(name = "surveyBean")
@SessionScoped
public class SurveyBean extends SurveyData implements Serializable
{
   @ManagedProperty("#{surveyDAO}")
   private SurveyDAO surveyDAO;

   @ManagedProperty("#{emailMessage}")
   private EmailMessage emailMessage;

   private WebUtil webutil = new WebUtil();
   private Map<Integer, String> passwrdMap = null;

   public void setSurveyDAO(SurveyDAO surveyDAO)
   {
      this.surveyDAO = surveyDAO;
   }

   public void setEmailMessage(EmailMessage emailMessage)
   {
      this.emailMessage = emailMessage;
   }

   @PostConstruct
   public void init()
   {
      try
      {
         passwrdMap = surveyDAO.getPasswords("DyMynd");
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
   }

   public String getPassword(Integer passwrdID) {
      if (passwrdMap == null)
         return "bBtKcSVa";
      if (passwrdMap.containsKey(passwrdID))
         return passwrdMap.get(passwrdID);
      else
         return "bBtKcSVa";
   }

   public String createSurveyLink() {
      String surveyLink = null;
      try {
         String companyURL= "http://www.dymyndsurvey.com/surveys/";
         String pageRedirection="login.php?survey=identity";
         String emailInfo = "email=" + getEmail();
         String passcode = "passcode=" + getPasswrd();
         String firstname = "firstname=" + getFirstname();
         String lastname = "lastname=" + getLastname();
         surveyLink = companyURL + pageRedirection + "&" +
            emailInfo + "&" + passcode + "&" + firstname + "&" + lastname;
      }
      catch (Exception ex) {

      }
      return surveyLink;
   }

   public Boolean validateData() {
      Boolean dataCheck = true;
      String msg = "";
      if (getFirstname() == null || getFirstname().length() == 0 || getFirstname().equalsIgnoreCase("first name")) {
         dataCheck = false;
         msg = msg + "First name is required.<br/>";
      }
      else if (getFirstname().contains(" ")) {
               dataCheck = false;
               msg = msg + "Please enter first name ONLY.<br/>";
      }

      if (getLastname() == null || getLastname().length() == 0 || getLastname().equalsIgnoreCase("last name")) {
         dataCheck = false;
         msg = msg + "Last name is required.<br/>";
      }
      else if (getLastname().contains(" ")) {
               dataCheck = false;
               msg = msg + "Please enter last name ONLY.<br/>";
      }

      EmailValidator emailValidator = new  EmailValidator();
      String emailFormatStr = emailValidator.validateEmailPattern(getEmail());
      if (emailFormatStr != null) {
         dataCheck = false;
         msg = msg + emailFormatStr;
      }

      if (! dataCheck) {
         if (msg.length() > 0) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("messages", new FacesMessage("Validate:<br/>", msg) );
         }
      }

      return dataCheck;
   }

   public void doSurvey() {
      String spMsg = "";
      try {
         if (validateData()) {
            MsgData data = new MsgData();
            Integer passcodeNum = webutil.randomGenerator(1,5);
            setPasswrd(getPassword(passcodeNum));
            setSurveylink(createSurveyLink());
            setEmailMimeType("HTML");
            setFollowup("Y");
            spMsg = surveyDAO.saveData(getInstance());
            if (spMsg.equalsIgnoreCase("success")) {
               String msg = emailMessage.buildMessage("HTML", "survey.email.template", null, new Object[]{getEmail(),getPasswrd(),getFirstname(),getLastname()});
               data.setSource("User");  // This is set to User to it insert into appropriate table.
               data.setSender(Const.MAIL_SENDER);
               data.setReceiver(getEmail());
               data.setSubject(Const.COMPANY_NAME + " - Financial Identity Assessment");
               data.setMsg(msg);
               data.setMimeType(getEmailMimeType());
               if (msg.length() > 0)
                  emailMessage.writeMessage("user", data);
               webutil.redirecttoMessagePage("N","mtp","mbp");
            }
            else {
               String msg = "Visitor: (" + getEmail() +") was unable to do the survey.\n" +
                  "First: " + getFirstname() + "\n" +
                  "Last: " + getLastname();
               data.setSource("Internal");  // This is set to User to it insert into appropriate table.
               data.setSender(Const.MAIL_SENDER);
               data.setReceiver("support@invessence.com");
               data.setSubject("Survey Problem");
               data.setMsg(msg);
               data.setMimeType("TEXT");
               emailMessage.writeMessage("Internal", data);
               webutil.redirecttoMessagePage("Error","mtse","mbse");
            }
         }
      }
      catch (Exception ex) {
         spMsg = "Failed";
      }

   }
}
