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
import com.invmodel.asset.data.AssetClass;
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
   private String acctOpnDtLbl,dsplDbdInvstAmt,dsplDfltCrncy;
   private boolean dispNoRcrd=true,dispHghAlrt=false,dsplPrflDtl=false;
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
               dsplDfltCrncy=webutil.getWebprofile().getInfo("DEFAULT.CURRENCY");
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
         if(highNotification!=null && highNotification.size()>0){
            dispHghAlrt=true;
         }else{
            dispHghAlrt=false;
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

         uiLayout.doMenuAction("custody", "editfunding.xhtml?app=E&acct=" + selectedAccount.getAcctnum().toString());
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
         if (selAccountList.get(0).getCustomName().isEmpty())
         {
            setGoalcstmnm("Goal-" + selAccountList.get(0).getGoal());
         }
         else
         {
            setGoalcstmnm("" + selAccountList.get(0).getCustomName());
         }

         Double amt = null;
         if (selAccountList.get(0).getManaged())
         {
            amt = selAccountList.get(0).getActualInvestment()*selAccountList.get(0).getExchangeRate();
//            actualInvestment
         }
         else
         {
//            initialInvestment
            Double amt1 = selAccountList.get(0).getInitialInvestment()*selAccountList.get(0).getExchangeRate();
            amt = amt1;
         }

         accoutListZize = selAccountList.size();

         DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
         Date date = new Date();
         date = df.parse(selAccountList.get(0).getDisplayDateOpened());
         SimpleDateFormat dt1 = new SimpleDateFormat("d MMM yyyy");
         acctOpnDtLbl = dt1.format(date);

         setResultChart("");
         setTypeOfChart("");
         setAssetData(new AssetClass[0]);
         dsplPrflDtl=false;
         DecimalFormat df2 = new DecimalFormat("##,###,##0.00");
         dsplDbdInvstAmt= df2.format(amt);
         System.out.println("converted amt "+dsplDbdInvstAmt);
         List<Position> l1=null;
         l1 = posDao.loadDBPosition(webutil, selAcctNum, selAccountList.get(0).getManaged());
         if(l1!=null && l1.size()>0)
         {
            Map<String, String> configMap = webutil.getWebprofile().getWebInfo();
            HighChartsController highChartsController = new HighChartsController();
            rollupAssetClassByPosList(l1,amt);
            setResultChart(highChartsController.highChartrequesthandler(null, getAssetData(), configMap));
            setTypeOfChart(webutil.getWebprofile().getInfo("CHART.ASSET.ALLOCATION"));

            System.out.println("DBD strDonutString~~>"+getResultChart()+"<~~");
            dsplPrflDtl=true;
         }
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

   public boolean isDispHghAlrt()
   {
      return dispHghAlrt;
   }

   public void setDispHghAlrt(boolean dispHghAlrt)
   {
      this.dispHghAlrt = dispHghAlrt;
   }

   public boolean isDsplPrflDtl()
   {
      return dsplPrflDtl;
   }

   public void setDsplPrflDtl(boolean dsplPrflDtl)
   {
      this.dsplPrflDtl = dsplPrflDtl;
   }

   public String getDsplDbdInvstAmt()
   {
      return dsplDbdInvstAmt;
   }

   public void setDsplDbdInvstAmt(String dsplDbdInvstAmt)
   {
      this.dsplDbdInvstAmt = dsplDbdInvstAmt;
   }

   public String getDsplDfltCrncy()
   {
      return dsplDfltCrncy;
   }

   public void setDsplDfltCrncy(String dsplDfltCrncy)
   {
      this.dsplDfltCrncy = dsplDfltCrncy;
   }
}
