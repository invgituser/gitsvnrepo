package com.invessence.web.service.docuSign.service;

import java.util.*;

import com.invessence.service.bean.ServiceRequest;
import com.invessence.ws.bean.*;
import com.invessence.ws.provider.td.bean.*;
import com.invessence.ws.provider.td.dao.TDDaoLayer;
import com.invessence.ws.service.*;
import com.invessence.ws.util.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;

/**
 * Created by abhangp on 3/11/2016.
 */

@Service("dcWebLayer")
public class DCWebLayerImpl implements DCWebLayer
{
   private static final Logger logger = Logger.getLogger(DCWebLayerImpl.class);
   @Autowired
   TDDaoLayer tdDaoLayer;
   @Autowired
   ServiceLayer serviceLayer;

   @Override
   public WSCallResult processDCRequest(ServiceRequest serviceRequest, Long acctNum, Integer eventNum)throws Exception
   {
      logger.info("CallingLayerTDImpl.processDCRequest");
      logger.info("serviceRequest = [" + serviceRequest + "], acctNum = [" + acctNum + "], eventNum = [" + eventNum + "]");
      logger.info("acctNum = [" + acctNum + "], eventNum = [" + eventNum + "]");
      WSCallResult wsCallResult=null;
      List<DCRequest> dcRequests= tdDaoLayer.getDCRequests(acctNum, eventNum);

      wsCallResult=processDocuSignRequest(dcRequests);
      if(wsCallResult.getWSCallStatus().getErrorCode()==0){
         wsCallResult = serviceLayer.processDCRequest(serviceRequest,(List<DCRequest>)wsCallResult.getGenericObject());//processDCRequest(serviceRequest,(List<DCRequest>)wsCallResult.getGenericObject());
      }
      return wsCallResult;
   }

   private WSCallResult processDocuSignRequest(List<DCRequest> dcRequests)
   {
      WSCallResult wsCallResult=null;
      try
      {
         Iterator<DCRequest> itr = dcRequests.iterator();
         Boolean acctInfoLoaded = false;
         AcctDetails acctDetails = null;
         while (itr.hasNext())
         {
            DCRequest dcRequest = (DCRequest) itr.next();
            System.out.println(dcRequest);
            if (acctInfoLoaded == false)
            {
               acctDetails = tdDaoLayer.getAcctDetails(dcRequest.getAcctnum(), dcRequest.getReqId(), true);
               if (acctDetails == null)
               {
                  logger.error("AccountDetails information not available for acctNum = " + dcRequest.getAcctnum() + " requestId = " + dcRequest.getReqId());
                  return new WSCallResult(new WSCallStatus(SysParameters.dcIGenErrCode, SysParameters.dcIGenErrMsg), null);
               }
               else
               {
                  List<AcctOwnerDetails> acctOwnerDetails = tdDaoLayer.getAcctOwnerDetails(dcRequest.getAcctnum(), dcRequest.getReqId(), true);
                  if (acctOwnerDetails == null || acctOwnerDetails.size() <= 0)
                  {
                     logger.error("AcctOwnerDetails information not available for acctNum = " + dcRequest.getAcctnum() + " requestId = " + dcRequest.getReqId());
                     return new WSCallResult(new WSCallStatus(SysParameters.dcIGenErrCode, SysParameters.dcIGenErrMsg), null);
                  }
                  else
                  {
                     acctDetails.setAcctOwnerDetails(acctOwnerDetails);
                     acctInfoLoaded = true;
                     dcRequest.setAcctDetails(acctDetails);
                  }
               }
            }
            else
            {
               dcRequest.setAcctDetails(acctDetails);
            }

            if (dcRequest.getFormType().equalsIgnoreCase("ADV"))
            {
               dcRequest.setAcctDetails(acctDetails);
            }
            else if (dcRequest.getReqType().equalsIgnoreCase(WSConstants.DocuSignServiceOperations.ACCT_APPLI_NEW.toString())
               || dcRequest.getReqType().equalsIgnoreCase(WSConstants.DocuSignServiceOperations.IRA_APPLI_NEW.toString())
               || dcRequest.getReqType().equalsIgnoreCase(WSConstants.DocuSignServiceOperations.IRA_QRP_BENE_NEW.toString()))
            {
               dcRequest.setAcctDetails(acctDetails);
            }
            else if (dcRequest.getReqType().equalsIgnoreCase(WSConstants.DocuSignServiceOperations.MOVE_MONEY_NEW.toString())
               || dcRequest.getReqType().equalsIgnoreCase(WSConstants.DocuSignServiceOperations.IRA_MOVE_MONEY_NEW.toString())
               || dcRequest.getReqType().equalsIgnoreCase(WSConstants.DocuSignServiceOperations.MOVE_MONEY_CHANGE.toString())
               || dcRequest.getReqType().equalsIgnoreCase(WSConstants.DocuSignServiceOperations.MOVE_MONEY_REMOVE.toString())
               || dcRequest.getReqType().equalsIgnoreCase(WSConstants.DocuSignServiceOperations.IRA_MOVE_MONEY_CHANGE.toString())
               || dcRequest.getReqType().equalsIgnoreCase(WSConstants.DocuSignServiceOperations.IRA_MOVE_MONEY_REMOVE.toString()))
            {
               List<MoveMoneyDetails> moveMoneyDetailsLst = tdDaoLayer.getMoveMoneyDetails(dcRequest.getAcctnum(), dcRequest.getRefReqId());
               if (moveMoneyDetailsLst == null || moveMoneyDetailsLst.size() <= 0)
               {
                  logger.error("MoveMoneyDetails information not available for acctNum  = " + dcRequest.getAcctnum() + " eventNum = " + dcRequest.getEventNum());
                  return new WSCallResult(new WSCallStatus(SysParameters.dcIGenErrCode, SysParameters.dcIGenErrMsg), null);
               }
               else
               {
                  dcRequest.setMoveMoneyDetails(moveMoneyDetailsLst);
               }
            }
            else if (dcRequest.getReqType().equalsIgnoreCase(WSConstants.DocuSignServiceOperations.ELEC_FUND_TRAN_CHANGE.toString())
               || dcRequest.getReqType().equalsIgnoreCase(WSConstants.DocuSignServiceOperations.ELEC_FUND_TRAN_NEW.toString())
               || dcRequest.getReqType().equalsIgnoreCase(WSConstants.DocuSignServiceOperations.ELEC_FUND_TRAN_REPLACE.toString()))
            {
               ElecFundTransferDetails elecFundTransferDetails = tdDaoLayer.getElecFundTransferDetails(dcRequest.getAcctnum(), dcRequest.getRefReqId());
               if (elecFundTransferDetails == null)
               {
                  logger.error("ElecFundTransferDetails information not available for acctNum = " + dcRequest.getAcctnum() + " requestId = " + dcRequest.getRefReqId());
                  return new WSCallResult(new WSCallStatus(SysParameters.dcIGenErrCode, SysParameters.dcIGenErrMsg), null);
               }
               else
               {
                  dcRequest.setElecFundTransferDetails(elecFundTransferDetails);
               }
            }
            else if (dcRequest.getReqType().equalsIgnoreCase(WSConstants.DocuSignServiceOperations.ACCT_TRAN_NEW.toString())
               ||dcRequest.getReqType().equalsIgnoreCase(WSConstants.DocuSignServiceOperations.ACAT_OTHER_NEW.toString())
               ||dcRequest.getReqType().equalsIgnoreCase(WSConstants.DocuSignServiceOperations.INT_ACAT_OTHER_NEW.toString())
               ||dcRequest.getReqType().equalsIgnoreCase(WSConstants.DocuSignServiceOperations.EXT_ACAT_OTHER_NEW.toString()))
            {
               AcctTransferDetails acctTransferDetails = tdDaoLayer.getAcctTransferDetails(dcRequest.getAcctnum(), dcRequest.getRefReqId());
               if (acctTransferDetails == null)
               {
                  logger.error("AcctTransferDetails information not available for acctNum = " + dcRequest.getAcctnum() + " requestId = " + dcRequest.getRefReqId());
                  return new WSCallResult(new WSCallStatus(SysParameters.dcIGenErrCode, SysParameters.dcIGenErrMsg), null);
               }
               else
               {
                  if (dcRequest.getReqType().equalsIgnoreCase(WSConstants.DocuSignServiceOperations.ACAT_OTHER_NEW.toString())
                     ||dcRequest.getReqType().equalsIgnoreCase(WSConstants.DocuSignServiceOperations.INT_ACAT_OTHER_NEW.toString())
                     ||dcRequest.getReqType().equalsIgnoreCase(WSConstants.DocuSignServiceOperations.EXT_ACAT_OTHER_NEW.toString()))
                  {
                     List<AcctOwnerDetails> acctOwnerDetailsLst = new ArrayList<>();
                     if (dcRequest.getAcctDetails().getAcctOwnerDetails().size() > 1)
                     {
                        AcctOwnerDetails clientAcctOwnerDetails=null;
                        String jointFullName=null;
                        Iterator<AcctOwnerDetails> acctOwnerDetailsIterator = dcRequest.getAcctDetails().getAcctOwnerDetails().iterator();
                        while (acctOwnerDetailsIterator.hasNext())
                        {
                           AcctOwnerDetails acctOwnerDetailsTemp = acctOwnerDetailsIterator.next();
                           if (acctOwnerDetailsTemp.getOwnership().equalsIgnoreCase("Client"))
                           {
                              clientAcctOwnerDetails=acctOwnerDetailsTemp;
                           }else if (acctOwnerDetailsTemp.getOwnership().equalsIgnoreCase("Joint")){
                              jointFullName=acctOwnerDetailsTemp.getFullName();
                           }
                        }
                        clientAcctOwnerDetails.setJointFullName(jointFullName);
                        acctOwnerDetailsLst.add(clientAcctOwnerDetails);

                        dcRequest.getAcctDetails().setAcctOwnerDetails(acctOwnerDetailsLst);
                     }
                  }

                  dcRequest.setAcctTransferDetails(acctTransferDetails);
               }
            }
//            else if (dcRequest.getReqType().equalsIgnoreCase(WSConstants.DocuSignServiceOperations.ACAT_OTHER_NEW.toString()))
//            {
//
//            }
            else if (dcRequest.getReqType().equalsIgnoreCase(WSConstants.DocuSignServiceOperations.TD_TRAN_NEW.toString()))
            {
               TDTransferDetails tdTransferDetails = tdDaoLayer.getTDTransferDetails(dcRequest.getAcctnum(), dcRequest.getRefReqId());
               if (tdTransferDetails == null)
               {
                  logger.error("TDTransferDetails information not available for acctNum = " + dcRequest.getAcctnum() + " requestId = " + dcRequest.getRefReqId());
                  return new WSCallResult(new WSCallStatus(SysParameters.dcIGenErrCode, SysParameters.dcIGenErrMsg), null);
               }
               else
               {
                  dcRequest.setTdTransferDetails(tdTransferDetails);
               }
            }
            else if (dcRequest.getReqType().equalsIgnoreCase(WSConstants.DocuSignServiceOperations.ACCT_CHNG_ADDR.toString()))
            {
               GetAcctChngAddrDetails getAcctChngAddrDetails = tdDaoLayer.getAcctChngAddrDetails(dcRequest.getAcctnum());
               if (getAcctChngAddrDetails == null)
               {
                  logger.error("GetAcctChngAddrDetails information not available for acctNum = " + dcRequest.getAcctnum());
                  return new WSCallResult(new WSCallStatus(SysParameters.dcIGenErrCode, SysParameters.dcIGenErrMsg), null);
               }
               else
               {
                  dcRequest.setGetAcctChngAddrDetails(getAcctChngAddrDetails);
               }
            }
         }
         return wsCallResult=new WSCallResult(new WSCallStatus(SysParameters.dcSuccessCode, null), dcRequests);
      }
      catch (Exception e)
      {
         logger.error(e.getMessage());
         e.printStackTrace();
         return wsCallResult = new WSCallResult(new WSCallStatus(SysParameters.dcIGenErrCode, SysParameters.dcIGenErrMsg), null);

      }
   }
}
