package com.invessence.web.data.custody.td;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 9/2/16
 * Time: 10:39 PM
 * To change this template use File | Settings | File Templates.
 */
public class ACATDetails
{
   private Long acctnum;
   private Long reqId;
   private String fromAccountTitle;
   private String accountNumber2;
   private String fullName;
   private String fromFirmAddress;
   private String fromFirmPhoneNumber;
   private String fromEEPlanType;
   private String simpleFunded;
   private String fromOtherAccountType;
   private String transferTypeId;

   public ACATDetails()
   {
   }

   public ACATDetails(Long acctnum, Long reqId)
   {
      this.acctnum = acctnum;
      this.reqId = reqId;
   }

   public Long getAcctnum()
   {
      return acctnum;
   }

   public void setAcctnum(Long acctnum)
   {
      this.acctnum = acctnum;
   }

   public Long getReqId()
   {
      return reqId;
   }

   public void setReqId(Long reqId)
   {
      this.reqId = reqId;
   }

   public String getFromAccountTitle()
   {
      return fromAccountTitle;
   }

   public void setFromAccountTitle(String fromAccountTitle)
   {
      this.fromAccountTitle = fromAccountTitle;
   }

   public String getAccountNumber2()
   {
      return accountNumber2;
   }

   public void setAccountNumber2(String accountNumber2)
   {
      this.accountNumber2 = accountNumber2;
   }

   public String getFullName()
   {
      return fullName;
   }

   public void setFullName(String fullName)
   {
      this.fullName = fullName;
   }

   public String getFromFirmAddress()
   {
      return fromFirmAddress;
   }

   public void setFromFirmAddress(String fromFirmAddress)
   {
      this.fromFirmAddress = fromFirmAddress;
   }

   public String getFromFirmPhoneNumber()
   {
      return fromFirmPhoneNumber;
   }

   public void setFromFirmPhoneNumber(String fromFirmPhoneNumber)
   {
      this.fromFirmPhoneNumber = fromFirmPhoneNumber;
   }

   public String getFromEEPlanType()
   {
      return fromEEPlanType;
   }

   public void setFromEEPlanType(String fromEEPlanType)
   {
      this.fromEEPlanType = fromEEPlanType;
   }

   public String getSimpleFunded()
   {
      return simpleFunded;
   }

   public void setSimpleFunded(String simpleFunded)
   {
      this.simpleFunded = simpleFunded;
   }

   public String getFromOtherAccountType()
   {
      return fromOtherAccountType;
   }

   public void setFromOtherAccountType(String fromOtherAccountType)
   {
      this.fromOtherAccountType = fromOtherAccountType;
   }

   public String getTransferTypeId()
   {
      return transferTypeId;
   }

   public void setTransferTypeId(String transferTypeId)
   {
      this.transferTypeId = transferTypeId;
   }
}
