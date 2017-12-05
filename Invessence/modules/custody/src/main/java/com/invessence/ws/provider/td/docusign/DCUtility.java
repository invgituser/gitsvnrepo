package com.invessence.ws.provider.td.docusign;

import java.io.IOException;
import java.lang.reflect.*;
import java.nio.file.*;
import java.util.*;
import java.util.Date;
import java.util.List;

import javax.xml.bind.DatatypeConverter;

import com.docusign.esign.api.*;
import com.docusign.esign.client.*;
import com.docusign.esign.model.Checkbox;
import com.docusign.esign.model.EnvelopeDefinition;
import com.docusign.esign.model.RadioGroup;
import com.docusign.esign.model.Signer;
import com.docusign.esign.model.Tabs;
import com.docusign.esign.model.Text;
import com.docusign.esign.model.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.*;
import com.invessence.service.bean.*;
import com.invessence.service.bean.docuSign.*;
import com.invessence.service.util.*;
import com.invessence.ws.provider.td.bean.*;
import com.invessence.ws.provider.td.dao.TDDaoLayer;
import com.invessence.ws.util.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by abhangp on 8/12/2016.
 */
@Component
public class DCUtility
{
   private static final Logger logger = Logger.getLogger(DCUtility.class);
   @Autowired
   TDDaoLayer tdDaoLayer;

   public static Object getInstanceValue(final Object classInstance, final String fieldName) throws SecurityException, NoSuchFieldException, ClassNotFoundException, IllegalArgumentException, IllegalAccessException
   {

      // Get the private field
      final Field field = classInstance.getClass().getDeclaredField(fieldName);
      // Allow modification on the field
      field.setAccessible(true);
      // Return the Obect corresponding to the field
      return field.get(classInstance);

   }

   public static List<String> getFieldNames(final Object obj, boolean publicOnly)
      throws IllegalArgumentException, IllegalAccessException
   {
      StringBuilder sb = new StringBuilder();
      Class<? extends Object> c1 = obj.getClass();
      List<String> lst = new ArrayList<String>();
      Field[] fields = c1.getDeclaredFields();
      for (int i = 0; i < fields.length; i++)
      {
         String name = fields[i].getName();
         if (publicOnly)
         {
            if (Modifier.isPublic(fields[i].getModifiers()))
            {
               Object value = fields[i].get(obj);
               if (value == null)
               {
                  sb.append(name + ", ");
               }
               else
               {
                  lst.add(name);
               }
            }
         }
         else
         {
            fields[i].setAccessible(true);
            Object value = fields[i].get(obj);
            if (value == null)
            {
               sb.append(name + ", ");
            }
            else
            {
               lst.add(name);
            }
         }
      }
//      System.out.println("Avoided properties of "+c1+" due to empty or null value : (" + sb + ")");
      logger.info("Properties for further process = " + lst);
      return lst;
   }

   public static Map<String, Object> getFieldNamesAndValues(final Object obj, boolean publicOnly)
      throws IllegalArgumentException, IllegalAccessException
   {
      Class<? extends Object> c1 = obj.getClass();
      Map<String, Object> map = new HashMap<String, Object>();
      Field[] fields = c1.getDeclaredFields();
      for (int i = 0; i < fields.length; i++)
      {
         String name = fields[i].getName();
         if (publicOnly)
         {
            if (Modifier.isPublic(fields[i].getModifiers()))
            {
               Object value = fields[i].get(obj);
               map.put(name, value);
            }
         }
         else
         {
            fields[i].setAccessible(true);
            Object value = fields[i].get(obj);
            map.put(name, value);
         }
      }
      return map;
   }

   public DCResponse createEnvelope(ServiceRequest serviceRequest, EnvelopeDefinition envDef, Long acctNum, int eventNum, String requestIds)
   {
      //boolean resultFlag = false;
      try
      {
         ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

         mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
         mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
         logger.info("EnvelopeDefinition :" + mapper.writeValueAsString(envDef));
      }
      catch (JsonProcessingException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }

      EnvelopeSummary envelopeSummary = null;
      Date reqTime = new Date();
      String UserName = ServiceDetails.getConfigProperty(serviceRequest.getProduct(), Constant.SERVICES.DOCUSIGN_SERVICES.toString(), serviceRequest.getMode(), "USERNAME");//"prashant@invessence.com";//"[EMAIL]";
      String Password = ServiceDetails.getConfigProperty(serviceRequest.getProduct(), Constant.SERVICES.DOCUSIGN_SERVICES.toString(), serviceRequest.getMode(), "PASSWORD");//"Inv3ss3nc3!";//"[PASSWORD]";
      // TODO: Enter your Integrator Key (aka API key), created through your developer sandbox preferences
      String IntegratorKey = ServiceDetails.getConfigProperty(serviceRequest.getProduct(), Constant.SERVICES.DOCUSIGN_SERVICES.toString(), serviceRequest.getMode(), "INTEGRATOR_KEY");//"TDAM-d7feb45c-e88d-4c20-b5bd-1dcd9a9d6f56";//"[INTEGRATOR_KEY]";
      // for production environment update to "www.docusign.net/restapi"
      String BaseUrl = ServiceDetails.getConfigProperty(serviceRequest.getProduct(), Constant.SERVICES.DOCUSIGN_SERVICES.toString(), serviceRequest.getMode(), "BASE_URL");//"https://demo.docusign.net/restapi";

      // initialize the api client
      ApiClient apiClient = new ApiClient();
      apiClient.setBasePath(BaseUrl);

      // create JSON formatted auth header
      String creds = "{\"Username\":\"" + UserName + "\",\"Password\":\"" + Password + "\",\"IntegratorKey\":\"" + IntegratorKey + "\"}";
      apiClient.addDefaultHeader("X-DocuSign-Authentication", creds);

      // assign api client to the Configuration object
      Configuration.setDefaultApiClient(apiClient);

      // list of user account(s)
      List<LoginAccount> loginAccounts = null;

      //===============================================================================
      // Step 1:  Login() API
      //===============================================================================
      try
      {
         // login call available off the AuthenticationApi
         AuthenticationApi authApi = new AuthenticationApi();

         // login has some optional parameters we can set
         AuthenticationApi.LoginOptions loginOps = authApi.new LoginOptions();
         loginOps.setApiPassword("true");
         loginOps.setIncludeAccountIdGuid("true");
         LoginInformation loginInfo = authApi.login(loginOps);

         // note that a given user may be a member of multiple accounts
         loginAccounts = loginInfo.getLoginAccounts();

         logger.info("LoginInformation: " + loginAccounts);

         // use the |accountId| we retrieved through the Login API to create the Envelope
         //String accountId = loginAccounts.get(0).getAccountId();

         String accountId = ServiceDetails.getConfigProperty(serviceRequest.getProduct(), Constant.SERVICES.DOCUSIGN_SERVICES.toString(), serviceRequest.getMode(), "ACCOUNT_ID");

         // instantiate a new EnvelopesApi object
         EnvelopesApi envelopesApi = new EnvelopesApi();
         // call the createEnvelope() API
         envelopeSummary = envelopesApi.createEnvelope(accountId, envDef);

         logger.info("EnvelopeSummary: " + envelopeSummary);
         DCRequestAudit dcRequestAudit = new DCRequestAudit(serviceRequest.getProduct(), serviceRequest.getMode(),requestIds, acctNum, eventNum, envDef == null ? "" : ""/*getJsonObjectToString(envDef)*/, envelopeSummary == null ? "" : getJsonObjectToString(envelopeSummary), "S", reqTime, envelopeSummary == null ? "" : envelopeSummary.getEnvelopeId());
         logger.debug("dcRequestAudit = " + dcRequestAudit);
         tdDaoLayer.callDCAuditSP(dcRequestAudit);
         return new DCResponse(envelopeSummary == null ? null : envelopeSummary.getEnvelopeId(),null,null);
      }
      catch (com.docusign.esign.client.ApiException ex)
      {
         logger.error(ex);
         DCRequestAudit dcRequestAudit = new DCRequestAudit(serviceRequest.getProduct(), serviceRequest.getMode(),requestIds, acctNum, eventNum, envDef == null ? "" : ""/*getJsonObjectToString(envDef)*/, ex.toString(), "E", reqTime, "");
         logger.debug("dcRequestAudit = " + dcRequestAudit);
         tdDaoLayer.callDCAuditSP(dcRequestAudit);
         return new DCResponse(null,""+ex.getCode(),ex.getMessage());
      }
      catch (Exception ex)
      {
         logger.error(ex.getMessage());
      }
      return new DCResponse(null, ""+SysParameters.dcEGenErrCode, SysParameters.dcEGenErrMsg);
   }

   public ServerTemplate getServerTemplate(String sequence, DCTemplateDetails dcTemplateDetails)
   {

      ServerTemplate serverTemplate = new ServerTemplate();
      serverTemplate.setTemplateId(dcTemplateDetails.getTempId());
      serverTemplate.setSequence(sequence);
      return serverTemplate;
   }

   public InlineTemplate getInlineTemplate(String sequence)
   {

      InlineTemplate inlineTemplate = new InlineTemplate();
      inlineTemplate.setSequence(sequence);
      return inlineTemplate;
   }

   public EnvelopeDefinition getEnvelopeDefinition(ServiceRequest serviceRequest)
   {

      EnvelopeDefinition envelopeDefinition = new EnvelopeDefinition();
      getNotification(serviceRequest, envelopeDefinition);
      return envelopeDefinition;
   }

   public EnvelopeDefinition getEnvelopeDefinition()
   {

      EnvelopeDefinition envelopeDefinition = new EnvelopeDefinition();

      return envelopeDefinition;
   }

   public void getNotification(ServiceRequest serviceRequest, EnvelopeDefinition envelopeDefinition)
   {
      Notification notification = null;

      if (ServiceDetails.getConfigProperty(serviceRequest.getProduct(), Constant.SERVICES.DOCUSIGN_SERVICES.toString(), serviceRequest.getMode(), "USE_ACCT_DEFAULT_NOTIFICATION").equalsIgnoreCase("false"))
      {
         notification = new Notification();
         notification.setUseAccountDefaults("false");
         if (ServiceDetails.getConfigProperty(serviceRequest.getProduct(), Constant.SERVICES.DOCUSIGN_SERVICES.toString(), serviceRequest.getMode(), "REMINDER_ENABLED").equalsIgnoreCase("true"))
         {
            Reminders reminders = new Reminders();
            reminders.setReminderEnabled("true");
            reminders.setReminderDelay(ServiceDetails.getConfigProperty(serviceRequest.getProduct(), Constant.SERVICES.DOCUSIGN_SERVICES.toString(), serviceRequest.getMode(), "REMINDER_DELAY"));
            reminders.setReminderFrequency(ServiceDetails.getConfigProperty(serviceRequest.getProduct(), Constant.SERVICES.DOCUSIGN_SERVICES.toString(), serviceRequest.getMode(), "REMINDER_FREQUENCY"));
            notification.setReminders(reminders);
         }
         if (ServiceDetails.getConfigProperty(serviceRequest.getProduct(), Constant.SERVICES.DOCUSIGN_SERVICES.toString(), serviceRequest.getMode(), "EXPIRE_ENABLED").equalsIgnoreCase("true"))
         {
            Expirations expirations = new Expirations();
            expirations.setExpireEnabled("true");
            expirations.setExpireAfter(ServiceDetails.getConfigProperty(serviceRequest.getProduct(), Constant.SERVICES.DOCUSIGN_SERVICES.toString(), serviceRequest.getMode(), "EXPIRE_AFTER"));
            expirations.setExpireWarn(ServiceDetails.getConfigProperty(serviceRequest.getProduct(), Constant.SERVICES.DOCUSIGN_SERVICES.toString(), serviceRequest.getMode(), "EXPIRE_WARN"));
            notification.setExpirations(expirations);
         }
      }
      else if (ServiceDetails.getConfigProperty(serviceRequest.getProduct(), Constant.SERVICES.DOCUSIGN_SERVICES.toString(), serviceRequest.getMode(), "USE_ACCT_DEFAULT_NOTIFICATION").equalsIgnoreCase("true"))
      {
         notification = new Notification();
         notification.setUseAccountDefaults("true");
      }
      else
      {
         logger.info("Notification flag not set!");
      }
      if (notification != null)
      {
         envelopeDefinition.setNotification(notification);
      }
   }

   private CarbonCopy getCarbonCopyEmail(ServiceRequest serviceRequest)
   {
      CarbonCopy cc = new CarbonCopy();
      cc.setEmail(ServiceDetails.getConfigProperty(serviceRequest.getProduct(), Constant.SERVICES.DOCUSIGN_SERVICES.toString(), serviceRequest.getMode(), "CCMAIL"));
      cc.setName(ServiceDetails.getConfigProperty(serviceRequest.getProduct(), Constant.SERVICES.DOCUSIGN_SERVICES.toString(), serviceRequest.getMode(), "CCMAILNAME"));
      cc.setRecipientId("1");
      cc.setRoutingOrder("102");
      return cc;
   }

   public Recipients getRecipientsAcctTransfer(ServiceRequest serviceRequest, DCTemplateDetails dcTemplateDetails, AcctDetails acctDetails, AcctTransferDetails acctTransferDetails)
   {
      logger.info("DCUtility.getRecipientsAcctTransfer");

      List<String> dbColumns = null;
      List<Signer> signerLst = new ArrayList<>();

      Tabs tabs = new Tabs();
      List<Text> textboxLst = new ArrayList<>();
      List<Checkbox> checkboxeLst = new ArrayList<>();
      List<RadioGroup> radioGroupLst = new ArrayList<>();
      List<com.docusign.esign.model.List> listboxLst = new ArrayList<>();
      Recipients recipients = null;

      signerLst = getSigners(serviceRequest, dcTemplateDetails, acctDetails, acctDetails.getAcctOwnerDetails(), textboxLst, checkboxeLst, radioGroupLst, listboxLst, null);
      try
      {
         dbColumns = getFieldNames(acctTransferDetails, false);
         System.out.println("dbColumns = =" + dbColumns);
         List<DCTemplateMapping> dcTemplateMappingList = dcTemplateDetails.getDcTemplateMappings().get("Client");
         getTabObject(dcTemplateMappingList, dbColumns, textboxLst, checkboxeLst, radioGroupLst, listboxLst, acctTransferDetails);
      }
      catch (IllegalAccessException e)
      {
         e.printStackTrace();
      }

      if (textboxLst.size() > 0)
      {
         tabs.setTextTabs(textboxLst);
      }
      if (checkboxeLst.size() > 0)
      {
         tabs.setCheckboxTabs(checkboxeLst);
      }
      if (radioGroupLst.size() > 0)
      {
         tabs.setRadioGroupTabs(radioGroupLst);
      }
      if (listboxLst.size() > 0)
      {
         tabs.setListTabs(listboxLst);
      }

      if (signerLst.size() > 0)
      {
         Iterator<Signer> signerItr = signerLst.iterator();
         while (signerItr.hasNext())
         {
            Signer signer = (Signer) signerItr.next();
            signer.setTabs(tabs);
         }
         recipients = new Recipients();
         recipients.setSigners(signerLst);
         recipients.setCarbonCopies(new ArrayList<CarbonCopy>());
         recipients.getCarbonCopies().add(getCarbonCopyEmail(serviceRequest));
      }
      return recipients;
   }

   public Recipients getRecipientsTDTransfer(ServiceRequest serviceRequest, DCTemplateDetails dcTemplateDetails, AcctDetails acctDetails, TDTransferDetails tdTransferDetails)
   {
      logger.info("DCUtility.getRecipientsTDTransfer");

      List<String> dbColumns = null;
      List<Signer> signerLst = new ArrayList<>();

      Tabs tabs = new Tabs();
      List<Text> textboxLst = new ArrayList<>();
      List<Checkbox> checkboxeLst = new ArrayList<>();
      List<RadioGroup> radioGroupLst = new ArrayList<>();
      List<com.docusign.esign.model.List> listboxLst = new ArrayList<>();
      List<Ssn> ssnLst = new ArrayList<>();
      Recipients recipients = null;

      signerLst = getSigners(serviceRequest, dcTemplateDetails, acctDetails, acctDetails.getAcctOwnerDetails(), textboxLst, checkboxeLst, radioGroupLst, listboxLst, ssnLst);
      try
      {
         dbColumns = getFieldNames(tdTransferDetails, false);
         System.out.println("dbColumns = =" + dbColumns);
         List<DCTemplateMapping> dcTemplateMappingList = dcTemplateDetails.getDcTemplateMappings().get("Client");
         getTabObject(dcTemplateMappingList, dbColumns, textboxLst, checkboxeLst, radioGroupLst, listboxLst, ssnLst, tdTransferDetails);
      }
      catch (IllegalAccessException e)
      {
         e.printStackTrace();
      }

      if (textboxLst.size() > 0)
      {
         tabs.setTextTabs(textboxLst);
      }
      if (checkboxeLst.size() > 0)
      {
         tabs.setCheckboxTabs(checkboxeLst);
      }
      if (radioGroupLst.size() > 0)
      {
         tabs.setRadioGroupTabs(radioGroupLst);
      }
      if (listboxLst.size() > 0)
      {
         tabs.setListTabs(listboxLst);
      }
      if (ssnLst.size() > 0)
      {
         tabs.setSsnTabs(ssnLst);
      }

      if (signerLst.size() > 0)
      {
         Iterator<Signer> signerItr = signerLst.iterator();
         while (signerItr.hasNext())
         {
            Signer signer = (Signer) signerItr.next();
            signer.setTabs(tabs);
         }
         recipients = new Recipients();
         recipients.setSigners(signerLst);
         recipients.setCarbonCopies(new ArrayList<CarbonCopy>());
         recipients.getCarbonCopies().add(getCarbonCopyEmail(serviceRequest));
      }
      return recipients;
   }

   public Recipients getRecipientsElecFundTransfer(ServiceRequest serviceRequest, DCTemplateDetails dcTemplateDetails, AcctDetails acctDetails, ElecFundTransferDetails elecFundTransferDetails)
   {
      logger.info("DCUtility.getRecipientsElecFundTransfer");

      List<String> dbColumns = null;
      List<Signer> signerLst = new ArrayList<>();

      Tabs tabs = new Tabs();
      List<Text> textboxLst = new ArrayList<>();
      List<Checkbox> checkboxeLst = new ArrayList<>();
      List<RadioGroup> radioGroupLst = new ArrayList<>();
      List<com.docusign.esign.model.List> listboxLst = new ArrayList<>();
      Recipients recipients = null;

      signerLst = getSigners(serviceRequest, dcTemplateDetails, acctDetails, acctDetails.getAcctOwnerDetails(), textboxLst, checkboxeLst, radioGroupLst, listboxLst, null);
      try
      {
         dbColumns = getFieldNames(elecFundTransferDetails, false);
         System.out.println("dbColumns =" + dbColumns);
         List<DCTemplateMapping> dcTemplateMappingList = dcTemplateDetails.getDcTemplateMappings().get("Client");
         getTabObject(dcTemplateMappingList, dbColumns, textboxLst, checkboxeLst, radioGroupLst, listboxLst, elecFundTransferDetails);
      }
      catch (IllegalAccessException e)
      {
         e.printStackTrace();
      }

      if (textboxLst.size() > 0)
      {
         tabs.setTextTabs(textboxLst);
      }
      if (checkboxeLst.size() > 0)
      {
         tabs.setCheckboxTabs(checkboxeLst);
      }
      if (radioGroupLst.size() > 0)
      {
         tabs.setRadioGroupTabs(radioGroupLst);
      }
      if (listboxLst.size() > 0)
      {
         tabs.setListTabs(listboxLst);
      }

      if (signerLst.size() > 0)
      {
         Iterator<Signer> signerItr = signerLst.iterator();
         while (signerItr.hasNext())
         {
            Signer signer = (Signer) signerItr.next();
            signer.setTabs(tabs);
         }
         recipients = new Recipients();
         recipients.setSigners(signerLst);
         recipients.setCarbonCopies(new ArrayList<CarbonCopy>());
         recipients.getCarbonCopies().add(getCarbonCopyEmail(serviceRequest));
      }
      return recipients;
   }

   public Recipients getRecipientsChngAdd(ServiceRequest serviceRequest, DCTemplateDetails dcTemplateDetails, AcctDetails acctDetails, GetAcctChngAddrDetails getAcctChngAddrDetails)
   {
      logger.info("DCUtility.getRecipientsElecFundTransfer");

      List<String> dbColumns = null;
      List<Signer> signerLst = new ArrayList<>();

      Tabs tabs = new Tabs();
      List<Text> textboxLst = new ArrayList<>();
      List<Checkbox> checkboxeLst = new ArrayList<>();
      List<RadioGroup> radioGroupLst = new ArrayList<>();
      List<com.docusign.esign.model.List> listboxLst = new ArrayList<>();
      Recipients recipients = null;

      signerLst = getSigners(serviceRequest, dcTemplateDetails, acctDetails, acctDetails.getAcctOwnerDetails(), textboxLst, checkboxeLst, radioGroupLst, listboxLst, null);
      try
      {
         dbColumns = getFieldNames(getAcctChngAddrDetails, false);
         System.out.println("dbColumns =" + dbColumns);
         List<DCTemplateMapping> dcTemplateMappingList = dcTemplateDetails.getDcTemplateMappings().get("Client");
         getTabObject(dcTemplateMappingList, dbColumns, textboxLst, checkboxeLst, radioGroupLst, listboxLst, getAcctChngAddrDetails);
      }
      catch (IllegalAccessException e)
      {
         e.printStackTrace();
      }

      if (textboxLst.size() > 0)
      {
         tabs.setTextTabs(textboxLst);
      }
      if (checkboxeLst.size() > 0)
      {
         tabs.setCheckboxTabs(checkboxeLst);
      }
      if (radioGroupLst.size() > 0)
      {
         tabs.setRadioGroupTabs(radioGroupLst);
      }
      if (listboxLst.size() > 0)
      {
         tabs.setListTabs(listboxLst);
      }

      if (signerLst.size() > 0)
      {
         Iterator<Signer> signerItr = signerLst.iterator();
         while (signerItr.hasNext())
         {
            Signer signer = (Signer) signerItr.next();
            signer.setTabs(tabs);
         }
         recipients = new Recipients();
         recipients.setSigners(signerLst);
         recipients.setCarbonCopies(new ArrayList<CarbonCopy>());
         recipients.getCarbonCopies().add(getCarbonCopyEmail(serviceRequest));
      }
      return recipients;
   }

   public Recipients getAcctADVForm(ServiceRequest serviceRequest, DCTemplateDetails dcTemplateDetails, AcctDetails acctDetails)
   {
      logger.info("DCUtility.getRecipientsElecFundTransfer");

      List<Signer> signerLst = new ArrayList<>();

      Tabs tabs = new Tabs();
      List<Text> textboxLst = new ArrayList<>();
      List<Checkbox> checkboxeLst = new ArrayList<>();
      List<RadioGroup> radioGroupLst = new ArrayList<>();
      List<com.docusign.esign.model.List> listboxLst = new ArrayList<>();
      Recipients recipients = null;

      signerLst = getSigners(serviceRequest, dcTemplateDetails, acctDetails, acctDetails.getAcctOwnerDetails(), textboxLst, checkboxeLst, radioGroupLst, listboxLst, null);

      if (textboxLst.size() > 0)
      {
         tabs.setTextTabs(textboxLst);
      }
      if (checkboxeLst.size() > 0)
      {
         tabs.setCheckboxTabs(checkboxeLst);
      }
      if (radioGroupLst.size() > 0)
      {
         tabs.setRadioGroupTabs(radioGroupLst);
      }
      if (listboxLst.size() > 0)
      {
         tabs.setListTabs(listboxLst);
      }

      if (signerLst.size() > 0)
      {
         Iterator<Signer> signerItr = signerLst.iterator();
         while (signerItr.hasNext())
         {
            Signer signer = (Signer) signerItr.next();
            signer.setTabs(tabs);
         }
         recipients = new Recipients();
         recipients.setSigners(signerLst);
         recipients.setCarbonCopies(new ArrayList<CarbonCopy>());
         recipients.getCarbonCopies().add(getCarbonCopyEmail(serviceRequest));
      }
      return recipients;
   }

   public Recipients getRecipientsAcctCreation(ServiceRequest serviceRequest, DCTemplateDetails dcTemplateDetails, AcctDetails acctDetails, List<AcctOwnerDetails> acctOwnerDetails)
   {

      System.out.println("DCUtility.getRecipientsAcctCreation");


      List<String> dbColumns = null;
      List<Signer> signerLst = new ArrayList<>();

      Tabs tabs = new Tabs();
      List<Text> textboxLst = new ArrayList<>();
      List<Checkbox> checkboxeLst = new ArrayList<>();
      List<RadioGroup> radioGroupLst = new ArrayList<>();
      List<com.docusign.esign.model.List> listboxLst = new ArrayList<>();
      List<Ssn> ssnLst = new ArrayList<>();
      Recipients recipients = null;

      signerLst = getSigners(serviceRequest, dcTemplateDetails, acctDetails, acctOwnerDetails, textboxLst, checkboxeLst, radioGroupLst, listboxLst, ssnLst);
      if (acctDetails.getBenefiaciaryDetails() == null || acctDetails.getBenefiaciaryDetails().size() <= 0)
      {

      }
      else
      {
         Iterator<BenefiaciaryDetails> itr1 = acctDetails.getBenefiaciaryDetails().iterator();
         int iterCount = 1;
         while (itr1.hasNext())
         {
            BenefiaciaryDetails beneDetails = (BenefiaciaryDetails) itr1.next();
            dbColumns = null;
            try
            {
               dbColumns = getFieldNames(beneDetails, false);
            }
            catch (IllegalAccessException e)
            {
               e.printStackTrace();
            }

//            if (acctOwner.getOwnership().equalsIgnoreCase("Client"))
//            {
//               System.out.println("Client");
//               dcDocumentMappingList = dcTemplateDetails.getDcTemplateMappings().get("Client");
            List<DCTemplateMapping> dcTemplateMappingList = dcTemplateDetails.getDcTemplateMappings().get("Client");
            // System.out.println("dcDocumentMappingList = " + dcDocumentMappingList);
            getTabObject(dcTemplateMappingList, dbColumns, textboxLst, checkboxeLst, radioGroupLst, listboxLst, ssnLst, beneDetails, "" + iterCount, true);
            iterCount++;
//            }
//            else if (acctOwner.getOwnership().equalsIgnoreCase("Joint"))
//            {
//               System.out.println("Joint");
//               dcDocumentMappingList = dcTemplateDetails.getDcTemplateMappings().get("Joint");
//               if (dcDocumentMappingList == null || dcDocumentMappingList.size() <= 0)
//               {
//
//               }
//               else
//               {
//                  getTabObject(dcDocumentMappingList, dbColumns, textboxLst, signHereLst, radioGroupLst, listboxLst, acctOwner);
//               }
//            }
         }
      }


      if (textboxLst.size() > 0)
      {
         tabs.setTextTabs(textboxLst);
      }
      if (checkboxeLst.size() > 0)
      {
         tabs.setCheckboxTabs(checkboxeLst);
      }
      if (radioGroupLst.size() > 0)
      {
         tabs.setRadioGroupTabs(radioGroupLst);
      }
      if (listboxLst.size() > 0)
      {
         tabs.setListTabs(listboxLst);
      }
      if (ssnLst.size() > 0)
      {
         tabs.setSsnTabs(ssnLst);
      }

      if (signerLst.size() > 0)
      {
         Iterator<Signer> signerItr = signerLst.iterator();
         while (signerItr.hasNext())
         {
            Signer signer = (Signer) signerItr.next();
            signer.setTabs(tabs);
         }
         recipients = new Recipients();
         recipients.setSigners(signerLst);
         recipients.setCarbonCopies(new ArrayList<CarbonCopy>());
         recipients.getCarbonCopies().add(getCarbonCopyEmail(serviceRequest));
      }
      return recipients;
   }

   public Recipients getRecipientsMoveMoney(ServiceRequest serviceRequest, DCTemplateDetails dcTemplateDetails, AcctDetails acctDetails, List<AcctOwnerDetails> acctOwnerDetails, MoveMoneyDetails moveMoneyDetails, Object dataObject)
   {

      logger.info("DCUtility.getRecipientsMoveMoney");


      List<String> dbColumns = null;
      List<Signer> signerLst = new ArrayList<>();

      Tabs tabs = new Tabs();
      List<Text> textboxLst = new ArrayList<>();
      List<Checkbox> checkboxeLst = new ArrayList<>();
      List<RadioGroup> radioGroupLst = new ArrayList<>();
      List<com.docusign.esign.model.List> listboxLst = new ArrayList<>();
      Recipients recipients = null;

      signerLst = getSigners(serviceRequest, dcTemplateDetails, acctDetails, acctOwnerDetails, textboxLst, checkboxeLst, radioGroupLst, listboxLst, null);

      if ((dataObject != null && ((List) dataObject).size() > 0) && ((List) dataObject).get(0) instanceof MMAchBankDetails)
      {
         Iterator<MMAchBankDetails> itr1 = ((List<MMAchBankDetails>) dataObject).iterator();
         while (itr1.hasNext())
         {
            MMAchBankDetails achBankDetails = (MMAchBankDetails) itr1.next();
            dbColumns = null;
            try
            {
               dbColumns = getFieldNames(achBankDetails, false);
            }
            catch (IllegalAccessException e)
            {
               e.printStackTrace();
            }

//            if (acctOwner.getOwnership().equalsIgnoreCase("Client"))
//            {
//               System.out.println("Client");
//               dcDocumentMappingList = dcTemplateDetails.getDcTemplateMappings().get("Client");
            List<DCTemplateMapping> dcTemplateMappingList = dcTemplateDetails.getDcTemplateMappings().get("Client");
            // System.out.println("dcDocumentMappingList = " + dcDocumentMappingList);
            getTabObject(dcTemplateMappingList, dbColumns, textboxLst, checkboxeLst, radioGroupLst, listboxLst, achBankDetails);
//            }
//            else if (acctOwner.getOwnership().equalsIgnoreCase("Joint"))
//            {
//               System.out.println("Joint");
//               dcDocumentMappingList = dcTemplateDetails.getDcTemplateMappings().get("Joint");
//               if (dcDocumentMappingList == null || dcDocumentMappingList.size() <= 0)
//               {
//
//               }
//               else
//               {
//                  getTabObject(dcDocumentMappingList, dbColumns, textboxLst, signHereLst, radioGroupLst, listboxLst, acctOwner);
//               }
//            }
         }
      }

      if (textboxLst.size() > 0)
      {
         tabs.setTextTabs(textboxLst);
      }
      if (checkboxeLst.size() > 0)
      {
         tabs.setCheckboxTabs(checkboxeLst);
      }
      if (radioGroupLst.size() > 0)
      {
         tabs.setRadioGroupTabs(radioGroupLst);
      }
      if (listboxLst.size() > 0)
      {
         tabs.setListTabs(listboxLst);
      }

      if (signerLst.size() > 0)
      {
         Iterator<Signer> signerItr = signerLst.iterator();
         while (signerItr.hasNext())
         {
            Signer signer = (Signer) signerItr.next();
            signer.setTabs(tabs);
         }
         recipients = new Recipients();
         recipients.setSigners(signerLst);
         recipients.setCarbonCopies(new ArrayList<CarbonCopy>());
         recipients.getCarbonCopies().add(getCarbonCopyEmail(serviceRequest));
      }
      return recipients;
   }

   public Recipients getRecipientsMoveMoney(ServiceRequest serviceRequest, DCTemplateDetails dcTemplateDetails, AcctDetails acctDetails, List<AcctOwnerDetails> acctOwnerDetails, List<MoveMoneyDetails> moveMoneyDetailsLst)
   {

      logger.info("DCUtility.getRecipientsMoveMoney");


      List<MMAchBankDetails> mmAchBankDetails = null;
      List<MMInternalTransferDetails> mmInternalTransferDetails = null;
      List<MMFedwireAcctDetails> mmFedwireAcctDetails = null;


      Recipients recipients = null;
      try
      {
         List<String> dbColumns = null;
         List<Signer> signerLst = new ArrayList<>();

         Tabs tabs = new Tabs();
         List<Text> textboxLst = new ArrayList<>();
         List<Checkbox> checkboxeLst = new ArrayList<>();
         List<RadioGroup> radioGroupLst = new ArrayList<>();
         List<com.docusign.esign.model.List> listboxLst = new ArrayList<>();
         List<Ssn> ssnLst = new ArrayList<>();

         signerLst = getSigners(serviceRequest, dcTemplateDetails, acctDetails, acctOwnerDetails, textboxLst, checkboxeLst, radioGroupLst, listboxLst, ssnLst);

         List<DCTemplateMapping> dcTemplateMappingList = dcTemplateDetails.getDcTemplateMappings().get("Client");

         Iterator<MoveMoneyDetails> itr = moveMoneyDetailsLst.iterator();
         while (itr.hasNext())
         {
            MoveMoneyDetails moveMoneyDetails = (MoveMoneyDetails) itr.next();
            dbColumns = null;
            try
            {
               dbColumns = getFieldNames(moveMoneyDetails, false);
               getTabObject(dcTemplateMappingList, dbColumns, textboxLst, checkboxeLst, radioGroupLst, listboxLst, moveMoneyDetails);
            }
            catch (IllegalAccessException e)
            {
               e.printStackTrace();
            }

            if (moveMoneyDetails.getPayMethod().equalsIgnoreCase("ACH"))
            {
               mmAchBankDetails = tdDaoLayer.getMMAchBankDetails(moveMoneyDetails.getAcctnum(), moveMoneyDetails.getMoveMoneyPayMethId());
               Iterator<MMAchBankDetails> itr1 = mmAchBankDetails.iterator();
               while (itr1.hasNext())
               {
                  MMAchBankDetails achBankDetails = (MMAchBankDetails) itr1.next();
                  dbColumns = null;
                  try
                  {
                     dbColumns = getFieldNames(achBankDetails, false);
                     getTabObject(dcTemplateMappingList, dbColumns, textboxLst, checkboxeLst, radioGroupLst, listboxLst, achBankDetails);
                  }
                  catch (IllegalAccessException e)
                  {
                     e.printStackTrace();
                  }
               }
            }
            else if (moveMoneyDetails.getPayMethod().equalsIgnoreCase("FedWires"))
            {
               mmFedwireAcctDetails = tdDaoLayer.getMMFedwireAcctDetails(moveMoneyDetails.getAcctnum(), moveMoneyDetails.getMoveMoneyPayMethId());
               Iterator<MMFedwireAcctDetails> itr1 = mmFedwireAcctDetails.iterator();
               while (itr1.hasNext())
               {
                  MMFedwireAcctDetails achBankDetails = (MMFedwireAcctDetails) itr1.next();
                  dbColumns = null;
                  try
                  {
                     dbColumns = getFieldNames(achBankDetails, false);
                     getTabObject(dcTemplateMappingList, dbColumns, textboxLst, checkboxeLst, radioGroupLst, listboxLst, achBankDetails);
                  }
                  catch (IllegalAccessException e)
                  {
                     e.printStackTrace();
                  }
               }
            }
            else if (moveMoneyDetails.getPayMethod().equalsIgnoreCase("3rdPartyInternal"))
            {
               mmInternalTransferDetails = tdDaoLayer.getMMInternalTransferDetails(moveMoneyDetails.getAcctnum(), moveMoneyDetails.getMoveMoneyPayMethId());
            }
         }

//      if( (dataObject!=null && ((List)dataObject).size()>0) && ((List)dataObject).get(0) instanceof MMAchBankDetails){
//      }

         if (textboxLst.size() > 0)
         {
            tabs.setTextTabs(textboxLst);
         }
         if (checkboxeLst.size() > 0)
         {
            tabs.setCheckboxTabs(checkboxeLst);
         }
         if (radioGroupLst.size() > 0)
         {
            tabs.setRadioGroupTabs(radioGroupLst);
         }
         if (listboxLst.size() > 0)
         {
            tabs.setListTabs(listboxLst);
         }
         if (ssnLst.size() > 0)
         {
            tabs.setSsnTabs(ssnLst);
         }

         if (signerLst.size() > 0)
         {
            Iterator<Signer> signerItr = signerLst.iterator();
            while (signerItr.hasNext())
            {
               Signer signer = (Signer) signerItr.next();
               signer.setTabs(tabs);
            }
            recipients = new Recipients();
            recipients.setSigners(signerLst);
            recipients.setCarbonCopies(new ArrayList<CarbonCopy>());
            recipients.getCarbonCopies().add(getCarbonCopyEmail(serviceRequest));
         }
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
      return recipients;
   }

   public InlineTemplate getInlineTemplateAgreements(Map<String, DCDocumentDetails> dcDocumentDetails, AcctDetails acctDetails, List<AcctOwnerDetails> acctOwnerDetails)
   {

      System.out.println("DCUtility.getInlineTemplateAgreements");

      List<String> dbColumns = null;
      InlineTemplate inlineTemplate = null;
      List<Signer> signerLst = new ArrayList<>();


      Recipients recipients = null;

      int signerRecipientId = 1;
      Iterator<AcctOwnerDetails> itr = acctOwnerDetails.iterator();
      while (itr.hasNext())
      {
         AcctOwnerDetails acctOwner = (AcctOwnerDetails) itr.next();
         Signer signer = null;
         if (acctOwner.getOwnership().equalsIgnoreCase("Client"))
         {
            signer = new Signer();
            signer.setEmail(acctOwner.getEmailAddress());
            signer.setName(acctOwner.getFirstName() + " " + acctOwner.getLastName());
            signer.setRecipientId("" + signerRecipientId);
            signer.setRoleName(acctOwner.getOwnership());
            //signer.setRoutingOrder("1");

         }
         else if (acctOwner.getOwnership().equalsIgnoreCase("Joint"))
         {
            signer = new Signer();
            signer.setEmail(acctOwner.getEmailAddress());
            signer.setName(acctOwner.getFirstName() + " " + acctOwner.getLastName());
            signer.setRecipientId("" + signerRecipientId);
            signer.setRoleName(acctOwner.getOwnership());
            //signer.setRoutingOrder("2");
         }
         if (signer != null)
         {
            signerLst.add(signer);
            signerRecipientId++;
         }

      }
      final String SignTest1File = ServiceParameters.getConfigProperty(Constant.SERVICES.DOCUSIGN_SERVICES.toString(), Constant.DOCUSIGN_SERVICES.DOCUSIGN.toString(), "DOC_PATH");//"[PATH/TO/DOCUMENT/TEST.PDF]";
      List<Document> docs = new ArrayList<Document>();

      List<DCDocumentMapping> dcDocumentDetailsList = null;

      int documentId = 1;
      Iterator<Map.Entry<String, DCDocumentDetails>> docItr = dcDocumentDetails.entrySet().iterator();
      while (docItr.hasNext())
      {
         Map.Entry<String, DCDocumentDetails> dcDocumentDetail1 = (Map.Entry<String, DCDocumentDetails>) docItr.next();
         DCDocumentDetails dcDocumentDetail = dcDocumentDetail1.getValue();
         logger.info("dcDocumentDetail = " + dcDocumentDetail);

         byte[] fileBytes = null;

         try
         {
            // read file from a local directory
            Path path = Paths.get(SignTest1File + "//" + dcDocumentDetail.getFileName());
            fileBytes = Files.readAllBytes(path);
         }
         catch (IOException ioExcp)
         {
            // handle error
            logger.error(ioExcp);
         }

         Document doc = new Document();
         String base64Doc = DatatypeConverter.printBase64Binary(fileBytes);//Base64.getEncoder().encodeToString(fileBytes);
         doc.setDocumentBase64(base64Doc);
         doc.setName(dcDocumentDetail.getDocName());    // can be different from actual file name
         doc.setDocumentId("" + documentId);


         if (doc != null)
         {
            docs.add(doc);
            documentId++;
         }

      }

      if (signerLst.size() > 0)
      {
         Iterator<Signer> signerItr = signerLst.iterator();
         while (signerItr.hasNext())
         {

            Signer signer = (Signer) signerItr.next();
            Tabs tabs = new Tabs();
            List<Text> textboxLst = new ArrayList<>();
            List<SignHere> clientSignHereLst = new ArrayList<>();
            List<SignHere> jointSignHereLst = new ArrayList<>();

            int documentIdTab = 1;
            Iterator<Map.Entry<String, DCDocumentDetails>> docItrTab = dcDocumentDetails.entrySet().iterator();
            while (docItrTab.hasNext())
            {
               Map.Entry<String, DCDocumentDetails> dcDocumentDetail1 = (Map.Entry<String, DCDocumentDetails>) docItrTab.next();
               DCDocumentDetails dcDocumentDetail = dcDocumentDetail1.getValue();
               logger.info("dcDocumentDetail = " + dcDocumentDetail);

               if (dcDocumentDetail.getEditable().equalsIgnoreCase("Y"))
               {
                  try
                  {
                     dbColumns = getFieldNames(acctDetails, false);
                     logger.info("dbColumns = =" + dbColumns);
                     dcDocumentDetailsList = dcDocumentDetail.getDcDocumentMappings().get("Client");
                     getTabObject(dcDocumentDetailsList, dbColumns, textboxLst, clientSignHereLst, acctDetails, "" + documentIdTab, false, "" + 0);
                  }
                  catch (IllegalAccessException e)
                  {
                     e.printStackTrace();
                  }

                  Iterator<AcctOwnerDetails> itr2 = acctOwnerDetails.iterator();
                  int i = 1;
                  while (itr2.hasNext())
                  {
                     AcctOwnerDetails acctOwner = (AcctOwnerDetails) itr2.next();
                     dbColumns = null;
                     try
                     {
                        dbColumns = getFieldNames(acctOwner, false);
                        dcDocumentDetailsList = dcDocumentDetail.getDcDocumentMappings().get(acctOwner.getOwnership());
                        getTabObject(dcDocumentDetailsList, dbColumns, textboxLst, acctOwner.getOwnership().equals("Client") ? clientSignHereLst : jointSignHereLst, acctOwner, "" + documentIdTab, true, "" + i);
                        i++;
                     }
                     catch (IllegalAccessException e)
                     {
                        e.printStackTrace();
                     }
                  }
               }
               documentIdTab++;
            }
            if (textboxLst.size() > 0)
            {
               tabs.setTextTabs(textboxLst);
            }
            tabs.setSignHereTabs(signer.getRoleName().equals("Client") ? clientSignHereLst : jointSignHereLst);
            signer.setTabs(tabs);
         }
         // if(textboxLst.size()>0){tabs.setTextTabs(textboxLst);}
         //if(signHereLst.size()>0){tabs.setSignHereTabs(signHereLst);}

//      if(signerLst.size()>0){
//         Iterator<Signer> signerItr=signerLst.iterator();
//         while(signerItr.hasNext()){
//
//            Signer signer=(Signer)signerItr.next();
//            if(signer.getRoleName().equals("Client")){
//               if(clientSignHereLst.size()>0)
//               {
//                  Tabs tabs1=new Tabs();
//                  tabs1 = tabs;
//                  tabs1.setSignHereTabs(clientSignHereLst);
//                  signer.setTabs(tabs1);
//               }
//            }else if(signer.getRoleName().equals("Joint")){
//               if(jointSignHereLst.size()>0){
//                  Tabs tabs1= new Tabs();
//                  tabs1=tabs;
//                  tabs1.setSignHereTabs(jointSignHereLst);
//                  signer.setTabs(tabs1);
//               }
//            }
//         }
         recipients = new Recipients();
         recipients.setSigners(signerLst);

         recipients.setCarbonCopies(new ArrayList<CarbonCopy>());
         //recipients.getCarbonCopies().add(getCarbonCopyEmail(se));

         inlineTemplate = new InlineTemplate();
         inlineTemplate.setSequence("1");
         inlineTemplate.setRecipients(recipients);
         inlineTemplate.setDocuments(docs);
         getJsonObjectToString(inlineTemplate);
      }

      return inlineTemplate;
   }

   private List<Signer> getSigners(ServiceRequest serviceRequest, DCTemplateDetails dcTemplateDetails, AcctDetails acctDetails, List<AcctOwnerDetails> acctOwnerDetails, List<Text> textboxLst, List<Checkbox> checkboxeLst, List<RadioGroup> radioGroupLst, List<com.docusign.esign.model.List> listboxLst, List<Ssn> ssnLst)
   {
      List<String> dbColumns = null;
      List<Signer> signerLst = new ArrayList<>();
      List<DCTemplateMapping> dcTemplateMappingList = dcTemplateDetails.getDcTemplateMappings()== null?null:dcTemplateDetails.getDcTemplateMappings().get("Client");
      if (dcTemplateMappingList != null && dcTemplateMappingList.size() > 0)
      {
         try
         {
            dbColumns = getFieldNames(acctDetails, false);
            logger.info("dbColumns = =" + dbColumns);
            getTabObject(dcTemplateMappingList, dbColumns, textboxLst, checkboxeLst, radioGroupLst, listboxLst, acctDetails);
         }
         catch (IllegalAccessException e)
         {
            e.printStackTrace();
         }
      }
      int signerRecipientId = 1;
      Iterator<AcctOwnerDetails> itr = acctDetails.getAcctOwnerDetails().iterator();
      while (itr.hasNext())
      {
         AcctOwnerDetails acctOwner = (AcctOwnerDetails) itr.next();
         dbColumns = null;
         try
         {
            dbColumns = getFieldNames(acctOwner, false);
         }
         catch (IllegalAccessException e)
         {
            e.printStackTrace();
         }
         Signer signer = null;

         if (acctOwner.getOwnership().equalsIgnoreCase("Client"))
         {
            signer = new Signer();
            signer.setEmail(acctOwner.getEmailAddress());
            signer.setName(acctOwner.getFirstName() + " " + acctOwner.getLastName());
            signer.setRoleName(acctOwner.getOwnership());
            signer.setRecipientId("" + signerRecipientId);
            if (dcTemplateDetails.getAuthRequired().equalsIgnoreCase("Y"))
            {
               signer.setIdCheckConfigurationName(
                  ServiceDetails.getConfigProperty(serviceRequest.getProduct(), Constant.SERVICES.DOCUSIGN_SERVICES.toString(), serviceRequest.getMode(), "ID_CHECK_CONF_NAME"));
               signer.setIdCheckInformationInput(getKBAInputs(acctOwner));
            }
            dcTemplateMappingList = dcTemplateDetails.getDcTemplateMappings()== null?null:dcTemplateDetails.getDcTemplateMappings().get("Client");
//            logger.info("dcDocumentMappingList = " + dcTemplateMappingList);
            if (dcTemplateMappingList == null || dcTemplateMappingList.size() <= 0)
            {
               logger.info("Template Mapping details are not available for Client");
            }
            else
            {
               getTabObject(dcTemplateMappingList, dbColumns, textboxLst, checkboxeLst, radioGroupLst, listboxLst, ssnLst, acctOwner);
               if (acctOwner.getEmploymentDetails() == null || acctOwner.getEmploymentDetails().size() <= 0)
               {
                  logger.info("Employment details are not available");
               }
               else
               {
                  try
                  {
                     dbColumns = getFieldNames(acctOwner.getEmploymentDetails().get(0), false);
                     getTabObject(dcTemplateMappingList, dbColumns, textboxLst, checkboxeLst, radioGroupLst, listboxLst, acctOwner.getEmploymentDetails().get(0));
                  }
                  catch (IllegalAccessException e)
                  {
                     e.printStackTrace();
                  }
               }
            }
         }
         else if (acctOwner.getOwnership().equalsIgnoreCase("Joint"))
         {
            signer = new Signer();
            signer.setEmail(acctOwner.getEmailAddress());
            signer.setName(acctOwner.getFirstName() + " " + acctOwner.getLastName());
            signer.setRoleName(acctOwner.getOwnership());
            signer.setRecipientId("" + signerRecipientId);
            if (dcTemplateDetails.getAuthRequired().equalsIgnoreCase("Y"))
            {
               signer.setIdCheckConfigurationName(
                  ServiceDetails.getConfigProperty(serviceRequest.getProduct(), Constant.SERVICES.DOCUSIGN_SERVICES.toString(), serviceRequest.getMode(), "ID_CHECK_CONF_NAME"));
               signer.setIdCheckInformationInput(getKBAInputs(acctOwner));
            }
            dcTemplateMappingList = dcTemplateDetails.getDcTemplateMappings()== null?null:dcTemplateDetails.getDcTemplateMappings().get("Joint");
            if (dcTemplateMappingList == null || dcTemplateMappingList.size() <= 0)
            {
               logger.info("Template Mapping details are not available for Joint");
            }
            else
            {
               getTabObject(dcTemplateMappingList, dbColumns, textboxLst, checkboxeLst, radioGroupLst, listboxLst, ssnLst, acctOwner);
               if (acctOwner.getEmploymentDetails() == null || acctOwner.getEmploymentDetails().size() <= 0)
               {
                  logger.info("Employment details are not available");
               }
               else
               {
                  try
                  {
                     dbColumns = getFieldNames(acctOwner.getEmploymentDetails().get(0), false);
                  }
                  catch (IllegalAccessException e)
                  {
                     e.printStackTrace();
                  }
                  getTabObject(dcTemplateMappingList, dbColumns, textboxLst, checkboxeLst, radioGroupLst, listboxLst, acctOwner.getEmploymentDetails().get(0));
               }
            }
         }
         if (signer != null)
         {
            signerLst.add(signer);
         }
         signerRecipientId++;
      }

      return signerLst;
   }

   private void getTabObject(List<DCTemplateMapping> dcTemplateMappingList, List<String> dbColumns, List<Text> textboxLst, List<Checkbox> checkboxeLst, List<RadioGroup> radioGroupLst, List<com.docusign.esign.model.List> listboxLst, Object dataObject)
   {
      Iterator<DCTemplateMapping> itr1 = dcTemplateMappingList.iterator();
      while (itr1.hasNext())
      {
         DCTemplateMapping dctemplate = (DCTemplateMapping) itr1.next();
         if (dbColumns.contains(dctemplate.getDbColumn()))
         {

            if (dctemplate.getTab().equalsIgnoreCase("Textbox"))
            {
               Text text = getText(dctemplate, dataObject);
               if (text == null)
               {
               }
               else
               {
                  textboxLst.add(text);
               }
            }
            else if (dctemplate.getTab().equalsIgnoreCase("Checkbox"))
            {
               Checkbox text = getCheckbox(dctemplate, dataObject);
               if (text == null)
               {
               }
               else
               {
                  checkboxeLst.add(text);
               }
            }
            else if (dctemplate.getTab().equalsIgnoreCase("Radiobox"))
            {
               RadioGroup text = getRadioGroup(dctemplate, dataObject);
               if (text == null)
               {
               }
               else
               {
                  radioGroupLst.add(text);
               }
            }
            else if (dctemplate.getTab().equalsIgnoreCase("Listbox"))
            {
               com.docusign.esign.model.List text = getList(dctemplate, dataObject);
               if (text == null)
               {
               }
               else
               {
                  listboxLst.add(text);
               }
            }
         }
      }
   }

   private void getTabObject(List<DCTemplateMapping> dcTemplateMappingList, List<String> dbColumns, List<Text> textboxLst, List<Checkbox> checkboxeLst, List<RadioGroup> radioGroupLst, List<com.docusign.esign.model.List> listboxLst, List<Ssn> ssnLst, Object dataObject)
   {
      Iterator<DCTemplateMapping> itr1 = dcTemplateMappingList.iterator();
      while (itr1.hasNext())
      {
         DCTemplateMapping dctemplate = (DCTemplateMapping) itr1.next();
         if (dbColumns.contains(dctemplate.getDbColumn()))
         {

            if (dctemplate.getTab().equalsIgnoreCase("Textbox"))
            {
               Text text = getText(dctemplate, dataObject);
               if (text == null)
               {
               }
               else
               {
                  textboxLst.add(text);
               }
            }
            else if (dctemplate.getTab().equalsIgnoreCase("Checkbox"))
            {
               Checkbox text = getCheckbox(dctemplate, dataObject);
               if (text == null)
               {
               }
               else
               {
                  checkboxeLst.add(text);
               }
            }
            else if (dctemplate.getTab().equalsIgnoreCase("Radiobox"))
            {
               RadioGroup text = getRadioGroup(dctemplate, dataObject);
               if (text == null)
               {
               }
               else
               {
                  radioGroupLst.add(text);
               }
            }
            else if (dctemplate.getTab().equalsIgnoreCase("Listbox"))
            {
               com.docusign.esign.model.List text = getList(dctemplate, dataObject);
               if (text == null)
               {
               }
               else
               {
                  listboxLst.add(text);
               }
            }
            else if (dctemplate.getTab().equalsIgnoreCase("Ssn"))
            {
               if (ssnLst == null)
               {
               }
               else
               {
                  Ssn text = getSsn(dctemplate, dataObject);
                  if (text == null)
                  {
                  }
                  else
                  {
                     ssnLst.add(text);
                  }
               }
            }
         }
      }

   }

   private void getTabObject(List<DCDocumentMapping> dcDocumentMappingList, List<String> dbColumns, List<Text> textboxLst, List<SignHere> signHereLst, Object dataObject, String documentId, boolean sigHereFlag, String recipientId)
   {
      Iterator<DCDocumentMapping> itr1 = dcDocumentMappingList.iterator();
      while (itr1.hasNext())
      {
         DCDocumentMapping dcDocumentMapping = (DCDocumentMapping) itr1.next();
         if (dbColumns.contains(dcDocumentMapping.getDbColumn()))
         {

            if (dcDocumentMapping.getTab().equalsIgnoreCase("Textbox"))
            {
               Text text = getText(dcDocumentMapping, dataObject, documentId, null);
               if (text == null)
               {
               }
               else
               {
                  textboxLst.add(text);
               }
            }
         }
         else if (dcDocumentMapping.getTab().equalsIgnoreCase("SignHere"))
         {
            if (sigHereFlag)
            {
               SignHere text = getSignHere(dcDocumentMapping, dataObject, documentId, recipientId);
               if (text == null)
               {
               }
               else
               {
                  signHereLst.add(text);
               }
            }
         }
         else if (dcDocumentMapping.getDbColumn().equalsIgnoreCase("DATE"))
         {
            Text text = getText(dcDocumentMapping, dataObject, documentId, dcDocumentMapping.getDbColumn());
            if (text == null)
            {
            }
            else
            {
               textboxLst.add(text);
            }
         }
      }
   }

   private void getTabObject(List<DCTemplateMapping> dcTemplateMappingList, List<String> dbColumns, List<Text> textboxLst, List<Checkbox> checkboxeLst, List<RadioGroup> radioGroupLst, List<com.docusign.esign.model.List> listboxLst, List<Ssn> ssnLst, Object dataObject, String addLableVal, boolean isPostfix)
   {
      Iterator<DCTemplateMapping> itr1 = dcTemplateMappingList.iterator();
      ;
      String preLable = null;
      while (itr1.hasNext())
      {
         preLable = addLableVal;
         DCTemplateMapping dctemplate = (DCTemplateMapping) itr1.next();
//         System.out.println(dctemplate.getDbColumn() +" : "+dctemplate.getDbColumn()+addLableVal+" : "+addLableVal+dctemplate.getDbColumn());

//         System.out.println(dbColumns.contains(dctemplate.getDbColumn()) + "(isPostfix==true?"+ dcTemplateMappingList.contains(dctemplate.getDbColumn()+addLableVal)+" : "+dcTemplateMappingList.contains(addLableVal+dctemplate.getDbColumn()));
         //if(dbColumns.contains(dctemplate.getDbColumn()) && (isPostfix==true? dcTemplateMappingList.contains(dctemplate.getDbColumn()+addLableVal):dcTemplateMappingList.contains(addLableVal+dctemplate.getDbColumn()))){
         if (dctemplate.getDbColumn().equalsIgnoreCase("beneShare"))
         {
            preLable = "%" + addLableVal;
         }
         if (dbColumns.contains(dctemplate.getDbColumn())
            && (isPostfix == true ? dctemplate.getLable().equalsIgnoreCase(dctemplate.getDbColumn() + preLable)
            : dctemplate.getLable().equalsIgnoreCase(preLable + dctemplate.getDbColumn())))
         {

            if (dctemplate.getTab().equalsIgnoreCase("Textbox"))
            {
               Text text = getText(dctemplate, dataObject);
               if (text == null)
               {
               }
               else
               {
                  textboxLst.add(text);
               }
            }
            else if (dctemplate.getTab().equalsIgnoreCase("Checkbox"))
            {
               Checkbox text = getCheckbox(dctemplate, dataObject);
               if (text == null)
               {
               }
               else
               {
                  checkboxeLst.add(text);
               }
            }
            else if (dctemplate.getTab().equalsIgnoreCase("Radiobox"))
            {
               RadioGroup text = getRadioGroup(dctemplate, dataObject);
               if (text == null)
               {
               }
               else
               {
                  radioGroupLst.add(text);
               }
            }
            else if (dctemplate.getTab().equalsIgnoreCase("Listbox"))
            {
               com.docusign.esign.model.List text = getList(dctemplate, dataObject);
               if (text == null)
               {
               }
               else
               {
                  listboxLst.add(text);
               }
            }
            else if (dctemplate.getTab().equalsIgnoreCase("Ssn"))
            {
               if (ssnLst == null)
               {
               }
               else
               {
                  Ssn text = getSsn(dctemplate, dataObject);
                  if (text == null)
                  {
                  }
                  else
                  {
                     ssnLst.add(text);
                  }
               }
            }
         }
      }

   }

   private IdCheckInformationInput getKBAInputs(AcctOwnerDetails acctOwnerDetails)
   {
      IdCheckInformationInput idCheckInformationInput = new IdCheckInformationInput();
      AddressInformationInput addressInformationInput = new AddressInformationInput();

      AddressInformation addressInformation = new AddressInformation();
      addressInformation.setStreet1(acctOwnerDetails.getPhysicalAddressStreet());//("6948 Shepherd");
      addressInformation.setCity(acctOwnerDetails.getPhysicalAddressCity());//("Russelville");
      addressInformation.setState(acctOwnerDetails.getPhysicalAddressState());//("AR");
      addressInformation.setZip(acctOwnerDetails.getPhysicalAddressZipCode());//("72801");
      addressInformationInput.setAddressInformation(addressInformation);

      Ssn4InformationInput ssn4InformationInput = new Ssn4InformationInput();
      ssn4InformationInput.setSsn4(acctOwnerDetails.getSsn() == null || acctOwnerDetails.getSsn().equals("") ? "" : acctOwnerDetails.getSsn().substring(acctOwnerDetails.getSsn().length() - 4, acctOwnerDetails.getSsn().length()));//("6280");//(elecFundTransferDetails.getSsn());//("7438");

      DobInformationInput dobInformationInput = new DobInformationInput();
      dobInformationInput.setDateOfBirth(acctOwnerDetails.getDob());//("09/11/1983");

      idCheckInformationInput.setAddressInformationInput(addressInformationInput);
      idCheckInformationInput.setSsn4InformationInput(ssn4InformationInput);
      idCheckInformationInput.setDobInformationInput(dobInformationInput);

      return idCheckInformationInput;
   }

   private Text getText(DCDocumentMapping documentMapping, Object dataObject, String documentId, String identifier)
   {
      Text text = null;
      try
      {
         text = new Text();
         text.setTabLabel(documentMapping.getLable());
         text.xPosition(documentMapping.getxPosition());
         text.yPosition(documentMapping.getyPosition());
         text.setDocumentId("" + documentId);
         text.setPageNumber(documentMapping.getPageNumber());
         text.setWidth(documentMapping.getWidth());
         text.setHeight(documentMapping.getHeight());
         if (identifier == null || identifier.equals(""))
         {
            text.setValue(getInstanceValue(dataObject, documentMapping.getDbColumn()).toString());
         }
         else if (identifier.equals("DATE"))
         {
            text.setValue(CommonUtil.getDateMMDDYYYY(new Date()));
         }
      }
      catch (NoSuchFieldException e)
      {
         logger.error(documentMapping.getLable() + " = " + e);
         e.printStackTrace();
      }
      catch (ClassNotFoundException e)
      {
         logger.error(documentMapping.getLable() + " = " + e);
         e.printStackTrace();
      }
      catch (IllegalAccessException e)
      {
         logger.error(documentMapping.getLable() + " = " + e);
         e.printStackTrace();
      }
      return text;
   }

   private SignHere getSignHere(DCDocumentMapping documentMapping, Object dataObject, String documentId, String recipientId)
   {
      SignHere signHere = null;
      try
      {
         System.out.println(documentMapping);
         signHere = new SignHere();
         signHere.setTabLabel(documentMapping.getLable());
         signHere.setDocumentId("" + documentId);
         signHere.setPageNumber(documentMapping.getPageNumber());
         signHere.setRecipientId(recipientId);
         signHere.setXPosition(documentMapping.getxPosition());
         signHere.setYPosition(documentMapping.getyPosition());

      }
      catch (Exception e)
      {

      }
      return signHere;
   }

   private Ssn getSsn(DCTemplateMapping dctemplate, Object dataObject)
   {
      Ssn text = null;
      try
      {
         text = new Ssn();
         text.setTabLabel(dctemplate.getLable());
         text.setValue(getInstanceValue(dataObject, dctemplate.getDbColumn()).toString());
      }
      catch (NoSuchFieldException e)
      {
         logger.error(dctemplate.getLable() + " = " + e);
         e.printStackTrace();
      }
      catch (ClassNotFoundException e)
      {
         logger.error(dctemplate.getLable() + " = " + e);
         e.printStackTrace();
      }
      catch (IllegalAccessException e)
      {
         logger.error(dctemplate.getLable() + " = " + e);
         e.printStackTrace();
      }
      return text;
   }

   private Text getText(DCTemplateMapping dctemplate, Object dataObject)
   {
      Text text = null;
      try
      {
         text = new Text();

         text.setTabLabel(dctemplate.getLable());
         text.setValue(getInstanceValue(dataObject, dctemplate.getDbColumn()).toString());

      }
      catch (NoSuchFieldException e)
      {
         logger.error(dctemplate.getLable() + " = " + e);
         e.printStackTrace();
      }
      catch (ClassNotFoundException e)
      {
         logger.error(dctemplate.getLable() + " = " + e);
         e.printStackTrace();
      }
      catch (IllegalAccessException e)
      {
         logger.error(dctemplate.getLable() + " = " + e);
         e.printStackTrace();
      }
      return text;
   }

   private Checkbox getCheckbox(DCTemplateMapping dctemplate, Object dataObject)
   {

      Checkbox checkbox = null;
      try
      {
         if (getInstanceValue(dataObject, dctemplate.getDbColumn()).toString().equalsIgnoreCase("Y") || getInstanceValue(dataObject, dctemplate.getDbColumn()).toString().equalsIgnoreCase("true"))
         {
            checkbox = new Checkbox();
            checkbox.setTabLabel(dctemplate.getLable());
            checkbox.setSelected("true");
         }
         else
         {
            checkbox = new Checkbox();
            checkbox.setTabLabel(getInstanceValue(dataObject, dctemplate.getDbColumn()).toString());
            checkbox.setSelected("true");
         }
         if (dctemplate.getIsDisabled().equalsIgnoreCase("Y"))
         {
            checkbox.setLocked("true");
         }
      }
      catch (NoSuchFieldException e)
      {
         logger.error(dctemplate.getLable() + " = " + e);
         e.printStackTrace();
      }
      catch (ClassNotFoundException e)
      {
         logger.error(dctemplate.getLable() + " = " + e);
         e.printStackTrace();
      }
      catch (IllegalAccessException e)
      {
         logger.error(dctemplate.getLable() + " = " + e);
         e.printStackTrace();
      }
      return checkbox;
   }

   private RadioGroup getRadioGroup(DCTemplateMapping dctemplate, Object dataObject)
   {


      RadioGroup radioGroup = null;
      try
      {
         radioGroup = new RadioGroup();
         radioGroup.setGroupName(dctemplate.getLable());
         getInstanceValue(dataObject, dctemplate.getDbColumn()).toString();
         radioGroup.setRadios(new ArrayList<Radio>());
         Radio radio = new Radio();
         radio.setValue(getInstanceValue(dataObject, dctemplate.getDbColumn()).toString());
         radio.setSelected("true");
         radioGroup.getRadios().add(radio);
         // radioGroup.se
         //					if(radioGroup.getGroupName().equalsIgnoreCase("AccountType")){
         //						radioGroup.setRadios(new ArrayList<Radio>());
         //						Radio radio=new Radio();
         //						radio.setValue("JointTenants");
         //						radio.setSelected("true");
         //						radioGroup.getRadios().add(radio);
         //						System.out.println(tempId+",Radio,"+radioGroup.getGroupName()+",,"+signer.getRoleName());
         //					}
      }
      catch (NoSuchFieldException e)
      {
         logger.error(dctemplate.getLable() + " = " + e);
         e.printStackTrace();
      }
      catch (ClassNotFoundException e)
      {
         logger.error(dctemplate.getLable() + " = " + e);
         e.printStackTrace();
      }
      catch (IllegalAccessException e)
      {
         logger.error(dctemplate.getLable() + " = " + e);
         e.printStackTrace();
      }

      return radioGroup;
   }

   private com.docusign.esign.model.List getList(DCTemplateMapping dctemplate, Object dataObject)
   {

      com.docusign.esign.model.List list = null;
      try
      {
         list = new com.docusign.esign.model.List();
         list.setTabLabel(dctemplate.getLable());
         list.setValue(getInstanceValue(dataObject, dctemplate.getDbColumn()).toString());
      }
      catch (NoSuchFieldException e)
      {
         e.printStackTrace();
      }
      catch (ClassNotFoundException e)
      {
         e.printStackTrace();
      }
      catch (IllegalAccessException e)
      {
         e.printStackTrace();
      }
      return list;
   }

   public String getJsonObjectToString(Object object)
   {
      String jsonObject = null;
      try
      {
         ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

         mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
         mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
         jsonObject = mapper.writeValueAsString(object);
         logger.info("PrintJsonObject :" + jsonObject);
         return jsonObject;
      }
      catch (JsonProcessingException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      return jsonObject;
   }

   //   public Tabs getTabs (String jsonString, Map<String, DCTemplateMapping> mapping){
//
//      int tempId=1;
//
//      System.out.println("JsonObjectIterator.JsonObjectIterator()");
//
//      ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//      List<Signer> envelopeDefinition=null;
//      try {
//         mapper.setSerializationInclusion(Include.NON_EMPTY);
//         envelopeDefinition = mapper.readValue(jsonString, new TypeReference<List<Signer>>() {});
//         System.out.println();
//
//         System.out.println();
//
//         List<Signer> signers=envelopeDefinition;//envelopeDefinition.getRecipientsAcctCreation().getSigners();
//         Iterator<Signer> signerItr=signers.iterator();
//
//         while (signerItr.hasNext()) {
//            Signer signer = (Signer) signerItr.next();
//            Tabs tabs=signer.getTabs();
//
//            Iterator<Text> itrText = tabs.getTextTabs().iterator();
//            while (itrText.hasNext()) {
//               Text text = (Text) itrText.next();
//               //System.out.println(tempId+",Textbox,"+text.getTabLabel()+",,"+signer.getRoleName());
//
//            }
//
//            Iterator<Checkbox> itrChkBox = tabs.getCheckboxTabs().iterator();
//            while (itrChkBox.hasNext()) {
//               Checkbox checkbox = (Checkbox) itrChkBox.next();
//               //System.out.println(tempId+",Checkbox,"+checkbox.getTabLabel()+",,"+signer.getRoleName());
//            }
//
//            Iterator<com.docusign.esign.model.List> itrListBox = tabs.getListTabs().iterator();
//            while (itrListBox.hasNext()) {
//               com.docusign.esign.model.List listbox = (com.docusign.esign.model.List) itrListBox.next();
//               //System.out.println(tempId+",Listbox,"+listbox.getTabLabel()+",,"+signer.getRoleName());
//            }
//
//            Iterator<RadioGroup> itrRadioGroup = tabs.getRadioGroupTabs().iterator();
//            while (itrRadioGroup.hasNext()) {
//               RadioGroup radioGroup = (RadioGroup) itrRadioGroup.next();
//               //System.out.println(tempId+",Radiobox,"+radioGroup.getGroupName()+",,"+signer.getRoleName());
////					if(radioGroup.getGroupName().equalsIgnoreCase("AccountType")){
////						radioGroup.setRadios(new ArrayList<Radio>());
////						Radio radio=new Radio();
////						radio.setValue("JointTenants");
////						radio.setSelected("true");
////						radioGroup.getRadios().add(radio);
////						System.out.println(tempId+",Radio,"+radioGroup.getGroupName()+",,"+signer.getRoleName());
////					}
//               radioGroup.getRadios();
//
//            }
//
//         }
//
//
//
//
//
//      } catch (JsonParseException e1) {
//         // TODO Auto-generated catch block
//         e1.printStackTrace();
//      } catch (JsonMappingException e1) {
//         // TODO Auto-generated catch block
//         e1.printStackTrace();
//      } catch (IOException e1) {
//         // TODO Auto-generated catch block
//         e1.printStackTrace();
//      }
//      return null;
//   }

}