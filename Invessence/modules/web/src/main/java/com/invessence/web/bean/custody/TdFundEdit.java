package com.invessence.web.bean.custody;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.*;
import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;

import com.invessence.converter.JavaUtil;
import com.invessence.service.bean.ServiceRequest;
import com.invessence.web.constant.*;
import com.invessence.web.dao.common.CommonDAO;
import com.invessence.web.dao.consumer.ConsumerListDataDAO;
import com.invessence.web.dao.custody.*;
import com.invessence.web.data.common.UserData;
import com.invessence.web.data.custody.TDMasterData;
import com.invessence.web.data.custody.td.*;
import com.invessence.web.util.Impl.PagesImpl;
import com.invessence.web.util.*;
import com.invessence.ws.bean.*;
import com.invessence.ws.provider.td.bean.DCResponse;
import com.invessence.ws.service.ServiceLayerImpl;
import org.apache.commons.logging.*;
import org.primefaces.event.*;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 8/7/16
 * Time: 5:36 PM
 * To change this template use File | Settings | File Templates.
 */

@ManagedBean(name = "tdEditFund")
@SessionScoped
public class TdFundEdit extends BaseTD
{
   private Integer activeTab = 0;   // Start with first tab.
   public Integer newTab, subtab;
   private String saveandOpenError;

   public void editStartCTO()
   {
      String msgheader;
      try
      {
         if (!FacesContext.getCurrentInstance().isPostback())
         {
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

            // Start fresh, clean and start from top page.
            setPagemanager(new PagesImpl(10));
            setTdMasterData(new TDMasterData(getPagemanager(), getLongBeanacctnum()));

            getCustodyListDAO().getAcatFirmList(getTdMasterData());
            Integer currentPage = getPagemanager().getPage();
            activeTab=0;
            getPagemanager().setPage(9);
            getPagemanager().clearAllErrorMessage();
            saveandOpenError = null;

            // load firm list for ACAt details
            editFundingInitPage();
         }
      }
      catch (Exception ex)
      {
         logger.info("Exception: raised during Starting TD CTO process.");
      }
   }

   public void setSubtab(Integer subtab)
   {
      this.subtab = subtab;
   }

   public Integer getActiveTab()
   {
      return activeTab;
   }

   public Integer getSubtab()
   {
      return subtab;
   }

   public void setActiveTab(Integer activeTab)
   {
      this.activeTab = activeTab;
      // subtab = 0;
   }

   public Boolean isAccordianDisabled(Integer pageno)
   {
      if (pageno != null)
      {
         if (pageno > 0 && pageno < getPagemanager().getMaxNoofPages())
         {
            if (getPagemanager().getLastPageVisited() >= pageno)
            {
               return false;
            }
         }
      }
      return true;
   }

   public void onTabChange(TabChangeEvent event)
   {
      try
      {

         if (event != null)
         {
            String tabname = event.getTab().getId();
            String tabnum = tabname.substring(3);
            Integer num = getWebutil().getConverter().getIntData(tabnum);
            subtab = 0;
            switch (num)
            {
               case 0:
                  getPagemanager().setPage(0);
                  break;
               case 1:
                  getPagemanager().setPage(1);
                  break;
               case 2:
                  getPagemanager().setPage(3);
                  break;
               case 3:
                  getPagemanager().setPage(5);
                  break;
               case 4:
                  getPagemanager().setPage(6);
                  break;
               case 5:
                  getPagemanager().setPage(8);
                  break;
               case 6:
                  getPagemanager().setPage(9);
                  break;
               default:
                  getPagemanager().setPage(0);

            }
         }
      }
      catch (Exception ex)
      {
         getPagemanager().setPage(0);
         //setActiveTab(0);
      }
   }

   public void onSubTabChange(TabChangeEvent event)
   {
      try
      {
         if (event != null)
         {
            String tabname = event.getTab().getId();
            String tabnum = tabname.substring(5);
            Integer num = getWebutil().getConverter().getIntData(tabnum);
            subtab = 0;
            switch (num)
            {
               case 0:

                  break;
               case 1:
                  //  pagemanager.nextPage();
                  subtab = 1;
                  break;

               default:
                  getPagemanager().setPage(0);

            }

            String tabnum1 = tabname.substring(3, 4);
            Integer num1 = getWebutil().getConverter().getIntData(tabnum1);
            switch (num1)
            {
               case 0:
                  getPagemanager().setPage(0);
                  break;
               case 1:
                  if (subtab == 0)
                     getPagemanager().setPage(1);
                  else
                     getPagemanager().setPage(2);
                  break;
               case 2:
                  if (subtab == 0)
                     getPagemanager().setPage(3);
                  else
                     getPagemanager().setPage(4);
                  break;
               case 3:
                  getPagemanager().setPage(5);
                  break;
               case 4:
                  if (subtab == 0)
                     getPagemanager().setPage(6);
                  else
                     getPagemanager().setPage(7);
                  break;
               case 5:
                  getPagemanager().setPage(8);
                  break;
               case 6:
                  if (subtab == 0)
                     getPagemanager().setPage(9);
                  else
                     getPagemanager().setPage(10);
                  break;
               default:
                  getPagemanager().setPage(0);

            }

         }
      }
      catch (Exception ex)
      {
         getPagemanager().setPage(0);
         //setActiveTab(0);

      }
   }

   private Integer pageControl(Integer pagenum)
   {
      if (pagenum == null)
      {
         return 1;
      }

      subtab = 0;
      switch (pagenum) // Note: The switch is based on pagenum
      {
         case 0: // accttype Page
            newTab = 0;
            break;
         case 1: // Individual Account Holder
            newTab = 1;
            break;
         case 2: // Joint Account Holder;
            if (getTdMasterData().getIsJointAcct())
            {
               newTab = 1;
               subtab = 1;
            }
            else
            {  // Force to go to next tab.
               getPagemanager().nextPage();
               newTab = 2;
            }
            break;
         case 3: // Individual Address
            newTab = 2;
            break;
         case 4: // Joint Address
            // If we are using next page, and the counter is 4, then if it is not joint, then go to tab #4.
            if (getTdMasterData().getIsJointAcct())
            {
               newTab = 2;
               subtab = 1;
            }
            else
            {
               getPagemanager().nextPage();
               newTab = 3;
            }
            break;
         case 5: // Regulatory;
            newTab = 3;
            break;
         case 6: // Employment
            newTab = 4;
            break;
         case 7: // Joint Employment
            // If we are using next page, and the counter is 4, then if it is not joint, then go to tab #5.
            if (getTdMasterData().getIsJointAcct())
            {
               newTab = 4;
               subtab = 1;
            }
            else
            {
               getPagemanager().nextPage();
               newTab = 5;
            }
            break;
         case 8: // Beneficiary

            newTab = 5;
            break;
         case 9: // Funding
            newTab = 6;
            break;
         case 10: // Funding Page 2
            if (getTdMasterData().getFundNow())  // Stay on same tab Fund now button
            {
               newTab = 6;
            }
            else
            {  // Force to go to next tab.
               //  pagemanager.nextPage();
               newTab = 6;
               subtab = 1;
            }
            break;
         default:
            newTab = null;
            break;
      }

      return newTab;
   }

   public void prevPage()
   {
      // customErrorText = null;   // If we go to previous page, then erase the error message.
      if (getTdMasterData().getAccttype() == 2 || getTdMasterData().getAccttype() == 3)
      {
         getPagemanager().prevPage();

      }
      else
      {
         getPagemanager().prevPage();
         getPagemanager().prevPage();
      }
      getPagemanager().clearAllErrorMessage();
      resetActiveTab(getPagemanager().getPage());
   }


   // Before going to next page, determine if the data in current tab passes validity,
   // then save the data beefore going to the next page
   // NOTE: Let resetActiveTab deal with joint account or not to go to appropriate tab.
   public void nextPage()
   {
      if (validatePage(getPagemanager().getPage()))
      {
         saveData(getPagemanager().getPage());
         if (getPagemanager().isLastPage())
            getTdMasterData().setSubmitButton(true);
         else
            getTdMasterData().setSubmitButton(false);

         getPagemanager().clearAllErrorMessage();
         if (getPagemanager().isLastPage() || (getPagemanager().getPage() == 9 && getTdMasterData().getFundNow()))
            resetActiveTab(11);
         else
         {
            getPagemanager().nextPage();
            resetActiveTab(getPagemanager().getPage());
         }
         saveandOpenError = null;
      }
   }

   public Boolean validateAllPage()
   {
      Integer currentPage = getPagemanager().getPage();
      Boolean status;
      saveandOpenError = null;

      getPagemanager().setPage(0);
      status = true;
      while (getPagemanager().getPage() <= getPagemanager().getMaxNoofPages())
      {
         if (validatePage(getPagemanager().getPage()))
         {
            //saveData(pagemanager.getPage());
            saveData(getPagemanager().getPage());
            if (getPagemanager().isLastPage())
            {
               break;
            }
            getPagemanager().nextPage();
            pageControl(getPagemanager().getPage());
         }
         else
         {
            currentPage = getPagemanager().getPage();
            status = false;
            break;
         }

      }
      getPagemanager().setPage(currentPage);
      resetActiveTab(getPagemanager().getPage());
      if (!status)
         saveandOpenError = "Please fill appropriate forms above.";

      return status;
   }


   private void resetActiveTab(Integer pagenum)
   {
      Integer nextTab = pageControl(pagenum);
      activeTab = nextTab;
   }

   private String loadData()
   {
      String retValue = "";
      Long logonid;
      if (getBeanacctnum() == null)
      {
         retValue = "NOACCOUNTNUMBER";
         return retValue;
      }

      if (getWebutil().isUserLoggedIn())
      {
         logonid = getWebutil().getLogonid();
      }
      else
      {
         logonid = getLongBeanlogonid();
      }

      getTdMasterData().getCustomerData().setAcctnum(getLongBeanacctnum());
      getTdMasterData().getCustomerData().setLogonid(logonid);
      getConsumerListDAO().getProfileData(getTdMasterData().getCustomerData());

      if (getTdMasterData().getCustomerData().getUserid() == null)
      {
         retValue = "NOACCOUNTMAP";
         return retValue;
      }
/*
      if (getLongBeanlogonid(). != webutil.getLogonid())
      {
         retValue = "differenUser";
         return retValue;
      }
*/

      if (getTdMasterData().getCustomerData().getEditable())
      {
         getCustodyListDAO().getTDAccountDetails(getTdMasterData());
         getCustodyListDAO().getTDAccountHolder(getTdMasterData());
         getCustodyListDAO().getTDEmployment(getTdMasterData());
         if (getTdMasterData().getOptoutBeneficiary())
         {
            getCustodySaveDAO().deleteBenefiaciaryDetails(getTdMasterData());
         }
         getCustodyListDAO().getTDBeneficiary(getTdMasterData());
         if (!getTdMasterData().getOptoutFunding())
         {
            getCustodyListDAO().getfundingData(getTdMasterData());
            if (getTdMasterData().getOptoutRecurring())
            {
               getTdMasterData().setRecurringFlag(true);
            }
         }
         else
         {
            getTdMasterData().setFundNow(true);
            getTdMasterData().setRecurringFlag(true);
         }


         // Fix issues related to bad data.
         if (getTdMasterData().getAcctOwnersDetail().getOwnership() == null ||
            getTdMasterData().getAcctOwnersDetail().getOwnership().isEmpty())
         {
            getTdMasterData().getAcctOwnersDetail().setOwnership("AOPRIMARY");
         }

         if (getTdMasterData().getJointAcctOwnersDetail().getOwnership() == null ||
            getTdMasterData().getJointAcctOwnersDetail().getOwnership().isEmpty())
         {
            getTdMasterData().getJointAcctOwnersDetail().setOwnership("AOJOINT");
         }
         retValue = "success";
         return retValue;
      }
      else
      {
         retValue = "ACCOUNTMANAGED";
         return retValue;
      }
   }


   public void editFundingInitPage()
   {
     try
      {

         loadTDAccountDetails();
         loadTDACHData();
      }
      catch (Exception e)
      {

      }

   }

   public void nextPageEdit()
   {
      if(getTdMasterData().getOptFund())
      {
         activeTab = 0;
         subtab = 1;
      }
      else
      {
         if (validatePage(getPagemanager().getPage()))
         {
            // saveData(pagemanager.getPage());
            if (getPagemanager().isLastPage())
               getTdMasterData().setSubmitButton(true);
            else
               getTdMasterData().setSubmitButton(false);

            getPagemanager().clearAllErrorMessage();
            if (getPagemanager().isLastPage() || (getPagemanager().getPage() == 9 && getTdMasterData().getFundNow()))
               resetActiveTab(11);
            else
            {
               getPagemanager().nextPage();
               activeTab = 0;
               subtab = 1;
            }
            saveandOpenError = null;
         }
      }
   }
   public void prevPageEdit()
   {
      activeTab=0;
      subtab=0;

   }
   public void optoutFundingChange()
   {
      if(getTdMasterData().getOptFund())
      {
         activeTab=0;
         subtab=1;
         getTdMasterData().setCopyAchInstructions(false);
      }
      else
      {
         activeTab=0;
         subtab=0;
      }
   }

   public void editFunddataSave()
   {
      String msg, msgheader;

      try
      {
         getPagemanager().setPage(9);
         if (!getTdMasterData().getOptFund()&& !validatePage(9))
         {
            activeTab = 0;
            subtab = 0;
            return;
         }
         else
         {
            getPagemanager().nextPage();
            pageControl(getPagemanager().getPage());
         }
         if (!getTdMasterData().getRecurringFlag()&& !validatePage(10))
         {
            activeTab = 0;
            subtab = 1;
            return;
         }


         WSCallStatus wsstatus;
         WSCallResult wsCallResult;

         String product=getWebutil().getWebprofile().getWebInfo().get("SERVICE.CUSTODY").toString();
         String mode=getWebutil().getWebprofile().getWebInfo().get("SERVICE.DOCUSIGN.MODE").toString();
         System.out.println("product "+product);
         System.out.println("mode "+mode);

         if(!getTdMasterData().getOptFund())
         {
            if (getTdMasterData().getFundType() != null && getTdMasterData().getFundType().equalsIgnoreCase("PMACH"))// for ACH acocunt
               getCustodySaveDAO().tdsaveACHData(getTdMasterData(), "ACH","EDIT");
            else if (getTdMasterData().getFundType() != null && getTdMasterData().getFundType().equalsIgnoreCase("PMFEDW"))
               getCustodySaveDAO().tdSaveACAT(getTdMasterData(), getTdMasterData().getAcctnum(), getTdMasterData().getAcatDetails(),"EDIT");

            else if (getTdMasterData().getFundType() != null && getTdMasterData().getFundType().equalsIgnoreCase("TDTRF"))
               getCustodySaveDAO().tdSaveTDTransferData(getTdMasterData(), getTdMasterData().getAcctnum(), getTdMasterData().getTdTransferDetails(),"EDIT");
         }
         if(!getTdMasterData().getRecurringFlag())
         {
            getCustodySaveDAO().tdSaveEFTEdit(getTdMasterData());
         }

         System.out.println("Advisor "+getTdMasterData().getCustomerData().getProfileInstance().getAdvisor());
         System.out.println("REP "+ getTdMasterData().getCustomerData().getProfileInstance().getRep());
         String eventRef = processDCRequest(getTdMasterData().getCustomerData().getProfileInstance().getAdvisor(), getTdMasterData().getCustomerData().getProfileInstance().getRep(), getTdMasterData().getAcctnum(), getTdMasterData().getRequest().getEventNum(), DCConstants.ACTION_FUNDING);

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

//         wsCallResult = getDcWebLayer().processDCRequest(new ServiceRequest("BUILDINGBENJAMINS", "UAT"), getTdMasterData().getAcctnum(), getTdMasterData().getRequest().getEventNum());
//         //return custodySaveDAO.processDCRequest(getTdMasterData().getCustomerData().getAdvisor(),getTdMasterData().getCustomerData().getRep(),acctnum,eventNo);
////         wsCallResult = getServiceLayer().processDCRequest(getTdMasterData().getAcctnum(), getTdMasterData().getRequest().getEventNum());
//         if (wsCallResult.getWSCallStatus().getErrorCode() != 0)
//         {
//            msg = wsCallResult.getWSCallStatus().getErrorMessage();
//            getWebutil().redirecttoMessagePage("ERROR", "Failed to Save", msg);
//         }
//         else
//         {
//
//            sendAlertMessage("F");
//            getUiLayout().doMenuAction("custody", "tdconfirmation.xhtml");
//         }
      }
      catch (Exception ex)
      {
         msgheader = "dctd.EX.101";
         getWebutil().redirecttoMessagePage("ERROR", "Service Error", msgheader);
         return;

      }
   }

}
