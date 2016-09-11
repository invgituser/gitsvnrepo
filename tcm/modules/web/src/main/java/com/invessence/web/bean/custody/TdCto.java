package com.invessence.web.bean.custody;

import java.util.*;
import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;

import com.invessence.web.dao.common.*;
import com.invessence.web.dao.custody.*;
import com.invessence.web.data.common.UserData;
import com.invessence.web.data.custody.*;
import com.invessence.web.data.custody.td.*;
import com.invessence.web.util.*;
import com.invessence.web.util.Impl.PagesImpl;
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
   private String beanacctnum;
   private Long acctnum;
   private UserData userdata = new UserData();
   private TDMasterData tdMasterData = new TDMasterData();
   private PagesImpl pagemanager = new PagesImpl(10);
   private Integer activeTab = 0;   // Start with first tab.
   private Integer newTab, subtab;
   private String defaultCheckedImage = "/javax.faces.resource/images/checkedN.png.xhtml?ln=tcm";
   private String selectedCheckedImage = "/javax.faces.resource/images/checkedY.png.xhtml?ln=tcm";
   BenefiaciaryDetails benefiaciaryDetails;

   private String beneFirstName;
   private String beneLastName;
   private Double sharePerc;
   private int intNum;

   public int getIntNum()
   {
      return intNum;
   }

   public void setIntNum(int intNum)
   {
      this.intNum = intNum;
   }

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

   @ManagedProperty("#{custodyListDAO}")
   private CustodyListDAO custodyListDAO;
   public void setListDAO(CustodyListDAO listDAO)
   {
      this.custodyListDAO = listDAO;
   }

   public CustodyListDAO getCustodyListDAO()
   {
      return custodyListDAO;
   }

   public void setCustodyListDAO(CustodyListDAO custodyListDAO)
   {
      this.custodyListDAO = custodyListDAO;
   }

   public CommonDAO getCommonDAO()
   {
      return commonDAO;
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

   public void setBeanacctnum(String beanacctnum)
   {
      this.beanacctnum = beanacctnum;
      Long acctnum;

      if (beanacctnum != null) {
         acctnum = Long.valueOf(beanacctnum);
         this.acctnum =  acctnum;
         if (tdMasterData != null) {
            tdMasterData.setAcctnum(acctnum);
            tdMasterData.getAcctdetail().setAcctnum(acctnum);
            tdMasterData.getAcctOwnersDetail().setAcctnum(acctnum);
            tdMasterData.getJointAcctOwnersDetail().setAcctnum(acctnum);
            tdMasterData.getOwneremploymentDetail().setAcctnum(acctnum);
            tdMasterData.getJointEmploymentDetail().setAcctnum(acctnum);
         }

      }
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

   public Double getSharePerc()
   {
      return sharePerc;
   }

   public void setSharePerc(Double sharePerc)
   {
      this.sharePerc = sharePerc;
   }

   public void startFundAccount(Long acctnum)
   {
      // If we called here with no acctnum, it must be a mistake.  This is special signup process for TD account opening only
      if (acctnum == null || acctnum <= 0L)
      {
         webutil.redirecttoMessagePage("Error", "Attempting to Signup", "This function is not allowed, till a proper portfolio is created.");
         return;
      }
      this.acctnum = acctnum;
      beanacctnum = acctnum.toString();

      // First determine if this account is registered.
      // If not, then create a new account with signup.
      // Then, allow the user to create Customer profile.
      // If they are signed-up then check if this account is already entered.
      // If so, then edit, else add.
      uiLayout.doMenuAction("custody", "index.xhtml?acct=" + acctnum.toString());
   }

   public void selectAcctType(Integer type)
   {
      tdMasterData.setAccttype(type);
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
      subtab = 0;
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
            newTab = 6;
            subtab = 1;
            break;
         default:
            newTab = 0;
            break;
      }

      return newTab;
   }

   public void prevPage()
   {
      // customErrorText = null;   // If we go to previous page, then erase the error message.
      pagemanager.prevPage();
      resetActiveTab(pagemanager.getPage());
   }


   // Before going to next page, determine if the data in current tab passes validity,
   // then save the data before going to the next page
   // NOTE: Let resetActiveTab deal with joint account or not to go to appropriate tab.
   public void nextPage()
   {
      Boolean cangoToNext = true;

      switch (pagemanager.getPage())
      {
         case 0: // Accttype page
            if (tdMasterData.getAccttype() == null)
            {
               cangoToNext = false;
            }
            break;
         case 1: // Account Holder
            break;
         case 2: // Joint Holder
            break;
         case 3: // Address
            break;
         case 4: // Joint Address
            break;
         case 5: // Regulatory
            break;
         case 6: // Employment
            break;
         case 7: // Joint Employment
            break;
         case 8: // Benefitiary
            break;
         case 9: // Funding Page 1
            break;
         case 10: // Funding Recurring Page 2
            break;

      }
      if (cangoToNext)
      {
         //saveData(pagemanager.getPage());
         pagemanager.nextPage();
         resetActiveTab(pagemanager.getPage());


      }
   }

   private void resetActiveTab(Integer pagenum)
   {
      Integer nextTab = pageControl(pagenum);
      if (nextTab != null)
      {
         activeTab = nextTab;
      }
   }


   private AdvisorDetails loadAdvisorData()
   {
      return null;
   }

   private void loadData(String acctnum)
   {

   }

   private void saveData(Integer pagenum)
   {

      if ( pagenum == null )
         return;

      switch (pagenum) {
         case 0: // Account Type and create basic info
            custodySaveDAO.tdSaveRequest(tdMasterData.getRequest());
            custodySaveDAO.tdSaveAccountDetail(tdMasterData.getAcctdetail());
            break;
         case 1: // Account Owner
            custodySaveDAO.tdSaveAccountOwner(tdMasterData.getAcctOwnersDetail());
            break;
         case 2:  // Joint Owner  (if Any)
            custodySaveDAO.tdSaveAccountOwner(tdMasterData.getJointAcctOwnersDetail());
            break;
         case 3:  // Owner Address
            custodySaveDAO.tdSaveEmployment(tdMasterData.getOwneremploymentDetail());
            break;
         case 4:  // Joint Owner  Address (if Any)
            custodySaveDAO.tdSaveAccountOwner(tdMasterData.getJointAcctOwnersDetail());
            break;
         case 5:  // Owner Emplyment
            custodySaveDAO.tdSaveEmployment(tdMasterData.getOwneremploymentDetail());
            break;
         case 6: // Joint Emplyment  (If any)
            custodySaveDAO.tdSaveEmployment(tdMasterData.getJointEmploymentDetail());
            break;
         case 7: // Regulatory
            custodySaveDAO.tdSaveAccountOwner(tdMasterData.getJointAcctOwnersDetail());
            break;
         case 8:
            custodySaveDAO.saveBenefiaciaryDetails(tdMasterData.getBenefiaciaryDetailses());
            break;
         case 9:
            break;
         case 10:
            break;
         default:
            break;
      }

   }

}
