package com.invessence.bean.consumer;

import java.io.Serializable;
import java.util.*;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;

import com.invessence.util.WebUtil;
import com.invessence.yodlee.model.*;
import com.invessence.yodlee.service.YodleeAPIService;
import org.primefaces.context.RequestContext;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 11/10/15
 * Time: 3:28 PM
 * To change this template use File | Settings | File Templates.
 */
@ManagedBean(name = "yodleeBean")
@SessionScoped
public class YodleeBean implements Serializable
{
   private Boolean welcomeDialog;
   @ManagedProperty("#{yodleeAPIService}")
   YodleeAPIService yodleeAPIService;
   public YodleeAPIService getYodleeAPIService() {
      return yodleeAPIService;
   }

   public void setYodleeAPIService(YodleeAPIService yodleeAPIService) {
      this.yodleeAPIService = yodleeAPIService;
   }

   @ManagedProperty("#{webutil}")
   private WebUtil webutil;
   public void setWebutil(WebUtil webutil)
   {
      this.webutil = webutil;
   }

   public WebUtil getWebutil()
   {
      return webutil;
   }

   public Boolean getWelcomeDialog()
   {
      return welcomeDialog;
   }

   public void setWelcomeDialog(Boolean welcomeDialog)
   {
      this.welcomeDialog = welcomeDialog;
   }

  /* public void preRenderView()
   {
   System.out.println("preRenderView");
      try {
         if (!FacesContext.getCurrentInstance().isPostback())
         {
            if (! getWebutil().isUserLoggedIn()) {
               getWebutil().redirect("/login.xhtml",null);
            }
            //welcomeDialog = isUserRegisteredAtYodlee();
            Long logonid = webutil.getLogonid();
            welcomeDialog = yodleeAPIService.isUserRegisteredAtYodlee(logonid);
         }
      }
      catch (Exception e)
      {
      }
   }*/

   public void moveToDash(){
      System.out.println("userRegistration");
      Map<String, Object> result =null;
      try
      {
         System.out.println("moveToDash");
         getConsDataList();
         //return result;
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
      webutil.redirect("/pages/consumer/aggr/ydash.xhtml", null);
      //return "yDash";
   }

   public void refreshAccountsData(){
      System.out.println("refreshAccountsData");
      Map<String, Object> result =null;
      try
      {
         Long logonid = webutil.getLogonid();
         yodleeAPIService.getAllSiteAccounts(logonid);
         //return result;
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
      // webutil.showMessage("", "Success", "Accounts data sucessfully refreshed!");
      //return "yDash";
   }

   List<ConsolidateData> consDataList;
   public List<ConsolidateData> getConsDataList() {
      consDataList = new ArrayList<ConsolidateData>();
      Long logonid = webutil.getLogonid();
      consDataList=(List<ConsolidateData>) yodleeAPIService.getUserAccountsDetail(logonid).get("consDataList");
      //System.out.println(consDataList.size()+" LIST SIZE");
      return consDataList;
   }


   public void setConsDataList(List<ConsolidateData> consDataList) {
      this.consDataList = consDataList;
   }


   public void userRegistration(){
      System.out.println("userRegistration");
      Map<String, Object> result =null;
      try
      {
         Long logonid = webutil.getLogonid();
         result = yodleeAPIService.userRegistration(logonid);
         //return result;
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
      //return result;
   }

   public void userLogin(){
      System.out.println("userRegistration");
      Map<String, Object> result =null;
      try
      {
         Long logonid = webutil.getLogonid();
         result = yodleeAPIService.userLogin(logonid);
         //return result;
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
      //return result;
   }

   public void addAcount(){
      System.out.println("userRegistration");
      Map<String, Object> result =null;
      try
      {
         Long logonid = webutil.getLogonid();
         result = yodleeAPIService.getFastLinkDetails("ADD_ACC","",logonid);
         RequestContext requestContext = RequestContext.getCurrentInstance();
         Map<String , String> flDetails= (Map<String, String>) result.get("flDetails");
         int i=100;

         // requestContext.execute("abhangCall("+flDetails.get("OAUTH_TOKEN")+","+ flDetails.get("OAUTH_TOKEN_SECRET")+","+ flDetails.get("APPLICATION_KEY")+","+ flDetails.get("APPLICATION_TOKEN")+","+ flDetails.get("FL_API_URL")+","+ flDetails.get("FL_API_PARAM")+")");
         requestContext.execute("getFastLinkUrl('" + flDetails.get("OAUTH_TOKEN") + "','"
                                   + flDetails.get("OAUTH_TOKEN_SECRET") + "','"
                                   + flDetails.get("APPLICATION_KEY") + "','"
                                   + flDetails.get("APPLICATION_TOKEN") + "','"
                                   + flDetails.get("FL_API_URL") + "','"
                                   + flDetails.get("FL_API_PARAM") + "')");
         //return result;
         System.out.println("getFastLinkUrl('" + flDetails.get("OAUTH_TOKEN") + "','"
                               + flDetails.get("OAUTH_TOKEN_SECRET") + "','"
                               + flDetails.get("APPLICATION_KEY") + "','"
                               + flDetails.get("APPLICATION_TOKEN") + "','"
                               + flDetails.get("FL_API_URL") + "','"
                               + flDetails.get("FL_API_PARAM") + "')");
         //requestContext.execute("confirmDelete('"+i+"');");
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
      //return result;
   }

/*   public Boolean isUserRegisteredAtYodlee() {
      try {
        Long logonid = webutil.getLogonid();
        Map <String, Object> result = yodleeAPIService.userLogin(logonid);
        if (result == null) {
           return false;
        }
        if (result.containsKey("errorDetails")) {
           YodleeError obj = (YodleeError) result.get("errorDetails");
           if (obj.getSeverity() > 0) {
              return true;
           }
           else
              redirecttoErrorPage(obj);
        }
        else {
           return true;
        }
      }
      catch (Exception ex) {
        redirecttoErrorPage(null);
      }
      return true;
   }*/

   public void redirecttoErrorPage(YodleeError errorInfo) {

   }




}
