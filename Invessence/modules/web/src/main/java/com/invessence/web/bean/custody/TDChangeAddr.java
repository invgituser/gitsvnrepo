package com.invessence.web.bean.custody;

import javax.faces.bean.*;
import javax.faces.context.FacesContext;

import com.invessence.service.bean.ServiceRequest;
import com.invessence.web.constant.*;
import com.invessence.web.data.custody.TDMasterData;
import com.invessence.web.data.custody.td.AcctOwnersDetails;
import com.invessence.web.util.Impl.PagesImpl;
import com.invessence.ws.bean.*;
import com.invessence.ws.provider.td.bean.DCResponse;

/**
 * Created by sagar on 1/5/2017.
 */

@ManagedBean(name = "tdChngAddrs")
@SessionScoped
public class TDChangeAddr extends BaseTD
{

   public void editStartCTO()
   {
      String msgheader;
      try
      {

         System.out.println("Inside editStartCTO2 >> ");
         if (!FacesContext.getCurrentInstance().isPostback())
         {
            System.out.println("Inside editStartCTO2 >> ");
            if (!getWebutil().validatePriviledge(WebConst.ROLE_USER))
            {
               return;
            }

            if (getBeanacctnum() == null || getBeanacctnum().isEmpty())
            {
               msgheader = "dctd.100";
               getWebutil().redirecttoMessagePage("ERROR", "Access Denied", msgheader);
               return;
            }
            // clear all data.
            resetBaseTD();

            setTdMasterData(new TDMasterData(getPagemanager(), getLongBeanacctnum()));

            loadTDAccountDetails();
            getTdMasterData().setAcctOwnersDetailHistory(getTdMasterData().getAcctOwnersDetail());
            getTdMasterData().setJointAcctOwnersDetailHistory(getTdMasterData().getJointAcctOwnersDetail());
            getTdMasterData().setAcctOwnersDetail(new AcctOwnersDetails());
            getTdMasterData().setJointAcctOwnersDetail(new AcctOwnersDetails());
            getTdMasterData().setAcctholderhasMailing(false);

         }
      }
      catch (Exception ex)
      {
         logger.info("Exception: raised during Starting TD CTO process.");
      }
   }


   public void nextPageEdit()
   {
      String msg, msgheader;

      try
      {
         System.out.println("Inside nextPageEdit >> ");
         boolean bflagHistory = false, bflag = false, bflagNewAdd = false;
         getPagemanager().clearAllErrorMessage();
         getPagemanager().setPage(3);
         System.out.println("Inside nextPageEdit >> " + getTdMasterData().getAcctholderhasMailing());
         if (validateChangeAddress(getTdMasterData().getAcctOwnersDetailHistory(), false))
         {
            bflagHistory = true;
         }
         bflagNewAdd = true;
         getPagemanager().setPage(4);
         if (validateChangeAddress(getTdMasterData().getAcctOwnersDetail(), true))
         {
//           if(!getTdMasterData().getAcctholderhasMailing()){
//              getTdMasterData().getAcctOwnersDetail().setMailingAddressStreet(getTdMasterData().getAcctOwnersDetail().getPhysicalAddressStreet());
//              getTdMasterData().getAcctOwnersDetail().setMailingAddressCity(getTdMasterData().getAcctOwnersDetail().getPhysicalAddressCity());
//              getTdMasterData().getAcctOwnersDetail().setMailingAddressState(getTdMasterData().getAcctOwnersDetail().getPhysicalAddressState());
//              getTdMasterData().getAcctOwnersDetail().setMailingAddressZipCode(getTdMasterData().getAcctOwnersDetail().getPhysicalAddressZipCode());
//           }
            bflag = true;
         }


         if (bflag && bflagHistory)
         {
            getCustodySaveDAO().tdSaveAccountChangeAddrs(getTdMasterData().getAcctOwnersDetailHistory(), getTdMasterData().getAcctOwnersDetail(), getTdMasterData());


            WSCallResult wsCallResult;
            String product=getWebutil().getWebprofile().getWebInfo().get("SERVICE.CUSTODY").toString();
            String mode=getWebutil().getWebprofile().getWebInfo().get("SERVICE.DOCUSIGN.MODE").toString();
            System.out.println("product "+product);
            System.out.println("mode "+mode);
            System.out.println("Advisor "+getTdMasterData().getCustomerData().getProfileInstance().getAdvisor());
            System.out.println("REP "+ getTdMasterData().getCustomerData().getProfileInstance().getRep());
            String eventRef = processDCRequest( getTdMasterData().getCustomerData().getProfileInstance().getAdvisor(), getTdMasterData().getCustomerData().getProfileInstance().getRep(), getTdMasterData().getAcctnum(), getTdMasterData().getRequest().getEventNum(), DCConstants.ACTION_ACCT_OPEN);

            System.out.println("Docusign Event Return " + eventRef);
            if (eventRef != null)
            {
               String eventNo[] = eventRef.split(",");
               int eventnum = Integer.parseInt(eventNo[0]);
               System.out.println("Docusign First event No " + eventnum);
               wsCallResult = getDcWebLayer().processDCRequest(new ServiceRequest(product, mode), getTdMasterData().getAcctnum(), eventnum);
               System.out.println("Docusign wsCallResult " + wsCallResult);
               if (wsCallResult.getWSCallStatus().getErrorCode() != 0)
               {
                  msg = wsCallResult.getWSCallStatus().getErrorMessage();
                  getWebutil().redirecttoMessagePage("ERROR", "Failed to Save", msg);
               }
               else
               {
//               DCResponse dcResponse = (DCResponse) wsCallResult.getGenericObject();
//               System.out.println("dcResponse = " + dcResponse);
                  sendAlertMessage("P");
                  getUiLayout().doMenuAction("custody", "tdconfirmation.xhtml");
               }
               eventnum = Integer.parseInt(eventNo[1]);
               System.out.println("Docusign Second event No " + eventnum);
               if (eventnum != 0)
               {
                  wsCallResult = getDcWebLayer().processDCRequest(new ServiceRequest(product, mode), getTdMasterData().getAcctnum(), eventnum);
                  System.out.println("Docusign wsCallResult " + wsCallResult);
                  if (wsCallResult.getWSCallStatus().getErrorCode() != 0)
                  {
                     msg = wsCallResult.getWSCallStatus().getErrorMessage();
                     getWebutil().redirecttoMessagePage("ERROR", "Failed to Save", msg);
                  }
                  else
                  {
//               DCResponse dcResponse = (DCResponse) wsCallResult.getGenericObject();
//               System.out.println("dcResponse = " + dcResponse);
                     sendAlertMessage("P");
                     getUiLayout().doMenuAction("custody", "tdconfirmation.xhtml");
                  }
               }
            }
            else
            {
               getWebutil().redirecttoMessagePage("ERROR", "Failed to Save", "Error occurred while document request generation");
            }
//
            //wsCallResult = getServiceLayer().processDCRequest(getTdMasterData().getAcctnum(), getTdMasterData().getRequest().getEventNum());
//            wsCallResult = getDcWebLayer().processDCRequest(new ServiceRequest("BUILDINGBENJAMINS", "UAT"), getTdMasterData().getAcctnum(), getTdMasterData().getRequest().getEventNum());
//            if (wsCallResult.getWSCallStatus().getErrorCode() != 0)
//            {
//               msg = wsCallResult.getWSCallStatus().getErrorMessage();
//               getWebutil().redirecttoMessagePage("ERROR", "Failed to Save", msg);
//            }
//            else
//            {
//               // sendAlertMessage("A");
////              getTdMasterData().getAcctnum();
////              getTdMasterData().getCustomerData().getAdvisor();
////              getTdMasterData().getCustomerData().getRep();
////
//               System.out.println("Inside nextPageEdit >> Account No " + getTdMasterData().getAcctnum());
//               System.out.println("Inside nextPageEdit >> Advisor" + getWebutil().getUserInfoData().getAdvisor());
//               System.out.println("Inside nextPageEdit >> Rep " + getWebutil().getUserInfoData().getRep());
//               getCustodySaveDAO().tdMngAdvisorNotification(getTdMasterData().getAcctnum(), getWebutil().getUserInfoData().getAdvisor(), getWebutil().getUserInfoData().getRep(), "CHNGADDRS");
//
//               getUiLayout().doMenuAction("custody", "tdconfirmation.xhtml");
//            }
         }

      }
      catch (Exception ex)
      {
         msgheader = "dctd.EX.101";
         getWebutil().redirecttoMessagePage("ERROR", "Service Error", msgheader);
         return;

      }
   }
}
