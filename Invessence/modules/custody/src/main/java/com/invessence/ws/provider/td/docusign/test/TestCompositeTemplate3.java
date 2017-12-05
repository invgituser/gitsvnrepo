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
import java.util.ListIterator;
import java.util.ArrayList;
import java.util.TimeZone;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import java.io.File;
import javax.xml.bind.DatatypeConverter;
import java.util.Iterator;

public class TestCompositeTemplate3 {

	String UserName = "abhangp@invessence.com";// "[EMAIL]";
	String Password = "DocuSign@2015";// "[PASSWORD]";
	String IntegratorKey = "289a45ad-018b-449c-98f8-4c7063468afd";// "[INTEGRATOR_KEY]";
	
//	 public static final String UserName = "prashant@invessence.com";//"[EMAIL]";
//	   public static final String Password = "Inv3ss3nc3!";//"[PASSWORD]";
//
//	   // TODO: Enter your Integrator Key (aka API key), created through your developer sandbox preferences
//	   public static final String IntegratorKey = "TDAM-d7feb45c-e88d-4c20-b5bd-1dcd9a9d6f56";//"[INTEGRATOR_KEY]";

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
		String signerName = "Henry Ssassafras";// "[SIGNER_NAME]";
		String signerEmail = "abhang.patil@gmail.com";// "[SIGNER_EMAIL]";
		String templateId = "DD085BAB-0148-4D64-82D2-4ED04FDFE007";// "e21c6a62-8527-40d8-a006-26845ca2a1d5";// "e21c6a62-8527-40d8-a006-26845ca2a1d5";//"[TEMPLATE_ID]";
		String templateRoleName = "Client";// "[TEMPLATE_ROLE_NAME]";

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
		
		
		ServerTemplate serverTemplate = new ServerTemplate();
		serverTemplate.setTemplateId(templateId);
		serverTemplate.setSequence("1");
		
		List<ServerTemplate> serverTemplates = new ArrayList<ServerTemplate>();
		
		serverTemplates.add(serverTemplate);
		
		
		Tabs tabs = new Tabs();
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
		
		addressInformationInput.setAddressInformation(addressInformation);
		
		Ssn4InformationInput ssn4InformationInput=new Ssn4InformationInput();
		ssn4InformationInput.setSsn4("7438");
		
		DobInformationInput dobInformationInput=new DobInformationInput();
		
		dobInformationInput.setDateOfBirth("09/11/1983");
		
		idCheckInformationInput.setAddressInformationInput(addressInformationInput);
		idCheckInformationInput.setSsn4InformationInput(ssn4InformationInput);
		idCheckInformationInput.setDobInformationInput(dobInformationInput);

		signer.idCheckInformationInput(idCheckInformationInput);

		signer.setTabs(tabs);

		InlineTemplate inlineTemplate = new InlineTemplate();

		inlineTemplate.setRecipients(new Recipients());
		inlineTemplate.getRecipients().setSigners(new ArrayList<Signer>());
		inlineTemplate.getRecipients().getSigners().add(signer);
		inlineTemplate.setSequence("1");

		List<InlineTemplate> inlineTemplates = new ArrayList<InlineTemplate>();
		inlineTemplates.add(inlineTemplate);
		
		CompositeTemplate compositeTemplate = new CompositeTemplate();
		compositeTemplate.setServerTemplates(serverTemplates);
		
		compositeTemplate.setInlineTemplates(inlineTemplates);	
		
		
		
		envDef.setCompositeTemplates(new ArrayList<CompositeTemplate>());
		envDef.getCompositeTemplates().add(compositeTemplate);

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
			EnvelopeSummary envelopeSummary = envelopesApi.createEnvelope("1666286", envDef);

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

		TestCompositeTemplate3 recipes = new TestCompositeTemplate3();

		// Test #1
		System.out.println("Running test #1...\n");
		recipes.RequestSignatureFromTemplate();
		System.out.println("\nTest #1 Complete.\n-----------------");

	} // end main()
} // end class
