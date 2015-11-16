package com.invessence.bean.consumer;

import java.io.Serializable;
import java.util.*;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;
import com.invessence.util.WebUtil;
import com.invessence.yodlee.model.*;
import com.invessence.yodlee.service.YodleeAPIService;
import com.invessence.yodlee.util.YodleeAPI;
import org.json.JSONArray;
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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

   @Autowired
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

   public void preRenderView()
   {

      try {
         if (!FacesContext.getCurrentInstance().isPostback())
         {
            if (! getWebutil().isUserLoggedIn()) {
               getWebutil().redirect("/login.xhtml",null);
            }
            welcomeDialog = isUserRegisteredAtYodlee();
         }
      }
      catch (Exception e)
      {
      }
   }

   public Boolean isUserRegisteredAtYodlee() {
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
   }

   public void redirecttoErrorPage(YodleeError errorInfo) {

   }


}
