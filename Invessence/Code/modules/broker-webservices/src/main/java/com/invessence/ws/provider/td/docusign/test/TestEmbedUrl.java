package com.invessence.ws.provider.td.docusign.test;

import com.docusign.esign.api.EnvelopesApi;
import com.docusign.esign.client.ApiException;
import com.docusign.esign.model.RecipientViewRequest;
import com.docusign.esign.model.ViewUrl;

public class TestEmbedUrl {

	public static void main(String[] args) {
		
		String signerName = "Abhang A. Patil";// "[SIGNER_NAME]";
		String signerEmail = "abhang.patil@gmail.com";// "[SIGNER_EMAIL]";
		
		EnvelopesApi envelopesApi = new EnvelopesApi();
		
		 // set the url where you want the recipient to go once they are done signing
        RecipientViewRequest returnUrl = new RecipientViewRequest();
        returnUrl.setReturnUrl("https://www.docusign.com/devcenter");
        
        // recipient information must match embedded recipient info we provided in step #2
        
        returnUrl.setReturnUrl("https://www.docusign.com/devcenter");
        returnUrl.setAuthenticationMethod("email");

        // recipient information must match embedded recipient info we provided in step #2
        returnUrl.setEmail(signerEmail);
        returnUrl.setUserName(signerName);
        returnUrl.setRecipientId("1");
        returnUrl.setClientUserId("1001");

        // call the CreateRecipientView API
        try {
			ViewUrl recipientView = envelopesApi.createRecipientView("1666286", "d887d827-3e3a-4ca0-b545-559f3c33066d", returnUrl);
			System.out.println(recipientView);
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
