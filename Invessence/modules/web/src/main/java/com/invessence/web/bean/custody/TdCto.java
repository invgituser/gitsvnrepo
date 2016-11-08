package com.invessence.web.bean.custody;

import java.util.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;

import com.invessence.converter.JavaUtil;
import com.invessence.web.dao.common.*;
import com.invessence.web.dao.consumer.ConsumerListDataDAO;
import com.invessence.web.dao.custody.*;
import com.invessence.web.data.common.UserData;
import com.invessence.web.data.custody.*;
import com.invessence.web.data.custody.td.*;
import com.invessence.web.data.custody.td.BenefiaciaryDetails;
import com.invessence.web.util.*;
import com.invessence.web.util.Impl.PagesImpl;
import com.invessence.ws.bean.*;
import com.invessence.ws.service.ServiceLayerImpl;
import org.apache.commons.lang.SystemUtils;
import org.apache.commons.logging.*;
import org.primefaces.component.accordionpanel.AccordionPanel;
import org.primefaces.context.RequestContext;
import org.primefaces.event.*;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 8/7/16
 * Time: 5:36 PM
 * To change this template use File | Settings | File Templates.
 */

@ManagedBean(name = "tdcto")
@SessionScoped
public class TdCto
{
   protected final Log logger = LogFactory.getLog(getClass());
   private String beanacctnum;
   private String beanlogonID;
   private UserData userdata = new UserData();
   private TDMasterData tdMasterData = new TDMasterData(null, 0L);
   private PagesImpl pagemanager = new PagesImpl(11);
   private Integer activeTab = 0;   // Start with first tab.
   public Integer newTab, subtab;
   private String defaultCheckedImage = "/javax.faces.resource/images/checkedN.png.xhtml?ln=tcm";
   private String selectedCheckedImage = "/javax.faces.resource/images/checkedY.png.xhtml?ln=tcm";

   private String beneFirstName;
   private String beneLastName;
   private String saveandOpenError;
   private BenefiaciaryDetails selectedAccount;



   public BenefiaciaryDetails getSelectedAccount()
   {
      return selectedAccount;
   }

   public void setSelectedAccount(BenefiaciaryDetails selectedAccount)
   {
      this.selectedAccount = selectedAccount;
   }


   public TDMasterData getTdMasterData()
   {
      return tdMasterData;
   }

   public void setTdMasterData(TDMasterData tdMasterData)
   {
      this.tdMasterData = tdMasterData;
   }

   @ManagedProperty("#{webutil}")
   private WebUtil webutil;

   public void setWebutil(WebUtil webutil)
   {
      this.webutil = webutil;
   }

   @ManagedProperty("#{uiLayout}")
   private UILayout uiLayout;

   public void setUiLayout(UILayout uiLayout)
   {
      this.uiLayout = uiLayout;
   }

   @ManagedProperty("#{consumerListDataDAO}")
   private ConsumerListDataDAO consumerListDAO;
   public void setConsumerListDAO(ConsumerListDataDAO consumerListDAO)
   {
      this.consumerListDAO = consumerListDAO;
   }

   @ManagedProperty("#{custodyListDAO}")
   private CustodyListDAO custodyListDAO;

   @ManagedProperty("#{serviceLayer}")
   private ServiceLayerImpl serviceLayer;

   public void setListDAO(CustodyListDAO listDAO)
   {
      this.custodyListDAO = listDAO;
   }
   public void setCustodyListDAO(CustodyListDAO custodyListDAO)
   {
      this.custodyListDAO = custodyListDAO;
   }

   @ManagedProperty("#{commonDAO}")
   private CommonDAO commonDAO;
   public void setCommonDAO(CommonDAO commonDAO)
   {
      this.commonDAO = commonDAO;
   }

   @ManagedProperty("#{custodySaveDAO}")
   private CustodySaveDAO custodySaveDAO;
   public void setSaveDAO(CustodySaveDAO saveDAO)
   {
      this.custodySaveDAO = saveDAO;
   }
   public CustodySaveDAO getCustodySaveDAO()
   {
      return custodySaveDAO;
   }

   public void setCustodySaveDAO(CustodySaveDAO custodySaveDAO)
   {
      this.custodySaveDAO = custodySaveDAO;
   }

   public String getBeanacctnum()
   {
      return beanacctnum;
   }

   public String getBeanlogonID()
   {
      return beanlogonID;
   }

   public void setBeanlogonID(String beanlogonID)
   {
      this.beanlogonID = beanlogonID;
   }

   public Long getLongBeanacctnum()
   {
      if (beanacctnum == null)
         return 0L;

      if (beanacctnum.isEmpty())
         return 0L;

      return (webutil.converter.getLongData(beanacctnum));
   }

   public Long getLongBeanlogonid()
   {
      if (beanlogonID == null)
         return 0L;

      if (beanlogonID.isEmpty())
         return 0L;

      return (webutil.converter.getLongData(beanlogonID));
   }

   public String getSaveandOpenError()
   {
      return saveandOpenError;
   }

   public ServiceLayerImpl getServiceLayer()
   {
      return serviceLayer;
   }

   public void setServiceLayer(ServiceLayerImpl serviceLayer)
   {
      this.serviceLayer = serviceLayer;
   }

   public void setBeanacctnum(String beanacctnum)
   {
      this.beanacctnum = beanacctnum;
   }

   public UserData getUserdata()
   {
      return userdata;
   }

   private ArrayList<BenefiaciaryDetails> beneTempList = new ArrayList<BenefiaciaryDetails>();

   public ArrayList<BenefiaciaryDetails> getBeneTempList()
   {
      return beneTempList;
   }

      public void startCTO() {
      String msgheader;
      try
      {
         if (!FacesContext.getCurrentInstance().isPostback())
         {
            if (beanacctnum == null || beanacctnum.isEmpty()) {
               msgheader = "dctd.100";
               webutil.redirecttoMessagePage("ERROR", "Access Denied", msgheader);
               return;
            }
            // clear all data.
            pagemanager = new PagesImpl(10);
            tdMasterData = new TDMasterData(pagemanager, getLongBeanacctnum());
            // load firm list for ACAt details
            custodyListDAO.getAcatFirmList(tdMasterData);
            // Start fresh, clean and start from top page.
            if(!webutil.isUserLoggedIn())
            {
               msgheader = "dctd.102";
               webutil.redirecttoMessagePage("ERROR", "Access Denied", msgheader);
               return;
            }
            String loadVal=loadData();
            //NOACCOUNTNUMBER,  NOACCOUNTMAP,success,ACCOUNTMANAGED,differenUser
            if (loadVal.equalsIgnoreCase("") ||
               loadVal.equalsIgnoreCase("NOACCOUNTNUMBER") ||
               loadVal.equalsIgnoreCase("differenUser") ||
               loadVal.equalsIgnoreCase("NOACCOUNTMAP"))
            {
               msgheader = "dctd.100";
               webutil.redirecttoMessagePage("ERROR", "Access Denied", msgheader);
               return;
            }
            else if(loadVal.equalsIgnoreCase("ACCOUNTMANAGED"))
            {
               msgheader = "dctd.103";
               webutil.redirecttoMessagePage("ERROR", "Access Denied", msgheader);
               return;
            }
            else {
               // Check all data to find out where they left off last time.
               // Just don't display error. Just go to that page.
               Boolean status = validateAllPage();
               Integer currentPage = pagemanager.getPage();
               pagemanager.initPage();
               resetActiveTab(0);
               pagemanager.setLastPageVisited(currentPage);
               pagemanager.clearAllErrorMessage();
               saveandOpenError=null;
            }
         }
      }
      catch (Exception ex) {
         logger.info("Exception: raised during Starting TD CTO process.");
      }
   }

   public void addTempBeneficiary() {

      tdMasterData.getBenefiaciaryDetailses().setAcctnum(tdMasterData.getAcctnum());
         if(beneTempList.isEmpty())
      {
         tdMasterData.getBenefiaciaryDetailses().setBeneId(1);
      }else
      {
         tdMasterData.getBenefiaciaryDetailses().setBeneId(tdMasterData.getBenefiaciaryDetailses().getBeneId()+1);
      }

         beneTempList.add(tdMasterData.getBenefiaciaryDetailses());
         tdMasterData.setBenefiaciaryDetailses(new BenefiaciaryDetails());


   }



   public void editTempBeneficiary(int beneId ) {

      tdMasterData.setBenefiaciaryDetailses(beneTempList.get(beneId));

}
   public String doDelete(BenefiaciaryDetails beneId)
   {
      try
      {
         if (beneId == null)
         {
            return "failed";
         }
         beneTempList.remove(beneId.getBeneId());
      }
      catch (Exception ex)
      {
         return ("failed");
      }

      return ("success");
   }
   public void onEditBenefiaciary(RowEditEvent event) {
      System.out.println("onEditBenefiaciary() ");
      FacesMessage msg = new FacesMessage("Benefiaciary Edited", ((BenefiaciaryDetails) event.getObject()).getBeneFirstName());
      FacesContext.getCurrentInstance().addMessage(null, msg);
   }

   public void onCancelBenefiaciary(RowEditEvent event) {
      System.out.println("onCancelBenefiaciary() ");
      FacesMessage msg = new FacesMessage("Benefiaciary Cancelled");
      FacesContext.getCurrentInstance().addMessage(null, msg);
      beneTempList.remove((BenefiaciaryDetails) event.getObject());
   }

   public String getBeneFirstName()
   {
      return beneFirstName;
   }

   public void setBeneFirstName(String beneFirstName)
   {
      this.beneFirstName = beneFirstName;
   }

   public String getBeneLastName()
   {
      return beneLastName;
   }

   public void setBeneLastName(String beneLastName)
   {
      this.beneLastName = beneLastName;
   }

   public String getErrorMessage(Integer pagenum)
   {
      return pagemanager.getErrorMessage(pagenum);
   }

   public void selectAcctType(Integer type)
   {
      tdMasterData.setAccttype(type);
      tdMasterData.setOptoutBeneficiary(false);
   }

   public void setSubtab(Integer subtab)
   {
      this.subtab = subtab;
   }

   public String getCheckedImage(Integer which)
   {
      which = (which == null) ? 0 : which;
      if (tdMasterData.getAccttype() != null)
      {
         if (tdMasterData.getAccttype() == which)
         {
            return selectedCheckedImage;
         }
      }
      return defaultCheckedImage;

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

  /* public boolean controlTab(TabChangeEvent event)
   {
      if(validatePage(activeTab))
      {
         if (event != null)
         {
            String tabname = event.getTab().getId();
            String tabnum = tabname.substring(3);
            Integer num = webutil.getConverter().getIntData(tabnum);
            subtab = 0;
            switch (num)
            {
               case 0:
                  pagemanager.setPage(0);
                  break;
               case 1:
                  pagemanager.setPage(1);
                  break;
               case 2:
                  pagemanager.setPage(3);
                  break;
               case 3:
                  pagemanager.setPage(5);
                  break;
               case 4:
                  pagemanager.setPage(6);
                  break;
               case 5:
                  pagemanager.setPage(8);
                  break;
               case 6:
                  pagemanager.setPage(9);
                  break;
               default:
                  pagemanager.setPage(0);

            }
         }
         return true;
      }
      else{
         RequestContext requestContext = RequestContext.getCurrentInstance();
         String activeStr = this.activeTab.toString() + "," + event.getTab().getId().substring(3);
      //   requestContext.execute("handleChange(" + activeStr + ")");
         resetActiveTab(activeTab);
         AccordionPanel panel= (AccordionPanel) event.getComponent();
         FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("Tab"+activeTab.toString());
         return false;
      }

   }*/

   public Boolean isAccordianDisabled(Integer pageno) {
     if (pageno != null ) {
        if (pageno > 0 && pageno < pagemanager.getMaxNoofPages()) {
           if (pagemanager.getLastPageVisited() >= pageno) {
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
               Integer num = webutil.getConverter().getIntData(tabnum);
               subtab = 0;
               switch (num)
               {
                  case 0:
                     pagemanager.setPage(0);
                     break;
                  case 1:
                     pagemanager.setPage(1);
                     break;
                  case 2:
                     pagemanager.setPage(3);
                     break;
                  case 3:
                     pagemanager.setPage(5);
                     break;
                  case 4:
                     pagemanager.setPage(6);
                     break;
                  case 5:
                     pagemanager.setPage(8);
                     break;
                  case 6:
                     pagemanager.setPage(9);
                    break;
                  default:
                     pagemanager.setPage(0);

               }
            }

      }

      catch (Exception ex)
      {
         pagemanager.setPage(0);
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
            Integer num = webutil.getConverter().getIntData(tabnum);
            subtab = 0;
            switch (num)
            {
               case 0:

                  break;
               case 1:
                //  pagemanager.nextPage();
                  subtab=1;
                  break;

               default:
                  pagemanager.setPage(0);

            }

            String tabnum1 = tabname.substring(3,4);
            Integer num1 = webutil.getConverter().getIntData(tabnum1);
            switch (num1)
            {
               case 0:
                  pagemanager.setPage(0);
                  break;
               case 1:
                  if(subtab==0)
                     pagemanager.setPage(1);
                  else
                     pagemanager.setPage(2);
                  break;
               case 2:
                  if(subtab==0)
                     pagemanager.setPage(3);
                  else
                     pagemanager.setPage(4);
                  break;
               case 3:
                  pagemanager.setPage(5);
                  break;
               case 4:
                  if(subtab==0)
                     pagemanager.setPage(6);
                  else
                     pagemanager.setPage(7);
                  break;
               case 5:
                  pagemanager.setPage(8);
                  break;
               case 6:
                  if(subtab==0)
                     pagemanager.setPage(9);
                  else
                     pagemanager.setPage(10);
                  break;
               default:
                  pagemanager.setPage(0);

            }

         }
      }
      catch (Exception ex)
      {
         pagemanager.setPage(0);
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
            if (tdMasterData.getIsJointAcct())
            {
               newTab = 1;
               subtab = 1;
            }
            else
            {  // Force to go to next tab.
               pagemanager.nextPage();
               newTab = 2;
            }
            break;
         case 3: // Individual Address
            newTab = 2;
            break;
         case 4: // Joint Address
            // If we are using next page, and the counter is 4, then if it is not joint, then go to tab #4.
            if (tdMasterData.getIsJointAcct())
            {
               newTab = 2;
               subtab = 1;
            }
            else
            {
               pagemanager.nextPage();
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
            if (tdMasterData.getIsJointAcct())
            {
               newTab = 4;
               subtab = 1;
            }
            else
            {
               pagemanager.nextPage();
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
            if (tdMasterData.getFundNow())  // Stay on same tab Fund now button
            {
             newTab = 6;
            }
            else
            {  // Force to go to next tab.
             //  pagemanager.nextPage();
               newTab = 6;
               subtab = 1;
            }


          //  newTab = 6;
         //   if (tdMasterData.getRecurringFlag())
            //   subtab = 1;
//            else
//            {
//               subtab = 0;
//               newTab=null;
//            }
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
      if(tdMasterData.getAccttype()==2 || tdMasterData.getAccttype()==3 )
      {
         pagemanager.prevPage();

      }
      else
      {
         pagemanager.prevPage();
         pagemanager.prevPage();
      }
      pagemanager.clearAllErrorMessage();
      resetActiveTab(pagemanager.getPage());
   }

   private Boolean hasRequiredData(String value) {

      if (value == null || value.isEmpty())
         return false;

      return true;
   }
   private Boolean hasRequiredData(Double value) {

      if (value == null )
         return false;

      return true;
   }
   private Boolean hasRequiredData(Integer value) {
      if (value == null )
         return false;

      return true;
   }
   public void saveBenefiaciaryDetails() {
      ArrayList<BenefiaciaryDetails> benefiaciaryDetailsList=tdMasterData.getBenefiaciaryDetailsList();
     if(validateBenificieryPage(tdMasterData.getTmpBenefiaciaryDetail(),8))
     {
        if (tdMasterData.getBenefiaciaryDetailsList() == null)
        {
           benefiaciaryDetailsList = new ArrayList<BenefiaciaryDetails>();
        }

        if (tdMasterData.getTmpBenefiaciaryDetail() == null)
           return;

        if (tdMasterData.getTmpBenefiaciaryDetail().getBeneId() == null)
        {
           tdMasterData.getTmpBenefiaciaryDetail().setBeneId(benefiaciaryDetailsList.size());
        }
        ArrayList<BenefiaciaryDetails> tmpbenefiaciaryDetailsList = benefiaciaryDetailsList;

        if (tdMasterData.getEditBeneficiaryForm())
        {
           for (int i = 0; i < tmpbenefiaciaryDetailsList.size(); i++)
           {
              BenefiaciaryDetails tmpb = tmpbenefiaciaryDetailsList.get(i);
              if (tdMasterData.getTmpBenefiaciaryDetail().getBeneId().intValue() == tmpb.getBeneId().intValue())
              {
                 benefiaciaryDetailsList.remove(i);

                tdMasterData.setTotalbeneficiaryShares((tdMasterData.getTotalbeneficiaryShares()) - (tmpb.getSharePerc().intValue()));
              }
           }

        }
        tdMasterData.setTotalbeneficiaryShares(((tdMasterData.getTotalbeneficiaryShares()== null) ? 0 : tdMasterData.getTotalbeneficiaryShares()) + tdMasterData.getTmpBenefiaciaryDetail().getSharePerc().intValue());
        tdMasterData.setTmptottalShares(tdMasterData.getTotalbeneficiaryShares());
        benefiaciaryDetailsList.add(tdMasterData.getTmpBenefiaciaryDetail());
        tdMasterData.setBenefiaciaryDetailsList(benefiaciaryDetailsList);
        tdMasterData.setShowBeneficiaryForm(false);
        tdMasterData.setEditBeneficiaryForm(false);
     }

   }

   public  boolean validateBenificieryPage(BenefiaciaryDetails benefiaciaryDetail, Integer pagenum) {

      Boolean dataOK = true;
      pagemanager.clearErrorMessage(pagenum);

      if (! hasRequiredData(benefiaciaryDetail.getBeneFirstName())) {
         dataOK = false;
         pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.firstName.requiredMsg", "First Name is required!", null));
      }
      if(!benefiaciaryDetail.getBeneDOB().equals("") && ! JavaUtil.isValidDate(benefiaciaryDetail.getBeneDOB(),"MM/dd/yyyy"))
      {
         dataOK = false;
         pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.dob.formatMsg", "Enter valid Date of Birth!", null));
      }
//      if (! hasRequiredData(benefiaciaryDetail.getBeneLastName())) {
//         dataOK = false;
//         pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.lastName.requiredMsg", "Last Name is required!", null));
//      }
//      if (! hasRequiredData(benefiaciaryDetail.getBeneDOB())) {
//         dataOK = false;
//         pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.dob.requiredMsg", "Date of Birth is required!", null));
//      }else if(! JavaUtil.isValidDate(benefiaciaryDetail.getBeneDOB(),"MM/dd/yyyy")){
//         dataOK = false;
//         pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.dob.formatMsg", "Enter valid Date of Birth!", null));
//      }
//      if (! hasRequiredData(benefiaciaryDetail.getBeneSSN())) {
//         dataOK = false;
//         pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.ssn.requiredMsg", "Social Security is required!", null));
//      }else if(! JavaUtil.isValidSSN(benefiaciaryDetail.getBeneSSN())){
//         dataOK = false;
//         pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.ssn.formatMsg", "Enter valid Social Security Number!", null));
//      }
      if (! hasRequiredData(benefiaciaryDetail.getBeneRel())) {
         dataOK = false;
         pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.beneRelation.requiredMsg", "Benefiaciary Relationship is required!", null));
      }
      if (! hasRequiredData(benefiaciaryDetail.getTypeOfBeneficiary())) {
         dataOK = false;
         pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.beneType.requiredMsg", "Type of Beneficiary is required!", null));
      }

      if (! hasRequiredData(""+benefiaciaryDetail.getSharePerc())) {
         dataOK = false;
         pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.beneShare.requiredMsg", "Share is required!", null));
      }
      else if (benefiaciaryDetail.getSharePerc()<=0) {
         dataOK = false;
         pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.beneSharegreter.requiredMsg", "Share should be greater than 0!", null));
      }
      else
      {
         double shareHolderPer = 0;
         ArrayList<BenefiaciaryDetails> tmpbenefiaciaryDetailsList = tdMasterData.getBenefiaciaryDetailsList();
         for (int i = 0; i < tmpbenefiaciaryDetailsList.size(); i++)
         {
            BenefiaciaryDetails tmpb = tmpbenefiaciaryDetailsList.get(i);
            shareHolderPer = tmpb.getSharePerc() + shareHolderPer;
         }
         shareHolderPer=tdMasterData.getTmptottalShares()+ tdMasterData.getTmpBenefiaciaryDetail().getSharePerc().intValue();

         if(shareHolderPer>100)
         {
            dataOK = false;
            pagemanager.setErrorMessage("Share holder total percentage should be 100%");
         }
      }

      return dataOK;
   }

   public Boolean validatePage(Integer pagenum) {

      Boolean dataOK = true;
      pagemanager.clearErrorMessage(pagenum);

      if (pagenum == null)
         return false;

      switch (pagenum)
      {
         case 0: // Accttype page

            if (tdMasterData.getAccttype() == null || tdMasterData.getAccttype()==0)
            {
               dataOK = false;
               pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.acctType.requiredMsg", "Account Type is required!", null));
            }
            break;
         case 1: // Account Holder
            if (! hasRequiredData(tdMasterData.getAcctOwnersDetail().getFirstName())) {
               dataOK = false;
               pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.firstName.requiredMsg", "First Name is required!", null));
            }
            if (! hasRequiredData(tdMasterData.getAcctOwnersDetail().getLastName())) {
               dataOK = false;
               pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.lastName.requiredMsg", "Last Name is required!", null));
//               pagemanager.setErrorMessage("Last Name is required!");
               //webutil.getMessageText().getDisplayMessage(msgheader, "This Email is already registered!", null);
            }
            if (! hasRequiredData(tdMasterData.getAcctOwnersDetail().getDob())) {
               dataOK = false;
               pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.dob.requiredMsg", "Date of Birth is required!", null));
            }else if(! JavaUtil.isValidDate(tdMasterData.getAcctOwnersDetail().getDob(),"MM/dd/yyyy")){
               dataOK = false;
               pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.dob.formatMsg", "Enter valid Date of Birth!", null));
            }
            if (! hasRequiredData(tdMasterData.getAcctOwnersDetail().getSsn())) {
               dataOK = false;
               pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.ssn.requiredMsg", "Social Security is required!", null));
            }else if(! JavaUtil.isValidSSN(tdMasterData.getAcctOwnersDetail().getSsn())){
               dataOK = false;
               pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.ssn.formatMsg", "Enter valid Social Security Number!", null));
            }
            if (! hasRequiredData(tdMasterData.getAcctOwnersDetail().getCitizenshiId())) {
               dataOK = false;
               pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.citizenship.requiredMsg", "Must of US Citizen!", null));
            }
            if (! hasRequiredData(tdMasterData.getAcctOwnersDetail().getPhoneNumber())) {
               dataOK = false;
               pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.phoneno.requiredMsg", "Phone Number is required!", null));
            }
            if (! hasRequiredData(tdMasterData.getAcctOwnersDetail().getEmailAddress())) {
               dataOK = false;
               pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.email.requiredMsg", "Email Address is required!", null));
            }
            else
            {
                  if(validateEmailPattern(tdMasterData.getAcctOwnersDetail().getEmailAddress()))
                  {
                     dataOK = false;
                     pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.validemail.requiredMsg", "Enter valid  Email address!", null));
                  }
            }
            break;
         case 2: // Joint Holder
            if (! hasRequiredData(tdMasterData.getJointAcctOwnersDetail().getFirstName())) {
               dataOK = false;
               pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.firstName.requiredMsg", "First Name is required!", null));
            }
            if (! hasRequiredData(tdMasterData.getJointAcctOwnersDetail().getLastName())) {
               dataOK = false;
               pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.lastName.requiredMsg", "Last Name is required!", null));
            }
            if (! hasRequiredData(tdMasterData.getJointAcctOwnersDetail().getDob())) {
               dataOK = false;
               pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.dob.requiredMsg", "Date of Birth is required!", null));
            }else if(! JavaUtil.isValidDate(tdMasterData.getJointAcctOwnersDetail().getDob(),"MM/dd/yyyy")){
               dataOK = false;
               pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.dob.formatMsg", "Enter valid Date of Birth!", null));
            }
            if (! hasRequiredData(tdMasterData.getJointAcctOwnersDetail().getSsn())) {
               dataOK = false;
               pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.ssn.requiredMsg", "Social Security Number is required!", null));
            }else if(! JavaUtil.isValidSSN(tdMasterData.getJointAcctOwnersDetail().getSsn())){
               dataOK = false;
               pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.ssn.formatMsg", "Enter valid Social Security Number!", null));
            }
            if (! hasRequiredData(tdMasterData.getJointAcctOwnersDetail().getCitizenshiId())) {
               dataOK = false;
               pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.citizenship.requiredMsg", "Must be US Citizen!", null));
            }
            if (! hasRequiredData(tdMasterData.getJointAcctOwnersDetail().getPhoneNumber())) {
               dataOK = false;
               pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.phoneno.requiredMsg", "Phone Number is required!", null));
            }
            if (! hasRequiredData(tdMasterData.getJointAcctOwnersDetail().getEmailAddress())) {
               dataOK = false;
               pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.email.requiredMsg", "Email Address is required!", null));
            }
            else
            {
               if(validateEmailPattern(tdMasterData.getJointAcctOwnersDetail().getEmailAddress()))
               {
                  dataOK = false;
                  pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.validemail.requiredMsg", "Enter valid  Email address!", null));
               }
            }
            break;
         case 3: // Address
               if (!hasRequiredData(tdMasterData.getAcctOwnersDetail().getPhysicalAddressStreet()))
               {
                  dataOK = false;
                  pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.resstreet.requiredMsg", "Residence Street Address is required!", null));
               }
               if (!hasRequiredData(tdMasterData.getAcctOwnersDetail().getPhysicalAddressCity()))
               {
                  dataOK = false;
                  pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.city.requiredMsg", "City is required!", null));
               }
               if (!hasRequiredData(tdMasterData.getAcctOwnersDetail().getPhysicalAddressState()))
               {
                  dataOK = false;
                  pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.state.requiredMsg", "State is required!", null));
               }
               if (!hasRequiredData(tdMasterData.getAcctOwnersDetail().getPhysicalAddressZipCode()))
               {
                  dataOK = false;
                  pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.zip.requiredMsg", "Zip Code is required!", null));
               }
               else if (!JavaUtil.isValidZipCode(tdMasterData.getAcctOwnersDetail().getPhysicalAddressZipCode()))
               {
                  dataOK = false;
                  pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.zip.formatMsg", "Enter valid Zip Code!", null));
               }
               if(tdMasterData.getAcctholderhasMailing())
               {
                  if (!hasRequiredData(tdMasterData.getAcctOwnersDetail().getMailingAddressStreet()))
                  {
                     dataOK = false;
                     pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.mailstreet.requiredMsg", "Mailing Street Address is required!", null));
                  }
                  if (!hasRequiredData(tdMasterData.getAcctOwnersDetail().getMailingAddressCity()))
                  {
                     dataOK = false;
                     pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.city.requiredMsg", "City is required!", null));
                  }
                  if (!hasRequiredData(tdMasterData.getAcctOwnersDetail().getMailingAddressState()))
                  {
                     dataOK = false;
                     pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.state.requiredMsg", "State is required!", null));
                  }
                  if (!hasRequiredData(tdMasterData.getAcctOwnersDetail().getMailingAddressZipCode()))
                  {
                     dataOK = false;
                     pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.zip.requiredMsg", "Zip Code is required!", null));
                  }
                  else if (!JavaUtil.isValidZipCode(tdMasterData.getAcctOwnersDetail().getMailingAddressZipCode()))
                  {
                     dataOK = false;
                     pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.zip.formatMsg", "Enter valid Zip Code!", null));
                  }
               }
            break;
         case 4: // Joint Address
            if(tdMasterData.getAccttype()==2)
            {
               if (tdMasterData.getJointhasDifferent())
               {
                  if (!hasRequiredData(tdMasterData.getJointAcctOwnersDetail().getPhysicalAddressStreet()))
                  {
                     dataOK = false;
                     pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.resstreet.requiredMsg", "Residence Street Address is required!", null));
                  }
                  if (!hasRequiredData(tdMasterData.getJointAcctOwnersDetail().getPhysicalAddressCity()))
                  {
                     dataOK = false;
                     pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.city.requiredMsg", "City is required!", null));
                  }
                  if (!hasRequiredData(tdMasterData.getJointAcctOwnersDetail().getPhysicalAddressState()))
                  {
                     dataOK = false;
                     pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.state.requiredMsg", "State is required!", null));
                  }
                  if (!hasRequiredData(tdMasterData.getJointAcctOwnersDetail().getPhysicalAddressZipCode()))
                  {
                     dataOK = false;
                     pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.zip.requiredMsg", "Zip Code is required!", null));
                  }
                  else if (!JavaUtil.isValidZipCode(tdMasterData.getJointAcctOwnersDetail().getPhysicalAddressZipCode()))
                  {
                     dataOK = false;
                     pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.zip.formatMsg", "Enter valid Zip Code!", null));
                  }

                  if(tdMasterData.getJointhasMailing())
                  {
                     if (!hasRequiredData(tdMasterData.getJointAcctOwnersDetail().getMailingAddressStreet()))
                     {
                        dataOK = false;
                        pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.mailstreet.requiredMsg", "Mailing Street Address is required!", null));
                     }
                     if (!hasRequiredData(tdMasterData.getJointAcctOwnersDetail().getMailingAddressCity()))
                     {
                        dataOK = false;
                        pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.city.requiredMsg", "City is required!", null));
                     }
                     if (!hasRequiredData(tdMasterData.getJointAcctOwnersDetail().getMailingAddressState()))
                     {
                        dataOK = false;
                        pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.state.requiredMsg", "State is required!", null));
                     }
                     if (!hasRequiredData(tdMasterData.getJointAcctOwnersDetail().getMailingAddressZipCode()))
                     {
                        dataOK = false;
                        pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.zip.requiredMsg", "Zip Code is required!", null));
                     }
                     else if (!JavaUtil.isValidZipCode(tdMasterData.getJointAcctOwnersDetail().getMailingAddressZipCode()))
                     {
                        dataOK = false;
                        pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.zip.formatMsg", "Enter valid Zip Code!", null));
                     }
                  }
               }
            }
            break;
         case 5: // Regulatory
            if(tdMasterData.getSenoirPolitical())
            {
               if (! hasRequiredData(tdMasterData.getAcctOwnersDetail().getSpfName())) {
                  dataOK = false;
                  pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.spfname.requiredMsg", "Name of SPF is required!", null));
               }
               if (! hasRequiredData(tdMasterData.getAcctOwnersDetail().getSpfRelationship())) {
                  dataOK = false;
                  pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.spfrelation.requiredMsg", "Relationship of SPF is required!", null));
               }
               if (! hasRequiredData(tdMasterData.getAcctOwnersDetail().getSpfTitle())) {
                  dataOK = false;
                  pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.spftitle.requiredMsg", "Political title is required!", null));
               }
               if (! hasRequiredData(tdMasterData.getAcctOwnersDetail().getSpfCountry())) {
                  dataOK = false;
                  pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.spfcountry.requiredMsg", "Country of office is required!", null));
               }
            }
            if(tdMasterData.getOwnerShare())
            {
               if (! hasRequiredData(tdMasterData.getAcctOwnersDetail().getShareholderCompany())) {
                  dataOK = false;
                  pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.sharecompany.requiredMsg", "Company Name is required!", null));
               }
               if (! hasRequiredData(tdMasterData.getAcctOwnersDetail().getShareholderAddress())) {
                  dataOK = false;
                  pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.shareaddress.requiredMsg", "Company Adddress is required!", null));
               }
               if (! hasRequiredData(tdMasterData.getAcctOwnersDetail().getShareholderCity())) {
                  dataOK = false;
                  pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.sharecity.requiredMsg", "Company city is required!", null));
               }
               if (! hasRequiredData(tdMasterData.getAcctOwnersDetail().getShareholderState())) {
                  dataOK = false;
                  pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.sharestate.requiredMsg", "Company state is required!", null));
               }
            }
            if(tdMasterData.getOwnerBD())
            {
               if (! hasRequiredData(tdMasterData.getAcctOwnersDetail().getBdDetail())) {
                  dataOK = false;
                  pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.bdentity.requiredMsg", "Entity is required!", null));
               }
            }

            break;
         case 6: // Employment
                  if (! hasRequiredData(tdMasterData.getOwneremploymentDetail().getEmplTypeId())) {
                     dataOK = false;
                     pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.empstatus.requiredMsg", "Employment status is required!", null));
                  }
                  else if (tdMasterData.getOwneremploymentDetail().getEmplTypeId().startsWith("EMPL") ||
                              tdMasterData.getOwneremploymentDetail().getEmplTypeId().startsWith("SLFEMPL"))
                  {
                     if (! hasRequiredData(tdMasterData.getOwneremploymentDetail().getEmployerName())) {
                        dataOK = false;
                        pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.empname.requiredMsg", "Employer name is required!", null));
                     }
                     if (! hasRequiredData(tdMasterData.getOwneremploymentDetail().getOccupation())) {
                        dataOK = false;
                        pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.occupation.requiredMsg", "Occupation is required!", null));
                     }
                  }
                  else
                  {
                     if (! hasRequiredData(tdMasterData.getOwneremploymentDetail().getSourceOfIncome())) {
                        dataOK = false;
                        pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.sourceincome.requiredMsg", "Source of income is required!", null));
                     }
                  }

            break;
         case 7: // Joint Employment
            if(tdMasterData.getAccttype()==2)
            {
               if (!hasRequiredData(tdMasterData.getJointEmploymentDetail().getEmplTypeId()))
               {
                  dataOK = false;
                  pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.empstatus.requiredMsg", "Employment status is required!", null));
               }
               else if (tdMasterData.getJointEmploymentDetail().getEmplTypeId().startsWith("EMPL") ||
                  tdMasterData.getJointEmploymentDetail().getEmplTypeId().startsWith("SLFEMPL"))
               {
                  if (!hasRequiredData(tdMasterData.getJointEmploymentDetail().getEmployerName()))
                  {
                     dataOK = false;
                     pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.empname.requiredMsg", "Employer name is required!", null));
                  }
                  if (!hasRequiredData(tdMasterData.getJointEmploymentDetail().getOccupation()))
                  {
                     dataOK = false;
                     pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.occupation.requiredMsg", "Occupation is required!", null));
                  }
               }
               else
               {
                  if (!hasRequiredData(tdMasterData.getJointEmploymentDetail().getSourceOfIncome()))
                  {
                     dataOK = false;
                     pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.sourceincome.requiredMsg", "Source of income is required!", null));
                  }
               }
            }

            break;
         case 8: // Benefitiary
            if(tdMasterData.getAcctdetail().getAcctTypeId().equalsIgnoreCase("IRAROTH") ||
               tdMasterData.getAcctdetail().getAcctTypeId().equalsIgnoreCase("IRAROOV") ||
               tdMasterData.getAcctdetail().getAcctTypeId().equalsIgnoreCase("IRATRAD") ||
               tdMasterData.getAcctdetail().getAcctTypeId().equalsIgnoreCase("IRABENE"))
               {
                  if (tdMasterData.getBenefiaciaryDetailsList().size() == 0)
                  {
                     dataOK = false;
                     pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.benedetails.requiredMsg", "Beneficiary details is required!", null));
                  }
               }

               if (tdMasterData.getBenefiaciaryDetailsList().size() > 0)
               {
                     double shareHolderPer = 0;
                     ArrayList<BenefiaciaryDetails> tmpbenefiaciaryDetailsList = tdMasterData.getBenefiaciaryDetailsList();
                     for (int i = 0; i < tmpbenefiaciaryDetailsList.size(); i++)
                     {
                        BenefiaciaryDetails tmpb = tmpbenefiaciaryDetailsList.get(i);
                        shareHolderPer = tmpb.getSharePerc() + shareHolderPer;
                     }
                     if (shareHolderPer == 100)
                        dataOK = true;
                     else
                     {
                        dataOK = false;
                        pagemanager.setErrorMessage("Share holder percentage should be 100%");
                     }
               }
            break;
         case 9: // Funding Page 1
               if(tdMasterData.getFundNow()==false) // flag if opt of for funcindg is false
               {
                  if (! hasRequiredData(tdMasterData.getFundType())) {
                     dataOK = false;
                     pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.fundtype.requiredMsg", "Fund type is required!", null));
                  }

                 else if(tdMasterData.getFundType()!=null && tdMasterData.getFundType().equalsIgnoreCase("PMACH"))
                  {
                     if (! hasRequiredData(tdMasterData.getInitialInvestment())) {
                        dataOK = false;
                        pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.investamt.requiredMsg", "Investment amount is required!", null));
                     }
                     else if (tdMasterData.getInitialInvestment()<50000) {
                        dataOK = false;
                        pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.investamtless.requiredMsg", "Investment amount should be greater than $50000!", null));
                     }

                     if (!hasRequiredData(tdMasterData.getAchBankDetail().getBankAcctType()))
                     {
                        dataOK = false;
                        pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.bankaccounttype.requiredMsg", "Account type is required!", null));
                     }
                     if (!hasRequiredData(tdMasterData.getAchBankDetail().getBankName()))
                     {
                        dataOK = false;
                        pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.bankname.requiredMsg", "Bank Name is required!", null));
                     }
                     if (!hasRequiredData(tdMasterData.getAchBankDetail().getBankAcctName()))
                     {
                        dataOK = false;
                        pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.bankaccountname.requiredMsg", "Name on bank account is required!", null));
                     }
                     if (!hasRequiredData(tdMasterData.getAchBankDetail().getBankCityState()))
                     {
                        dataOK = false;
                        pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.bankcityState.requiredMsg", "Bank City/State is required!", null));
                     }
                     if (!hasRequiredData(tdMasterData.getAchBankDetail().getBankPhoneNumber()))
                     {
                        dataOK = false;
                        pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.bankphoneno.requiredMsg", "Bank Phone number is required!", null));
                     }
                     if (!hasRequiredData(tdMasterData.getAchBankDetail().getBankABARouting()))
                     {
                        dataOK = false;
                        pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.abaroutingno.requiredMsg", "ABA Routing Number is required!", null));
                     }
                     if (!hasRequiredData(tdMasterData.getAchBankDetail().getBankAcctNumber()))
                     {
                        dataOK = false;
                        pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.bankaccountno.requiredMsg", "Bank Account Number is required!", null));
                     }
                  }
                  else if(tdMasterData.getFundType()!=null && tdMasterData.getFundType().equalsIgnoreCase("PMFEDW"))
                  {
                     if (!hasRequiredData(tdMasterData.getAcatDetails().getFromAccountTitle()))
                     {
                        dataOK = false;
                        pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.bankaccounttitle.requiredMsg", "Account Title is required!", null));
                     }
                     if (!hasRequiredData(tdMasterData.getAcatDetails().getAccountNumber2()))
                     {
                        dataOK = false;
                        pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.bankaccountno.requiredMsg", "Account Number is required!", null));
                     }
                     if (!hasRequiredData(tdMasterData.getAcatDetails().getContraFirmList()))
                     {
                        dataOK = false;
                        pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.deliveryfirm.requiredMsg", "Name of delivery firm is required!", null));
                     }
                     /*if (tdMasterData.getAcatDetails().getContraFirmList().equalsIgnoreCase("OTH") && !hasRequiredData(tdMasterData.getAcatDetails().getOtherContraFirmList()))
                     {
                        dataOK = false;
                        pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.deliveryfirmother.requiredMsg", "Name of other delivery firm is required!", null));
                     }*/

                     if (!hasRequiredData(tdMasterData.getAcatDetails().getFromOtherAccountType()))
                     {
                        dataOK = false;
                        pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.accttype.requiredMsg", "Account Type is required!", null));
                     }
                     if (!hasRequiredData(tdMasterData.getAcatDetails().getTransferTypeId()))
                     {
                        dataOK = false;
                        pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.fullpartial.requiredMsg", "Full/Partial transfer is required!", null));
                     }
                  }

                  else if(tdMasterData.getFundType()!=null && tdMasterData.getFundType().equalsIgnoreCase("TDTRF"))
                  {
                     if(tdMasterData.getTdTransferDetails().getRetilFlag()==null || tdMasterData.getTdTransferDetails().getRetilFlag().equals(""))
                     {
                        dataOK = false;
                        pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.brokerfirmnamefalg.requiredMsg", "Is this a Retail or Advisor managed account is required!", null));
                     }
                     else if (tdMasterData.getTdTransferDetails().getRetilFlag().equals("Y"))
                     {
                        if (!hasRequiredData(tdMasterData.getTdTransferDetails().getPriorFirmName()))
                        {
                           dataOK = false;
                           pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.brokerfirmname.requiredMsg", "Existing Advisory Firm Name is required!", null));
                        }
                        if (!hasRequiredData(tdMasterData.getTdTransferDetails().getFirmAccountNo()))
                        {
                           dataOK = false;
                           pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.firmaccountno.requiredMsg", "Firm Account Number is required!", null));
                        }
                     }
                     else  if (tdMasterData.getTdTransferDetails().getRetilFlag().equals("N"))
                     {
                        if (!hasRequiredData(tdMasterData.getTdTransferDetails().getRetailAccountNumber()))
                        {
                           dataOK = false;
                           pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.brokerAccno.requiredMsg", "TD Ameritrade Retail Brokerage Account Number is required!", null));
                        }
                        if (!hasRequiredData(tdMasterData.getTdTransferDetails().getAdvisorID()))
                        {
                           dataOK = false;
                           pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.advisorId.requiredMsg", "Advisor ID is required!", null));
                        }
                     }

                  }

               }
            break;
         case 10: // Funding Recurring Page 2
               if(tdMasterData.getFundNow()==false) // flag if opt of for funding is false
               {
                  if(tdMasterData.getRecurringFlag()==false) //
                  {
                     if (!hasRequiredData(tdMasterData.getElectroicBankDetail().getTranFreqId()))
                     {
                        dataOK = false;
                        pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.transfrequency.requiredMsg", "Frequency is required!", null));
                     }
                    if (!hasRequiredData(tdMasterData.getElectroicBankDetail().getTranAmount()))
                     {
                        dataOK = false;
                        pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.transamt.requiredMsg", "Amount is required!", null));
                     }
                     else if (tdMasterData.getElectroicBankDetail().getTranAmount()<50)
                    {
                       dataOK = false;
                       pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.transamtless.requiredMsg", "Transaction Amount should be greater than $50.", null));
                    }

                     if (!hasRequiredData(tdMasterData.getElectroicBankDetail().getTranStartDate()))
                     {
                        dataOK = false;
                        pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.transstartdate.requiredMsg", "Start date is requied!", null));
                     }
                     else if(!tdMasterData.getElectroicBankDetail().getTranStartDate().equals("") && ! JavaUtil.isValidDate(tdMasterData.getElectroicBankDetail().getTranStartDate(),"MM/dd/yyyy"))
                     {
                        dataOK = false;
                        pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.validateStartDate.requiredMsg", "Enter valid Start Date (mm/dd/yyyy)!", null));
                     }
                     else if(!tdMasterData.getElectroicBankDetail().getTranStartDate().equals("") && ! JavaUtil.checkdate(tdMasterData.getElectroicBankDetail().getTranStartDate()))
                     {
                        dataOK = false;
                        pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.validateStartDate.formatMsg", "Start date should be greater than today date!", null));
                     }

                     if(tdMasterData.getFundType().equalsIgnoreCase("PMACH") && tdMasterData.getOwnerSPF())
                     {

                     }
                     else
                     {
                        if (!hasRequiredData(tdMasterData.getElectroicBankDetail().getBankAcctType()))
                        {
                           dataOK = false;
                           pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.accounttype.requiredMsg", "* Bank Account Type is required!", null));
                        }
                        if (!hasRequiredData(tdMasterData.getElectroicBankDetail().getBankName()))
                        {
                           dataOK = false;
                           pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.bankname.requiredMsg", "Bank Name is required!", null));
                        }
                        if (!hasRequiredData(tdMasterData.getElectroicBankDetail().getBankAcctName()))
                        {
                           dataOK = false;
                           pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.banktitle.requiredMsg", "Bank Account title is required!", null));
                        }
                        if (!hasRequiredData(tdMasterData.getElectroicBankDetail().getBankCityState()))
                        {
                           dataOK = false;
                           pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.bankcityState.requiredMsg", "Bank City/State is required!", null));
                        }
                        if (!hasRequiredData(tdMasterData.getElectroicBankDetail().getBankPhoneNumber()))
                        {
                           dataOK = false;
                           pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.bankphoneno.requiredMsg", "Bank Phone number is required!", null));
                        }
                        if (!hasRequiredData(tdMasterData.getElectroicBankDetail().getBankABARouting()))
                        {
                           dataOK = false;
                           pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.abaroutingno.requiredMsg", "ABA Routing Number is required!", null));
                        }
                        if (!hasRequiredData(tdMasterData.getElectroicBankDetail().getBankAcctNumber()))
                        {
                           dataOK = false;
                           pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.bankaccountno.requiredMsg", "Bank Account Number is required!", null));
                        }
                     }

                  }

               }

            break;

      }
      return dataOK;
   }

   // Before going to next page, determine if the data in current tab passes validity,
   // then save the data beefore going to the next page
   // NOTE: Let resetActiveTab deal with joint account or not to go to appropriate tab.
   public void nextPage()
   {
      if (validatePage(pagemanager.getPage()))
      {
         saveData(pagemanager.getPage());
         if(pagemanager.isLastPage())
            tdMasterData.setSubmitButton(true);
         else
            tdMasterData.setSubmitButton(false);

         pagemanager.clearAllErrorMessage();
            if(pagemanager.isLastPage() || (pagemanager.getPage()==9 && tdMasterData.getFundNow()) )
               resetActiveTab(11);
            else
            {
               pagemanager.nextPage();
               resetActiveTab(pagemanager.getPage());
            }
         saveandOpenError = null;
      }
   }

   public Boolean validateAllPage()
   {
      Integer currentPage = pagemanager.getPage();
      Boolean status;
      saveandOpenError = null;

      pagemanager.setPage(0);
      status = true;
      while (pagemanager.getPage() <= pagemanager.getMaxNoofPages())
      {
         if (validatePage(pagemanager.getPage()))
         {
            //saveData(pagemanager.getPage());
            saveData(pagemanager.getPage());
            if (pagemanager.isLastPage()) {
               break;
            }
            pagemanager.nextPage();
            pageControl(pagemanager.getPage());
         }
         else {
            currentPage=pagemanager.getPage();
            status = false;
            break;
         }

      }
      pagemanager.setPage(currentPage);
      resetActiveTab(pagemanager.getPage());
      if (!status)
         saveandOpenError="Please fill appropriate forms above.";

      return status;
   }


   private void resetActiveTab(Integer pagenum)
   {
      Integer nextTab = pageControl(pagenum);
      activeTab = nextTab;
/*
      if (nextTab != null)
      {
         activeTab = nextTab;
      }
*/
   }


   private AdvisorDetails loadAdvisorData()
   {
      return null;
   }

      private String loadData()
   {
      String retValue="";
      if (beanacctnum == null)
      {
         retValue = "NOACCOUNTNUMBER";
         return retValue;
      }


/*
      acctnum = Long.valueOf(beanacctnum);
      this.acctnum =  acctnum;
      if (tdMasterData != null) {
         tdMasterData.setAcctnum(acctnum);
         tdMasterData.getAcctdetail().setAcctnum(acctnum);
         tdMasterData.getAcctdetail().setCashSweepVehicleChoiceId("TDSIPC");
         tdMasterData.getAcctOwnersDetail().setAcctnum(acctnum);
         tdMasterData.getJointAcctOwnersDetail().setAcctnum(acctnum);
         tdMasterData.getOwneremploymentDetail().setAcctnum(acctnum);
         tdMasterData.getJointEmploymentDetail().setAcctnum(acctnum);
      }
*/

      tdMasterData.getCustomerData().setAcctnum(getLongBeanacctnum());
      tdMasterData.getCustomerData().setLogonid(getLongBeanlogonid());
      consumerListDAO.getProfileData(tdMasterData.getCustomerData());

      if (tdMasterData.getCustomerData().getUserid() == null)
      {
         retValue = "NOACCOUNTMAP";
         return retValue;
      }
      if (getLongBeanlogonid() != webutil.getLogonid())
      {
         retValue = "differenUser";
         return retValue;
      }

     if ((tdMasterData.getCustomerData().getManaged() == null) || ( ! tdMasterData.getCustomerData().getManaged())) {
         custodyListDAO.getTDAccountDetails(tdMasterData);
         custodyListDAO.getTDAccountHolder(tdMasterData);
         custodyListDAO.getTDEmployment(tdMasterData);
        if(tdMasterData.getOptoutBeneficiary())
        {
           custodySaveDAO.deleteBenefiaciaryDetails(tdMasterData);
        }
         custodyListDAO.getTDBeneficiary(tdMasterData);
        if(!tdMasterData.getOptoutFunding())
        {
           custodyListDAO.getfundingData(tdMasterData);
           if(tdMasterData.getOptoutRecurring())
           {
              tdMasterData.setRecurringFlag(true);
           }
        }
        else
        {
           tdMasterData.setFundNow(true);
           tdMasterData.setRecurringFlag(true);
        }


         // Fix issues related to bad data.
         if (tdMasterData.getAcctOwnersDetail().getOwnership() == null ||
            tdMasterData.getAcctOwnersDetail().getOwnership().isEmpty()) {
            tdMasterData.getAcctOwnersDetail().setOwnership("AOPRIMARY");
         }

         if (tdMasterData.getJointAcctOwnersDetail().getOwnership() == null ||
            tdMasterData.getJointAcctOwnersDetail().getOwnership().isEmpty()) {
            tdMasterData.getJointAcctOwnersDetail().setOwnership("AOJOINT");
         }
        retValue="success";
        return retValue;
      }
      else {
        retValue="ACCOUNTMANAGED";
        return retValue;
      }
   }

   private AcctOwnersDetails setOwnerData(String dataFlag,TDMasterData tdMasterData)
   {
      TDMasterData mstData=tdMasterData;
      AcctOwnersDetails acctOwnersDetail=tdMasterData.getAcctOwnersDetail();
      AcctOwnersDetails jointAcctOwnersDetail=tdMasterData.getJointAcctOwnersDetail();
      if(dataFlag.equals("add"))
      {
         jointAcctOwnersDetail.setPhysicalAddressStreet(acctOwnersDetail.getPhysicalAddressStreet());
         jointAcctOwnersDetail.setPhysicalAddressCity(acctOwnersDetail.getPhysicalAddressCity());
         jointAcctOwnersDetail.setPhysicalAddressState(acctOwnersDetail.getPhysicalAddressState());
         jointAcctOwnersDetail.setPhysicalAddressZipCode(acctOwnersDetail.getPhysicalAddressZipCode());
         jointAcctOwnersDetail.setMailingAddressStreet(acctOwnersDetail.getMailingAddressStreet());
         jointAcctOwnersDetail.setMailingAddressCity(acctOwnersDetail.getMailingAddressCity());
         jointAcctOwnersDetail.setMailingAddressState(acctOwnersDetail.getMailingAddressState());
         jointAcctOwnersDetail.setMailingAddressZipCode(acctOwnersDetail.getMailingAddressZipCode());

      }

      return jointAcctOwnersDetail;
   }
   private AcctOwnersDetails setRegulatoryData(TDMasterData tdMasterData)
   {
      TDMasterData mstData=tdMasterData;
      AcctOwnersDetails acctOwnersDetail=tdMasterData.getAcctOwnersDetail();
      if(mstData.getSenoirPolitical())
      {
         acctOwnersDetail.setSPF("Y");
         acctOwnersDetail.setSpfDetail(acctOwnersDetail.getSpfName() + "," + acctOwnersDetail.getSpfRelationship() + "," + acctOwnersDetail.getSpfTitle() + "," + acctOwnersDetail.getSpfCountry());
      }
      if(mstData.getOwnerShare())
      {
         acctOwnersDetail.setDirectorShareholder("Y");
         acctOwnersDetail.setDirectorShareholderDetail(acctOwnersDetail.getShareholderCompany() + "," + acctOwnersDetail.getShareholderAddress() + "," + acctOwnersDetail.getShareholderCity() + "," + acctOwnersDetail.getShareholderState());
      }
      if(mstData.getOwnerBD())
      {
         acctOwnersDetail.setBd("Y");
      }
      return acctOwnersDetail;
   }
   private void saveData(Integer pagenum)
   {

      if ( pagenum == null )
         return;

      if(tdMasterData.getAccttype()==3)
      {
         tdMasterData.getAcctOwnersDetail().setOwnership("AOCUSTODIAN");
         tdMasterData.getJointAcctOwnersDetail().setOwnership("AOMINOR");
      }
      else
      {
         tdMasterData.getAcctOwnersDetail().setOwnership("AOPRIMARY");
         tdMasterData.getJointAcctOwnersDetail().setOwnership("AOJOINT");
      }
      switch (pagenum) {
         case 0: // Account Type and create basic info
            // custodySaveDAO.tdSaveRequest(tdMasterData.getRequest());   We are going to add the request on final save.
            custodySaveDAO.tdSaveAccountDetail(tdMasterData.getAcctdetail(),tdMasterData);
            break;
         case 1: // Account Owner

            custodySaveDAO.tdSaveAccountOwner(tdMasterData.getAcctOwnersDetail());
            break;
         case 2:  // Joint Owner  (if Any)
            custodySaveDAO.tdSaveAccountOwner(tdMasterData.getJointAcctOwnersDetail());
            break;
         case 3:  // Owner Address
            custodySaveDAO.tdSaveAccountOwner(tdMasterData.getAcctOwnersDetail());
            break;
         case 4:  // Joint Owner  Address (if Any)
            if(tdMasterData.getJointhasDifferent())
               custodySaveDAO.tdSaveAccountOwner(tdMasterData.getJointAcctOwnersDetail());
            else
                custodySaveDAO.tdSaveAccountOwner(setOwnerData("add",tdMasterData));
            break;
         case 5: // Regulatory
            custodySaveDAO.tdSaveAccountOwner(setRegulatoryData(tdMasterData));
            custodySaveDAO.tdSaveAccountDetail(tdMasterData.getAcctdetail(),tdMasterData);
            break;
         case 6:  // Owner Emplyment
            custodySaveDAO.tdSaveEmployment(tdMasterData.getOwneremploymentDetail());
            break;
         case 7: // Joint Emplyment  (If any)
            custodySaveDAO.tdSaveEmployment(tdMasterData.getJointEmploymentDetail());
            break;
         case 8:
            if(tdMasterData.getOptoutBeneficiary())
            {
               custodySaveDAO.deleteBenefiaciaryDetails(tdMasterData);
            }
            custodySaveDAO.saveBenefiaciaryDetails(tdMasterData.getBenefiaciaryDetailsList());
            custodySaveDAO.tdSaveAccountDetail(tdMasterData.getAcctdetail(),tdMasterData);
            break;
         case 9: // funding

           // custodySaveDAO.tdsaveACHData(tdMasterData);
            if(tdMasterData.getOptoutFunding())
            {
               custodySaveDAO.tdSaveAccountDetail(tdMasterData.getAcctdetail(),tdMasterData);
            }
            else
            {
               custodySaveDAO.tdSaveAccountDetail(tdMasterData.getAcctdetail(),tdMasterData);
               if (tdMasterData.getFundType() != null && tdMasterData.getFundType().equalsIgnoreCase("PMACH"))// for ACH acocunt
               {
                  custodySaveDAO.tdsaveACHData(tdMasterData, "ACH");
                  if (tdMasterData.getOwnerSPF() && tdMasterData.getFundType().equalsIgnoreCase("PMACH"))
                  {
                     tdMasterData.getElectroicBankDetail().setBankAcctType(tdMasterData.getAchBankDetail().getBankAcctType());
                     tdMasterData.getElectroicBankDetail().setBankName(tdMasterData.getAchBankDetail().getBankName());
                     tdMasterData.getElectroicBankDetail().setBankAcctName(tdMasterData.getAchBankDetail().getBankAcctName());
                     tdMasterData.getElectroicBankDetail().setBankCityState(tdMasterData.getAchBankDetail().getBankCityState());
                     tdMasterData.getElectroicBankDetail().setBankPhoneNumber(tdMasterData.getAchBankDetail().getBankPhoneNumber());
                     tdMasterData.getElectroicBankDetail().setBankABARouting(tdMasterData.getAchBankDetail().getBankABARouting());
                     tdMasterData.getElectroicBankDetail().setBankAcctNumber(tdMasterData.getAchBankDetail().getBankAcctNumber());

                  }
               }
               else if (tdMasterData.getFundType() != null && tdMasterData.getFundType().equalsIgnoreCase("PMFEDW"))
                  custodySaveDAO.tdSaveACAT(tdMasterData, tdMasterData.getAcctnum(), tdMasterData.getAcatDetails());

               else if (tdMasterData.getFundType() != null && tdMasterData.getFundType().equalsIgnoreCase("TDTRF"))
                  custodySaveDAO.tdSaveTDTransferData(tdMasterData, tdMasterData.getAcctnum(), tdMasterData.getTdTransferDetails());
            }
              // custodySaveDAO.tdSaveACH("ACH",tdMasterData.getOwnerSPF(),tdMasterData.getAcctnum(),tdMasterData.getInitialInvestment(),tdMasterData.getFundType(),tdMasterData.getAchBankDetail());
            break;
         case 10:
            if(tdMasterData.getOptoutRecurring())
            {
               custodySaveDAO.tdSaveAccountDetail(tdMasterData.getAcctdetail(),tdMasterData);
            }else
            {
               if (!tdMasterData.getRecurringFlag())
               {
                  custodySaveDAO.tdSaveAccountDetail(tdMasterData.getAcctdetail(),tdMasterData);
                  custodySaveDAO.tdSaveElectronicPaymentData(tdMasterData);
               }
            }
               //custodySaveDAO.tdSaveElectronicPayment("REC",tdMasterData.getOwnerSPF(),tdMasterData.getAcctnum(),tdMasterData.getInitialInvestment(),tdMasterData.getFundType(),tdMasterData.getElectroicBankDetail());
            break;
         default:
            break;
      }

   }

   public void openAccount()
   {
      String msg, msgheader;

      try {
         if (! validateAllPage() )
            return;
/*
         if (!serviceLayer.isServiceActive())
         {
            msgheader = "dctd.101";
            webutil.redirecttoMessagePage("ERROR", "Service not Active", msgheader);
            return;
         }
*/

         WSCallStatus wsstatus;
         WSCallResult wsCallResult;

         custodySaveDAO.tdCheckRequest(tdMasterData);  // check for fund and recuring tab is filled on save and open buttton

         if(!tdMasterData.getFundType().equalsIgnoreCase("TDTRF"))
         {
            Request data = new Request();
            data.setReqId(new Long(0));
            data.setEventNum(0);
            data.setAcctnum(tdMasterData.getAcctnum());
            if (tdMasterData.getAcctdetail().getAcctTypeId().equalsIgnoreCase("ACINDIV") ||
               tdMasterData.getAcctdetail().getAcctTypeId().equalsIgnoreCase("ACJOINT") ||
               tdMasterData.getAcctdetail().getAcctTypeId().equalsIgnoreCase("ACCSTD"))
               data.setReqType("ACCT_APPLI_NEW");
            else if (tdMasterData.getAcctdetail().getAcctTypeId().equalsIgnoreCase("IRABENE"))
               data.setReqType("IRA_QRP_BENE_NEW");
            else
               data.setReqType("IRA_APPLI_NEW");

            data.setEnvelopeHeading("Please sign account opening document.");
            custodySaveDAO.tdOpenAccount(data);
            tdMasterData.getRequest().setEventNum(data.getEventNum());
         }
         System.out.println("Aoount Number:-"+tdMasterData.getAcctnum()+" ---Event Number="+tdMasterData.getRequest().getEventNum());
         wsCallResult = serviceLayer.processDCRequest(tdMasterData.getAcctnum(),tdMasterData.getRequest().getEventNum());
         if (wsCallResult.getWSCallStatus().getErrorCode() != 0)
         {
            msg = wsCallResult.getWSCallStatus().getErrorMessage();
            webutil.redirecttoMessagePage("ERROR", "Failed to Save", msg);
         }
         else {
            custodySaveDAO.tdMangedUserProfile(tdMasterData.getAcctnum());
            uiLayout.doMenuAction("custody","tdconfirmation.xhtml");
         }
      }
      catch (Exception ex) {
         msgheader = "dctd.EX.101";
         webutil.redirecttoMessagePage("ERROR", "Service Error", msgheader);
         return;

      }
   }
   public Boolean validateEmailPattern(String emailID)
   {

      final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\." +
         "[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*" +
         "(\\.[A-Za-z]{2,})$";

      Pattern pattern;
      Matcher matcher;
      pattern = Pattern.compile(EMAIL_PATTERN);
      Boolean msg = false;
      if ((emailID == null) || (emailID.trim().equals("")) ||
         (emailID.indexOf('.') == -1) ||
         (emailID.indexOf('@') == -1))
      {
         msg = true;
      }

      matcher = pattern.matcher(emailID);
      if (!matcher.matches())
      {

         msg = true;
      }
      return msg;
   }
}
