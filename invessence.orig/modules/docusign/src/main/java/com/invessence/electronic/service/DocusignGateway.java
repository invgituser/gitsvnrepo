package com.invessence.electronic.service;

import java.util.*;

import com.invessence.electronic.service.security.LoginCredentials;
import uk.co.techblue.docusign.client.dto.*;
import uk.co.techblue.docusign.client.dto.recipients.*;
import uk.co.techblue.docusign.client.dto.user.DocuSignCredentials;
import uk.co.techblue.docusign.client.envelope.attributes.Status;
import uk.co.techblue.docusign.client.exception.*;
import uk.co.techblue.docusign.client.services.RequestSignatureService;

public class DocusignGateway
{
   public static final String LOGIN_URL = "https://demo.docusign.net/restapi/v2/login_information";

   public static void main(String[] args)
   {
      new DocusignGateway().authenticate(new LoginCredentials());
   }

   public void authenticate(LoginCredentials loginCredentials)
   {
      DocuSignCredentials credentials = new DocuSignCredentials(loginCredentials.getUsername(), loginCredentials.getPassword(), loginCredentials.getIntegratorKey());
      try
      {
         RequestSignatureService signatureService = new RequestSignatureService(LOGIN_URL, credentials);

         // Signature Request email subject and body
         DocumentSignatureRequest signatureRequest = new DocumentSignatureRequest();
         signatureRequest.setEmailBlurb("Something to sign");
         signatureRequest.setEmailSubject("For Lijy to sign");
         signatureRequest.setStatus(Status.sent);

         // Recipients and signatures required
         Signer signer = new Signer();
         signer.setRecipientId("1");
         signer.setEmail("lijyth@gmail.com");
         signer.setName("Lijy Thomas");
         LinkedList<Signer> signers = new LinkedList<Signer>();
         signers.add(signer);
         RecipientCollection recipientCollection = new RecipientCollection();
         recipientCollection.setSigners(signers);

         signatureRequest.setRecipients(recipientCollection);

         List<Document> documentList = new ArrayList<Document>();
         Document document = new Document();
         document.setName("test-signature.txt");
         document.setDocumentId("1");
         document.setPath("C:\\Users\\Administrator\\Desktop\\HPQC.txt");
         documentList.add(document);
         signatureRequest.setDocuments(documentList);

         SignatureResponse response = signatureService.sendDocument(signatureRequest);

         response.getEnvelopeId();
         response.getUri();
         System.out.println(signatureService);
      }

      catch (ServiceInitException e)
      {
         e.printStackTrace();
      }
      catch (SignatureRequestException e)
      {
         e.printStackTrace();
      }

   }
}
