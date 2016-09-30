package com.invessence.ws.provider.td.service;

import java.sql.SQLException;
import java.util.*;
import java.util.List;

import com.docusign.esign.model.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.invessence.service.bean.*;
import com.invessence.service.util.*;
import com.invessence.ws.bean.*;
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
      WSCallResult wsCallResult = null;
      EnvelopeDefinition envDef = new EnvelopeDefinition();
      String emailSubject = null;
      Long acctNum = null;
      int eventNum = 0;
      StringBuilder requestIds = new StringBuilder();
      try{
      Map<String, DCTemplateDetails> dcTemplateDetails = (Map<String, DCTemplateDetails>) ServiceParameters.additionalDetails.get(Constant.SERVICES.DOCUSIGN_SERVICES.toString()).get(Constant.ADDITIONAL_DETAILS.TEMPLATE_DETAILS.toString());
      Map<String, ServiceOperationDetails> docuSignOperationDetails = ServiceParameters.serviceDetailsMap.get(Constant.SERVICES.DOCUSIGN_SERVICES.toString()).get(Constant.DOCUSIGN_SERVICES.DOCUSIGN.toString());

      Iterator<DCRequest> itr = dcRequests.iterator();
      int reqCounter = 1;
      envDef.setCompositeTemplates(new ArrayList<CompositeTemplate>());
      DCRequest dcReqExtDoc = null;
      while (itr.hasNext())
      {
         DCRequest dcRequest = (DCRequest) itr.next();
         if (emailSubject == null)
         {
            if (dcRequest.getEnvelopeHeading() == null || dcRequest.getEnvelopeHeading().equals(""))
            {
               logger.debug("EnvelopeHeading is empty.");
            }
            else
            {
               emailSubject = dcRequest.getEnvelopeHeading();
               acctNum = dcRequest.getAcctnum();
               eventNum = dcRequest.getEventNum();
            }
         }
         requestIds = requestIds.append(dcRequest.getReqId() + ",");

         DCTemplateDetails dcTemplateDetail = dcTemplateDetails.get(docuSignOperationDetails.get(dcRequest.getReqType()).getRefValue());

         if (dcRequest.getReqType().equalsIgnoreCase(WSConstants.DocuSignServiceOperations.ACCT_APPLI_NEW.toString()))
         {
            CompositeTemplate compositeTemplate = accountApplication(dcRequest, dcTemplateDetail, "" + reqCounter);
            if(compositeTemplate==null) throw new EnvelopeCreationException("EnvelopeCreationException for acctNum ="+dcRequest.getAcctnum()+" reqId = "+dcRequest.getReqId());
            envDef.getCompositeTemplates().add(compositeTemplate);
            dcReqExtDoc = dcRequest;
         }
         else if (dcRequest.getReqType().equalsIgnoreCase(WSConstants.DocuSignServiceOperations.IRA_APPLI_NEW.toString()))
         {
            CompositeTemplate compositeTemplate = accountApplication(dcRequest, dcTemplateDetail, "" + reqCounter);
            if(compositeTemplate==null) throw new EnvelopeCreationException("EnvelopeCreationException for acctNum ="+dcRequest.getAcctnum()+" reqId = "+dcRequest.getReqId());
            envDef.getCompositeTemplates().add(compositeTemplate);
            dcReqExtDoc = dcRequest;
         }
         else if (dcRequest.getReqType().equalsIgnoreCase(WSConstants.DocuSignServiceOperations.IRA_MOVE_MONEY_NEW.toString())
            || dcRequest.getReqType().equalsIgnoreCase(WSConstants.DocuSignServiceOperations.IRA_MOVE_MONEY_CHANGE.toString())
            || dcRequest.getReqType().equalsIgnoreCase(WSConstants.DocuSignServiceOperations.IRA_MOVE_MONEY_REMOVE.toString()))
         {
            CompositeTemplate compositeTemplate = moveMoney(dcRequest, dcTemplateDetail, "" + reqCounter);
            if(compositeTemplate==null) throw new EnvelopeCreationException("EnvelopeCreationException for acctNum ="+dcRequest.getAcctnum()+" reqId = "+dcRequest.getReqId());
            envDef.getCompositeTemplates().add(compositeTemplate);
         }
         else if (dcRequest.getReqType().equalsIgnoreCase(WSConstants.DocuSignServiceOperations.MOVE_MONEY_NEW.toString())
            || dcRequest.getReqType().equalsIgnoreCase(WSConstants.DocuSignServiceOperations.MOVE_MONEY_CHANGE.toString())
            || dcRequest.getReqType().equalsIgnoreCase(WSConstants.DocuSignServiceOperations.MOVE_MONEY_REMOVE.toString()))
         {
            CompositeTemplate compositeTemplate = moveMoney(dcRequest, dcTemplateDetail, "" + reqCounter);
            if(compositeTemplate==null) throw new EnvelopeCreationException("EnvelopeCreationException for acctNum ="+dcRequest.getAcctnum()+" reqId = "+dcRequest.getReqId());
            envDef.getCompositeTemplates().add(compositeTemplate);
         }
         else if (dcRequest.getReqType().equalsIgnoreCase(WSConstants.DocuSignServiceOperations.ELEC_FUND_TRAN_CHANGE.toString())
            || dcRequest.getReqType().equalsIgnoreCase(WSConstants.DocuSignServiceOperations.ELEC_FUND_TRAN_NEW.toString())
            || dcRequest.getReqType().equalsIgnoreCase(WSConstants.DocuSignServiceOperations.ELEC_FUND_TRAN_REPLACE.toString()))
         {
            CompositeTemplate compositeTemplate = elecFundTransfer(dcRequest, dcTemplateDetail, "" + reqCounter);
            if(compositeTemplate==null) throw new EnvelopeCreationException("EnvelopeCreationException for acctNum ="+dcRequest.getAcctnum()+" reqId = "+dcRequest.getReqId());
            envDef.getCompositeTemplates().add(compositeTemplate);
         }
         else if (dcRequest.getReqType().equalsIgnoreCase(WSConstants.DocuSignServiceOperations.ACCT_TRAN_NEW.toString()))
         {
            CompositeTemplate compositeTemplate = acctTransfer(dcRequest, dcTemplateDetail, "" + reqCounter);
            if(compositeTemplate==null) throw new EnvelopeCreationException("EnvelopeCreationException for acctNum ="+dcRequest.getAcctnum()+" reqId = "+dcRequest.getReqId());
            envDef.getCompositeTemplates().add(compositeTemplate);

         }
         else if (dcRequest.getReqType().equalsIgnoreCase(WSConstants.DocuSignServiceOperations.IRA_QRP_BENE_NEW.toString()))
         {
            logger.info("Required in next phase");
            CompositeTemplate compositeTemplate = accountApplication(dcRequest, dcTemplateDetail, "" + reqCounter);
            if(compositeTemplate==null) throw new EnvelopeCreationException("EnvelopeCreationException for acctNum ="+dcRequest.getAcctnum()+" reqId = "+dcRequest.getReqId());
            envDef.getCompositeTemplates().add(compositeTemplate);
//            dcReqExtDoc = dcRequest;
         }
         reqCounter++;
      }
      envDef.setEmailSubject(emailSubject);
      envDef.setStatus("sent");

      if (dcUtility.createEnvelope(envDef, acctNum, eventNum, requestIds.toString()) == true)
      {
         if (dcReqExtDoc == null)
         {
            logger.info("No need to send additional documents for signature.");
         }
         else
         {
            if (dcReqExtDoc.getReqType().equalsIgnoreCase(WSConstants.DocuSignServiceOperations.ACCT_APPLI_NEW.toString())
               || dcReqExtDoc.getReqType().equalsIgnoreCase(WSConstants.DocuSignServiceOperations.IRA_APPLI_NEW.toString()))
            {
               if (agreementDocuments(dcReqExtDoc, acctNum, eventNum, "" + dcReqExtDoc.getReqId())==false){
                  wsCallResult = new WSCallResult(new WSCallStatus(SysParameters.dcEGenErrCode, SysParameters.dcEGenErrMsg), null);
                  logger.info("Something went wrong while processing agreement documents envelope");
               }
            }
         }
         return new WSCallResult(new WSCallStatus(SysParameters.dcSuccessCode, SysParameters.dcSuccessMsg), null);
      }
      else
      {
         wsCallResult = new WSCallResult(new WSCallStatus(SysParameters.dcEGenErrCode, SysParameters.dcEGenErrMsg), null);
         logger.info("Something went wrong while creating DocuSign envelope");
      }
   }catch(Exception e)
   {
      wsCallResult = new WSCallResult(new WSCallStatus(SysParameters.dcIGenErrCode, SysParameters.dcIGenErrMsg), null);
      logger.error(e.getMessage());
      e.printStackTrace();
   }
      return wsCallResult;
   }

   private CompositeTemplate acctTransfer(DCRequest dcRequest, DCTemplateDetails dcTemplateDetail,String servTempSeq)throws Exception{
      logger.info("TDAccountOpeningLayerImpl.acctTransfer");
      logger.info("dcRequest = [" + dcRequest + "]");

      CompositeTemplate compositeTemplate=null;
      AcctDetails acctDetails = tdDaoLayer.getAcctDetails(dcRequest.getAcctnum(),dcRequest.getReqId(), false);
      if(acctDetails==null){
         logger.error("AccountDetails information not available for acctNum = "+dcRequest.getAcctnum()+" requestId = "+dcRequest.getReqId());
      }
      else
      {
         List<AcctOwnerDetails> acctOwnerDetails = tdDaoLayer.getAcctOwnerDetails(dcRequest.getAcctnum(), dcRequest.getReqId(), false);
         if (acctOwnerDetails == null || acctOwnerDetails.size() <= 0)
         {
            logger.error("AcctOwnerDetails information not available for acctNum = " + dcRequest.getAcctnum() + " requestId = " + dcRequest.getReqId());
         }
         else
         {
            AcctTransferDetails acctTransferDetails = tdDaoLayer.getAcctTransferDetails(dcRequest.getAcctnum(), dcRequest.getReqId());
            if (acctTransferDetails == null)
            {
               logger.error("AcctTransferDetails information not available for acctNum = " + dcRequest.getAcctnum() + " requestId = " + dcRequest.getReqId());
            }else
            {

               acctDetails.setAcctOwnerDetails(acctOwnerDetails);

               InlineTemplate inlineTemplate = dcUtility.getInlineTemplate(servTempSeq);
               inlineTemplate.setRecipients(dcUtility.getRecipientsAcctTransfer(dcTemplateDetail, acctDetails, acctTransferDetails));
               ServerTemplate serverTemplate = dcUtility.getServerTemplate(servTempSeq, dcTemplateDetail);

               compositeTemplate = new CompositeTemplate();
               compositeTemplate.setServerTemplates(new ArrayList<ServerTemplate>());
               compositeTemplate.getServerTemplates().add(serverTemplate);

               compositeTemplate.setInlineTemplates(new ArrayList<InlineTemplate>());
               compositeTemplate.getInlineTemplates().add(inlineTemplate);
            }
         }
      }

      return compositeTemplate;
   }
   private CompositeTemplate elecFundTransfer(DCRequest dcRequest, DCTemplateDetails dcTemplateDetail, String servTempSeq) throws Exception{
      logger.info("TDAccountOpeningLayerImpl.acctTransfer");
      logger.info("dcRequest = [" + dcRequest + "]");

      CompositeTemplate compositeTemplate=null;
      AcctDetails acctDetails = tdDaoLayer.getAcctDetails(dcRequest.getAcctnum(),dcRequest.getReqId(), false);
      if(acctDetails==null){
         logger.error("AccountDetails information not available for acctNum = "+dcRequest.getAcctnum()+" requestId = "+dcRequest.getReqId());
      }
      else
      {
         List<AcctOwnerDetails> acctOwnerDetails = tdDaoLayer.getAcctOwnerDetails(dcRequest.getAcctnum(), dcRequest.getReqId(), false);
         if (acctOwnerDetails == null || acctOwnerDetails.size() <= 0)
         {
            logger.error("AcctOwnerDetails information not available for acctNum = " + dcRequest.getAcctnum() + " requestId = " + dcRequest.getReqId());
         }
         else
         {
            ElecFundTransferDetails elecFundTransferDetails = tdDaoLayer.getElecFundTransferDetails(dcRequest.getAcctnum(), dcRequest.getReqId());
            if (elecFundTransferDetails == null)
            {
               logger.error("ElecFundTransferDetails information not available for acctNum = " + dcRequest.getAcctnum() + " requestId = " + dcRequest.getReqId());
            }else
            {
               acctDetails.setAcctOwnerDetails(acctOwnerDetails);
               InlineTemplate inlineTemplate = dcUtility.getInlineTemplate(servTempSeq);
               inlineTemplate.setRecipients(dcUtility.getRecipientsElecFundTransfer(dcTemplateDetail, acctDetails, elecFundTransferDetails));
               ServerTemplate serverTemplate = dcUtility.getServerTemplate(servTempSeq, dcTemplateDetail);

               compositeTemplate = new CompositeTemplate();
               compositeTemplate.setServerTemplates(new ArrayList<ServerTemplate>());
               compositeTemplate.getServerTemplates().add(serverTemplate);

               compositeTemplate.setInlineTemplates(new ArrayList<InlineTemplate>());
               compositeTemplate.getInlineTemplates().add(inlineTemplate);
            }
         }
      }

      return compositeTemplate;
   }
   private CompositeTemplate moveMoney(DCRequest dcRequest, DCTemplateDetails dcTemplateDetail, String servTempSeq)throws Exception{
      logger.info("TDAccountOpeningLayerImpl.moveMoney");
      logger.info("dcRequest = [" + dcRequest + "]");

      CompositeTemplate compositeTemplate=null;
      AcctDetails acctDetails = tdDaoLayer.getAcctDetails(dcRequest.getAcctnum(),dcRequest.getReqId(), false);
      if(acctDetails==null){
         logger.error("AccountDetails information not available for acctNum = "+dcRequest.getAcctnum()+" requestId = "+dcRequest.getReqId());
      }
      else
      {
         List<AcctOwnerDetails> acctOwnerDetails = tdDaoLayer.getAcctOwnerDetails(dcRequest.getAcctnum(), dcRequest.getReqId(), false);
         if (acctOwnerDetails == null || acctOwnerDetails.size() <= 0)
         {
            logger.error("AcctOwnerDetails information not available for acctNum = " + dcRequest.getAcctnum() + " requestId = " + dcRequest.getReqId());
         }
         else
         {
            List<MoveMoneyDetails> moveMoneyDetailsLst = tdDaoLayer.getMoveMoneyDetails(dcRequest.getAcctnum(), dcRequest.getEventNum());
            if (moveMoneyDetailsLst == null || moveMoneyDetailsLst.size() <= 0)
            {
               logger.error("MoveMoneyDetails information not available for acctNum  = " + dcRequest.getAcctnum() + " eventNum = " + dcRequest.getEventNum());
            }
            else
            {
               acctDetails.setAcctOwnerDetails(acctOwnerDetails);
               InlineTemplate inlineTemplate = dcUtility.getInlineTemplate(servTempSeq);
               inlineTemplate.setRecipients(dcUtility.getRecipientsMoveMoney(dcTemplateDetail, acctDetails, acctOwnerDetails, moveMoneyDetailsLst));
               ServerTemplate serverTemplate = dcUtility.getServerTemplate(servTempSeq, dcTemplateDetail);

               compositeTemplate = new CompositeTemplate();
               compositeTemplate.setServerTemplates(new ArrayList<ServerTemplate>());
               compositeTemplate.getServerTemplates().add(serverTemplate);

               compositeTemplate.setInlineTemplates(new ArrayList<InlineTemplate>());
               compositeTemplate.getInlineTemplates().add(inlineTemplate);
            }
         }
      }

    return compositeTemplate;
 }
   private CompositeTemplate accountApplication(DCRequest dcRequest, DCTemplateDetails dcTemplateDetail, String servTempSeq)throws Exception{
      logger.info("TDAccountOpeningLayerImpl.accountApplication");
      logger.info("dcRequest = [" + dcRequest + "]");
      CompositeTemplate compositeTemplate =null;
      AcctDetails acctDetails = tdDaoLayer.getAcctDetails(dcRequest.getAcctnum(),dcRequest.getReqId(), true);
      if(acctDetails==null){
         logger.error("AccountDetails information not available for acctNum = "+dcRequest.getAcctnum()+" requestId = "+dcRequest.getReqId());
      }
      else
      {
         List<AcctOwnerDetails> acctOwnerDetails = tdDaoLayer.getAcctOwnerDetails(dcRequest.getAcctnum(), dcRequest.getReqId(), true);
         if(acctOwnerDetails==null || acctOwnerDetails.size()<=0){
            logger.error("AcctOwnerDetails information not available for acctNum = "+dcRequest.getAcctnum()+" requestId = "+dcRequest.getReqId());
         }else
         {
            acctDetails.setAcctOwnerDetails(acctOwnerDetails);

            InlineTemplate inlineTemplate = dcUtility.getInlineTemplate(servTempSeq);
            inlineTemplate.setRecipients(dcUtility.getRecipientsAcctCreation(dcTemplateDetail, acctDetails, acctOwnerDetails));
            ServerTemplate serverTemplate = dcUtility.getServerTemplate(servTempSeq, dcTemplateDetail);

            compositeTemplate = new CompositeTemplate();
            compositeTemplate.setServerTemplates(new ArrayList<ServerTemplate>());
            compositeTemplate.getServerTemplates().add(serverTemplate);

            compositeTemplate.setInlineTemplates(new ArrayList<InlineTemplate>());
            compositeTemplate.getInlineTemplates().add(inlineTemplate);
         }
      }
      return compositeTemplate;
   }
   private boolean agreementDocuments(DCRequest dcRequest, Long acctNum, int eventNum, String requestIds)throws Exception{
      logger.info("TDAccountOpeningLayerImpl.agreementDocuments");
      logger.info("dcRequest = [" + dcRequest + "]");

      Map<String,DCDocumentDetails> dcDocumentDetails=(Map<String,DCDocumentDetails>) ServiceParameters.additionalDetails.get(Constant.SERVICES.DOCUSIGN_SERVICES.toString()).get(Constant.ADDITIONAL_DETAILS.DOCUMENT_DETAILS
                                                                                                                                                                                     .toString());
      EnvelopeDefinition envDef = new EnvelopeDefinition();
      String emailSubject=dcRequest.getEnvelopeHeading();

      CompositeTemplate compositeTemplate=null;
      AcctDetails acctDetails = tdDaoLayer.getAcctDetails(dcRequest.getAcctnum(),dcRequest.getReqId(), false);
      if(acctDetails==null){
         logger.error("AccountDetails information not available for acctNum = "+dcRequest.getAcctnum()+" requestId = "+dcRequest.getReqId());
      }
      else
      {
         List<AcctOwnerDetails> acctOwnerDetails = tdDaoLayer.getAcctOwnerDetails(dcRequest.getAcctnum(), dcRequest.getReqId(), false);
         if (acctOwnerDetails == null || acctOwnerDetails.size() <= 0)
         {
            logger.error("AcctOwnerDetails information not available for acctNum = " + dcRequest.getAcctnum() + " requestId = " + dcRequest.getReqId());
         }
         else
         {
            acctDetails.setAcctOwnerDetails(acctOwnerDetails);

            InlineTemplate inlineTemplate = dcUtility.getInlineTemplateAgreements(dcDocumentDetails, acctDetails, acctOwnerDetails);

            compositeTemplate = new CompositeTemplate();
            compositeTemplate.setInlineTemplates(new ArrayList<InlineTemplate>());
            compositeTemplate.getInlineTemplates().add(inlineTemplate);

            envDef.getCompositeTemplates().add(compositeTemplate);
            envDef.setEmailSubject(emailSubject);
            envDef.setStatus("sent");

            return dcUtility.createEnvelope(envDef, acctNum, eventNum, requestIds);
         }
      }
      return false;
   }

}
