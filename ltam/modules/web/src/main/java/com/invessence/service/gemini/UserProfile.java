package com.invessence.service.gemini;



/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 4/11/16
 * Time: 10:13 PM
 * To change this template use File | Settings | File Templates.
 */
public class UserProfile
{

/*
   ServiceLayer serviceLayer;
*/

/*
   UserInfoDAO userInfoDAO;
*/

   public Boolean updateEmail(Long logonid, String newemail) {
      try {
/*
         List<AccountData> listofAccount = null;
         WSCallStatus status;
         ServiceResult serviceResult = new ServiceResult(true, null);
         listofAccount = userInfoDAO.getListofAccount(logonid, null);

         if (listofAccount != null && listofAccount.size() > 0) {
            for (AccountData data: listofAccount) {
               if (data.getClientAccountID() != null) {
                  if (data.getPrivileges().equalsIgnoreCase("A")) {
                     status = serviceLayer.updateEmail(data.getClientAccountID(), newemail);
                     if (status == null || status.getErrorCode() != 0) {
                        serviceResult.setStatus(false);
                        serviceResult.setMessage(status.getErrorMessage());
                     }
                  }
               }
            }
         }
*/
      }
      catch (Exception ex) {

      }
      return true;
   }

}
