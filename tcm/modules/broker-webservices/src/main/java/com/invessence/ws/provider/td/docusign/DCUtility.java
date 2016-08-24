package com.invessence.ws.provider.td.docusign;

import java.io.IOException;
import java.io.IOException;
import java.lang.reflect.*;
import java.util.*;
import java.util.List;

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
import com.invessence.ws.provider.td.bean.*;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 *
 * Created by abhangp on 8/12/2016.
 */
@Component
public class DCUtility
{

   public EnvelopeSummary createEnvelope (EnvelopeDefinition envDef)
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
      String UserName = "prashant@invessence.com";//"[EMAIL]";
      String Password = "Inv3ss3nc3!";//"[PASSWORD]";

      // TODO: Enter your Integrator Key (aka API key), created through your developer sandbox preferences
      String IntegratorKey = "TDAM-d7feb45c-e88d-4c20-b5bd-1dcd9a9d6f56";//"[INTEGRATOR_KEY]";

      // for production environment update to "www.docusign.net/restapi"
      String BaseUrl = "https://demo.docusign.net/restapi";

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
//      try
//      {
//         // login call available off the AuthenticationApi
//         AuthenticationApi authApi = new AuthenticationApi();
//
//         // login has some optional parameters we can set
//         AuthenticationApi.LoginOptions loginOps = authApi.new LoginOptions();
//         loginOps.setApiPassword("true");
//         loginOps.setIncludeAccountIdGuid("true");
//         LoginInformation loginInfo = authApi.login(loginOps);
//
//         // note that a given user may be a member of multiple accounts
//         loginAccounts = loginInfo.getLoginAccounts();
//
//         System.out.println("LoginInformation: " + loginAccounts);
//      }
//      catch (com.docusign.esign.client.ApiException ex)
//      {
//         System.out.println("Exception: " + ex);
//      }
//
//      //===============================================================================
//
//
//      try
//      {
//         // use the |accountId| we retrieved through the Login API to create the Envelope
//         String accountId = loginAccounts.get(0).getAccountId();
//
//         // instantiate a new EnvelopesApi object
//         EnvelopesApi envelopesApi = new EnvelopesApi();
//
//         // call the createEnvelope() API
//         // envDef.setCustomFields(customFields);
//         envelopeSummary = envelopesApi.createEnvelope(accountId, envDef);
//
//         System.out.println("EnvelopeSummary: " + envelopeSummary);
//      }
//      catch (com.docusign.esign.client.ApiException ex)
//      {
//         System.out.println("Exception: " + ex);
//      }

      return envelopeSummary;
   }



   public ServerTemplate getServerTemplate (String sequence, DCTemplateDetails dcTemplateDetails){

      ServerTemplate serverTemplate = new ServerTemplate();
      serverTemplate.setTemplateId(dcTemplateDetails.getTempId());
      serverTemplate.setSequence(sequence);

      return serverTemplate;
   }

   public InlineTemplate getInlineTemplate (String sequence, DCTemplateDetails dcTemplateDetails,AcctDetails acctDetails, List<AcctOwnerDetails> acctOwnerDetails){

      InlineTemplate inlineTemplate = new InlineTemplate();
      inlineTemplate.setRecipients(getSigner(dcTemplateDetails,acctDetails,acctOwnerDetails));
      inlineTemplate.setSequence(sequence);

      return inlineTemplate;
   }

   public Recipients getSigner(DCTemplateDetails dcTemplateDetails,AcctDetails acctDetails, List<AcctOwnerDetails> acctOwnerDetails)
   {
      System.out.println("*****************************************************");
      System.out.println("DCUtility.getSigner");
      System.out.println("*****************************************************");

//      try
//      {
//         System.out.println(getFieldNames(acctDetails, false));
//      }
//      catch (IllegalAccessException e)
//      {
//         e.printStackTrace();
//      }

      List<String> dbColumns=null;
      List<Signer> signerLst=new ArrayList<>();

      Tabs tabs = new Tabs();
      List<Text> textboxLst=new ArrayList<>();
      List<Checkbox> checkboxeLst=new ArrayList<>();
      List<RadioGroup> radioGroupLst=new ArrayList<>();
      List<com.docusign.esign.model.List> listboxLst=new ArrayList<>();
      Recipients recipients=null;

      List<DCTemplateMapping> dcTemplateMappingList=dcTemplateDetails.getDcTemplateMappings().get("Client");
      Iterator <DCTemplateMapping> itr1=dcTemplateMappingList.iterator();

      try
      {
         dbColumns=getFieldNames(acctDetails, false);
         System.out.println("dbColumns = =" + dbColumns);
      }
      catch (IllegalAccessException e)
      {
         e.printStackTrace();
      }
      while (itr1.hasNext()){
         DCTemplateMapping dctemplate=(DCTemplateMapping)itr1.next();
         System.out.println(dctemplate.getDbColumn());
         if(dbColumns.contains(dctemplate.getDbColumn())){

            if(dctemplate.getTab().equalsIgnoreCase("Textbox")){
               Text text=getText(dctemplate,acctDetails);
               if(text==null){}else{textboxLst.add(text);}
            }else if(dctemplate.getTab().equalsIgnoreCase("Checkbox")){
               Checkbox text=getCheckbox(dctemplate,acctDetails);
               if(text==null){}else{checkboxeLst.add(text);}
            }else if(dctemplate.getTab().equalsIgnoreCase("Radiobox")){
               RadioGroup text=getRadioGroup(dctemplate,acctDetails);
               if(text==null){}else{radioGroupLst.add(text);}
            }else if(dctemplate.getTab().equalsIgnoreCase("Listbox")){
               com.docusign.esign.model.List text=getList(dctemplate, acctDetails);
               if(text==null){}else{listboxLst.add(text);}
            }
         }
      }


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
         Signer signer=new Signer();
         signer.setEmail(acctOwner.getEmailAddress());
         signer.setName(acctOwner.getFirstName());
         signer.roleName(acctOwner.getOwnership());
         signer.setRecipientId(""+signerRecipientId);
         signer.setTabs(tabs);

         if(acctOwner.getOwnership().equalsIgnoreCase("Client")){
            signer.routingOrder("1");
            System.out.println("Client");
            dcTemplateMappingList=dcTemplateDetails.getDcTemplateMappings().get("Client");
            itr1=dcTemplateMappingList.iterator();
            while (itr1.hasNext()){
               DCTemplateMapping dctemplate=(DCTemplateMapping)itr1.next();
               if(dbColumns.contains(dctemplate.getDbColumn())){

                  if(dctemplate.getTab().equalsIgnoreCase("Textbox")){
                     Text text=getText(dctemplate,acctOwner);
                     if(text==null){}else{textboxLst.add(text);}
                  }else if(dctemplate.getTab().equalsIgnoreCase("Checkbox")){
                     Checkbox text=getCheckbox(dctemplate,acctOwner);
                     if(text==null){}else{checkboxeLst.add(text);}
                  }else if(dctemplate.getTab().equalsIgnoreCase("Radiobox")){
                     RadioGroup text=getRadioGroup(dctemplate,acctOwner);
                     if(text==null){}else{radioGroupLst.add(text);}
                  }else if(dctemplate.getTab().equalsIgnoreCase("Listbox")){
                     com.docusign.esign.model.List text=getList(dctemplate, acctOwner);
                     if(text==null){}else{listboxLst.add(text);}
                  }
               }
            }

         }else if(acctOwner.getOwnership().equalsIgnoreCase("Joint")){
            signer.routingOrder("2");
            System.out.println("Joint");
            dcTemplateMappingList=dcTemplateDetails.getDcTemplateMappings().get("Joint");
            itr1=dcTemplateMappingList.iterator();
            while (itr1.hasNext()){
               DCTemplateMapping dctemplate=(DCTemplateMapping)itr1.next();
               if(dbColumns.contains(dctemplate.getDbColumn())){

                  if(dctemplate.getTab().equalsIgnoreCase("Textbox")){
                     Text text=getText(dctemplate,acctOwner);
                     if(text==null){}else{textboxLst.add(text);}
                  }else if(dctemplate.getTab().equalsIgnoreCase("Checkbox")){
                     Checkbox text=getCheckbox(dctemplate,acctOwner);
                     if(text==null){}else{checkboxeLst.add(text);}
                  }else if(dctemplate.getTab().equalsIgnoreCase("Radiobox")){
                     RadioGroup text=getRadioGroup(dctemplate,acctOwner);
                     if(text==null){}else{radioGroupLst.add(text);}
                  }else if(dctemplate.getTab().equalsIgnoreCase("Listbox")){
                     com.docusign.esign.model.List text=getList(dctemplate, acctOwner);
                     if(text==null){}else{listboxLst.add(text);}
                  }
               }
            }
         }

         signerLst.add(signer);
         signerRecipientId ++;
      }
      if(textboxLst.size()>0){tabs.setTextTabs(textboxLst);}
      if(checkboxeLst.size()>0){tabs.setCheckboxTabs(checkboxeLst);}
      if(radioGroupLst.size()>0){tabs.setRadioGroupTabs(radioGroupLst);}
      if(listboxLst.size()>0){tabs.setListTabs(listboxLst);}

//      System.out.println("tabs :"+tabs);
      if(signerLst.size()>0){
         Iterator<Signer> signerItr=signerLst.iterator();
         while(signerItr.hasNext()){
            Signer signer=(Signer)signerItr.next();
            signer.setTabs(tabs);
         }
         recipients=new Recipients();
         recipients.setSigners(signerLst);
//         Agent agent=new Agent();
//         recipients.setAgents();
      }
//         Field[] fields = aClass.getFields();
//         System.out.println("fields = " + fields);
//         for(Field field : fields){
//            System.out.println(field.getName());
//         }
         //System.out.println("acctOwner = " + acctOwner);
//         Signer signer = new Signer();
//         signer.setEmail(acctOwnersDetails.getEmailAddress());
//         signer.setName(acctOwnersDetails.getFirstName());
//         signer.roleName(acctOwnersDetails.getOwnership());
//         signer.setRecipientId("1");



      //getTabs(dcTemplateDetails.getConfigFile(), dcTemplateDetails.getDcTemplateMappings());
      return recipients;
   }
   private Text getText (DCTemplateMapping dctemplate, Object acctOwner){
      Text text=null;
      try
      {
         text=new Text();

      text.setTabLabel(dctemplate.getLable());
      text.setValue(getInstanceValue(acctOwner,dctemplate.getDbColumn()).toString());
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
   return text;
}
   private Checkbox getCheckbox (DCTemplateMapping dctemplate, Object acctOwner){

   Checkbox checkbox=null;
      try
      {checkbox=new Checkbox();
   checkbox.setTabLabel(dctemplate.getLable());
   checkbox.setStatus(getInstanceValue(acctOwner,dctemplate.getDbColumn()).toString());
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
   private RadioGroup getRadioGroup (DCTemplateMapping dctemplate, Object acctOwner){


   RadioGroup radioGroup =null;
   try
   {
      radioGroup= new RadioGroup();
      getInstanceValue(acctOwner,dctemplate.getDbColumn()).toString();
      radioGroup.setRadios(new ArrayList<Radio>());
      						Radio radio=new Radio();
      						radio.setValue(getInstanceValue(acctOwner,dctemplate.getDbColumn()).toString());
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
   private com.docusign.esign.model.List getList (DCTemplateMapping dctemplate, Object acctOwner){

      com.docusign.esign.model.List list=null;
      try
      {
         list=new com.docusign.esign.model.List();
         list.setTabLabel(dctemplate.getLable());
         list.setValue(getInstanceValue(acctOwner,dctemplate.getDbColumn()).toString());
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

   public static Object getInstanceValue(final Object classInstance, final String fieldName) throws SecurityException,     NoSuchFieldException,ClassNotFoundException,          IllegalArgumentException, IllegalAccessException {

      // Get the private field
      final Field field = classInstance.getClass().getDeclaredField(fieldName);
      // Allow modification on the field
      field.setAccessible(true);
      // Return the Obect corresponding to the field
      return field.get(classInstance);

   }

   public Tabs getTabs (String jsonString, Map<String, DCTemplateMapping> mapping){
      //String jsonString="[{\"defaultRecipient\": \"false\",\"signInEachLocation\": \"false\",\"email\": \"\",\"accessCode\": \"\",\"requireIdLookup\": \"true\",\"idCheckConfigurationName\": \"ID Check $\",\"routingOrder\": \"10\",\"note\": \"\",\"roleName\": \"Client\",\"inheritEmailNotificationConfiguration\": \"false\",\"recipientType\": \"signer\",\"tabs\": {\"signHereTabs\": [{\"stampType\": \"signature\",\"tabLabel\": \"Signature 104\",\"scaleValue\": \"0.8\",\"optional\": \"false\"}],\"initialHereTabs\": [{\"tabLabel\": \"LPOA Initials\",\"scaleValue\": \"0.75\",\"optional\": \"false\"}, {\"tabLabel\": \"Man Fee Initials\",\"scaleValue\": \"0.75\",\"optional\": \"false\"}],\"dateSignedTabs\": [{\"value\": \"\",\"tabLabel\": \"Date Signed\"}],\"textTabs\": [{\"value\": \"\",\"tabLabel\": \"FirmName\"}, {\"value\": \"\",\"tabLabel\": \"PrimaryContact\"}, {\"value\": \"\",\"tabLabel\": \"SSN\"}, {\"value\": \"\",\"tabLabel\": \"DOB\"}, {\"value\": \"\",\"tabLabel\": \"MailingAddressCity\"}, {\"value\": \"\",\"tabLabel\": \"PhysicalAddressCity\"}, {\"value\": \"\",\"tabLabel\": \"PhysicalAddressZipCode\"}, {\"value\": \"\",\"tabLabel\": \"PhoneNumber\"}, {\"value\": \"\",\"tabLabel\": \"PhysicalAddressStreet\"}, {\"value\": \"\",\"tabLabel\": \"MailingAddressStreet\"}, {\"value\": \"\",\"tabLabel\": \"MailingAddressZipCode\"}, {\"value\": \"\",\"tabLabel\": \"DupAddressCompany\"}, {\"value\": \"\",\"tabLabel\": \"FullName\"}, {\"value\": \"\",\"tabLabel\": \"SecondPhoneNumber\"}, {\"value\": \"\",\"tabLabel\": \"AccountNumber\"}, {\"value\": \"\",\"tabLabel\": \"repcode\"}, {\"value\": \"\",\"tabLabel\": \"TypeOfBusiness\"}, {\"value\": \"\",\"tabLabel\": \"EmployerStreetAddress\"}, {\"value\": \"\",\"tabLabel\": \"EmployerCity\"}, {\"value\": \"\",\"tabLabel\": \"CountryOfDualCitizenship\"}, {\"value\": \"\",\"tabLabel\": \"CaseNumber\"}, {\"value\": \"\",\"tabLabel\": \"FirstName\"}, {\"value\": \"\",\"tabLabel\": \"LastName\"}, {\"value\": \"\",\"tabLabel\": \"MidInitial\"}, {\"value\": \"\",\"tabLabel\": \"EstateName\"}, {\"value\": \"\",\"tabLabel\": \"EmployerZipCode\"}, {\"value\": \"\",\"tabLabel\": \"EmailAddress\"}, {\"value\": \"\",\"tabLabel\": \"DupAddressName\"}, {\"value\": \"\",\"tabLabel\": \"DupAddressStreet\"}, {\"value\": \"\",\"tabLabel\": \"DupAddressCity\"}, {\"value\": \"\",\"tabLabel\": \"DupAddressZipCode\"}, {\"value\": \"\",\"tabLabel\": \"CustForMinorState\"}, {\"value\": \"\",\"tabLabel\": \"TICOwner%\"}, {\"value\": \"\",\"tabLabel\": \"DecedentAccountNumber\"}, {\"value\": \"\",\"tabLabel\": \"SPFDetail\"}, {\"value\": \"\",\"tabLabel\": \"DirectorShareholderDetail\"}, {\"value\": \"\",\"tabLabel\": \"BDDetail\"}, {\"value\": \"\",\"tabLabel\": \"TICCOOwner%\"}, {\"value\": \"\",\"tabLabel\": \"VisaExpiration\"}, {\"value\": \"\",\"tabLabel\": \"CountryOfCitizenship\"}, {\"value\": \"\",\"tabLabel\": \"CountryOfBirth\"}, {\"value\": \"\",\"tabLabel\": \"SourceOfIncome\"}, {\"value\": \"\",\"tabLabel\": \"EmployerName\"}, {\"value\": \"\",\"tabLabel\": \"Occupation\"}, {\"value\": \"\",\"tabLabel\": \"VisaNumber\"}, {\"value\": \"\",\"tabLabel\": \"Occupation\"}, {\"value\": \"\",\"tabLabel\": \"SourceOfIncome\"}, {\"value\": \"\",\"tabLabel\": \"EmployerName\"}, {\"value\": \"\",\"tabLabel\": \"SourceOfIncome\"}, {\"value\": \"\",\"tabLabel\": \"SourceOfIncome\"}, {\"value\": \"\",\"tabLabel\": \"CountryOfCitizenship\"}, {\"value\": \"\",\"tabLabel\": \"CountryOfBirth\"}],\"noteTabs\": [{\"value\": \"If you choose not to initial the LPOA or Management Fee section, simply uncheck the check box to the left of the initial box.\",\"tabLabel\": \"Note\",\"font\": \"arial\",\"bold\": \"true\",\"italic\": \"false\",\"underline\": \"false\",\"fontColor\": \"brightred\"}, {\"value\": \"If you choose not to initial the LPOA or Management Fee section, simply uncheck the check box to the left of the initial box.\",\"tabLabel\": \"Note\",\"font\": \"arial\",\"bold\": \"true\",\"italic\": \"false\",\"underline\": \"false\",\"fontColor\": \"brightred\"}],\"checkboxTabs\": [{\"tabLabel\": \"NoCorpComm\",\"selected\": \"false\"}, {\"tabLabel\": \"DupTradeConfirm\",\"selected\": \"false\"}, {\"tabLabel\": \"PhoneNumberNonUS\",\"selected\": \"false\"}, {\"tabLabel\": \"SecondPhoneNumberNonUS\",\"selected\": \"false\"}, {\"tabLabel\": \"##parent458c4da2-4e71-47ff-96c8-8109be730cc3##DupStatement\",\"selected\": \"false\"}, {\"tabLabel\": \"##parentcf7c9315-b404-4e5f-a31a-2c7ce62632f9##SPF\",\"selected\": \"false\"}, {\"tabLabel\": \"##parent5e32c9ee-bd2b-4b69-83e0-0f4e834ceb77##DirectorShareholder\",\"selected\": \"false\"}, {\"tabLabel\": \"##parentf1b52420-165e-45a3-a658-6acbf74a9604##BD\",\"selected\": \"false\"}, {\"tabLabel\": \"##parent93a23c85-c137-4dbe-b4bf-e5faf39bee4a##LPOA Checkbox\",\"selected\": \"true\"}, {\"tabLabel\": \"##parente3d13c44-563b-4658-9087-a60ffa940c72##Man Fee Checkbox\",\"selected\": \"true\"}],\"radioGroupTabs\": [{\"groupName\": \"AccountType\",\"radios\": [{\"value\": \"CustodianForMinor\",\"selected\": \"false\"}, {\"value\": \"JointTenants\",\"selected\": \"false\"}, {\"value\": \"TenantsInCommon\",\"selected\": \"false\"}, {\"value\": \"CommunityProperty\",\"selected\": \"false\"}, {\"value\": \"TenantsByTheEntireties\",\"selected\": \"false\"}, {\"value\": \"Estate\",\"selected\": \"false\"}, {\"value\": \"Guardianship\",\"selected\": \"false\"}, {\"value\": \"Conservatorship\",\"selected\": \"false\"}]}, {\"groupName\": \"cashsweepvehiclechoice\",\"radios\": [{\"value\": \"TDAFDICIDA\",\"selected\": \"false\"}, {\"value\": \"TDACASH\",\"selected\": \"false\"}]}, {\"groupName\": \"DivIntPref\",\"radios\": [{\"value\": \"Hold\",\"selected\": \"false\"}, {\"value\": \"Mail\",\"selected\": \"false\"}]}, {\"groupName\": \"MonthlyStatements\",\"radios\": [{\"value\": \"Electronic\",\"selected\": \"false\"}, {\"value\": \"Paper\",\"selected\": \"false\"}]}, {\"groupName\": \"TradeConfirmations\",\"radios\": [{\"value\": \"Electronic\",\"selected\": \"false\"}, {\"value\": \"Paper\",\"selected\": \"false\"}]}, {\"groupName\": \"Proxy\",\"radios\": [{\"value\": \"Client\",\"selected\": \"false\"}, {\"value\": \"Agent\",\"selected\": \"false\"}, {\"value\": \"Agent/Client\",\"selected\": \"false\"}]}, {\"groupName\": \"Employment\",\"radios\": [{\"value\": \"Unemployed\",\"selected\": \"false\"}, {\"value\": \"Retired\",\"selected\": \"false\"}, {\"value\": \"Homemaker\",\"selected\": \"false\"}, {\"value\": \"Student\",\"selected\": \"false\"}, {\"value\": \"SelfEmployed\",\"selected\": \"false\"}]}, {\"groupName\": \"USVisa\",\"radios\": [{\"value\": \"No\",\"selected\": \"false\"}]}, {\"groupName\": \"Citizenship\",\"radios\": [{\"value\": \"NotUSCitizen\",\"selected\": \"false\"}, {\"value\": \"PermanentResident\",\"selected\": \"false\"}]}, {\"groupName\": \"##parent2261d822-0e41-4c72-9729-cacb1f1514e1##AccountType\",\"radios\": [{\"value\": \"Individual\",\"selected\": \"false\"}]}, {\"groupName\": \"##parent71dcbe65-cff3-4140-b218-2958a194cc83##USVisa\",\"radios\": [{\"value\": \"Yes\",\"selected\": \"false\"}]}, {\"groupName\": \"##parent4d7af60a-2b4c-4a25-9fc4-bcf476114ca3##Employment\",\"radios\": [{\"value\": \"Employed\",\"selected\": \"false\"}]}, {\"groupName\": \"##parent9ecf8129-629d-497e-a04f-1fbca6ae621e##Citizenship\",\"radios\": [{\"value\": \"USCitizen\",\"selected\": \"false\"}]}],\"listTabs\": [{\"listItems\": [{\"text\": \"AA\",\"value\": \"AA\",\"selected\": \"false\"}, {\"text\": \"AE\",\"value\": \"AE\",\"selected\": \"false\"}, {\"text\": \"AK\",\"value\": \"AK\",\"selected\": \"false\"}, {\"text\": \"AL\",\"value\": \"AL\",\"selected\": \"false\"}, {\"text\": \"AP\",\"value\": \"AP\",\"selected\": \"false\"}, {\"text\": \"AS\",\"value\": \"AS\",\"selected\": \"false\"}, {\"text\": \"AZ\",\"value\": \"AZ\",\"selected\": \"false\"}, {\"text\": \"AR\",\"value\": \"AR\",\"selected\": \"false\"}, {\"text\": \"CA\",\"value\": \"CA\",\"selected\": \"false\"}, {\"text\": \"CO\",\"value\": \"CO\",\"selected\": \"false\"}, {\"text\": \"CT\",\"value\": \"CT\",\"selected\": \"false\"}, {\"text\": \"DE\",\"value\": \"DE\",\"selected\": \"false\"}, {\"text\": \"DC\",\"value\": \"DC\",\"selected\": \"false\"}, {\"text\": \"FL\",\"value\": \"FL\",\"selected\": \"false\"}, {\"text\": \"FM\",\"value\": \"FM\",\"selected\": \"false\"}, {\"text\": \"GA\",\"value\": \"GA\",\"selected\": \"false\"}, {\"text\": \"GU\",\"value\": \"GU\",\"selected\": \"false\"}, {\"text\": \"HI\",\"value\": \"HI\",\"selected\": \"false\"}, {\"text\": \"ID\",\"value\": \"ID\",\"selected\": \"false\"}, {\"text\": \"IL\",\"value\": \"IL\",\"selected\": \"false\"}, {\"text\": \"IN\",\"value\": \"IN\",\"selected\": \"false\"}, {\"text\": \"IA\",\"value\": \"IA\",\"selected\": \"false\"}, {\"text\": \"KS\",\"value\": \"KS\",\"selected\": \"false\"}, {\"text\": \"KY\",\"value\": \"KY\",\"selected\": \"false\"}, {\"text\": \"LA\",\"value\": \"LA\",\"selected\": \"false\"}, {\"text\": \"ME\",\"value\": \"ME\",\"selected\": \"false\"}, {\"text\": \"MD\",\"value\": \"MD\",\"selected\": \"false\"}, {\"text\": \"MA\",\"value\": \"MA\",\"selected\": \"false\"}, {\"text\": \"MH\",\"value\": \"MH\",\"selected\": \"false\"}, {\"text\": \"MI\",\"value\": \"MI\",\"selected\": \"false\"}, {\"text\": \"MN\",\"value\": \"MN\",\"selected\": \"false\"}, {\"text\": \"MP\",\"value\": \"MP\",\"selected\": \"false\"}, {\"text\": \"MS\",\"value\": \"MS\",\"selected\": \"false\"}, {\"text\": \"MO\",\"value\": \"MO\",\"selected\": \"false\"}, {\"text\": \"MT\",\"value\": \"MT\",\"selected\": \"false\"}, {\"text\": \"NE\",\"value\": \"NE\",\"selected\": \"false\"}, {\"text\": \"NV\",\"value\": \"NV\",\"selected\": \"false\"}, {\"text\": \"NH\",\"value\": \"NH\",\"selected\": \"false\"}, {\"text\": \"NJ\",\"value\": \"NJ\",\"selected\": \"false\"}, {\"text\": \"NM\",\"value\": \"NM\",\"selected\": \"false\"}, {\"text\": \"NY\",\"value\": \"NY\",\"selected\": \"false\"}, {\"text\": \"NC\",\"value\": \"NC\",\"selected\": \"false\"}, {\"text\": \"ND\",\"value\": \"ND\",\"selected\": \"false\"}, {\"text\": \"OH\",\"value\": \"OH\",\"selected\": \"false\"}, {\"text\": \"OK\",\"value\": \"OK\",\"selected\": \"false\"}, {\"text\": \"OR\",\"value\": \"OR\",\"selected\": \"false\"}, {\"text\": \"PA\",\"value\": \"PA\",\"selected\": \"false\"}, {\"text\": \"PR\",\"value\": \"PR\",\"selected\": \"false\"}, {\"text\": \"PW\",\"value\": \"PW\",\"selected\": \"false\"}, {\"text\": \"RI\",\"value\": \"RI\",\"selected\": \"false\"}, {\"text\": \"SC\",\"value\": \"SC\",\"selected\": \"false\"}, {\"text\": \"SD\",\"value\": \"SD\",\"selected\": \"false\"}, {\"text\": \"TN\",\"value\": \"TN\",\"selected\": \"false\"}, {\"text\": \"TX\",\"value\": \"TX\",\"selected\": \"false\"}, {\"text\": \"UT\",\"value\": \"UT\",\"selected\": \"false\"}, {\"text\": \"VT\",\"value\": \"VT\",\"selected\": \"false\"}, {\"text\": \"VA\",\"value\": \"VA\",\"selected\": \"false\"}, {\"text\": \"VI\",\"value\": \"VI\",\"selected\": \"false\"}, {\"text\": \"WA\",\"value\": \"WA\",\"selected\": \"false\"}, {\"text\": \"WV\",\"value\": \"WV\",\"selected\": \"false\"}, {\"text\": \"WI\",\"value\": \"WI\",\"selected\": \"false\"}, {\"text\": \"WY\",\"value\": \"WY\",\"selected\": \"false\"}, {\"text\": \"N/A\",\"value\": \"N/A\",\"selected\": \"false\"}],\"value\": \"\",\"tabLabel\": \"PhysicalAddressState\",\"font\": \"arial\"}, {\"listItems\": [{\"text\": \"AA\",\"value\": \"AA\",\"selected\": \"false\"}, {\"text\": \"AE\",\"value\": \"AE\",\"selected\": \"false\"}, {\"text\": \"AK\",\"value\": \"AK\",\"selected\": \"false\"}, {\"text\": \"AL\",\"value\": \"AL\",\"selected\": \"false\"}, {\"text\": \"AP\",\"value\": \"AP\",\"selected\": \"false\"}, {\"text\": \"AS\",\"value\": \"AS\",\"selected\": \"false\"}, {\"text\": \"AZ\",\"value\": \"AZ\",\"selected\": \"false\"}, {\"text\": \"AR\",\"value\": \"AR\",\"selected\": \"false\"}, {\"text\": \"CA\",\"value\": \"CA\",\"selected\": \"false\"}, {\"text\": \"CO\",\"value\": \"CO\",\"selected\": \"false\"}, {\"text\": \"CT\",\"value\": \"CT\",\"selected\": \"false\"}, {\"text\": \"DE\",\"value\": \"DE\",\"selected\": \"false\"}, {\"text\": \"DC\",\"value\": \"DC\",\"selected\": \"false\"}, {\"text\": \"FL\",\"value\": \"FL\",\"selected\": \"false\"}, {\"text\": \"FM\",\"value\": \"FM\",\"selected\": \"false\"}, {\"text\": \"GA\",\"value\": \"GA\",\"selected\": \"false\"}, {\"text\": \"GU\",\"value\": \"GU\",\"selected\": \"false\"}, {\"text\": \"HI\",\"value\": \"HI\",\"selected\": \"false\"}, {\"text\": \"ID\",\"value\": \"ID\",\"selected\": \"false\"}, {\"text\": \"IL\",\"value\": \"IL\",\"selected\": \"false\"}, {\"text\": \"IN\",\"value\": \"IN\",\"selected\": \"false\"}, {\"text\": \"IA\",\"value\": \"IA\",\"selected\": \"false\"}, {\"text\": \"KS\",\"value\": \"KS\",\"selected\": \"false\"}, {\"text\": \"KY\",\"value\": \"KY\",\"selected\": \"false\"}, {\"text\": \"LA\",\"value\": \"LA\",\"selected\": \"false\"}, {\"text\": \"ME\",\"value\": \"ME\",\"selected\": \"false\"}, {\"text\": \"MD\",\"value\": \"MD\",\"selected\": \"false\"}, {\"text\": \"MA\",\"value\": \"MA\",\"selected\": \"false\"}, {\"text\": \"MH\",\"value\": \"MH\",\"selected\": \"false\"}, {\"text\": \"MI\",\"value\": \"MI\",\"selected\": \"false\"}, {\"text\": \"MN\",\"value\": \"MN\",\"selected\": \"false\"}, {\"text\": \"MP\",\"value\": \"MP\",\"selected\": \"false\"}, {\"text\": \"MS\",\"value\": \"MS\",\"selected\": \"false\"}, {\"text\": \"MO\",\"value\": \"MO\",\"selected\": \"false\"}, {\"text\": \"MT\",\"value\": \"MT\",\"selected\": \"false\"}, {\"text\": \"NE\",\"value\": \"NE\",\"selected\": \"false\"}, {\"text\": \"NV\",\"value\": \"NV\",\"selected\": \"false\"}, {\"text\": \"NH\",\"value\": \"NH\",\"selected\": \"false\"}, {\"text\": \"NJ\",\"value\": \"NJ\",\"selected\": \"false\"}, {\"text\": \"NM\",\"value\": \"NM\",\"selected\": \"false\"}, {\"text\": \"NY\",\"value\": \"NY\",\"selected\": \"false\"}, {\"text\": \"NC\",\"value\": \"NC\",\"selected\": \"false\"}, {\"text\": \"ND\",\"value\": \"ND\",\"selected\": \"false\"}, {\"text\": \"OH\",\"value\": \"OH\",\"selected\": \"false\"}, {\"text\": \"OK\",\"value\": \"OK\",\"selected\": \"false\"}, {\"text\": \"OR\",\"value\": \"OR\",\"selected\": \"false\"}, {\"text\": \"PA\",\"value\": \"PA\",\"selected\": \"false\"}, {\"text\": \"PR\",\"value\": \"PR\",\"selected\": \"false\"}, {\"text\": \"PW\",\"value\": \"PW\",\"selected\": \"false\"}, {\"text\": \"RI\",\"value\": \"RI\",\"selected\": \"false\"}, {\"text\": \"SC\",\"value\": \"SC\",\"selected\": \"false\"}, {\"text\": \"SD\",\"value\": \"SD\",\"selected\": \"false\"}, {\"text\": \"TN\",\"value\": \"TN\",\"selected\": \"false\"}, {\"text\": \"TX\",\"value\": \"TX\",\"selected\": \"false\"}, {\"text\": \"UT\",\"value\": \"UT\",\"selected\": \"false\"}, {\"text\": \"VT\",\"value\": \"VT\",\"selected\": \"false\"}, {\"text\": \"VA\",\"value\": \"VA\",\"selected\": \"false\"}, {\"text\": \"VI\",\"value\": \"VI\",\"selected\": \"false\"}, {\"text\": \"WA\",\"value\": \"WA\",\"selected\": \"false\"}, {\"text\": \"WV\",\"value\": \"WV\",\"selected\": \"false\"}, {\"text\": \"WI\",\"value\": \"WI\",\"selected\": \"false\"}, {\"text\": \"WY\",\"value\": \"WY\",\"selected\": \"false\"}, {\"text\": \"N/A\",\"value\": \"N/A\",\"selected\": \"false\"}],\"value\": \"\",\"tabLabel\": \"MailingAddressState\",\"font\": \"arial\"}, {\"listItems\": [{\"text\": \"AA\",\"value\": \"AA\",\"selected\": \"false\"}, {\"text\": \"AE\",\"value\": \"AE\",\"selected\": \"false\"}, {\"text\": \"AK\",\"value\": \"AK\",\"selected\": \"false\"}, {\"text\": \"AL\",\"value\": \"AL\",\"selected\": \"false\"}, {\"text\": \"AP\",\"value\": \"AP\",\"selected\": \"false\"}, {\"text\": \"AS\",\"value\": \"AS\",\"selected\": \"false\"}, {\"text\": \"AZ\",\"value\": \"AZ\",\"selected\": \"false\"}, {\"text\": \"AR\",\"value\": \"AR\",\"selected\": \"false\"}, {\"text\": \"CA\",\"value\": \"CA\",\"selected\": \"false\"}, {\"text\": \"CO\",\"value\": \"CO\",\"selected\": \"false\"}, {\"text\": \"CT\",\"value\": \"CT\",\"selected\": \"false\"}, {\"text\": \"DE\",\"value\": \"DE\",\"selected\": \"false\"}, {\"text\": \"DC\",\"value\": \"DC\",\"selected\": \"false\"}, {\"text\": \"FL\",\"value\": \"FL\",\"selected\": \"false\"}, {\"text\": \"FM\",\"value\": \"FM\",\"selected\": \"false\"}, {\"text\": \"GA\",\"value\": \"GA\",\"selected\": \"false\"}, {\"text\": \"GU\",\"value\": \"GU\",\"selected\": \"false\"}, {\"text\": \"HI\",\"value\": \"HI\",\"selected\": \"false\"}, {\"text\": \"ID\",\"value\": \"ID\",\"selected\": \"false\"}, {\"text\": \"IL\",\"value\": \"IL\",\"selected\": \"false\"}, {\"text\": \"IN\",\"value\": \"IN\",\"selected\": \"false\"}, {\"text\": \"IA\",\"value\": \"IA\",\"selected\": \"false\"}, {\"text\": \"KS\",\"value\": \"KS\",\"selected\": \"false\"}, {\"text\": \"KY\",\"value\": \"KY\",\"selected\": \"false\"}, {\"text\": \"LA\",\"value\": \"LA\",\"selected\": \"false\"}, {\"text\": \"ME\",\"value\": \"ME\",\"selected\": \"false\"}, {\"text\": \"MD\",\"value\": \"MD\",\"selected\": \"false\"}, {\"text\": \"MA\",\"value\": \"MA\",\"selected\": \"false\"}, {\"text\": \"MH\",\"value\": \"MH\",\"selected\": \"false\"}, {\"text\": \"MI\",\"value\": \"MI\",\"selected\": \"false\"}, {\"text\": \"MN\",\"value\": \"MN\",\"selected\": \"false\"}, {\"text\": \"MP\",\"value\": \"MP\",\"selected\": \"false\"}, {\"text\": \"MS\",\"value\": \"MS\",\"selected\": \"false\"}, {\"text\": \"MO\",\"value\": \"MO\",\"selected\": \"false\"}, {\"text\": \"MT\",\"value\": \"MT\",\"selected\": \"false\"}, {\"text\": \"NE\",\"value\": \"NE\",\"selected\": \"false\"}, {\"text\": \"NV\",\"value\": \"NV\",\"selected\": \"false\"}, {\"text\": \"NH\",\"value\": \"NH\",\"selected\": \"false\"}, {\"text\": \"NJ\",\"value\": \"NJ\",\"selected\": \"false\"}, {\"text\": \"NM\",\"value\": \"NM\",\"selected\": \"false\"}, {\"text\": \"NY\",\"value\": \"NY\",\"selected\": \"false\"}, {\"text\": \"NC\",\"value\": \"NC\",\"selected\": \"false\"}, {\"text\": \"ND\",\"value\": \"ND\",\"selected\": \"false\"}, {\"text\": \"OH\",\"value\": \"OH\",\"selected\": \"false\"}, {\"text\": \"OK\",\"value\": \"OK\",\"selected\": \"false\"}, {\"text\": \"OR\",\"value\": \"OR\",\"selected\": \"false\"}, {\"text\": \"PA\",\"value\": \"PA\",\"selected\": \"false\"}, {\"text\": \"PR\",\"value\": \"PR\",\"selected\": \"false\"}, {\"text\": \"PW\",\"value\": \"PW\",\"selected\": \"false\"}, {\"text\": \"RI\",\"value\": \"RI\",\"selected\": \"false\"}, {\"text\": \"SC\",\"value\": \"SC\",\"selected\": \"false\"}, {\"text\": \"SD\",\"value\": \"SD\",\"selected\": \"false\"}, {\"text\": \"TN\",\"value\": \"TN\",\"selected\": \"false\"}, {\"text\": \"TX\",\"value\": \"TX\",\"selected\": \"false\"}, {\"text\": \"UT\",\"value\": \"UT\",\"selected\": \"false\"}, {\"text\": \"VT\",\"value\": \"VT\",\"selected\": \"false\"}, {\"text\": \"VA\",\"value\": \"VA\",\"selected\": \"false\"}, {\"text\": \"VI\",\"value\": \"VI\",\"selected\": \"false\"}, {\"text\": \"WA\",\"value\": \"WA\",\"selected\": \"false\"}, {\"text\": \"WV\",\"value\": \"WV\",\"selected\": \"false\"}, {\"text\": \"WI\",\"value\": \"WI\",\"selected\": \"false\"}, {\"text\": \"WY\",\"value\": \"WY\",\"selected\": \"false\"}, {\"text\": \"N/A\",\"value\": \"N/A\",\"selected\": \"false\"}],\"value\": \"\",\"tabLabel\": \"EmployerState\",\"font\": \"arial\"}, {\"listItems\": [{\"text\": \"AA\",\"value\": \"AA\",\"selected\": \"false\"}, {\"text\": \"AE\",\"value\": \"AE\",\"selected\": \"false\"}, {\"text\": \"AK\",\"value\": \"AK\",\"selected\": \"false\"}, {\"text\": \"AL\",\"value\": \"AL\",\"selected\": \"false\"}, {\"text\": \"AP\",\"value\": \"AP\",\"selected\": \"false\"}, {\"text\": \"AS\",\"value\": \"AS\",\"selected\": \"false\"}, {\"text\": \"AZ\",\"value\": \"AZ\",\"selected\": \"false\"}, {\"text\": \"AR\",\"value\": \"AR\",\"selected\": \"false\"}, {\"text\": \"CA\",\"value\": \"CA\",\"selected\": \"false\"}, {\"text\": \"CO\",\"value\": \"CO\",\"selected\": \"false\"}, {\"text\": \"CT\",\"value\": \"CT\",\"selected\": \"false\"}, {\"text\": \"DE\",\"value\": \"DE\",\"selected\": \"false\"}, {\"text\": \"DC\",\"value\": \"DC\",\"selected\": \"false\"}, {\"text\": \"FL\",\"value\": \"FL\",\"selected\": \"false\"}, {\"text\": \"FM\",\"value\": \"FM\",\"selected\": \"false\"}, {\"text\": \"GA\",\"value\": \"GA\",\"selected\": \"false\"}, {\"text\": \"GU\",\"value\": \"GU\",\"selected\": \"false\"}, {\"text\": \"HI\",\"value\": \"HI\",\"selected\": \"false\"}, {\"text\": \"ID\",\"value\": \"ID\",\"selected\": \"false\"}, {\"text\": \"IL\",\"value\": \"IL\",\"selected\": \"false\"}, {\"text\": \"IN\",\"value\": \"IN\",\"selected\": \"false\"}, {\"text\": \"IA\",\"value\": \"IA\",\"selected\": \"false\"}, {\"text\": \"KS\",\"value\": \"KS\",\"selected\": \"false\"}, {\"text\": \"KY\",\"value\": \"KY\",\"selected\": \"false\"}, {\"text\": \"LA\",\"value\": \"LA\",\"selected\": \"false\"}, {\"text\": \"ME\",\"value\": \"ME\",\"selected\": \"false\"}, {\"text\": \"MD\",\"value\": \"MD\",\"selected\": \"false\"}, {\"text\": \"MA\",\"value\": \"MA\",\"selected\": \"false\"}, {\"text\": \"MH\",\"value\": \"MH\",\"selected\": \"false\"}, {\"text\": \"MI\",\"value\": \"MI\",\"selected\": \"false\"}, {\"text\": \"MN\",\"value\": \"MN\",\"selected\": \"false\"}, {\"text\": \"MP\",\"value\": \"MP\",\"selected\": \"false\"}, {\"text\": \"MS\",\"value\": \"MS\",\"selected\": \"false\"}, {\"text\": \"MO\",\"value\": \"MO\",\"selected\": \"false\"}, {\"text\": \"MT\",\"value\": \"MT\",\"selected\": \"false\"}, {\"text\": \"NE\",\"value\": \"NE\",\"selected\": \"false\"}, {\"text\": \"NV\",\"value\": \"NV\",\"selected\": \"false\"}, {\"text\": \"NH\",\"value\": \"NH\",\"selected\": \"false\"}, {\"text\": \"NJ\",\"value\": \"NJ\",\"selected\": \"false\"}, {\"text\": \"NM\",\"value\": \"NM\",\"selected\": \"false\"}, {\"text\": \"NY\",\"value\": \"NY\",\"selected\": \"false\"}, {\"text\": \"NC\",\"value\": \"NC\",\"selected\": \"false\"}, {\"text\": \"ND\",\"value\": \"ND\",\"selected\": \"false\"}, {\"text\": \"OH\",\"value\": \"OH\",\"selected\": \"false\"}, {\"text\": \"OK\",\"value\": \"OK\",\"selected\": \"false\"}, {\"text\": \"OR\",\"value\": \"OR\",\"selected\": \"false\"}, {\"text\": \"PA\",\"value\": \"PA\",\"selected\": \"false\"}, {\"text\": \"PR\",\"value\": \"PR\",\"selected\": \"false\"}, {\"text\": \"PW\",\"value\": \"PW\",\"selected\": \"false\"}, {\"text\": \"RI\",\"value\": \"RI\",\"selected\": \"false\"}, {\"text\": \"SC\",\"value\": \"SC\",\"selected\": \"false\"}, {\"text\": \"SD\",\"value\": \"SD\",\"selected\": \"false\"}, {\"text\": \"TN\",\"value\": \"TN\",\"selected\": \"false\"}, {\"text\": \"TX\",\"value\": \"TX\",\"selected\": \"false\"}, {\"text\": \"UT\",\"value\": \"UT\",\"selected\": \"false\"}, {\"text\": \"VT\",\"value\": \"VT\",\"selected\": \"false\"}, {\"text\": \"VA\",\"value\": \"VA\",\"selected\": \"false\"}, {\"text\": \"VI\",\"value\": \"VI\",\"selected\": \"false\"}, {\"text\": \"WA\",\"value\": \"WA\",\"selected\": \"false\"}, {\"text\": \"WV\",\"value\": \"WV\",\"selected\": \"false\"}, {\"text\": \"WI\",\"value\": \"WI\",\"selected\": \"false\"}, {\"text\": \"WY\",\"value\": \"WY\",\"selected\": \"false\"}, {\"text\": \"N/A\",\"value\": \"N/A\",\"selected\": \"false\"}],\"value\": \"\",\"tabLabel\": \"DupAddressState\",\"font\": \"arial\"}, {\"listItems\": [{\"text\": \"\",\"value\": \"\",\"selected\": \"true\"}, {\"text\": \"B1/B2\",\"value\": \"B1/B2\",\"selected\": \"false\"}, {\"text\": \"A/G\",\"value\": \"A/G\",\"selected\": \"false\"}, {\"text\": \"C\",\"value\": \"C\",\"selected\": \"false\"}, {\"text\": \"D\",\"value\": \"D\",\"selected\": \"false\"}, {\"text\": \"E\",\"value\": \"E\",\"selected\": \"false\"}, {\"text\": \"F\",\"value\": \"F\",\"selected\": \"false\"}, {\"text\": \"H\",\"value\": \"H\",\"selected\": \"false\"}, {\"text\": \"I\",\"value\": \"I\",\"selected\": \"false\"}, {\"text\": \"J\",\"value\": \"J\",\"selected\": \"false\"}, {\"text\": \"K\",\"value\": \"K\",\"selected\": \"false\"}, {\"text\": \"L\",\"value\": \"L\",\"selected\": \"false\"}, {\"text\": \"M\",\"value\": \"M\",\"selected\": \"false\"}, {\"text\": \"O\",\"value\": \"O\",\"selected\": \"false\"}, {\"text\": \"P\",\"value\": \"P\",\"selected\": \"false\"}, {\"text\": \"Q\",\"value\": \"Q\",\"selected\": \"false\"}, {\"text\": \"R\",\"value\": \"R\",\"selected\": \"false\"}, {\"text\": \"TC\",\"value\": \"TC\",\"selected\": \"false\"}, {\"text\": \"TD\",\"value\": \"TD\",\"selected\": \"false\"}, {\"text\": \"TN\",\"value\": \"TN\",\"selected\": \"false\"}, {\"text\": \"V\",\"value\": \"V\",\"selected\": \"false\"}, {\"text\": \"None\",\"value\": \"None\",\"selected\": \"false\"}],\"value\": \"\",\"tabLabel\": \"VisaType\",\"font\": \"arial\"}]}}, {\"defaultRecipient\": \"false\",\"signInEachLocation\": \"false\",\"email\": \"\",\"accessCode\": \"\",\"requireIdLookup\": \"true\",\"idCheckConfigurationName\": \"ID Check $\",\"routingOrder\": \"20\",\"note\": \"\",\"roleName\": \"Joint\",\"inheritEmailNotificationConfiguration\": \"false\",\"recipientType\": \"signer\",\"tabs\": {\"signHereTabs\": [{\"stampType\": \"signature\",\"tabLabel\": \"Signature 107\",\"scaleValue\": \"0.8\",\"optional\": \"false\"}],\"initialHereTabs\": [{\"tabLabel\": \"LPOA Joint Initials\",\"scaleValue\": \"0.75\",\"optional\": \"false\"}, {\"tabLabel\": \"Man Fee Joint Initials\",\"scaleValue\": \"0.75\",\"optional\": \"false\"}],\"dateSignedTabs\": [{\"value\": \"\",\"tabLabel\": \"Date Signed\"}],\"textTabs\": [{\"value\": \"\",\"tabLabel\": \"JointAHFirstName\"}, {\"value\": \"\",\"tabLabel\": \"JointAHSSN\"}, {\"value\": \"\",\"tabLabel\": \"JointAHDOB\"}, {\"value\": \"\",\"tabLabel\": \"JointAHPhoneNumber\"}, {\"value\": \"\",\"tabLabel\": \"JointAHPhysicalAddressStreet\"}, {\"value\": \"\",\"tabLabel\": \"JointAHPhysicalAddressCity\"}, {\"value\": \"\",\"tabLabel\": \"JointAHPhysicalAddressZipCode\"}, {\"value\": \"\",\"tabLabel\": \"JointAHMailingAddressStreet\"}, {\"value\": \"\",\"tabLabel\": \"JointAHMailingAddressCity\"}, {\"value\": \"\",\"tabLabel\": \"JointAHMailingAddressZipCode\"}, {\"value\": \"\",\"tabLabel\": \"JointAHFullName\"}, {\"value\": \"\",\"tabLabel\": \"JointAHSecondPhoneNumber\"}, {\"value\": \"\",\"tabLabel\": \"JointAHTypeOfBusiness\"}, {\"value\": \"\",\"tabLabel\": \"JointAHEmployerStreetAddress\"}, {\"value\": \"\",\"tabLabel\": \"JointAHEmployerCity\"}, {\"value\": \"\",\"tabLabel\": \"JointAHCountryOfDualCitizenship\"}, {\"value\": \"\",\"tabLabel\": \"JointAHLastName\"}, {\"value\": \"\",\"tabLabel\": \"JointAHEstateName\"}, {\"value\": \"\",\"tabLabel\": \"JointAHMidInitial\"}, {\"value\": \"\",\"tabLabel\": \"JointAHEmployerName\"}, {\"value\": \"\",\"tabLabel\": \"JointAHSourceOfIncome\"}, {\"value\": \"\",\"tabLabel\": \"JointAHCountryOfCitizenship\"}, {\"value\": \"\",\"tabLabel\": \"JointAHVisaExpiration\"}, {\"value\": \"\",\"tabLabel\": \"JointAHSPFDetail\"}, {\"value\": \"\",\"tabLabel\": \"JointAHDirectorShareholderDetail\"}, {\"value\": \"\",\"tabLabel\": \"JointAHBDDetail\"}, {\"value\": \"\",\"tabLabel\": \"JointAHCountryOfBirth\"}, {\"value\": \"\",\"tabLabel\": \"JointAHSourceOfIncome\"}, {\"value\": \"\",\"tabLabel\": \"JointAHSourceOfIncome\"}, {\"value\": \"\",\"tabLabel\": \"JointAHSourceOfIncome\"}, {\"value\": \"\",\"tabLabel\": \"JointAHVisaNumber\"}, {\"value\": \"\",\"tabLabel\": \"JointAHOccupation\"}, {\"value\": \"\",\"tabLabel\": \"JointAHOccupation\"}, {\"value\": \"\",\"tabLabel\": \"JointAHEmployerName\"}, {\"value\": \"\",\"tabLabel\": \"JointAHCountryOfCitizenship\"}, {\"value\": \"\",\"tabLabel\": \"JointAHCountryOfBirth\"}],\"checkboxTabs\": [{\"tabLabel\": \"SecondPhoneNumberNonUS 176\",\"selected\": \"false\"}, {\"tabLabel\": \"JointAHPhoneNumberNonUS\",\"selected\": \"false\"}, {\"tabLabel\": \"##parent49558361-ff5f-43a7-9800-31024f8c4e32##JointAHSPF\",\"selected\": \"false\"}, {\"tabLabel\": \"##parentbddd1cf7-29e1-4550-a0fd-ce54a92d2a25##JointAHDirectorShareholder\",\"selected\": \"false\"}, {\"tabLabel\": \"##parent372f7cd0-8385-44fe-96be-7e7bbb4c1e77##JointAHBD\",\"selected\": \"false\"}, {\"tabLabel\": \"##parent7761640b-6924-485c-b7bd-ba868eb14b51##LPOA Joint Checkbox\",\"selected\": \"true\"}, {\"tabLabel\": \"##parent522fd208-28c2-499e-ac0a-ee2122124244##Man Fee Joint Checkbox\",\"selected\": \"true\"}],\"radioGroupTabs\": [{\"groupName\": \"JointAHEmployment\",\"radios\": [{\"value\": \"Retired\",\"selected\": \"false\"}, {\"value\": \"Homemaker\",\"selected\": \"false\"}, {\"value\": \"Student\",\"selected\": \"false\"}, {\"value\": \"SelfEmployed\",\"selected\": \"false\"}]}, {\"groupName\": \"JointAHUSVisa\",\"radios\": [{\"value\": \"No\",\"selected\": \"false\"}]}, {\"groupName\": \"JointAHCitizenship\",\"radios\": [{\"value\": \"NotUSCitizen\",\"selected\": \"false\"}, {\"value\": \"PermanentResident\",\"selected\": \"false\"}]}, {\"groupName\": \"##parent3968dc41-fbc4-408b-8d02-6158af488a61##JointAHEmployment\",\"radios\": [{\"value\": \"Employed\",\"selected\": \"false\"}]}, {\"groupName\": \"##parent042f6b32-adf8-4d9f-9932-44d04ac07bb2##JointAHEmployment\",\"radios\": [{\"value\": \"Unemployed\",\"selected\": \"false\"}]}, {\"groupName\": \"##parent89a140b7-e8db-428e-86cf-896aeb57b83f##JointAHUSVisa\",\"radios\": [{\"value\": \"Yes\",\"selected\": \"false\"}]}, {\"groupName\": \"##parent68bd95b7-6d83-47dd-a0b3-a66100ab41b7##JointAHCitizenship\",\"radios\": [{\"value\": \"USCitizen\",\"selected\": \"false\"}]}],\"listTabs\": [{\"listItems\": [{\"text\": \"AA\",\"value\": \"AA\",\"selected\": \"false\"}, {\"text\": \"AE\",\"value\": \"AE\",\"selected\": \"false\"}, {\"text\": \"AK\",\"value\": \"AK\",\"selected\": \"false\"}, {\"text\": \"AL\",\"value\": \"AL\",\"selected\": \"false\"}, {\"text\": \"AP\",\"value\": \"AP\",\"selected\": \"false\"}, {\"text\": \"AS\",\"value\": \"AS\",\"selected\": \"false\"}, {\"text\": \"AZ\",\"value\": \"AZ\",\"selected\": \"false\"}, {\"text\": \"AR\",\"value\": \"AR\",\"selected\": \"false\"}, {\"text\": \"CA\",\"value\": \"CA\",\"selected\": \"false\"}, {\"text\": \"CO\",\"value\": \"CO\",\"selected\": \"false\"}, {\"text\": \"CT\",\"value\": \"CT\",\"selected\": \"false\"}, {\"text\": \"DE\",\"value\": \"DE\",\"selected\": \"false\"}, {\"text\": \"DC\",\"value\": \"DC\",\"selected\": \"false\"}, {\"text\": \"FL\",\"value\": \"FL\",\"selected\": \"false\"}, {\"text\": \"FM\",\"value\": \"FM\",\"selected\": \"false\"}, {\"text\": \"GA\",\"value\": \"GA\",\"selected\": \"false\"}, {\"text\": \"GU\",\"value\": \"GU\",\"selected\": \"false\"}, {\"text\": \"HI\",\"value\": \"HI\",\"selected\": \"false\"}, {\"text\": \"ID\",\"value\": \"ID\",\"selected\": \"false\"}, {\"text\": \"IL\",\"value\": \"IL\",\"selected\": \"false\"}, {\"text\": \"IN\",\"value\": \"IN\",\"selected\": \"false\"}, {\"text\": \"IA\",\"value\": \"IA\",\"selected\": \"false\"}, {\"text\": \"KS\",\"value\": \"KS\",\"selected\": \"false\"}, {\"text\": \"KY\",\"value\": \"KY\",\"selected\": \"false\"}, {\"text\": \"LA\",\"value\": \"LA\",\"selected\": \"false\"}, {\"text\": \"ME\",\"value\": \"ME\",\"selected\": \"false\"}, {\"text\": \"MD\",\"value\": \"MD\",\"selected\": \"false\"}, {\"text\": \"MA\",\"value\": \"MA\",\"selected\": \"false\"}, {\"text\": \"MH\",\"value\": \"MH\",\"selected\": \"false\"}, {\"text\": \"MI\",\"value\": \"MI\",\"selected\": \"false\"}, {\"text\": \"MN\",\"value\": \"MN\",\"selected\": \"false\"}, {\"text\": \"MP\",\"value\": \"MP\",\"selected\": \"false\"}, {\"text\": \"MS\",\"value\": \"MS\",\"selected\": \"false\"}, {\"text\": \"MO\",\"value\": \"MO\",\"selected\": \"false\"}, {\"text\": \"MT\",\"value\": \"MT\",\"selected\": \"false\"}, {\"text\": \"NE\",\"value\": \"NE\",\"selected\": \"false\"}, {\"text\": \"NV\",\"value\": \"NV\",\"selected\": \"false\"}, {\"text\": \"NH\",\"value\": \"NH\",\"selected\": \"false\"}, {\"text\": \"NJ\",\"value\": \"NJ\",\"selected\": \"false\"}, {\"text\": \"NM\",\"value\": \"NM\",\"selected\": \"false\"}, {\"text\": \"NY\",\"value\": \"NY\",\"selected\": \"false\"}, {\"text\": \"NC\",\"value\": \"NC\",\"selected\": \"false\"}, {\"text\": \"ND\",\"value\": \"ND\",\"selected\": \"false\"}, {\"text\": \"OH\",\"value\": \"OH\",\"selected\": \"false\"}, {\"text\": \"OK\",\"value\": \"OK\",\"selected\": \"false\"}, {\"text\": \"OR\",\"value\": \"OR\",\"selected\": \"false\"}, {\"text\": \"PA\",\"value\": \"PA\",\"selected\": \"false\"}, {\"text\": \"PR\",\"value\": \"PR\",\"selected\": \"false\"}, {\"text\": \"PW\",\"value\": \"PW\",\"selected\": \"false\"}, {\"text\": \"RI\",\"value\": \"RI\",\"selected\": \"false\"}, {\"text\": \"SC\",\"value\": \"SC\",\"selected\": \"false\"}, {\"text\": \"SD\",\"value\": \"SD\",\"selected\": \"false\"}, {\"text\": \"TN\",\"value\": \"TN\",\"selected\": \"false\"}, {\"text\": \"TX\",\"value\": \"TX\",\"selected\": \"false\"}, {\"text\": \"UT\",\"value\": \"UT\",\"selected\": \"false\"}, {\"text\": \"VT\",\"value\": \"VT\",\"selected\": \"false\"}, {\"text\": \"VA\",\"value\": \"VA\",\"selected\": \"false\"}, {\"text\": \"VI\",\"value\": \"VI\",\"selected\": \"false\"}, {\"text\": \"WA\",\"value\": \"WA\",\"selected\": \"false\"}, {\"text\": \"WV\",\"value\": \"WV\",\"selected\": \"false\"}, {\"text\": \"WI\",\"value\": \"WI\",\"selected\": \"false\"}, {\"text\": \"WY\",\"value\": \"WY\",\"selected\": \"false\"}, {\"text\": \"N/A\",\"value\": \"N/A\",\"selected\": \"false\"}],\"value\": \"\",\"tabLabel\": \"JointAHPhysicalAddressState\",\"font\": \"arial\"}, {\"listItems\": [{\"text\": \"AA\",\"value\": \"AA\",\"selected\": \"false\"}, {\"text\": \"AE\",\"value\": \"AE\",\"selected\": \"false\"}, {\"text\": \"AK\",\"value\": \"AK\",\"selected\": \"false\"}, {\"text\": \"AL\",\"value\": \"AL\",\"selected\": \"false\"}, {\"text\": \"AP\",\"value\": \"AP\",\"selected\": \"false\"}, {\"text\": \"AS\",\"value\": \"AS\",\"selected\": \"false\"}, {\"text\": \"AZ\",\"value\": \"AZ\",\"selected\": \"false\"}, {\"text\": \"AR\",\"value\": \"AR\",\"selected\": \"false\"}, {\"text\": \"CA\",\"value\": \"CA\",\"selected\": \"false\"}, {\"text\": \"CO\",\"value\": \"CO\",\"selected\": \"false\"}, {\"text\": \"CT\",\"value\": \"CT\",\"selected\": \"false\"}, {\"text\": \"DE\",\"value\": \"DE\",\"selected\": \"false\"}, {\"text\": \"DC\",\"value\": \"DC\",\"selected\": \"false\"}, {\"text\": \"FL\",\"value\": \"FL\",\"selected\": \"false\"}, {\"text\": \"FM\",\"value\": \"FM\",\"selected\": \"false\"}, {\"text\": \"GA\",\"value\": \"GA\",\"selected\": \"false\"}, {\"text\": \"GU\",\"value\": \"GU\",\"selected\": \"false\"}, {\"text\": \"HI\",\"value\": \"HI\",\"selected\": \"false\"}, {\"text\": \"ID\",\"value\": \"ID\",\"selected\": \"false\"}, {\"text\": \"IL\",\"value\": \"IL\",\"selected\": \"false\"}, {\"text\": \"IN\",\"value\": \"IN\",\"selected\": \"false\"}, {\"text\": \"IA\",\"value\": \"IA\",\"selected\": \"false\"}, {\"text\": \"KS\",\"value\": \"KS\",\"selected\": \"false\"}, {\"text\": \"KY\",\"value\": \"KY\",\"selected\": \"false\"}, {\"text\": \"LA\",\"value\": \"LA\",\"selected\": \"false\"}, {\"text\": \"ME\",\"value\": \"ME\",\"selected\": \"false\"}, {\"text\": \"MD\",\"value\": \"MD\",\"selected\": \"false\"}, {\"text\": \"MA\",\"value\": \"MA\",\"selected\": \"false\"}, {\"text\": \"MH\",\"value\": \"MH\",\"selected\": \"false\"}, {\"text\": \"MI\",\"value\": \"MI\",\"selected\": \"false\"}, {\"text\": \"MN\",\"value\": \"MN\",\"selected\": \"false\"}, {\"text\": \"MP\",\"value\": \"MP\",\"selected\": \"false\"}, {\"text\": \"MS\",\"value\": \"MS\",\"selected\": \"false\"}, {\"text\": \"MO\",\"value\": \"MO\",\"selected\": \"false\"}, {\"text\": \"MT\",\"value\": \"MT\",\"selected\": \"false\"}, {\"text\": \"NE\",\"value\": \"NE\",\"selected\": \"false\"}, {\"text\": \"NV\",\"value\": \"NV\",\"selected\": \"false\"}, {\"text\": \"NH\",\"value\": \"NH\",\"selected\": \"false\"}, {\"text\": \"NJ\",\"value\": \"NJ\",\"selected\": \"false\"}, {\"text\": \"NM\",\"value\": \"NM\",\"selected\": \"false\"}, {\"text\": \"NY\",\"value\": \"NY\",\"selected\": \"false\"}, {\"text\": \"NC\",\"value\": \"NC\",\"selected\": \"false\"}, {\"text\": \"ND\",\"value\": \"ND\",\"selected\": \"false\"}, {\"text\": \"OH\",\"value\": \"OH\",\"selected\": \"false\"}, {\"text\": \"OK\",\"value\": \"OK\",\"selected\": \"false\"}, {\"text\": \"OR\",\"value\": \"OR\",\"selected\": \"false\"}, {\"text\": \"PA\",\"value\": \"PA\",\"selected\": \"false\"}, {\"text\": \"PR\",\"value\": \"PR\",\"selected\": \"false\"}, {\"text\": \"PW\",\"value\": \"PW\",\"selected\": \"false\"}, {\"text\": \"RI\",\"value\": \"RI\",\"selected\": \"false\"}, {\"text\": \"SC\",\"value\": \"SC\",\"selected\": \"false\"}, {\"text\": \"SD\",\"value\": \"SD\",\"selected\": \"false\"}, {\"text\": \"TN\",\"value\": \"TN\",\"selected\": \"false\"}, {\"text\": \"TX\",\"value\": \"TX\",\"selected\": \"false\"}, {\"text\": \"UT\",\"value\": \"UT\",\"selected\": \"false\"}, {\"text\": \"VT\",\"value\": \"VT\",\"selected\": \"false\"}, {\"text\": \"VA\",\"value\": \"VA\",\"selected\": \"false\"}, {\"text\": \"VI\",\"value\": \"VI\",\"selected\": \"false\"}, {\"text\": \"WA\",\"value\": \"WA\",\"selected\": \"false\"}, {\"text\": \"WV\",\"value\": \"WV\",\"selected\": \"false\"}, {\"text\": \"WI\",\"value\": \"WI\",\"selected\": \"false\"}, {\"text\": \"WY\",\"value\": \"WY\",\"selected\": \"false\"}, {\"text\": \"N/A\",\"value\": \"N/A\",\"selected\": \"false\"}],\"value\": \"\",\"tabLabel\": \"JointAHMailingAddressState\",\"font\": \"arial\"}, {\"listItems\": [{\"text\": \"AA\",\"value\": \"AA\",\"selected\": \"false\"}, {\"text\": \"AE\",\"value\": \"AE\",\"selected\": \"false\"}, {\"text\": \"AK\",\"value\": \"AK\",\"selected\": \"false\"}, {\"text\": \"AL\",\"value\": \"AL\",\"selected\": \"false\"}, {\"text\": \"AP\",\"value\": \"AP\",\"selected\": \"false\"}, {\"text\": \"AS\",\"value\": \"AS\",\"selected\": \"false\"}, {\"text\": \"AZ\",\"value\": \"AZ\",\"selected\": \"false\"}, {\"text\": \"AR\",\"value\": \"AR\",\"selected\": \"false\"}, {\"text\": \"CA\",\"value\": \"CA\",\"selected\": \"false\"}, {\"text\": \"CO\",\"value\": \"CO\",\"selected\": \"false\"}, {\"text\": \"CT\",\"value\": \"CT\",\"selected\": \"false\"}, {\"text\": \"DE\",\"value\": \"DE\",\"selected\": \"false\"}, {\"text\": \"DC\",\"value\": \"DC\",\"selected\": \"false\"}, {\"text\": \"FL\",\"value\": \"FL\",\"selected\": \"false\"}, {\"text\": \"FM\",\"value\": \"FM\",\"selected\": \"false\"}, {\"text\": \"GA\",\"value\": \"GA\",\"selected\": \"false\"}, {\"text\": \"GU\",\"value\": \"GU\",\"selected\": \"false\"}, {\"text\": \"HI\",\"value\": \"HI\",\"selected\": \"false\"}, {\"text\": \"ID\",\"value\": \"ID\",\"selected\": \"false\"}, {\"text\": \"IL\",\"value\": \"IL\",\"selected\": \"false\"}, {\"text\": \"IN\",\"value\": \"IN\",\"selected\": \"false\"}, {\"text\": \"IA\",\"value\": \"IA\",\"selected\": \"false\"}, {\"text\": \"KS\",\"value\": \"KS\",\"selected\": \"false\"}, {\"text\": \"KY\",\"value\": \"KY\",\"selected\": \"false\"}, {\"text\": \"LA\",\"value\": \"LA\",\"selected\": \"false\"}, {\"text\": \"ME\",\"value\": \"ME\",\"selected\": \"false\"}, {\"text\": \"MD\",\"value\": \"MD\",\"selected\": \"false\"}, {\"text\": \"MA\",\"value\": \"MA\",\"selected\": \"false\"}, {\"text\": \"MH\",\"value\": \"MH\",\"selected\": \"false\"}, {\"text\": \"MI\",\"value\": \"MI\",\"selected\": \"false\"}, {\"text\": \"MN\",\"value\": \"MN\",\"selected\": \"false\"}, {\"text\": \"MP\",\"value\": \"MP\",\"selected\": \"false\"}, {\"text\": \"MS\",\"value\": \"MS\",\"selected\": \"false\"}, {\"text\": \"MO\",\"value\": \"MO\",\"selected\": \"false\"}, {\"text\": \"MT\",\"value\": \"MT\",\"selected\": \"false\"}, {\"text\": \"NE\",\"value\": \"NE\",\"selected\": \"false\"}, {\"text\": \"NV\",\"value\": \"NV\",\"selected\": \"false\"}, {\"text\": \"NH\",\"value\": \"NH\",\"selected\": \"false\"}, {\"text\": \"NJ\",\"value\": \"NJ\",\"selected\": \"false\"}, {\"text\": \"NM\",\"value\": \"NM\",\"selected\": \"false\"}, {\"text\": \"NY\",\"value\": \"NY\",\"selected\": \"false\"}, {\"text\": \"NC\",\"value\": \"NC\",\"selected\": \"false\"}, {\"text\": \"ND\",\"value\": \"ND\",\"selected\": \"false\"}, {\"text\": \"OH\",\"value\": \"OH\",\"selected\": \"false\"}, {\"text\": \"OK\",\"value\": \"OK\",\"selected\": \"false\"}, {\"text\": \"OR\",\"value\": \"OR\",\"selected\": \"false\"}, {\"text\": \"PA\",\"value\": \"PA\",\"selected\": \"false\"}, {\"text\": \"PR\",\"value\": \"PR\",\"selected\": \"false\"}, {\"text\": \"PW\",\"value\": \"PW\",\"selected\": \"false\"}, {\"text\": \"RI\",\"value\": \"RI\",\"selected\": \"false\"}, {\"text\": \"SC\",\"value\": \"SC\",\"selected\": \"false\"}, {\"text\": \"SD\",\"value\": \"SD\",\"selected\": \"false\"}, {\"text\": \"TN\",\"value\": \"TN\",\"selected\": \"false\"}, {\"text\": \"TX\",\"value\": \"TX\",\"selected\": \"false\"}, {\"text\": \"UT\",\"value\": \"UT\",\"selected\": \"false\"}, {\"text\": \"VT\",\"value\": \"VT\",\"selected\": \"false\"}, {\"text\": \"VA\",\"value\": \"VA\",\"selected\": \"false\"}, {\"text\": \"VI\",\"value\": \"VI\",\"selected\": \"false\"}, {\"text\": \"WA\",\"value\": \"WA\",\"selected\": \"false\"}, {\"text\": \"WV\",\"value\": \"WV\",\"selected\": \"false\"}, {\"text\": \"WI\",\"value\": \"WI\",\"selected\": \"false\"}, {\"text\": \"WY\",\"value\": \"WY\",\"selected\": \"false\"}, {\"text\": \"N/A\",\"value\": \"N/A\",\"selected\": \"false\"}],\"value\": \"\",\"tabLabel\": \"JointAHEmployerState\",\"font\": \"arial\"}, {\"listItems\": [{\"text\": \"\",\"value\": \"\",\"selected\": \"true\"}, {\"text\": \"B1/B2\",\"value\": \"B1/B2\",\"selected\": \"false\"}, {\"text\": \"A/G\",\"value\": \"A/G\",\"selected\": \"false\"}, {\"text\": \"C\",\"value\": \"C\",\"selected\": \"false\"}, {\"text\": \"D\",\"value\": \"D\",\"selected\": \"false\"}, {\"text\": \"E\",\"value\": \"E\",\"selected\": \"false\"}, {\"text\": \"F\",\"value\": \"F\",\"selected\": \"false\"}, {\"text\": \"H\",\"value\": \"H\",\"selected\": \"false\"}, {\"text\": \"I\",\"value\": \"I\",\"selected\": \"false\"}, {\"text\": \"J\",\"value\": \"J\",\"selected\": \"false\"}, {\"text\": \"K\",\"value\": \"K\",\"selected\": \"false\"}, {\"text\": \"L\",\"value\": \"L\",\"selected\": \"false\"}, {\"text\": \"M\",\"value\": \"M\",\"selected\": \"false\"}, {\"text\": \"O\",\"value\": \"O\",\"selected\": \"false\"}, {\"text\": \"P\",\"value\": \"P\",\"selected\": \"false\"}, {\"text\": \"Q\",\"value\": \"Q\",\"selected\": \"false\"}, {\"text\": \"R\",\"value\": \"R\",\"selected\": \"false\"}, {\"text\": \"TC\",\"value\": \"TC\",\"selected\": \"false\"}, {\"text\": \"TD\",\"value\": \"TD\",\"selected\": \"false\"}, {\"text\": \"TN\",\"value\": \"TN\",\"selected\": \"false\"}, {\"text\": \"V\",\"value\": \"V\",\"selected\": \"false\"}, {\"text\": \"None\",\"value\": \"None\",\"selected\": \"false\"}],\"value\": \"\",\"tabLabel\": \"JointAHVisaType\",\"font\": \"arial\"}]}}]";

      int tempId=1;

      System.out.println("JsonObjectIterator.JsonObjectIterator()");

      ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
      List<Signer> envelopeDefinition=null;
      try {
         mapper.setSerializationInclusion(Include.NON_EMPTY);
         envelopeDefinition = mapper.readValue(jsonString, new TypeReference<List<Signer>>() {});
         System.out.println();

         System.out.println();

         List<Signer> signers=envelopeDefinition;//envelopeDefinition.getRecipients().getSigners();
         Iterator<Signer> signerItr=signers.iterator();

         while (signerItr.hasNext()) {
            Signer signer = (Signer) signerItr.next();
            Tabs tabs=signer.getTabs();

            Iterator<Text> itrText = tabs.getTextTabs().iterator();
            while (itrText.hasNext()) {
               Text text = (Text) itrText.next();
               //System.out.println(tempId+",Textbox,"+text.getTabLabel()+",,"+signer.getRoleName());

            }

            Iterator<Checkbox> itrChkBox = tabs.getCheckboxTabs().iterator();
            while (itrChkBox.hasNext()) {
               Checkbox checkbox = (Checkbox) itrChkBox.next();
               //System.out.println(tempId+",Checkbox,"+checkbox.getTabLabel()+",,"+signer.getRoleName());
            }

            Iterator<com.docusign.esign.model.List> itrListBox = tabs.getListTabs().iterator();
            while (itrListBox.hasNext()) {
               com.docusign.esign.model.List listbox = (com.docusign.esign.model.List) itrListBox.next();
               //System.out.println(tempId+",Listbox,"+listbox.getTabLabel()+",,"+signer.getRoleName());
            }

            Iterator<RadioGroup> itrRadioGroup = tabs.getRadioGroupTabs().iterator();
            while (itrRadioGroup.hasNext()) {
               RadioGroup radioGroup = (RadioGroup) itrRadioGroup.next();
               //System.out.println(tempId+",Radiobox,"+radioGroup.getGroupName()+",,"+signer.getRoleName());
//					if(radioGroup.getGroupName().equalsIgnoreCase("AccountType")){
//						radioGroup.setRadios(new ArrayList<Radio>());
//						Radio radio=new Radio();
//						radio.setValue("JointTenants");
//						radio.setSelected("true");
//						radioGroup.getRadios().add(radio);
//						System.out.println(tempId+",Radio,"+radioGroup.getGroupName()+",,"+signer.getRoleName());
//					}
               radioGroup.getRadios();

            }

         }





      } catch (JsonParseException e1) {
         // TODO Auto-generated catch block
         e1.printStackTrace();
      } catch (JsonMappingException e1) {
         // TODO Auto-generated catch block
         e1.printStackTrace();
      } catch (IOException e1) {
         // TODO Auto-generated catch block
         e1.printStackTrace();
      }
      return null;
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
   public void temp(){

}
}
