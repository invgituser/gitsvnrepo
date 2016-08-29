package com.invessence.ws.provider.td.service;

import java.sql.SQLException;
import java.util.*;
import java.util.List;

import com.docusign.esign.model.*;
import com.invessence.service.bean.DCTemplateDetails;
import com.invessence.service.util.*;
import com.invessence.ws.provider.td.bean.*;
import com.invessence.ws.provider.td.dao.TDDaoLayer;
import com.invessence.ws.provider.td.docusign.DCUtility;
import com.invessence.ws.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by abhangp on 8/17/2016.
 */
@Service
public class TDAccountOpeningLayerImpl implements TDAccountOpeningLayer
{
   @Autowired
   TDDaoLayer tdDaoLayer;
   @Autowired
   DCUtility dcUtility;

   @Override
   public CompositeTemplate docuSignRequestHandler(List<DCRequest> dcRequests)
   {
      EnvelopeDefinition envDef = new EnvelopeDefinition();
      envDef.setEmailSubject("Please sign Account Application document");
      Map<String,DCTemplateDetails> dcTemplateDetails=(Map<String,DCTemplateDetails>) ServiceParameters.additionalDetails.get(Constant.SERVICES.DOCUSIGN_SERVICES.toString());

      Iterator<DCRequest> itr=dcRequests.iterator();
      while(itr.hasNext())
      {
         DCRequest dcRequest=(DCRequest)itr.next();
         if(dcRequest.getReqType().equalsIgnoreCase(WSConstants.DocuSignServiceOperations.ACCT_APPLI_NEW.toString())){
            DCTemplateDetails dcTemplateDetail=dcTemplateDetails.get("BB_ACCT_APPLI");
            CompositeTemplate compositeTemplate=accountApplication(dcRequest,dcTemplateDetail);
            //System.out.println("compositeTemplate = " + compositeTemplate);
            envDef.getCompositeTemplates().add(compositeTemplate);
         }
      }
      envDef.setStatus("sent");

      dcUtility.createEnvelope(envDef);

      return null;
   }

 private CompositeTemplate accountApplication(DCRequest dcRequest, DCTemplateDetails dcTemplateDetail){
    System.out.println("TDAccountOpeningLayerImpl.accountApplication");
    System.out.println("dcRequest = [" + dcRequest + "]");

//      System.out.println(dcTemplateDetail.getDcTemplateMappings());
//      System.out.println("genericDetails :"+ServiceParameters.genericDetails.get(Constant.GENERIC_DETAILS.LOOKUP_DETAILS.toString()));
//      String signerName = "Abhang A. Patil";// "[SIGNER_NAME]";
//      String signerEmail = "abhang.patil@gmail.com";// "[SIGNER_EMAIL]";
//      String templateId = "14c631b8-6ea2-4eaa-b507-040414e3e8fe";// "e21c6a62-8527-40d8-a006-26845ca2a1d5";//"[TEMPLATE_ID]";
//      String templateRoleName = "Client";// "
//      Signer signer3 = new Signer();
//      signer3.setEmail(signerEmail);
//      signer3.setName(signerName);
//      signer3.roleName(templateRoleName);
//      signer3.setRecipientId("1");

    //      InlineTemplate inlineTemplate = new InlineTemplate();
//      inlineTemplate.setRecipients(new Recipients());
//      inlineTemplate.getRecipientsAcctCreation().setSigners(new ArrayList<Signer>());
//      inlineTemplate.getRecipientsAcctCreation().getSigners().add(signer3);
////		inlineTemplate2.getRecipientsAcctCreation().getSigners().add(signer4);
//      inlineTemplate.setSequence("2");

//      ServerTemplate serverTemplate = new ServerTemplate();
//      serverTemplate.setTemplateId(templateId);
//      serverTemplate.setSequence("2");
    AcctDetails acctDetails= null;
    List<AcctOwnerDetails> acctOwnerDetails=null;
    try
    {
       acctDetails = tdDaoLayer.getAcctDetails(dcRequest.getAcctnum(),dcRequest.getReqId(), false);
       acctOwnerDetails=tdDaoLayer.getAcctOwnerDetails(dcRequest.getAcctnum(),dcRequest.getReqId(), true);
       acctDetails.setAcctOwnerDetails(acctOwnerDetails);

    }
    catch (SQLException e)
    {
       e.printStackTrace();
    }

    InlineTemplate inlineTemplate = dcUtility.getInlineTemplate("1");
    inlineTemplate.setRecipients(dcUtility.getRecipientsAcctCreation(dcTemplateDetail, acctDetails, acctOwnerDetails));
    ServerTemplate serverTemplate =dcUtility.getServerTemplate("1",dcTemplateDetail);

    CompositeTemplate compositeTemplate = new CompositeTemplate();
    compositeTemplate.setServerTemplates(new ArrayList<ServerTemplate>());
    compositeTemplate.getServerTemplates().add(serverTemplate);

    compositeTemplate.setInlineTemplates(new ArrayList<InlineTemplate>());
    compositeTemplate.getInlineTemplates().add(inlineTemplate);
    return compositeTemplate;
 }
}
