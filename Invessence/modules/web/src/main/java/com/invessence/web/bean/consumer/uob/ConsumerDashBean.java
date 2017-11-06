package com.invessence.web.bean.consumer.uob;

import java.io.Serializable;
import java.text.*;
import java.util.*;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;

import com.invessence.web.constant.*;
import com.invessence.web.controller.HighChartsController;
import com.invessence.web.dao.common.*;
import com.invessence.web.dao.consumer.*;
import com.invessence.web.data.common.*;
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

   @ManagedProperty("#{positionDAO}")
   private PositionDAO posDao;
   @ManagedProperty("#{commonDAO}")
   private CommonDAO commonDAO;

   private ArrayList<CustomerData> manageAccountList;
   private ArrayList<CustomerData> selAccountList;

   private CustomerData selectedAccount;
   private long selAcctNum,accoutListZize;
   private boolean dsplStrategyCNfPnl;
   private String goalcstmnm;
   private ArrayList<NotificationData> highNotification;
   private String acctOpnDtLbl;
   private boolean dispNoRcrd=true;
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

         highNotification = commonDAO.getNotificationDtls(webutil.getLogonid(), "M", "N",webutil.getAccess(),true,selAcctNum);
         System.out.println("highNotification "+highNotification.size());
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
      if(manageAccountList!=null && manageAccountList.size()>0){
         dispNoRcrd=false;
         if(selAcctNum==0)
         {
            selAcctNum = manageAccountList.get(0).getAcctnum();
         }
         showSelAcctDtls();
      }else{
         dispNoRcrd=true;
      }
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

   public String showPositionNew()
   {
      String whichXML;
      try
      {
         uiLayout.doMenuAction("consumer", "overview.xhtml?acct=" + selAcctNum);
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
   public String doPortfolioSelectedActionNew()
   {
      String whichXML;
      try
      {
         if (selAccountList.get(0).isUnopened())
         {
//            dsplStrategyPnl=false;
            if(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ProfileCnf")!=null){

               FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("ProfileCnf");
            }
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("ProfileCnf",false);
            uiLayout.doMenuAction("consumer", "cadd.xhtml?app=E&acct=" + selAcctNum);
         }
         else
         {
//            dsplStrategyPnl=true;
            if(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ProfileCnf")!=null){

               FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("ProfileCnf");
            }
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("ProfileCnf",true);
            uiLayout.doMenuAction("consumer", "portfolioedit.xhtml?app=E&acct=" + selAcctNum);
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
   public String doFundingActionNew()
   {
      String whichXML;
      try
      {
         uiLayout.doMenuAction("custody", "funding.xhtml?app=E&acct=" +selAcctNum);
      }
      catch (Exception ex)
      {
         return ("failed");
      }

      return ("success");
   }

   public String doPrflCnfActionNew()
   {
      String whichXML;
      try
      {
         uiLayout.doMenuAction("consumer", "portfolioedit.xhtml?app=E&acct=" +selAcctNum);
         if(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ProfileCnf1")!=null){

            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("ProfileCnf1");
         }
         FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("ProfileCnf1",true);
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


         List<Position> l1=posDao.loadDBPosition(webutil, selAcctNum,selAccountList.get(0).getManaged());


         if(selAccountList.get(0).getCustomName().isEmpty())
         {
            setGoalcstmnm("Goal-"+selAccountList.get(0).getGoal());
         }else{
//            strGoalCstmName =selAccountList.get(0).getCustomName();
            setGoalcstmnm(""+selAccountList.get(0).getCustomName());
         }
//         rollupAssetClassByPosList(l1,Double.parseDouble(""+selAccountList.get(0).getInitialInvestment()));
         if(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("AccountBal")!=null){

            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("AccountBal");
         }
         Double amt=null;
         if(selAccountList.get(0).getManaged()){
             amt=selAccountList.get(0).getActualInvestment();
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("AccountBal",amt);
//            actualInvestment
         }else{
//            initialInvestment
            Integer amt1=selAccountList.get(0).getInitialInvestment();
             amt=Double.parseDouble(""+amt1);
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("AccountBal",amt);
         }

         accoutListZize=selAccountList.size();
         if(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(WebConst.SEL_ACCOUNT)!=null){

            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove(WebConst.SEL_ACCOUNT);
         }
         FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(WebConst.SEL_ACCOUNT,selAcctNum);

         DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
         Date date = new Date();
         date = df.parse(selAccountList.get(0).getDisplayDateOpened());
//         System.out.println(date);

         SimpleDateFormat dt1 = new SimpleDateFormat("d MMM yyyy");
         acctOpnDtLbl=dt1.format(date);
         Map<String, String> configMap = webutil.getWebprofile().getWebInfo();
         HighChartsController highChartsController = new HighChartsController();
         rollupAssetClassByPosList(l1,amt);
         setResultChart(highChartsController.highChartrequesthandler(null,getAssetData(),configMap));
         setTypeOfChart(webutil.getWebprofile().getInfo("CHART.ASSET.ALLOCATION"));
         System.out.println("Helloooooo ~@~["+getResultChart()+"]~@~");
         System.out.println("Helloooooo1 ~@~["+getTypeOfChart()+"]~@~");


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

   public void updateGoalDescription(){
      try{
//         System.out.println("Hellooo "+strGoalCstmName);
//         System.out.println("bye "+getStrGoalCstmName());
         System.out.println("Parma ");
         System.out.println("strGoalCstmName "+goalcstmnm);
         CustomerData objCustData=selAccountList.get(0);
         objCustData.setCustomName(goalcstmnm);
         updateProfileData(objCustData);
      }catch (Exception e){

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

   public PositionDAO getPosDao()
   {
      return posDao;
   }

   public void setPosDao(PositionDAO posDao)
   {
      this.posDao = posDao;
   }

//   public boolean isDsplStrategyPnl()
//   {
//      return dsplStrategyPnl;
//   }
//
//   public void setDsplStrategyPnl(boolean dsplStrategyPnl)
//   {updateGoalDescription
//      this.dsplStrategyPnl = dsplStrategyPnl;
//   }

   public void onChngCsmVal()
   {
      System.out.println("Del "+getGoalcstmnm());
      System.out.println("strGoalCstmName "+goalcstmnm);
   }

   public String getGoalcstmnm()
   {
      return goalcstmnm;
   }

   public void setGoalcstmnm(String goalcstmnm)
   {
      this.goalcstmnm = goalcstmnm;
   }

   public ArrayList<NotificationData> getHighNotification()
   {
      return highNotification;
   }

   public void setHighNotification(ArrayList<NotificationData> highNotification)
   {
      this.highNotification = highNotification;
   }

   public CommonDAO getCommonDAO()
   {
      return commonDAO;
   }

   public void setCommonDAO(CommonDAO commonDAO)
   {
      this.commonDAO = commonDAO;
   }

   public String getAcctOpnDtLbl()
   {
      return acctOpnDtLbl;
   }

   public void setAcctOpnDtLbl(String acctOpnDtLbl)
   {
      this.acctOpnDtLbl = acctOpnDtLbl;
   }

   public boolean isDispNoRcrd()
   {
      return dispNoRcrd;
   }

   public void setDispNoRcrd(boolean dispNoRcrd)
   {
      this.dispNoRcrd = dispNoRcrd;
   }
}
