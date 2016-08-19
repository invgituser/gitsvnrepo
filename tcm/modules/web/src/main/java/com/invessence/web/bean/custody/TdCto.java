package com.invessence.web.bean.custody;

import javax.faces.bean.*;

import com.invessence.web.dao.common.*;
import com.invessence.web.dao.consumer.*;
import com.invessence.web.data.common.UserData;
import com.invessence.web.data.custody.*;
import com.invessence.web.util.*;

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

   public void startFundAccount(Long acctnum) {
      // If we called here with no acctnum, it must be a mistake.  This is special signup process for TD account opening only
      if (acctnum == null || acctnum <= 0L) {
         webutil.redirecttoMessagePage("Error","Attempting to Signup","This function is not allowed, till a proper portfolio is created.");
         return;
      }
      this.acctnum = acctnum;
      beanacctnum = acctnum.toString();

      // First determine if this account is registered.
      // If not, then create a new account with signup.
      // Then, allow the user to create Customer profile.
      // If they are signed-up then check if this account is already entered.
      // If so, then edit, else add.
      uiLayout.doMenuAction("custody","index.xhtml?acct=" + acctnum.toString());
   }


   // This interface will show the signup window, and then allow the user.
   // Function addUserLogon will determine the validity of data and returns the valid LogonID.
   // After the LogonID is created, then call function to connect the logonID with acctnum.
   public void creteSimpleSignup() {
      if (userdata == null) {
         webutil.redirecttoMessagePage("Error","System Error","Unable to process your request at this time.  Please contact support");
         webutil.alertSupport("TdCto.createSimpleSignup()","CreateSimpleSignup error","UserData Object is null.  Check System.",null);
         return;
      }
      userdata.setAcctnum(acctnum);
      Long logonID = userdata.addUserLogon();
      if (logonID != null && logonID >= 0L) {
         tdMasterData.setAcctnum(acctnum);
         AdvisorDetails advisorDetails = loadAdvisorData();
         if (advisorDetails != null)
         {
            tdMasterData.setAdvisorDetails(advisorDetails);
         }
         uiLayout.doMenuAction("custody","cto.xhtml?acct=" + acctnum.toString());
      }
      else {
         webutil.redirecttoMessagePage("Error","System Error","Unable to create your credentials at this time.  Please try again later.");
         webutil.alertSupport("TdCto.createSimpleSignup()","CreateSimpleSignup error","LogonID was not created, even though the signup process was initiated.  Check SQL log.",null);
         return;
      }


   }

   private AdvisorDetails loadAdvisorData() {
      return null;
   }

   private void loadData(String acctnum) {

   }


}
