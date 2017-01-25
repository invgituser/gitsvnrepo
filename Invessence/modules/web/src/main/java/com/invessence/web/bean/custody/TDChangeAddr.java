package com.invessence.web.bean.custody;

import javax.faces.bean.*;
import javax.faces.context.FacesContext;

import com.invessence.web.constant.WebConst;
import com.invessence.web.data.custody.TDMasterData;
import com.invessence.web.data.custody.td.AcctOwnersDetails;
import com.invessence.web.util.Impl.PagesImpl;
import com.invessence.ws.bean.*;

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
         boolean bflagHistory = false, bflag = false,bflagNewAdd=false;
         getPagemanager().clearAllErrorMessage();
         getPagemanager().setPage(3);
         System.out.println("Inside nextPageEdit >> "+ getTdMasterData().getAcctholderhasMailing());
         if (validateChangeAddress(getTdMasterData().getAcctOwnersDetailHistory(),false))
         {
            bflagHistory = true;
         }
         bflagNewAdd=true;
         getPagemanager().setPage(4);
         if (validateChangeAddress(getTdMasterData().getAcctOwnersDetail(),true))
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
            getCustodySaveDAO().tdSaveAccountChangeAddrs(getTdMasterData().getAcctOwnersDetailHistory(), getTdMasterData().getAcctOwnersDetail(),getTdMasterData());
            WSCallStatus wsstatus;
            WSCallResult wsCallResult;

            wsCallResult = getServiceLayer().processDCRequest(getTdMasterData().getAcctnum(), getTdMasterData().getRequest().getEventNum());
            if (wsCallResult.getWSCallStatus().getErrorCode() != 0)
            {
              msg = wsCallResult.getWSCallStatus().getErrorMessage();

              getWebutil().redirecttoMessagePage("ERROR", "Failed to Save", msg);
           }else
           {
             // sendAlertMessage("A");
//              getTdMasterData().getAcctnum();
//              getTdMasterData().getCustomerData().getAdvisor();
//              getTdMasterData().getCustomerData().getRep();
//
              System.out.println("Inside nextPageEdit >> Account No "+getTdMasterData().getAcctnum());
              System.out.println("Inside nextPageEdit >> Advisor"+getWebutil().getUserInfoData().getAdvisor());
              System.out.println("Inside nextPageEdit >> Rep "+getWebutil().getUserInfoData().getRep());
              getCustodySaveDAO().tdMngAdvisorNotification(getTdMasterData().getAcctnum(),getWebutil().getUserInfoData().getAdvisor(),getWebutil().getUserInfoData().getRep(),"CHNGADDRS" );

              getUiLayout().doMenuAction("custody", "tdconfirmation.xhtml");
           }
         }

      }catch (Exception ex)
      {
         msgheader = "dctd.EX.101";
         getWebutil().redirecttoMessagePage("ERROR", "Service Error", msgheader);
         return;

      }
   }
}
