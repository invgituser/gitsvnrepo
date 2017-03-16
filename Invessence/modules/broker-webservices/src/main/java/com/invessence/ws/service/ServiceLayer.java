package com.invessence.ws.service;

import java.util.List;

import com.invessence.service.bean.ServiceRequest;
import com.invessence.ws.bean.*;
import com.invessence.ws.provider.td.bean.DCRequest;

/**
 * Created by abhangp on 3/11/2016.
 */
public interface ServiceLayer
{
   public WSCallResult processDCRequest(ServiceRequest serviceRequest, List<DCRequest> dcRequests);
   public WSCallResult processDCRequest(Long acctNum, int eventNum);
   public void createPendingUser();
   public WSCallStatus updateEmail(String clientAccountID, String newEmail);
   public WSCallStatus updateMailingAddress(String clientAccountID,
                                            String firstName,String middleName,String lastName,
                                            String addressLine1, String addressLine2,String addressLine3,
                                            String city, String  state,String postalZip, short countryCode,
                                            String voicePhone, String altPhone, String faxPhone, String emailAddress);

   public WSCallResult getUserBankAcctDetails(String clientAccountID);

   public WSCallResult fundAccount(String clientAccountID, int fundID, double amount, String bankAccountNumber);
   public WSCallResult fullFundTransfer(String clientAccountID, int fromFundID, int toFundID, String bankAccountNumber);
   public boolean isServiceActive();

   public WSCallStatus resetPassword(String clientAccountID);

//   public void resetPassword();
//   public void toTestAPI();
   //Future required functions

   //public WSCallStatus loginUser(String clientAccountID);
   //public WSCallStatso createUser(String clientAccountID, String securityQuestion, String securityAnswer);
   //public WSCallStatus isUserExist(String userId);
   public WSCallResult getMailingAddress(String clientAccountID);

}
