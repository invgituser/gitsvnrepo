package com.invessence.web.bean.consumer.uob;

import java.io.Serializable;
import java.util.*;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;

import com.invessence.web.constant.*;
import com.invessence.web.dao.consumer.*;
import com.invessence.web.data.common.CustomerData;
import com.invessence.web.data.consumer.uob.UOBRiskCalculator;
import com.invessence.web.util.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 1/3/15
 * Time: 6:52 PM
 * To change this template use File | Settings | File Templates.
 */

@ManagedBean(name = "uobdash")
@SessionScoped
public class ConsumerDashBean extends CustomerData implements Serializable
{
   private static final long serialVersionUID = 1001L;

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

   private ArrayList<CustomerData> manageAccountList;
   private ArrayList<CustomerData> selAccountList;

   private CustomerData selectedAccount;
   private long selAcctNum,accoutListZize;
//   private UOBRiskCalculator riskCalculator;
//   private String whichChart;
//   public String riskCalcMethod = "C";
//   private Integer prefView;


   public void preRenderView()
   {

      Long logonid;
      String fetchStatus;
      try
      {
         if (!FacesContext.getCurrentInstance().isPostback())
         {
            if (webutil.validatePriviledge(WebConst.ROLE_USER)) {
               logonid = webutil.getLogonid();

               if (logonid != null)
                  collectData(logonid);
            }
         }
         if(selAccountList!=null && selAccountList.size()>0){
            accoutListZize=selAccountList.size();
         }else{
            accoutListZize=0;
         }
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
   }

   public String getLoggedUserName() {
      return webutil.getLastFirstName();
   }

   public void collectData(Long logonid)
   {
      manageAccountList = listDAO.getClientProfileList(logonid,null, null, webutil.getUserInfoData().getAdvisor(), webutil.getUserInfoData().getRep());
   }


   public List<CustomerData> getManageAccountList()
   {
      return manageAccountList;
   }

   public CustomerData getSelectedAccount()
   {
      return selectedAccount;
   }

   public void setSelectedAccount(CustomerData selectedAccount)
   {
      this.selectedAccount = selectedAccount;
   }

   public String showPosition()
   {
      String whichXML;
      try
      {
         uiLayout.doMenuAction("consumer", "overview.xhtml?acct=" + selectedAccount.getAcctnum().toString());
      }
      catch (Exception ex)
      {
         return ("failed");
      }

      return ("success");
   }



   public String doEditAction()
   {
      String whichXML;
      try
      {
         uiLayout.doMenuAction("custody", "cedit.xhtml?app=E&acct=" + selectedAccount.getAcctnum().toString());
      }
      catch (Exception ex)
      {
         return ("failed");
      }

      return ("success");
   }

   public String doPortfolioSelectedAction()
   {
      String whichXML;
      try
      {
         if (getSelectedAccount().isUnopened())
         {
            uiLayout.doMenuAction("consumer", "cadd.xhtml?app=E&acct=" + selectedAccount.getAcctnum().toString());
         }
         else
         {
            uiLayout.doMenuAction("consumer", "portfolioedit.xhtml?app=E&acct=" + selectedAccount.getAcctnum().toString());
         }
      }
      catch (Exception ex)
      {
         return ("failed");
      }

      return ("success");
   }

   public String doFundingAction()
   {
      String whichXML;
      try
      {
         uiLayout.doMenuAction("custody", "editfunding.xhtml?app=E&acct=" + selectedAccount.getAcctnum().toString());
      }
      catch (Exception ex)
      {
         return ("failed");
      }

      return ("success");
   }


   public String doOptionMenuAction()
   {
      String whichXML;
      try
      {

         uiLayout.doMenuAction("custody", "editaddress.xhtml?app=E&acct=" + selectedAccount.getAcctnum().toString());
      }
      catch (Exception ex)
      {
         return ("failed");
      }

      return ("success");
   }


   public void showSelAcctDtls()
   {

      System.out.println("selectedAccount.getAcctnum().toString()");
      try
      {
         selAccountList = listDAO.getClientProfileList(webutil.getLogonid(),selAcctNum, null, webutil.getUserInfoData().getAdvisor(), webutil.getUserInfoData().getRep());
//         CustomerData ob=selAccountList.get(0);
//         ob.buildAssetClass();

//         UOBRiskCalculator riskCalculator =new UOBRiskCalculator();

//         riskCalculator.setNumberofQuestions(9);
//         whichChart = "pie";
//         setRiskCalcMethod(WebConst.CONSUMER_RISK_FORMULA);
//         super.loadProfileData(selAcctNum, riskCalculator);
//         Double riskIndex = riskCalculator.calculateRisk();
//         createDynaAssetPortfolio(1, riskIndex,selAccountList.get(0).getTheme());
         accoutListZize=selAccountList.size();
         if(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(WebConst.SEL_ACCOUNT)!=null){

            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove(WebConst.SEL_ACCOUNT);
         }
         FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(WebConst.SEL_ACCOUNT,selAcctNum);
//         setDisplayFTPanel(false);
//         setEnableChangeStrategy(true);
//         setAltrOnChngStrategy(true);
//         setDoesUserHavaLogonID(false);  //  This is default, but fetchCustomer will set reset it.
//         flagforInvestShow = false;
//
//         masterpagemanager = new PagesImpl(3);
//         masterpagemanager.setPage(0);
//         pagemanager = new PagesImpl(9);
//         if (newapp != null && newapp.startsWith("N"))
//         {
//            beanAcctnum = null;
//         }
//         else
//         {
//            if (!webutil.isUserLoggedIn())
//            {
//               webutil.redirect("/login.xhtml", null);
//               return;
//            }
//         }
//         riskCalculator.setNumberofQuestions(9);
//         whichChart = "pie";
//
//         setPrefView(0);
//         setRiskCalcMethod(WebConst.CONSUMER_RISK_FORMULA);
//
//         disablegraphtabs = true;
//         disabledetailtabs = true;
//         fetchClientData();
//
//         canOpenAccount = initCanOpenAccount();
      }
      catch (Exception ex)
      {
         ex.printStackTrace();
      }

   }

   public ArrayList<CustomerData> getSelAccountList()
   {
      return selAccountList;
   }

   public void setSelAccountList(ArrayList<CustomerData> selAccountList)
   {
      this.selAccountList = selAccountList;
   }

   public long getSelAcctNum()
   {
      return selAcctNum;
   }

   public void setSelAcctNum(long selAcctNum)
   {
      this.selAcctNum = selAcctNum;
   }

   public long getAccoutListZize()
   {
      return accoutListZize;
   }

   public void setAccoutListZize(long accoutListZize)
   {
      this.accoutListZize = accoutListZize;
   }
//
//   public String getWhichChart()
//   {
//      return whichChart;
//   }
//
//   public void setWhichChart(String whichChart)
//   {
//      this.whichChart = whichChart;
//   }

}
