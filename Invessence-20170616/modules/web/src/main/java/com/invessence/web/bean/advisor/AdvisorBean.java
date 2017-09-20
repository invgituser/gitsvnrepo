package com.invessence.web.bean.advisor;

import java.io.*;
import java.util.*;
import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import javax.faces.event.*;
import javax.servlet.http.*;

import com.invessence.emailer.data.MsgData;
import com.invessence.web.bean.consumer.InvessenceCharts;
import com.invessence.web.constant.*;
import com.invessence.converter.SQLData;
import com.invessence.web.dao.advisor.*;
import com.invessence.web.data.advisor.*;
import com.invessence.web.data.common.UserInfoData;
import com.invessence.web.util.*;
import com.invmodel.asset.data.*;
import com.invmodel.inputData.*;
import com.invmodel.portfolio.data.*;
import org.primefaces.model.TreeNode;

import static javax.faces.context.FacesContext.getCurrentInstance;

@ManagedBean(name = "advisorBean")
@SessionScoped
public class AdvisorBean extends AdvisorData implements Serializable
{
   private static final long serialVersionUID = 100001L;
   private String beanAcctnum;
   private InvessenceCharts invessenceCharts = new InvessenceCharts();

   @ManagedProperty("#{advisorListDataDAO}")
   private AdvisorListDataDAO listAdvisorDAO;

   @ManagedProperty("#{advisorSaveDataDAO}")
   private AdvisorSaveDataDAO saveAdvisorDAO;

   @ManagedProperty("#{webutil}")
   private WebUtil webutil;
   public void setWebutil(WebUtil webutil)
   {
      this.webutil = webutil;
   }

   private Boolean displayPieChart = false;
   private Boolean enableTabs = true;
   private Boolean themeChanged = false;
   private Boolean formDirty = false;

   public AdvisorSaveDataDAO getSaveAdvisorDAO()
   {
      return saveAdvisorDAO;
   }

   public void setSaveDAO(AdvisorSaveDataDAO saveDAO)
   {
      this.saveAdvisorDAO = saveDAO;
   }

   public AdvisorListDataDAO getListAdvisorDAO()
   {
      return listAdvisorDAO;
   }

   public void setListAdvisorDAO(AdvisorListDataDAO listAdvisorDAO)
   {
      this.listAdvisorDAO = listAdvisorDAO;
   }

   public String getBeanAcctnum()
   {
      return beanAcctnum;
   }

   public void setBeanAcctnum(String beanAcctnum)
   {
      this.beanAcctnum = beanAcctnum;
   }

   public Boolean getDisplayPieChart()
   {
      return displayPieChart;
   }

   public void setDisplayPieChart(Boolean displayPieChart)
   {
      this.displayPieChart = displayPieChart;
   }

   public Boolean getEnableTabs()
   {
      return enableTabs;
   }

   public void setEnableTabs(Boolean enableTabs)
   {
      this.enableTabs = enableTabs;
   }

   public void preRenderView()
   {

      try {
      if (!FacesContext.getCurrentInstance().isPostback())
      {
            SQLData converter = new SQLData();
            Long acctnum = converter.getLongData(beanAcctnum);
            if (acctnum != null && acctnum > 0L)
               loadData(acctnum);
            else
               resetAdvisorBean();
         }

      }
      catch (Exception e)
      {
         resetAdvisorBean();
      }
   }

   public void selectedActionBasket() {
      getExcludedSubAsset().clear();
      setNumOfPortfolio(1);
      buildPortfolio();
      this.themeChanged = true;
   }


   public void changeEvent(ValueChangeEvent event)
   {
      String oldValue = null;
      String newValue = null;
      if (!formDirty)
      {
         if (event.getNewValue() == null)
         {
            return;
         }

         oldValue = "";
         if (event.getOldValue() != null)
         {
            oldValue = event.getOldValue().toString();
         }

         try
         {
            newValue = event.getNewValue().toString();
            Integer decimalPosition = newValue.indexOf(".0");
            if (decimalPosition > 0)
            {
               newValue = newValue.substring(0, decimalPosition);
            }
         }
         catch (Exception ex)
         {
            newValue = event.getNewValue().toString();
         }
         // This is to ignore all already selected items.
         if (!(oldValue.equals(newValue)))
         {
            this.formDirty = true;
         }
      }
   }

   public Boolean validateProfile() {
      if (getAge() == null ||
         getHorizon() == null ||
         getInitialInvestment() == null ||
         getRiskIndex() == null) {
         FacesContext.getCurrentInstance().addMessage(null,
                                 new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                                  "",
                                                  "Try Again. Please fill all data as required."));

      }
      try {
         UserInfoData uid = (UserInfoData)  getCurrentInstance().getExternalContext().getSessionMap().get(WebConst.USER_INFO);
         setAdvisor(uid.getAdvisor()); // Portfolio solves the null issue, or blank issue.
         Long logonid = (Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(WebConst.LOGONID_PARAM);
         // due to reset call, make sure to reset the logonid.
         setLogonid(logonid);
         if (formDirty) {
            formDirty = false;
            Long newacctnum = saveAdvisorDAO.saveProfile((AdvisorBean) this.getInstance());
            if (newacctnum < 0) {
               FacesContext.getCurrentInstance().addMessage(null,
                                       new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                                        "",
                                                        "Try Again. This user is already registered client."));

               return false;
            }
            else
               setAcctnum(newacctnum);
         }

      }
      catch (Exception ex) {
         FacesContext.getCurrentInstance().addMessage(null,
                                                      new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                                                       "",
                                                                       "System Error: " + ex.getMessage()));
         return false;
      }
      return true;
   }

   public String saveProfile()
   {
      try
      {
         if (formDirty) {
            Boolean validate = validateProfile();
            loadBasketInfo();

            createAssetPortfolio(1);
            formDirty = false;
            enableTabs = false;
         }
      }
      catch (Exception ex)
      {
        ex.printStackTrace();
      }
      return "success";
   }

   public void resetAllocation()
   {
      try
      {
         setUserAssetOverride(false);
         loadBasketInfo();

         createAssetPortfolio(1);
      }
      catch (Exception ex) {

      }
   }

   public String saveAllocation()
   {
      try
      {
         if (getAssetAllocationTotal() == 100.00) {
            // New recreate the new portfolio.
            if (this.formDirty) {
               this.formDirty = false;
               saveAdvisorDAO.saveAllocation((AdvisorData) this.getInstance());
               setNumOfPortfolio(1);
               buildPortfolio();
            }
            return "success";
         }
         else {
            FacesContext.getCurrentInstance().addMessage(null,
                                                         new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                                                          "",
                                                                          "Sum of all asset has to equal to 100%, please adjust."));

            FacesContext.getCurrentInstance().renderResponse();
            return "failed";
         }
      }
      catch (Exception ex)
      {
         ex.printStackTrace();
      }
      return "failed";
   }

   private void createAssetPortfolio(Integer noOfYears) {

      try {
         Integer displayYear = 0;
         setObjective(2);
         setExperience(2);
         setStayInvested(1);
         setDependent(0);
         setNumOfAllocation(noOfYears);
         setNumOfPortfolio(noOfYears);
         buildAssetClass();
         if (getAssetData() != null) {
            // totalAssetClassWeights(getAssetData()[displayYear].getAssetclass(),displayYear);  // True calculation after Portfolio allocated.
            if (getEditableAsset() != null) {
               // Now refresh the pages...
               // charts.createPieModel(getEditableAsset());
            }
/*
            if (getPortfolioData() != null) {
               lineModel = charts.createLineModel(getPortfolioData(), getPortfolioData().length);
            }
*/

         }
      }
      catch (Exception ex) {
         MsgData data = new MsgData();
         String stackTrace = ex.getMessage();
         data.setSource("Internal");
         data.setSender(Const.MAIL_SENDER);
         data.setReceiver(Const.MAIL_SUPPORT);
         data.setSubject(Const.COMPANY_NAME + " - Error:AdvisorBean.createAssetPlan");
         data.setMsg(webutil.getMessageText().buildInternalMessage("error.createAssetPlan", new Object[]{stackTrace}));
         webutil.getMessageText().writeMessage("Error", data);
      }
   }

   private void createCharts() {

      try {
         if (getAssetData() != null) {
            // totalAssetClassWeights(getAssetData()[0].getAssetclass(),0);  // True calculation after Portfolio allocated.
            if (getEditableAsset() != null) {
               // Now refresh the pages...
               //charts.createPieModel(getEditableAsset());
            }
/*
            if (getPortfolioData() != null) {
               lineModel = charts.createLineModel(getPortfolioData(), getPortfolioData().length);
            }
*/

         }
      }
      catch (Exception ex) {
         ex.printStackTrace();
      }
   }


   public void buildExcludeList(TreeNode[] nodes) {
      if(nodes != null && nodes.length > 0) {
         if (getExcludedSubAsset() == null)
            setExcludedSubAsset(new ArrayList<PortfolioSubclass>());
         else
            getExcludedSubAsset().clear();
         for(TreeNode node : nodes) {
            AssetClassFilter acf = (AssetClassFilter) node.getData();
            PortfolioSubclass psc = new PortfolioSubclass(acf.getDatakey(), acf.getParentclass(),acf.getName(),
                                                          acf.getColor(), acf.getWeight(), acf.getValue(),
                                                          true);
            getExcludedSubAsset().add(psc);
         }
      }
   }

   // Asset Allocation Page Data
   private AssetClass[] aamc;

   private String seriesColor;
   private String scseriesColor;

   public String getSeriesColor()
   {
      return seriesColor;
   }

   public void setSeriesColor(String seriesColor)
   {
      this.seriesColor = seriesColor;
   }

   public String getScseriesColor()
   {
      return scseriesColor;
   }

   public void setScseriesColor(String scseriesColor)
   {
      this.scseriesColor = scseriesColor;
   }

   public InvessenceCharts getInvessenceCharts()
   {
      return invessenceCharts;
   }

   public void refreshPie()
   {
      createCharts();
   }

   public void savePortfolio()
   {
      AssetClass[] aamc;
      Portfolio[] pfclass;
      String key, assetclass, subclass;
      try
      {
 /*
         // NOTE:  Currently, this is duplicated.  First we are filtering using Tree.
         // Then we are reloaded excludedList.  We can bypass and create customAllocation.
         // For now, we are doing this to confirm which widget is better (Tree or DataTable).
         // buildExcludeList(getSubclassFilterNode()); (Tree node is now disabled, it is not working well)
         if (getExcludedSubAsset() != null)
         {
            resetCustomAllocation();
            for (int i = 0; i < getExcludedSubAsset().size(); i++)
            {
               assetclass = getExcludedSubAsset().get(i).getParentclass();
               subclass = getExcludedSubAsset().get(i).getSubasset();
               addCustomAllocation(assetclass, subclass, 0.0);
            }
            setNumOfPortfolio(1);
            buildPortfolio();
            // Since we are saving theme and advisor, make sure to save user profile correctly.
*/
         if (this.themeChanged) {
               saveProfile();
               this.themeChanged = false;   // reset the flag so that we don't keep saving this data.
               // NOTE: Basket change does not have anything to do with Asset allocation, only advisor.
               saveAdvisorDAO.saveExcludeSubClass(getInstance());
               saveAdvisorDAO.savePortfolio(getInstance());
         }
/*
         }
*/

      }
      catch (Exception ex)
      {
         ex.printStackTrace();
      }
   }

   public void saveData()
   {
      AssetClass[] aamc;
      Portfolio[] pfclass;
      Map<String, CustomAllocation> excludeList;
      try
      {
         aamc = getAssetData();
         pfclass = getPortfolioData();
         excludeList = getCustomAllocations();
      }
      catch (Exception ex)
      {
         ex.printStackTrace();
      }
   }

   public void resetAdvisorBean() {
      setRiskIndex(5.0);
      setDisplayPieChart(false);
      setEnableTabs(true);
      resetAdvisorData();

      UserInfoData uid = webutil.getUserInfoData();
      if (uid != null) {
         setAdvisor(uid.getAdvisor()); // Portfolio solves the null issue, or blank issue.
         setLogonid(uid.getLogonID());
      }

   }
   public void loadBasketInfo() {
      if (getAccountTaxable())
         setAdvisorBasket(listDAO.getBasket(getAdvisor(), "T"));
      else
         setAdvisorBasket(listDAO.getBasket(getAdvisor(), "R"));
   }

   public void loadData(Long acctnum) {

      resetAdvisorData();
      try {
         setAcctnum(acctnum);
         listDAO.getProfileData((AdvisorData) this.getInstance());
         loadBasketInfo();

         createAssetPortfolio(1);
         formDirty = false;
      }
      catch (Exception ex) {
         ex.printStackTrace();
      }

   }

   private Boolean canOpenAccount = true;

   public void setCanOpenAccount(Boolean canOpenAccount)
   {
      this.canOpenAccount = canOpenAccount;
   }

   public Boolean getCanOpenAccount() {
      return this.canOpenAccount;
   }

   public void forwardToIB() {

      if (getCanOpenAccount()) {
         FacesContext facesContext = FacesContext.getCurrentInstance();
         HttpSession httpSession = (HttpSession)facesContext.getExternalContext().getSession(false);
         httpSession.invalidate();
         webutil.redirect(getIblink(), null);
      }
   }

}