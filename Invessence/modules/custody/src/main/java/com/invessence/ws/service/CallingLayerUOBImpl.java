package com.invessence.ws.service;

import java.util.List;

import com.invessence.service.bean.ServiceRequest;
import com.invessence.custody.uob.dao.UOBDaoImpl;
import com.invessence.ws.bean.*;
import com.invessence.ws.provider.td.bean.DCRequest;
import com.invessence.ws.provider.td.service.TDAccountOpeningLayer;
import com.invessence.ws.util.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by abhangp on 3/11/2016.
 */
@Service("UOBKH")
public class CallingLayerUOBImpl implements CallingLayer
{
   private static final Logger logger = Logger.getLogger(CallingLayerUOBImpl.class);
   @Autowired
   UOBDaoImpl uobDaoImpl;
   @Autowired
   TDAccountOpeningLayer tdAccountOpeningLayer;
   public CallingLayerUOBImpl(){
      System.out.println("CallingLayerTDImpl.CallingLayerTDImpl");
   }

   @Override
   public WSCallResult processDCRequest(Long acctNum, Integer eventNum) throws Exception
   {
      return null;
   }
   @Override
   public WSCallResult processDCRequest(ServiceRequest serviceRequest, List<DCRequest> dcRequests)throws Exception
   {
      return null;
   }

   @Override
   public WSCallResult processDCRequest(ServiceRequest serviceRequest, List<DCRequest> dcRequests, Object object) throws Exception
   {
      logger.info("CallingLayerTDImpl.processDCRequest");
      logger.info("serviceRequest = [" + serviceRequest + "]");
      WSCallResult wsCallResult=null;
      //List<DCRequest> dcRequests= uobDaoImpl.getDCRequests(acctNum, eventNum);
      if(dcRequests.size()>0)
      {
         wsCallResult=tdAccountOpeningLayer.docuSignRequestHandler(serviceRequest, dcRequests, object);
         //wsCallResult=docuSignService.processDocuSignRequest(dcRequests);
      }else{
         wsCallResult=new WSCallResult(new WSCallStatus(SysParameters.dcReqDataIssueCode, SysParameters.dcReqDataIssueMsg), null);
         //logger.info("Request details are not available for acctNum = [" + acctNum + "], eventNum = [" + eventNum + "]");
      }
      return wsCallResult;
   }

   @Override
   public WSCallResult moveMoney(Long acctNum, Integer reqId)
   {
      return null;
   }

   @Override
   public WSCallResult fundTransfer(Long acctNum, Integer reqId)
   {
      return null;
   }

   public WSCallResult getMailingAddress(UserAcctDetails userAcctDetails) throws Exception
   {
      throw new NoServiceSupportException("getMailingAddress Service Not Support");
      // return null;
   }

   @Override
   public WSCallResult getAccountInfo(UserAcctDetails userAcctDetails) throws Exception
   {
      return null;
   }

//   @Override
//   public UserAcctExt getAcctExtInfo(UserAcctDetails userAcctDetails) throws Exception
//   {
//      return null;
//   }

   @Override
   public WSCallResult getUserBankAcctDetails(UserAcctDetails userAcctDetails) throws Exception
   {
      return null;
   }

   @Override
   public WSCallResult fundAccount(UserAcctDetails userAcctDetails, int fundID, double amount, String bankAccountNumber, UserAcctExt userAcctExt) throws Exception
   {
      throw new NoServiceSupportException("fundAccount Service Not Support");
      //return null;
   }

   @Override
   public WSCallResult fullFundTransfer(UserAcctDetails userAcctDetails, int fromFundID, int toFundID, String bankAccountNumber, UserAcctExt userAcctExt) throws Exception
   {
      throw new NoServiceSupportException("fullFundTransfer Service Not Support");
      //return null;
   }


   public WSCallStatus updateMailingAddress(UserAcctDetails userAcctDetails, UserAddress mailingAddress) throws Exception
   {
      throw new NoServiceSupportException("updateMailingAddress Service Not Support");
      // return null;
   }

   public WSCallStatus loginUser(UserAcctDetails userAcctDetails) throws Exception
   {
      throw new NoServiceSupportException("loginUser Service Not Support");
     // return null;
   }

   public WSCallStatus createUser(UserAcctDetails userAcctDetails) throws Exception
   {
      throw new NoServiceSupportException("createUser Service Not Support");
      //return null;
   }

   public WSCallStatus isUserExist(UserAcctDetails userAcctDetails) throws Exception
   {
      throw new NoServiceSupportException("isUserExist Service Not Support");
      //return null;
   }

   public WSCallStatus updateUserEmail(UserAcctDetails userAcctDetails, String newEmail) throws Exception
   {

      throw new NoServiceSupportException("updateUserEmail Service Not Support");
      //return null;
   }

   @Override
   public WSCallStatus resetPassword(UserAcctDetails userAcctDetails, String newPwd) throws Exception
   {
      return null;
   }
}
