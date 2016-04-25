package com.invessence.service.gemini;


import java.util.List;
import javax.faces.bean.ManagedProperty;

import com.invessence.dao.common.UserInfoDAO;
import com.invessence.data.common.*;
import com.invessence.ws.bean.WSCallStatus;
import com.invessence.ws.service.ServiceLayer;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 4/11/16
 * Time: 10:13 PM
 * To change this template use File | Settings | File Templates.
 */
public class UserProfile
{

   @ManagedProperty("#{serviceLayer}")
   private ServiceLayer serviceLayer;
   public void setServiceLayer(ServiceLayer serviceLayer)
   {
      this.serviceLayer = serviceLayer;
   }

   @ManagedProperty("#{userInfoDAO}")
   UserInfoDAO userInfoDAO;

   public void setUserInfoDAO(UserInfoDAO userInfoDAO)
   {
      this.userInfoDAO = userInfoDAO;
   }

   public ServiceResult updateEmail(Long logonid, String origemail, String newemail) {
      ServiceResult serviceResult = null;
      try {
         if (newemail == null || newemail.isEmpty()) {
            serviceResult = new ServiceResult(false, "Unchanged. No, new email address");
         }

         List<AccountData> listofAccount = null;
         WSCallStatus status;
         listofAccount = userInfoDAO.getListofAccount(logonid, null);

         if (listofAccount != null && listofAccount.size() > 0) {
            serviceResult = new ServiceResult(true, null);
            for (AccountData data: listofAccount) {
               if (data.getClientAccountID() != null) {
                  if (data.getPrivileges().equalsIgnoreCase("A")) {
                     status = serviceLayer.updateEmail(data.getClientAccountID(), newemail);
                     if (status == null || status.getErrorCode() != 0) {
                        serviceResult.setStatus(false);
                        serviceResult.setMessage(((status.getErrorMessage() != null) ? status.getErrorMessage() : "")) ;
                        break;
                     }
                  }
               }
            }
         }
      }
      catch (Exception ex) {
         serviceResult = new ServiceResult(false, ex.getMessage());
      }
      return serviceResult;
   }

}
