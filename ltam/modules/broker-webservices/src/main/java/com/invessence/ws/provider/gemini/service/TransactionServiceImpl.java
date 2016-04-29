package com.invessence.ws.provider.gemini.service;

import java.math.BigDecimal;
import java.util.*;

import com.invessence.ws.bean.*;
import com.invessence.ws.util.SysParameters;
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

   String encryDecryKey="aRXDugfr4WQpVrxu";

   @Override
   public WSCallResult fundAccount(UserAcctDetails userAcctDetails, int fundID, double amount, String bankAccountNumber) throws Exception
   {
      logger.info("TransactionServiceImpl.fundAccount");
      logger.debug("userAcctDetails = [" + userAcctDetails + "], fundID = [" + fundID + "], amount = [" + amount + "], bankAccountNumber = [" + bankAccountNumber + "]");
      AchPayeeResult achPayeeResult=new AccountServiceImpl().getAchPayeeCollection(userAcctDetails, bankAccountNumber);
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
         tranInfo.setRetirementIndicator(new UnsignedByte(0));
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
            return new WSCallResult( new WSCallStatus(SysParameters.wsResIssueCode, SysParameters.wsResIssueMsg),null);
         }else if(transactionCollectionResult.getErrorStatus().getErrorCode()==0)
         {
            TransactionDetails transactionDetails=new TransactionDetails(""+transactionCollectionResult.getMasterTransactionId());
            return new WSCallResult( new WSCallStatus(transactionCollectionResult.getErrorStatus().getErrorCode(), transactionCollectionResult.getErrorStatus().getErrorMessage()),transactionDetails);
         }else
         {
            return new WSCallResult( new WSCallStatus(transactionCollectionResult.getErrorStatus().getErrorCode(), transactionCollectionResult.getErrorStatus().getErrorMessage()),null);
         }
      }
   }

   @Override
   public WSCallResult fullFundTransfer(UserAcctDetails userAcctDetails, int fromFundID, int toFundID, String bankAccountNumber) throws Exception
   {
      logger.info("TransactionServiceImpl.fullFundTransfer");
      logger.debug("userAcctDetails = [" + userAcctDetails + "], fromFundID = [" + fromFundID + "], toFundID = [" + toFundID + "], bankAccountNumber = [" + bankAccountNumber + "]");
      AchPayeeResult achPayeeResult=new AccountServiceImpl().getAchPayeeCollection(userAcctDetails, bankAccountNumber);
      logger.debug("achPayeeResult = " + achPayeeResult);
      if(achPayeeResult==null){
         return new WSCallResult( new WSCallStatus(SysParameters.wsResIssueCode, SysParameters.wsResIssueMsg),null);
      }else
      {
         servicesSoap = servicesLocator.getTransactionServicesSoap();
         List<FundInformation> lstFundIfo = new ArrayList<>();
         BigDecimal tranTotalAmount;

         FundInformation fromFund = new FundInformation();
         fromFund.setFundId((short) fromFundID);         // Fund to trade FROM
         fromFund.setAmountType(new UnsignedByte(7));    // 7 - full redemption / close account
         fromFund.setAmount(new BigDecimal(100));        // 100%
         fromFund.setFromToLineIndicator(new UnsignedByte(1));
         lstFundIfo.add(fromFund);
         logger.debug("fromFund = " + fromFund);

         FundInformation toFund = new FundInformation();
         toFund.setFundId((short) toFundID);             // Fund to trade TO
         toFund.setAmountType(new UnsignedByte(4));      // 4 - full redemption
         toFund.setAmount(new BigDecimal(100));          // 100%
         toFund.setFromToLineIndicator(new UnsignedByte(0));
         lstFundIfo.add(toFund);
         logger.debug("toFund = " + toFund);

         tranTotalAmount=fromFund.getAmount().add(toFund.getAmount());

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
         tranInfo.setRetirementIndicator(new UnsignedByte(0));
         tranInfo.setMoneyAmount(tranTotalAmount);
         Calendar calendar = Calendar.getInstance();
         calendar.setTime(new Date());
         tranInfo.setTradeDate(calendar);
         logger.debug("tranInfo = " + tranInfo);

         FundInformation[] fundInfo = lstFundIfo.toArray(new FundInformation[lstFundIfo.size()]);
         TransactionCollectionResult transactionCollectionResult= servicesSoap.createTransaction(
            new AuthenticateLogin(userAcctDetails.getUserID(), userAcctDetails.getPwd(), userAcctDetails.getFundGroupName(), "00"),
            tranInfo,fundInfo);
         logger.debug("transactionCollectionResult = " + transactionCollectionResult);

         if (transactionCollectionResult==null|| transactionCollectionResult.getErrorStatus()==null)
         {
            return new WSCallResult( new WSCallStatus(SysParameters.wsResIssueCode, SysParameters.wsResIssueMsg),null);
         }else if(transactionCollectionResult.getErrorStatus().getErrorCode()==0)
         {
            TransactionDetails transactionDetails=new TransactionDetails(""+transactionCollectionResult.getMasterTransactionId());
            return new WSCallResult( new WSCallStatus(transactionCollectionResult.getErrorStatus().getErrorCode(), transactionCollectionResult.getErrorStatus().getErrorMessage()),transactionDetails);
         }else
         {
            if(transactionCollectionResult.getNoOfErrors()>0 || transactionCollectionResult.getErrorStatus().getErrorCode()!=0){
               StringBuilder errMsg=new StringBuilder();
               TransactionMessageResult []transactionMessageResults=transactionCollectionResult.getTransactionMessageCollection();
               if(transactionMessageResults.length>0){
                  return new WSCallResult( new WSCallStatus(transactionMessageResults[0].getTransactionMessageId(), transactionMessageResults[0].getTransactionMessage()),null);
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
               return new WSCallResult( new WSCallStatus(transactionCollectionResult.getErrorStatus().getErrorCode(), transactionCollectionResult.getErrorStatus().getErrorMessage()),null);
            }else
            {
               return new WSCallResult( new WSCallStatus(transactionCollectionResult.getErrorStatus().getErrorCode(), transactionCollectionResult.getErrorStatus().getErrorMessage()),null);
            }
         }
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
