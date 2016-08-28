package com.invessence.web.bean.custody;

import javax.faces.bean.*;

import com.invessence.web.dao.common.*;
import com.invessence.web.dao.consumer.*;
import com.invessence.web.data.common.UserData;
import com.invessence.web.data.custody.*;
import com.invessence.web.util.*;
import com.invessence.web.util.Impl.PagesImpl;
import org.primefaces.event.TabChangeEvent;

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
   private TDMasterData tdMasterData;
   private PagesImpl pagemanager = new PagesImpl(11);
   private String activeTab, newTab, subtab;


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
   private ConsumerListDataDAO listDAO;

   public void setListDAO(ConsumerListDataDAO listDAO)
   {
      this.listDAO = listDAO;
   }

   @ManagedProperty("#{commonDAO}")
   private CommonDAO commonDAO;

   public void setCommonDAO(CommonDAO commonDAO)
   {
      this.commonDAO = commonDAO;
   }

   @ManagedProperty("#{consumerSaveDataDAO}")
   private ConsumerSaveDataDAO saveDAO;

   public void setSaveDAO(ConsumerSaveDataDAO saveDAO)
   {
      this.saveDAO = saveDAO;
   }

   public TdCto()
   {
      userdata = new UserData();
      tdMasterData = new TDMasterData();
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

   public TDMasterData getTdMasterData()
   {
      return tdMasterData;
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

   public String getActiveTab()
   {
      return activeTab;
   }

   public String getSubtab()
   {
      return subtab;
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
            pagemanager.setPage(num);
            // setActiveTab(num); // Since the user is moving via tab control, we don't need to make active tab.
         }
      }
      catch (Exception ex)
      {
         pagemanager.setPage(0);
         //setActiveTab(0);

      }
   }

   private String pageControl(Integer pagenum)
   {
      if (pagenum == null)
      {
         return "1";
      }

      subtab = "0";
      switch (pagenum) // Note: The switch is based on pagenum
      {
         case 1: // accttype Page
            newTab = "1";
            break;
         case 2: // Individual Account Holder
            newTab = "2";
            break;
         case 3: // Joint Account Holder;
               newTab = "3";
               subtab = "0";
            break;
         case 4: // Individual Address
            // If we are using next page, and the counter is 4, then if it is not joint, then go to tab #4.
            if (tdMasterData.getIsJointAcct()) {
               newTab = "3";
               subtab = "1";
            }
            else {
               pagemanager.nextPage();
               newTab = "4";
               subtab = "1";
            }
            break;
         case 5: // Joint Account Holder;
            newTab = "4";
            subtab = "0";
            break;
         case 6: // Joint Address
            // If we are using next page, and the counter is 4, then if it is not joint, then go to tab #5.
            if (tdMasterData.getIsJointAcct()) {
               newTab = "5";
               subtab = "0";
            }
            else {
               pagemanager.nextPage();
               newTab = "4";
               subtab = "1";
            }
            break;
         case 7: // Regulatory
            newTab = "4";
            break;
         case 8: // Employment
            newTab = "5";
            subtab = "0";
            break;
         case 9: // Joint Employment
            if (!tdMasterData.getIsJointAcct())
            { // Skip the page, not joint account
               pagemanager.nextPage();
               newTab = "6";
               subtab = "0";
            }
            else
            {
               newTab = "5";
               subtab = "1";
            }
            break;
         case 10: // Beneficiary
            newTab = "6";
            break;
         case 11: // Funding
            newTab = "7";
            subtab = "0";
            break;
         case 12: // Funding
            newTab = "7";
            subtab = "1";
            break;
         default:
            newTab = "0";
            subtab = "0";
            break;
      }

      return newTab;
   }

   public void prevPage()
   {
      // customErrorText = null;   // If we go to previous page, then erase the error message.
      pagemanager.prevPage();
      setActiveTab(pagemanager.getPage());
   }


   public void nextPage()
   {
      Boolean cangoToNext = true;

      switch (pagemanager.getPage())
      {
         case 0:
            if (tdMasterData.getAccttype() == null)
            {
               cangoToNext = false;
            }
            break;
         case 1:
            break;
         case 2:
            break;
         case 3:
            break;
         case 4:

      }
      if (cangoToNext)
      {
         pagemanager.nextPage();
         setActiveTab(pagemanager.getPage());

      }
   }

   private void setActiveTab(Integer pagenum)
   {
      String nextTab = pageControl(pagenum);
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


}
