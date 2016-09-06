package com.invessence.web.bean.custody;

import java.util.*;
import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;

import com.invessence.web.bean.consumer.CTOBean;
import com.invessence.web.dao.common.*;
import com.invessence.web.dao.consumer.*;
import com.invessence.web.dao.custody.*;
import com.invessence.web.data.common.UserData;
import com.invessence.web.data.custody.*;
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
   private UserData userdata;
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

   @ManagedProperty("#{ctobean}")
   private CTOBean ctoBean;

   public CTOBean getCtoBean()
   {
      return ctoBean;
   }

   public void setCtoBean(CTOBean ctoBean)
   {
      this.ctoBean = ctoBean;
   }

   public String getBeanacctnum()
   {
      return beanacctnum;
   }

   public void setBeanacctnum(String beanacctnum)
   {
      this.beanacctnum = beanacctnum;
      this.acctnum = Long.valueOf(beanacctnum);

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

      tdMasterData.getBenefiaciaryDetailses().setAcctnum(ctoBean.getBeanAcctnum());

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


   // This interface will show the signup window, and then allow the user.
   // Function addUserLogon will determine the validity of data and returns the valid LogonID.
   // After the LogonID is created, then call function to connect the logonID with acctnum.
   public void creteSimpleSignup()
   {
      if (userdata == null)
      {
         webutil.redirecttoMessagePage("Error", "System Error", "Unable to process your request at this time.  Please contact support");
         webutil.alertSupport("TdCto.createSimpleSignup()", "CreateSimpleSignup error", "UserData Object is null.  Check System.", null);
         return;
      }
      userdata.setAcctnum(acctnum);
      userdata.setUserID(userdata.getEmail()); // Since we are collecting email, make sure to set the userid same.
      Long logonID = userdata.addUserLogon();
      if (logonID != null && logonID >= 0L)
      {
         tdMasterData.setAcctnum(acctnum);
         AdvisorDetails advisorDetails = loadAdvisorData();
         if (advisorDetails != null)
         {
            tdMasterData.setAdvisorDetails(advisorDetails);
         }
         uiLayout.doMenuAction("custody", "cto.xhtml?acct=" + acctnum.toString());
      }
      else
      {
         webutil.redirecttoMessagePage("Error", "System Error", "Unable to create your credentials at this time.  Please try again later.");
         webutil.alertSupport("TdCto.createSimpleSignup()", "CreateSimpleSignup error", "LogonID was not created, even though the signup process was initiated.  Check SQL log.", null);
         return;
      }


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
         saveData(pagemanager.getPage());
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
         case 0:
            custodySaveDAO.td_saveRequest(tdMasterData.getRequest());
            custodySaveDAO.td_saveAccountDetail(tdMasterData.getAcctdetail());
            break;
         case 1:
            custodySaveDAO.td_saveAccountOwner(tdMasterData.getAcctOwnersDetail());
            break;
         case 2:
            custodySaveDAO.td_saveAccountOwner(tdMasterData.getJointAcctOwnersDetail());
            break;
         case 3:
            break;
         case 4:
            break;
         case 5:
            break;
         case 6:
            break;
         case 7:
            break;
         case 8:
            custodySaveDAO.td_saveBenefiaciaryDetails(beneTempList);
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
