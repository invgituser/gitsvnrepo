package com.invessence.ws.provider.gemini.service;

import java.math.BigDecimal;
import java.util.*;

import com.invessence.ws.bean.*;
import com.invessence.ws.dao.WSCommonDao;
import com.invessence.ws.util.*;
import com.invessence.ws.provider.gemini.wsdl.account.AchPayeeResult;
import com.invessence.ws.provider.gemini.wsdl.transaction.*;
import org.apache.axis.types.UnsignedByte;
import org.apache.log4j.Logger;

/**
 * Created by abhangp on 3/28/2016.
 */
public class TransactionServiceImpl implements TransactionService
{
   private static final Logger logger = Logger.getLogger(TransactionServiceImpl.class);
   TransactionServicesLocator servicesLocator = new TransactionServicesLocator();
   TransactionServicesSoap_PortType servicesSoap = null;

   //Date reqTime=null;
   private WSCommonDao wsCommonDao;
   public TransactionServiceImpl(WSCommonDao wsCommonDao){
      this.wsCommonDao=wsCommonDao;
   }

   @Override
   public WSCallResult fundAccount(UserAcctDetails userAcctDetails, int fundID, double amount, String bankAccountNumber, UserAcctExt userAcctExt) throws Exception
   {
      logger.info("TransactionServiceImpl.fundAccount");
      logger.info("userAcctDetails = [" + userAcctDetails + "], fundID = [" + fundID + "], amount = [" + amount + "], bankAccountNumber = [" + bankAccountNumber + "], userAcctExt = [" + userAcctExt + "]");
      Date reqTime=new Date();
      try{

      AchPayeeResult achPayeeResult=new AccountServiceImpl(wsCommonDao).getAchPayeeCollection(userAcctDetails, bankAccountNumber);
      logger.debug("achPayeeResult = " + achPayeeResult);
      if(achPayeeResult==null){
         return new WSCallResult( new WSCallStatus(SysParameters.wsResIssueCode, SysParameters.wsResIssueMsg),null);
      }else
      {
         servicesSoap = servicesLocator.getTransactionServicesSoap();

         List<FundInformation> lstFundIfo = new ArrayList<>();
         FundInformation fund = new FundInformation();
         fund.setFundId((short) fundID);
         fund.setAmountType(new UnsignedByte(1));
         fund.setAmount(new BigDecimal(amount));
         fund.setFromToLineIndicator(new UnsignedByte(0));
         lstFundIfo.add(fund);
         logger.debug("fund = " + fund);

         TransactionInfo tranInfo = new TransactionInfo();
         tranInfo.setBankName(achPayeeResult.getBankName().trim());
         //tranInfo.setBankRoutingNumber(achPayeeResult.getBankRoutingNumber().trim());
         tranInfo.setBankAccountNumber(achPayeeResult.getBankAccountNumber().trim());
         tranInfo.setBankAccountType(achPayeeResult.getBankAccountType());
         tranInfo.setMasterTransactionType(new UnsignedByte(1));
         tranInfo.setAccountNumber(userAcctDetails.getClientAccountID().trim());
         tranInfo.setMoneyTransactionType(new UnsignedByte(3));
         tranInfo.setMasterTransactionSource(new UnsignedByte(9));
         tranInfo.setCurrencyId(new UnsignedByte(1));
//tranInfo.setDealerTransactionIndicator(0);
         tranInfo.setAccountPayeeId(1);
//tranInfo.setAs//tranInfo.setAllocationTradeIndicator(0);
//tranInfo.setEndResultExchangeInd(0);
//tranInfo.setExcludeAccountsList(string.Empty);
         tranInfo.setReturnReadBackInfo(new UnsignedByte(2));
         tranInfo.setReturnMessagesInfo(new UnsignedByte(1));
         tranInfo.setNumberOfMasterTransactionLines(new UnsignedByte(lstFundIfo.size()));
         tranInfo.setPriceCycleId(new UnsignedByte(200));
         tranInfo.setUserId((short) 96);
//tranInfo.setAgeBasedModelId(0);
//tranInfo.setAutoDetermineFunds(0);
//tranInfo.setAmountTypeIndicator(0);
//tranInfo.setWebUserId("INV_310100016");
         if(userAcctExt.getAccountType().equalsIgnoreCase("32")){
            tranInfo.setRetirementIndicator(new UnsignedByte(51)); // If account type is Retirement account // 52 we have to use for prior year
         }else{
            tranInfo.setRetirementIndicator(new UnsignedByte(0)); // for all other types of accounts
         }
         //tranInfo.setRetirementIndicator(new UnsignedByte(51)); //

         tranInfo.setMoneyAmount(new BigDecimal(1));
         Calendar calendar = Calendar.getInstance();
         calendar.setTime(new Date());
         tranInfo.setTradeDate(calendar);
         logger.info("tranInfo = " + tranInfo);

         FundInformation[] fundInfo = lstFundIfo.toArray(new FundInformation[lstFundIfo.size()]);
         TransactionCollectionResult transactionCollectionResult= servicesSoap.createTransaction(
         new AuthenticateLogin(userAcctDetails.getUserID(), userAcctDetails.getPwd(), userAcctDetails.getFundGroupName(), "00"),
         tranInfo,fundInfo);

         logger.debug("transactionCollectionResult = " + transactionCollectionResult);
         if (transactionCollectionResult==null || transactionCollectionResult.getErrorStatus()==null)
         {
            WSRequest wsRequest = new WSRequest("F", userAcctDetails.getClientAccountID(), WSConstants.BrokerWebServiceOperations.FUND_ACCOUNT.toString(),reqTime, SysParameters.wsResIssueMsg);
            wsCommonDao.insertWSRequest(wsRequest);
            return new WSCallResult( new WSCallStatus(SysParameters.wsResIssueCode, SysParameters.wsResIssueMsg),null);
         }else if(transactionCollectionResult.getErrorStatus().getErrorCode()==0)
         {
            TransactionDetails transactionDetails=new TransactionDetails(""+transactionCollectionResult.getMasterTransactionId());
            WSRequest wsRequest = new WSRequest("S", userAcctDetails.getClientAccountID(), WSConstants.BrokerWebServiceOperations.FUND_ACCOUNT.toString(),reqTime, transactionCollectionResult.getErrorStatus().getErrorMessage());
            wsCommonDao.insertWSRequest(wsRequest);
            return new WSCallResult( new WSCallStatus(transactionCollectionResult.getErrorStatus().getErrorCode(), transactionCollectionResult.getErrorStatus().getErrorMessage()),transactionDetails);
         }else
         {
            if (transactionCollectionResult.getNoOfErrors() > 0 || transactionCollectionResult.getErrorStatus().getErrorCode() != 0)
            {
               StringBuilder errMsg = new StringBuilder();
               TransactionMessageResult[] transactionMessageResults = transactionCollectionResult.getTransactionMessageCollection();
               if (transactionMessageResults.length > 0)
               {
                  WSRequest wsRequest = new WSRequest("F", userAcctDetails.getClientAccountID(), WSConstants.BrokerWebServiceOperations.FUND_ACCOUNT.toString(), reqTime, transactionMessageResults[0].getTransactionMessage());
                  wsCommonDao.insertWSRequest(wsRequest);
                  return new WSCallResult(new WSCallStatus(transactionMessageResults[0].getTransactionMessageId(), transactionMessageResults[0].getTransactionMessage()), null);
               }
//               for(TransactionMessageResult transactionMessage: transactionMessageResults){
//
//                  errMsg.append("Account Number:"+ transactionMessage.getAccountNumber());
//                  errMsg.append("Fund Id:"+ transactionMessage.getFundId());
//                  errMsg.append("Fund Name:"+ transactionMessage.getFundShortName());
//                  errMsg.append("Error Message:"+ transactionMessage.getTransactionMessage()+"\n");
//                  logger.info("transactionMessage = " + transactionMessage);
//                  //return new WSCallStatus(transactionCollectionResult.getErrorStatus().getErrorCode(), errMsg.toString());
//               }
               WSRequest wsRequest = new WSRequest("F", userAcctDetails.getClientAccountID(), WSConstants.BrokerWebServiceOperations.FUND_ACCOUNT.toString(), reqTime, transactionCollectionResult.getErrorStatus().getErrorMessage());
               wsCommonDao.insertWSRequest(wsRequest);
               return new WSCallResult(new WSCallStatus(transactionCollectionResult.getErrorStatus().getErrorCode(), transactionCollectionResult.getErrorStatus().getErrorMessage()), null);
            }
            else
            {
               WSRequest wsRequest = new WSRequest("F", userAcctDetails.getClientAccountID(), WSConstants.BrokerWebServiceOperations.FUND_ACCOUNT.toString(), reqTime, transactionCollectionResult.getErrorStatus().getErrorMessage());
               wsCommonDao.insertWSRequest(wsRequest);
               return new WSCallResult(new WSCallStatus(transactionCollectionResult.getErrorStatus().getErrorCode(), transactionCollectionResult.getErrorStatus().getErrorMessage()), null);
            }
         }
      } }
      catch (Exception e)
      {
         WSRequest wsRequest = new WSRequest("E", userAcctDetails.getClientAccountID(), WSConstants.BrokerWebServiceOperations.FUND_ACCOUNT.toString(),reqTime, e.getMessage());
         wsCommonDao.insertWSRequest(wsRequest);
         throw e;
      }
   }

   @Override
   public WSCallResult fullFundTransfer(UserAcctDetails userAcctDetails, int fromFundID, int toFundID, String bankAccountNumber,UserAcctExt userAcctExt) throws Exception
   {
      logger.info("TransactionServiceImpl.fullFundTransfer");
      logger.info("userAcctDetails = [" + userAcctDetails + "], fromFundID = [" + fromFundID + "], toFundID = [" + toFundID + "], bankAccountNumber = [" + bankAccountNumber + "], userAcctExt = [" + userAcctExt + "]");
      Date reqTime=new Date();
      try
      {
         AchPayeeResult achPayeeResult = new AccountServiceImpl(wsCommonDao).getAchPayeeCollection(userAcctDetails, bankAccountNumber);
         logger.debug("achPayeeResult = " + achPayeeResult);
         if (achPayeeResult == null)
         {
            return new WSCallResult(new WSCallStatus(SysParameters.wsResIssueCode, SysParameters.wsResIssueMsg), null);
         }
         else
         {
            servicesSoap = servicesLocator.getTransactionServicesSoap();
            List<FundInformation> lstFundIfo = new ArrayList<>();
            BigDecimal tranTotalAmount;

            FundInformation fromFund = new FundInformation();
            fromFund.setFundId((short) fromFundID);         // Fund to trade FROM
            fromFund.setAmountType(new UnsignedByte(7));    // 7 - full redemption / close account
            fromFund.setAmount(new BigDecimal(100));        // 100%
            fromFund.setFromToLineIndicator(new UnsignedByte(0)); //1 intial //2
            lstFundIfo.add(fromFund);
            logger.debug("fromFund = " + fromFund);

            FundInformation toFund = new FundInformation();
            toFund.setFundId((short) toFundID);             // Fund to trade TO
            toFund.setAmountType(new UnsignedByte(5));      // 4 - full redemption // 5 - Suggested by envision
            toFund.setAmount(new BigDecimal(100));          // 100%
            toFund.setFromToLineIndicator(new UnsignedByte(1)); //0 //1
            lstFundIfo.add(toFund);
            logger.debug("toFund = " + toFund);

            tranTotalAmount =  fromFund.getAmount().add(toFund.getAmount()); //

            TransactionInfo tranInfo = new TransactionInfo();
            tranInfo.setBankName(achPayeeResult.getBankName().trim());
            //tranInfo.setBankRoutingNumber(achPayeeResult.getBankRoutingNumber().trim());
            tranInfo.setBankAccountNumber(achPayeeResult.getBankAccountNumber().trim());
            tranInfo.setBankAccountType(achPayeeResult.getBankAccountType());
            tranInfo.setMasterTransactionType(new UnsignedByte(3)); // exchange
            tranInfo.setAccountNumber(userAcctDetails.getClientAccountID().trim());
            tranInfo.setMoneyTransactionType(new UnsignedByte(3));
            tranInfo.setMasterTransactionSource(new UnsignedByte(9));
            tranInfo.setCurrencyId(new UnsignedByte(1));
//tranInfo.setDealerTransactionIndicator(0);
            tranInfo.setAccountPayeeId(1);
//tranInfo.setAs//tranInfo.setAllocationTradeIndicator(0);
//tranInfo.setEndResultExchangeInd(0);
//tranInfo.setExcludeAccountsList(string.Empty);
            tranInfo.setReturnReadBackInfo(new UnsignedByte(2));
            tranInfo.setReturnMessagesInfo(new UnsignedByte(1));
            tranInfo.setNumberOfMasterTransactionLines(new UnsignedByte(lstFundIfo.size()));
            tranInfo.setPriceCycleId(new UnsignedByte(200));
            tranInfo.setUserId((short) 96);
//tranInfo.setAgeBasedModelId(0);
//tranInfo.setAutoDetermineFunds(0);
//tranInfo.setAmountTypeIndicator(0);
//tranInfo.setWebUserId("INV_310100016");
//            if(userAcctExt.getAccountType().equalsIgnoreCase("32")){
//               tranInfo.setRetirementIndicator(new UnsignedByte(51));
//            }else{
//               tranInfo.setRetirementIndicator(new UnsignedByte(0));
//            }
            tranInfo.setRetirementIndicator(new UnsignedByte(0)); // 0
            tranInfo.setMoneyAmount(tranTotalAmount);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            tranInfo.setTradeDate(calendar);
            logger.debug("tranInfo = " + tranInfo);

            FundInformation[] fundInfo = lstFundIfo.toArray(new FundInformation[lstFundIfo.size()]);
            TransactionCollectionResult transactionCollectionResult = servicesSoap.createTransaction(
               new AuthenticateLogin(userAcctDetails.getUserID(), userAcctDetails.getPwd(), userAcctDetails.getFundGroupName(), "00"),
               tranInfo, fundInfo);
            logger.debug("transactionCollectionResult = " + transactionCollectionResult);

            if (transactionCollectionResult == null || transactionCollectionResult.getErrorStatus() == null)
            {
               WSRequest wsRequest = new WSRequest("F", userAcctDetails.getClientAccountID(), WSConstants.BrokerWebServiceOperations.FULL_FUND_TRANSFER.toString(), reqTime, SysParameters.wsResIssueMsg);
               wsCommonDao.insertWSRequest(wsRequest);
               return new WSCallResult(new WSCallStatus(SysParameters.wsResIssueCode, SysParameters.wsResIssueMsg), null);
            }
            else if (transactionCollectionResult.getErrorStatus().getErrorCode() == 0)
            {
               WSRequest wsRequest = new WSRequest("S", userAcctDetails.getClientAccountID(), WSConstants.BrokerWebServiceOperations.FULL_FUND_TRANSFER.toString(), reqTime, transactionCollectionResult.getErrorStatus().getErrorMessage());
               wsCommonDao.insertWSRequest(wsRequest);
               TransactionDetails transactionDetails = new TransactionDetails("" + transactionCollectionResult.getMasterTransactionId());
               return new WSCallResult(new WSCallStatus(transactionCollectionResult.getErrorStatus().getErrorCode(), transactionCollectionResult.getErrorStatus().getErrorMessage()), transactionDetails);
            }
            else
            {
               if (transactionCollectionResult.getNoOfErrors() > 0 || transactionCollectionResult.getErrorStatus().getErrorCode() != 0)
               {
                  StringBuilder errMsg = new StringBuilder();
                  TransactionMessageResult[] transactionMessageResults = transactionCollectionResult.getTransactionMessageCollection();
                  if (transactionMessageResults.length > 0)
                  {
                     WSRequest wsRequest = new WSRequest("F", userAcctDetails.getClientAccountID(), WSConstants.BrokerWebServiceOperations.FULL_FUND_TRANSFER.toString(), reqTime, transactionMessageResults[0].getTransactionMessage());
                     wsCommonDao.insertWSRequest(wsRequest);
                     return new WSCallResult(new WSCallStatus(transactionMessageResults[0].getTransactionMessageId(), transactionMessageResults[0].getTransactionMessage()), null);
                  }
//               for(TransactionMessageResult transactionMessage: transactionMessageResults){
//
//                  errMsg.append("Account Number:"+ transactionMessage.getAccountNumber());
//                  errMsg.append("Fund Id:"+ transactionMessage.getFundId());
//                  errMsg.append("Fund Name:"+ transactionMessage.getFundShortName());
//                  errMsg.append("Error Message:"+ transactionMessage.getTransactionMessage()+"\n");
//                  logger.info("transactionMessage = " + transactionMessage);
//                  //return new WSCallStatus(transactionCollectionResult.getErrorStatus().getErrorCode(), errMsg.toString());
//               }
                  WSRequest wsRequest = new WSRequest("F", userAcctDetails.getClientAccountID(), WSConstants.BrokerWebServiceOperations.FULL_FUND_TRANSFER.toString(), reqTime, transactionCollectionResult.getErrorStatus().getErrorMessage());
                  wsCommonDao.insertWSRequest(wsRequest);
                  return new WSCallResult(new WSCallStatus(transactionCollectionResult.getErrorStatus().getErrorCode(), transactionCollectionResult.getErrorStatus().getErrorMessage()), null);
               }
               else
               {
                  WSRequest wsRequest = new WSRequest("F", userAcctDetails.getClientAccountID(), WSConstants.BrokerWebServiceOperations.FULL_FUND_TRANSFER.toString(), reqTime, transactionCollectionResult.getErrorStatus().getErrorMessage());
                  wsCommonDao.insertWSRequest(wsRequest);
                  return new WSCallResult(new WSCallStatus(transactionCollectionResult.getErrorStatus().getErrorCode(), transactionCollectionResult.getErrorStatus().getErrorMessage()), null);
               }
            }
         }

      }
      catch (Exception e)
      {
         WSRequest wsRequest = new WSRequest("E", userAcctDetails.getClientAccountID(), WSConstants.BrokerWebServiceOperations.FULL_FUND_TRANSFER.toString(), reqTime, e.getMessage());
         wsCommonDao.insertWSRequest(wsRequest);
         throw e;
      }
   }
}


//   public WSCallStatus fullFundTransfer(UserAcctDetails userAcctDetails, int fromFundID, int toFundID, double amount, String bankAccountNumber) throws Exception
//   {
//      AchPayeeResult achPayeeResult=new AccountServiceImpl().getAchPayeeCollection(userAcctDetails, bankAccountNumber);
//      System.out.println("achPayeeResult = " + achPayeeResult.toString());
//      if(achPayeeResult==null){
//         return  null;
//      }else
//      {
//         servicesSoap = servicesLocator.getTransactionServicesSoap();
//         List<FundInformation> lstFundIfo = new ArrayList<>();
//         FundInformation fi1 = new FundInformation();
//         fi1.setFundId((short) fromFundID);
//         fi1.setAmountType(new UnsignedByte(1));
//         fi1.setAmount(new BigDecimal(amount));
//         fi1.setFromToLineIndicator(new UnsignedByte(0));
//         lstFundIfo.add(fi1);
//
//         FundInformation fi2 = new FundInformation();
//         fi2.setFundId((short) toFundID);
//         fi2.setAmountType(new UnsignedByte(1));
//         fi2.setAmount(new BigDecimal(amount));
//         fi2.setFromToLineIndicator(new UnsignedByte(1));
//         lstFundIfo.add(fi2);
//
//         TransactionInfo tranInfo = new TransactionInfo();
//         tranInfo.setBankName(achPayeeResult.getBankName().trim());
//         //tranInfo.setBankRoutingNumber(achPayeeResult.getBankRoutingNumber().trim());
//         tranInfo.setBankAccountNumber(achPayeeResult.getBankAccountNumber().trim());
//         tranInfo.setBankAccountType(achPayeeResult.getBankAccountType());
//         tranInfo.setMasterTransactionType(new UnsignedByte(3));
//         tranInfo.setAccountNumber(userAcctDetails.getClientAccountID().trim());
//         tranInfo.setMoneyTransactionType(new UnsignedByte(3));
//         tranInfo.setMasterTransactionSource(new UnsignedByte(9));
//         tranInfo.setCurrencyId(new UnsignedByte(1));
////tranInfo.setDealerTransactionIndicator(0);
//         tranInfo.setAccountPayeeId(1);
////tranInfo.setAs//tranInfo.setAllocationTradeIndicator(0);
////tranInfo.setEndResultExchangeInd(0);
////tranInfo.setExcludeAccountsList(string.Empty);
//         tranInfo.setReturnReadBackInfo(new UnsignedByte(2));
//         tranInfo.setReturnMessagesInfo(new UnsignedByte(1));
//         tranInfo.setNumberOfMasterTransactionLines(new UnsignedByte(lstFundIfo.size()));
//         tranInfo.setPriceCycleId(new UnsignedByte(200));
//         tranInfo.setUserId((short) 96);
////tranInfo.setAgeBasedModelId(0);
////tranInfo.setAutoDetermineFunds(0);
////tranInfo.setAmountTypeIndicator(0);
////tranInfo.setWebUserId("INV_310100016");
//         tranInfo.setRetirementIndicator(new UnsignedByte(0));
//         tranInfo.setMoneyAmount(new BigDecimal(1));
//         Calendar calendar = Calendar.getInstance();
//         calendar.setTime(new Date());
//         tranInfo.setTradeDate(calendar);
//         System.out.println("tranInfo = " + tranInfo);
//
//
//
////         TransactionInfo tranInfo = new TransactionInfo();
////         //tranInfo.setBankName("JP Morgan Chase Bank");
//////tranInfo.setBankRoutingNumber("021202337");
////         tranInfo.setBankAccountNumber("6105640720");
//////tranInfo.setBankAccountType(1);
//////tranInfo.setNameOnAccount("JAIME L DESMOND");
////         tranInfo.setMasterTransactionType(new UnsignedByte(3));
////         tranInfo.setAccountNumber("310100016");
////         tranInfo.setMoneyTransactionType(new UnsignedByte(3));
////         tranInfo.setMasterTransactionSource(new UnsignedByte(9));
////         tranInfo.setCurrencyId(new UnsignedByte(1));
//////tranInfo.setDealerTransactionIndicator(0);
////         tranInfo.setAccountPayeeId(1);
//////tranInfo.setAs//tranInfo.setAllocationTradeIndicator(0);
//////tranInfo.setEndResultExchangeInd(0);
//////tranInfo.setExcludeAccountsList(string.Empty);
////         tranInfo.setReturnReadBackInfo(new UnsignedByte(2));
////         tranInfo.setReturnMessagesInfo(new UnsignedByte(1));
////         tranInfo.setNumberOfMasterTransactionLines(new UnsignedByte(lstFundIfo.size()));
////         tranInfo.setPriceCycleId(new UnsignedByte(200));
////         tranInfo.setUserId((short) 96);
//////tranInfo.setAgeBasedModelId(0);
//////tranInfo.setAutoDetermineFunds(0);
//////tranInfo.setAmountTypeIndicator(0);
//////tranInfo.setWebUserId("INV_310100016");
////         tranInfo.setRetirementIndicator(new UnsignedByte(0));
////         tranInfo.setMoneyAmount(new BigDecimal(1));
////         Calendar calendar = Calendar.getInstance();
////         calendar.setTime(new Date());
////         tranInfo.setTradeDate(calendar);
////         System.out.println("tranInfo = " + tranInfo);
//
//
//         FundInformation[] fundInfo = lstFundIfo.toArray(new FundInformation[lstFundIfo.size()]);
//         System.out.println("AuthenticateLogin:[UserId:" + userAcctDetails.getUserID() + ", Password:" + userAcctDetails.getPwd() + ", FundGroupName:" + userAcctDetails.getFundGroupName() + ", AllowableShareClassList:00]");
//         TransactionCollectionResult transactionCollectionResult= servicesSoap.createTransaction(
//            new AuthenticateLogin(userAcctDetails.getUserID(), userAcctDetails.getPwd(), userAcctDetails.getFundGroupName(), "00"),
//            tranInfo,fundInfo);
//
//         System.out.println(transactionCollectionResult.toString());
//         if (transactionCollectionResult==null)
//         {
//            return new WSCallStatus(SysParameters.wsResIssueCode, SysParameters.wsResIssueMsg);
//         }
//         else
//         {
//            return new WSCallStatus(transactionCollectionResult.getErrorStatus().getErrorCode(), transactionCollectionResult.getErrorStatus().getErrorMessage());
//         }
//      }
//
//   }
/*         TransactionInfo tranInfo = new TransactionInfo();
         //tranInfo.setBankName("JP Morgan Chase Bank");
//tranInfo.setBankRoutingNumber("021202337");
         tranInfo.setBankAccountNumber("6105640720");
//tranInfo.setBankAccountType(1);
//tranInfo.setNameOnAccount("JAIME L DESMOND");
         tranInfo.setMasterTransactionType(new UnsignedByte(1));
         tranInfo.setAccountNumber("310100016");
         tranInfo.setMoneyTransactionType(new UnsignedByte(3));
         tranInfo.setMasterTransactionSource(new UnsignedByte(9));
         tranInfo.setCurrencyId(new UnsignedByte(1));
//tranInfo.setDealerTransactionIndicator(0);
         tranInfo.setAccountPayeeId(1);
//tranInfo.setAs//tranInfo.setAllocationTradeIndicator(0);
//tranInfo.setEndResultExchangeInd(0);
//tranInfo.setExcludeAccountsList(string.Empty);
         tranInfo.setReturnReadBackInfo(new UnsignedByte(2));
         tranInfo.setReturnMessagesInfo(new UnsignedByte(1));
         tranInfo.setNumberOfMasterTransactionLines(new UnsignedByte(lstFundIfo.size()));
         tranInfo.setPriceCycleId(new UnsignedByte(200));
         tranInfo.setUserId((short) 96);
//tranInfo.setAgeBasedModelId(0);
//tranInfo.setAutoDetermineFunds(0);
//tranInfo.setAmountTypeIndicator(0);
//tranInfo.setWebUserId("INV_310100016");
         tranInfo.setRetirementIndicator(new UnsignedByte(0));
         tranInfo.setMoneyAmount(new BigDecimal(1));
         Calendar calendar = Calendar.getInstance();
         calendar.setTime(new Date());
         tranInfo.setTradeDate(calendar);*/
