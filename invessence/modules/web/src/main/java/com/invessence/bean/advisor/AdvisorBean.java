package com.invessence.bean.advisor;

import java.io.*;
import java.util.*;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import javax.faces.event.*;
import javax.servlet.http.*;

import com.invessence.constant.Const;
import com.invessence.dao.advisor.*;
import com.invessence.data.*;
import com.invessence.data.advisor.*;
import com.invessence.util.*;
import com.invmodel.Const.InvConst;
import com.invmodel.asset.data.AssetClass;
import com.invmodel.inputData.*;
import com.invmodel.portfolio.data.*;
import org.primefaces.event.*;
import org.primefaces.model.TreeNode;
import org.primefaces.model.chart.*;

import static javax.faces.context.FacesContext.getCurrentInstance;

@ManagedBean(name = "advisorBean")
@SessionScoped
public class AdvisorBean extends AdvisorData implements Serializable
{
   private static final long serialVersionUID = 100001L;
   UserValidation uval = new UserValidation();

   private AdvisorSaveDataDAO saveDAO;
   private AdvisorListDataDAO listDAO;
   private EmailMessage messageText;
   private Boolean displayPieChart = false;
   private Boolean enableTabs = false;
   private Boolean themeChanged = false;

   private PieChartModel pieModel;
   private PieChartModel scpieModel;

   public AdvisorSaveDataDAO getSaveDAO()
   {
      return saveDAO;
   }

   public void setSaveDAO(AdvisorSaveDataDAO saveDAO)
   {
      this.saveDAO = saveDAO;
   }

   public AdvisorListDataDAO getListDAO()
   {
      return listDAO;
   }

   public void setListDAO(AdvisorListDataDAO listDAO)
   {
      this.listDAO = listDAO;
   }

   public EmailMessage getMessageText()
   {
      return messageText;
   }

   public void setMessageText(EmailMessage messageText)
   {
      this.messageText = messageText;
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


   @PostConstruct
   public void init()
   {
      try
      {
         HttpServletRequest req = (HttpServletRequest) getCurrentInstance().getExternalContext().getRequest();
         String userName = req.getRemoteUser();
         Long logonid = (Long) getCurrentInstance().getExternalContext().getSessionMap().get(Const.LOGONID_PARAM);
         if (logonid == null)
         {
            getCurrentInstance().getExternalContext().redirect("/login.xhtml");
         }
         else {
            UserInfoData uid = (UserInfoData)  getCurrentInstance().getExternalContext().getSessionMap().get(Const.USER_INFO);
            setAdvisor(uid.getGroupname()); // Portfolio solves the null issue, or blank issue.
            setLogonid(logonid);
          }
      }
      catch (Exception ex)
      {

      }
   }


   public void refreshChart(SlideEndEvent event)
   {
      //createAssetPlan(this.getInstance());
   }

   public void selectedActionBasket() {
      createPortfolio(this.getInstance());
      this.themeChanged = true;
   }


   public void changeEvent(ValueChangeEvent event)
   {
      String oldValue;
      String newValue;
      try
      {
      }
      catch (Exception ex)
      {
         ex.printStackTrace();
      }
   }

   public String saveProfile()
   {
      try
      {
         if (getAge() != null ||
            getHorizon() != null ||
            getInitialInvestment() != null ||
            getRiskIndex() != null)
         {
            UserInfoData uid = (UserInfoData)  getCurrentInstance().getExternalContext().getSessionMap().get(Const.USER_INFO);
            setAdvisor(uid.getGroupname()); // Portfolio solves the null issue, or blank issue.
            Long logonid = (Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(Const.LOGONID_PARAM);
            // due to reset call, make sure to reset the logonid.
            setLogonid(logonid);
            saveDAO.saveProfile((AdvisorBean) this.getInstance());
            setAdvisorBasket(listDAO.getBasket(getAdvisor()));
            setEnableTabs(true);
            createAssetPlan(getInstance());
            createPortfolio(getInstance());
         }
         else
         {
            FacesContext.getCurrentInstance().addMessage(null,
                                                         new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                                                          "",
                                                                          "Try Again. Please fill all data as required."));

            return "failed";
         }
      }
      catch (Exception ex)
      {

      }
      return "success";
   }

   public void resetAllocation()
   {
      try
      {
         setUserAssetOverride(false);
         createAssetPlan(getInstance());
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
            saveDAO.saveAllocation((AdvisorData) this.getInstance());
            createPortfolio(getInstance());
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

   public void createAssetPlan(ManageGoals goals)
   {
      MsgData data = new MsgData();
      try
      {
         Integer displayYear = 0;
         setDisplayPieChart(true);
         setNumOfAllocation(1);
         setObjective(2);
         setExperience(2);
         setStayInvested(1);
         setDependent(0);
         setRisk("M");
         loadAssetClass();

         if (getAssetData() != null) {
            // Now refresh the pages...
            createPieModel(getAssetData()[displayYear]);
         }

      }
      catch (Exception ex)
      {
         String stackTrace = ex.getMessage();
         data.setSource("Internal");
         data.setSender(Const.MAIL_SENDER);
         data.setReceiver(Const.MAIL_SUPPORT);
         data.setSubject(Const.COMPANY_NAME + " - Error:AdvisorBean.createAssetPlan");
         data.setMsg(messageText.getMessagetext("error.createAssetPlan", new Object[]{stackTrace}));
         messageText.writeMessage("Error", data);
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

   public void createPortfolio(ManageGoals goals)
   {
      AssetClass[] aamc;
      Portfolio[] pfclass;
      MsgData data = new MsgData();
      try
      {
         goals.setNumOfPortfolio(1);
         loadPortfolio();
         // setSubclassDisplayNode(getTreeAssetFilterModel().createNewTreeNodes(getInstance()));   (Treenode not working well)

      }
      catch (Exception ex)
      {
         String stackTrace = ex.getMessage();
         data.setSource("Internal");
         data.setSender(Const.MAIL_SENDER);
         data.setReceiver(Const.MAIL_SUPPORT);
         data.setSubject(Const.COMPANY_NAME + " - Error:AdvisorBean.createPortfolio");
         data.setMsg(messageText.getMessagetext("error.createPortfolio", new Object[]{stackTrace}));
         messageText.writeMessage("Error", data);
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

   public PieChartModel getPieModel()
   {
      if (this.pieModel == null)
      {
         createAssetPlan(getInstance());
      }
      return pieModel;
   }

   public void setPieModel(PieChartModel pieModel)
   {
      this.pieModel = pieModel;
   }

   public void refreshPie()
   {
      setUserAssetOverride(true);
      loadEditableAssetClass(this.getInstance().getAssetData()[getAssetyear()].getAssetclass());
      createPieModel(this.getInstance().getAssetData()[getAssetyear()]);
   }

   private void createPieModel(AssetClass aac)
   {
      String color;
      Integer slices;

      this.pieModel = new PieChartModel();
      slices = aac.getOrderedAsset().size();
      for (int i = 0; i < slices; i++)
      {
         String assetname = aac.getOrderedAsset().get(i);
         String label = assetname + " - " + aac.getAssetRoundedActualWeight(assetname) + "%";
         pieModel.set(label, aac.getAssetRoundedActualWeight(assetname));

         //color = aac.getAssetColor(assetname).replace('#', ' ');
         color = aac.getAssetColor(assetname);
         color.trim();
         if (i == 0)
         {
            seriesColor = color;
         }
         else
         {
            seriesColor = seriesColor + ", " + color;
         }
      }
   }

   public void savePortfolio()
   {
      AssetClass[] aamc;
      Portfolio[] pfclass;
      String key, assetclass, subclass;
      try
      {
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
            createPortfolio(getInstance());
            // Since we are saving theme and advisor, make sure to save user profile correctly.
            if (this.themeChanged) {
               saveProfile();
               this.themeChanged = false;   // reset the flag so that we don't keep saving this data.
               // NOTE: Theme change does not have anything to do with Asset allocation, only advisor.
            }
            saveDAO.saveExcludeSubClass(getInstance());
            saveDAO.savePortfolio(getInstance());

         }

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


   public String testProfile()
   {
      try
      {
         setAge(30);
         setHorizon(35);
         setInitialInvestment(100000);
         setRiskIndex(10);
         createAssetPlan(getInstance());
         createPortfolio(getInstance());
      }
      catch (Exception ex)
      {

      }
      return "success";
   }



   public String onFlowProcess(FlowEvent event)
   {
      String toTab, fromTab;

      try {
         fromTab = event.getOldStep();
         toTab = event.getNewStep();
         System.out.println("Moving to tab ->" + toTab);
         // Only create asset, if we changed values in tab1,2,or 3.  Otherwise skip.
         if (fromTab == null)
            fromTab = "tab1";
         if (fromTab.equalsIgnoreCase("tab1") && toTab.equalsIgnoreCase("tab2")) {
            saveProfile();
            //saveAllocation();
            //savePortfolio();
         }
         else if (fromTab.equalsIgnoreCase("tab2") && toTab.equalsIgnoreCase("tab3")) {
            //saveAllocation();
            savePortfolio();
         }
         else if (fromTab.equalsIgnoreCase("tab3") && toTab.equalsIgnoreCase("tab4")) {
            savePortfolio();
         }
      }

      catch (Exception ex) {
         toTab = "tab1";
      }
      return event.getNewStep();
   }

   public void resetAdvisorBean() {
      setAdvisorRiskIndex(5);
      setDisplayPieChart(false);
      setEnableTabs(false);
      resetAdvisorData();
   }

   public void loadData(Long acctnum) {

      resetAdvisorBean();
      try {
         setAcctnum(acctnum);
         listDAO.getProfileData((AdvisorData) this.getInstance());
         createAssetPlan(getInstance()); // Load default allocation;
         loadEditableAssetClass(listDAO.getAllocation((AdvisorData) this.getInstance()));
         setExcludedSubAsset(listDAO.getExcludedSubclass((AdvisorData) this.getInstance()));
      }
      catch (Exception ex) {

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
         uval.redirect(getIblink(),null);
      }
   }

}