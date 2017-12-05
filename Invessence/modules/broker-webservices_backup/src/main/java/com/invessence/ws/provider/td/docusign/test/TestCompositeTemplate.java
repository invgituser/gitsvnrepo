package com.invessence.ws.provider.td.docusign.test;

/**
 * Created by abhangp on 6/13/2016.
 */
/* CoreRecipes.java
 *
 * Simple Class that demonstrates how to accomplish various REST API use-cases.
 */

// DocuSign imports
import com.docusign.esign.api.*;
import com.docusign.esign.client.*;
import com.docusign.esign.model.*;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

// additional imports
import java.util.List;
import java.util.ArrayList;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import javax.xml.bind.DatatypeConverter;
import java.util.Iterator;

public class TestCompositeTemplate {

	String UserName = "abhangp@invessence.com";// "[EMAIL]";
	String Password = "DocuSign@2015";// "[PASSWORD]";
	String IntegratorKey = "289a45ad-018b-449c-98f8-4c7063468afd";// "[INTEGRATOR_KEY]";

	// for production environment update to "www.docusign.net/restapi"
	public static final String BaseUrl = "https://demo.docusign.net/restapi";

	/*****************************************************************************************************************
	 * RequestSignatureFromTemplate()
	 *
	 * This recipe demonstrates how to request a signature from a template in
	 * your account. Templates are design-time objects that contain documents,
	 * tabs, routing, and recipient roles. To run this recipe you need to
	 * provide a valid templateId from your account along with a role name that
	 * the template has configured.
	 ******************************************************************************************************************/
	public void RequestSignatureFromTemplate() {

		// TODO: Enter signer information and template info from a template in
		// your account
		String signerName = "Abhang A. Patil";// "[SIGNER_NAME]";
		String signerEmail = "abhang.patil@gmail.com";// "[SIGNER_EMAIL]";
		String templateId = "14c631b8-6ea2-4eaa-b507-040414e3e8fe";// "e21c6a62-8527-40d8-a006-26845ca2a1d5";//"[TEMPLATE_ID]";
		String templateRoleName = "Client";// "[TEMPLATE_ROLE_NAME]";
		
		String signerName2 = "Abhang Patil";// "[SIGNER_NAME]";
		String signerEmail2 = "abhangp@mindcraft.in";// "[SIGNER_EMAIL]";
		String templateRoleName2 = "Joint";// "[TEMPLATE_ROLE_NAME]";
		String templateId2 = "e76c30f0-5688-4ad8-8cc2-8187e0106a7e";

		// initialize the api client
		ApiClient apiClient = new ApiClient();
		apiClient.setBasePath(BaseUrl);

		// create JSON formatted auth header
		String creds = "{\"Username\":\"" + UserName + "\",\"Password\":\"" + Password + "\",\"IntegratorKey\":\""
				+ IntegratorKey + "\"}";
		apiClient.addDefaultHeader("X-DocuSign-Authentication", creds);

		// assign api client to the Configuration object
		Configuration.setDefaultApiClient(apiClient);

		// list of user account(s)
		List<LoginAccount> loginAccounts = null;

		// ===============================================================================
		// Step 1: Login() API
		// ===============================================================================
		try {
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
		} catch (ApiException ex) {
			System.out.println("Exception: " + ex);
		}

		// ===============================================================================
		// Step 2: Create Envelope API (AKA Signature Request) from a Template
		// ===============================================================================

		// create a new envelope object that we will manage the signature
		// request through
		EnvelopeDefinition envDef = new EnvelopeDefinition();
		envDef.setEmailSubject("Please sign this document sent from Java SDK)");
		
		Tabs tabs = new Tabs();
//		String jsonString = "[{\"isPaymentAmount\":\"false\",\"validationPattern\":\"\",\"validationMessage\":\"\",\"shared\":\"false\",\"requireInitialOnSharedChange\":\"false\",\"requireAll\":\"false\",\"name\":\"Text\",\"value\":\"\",\"required\":\"false\",\"locked\":\"false\",\"concealValueOnDocument\":\"false\",\"disableAutoSize\":\"false\",\"tabLabel\":\"City\",\"font\":\"arial\",\"bold\":\"false\",\"italic\":\"false\",\"underline\":\"false\",\"fontColor\":\"black\",\"fontSize\":\"size9\",\"documentId\":\"67577759\",\"recipientId\":\"92873670\",\"pageNumber\":\"1\",\"xPosition\":\"129\",\"yPosition\":\"476\",\"width\":\"120\",\"height\":\"22\",\"tabId\":\"2001a3cc-c2d7-4eef-9eeb-f93a0179a881\",\"tabType\":\"text\"},{\"isPaymentAmount\":\"false\",\"validationPattern\":\"\",\"validationMessage\":\"\",\"shared\":\"false\",\"requireInitialOnSharedChange\":\"false\",\"requireAll\":\"false\",\"name\":\"Text\",\"value\":\"\",\"required\":\"false\",\"locked\":\"false\",\"concealValueOnDocument\":\"false\",\"disableAutoSize\":\"false\",\"tabLabel\":\"State\",\"font\":\"arial\",\"bold\":\"false\",\"italic\":\"false\",\"underline\":\"false\",\"fontColor\":\"black\",\"fontSize\":\"size9\",\"documentId\":\"67577759\",\"recipientId\":\"92873670\",\"pageNumber\":\"1\",\"xPosition\":\"352\",\"yPosition\":\"475\",\"width\":\"120\",\"height\":\"22\",\"tabId\":\"7ceef5c9-420e-4334-bd72-78f0c9fcd6d6\",\"tabType\":\"text\"},{\"isPaymentAmount\":\"false\",\"validationPattern\":\"\",\"validationMessage\":\"\",\"shared\":\"false\",\"requireInitialOnSharedChange\":\"false\",\"requireAll\":\"false\",\"name\":\"Text\",\"value\":\"\",\"required\":\"false\",\"locked\":\"false\",\"concealValueOnDocument\":\"false\",\"disableAutoSize\":\"false\",\"tabLabel\":\"ZipCode\",\"font\":\"arial\",\"bold\":\"false\",\"italic\":\"false\",\"underline\":\"false\",\"fontColor\":\"black\",\"fontSize\":\"size9\",\"documentId\":\"67577759\",\"recipientId\":\"92873670\",\"pageNumber\":\"1\",\"xPosition\":\"492\",\"yPosition\":\"471\",\"width\":\"120\",\"height\":\"22\",\"tabId\":\"58a30164-76f5-432b-bbcd-3b49a046aa85\",\"tabType\":\"text\"}]";
		String jsonString="[{\"tabLabel\": \"State\",\"value\": \"\"}, {\"tabLabel\": \"ZipCode\",\"value\": \"\"}, {\"tabLabel\": \"City\",\"value\": \"\"}]";
		ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		// List<Text> textTabs=new ArrayList<Text>();
		List<Text> textTabs;
		try {
			textTabs = mapper.readValue(jsonString, new TypeReference<List<Text>>() {});
			Iterator<Text> itrText = textTabs.iterator();
			while (itrText.hasNext()) {
				Text text = (Text) itrText.next();
				if (text.getTabLabel().equalsIgnoreCase("City")) {
					text.setValue("Mumbai");
				} else if (text.getTabLabel().equalsIgnoreCase("State")) {
					text.setValue("Maharashttra");
				} else if (text.getTabLabel().equalsIgnoreCase("ZipCode")) {
					text.setValue("400706");
				}if (text.getTabLabel().equalsIgnoreCase("Address")) {
					text.setValue("PrePopulated Address");
				}
				System.out.println("text :" + text);
			}
			tabs.setTextTabs(textTabs);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Signer signer = new Signer();
		signer.setEmail(signerEmail);
		signer.setName(signerName);
		signer.roleName(templateRoleName);
		signer.setRecipientId("1");
		
		signer.idCheckConfigurationName("ID Check $");
		
		IdCheckInformationInput idCheckInformationInput=new IdCheckInformationInput();
		AddressInformationInput addressInformationInput=new AddressInformationInput();
		AddressInformation addressInformation=new AddressInformation();
		addressInformation.setStreet1("6948 Shepherd");
		addressInformation.setCity("Russelville");
		addressInformation.setState("AR");
		addressInformation.zip("72801");
		
		idCheckInformationInput.setAddressInformationInput(addressInformationInput);

		signer.idCheckInformationInput(idCheckInformationInput);
		signer.setTabs(tabs);
		
		Signer signer2 = new Signer();
		signer2.setEmail(signerEmail2);
		signer2.setName(signerName2);
		signer2.roleName(templateRoleName2);
		signer2.setRecipientId("2");
		signer2.setTabs(tabs);

		InlineTemplate inlineTemplate = new InlineTemplate();
		inlineTemplate.setRecipients(new Recipients());
		inlineTemplate.getRecipients().setSigners(new ArrayList<Signer>());
		inlineTemplate.getRecipients().getSigners().add(signer);
		inlineTemplate.getRecipients().getSigners().add(signer2);
		inlineTemplate.setSequence("1");
		
		Signer signer3 = new Signer();
		signer3.setEmail(signerEmail);
		signer3.setName(signerName);
		signer3.roleName(templateRoleName);
		signer3.setRecipientId("1");
		
//		Signer signer4 = new Signer();
//		signer4.setEmail(signerEmail2);
//		signer4.setName(signerName2);
//		signer4.roleName(templateRoleName2);
//		signer4.setRecipientId("2");
		
		InlineTemplate inlineTemplate2 = new InlineTemplate();
		inlineTemplate2.setRecipients(new Recipients());
		inlineTemplate2.getRecipients().setSigners(new ArrayList<Signer>());
		inlineTemplate2.getRecipients().getSigners().add(signer3);
//		inlineTemplate2.getRecipientsAcctCreation().getSigners().add(signer4);
		inlineTemplate2.setSequence("2");

		List<InlineTemplate> inlineTemplates = new ArrayList<InlineTemplate>();
		inlineTemplates.add(inlineTemplate);
		
		ServerTemplate serverTemplate = new ServerTemplate();
		serverTemplate.setTemplateId(templateId);
		serverTemplate.setSequence("1");
		
		ServerTemplate serverTemplate2 = new ServerTemplate();
		serverTemplate2.setTemplateId(templateId2);
		serverTemplate2.setSequence("2");
		
		List<ServerTemplate> serverTemplates = new ArrayList<ServerTemplate>();		
		serverTemplates.add(serverTemplate);
		
		String SignTest1File = "D:\\DocuSign\\Db.pdf";//"D:\\Project\\Abhang\\RnD\\DocuSign\\docs\\IntelliJIDEA_ReferenceCard.pdf";//"[PATH/TO/DOCUMENT/TEST.PDF]";

		byte[] fileBytes = null;

	      try
	      {
	         String currentDir = System.getProperty("user.dir");
	         // read file from a local directory
	         Path path = Paths.get(SignTest1File);
	         fileBytes = Files.readAllBytes(path);
	      }
	      catch (IOException ioExcp)
	      {
	         // handle error
	         System.out.println("Exception: " + ioExcp);
	         return;
	      }
	      
	      InlineTemplate inlineTemplate3=new InlineTemplate();
		Document doc = new Document();
	      String base64Doc = DatatypeConverter.printBase64Binary(fileBytes);//Base64.getEncoder().encodeToString(fileBytes);
	      doc.setDocumentBase64(base64Doc);
	      doc.setName("Service.png");    // can be different from actual file name
	      doc.setDocumentId("1");
	      doc.setFileExtension("pdf");

	      List<Document> docs = new ArrayList<Document>();
	      docs.add(doc);
//	      envDef.setDocuments(docs);
//		compositeTemplate.setDocument(docs);
	      
			inlineTemplate3.setRecipients(new Recipients());
			inlineTemplate3.getRecipients().setSigners(new ArrayList<Signer>());
			inlineTemplate3.getRecipients().getSigners().add(signer3);
//			inlineTemplate2.getRecipientsAcctCreation().getSigners().add(signer4);
			inlineTemplate3.setSequence("2");
	      inlineTemplate3.setDocuments(docs);
		
	      
	      
		CompositeTemplate compositeTemplate = new CompositeTemplate();
		compositeTemplate.setServerTemplates(serverTemplates);		
		compositeTemplate.setInlineTemplates(inlineTemplates);
		
		CompositeTemplate compositeTemplate2 = new CompositeTemplate();
		compositeTemplate2.setServerTemplates(new ArrayList<ServerTemplate>());	
		compositeTemplate2.getServerTemplates().add(serverTemplate2);
		
		compositeTemplate2.setInlineTemplates(new ArrayList<InlineTemplate>());
		compositeTemplate2.getInlineTemplates().add(inlineTemplate2);
		
		CompositeTemplate compositeTemplate3 = new CompositeTemplate();
		compositeTemplate3.setInlineTemplates(new ArrayList<InlineTemplate>());
		compositeTemplate3.getInlineTemplates().add(inlineTemplate3);	

		
		envDef.setCompositeTemplates(new ArrayList<CompositeTemplate>());
		envDef.getCompositeTemplates().add(compositeTemplate);
		envDef.getCompositeTemplates().add(compositeTemplate2);
		envDef.getCompositeTemplates().add(compositeTemplate3);
		
		

		// send the envelope by setting |status| to "sent". To save as a draft
		// set to "created"
		envDef.setStatus("sent");
		

		try {
			mapper.setSerializationInclusion(Include.NON_NULL);
			mapper.setSerializationInclusion(Include.NON_EMPTY);
			System.out.println(mapper.writeValueAsString(envDef));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			// use the |accountId| we retrieved through the Login API to create
			// the Envelope
			String accountId = loginAccounts.get(0).getAccountId();

			// instantiate a new EnvelopesApi object
			EnvelopesApi envelopesApi = new EnvelopesApi();

			// call the createEnvelope() API
			// envDef.setCustomFields(customFields);
			EnvelopeSummary envelopeSummary = envelopesApi.createEnvelope(accountId, envDef);

			 // set the url where you want the recipient to go once they are done signing
//	         RecipientViewRequest returnUrl = new RecipientViewRequest();
//	         returnUrl.setReturnUrl("https://www.docusign.com/devcenter");
//	         returnUrl.setAuthenticationMethod("email");
//
//	         // recipient information must match embedded recipient info we provided in step #2
//	         returnUrl.setEmail(signerEmail);
//	         returnUrl.setUserName(signerName);
//	         returnUrl.setRecipientId("1");
//	         returnUrl.setClientUserId("1001");
//
//	         // call the CreateRecipientView API
//	         ViewUrl recipientView = envelopesApi.createRecipientView(accountId, envelopeId.toString(), returnUrl);
			
			System.out.println("EnvelopeSummary: " + envelopeSummary);
		} catch (ApiException ex) {
			System.out.println("Exception: " + ex);
		}
	} // end RequestSignatureFromTemplate()

	// *****************************************************************
	// *****************************************************************
	// main() -
	// *****************************************************************
	// *****************************************************************
	public static void main(String args[]) {

		TestCompositeTemplate recipes = new TestCompositeTemplate();

		// Test #1
		System.out.println("Running test #1...\n");
		recipes.RequestSignatureFromTemplate();
		System.out.println("\nTest #1 Complete.\n-----------------");

	} // end main()
} // end class
