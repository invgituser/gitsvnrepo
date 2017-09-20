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

            getTdMasterData().getCustomerData().setAcctnum(getLongBeanacctnum());
            getTdMasterData().getCustomerData().setLogonid(getWebutil().getLogonid());

            loadCustomerProfileData();
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
            String product = getWebutil().getWebprofile().getWebInfo().get("SERVICE.CUSTODY").toString();
            String mode = getWebutil().getWebprofile().getWebInfo().get("SERVICE.DOCUSIGN.MODE").toString();
            System.out.println("#################################################### Change Address DC Request Start ##########################################");
            System.out.println("Product " + product);
            System.out.println("Mode " + mode);
            System.out.println("Advisor " + getTdMasterData().getCustomerData().getProfileInstance().getAdvisor());
            System.out.println("Rep " + getTdMasterData().getCustomerData().getProfileInstance().getRep());
            System.out.println("Account No " + getTdMasterData().getAcctnum());
            String eventRef = processDCRequest(getTdMasterData().getCustomerData().getProfileInstance().getAdvisor(), getTdMasterData().getCustomerData().getProfileInstance().getRep(), getTdMasterData().getAcctnum(), getTdMasterData().getRequest().getEventNum(), DCConstants.ACTION_CHNG_ADDR);

            System.out.println("Docusign Event Return " + eventRef);
            if (eventRef != null)
            {
               String eventNo[] = eventRef.split(",");
               for (int i = 0; i < eventNo.length; i++)
               {
                  int eventnum = Integer.parseInt(eventNo[i]);
                  System.out.println("Docusign event No " + eventnum);
                  wsCallResult = getDcWebLayer().processDCRequest(new ServiceRequest(product, mode), getTdMasterData().getAcctnum(), eventnum);
                  System.out.println("Docusign wsCallResult " + wsCallResult);
                  if (wsCallResult.getWSCallStatus().getErrorCode() != 0)
                  {
                     msg = wsCallResult.getWSCallStatus().getErrorMessage();
                     getWebutil().redirecttoMessagePage("ERROR", "Failed to Save", msg);
                  }
                  else
                  {
                     sendAlertMessage("P");
                     getUiLayout().doMenuAction("custody", "tdconfirmation.xhtml");
                  }
               }
            }
            else
            {
               getWebutil().redirecttoMessagePage("ERROR", "Failed to Save", "Error occurred while document request generation");
            }
            System.out.println("#################################################### Change Address DC Request End ##########################################");
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
