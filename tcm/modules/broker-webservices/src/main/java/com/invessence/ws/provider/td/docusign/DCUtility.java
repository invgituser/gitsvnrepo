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
import com.invessence.service.util.*;
import com.invessence.ws.provider.td.bean.*;
import com.invessence.ws.provider.td.dao.TDDaoLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 *
 * Created by abhangp on 8/12/2016.
 */
@Component
public class DCUtility
{
   @Autowired
   TDDaoLayer tdDaoLayer;
   public EnvelopeSummary createEnvelope (EnvelopeDefinition envDef/*, String acctNum, String eventNum*/)
   {
      try {
         ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

         mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
         mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
         System.out.println("EnvelopeDefinition :" +mapper.writeValueAsString(envDef));
      } catch (JsonProcessingException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }

      EnvelopeSummary envelopeSummary=null;
      String UserName = ServiceParameters.getConfigProperty(Constant.SERVICES.DOCUSIGN_SERVICES.toString(),Constant.DOCUSIGN_SERVICES.DOCUSIGN.toString(),"USERNAME");//"prashant@invessence.com";//"[EMAIL]";
      String Password = ServiceParameters.getConfigProperty(Constant.SERVICES.DOCUSIGN_SERVICES.toString(),Constant.DOCUSIGN_SERVICES.DOCUSIGN.toString(),"PASSWORD");//"Inv3ss3nc3!";//"[PASSWORD]";

      // TODO: Enter your Integrator Key (aka API key), created through your developer sandbox preferences
      String IntegratorKey = ServiceParameters.getConfigProperty(Constant.SERVICES.DOCUSIGN_SERVICES.toString(),Constant.DOCUSIGN_SERVICES.DOCUSIGN.toString(),"INTEGRATOR_KEY");//"TDAM-d7feb45c-e88d-4c20-b5bd-1dcd9a9d6f56";//"[INTEGRATOR_KEY]";

      // for production environment update to "www.docusign.net/restapi"
      String BaseUrl = ServiceParameters.getConfigProperty(Constant.SERVICES.DOCUSIGN_SERVICES.toString(),Constant.DOCUSIGN_SERVICES.DOCUSIGN.toString(),"BASE_URL");//"https://demo.docusign.net/restapi";

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

         System.out.println("LoginInformation: " + loginAccounts);
      }
      catch (com.docusign.esign.client.ApiException ex)
      {
         System.out.println("Exception: " + ex);
      }

//      //===============================================================================
//
//
      Date reqTime= null;
      try
      {
         // use the |accountId| we retrieved through the Login API to create the Envelope
         //String accountId = loginAccounts.get(0).getAccountId();

         String accountId=ServiceParameters.getConfigProperty(Constant.SERVICES.DOCUSIGN_SERVICES.toString(),Constant.DOCUSIGN_SERVICES.DOCUSIGN.toString(),"ACCOUNT_ID");


         // instantiate a new EnvelopesApi object
         EnvelopesApi envelopesApi = new EnvelopesApi();
         reqTime= new Date();
         // call the createEnvelope() API
         // envDef.setCustomFields(customFields);
         envelopeSummary = envelopesApi.createEnvelope(accountId, envDef);

         System.out.println("EnvelopeSummary: " + envelopeSummary);
         DCRequestAudit dcRequest = new DCRequestAudit(1,envDef==null?"":""/*getJsonObjectToString(envDef)*/,envelopeSummary==null?"":getJsonObjectToString(envelopeSummary),"S", reqTime, envelopeSummary==null?"":envelopeSummary.getEnvelopeId());
         System.out.println("dcRequest = " + dcRequest);
         tdDaoLayer.callDCAuditSP(dcRequest);
      }
      catch (com.docusign.esign.client.ApiException ex)
      {
         System.out.println("Exception: " + ex);
         DCRequestAudit dcRequest = new DCRequestAudit(1,envDef==null?"":""/*getJsonObjectToString(envDef)*/,ex.toString(),"E", reqTime, "");
         System.out.println("dcRequest = " + dcRequest);
         tdDaoLayer.callDCAuditSP(dcRequest);
      }
//      return new WSCallResult(new WSCallStatus(mailingAddressesResult.getErrorStatus().getErrorCode(), mailingAddressesResult.getErrorStatus().getErrorMessage()),userAcctExt);


      return envelopeSummary;
   }

   public ServerTemplate getServerTemplate (String sequence, DCTemplateDetails dcTemplateDetails){

      ServerTemplate serverTemplate = new ServerTemplate();
      serverTemplate.setTemplateId(dcTemplateDetails.getTempId());
      serverTemplate.setSequence(sequence);
      return serverTemplate;
   }

   public InlineTemplate getInlineTemplate (String sequence){

      InlineTemplate inlineTemplate = new InlineTemplate();
      inlineTemplate.setSequence(sequence);
      return inlineTemplate;
   }

   private CarbonCopy getCarbonCopyEmail(){
      CarbonCopy cc = new CarbonCopy();
      cc.setEmail(ServiceParameters.getConfigProperty(Constant.SERVICES.DOCUSIGN_SERVICES.toString(),Constant.DOCUSIGN_SERVICES.DOCUSIGN.toString(),"CCMAIL"));
      cc.setName(ServiceParameters.getConfigProperty(Constant.SERVICES.DOCUSIGN_SERVICES.toString(),Constant.DOCUSIGN_SERVICES.DOCUSIGN.toString(),"CCMAILNAME"));
      cc.setRecipientId("1");
      return cc;
   }

   public Recipients getRecipientsAcctTransfer(DCTemplateDetails dcTemplateDetails, AcctDetails acctDetails, AcctTransferDetails acctTransferDetails)
   {
   System.out.println("*****************************************************");
      System.out.println("DCUtility.getRecipientsAcctTransfer");
   System.out.println("*****************************************************");

   List<String> dbColumns=null;
   List<Signer> signerLst=new ArrayList<>();

   Tabs tabs = new Tabs();
   List<Text> textboxLst=new ArrayList<>();
   List<Checkbox> checkboxeLst=new ArrayList<>();
   List<RadioGroup> radioGroupLst=new ArrayList<>();
   List<com.docusign.esign.model.List> listboxLst=new ArrayList<>();
   Recipients recipients=null;

   signerLst=getSigners(dcTemplateDetails, acctDetails, acctDetails.getAcctOwnerDetails(), textboxLst, checkboxeLst, radioGroupLst, listboxLst);
   try
   {
      dbColumns=getFieldNames(acctTransferDetails, false);
      System.out.println("dbColumns = =" + dbColumns);
   }
   catch (IllegalAccessException e)
   {
      e.printStackTrace();
   }

   List<DCTemplateMapping> dcTemplateMappingList=dcTemplateDetails.getDcTemplateMappings().get("Client");
   getTabObject(dcTemplateMappingList, dbColumns, textboxLst, checkboxeLst, radioGroupLst, listboxLst, acctTransferDetails);


   if(textboxLst.size()>0){tabs.setTextTabs(textboxLst);}
   if(checkboxeLst.size()>0){tabs.setCheckboxTabs(checkboxeLst);}
   if(radioGroupLst.size()>0){tabs.setRadioGroupTabs(radioGroupLst);}
   if(listboxLst.size()>0){tabs.setListTabs(listboxLst);}

   if(signerLst.size()>0){
      Iterator<Signer> signerItr=signerLst.iterator();
      while(signerItr.hasNext()){
         Signer signer=(Signer)signerItr.next();
         signer.setTabs(tabs);
      }
      recipients=new Recipients();
      recipients.setSigners(signerLst);
      recipients.setCarbonCopies(new ArrayList<CarbonCopy>());
      recipients.getCarbonCopies().add(getCarbonCopyEmail());
   }
   return recipients;
}

   public Recipients getRecipientsElecFundTransfer(DCTemplateDetails dcTemplateDetails, AcctDetails acctDetails, ElecFundTransferDetails elecFundTransferDetails)
   {
      System.out.println("*****************************************************");
      System.out.println("DCUtility.getRecipientsAcctCreation");
      System.out.println("*****************************************************");

      List<String> dbColumns=null;
      List<Signer> signerLst=new ArrayList<>();

      Tabs tabs = new Tabs();
      List<Text> textboxLst=new ArrayList<>();
      List<Checkbox> checkboxeLst=new ArrayList<>();
      List<RadioGroup> radioGroupLst=new ArrayList<>();
      List<com.docusign.esign.model.List> listboxLst=new ArrayList<>();
      Recipients recipients=null;

      signerLst=getSigners(dcTemplateDetails, acctDetails, acctDetails.getAcctOwnerDetails(), textboxLst, checkboxeLst, radioGroupLst, listboxLst);
      try
      {
         dbColumns=getFieldNames(elecFundTransferDetails, false);
         System.out.println("dbColumns = =" + dbColumns);
      }
      catch (IllegalAccessException e)
      {
         e.printStackTrace();
      }

      List<DCTemplateMapping> dcTemplateMappingList=dcTemplateDetails.getDcTemplateMappings().get("Client");
      getTabObject(dcTemplateMappingList, dbColumns, textboxLst, checkboxeLst, radioGroupLst, listboxLst, elecFundTransferDetails);


      if(textboxLst.size()>0){tabs.setTextTabs(textboxLst);}
      if(checkboxeLst.size()>0){tabs.setCheckboxTabs(checkboxeLst);}
      if(radioGroupLst.size()>0){tabs.setRadioGroupTabs(radioGroupLst);}
      if(listboxLst.size()>0){tabs.setListTabs(listboxLst);}

      if(signerLst.size()>0){
         Iterator<Signer> signerItr=signerLst.iterator();
         while(signerItr.hasNext()){
            Signer signer=(Signer)signerItr.next();
            signer.setTabs(tabs);
         }
         recipients=new Recipients();
         recipients.setSigners(signerLst);
         recipients.setCarbonCopies(new ArrayList<CarbonCopy>());
         recipients.getCarbonCopies().add(getCarbonCopyEmail());
      }
      return recipients;
   }

   public Recipients getRecipientsAcctCreation(DCTemplateDetails dcTemplateDetails, AcctDetails acctDetails, List<AcctOwnerDetails> acctOwnerDetails)
   {
      System.out.println("*****************************************************");
      System.out.println("DCUtility.getRecipientsAcctCreation");
      System.out.println("*****************************************************");

      List<String> dbColumns=null;
      List<Signer> signerLst=new ArrayList<>();

      Tabs tabs = new Tabs();
      List<Text> textboxLst=new ArrayList<>();
      List<Checkbox> checkboxeLst=new ArrayList<>();
      List<RadioGroup> radioGroupLst=new ArrayList<>();
      List<com.docusign.esign.model.List> listboxLst=new ArrayList<>();
      Recipients recipients=null;

      signerLst=getSigners(dcTemplateDetails,acctDetails,acctOwnerDetails,textboxLst,checkboxeLst,radioGroupLst,listboxLst);
         if(acctDetails.getBenefiaciaryDetails()==null || acctDetails.getBenefiaciaryDetails().size()<=0){

         }else{
         Iterator<BenefiaciaryDetails> itr1 = acctDetails.getBenefiaciaryDetails().iterator();
            int iterCount=1;
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
            getTabObject(dcTemplateMappingList, dbColumns, textboxLst, checkboxeLst, radioGroupLst, listboxLst, beneDetails, ""+iterCount, true);
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


      if(textboxLst.size()>0){tabs.setTextTabs(textboxLst);}
      if(checkboxeLst.size()>0){tabs.setCheckboxTabs(checkboxeLst);}
      if(radioGroupLst.size()>0){tabs.setRadioGroupTabs(radioGroupLst);}
      if(listboxLst.size()>0){tabs.setListTabs(listboxLst);}

      if(signerLst.size()>0){
         Iterator<Signer> signerItr=signerLst.iterator();
         while(signerItr.hasNext()){
            Signer signer=(Signer)signerItr.next();
            signer.setTabs(tabs);
         }
         recipients=new Recipients();
         recipients.setSigners(signerLst);
         recipients.setCarbonCopies(new ArrayList<CarbonCopy>());
         recipients.getCarbonCopies().add(getCarbonCopyEmail());
      }
      return recipients;
   }

   public Recipients getRecipientsMoveMoney(DCTemplateDetails dcTemplateDetails, AcctDetails acctDetails, List<AcctOwnerDetails> acctOwnerDetails, MoveMoneyDetails moveMoneyDetails,  Object dataObject)
   {
      System.out.println("*****************************************************");
      System.out.println("DCUtility.getRecipientsAcctCreation");
      System.out.println("*****************************************************");

      List<String> dbColumns=null;
      List<Signer> signerLst=new ArrayList<>();

      Tabs tabs = new Tabs();
      List<Text> textboxLst=new ArrayList<>();
      List<Checkbox> checkboxeLst=new ArrayList<>();
      List<RadioGroup> radioGroupLst=new ArrayList<>();
      List<com.docusign.esign.model.List> listboxLst=new ArrayList<>();
      Recipients recipients=null;

      signerLst=getSigners(dcTemplateDetails,acctDetails,acctOwnerDetails,textboxLst,checkboxeLst,radioGroupLst,listboxLst);

      if( (dataObject!=null && ((List)dataObject).size()>0) && ((List)dataObject).get(0) instanceof MMAchBankDetails){
         Iterator<MMAchBankDetails> itr1 = ((List<MMAchBankDetails>)dataObject).iterator();
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

      if(textboxLst.size()>0){tabs.setTextTabs(textboxLst);}
      if(checkboxeLst.size()>0){tabs.setCheckboxTabs(checkboxeLst);}
      if(radioGroupLst.size()>0){tabs.setRadioGroupTabs(radioGroupLst);}
      if(listboxLst.size()>0){tabs.setListTabs(listboxLst);}

      if(signerLst.size()>0){
         Iterator<Signer> signerItr=signerLst.iterator();
         while(signerItr.hasNext()){
            Signer signer=(Signer)signerItr.next();
            signer.setTabs(tabs);
         }
         recipients=new Recipients();
         recipients.setSigners(signerLst);
         recipients.setCarbonCopies(new ArrayList<CarbonCopy>());
         recipients.getCarbonCopies().add(getCarbonCopyEmail());
      }
      return recipients;
   }

   public Recipients getRecipientsMoveMoney(DCTemplateDetails dcTemplateDetails, AcctDetails acctDetails, List<AcctOwnerDetails> acctOwnerDetails, List<MoveMoneyDetails> moveMoneyDetailsLst)
   {
      System.out.println("*****************************************************");
      System.out.println("DCUtility.getRecipientsAcctCreation");
      System.out.println("*****************************************************");

      List<MMAchBankDetails> mmAchBankDetails=null;
      List<MMInternalTransferDetails> mmInternalTransferDetails=null;
      List<MMFedwireAcctDetails> mmFedwireAcctDetails=null;

      Recipients recipients=null;
try{
      List<String> dbColumns=null;
      List<Signer> signerLst=new ArrayList<>();

      Tabs tabs = new Tabs();
      List<Text> textboxLst=new ArrayList<>();
      List<Checkbox> checkboxeLst=new ArrayList<>();
      List<RadioGroup> radioGroupLst=new ArrayList<>();
      List<com.docusign.esign.model.List> listboxLst=new ArrayList<>();

      signerLst=getSigners(dcTemplateDetails,acctDetails,acctOwnerDetails,textboxLst,checkboxeLst,radioGroupLst,listboxLst);

      List<DCTemplateMapping> dcTemplateMappingList = dcTemplateDetails.getDcTemplateMappings().get("Client");

      Iterator<MoveMoneyDetails> itr = moveMoneyDetailsLst.iterator();
      while (itr.hasNext())
      {
         MoveMoneyDetails moveMoneyDetails = (MoveMoneyDetails) itr.next();
         dbColumns = null;
         try
         {
            dbColumns = getFieldNames(moveMoneyDetails, false);
         }
         catch (IllegalAccessException e)
         {
            e.printStackTrace();
         }
         getTabObject(dcTemplateMappingList, dbColumns, textboxLst, checkboxeLst, radioGroupLst, listboxLst, moveMoneyDetails);

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
                }
                catch (IllegalAccessException e)
                {
                   e.printStackTrace();
                }

                getTabObject(dcTemplateMappingList, dbColumns, textboxLst, checkboxeLst, radioGroupLst, listboxLst, achBankDetails);
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
                }
                catch (IllegalAccessException e)
                {
                   e.printStackTrace();
                }

                getTabObject(dcTemplateMappingList, dbColumns, textboxLst, checkboxeLst, radioGroupLst, listboxLst, achBankDetails);
             }
          }
          else if (moveMoneyDetails.getPayMethod().equalsIgnoreCase("3rdPartyInternal"))
          {
             mmInternalTransferDetails = tdDaoLayer.getMMInternalTransferDetails(moveMoneyDetails.getAcctnum(), moveMoneyDetails.getMoveMoneyPayMethId());
          }


      }

//      if( (dataObject!=null && ((List)dataObject).size()>0) && ((List)dataObject).get(0) instanceof MMAchBankDetails){
//      }

      if(textboxLst.size()>0){tabs.setTextTabs(textboxLst);}
      if(checkboxeLst.size()>0){tabs.setCheckboxTabs(checkboxeLst);}
      if(radioGroupLst.size()>0){tabs.setRadioGroupTabs(radioGroupLst);}
      if(listboxLst.size()>0){tabs.setListTabs(listboxLst);}

      if(signerLst.size()>0){
         Iterator<Signer> signerItr=signerLst.iterator();
         while(signerItr.hasNext()){
            Signer signer=(Signer)signerItr.next();
            signer.setTabs(tabs);
         }
         recipients=new Recipients();
         recipients.setSigners(signerLst);
         recipients.setCarbonCopies(new ArrayList<CarbonCopy>());
         recipients.getCarbonCopies().add(getCarbonCopyEmail());
      }
}
catch (Exception e)
{
   e.printStackTrace();
}
      return recipients;
   }

   public InlineTemplate getInlineTemplateAgreements(Map<String,DCDocumentDetails> dcDocumentDetails, AcctDetails acctDetails, List<AcctOwnerDetails> acctOwnerDetails)
   {
      System.out.println("*****************************************************");
      System.out.println("DCUtility.getInlineTemplateAgreements");
      System.out.println("*****************************************************");

      List<String> dbColumns=null;
      InlineTemplate inlineTemplate=null;
      List<Signer> signerLst=new ArrayList<>();

      Tabs tabs = new Tabs();
      List<Text> textboxLst=new ArrayList<>();
      List<SignHere> signHereLst=new ArrayList<>();
      Recipients recipients=null;

      int signerRecipientId=1;
      Iterator<AcctOwnerDetails> itr = acctOwnerDetails.iterator();
      while (itr.hasNext())
      {
         AcctOwnerDetails acctOwner = (AcctOwnerDetails) itr.next();
         Signer signer=null;
         if(acctOwner.getOwnership().equalsIgnoreCase("Client")){
            signer=new Signer();
            signer.setEmail(acctOwner.getEmailAddress());
            signer.setName(acctOwner.getFirstName()+" "+acctOwner.getLastName());
            signer.roleName(acctOwner.getOwnership());
            signer.setRecipientId(""+signerRecipientId);

         }else if(acctOwner.getOwnership().equalsIgnoreCase("Joint")){
               signer=new Signer();
               signer.setEmail(acctOwner.getEmailAddress());
               signer.setName(acctOwner.getFirstName());
               signer.roleName(acctOwner.getOwnership());
               signer.setRecipientId(""+signerRecipientId);
         }
         if(signer!=null)
         {
            signerLst.add(signer);
            signerRecipientId ++;
         }

      }
      final String SignTest1File = ServiceParameters.getConfigProperty(Constant.SERVICES.DOCUSIGN_SERVICES.toString(),Constant.DOCUSIGN_SERVICES.DOCUSIGN.toString(),"DOC_PATH");//"[PATH/TO/DOCUMENT/TEST.PDF]";

//      ServiceParameters.getConfigProperty(Constant.SERVICES.EMAIL_SERVICE.toString(),Constant.EMAIL_SERVICE.INVESSENCE_GMAIL.toString(),"HOST");
      List<Document> docs = new ArrayList<Document>();

      List<DCDocumentMapping> dcDocumentDetailsList=null;

      int documentId=1;
      Iterator<Map.Entry<String,DCDocumentDetails>> docItr = dcDocumentDetails.entrySet().iterator();
      while (docItr.hasNext())
      {
         Map.Entry<String, DCDocumentDetails> dcDocumentDetail1 = (Map.Entry<String, DCDocumentDetails>) docItr.next();
         DCDocumentDetails dcDocumentDetail=dcDocumentDetail1.getValue();
         System.out.println(dcDocumentDetail);

         byte[] fileBytes = null;

         try
         {
            // read file from a local directory
            Path path = Paths.get(SignTest1File+"//"+dcDocumentDetail.getFileName());
            fileBytes = Files.readAllBytes(path);
         }
         catch (IOException ioExcp)
         {
            // handle error
            System.out.println("Exception: " + ioExcp);
         }

         Document doc = new Document();
         String base64Doc = DatatypeConverter.printBase64Binary(fileBytes);//Base64.getEncoder().encodeToString(fileBytes);
         doc.setDocumentBase64(base64Doc);
         doc.setName(dcDocumentDetail.getDocName());    // can be different from actual file name
         doc.setDocumentId(""+documentId);


         if(dcDocumentDetail.getEditable().equalsIgnoreCase("Y"))
         {
            try
            {
               dbColumns=getFieldNames(acctDetails, false);
               System.out.println("dbColumns = =" + dbColumns);
               dcDocumentDetailsList = dcDocumentDetail.getDcDocumentMappings().get("Client");
               getTabObject(dcDocumentDetailsList,dbColumns,textboxLst,signHereLst,acctDetails, ""+documentId);
            }
            catch (IllegalAccessException e)
            {
               e.printStackTrace();
            }



            Iterator<AcctOwnerDetails> itr2 = acctOwnerDetails.iterator();
            while (itr2.hasNext())
            {
               AcctOwnerDetails acctOwner = (AcctOwnerDetails) itr2.next();
               dbColumns=null;
               try
               {
                  dbColumns=getFieldNames(acctOwner, false);
                  dcDocumentDetailsList = dcDocumentDetail.getDcDocumentMappings().get(acctOwner.getOwnership());
                  getTabObject(dcDocumentDetailsList,dbColumns,textboxLst,signHereLst,acctOwner, ""+documentId);
               }
               catch (IllegalAccessException e)
               {
                  e.printStackTrace();
               }
            }
         }
         if(doc!=null)
         {
            docs.add(doc);
            documentId ++;
         }
      }

      if(textboxLst.size()>0){tabs.setTextTabs(textboxLst);}
      if(signHereLst.size()>0){tabs.setSignHereTabs(signHereLst);}

      if(signerLst.size()>0){
         Iterator<Signer> signerItr=signerLst.iterator();
         while(signerItr.hasNext()){
            Signer signer=(Signer)signerItr.next();
            signer.setTabs(tabs);
         }
         recipients=new Recipients();
         recipients.setSigners(signerLst);

         recipients.setCarbonCopies(new ArrayList<CarbonCopy>());
         recipients.getCarbonCopies().add(getCarbonCopyEmail());

         inlineTemplate=new InlineTemplate();
         inlineTemplate.setSequence("1");
         inlineTemplate.setRecipients(recipients);
         inlineTemplate.setDocuments(docs);
         getJsonObjectToString(inlineTemplate);
      }

      return inlineTemplate;
   }

   private List<Signer> getSigners(DCTemplateDetails dcTemplateDetails, AcctDetails acctDetails, List<AcctOwnerDetails> acctOwnerDetails, List<Text> textboxLst, List<Checkbox> checkboxeLst,List<RadioGroup> radioGroupLst,List<com.docusign.esign.model.List> listboxLst){
      List<String> dbColumns=null;
      List<Signer> signerLst=new ArrayList<>();
      try
      {
         dbColumns=getFieldNames(acctDetails, false);
         System.out.println("dbColumns = =" + dbColumns);
      }
      catch (IllegalAccessException e)
      {
         e.printStackTrace();
      }

      List<DCTemplateMapping> dcTemplateMappingList=dcTemplateDetails.getDcTemplateMappings().get("Client");
      getTabObject(dcTemplateMappingList, dbColumns, textboxLst, checkboxeLst, radioGroupLst, listboxLst, acctDetails);


      int signerRecipientId=1;
      Iterator<AcctOwnerDetails> itr = acctDetails.getAcctOwnerDetails().iterator();
      while (itr.hasNext())
      {
         AcctOwnerDetails acctOwner = (AcctOwnerDetails) itr.next();
         dbColumns=null;
         try
         {
            dbColumns=getFieldNames(acctOwner, false);
         }
         catch (IllegalAccessException e)
         {
            e.printStackTrace();
         }
         Signer signer=null;

         if(acctOwner.getOwnership().equalsIgnoreCase("Client")){
            System.out.println("Client");
            signer=new Signer();
            signer.setEmail(acctOwner.getEmailAddress());
            signer.setName(acctOwner.getFirstName()+" "+acctOwner.getLastName());
            signer.roleName(acctOwner.getOwnership());
            signer.setRecipientId(""+signerRecipientId);
            if(dcTemplateDetails.getAuthRequired().equalsIgnoreCase("Y")){
              signer.setIdCheckConfigurationName(ServiceParameters.getConfigProperty(Constant.SERVICES.DOCUSIGN_SERVICES.toString(),Constant.DOCUSIGN_SERVICES.DOCUSIGN.toString(),"ID_CHECK_CONF_NAME"));
               signer.setIdCheckInformationInput(getKBAInputs(acctOwner));
            }
            dcTemplateMappingList=dcTemplateDetails.getDcTemplateMappings().get("Client");
            System.out.println("dcDocumentMappingList = " + dcTemplateMappingList);
            getTabObject(dcTemplateMappingList, dbColumns, textboxLst, checkboxeLst, radioGroupLst, listboxLst, acctOwner);

         }else if(acctOwner.getOwnership().equalsIgnoreCase("Joint")){
            System.out.println("Joint");
            dcTemplateMappingList=dcTemplateDetails.getDcTemplateMappings().get("Joint");
            if(dcTemplateMappingList==null || dcTemplateMappingList.size()<=0){

            }else{
               signer=new Signer();
               signer.setEmail(acctOwner.getEmailAddress());
               signer.setName(acctOwner.getFirstName());
               signer.roleName(acctOwner.getOwnership());
               signer.setRecipientId(""+signerRecipientId);
               if(dcTemplateDetails.getAuthRequired().equalsIgnoreCase("Y")){
                  signer.setIdCheckConfigurationName(ServiceParameters.getConfigProperty(Constant.SERVICES.DOCUSIGN_SERVICES.toString(),Constant.DOCUSIGN_SERVICES.DOCUSIGN.toString(),"ID_CHECK_CONF_NAME"));
                  signer.setIdCheckInformationInput(getKBAInputs(acctOwner));
               }
               getTabObject(dcTemplateMappingList, dbColumns, textboxLst, checkboxeLst, radioGroupLst, listboxLst, acctOwner);
            }
         }
         if(signer!=null)
         {
            signerLst.add(signer);
         }
         signerRecipientId ++;
      }

      return signerLst;
   }
   private void getTabObject(List<DCTemplateMapping> dcTemplateMappingList, List<String> dbColumns, List<Text> textboxLst, List<Checkbox> checkboxeLst,List<RadioGroup> radioGroupLst,List<com.docusign.esign.model.List> listboxLst, Object dataObject){
     Iterator<DCTemplateMapping> itr1=dcTemplateMappingList.iterator();
      while (itr1.hasNext()){
         DCTemplateMapping dctemplate=(DCTemplateMapping)itr1.next();
         if(dbColumns.contains(dctemplate.getDbColumn())){

            if(dctemplate.getTab().equalsIgnoreCase("Textbox")){
               Text text=getText(dctemplate, dataObject);
               if(text==null){}else{textboxLst.add(text);}
            }else if(dctemplate.getTab().equalsIgnoreCase("Checkbox")){
               Checkbox text=getCheckbox(dctemplate, dataObject);
               if(text==null){}else{checkboxeLst.add(text);}
            }else if(dctemplate.getTab().equalsIgnoreCase("Radiobox")){
               RadioGroup text=getRadioGroup(dctemplate, dataObject);
               if(text==null){}else{radioGroupLst.add(text);}
            }else if(dctemplate.getTab().equalsIgnoreCase("Listbox")){
               com.docusign.esign.model.List text=getList(dctemplate, dataObject);
               if(text==null){}else{listboxLst.add(text);}
            }
         }
      }

   }
   private void getTabObject(List<DCDocumentMapping> dcDocumentMappingList, List<String> dbColumns, List<Text> textboxLst, List<SignHere> signHereLst, Object dataObject, String documentId){
      Iterator<DCDocumentMapping> itr1= dcDocumentMappingList.iterator();
      while (itr1.hasNext()){
         DCDocumentMapping dcDocumentMapping=(DCDocumentMapping)itr1.next();
         if(dbColumns.contains(dcDocumentMapping.getDbColumn())){

            if(dcDocumentMapping.getTab().equalsIgnoreCase("Textbox")){
               Text text=getText(dcDocumentMapping, dataObject, documentId,null);
               if(text==null){}else{textboxLst.add(text);}
            }
         }else if(dcDocumentMapping.getTab().equalsIgnoreCase("SignHere")){
            SignHere text=getSignHere(dcDocumentMapping, dataObject, documentId);
            if(text==null){}else{
               signHereLst.add(text);}
         }else if(dcDocumentMapping.getDbColumn().equalsIgnoreCase("DATE")){
            Text text=getText(dcDocumentMapping, dataObject, documentId,dcDocumentMapping.getDbColumn());
            if(text==null){}else{textboxLst.add(text);}
         }
      }
   }
   private void getTabObject(List<DCTemplateMapping> dcTemplateMappingList, List<String> dbColumns, List<Text> textboxLst,
                             List<Checkbox> checkboxeLst,List<RadioGroup> radioGroupLst,List<com.docusign.esign.model.List> listboxLst, Object dataObject, String addLableVal, boolean isPostfix){
      //0 preFix, 1 postFix
      Iterator<DCTemplateMapping> itr1=dcTemplateMappingList.iterator();
      while (itr1.hasNext()){
         DCTemplateMapping dctemplate=(DCTemplateMapping)itr1.next();
         System.out.println(dctemplate.getDbColumn() +" : "+dctemplate.getDbColumn()+addLableVal+" : "+addLableVal+dctemplate.getDbColumn());

         System.out.println(dbColumns.contains(dctemplate.getDbColumn()) + "(isPostfix==true?"+ dcTemplateMappingList.contains(dctemplate.getDbColumn()+addLableVal)+" : "+dcTemplateMappingList.contains(addLableVal+dctemplate.getDbColumn()));
         //if(dbColumns.contains(dctemplate.getDbColumn()) && (isPostfix==true? dcTemplateMappingList.contains(dctemplate.getDbColumn()+addLableVal):dcTemplateMappingList.contains(addLableVal+dctemplate.getDbColumn()))){
         if(dbColumns.contains(dctemplate.getDbColumn()) && (isPostfix==true? dctemplate.getLable().equalsIgnoreCase(dctemplate.getDbColumn()+addLableVal):dctemplate.getLable().equalsIgnoreCase(addLableVal+dctemplate.getDbColumn()))){

            if(dctemplate.getTab().equalsIgnoreCase("Textbox")){
               Text text=getText(dctemplate, dataObject);
               if(text==null){}else{textboxLst.add(text);}
            }else if(dctemplate.getTab().equalsIgnoreCase("Checkbox")){
               Checkbox text=getCheckbox(dctemplate, dataObject);
               if(text==null){}else{checkboxeLst.add(text);}
            }else if(dctemplate.getTab().equalsIgnoreCase("Radiobox")){
               RadioGroup text=getRadioGroup(dctemplate, dataObject);
               if(text==null){}else{radioGroupLst.add(text);}
            }else if(dctemplate.getTab().equalsIgnoreCase("Listbox")){
               com.docusign.esign.model.List text=getList(dctemplate, dataObject);
               if(text==null){}else{listboxLst.add(text);}
            }
         }
      }

   }

   private IdCheckInformationInput getKBAInputs(AcctOwnerDetails acctOwnerDetails){
      IdCheckInformationInput idCheckInformationInput=new IdCheckInformationInput();
      AddressInformationInput addressInformationInput=new AddressInformationInput();

      AddressInformation addressInformation=new AddressInformation();
      addressInformation.setStreet1(acctOwnerDetails.getPhysicalAddressStreet());//("6948 Shepherd");
      addressInformation.setCity(acctOwnerDetails.getPhysicalAddressCity());//("Russelville");
      addressInformation.setState(acctOwnerDetails.getPhysicalAddressState());//("AR");
      addressInformation.zip(acctOwnerDetails.getPhysicalAddressZipCode());//("72801");
      addressInformationInput.setAddressInformation(addressInformation);

      Ssn4InformationInput ssn4InformationInput=new Ssn4InformationInput();
      ssn4InformationInput.setSsn4(acctOwnerDetails.getSsn()==null || acctOwnerDetails.getSsn().equals("")?"":acctOwnerDetails.getSsn().substring(acctOwnerDetails.getSsn().length()-4,acctOwnerDetails.getSsn().length()));//("6280");//(elecFundTransferDetails.getSsn());//("7438");

      DobInformationInput dobInformationInput=new DobInformationInput();
      dobInformationInput.setDateOfBirth(acctOwnerDetails.getDob());//("09/11/1983");

      idCheckInformationInput.setAddressInformationInput(addressInformationInput);
      idCheckInformationInput.setSsn4InformationInput(ssn4InformationInput);
      idCheckInformationInput.setDobInformationInput(dobInformationInput);

     return idCheckInformationInput;
   }


   private Text getText (DCDocumentMapping documentMapping, Object dataObject, String documentId,String identifier){
      Text text=null;
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
         if (identifier == null || identifier.equals("")){
            text.setValue(getInstanceValue(dataObject, documentMapping.getDbColumn()).toString());
         }else if(identifier.equals("DATE")){
            text.setValue(""+new Date());
         }
      }
      catch (NoSuchFieldException e)
      {
         System.out.println("dctemplate.getLable() :"+documentMapping.getLable());
         e.printStackTrace();
      }
      catch (ClassNotFoundException e)
      {
         System.out.println("dctemplate.getLable() :"+documentMapping.getLable());
         e.printStackTrace();
      }
      catch (IllegalAccessException e)
      {
         System.out.println("dctemplate.getLable() :"+documentMapping.getLable());
         e.printStackTrace();
      }
      return text;
   }
   private SignHere getSignHere (DCDocumentMapping documentMapping, Object dataObject, String documentId){
      SignHere signHere=null;
      try
      {
         signHere = new SignHere();
         signHere.setTabLabel(documentMapping.getLable());
         signHere.setDocumentId("" + documentId);
         signHere.setPageNumber(documentMapping.getPageNumber());
         //signHere.setRecipientId("1");
         signHere.setXPosition(documentMapping.getxPosition());
         signHere.setYPosition(documentMapping.getyPosition());

      }catch (Exception e){

      }
      return signHere;
   }

   private Text getText (DCTemplateMapping dctemplate, Object dataObject){
      Text text=null;
      try
      {
         text=new Text();

      text.setTabLabel(dctemplate.getLable());
      text.setValue(getInstanceValue(dataObject, dctemplate.getDbColumn()).toString());
   }
   catch (NoSuchFieldException e)
   {
      System.out.println("dctemplate.getLable() :"+dctemplate.getLable());
      e.printStackTrace();
   }
   catch (ClassNotFoundException e)
   {
      System.out.println("dctemplate.getLable() :"+dctemplate.getLable());
      e.printStackTrace();
   }
   catch (IllegalAccessException e)
   {
      System.out.println("dctemplate.getLable() :"+dctemplate.getLable());
      e.printStackTrace();
   }
   return text;
}
   private Checkbox getCheckbox (DCTemplateMapping dctemplate, Object dataObject){

   Checkbox checkbox=null;
      try
      {
         System.out.println("$%$%$%$%$%$%$%$");
         if(getInstanceValue(dataObject, dctemplate.getDbColumn()).toString().equalsIgnoreCase("Y")){
            checkbox=new Checkbox();
            checkbox.setTabLabel(dctemplate.getLable());
            checkbox.setSelected("true");
         }else{
            checkbox=new Checkbox();
            checkbox.setTabLabel(getInstanceValue(dataObject, dctemplate.getDbColumn()).toString());
            checkbox.setSelected("true");
         }
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
   return checkbox;
   }
   private RadioGroup getRadioGroup (DCTemplateMapping dctemplate, Object dataObject){


   RadioGroup radioGroup =null;
   try
   {
      radioGroup= new RadioGroup();
      radioGroup.setGroupName(dctemplate.getLable());
      getInstanceValue(dataObject, dctemplate.getDbColumn()).toString();
      radioGroup.setRadios(new ArrayList<Radio>());
      						Radio radio=new Radio();
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

   return radioGroup;
   }
   private com.docusign.esign.model.List getList (DCTemplateMapping dctemplate, Object dataObject){

      com.docusign.esign.model.List list=null;
      try
      {
         list=new com.docusign.esign.model.List();
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

   public static Object getInstanceValue(final Object classInstance, final String fieldName) throws SecurityException,     NoSuchFieldException,ClassNotFoundException, IllegalArgumentException, IllegalAccessException {

      // Get the private field
      final Field field = classInstance.getClass().getDeclaredField(fieldName);
      // Allow modification on the field
      field.setAccessible(true);
      // Return the Obect corresponding to the field
      return field.get(classInstance);

   }

   public static List<String> getFieldNames(final Object obj, boolean publicOnly)
      throws IllegalArgumentException,IllegalAccessException
   {
      StringBuilder sb=new StringBuilder();
      Class<? extends Object> c1 = obj.getClass();
      List<String> lst = new ArrayList<String>();
      Field[] fields = c1.getDeclaredFields();
      for (int i = 0; i < fields.length; i++) {
         String name = fields[i].getName();
         if (publicOnly) {
            if(Modifier.isPublic(fields[i].getModifiers())) {
               Object value = fields[i].get(obj);
               if(value==null)
               {
                  sb.append(name +", ");
               }else{
                  lst.add(name);}
            }
         }
         else {
            fields[i].setAccessible(true);
            Object value = fields[i].get(obj);
            if(value==null)
            {
               sb.append(name +", ");
            }else{
            lst.add(name);}
         }
      }
      System.out.println("Avoided properties of "+c1+" due to empty or null value : (" + sb + ")");
      System.out.println("Properties for further process = " + lst);
      return lst;
   }

   public static Map<String, Object> getFieldNamesAndValues(final Object obj, boolean publicOnly)
      throws IllegalArgumentException,IllegalAccessException
   {
      Class<? extends Object> c1 = obj.getClass();
      Map<String, Object> map = new HashMap<String, Object>();
      Field[] fields = c1.getDeclaredFields();
      for (int i = 0; i < fields.length; i++) {
         String name = fields[i].getName();
         if (publicOnly) {
            if(Modifier.isPublic(fields[i].getModifiers())) {
               Object value = fields[i].get(obj);
               map.put(name, value);
            }
         }
         else {
            fields[i].setAccessible(true);
            Object value = fields[i].get(obj);
            map.put(name, value);
         }
      }
      return map;
   }

   public String getJsonObjectToString(Object object){
String jsonObject=null;
      try {
         ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

         mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
         mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
         jsonObject=mapper.writeValueAsString(object);
         System.out.println("PrintJsonObject :" +jsonObject);
         return jsonObject;
      } catch (JsonProcessingException e) {
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