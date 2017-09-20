package com.invessence.web.bean.advisor;

import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import com.invessence.crm.bean.*;
import com.invessence.crm.provider.redTail.bean.*;
import com.invessence.service.bean.*;
import com.invessence.web.service.aggr.*;
import com.invessence.web.service.crm.*;
import com.invessence.web.util.*;
import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;

/**
 * Created by abhangp on 12/13/2016.
 */
@ManagedBean(name = "crmBean")
@SessionScoped
public class CRMBean
{
   private static final Logger logger = Logger.getLogger(CRMBean.class);
   @ManagedProperty("#{webutil}")
   private WebUtil webutil;

   @ManagedProperty("#{crmService}")
   private CRMService crmService;

   private String userId, password;
   private String errorMessage;

   public CRMService getCrmService()
   {
      return crmService;
   }

   public void setCrmService(CRMService crmService)
   {
      this.crmService = crmService;
   }

   public WebUtil getWebutil()
   {
      return webutil;
   }

   public void setWebutil(WebUtil webutil)
   {
      this.webutil = webutil;
   }

   public String getErrorMessage()
   {
      return errorMessage;
   }

   public void setErrorMessage(String errorMessage)
   {
      this.errorMessage = errorMessage;
   }

   public String getUserId()
   {
      return userId;
   }

   public void setUserId(String userId)
   {
      this.userId = userId;
   }

   public String getPassword()
   {
      return password;
   }

   public void setPassword(String password)
   {
      this.password = password;
   }

   @ManagedProperty("#{uiLayout}")
   public UILayout uiLayout;

   public UILayout getUiLayout()
   {
      return uiLayout;
   }

   public void setUiLayout(UILayout uiLayout)
   {
      this.uiLayout = uiLayout;
   }

   private Long logonid;

   public Long getLogonid()
   {
      return logonid;
   }

   public void setLogonid(Long logonid)
   {
      this.logonid = logonid;
   }

   public void startup(){
      logger.info("CRMBean.startup");
      String product = getWebutil().getWebprofile().getWebInfo().get("SERVICE.PRODUCT").toString();
      String serviceMode = getWebutil().getWebprofile().getWebInfo().get("SERVICE.CRM.MODE").toString();
      logger.info("Product " + product);
      logger.info("ServiceMode " + serviceMode);
      try {
         if (logonid == null) {
            logonid = webutil.getLogonid();
         }

         CRMUserDetails crmUserDetails=crmService.getUserAccDetailsByLogonId(logonid);
         if(crmUserDetails==null){
            logger.info("CRMUserDetails not available for logonid :"+logonid);
         }else{
            if(crmUserDetails.getCrmUserId()==null || crmUserDetails.getCrmUserId().equals("")){
               logger.info("Go for CRM Registration");
               clear();
               uiLayout.doMenuAction("advisor", "crm/redTail/login.xhtml");
            }else if (crmUserDetails.getCrmStatus().equals("A")){
               logger.info("Go for widget window");
               uiLayout.doMenuAction("advisor", "crm/redTail/widget.xhtml");
            }else{
               logger.info("CRMUser is not Active!");
               errorMessage="CRMUser is not Active!";
               clear();
               uiLayout.doMenuAction("advisor", "crm/redTail/login.xhtml");
            }
         }

      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   public void processCRMRegistration(){

      logger.info("CRMBean.processCRMRegistraion");
      try {
         if (logonid == null) {
            logonid = webutil.getLogonid();
         }
         String product = getWebutil().getWebprofile().getWebInfo().get("SERVICE.PRODUCT").toString();
         String serviceMode = getWebutil().getWebprofile().getWebInfo().get("SERVICE.CRM.MODE").toString();
         logger.info("Product " + product);
         logger.info("ServiceMode " + serviceMode);

         CRMUserDetails userAcctDetails=crmService.getUserAccDetailsByLogonId(logonid);
         if(userAcctDetails==null){
            logger.info("CRMUserDetails not available for logonid :"+logonid);
            errorMessage="CRMUserDetails not available for logonid :"+logonid;
         }else{
           //UserProfile userProfile= crmService.userRegistration(userAcctDetails);
            WSCallResult wsCallResult= crmService.authentication(new ServiceRequest(product, serviceMode), new User(userId, password, "", ""));
            logger.info("wsCallResult = " + wsCallResult);
            if(wsCallResult==null || wsCallResult.getWSCallStatus()==null || wsCallResult.getWSCallStatus().getErrorCode()!=0 || wsCallResult.getGenericObject()==null){
               logger.info("Service end issue!");
               if(wsCallResult!=null && wsCallResult.getGenericObject()!=null && ((AuthResponse)wsCallResult.getGenericObject()).getMessage()!=null){
                  AuthResponse authResponse=(AuthResponse)wsCallResult.getGenericObject();
                  errorMessage=authResponse.getMessage();
               }else{
                  errorMessage="Service end issue!";
               }
               clear();
               uiLayout.doMenuAction("advisor", "crm/redTail/login.xhtml");
              // webutil.redirecttoMessagePage("ERROR", "MX Service", userProfile.getErrorStatus().getErrorMessage(), "/pages/common/aggr/errorMessage.xhtml","");
            }else if(wsCallResult.getWSCallStatus().getErrorCode()==0){
               AuthResponse authResponse=(AuthResponse)wsCallResult.getGenericObject();
               CRMUserDetails crmUserDetails=new CRMUserDetails(logonid,userId,password,authResponse.getUserKey(),userAcctDetails.getEmail());
               crmService.userRegistration(crmUserDetails);
               uiLayout.doMenuAction("advisor", "crm/redTail/widget.xhtml");
            }
         }
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   public void preRenderViewWidget()
   {
      logger.info("CRMBean.preRenderViewWidget");
      //Url result = null;
      try
      {
         if (!FacesContext.getCurrentInstance().isPostback())
         {
            if (logonid == null) {
               logonid = webutil.getLogonid();
            }
            String product = getWebutil().getWebprofile().getWebInfo().get("SERVICE.PRODUCT").toString();
            String serviceMode = getWebutil().getWebprofile().getWebInfo().get("SERVICE.CRM.MODE").toString();
            logger.info("Product " + product);
            logger.info("ServiceMode " + serviceMode);

            CRMUserDetails userAcctDetails=crmService.getUserAccDetailsByLogonId(logonid);
            if(userAcctDetails==null){
               logger.info("CRMUserDetails not available for logonid :"+logonid);
               errorMessage="CRMUserDetails not available for logonid :"+logonid;
            }else{
               //UserProfile userProfile= crmService.userRegistration(userAcctDetails);
               WSCallResult wsCallResult= crmService.ssoLogin(new ServiceRequest(product, serviceMode), new User(userAcctDetails.getCrmUserId(), userAcctDetails.getCrmPwd(), "", userAcctDetails.getCrmUserKey()));
               logger.info("wsCallResult = " + wsCallResult);
               if(wsCallResult==null || wsCallResult.getWSCallStatus()==null || wsCallResult.getWSCallStatus().getErrorCode()!=0 || wsCallResult.getGenericObject()==null){
                  logger.info("Service end issue!");
                  if(wsCallResult!=null && wsCallResult.getGenericObject()!=null && ((AuthResponse)wsCallResult.getGenericObject()).getMessage()!=null){
                     AuthResponse authResponse=(AuthResponse)wsCallResult.getGenericObject();
                     errorMessage=authResponse.getMessage();
                  }else{
                     errorMessage="Service end issue!";
                  }
                  clear();
                  uiLayout.doMenuAction("advisor", "crm/redTail/login.xhtml");
                  // webutil.redirecttoMessagePage("ERROR", "MX Service", userProfile.getErrorStatus().getErrorMessage(), "/pages/common/aggr/errorMessage.xhtml","");
               }else if(wsCallResult.getWSCallStatus().getErrorCode()==0){
                  AuthResponse authResponse=(AuthResponse)wsCallResult.getGenericObject();
                  RequestContext requestContext = RequestContext.getCurrentInstance();
                  requestContext.execute("openCRMWidget('" + authResponse.getReturnURL() + "')");
                  logger.info("openCRMWidget('" + authResponse.getReturnURL() + "')");
               }
            }

//            CRMUserDetails userAcctDetails=crmService.getUserAccDetailsByLogonId(logonid);
//            result = crmService.getWidget(userAcctDetails, mode);
//            if(result.getErrorStatus().getErrorCode()==0)
//            {
//               RequestContext requestContext = RequestContext.getCurrentInstance();
//               requestContext.execute("openWidget('" + result.getUrl() + "')");
//               System.out.println("openWidget('" + result.getUrl() + "')");
//            }
//            else if(result.getErrorStatus().getErrorCode().equals(404))
//            {
//               processCRMRegistraion();
//            }else
//            {
//               webutil.redirecttoMessagePage("ERROR", "MX Service", result.getErrorStatus().getErrorMessage(), "/pages/common/aggr/errorMessage.xhtml","");
//            }
      }}
      catch (Exception e)
      {
        e.printStackTrace();
      }
   }

   public void clear(){
      setUserId(null);
      setPassword(null);
   }//end clear`


//
//   public void preRenderViewForRegistration(){
//
//      System.out.println("CRMBean.preRenderViewForRegistration");
//      try {
//         if (logonid == null) {
//            logonid = webutil.getLogonid();
//         }
//
//         UserAcctDetails userAcctDetails=aggregationService.getUserAccDetailsByLogonId(logonid);
//         if(userAcctDetails==null){
//            System.out.println("UserAcctDetails not available for logonid :"+logonid);
//         }else{
//            if(userAcctDetails.getCrmUserId()==null || userAcctDetails.getCrmUserId().equals("")){
//               System.out.println("Go for MX Registration");
//            }else if (userAcctDetails.getCrmStatus().equals("A")){
//               System.out.println("Go for widget window");
//               uiLayout.doMenuAction("common", "aggr/widget.xhtml");
//            }
//         }
//
//      } catch (Exception e) {
//         e.printStackTrace();
//      }
//   }


//   public void openWidget(String str) {
//      System.out.println("userRegistration");
//      Url result = null;
//      try {
//         if (logonid == null) {
//            logonid = webutil.getLogonid();
//         }
////         UserProfile userProfile=new UserProfile(new Long(1), "U1234", "XXXXX", "abhangp@invessence.com", "Abhang", "Patil", new Credentials("INVESSENCE", "PASSWORD", "BUILDINGBANJAMINS"));
////         result = aggregationService.getWidgetUrl(userProfile);
////         RequestContext requestContext = RequestContext.getCurrentInstance();
////         int i = 100;
////
////         // requestContext.execute("abhangCall("+flDetails.get("OAUTH_TOKEN")+","+ flDetails.get("OAUTH_TOKEN_SECRET")+","+ flDetails.get("APPLICATION_KEY")+","+ flDetails.get("APPLICATION_TOKEN")+","+ flDetails.get("FL_API_URL")+","+ flDetails.get("FL_API_PARAM")+")");
////         requestContext.execute("openWidget('" + result.getUrl() + "')");
////         //return result;
////         System.out.println("openWidget('" + result.getUrl() + "')");
//         //requestContext.execute("confirmDelete('"+i+"');");
//         webutil.redirect("/pages/common/aggr/widget.xhtml", null);
//      } catch (Exception e) {
//         e.printStackTrace();
//      }
//      //return result;
//   }
}
