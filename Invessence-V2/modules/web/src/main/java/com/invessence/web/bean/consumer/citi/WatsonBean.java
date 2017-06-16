package com.invessence.web.bean.consumer.citi;

import java.util.*;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;

import com.invessence.aggr.bean.*;
import com.invessence.web.service.watson.WatsonAPIRepository;
import com.invessence.web.util.*;
import org.json.*;
import org.primefaces.context.RequestContext;

/**
 * Created by abhangp on 12/13/2016.
 */
@ManagedBean(name = "watsonBean")
@SessionScoped
public class WatsonBean
{
   @ManagedProperty("#{webutil}")
   public WebUtil webutil;

   public WebUtil getWebutil()
   {
      return webutil;
   }

   public void setWebutil(WebUtil webutil)
   {
      this.webutil = webutil;
   }

   @ManagedProperty("#{citidemo}")
   private CitiDemo citiDemo;

   public CitiDemo getCitiDemo()
   {
      return citiDemo;
   }

   public void setCitiDemo(CitiDemo citiDemo)
   {
      this.citiDemo = citiDemo;
   }

   @ManagedProperty("#{watsonAPIRepository}")
   private WatsonAPIRepository watsonAPIRepository;

   public WatsonAPIRepository getWatsonAPIRepository()
   {
      return watsonAPIRepository;
   }

   public void setWatsonAPIRepository(WatsonAPIRepository watsonAPIRepository)
   {
      this.watsonAPIRepository = watsonAPIRepository;
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
//   public void startup(String mode){
//      System.out.println("AggregationBean.startup");
//      String product = getWebutil().getWebprofile().getWebInfo().get("SERVICE.CUSTODY").toString();
//      String serviceMode = getWebutil().getWebprofile().getWebInfo().get("SERVICE.DOCUSIGN.MODE").toString();
//      System.out.println("Product " + product);
//      System.out.println("ServiceMode " + serviceMode);
//      System.out.println("mode = [" + mode + "]");
//      setMode(mode);
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
//               uiLayout.doMenuAction("consumer", "aggr/register.xhtml");
//            }else if (userAcctDetails.getAggrStatus().equals("A")){
//               System.out.println("Go for widget window");
//               uiLayout.doMenuAction("consumer", "aggr/widget.xhtml");
//            }
//         }
//
//      } catch (Exception e) {
//         e.printStackTrace();
//      }
//   }
//
//   public void processAggrRegistraion(){
//
//      System.out.println("AggrBean.processAggrRegistraion");
//      try {
//         if (logonid == null) {
//            logonid = webutil.getLogonid();
//         }
////         Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
////         String mode = params.get("mode");
////         System.out.println(mode+" : mode");
//
//         UserAcctDetails userAcctDetails=aggregationService.getUserAccDetailsByLogonId(logonid);
//         if(userAcctDetails==null){
//            System.out.println("UserAcctDetails not available for logonid :"+logonid);
//         }else{
//           UserProfile userProfile= aggregationService.userRegistration(userAcctDetails);
//            if(userProfile==null || userProfile.getErrorStatus()==null || userProfile.getErrorStatus().getErrorCode()!=0){
//               System.out.println("Service end issue!");
//               webutil.redirecttoMessagePage("ERROR", "MX Service", userProfile.getErrorStatus().getErrorMessage(), "/pages/consumer/" + webutil.getWebprofile().getConsumerdir() + "/aggr/errorMessage.xhtml","");
//            }else if(userProfile.getErrorStatus().getErrorCode()==0){
//               uiLayout.doMenuAction("consumer", "aggr/widget.xhtml");
//            }
//         }
//      } catch (Exception e) {
//         e.printStackTrace();
//      }
//   }

   private String token, secret, oauthVerifier, summary, personality, riskCalculation;

   public String getToken()
   {
      return token;
   }

   public void setToken(String token)
   {
      this.token = token;
   }

   public String getSecret()
   {
      return secret;
   }

   public void setSecret(String secret)
   {
      this.secret = secret;
   }

   public String getOauthVerifier()
   {
      return oauthVerifier;
   }

   public void setOauthVerifier(String oauthVerifier)
   {
      this.oauthVerifier = oauthVerifier;
   }

   public String getSummary()
   {
      return summary;
   }

   public void setSummary(String summary)
   {
      this.summary = summary;
   }

   public String getPersonality()
   {
      return personality;
   }

   public void setPersonality(String personality)
   {
      this.personality = personality;
   }

   public String getRiskCalculation()
   {
      return riskCalculation;
   }

   public void setRiskCalculation(String riskCalculation)
   {
      this.riskCalculation = riskCalculation;
   }

   public void preRenderLinkedInWidget()
   {
      System.out.println("AggrBean.preRenderViewWidget");
      Url result = null;
      try
      {
//         if (!FacesContext.getCurrentInstance().isPostback())
//         {
            System.out.println("mode +"+mode);
            String renderKitId = FacesContext.getCurrentInstance().getViewRoot().getRenderKitId();
            System.out.println("renderKitId = " + renderKitId);
            if (renderKitId.equalsIgnoreCase("PRIMEFACES_MOBILE")) {
                  System.out.println("******************* MOBILE REQUEST**********");
            }
            String callBackUrl = getWebutil().getWebprofile().getWebInfo().get("LINKEDIN.CALLBACK.URL").toString();
            String watsonApiUrl = getWebutil().getWebprofile().getWebInfo().get("WATSON.API.URL").toString();
            System.out.println("callBackUrl = " + callBackUrl);
            System.out.println("watsonApiUrl = " + watsonApiUrl);
            JSONObject jsonObject=watsonAPIRepository.getLinkedInUrl(watsonApiUrl,callBackUrl);

            if(jsonObject !=null)
            {
               RequestContext requestContext = RequestContext.getCurrentInstance();
               requestContext.execute("openLinkedInWidget('" + jsonObject.getString("authURL") + "')");
               System.out.println("openLinkedInWidget('" + jsonObject.getString("authURL") + "')");

               setToken(jsonObject.getString("token"));
               setSecret(jsonObject.getString("secret"));
            }
            else if(result.getErrorStatus().getErrorCode().equals(404))
            {
//               processAggrRegistraion();
            }else
            {
//               webutil.redirecttoMessagePage("ERROR", "MX Service", result.getErrorStatus().getErrorMessage(), "/pages/consumer/" + webutil.getWebprofile().getConsumerdir() + "/aggr/errorMessage.xhtml","");
            }
//      }
         System.out.println("token = " + token + "secret = " + secret);
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
   }

   public void preRenderLinkedInCallBack()
   {
      System.out.println("WatsonBean.preRenderLinkedInCallBack");
      System.out.println("Token = " + token + "Secret = " + secret+ "WatsonRiskScore() = " + getCitiDemo().getWatsonRiskScore());
      Url result = null;
      long riskValue=0;

      try
      {
         if (!FacesContext.getCurrentInstance().isPostback())
         {
            Map<String,String> reqMap=FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();

            Iterator<Map.Entry<String,String>> iterator = reqMap.entrySet().iterator();
            while (iterator.hasNext()) {
               Map.Entry<String,String> entry = (Map.Entry<String,String>) iterator.next();
               System.out.println("Key : " + entry.getKey() + " Value :" + entry.getValue());
            }
            setOauthVerifier(reqMap.get("oauth_verifier"));

            //LinkedIn call for profile summary
            JSONObject jsonObject=watsonAPIRepository.getLinkedInProfileSummary(token, secret, oauthVerifier);
            try
            {
               if (jsonObject != null && jsonObject.getString("summary") != null)
               {
                  setSummary(jsonObject.getString("summary"));
                  //Watson call for Personality Insight details
                  jsonObject = watsonAPIRepository.getWatsonPersonalityInsight(summary);
                  if (jsonObject != null && jsonObject.getString("personality") != null)
                  {
                     JSONArray jsonArray = jsonObject.getJSONArray("personality");
                     setPersonality(jsonObject.getJSONArray("personality").toString());
                     long openness = 0, conscientiousness = 0, extraversion = 0, agreeableness = 0, neuroticism = 0;
                     StringBuilder riskCalculator=new StringBuilder();
                     for (int j = 0; j < jsonArray.length(); j++)
                     {
                        JSONObject jo = jsonArray.getJSONObject(j);
                        System.out.println(jo.get("trait_id") + " : percentile :" + jo.get("percentile") + " : " + watsonAPIRepository.getNumericVal(jo.get("percentile")));
                        if (jo.get("trait_id").toString().trim().equals("big5_openness"))
                        {
                           openness = Math.round(watsonAPIRepository.getNumericVal(jo.get("percentile"))*100);
                           riskCalculator.append(jo.get("name").toString()).append("(O) : "+openness+"</br>");
                        }
                        else if (jo.get("trait_id").toString().trim().equals("big5_conscientiousness"))
                        {
                           conscientiousness = Math.round(watsonAPIRepository.getNumericVal(jo.get("percentile"))*100);
                           riskCalculator.append(jo.get("name").toString()).append("(C) : "+conscientiousness+"</br>");
                        }
                        else if (jo.get("trait_id").toString().trim().equals("big5_extraversion"))
                        {
                           extraversion = Math.round(watsonAPIRepository.getNumericVal(jo.get("percentile"))*100);
                           riskCalculator.append(jo.get("name").toString()).append("(E) : "+extraversion+"</br>");
                        }
                        else if (jo.get("trait_id").toString().trim().equals("big5_agreeableness"))
                        {
                           agreeableness = Math.round(watsonAPIRepository.getNumericVal(jo.get("percentile"))*100);
                           riskCalculator.append(jo.get("name").toString()).append("(A) : "+agreeableness+"</br>");
                        }
                        else if (jo.get("trait_id").toString().trim().equals("big5_neuroticism"))
                        {
                           neuroticism = Math.round(watsonAPIRepository.getNumericVal(jo.get("percentile"))*100);
                           riskCalculator.append(jo.get("name").toString()).append("(N) : "+neuroticism+"</br>");
                        }
                     }
                     riskCalculator.append("</br>");
                     System.out.println("openness = " + openness);
                     System.out.println("conscientiousness = " + conscientiousness);
                     System.out.println("extraversion = " + extraversion);
                     System.out.println("agreeableness = " + agreeableness);
                     System.out.println("neuroticism = " + neuroticism);
                     riskValue = (openness + conscientiousness + extraversion + agreeableness + (100 - neuroticism)) / 5;
                     riskCalculator.append("(O + C + E + A + (100 - N)) / 5 </br>");
                     riskCalculator.append("("+openness+"+" + conscientiousness +"+"+ extraversion +"+"+ agreeableness +"+ (100 - " +neuroticism+"))/5 = "+riskValue+ "</br>");
                     riskCalculator.append("</br>");
                     System.out.println("riskValue = " + riskValue);
                     getCitiDemo().setProfileAvailable(true);
                     getCitiDemo().setWatsonRiskScore(riskValue*1.0);

                     setRiskCalculation(riskCalculator.toString());

                     System.out.println("getCitiDemo().getWatsonRiskScore() = " + getCitiDemo().getWatsonRiskScore());
                  }
                  else
                  {
                     System.out.println("Trait details not available");
                     getCitiDemo().setProfileAvailable(false);
                  }
               }
               else
               {
                  System.out.println("LinkedIn profile summary not available");
                  getCitiDemo().setProfileAvailable(false);
               }
            }catch (JSONException e)
            {
               getCitiDemo().setProfileAvailable(false);
               e.printStackTrace();
            }

         }
      }
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
//         webutil.redirect("/pages/consumer/tcm/aggr/widget.xhtml", null);
//      } catch (Exception e) {
//         e.printStackTrace();
//      }
//      //return result;
//   }
}
