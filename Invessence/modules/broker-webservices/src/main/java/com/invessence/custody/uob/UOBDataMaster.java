package com.invessence.custody.uob;

import com.invessence.custody.uob.data.*;

/**
 * Created by abhangp on 10/24/2017.
 */
public class UOBDataMaster
{
   private AccountDetails accountDetails;
   private OwnerDetails individualOwnersDetails;
   private OwnerDetails jointOwnersDetails;

   @Override
   public String toString()
   {
      return "UOBDataMaster{" +
         "accountDetails=" + accountDetails +
         ", individualOwnersDetails=" + individualOwnersDetails +
         ", jointOwnersDetails=" + jointOwnersDetails +
         '}';
   }

   public AccountDetails getAccountDetails()
   {
      return accountDetails;
   }

   public void setAccountDetails(AccountDetails accountDetails)
   {
      this.accountDetails = accountDetails;
   }

   public OwnerDetails getIndividualOwnersDetails()
   {
      return individualOwnersDetails;
   }

   public void setIndividualOwnersDetails(OwnerDetails individualOwnersDetails)
   {
      this.individualOwnersDetails = individualOwnersDetails;
   }

   public OwnerDetails getJointOwnersDetails()
   {
      return jointOwnersDetails;
   }

   public void setJointOwnersDetails(OwnerDetails jointOwnersDetails)
   {
      this.jointOwnersDetails = jointOwnersDetails;
   }
}
