package com.invessence.ws.provider.td.service;

import java.sql.SQLException;
import java.util.*;
import java.util.List;

import com.docusign.esign.model.*;
import com.invessence.service.bean.*;
import com.invessence.service.util.*;
import com.invessence.ws.bean.WSCallResult;
import com.invessence.ws.provider.td.bean.*;
import com.invessence.ws.provider.td.dao.TDDaoLayer;
import com.invessence.ws.provider.td.docusign.DCUtility;
import com.invessence.ws.util.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by abhangp on 8/17/2016.
 */
@Service
public class TDAccountOpeningLayerImpl implements TDAccountOpeningLayer
{
   private static final Logger logger = Logger.getLogger(TDAccountOpeningLayerImpl.class);
   @Autowired
   TDDaoLayer tdDaoLayer;
   @Autowired
   DCUtility dcUtility;

   @Override
   public WSCallResult docuSignRequestHandler(List<DCRequest> dcRequests)
   {
      EnvelopeDefinition envDef = new EnvelopeDefinition();
      String emailSubject=null;
      Map<String,DCTemplateDetails> dcTemplateDetails=(Map<String,DCTemplateDetails>) ServiceParameters.additionalDetails.get(Constant.SERVICES.DOCUSIGN_SERVICES.toString()).get(Constant.ADDITIONAL_DETAILS.TEMPLATE_DETAILS.toString());
      Map<String,ServiceOperationDetails> docuSignOperationDetails=ServiceParameters.serviceDetailsMap.get(Constant.SERVICES.DOCUSIGN_SERVICES.toString()).get(Constant.DOCUSIGN_SERVICES.DOCUSIGN.toString());
      Iterator<DCRequest> itr=dcRequests.iterator();
      while(itr.hasNext())
      {
         DCRequest dcRequest=(DCRequest)itr.next();
         if(emailSubject==null){
            if(dcRequest.getEnvelopeHeading()==null || dcRequest.getEnvelopeHeading().equals("")){
               logger.debug("EnvelopeHeading is empty.");
            }else
            {
               emailSubject = dcRequest.getEnvelopeHeading();
            }
         }

         System.out.println(dcTemplateDetails.size());

         DCTemplateDetails dcTemplateDetail=dcTemplateDetails.get(docuSignOperationDetails.get(dcRequest.getReqType()).getRefValue());

         if(dcRequest.getReqType().equalsIgnoreCase(WSConstants.DocuSignServiceOperations.ACCT_APPLI_NEW.toString())){

            CompositeTemplate compositeTemplate=accountApplication(dcRequest,dcTemplateDetail);
            envDef.getCompositeTemplates().add(compositeTemplate);
         }
         else if(dcRequest.getReqType().equalsIgnoreCase(WSConstants.DocuSignServiceOperations.ACCT_TRAN_NEW.toString())){
            agreementDocuments(dcRequest);
         }
         else if(dcRequest.getReqType().equalsIgnoreCase(WSConstants.DocuSignServiceOperations.IRA_APPLI_NEW.toString())){
            System.out.println("*********************");
            System.out.println("dcTemplateDetail :"+dcTemplateDetail);
            CompositeTemplate compositeTemplate=accountApplication(dcRequest,dcTemplateDetail);
            envDef.getCompositeTemplates().add(compositeTemplate);
         }
         else if(dcRequest.getReqType().equalsIgnoreCase(WSConstants.DocuSignServiceOperations.IRA_MOVE_MONEY_NEW.toString())
            ||dcRequest.getReqType().equalsIgnoreCase(WSConstants.DocuSignServiceOperations.IRA_MOVE_MONEY_CHANGE.toString())
            ||dcRequest.getReqType().equalsIgnoreCase(WSConstants.DocuSignServiceOperations.IRA_MOVE_MONEY_REMOVE.toString())){
            
         }
         else if(dcRequest.getReqType().equalsIgnoreCase(WSConstants.DocuSignServiceOperations.IRA_QRP_BENE_NEW.toString())){
            
         }
         else if(dcRequest.getReqType().equalsIgnoreCase(WSConstants.DocuSignServiceOperations.MOVE_MONEY_NEW.toString())
            || dcRequest.getReqType().equalsIgnoreCase(WSConstants.DocuSignServiceOperations.MOVE_MONEY_CHANGE.toString())
            ||dcRequest.getReqType().equalsIgnoreCase(WSConstants.DocuSignServiceOperations.MOVE_MONEY_REMOVE.toString())){
            CompositeTemplate compositeTemplate=moveMoney(dcRequest, dcTemplateDetail);
            envDef.getCompositeTemplates().add(compositeTemplate);

         }
         else if(dcRequest.getReqType().equalsIgnoreCase(WSConstants.DocuSignServiceOperations.ELEC_FUND_TRAN_CHANGE.toString())
         ||dcRequest.getReqType().equalsIgnoreCase(WSConstants.DocuSignServiceOperations.ELEC_FUND_TRAN_NEW.toString())
         ||dcRequest.getReqType().equalsIgnoreCase(WSConstants.DocuSignServiceOperations.ELEC_FUND_TRAN_REPLACE.toString())){
            
         }
      }
      envDef.setEmailSubject(emailSubject);
      envDef.setStatus("sent");

      //dcUtility.createEnvelope(envDef);

      return null;
   }

   private void agreementDocuments(DCRequest dcRequest){
      System.out.println("TDAccountOpeningLayerImpl.agreementDocumnets");
      System.out.println("dcRequest = [" + dcRequest + "]");
      AcctDetails acctDetails= null;
      Map<String,DCDocumentDetails> dcDocumentDetails=(Map<String,DCDocumentDetails>) ServiceParameters.additionalDetails.get(Constant.SERVICES.DOCUSIGN_SERVICES.toString()).get(Constant.ADDITIONAL_DETAILS.DOCUMENT_DETAILS
                                                                                                                                                                                     .toString());
      EnvelopeDefinition envDef = new EnvelopeDefinition();
      String emailSubject=dcRequest.getEnvelopeHeading();
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

      InlineTemplate inlineTemplate = dcUtility.getInlineTemplateAgreements(dcDocumentDetails, acctDetails, acctOwnerDetails);

      CompositeTemplate compositeTemplate = new CompositeTemplate();

      compositeTemplate.setInlineTemplates(new ArrayList<InlineTemplate>());
      compositeTemplate.getInlineTemplates().add(inlineTemplate);

      envDef.getCompositeTemplates().add(compositeTemplate);
      envDef.setEmailSubject(emailSubject);
      envDef.setStatus("sent");

      dcUtility.createEnvelope(envDef);
   }


   private CompositeTemplate moveMoney(DCRequest dcRequest, DCTemplateDetails dcTemplateDetail){
    System.out.println("TDAccountOpeningLayerImpl.moveMoney");
    System.out.println("dcRequest = [" + dcRequest + "]");
    AcctDetails acctDetails= null;
    List<AcctOwnerDetails> acctOwnerDetails=null;
      MoveMoneyDetails moveMoneyDetails=null;
      List<MMAchBankDetails> mmAchBankDetails=null;
      List<MMInternalTransferDetails> mmInternalTransferDetails=null;
      List<MMFedwireAcctDetails> mmFedwireAcctDetails=null;
      InlineTemplate inlineTemplate = dcUtility.getInlineTemplate("1");
    try
    {
       acctDetails = tdDaoLayer.getAcctDetails(dcRequest.getAcctnum(),dcRequest.getReqId(), false);
       acctOwnerDetails=tdDaoLayer.getAcctOwnerDetails(dcRequest.getAcctnum(),dcRequest.getReqId(), false);
       acctDetails.setAcctOwnerDetails(acctOwnerDetails);
       moveMoneyDetails=tdDaoLayer.getMoveMoneyDetails(dcRequest.getAcctnum(),dcRequest.getEventNum());
       if(moveMoneyDetails==null){
          logger.debug("Move Money Details are not available for acctNum :"+dcRequest.getAcctnum()+" eventNum :"+dcRequest.getReqId());
          System.out.println("Move Money Details are not available for acctNum :"+dcRequest.getAcctnum()+" eventNum :"+dcRequest.getReqId());
       }else
       {
          if (moveMoneyDetails.getPayMethod().equalsIgnoreCase("ACH"))
          {
             mmAchBankDetails = tdDaoLayer.getMMAchBankDetails(moveMoneyDetails.getAcctnum(), moveMoneyDetails.getMoveMoneyPayMethId());
             inlineTemplate.setRecipients(dcUtility.getRecipientsMoveMoney(dcTemplateDetail, acctDetails, acctOwnerDetails, moveMoneyDetails, mmAchBankDetails));
          }
          else if (moveMoneyDetails.getPayMethod().equalsIgnoreCase("FedWires"))
          {
             mmFedwireAcctDetails = tdDaoLayer.getMMFedwireAcctDetails(moveMoneyDetails.getAcctnum(), moveMoneyDetails.getMoveMoneyPayMethId());
             inlineTemplate.setRecipients(dcUtility.getRecipientsMoveMoney(dcTemplateDetail, acctDetails, acctOwnerDetails, moveMoneyDetails, mmFedwireAcctDetails));
          }
          else if (moveMoneyDetails.getPayMethod().equalsIgnoreCase("3rdPartyInternal"))
          {
             mmInternalTransferDetails = tdDaoLayer.getMMInternalTransferDetails(moveMoneyDetails.getAcctnum(), moveMoneyDetails.getMoveMoneyPayMethId());
             inlineTemplate.setRecipients(dcUtility.getRecipientsMoveMoney(dcTemplateDetail, acctDetails, acctOwnerDetails, moveMoneyDetails, mmInternalTransferDetails));
          }
       }

    }
    catch (SQLException e)
    {
       e.printStackTrace();
    }


    ServerTemplate serverTemplate =dcUtility.getServerTemplate("1",dcTemplateDetail);

    CompositeTemplate compositeTemplate = new CompositeTemplate();
    compositeTemplate.setServerTemplates(new ArrayList<ServerTemplate>());
    compositeTemplate.getServerTemplates().add(serverTemplate);

    compositeTemplate.setInlineTemplates(new ArrayList<InlineTemplate>());
    compositeTemplate.getInlineTemplates().add(inlineTemplate);
    return compositeTemplate;
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
