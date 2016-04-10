package com.ws.gemini.service;

import java.math.BigDecimal;
import java.util.*;

import com.invessence.bean.*;
import com.ws.gemini.wsdl.account.AchPayeeResult;
import com.ws.gemini.wsdl.transaction.*;
import org.apache.axis.types.UnsignedByte;

/**
 * Created by abhangp on 3/28/2016.
 */
public class TransactionServiceImpl implements TransactionService
{
   TransactionServicesLocator servicesLocator = new TransactionServicesLocator();
   TransactionServicesSoap_PortType servicesSoap = null;

   String encryDecryKey="aRXDugfr4WQpVrxu";

   @Override
   public CallStatus fundAccount(UserAcctDetails userAcctDetails,int fundID, double amount, String accountNumber) throws Exception
   {

      AchPayeeResult achPayeeResult=new AccountServiceImpl().getAchPayeeCollection(userAcctDetails, accountNumber);
      System.out.println("achPayeeResult = " + achPayeeResult.toString());
      if(achPayeeResult==null){
         return  null;
      }else
      {
         servicesSoap = servicesLocator.getTransactionServicesSoap();
         List<FundInformation> lstFundIfo = new ArrayList<>();
         FundInformation fi1 = new FundInformation();
         fi1.setFundId((short) fundID);
         fi1.setAmountType(new UnsignedByte(1));
         fi1.setAmount(new BigDecimal(amount));
         fi1.setFromToLineIndicator(new UnsignedByte(0));
         lstFundIfo.add(fi1);

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
         System.out.println("tranInfo = " + tranInfo);


         FundInformation[] fundInfo = lstFundIfo.toArray(new FundInformation[lstFundIfo.size()]);
         System.out.println("AuthenticateLogin:[UserId:" + userAcctDetails.getUserID() + ", Password:" + userAcctDetails.getPwd() + ", FundGroupName:" + userAcctDetails.getFundGroupName() + ", AllowableShareClassList:00]");
      TransactionCollectionResult transactionCollectionResult= servicesSoap.createTransaction(
         new AuthenticateLogin(userAcctDetails.getUserID(), userAcctDetails.getPwd(), userAcctDetails.getFundGroupName(), "00"),
         tranInfo,fundInfo);

      System.out.println(transactionCollectionResult.toString());
      if (transactionCollectionResult==null)
      {
         return null;
      }
      else
      {
         return new CallStatus(transactionCollectionResult.getErrorStatus().getErrorCode(),transactionCollectionResult.getErrorStatus().getErrorMessage());
      }
      }
      //return null;
   }

   @Override
   public CallStatus fundTransfer(UserAcctDetails userAcctDetails, int fromFundID, int toFundID, double amount, String accountNumber) throws Exception
   {
      AchPayeeResult achPayeeResult=new AccountServiceImpl().getAchPayeeCollection(userAcctDetails, accountNumber);
      System.out.println("achPayeeResult = " + achPayeeResult.toString());
      if(achPayeeResult==null){
         return  null;
      }else
      {
         servicesSoap = servicesLocator.getTransactionServicesSoap();
         List<FundInformation> lstFundIfo = new ArrayList<>();
         FundInformation fi1 = new FundInformation();
         fi1.setFundId((short) fromFundID);
         fi1.setAmountType(new UnsignedByte(1));
         fi1.setAmount(new BigDecimal(amount));
         fi1.setFromToLineIndicator(new UnsignedByte(0));
         lstFundIfo.add(fi1);

         FundInformation fi2 = new FundInformation();
         fi2.setFundId((short) toFundID);
         fi2.setAmountType(new UnsignedByte(1));
         fi2.setAmount(new BigDecimal(amount));
         fi2.setFromToLineIndicator(new UnsignedByte(1));
         lstFundIfo.add(fi2);

         TransactionInfo tranInfo = new TransactionInfo();
         tranInfo.setBankName(achPayeeResult.getBankName().trim());
         //tranInfo.setBankRoutingNumber(achPayeeResult.getBankRoutingNumber().trim());
         tranInfo.setBankAccountNumber(achPayeeResult.getBankAccountNumber().trim());
         tranInfo.setBankAccountType(achPayeeResult.getBankAccountType());
         tranInfo.setMasterTransactionType(new UnsignedByte(3));
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
         System.out.println("tranInfo = " + tranInfo);



//         TransactionInfo tranInfo = new TransactionInfo();
//         //tranInfo.setBankName("JP Morgan Chase Bank");
////tranInfo.setBankRoutingNumber("021202337");
//         tranInfo.setBankAccountNumber("6105640720");
////tranInfo.setBankAccountType(1);
////tranInfo.setNameOnAccount("JAIME L DESMOND");
//         tranInfo.setMasterTransactionType(new UnsignedByte(3));
//         tranInfo.setAccountNumber("310100016");
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


         FundInformation[] fundInfo = lstFundIfo.toArray(new FundInformation[lstFundIfo.size()]);
         System.out.println("AuthenticateLogin:[UserId:" + userAcctDetails.getUserID() + ", Password:" + userAcctDetails.getPwd() + ", FundGroupName:" + userAcctDetails.getFundGroupName() + ", AllowableShareClassList:00]");
         TransactionCollectionResult transactionCollectionResult= servicesSoap.createTransaction(
            new AuthenticateLogin(userAcctDetails.getUserID(), userAcctDetails.getPwd(), userAcctDetails.getFundGroupName(), "00"),
            tranInfo,fundInfo);

         System.out.println(transactionCollectionResult.toString());
         if (transactionCollectionResult==null)
         {
            return null;
         }
         else
         {
            return new CallStatus(transactionCollectionResult.getErrorStatus().getErrorCode(),transactionCollectionResult.getErrorStatus().getErrorMessage());
         }
      }

   }
}
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
