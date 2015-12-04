package com.invessence.bean.consumer;

import java.io.Serializable;
import java.util.*;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;


import com.invessence.yodlee.model.*;
import com.invessence.yodlee.service.YodleeAPIService;
import org.springframework.beans.factory.annotation.Autowired;

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
   private Long logonid;
   private String controlWidget;

   //@Autowired
   @ManagedProperty("#{yodleeAPIService}")
   YodleeAPIService yodleeAPIService;
   public YodleeAPIService getYodleeAPIService() {
      return yodleeAPIService;
   }

   public void setYodleeAPIService(YodleeAPIService yodleeAPIService) {
      this.yodleeAPIService = yodleeAPIService;
   }


   public Long getLogonid()
   {
      return logonid;
   }

   public void setLogonid(Long logonid)
   {
      this.logonid = logonid;
   }

   public String getControlWidget()
   {
      return controlWidget;
   }

   public void setControlWidget(String controlWidget)
   {
      this.controlWidget = controlWidget;
   }

   public void startup()
   {
   System.out.println("startup");
      try {
            if (isUserRegisteredAtYodlee()) {
               controlWidget = "/pages/consumer/aggr/profile.xhtml";
               String strToPass = "?lid="+logonid.toString();
               //webutil.redirect("/pages/consumer/aggr/profile.xhtml", null);
               FacesContext.getCurrentInstance().getExternalContext().redirect(controlWidget + strToPass );
            }
         else {
               controlWidget = "/pages/consumer/aggr/acct.xhtml";
               String strToPass = "?lid="+logonid.toString();
               //webutil.redirect("/pages/consumer/aggr/profile.xhtml", null);
               FacesContext.getCurrentInstance().getExternalContext().redirect(controlWidget + strToPass );
            }
      }
      catch (Exception e)
      {
      }
   }

   public void userRegistration(){
      System.out.println("userRegistration");
      Map<String, Object> result =null;
      try
      {
         result = yodleeAPIService.userRegistration(logonid);
         //return result;
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
      //return result;
   }

   public Boolean isUserRegisteredAtYodlee() {
      try {
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
   }


   public void redirecttoErrorPage(YodleeError errorInfo) {

   }




}
