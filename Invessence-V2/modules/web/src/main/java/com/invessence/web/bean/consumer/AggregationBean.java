package com.invessence.web.bean.consumer;

import javax.faces.bean.*;
import javax.faces.context.FacesContext;

import com.invessence.aggr.bean.*;
import com.invessence.web.service.aggr.*;
import com.invessence.web.util.*;
import org.primefaces.context.RequestContext;

/**
 * Created by abhangp on 12/13/2016.
 */
@ManagedBean(name = "aggrBean")
@SessionScoped
public class AggregationBean
{
   @ManagedProperty("#{webutil}")
   private WebUtil webutil;

   @ManagedProperty("#{aggregationService}")
   private AggregationService aggregationService;

   public AggregationService getAggregationService()
   {
      return aggregationService;
   }

   public void setAggregationService(AggregationService aggregationService)
   {
      this.aggregationService = aggregationService;
   }

   private String mode;

   public String getMode()
   {
      return mode;
   }

   public void setMode(String mode)
   {
      this.mode = mode;
   }

   public WebUtil getWebutil()
   {
      return webutil;
   }

   public void setWebutil(WebUtil webutil)
   {
      this.webutil = webutil;
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


//
//   public void preRenderViewForRegistration(){
//
//      System.out.println("AggrBean.preRenderViewForRegistration");
//      try {
//         if (logonid == null) {
//            logonid = webutil.getLogonid();
//         }
//
//         UserAcctDetails userAcctDetails=aggregationService.getUserAccDetailsByLogonId(logonid);
//         if(userAcctDetails==null){
//            System.out.println("UserAcctDetails not available for logonid :"+logonid);
//         }else{
//            if(userAcctDetails.getAggrUserId()==null || userAcctDetails.getAggrUserId().equals("")){
//               System.out.println("Go for MX Registration");
//            }else if (userAcctDetails.getAggrStatus().equals("A")){
//               System.out.println("Go for widget window");
//               uiLayout.doMenuAction("common", "aggr/widget.xhtml");
//            }
//         }
//
//      } catch (Exception e) {
//         e.printStackTrace();
//      }
//   }

   public void startup(String mode){
      System.out.println("AggregationBean.startup");
      String product = getWebutil().getWebprofile().getWebInfo().get("SERVICE.PRODUCT").toString();
      String serviceMode = getWebutil().getWebprofile().getWebInfo().get("SERVICE.AGGREGATION.MODE").toString();
      System.out.println("Product " + product);
      System.out.println("ServiceMode " + serviceMode);
      System.out.println("mode = [" + mode + "]");
      setMode(mode);
      try {
         if (logonid == null) {
            logonid = webutil.getLogonid();
         }

         UserAcctDetails userAcctDetails=aggregationService.getUserAccDetailsByLogonId(logonid);
         if(userAcctDetails==null){
            System.out.println("UserAcctDetails not available for logonid :"+logonid);
         }else{
            if(userAcctDetails.getAggrUserId()==null || userAcctDetails.getAggrUserId().equals("")){
               System.out.println("Go for MX Registration");
               uiLayout.doMenuAction("common", "aggr/register.xhtml");
            }else if (userAcctDetails.getAggrStatus().equals("A")){
               System.out.println("Go for widget window");
               uiLayout.doMenuAction("common", "aggr/widget.xhtml");
            }
         }

      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   public void processAggrRegistraion(){

      System.out.println("AggrBean.processAggrRegistraion");
      try {
         if (logonid == null) {
            logonid = webutil.getLogonid();
         }
//         Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
//         String mode = params.get("mode");
//         System.out.println(mode+" : mode");

         UserAcctDetails userAcctDetails=aggregationService.getUserAccDetailsByLogonId(logonid);
         if(userAcctDetails==null){
            System.out.println("UserAcctDetails not available for logonid :"+logonid);
         }else{
           UserProfile userProfile= aggregationService.userRegistration(userAcctDetails);
            if(userProfile==null || userProfile.getErrorStatus()==null || userProfile.getErrorStatus().getErrorCode()!=0){
               System.out.println("Service end issue!");
               webutil.redirecttoMessagePage("ERROR", "MX Service", userProfile.getErrorStatus().getErrorMessage(), "/pages/common/aggr/errorMessage.xhtml","");
            }else if(userProfile.getErrorStatus().getErrorCode()==0){
               uiLayout.doMenuAction("common", "aggr/widget.xhtml");
            }
         }
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   public void preRenderViewWidget()
   {
      System.out.println("AggrBean.preRenderViewWidget");
      Url result = null;
      try
      {
         if (!FacesContext.getCurrentInstance().isPostback())
         {
            System.out.println("mode +"+mode);
            String renderKitId = FacesContext.getCurrentInstance().getViewRoot().getRenderKitId();
            System.out.println("renderKitId = " + renderKitId);
            if (renderKitId.equalsIgnoreCase("PRIMEFACES_MOBILE")) {
                  System.out.println("******************* MOBILE REQUEST**********");
            }
            if (logonid == null) {
               logonid = webutil.getLogonid();
            }

            UserAcctDetails userAcctDetails=aggregationService.getUserAccDetailsByLogonId(logonid);
            result = aggregationService.getWidget(userAcctDetails, mode);
            if(result.getErrorStatus().getErrorCode()==0)
            {
               RequestContext requestContext = RequestContext.getCurrentInstance();
               requestContext.execute("openWidget('" + result.getUrl() + "')");
               System.out.println("openWidget('" + result.getUrl() + "')");
            }
            else if(result.getErrorStatus().getErrorCode().equals(404))
            {
               processAggrRegistraion();
            }else
            {
               webutil.redirecttoMessagePage("ERROR", "MX Service", result.getErrorStatus().getErrorMessage(), "/pages/common/aggr/errorMessage.xhtml","");
            }
      }}
      catch (Exception e)
      {
        e.printStackTrace();
      }
   }

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
